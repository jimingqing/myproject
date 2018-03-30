package com.yrtech.wx.capp.portal.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WhSignLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wh_sign_log")
public class WhSignLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private String batchId;
	private Timestamp liqDate;
	private Integer transCnt;
	private Double transAmt;
	private Double feeRatio;
	private Double feeAmt;
	private Double liqAmt;
	private String liqType;
	private String resv;

	// Constructors

	/** default constructor */
	public WhSignLog() {
	}

	/** minimal constructor */
	public WhSignLog(Integer id, Integer merId, String batchId,
			Timestamp liqDate, Integer transCnt, Double transAmt,
			Double feeRatio, Double feeAmt, Double liqAmt, String liqType) {
		this.id = id;
		this.merId = merId;
		this.batchId = batchId;
		this.liqDate = liqDate;
		this.transCnt = transCnt;
		this.transAmt = transAmt;
		this.feeRatio = feeRatio;
		this.feeAmt = feeAmt;
		this.liqAmt = liqAmt;
		this.liqType = liqType;
	}

	/** full constructor */
	public WhSignLog(Integer id, Integer merId, String batchId,
			Timestamp liqDate, Integer transCnt, Double transAmt,
			Double feeRatio, Double feeAmt, Double liqAmt, String liqType,
			String resv) {
		this.id = id;
		this.merId = merId;
		this.batchId = batchId;
		this.liqDate = liqDate;
		this.transCnt = transCnt;
		this.transAmt = transAmt;
		this.feeRatio = feeRatio;
		this.feeAmt = feeAmt;
		this.liqAmt = liqAmt;
		this.liqType = liqType;
		this.resv = resv;
	}

	// Property accessors
	@Id
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

	@Column(name = "batch_id", nullable = false, length = 6)
	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Column(name = "liq_date", nullable = false, length = 19)
	public Timestamp getLiqDate() {
		return this.liqDate;
	}

	public void setLiqDate(Timestamp liqDate) {
		this.liqDate = liqDate;
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

	@Column(name = "fee_ratio", nullable = false, precision = 10, scale = 3)
	public Double getFeeRatio() {
		return this.feeRatio;
	}

	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}

	@Column(name = "fee_amt", nullable = false, precision = 10)
	public Double getFeeAmt() {
		return this.feeAmt;
	}

	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}

	@Column(name = "liq_amt", nullable = false, precision = 10)
	public Double getLiqAmt() {
		return this.liqAmt;
	}

	public void setLiqAmt(Double liqAmt) {
		this.liqAmt = liqAmt;
	}

	@Column(name = "liq_type", nullable = false, length = 1)
	public String getLiqType() {
		return this.liqType;
	}

	public void setLiqType(String liqType) {
		this.liqType = liqType;
	}

	@Column(name = "resv", length = 20)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}