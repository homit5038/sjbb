package com.xqx.frame.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;

import com.xqx.frame.security.SecurityUtil;

/**
 * 
 * @author HBR
 * 
 * @since 2015-7-15
 * 
 * @Description 收费项
 */
@Entity
public class TEmporaryTicket extends BaseAuditEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;


	/**
	 * 收费项目名称
	 */
	
	
	@NotEmpty(message = "名称不能为空")
	@Size(max = 50)
	private String chargeName;
	/**
	 * 收费金额
	 */
	private BigDecimal chargeAmount;
	/**
	 * 收据模板
	 */
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "templateid",referencedColumnName="id")
	private TIcketTemplate templates;


	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public TIcketTemplate getTemplates() {
		return templates;
	}

	public void setTemplates(TIcketTemplate templates) {
		this.templates = templates;
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
	
	
	
	
	public String tsting() {
		return this.chargeName+":"+chargeAmount+",";
	}
	
	@Override
	public String toString() {
		return "{chargeName:"+chargeName+",chargeAmount:"+chargeAmount+"}";
	}



	
}
