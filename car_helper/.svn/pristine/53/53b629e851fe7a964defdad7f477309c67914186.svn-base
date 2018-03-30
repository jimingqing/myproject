package com.yrtech.wx.capp.portal.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GrpAuthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "grp_auth_info")
public class GrpAuthInfo implements java.io.Serializable {

	private Integer id;
	private String grpId;
	private Integer authId;
	private String state;
	private String resv;

	// Constructors

	/** default constructor */
	public GrpAuthInfo() {
	}

	/** minimal constructor */
	public GrpAuthInfo(String grpId, Integer authId, String state) {
		this.grpId = grpId;
		this.authId = authId;
		this.state = state;
	}

	/** full constructor */
	public GrpAuthInfo(String grpId, Integer authId, String state, String resv) {
		this.grpId = grpId;
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

	@Column(name = "grp_id", nullable = false, length = 2)
	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
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

	@Column(name = "resv", length = 30)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}
}