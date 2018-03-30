package com.yrtech.wx.capp.portal.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProvCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prov_city")
public class ProvCity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String areaId;
	private String name;
	private String pid;
	private String areaCode;
	private String levelCode;
	private String levelName;
	private String state;

	// Constructors

	/** default constructor */
	public ProvCity() {
	}

	/** full constructor */
	public ProvCity(String areaId, String name, String pid, String areaCode,
			String levelCode, String levelName, String state) {
		this.areaId = areaId;
		this.name = name;
		this.pid = pid;
		this.areaCode = areaCode;
		this.levelCode = levelCode;
		this.levelName = levelName;
		this.state = state;
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

	@Column(name = "area_id", nullable = false, length = 10)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pid", nullable = false, length = 10)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "area_code", nullable = false, length = 10)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "level_code", nullable = false)
	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	@Column(name = "level_name", nullable = false, length = 10)
	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}