package com.xqx.frame.model;

public enum Periodic {

	MONTH("MONTH","按月",""),
	DAY("DAY","按天",""),
	DFDAY("DFDAY","按天退费",""),
	CONSERVATION("CONSERVATION","按保育费",""),
	OTHER("OTHER","其他","");
	
	private String name;
	private String displayName;
	private String describe;
	

	/**
	 * @param name
	 * @param displayName
	 * @param describe
	 */
	private Periodic(String name, String displayName, String describe) {
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
