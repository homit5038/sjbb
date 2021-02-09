package com.xqx.frame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import com.xqx.frame.security.SecurityUtil;


/**
 * 
 * @author homit
 * 
 * @since 2015-7-15
 * 
 * @Description 幼儿园实体
 */

@Entity
@DynamicInsert
@DynamicUpdate
public class TKindergarten extends BaseAuditEntity {


	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 幼儿园名称
	 */
	@NotEmpty(message = "幼儿园名称不能为空")
	@Size(max = 50)
	private String Kindergartenname;
 
	/**
	 * 地址
	 */
	
	private String Addresea;
	/**
	 * 到期时间
	 */
	@Temporal(TemporalType.DATE) 
	private Date Duetime;

	/**
	 * 园长
	 */
	private String Principal;
	/**
	 * qq
	 */
	private String KinderQq;
	
	/*
	 * 联系电话
	 */
	
	private String KinderPhoneNumber;
	/*
	 * 父幼儿园
	 */
	private Integer Fid;

	/**
	 * 是否分园
	 */
	private String fisSubKingdergarten;
	/**
	 * 与系统角色多对多关联
	 */

	@JsonBackReference
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(name = "TUser_TKindergarten_dz", joinColumns = {@JoinColumn(name = "KindergartenID")}, inverseJoinColumns = {@JoinColumn(name = "userID")})
	 private List<TUser> user = new ArrayList<>();
	
	@JsonIgnore
/*	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)*/
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "kindergarten")
	//@NotFound(action = NotFoundAction.IGNORE)
	private List<TEmploye> employe;
	
	
	public String getKindergartenname() {
		return Kindergartenname;
	}
	public void setKindergartenname(String kindergartenname) {
		this.Kindergartenname = kindergartenname;
	}
	public String getAddresea() {
		return Addresea;
	}
	public void setAddresea(String addresea) {
		this.Addresea = addresea;
	}
	public Date getDuetime() {
		return Duetime;
	}
	public void setDuetime(Date duetime) {
		this.Duetime = duetime;
	}
	public String getPrincipal() {
		return Principal;
	}
	public void setPrincipal(String principal) {
		this.Principal = principal;
	}
	public String getKinderQq() {
		return KinderQq;
	}
	public void setKinderQq(String kinderQq) {
		this.KinderQq = kinderQq;
	}
	public String getKinderPhoneNumber() {
		return KinderPhoneNumber;
	}
	public void setKinderPhoneNumber(String kinderPhoneNumber) {
		this.KinderPhoneNumber = kinderPhoneNumber;
	}

	public String getFisSubKingdergarten() {
		return fisSubKingdergarten;
	}
	public void setFisSubKingdergarten(String fisSubKingdergarten) {
		this.fisSubKingdergarten = fisSubKingdergarten;
	}
	public Integer getFid() {
		return Fid;
	}
	public void setFid(Integer fid) {
		this.Fid = fid;
	}

	
	public List<TEmploye> getEmploye() {
		return employe;
	}
	public void setEmploye(List<TEmploye> employe) {
		this.employe = employe;
	}

	public List<TUser> getUser() {
		return user;
	}
	public void setUser(List<TUser> user) {
		this.user = user;
	}
	@PrePersist
	public void createAuditInfo() {
		audit(true);
	}
	
	@PreUpdate
	public void updateAuditInfo() {
		audit(false);
	}
	
	@Override
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
		setLastUpdaterIp(u.getLoginIpAddress());
	}


}
