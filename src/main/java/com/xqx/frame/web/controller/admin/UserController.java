package com.xqx.frame.web.controller.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.RoleType;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.UserManager;

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
		if (user == null) {
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
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public String validateCode(String fCode, HttpSession session) {
		String result = "no";
		String randcode = (String) session.getAttribute("randcode");
		if (fCode.equals(randcode)) {
			result = "ok";
		}
		return result;
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
	 * @param userType
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
	 * 
	 * @param p
	 * @param userType
	 * @param isAccountNonLocked
	 * @param fLoginName
	 * @param fUserName
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
}
