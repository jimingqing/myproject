package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OperAuthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oper_auth_info")
public class OperAuthInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private Integer operId;
	private Integer authId;
	private String state;
	private String resv;

	// Constructors

	/** default constructor */
	public OperAuthInfo() {
	}

	/** minimal constructor */
	public OperAuthInfo(Integer merId, Integer operId, Integer authId,
			String state) {
		this.merId = merId;
		this.operId = operId;
		this.authId = authId;
		this.state = state;
	}

	/** full constructor */
	public OperAuthInfo(Integer merId, Integer operId, Integer authId,
			String state, String resv) {
		this.merId = merId;
		this.operId = operId;
		this.authId = authId;
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

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId() {
		return this.merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	@Column(name = "oper_id", nullable = false)
	public Integer getOperId() {
		return this.operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}

	@Column(name = "auth_id", nullable = false)
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "resv", length = 60)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}