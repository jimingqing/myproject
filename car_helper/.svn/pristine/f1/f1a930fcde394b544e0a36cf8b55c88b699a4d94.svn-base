package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrderItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ordId;
	private String itemName;
	private Double itemAmt;
	private Integer itemNum;

	// Constructors

	/** default constructor */
	public OrderItem() {
	}

	/** full constructor */
	public OrderItem(Integer ordId, String itemName, Double itemAmt,
			Integer itemNum) {
		this.ordId = ordId;
		this.itemName = itemName;
		this.itemAmt = itemAmt;
		this.itemNum = itemNum;
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

	@Column(name = "ord_id", nullable = false)
	public Integer getOrdId() {
		return this.ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	@Column(name = "item_name", nullable = false, length = 30)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_amt", nullable = false, precision = 10)
	public Double getItemAmt() {
		return this.itemAmt;
	}

	public void setItemAmt(Double itemAmt) {
		this.itemAmt = itemAmt;
	}

	@Column(name = "item_num", nullable = false)
	public Integer getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

}