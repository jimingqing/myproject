package com.yrtech.wx.capp.portal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.struts2.json.annotations.JSON;

/**
 * OrderLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_log", catalog = "ysf", uniqueConstraints = @UniqueConstraint(columnNames = {
		"mer_id", "ord_id" }))
public class OrderLog implements java.io.Serializable
{

	// Fields

	private Integer id;
	private Integer merId;
	private String ordId;
//	private Integer payUserId;
	private Integer recUserId;
	private String recUserPhone;
	private Double ordAmt;
	private Double actAmt;
	private String ordState;
	private String ordOrg;
	private String liqState;
	private Timestamp createTime;
	private String bankTipUrl;
	private Timestamp bankDate;
	private String bankRespMsg;
	private String bankRespCode;
	private String bankTradeNum;
	private String bankSystemNum;
	private String bankAuthNum;
	private String chkFlag;
	private String type;
	private String acctNo;
	private String acctName;
	private String bankName;
	private String userCode;
	private Integer qryCnt;
	private String shipState;
	private String shipNo;
	private String logtCode;
	private String payMerId;
	private String payTid;
	private String resv;
	private UserInfo userInfo;

	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pay_user_id",unique=true)
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
//	private UserInfo userInfo;

	// Constructors

	/** default constructor */
	public OrderLog()
	{
	}

	/** minimal constructor */
	public OrderLog(Integer merId, String ordId, Double ordAmt,
			String ordState, String ordOrg)
	{
		this.merId = merId;
		this.ordId = ordId;
		this.ordAmt = ordAmt;
		this.ordState = ordState;
		this.ordOrg = ordOrg;
	}

	/** full constructor */
	public OrderLog(Integer merId, String ordId, Integer payUserId,
			Integer recUserId, String recUserPhone, Double ordAmt,
			Double actAmt, String ordState, String ordOrg, String liqState,
			Timestamp createTime, String bankTipUrl, Timestamp bankDate,
			String bankRespMsg, String bankRespCode, String bankTradeNum,
			String bankSystemNum, String bankAuthNum, String chkFlag,
			String type, String acctNo, String acctName, String bankName,
			String userCode, Integer qryCnt, String shipState,
			String shipNo, String logtCode, String payMerId, String payTid)
	{
		this.merId = merId;
		this.ordId = ordId;
//		this.payUserId = payUserId;
		this.recUserId = recUserId;
		this.recUserPhone = recUserPhone;
		this.ordAmt = ordAmt;
		this.actAmt = actAmt;
		this.ordState = ordState;
		this.ordOrg = ordOrg;
		this.liqState = liqState;
		this.createTime = createTime;
		this.bankTipUrl = bankTipUrl;
		this.bankDate = bankDate;
		this.bankRespMsg = bankRespMsg;
		this.bankRespCode = bankRespCode;
		this.bankTradeNum = bankTradeNum;
		this.bankSystemNum = bankSystemNum;
		this.bankAuthNum = bankAuthNum;
		this.chkFlag = chkFlag;
		this.type = type;
		this.acctNo = acctNo;
		this.acctName = acctName;
		this.bankName = bankName;
		this.userCode = userCode;
		this.qryCnt = qryCnt;
		this.shipState = shipState;
		this.shipNo = shipNo;
		this.logtCode = logtCode;
		this.payMerId = payMerId;
		this.payTid = payTid;
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

	@Column(name = "mer_id", nullable = false)
	public Integer getMerId()
	{
		return this.merId;
	}

	public void setMerId( Integer merId )
	{
		this.merId = merId;
	}

	@Column(name = "ord_id", nullable = false, length = 20)
	public String getOrdId()
	{
		return this.ordId;
	}

	public void setOrdId( String ordId )
	{
		this.ordId = ordId;
	}

//	@Column(name = "pay_user_id")
//	public Integer getPayUserId()
//	{
//		return this.payUserId;
//	}
//
//	public void setPayUserId( Integer payUserId )
//	{
//		this.payUserId = payUserId;
//	}

	@Column(name = "rec_user_id")
	public Integer getRecUserId()
	{
		return this.recUserId;
	}

	public void setRecUserId( Integer recUserId )
	{
		this.recUserId = recUserId;
	}

	@Column(name = "rec_user_phone", length = 11)
	public String getRecUserPhone()
	{
		return this.recUserPhone;
	}

	public void setRecUserPhone( String recUserPhone )
	{
		this.recUserPhone = recUserPhone;
	}

	@Column(name = "ord_amt", nullable = false, precision = 10)
	public Double getOrdAmt()
	{
		return this.ordAmt;
	}

	public void setOrdAmt( Double ordAmt )
	{
		this.ordAmt = ordAmt;
	}

	@Column(name = "act_amt", precision = 10)
	public Double getActAmt()
	{
		return this.actAmt;
	}

	public void setActAmt( Double actAmt )
	{
		this.actAmt = actAmt;
	}

	@Column(name = "ord_state", nullable = false, length = 1)
	public String getOrdState()
	{
		return this.ordState;
	}

	public void setOrdState( String ordState )
	{
		this.ordState = ordState;
	}

	@Column(name = "ord_org", nullable = false, length = 1)
	public String getOrdOrg()
	{
		return this.ordOrg;
	}

	public void setOrdOrg( String ordOrg )
	{
		this.ordOrg = ordOrg;
	}

	@Column(name = "liq_state", length = 1)
	public String getLiqState()
	{
		return this.liqState;
	}

	public void setLiqState( String liqState )
	{
		this.liqState = liqState;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime( Timestamp createTime )
	{
		this.createTime = createTime;
	}

	@Column(name = "bank_tip_url")
	public String getBankTipUrl()
	{
		return this.bankTipUrl;
	}

	public void setBankTipUrl( String bankTipUrl )
	{
		this.bankTipUrl = bankTipUrl;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "bank_date", length = 19)
	public Timestamp getBankDate()
	{
		return this.bankDate;
	}

	public void setBankDate( Timestamp bankDate )
	{
		this.bankDate = bankDate;
	}

	@Column(name = "bank_resp_msg", length = 100)
	public String getBankRespMsg()
	{
		return this.bankRespMsg;
	}

	public void setBankRespMsg( String bankRespMsg )
	{
		this.bankRespMsg = bankRespMsg;
	}

	@Column(name = "bank_resp_code", length = 10)
	public String getBankRespCode()
	{
		return this.bankRespCode;
	}

	public void setBankRespCode( String bankRespCode )
	{
		this.bankRespCode = bankRespCode;
	}

	@Column(name = "bank_trade_num", length = 100)
	public String getBankTradeNum()
	{
		return this.bankTradeNum;
	}

	public void setBankTradeNum( String bankTradeNum )
	{
		this.bankTradeNum = bankTradeNum;
	}

	@Column(name = "bank_system_num", length = 100)
	public String getBankSystemNum()
	{
		return this.bankSystemNum;
	}

	public void setBankSystemNum( String bankSystemNum )
	{
		this.bankSystemNum = bankSystemNum;
	}

	@Column(name = "bank_auth_num")
	public String getBankAuthNum()
	{
		return this.bankAuthNum;
	}

	public void setBankAuthNum( String bankAuthNum )
	{
		this.bankAuthNum = bankAuthNum;
	}

	@Column(name = "chk_flag", length = 1)
	public String getChkFlag()
	{
		return this.chkFlag;
	}

	public void setChkFlag( String chkFlag )
	{
		this.chkFlag = chkFlag;
	}

	@Column(name = "type", length = 300)
	public String getType()
	{
		return this.type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	@Column(name = "acct_no", length = 50)
	public String getAcctNo()
	{
		return this.acctNo;
	}

	public void setAcctNo( String acctNo )
	{
		this.acctNo = acctNo;
	}

	@Column(name = "acct_name", length = 50)
	public String getAcctName()
	{
		return this.acctName;
	}

	public void setAcctName( String acctName )
	{
		this.acctName = acctName;
	}

	@Column(name = "bank_name", length = 50)
	public String getBankName()
	{
		return this.bankName;
	}

	public void setBankName( String bankName )
	{
		this.bankName = bankName;
	}

	@Column(name = "user_code", length = 20)
	public String getUserCode()
	{
		return this.userCode;
	}

	public void setUserCode( String userCode )
	{
		this.userCode = userCode;
	}

	@Column(name = "qry_cnt")
	public Integer getQryCnt()
	{
		return this.qryCnt;
	}

	public void setQryCnt( Integer qryCnt )
	{
		this.qryCnt = qryCnt;
	}

	@Column(name = "ship_state", length = 1)
	public String getShipState()
	{
		return this.shipState;
	}

	public void setShipState( String shipState )
	{
		this.shipState = shipState;
	}

	@Column(name = "ship_no", length = 50)
	public String getShipNo()
	{
		return this.shipNo;
	}

	public void setShipNo( String shipNo )
	{
		this.shipNo = shipNo;
	}

	@Column(name = "logt_code", length = 10)
	public String getLogtCode()
	{
		return this.logtCode;
	}

	public void setLogtCode( String logtCode )
	{
		this.logtCode = logtCode;
	}

	@Column(name = "pay_mer_id")
	public String getPayMerId()
	{
		return this.payMerId;
	}

	public void setPayMerId( String payMerId )
	{
		this.payMerId = payMerId;
	}

	@Column(name = "pay_tid")
	public String getPayTid()
	{
		return this.payTid;
	}

	public void setPayTid( String payTid )
	{
		this.payTid = payTid;
	}

//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="pay_user_id",unique=true)
//	public UserInfo getUserInfo() {
//		return userInfo;
//	}
//
//	public void setUserInfo(UserInfo userInfo) {
//		this.userInfo = userInfo;
//	}
	
	@Column(name = "resv", length = 300)
	public String getResv() {
		return this.resv;
	}

	public void setResv(String resv) {
		this.resv = resv;
	}
}