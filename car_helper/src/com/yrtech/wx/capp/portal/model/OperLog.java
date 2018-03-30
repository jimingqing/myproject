package com.yrtech.wx.capp.portal.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OperLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oper_log")
public class OperLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer operUserId;
	private String operFunc;
	private String operContent;
	private Timestamp operTime;
	private String operState;
	private String userIp;

	// Constructors

	/** default constructor */
	public OperLog() {
	}

	/** full constructor */
	public OperLog(Integer operUserId, String operFunc, String operContent,
			Timestamp operTime, String operState, String userIp) {
		this.operUserId = operUserId;
		this.operFunc = operFunc;
		this.operContent = operContent;
		this.operTime = operTime;
		this.operState = operState;
		this.userIp = userIp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "oper_user_id", nullable = false)
	public Integer getOperUserId() {
		return this.operUserId;
	}

	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}

	@Column(name = "oper_func", nullable = false, length = 20)
	public String getOperFunc() {
		return this.operFunc;
	}

	public void setOperFunc(String operFunc) {
		this.operFunc = operFunc;
	}

	@Column(name = "oper_content", nullable = false, length = 250)
	public String getOperContent() {
		return this.operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}

	@Column(name = "oper_time", nullable = false, length = 19)
	public Timestamp getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}

	@Column(name = "oper_state", nullable = false)
	public String getOperState() {
		return this.operState;
	}

	public void setOperState(String operState) {
		this.operState = operState;
	}

	@Column(name = "user_ip", nullable = false, length = 50)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}