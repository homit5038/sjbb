package com.xqx.frame.service;

import com.xqx.frame.config.Config;
import com.xqx.frame.config.JWTUtil;
import com.xqx.frame.config.auth.CustomUserDetailsServiceImpl;
import com.xqx.frame.dao.TUserDao;
import com.xqx.frame.dao.TUserRoleDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.TUserRole;
import com.xqx.frame.security.RoleType;
import com.xqx.frame.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	TUserDao userDao;
	@Resource
	private JWTUtil jwtUtils;
	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	TUserRoleDao userRoleDao;
	@Resource
	CustomUserDetailsServiceImpl customUserDetailsService;
	@Override
	public Page<TUser> findUserList(final String name, final String loginName,
			final String beginTime, final String endTime, final String availability,Pageable pageRequest) {

		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		final String currentLoginName = user.getLoginName();
        Page<TUser> users = userDao.findAll(new Specification<TUser>(){
            @Override
            public Predicate toPredicate(Root<TUser> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
            	Predicate predicate = cb.conjunction();
                if (!"all".equals(availability) && !StringUtils.isEmpty(availability)) {
					if ("2".equals(availability)) {
						// 锁定
						predicate.getExpressions().add(
								cb.equal(root.<Integer> get("availability"), 2));
					}else if ("1".equals(availability)) {
						// 有效
						predicate.getExpressions().add(
								cb.equal(root.<Integer> get("availability"), 1));

					} else if ("0".equals(availability)) {
						// 无效(已删除)
						predicate.getExpressions().add(
								cb.equal(root.<Integer> get("availability"), 0));
					}

				}else if (StringUtils.isEmpty(availability)||"all".equals(availability)){
					predicate.getExpressions().add(
							cb.notEqual(root.<Integer> get("availability"), 0));
				}
                if(!StringUtils.isEmpty(loginName)){
                	predicate.getExpressions().add(cb.equal(root.get("loginName"), loginName));
                }
                if(!StringUtils.isEmpty(name)){
                	predicate.getExpressions().add(cb.equal(root.get("name"), name));
                }
                if(!StringUtils.isEmpty(currentLoginName)){
                	predicate.getExpressions().add(cb.notEqual(root.<Long>get("loginName"), currentLoginName));
                }
                if (!StringUtils.isEmpty(beginTime)
						&& !StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.between(root.<Date> get("updateLastTime"),
										sdf.parse(beginTime),
										sdf.parse(endTime)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (!StringUtils.isEmpty(beginTime)
						&& StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.between(root.<Date> get("updateLastTime"),
										sdf.parse(beginTime), new Date()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (StringUtils.isEmpty(beginTime)
						&& !StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicate.getExpressions().add(
								cb.between(root.<Date> get("updateLastTime"),
										sdf.parse("2017-03-13 00:00:00"),
										sdf.parse(endTime)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				query.where(predicate);
                return null;
            }
        }, pageRequest);
       return  users;
	}

	@Override
	public TUser findUserById(long id) {
		return userDao.findOne(id);
	}

	@Override
	public TUser findTUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	@Override
	@Transactional
	public void saveUser(TUser user, String role) throws ParameterCheckException {
		if (user != null) {
			// 新建的用户
			if (user.getId() == null) {
				// 判断用户名是否存在
				TUser userExists = userDao.getUserByLoginName(user.getLoginName());
				if (userExists != null) {
					throw new ParameterCheckException("用户已存在");
				}
				user.createAuditInfo();
			} else {
				// 修改已存在的用户
				user.updateAuditInfo();
			}
			// 级联保存用户和角色
			TUser saveUser = userDao.save(user);

			if(saveUser.getId() != null && !StringUtils.isEmpty(role) && role.trim().length() > 0){
				if(!StringUtils.isEmpty(saveUser.getRoles())){
					userRoleDao.delete(saveUser.getRoles());//删除原先的角色
				}
				saveUser.setRoles(null);
		    	String[] roleArr = role.split(",");
		    	Set<TUserRole> userRoles = new HashSet<TUserRole>();
		    	for (String r : roleArr) {
		    		r = r.trim();
		    		RoleType roleType = RoleType.valueOf(r);
		    		if(roleType != null){
		    			TUserRole ur = new TUserRole();
			    		ur.setRole(roleType);
			    		ur.setRoleName(roleType.getDisplayName());
			    		ur.setUser(saveUser);

			    		userRoles.add(ur);
		    		}
				}
		    	userRoleDao.save(userRoles);//保存现在的角色
		    }
		}
	}

	@Override
	@Transactional
	public void deleteUser(long id, Integer availability) {
		TUser user = userDao.findOne(id);
		if(availability != null && (availability == 1 || availability == 0))
		      user.setAvailability(availability);
		userDao.save(user);
	}

	/**
	 * 初始化角色信息
	 *
	 * @return
	 */
	@Override
	public List<TUserRole> getRoles() {
		List<TUserRole> roleList = new ArrayList<TUserRole>();
		TUserRole userRole = null;
		String[] roles = Config.getStringArray("ROLE");
		if (roles.length > 0) {
			for (String role : roles) {
				userRole = new TUserRole();
				userRole.setRoleName(Config.getString(role + "_NAME"));
				userRole.setRole(RoleType.valueOf(role));
				roleList.add(userRole);
			}
		}
		return roleList;
	}

	@Override
	public void lockedUser(Long id) {
		TUser user = userDao.findOne(id);
		if (user != null) {
			if (user.isAccountNonLocked()) {
				user.setAvailability(2);
			} else {
				user.setAvailability(1);
			}
		}
		userDao.save(user);
	}

	@Transactional
	@Override
	public void processEditPwd(String password) {
		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		TUser editUser = null;
		if (user != null) {
			editUser = userDao.findOne(user.getId());
			editUser.setPassword(password);
			userDao.save(editUser);
		}
	}

	@Transactional
	@Override
	public void delUserRole(Long userID, Long roleID) {
		userRoleDao.deleteUserRole(roleID);
	}

	@Transactional
	@Override
	public void addUserRole(Long userID, String roleNum) {
		TUser user = userDao.findOne(userID);

		RoleType role = RoleType.valueOf(roleNum);
		TUserRole userRole = new TUserRole();
		userRole.setRole(role);
		userRole.setRoleName(role.getDescribe());
		userRole.setUser(user);

		userRoleDao.save(userRole);
	}

	@Override
	public TUser resePassword(Long id) {
		TUser user = userDao.findOne(id);

		user.setPassword("123456");
		userDao.save(user);

		return user;
	}
	public List<TUserRole> selectByUserId(Long id) {
		return userRoleDao.selectByUserId(id);
//        return null;
	}

	/**
	 * 功能说明: 登录
	 *
	 * @param username  用户名
	 * @param password  密码
	 * @return com.lwx.dto.Result 返回登录结果
	 * @author 黄邦荣
	 * @date 2019/8/28 0028 16:02
	 */
	@Override
	public Object login(String username, String password) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		try {
		final Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		TUser u = (TUser) authentication.getPrincipal();
//		HttpSession session = request.getSession();
//		session.setAttribute("token",token);

		UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
		String tokenes =  jwtUtils.generateToken(userDetails);


		System.out.println("-------------------"+tokenes);
		Map<String, Object> map = new HashMap<>(1);
		map.put("status",1);
		map.put("Authorization", tokenes);
		map.put("userID", 2);
		map.put("msg","登录成功");
		System.out.println("===============111111111===================="+ principal);
		return map;
		} catch (AuthenticationException e) {
			//logger.error("用户名不存在www！"+e);
			Map<String, Object> map = new HashMap<>(1);
			map.put("msg","登录失败,"+e.getMessage());
			map.put("status",0);
			return map;
		}

	}

}
