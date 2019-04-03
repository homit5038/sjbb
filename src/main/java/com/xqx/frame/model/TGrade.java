package com.xqx.frame.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author yyhua
 * 
 * @since 2015-7-15
 * 
 * @Description 年级实体
 */
@Entity
public class TGrade extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;
	
	//@Column(updatable=false)
	//@Transient
	private Integer numbedr;
	/**
	 * 年级
	 */
	@NotEmpty(message = "年级不能为空")
	@Size(max = 50)
	private String gradename;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grade", cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<TClasses> classt;
	
	
	/**
	 * 头像上传
	 */
	
	@Size(max = 100)
	//@NotEmpty(message = "头像不能空啊！")
	//@Column(updatable=false)
	//@Transient
	private String  photopath;
	
	/**
	 * 关联收费项
	 */

	private String  chargeitem;
	
	
	public Integer getNumbedr() {
		return numbedr;
	}

	
	public void setNumbedr(Integer numbedr) {
	
			this.numbedr = numbedr;	
		
		
	}



	
	public String getChargeitem() {
		return chargeitem;
	}


	public void setChargeitem(String chargeitem) {
		this.chargeitem = chargeitem;
	}


	public String getPhotopath() {
		return photopath;
	}


	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}


	public String getGradename() {
		return gradename;
	}


	public void setGradename(String gradename) {
		this.gradename = gradename;
	}


	public List<TClasses> getClasst() {
		return classt;
	}


	public void setClasst(List<TClasses> classt) {
		this.classt = classt;
	}


	
}
