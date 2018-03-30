package com.yrtech.wx.capp.portal.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserAcctSumLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_acct_sum_log")
public class UserAcctSumLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date acctDate;
	private Integer merId;
//	private Integer userId;
	private Integer transCnt;
	private Double transAmt;
	private Double liqAmt;
	private Double feeAmt;
	private String resv;
	private UserInfo userInfo;

	// Constructors

	/** default constructor */
	public UserAcctSumLog() {
	}

	/** minimal constructor */
	public UserAcctSumLog(Date acctDate, Integer merId,
			Integer transCnt, Double transAmt, Double liqAmt, Double feeAmt) {
		this.acctDate = acctDate;
		this.merId = merId;
		this.transCnt = transCnt;
		this.transAmt = transAmt;
		this.liqAmt = liqAmt;
		this.feeAmt = feeAmt;
	}

	/** full constructor */
	public UserAcctSumLog(Date acctDate, Integer merId, Integer userId,
			Integer transCnt, Double transAmt, Double liqAmt, Double feeAmt,
			String resv) {
		this.acctDate = acctDate;
		this.merId = merId;
		this.transCnt = transCnt;
		this.transAmt = transAmt;
		this.liqAmt = liqAmt;
		this.feeAmt = feeAmt;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "acct_date", nullable = false, length = 10)
	public Date getAcctDate() {
		return this.acctDate;
	}

	public void setAcctDate(Date acctDate) {
		this.acctDate = acctDate;
	}

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId() {
		return this.merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	/*@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}*/

	@Column(name = "trans_cnt", nullable = false)
	public Integer getTransCnt() {
		return this.transCnt;
	}

	public void setTransCnt(Integer transCnt) {
		this.transCnt = transCnt;
	}

	@Column(name = "trans_amt", nullable = false, precision = 10)
	public Double getTransAmt() {
		return this.transAmt;
	}

	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}

	@Column(name = "liq_amt", nullable = false, precision = 10)
	public Double getLiqAmt() {
		return this.liqAmt;
	}

	public void setLiqAmt(Double liqAmt) {
		this.liqAmt = liqAmt;
	}

	@Column(name = "fee_amt", nullable = false, precision = 10)
	public Double getFeeAmt() {
		return this.feeAmt;
	}

	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}

	@Column(name = "resv", length = 50)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",unique=true)
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}