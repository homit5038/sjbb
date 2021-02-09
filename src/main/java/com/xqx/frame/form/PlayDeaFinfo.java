package com.xqx.frame.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.xqx.frame.model.Payetyped;
import com.xqx.frame.model.TChildren;



public class PlayDeaFinfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cid;
	private Payetyped paytype;
    private String flowCode;
    private BigDecimal chargereturn;
	private BigDecimal chargerealpay;
	private BigDecimal chargeshouldpay;
    private String children;
    private String childSchoolId;
    private String classe;
    private String bzhu;
    private Date payableDsate;

    
    
    
    
    public PlayDeaFinfo(){
    	
    };

/*    public PlayDeaFinfo(Long cid, String paytype, String flowCode, String chargereturn) {
        this.cid = cid;
        this.paytype = paytype;
        this.flowCode = flowCode;
        this.chargereturn = chargereturn;
    }*/
    public PlayDeaFinfo(Long cid,Payetyped paytype,String flowCode, BigDecimal chargereturn,
    		BigDecimal chargerealpay,BigDecimal chargeshouldpay,TChildren children,String bzhu,Date payableDsate) {
        this.cid=cid;
        this.flowCode = flowCode;
        this.paytype=paytype;
        this.chargereturn = chargereturn;
        this.chargerealpay = chargerealpay;
        this.chargeshouldpay = chargeshouldpay;
        this.children=children.getChildName();
        this.childSchoolId=children.getChildSchoolId();
        this.classe=children.getClasse().getClassesname();
        this.bzhu=bzhu;
        this.payableDsate=payableDsate;
    }
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
	this.cid = cid;}

	public Payetyped getPaytype() {
		return paytype;
	}

	public void setPaytype(Payetyped paytype) {
	this.paytype = paytype;}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
	this.flowCode = flowCode;}

	public BigDecimal getChargereturn() {
		return chargereturn;
	}

	public void setChargereturn(BigDecimal chargereturn) {
	this.chargereturn = chargereturn;}
    
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

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
	this.children = children;}
    


	public String getChildSchoolId() {
		return childSchoolId;
	}

	public void setChildSchoolId(String childSchoolId) {
		this.childSchoolId = childSchoolId;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getBzhu() {
		return bzhu;
	}

	public void setBzhu(String bzhu) {
		this.bzhu = bzhu;
	}

	public Date getPayableDsate() {
		return payableDsate;
	}

	public void setPayableDsate(Date payableDsate) {
		this.payableDsate = payableDsate;
	}

	
	
}
