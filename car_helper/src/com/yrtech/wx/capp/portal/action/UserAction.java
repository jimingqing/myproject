package com.yrtech.wx.capp.portal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ralasafe.db.sql.Operand;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.CheckUtil;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.ProvCity;
import com.yrtech.wx.capp.portal.model.UserBankRel;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.service.MerInfoSrv;
import com.yrtech.wx.capp.portal.service.UserInfoSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;
import com.yrtech.wx.capp.portal.vo.UserInfoVo;

public class UserAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String merId;
	private String userCode;
	private String userId;
	private String phoneNo;
	private String merCode;
	private String type;
	private String state;
	private String curPage;
	
	private String userName;//用户姓名
	private String certId;//身份证号码
	private String bankId;//默认快捷支付银行id
	
	private String provId;
	private String cityId;
	private String areaId;
	private String certType;
	private String userSex;
	private String email;
	private String userAddr;
	private String userZip;
	private String deptName;
	private String position;
	private String openBankName;
	private String openBankCard;
	
	private String pwd;
	
	private String auditFlag;
	private String bankRelId;
	private String acctBal;
	private String qryType;
	
	
	public String getPwd()
	{
		return pwd;
	}

	public void setPwd( String pwd )
	{
		this.pwd = pwd;
	}

	@Resource
	private UserInfoSrv userInfoSrv;
	@Resource
	private MerInfoSrv merInfoSrv;
	
	public String editUserAcctBal(){
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(userId)){
			log.info("用户号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(acctBal)){
			log.info("账户余额错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "账户余额错误");
			return SUCCESS;
		}
		
		try {
			retInfo = userInfoSrv.editUserAcctBal(getMerOperInfoVo().getMerId(), Integer.valueOf(userId), acctBal);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("用户账户余额修改成功，merId="+getMerOperInfoVo().getMerId()+", userId="+userId+", acctBal="+acctBal);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "用户信息修改成功");
			}else{
				log.info("用户账户余额修改失败，merId="+getMerOperInfoVo().getMerId()+", userId="+userId+", acctBal="+acctBal);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户账户余额修改失败");
			}
			
		} catch (Exception e) {
			log.error("用户账户余额修改异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "用户账户余额修改异常");
		}
		return SUCCESS;
	}
	
	public String qryUserInfoByCode(){
		if(StringUtils.isEmpty(userCode)){
			log.info("用户编号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户编号错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = userInfoSrv.qryUserInfoByCode(getMerOperInfoVo().getMerId(), userCode);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				RetInfo retInfoMer = merInfoSrv.qryMerInfo("", "", "");
				Map<Integer, MerInfo> merMap = new HashMap<Integer, MerInfo>();
				for(MerInfo o : (List<MerInfo>)retInfoMer.getList()){
					merMap.put(o.getId(), o);
				}
				
				UserInfo userInfo = (UserInfo)retInfo.getList().get(0);
				UserInfoVo vo = new UserInfoVo();
				PropertyUtils.copyProperties(vo, userInfo);
				Map<String, ProvCity> map = (Map<String, ProvCity>)DataCacheManager.get(Constants.CACHE_KEY_PROVCITY);
				if(userInfo.getUserProv()!=null){
					vo.setUserProvName(map.get(userInfo.getUserProv()).getName());
				}
				if(userInfo.getUserArea()!=null){
					vo.setUserAreaName(map.get(userInfo.getUserArea()).getName());
				}
				vo.setMerName(merMap.get(vo.getMerId()).getMerName());
				vo.setMerCode(merMap.get(vo.getMerId()).getMerCode());
				
				List<UserBankRel> bankRels = (List<UserBankRel>)retInfo.getList().get(1);
				if(bankRels!=null && bankRels.size()>0){
					String cardno = SecretUtil.getDecryptedString(bankRels.get(0).getBankCardNo());
					bankRels.get(0).setBankCardNo(cardno.substring(cardno.length()-4));
					bankRels.get(0).setProtFileName("");
				}
				vo.setBankRels(bankRels);
				
				log.info("查询用户明细信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息成功");
				resultMap.put(Constants.JSON_CONTENT, vo);
			}else{
				log.info("查询用户信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询用户明细信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息异常");
		}
		return SUCCESS;
	}
	
	public String resetUserPwd(){
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(userId)){
			log.info("参数错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "参数错误");
			return SUCCESS;
		}
		
		try {
			retInfo = userInfoSrv.editUserPwd(getMerOperInfoVo().getMerId(), userId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改用户密码信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改用户密码信息成功");
			}else{
				log.info("修改用户密码信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改用户密码信息失败");
			}
			
		} catch (Exception e) {
			log.error("修改用户密码信息失败", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改用户密码信息失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 解除绑定快捷支付银行卡
	 * @return
	 */
	public String editUserBankRelState(){
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(bankRelId)){
			log.info("参数错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "参数错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(type)){
			log.info("参数错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "参数错误");
			return SUCCESS;
		}
		
		try {
			retInfo = userInfoSrv.editUserBankState(bankRelId,type);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改用户快捷支付银行状态信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改用户快捷支付银行状态信息成功");
			}else{
				log.info("修改用户快捷支付银行状态信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改用户快捷支付银行状态信息失败");
			}
			
		} catch (Exception e) {
			log.error("修改用户快捷支付银行状态信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改用户快捷支付银行状态信息异常！");
		}
		return SUCCESS;
		
	}
	
	public String editUserInfo(){
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(userId)){
			log.info("用户号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(userName)){
			log.info("用户姓名错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户姓名错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(phoneNo)){
			log.info("电话号码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "电话号码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(provId)){
			log.info("所在省份错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在省份错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(cityId)){
			log.info("所在城市错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在城市错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(certType)){
			log.info("证件类型错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "证件类型错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(certId)){
			log.info("证件号码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "证件号码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(deptName)){
			log.info("所在单位名称错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在单位名称错误");
			return SUCCESS;
		}
		
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(Integer.valueOf(userId));
			userInfo.setUserCode(phoneNo);
			userInfo.setPhoneNo(phoneNo);
			userInfo.setUserName(userName);
			userInfo.setUserSex(userSex);
			if(StringUtils.isNotEmpty(certId)){
				userInfo.setCertId(SecretUtil.getEncryptedString(certId));
			}
			userInfo.setCertType(certType);
			userInfo.setUserProv(provId);
			userInfo.setUserArea(areaId);
			userInfo.setUserCity(cityId);
			userInfo.setUserZip(userZip);
			userInfo.setUserAddr(userAddr);
			userInfo.setOpenBankName(openBankName);
			userInfo.setOpenBankCard(openBankCard);
			userInfo.setDeptName(deptName);
			userInfo.setPosition(position);
			userInfo.setEmail(email);
			userInfo.setUserState(state);
			
			UserBankRel userBankRel = new UserBankRel();
			if(!StringUtils.isEmpty(bankRelId)){
				userBankRel.setId(Integer.valueOf(bankRelId));
			}
			if(!StringUtils.isEmpty(openBankName)){
				userBankRel.setBankId(Integer.valueOf(openBankName));
			}
			userBankRel.setBankCardNo(SecretUtil.getEncryptedString(openBankCard));
			
			retInfo = userInfoSrv.editUserInfo(userInfo, userBankRel);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("用户信息修改成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "用户信息修改成功");
			}else if(retInfo.getRetCode().equals(Constants.RET_CODE_REGIST_USER_EXISTS)){
				log.info("用户手机号码已注册");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户手机号码已注册");
			}else{
				log.info("用户信息修改失败，phoneNo="+phoneNo);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户信息修改失败");
			}
			
		} catch (Exception e) {
			log.error("用户信息保存异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "用户信息保存异常！");
		}
		return SUCCESS;
	}
	
	public String checkUserBankRelInfo(){
		if(StringUtils.isEmpty(userId)){
			log.info("用户号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(bankId)){
			log.info("开户银行错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "开户银行错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(openBankCard)){
			log.info("开户银行账号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "开户银行账号错误");
			return SUCCESS;
		}
		
		try {
			if(userInfoSrv.checkUserBankCardExist(Integer.valueOf(userId), bankId, openBankCard)){
				log.info("用户银行卡信息已存在");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_USER_BANKCARD_EXISTS);
				resultMap.put(Constants.JSON_RETMSG, "用户银行卡信息已存在");
			}else{
				log.info("用户银行卡信息不存在");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_USER_BANKCARD_NOEXISTS);
				resultMap.put(Constants.JSON_RETMSG, "用户银行卡信息不存在");
			}
		} catch (Exception e) {
			log.error("用户银行卡信息校验异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "用户银行卡信息校验异常");
		}
		return SUCCESS;
		
	}
	
	public String auditUserInfo(){
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(userId)){
			log.info("用户id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(auditFlag)){
			log.info("审核标识错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "审核标识错误");
			return SUCCESS;
		}
		
		try {
			if(auditFlag.equals("Y")){
				state = Constants.USER_STATE_N;
			}else{
				state = Constants.USER_STATE_D;
			}
			retInfo = userInfoSrv.editUserInfoState(getMerOperInfoVo().getMerId(), Integer.valueOf(userId), state);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("审核用户信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "审核用户信息成功");
			}else{
				log.info("审核用户信息失败，phoneNo="+phoneNo);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "审核用户信息失败");
			}
		} catch (Exception e) {
			log.error("审核用户信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "审核用户信息异常");
		}
		return SUCCESS;
	}
	
	public String addUserInfo(){
		
		MerOperInfoVo  vo =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		 if(vo==null)
		 {
			 resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			 resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			 return SUCCESS;
		 }
		 
	if(!(vo.getMerCode()+"").equals("880001"))
	{
		System.out.println(merId);
		System.out.println(vo.getMerId());
		 if(!(vo.getMerId()+"").equals(merId))//传过来的商户id和登录不一样
		 {
			  resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
			  resultMap.put(Constants.JSON_RETMSG, "你没有增加其他商户用户的权限");
			  return  SUCCESS;
		 }
	}
		RetInfo retInfo = new RetInfo();
		if(StringUtils.isEmpty(userName)){
			log.info("用户姓名错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户姓名错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(phoneNo)){
			log.info("电话号码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "电话号码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(provId)){
			log.info("所在省份错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在省份错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(cityId)){
			log.info("所在城市错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在城市错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(certType)){
			log.info("证件类型错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "证件类型错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(certId)){
			log.info("证件号码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "证件号码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(deptName)){
			log.info("所在单位名称错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在单位名称错误");
			return SUCCESS;
		}
		/*if(merId==null || (!merId.equals(getMerOperInfoVo().getMerId()+"")))
		{
			System.out.println(merId);
			System.out.println(getMerOperInfoVo().getMerId());
//			System.out.println("aaaaaaaaaaaaaaaaaaaaa");
			resultMap.put(Constants.JSON_RETCODE, "2312");
			resultMap.put(Constants.JSON_RETMSG, "商户编号错误");
			return SUCCESS;
		}*/
		try {
//			String pwd = certId.substring(certId.length()-6);
//			MerOperInfoVo  vo =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
//			 if(vo==null)
//			 {
//				 resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
//				 resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
//				 return SUCCESS;
//			 }
//			 
//		if(!(vo.getMerCode()+"").equals("880001"))
//		{
//			 if(!merId.equals(vo.getMerId()+""))
//			 {
//				  resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
//				  resultMap.put(Constants.JSON_RETMSG, "你没有增加其他商户用户的权限");
//				  return  SUCCESS;
//			 }
//		}
			
			UserInfo userInfo = new UserInfo();
			userInfo.setMerId(new Integer(merId));
			userInfo.setUserCode(phoneNo);
			userInfo.setPhoneNo(phoneNo);
			userInfo.setUserName(userName);
			userInfo.setUserType(Constants.USER_TYPE_P);
			userInfo.setUserSex(userSex);
			userInfo.setCertId(SecretUtil.getEncryptedString(certId));
			userInfo.setCertType(certType);
			userInfo.setUserProv(provId);
			userInfo.setUserArea(areaId);
			userInfo.setUserCity(cityId);
			userInfo.setUserAddr(userAddr);
			userInfo.setOpenTime(DateOper.getCurTimestamp());
			userInfo.setSignDate(DateOper.getCurTimestamp());
			userInfo.setOpenBankName(openBankName);
//			userInfo.setOpenBankCard(SecretUtil.getEncryptedString(openBankCard));
			userInfo.setDeptName(deptName);
			userInfo.setPosition(position);
			userInfo.setEmail(email);
			userInfo.setLoginPwd(new MD5(pwd).getStrDigest());
			userInfo.setUserState(Constants.USER_STATE_A);
			userInfo.setChannel(Constants.USER_INFO_CHANNEL_C);
//			userInfo.setAcctBal(0d);
			userInfo.setMerId(new Integer(merId));
			retInfo = userInfoSrv.addUserInfo(userInfo);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("用户信息保存成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "用户信息保存成功");
			}
			else if(retInfo.getRetCode().equals(Constants.RET_CODE_REGIST_USER_EXISTS)){
				log.info("用户手机号码已注册");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户手机号码已注册");
			}
			else{
				log.info("用户信息保存失败，phoneNo="+phoneNo);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户信息保存失败");
			}
			
		} catch (Exception e) {
			log.error("用户信息保存异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "用户信息保存异常！");
		}
		return SUCCESS;
	}
	
	/**
	 * 验证手机号码是否已注册
	 * 参数：merCode + phoneNo + type + 随机串 + 签名串
	 * @return
	 */
	
	public String checkPhoneNo(){
		RetInfo retInfo = new RetInfo();
		try {
			if(StringUtils.isEmpty(phoneNo)){
				log.info("手机号码错误， phoneNo="+phoneNo+"; merCode="+merCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "手机号码错误");
				return SUCCESS;
			}
			if(!CheckUtil.isPhoneNo(phoneNo)){
				log.info("手机号码不正确， phoneNo="+phoneNo+"; merCode="+merCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "手机号码不正确");
				return SUCCESS;
			}
			
			retInfo = userInfoSrv.qryUserInfo(phoneNo, getMerOperInfoVo().getMerId());
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("手机号码已注册");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "手机号码已注册");
				resultMap.put(Constants.JSON_CONTENT, ((UserInfo)retInfo.getObject()).getId());
			}else{
				log.info("手机号码未注册！phoneNo="+phoneNo);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "手机号码未注册");
			}
			
		} catch (Exception e) {
			log.error("发送短信异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "发送短信异常！");
		}
		return SUCCESS;
	}
	
	public String qryUserInfoDetail(){
		if(StringUtils.isEmpty(userId) || !CheckUtil.isNumeric(userId)){
			log.info("用户号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户号错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = userInfoSrv.qryUserInfoById( userId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				UserInfo  userInfo = (UserInfo)retInfo.getObject();
				log.info("查询用户明细信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息成功");
				resultMap.put(Constants.JSON_CONTENT, userInfo);
			}else{
//				log.info("查询用户信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询用户明细信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户明细信息异常");
		}
		return SUCCESS;
	}
	
	public String qryUserInfo(){
	try {
		MerOperInfoVo  vo1 =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		if(vo1==null)
		{
			resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
		     resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			return SUCCESS;
		}
			 
		
		if(!(vo1.getMerCode()+"").equals("880001"))
		{
			System.out.println(merId);
			System.out.println(vo1.getMerId());
			 if(!(vo1.getMerId()+"").equals(merId))
			 {
				  resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
				  resultMap.put(Constants.JSON_RETMSG, "你没有查询其他商户用户的权限");
				  return  SUCCESS;
			 }
		}
			
			
			
			
			
			
			Pager pager = new Pager();
			pager.setCurrentPage(Integer.valueOf(curPage==null?"1":curPage));
		/*	if(merId==null || (!merId.equals(getMerOperInfoVo().getMerId()+"")))
			{
				System.out.println(merId);
				System.out.println(getMerOperInfoVo().getMerId());
//				System.out.println("aaaaaaaaaaaaaaaaaaaaa");
				resultMap.put(Constants.JSON_RETCODE, "2312");
				resultMap.put(Constants.JSON_RETMSG, "商户编号错误");
				return SUCCESS;
			}*/
			if(merId==null || merId.length()==0)
			{
				resultMap.put(Constants.JSON_RETCODE, Constants.BLANK);
				resultMap.put(Constants.JSON_RETMSG, "商户id不能为空");
				return SUCCESS;
			}
			RetInfo retInfo = userInfoSrv.qryUserInfo(new Integer(merId), provId, cityId, areaId, phoneNo, state, qryType, pager );
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				RetInfo retInfoMer = merInfoSrv.qryMerInfo("", "", "");
				Map<Integer, String> merMap = new HashMap<Integer, String>();
				for(MerInfo o : (List<MerInfo>)retInfoMer.getList()){
					merMap.put(o.getId(), o.getMerName());
				}
				
				List<UserInfoVo> list = new ArrayList<UserInfoVo>();
				UserInfoVo vo = new UserInfoVo();
				for(UserInfo o:(List<UserInfo>)retInfo.getList()){
					vo = new UserInfoVo();
					PropertyUtils.copyProperties(vo, o);
					vo.setMerName(merMap.get(o.getMerId()));
//					vo.setAcctBalStr(GmsUtil.double2amt(o.getAcctBal()));
					if(StringUtils.isEmpty(vo.getDeptName())){
						vo.setDeptName("");
					}
					list.add(vo);
				}
				
				Map<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("datalist", list);
				retmap.put("pager", pager);
				
//				log.info("查询用户信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询用户信息成功");
				resultMap.put(Constants.JSON_CONTENT, retmap);
			}else{
				log.info("查询用户信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户信息失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询用户信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户信息异常");
		}
		return SUCCESS;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserZip() {
		return userZip;
	}

	public void setUserZip(String userZip) {
		this.userZip = userZip;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getOpenBankCard() {
		return openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getBankRelId() {
		return bankRelId;
	}

	public void setBankRelId(String bankRelId) {
		this.bankRelId = bankRelId;
	}

	public String getAcctBal() {
		return acctBal;
	}

	public void setAcctBal(String acctBal) {
		this.acctBal = acctBal;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}
	
}
