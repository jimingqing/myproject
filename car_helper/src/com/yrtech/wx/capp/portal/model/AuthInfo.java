package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AuthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "auth_info")
public class AuthInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer authLevel;
	private Integer parentId;
	private String haveSon;
	private Integer porder;
	private String authDesc;
	private String state;
	private String isDefault;
	private String isToMer;
	private String isDisplay;
	private String action;
	private String resv;

	// Constructors

	/** default constructor */
	public AuthInfo() {
	}

	/** minimal constructor */
	public AuthInfo(Integer authLevel, Integer parentId, String haveSon,
			Integer porder, String authDesc, String state, String isDefault,
			String isToMer, String isDisplay, String action) {
		this.authLevel = authLevel;
		this.parentId = parentId;
		this.haveSon = haveSon;
		this.porder = porder;
		this.authDesc = authDesc;
		this.state = state;
		this.isDefault = isDefault;
		this.isToMer = isToMer;
		this.isDisplay = isDisplay;
		this.action = action;
	}

	/** full constructor */
	public AuthInfo(Integer authLevel, Integer parentId, String haveSon,
			Integer porder, String authDesc, String state, String isDefault,
			String isToMer, String isDisplay, String action, String resv) {
		this.authLevel = authLevel;
		this.parentId = parentId;
		this.haveSon = haveSon;
		this.porder = porder;
		this.authDesc = authDesc;
		this.state = state;
		this.isDefault = isDefault;
		this.isToMer = isToMer;
		this.isDisplay = isDisplay;
		this.action = action;
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

	@Column(name = "auth_level", nullable = false)
	public Integer getAuthLevel() {
		return this.authLevel;
	}

	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}

	@Column(name = "parent_id", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "have_son", nullable = false, length = 1)
	public String getHaveSon() {
		return this.haveSon;
	}

	public void setHaveSon(String haveSon) {
		this.haveSon = haveSon;
	}

	@Column(name = "porder", nullable = false)
	public Integer getPorder() {
		return this.porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
	}

	@Column(name = "auth_desc", nullable = false, length = 20)
	public String getAuthDesc() {
		return this.authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "is_default", nullable = false, length = 1)
	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "is_to_mer", nullable = false, length = 1)
	public String getIsToMer() {
		return this.isToMer;
	}

	public void setIsToMer(String isToMer) {
		this.isToMer = isToMer;
	}

	@Column(name = "is_display", nullable = false, length = 1)
	public String getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Column(name = "action", nullable = false, length = 100)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "resv", length = 30)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}