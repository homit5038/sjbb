package com.xqx.frame.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author HBR
 * 
 * @since 2015-7-15
 * 
 * @Description 收费项
 */
@Entity
public class TChargeItem extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;


	/**
	 * 收费项目名称
	 */
	
	
	@NotEmpty(message = "名称不能为空")
	@Size(max = 50)
	private String ItemName;
	/**
	 * 收费金额
	 */
	private BigDecimal Amount;
	
	
	/**
	 * 收费周期
	 */
	
	private Periodic Periodic;
	
	/**
	 * 小票等级
	 */
	private TicketLevel TicketLevel;
	
	
	
	public TicketLevel getTicketLevel() {
		return TicketLevel;
	}
	public void setTicketLevel(TicketLevel ticketLevel) {
		TicketLevel = ticketLevel;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public BigDecimal getAmount() {
		return Amount;
	}
	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}
	public Periodic getPeriodic() {
		return Periodic;
	}
	public void setPeriodic(Periodic periodic) {
		Periodic = periodic;
	}
	
	
	@Override
	public String toString() {
		return ItemName+"："+Amount+" ";
	}



	
}
