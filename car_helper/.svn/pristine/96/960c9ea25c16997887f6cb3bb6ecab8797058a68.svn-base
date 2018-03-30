package com.yrtech.wx.capp.portal.task;

import handout.TransProcess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.util.Constants;

public class TGDataTimerTask extends TimerTask {

	private static Logger log = Logger.getLogger(TGDataTimerTask.class);
	
	@Override
	public void run() {
		try {
			MerInfoDao merInfoDao = (MerInfoDao)SpringInfo.getBean("merInfoDao");
			List<MerInfo> list = merInfoDao.findTbyHql("from MerInfo where merState='"+Constants.MER_STATE_N+"'");
			Map<String, String> cerFileMap = new HashMap<String, String>();
			Map<String, String> pfxFileMap = new HashMap<String, String>();
			Map<String, String> pfxPassMap = new HashMap<String, String>();
			Map<String, String> userNameMap = new HashMap<String, String>();
			Map<String, String> userPassMap = new HashMap<String, String>();
			for(MerInfo o : list){
				cerFileMap.put(o.getMerCode(), o.getCerFile());
				pfxFileMap.put(o.getMerCode(), o.getPfxFile());
				pfxPassMap.put(o.getMerCode(), o.getPfxPass());
				userNameMap.put(o.getMerCode(), o.getUnUserName());
				userPassMap.put(o.getMerCode(), o.getUnUserPass());
			}
			TransProcess.setCerFileMap(cerFileMap);
			TransProcess.setPfxFileMap(pfxFileMap);
			TransProcess.setPfxPassMap(pfxPassMap);
			TransProcess.setUserNameMap(userNameMap);
			TransProcess.setUserPassMap(userPassMap);
			log.info("-------------定时审核注册用户信息成功--------------");
		} catch (Exception e) {
			log.info("-------------定时审核注册用户信息异常--------------", e);
		}
	}
}
