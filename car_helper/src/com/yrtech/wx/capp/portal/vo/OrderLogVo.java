package com.yrtech.wx.capp.portal.vo;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

public class OrderLogVo
{

	private Integer id;
	private String merName;
	private Integer merId;
	private String ordId;
	private Integer payUserId;
	private Integer recUserId;
	private String payUserCode;
	private String recUserPhone;
	private String ordAmtStr;
	private String actAmtStr;
	private String ordState;
	private String ordOrg;
	private String liqState;
	private String createTimeStr;
	private Timestamp bankDate;
	private String bankSeqId;
	private String bankRespCode;
	private String chkFlag;
	private String acctName;
	private Timestamp createTime;
	private String deptName;
	private String userProv;
	private String userCity;
	private String userArea;
	private String shipState;
	private String shipNo;
	private String logtCode;
	private String acctNo;
	private String userCode;
	private String bankRespMsg;
	private List ordItems;

	public String getAcctName()
	{
		return acctName;
	}

	public void setAcctName( String acctName )
	{
		this.acctName = acctName;
	}

	public String getMerName()
	{
		return merName;
	}

	public void setMerName( String merName )
	{
		this.merName = merName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public Integer getMerId()
	{
		return merId;
	}

	public void setMerId( Integer merId )
	{
		this.merId = merId;
	}

	public String getOrdId()
	{
		return ordId;
	}

	public void setOrdId( String ordId )
	{
		this.ordId = ordId;
	}

	public Integer getPayUserId()
	{
		return payUserId;
	}

	public void setPayUserId( Integer payUserId )
	{
		this.payUserId = payUserId;
	}

	public Integer getRecUserId()
	{
		return recUserId;
	}

	public void setRecUserId( Integer recUserId )
	{
		this.recUserId = recUserId;
	}

	public String getRecUserPhone()
	{
		return recUserPhone;
	}

	public void setRecUserPhone( String recUserPhone )
	{
		this.recUserPhone = recUserPhone;
	}

	public String getOrdState()
	{
		return ordState;
	}

	public void setOrdState( String ordState )
	{
		this.ordState = ordState;
	}

	public String getOrdOrg()
	{
		return ordOrg;
	}

	public void setOrdOrg( String ordOrg )
	{
		this.ordOrg = ordOrg;
	}

	public String getLiqState()
	{
		return liqState;
	}

	public void setLiqState( String liqState )
	{
		this.liqState = liqState;
	}

	public Timestamp getBankDate()
	{
		return bankDate;
	}

	public void setBankDate( Timestamp bankDate )
	{
		this.bankDate = bankDate;
	}

	public String getBankSeqId()
	{
		return bankSeqId;
	}

	public void setBankSeqId( String bankSeqId )
	{
		this.bankSeqId = bankSeqId;
	}

	public String getBankRespCode()
	{
		return bankRespCode;
	}

	public void setBankRespCode( String bankRespCode )
	{
		this.bankRespCode = bankRespCode;
	}

	public String getChkFlag()
	{
		return chkFlag;
	}

	public void setChkFlag( String chkFlag )
	{
		this.chkFlag = chkFlag;
	}

	public String getOrdAmtStr()
	{
		return ordAmtStr;
	}

	public void setOrdAmtStr( String ordAmtStr )
	{
		this.ordAmtStr = ordAmtStr;
	}

	public String getActAmtStr()
	{
		return actAmtStr;
	}

	public void setActAmtStr( String actAmtStr )
	{
		this.actAmtStr = actAmtStr;
	}

	public String getCreateTimeStr()
	{
		return createTimeStr;
	}

	public void setCreateTimeStr( String createTimeStr )
	{
		this.createTimeStr = createTimeStr;
	}

	public String getPayUserCode()
	{
		return payUserCode;
	}

	public void setPayUserCode( String payUserCode )
	{
		this.payUserCode = payUserCode;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime( Timestamp createTime )
	{
		this.createTime = createTime;
	}

	public List getOrdItems()
	{
		return ordItems;
	}

	public void setOrdItems( List ordItems )
	{
		this.ordItems = ordItems;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName( String deptName )
	{
		this.deptName = deptName;
	}

	public String getUserCity()
	{
		return userCity;
	}

	public void setUserCity( String userCity )
	{
		this.userCity = userCity;
	}

	public String getUserArea()
	{
		return userArea;
	}

	public void setUserArea( String userArea )
	{
		this.userArea = userArea;
	}

	public String getUserProv()
	{
		return userProv;
	}

	public void setUserProv( String userProv )
	{
		this.userProv = userProv;
	}

	public String getShipState()
	{
		return shipState;
	}

	public void setShipState( String shipState )
	{
		this.shipState = shipState;
	}

	public String getShipNo()
	{
		return shipNo;
	}

	public void setShipNo( String shipNo )
	{
		this.shipNo = shipNo;
	}

	public String getLogtCode()
	{
		return logtCode;
	}

	public void setLogtCode( String logtCode )
	{
		this.logtCode = logtCode;
	}

	public String getAcctNo()
	{
		return acctNo;
	}

	public void setAcctNo( String acctNo )
	{
		this.acctNo = acctNo;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode( String userCode )
	{
		this.userCode = userCode;
	}

	public String getBankRespMsg()
	{
		return bankRespMsg;
	}

	public void setBankRespMsg( String bankRespMsg )
	{
		this.bankRespMsg = bankRespMsg;
	}

}
