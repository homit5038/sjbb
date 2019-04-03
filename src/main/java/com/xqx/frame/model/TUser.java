package com.xqx.frame.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.xqx.frame.security.SecurityUtil;

/**
 * 
 * @author yyhua
 * 
 * @since 2015-7-15
 * 
 * @Description 用户实体
 */
@Entity
public class TUser extends BaseAuditEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 登录名
	 */
	@Column(unique = true)
	@Pattern(regexp = "([A-Z]|[a-z]|[0-9]|_){1,32}", message = "登录名由1-32位英文、数字或下划线组成")
	@Size(max = 32)
	private String loginName;

	/**
	 * 密码
	 */
	@Size(max = 100)
	private String password;

	/**
	 * 姓名
	 */
	@NotEmpty(message = "姓名不能为空")
	@Size(max = 50)
	private String name;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱地址格式错误")
	@Size(max = 50)
	private String email;
	
	/**
	 * 有效性 1：有效 0：删除 2：锁定
	 */
	private Integer availability;
	
	/**
	 * 部门名称
	 */
	@Size(max = 100)
	private String deptName;
	
	/**
	 * 手机号码
	 */
	@Size(max = 14)
	private String phoneNum;

	/**
	 * 用户角色
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<TUserRole> roles;

	/**
	 * 用于记录用户登录系统时的ip， 不保存到用户表中
	 */
	@Transient
	private String loginIpAddress;

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		this.password = pe.encode(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TUserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TUserRole> roles) {
		this.roles = roles;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getUsername() {
		return loginName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.availability != 2;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}
	
	@PrePersist
	public void createAuditInfo() {
		audit(true);
	}

	@PreUpdate
	public void updateAuditInfo() {
		audit(false);
	}
	
	/**
	 * 记录创建或更新时设置审计信息
	 * 
	 * @param isCreate
	 *            是否为新创建记录
	 */
	protected void audit(boolean isCreate) {
		Date now = new Date();
		TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		if (isCreate) {
			setCreateTime(now);
			setCreateUserID(u.getId());
			setCreatorIp(u.getLoginIpAddress());
		}
		setUpdateLastTime(now);
		setUpdateUserID(u.getId());
		setCreatorIp(u.getLoginIpAddress());

	}
}
