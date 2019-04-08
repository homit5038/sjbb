package com.xqx.frame.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.xqx.frame.security.SecurityUtil;

@Entity
public class TChildren extends BaseAuditEntity {
	
	private static final long serialVersionUID = -7477170402920751916L;

	/*
	 * 幼儿姓名
	 */

	private String childName;
	/*
	 *  学号
	 */
	private String childSchoolId; 
	
	/*
	 * 性别
	 */
	private String childSex;

	
	/*
	 * 生日
	 */
	@Temporal(TemporalType.DATE) 
	private Date childBirthday;
	
	/*
	 * 年级
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "childGradeId",referencedColumnName="id")
	private TGrade grade;
	

	
	
	/**
	 * 班级
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "childClassId",referencedColumnName="id")
	private TClasses classe;
	
	/*
	 * 入园时间
	 */
	@Temporal(TemporalType.DATE) 
	private Date childInGartenDate;
	
	/*
	 * 考勤卡ID
	 */
	private String attendanceIdcardNumber;
	
	
	/*
	 * 幼儿身份证号
	 */
	private String childIdcardNumber;
	
	/*
	 * 籍贯
	 */
	
	private String birthPlace;
	
	/*
	 * 名族
	 */
	private String childNation;
	
	
	/*
	 * 独生子女否 1：是, 0：否 
	 */
	private Integer onlyChild;
	
	/*
	 * 家长姓名
	 */
	private String parentName;
	
	
	/*
	 * 家庭住址
	 */
	private String homeAddress;
	
	
	/*
	 * 家长身份证
	 */
	
	private String parentIdcardNumber;
	
	
	/*
	 * 工作单位
	 */
	private String parentWorkAddress;
	
	
	/*
	 * 联系电话
	 */
	
	private String parentPhoneNumber;
	
	/*
	 * 联系电话2
	 */
	private String parentEmail;
	
	
	/*
	 * 是否医保
	 */
	
	private Integer medicalInsurance;
	
	/*
	 * 是否在园
	 */
	
	private Integer status;
	
	/*
	 * 健康档案是否在园
	 */
	private Integer healthRecod;
	
	
	/*
	 * 保育费折扣 例如：5代表5折，5.8代表58折
	 */
	
	private String discount;
	
	
	/*
	 * 照片
	 */
	

	@Size(max = 255)
	private String photoDir;
	
	/*
	 * 备注
	 */
	private String remarks;
	
	
	/*
	 * 关联收费项
	 */
	@Size(max = 500)
	private String chargConnection;



	public String getChildName() {
		return childName;
	}


	public void setChildName(String childName) {
		this.childName = childName;
	}


	public String getChildSchoolId() {
		return childSchoolId;
	}


	public void setChildSchoolId(String childSchoolId) {
		this.childSchoolId = childSchoolId;
	}


	public String getChildSex() {
		return childSex;
	}


	public void setChildSex(String childSex) {
		this.childSex = childSex;
	}


	public Date getChildBirthday() {
		return childBirthday;
	}


	public void setChildBirthday(Date childBirthday) {
		this.childBirthday = childBirthday;
	}




	public TGrade getGrade() {
		return grade;
	}


	public void setGrade(TGrade grade) {
		this.grade = grade;
	}


	public TClasses getClasse() {
		return classe;
	}


	public void setClasse(TClasses classe) {
		this.classe = classe;
	}


	public Date getChildInGartenDate() {
		return childInGartenDate;
	}


	public void setChildInGartenDate(Date childInGartenDate) {
		this.childInGartenDate = childInGartenDate;
	}


	public String getAttendanceIdcardNumber() {
		return attendanceIdcardNumber;
	}


	public void setAttendanceIdcardNumber(String attendanceIdcardNumber) {
		this.attendanceIdcardNumber = attendanceIdcardNumber;
	}


	public String getChildIdcardNumber() {
		return childIdcardNumber;
	}


	public void setChildIdcardNumber(String childIdcardNumber) {
		this.childIdcardNumber = childIdcardNumber;
	}


	public String getBirthPlace() {
		return birthPlace;
	}


	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}


	public String getChildNation() {
		return childNation;
	}


	public void setChildNation(String childNation) {
		this.childNation = childNation;
	}


	public Integer getOnlyChild() {
		return onlyChild;
	}


	public void setOnlyChild(Integer onlyChild) {
		this.onlyChild = onlyChild;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getHomeAddress() {
		return homeAddress;
	}


	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}


	public String getParentIdcardNumber() {
		return parentIdcardNumber;
	}


	public void setParentIdcardNumber(String parentIdcardNumber) {
		this.parentIdcardNumber = parentIdcardNumber;
	}


	public String getParentWorkAddress() {
		return parentWorkAddress;
	}


	public void setParentWorkAddress(String parentWorkAddress) {
		this.parentWorkAddress = parentWorkAddress;
	}


	public String getParentPhoneNumber() {
		return parentPhoneNumber;
	}


	public void setParentPhoneNumber(String parentPhoneNumber) {
		this.parentPhoneNumber = parentPhoneNumber;
	}


	public String getParentEmail() {
		return parentEmail;
	}


	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}


	public Integer getMedicalInsurance() {
		return medicalInsurance;
	}


	public void setMedicalInsurance(Integer medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getHealthRecod() {
		return healthRecod;
	}


	public void setHealthRecod(Integer healthRecod) {
		this.healthRecod = healthRecod;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}


	public String getPhotoDir() {
		return photoDir;
	}

	public void setPhotoDir(String photoDir) {
		if(!photoDir.isEmpty()) {
			this.photoDir = photoDir;
		}else {
			
			
		}
		
		
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	
	public String getChargConnection() {
		return chargConnection;
	}


	public void setChargConnection(String chargConnection) {
		this.chargConnection = chargConnection;
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
	
}
