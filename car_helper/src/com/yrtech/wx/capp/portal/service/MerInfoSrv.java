package com.yrtech.wx.capp.portal.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.FileUtil;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.MerOperInfoDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.MerOperInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class MerInfoSrv {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private MerInfoDao merInfoDao;
	@Resource
	private MerOperInfoDao merOperInfoDao;
	
	public RetInfo editOperPwd(String operId, String pwd) throws Exception{
		
		RetInfo retInfo = new RetInfo();
		String hql = "update MerOperInfo set operPwd='"+new MD5(pwd).getStrDigest()+ "' where  id="+operId;
		int ret = merOperInfoDao.executeDML(hql);
		if(ret>0){
			log.info("修改操作员密码成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改操作员密码成功");
		}else{
			log.info("修改操作员密码失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("修改操作员密码失败");
		}
		
		return retInfo;
	}
	
	public RetInfo editMerOperInfo(MerOperInfo merOperInfo){
		RetInfo retInfo = new RetInfo();
		MerOperInfo oper = merOperInfoDao.find(merOperInfo.getId());
		if(oper!=null){
			oper.setOperEmail(merOperInfo.getOperEmail());
			oper.setOperMp(merOperInfo.getOperMp());
			oper.setOperName(merOperInfo.getOperName());
			oper.setOperState(merOperInfo.getOperState());
			oper.setOperTel(merOperInfo.getOperTel());
			oper.setGrpId(merOperInfo.getGrpId());
			merOperInfoDao.update(oper);
			
			log.info("修改商户操作员信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改商户操作员信息成功");
		}else{
			log.info("修改商户操作员信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("修改商户操作员信息失败");
		}
		
		return retInfo;
	}
	
	public RetInfo qryMerOperInfo( String operId){
		RetInfo retInfo = new RetInfo();
		String hql = "from MerOperInfo where id=?";
		List<MerOperInfo> list = merOperInfoDao.findTbyHql(hql, new Object[]{
				Integer.valueOf(operId)
		});
		if(list!=null&&list.size()>0){
			log.info("查询商户操作员信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询商户操作员信息成功");
			retInfo.setObject(list.get(0));
		}else{
			log.info("查询商户操作员信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询商户操作员信息失败");
		}
		
		return retInfo;
	}
	
	public RetInfo editMerInfo(MerInfo merInfo){
		RetInfo retInfo = new RetInfo();
		MerInfo tmp = merInfoDao.find(merInfo.getId());
		if(tmp!=null){
			tmp.setMerName(merInfo.getMerName());
			tmp.setMerProv(merInfo.getMerProv());
			tmp.setMerArea(merInfo.getMerArea());
			tmp.setMerAddr(merInfo.getMerAddr());
			tmp.setRegCode(merInfo.getRegCode());
			tmp.setCorpCode(merInfo.getCorpCode());
			tmp.setMerZip(merInfo.getMerZip());
			tmp.setMerState(merInfo.getMerState());
			tmp.setUnMerId(merInfo.getUnMerId());
			tmp.setMerBankName(merInfo.getMerBankName());
			tmp.setMerBankAcct(merInfo.getMerBankAcct());
			tmp.setIsRealWh(merInfo.getIsRealWh());
			tmp.setDayTransCnt(merInfo.getDayTransCnt());
			tmp.setOneLimitAmt(merInfo.getOneLimitAmt());
			tmp.setTotalLimitAmt(merInfo.getTotalLimitAmt());
			tmp.setIsSendSms(merInfo.getIsSendSms());
			tmp.setMerContEmail(merInfo.getMerContEmail());
			tmp.setMerContMp(merInfo.getMerContMp());
			tmp.setMerContName(merInfo.getMerContName());
			tmp.setMerContRole(merInfo.getMerContRole());
			tmp.setMerContTel(merInfo.getMerContTel());
			try{
				tmp.setLastTime(DateOper.getCurTimestamp());
			}catch(Exception e){
				e.printStackTrace();
			}
			merInfoDao.update(tmp);
			
			log.info("修改商户信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改商户信息成功");
		}else{
			log.info("商户信息查询失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("商户信息查询失败");
		}
		
		return retInfo;
	}
	
	public RetInfo qryMerOperInfo(Integer merId, String operCode, String operState){
		RetInfo retInfo = new RetInfo();
		String hql = "from MerOperInfo where merId="+merId+" ";
		if(!StringUtils.isEmpty(operCode)){
			hql += "and operCode='"+operCode+"' ";
		}
		if(!StringUtils.isEmpty(operState) && !Constants.SELECT_ALL.equals(operState.toUpperCase())){
			hql += "and operState='"+operState+"' ";
		}
		hql += "order by createTime desc, isAdmin, operState";
		List<MerOperInfo> list = merOperInfoDao.findTbyHql(hql);
		log.info("查询商户操作员信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询商户操作员信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo addMerOperInfo(MerOperInfo merOperInfo){
		RetInfo retInfo = new RetInfo();
		String hql = "from MerOperInfo where merId=? and operCode=?";
		List list = merOperInfoDao.findTbyHql(hql, new Object[]{merOperInfo.getMerId(), merOperInfo.getOperCode()});
		if(list!=null && list.size()>0){
			log.info("操作员号已存在");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("操作员号已存在");
			return retInfo;
		}
		merOperInfoDao.save(merOperInfo);
		
		log.info("新增商户操作员信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("新增商户操作员信息成功");
		return retInfo;
	}
	
	/**
	 * 检查操作员号是否已存在
	 * @param operCode，merId
	 * @return  true:存在， false：不存在
	 */
	public boolean checkOperCode(String operCode, Integer merId){
		String hql = "from MerOperInfo where merId=? and operCode=?";
		List list = merOperInfoDao.findTbyHql(hql, new Object[]{merId, operCode });
		if(list!=null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public RetInfo qryMerInfoById(String merId){
		RetInfo retInfo = new RetInfo();
		MerInfo merInfo = merInfoDao.find(Integer.valueOf(merId));
		if(merInfo!=null){
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询商户明细信息成功");
			retInfo.setObject(merInfo);
		}else{
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询商户明细信息失败");
		}
		return retInfo;
	}
	
	public RetInfo qryMerInfo(String merCode, String merName, String state){
		RetInfo retInfo = new RetInfo();
		String hql = "from MerInfo where 1=1 ";
		if(!StringUtils.isEmpty(merCode)){
			hql += "and merCode='"+merCode+"' ";
		}
		if(!StringUtils.isEmpty(merName)){
			hql += "and merName like '%"+merName+"%' ";
		}
		if(!StringUtils.isEmpty(state) && !Constants.SELECT_ALL.equals(state.toUpperCase())){
			hql += "and merState='"+state+"' ";
		}
		hql += "order by openTime desc, merCode";
		List<MerInfo> list = merInfoDao.findTbyHql(hql);
		log.info("查询商户信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询商户信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo addMerInfo(MerInfo merInfo, File cerFile, String cerFileName, File pfxFile, String pfxFileName){
		RetInfo retInfo = new RetInfo();
		String hql = "from MerInfo where merCode=?";
		List list = merInfoDao.findTbyHql(hql, new Object[]{merInfo.getMerCode()});
		if(list!=null && list.size()>0){
			log.info("商户号已存在");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("商户号已存在");
			return retInfo;
		}
		merInfoDao.save(merInfo);
		
		//创建协议商户目录
//		FileUtil.mkdir(Config.getProperty("agree_pdf_save_path")+merInfo.getMerCode(), false);
		//创建商户商品图片保存路径
//		FileUtil.mkdir(Config.getProperty("goods_pic_save_dir")+merInfo.getMerCode(), false);
		//创建证书文件目录
//		FileUtil.mkdir(Config.getProperty("mer_cer_pfx_file_dir")+merInfo.getMerCode(), false);
		//保存证书文件
//		File file = new File(Config.getProperty("mer_cer_pfx_file_dir")+merInfo.getMerCode()+"/"+cerFileName);
//		FileUtil.copy(cerFile, file);
//		file = new File(Config.getProperty("mer_cer_pfx_file_dir")+merInfo.getMerCode()+"/"+pfxFileName);
//		FileUtil.copy(pfxFile, file);
		
		log.info("新增商户信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("新增商户信息成功");
		return retInfo;
	}
	
	/**
	 * 检查商户号是否已存在
	 * @param merCode
	 * @return  true:存在， false：不存在
	 */
	public boolean checkMerCode(String merCode){
		String hql = "from MerInfo where merCode=?";
		List list = merInfoDao.findTbyHql(hql, new Object[]{merCode});
		if(list!=null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
