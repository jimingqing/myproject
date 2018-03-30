package com.yrtech.wx.capp.portal.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MerInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mer_info", catalog = "ysf")
public class MerInfo implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String merCode;
	private String unMerId;
	private String merName;
	private String merProv;
	private String merCity;
	private String merArea;
	private String corpCode;
	private String merBankName;
	private String merBankAcct;
	private String regCode;
	private String merAddr;
	private String merZip;
	private String merState;
	private Timestamp openTime;
	private Timestamp lastTime;
	private String isRealWh;
	private String isSendSms;
	private Integer dayTransCnt;
	private Double oneLimitAmt;
	private Double totalLimitAmt;
	private String merContName;
	private String merContTel;
	private String merContMp;
	private String merContEmail;
	private String merContRole;
	private String cerFile;
	private String pfxFile;
	private String pfxPass;
	private String unUserName;
	private String unUserPass;
	private String recv;
	private String logo;
	private Double feeAmt;

	// Constructors

	/** default constructor */
	public MerInfo()
	{
	}

	/** minimal constructor */
	public MerInfo(String merCode, String merName, String merProv)
	{
		this.merCode = merCode;
		this.merName = merName;
		this.merProv = merProv;
	}

	/** full constructor */
	public MerInfo(String merCode, String unMerId, String merName,
			String merProv, String merCity, String merArea, String corpCode,
			String merBankName, String merBankAcct, String regCode,
			String merAddr, String merZip, String merState,
			Timestamp openTime, Timestamp lastTime, String isRealWh,
			String isSendSms, Integer dayTransCnt, Double oneLimitAmt,
			Double totalLimitAmt, String merContName, String merContTel,
			String merContMp, String merContEmail, String merContRole,
			String cerFile, String pfxFile, String pfxPass,
			String unUserName, String unUserPass, String recv, String logo,
			Double feeAmt)
	{
		this.merCode = merCode;
		this.unMerId = unMerId;
		this.merName = merName;
		this.merProv = merProv;
		this.merCity = merCity;
		this.merArea = merArea;
		this.corpCode = corpCode;
		this.merBankName = merBankName;
		this.merBankAcct = merBankAcct;
		this.regCode = regCode;
		this.merAddr = merAddr;
		this.merZip = merZip;
		this.merState = merState;
		this.openTime = openTime;
		this.lastTime = lastTime;
		this.isRealWh = isRealWh;
		this.isSendSms = isSendSms;
		this.dayTransCnt = dayTransCnt;
		this.oneLimitAmt = oneLimitAmt;
		this.totalLimitAmt = totalLimitAmt;
		this.merContName = merContName;
		this.merContTel = merContTel;
		this.merContMp = merContMp;
		this.merContEmail = merContEmail;
		this.merContRole = merContRole;
		this.cerFile = cerFile;
		this.pfxFile = pfxFile;
		this.pfxPass = pfxPass;
		this.unUserName = unUserName;
		this.unUserPass = unUserPass;
		this.recv = recv;
		this.logo = logo;
		this.feeAmt = feeAmt;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	@Column(name = "mer_code", nullable = false, length = 6)
	public String getMerCode()
	{
		return this.merCode;
	}

	public void setMerCode( String merCode )
	{
		this.merCode = merCode;
	}

	@Column(name = "un_mer_id", length = 20)
	public String getUnMerId()
	{
		return this.unMerId;
	}

	public void setUnMerId( String unMerId )
	{
		this.unMerId = unMerId;
	}

	@Column(name = "mer_name", nullable = false, length = 50)
	public String getMerName()
	{
		return this.merName;
	}

	public void setMerName( String merName )
	{
		this.merName = merName;
	}

	@Column(name = "mer_prov", nullable = false, length = 10)
	public String getMerProv()
	{
		return this.merProv;
	}

	public void setMerProv( String merProv )
	{
		this.merProv = merProv;
	}

	@Column(name = "mer_city", length = 10)
	public String getMerCity()
	{
		return this.merCity;
	}

	public void setMerCity( String merCity )
	{
		this.merCity = merCity;
	}

	@Column(name = "mer_area", length = 10)
	public String getMerArea()
	{
		return this.merArea;
	}

	public void setMerArea( String merArea )
	{
		this.merArea = merArea;
	}

	@Column(name = "corp_code", length = 20)
	public String getCorpCode()
	{
		return this.corpCode;
	}

	public void setCorpCode( String corpCode )
	{
		this.corpCode = corpCode;
	}

	@Column(name = "mer_bank_name", length = 30)
	public String getMerBankName()
	{
		return this.merBankName;
	}

	public void setMerBankName( String merBankName )
	{
		this.merBankName = merBankName;
	}

	@Column(name = "mer_bank_acct", length = 30)
	public String getMerBankAcct()
	{
		return this.merBankAcct;
	}

	public void setMerBankAcct( String merBankAcct )
	{
		this.merBankAcct = merBankAcct;
	}

	@Column(name = "reg_code", length = 30)
	public String getRegCode()
	{
		return this.regCode;
	}

	public void setRegCode( String regCode )
	{
		this.regCode = regCode;
	}

	@Column(name = "mer_addr", length = 30)
	public String getMerAddr()
	{
		return this.merAddr;
	}

	public void setMerAddr( String merAddr )
	{
		this.merAddr = merAddr;
	}

	@Column(name = "mer_zip", length = 6)
	public String getMerZip()
	{
		return this.merZip;
	}

	public void setMerZip( String merZip )
	{
		this.merZip = merZip;
	}

	@Column(name = "mer_state", length = 1)
	public String getMerState()
	{
		return this.merState;
	}

	public void setMerState( String merState )
	{
		this.merState = merState;
	}

	@Column(name = "open_time", length = 19)
	public Timestamp getOpenTime()
	{
		return this.openTime;
	}

	public void setOpenTime( Timestamp openTime )
	{
		this.openTime = openTime;
	}

	@Column(name = "last_time", length = 19)
	public Timestamp getLastTime()
	{
		return this.lastTime;
	}

	public void setLastTime( Timestamp lastTime )
	{
		this.lastTime = lastTime;
	}

	@Column(name = "is_real_wh", length = 1)
	public String getIsRealWh()
	{
		return this.isRealWh;
	}

	public void setIsRealWh( String isRealWh )
	{
		this.isRealWh = isRealWh;
	}

	@Column(name = "is_send_sms", length = 1)
	public String getIsSendSms()
	{
		return this.isSendSms;
	}

	public void setIsSendSms( String isSendSms )
	{
		this.isSendSms = isSendSms;
	}

	@Column(name = "day_trans_cnt")
	public Integer getDayTransCnt()
	{
		return this.dayTransCnt;
	}

	public void setDayTransCnt( Integer dayTransCnt )
	{
		this.dayTransCnt = dayTransCnt;
	}

	@Column(name = "one_limit_amt", precision = 10)
	public Double getOneLimitAmt()
	{
		return this.oneLimitAmt;
	}

	public void setOneLimitAmt( Double oneLimitAmt )
	{
		this.oneLimitAmt = oneLimitAmt;
	}

	@Column(name = "total_limit_amt", precision = 10)
	public Double getTotalLimitAmt()
	{
		return this.totalLimitAmt;
	}

	public void setTotalLimitAmt( Double totalLimitAmt )
	{
		this.totalLimitAmt = totalLimitAmt;
	}

	@Column(name = "mer_cont_name", length = 20)
	public String getMerContName()
	{
		return this.merContName;
	}

	public void setMerContName( String merContName )
	{
		this.merContName = merContName;
	}

	@Column(name = "mer_cont_tel", length = 20)
	public String getMerContTel()
	{
		return this.merContTel;
	}

	public void setMerContTel( String merContTel )
	{
		this.merContTel = merContTel;
	}

	@Column(name = "mer_cont_mp", length = 11)
	public String getMerContMp()
	{
		return this.merContMp;
	}

	public void setMerContMp( String merContMp )
	{
		this.merContMp = merContMp;
	}

	@Column(name = "mer_cont_email", length = 30)
	public String getMerContEmail()
	{
		return this.merContEmail;
	}

	public void setMerContEmail( String merContEmail )
	{
		this.merContEmail = merContEmail;
	}

	@Column(name = "mer_cont_role", length = 1)
	public String getMerContRole()
	{
		return this.merContRole;
	}

	public void setMerContRole( String merContRole )
	{
		this.merContRole = merContRole;
	}

	@Column(name = "cer_file", length = 50)
	public String getCerFile()
	{
		return this.cerFile;
	}

	public void setCerFile( String cerFile )
	{
		this.cerFile = cerFile;
	}

	@Column(name = "pfx_file", length = 50)
	public String getPfxFile()
	{
		return this.pfxFile;
	}

	public void setPfxFile( String pfxFile )
	{
		this.pfxFile = pfxFile;
	}

	@Column(name = "pfx_pass", length = 200)
	public String getPfxPass()
	{
		return this.pfxPass;
	}

	public void setPfxPass( String pfxPass )
	{
		this.pfxPass = pfxPass;
	}

	@Column(name = "un_user_name", length = 200)
	public String getUnUserName()
	{
		return this.unUserName;
	}

	public void setUnUserName( String unUserName )
	{
		this.unUserName = unUserName;
	}

	@Column(name = "un_user_pass", length = 200)
	public String getUnUserPass()
	{
		return this.unUserPass;
	}

	public void setUnUserPass( String unUserPass )
	{
		this.unUserPass = unUserPass;
	}

	@Column(name = "recv", length = 50)
	public String getRecv()
	{
		return this.recv;
	}

	public void setRecv( String recv )
	{
		this.recv = recv;
	}

	@Column(name = "logo", length = 50)
	public String getLogo()
	{
		return this.logo;
	}

	public void setLogo( String logo )
	{
		this.logo = logo;
	}

	@Column(name = "fee_amt", precision = 10)
	public Double getFeeAmt()
	{
		return this.feeAmt;
	}

	public void setFeeAmt( Double feeAmt )
	{
		this.feeAmt = feeAmt;
	}

}