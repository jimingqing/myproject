package com.yrtech.wx.capp.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.portal.dao.impl.AuthInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.MerInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.MerOperInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.UserBankRelDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.UserLimitDao;
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.model.BankInfo;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.MerOperInfo;
import com.yrtech.wx.capp.portal.model.UserBankRel;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.model.UserLimit;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.PageData;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class UserInfoSrv {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private MerInfoDao merInfoDao;
	@Resource
	private MerOperInfoDao merOperInfoDao;
	@Resource
	private UserBankRelDao userBankRelDao;
	@Resource
	private UserLimitDao userLimitDao;
	@Resource
	private AuthInfoDao authInfoDao;
	
	public RetInfo editUserAcctBal(Integer merId, Integer userId, String acctBal){
		RetInfo retInfo = new RetInfo();
		String hql = "update UserInfo set acctBal="+acctBal+" where id="+userId+" and merId="+merId;
		int ret = userInfoDao.executeDML(hql);
		if(ret==1){
			log.info("修改用户账户余额信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改用户账户余额信息成功");
		}else{
			log.info("修改用户账户余额信息失败");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改用户账户余额信息失败");
		}
		return retInfo;
	}
	
	public RetInfo qryUserInfoByCode(Integer merId, String userCode){
		RetInfo retInfo = new RetInfo();
		String hql = "from UserInfo where merId=? and userCode=?";
		List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{merId, userCode});
		if(list!=null&&list.size()>0){
			hql = "from UserBankRel where userId=? and defState=?";
			List<UserBankRel> banklist = userBankRelDao.findTbyHql(hql, new Object[]{list.get(0).getId(), Constants.YES_OR_NO_FLAG_Y});
			
			List tmp = new ArrayList();
			tmp.add(list.get(0));
			tmp.add(banklist);
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询用户明细信息成功");
			retInfo.setList(tmp);
		}else{
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询用户明细信息失败");
		}
		return retInfo;
	}
	
	public RetInfo editUserPwd(Integer merId, String userId) throws Exception{
		RetInfo retInfo = new RetInfo();
		String hql = "from UserInfo where id=? and merId=?";
		List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{
				Integer.valueOf(userId), merId
		});
		if(list!=null && list.size()>0){
			String certId = SecretUtil.getDecryptedString(list.get(0).getCertId());
			
			System.out.println("密码"+certId.substring(certId.length()-6));
			String newpwd = new MD5(certId.substring(certId.length()-6)).getStrDigest();
			
			
			hql = "update UserInfo set loginPwd='"+newpwd+"' where id="+userId;
			userInfoDao.executeDML(hql);
		}
		
		log.info("修改用户密码信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改用户密码信息成功");
		return retInfo;
	}
	
	public RetInfo editUserBankState(String userBankRelId, String type){
		RetInfo retInfo = new RetInfo();
		String hql = "";
		if(type.equals(Constants.STATE_FLAG_C)){
			hql = "update UserBankRel set state='"+Constants.STATE_FLAG_C+"' where id="+userBankRelId;
		}else{
			hql = "update UserBankRel set state='"+Constants.STATE_FLAG_N+"' where id="+userBankRelId;
		}
			
		userBankRelDao.executeDML(hql);
		log.info("修改用户快捷支付银行状态信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改用户快捷支付银行状态信息成功");
		return retInfo;
	}
	
	public RetInfo editUserInfo(UserInfo userInfo, UserBankRel userBankRel) throws Exception{
		RetInfo retInfo = new RetInfo();
		String hql = "from UserInfo where merId=? and userCode=?";
		List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{userInfo.getMerId(), userInfo.getUserCode()});
		if(list!=null&&list.size()>0){
			if(list.get(0).getId() != userInfo.getId()){
				log.info("用户手机号码已注册");
				retInfo.setRetCode(Constants.RET_CODE_REGIST_USER_EXISTS);
				retInfo.setRetMsg("用户手机号码已注册");
				return retInfo;
			}
		}
		UserInfo tmp = userInfoDao.find(userInfo.getId());
		tmp.setUserName(userInfo.getUserName());
		tmp.setUserCode(userInfo.getUserCode());
		tmp.setPhoneNo(userInfo.getPhoneNo());
		tmp.setCertType(userInfo.getCertType());
		tmp.setCertId(userInfo.getCertId());
		tmp.setUserSex(userInfo.getUserSex());
		tmp.setEmail(userInfo.getEmail());
		tmp.setUserProv(userInfo.getUserProv());
		tmp.setUserCity(userInfo.getUserCity());
		tmp.setUserArea(userInfo.getUserArea());
		tmp.setUserAddr(userInfo.getUserAddr());
		tmp.setUserZip(userInfo.getUserZip());
		tmp.setDeptName(userInfo.getDeptName());
		tmp.setPosition(userInfo.getPosition());
		tmp.setUserState(userInfo.getUserState());
		tmp.setLastTime(DateOper.getCurTimestamp());
		userInfoDao.update(tmp);
		
		Map<Integer, BankInfo> bankMap = (Map<Integer, BankInfo>)DataCacheManager.get(Constants.CACHE_KEY_BANKINFO);
		if(userBankRel.getId()!= null){
			UserBankRel ubr = userBankRelDao.find(userBankRel.getId());
			ubr.setBankCardNo(userBankRel.getBankCardNo());
			ubr.setBankName(bankMap.get(Integer.valueOf(userBankRel.getBankId())).getBankName());
			ubr.setBankId(userBankRel.getBankId());
			userBankRelDao.update(ubr);
		}else{
			if(userBankRel.getBankId()!=null && userBankRel.getBankCardNo()!=null){
				UserBankRel ubr = new UserBankRel();
				ubr.setMerId(tmp.getMerId());
				ubr.setUserId(tmp.getId());
				ubr.setBankId(userBankRel.getBankId());
				ubr.setBankName(bankMap.get(Integer.valueOf(userBankRel.getBankId())).getBankName());
				ubr.setBankCardNo(userBankRel.getBankCardNo());
				ubr.setDefState(Constants.YES_OR_NO_FLAG_N);
				ubr.setState(Constants.STATE_FLAG_N);
				ubr.setChkFlag(Constants.USER_BANK_CHK_FLAG_C);
				ubr.setChkStat(Constants.USER_BANK_CHK_STATE_S);
				ubr.setCreateTime(DateOper.getCurTimestamp());
				userBankRelDao.save(ubr);
			}
		}
		log.info("修改用户信息成功，userCode="+userInfo.getUserCode());
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改用户信息成功");
		return retInfo;
	}
	
	public boolean checkUserBankCardExist(Integer userId, String bankId, String openBankCard){
		String hql = "from UserBankRel where userId=? and bankId=? and bankCardNo=?";
		String cardNo = SecretUtil.getEncryptedString(openBankCard);
		List list = userBankRelDao.findTbyHql(hql, new Object[]{userId, Integer.valueOf(bankId), cardNo});
		if(list!=null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public RetInfo editUserInfoState(Integer merId, Integer userId, String state){
		RetInfo retInfo = new RetInfo();
		String hql = "set userState=? where merId=? and id=? ";
		userInfoDao.executeDML(hql, new Object[]{state, merId, userId});
		log.info("更新用户状态成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("更新用户状态成功");
		return retInfo;
	}
	
	public RetInfo addUserInfo(UserInfo userInfo){
		RetInfo retInfo = new RetInfo();
		try {
			String hql = "from UserInfo where merId=? and phoneNo=?";
			List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{userInfo.getMerId(), userInfo.getPhoneNo()});
			if(list!=null&&list.size()>0){
				log.info("用户手机号码已注册");
				retInfo.setRetCode(Constants.RET_CODE_REGIST_USER_EXISTS);
				retInfo.setRetMsg("用户手机号码已注册");
				return retInfo;
			}
			UserInfo user = new UserInfo();
			PropertyUtils.copyProperties(user, userInfo);
//			user.setOpenBankName("");
//			user.setOpenBankCard("");
			userInfoDao.save(user);
			
			/*Map<Integer, BankInfo> bankMap = (Map<Integer, BankInfo>)DataCacheManager.get(Constants.CACHE_KEY_BANKINFO);
			BankInfo bankInfo = new BankInfo();
			UserBankRel userBankRel = new UserBankRel();
			userBankRel.setMerId(userInfo.getMerId());
			userBankRel.setUserId(user.getId());
			userBankRel.setBankId(Integer.valueOf(userInfo.getOpenBankName()));
			userBankRel.setBankName(bankMap.get(Integer.valueOf(userInfo.getOpenBankName())).getBankName());
			userBankRel.setBankCardNo(userInfo.getOpenBankCard());
			userBankRel.setDefState(Constants.YES_OR_NO_FLAG_Y);
			userBankRel.setState(Constants.STATE_FLAG_N);
			userBankRel.setChkFlag(Constants.USER_BANK_CHK_FLAG_C);
			userBankRel.setChkStat(Constants.USER_BANK_CHK_STATE_S);
			userBankRel.setCreateTime(DateOper.getCurTimestamp());
			userBankRelDao.save(userBankRel);*/
			
//			UserLimit userLimit = new UserLimit();
//			userLimit.setMerId(user.getMerId());
//			userLimit.setUserId(user.getId());
//			userLimit.setDayLimitAmt(0d);
//			userLimit.setDayLimitCnt(0);
//			userLimit.setMonthLimitAmt(0d);
//			userLimit.setMonthLimitCnt(0);
//			userLimit.setStatFlag(Constants.STATE_FLAG_N);
//			userLimitDao.save(userLimit);
			
			log.info("保存用户信息成功，userCode="+userInfo.getUserCode());
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("保存用户信息成功");
		} catch (Exception e) {
			log.error("保存用户信息异常，userCode="+userInfo.getUserCode(),e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("保存用户信息异常");
		}
		return retInfo;
	}
	
	public RetInfo qryUserInfoById( String userId){
		RetInfo retInfo = new RetInfo();
		UserInfo userInfo = userInfoDao.find(Integer.valueOf(userId));
		
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询用户明细信息成功");
			retInfo.setObject(userInfo);
		     return retInfo;
	}
	
	public RetInfo qryUserInfo(Integer merId, String provId,String cityId, String areaId, String phoneNo, String state, String qryType, Pager pager){
		RetInfo retInfo = new RetInfo();
		String hql = "and merId="+merId+" ";
		if(!StringUtils.isEmpty(provId)){
			hql += "and userProv='"+provId+"' ";
		}
		if(!StringUtils.isEmpty(cityId)){
			hql += "and userCity='"+cityId+"' ";
		}
		if(!StringUtils.isEmpty(areaId)){
			hql += "and userArea='"+areaId+"' ";
		}
		if(!StringUtils.isEmpty(phoneNo)){
			hql += "and phoneNo='"+phoneNo+"' ";
		}
		if(!StringUtils.isEmpty(state)){
			hql += "and userState='"+state+"' ";
		}
//		if(!StringUtils.isEmpty(acctBal)){
//			if(qryType.equals("eq")){
//				hql += "and acctBal="+acctBal+" ";
//			}else if(qryType.equals("gt")){
//				hql += "and acctBal>"+acctBal+" ";
//			}else{
//				hql += "and acctBal<"+acctBal+" ";
//			}
//		}
		hql += "order by openTime desc, userState, userType";
		PageData data = userInfoDao.getScrollData(pager.getStartRow(), pager.getPageSize(), hql, null);
		pager.setTotalRows(Integer.parseInt(String.valueOf(data.getTotalRecords())));
		log.info("用户信息查询成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("用户信息查询成功");
		retInfo.setList(data.getResultlist());
		return retInfo;
	}
	
	/**
	 * 检查用户信息是否正确
	 */
	public boolean checkUserInfo(Integer userId, String userName, String certId){
		try {
			String hql = "from UserInfo where id=? and userName=? and certId=? and userType='P' and userState in ('N','A')";
			List<MerInfo> list = merInfoDao.findTbyHql(hql, 
					new Object[]{userId, userName, certId});
			if(list==null||list.size()==0){
				log.info("用户信息查询失败");
				return false;
			}else{
				log.info("用户信息查询成功");
				return true;
			}
			
		} catch (Exception e) {
			log.error("用户信息查询异常",e);
			return false;
		}
	}
	
	/**
	 * 检查商户信息是否正确
	 */
	public boolean checkMerInfo(Integer merId, String merCode, 
			String merName, String merBankName, String merBankCardNo, 
			String merCorpCode){
		try {
			String hql = "from MerInfo where id=? and merCode=? and corpCode=? and merName=? and merBankName=? and merBankAcct=?";
			List<MerInfo> list = merInfoDao.findTbyHql(hql, 
					new Object[]{merId, merCode, merCorpCode, merName, merBankName, merBankCardNo});
			if(list==null||list.size()==0){
				log.info("商户信息查询失败");
				return false;
			}else{
				log.info("商户信息查询成功");
				return true;
			}
			
		} catch (Exception e) {
			log.error("商户信息查询异常",e);
			return false;
		}
	}
	
	public RetInfo updateUserCertInfo(Integer userId, String userName, String certId, String certPicUrl){
		RetInfo retInfo = new RetInfo();
		try {
			UserInfo userInfo = userInfoDao.find(userId);
			if(userInfo==null){
				log.info("查询用户信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询用户信息失败");
				return retInfo;
			}else{
				userInfo.setUserName(userName);
				userInfo.setCertType(Constants.USER_CERT_TYPE_01);
				userInfo.setCertId(certId);
				userInfo.setCertPicPath(certPicUrl);
				userInfo.setUserState(Constants.USER_STATE_A);
				userInfoDao.update(userInfo);
				log.info("更新用户身份证信息成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("更新用户身份证信息成功");
				return retInfo;
			}
		} catch (Exception e) {
			log.error("更新用户身份证信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("更新用户身份证信息异常");
		}
		return retInfo;
	}
	
	public RetInfo updateUserName(Integer userId, String userName){
		RetInfo retInfo = new RetInfo();
		try {
			UserInfo userInfo = userInfoDao.find(userId);
			if(userInfo==null){
				log.info("查询用户信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询用户信息失败");
				return retInfo;
			}else{
				userInfo.setUserName(userName);
				userInfoDao.update(userInfo);
				log.info("修改用户姓名成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("修改用户姓名成功");
				return retInfo;
			}
		} catch (Exception e) {
			log.error("修改用户名称异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("修改用户名称异常");
		}
		return retInfo;
	}
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @param bankId
	 * @return
	 */
	public RetInfo updateUserInfo(UserInfo userInfo, String bankId){
		RetInfo retInfo = new RetInfo();
		try {
			if(!StringUtils.isEmpty(bankId)){
				String hql="from UserBankRel where id=? and userId=? ";
				List<UserInfo> list = userInfoDao.findTbyHql(
						hql, new Object[]{Integer.valueOf(bankId), userInfo.getId()});
				if(list == null || list.size()==0){
					log.info("查询用户快捷支付银行信息失败");
					retInfo.setRetCode(Constants.RET_CODE_ERROR);
					retInfo.setRetMsg("查询用户快捷支付银行信息失败");
					return retInfo;
				}else{
					hql = "set defState=? where userId=? ";
					userBankRelDao.executeDML(hql, new Object[]{Constants.BANK_DEF_STATE_N, userInfo.getId()});
					
					hql = "set defState=? where id=? and userId=? ";
					userBankRelDao.executeDML(hql, new Object[]{Constants.BANK_DEF_STATE_Y, Integer.valueOf(bankId), userInfo.getId()});
					
					userInfoDao.update(userInfo);
					log.info("修改用户信息成功");
					retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
					retInfo.setRetMsg("修改用户信息成功");
				}
			}else{
				userInfoDao.update(userInfo);
				log.info("修改用户信息成功");
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("修改用户信息成功");
			}
		} catch (Exception e) {
			log.error("修改用户信息处理异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("修改用户信息处理异常");
		}
		return retInfo;
	}
	
	/**
	 * 保存用户信息
	 * @param merCode
	 * @param UserInfo
	 * @return
	 */
	public RetInfo saveUserInfo(String merCode, UserInfo user){
		RetInfo retInfo = new RetInfo();
		try {
			String hql="select u from UserInfo u, MerInfo m where u.merId=m.id and u.userCode=? and u.userType=? and m.merCode=? ";
			List<UserInfo> list = userInfoDao.findTbyHql(
					hql, new Object[]{user.getUserCode(), user.getUserType(), merCode});
			if(list.size()>0){
				log.info("用户号码已存在");
				retInfo.setRetCode(Constants.RET_CODE_REGIST_USER_EXISTS);
				retInfo.setRetMsg("用户号码已存在");
				return retInfo;
			}else{
				hql = "from MerInfo m where m.merCode = ?";
				List<MerInfo> merList = merInfoDao.findTbyHql(hql, new Object[]{merCode});
				if(merList.size()>0){
					MerInfo merInfo = merList.get(0);
					user.setMerId(merInfo.getId());
					
					userInfoDao.save(user);
					
					log.info("保存用户信息成功");
					retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
					retInfo.setRetMsg("保存用户信息成功");
				}else{
					log.info("商户信息查询失败");
					retInfo.setRetCode(Constants.RET_CODE_ERROR);
					retInfo.setRetMsg("商户信息查询失败");
				}
			}
		} catch (Exception e) {
			log.error("保存用户信息处理异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("保存用户信息处理异常");
		}
		return retInfo;
	}
	
	/**
	 * 检查商户操作员信息
	 * @param name
	 * @param password
	 * @return
	 */
	public RetInfo checkMerOperInfo(String merCode, String operCode, String password, String ip){
		RetInfo retInfo = new RetInfo();
		try {
			String hql ="select o from MerInfo m, MerOperInfo o where m.id=o.merId and m.merCode=? and o.operCode=?  and   m.merState = 'N'";
			List<MerOperInfo> list = merOperInfoDao.findTbyHql(hql, new Object[]{merCode, operCode});
			if(list.size()>0){
				MerOperInfo merOperInfo = list.get(0);
				if(merOperInfo.getOperState().equals(Constants.USER_STATE_N)){
					if(merOperInfo.getOperPwd().equals(password)){
						merOperInfo.setLogTime(DateOper.getCurTimestamp());
						merOperInfo.setPwdErrCnt(0);
						merOperInfo.setLogIp(ip);
						merOperInfoDao.update(merOperInfo);
						retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
						retInfo.setRetMsg("检查商户操作员信息成功");
						hql ="select m from MerInfo m, MerOperInfo o where m.id=o.merId and m.merCode=? and o.operCode=?";
						List<MerInfo> merlist = merInfoDao.findTbyHql(hql, new Object[]{merCode, operCode});

						List<AuthInfo> authList = new ArrayList<AuthInfo>();
						
						if(merCode.equals("880001"))
						{
							hql = "from AuthInfo where state=? order by authLevel, porder";
							authList = authInfoDao.findTbyHql(hql, new Object[]{Constants.STATE_FLAG_N });
						}
						else
						{
							hql = "select a from AuthInfo a, GrpAuthInfo g where a.id=g.authId and g.grpId=?  and g.state=?  and a.state=? order by a.authLevel, a.porder";
							authList = authInfoDao.findTbyHql(hql, new Object[]{
									merOperInfo.getGrpId(), Constants.STATE_FLAG_N, Constants.STATE_FLAG_N
							});
						}
						Map map = new HashMap();
						map.put("merInfo", merlist.get(0));
						map.put("operInfo", merOperInfo);
						map.put("authInfo", authList);
						retInfo.setObject(map);
					}else{
						int pwderrcnt = merOperInfo.getPwdErrCnt().intValue()+1;
						if(pwderrcnt>=5){
							merOperInfo.setOperState(Constants.OPER_STATE_L);
						}
						merOperInfo.setPwdErrCnt(pwderrcnt);
						merOperInfo.setLogErrTime(DateOper.getCurTimestamp());
						merOperInfo.setLogIp(ip);
						merOperInfoDao.update(merOperInfo);
						retInfo.setRetCode(Constants.RET_CODE_LOGIN_PWD_ERROR);
						retInfo.setRetMsg("密码错误");
						retInfo.setObject(merOperInfo);
					}
				}else{
					log.info("操作员状态异常, state="+merOperInfo.getOperState());
					retInfo.setRetCode(Constants.RET_CODE_LOGIN_STATE_ERROR);
					retInfo.setRetMsg("操作员状态异常");
					retInfo.setObject(merOperInfo);
				}
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("检查商户操作员信息失败");
			}
		} catch (Exception e) {
			log.error("检查商户操作员信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("检查商户操作员信息异常");
		}
		
		return retInfo;
	}
	
	/**
	 * 查询用户信息
	 * @param phoneNo, merId
	 * @return
	 */
	public RetInfo qryUserInfo(String phoneNo, Integer merId){
		RetInfo retInfo = new RetInfo();
		try {
			String hql="from UserInfo where merId=? and userCode=? ";
			List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{merId, phoneNo});
			if(list.size()>0){
				UserInfo userInfo = list.get(0);
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("查询用户信息成功");
				retInfo.setObject(userInfo);
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询用户信息失败");
			}
		} catch (Exception e) {
			log.error("查询用户信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询用户信息异常");
		}
		
		return retInfo;
	}
	
	/**
	 * 查询用户信息
	 * @param phoneNo
	 * @return
	 */
	public RetInfo qryUserInfo(Integer userId){
		RetInfo retInfo = new RetInfo();
		try {
			String hql="from UserInfo where id=? ";
			UserInfo userInfo = userInfoDao.find(userId);
			if(userInfo!=null){
				hql="from MerInfo where id=? ";
				MerInfo merInfo = merInfoDao.find(userInfo.getMerId());
				List tmplist = new ArrayList();
				tmplist.add(userInfo);
				tmplist.add(merInfo);
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("查询用户信息成功");
				retInfo.setList(tmplist);
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询用户信息失败");
			}
		} catch (Exception e) {
			log.error("查询用户信息异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("查询用户信息异常");
		}
		
		return retInfo;
	}
	
	public RetInfo editUserPwd(String phoneNo, String merCode, String type, String pwd){
		RetInfo retInfo = new RetInfo();
		try {
			String hql="select u from UserInfo u, MerInfo m where u.merId=m.id and u.userCode=? and u.userType=? and m.merCode=? ";
			List<UserInfo> list = userInfoDao.findTbyHql(hql, new Object[]{phoneNo, type, merCode});
			if(list.size()>0){
				UserInfo userInfo = list.get(0);
				userInfo.setLoginPwd(pwd);
				userInfo.setLastTime(DateOper.getCurTimestamp());
				
				userInfoDao.update(userInfo);
				retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
				retInfo.setRetMsg("修改密码成功");
			}else{
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询用户信息失败");
			}
		} catch (Exception e) {
			log.error("修改密码异常",e);
			retInfo.setRetCode(Constants.RET_CODE_EXCEP);
			retInfo.setRetMsg("修改密码异常");
		}
		
		return retInfo;
	}
	
}
