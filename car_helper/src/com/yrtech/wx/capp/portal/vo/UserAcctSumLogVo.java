package com.yrtech.wx.capp.portal.vo;

import java.util.Date;

public class UserAcctSumLogVo {

	private Integer id;
	private Date acctDate;
	private Integer merId;
	private Integer userId;
	private String userName;
	private String userCode;
	private Integer transCnt;
	private Double transAmt;
	private Double liqAmt;
	private Double feeAmt;
	private String resv;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getAcctDate() {
		return acctDate;
	}
	public void setAcctDate(Date acctDate) {
		this.acctDate = acctDate;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTransCnt() {
		return transCnt;
	}
	public void setTransCnt(Integer transCnt) {
		this.transCnt = transCnt;
	}
	public Double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}
	public Double getLiqAmt() {
		return liqAmt;
	}
	public void setLiqAmt(Double liqAmt) {
		this.liqAmt = liqAmt;
	}
	public Double getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getResv() {
		return resv;
	}
	public void setResv(String resv) {
		this.resv = resv;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
