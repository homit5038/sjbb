package com.xqx.frame.security;

public enum RoleType {
	ROLE_ADMIN("ROLE_ADMIN","系统管理员","管理及分配所有用户及权限"),
	ROLE_EXPERT("ROLE_EXPERT","专家管理员","维护专家信息"),
	ROLE_SALES("ROLE_SALES","业务员","抽取专家");

	private String name;
	private String displayName;
	private String describe;
	
	private RoleType(String name, String displayName, String describe) {
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
