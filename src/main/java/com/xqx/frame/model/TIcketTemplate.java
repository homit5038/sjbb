package com.xqx.frame.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
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
public class TIcketTemplate extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;


	/**
	 * 模板名称
	 */
	
	
	@NotEmpty(message = "名称不能为空")
	@Size(max = 50)
	private String tempname;
	
	/**
	 * 模板收据
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "templates", fetch = FetchType.LAZY)
	private List<TEmporaryTicket> ticket;
	
	
	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	public List<TEmporaryTicket> getTicket() {
		return ticket;
	}

	public void setTicket(List<TEmporaryTicket> ticket) {
		this.ticket = ticket;
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
