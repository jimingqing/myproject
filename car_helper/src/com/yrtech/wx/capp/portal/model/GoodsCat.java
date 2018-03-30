package com.yrtech.wx.capp.portal.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GoodsCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yr_goods_cat")
public class GoodsCat implements java.io.Serializable {

	// Fields

	private Integer id;
	private String catName;
	private String catDesc;
	private Integer merId;
	private String merCode;
	private Integer porder;
	private String state;
	private Timestamp createTime;
	private Timestamp lastTime;

	// Constructors

	/** default constructor */
	public GoodsCat() {
	}

	/** minimal constructor */
	public GoodsCat(String catName, Integer merId, String merCode,
			String state, Timestamp createTime) {
		this.catName = catName;
		this.merId = merId;
		this.merCode = merCode;
		this.state = state;
		this.createTime = createTime;
	}

	/** full constructor */
	public GoodsCat(String catName, String catDesc, Integer merId,
			String merCode, String state, Timestamp createTime,
			Timestamp lastTime) {
		this.catName = catName;
		this.catDesc = catDesc;
		this.merId = merId;
		this.merCode = merCode;
		this.state = state;
		this.createTime = createTime;
		this.lastTime = lastTime;
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

	@Column(name = "cat_name", nullable = false, length = 50)
	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Column(name = "cat_desc")
	public String getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId() {
		return this.merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	@Column(name = "mer_code", nullable = false, length = 6)
	public String getMerCode() {
		return this.merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
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

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_time", length = 19)
	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

}