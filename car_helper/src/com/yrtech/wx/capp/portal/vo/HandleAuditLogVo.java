package com.yrtech.wx.capp.portal.vo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

public class HandleAuditLogVo {
	
	private Integer id;
	private Integer merId;
	private Integer operId;
	private Timestamp handleDate;
	private Timestamp auditDate;
	private String procType;
	private String procStat;
	private String procCont1;
	private String procCont2;
	private String procCont3;
	private String procCont4;
	private String procCont5;
	private String procCont6;
	private String procCont7;
	private String procCont8;
	private String procCont9;
	private String procCont10;
	private String operCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Integer getOperId() {
		return operId;
	}
	public void setOperId(Integer operId) {
		this.operId = operId;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(Timestamp handleDate) {
		this.handleDate = handleDate;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}
	public String getProcType() {
		return procType;
	}
	public void setProcType(String procType) {
		this.procType = procType;
	}
	public String getProcStat() {
		return procStat;
	}
	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}
	public String getProcCont1() {
		return procCont1;
	}
	public void setProcCont1(String procCont1) {
		this.procCont1 = procCont1;
	}
	public String getProcCont2() {
		return procCont2;
	}
	public void setProcCont2(String procCont2) {
		this.procCont2 = procCont2;
	}
	public String getProcCont3() {
		return procCont3;
	}
	public void setProcCont3(String procCont3) {
		this.procCont3 = procCont3;
	}
	public String getProcCont4() {
		return procCont4;
	}
	public void setProcCont4(String procCont4) {
		this.procCont4 = procCont4;
	}
	public String getProcCont5() {
		return procCont5;
	}
	public void setProcCont5(String procCont5) {
		this.procCont5 = procCont5;
	}
	public String getProcCont6() {
		return procCont6;
	}
	public void setProcCont6(String procCont6) {
		this.procCont6 = procCont6;
	}
	public String getProcCont7() {
		return procCont7;
	}
	public void setProcCont7(String procCont7) {
		this.procCont7 = procCont7;
	}
	public String getProcCont8() {
		return procCont8;
	}
	public void setProcCont8(String procCont8) {
		this.procCont8 = procCont8;
	}
	public String getProcCont9() {
		return procCont9;
	}
	public void setProcCont9(String procCont9) {
		this.procCont9 = procCont9;
	}
	public String getProcCont10() {
		return procCont10;
	}
	public void setProcCont10(String procCont10) {
		this.procCont10 = procCont10;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	
}
