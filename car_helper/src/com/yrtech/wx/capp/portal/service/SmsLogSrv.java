package com.yrtech.wx.capp.portal.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.SmsLogDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.SmsLog;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.util.SendSms;

public class SmsLogSrv {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private static String content = Config.getProperty("sms_chanel.content");
	private SendSms sendsms = new SendSms();
	
	private RetInfo retInfo = new RetInfo();
	
	@Resource
	private SmsLogDao smsLogDao;
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private MerInfoDao merInfoDao;
	
	/**
	 * 注册用户短信验证码发送
	 * @param userId
	 * @param merId
	 * @param phoneNo
	 * @param randCode
	 * @return
	 */
	public RetInfo sendSms(Integer userId, Integer merId, String phoneNo, String randCode){
		try {
			SmsLog smsLog = new SmsLog();
			smsLog.setMerId(merId);
			smsLog.setUserId(userId);
			smsLog.setPhoneNo(phoneNo);
			smsLog.setSendTime(new Timestamp(new Date().getTime()));
			smsLog.setState(Constants.SMS_LOG_STATE_INIT);//设置已发送
			smsLog.setType(Constants.SMS_LOG_TYPE_YZM);
			smsLog.setContent(content.replace("${code}", randCode));
			smsLogDao.save(smsLog);
			
//			smsLog = sendsms.send(smsLog);//发送成功后状态为“发送成功”
			//for test
			smsLog.setState(Constants.SMS_LOG_STATE_SUCC);
			smsLog.setRecv("测试");
			
			smsLogDao.update(smsLog);
			if(smsLog.getState().equals(Constants.SMS_LOG_STATE_SUCC) && sendsms.getErrhttp()==0 ){
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("发送短信成功");
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("发送短信失败");
			}
		} catch (Exception e) {
			log.error("发送短信异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("发送短信异常");
		}
		
		return retInfo;
	}
	
	/**
	 * 非注册用户短信验证码发送
	 * @param merCode
	 * @param phoneNo
	 * @param randCode
	 * @return
	 */
	public RetInfo sendSms(String merCode, String phoneNo, String randCode){
		try {
			String hql = "from MerInfo m where m.merCode = ?";
			List<MerInfo> merList = merInfoDao.findTbyHql(hql, new Object[]{merCode});
			if(merList.size()>0){
				MerInfo merInfo = merList.get(0);
				SmsLog smsLog = new SmsLog();
				smsLog.setMerId(merInfo.getId());
				smsLog.setPhoneNo(phoneNo);
				smsLog.setSendTime(new Timestamp(new Date().getTime()));
				smsLog.setState(Constants.SMS_LOG_STATE_INIT);//设置已发送
				smsLog.setType(Constants.SMS_LOG_TYPE_YZM);
				smsLog.setContent(content.replace("${code}", randCode));
				smsLogDao.save(smsLog);
				
//				smsLog = sendsms.send(smsLog);//发送成功后状态为“发送成功”
				//for test
				smsLog.setState(Constants.SMS_LOG_STATE_SUCC);
				smsLog.setRecv("测试");
				
				smsLogDao.update(smsLog);
				if(smsLog.getState().equals(Constants.SMS_LOG_STATE_SUCC) && sendsms.getErrhttp()==0 ){
					retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
					retInfo.setRetMsg("发送短信成功");
				}else{
					retInfo.setRetCode(Constants.RET_CODE_ERROR);
					retInfo.setRetMsg("发送短信失败");
				}
			}else{
				log.info("商户信息查询失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("商户信息查询失败");
			}
		} catch (Exception e) {
			log.error("发送短信异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("发送短信异常");
		}
		
		return retInfo;
	}
	
	/**
	 * 发送指定短信内容
	 * @param merCode
	 * @param phoneNo
	 * @param randCode
	 * @return
	 */
	public RetInfo sendSmsC(String merCode, String phoneNo, String smscont){
		try {
			String hql = "from MerInfo m where m.merCode = ?";
			List<MerInfo> merList = merInfoDao.findTbyHql(hql, new Object[]{merCode});
			if(merList.size()>0){
				MerInfo merInfo = merList.get(0);
				SmsLog smsLog = new SmsLog();
				smsLog.setMerId(merInfo.getId());
				smsLog.setPhoneNo(phoneNo);
				smsLog.setSendTime(new Timestamp(new Date().getTime()));
				smsLog.setState(Constants.SMS_LOG_STATE_INIT);//设置已发送
				smsLog.setType(Constants.SMS_LOG_TYPE_YZM);
				smsLog.setContent(smscont);
				smsLogDao.save(smsLog);
				
//				smsLog = sendsms.send(smsLog);//发送成功后状态为“发送成功”
				//for test
				smsLog.setState(Constants.SMS_LOG_STATE_SUCC);
				smsLog.setRecv("测试");
				
				smsLogDao.update(smsLog);
				if(smsLog.getState().equals(Constants.SMS_LOG_STATE_SUCC) && sendsms.getErrhttp()==0 ){
					retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
					retInfo.setRetMsg("发送短信成功");
				}else{
					retInfo.setRetCode(Constants.RET_CODE_ERROR);
					retInfo.setRetMsg("发送短信失败");
				}
			}else{
				log.info("商户信息查询失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("商户信息查询失败");
			}
		} catch (Exception e) {
			log.error("发送短信异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("发送短信异常");
		}
		
		return retInfo;
	}
	
	public RetInfo qrySmsLog(){
		try {
			List<SmsLog> list = smsLogDao.findTbyHql("from SmsLog order by sendTime desc");
			if(list.size()>0){
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("查询发送短信记录成功");
				retInfo.setObject(list.get(0));
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询发送短信记录失败");
			}
		} catch (Exception e) {
			log.error("查询发送短信记录异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询发送短信记录异常");
		}
		
		return retInfo;
	}
	
}
