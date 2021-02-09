package com.xqx.frame.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.xqx.frame.security.SecurityUtil;

/**
 * 
 * @author yyhua
 *
 * @since 2012-7-15
 *
 * @Description 所有实体的父类，抽象出ID属性
 */
@MappedSuperclass
public class BaseAuditEntity extends BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	private String creatorIp;

	/**
	 * 创建人Id
	 */
	private Long createUserID;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 更新人id
	 */
	private Long updateUserID;

	/**
	 * 更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateLastTime;

	@Size(max = 100)
	private String lastUpdaterIp;

	@PrePersist
	public void updateAuditInfo() {
		if (this.getId() == null) {
			audit(true);
		} else {
			audit(false);
		}
	}

	/**
	 * 记录创建或更新时设置审计信息
	 * 
	 * @param isCreate 是否为新创建记录
	 */
	protected void audit(boolean isCreate) {
		//TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		TUser u = new TUser();
		u.setId((long) 1);
		u.setLoginIpAddress("127.0.0.1");
		Date now = new Date();
		if (isCreate) {
			this.createTime = now;
			//this.createUserID = u.getId();
			this.creatorIp = u.getLoginIpAddress();
		}

		this.updateLastTime = now;
		this.updateUserID = u.getId();
		this.lastUpdaterIp = u.getLoginIpAddress();

	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorIp() {
		return creatorIp;
	}

	public void setCreatorIp(String creatorIp) {
		this.creatorIp = creatorIp;
	}
	@Column(updatable=false)
	public Long getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(Long createUserID) {
		this.createUserID = createUserID;
	}

	public Long getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(Long updateUserID) {
		this.updateUserID = updateUserID;
	}

	public Date getUpdateLastTime() {
		return updateLastTime;
	}

	public void setUpdateLastTime(Date updateLastTime) {
		this.updateLastTime = updateLastTime;
	}

	public String getLastUpdaterIp() {
		return lastUpdaterIp;
	}

	public void setLastUpdaterIp(String lastUpdaterIp) {
		this.lastUpdaterIp = lastUpdaterIp;
	}
}
