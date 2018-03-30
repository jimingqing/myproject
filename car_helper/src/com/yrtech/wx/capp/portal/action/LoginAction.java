package com.yrtech.wx.capp.portal.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.net.IPUtil;
import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.MerOperInfo;
import com.yrtech.wx.capp.portal.service.UserInfoSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;

public class LoginAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private UserInfoSrv userInfoSrv;
	
	private String merCode;
	private String operCode;
	private String phoneNo;
	private String pwd;
	private String verifyCode;
	private String type; 	//用户类型, R:收款用户，P：付款用户
	private String userId;
	
	private void initAuthInfo(){
		Map<Integer, AuthInfo> map = (Map<Integer, AuthInfo>)DataCacheManager.get(Constants.CACHE_KEY_AUTHINFO);
		List<AuthInfo> oneLevelList = new ArrayList<AuthInfo>();
		List<AuthInfo> twoLevelList = new ArrayList<AuthInfo>();
		List<AuthInfo> threeLevelList = new ArrayList<AuthInfo>();
		for(AuthInfo o:map.values()){
			if(o.getAuthLevel()==1){
				oneLevelList.add(o);
			}
			else if(o.getAuthLevel()==2){
				twoLevelList.add(o);
			}
			else if(o.getAuthLevel()==3){
				threeLevelList.add(o);
			}
		}
		session().setAttribute(Constants.SESSION_ONE_LEVEL_AUTH, oneLevelList);
		session().setAttribute(Constants.SESSION_TWO_LEVEL_AUTH, twoLevelList);
		session().setAttribute(Constants.SESSION_THREE_LEVEL_AUTH, threeLevelList);
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String login(){
		if(StringUtils.isEmpty(merCode)||merCode.length()!=6){
			log.info("商户编号错误，  operCode="+operCode+"; merCode="+merCode);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户编号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(operCode)){
			log.info("操作员号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(pwd)){
			log.info("登录密码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "登录密码错误");
			return SUCCESS;
		}
		String sessionVerifyCode = (String)session().getAttribute("SESSION_SECURITY_CODE");
		if(StringUtils.isEmpty(verifyCode) || !verifyCode.toLowerCase().equals(sessionVerifyCode)){
			log.info("验证码错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "验证码错误");
			return SUCCESS;
		}
		session().removeAttribute("SESSION_SECURITY_CODE");
		try{
			String ip = IPUtil.getClinetIpByReq(request());
			pwd = new MD5(pwd).getStrDigest();
			RetInfo retInfo = userInfoSrv.checkMerOperInfo(merCode, operCode, pwd, ip);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				Map map = (Map)retInfo.getObject();
				MerInfo merInfo = (MerInfo)map.get("merInfo");
				MerOperInfo merOperInfo = (MerOperInfo)map.get("operInfo");
				MerOperInfoVo vo = new MerOperInfoVo();
				PropertyUtils.copyProperties(vo, merOperInfo);
				vo.setMerCode(merInfo.getMerCode());
				vo.setMerName(merInfo.getMerName());
				vo.setMerId(merInfo.getId());
				vo.setMerProv(merInfo.getMerProv());
				session().setAttribute(Constants.USER_INFOVO, vo);
				session().setAttribute(Constants.SESSION_AUTH_INFO, map.get("authInfo"));
				
				log.info("用户登录成功，merCode="+merCode+", operCode="+operCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "登录成功");
				resultMap.put(Constants.JSON_CONTENT, vo);
//				initAuthInfo();
				
			}else if(retInfo.getRetCode().equals(Constants.RET_CODE_LOGIN_PWD_ERROR)){
				MerOperInfo merOperInfo = (MerOperInfo)retInfo.getObject();
				MerOperInfoVo vo = new MerOperInfoVo();
				PropertyUtils.copyProperties(vo, merOperInfo);
				vo.setPwdErrCnt(Integer.parseInt(Constants.USER_PWD_ERR_LIMIT)-vo.getPwdErrCnt());
				
				log.info("操作员密码错误，merCode="+merCode+", operCode="+operCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_LOGIN_PWD_ERROR);
				if(vo.getPwdErrCnt()==0){
					resultMap.put(Constants.JSON_RETMSG, "密码错误次数过多，操作员已被锁定");
				}else{
					resultMap.put(Constants.JSON_RETMSG, "密码错误，还可以再输入"+vo.getPwdErrCnt()+"次");
				}
			}else if(retInfo.getRetCode().equals(Constants.RET_CODE_LOGIN_STATE_ERROR)){
				MerOperInfo merOperInfo = (MerOperInfo)retInfo.getObject();
				MerOperInfoVo vo = new MerOperInfoVo();
				PropertyUtils.copyProperties(vo, merOperInfo);
				
				log.info("操作员状态异常，merCode="+merCode+", operCode="+operCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_LOGIN_STATE_ERROR);
				if(vo.getOperState().equals(Constants.OPER_STATE_L)){
					resultMap.put(Constants.JSON_RETMSG, "操作员已被锁定，请联系管理员！");
				}else{
					resultMap.put(Constants.JSON_RETMSG, "操作员状态异常");
				}
			}else{
				log.info("操作员登录失败，merCode="+merCode+", operCode="+operCode);
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "操作员号或密码错误");
			}
		} catch (Exception e) {
			log.error("操作员登录异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员登录异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 操作员登出
	 * @return
	 */
	public String logout(){
		MerOperInfoVo merOperInfoVo = (MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		session().invalidate();
		session().removeAttribute(Constants.USER_INFOVO);
		log.info("用户登出成功，merCode="+merOperInfoVo.getMerId()+", operCode="+merOperInfoVo.getOperCode());
		resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
		resultMap.put(Constants.JSON_RETMSG, "登出成功");
		return SUCCESS;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	
}