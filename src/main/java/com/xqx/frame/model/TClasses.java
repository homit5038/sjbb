package com.xqx.frame.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 
 * @author yyhua
 * 
 * @since 2015-7-15
 * 
 * @Description 班级实体
 */
@Entity
public class TClasses extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;


	/**
	 * 年级
	 */
	@NotEmpty(message = "年级不能为空")
	@Size(max = 50)
	private String classesname;
 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "classe", cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<TChildren> children;
	
	private String  chargeitem;
	
/*	@ManyToOne
	@JoinColumn(name="gradeid")
	private TGrade grade;*/

	
	@ManyToOne(optional = false)
	@JoinColumn(name = "gradeid",referencedColumnName="id")
	private TGrade grade;
	
		 
	public List<TChildren> getChildren() {
			return children;
		}
	
	public void setChildren(List<TChildren> children) {
			this.children = children;
		}
	
	public String getClassesname() {
		return classesname;
	}
	
	public void setClassesname(String classesname) {
		this.classesname = classesname;
	}
	
	public String getChargeitem() {
		return chargeitem;
	}

	public void setChargeitem(String chargeitem) {
		this.chargeitem = chargeitem;
	}

	public TGrade getGrade() {
		return grade;
	}
	
	public void setGrade(TGrade grade) {
		this.grade = grade;
	}

	
}
