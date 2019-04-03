package com.xqx.frame.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Persister;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.xqx.frame.security.SecurityUtil;

/**
 * 
 * 
 * 
 * @Description 专家实体
 */
@Entity
public class TExpert extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 专家名称
	 */
	@Size(max = 100)
	@NotEmpty(message = "姓名不能为空")
	private String expertName;

	/**
	 * 评估机构
	 */
	@Size(max = 200)
	@NotEmpty(message = "评估机构不能为空")
	private String assessmentStructure;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱地址格式错误")
	@Size(max = 50)
	private String expertEmail;

	/**
	 * 手机号码
	 */
	@Size(max = 14)
	private String phoneNum;
	
	/**
	 * 有效性 ,1：有效, 0：删除 
	 */
	private Integer availability;
	
	/**
	 * 抽取类型（1：必选，0：可选，-1：不可选）
	 */
	private Integer flag;
	/**
	 * 专家类型ID
	 */
	private Long expertTypeID;
	
	/**
	 * 通知状态存于关系表 Expert_Batch_dz 修改请修改该表中的该字段
	 */
	private Integer noteStatus;
	
	/**
	 * 与用户多对多关联
	 */
	@ManyToMany(mappedBy = "experts", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	private List<TExpertBatch> expertBatch = new ArrayList<TExpertBatch>();

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getAssessmentStructure() {
		return assessmentStructure;
	}

	public void setAssessmentStructure(String assessmentStructure) {
		this.assessmentStructure = assessmentStructure;
	}

	public String getExpertEmail() {
		return expertEmail;
	}

	public void setExpertEmail(String expertEmail) {
		this.expertEmail = expertEmail;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getExpertTypeID() {
		return expertTypeID;
	}

	public void setExpertTypeID(Long expertTypeID) {
		this.expertTypeID = expertTypeID;
	}

	public List<TExpertBatch> getExpertBatch() {
		return expertBatch;
	}

	public void setExpertBatch(List<TExpertBatch> expertBatch) {
		this.expertBatch = expertBatch;
	}

	public Integer getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(Integer noteStatus) {
		this.noteStatus = noteStatus;
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
