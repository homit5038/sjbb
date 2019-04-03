package com.xqx.frame.enumeration;

/**
 * 接口类型
 */
public enum ApiType {
	CheckingNo("validByCheckingNo", "通过房屋核验码查询房屋信息"),
	ContractCode("validByContractCode", "通过预销售合同号查询房屋信息"),
	PrintNo("validByPrintNo", "不动产权证查询房屋信息"),
	limitCHeck("limitCHeck", "验证房屋有效性"),
	OryEnterprise("findOryEnterprise", "查询开发企业"),
	EnterpriseProject("findEnterpriseProject", "查询开发企业管理的项目"),
	EnterpriseProjectHouse("findEnterpriseProjectHouse", "查询开发企业管理的项目的房子"),
	CCB_CONTRACT_RECORD_PUSH("CCB_CONTRACT_RECORD_PUSH","建行租赁备案推送"),
	CCB_CONTRACT_RECORD_CANCEL_PUSH("CCB_CONTRACT_RECORD_CANCEL_PUSH","建行注销租赁备案推送");
	
	private String name;
	private String displayName;

	ApiType(String name, String displayName) {
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
