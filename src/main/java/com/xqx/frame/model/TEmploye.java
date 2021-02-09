package com.xqx.frame.model;

import com.xqx.frame.security.SecurityUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * 
 * 
 * 
 * @Description 专家实体
 */
@Entity
public class TEmploye extends BaseAuditEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 教师名称
	 */
	@Size(max =10)
	@NotEmpty(message = "姓名不能为空")
	private String employeName;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱地址格式错误")
	@Size(max = 50)
	private String employeEmail;
	
	/**
	 * 所在幼儿园
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "kindergartenId",referencedColumnName="id")
	private TKindergarten kindergarten; 
    
	/**
	 * 工号码
	 */
	@Size(max = 14)
	private String jobNumber;
	
	
	/**
	 * 民族
	 */
	@Size(max =15)
	private String nation;
	
	
	/**
	 * 学历
	 */
	@Size(max =15)
	private String academicQualification;
	
	
	
	
	/**
	 * 性别	
	 */

	//@Enumerated(EnumType.STRING)
	private String sexType;

	
	
	/**
	 * 毕业院校
	 */
	@Size(max =40)
	private String graduateSchool;
	
	/**
	 * 贯籍
	 */
	@Size(max =40)
	private String birthPlace;
	
	
	
	/**
	 * 政治面貌
	 */
	@Size(max =40)
	private String politicalBackground;
	
	
	
	/**
	 * 住址
	 */
	@Size(max =40)
	private String dwellingPlace;
	
	/**
	 * 身份证号码
	 */
	@Size(max = 22)
	private String idcardNumber;


	
	/**
	 * 考勤卡号
	 */
	@Size(max = 22)
	private String workAttendanceCardNumber;
	
	
	
	/**
	 * 头像 
	 */
	
	@Size(max = 100)
	private String photoDir;

	
	/**
	 * 手机号码
	 */
	@Size(max = 14)
	private String phoneNum;
	
	/**
	 * 毕业时间
	 */
	@Temporal(TemporalType.DATE) 
	private Date beginToWorkDate;
	
	/**
	 * 进园时间
	 */

	@Temporal(TemporalType.DATE) 
	private Date inGartenDate;
	
	/**
	 * 生日
	 */
	@Temporal(TemporalType.DATE) 
	private Date birthday;

	public String getEmployeName() {
		return employeName;
	}

	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}

	public String getEmployeEmail() {
		return employeEmail;
	}

	public void setEmployeEmail(String employeEmail) {
		this.employeEmail = employeEmail;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAcademicQualification() {
		return academicQualification;
	}

	public void setAcademicQualification(String academicQualification) {
		this.academicQualification = academicQualification;
	}

	
	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(String politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public String getDwellingPlace() {
		return dwellingPlace;
	}

	public void setDwellingPlace(String dwellingPlace) {
		this.dwellingPlace = dwellingPlace;
	}

	public String getIdcardNumber() {
		return idcardNumber;
	}

	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}

	public String getWorkAttendanceCardNumber() {
		return workAttendanceCardNumber;
	}

	public void setWorkAttendanceCardNumber(String workAttendanceCardNumber) {
		this.workAttendanceCardNumber = workAttendanceCardNumber;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getBeginToWorkDate() {
		return beginToWorkDate;
	}

	public void setBeginToWorkDate(Date beginToWorkDate) {
		this.beginToWorkDate = beginToWorkDate;
	}

	public Date getInGartenDate() {
		return inGartenDate;
	}

	public void setInGartenDate(Date inGartenDate) {
		this.inGartenDate = inGartenDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSexType() {
		return sexType;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	

	public TKindergarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(TKindergarten kindergarten) {
		this.kindergarten = kindergarten;
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
