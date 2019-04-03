package com.xqx.frame.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.xqx.frame.security.SecurityUtil;

/**
 * 
 * 
 * 
 * @Description 专家实体
 */
@Entity
public class TExpertBatch extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 批次名称
	 */
	@Size(max = 100)
	private String batchName;

	/**
	 * 抽取人数
	 */
	@NotNull
	private Integer extractPepleNum;

	/**
	 * 抽取状态
	 */
	private Integer batchStatus;

	/**
	 * 备注
	 */
	@Size(max = 255)
	private String remark;
	
	/**
	 * 有效性 1：有效 0：删除 
	 */
	private Integer availability;
	
	/**
	 * 与系统角色多对多关联
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Expert_Batch_dz", joinColumns = @JoinColumn(name = "batchID"), inverseJoinColumns = @JoinColumn(name = "expertID"))
	@JsonIgnore
	private List<TExpert> experts = new ArrayList<TExpert>();


	public List<TExpert> getExperts() {
		List<TExpert> expertList = new ArrayList<TExpert>();
		for (TExpert expert : this.experts) {
			if (!expertList.contains(expert)) {
				expertList.add(expert);
			}
		}
		return expertList;
	}

	public void setExperts(List<TExpert> experts) {
		this.experts = experts;
	}
	
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Integer getExtractPepleNum() {
		return extractPepleNum;
	}

	public void setExtractPepleNum(Integer extractPepleNum) {
		this.extractPepleNum = extractPepleNum;
	}

	public Integer getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(Integer batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	
	/**
	 * 记录创建或更新时设置审计信息
	 * 
	 * @param isCreate
	 *            是否为新创建记录
	 */
	public void audit(boolean isCreate) {
		Date now = new Date();
		TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		if (isCreate) {
			setCreateTime(now);
			setCreateUserID(u.getId());
			setCreatorIp(u.getLoginIpAddress());
		}
		setUpdateLastTime(now);
		setUpdateUserID(u.getId());
		setCreatorIp(u.getLoginIpAddress());

	}

}
