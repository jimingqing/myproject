package com.yrtech.wx.capp.portal.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sun.net.httpserver.Authenticator.Success;
import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.json.JSONUtil;
import com.yrtech.wx.capp.framework.core.net.HttpClientUtil;
import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.portal.dao.impl.HandleAuditLogDao;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderItemDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.model.BankInfo;
import com.yrtech.wx.capp.portal.model.HandleAuditLog;
import com.yrtech.wx.capp.portal.model.OrderItem;
import com.yrtech.wx.capp.portal.model.OrderLog;
import com.yrtech.wx.capp.portal.model.UserBankRel;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.PageData;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class OrderLogSrv
{

	private Log log = LogFactory.getLog(this.getClass());

	private HttpClientUtil httpClientUtil = new HttpClientUtil();

	@Resource
	private MerInfoDao merInfoDao;
	@Resource
	private OrderLogDao orderLogDao;
	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private HandleAuditLogDao handleAuditLogDao;

	public RetInfo saveOrderLogshipInfo( Integer merId , String ordId ,
			String shipState , String logtCode , String shipNo )
	{
		RetInfo retInfo = new RetInfo();
		String hql = "from OrderLog where merId=? and id=? and ordState=? and shipState=?";
		List list = orderLogDao.findTbyHql(hql, new Object[] { merId,
				Integer.valueOf(ordId), Constants.ORDER_LOG_STATE_S,
				Constants.YES_OR_NO_FLAG_Y });
		if(list != null && list.size() > 0)
		{
			log.info("订单发货状态错误, ordId=" + ordId);
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("订单发货状态错误");
			return retInfo;
		}
		OrderLog ordLog = orderLogDao.find(Integer.valueOf(ordId));
		if(ordLog == null)
		{
			log.info("订单查询失败, ordId=" + ordId);
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("订单查询失败");
			return retInfo;
		}else
		{
			ordLog.setShipState(shipState);
			ordLog.setLogtCode(logtCode);
			ordLog.setShipNo(shipNo);
			orderLogDao.update(ordLog);

			log.info("订单查询成功, ordId=" + ordId);
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("订单查询成功");
			return retInfo;
		}

	}

	public RetInfo addOrderHandAudit( String merId , String operId ,
			String merCode , String userType , String userCode ,
			String userName , String bankCode , String bankName ,
			String openBankCard , String ordAmt , String ordDesc )
			throws Exception
	{
		RetInfo retInfo = new RetInfo();
		String hql = "from UserInfo u, UserBankRel r where u.id=r.userId and u.merId=? and u.userCode=? and r.defState=?";
		UserInfo userInfo = new UserInfo();
		UserBankRel userBankRel = new UserBankRel();
		if(userType.equals("R"))
		{
			List list = userInfoDao.findTbyHql(hql, new Object[] {
					Integer.valueOf(merId), userCode,
					Constants.YES_OR_NO_FLAG_Y });
			if(list != null && list.size() > 0)
			{
				userInfo = (UserInfo) ((Object[]) list.get(0))[0];
				userBankRel = (UserBankRel) ((Object[]) list.get(0))[1];
			}else
			{
				log.info("查询用户信息失败");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("查询用户信息失败");
				return retInfo;
			}
		}

		HandleAuditLog hal = new HandleAuditLog();
		hal.setMerId(Integer.valueOf(merId));
		hal.setOperId(Integer.valueOf(operId));
		hal.setProcType(Constants.HANDLE_AUDIT_TYPE_10);
		hal.setProcStat(Constants.HANDLE_AUDIT_STATE_H);
		hal.setHandleDate(DateOper.getCurTimestamp());
		hal.setProcCont1(merCode);
		hal.setProcCont2(userType);
		hal.setProcCont3(userCode);
		if(userType.equals("R"))
		{
			Map<Integer, BankInfo> bankMap = (Map<Integer, BankInfo>) DataCacheManager
					.get(Constants.CACHE_KEY_BANKINFO);
			hal.setProcCont4(userInfo.getUserName());
			hal.setProcCont5(bankMap.get(userBankRel.getBankId())
					.getBankCode());
			hal.setProcCont6(userBankRel.getBankName());
			hal.setProcCont7(userBankRel.getBankCardNo());
			hal.setProcCont10(userInfo.getId().toString());
		}else
		{
			hal.setProcCont4(userName);
			hal.setProcCont5(bankCode);
			hal.setProcCont6(bankName);
			hal.setProcCont7(SecretUtil.getDecryptedString(openBankCard));
		}
		hal.setProcCont8(ordAmt);
		hal.setProcCont9(ordDesc);
		handleAuditLogDao.save(hal);
		log.info("保存订单经办信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("保存订单经办信息成功");
		return retInfo;
	}

	public RetInfo orderPay( String merCode , String userType ,
			String userCode , String userName , String bankCode ,
			String bankName , String openBankCard , String ordAmt ,
			String ordDesc ) throws Exception
	{
		RetInfo retInfo = new RetInfo();
		String signStr = "";
		if(userType.equals("R"))
		{
			signStr = merCode + userType + userCode + ordAmt + ordDesc
					+ Constants.MD5_RAND_STRING;
		}else
		{
			signStr = merCode + userType + userName + bankCode + bankName
					+ openBankCard + ordAmt + ordDesc
					+ Constants.MD5_RAND_STRING;
		}
		String md5str = new MD5(signStr).getStrDigest();
		NameValuePair[] pairs = new NameValuePair[10];
		pairs[0] = new NameValuePair("merCode", merCode);
		pairs[1] = new NameValuePair("userType", userType);
		pairs[2] = new NameValuePair("userCode", userCode);
		pairs[3] = new NameValuePair("userName", userName);
		pairs[4] = new NameValuePair("bankCode", bankCode);
		pairs[5] = new NameValuePair("bankName", bankName);
		pairs[6] = new NameValuePair("openBankCard", openBankCard);
		pairs[7] = new NameValuePair("ordAmt", ordAmt);
		pairs[8] = new NameValuePair("ordDesc", ordDesc);
		pairs[9] = new NameValuePair("m", md5str);

		String repJson = httpClientUtil.httpClientPost(Config
				.getProperty("trans_pay_url"), pairs);
		if(repJson != null)
		{
			retInfo = (RetInfo) JSONUtil.json2Object(repJson, RetInfo.class);
		}else
		{
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("订单处理失败");
		}
		return retInfo;
	}

	public RetInfo qryOrdLogDetail( String ordId )
	{
		RetInfo retInfo = new RetInfo();
		String hql = "from OrderLog  where ordId  =?";
		List<OrderLog> list = orderLogDao.findTbyHql("from OrderLog  where ordId  ='"+ordId+"'"); 
	    // List<UserInfo> listInfo = userInfoDao.findTbyHql("from UserInfo where id  in(select payUserId  from  OrderLog  where ordId = "+ordId+")");
//	     UserInfo userInfo = null;
//	     if(listInfo==null)
//	     {
//	     	
//	     }
//	     else
//	     {
//	        userInfo = (UserInfo) listInfo.get(0);
//	     }
		if(list != null && list.size() > 0)
		{
			retInfo.setList(list);
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
//			retInfo.setObject(userInfo);
			retInfo.setRetMsg("查询订单明细成功");
		}else
		{
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询订单明细失败");
		}
		return retInfo;
	}

	/**
	 * 查询订单信息
	 */
	/*
	 * public RetInfo qryOrderInfo( Integer merId , String deptName , String
	 * cityId , String areaId , String userCode , String ordState , String
	 * payUserName , String ordOrg , String startDate , String endDate , String
	 * qryType , String ordAmt , String shipState , Pager pager ) throws
	 * Exception { RetInfo retInfo = new RetInfo(); String select =
	 * "select o "; String totalselect = "select count(o.id) "; String where =
	 * "from OrderLog o left outer join o.userInfo where o.merId = " + merId;
	 * if(!StringUtils.isEmpty(deptName)) { where +=
	 * " and o.userInfo.deptName like '%" + deptName + "%'"; }
	 * if(!StringUtils.isEmpty(cityId)) { where += " and o.userInfo.userCity='"
	 * + cityId + "'"; } if(!StringUtils.isEmpty(areaId)) { where +=
	 * " and o.userInfo.userArea='" + areaId + "'"; }
	 * if(!StringUtils.isEmpty(userCode)) { where += " and o.userCode='" +
	 * userCode + "'"; } if(!StringUtils.isEmpty(ordState)) { where +=
	 * " and o.ordState='" + ordState + "'"; }
	 * if(!StringUtils.isEmpty(payUserName)) { where +=
	 * " and o.acctName like '%" + payUserName + "%'"; }
	 * if(!StringUtils.isEmpty(ordOrg)) { where += " and o.ordOrg='" + ordOrg +
	 * "'"; } if(!StringUtils.isEmpty(ordAmt)) { if(qryType.equals("eq")) {
	 * where += "and o.ordAmt=" + ordAmt + " "; }else if(qryType.equals("gt"))
	 * { where += "and o.ordAmt>" + ordAmt + " "; }else { where +=
	 * "and o.ordAmt<" + ordAmt + " "; } } if(!StringUtils.isEmpty(shipState))
	 * { where += " and o.shipState='" + shipState + "'"; }
	 * if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
	 * where += " and o.createTime between '" + startDate + " 00:00:00" +
	 * "' and '" + endDate + " 23:59:59" + "'"; } where +=
	 * " order by o.createTime desc"; select = where; totalselect += where;
	 * PageData data = orderLogDao.getScrollDataByHqlForMutlRel(pager
	 * .getStartRow(), pager.getPageSize(), select, totalselect);
	 * pager.setTotalRows(Integer.parseInt(String.valueOf(data
	 * .getTotalRecords()))); retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
	 * retInfo.setRetMsg("查询订单信息成功"); retInfo.setList(data.getResultlist());
	 * 
	 * String totalAmtHql = "select sum(o.ordAmt) " + where; List list =
	 * orderLogDao.findTbyHql(totalAmtHql); retInfo.setObject(list.get(0));
	 * return retInfo; }
	 */

	public static void main( String[] args )
	{
		// try
		// {
		/*
		 * Pager page = new Pager(); page.setCurrentPage(1); OrderLogSrv srv =
		 * (OrderLogSrv) SpringInfo .getBean("orderLogSrv");
		 */
		/*
		 * OrderLogDao dao = (OrderLogDao)SpringInfo.getBean("orderLogDao");
		 * List list =
		 * dao.findTbyHql("from OrderLog o left outer join o.userInfo");
		 * for(Object o : list){ Object[] tmp = (Object[])o; OrderLog ol =
		 * (OrderLog)tmp[0]; UserInfo userInfo = (UserInfo)tmp[1];
		 * System.out.println(ol.getUserInfo().getUserName()); }
		 */
		// System.out.println(list.size());

		// String where =
		// "from OrderLog o where o.userInfo.phoneNo='15715517826'";
		// System.out.println(dao.findTbyHql(where).size());
		// RetInfo retInfo = srv.orderPay("880001", "R", "18256092109",
		// "", "", "", "", "10", "");
		// RetInfo retInfo = srv.qryOrderInfo(1, "", "", "", "", "", "",
		// "", "", "", "", "", "", page);
		// for (Object o : retInfo.getList())
		// {
		// Object[] tmp = (Object[]) o;
		// OrderLog ol = (OrderLog) tmp[0];
		// UserInfo userInfo = (UserInfo) tmp[1];
		// System.out.println(ol.getUserInfo().getUserName());
		// }
		// System.out.println(retInfo.getRetMsg());
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
	}

	// public RetInfo qryAllOrdInfoByOrderId( String ordId )
	// {
	// RetInfo retInfo = new RetInfo();
	//		
	// try
	// {
	// List<OrderLog> list =
	// orderLogDao.findTbyHql("from  OrderLog where ordId = '" + ordId
	// + "'");
	// if(list==null)
	// {
	// retInfo.setRetCode(Constants.BLANK);
	// retInfo.setRetMsg("订单为空");
	// return retInfo;
	// }
	// else
	// {
	// retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
	// retInfo.setRetMsg("查询订单成功");
	// retInfo.setObject(list.get(0));
	// return retInfo;
	// }
	// }
	// catch (Exception e)
	// {
	//			
	// }
	// return null;
	// }

	public RetInfo qryOrderInfo( Integer merId , String ordState ,
			String startDate , String endDate , String qryType ,
			String ordAmt ,String cityId,String areaId,String userCode,String payUserName, Pager pager )
	{
		
		/***
		RetInfo retInfo = new RetInfo();
		// String select = "select o ";
		String totalselect = "select count(o.id) ";
		StringBuffer sql = new StringBuffer(" from OrderLog  where ");
		if(merId != null  ||  merId==0)
		{
			// sql = new
			// StringBuffer("from OrderLog  where  payUserId  in(select id  from  UserInfo  where merId = "+merId+")");
			sql
					.append("  merId  in(select id  from  MerInfo  where id = "
							+ merId + ")");
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate))
		{
			sql.append("  and  bankDate   between '" + startDate
					+ " 00:00:00" + "' and '" + endDate + " 23:59:59"
					+ "'");
		}
		if(!StringUtils.isEmpty(ordAmt))
		{
			if(qryType.equals("eq"))
			{
				sql.append("  and  ordAmt =" + ordAmt);
			}else if(qryType.equals("gt"))
			{
				sql.append("  and  ordAmt > " + ordAmt);
			}else
			{
				sql.append("  and  ordAmt < " + ordAmt);
			}
		}

		if( ordState!=null && ordState.length()>0 && (!ordState.equals("A")))
		{
//			System.out.println(ordState);
//			System.out.println("aaaaaaaaaaaaaaa");
			sql.append("  and  ordState ='" + ordState + "'");
		}
		else
		{
			
		}
		// StringBuffer sql = new
		// StringBuffer("from OrderLog  where  payUserId  in(select id  from  UserInfo  where merId = "+merId+")");

		String countsql = "select count(*)  " + sql.toString();
		
		Double totleMoney =   orderLogDao.getTotleMoney(merId, ordState, startDate, endDate, qryType, ordAmt);

		PageData data = orderLogDao.getScrollDataByHql(pager.getStartRow(),
				pager.getPageSize(), sql.toString(), countsql);
		pager.setTotalRows(Integer.parseInt(String.valueOf(data
				.getTotalRecords())));
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询订单信息成功");
		retInfo.setObject(totleMoney);
		retInfo.setList(data.getResultlist());
		return retInfo;**/
		
		RetInfo retInfo = new RetInfo();
		String select = "select o ";
		String totalselect = "select count(o.id) ";
		String where = "from OrderLog o left outer join o.userInfo where o.merId = "+merId;
		if(!StringUtils.isEmpty(cityId)){
			where += " and o.userInfo.userCity='"+cityId+"'";
		}
		if(!StringUtils.isEmpty(areaId)){
			where += " and o.userInfo.userArea='"+areaId+"'";
		}
		if(!StringUtils.isEmpty(userCode)){
			where += " and o.userCode='"+userCode+"'";
		}
		if(!StringUtils.isEmpty(ordState)){
			where += " and o.ordState='"+ordState+"'";
		}
		if(!StringUtils.isEmpty(payUserName)){
			where += " and o.acctName like '%"+payUserName+"%'";
		}
		
		if(!StringUtils.isEmpty(ordAmt)){
			if(qryType.equals("eq")){
				where += "and o.ordAmt="+ordAmt+" ";
			}else if(qryType.equals("gt")){
				where += "and o.ordAmt>"+ordAmt+" ";
			}else{
				where += "and o.ordAmt<"+ordAmt+" ";
			}
		}
		if(!StringUtils.isEmpty(startDate)&&!StringUtils.isEmpty(endDate)){
			where += " and o.createTime between '"+startDate+" 00:00:00"+"' and '"+endDate+" 23:59:59"+"'";
		}
		where += " order by o.createTime desc";
		select = where;
		totalselect += where;
		PageData data = orderLogDao.getScrollDataByHqlForMutlRel(pager.getStartRow(), pager.getPageSize(), select, totalselect);
		pager.setTotalRows(Integer.parseInt(String.valueOf(data.getTotalRecords())));
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询订单信息成功");
		retInfo.setList(data.getResultlist());
		
		String totalAmtHql = "select sum(o.ordAmt) "+where;
		List list = orderLogDao.findTbyHql(totalAmtHql);
		retInfo.setObject(list.get(0));
		return retInfo;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public RetInfo merReport(String id,String merCode, String startDate , String endDate )
	{
		RetInfo retInfo = new RetInfo();
		try
		{
			// String sql =
			// "select merId , count(*), sum(ordAmt)  from  OrderLog  group by  merId ";

			// String countsql
			// =" select merId , count(*), sum(ordAmt)  from  OrderLog  group by  merId  ";
			// PageData data = orderLogDao.getScrollDataByHql(pager
			// .getStartRow(), pager.getPageSize(),sql ,countsql);
			// pager.setTotalRows(Integer.parseInt(String.valueOf(data
			// .getTotalRecords())));
			// retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			// retInfo.setRetMsg("查询订单信息成功");
			// retInfo.setList(data.getResultlist());
			// return retInfo;
			
		
			
			
			List<OrderLog> list =null;
			
			if(merCode.equals("880001"))
			{
				if(startDate!=null && endDate !=null)
				{
//					System.out.println("aaaaaaaaaaaaaaaaaaaaa");
					list = orderLogDao
					.findTbyHql("select merId , count(*), sum(ordAmt) from  OrderLog    where createTime   between  '"+startDate+"'  and  '"+endDate+"'   group by  merId ");
				}
				else
				{
				list = orderLogDao
						.findTbyHql("select merId , count(*), sum(ordAmt ) from  OrderLog   where createTime  between  current_date()-3 and  current_date()  group by merId");
				}
			}
			else
			{
//				System.out.println("aaaaaaaaaaaaaaaaaaaaa");
				if(startDate!=null && endDate !=null)
				{
					list = orderLogDao
					.findTbyHql("select merId , count(*), sum(ordAmt ) from  OrderLog    where createTime   between  '"+startDate+"'  and  '"+endDate+"'   and  merId = "+id);
				}
				else
				{
				list = orderLogDao
						.findTbyHql("select merId , count(*), sum(ordAmt ) from  OrderLog   where createTime  between  current_date()-3 and  current_date()  and  merId="+id);
				}
			}
			
			
			if(list == null || list.size() == 0)
			{
				
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setList(null);
				retInfo.setRetMsg("查询订单成功");
				log.info("查询订单成功");
				return retInfo;
			}else
			{
				log.info("查询订单成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setList(list);
				retInfo.setRetMsg("查询订单成功");
				return retInfo;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error("查询订单异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询订单异常");
			return retInfo;
		}
		// }
	}
	
	
//	public Double  getTotleMoney( Integer merId , String ordState ,
//			String startDate , String endDate , String qryType ,
//			String ordAmt)
//	{
//		StringBuffer sql = new StringBuffer(" from OrderLog  where ");
//		if(merId != null)
//		{
//			// sql = new
//			// StringBuffer("from OrderLog  where  payUserId  in(select id  from  UserInfo  where merId = "+merId+")");
//			sql
//					.append("payUserId  in(select id  from  UserInfo  where merId = "
//							+ merId + ")");
//		}
//		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate))
//		{
//			sql.append("  and  createTime   between '" + startDate
//					+ " 00:00:00" + "' and '" + endDate + " 23:59:59"
//					+ "'");
//		}
//		if(!StringUtils.isEmpty(ordAmt))
//		{
//			if(qryType.equals("eq"))
//			{
//				sql.append("  and  ordAmt =" + ordAmt);
//			}else if(qryType.equals("gt"))
//			{
//				sql.append("  and  ordAmt > " + ordAmt);
//			}else
//			{
//				sql.append("  and  ordAmt < " + ordAmt);
//			}
//		}
//
//		if(!StringUtils.isEmpty(ordState))
//		{
//			sql.append("  and  ordState ='" + ordState + "'");
//		}
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public RetInfo userReport(String id ,String merCode,String startDate , String endDate)
	{
		RetInfo retInfo = new RetInfo();
		try
		{
			List<OrderLog> list  = null;
			
			if(merCode.equals("880001"))
			{
				if(id==null || id.length()==0)
				{
					if(startDate!=null & endDate !=null)
					{
						
					     list = orderLogDao
						.findTbyHql("select  payUserId ,count(*),sum(ordAmt )  from   OrderLog   where createTime  between  '"+startDate+"'  and  '"+endDate+"'      group by    payUserId ");
					     
//					     list = orderLogDao
//						.findBySql("select u.user_name, s.ct ,s.sm from(select  pay_User_Id  ,count(*) ct, sum(ord_Amt)  sm from   Order_Log    where create_Time  between  '"+startDate+"'  and  '"+endDate+"'  and mer_Id = "+id+"   group by    pay_User_Id) s,User_Info u where s.pay_User_Id = u.id ");
					     
					}
					else
					{
					          list = orderLogDao
							.findTbyHql("select  payUserId ,count(*),sum(ordAmt )  from   OrderLog  where createTime  between  current_date()-3 and  current_date()    group by  payUserId");
					}
				}
				else
				{
					System.out.println(id);
					if(startDate!=null & endDate !=null)
					{
					     list = orderLogDao
						.findTbyHql(" select  payUserId ,count(*), sum(ordAmt)  from   OrderLog    where createTime  between  '"+startDate+"'  and  '"+endDate+"'  and merId = "+id+"    group by    payUserId ");
						
//						list = orderLogDao
//						.findBySql("select u.user_name, s.ct ,s.sm from(select  pay_User_Id  ,count(*) ct, sum(ord_Amt)  sm from   Order_Log    where create_Time  between  '"+startDate+"'  and  '"+endDate+"'  and mer_Id = "+id+"   group by    pay_User_Id) s,User_Info u where s.pay_User_Id = u.id ");
					}
					else
					{
					          list = orderLogDao
							.findTbyHql(" select  payUserId ,count(*),sum(ordAmt )  from   OrderLog   where createTime  between  current_date()-3 and  current_date()   and merId = "+id+"      group by  payUserId");
					}
				}
			}
			else
			{
				if(startDate!=null & endDate !=null)
				{
				     list = orderLogDao
					.findTbyHql("(select  payUserId ,count(*),sum(ordAmt )  from   OrderLog  where createTime  between  '"+startDate+"'  and  '"+endDate+"'  and  merId="+id+"    group by    payUserId )");
				}
				else
				{
				          list = orderLogDao
						.findTbyHql("select  payUserId ,count(*), sum(ordAmt )  from   OrderLog  where createTime  between  current_date()-3 and  current_date()    and  merId="+id+"    group by  payUserId");
				}
			}
			
			if(list==null)
			{
				log.info("查询成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setList(null);
				retInfo.setRetMsg("查询成功");
				return retInfo;
			}
			else
			{
			     log.info("查询成功");	
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setList(list);
				retInfo.setRetMsg("查询成功");
				return retInfo;
			}

		
		}
		catch (Exception e)
		{
               log.error("查询异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询异常");
			return retInfo;
		}
	}
}
