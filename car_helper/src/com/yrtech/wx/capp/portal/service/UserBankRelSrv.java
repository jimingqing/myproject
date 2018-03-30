package com.yrtech.wx.capp.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.FileUtil;
import com.yrtech.wx.capp.portal.dao.impl.UserBankRelDao;
import com.yrtech.wx.capp.portal.model.UserBankRel;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class UserBankRelSrv {

	private Log log = LogFactory.getLog(this.getClass());
	
	private RetInfo retInfo = new RetInfo();
	
	@Resource
	private UserBankRelDao userBankRelDao;
	
	/**
	 * 修改用户默认使用签约银行信息
	 * @param userId
	 * @param userBankRelId
	 * @return
	 */
	public RetInfo editUserDefBankInfo(Integer merId, Integer userId, Integer userBankRelId){
		try {
			UserBankRel userBankRel = userBankRelDao.find(userBankRelId);
			if(userBankRel==null){
				log.info("用户快捷支付银行id错误");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("用户快捷支付银行id错误");
			}else{
				String hql = "update UserBankRel set defState = 'N' where userId="+userId+" and merId="+merId;
				userBankRelDao.executeDML(hql);
				
				userBankRel.setDefState(Constants.BANK_DEF_STATE_Y);
				userBankRelDao.update(userBankRel);
				
				log.info("更新用户默认使用签约银行信息成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("更新用户默认使用签约银行信息成功");
			}
		} catch (Exception e) {
			log.error("更新用户默认使用签约银行信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("更新用户默认使用签约银行信息异常");
		}
		return retInfo;
	}
	
	/**
	 * 保存用户签约银行信息
	 * @param userBankRel
	 * @return
	 */
	public RetInfo saveUserBankRel(UserBankRel userBankRel, String[] params){
		try{
			if(userBankRel==null){
				log.info("参数错误");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("参数错误");
			}
			//检查银行卡是否真实有效，调用银联接口进行验证
			userBankRel.setChkFlag("O");
			userBankRel.setChkStat("N");
			
			//生成pdf格式的协议文件
			String agreeUrl = Config.getProperty("agree_file_path")+"?merName="+params[0]+"&merBankName="+params[1]+"&merBankCardNo="+params[2]+"&userName="+params[3]+"&certId="+params[4]+"&userBankName="+params[5]+"&userBankCardNo="+params[6];
			String agreeFileName = params[8]+"_"+params[7]+params[5]+".pdf";
			FileUtil.generationPdf(agreeUrl, Config.getProperty("agree_pdf_save_path")+agreeFileName);
			userBankRel.setProtFileName(agreeFileName);
			
			userBankRelDao.save(userBankRel);
			log.info("保存用户签约银行信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("保存用户签约银行信息成功");
		} catch (Exception e) {
			log.error("保存用户签约银行信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("保存用户签约银行信息异常");
		}
		return retInfo;
	}
	
	public RetInfo qryUserBankRel(Integer userId){
		if(userId==null){
			log.info("参数错误");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("参数错误");
			return retInfo;
		}
		String hql = "from UserBankRel where userId=? and state=? order by defState desc";
		List<UserBankRel> list = userBankRelDao.findTbyHql(hql, new Object[]{userId, "N"});
		if(list!=null && list.size()>0){
			log.info("查询用户银行信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询用户银行信息成功");
			retInfo.setList(list);
		}else{
			log.info("查询用户银行信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询用户银行信息失败");
		}
		return retInfo;
	}
	
}
