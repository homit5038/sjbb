package com.xqx.frame.web.controller.admin;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.RoleType;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.UserManager;
import com.xqx.frame.util.Randomcode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 
 * @author yyhua
 * 
 * @date 2015-08-25
 * 
 * @Description 用户Controller
 */

@Controller
@SessionAttributes({ "user", "roleTypes" })
public class UserController {

	@Autowired
	private UserManager userManager;


	@Autowired
	@Qualifier("randomcode")
	private Randomcode randomcode;

	/**
	 * 验证用户名是否存在
	 * 
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/validateLoginName", method = RequestMethod.POST)
	public String validateLoginName(String loginName) {
		String result = "no";
		TUser user = userManager.findTUserByLoginName(loginName);
		//List<TKindergarten> tk=user.getKindergarten();
		if (user == null) {
			result = "ok";
		}
		return result;
	}

	
	
	
	
	
	@RequestMapping("/randomcode")
	public void randomcode(HttpServletRequest request, HttpServletResponse response){
		try {
			ImageIO.write(randomcode.createImage(request), "jpg", response.getOutputStream());  
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	@ResponseBody
	@RequestMapping("/randomcodes")
	public Object randomcodes(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		JSONObject jsonObj = new JSONObject();
		try {

			jsonObj.put("sessionid",(String) session.getId());
			jsonObj.put("img", ImageIO.write(randomcode.createImage(request), "jpg", response.getOutputStream()));
			jsonObj.put("result", "no");
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonObj;
	}
	
	
	
	/**
	 * 验证验证码
	 * 
	 * @param fCode
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public String validateCode(String fCode, HttpServletRequest request,HttpSession session) {
		String result = "no";
		//String randcode = (String) session.getAttribute("randcode");
/*		if (fCode.equals(randcode)) {
			result = "ok";
		}
		*/
		String randcode=randomcode.getCode(request);
		if (randomcode.getCode(request).equalsIgnoreCase(fCode)) {
			result = "ok";
			
		}
		
