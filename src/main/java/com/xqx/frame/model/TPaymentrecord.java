package com.xqx.frame.model;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Size;


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
public class TPaymentrecord extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;


	/**
	 * 年级
	 */
	@NotEmpty(message = "年级不能为空")
	@Size(max = 50)
	private String ticketid;
 
	@ManyToOne
	@JoinColumn(name="gradeid")
	private TGrade grade;//街道办事处

	 
		public String getClassesname() {
			return classesname;
		}

		public void setClassesname(String classesname) {
			this.classesname = classesname;
		}

		public TGrade getGrade() {
			return grade;
		}

		public void setGrade(TGrade grade) {
			this.grade = grade;
		}

	
}
