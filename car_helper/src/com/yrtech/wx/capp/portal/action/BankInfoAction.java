package com.yrtech.wx.capp.portal.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.service.BankInfoSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.UserInfoVo;

public class BankInfoAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private BankInfoSrv bankInfoSrv;
	
	/**
	 * 查询银行信息列表
	 * @return
	 */
	public String qryBankInfoList(){
		RetInfo retInfo = new RetInfo();
		try {
			retInfo = bankInfoSrv.listBankInfo();
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询银行信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询银行信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询银行信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询银行信息失败");
			}
		} catch (Exception e) {
			log.error("查询银行信息异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询银行信息异常");
		}
		return SUCCESS;
	}
	
}
