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
 * SmsLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sms_log")
public class SmsLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private Integer userId;
	private String phoneNo;
	private String type;
	private String content;
	private String state;
	private String recv;
	private Timestamp sendTime;
	private String resv;

	// Constructors

	/** default constructor */
	public SmsLog() {
	}

	/** minimal constructor */
	public SmsLog(Integer merId, Integer userId, String phoneNo, String type,
			String content, String state, Timestamp sendTime) {
		this.merId = merId;
		this.userId = userId;
		this.phoneNo = phoneNo;
		this.type = type;
		this.content = content;
		this.state = state;
		this.sendTime = sendTime;
	}

	/** full constructor */
	public SmsLog(Integer merId, Integer userId, String phoneNo, String type,
			String content, String state, String recv, Timestamp sendTime,
			String resv) {
		this.merId = merId;
		this.userId = userId;
		this.phoneNo = phoneNo;
		this.type = type;
		this.content = content;
		this.state = state;
		this.recv = recv;
		this.sendTime = sendTime;
		this.resv = resv;
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

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId() {
		return this.merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "phone_no", nullable = false, length = 11)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "type", nullable = false, length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "content", nullable = false, length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "recv", length = 200)
	public String getRecv() {
		return this.recv;
	}

	public void setRecv(String recv) {
		this.recv = recv;
	}

	@Column(name = "send_time", nullable = false, length = 19)
	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "resv", length = 100)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}