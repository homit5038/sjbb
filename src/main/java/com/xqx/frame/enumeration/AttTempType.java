package com.xqx.frame.enumeration;

/**
 * 文件模板业类型类型
 */
public enum AttTempType {
	BN_BA("BN_BA", "合同备案业务"),
	BN_GP("BN_GP", "挂牌文件业务"),
	BN_ZJZC("BN_ZJZC", "中介机构注册业务");

	private String name;
	private String displayName;

	AttTempType(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
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
}
