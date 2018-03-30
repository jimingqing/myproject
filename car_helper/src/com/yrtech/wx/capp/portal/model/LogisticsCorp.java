package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * YrLogisticsCorp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yr_logistics_corp")
public class LogisticsCorp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String logtCode;
	private String logtName;
	private Integer porder;
	private String state;
	private String resv;

	// Constructors

	/** default constructor */
	public LogisticsCorp() {
	}

	/** minimal constructor */
	public LogisticsCorp(String logtCode, String logtName, Integer porder,
			String state) {
		this.logtCode = logtCode;
		this.logtName = logtName;
		this.porder = porder;
		this.state = state;
	}

	/** full constructor */
	public LogisticsCorp(String logtCode, String logtName, Integer porder,
			String state, String resv) {
		this.logtCode = logtCode;
		this.logtName = logtName;
		this.porder = porder;
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

	@Column(name = "logt_code", nullable = false, length = 10)
	public String getLogtCode() {
		return this.logtCode;
	}

	public void setLogtCode(String logtCode) {
		this.logtCode = logtCode;
	}

	@Column(name = "logt_name", nullable = false, length = 50)
	public String getLogtName() {
		return this.logtName;
	}

	public void setLogtName(String logtName) {
		this.logtName = logtName;
	}

	@Column(name = "porder", nullable = false)
	public Integer getPorder() {
		return this.porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "resv", length = 50)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}