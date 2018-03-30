package com.yrtech.wx.capp.portal.vo;

import java.sql.Timestamp;

import com.yrtech.wx.capp.portal.model.MerInfo;

public class MerOperInfoVo {

	private Integer id;
	private Integer merId;
	private String operCode;
	private String operName;
	private String operTel;
	private String operMp;
	private String operEmail;
	private Timestamp createTime;
	private Timestamp lastTime;
	private String operState;
	private String isAdmin;
	private Integer pwdErrCnt;
	private String logIp;
	private Timestamp logTime;
	private String resv;
	
	private String merName;
	private String merCode;
	private String merProv;
	
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
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public String getOperState() {
		return operState;
	}
	public void setOperState(String operState) {
		this.operState = operState;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getResv() {
		return resv;
	}
	public void setResv(String resv) {
		this.resv = resv;
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
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	public String getOperTel() {
		return operTel;
	}
	public void setOperTel(String operTel) {
		this.operTel = operTel;
	}
	public String getOperMp() {
		return operMp;
	}
	public void setOperMp(String operMp) {
		this.operMp = operMp;
	}
	public String getOperEmail() {
		return operEmail;
	}
	public void setOperEmail(String operEmail) {
		this.operEmail = operEmail;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	public String getMerProv() {
		return merProv;
	}
	public void setMerProv(String merProv) {
		this.merProv = merProv;
	}
	
}
