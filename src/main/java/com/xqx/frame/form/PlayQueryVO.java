package com.xqx.frame.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;


/**
 * @author Wersion
 * @Time 2018-02-06  16:53
 * @Description  主管部门企业信息统计
 */
public class PlayQueryVO implements Serializable {

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
	



	
	
}
