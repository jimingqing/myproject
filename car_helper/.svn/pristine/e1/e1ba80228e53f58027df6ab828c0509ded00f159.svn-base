package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_info")
public class BankInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bankCode;
	private String bankName;
	private String bankShortName;
	private String bankNameEn;
	private String iconName;
	private String state;
	private String resv;

	// Constructors

	/** default constructor */
	public BankInfo() {
	}

	/** minimal constructor */
	public BankInfo(String bankName, String state) {
		this.bankName = bankName;
		this.state = state;
	}

	/** full constructor */
	public BankInfo(String bankCode, String bankName, String bankShortName,
			String bankNameEn, String state, String resv) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankShortName = bankShortName;
		this.bankNameEn = bankNameEn;
		this.state = state;
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

	@Column(name = "bank_code", length = 8)
	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "bank_name", nullable = false, length = 50)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_short_name", length = 20)
	public String getBankShortName() {
		return this.bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	@Column(name = "bank_name_en", length = 50)
	public String getBankNameEn() {
		return this.bankNameEn;
	}

	public void setBankNameEn(String bankNameEn) {
		this.bankNameEn = bankNameEn;
	}

	@Column(name = "state", nullable = false)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "icon_name", length = 30)
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	@Column(name = "resv", length = 30)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}