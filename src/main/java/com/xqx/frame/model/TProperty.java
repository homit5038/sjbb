/**
 * 
 */
package com.xqx.frame.model;
import java.io.Serializable;

import javax.persistence.Entity;
/**
 *
 * @author Wersion
 *
 * @version 
 *
 * @Time 2017年8月17日
 *
 * @Description 属性表
 *
 */

@Entity
public class TProperty extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;
/*	private static final long serialVersionUID = 1L;*/


	
	private Long fId;
	
	private String fName;
	
	private String fValue;
	
	private Integer fSort;
	
	private String fMemo;

	private String fRemak;

	public TProperty() {
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfValue() {
		return fValue;
	}

	public void setfValue(String fValue) {
		this.fValue = fValue;
	}

	public Integer getfSort() {
		return fSort;
	}

	public void setfSort(Integer fSort) {
		this.fSort = fSort;
	}

	public String getfMemo() {
		return fMemo;
	}

	public void setfMemo(String fMemo) {
		this.fMemo = fMemo;
	}

	public String getfRemak() {
		return fRemak;
	}

	public void setfRemak(String fRemak) {
		this.fRemak = fRemak;
	}
	
}
