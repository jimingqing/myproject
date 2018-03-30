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
 * GoodsImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yr_goods_img")
public class GoodsImg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer merId;
	private Integer goodsId;
	private String picDesc;
	private String picType;
	private String picUrl;
	private Integer porder;
	private Timestamp createTime;
	private Timestamp lastTime;

	// Constructors

	/** default constructor */
	public GoodsImg() {
	}

	/** minimal constructor */
	public GoodsImg(Integer merId, Integer goodsId, String picType,
			String picUrl, Integer porder, Timestamp createTime) {
		this.merId = merId;
		this.goodsId = goodsId;
		this.picType = picType;
		this.picUrl = picUrl;
		this.porder = porder;
		this.createTime = createTime;
	}

	/** full constructor */
	public GoodsImg(Integer merId, Integer goodsId, String picDesc,
			String picType, String picUrl, Integer porder,
			Timestamp createTime, Timestamp lastTime) {
		this.merId = merId;
		this.goodsId = goodsId;
		this.picDesc = picDesc;
		this.picType = picType;
		this.picUrl = picUrl;
		this.porder = porder;
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

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId() {
		return this.merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	@Column(name = "goods_id", nullable = false)
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "pic_desc", length = 50)
	public String getPicDesc() {
		return this.picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	@Column(name = "pic_type", nullable = false, length = 1)
	public String getPicType() {
		return this.picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	@Column(name = "pic_url", nullable = false, length = 30)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "porder", nullable = false)
	public Integer getPorder() {
		return this.porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
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