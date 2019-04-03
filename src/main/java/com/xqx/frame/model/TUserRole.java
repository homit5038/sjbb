package com.xqx.frame.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.xqx.frame.security.RoleType;

/**
 * 
 * @author yyhua
 * 
 * @since 2015-7-15
 * 
 * @Description 角色实体
 */
@Entity
public class TUserRole extends BaseEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059889841842551568L;

	/**
	 * 用户
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId",referencedColumnName="id")
	private TUser user;

	/**
	 * 角色
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType role;

	/**
	 * 角色名
	 */
	@Size(max = 50)
	private String roleName;

	/**
	 * 返回角色名（接口方法）
	 */
	@Override
	public String getAuthority() {
		return role.getName();
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return this.getRole().getName();
	}

}
