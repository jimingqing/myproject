package com.yrtech.wx.capp.portal.task;

import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.service.AcctSumLogSrv;
import com.yrtech.wx.capp.portal.util.Constants;

public class AcctSumTimerTask extends TimerTask {

	private static Logger log = Logger.getLogger(AcctSumTimerTask.class);
	
	@Override
	public void run() {
		try {
			MerInfoDao merInfoDao = (MerInfoDao)SpringInfo.getBean("merInfoDao");
			AcctSumLogSrv acctSumLogSrv = (AcctSumLogSrv)SpringInfo.getBean("acctSumLogSrv");
			List<MerInfo> list = merInfoDao.findTbyHql("from MerInfo where merState='"+Constants.MER_STATE_N+"'");
			if(list!=null&&list.size()>0){
				for(MerInfo o : list){
					acctSumLogSrv.updateMerAcctSumLog(o.getId());
					acctSumLogSrv.updateUserAcctSumLog(o.getId());
				}
			}
			
			UserInfoDao userInfoDao = (UserInfoDao)SpringInfo.getBean("userInfoDao");
			List<UserInfo> userlist = userInfoDao.findAll();
			if(userlist!=null&&userlist.size()>0){
				for(UserInfo o : userlist){
					acctSumLogSrv.updateUserAcctSumLog(o.getMerId(), o.getId());
				}
			}
			log.info("-------------定时审核统计账号日志信息成功--------------");
		} catch (Exception e) {
			log.info("-------------定时审核统计账号日志信息异常--------------", e);
		}
	}
}
