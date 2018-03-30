package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserLimit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_limit")
public class UserLimit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private Integer userId;
	private Integer dayLimitCnt;
	private Double dayLimitAmt;
	private Integer monthLimitCnt;
	private Double monthLimitAmt;
	private String statFlag;
	private String resv;

	// Constructors

	/** default constructor */
	public UserLimit() {
	}

	/** full constructor */
	public UserLimit(Integer merId, Integer userId, Integer dayLimitCnt,
			Double dayLimitAmt, Integer monthLimitCnt, Double monthLimitAmt) {
		this.merId = merId;
		this.userId = userId;
		this.dayLimitCnt = dayLimitCnt;
		this.dayLimitAmt = dayLimitAmt;
		this.monthLimitCnt = monthLimitCnt;
		this.monthLimitAmt = monthLimitAmt;
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

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "day_limit_cnt", nullable = false)
	public Integer getDayLimitCnt() {
		return this.dayLimitCnt;
	}

	public void setDayLimitCnt(Integer dayLimitCnt) {
		this.dayLimitCnt = dayLimitCnt;
	}

	@Column(name = "day_limit_amt", nullable = false, precision = 10)
	public Double getDayLimitAmt() {
		return this.dayLimitAmt;
	}

	public void setDayLimitAmt(Double dayLimitAmt) {
		this.dayLimitAmt = dayLimitAmt;
	}

	@Column(name = "month_limit_cnt", nullable = false)
	public Integer getMonthLimitCnt() {
		return this.monthLimitCnt;
	}

	public void setMonthLimitCnt(Integer monthLimitCnt) {
		this.monthLimitCnt = monthLimitCnt;
	}

	@Column(name = "month_limit_amt", nullable = false, precision = 10)
	public Double getMonthLimitAmt() {
		return this.monthLimitAmt;
	}

	public void setMonthLimitAmt(Double monthLimitAmt) {
		this.monthLimitAmt = monthLimitAmt;
	}

	@Column(name = "stat_flag", nullable = false, length = 1)
	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}
	
	@Column(name = "resv", length = 20)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}
}