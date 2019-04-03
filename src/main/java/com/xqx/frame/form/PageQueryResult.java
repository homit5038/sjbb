package com.xqx.frame.form;
import java.util.List;
import org.springframework.data.domain.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author ChengJun
 * @date 2018-10-29  00:57:44
 */

public class PageQueryResult<T> {
	private String msg;
	private String code;
	private boolean success;
	private Long count;
	private List data;

	public PageQueryResult() {
		this(null);
	}

	public PageQueryResult(T result) {
		this(result, "");
	}

	public PageQueryResult(T result, String msg) {
		this(result, msg, true);
	}

	public PageQueryResult(T result, ResponseCodeENUM message) {
		this(result, message.getMessage(), message.isSuccess(), message.getCode());
	}

	public PageQueryResult(T result, String msg, boolean isSuccess) {
		this(result, msg, isSuccess, "0");
	}

	public PageQueryResult(T result, String msg, boolean isSuccess, String code) {
		this.code = code;
		this.msg = msg;
		this.success = isSuccess;
		if (result instanceof Page) {
			this.count = ((Page) result).getTotalElements();
			this.data = ((Page) result).getContent();
		} else if (result instanceof List) {
			this.data = (List) result;
		}
	}

 public  PageQueryResult(Integer page,Integer limit,List<T>objList ){
		 	limit=limit>objList.size()?objList.size():limit;
		 	objList = page*limit>objList.size()?objList.subList((page-1)*limit,objList.size()):objList.subList((page-1)*limit,page*limit);
		 	this.code = "0";
			this.msg = "";
			this.success = true;
			long b = (int)objList.size();
			this.count =b;
			this.data = objList;
		 
		 	 
	 }
	public List getData() {
		return data;
	}

	/**
	 * 设置data，如果传入的是list，则需要手动设置count（数据总条数）
	 *
	 * @param data
	 * 		可接受的值：List or  Page&lt;List&gt;
	 */
	public void setData(List<T> data) {
		if (data instanceof Page) {
			this.count = ((Page) data).getTotalElements();
			this.data = ((Page) data).getContent();
		} else if (data instanceof List) {
			this.data = (List) data;
		}
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	
}
