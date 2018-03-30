package com.yrtech.wx.capp.portal.model;
// default package

import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yr_goods")
public class Goods implements java.io.Serializable {

	// Fields

	private Integer id;
	private String goodsName;
	private String goodsCode;
	private Integer merId;
	private String merCode;
	private Integer catId;
	private Integer typeId;
	private Integer brandId;
	private Double mktprice;
	private Double price;
	private String marketable;
	private String unit;
	private Integer store;
	private String state;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Timestamp expDate;
	private String intro;
	private String brief;
	private String resv;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String goodsName, String goodsCode, Integer merId,
			String merCode, Double price, String marketable, Integer store,
			String state, Timestamp createTime) {
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
		this.merId = merId;
		this.merCode = merCode;
		this.price = price;
		this.marketable = marketable;
		this.store = store;
		this.state = state;
		this.createTime = createTime;
	}

	/** full constructor */
	public Goods(String goodsName, String goodsCode, Integer merId,
			String merCode, Integer catId, Integer typeId, Integer brandId,
			Double mktprice, Double price, String marketable, String unit,
			Integer store, String state, Timestamp createTime,
			Timestamp lastTime, Timestamp expDate, String intro, String brief,
			String resv) {
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
		this.merId = merId;
		this.merCode = merCode;
		this.catId = catId;
		this.typeId = typeId;
		this.brandId = brandId;
		this.mktprice = mktprice;
		this.price = price;
		this.marketable = marketable;
		this.unit = unit;
		this.store = store;
		this.state = state;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.expDate = expDate;
		this.intro = intro;
		this.brief = brief;
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

	@Column(name = "goods_name", nullable = false, length = 200)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_code", nullable = false, length = 50)
	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "mer_id", nullable = false, length = 10)
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

	@Column(name = "cat_id")
	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "brand_id")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "mktprice", precision = 10)
	public Double getMktprice() {
		return this.mktprice;
	}

	public void setMktprice(Double mktprice) {
		this.mktprice = mktprice;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "marketable", nullable = false, length = 1)
	public String getMarketable() {
		return this.marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	@Column(name = "unit", length = 2)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "store", nullable = false)
	public Integer getStore() {
		return this.store;
	}

	public void setStore(Integer store) {
		this.store = store;
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

	@Column(name = "exp_date", length = 8)
	public Timestamp getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}

	@Column(name = "intro")
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "brief")
	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Column(name = "resv", length = 100)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}

}