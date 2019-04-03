package com.xqx.frame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.TUserRole;

public interface UserManager {
	/**
	 * 获取全部用户列表
	 * 
	 * @return
	 */
	public Page<TUser> findUserList(final String name, final String loginName, 
			final String beginTime, final String endTime, final String availability,Pageable pageRequest);


	/**
	 * 根据id获取用户
	 * 
	 * @param id
	 * @return
	 */
	public TUser findUserById(long id);

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return
	 */
	public TUser findTUserByLoginName(String loginName);

	/**
	 * 保存、更改用户
	 * 
	 * @param u
	 * @param role 
	 * @throws ParameterCheckException
	 */
	public void saveUser(TUser u, String role) throws ParameterCheckException;

	/**
	 * 修改密码
	 * 
	 * @param password
	 */
	public void processEditPwd(String password);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param availability 
	 */
	public void deleteUser(long id, Integer availability);

	/**
	 * 锁定用户
	 * 
	 * @param id
	 */
	public void lockedUser(Long id);

	/**
	 * 初始化角色信息
	 * 
	 * @return
	 */
	public List<TUserRole> getRoles();


	/**
	 * 删除权限ID
	 * 
	 * @param userID
	 * @param roleID
	 */
	public void delUserRole(Long userID, Long roleID);

	/**
	 * 添加用户角色
	 * 
	 * @param userID
	 * @param roleNum
	 */
	public void addUserRole(Long userID, String roleNum);


	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return
	 */
	public TUser resePassword(Long id);

}
