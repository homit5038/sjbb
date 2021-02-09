package com.xqx.frame.form;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Wersion
 * @Time 2018-02-06  16:53
 * @Description  主管部门企业信息统计
 */
public class ChildrenQueryVO{

	private String childName;
	private String childSex;
	private Date childInGartenDate; 
	private Date childBirthday;
	private String grade;
	private String classe;
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildSex() {
		return childSex;
	}
	public void setChildSex(String childSex) {
		this.childSex = childSex;
	}
	public Date getChildInGartenDate() {
		return childInGartenDate;
	}
	public void setChildInGartenDate(Date childInGartenDate) {
		this.childInGartenDate = childInGartenDate;
	}
	public Date getChildBirthday() {
		return childBirthday;
	}
	public void setChildBirthday(Date childBirthday) {
		this.childBirthday = childBirthday;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
}
