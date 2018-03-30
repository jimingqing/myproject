package com.yrtech.wx.capp.portal.vo;

import java.sql.Timestamp;
import java.util.List;

import com.yrtech.wx.capp.portal.model.UserBankRel;

public class UserInfoVo {
	
	private Integer id;
	private Integer merId;
	private String userCode;
	private String phoneNo;
	private String userName;
	private String certType;
	private String certId;
	private String certPicPath;
	private String userType;
	private String userSex;
	private String userProv;
	private String userArea;
	private String userCity;
	private String userProvName;
	private String userCityName;
	private String userAreaName;
	private String userAddr;
	private String userZip;
	private Timestamp openTime;
	private String deptName;
	private String position;
	private String email;
	private String userState;
	private Integer pwdErrCnt;
	private String merName;
	private String merAddr;
	private String merCode;
	private String corpCode;
	private String merBankName;
	private String merBankAcct;
	private String acctBalStr;
	private List<UserBankRel> bankRels;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserProv() {
		return userProv;
	}
	public void setUserProv(String userProv) {
		this.userProv = userProv;
	}
	public String getUserArea() {
		return userArea;
	}
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserZip() {
		return userZip;
	}
	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}
	public Timestamp getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public Integer getPwdErrCnt() {
		return pwdErrCnt;
	}
	public void setPwdErrCnt(Integer pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerAddr() {
		return merAddr;
	}
	public void setMerAddr(String merAddr) {
		this.merAddr = merAddr;
	}
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getMerBankName() {
		return merBankName;
	}
	public void setMerBankName(String merBankName) {
		this.merBankName = merBankName;
	}
	public String getMerBankAcct() {
		return merBankAcct;
	}
	public void setMerBankAcct(String merBankAcct) {
		this.merBankAcct = merBankAcct;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserProvName() {
		return userProvName;
	}
	public void setUserProvName(String userProvName) {
		this.userProvName = userProvName;
	}
	public String getUserAreaName() {
		return userAreaName;
	}
	public void setUserAreaName(String userAreaName) {
		this.userAreaName = userAreaName;
	}
	public List<UserBankRel> getBankRels() {
		return bankRels;
	}
	public void setBankRels(List<UserBankRel> bankRels) {
		this.bankRels = bankRels;
	}
	public String getAcctBalStr() {
		return acctBalStr;
	}
	public void setAcctBalStr(String acctBalStr) {
		this.acctBalStr = acctBalStr;
	}
	public String getCertPicPath() {
		return certPicPath;
	}
	public void setCertPicPath(String certPicPath) {
		this.certPicPath = certPicPath;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserCityName() {
		return userCityName;
	}
	public void setUserCityName(String userCityName) {
		this.userCityName = userCityName;
	}
	
}
