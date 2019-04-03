package com.xqx.frame.model;

public enum SexType {
	MAN_SEX("MAN_SEX","男",""),
	WOMAN_SEX("WOMAN_SEX","女","");
	
	private String name;
	private String displayName;
	private String describe;
	
	private SexType(String name, String displayName, String describe) {
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
