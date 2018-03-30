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
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private String userCode;
	private String phoneNo;
	private String userName;
	private String userType;
	private String userSex;
	private String certType;
	private String certId;
	private String certPicPath;
	private String userProv;
	private String userCity;
	private String userArea;
	private String userAddr;
	private String userZip;
	private Timestamp openTime;
	private Timestamp signDate;
	private Timestamp cnlDate;
	private String openBankName;
	private String openBankAddr;
	private String openBankCard;
	private String deptName;
	private String position;
	private String email;
	private String loginPwd;
	private String channel;
	private String userState;
	private Timestamp lastLogDate;
	private String logIp;
	private Timestamp lastTime;
	private Timestamp logErrTime;
	private Integer pwdErrCnt;
	private String secType;
	private String secAnswer;
	private Timestamp modPwdTime;
	private Double acctBal;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(Integer merId, String userCode, String phoneNo,
			String userName, String userType, Timestamp openTime,
			String loginPwd, String channel, String userState) {
		this.merId = merId;
		this.userCode = userCode;
		this.phoneNo = phoneNo;
		this.userName = userName;
		this.userType = userType;
		this.openTime = openTime;
		this.loginPwd = loginPwd;
		this.channel = channel;
		this.userState = userState;
	}

	/** full constructor */
	public UserInfo(Integer merId, String userCode, String phoneNo,
			String userName, String userType, String userSex, String certType,
			String certId, String certPicPath, String userProv,
			String userCity, String userArea, String userAddr, String userZip,
			Timestamp openTime, Timestamp signDate, Timestamp cnlDate,
			String openBankName, String openBankAddr, String openBankCard,
			String deptName, String position, String email, String loginPwd,
			String channel, String userState, Timestamp lastLogDate,
			String logIp, Timestamp lastTime, Timestamp logErrTime,
			Integer pwdErrCnt, String secType, String secAnswer,
			Timestamp modPwdTime, Double acctBal) {
		this.merId = merId;
		this.userCode = userCode;
		this.phoneNo = phoneNo;
		this.userName = userName;
		this.userType = userType;
		this.userSex = userSex;
		this.certType = certType;
		this.certId = certId;
		this.certPicPath = certPicPath;
		this.userProv = userProv;
		this.userCity = userCity;
		this.userArea = userArea;
		this.userAddr = userAddr;
		this.userZip = userZip;
		this.openTime = openTime;
		this.signDate = signDate;
		this.cnlDate = cnlDate;
		this.openBankName = openBankName;
		this.openBankAddr = openBankAddr;
		this.openBankCard = openBankCard;
		this.deptName = deptName;
		this.position = position;
		this.email = email;
		this.loginPwd = loginPwd;
		this.channel = channel;
		this.userState = userState;
		this.lastLogDate = lastLogDate;
		this.logIp = logIp;
		this.lastTime = lastTime;
		this.logErrTime = logErrTime;
		this.pwdErrCnt = pwdErrCnt;
		this.secType = secType;
		this.secAnswer = secAnswer;
		this.modPwdTime = modPwdTime;
		this.acctBal = acctBal;
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

	@Column(name = "user_code", nullable = false, length = 20)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "phone_no", nullable = false, length = 11)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "user_name", nullable = false, length = 30)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_type", nullable = false, length = 1)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "user_sex", length = 1)
	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Column(name = "cert_type", length = 2)
	public String getCertType() {
		return this.certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	@Column(name = "cert_id", length = 100)
	public String getCertId() {
		return this.certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	@Column(name = "cert_pic_path", length = 50)
	public String getCertPicPath() {
		return this.certPicPath;
	}

	public void setCertPicPath(String certPicPath) {
		this.certPicPath = certPicPath;
	}

	@Column(name = "user_prov", length = 10)
	public String getUserProv() {
		return this.userProv;
	}

	public void setUserProv(String userProv) {
		this.userProv = userProv;
	}

	@Column(name = "user_city", length = 10)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "user_area", length = 10)
	public String getUserArea() {
		return this.userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	@Column(name = "user_addr", length = 30)
	public String getUserAddr() {
		return this.userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	@Column(name = "user_zip", length = 6)
	public String getUserZip() {
		return this.userZip;
	}

	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}

	@Column(name = "open_time", nullable = false, length = 19)
	public Timestamp getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}

	@Column(name = "sign_date", length = 19)
	public Timestamp getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Timestamp signDate) {
		this.signDate = signDate;
	}

	@Column(name = "cnl_date", length = 19)
	public Timestamp getCnlDate() {
		return this.cnlDate;
	}

	public void setCnlDate(Timestamp cnlDate) {
		this.cnlDate = cnlDate;
	}

	@Column(name = "open_bank_name", length = 20)
	public String getOpenBankName() {
		return this.openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	@Column(name = "open_bank_addr", length = 50)
	public String getOpenBankAddr() {
		return this.openBankAddr;
	}

	public void setOpenBankAddr(String openBankAddr) {
		this.openBankAddr = openBankAddr;
	}

	@Column(name = "open_bank_card", length = 20)
	public String getOpenBankCard() {
		return this.openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}

	@Column(name = "dept_name", length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "position", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "login_pwd", nullable = false, length = 32)
	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	@Column(name = "channel", nullable = false, length = 1)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "user_state", nullable = false, length = 1)
	public String getUserState() {
		return this.userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	@Column(name = "last_log_date", length = 19)
	public Timestamp getLastLogDate() {
		return this.lastLogDate;
	}

	public void setLastLogDate(Timestamp lastLogDate) {
		this.lastLogDate = lastLogDate;
	}

	@Column(name = "log_ip", length = 50)
	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	@Column(name = "last_time", length = 19)
	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "log_err_time", length = 19)
	public Timestamp getLogErrTime() {
		return this.logErrTime;
	}

	public void setLogErrTime(Timestamp logErrTime) {
		this.logErrTime = logErrTime;
	}

	@Column(name = "pwd_err_cnt")
	public Integer getPwdErrCnt() {
		return this.pwdErrCnt;
	}

	public void setPwdErrCnt(Integer pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}

	@Column(name = "sec_type", length = 2)
	public String getSecType() {
		return this.secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	@Column(name = "sec_answer", length = 50)
	public String getSecAnswer() {
		return this.secAnswer;
	}

	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}

	@Column(name = "mod_pwd_time", length = 19)
	public Timestamp getModPwdTime() {
		return this.modPwdTime;
	}

	public void setModPwdTime(Timestamp modPwdTime) {
		this.modPwdTime = modPwdTime;
	}

	@Column(name = "acct_bal", precision = 10)
	public Double getAcctBal() {
		return this.acctBal;
	}

	public void setAcctBal(Double acctBal) {
		this.acctBal = acctBal;
	}

}