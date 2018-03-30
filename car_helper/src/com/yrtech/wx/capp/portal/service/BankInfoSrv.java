package com.yrtech.wx.capp.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.portal.dao.impl.BankInfoDao;
import com.yrtech.wx.capp.portal.model.BankInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class BankInfoSrv {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private BankInfoDao bankInfoDao;
	
	/**
	 * 检查银行信息是否正确
	 */
	public boolean checkBankInfo(Integer bankId, String bankName){
		try {
			String hql = "from BankInfo where id=? and bankName=? and state='N'";
			List<BankInfo> list = bankInfoDao.findTbyHql(hql, 
					new Object[]{bankId, bankName});
			if(list==null||list.size()==0){
				log.info("银行信息查询失败");
				return false;
			}else{
				log.info("银行信息查询成功");
				return true;
			}
			
		} catch (Exception e) {
			log.error("银行信息查询异常",e);
			return false;
		}
	}
	
	/**
	 * 查询银行信息
	 * @return
	 */
	public RetInfo listBankInfo(){
		RetInfo retInfo = new RetInfo();
		try {
			String hql = "from BankInfo where state = ?";
			List<BankInfo> list = bankInfoDao.findTbyHql(hql, new Object[]{Constants.BANK_STATE_N});
			if(list!=null && list.size()>0){
				log.info("查询银行信息成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("查询银行信息成功");
				retInfo.setList(list);
			}else{
				log.info("查询银行信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询银行信息失败");
			}
		} catch (Exception e) {
			log.error("查询银行信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询银行信息异常");
		}
		return retInfo;
	}
	
	
	
}
