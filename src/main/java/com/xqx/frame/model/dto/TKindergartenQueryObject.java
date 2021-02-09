package com.xqx.frame.model.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.xqx.frame.util.PubUtil;


public class TKindergartenQueryObject {
	
	private final static String ORDER_FIELD = "operateDate";
	private final static String ORDER_TYPE = "desc";
	
	/**
	 * 报名内容（培训主题）
	 */
	private String principal;
	
	
	private String loginName;
	
	/**
	 * 查询起始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	
	/**
	 * 查询结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	/**
	 * 幼儿园名称查询
	 */
	private String kindergartenname;
	

	
	/**
	 * 排序方式
	 */
	private String order;
	

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = PubUtil.isEmpty(principal) ? null : principal.trim();
	}

	
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = PubUtil.isEmpty(loginName) ? null : loginName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String getKindergartenname() {
		return kindergartenname;
	}

	public void setKindergartenname(String kindergartenname) {
		this.kindergartenname = PubUtil.isEmpty(kindergartenname) ? null : kindergartenname.trim();
	}

	public String getOrder() {
		return order == null ? ORDER_TYPE : order;
	}

	public void setOrder(String order) {
		this.order = PubUtil.isEmpty(order) ? ORDER_TYPE : order.trim();
	}
	
}
