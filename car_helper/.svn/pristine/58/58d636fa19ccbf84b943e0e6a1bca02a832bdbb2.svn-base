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
 * MerOperInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mer_oper_info")
public class MerOperInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private String operCode;
	private String operPwd;
	private String operName;
	private String operTel;
	private String operMp;
	private String operEmail;
	private Timestamp createTime;
	private Timestamp lastTime;
	private String operState;
	private String isAdmin;
	private String grpId;
	private String logIp;
	private Timestamp logTime;
	private Timestamp logErrTime;
	private Integer pwdErrCnt;
	private String resv;

	// Constructors

	/** default constructor */
	public MerOperInfo() {
	}

	/** minimal constructor */
	public MerOperInfo(Integer merId, String operCode, String operPwd,
			Timestamp createTime, String operState, String isAdmin,
			Integer pwdErrCnt) {
		this.merId = merId;
		this.operCode = operCode;
		this.operPwd = operPwd;
		this.createTime = createTime;
		this.operState = operState;
		this.isAdmin = isAdmin;
		this.pwdErrCnt = pwdErrCnt;
	}

	/** full constructor */
	public MerOperInfo(Integer merId, String operCode, String operPwd,
			String operName, String operTel, String operMp, String operEmail,
			Timestamp createTime, Timestamp lastTime, String operState,
			String isAdmin, String grpId, String logIp, Timestamp logTime,
			Timestamp logErrTime, Integer pwdErrCnt, String resv) {
		this.merId = merId;
		this.operCode = operCode;
		this.operPwd = operPwd;
		this.operName = operName;
		this.operTel = operTel;
		this.operMp = operMp;
		this.operEmail = operEmail;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.operState = operState;
		this.isAdmin = isAdmin;
		this.grpId = grpId;
		this.logIp = logIp;
		this.logTime = logTime;
		this.logErrTime = logErrTime;
		this.pwdErrCnt = pwdErrCnt;
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

	@Column(name = "oper_code", nullable = false, length = 20)
	public String getOperCode() {
		return this.operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	@Column(name = "oper_pwd", nullable = false, length = 32)
	public String getOperPwd() {
		return this.operPwd;
	}

	public void setOperPwd(String operPwd) {
		this.operPwd = operPwd;
	}

	@Column(name = "oper_name", length = 20)
	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Column(name = "oper_tel", length = 20)
	public String getOperTel() {
		return this.operTel;
	}

	public void setOperTel(String operTel) {
		this.operTel = operTel;
	}

	@Column(name = "oper_mp", length = 11)
	public String getOperMp() {
		return this.operMp;
	}

	public void setOperMp(String operMp) {
		this.operMp = operMp;
	}

	@Column(name = "oper_email", length = 30)
	public String getOperEmail() {
		return this.operEmail;
	}

	public void setOperEmail(String operEmail) {
		this.operEmail = operEmail;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_time", length = 19)
	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "oper_state", nullable = false, length = 1)
	public String getOperState() {
		return this.operState;
	}

	public void setOperState(String operState) {
		this.operState = operState;
	}

	@Column(name = "is_admin", nullable = false, length = 1)
	public String getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "grp_id", length = 2)
	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	@Column(name = "log_ip", length = 50)
	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	@Column(name = "log_time", length = 19)
	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	@Column(name = "log_err_time", length = 19)
	public Timestamp getLogErrTime() {
		return this.logErrTime;
	}

	public void setLogErrTime(Timestamp logErrTime) {
		this.logErrTime = logErrTime;
	}

	@Column(name = "pwd_err_cnt", nullable = false)
	public Integer getPwdErrCnt() {
		return this.pwdErrCnt;
	}

	public void setPwdErrCnt(Integer pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}

	@Column(name = "resv", length = 50)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}