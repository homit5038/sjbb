package com.xqx.frame.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.xqx.frame.security.SecurityUtil;

@Entity
public class TPayedInfo extends BaseAuditEntity {

	private static final long serialVersionUID = -7477170402920751916L;

	/*
	 * 付款方式
	 */

	private String paytype;
	/*
	 *  小票号
	 */
	@Size(max = 255)
	private String flowCode; 
	
	/*
	 * 收款人
	 */
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId",referencedColumnName="id")
	private TUser user;
	
	/*
	 * 找零
	 */
    private BigDecimal chargereturn;		
	/*
	 * 实付金额
	 */
	private BigDecimal chargerealpay;
	/*
	 * 应付金额
	 */
	private BigDecimal chargeshouldpay;
	

	
	/*
	 *小票打印日期
	 */
	@Temporal(TemporalType.DATE) 
	private Date timeb;
	
	/*
	 *付款日期
	 */
	@Temporal(TemporalType.DATE) 
	private Date payableDsate;
	
	/*
	 * 缴费学生关联
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "childId",referencedColumnName="id")
	private TChildren children;
	
	/*
	 * 备注
	 */
	private String remarks;
	
	
	/*
	 * 关联收费项
	 */
	@Size(max = 500)
	private String chargConnection;

	/*
	 * 关联收费项详情
	 */
	@Size(max = 2500)
	private String chargConnectiontext;


	public String getPaytype() {
		return paytype;
	}


	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}


	public String getFlowCode() {
		return flowCode;
	}


	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}


	public TUser getUser() {
		return user;
	}


	public void setUser(TUser user) {
		this.user = user;
	}


	public BigDecimal getChargereturn() {
		return chargereturn;
	}


	public void setChargereturn(BigDecimal chargereturn) {
		this.chargereturn = chargereturn;
	}


	public BigDecimal getChargerealpay() {
		return chargerealpay;
	}


	public void setChargerealpay(BigDecimal chargerealpay) {
		this.chargerealpay = chargerealpay;
	}


	public BigDecimal getChargeshouldpay() {
		return chargeshouldpay;
	}


	public void setChargeshouldpay(BigDecimal chargeshouldpay) {
		this.chargeshouldpay = chargeshouldpay;
	}






	public Date getTimeb() {
		return timeb;
	}


	public void setTimeb(Date timeb) {
		this.timeb = timeb;
	}


	public Date getPayableDsate() {
		return payableDsate;
	}


	public void setPayableDsate(Date payableDsate) {
		this.payableDsate = payableDsate;
	}


	public TChildren getChildren() {
		return children;
	}


	public void setChildren(TChildren children) {
		this.children = children;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	
	public String getChargConnection() {
		return chargConnection;
	}


	public void setChargConnection(String chargConnection) {
		this.chargConnection = chargConnection;
	}

	
	
	
	

	public String getChargConnectiontext() {
		return chargConnectiontext;
	}


	public void setChargConnectiontext(String chargConnectiontext) {
		this.chargConnectiontext = chargConnectiontext;
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
