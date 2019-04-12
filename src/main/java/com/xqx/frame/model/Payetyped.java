package com.xqx.frame.model;

public enum Payetyped {

	CASH("现金","1",""),
	CARD("刷卡","2",""),
	WET("微信","3",""),
	PAY("支付宝","4","");
	
	
	private String name;
	private String displayName;
	private String describe;
	

	/**
	 * @param name
	 * @param displayName
	 * @param describe
	 */
	private Payetyped(String name, String displayName, String describe) {
		this.name = name;
		this.displayName = displayName;
		this.describe = describe;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	

	

}
