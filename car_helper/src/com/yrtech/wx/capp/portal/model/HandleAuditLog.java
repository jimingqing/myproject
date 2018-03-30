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
 * HandleAuditLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "handle_audit_log")
public class HandleAuditLog implements java.io.Serializable {

	// Fields

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
	private String procCont11;
	private String procCont12;
	private String procCont13;
	private String procCont14;
	private String procCont15;
	private String procCont16;
	private String procCont17;
	private String procCont18;
	private String procCont19;
	private String procCont20;

	// Constructors

	/** default constructor */
	public HandleAuditLog() {
	}

	/** minimal constructor */
	public HandleAuditLog(Integer merId, Integer operId, Timestamp handleDate,
			String procType, String procStat) {
		this.merId = merId;
		this.operId = operId;
		this.handleDate = handleDate;
		this.procType = procType;
		this.procStat = procStat;
	}

	/** full constructor */
	public HandleAuditLog(Integer merId, Integer operId, Timestamp handleDate,
			Timestamp auditDate, String procType, String procStat,
			String procCont1, String procCont2, String procCont3,
			String procCont4, String procCont5, String procCont6,
			String procCont7, String procCont8, String procCont9,
			String procCont10, String procCont11, String procCont12,
			String procCont13, String procCont14, String procCont15,
			String procCont16, String procCont17, String procCont18,
			String procCont19, String procCont20) {
		this.merId = merId;
		this.operId = operId;
		this.handleDate = handleDate;
		this.auditDate = auditDate;
		this.procType = procType;
		this.procStat = procStat;
		this.procCont1 = procCont1;
		this.procCont2 = procCont2;
		this.procCont3 = procCont3;
		this.procCont4 = procCont4;
		this.procCont5 = procCont5;
		this.procCont6 = procCont6;
		this.procCont7 = procCont7;
		this.procCont8 = procCont8;
		this.procCont9 = procCont9;
		this.procCont10 = procCont10;
		this.procCont11 = procCont11;
		this.procCont12 = procCont12;
		this.procCont13 = procCont13;
		this.procCont14 = procCont14;
		this.procCont15 = procCont15;
		this.procCont16 = procCont16;
		this.procCont17 = procCont17;
		this.procCont18 = procCont18;
		this.procCont19 = procCont19;
		this.procCont20 = procCont20;
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

	@Column(name = "handle_date", nullable = false, length = 19)
	public Timestamp getHandleDate() {
		return this.handleDate;
	}

	public void setHandleDate(Timestamp handleDate) {
		this.handleDate = handleDate;
	}

	@Column(name = "audit_date", length = 19)
	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name = "proc_type", nullable = false, length = 2)
	public String getProcType() {
		return this.procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	@Column(name = "proc_stat", nullable = false, length = 1)
	public String getProcStat() {
		return this.procStat;
	}

	public void setProcStat(String procStat) {
		this.procStat = procStat;
	}

	@Column(name = "proc_cont1", length = 60)
	public String getProcCont1() {
		return this.procCont1;
	}

	public void setProcCont1(String procCont1) {
		this.procCont1 = procCont1;
	}

	@Column(name = "proc_cont2", length = 60)
	public String getProcCont2() {
		return this.procCont2;
	}

	public void setProcCont2(String procCont2) {
		this.procCont2 = procCont2;
	}

	@Column(name = "proc_cont3", length = 60)
	public String getProcCont3() {
		return this.procCont3;
	}

	public void setProcCont3(String procCont3) {
		this.procCont3 = procCont3;
	}

	@Column(name = "proc_cont4", length = 60)
	public String getProcCont4() {
		return this.procCont4;
	}

	public void setProcCont4(String procCont4) {
		this.procCont4 = procCont4;
	}

	@Column(name = "proc_cont5", length = 60)
	public String getProcCont5() {
		return this.procCont5;
	}

	public void setProcCont5(String procCont5) {
		this.procCont5 = procCont5;
	}

	@Column(name = "proc_cont6", length = 60)
	public String getProcCont6() {
		return this.procCont6;
	}

	public void setProcCont6(String procCont6) {
		this.procCont6 = procCont6;
	}

	@Column(name = "proc_cont7", length = 60)
	public String getProcCont7() {
		return this.procCont7;
	}

	public void setProcCont7(String procCont7) {
		this.procCont7 = procCont7;
	}

	@Column(name = "proc_cont8", length = 60)
	public String getProcCont8() {
		return this.procCont8;
	}

	public void setProcCont8(String procCont8) {
		this.procCont8 = procCont8;
	}

	@Column(name = "proc_cont9", length = 60)
	public String getProcCont9() {
		return this.procCont9;
	}

	public void setProcCont9(String procCont9) {
		this.procCont9 = procCont9;
	}

	@Column(name = "proc_cont10", length = 60)
	public String getProcCont10() {
		return this.procCont10;
	}

	public void setProcCont10(String procCont10) {
		this.procCont10 = procCont10;
	}

	@Column(name = "proc_cont11", length = 60)
	public String getProcCont11() {
		return this.procCont11;
	}

	public void setProcCont11(String procCont11) {
		this.procCont11 = procCont11;
	}

	@Column(name = "proc_cont12", length = 60)
	public String getProcCont12() {
		return this.procCont12;
	}

	public void setProcCont12(String procCont12) {
		this.procCont12 = procCont12;
	}

	@Column(name = "proc_cont13", length = 60)
	public String getProcCont13() {
		return this.procCont13;
	}

	public void setProcCont13(String procCont13) {
		this.procCont13 = procCont13;
	}

	@Column(name = "proc_cont14", length = 60)
	public String getProcCont14() {
		return this.procCont14;
	}

	public void setProcCont14(String procCont14) {
		this.procCont14 = procCont14;
	}

	@Column(name = "proc_cont15", length = 60)
	public String getProcCont15() {
		return this.procCont15;
	}

	public void setProcCont15(String procCont15) {
		this.procCont15 = procCont15;
	}

	@Column(name = "proc_cont16", length = 60)
	public String getProcCont16() {
		return this.procCont16;
	}

	public void setProcCont16(String procCont16) {
		this.procCont16 = procCont16;
	}

	@Column(name = "proc_cont17", length = 60)
	public String getProcCont17() {
		return this.procCont17;
	}

	public void setProcCont17(String procCont17) {
		this.procCont17 = procCont17;
	}

	@Column(name = "proc_cont18", length = 60)
	public String getProcCont18() {
		return this.procCont18;
	}

	public void setProcCont18(String procCont18) {
		this.procCont18 = procCont18;
	}

	@Column(name = "proc_cont19", length = 60)
	public String getProcCont19() {
		return this.procCont19;
	}

	public void setProcCont19(String procCont19) {
		this.procCont19 = procCont19;
	}

	@Column(name = "proc_cont20", length = 60)
	public String getProcCont20() {
		return this.procCont20;
	}

	public void setProcCont20(String procCont20) {
		this.procCont20 = procCont20;
	}

}