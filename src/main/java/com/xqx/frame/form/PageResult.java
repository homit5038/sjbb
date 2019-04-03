package com.xqx.frame.form;

import com.alibaba.fastjson.JSONArray;

public class PageResult {
    
    private long sEcho;//
    private int iTotalRecords;//实际的行数
    private Long iTotalDisplayRecords;//过滤之后，实际的行数。
    private JSONArray aaData;
	public long getsEcho() {
		return sEcho;
	}
	public void setsEcho(long l) {
		this.sEcho = l;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(long i) {
		this.iTotalDisplayRecords = i;
	}
	public JSONArray getAaData() {
		return aaData;
	}
	public void setAaData(JSONArray aaData) {
		this.aaData = aaData;
	}
    
}