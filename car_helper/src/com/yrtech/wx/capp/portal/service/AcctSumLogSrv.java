package com.yrtech.wx.capp.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.portal.dao.impl.MerAcctSumLogDao;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.dao.impl.UserAcctSumLogDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.model.MerAcctSumLog;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.UserAcctSumLog;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class AcctSumLogSrv {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private MerAcctSumLogDao merAcctSumLogDao;
	@Resource
	private UserAcctSumLogDao userAcctSumLogDao;
	@Resource
	private OrderLogDao orderLogDao;
	@Resource
	private UserInfoDao userInfoDao;
	
	public RetInfo qryMerAcctSumLog(Integer merId, String startDate, String endDate){
//		System.out.println("aaaaaaaaaaaaaaaaaa");
		RetInfo retInfo = new RetInfo();
		String hql = "from MerAcctSumLog where merId="+merId+" and acctDate between '"+startDate+" 00:00:00' and '"+endDate+" 23:59:59' order by acctDate desc";
		List<MerAcctSumLog> list = merAcctSumLogDao.findTbyHql(hql);
		System.out.println(list+"aaaaaaaaaaaa");
		log.info("查询账务汇总日志成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询账务汇总日志成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo qryUserAcctSumLog(Integer merId, String startDate, String endDate){
		RetInfo retInfo = new RetInfo();
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb");
		String hql = "from UserAcctSumLog u left outer join u.userInfo where u.merId="+merId+" and u.acctDate between '"+startDate+" 00:00:00' and '"+endDate+" 23:59:59' order by u.acctDate desc";
		List<UserAcctSumLog> list = userAcctSumLogDao.findTbyHql(hql);
		log.info("查询账务汇总日志成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询账务汇总日志成功");
		retInfo.setList(list);
		return retInfo;
	}
	/**
	 * 统计非注册用户交易汇总信息
	 */
	public RetInfo updateUserAcctSumLog(Integer merId){
		System.out.println("sssssssssss");
		
		RetInfo retInfo = new RetInfo();
		String acctDate = DateOper.getYesterday(); //账务日期为昨天
		String hql = "select acctName, acctNo from OrderLog where userInfo is null and bankDate between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' group by acctName, acctNo";
		List list = userAcctSumLogDao.findTbyHql(hql);
		String acctName = "";
		String acctNo = "";
		if(list!=null && list.size()>0){
			Object[] tmp;
			for(Object o : list){
				tmp = (Object[])o;
				acctName = (String)tmp[0];
				acctNo = (String)tmp[1];
				String cardNo = SecretUtil.getDecryptedString(acctNo).substring(SecretUtil.getDecryptedString(acctNo).length()-4);
				
				hql = "from UserAcctSumLog where merId="+merId+" and userInfo is null and resv='"+acctName+"|"+cardNo+"' and acctDate='"+acctDate+" 00:00:00'";
				List tmplist = userAcctSumLogDao.findTbyHql(hql);
				if(tmplist!=null&&tmplist.size()>0){
					log.info("账务汇总日志已生成");
					retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
					retInfo.setRetMsg("账务汇总日志已生成");
					return retInfo;
				}
				
				UserAcctSumLog acctSumLog = new UserAcctSumLog();
				acctSumLog.setMerId(merId);
				acctSumLog.setAcctDate(DateOper.string2Date(acctDate+" 00:00:00"));
				
				String where = " where merId="+merId+" and userInfo is null and acctName='"+acctName+"' and acctNo='"+acctNo+"' and ordState='"+Constants.ORDER_LOG_STATE_S+"' and createTime between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
				hql = "select count(id), sum(ordAmt) from OrderLog";
				tmplist = orderLogDao.findTbyHql(hql+where);
				long cnt = 0;
				if(tmplist!=null&&tmplist.size()>0){
					cnt = (Long)((Object[])tmplist.get(0))[0];
					acctSumLog.setTransCnt(Integer.valueOf(String.valueOf(cnt)));
					if(((Object[])tmplist.get(0))[1]==null){
						acctSumLog.setTransAmt(0d);
					}else{
						acctSumLog.setTransAmt((Double)((Object[])tmplist.get(0))[1]);
					}
				}
				
				where = " where merId="+merId+" and userInfo is null and acctName='"+acctName+"' and acctNo='"+acctNo+"' and ordState='"+Constants.ORDER_LOG_STATE_S+"' and liqState='"+Constants.ORDER_LOG_LIQ_STATE_Y+"' and bankDate between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
				hql = "select count(id), sum(ordAmt) from OrderLog";
				tmplist = orderLogDao.findTbyHql(hql+where);
				if(tmplist!=null&&tmplist.size()>0){
					cnt = (Long)((Object[])tmplist.get(0))[0];
					if(((Object[])tmplist.get(0))[1]==null){
						acctSumLog.setLiqAmt(0d);
					}else{
						acctSumLog.setLiqAmt((Double)((Object[])tmplist.get(0))[1]);
					}
				}
				acctSumLog.setFeeAmt(0.00);
				acctSumLog.setResv(acctName+"|"+cardNo);
				
				userAcctSumLogDao.save(acctSumLog);
			}
		}
		
		log.info("账务汇总日志更新成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("账务汇总日志更新成功");
		return retInfo;
	}
	
	/**
	 * 统计商户交易汇总信息
	 */
	public RetInfo updateMerAcctSumLog(Integer merId){
		System.out.println("mmmmmmmmmmmmmm");
		RetInfo retInfo = new RetInfo();
		String acctDate = DateOper.getYesterday(); //账务日期为昨天
		String hql = "from MerAcctSumLog where merId="+merId+" and acctDate='"+acctDate+" 00:00:00'";
		List list = merAcctSumLogDao.findTbyHql(hql);
		if(list!=null&&list.size()>0){
			log.info("账务汇总日志已生成");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("账务汇总日志已生成");
			return retInfo;
		}
		MerAcctSumLog acctSumLog = new MerAcctSumLog();
		acctSumLog.setMerId(merId);
		acctSumLog.setAcctDate(DateOper.string2Date(acctDate+" 00:00:00"));
		
		String where = " where merId="+merId+" and ordState='"+Constants.ORDER_LOG_STATE_S+"' and  bankDate  between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
		hql = "select count(id), sum(ordAmt) from OrderLog";
		list = orderLogDao.findTbyHql(hql+where);
		long cnt = 0;
		if(list!=null&&list.size()>0){
			cnt = (Long)((Object[])list.get(0))[0];
			acctSumLog.setTransCnt(Integer.valueOf(String.valueOf(cnt)));
			if(((Object[])list.get(0))[1]==null){
				acctSumLog.setTransAmt(0d);
			}else{
				acctSumLog.setTransAmt((Double)((Object[])list.get(0))[1]);
			}
		}
		
		where = " where merId="+merId+" and ordState='"+Constants.ORDER_LOG_STATE_S+"' and liqState='"+Constants.ORDER_LOG_LIQ_STATE_Y+"' and  bankDate between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
		hql = "select count(id), sum(ordAmt) from OrderLog";
		list = orderLogDao.findTbyHql(hql+where);
		if(list!=null&&list.size()>0){
			cnt = (Long)((Object[])list.get(0))[0];
			if(((Object[])list.get(0))[1]==null){
				acctSumLog.setLiqAmt(0d);
			}else{
				acctSumLog.setLiqAmt((Double)((Object[])list.get(0))[1]);
			}
		}
		acctSumLog.setFeeAmt(0.00);
		
		merAcctSumLogDao.save(acctSumLog);
		log.info("账务汇总日志更新成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("账务汇总日志更新成功");
		return retInfo;
	}
	
	/**
	 * 统计注册用户交易汇总信息
	 */
	public RetInfo updateUserAcctSumLog(Integer merId, Integer userId){
		
		System.out.println("kkkkkkkkkkkkkkk");
		RetInfo retInfo = new RetInfo();
		String acctDate = DateOper.getYesterday(); //账务日期为昨天
		String hql = "from UserAcctSumLog where merId="+merId+" and userInfo.id="+userId+" and acctDate='"+acctDate+" 00:00:00'";
		List list = userAcctSumLogDao.findTbyHql(hql);
		if(list!=null&&list.size()>0){
			log.info("账务汇总日志已生成");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("账务汇总日志已生成");
			return retInfo;
		}
		
		UserAcctSumLog acctSumLog = new UserAcctSumLog();
		acctSumLog.setMerId(merId);
		acctSumLog.setUserInfo(userInfoDao.find(userId));
		acctSumLog.setAcctDate(DateOper.string2Date(acctDate+" 00:00:00"));
		
		String where = " where merId="+merId+" and userInfo.id="+userId+" and ordState='"+Constants.ORDER_LOG_STATE_S+"' and bankDate between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
		hql = "select count(id), sum(ordAmt) from OrderLog";
		list = orderLogDao.findTbyHql(hql+where);
		long cnt = 0;
		if(list!=null&&list.size()>0){
			cnt = (Long)((Object[])list.get(0))[0];
			acctSumLog.setTransCnt(Integer.valueOf(String.valueOf(cnt)));
			if(((Object[])list.get(0))[1]==null){
				acctSumLog.setTransAmt(0d);
			}else{
				acctSumLog.setTransAmt((Double)((Object[])list.get(0))[1]);
			}
		}
		
		where = " where merId="+merId+" and userInfo.id="+userId+" and ordState='"+Constants.ORDER_LOG_STATE_S+"' and liqState='"+Constants.ORDER_LOG_LIQ_STATE_Y+"' and bankDate between '"+acctDate+" 00:00:00'  and  '"+acctDate+" 23:59:59' ";
		hql = "select count(id), sum(ordAmt) from OrderLog";
		list = orderLogDao.findTbyHql(hql+where);
		if(list!=null&&list.size()>0){
			cnt = (Long)((Object[])list.get(0))[0];
			if(((Object[])list.get(0))[1]==null){
				acctSumLog.setLiqAmt(0d);
			}else{
				acctSumLog.setLiqAmt((Double)((Object[])list.get(0))[1]);
			}
		}
		acctSumLog.setFeeAmt(0.00);
		
		userAcctSumLogDao.save(acctSumLog);
		log.info("账务汇总日志更新成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("账务汇总日志更新成功");
		return retInfo;
	}
	
	public static void main(String[] args) {
		MerInfoDao merInfoDao = (MerInfoDao)SpringInfo.getBean("merInfoDao");
		AcctSumLogSrv acctSumLogSrv = (AcctSumLogSrv)SpringInfo.getBean("acctSumLogSrv");
		List<MerInfo> list = merInfoDao.findTbyHql("from MerInfo where merState='"+Constants.MER_STATE_N+"'");
		if(list!=null&&list.size()>0){
			for(MerInfo o : list){
//				RetInfo retInfo = acctSumLogSrv.qryUserAcctSumLog(o.getId(), "2014-06-21", "2014-06-23");
				RetInfo retInfo = acctSumLogSrv.updateUserAcctSumLog(o.getId());
//				System.out.println(retInfo.getList().size());
			}
		}
		
		/*UserInfoDao userInfoDao = (UserInfoDao)SpringInfo.getBean("userInfoDao");
		List<UserInfo> userlist = userInfoDao.findTbyHql("from UserInfo");
		if(userlist!=null&&userlist.size()>0){
			for(UserInfo o : userlist){
//				RetInfo retInfo = acctSumLogSrv.qryAcctSumLog(o.getId(), "2014-06-21", "2014-06-21");
//				System.out.println(retInfo.getList().size());
				acctSumLogSrv.updateUserAcctSumLog(o.getMerId(), o.getId());
			}
		}*/
	}

}
