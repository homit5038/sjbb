package com.xqx.frame.form;

import java.util.List;

import com.xqx.frame.model.TEmploye;

public class ResultMap<T> {
    private String msg;
    private  T data;
    private  int code;
    private  int count;
	private boolean success;
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public T getData() {
        return getData();
    }
 
    public void setData(T data) {
        this.data = data;
    }
 
    public int getCode() {
        return code;
    }
 
    public void setCode(int code) {
        this.code = code;
    }
 
    public int getCount() {
        return count;
    }
 
    public void setCount(int count) {
        this.count = count;
    }
 
    
    
    
    

	public ResultMap() {
		this(null);
	}

	public ResultMap(T result) {
		this(result, "");
	}

	public ResultMap(T result, String message) {
		this(result, message, true);
	}

	public ResultMap(T result, ResponseCodeENUM message) {
		this(result, message.getMessage(), message.isSuccess());
	}

	public ResultMap(T result, String message, boolean isSuccess) {
		this(result, message, isSuccess, 200);
	}
	public ResultMap(T result, String message, boolean isSuccess, int code) {
		this.code = code;
		this.msg = message;
		
		this.data = result;
		this.success = isSuccess;
	}
    
    public ResultMap(String msg, T result, int code, int count) {
        this.msg = msg;
        this.data = result;
        this.code = code;
        this.count = count;
	}
    
    public ResultMap(String msg,int code,Integer page,Integer limit,T list) {
    	  this.msg = msg;
          this.data = list;
          this.code = code;
          this.count = limit;
    }
     
 
}