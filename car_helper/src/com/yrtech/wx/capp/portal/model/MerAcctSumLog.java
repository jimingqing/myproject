package com.yrtech.wx.capp.portal.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AcctSumLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mer_acct_sum_log")
public class MerAcctSumLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date acctDate;
	private Integer merId;
	private Integer transCnt;
	private Double transAmt;
	private Double liqAmt;
	private Double feeAmt;
	private String resv;

	// Constructors

	/** default constructor */
	public MerAcctSumLog() {
	}

	/** minimal constructor */
	public MerAcctSumLog(Integer id, Date acctDate, Integer merId,
			Integer transCnt, Double transAmt, Double liqAmt, Double feeAmt) {
		this.id = id;
		this.acctDate = acctDate;
		this.merId = merId;
		this.transCnt = transCnt;
		this.transAmt = transAmt;
		this.liqAmt = liqAmt;
		this.feeAmt = feeAmt;
	}

	/** full constructor */
	public MerAcctSumLog(Integer id, Date acctDate, Integer merId,
			Integer transCnt, Double transAmt, Double liqAmt, Double feeAmt,
			String resv) {
		this.id = id;
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

}