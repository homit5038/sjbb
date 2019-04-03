package com.xqx.frame.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import com.xqx.frame.security.SecurityUtil;

/**
 * @author yc
 * 
 * @date 2017-03-10
 * 
 * @Description 日志
 */
@Entity
public class SystemLog extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 日志ip
	 */
	@Size(max = 100)
	private String IP;

	/**
	 * 添加的用户id
	 */
	private Long userID;

	/**
	 * 添加的用户名称
	 */
	@Size(max = 100)
	private String userName;

	/**
	 * 操作表
	 */
	@Size(max = 100)
	private String operTable;
	
	/**
	 * 操作时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date operTime;
	
	/**
	 * 操作数据ID
	 */
	private Long operTableDataID;
	
	/**
	 * 内容
	 */
	@Size(max = 500)
	private String operContent;

	public SystemLog() {
	}

	public SystemLog(String operTable, Long operTableDataID, String operContent) {
		super();
		Date now = new Date();
		TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		setIP(u.getLoginIpAddress());
		setUserID(u.getId());
		setOperTime(now);
		setUserName(u.getName());
		this.operTable = operTable;
		this.operTableDataID = operTableDataID;
		this.operContent = operContent;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperTable() {
		return operTable;
	}

	public void setOperTable(String operTable) {
		this.operTable = operTable;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Long getOperTableDataID() {
		return operTableDataID;
	}

	public void setOperTableDataID(Long operTableDataID) {
		this.operTableDataID = operTableDataID;
	}

	public String getOperContent() {
		return operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}
}
