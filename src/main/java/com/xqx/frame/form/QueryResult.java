package com.xqx.frame.form;


/**
 * @author ChengJun
 * @date 2018-10-29  00:57:44
 */

public class QueryResult<T> {
	private String message;
	private String code;
	private boolean success;
	private T result;

	public QueryResult() {
		this(null);
	}

	public QueryResult(T result) {
		this(result, "");
	}

	public QueryResult(T result, String message) {
		this(result, message, true);
	}

	public QueryResult(T result, ResponseCodeENUM message) {
		this(result, message.getMessage(), message.isSuccess(), message.getCode());
	}

	public QueryResult(T result, String message, boolean isSuccess) {
		this(result, message, isSuccess, "200");
	}

	public QueryResult(T result, String message, boolean isSuccess, String code) {
		this.code = code;
		this.message = message;
		this.result = result;
		this.success = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
}