		return result;
	}
	
	
	
	
	
	/**
	 * 验证验证码
	 * 
	 * @param fCode
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxvalidateCode",method = RequestMethod.POST)
	public Object wxvalidateCode(String fCode, HttpServletRequest request, HttpSession session) {
	
		JSONObject jsonObj = new JSONObject();
		
		String randcode = randomcode.getCode(request);
		jsonObj.put("sessionid",(String) session.getId());
		jsonObj.put("result", "no");

		if (fCode.equals(randcode)) {
			jsonObj.put("result", "ok");
		}
		return jsonObj;
	}
	
	

	/**
	 * ajax验证用户密码
	 * 
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/validatePwd", method = RequestMethod.POST)
	private String validatePwd(String pwd) {
		TUser user = (TUser) SecurityUtil.getCurrentUser();
		TUser tUser = userManager.findUserById(user.getId());
		if (pwd.equals(tUser.getPassword())) {
			return "ok";
		} else {
			return "no";
		}
	}

	/**
	 * 初始化密码修改表单
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}
	/**
	 * 初始化密码修改表单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = RequestMethod.GET)
	public String initEditPwd() {
		return "/admin/user/editPwd";
	}

	/**
	 * 处理密码修改表单
	 * 
	 * @param password
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = RequestMethod.POST)
	public String processEditPwd(String password, Model m) {
		userManager.processEditPwd(password);
		m.addAttribute("editPwd", "ok");
		return "/admin/user/editPwd";
	}

	/**
	 * 初始化新增用户
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/admin/user/new", method = RequestMethod.GET)
	public String initCreatForm(Model m) {
		TUser user = new TUser();
		user.setAvailability(1);
		RoleType[] roles = RoleType.values();
		m.addAttribute("user", user);
		m.addAttribute("roles", roles);
		return "admin/user/createOrUpdate";
	}

	/**
	 * 处理新增用户
	 * 
	 * @param user
	 * @param bind
	 * @param status
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/admin/user/new", method = RequestMethod.POST)
	public String processCreatForm(@ModelAttribute("user") @Valid TUser user,
			String role,BindingResult bind, SessionStatus status, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "admin/user/createOrUpdate";
		}
		userManager.saveUser(user, role);
		status.setComplete();
		return "redirect:/admin/user/list";
	}

	/**
	 * 删除用户
	 * 
	 * @param id

	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/{id}/updateAvail", method = RequestMethod.POST)
	public String updateAvailNews(@PathVariable Long id, Integer availability) {
		userManager.deleteUser(id, availability);
		return "ok";
	}

	/**
	 * 锁定用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/user/lockedUser", method = RequestMethod.POST)
	@ResponseBody
	public String lockedUser(Long id) {
		userManager.lockedUser(id);
		return "ok";
	}

	/**
	 * 初始化修改用户
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable Long id, Model m) {
		TUser user = userManager.findUserById(id);
		m.addAttribute("user", user);
		RoleType[] roles = RoleType.values();
		m.addAttribute("roles", roles);
		m.addAttribute("userRole", user.getRoles());
		return "admin/user/createOrUpdate";
	}

	/**
	 * 处理修改用户
	 * 
	 * @param user
	 * @param bind
	 * @param status
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)
	public String processUpdateForm(@ModelAttribute("user") @Valid TUser user,
			String role, BindingResult bind, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "admin/user/createOrUpdate";
		}
		userManager.saveUser(user, role);
		m.addAttribute("user", user);
		m.addAttribute("result", true);
		return "admin/user/createOrUpdate";
	}

	/**
	 * 用户列表
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
	public String list(@PageableDefault(page = 0, size = 10, sort = { "updateLastTime" }, direction = Direction.DESC) Pageable p,
			Model m) {
		Page<TUser> users = userManager.findUserList(null, null, null, null, null, p);
	
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", users.getTotalElements());
		m.addAttribute("users", users);
		m.addAttribute("method", "get");
		return "admin/user/list";
	}

	/**
	 * 用户查询

	 * @param beginTime
	 * @param endTime
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/admin/user/search", method = RequestMethod.POST)
	public String search(
			@PageableDefault(page = 0, size = 10, sort = { "updateLastTime" }, direction = Direction.DESC) Pageable p,
			String availability, String loginName,
			String name, String beginTime, String endTime, Model m) {
		Page<TUser> users = userManager.findUserList(name, loginName, beginTime, endTime, availability, p);
		m.addAttribute("users", users);
		m.addAttribute("size", p.getPageSize());
		m.addAttribute("page", p.getPageNumber());
		m.addAttribute("num", users.getTotalElements());
		m.addAttribute("availability", availability);
		m.addAttribute("loginName", loginName);
		m.addAttribute("name", name);
		m.addAttribute("beginTime", beginTime);
		m.addAttribute("endTime", endTime);
		m.addAttribute("method", "post");
		return "admin/user/list";
	}

	/**
	 * 获取权限管理页面
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/admin/user/{id}/userRoleManage", method = RequestMethod.GET)
	public String initAuthManage(@PathVariable Long id, Model m) {
		TUser user = userManager.findUserById(id);
		RoleType[] roleTypes = RoleType.values();
		m.addAttribute("roleTypes", roleTypes);
		m.addAttribute("user", user);
		return "admin/user/userRoleManage";
	}

	/**
	 * 获取权限管理页面
	 * 
	 * @param userID
	 * @param roleID
	 * @return
	 * @throws ParameterCheckException
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/{userID}/delUserRole", method = RequestMethod.POST)
	public String delUserRole(@PathVariable Long userID, Long roleID, Model m)
			throws ParameterCheckException {
		userManager.delUserRole(userID,roleID);
		return "OK";
	}

	/**
	 * 获取权限管理页面
	 * 
	 * @param userID
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/user/{userID}/addUserRole", method = RequestMethod.POST)
	public String addUserRole(@PathVariable Long userID, String roleNum, Model m)
			throws ParameterCheckException {
		userManager.addUserRole(userID,roleNum);
		return "OK";
	}
	
	/**
	 * 验证用户名是否存在
	 * 
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/{id}/resePassword", method = RequestMethod.POST)
	public String resePassword(@PathVariable Long id) {
		String result = "no";
		TUser user = userManager.resePassword(id);
		if (user != null) {
			result = "ok";
		}
		return result;
	}

	/**
	 * 功能说明: 登录
	 *
	 * @param username  用户名
	 * @param password  密码
	 * @return com.lwx.dto.Result
	 * @author 黄邦荣
	 * @date 2019/8/21 11:47
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/login",  method = RequestMethod.POST)
	public Object login(HttpServletRequest request,String username,String password, HttpSession session){
		System.out.println("========4444============"+username+password);
		session.setAttribute("userId",userManager.findTUserByLoginName(username));
		String sessionId = request.getSession().getId();

		request.getSession().setAttribute("JSESSIONID", sessionId);
        request.getSession().setAttribute("user", username);
		return  userManager.login(username,password);

	}


}
