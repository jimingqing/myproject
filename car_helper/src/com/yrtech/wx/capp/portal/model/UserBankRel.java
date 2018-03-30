package com.yrtech.wx.capp.portal.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserBankRel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_bank_rel")
public class UserBankRel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private Integer userId;
	private Integer bankId;
	private String bankName;
	private String bankCardNo;
	private String defState;
	private String state;
	private String chkFlag;
	private String chkStat;
	private String protFileName;
	private Timestamp createTime;
	private String resv;

	// Constructors

	/** default constructor */
	public UserBankRel() {
	}

	/** minimal constructor */
	public UserBankRel(Integer id, Integer merId, Integer userId,
			Integer bankId, String bankName, String bankCardNo,
			String defState, String state, String chkFlag, String chkStat,
			String protFileName, Timestamp createTime) {
		this.id = id;
		this.merId = merId;
		this.userId = userId;
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankCardNo = bankCardNo;
		this.defState = defState;
		this.state = state;
		this.chkFlag = chkFlag;
		this.chkStat = chkStat;
		this.protFileName = protFileName;
		this.createTime = createTime;
	}

	/** full constructor */
	public UserBankRel(Integer id, Integer merId, Integer userId,
			Integer bankId, String bankName, String bankCardNo,
			String defState, String state, String chkFlag, String chkStat,
			String protFileName, Timestamp createTime, String resv) {
		this.id = id;
		this.merId = merId;
		this.userId = userId;
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankCardNo = bankCardNo;
		this.defState = defState;
		this.state = state;
		this.chkFlag = chkFlag;
		this.chkStat = chkStat;
		this.protFileName = protFileName;
		this.createTime = createTime;
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

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "bank_id", nullable = false)
	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	@Column(name = "bank_name", nullable = false, length = 50)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_card_no", nullable = false, length = 100)
	public String getBankCardNo() {
		return this.bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	@Column(name = "def_state", nullable = false, length = 1)
	public String getDefState() {
		return this.defState;
	}

	public void setDefState(String defState) {
		this.defState = defState;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "chk_flag", nullable = false, length = 1)
	public String getChkFlag() {
		return this.chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	@Column(name = "chk_stat", nullable = false, length = 1)
	public String getChkStat() {
		return this.chkStat;
	}

	public void setChkStat(String chkStat) {
		this.chkStat = chkStat;
	}

	@Column(name = "prot_file_name", length = 100)
	public String getProtFileName() {
		return this.protFileName;
	}

	public void setProtFileName(String protFileName) {
		this.protFileName = protFileName;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "resv", length = 30)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}