package com.yrtech.wx.capp.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.portal.dao.impl.UserLimitDao;
import com.yrtech.wx.capp.portal.model.UserLimit;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class UserLimitSrv {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private RetInfo retInfo = new RetInfo();
	
	@Resource
	private UserLimitDao userLimitDao;
	
	/**
	 * 查询用户限额信息
	 */
	public RetInfo qryUserLimit(Integer merId, Integer userId){
		String hql = "from UserLimit where merId=? and userId=?";
		List<UserLimit> list = userLimitDao.findTbyHql(hql, new Object[]{merId, userId});
		if(list==null||list.size()==0){
			log.info("查询用户限额信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询用户限额信息失败");
		}else{
			log.info("查询用户限额信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询用户限额信息成功");
			retInfo.setObject(list.get(0));
		}
		return retInfo;
	}
	
	/**
	 * 修改用户限额信息
	 */
	public RetInfo editUserLimitInfo(UserLimit userLimit){
		String hql = "from UserLimit where merId=? and userId=?";
		List<UserLimit> list = userLimitDao.findTbyHql(hql, new Object[]{
			userLimit.getMerId(), userLimit.getUserId()	
		});
		if(list==null||list.size()==0){
			log.info("查询用户限额信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询用户限额信息失败");
		}else{
			UserLimit tmp = list.get(0);
			if(userLimit.getDayLimitAmt()!=null){
				tmp.setDayLimitAmt(userLimit.getDayLimitAmt());
			}
			if(userLimit.getDayLimitCnt()!=null){
				tmp.setDayLimitCnt(userLimit.getDayLimitCnt());
			}
			if(userLimit.getMonthLimitAmt()!=null){
				tmp.setMonthLimitAmt(userLimit.getMonthLimitAmt());
			}
			if(userLimit.getDayLimitCnt()!=null){
				tmp.setMonthLimitCnt(userLimit.getMonthLimitCnt());
			}
			tmp.setStatFlag(userLimit.getStatFlag());
			
			userLimitDao.update(tmp);
			log.info("修改用户限额信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改用户限额信息成功");
		}
		return retInfo;
	}

}
