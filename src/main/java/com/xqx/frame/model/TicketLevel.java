package com.xqx.frame.model;

public enum TicketLevel {
	
	MONTH("MONTH","一级"),
	SECOND("SECOND","二级"),
	THTEE("THTEE","三级"),
	FOURE("FOURE","四级"),
	FIVE("FIVE","五级");
	/**
		 * @param name
		 * @param displayName
		 */
	private TicketLevel(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
	}
		
	private String name;
	private String displayName;
	  
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
	
	
}
