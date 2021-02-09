package com.xqx.frame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.codehaus.jackson.annotate.JsonBackReference;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.xqx.frame.security.SecurityUtil;

import net.sf.json.JSONObject;
import net.sf.json.groovy.GJson;

/**
 * 
 * @author yyhua
 * 
 * @since 2015-7-15
 * 
 * @Description 年级实体
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id")  
public class TGrade extends BaseAuditEntity {
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
	
	@JsonBackReference
/*	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)*/
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grade", cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<TClasses> classt;
	

	//@JsonBackReference  
	//private Set<TChildren> children;
	
	
    //@JsonIgnoreProperties("grade")
	//@JsonIgnore

	@JsonBackReference
/*	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
	private List<TChildren> children;

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
	
	@Transient
	private JSONObject classed;
	
	
	
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

	public List<TChildren> getChildren() {
		return children;
	}


	public void setChildren(List<TChildren> children) {
		this.children = children;
	}
	
	public Object getClassed() {
		
			JSONArray jsonarr= new JSONArray();
			  List<TClasses> strList = this.classt;	   
			  for (int i = 0; i < strList.size(); i++) {
					JSONObject jsonObj = new JSONObject();
				  jsonObj.put("name", strList.get(i).getClassesname());
				  jsonObj.put("id", strList.get(i).getId());
				  jsonarr.add(jsonObj);
			
		        }
			  jsonarr=jsonarr == null || jsonarr.isEmpty()?null:jsonarr; 
		/*		 if(jsonarr == null || jsonarr.isEmpty() ||  "null".equals(jsonarr)){
				               return null;
				}else {
					return jsonarr;
				}*/
				 return jsonarr;
		}

	public void setClassed(JSONObject classed) {
		this.classed = classed;
	}

	@PrePersist
	public void createAuditInfo() {
		audit(true);
	}
	
	@PreUpdate
	public void updateAuditInfo() {
		audit(false);
	}
	
	@Override
	protected void audit(boolean isCreate) {
		Date now = new Date();
		TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		if (isCreate) {
			setCreateTime(now);
			setCreateUserID(u.getId());
			setCreatorIp(u.getLoginIpAddress());
		}
		setUpdateLastTime(now);
		setUpdateUserID(u.getId());
		setLastUpdaterIp(u.getLoginIpAddress());
	}
	
	
	@Override
	public String toString() {
		return this.getId().toString();
	}

	
}
