package com.yrtech.wx.capp.portal.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.util.CheckUtil;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.model.UserLimit;
import com.yrtech.wx.capp.portal.service.UserLimitSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.UserLimitVo;

public class UserLimitAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String dayLimitCnt;
	private String dayLimitAmt;
	private String monthLimitCnt;
	private String monthLimitAmt;
	private String statFlag;
	
	@Resource
	private UserLimitSrv userLimitSrv;
	
	/**
	 * 修改用户支付限额信息
	 */
	public String editUserLimit(){
		RetInfo retInfo = new RetInfo();
		try {
			UserInfo userInfo = (UserInfo)session().getAttribute(Constants.USER_INFOVO);
			if(userInfo==null){
				log.info("用户未登陆错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_USER_NOLOG);
				resultMap.put(Constants.JSON_RETMSG, "用户未登陆");
				return SUCCESS;
			}
			
			if(StringUtils.isEmpty(statFlag)){
				log.info("限额状态错误， statFlag="+statFlag);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "限额状态错误");
				return SUCCESS;
			}
			
			if(!StringUtils.isEmpty(dayLimitCnt) && !CheckUtil.isNumeric(dayLimitCnt)){
				log.info("日交易笔数错误， dayLimitCnt="+dayLimitCnt);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "日交易笔数错误");
				return SUCCESS;
			}
			if(!StringUtils.isEmpty(dayLimitAmt) && !CheckUtil.validAmt(dayLimitAmt)){
				log.info("日交易限额错误， dayLimitAmt="+dayLimitAmt);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "日交易限额错误");
				return SUCCESS;
			}
			if(!StringUtils.isEmpty(monthLimitCnt) && !CheckUtil.isNumeric(monthLimitCnt)){
				log.info("月交易笔数错误， monthLimitCnt="+monthLimitCnt);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "月交易笔数错误");
				return SUCCESS;
			}
			if(!StringUtils.isEmpty(monthLimitAmt) && !CheckUtil.validAmt(monthLimitAmt)){
				log.info("月交易限额错误， monthLimitAmt="+monthLimitAmt);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "月交易限额错误");
				return SUCCESS;
			}
			
			UserLimit userLimit = new UserLimit();
			userLimit.setMerId(userInfo.getMerId());
			userLimit.setUserId(userInfo.getId());
			if(!StringUtils.isEmpty(dayLimitCnt)){
				userLimit.setDayLimitCnt(Integer.valueOf(dayLimitCnt));
			}
			if(!StringUtils.isEmpty(dayLimitAmt)){
				userLimit.setDayLimitAmt(Double.valueOf(dayLimitAmt));
			}
			if(!StringUtils.isEmpty(monthLimitCnt)){
				userLimit.setMonthLimitCnt(Integer.valueOf(monthLimitCnt));
			}
			if(!StringUtils.isEmpty(monthLimitAmt)){
				userLimit.setMonthLimitAmt(Double.valueOf(monthLimitAmt));
			}
			userLimit.setStatFlag(statFlag);
			
			retInfo = userLimitSrv.editUserLimitInfo(userLimit);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改用户支付限额信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改用户支付限额信息成功");
			}else{
				log.info("查询用户支付限额信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户支付限额信息失败");
			}
		} catch (Exception e) {
			log.error("查询用户支付限额信息异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户支付限额信息异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户支付限额信息
	 */
	public String qryUserLimit(){
		RetInfo retInfo = new RetInfo();
		try {
			UserInfo userInfo = (UserInfo)session().getAttribute(Constants.USER_INFOVO);
			if(userInfo==null){
				log.info("用户未登陆错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_USER_NOLOG);
				resultMap.put(Constants.JSON_RETMSG, "用户未登陆");
				return SUCCESS;
			}
			
			retInfo = userLimitSrv.qryUserLimit(userInfo.getMerId(), userInfo.getId());
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询用户支付限额信息成功");
				
				UserLimitVo vo = new UserLimitVo();
				UserLimit tmp = (UserLimit)retInfo.getObject();
				vo.setId(tmp.getId());
				vo.setMerId(tmp.getMerId());
				vo.setUserId(tmp.getUserId());
				vo.setDayLimitAmt(GmsUtil.double2amt(tmp.getDayLimitAmt()));
				vo.setDayLimitCnt(String.valueOf(tmp.getDayLimitCnt()));
				vo.setMonthLimitAmt(GmsUtil.double2amt(tmp.getMonthLimitAmt()));
				vo.setMonthLimitCnt(String.valueOf(tmp.getMonthLimitCnt()));
				vo.setStatFlag(tmp.getStatFlag());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询用户支付限额信息成功");
				resultMap.put(Constants.JSON_CONTENT, vo);
			}else{
				log.info("查询用户支付限额信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户支付限额信息失败");
			}
		} catch (Exception e) {
			log.error("查询用户支付限额信息异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户支付限额信息异常");
		}
		return SUCCESS;
	}

	public String getDayLimitCnt() {
		return dayLimitCnt;
	}

	public void setDayLimitCnt(String dayLimitCnt) {
		this.dayLimitCnt = dayLimitCnt;
	}

	public String getDayLimitAmt() {
		return dayLimitAmt;
	}

	public void setDayLimitAmt(String dayLimitAmt) {
		this.dayLimitAmt = dayLimitAmt;
	}

	public String getMonthLimitCnt() {
		return monthLimitCnt;
	}

	public void setMonthLimitCnt(String monthLimitCnt) {
		this.monthLimitCnt = monthLimitCnt;
	}

	public String getMonthLimitAmt() {
		return monthLimitAmt;
	}

	public void setMonthLimitAmt(String monthLimitAmt) {
		this.monthLimitAmt = monthLimitAmt;
	}

	public String getStatFlag() {
		return statFlag;
	}

	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}

}
