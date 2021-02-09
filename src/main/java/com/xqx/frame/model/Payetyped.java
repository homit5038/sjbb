package com.xqx.frame.model;

public enum Payetyped {

	CASH("CASH","1","现金"),
	CARD("CARD","2","刷卡"),
	WET("WET","3","微信"),
	PAY("PAY","4","支付宝");
	
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
	
	
	public static Payetyped getByValue(String code) { 
		for (Payetyped resultcode : values()) {
			if (resultcode.getName()==code) { 
				return resultcode; } 
			} 
		return null; 
				
	} 

	
	 public static Payetyped get(int v) {
	        String str = String.valueOf(v);
	        return get(str);
	    }
	 
    public static Payetyped get(String str) {
	        for (Payetyped e : values()) {
	            if(e.toString().equals(str)) {
	                return e;
	            }
	        }
	        return null;
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
	
	public String toString(){

        return this.name.toString();

}
	

}
