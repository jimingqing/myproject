package com.yrtech.wx.capp.portal.action;

import java.io.File;
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
import com.yrtech.wx.capp.framework.core.security.MD5;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.CheckUtil;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.FileUtil;
import com.yrtech.wx.capp.framework.core.util.RandomStringUtils;
import com.yrtech.wx.capp.framework.core.util.StringUtil;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.MerOperInfo;
import com.yrtech.wx.capp.portal.model.ProvCity;
import com.yrtech.wx.capp.portal.service.MerInfoSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.MerInfoVo;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;
import com.yrtech.wx.capp.portal.vo.UserInfoVo;

public class MerInfoAction extends BaseAction
{

	private Log log = LogFactory.getLog(this.getClass());

	private String merCode;
	private String merName;
	private String provId;
	private String cityId;
	private String merAddr;
	private String regCode;
	private String corpCode;
	private String merZip;

	private String unMerId;
	private File cerFile;
	private String cerFileFileName;
	private File pfxFile;
	private String pfxFileFileName;
	private String pfxPass;
	private String unUserName;
	private String unUserPass;
	private String merBankName;
	private String merBankAcct;
	private String isRealWh;
	private String dayTransCnt;
	private String oneLimitAmt;
	private String totalLimitAmt;
	private String isSendSms;

	private String merContName;
	private String merContTel;
	private String merContMp;
	private String merContEmail;
	private String merContRole;
	private String merState;
	private String merId;

	private String operCode;
	private String operPwd;
	private String operName;
	private String operTel;
	private String operMp;
	private String operEmail;
	private String operState;
	private String isAdmin;
	private String operId;
	private String grpId;
	private String type;

	@Resource
	private MerInfoSrv merInfoSrv;

	public String editOperPwd()
	{
		if(StringUtils.isEmpty(operId))
		{
			log.info("操作员id错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员id错误");
			return SUCCESS;
		}

		if(StringUtils.isEmpty(type))
		{
			log.info("类型错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "类型错误");
			return SUCCESS;
		}

		if(type.equals("edit"))
		{
			if(StringUtils.isEmpty(operPwd))
			{
				log.info("登录密码错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "登录密码错误");
				return SUCCESS;
			}
		}

		try
		{
			if(type.equals("reset"))
			{
				operPwd = RandomStringUtils.random(6, true, true);
			}

			MerOperInfoVo vo = (MerOperInfoVo) session().getAttribute(
					Constants.USER_INFOVO);
			if(vo == null)
			{
				resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
				resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
				return SUCCESS;
			}

			if(!(vo.getMerCode() + "").equals("880001"))
			{
				System.out.println(merId);
				System.out.println(vo.getMerId());
				if(!merId.equals(vo.getMerId() + ""))
				{
					resultMap
							.put(Constants.JSON_RETCODE,
									Constants.NO_PRO);
					resultMap
							.put(Constants.JSON_RETMSG,
									"你没有修改其他操作员密码的权限");
					return SUCCESS;
				}
			}

			RetInfo retInfo = merInfoSrv.editOperPwd(operId, operPwd);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("重置操作员密码成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "重置操作员密码成功");
				resultMap.put(Constants.JSON_CONTENT, operPwd);
			}else
			{
				log.info("重置操作员密码失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "重置操作员密码失败");
			}
		}
		catch (Exception e)
		{
			log.error("重置操作员密码异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "重置操作员密码异常");
		}
		return SUCCESS;
	}

	public String editMerOperInfo()
	{
		MerOperInfoVo vo = (MerOperInfoVo) session().getAttribute(
				Constants.USER_INFOVO);
		if(vo == null)
		{
			resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			return SUCCESS;
		}

		if(!(vo.getMerCode() + "").equals("880001"))
		{
			System.out.println(merId);
			System.out.println(vo.getMerId());
			if(!merId.equals(vo.getMerId()+""))
			{
				resultMap
						.put(Constants.JSON_RETCODE,
								Constants.NO_PRO);
				resultMap
						.put(Constants.JSON_RETMSG,
								"你没有修改其他商户操作员的权限");
				return SUCCESS;
			}
		}
		
		
		if(StringUtils.isEmpty(operState)
				|| (!Constants.STATE_FLAG_N.equals(operState) && !Constants.STATE_FLAG_C
						.equals(operState)))
		{
			log.info("操作员状态错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员状态错误");
			return SUCCESS;
		}

		try
		{
			MerOperInfo merOperInfo = new MerOperInfo();
			merOperInfo.setId(Integer.valueOf(operId));
			merOperInfo.setMerId(getMerOperInfoVo().getMerId());
			if(!StringUtils.isEmpty(operName))
			{
				merOperInfo.setOperName(operName);
			}
			if(!StringUtils.isEmpty(operTel))
			{
				merOperInfo.setOperTel(operTel);
			}
			if(!StringUtils.isEmpty(operMp))
			{
				merOperInfo.setOperMp(operMp);
			}
			if(!StringUtils.isEmpty(operEmail))
			{
				merOperInfo.setOperEmail(operEmail);
			}
			merOperInfo.setIsAdmin(isAdmin);
			merOperInfo.setGrpId(grpId);
			merOperInfo.setOperState(operState);
			merOperInfo.setPwdErrCnt(0);
			merOperInfo.setCreateTime(DateOper.getCurTimestamp());

			RetInfo retInfo = merInfoSrv.editMerOperInfo(merOperInfo);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("添加商户操作员信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息成功");

			}else
			{
				log.info("添加商户操作员信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("添加商户操作员信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息异常");
		}
		return SUCCESS;

	}

	public String qryMerOperInfoDetail()
	{
		// MerOperInfoVo vo
		// =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		// if(vo==null)
		// {
		// resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
		// resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
		// return SUCCESS;
		// }
		//		 
		// if(!(vo.getMerCode()+"").equals("880001"))
		// {
		// System.out.println(merId);
		// System.out.println(vo.getMerId());
		// if(!merId.equals(vo.getMerId()+""))
		// {
		// resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
		// resultMap.put(Constants.JSON_RETMSG, "你没有增加其他商户用户的权限");
		// return SUCCESS;
		// }
		// }

		if(StringUtils.isEmpty(operId))
		{
			log.info("操作员id错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员id错误");
			return SUCCESS;
		}

		try
		{
			RetInfo retInfo = merInfoSrv.qryMerOperInfo(operId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("查询商户操作员信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getObject());
			}else
			{
				log.info("查询商户操作员信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("查询商户操作员信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息异常");
		}
		return SUCCESS;
	}

	public String editMerInfo()
	{
		if(StringUtils.isEmpty(merId) || !CheckUtil.isNumeric(merId))
		{
			log.info("商户ID错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户ID错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(merCode) || !CheckUtil.isNumeric(merCode)
				|| merCode.length() != 6)
		{
			log.info("商户编号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户编号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(merName))
		{
			log.info("商户名称错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户名称错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(provId))
		{
			log.info("所在省份错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在省份错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(cityId))
		{
			log.info("所在城市错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在城市错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(merAddr))
		{
			log.info("商户地址错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户地址错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(regCode))
		{
			log.info("工商登记号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "工商登记号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(corpCode))
		{
			log.info("组织机构代码错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "组织机构代码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(merState))
		{
			log.info("商户状态错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户状态错误");
			return SUCCESS;
		}
		// if(StringUtils.isEmpty(unMerId)){
		// log.info("银联商户号错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "银联商户号错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(merBankName)){
		// log.info("开户银行错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "开户银行错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(merBankAcct)){
		// log.info("开户银行账号错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "开户银行账号错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(isRealWh)){
		// log.info("是否实时代扣错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "是否实时代扣错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(dayTransCnt)){
		// log.info("账户日交易次数错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "账户日交易次数错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(oneLimitAmt)){
		// log.info("单笔限制金额错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "单笔限制金额错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(totalLimitAmt)){
		// log.info("总计限制金额错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "总计限制金额错误");
		// return SUCCESS;
		// }
		// if(StringUtils.isEmpty(isSendSms)){
		// log.info("是否发送短信错误");
		// resultMap.put(Constants.JSON_RETCODE,
		// Constants.RET_CODE_PARAM_ERROR);
		// resultMap.put(Constants.JSON_RETMSG, "是否发送短信错误");
		// return SUCCESS;
		// }

		try
		{
			MerInfo merInfo = new MerInfo();
			merInfo.setId(Integer.valueOf(merId));
			merInfo.setMerCode(merCode);
			merInfo.setMerName(merName);
			merInfo.setMerProv(provId);
			merInfo.setMerArea(cityId);
			merInfo.setMerAddr(merAddr);
			merInfo.setRegCode(regCode);
			merInfo.setCorpCode(corpCode);
			merInfo.setMerZip(merZip);
			merInfo.setUnMerId(unMerId);
			// merInfo.setMerBankName(merBankName);
			// merInfo.setMerBankAcct(merBankAcct);
			merInfo.setIsRealWh(isRealWh);
			merInfo.setIsSendSms(isSendSms);
			// merInfo.setDayTransCnt(Integer.valueOf(dayTransCnt));
			// merInfo.setOneLimitAmt(Double.valueOf(oneLimitAmt));
			// merInfo.setTotalLimitAmt(Double.valueOf(totalLimitAmt));
			merInfo.setMerContName(merContName);
			merInfo.setMerContTel(merContTel);
			merInfo.setMerContMp(merContMp);
			merInfo.setMerContEmail(merContEmail);
			merInfo.setMerContRole(merContRole);
			merInfo.setMerState(merState);

			RetInfo retInfo = merInfoSrv.editMerInfo(merInfo);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("修改商户信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改商户信息成功");

			}else
			{
				log.info("修改商户信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改商户信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("修改商户信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改商户信息异常");
		}
		return SUCCESS;

	}

	public String qryMerOperInfo()
	{
		try
		{

			MerOperInfoVo vo1 = (MerOperInfoVo) session().getAttribute(
					Constants.USER_INFOVO);
			if(vo1 == null)
			{
				resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
				resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
				return SUCCESS;
			}

			if(!(vo1.getMerCode() + "").equals("880001"))
			{
				System.out.println(merId);
				System.out.println(vo1.getMerId());
				if(merId==null || merId.length()==0)
				{
					resultMap
					.put(Constants.JSON_RETCODE,
							Constants.NO_PRO);
			resultMap
					.put(Constants.JSON_RETMSG,
							"你没有查询其他商户操作员的权限");
			return SUCCESS;
				}
				
				if(!merId.equals(vo1.getMerId() + ""))
				{
					resultMap
							.put(Constants.JSON_RETCODE,
									Constants.NO_PRO);
					resultMap
							.put(Constants.JSON_RETMSG,
									"你没有查询其他商户操作员的权限");
					return SUCCESS;
				}
			}

			if(merId == null || merId.length() == 0)
			{
				resultMap.put(Constants.JSON_RETCODE, Constants.BLANK);
				resultMap.put(Constants.JSON_RETMSG, "商户id不能为空");
				return SUCCESS;
			}

			RetInfo retInfo = merInfoSrv.qryMerOperInfo(new Integer(merId),
					operCode, operState);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				RetInfo retInfoMer = merInfoSrv.qryMerInfo("", "", "");
				Map<Integer, String> merMap = new HashMap<Integer, String>();
				for (MerInfo o : (List<MerInfo>) retInfoMer.getList())
				{
					merMap.put(o.getId(), o.getMerName());
				}

				List<MerOperInfoVo> list = new ArrayList<MerOperInfoVo>();
				MerOperInfoVo vo = new MerOperInfoVo();
				for (MerOperInfo o : (List<MerOperInfo>) retInfo.getList())
				{
					vo = new MerOperInfoVo();
					PropertyUtils.copyProperties(vo, o);
					vo.setMerName(merMap.get(o.getMerId()));
					list.add(vo);
				}

				log.info("查询商户操作员信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息成功");
				resultMap.put(Constants.JSON_CONTENT, list);
			}else
			{
				log.info("查询商户操作员信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("查询商户操作员信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商户操作员信息异常");
		}
		return SUCCESS;
	}

	public String addMerOperInfo()
	{
		// // if()
		//		
		// // session().setAttribute(Constants.USER_INFOVO, vo);
		MerOperInfoVo vo = (MerOperInfoVo) session().getAttribute(
				Constants.USER_INFOVO);
		if(vo == null)
		{
			resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			return SUCCESS;
		}

		if(!(vo.getMerCode() + "").equals("880001"))
		{
			System.out.println(grpId);
			if(!(vo.getMerId()+"").equals(merId))
			{
				log.info("你没有增加其他商户的操作员权限");
				resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
				resultMap.put(Constants.JSON_RETMSG, "没有权限");
				return SUCCESS;
			}
			if(grpId == null || grpId.length() == 0)
			{
				log.info("你没有增加其他权限的权限");
				resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
				resultMap.put(Constants.JSON_RETMSG, "没有权限");
				return SUCCESS;
			}
			if(!("00").equals(grpId))
			{
				log.info("你没有增加其他权限的权限");
				resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
				resultMap.put(Constants.JSON_RETMSG, "没有权限");
				return SUCCESS;
			}
		}

		if(StringUtils.isEmpty(operCode))
		{
			log.info("操作员号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(operPwd))
		{
			log.info("登录密码错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "登录密码错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(operState)
				|| (!Constants.STATE_FLAG_N.equals(operState) && !Constants.STATE_FLAG_C
						.equals(operState)))
		{
			log.info("操作员状态错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员状态错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(grpId))
		{
			log.info("权限组错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限组错误");
			return SUCCESS;
		}
		// if()
		try
		{
			// MerOperInfoVo vo
			// =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
			// if(vo==null)
			// {
			// resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			// resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			// return SUCCESS;
			// }
			//			 
			// if(!(vo.getMerCode()+"").equals("880001"))
			// {
			//			
			// System.out.println(grpId);
			// if(!(vo.getMerId()+"").equals(merId))
			// {
			// log.info("你没有增加其他商户的操作员权限");
			// resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
			// resultMap.put(Constants.JSON_RETMSG, "没有权限");
			// return SUCCESS;
			// }
			//			 
			// if(!grpId.equals("00"))
			// {
			// log.info("你没有增加其他权限的权限");
			// resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
			// resultMap.put(Constants.JSON_RETMSG, "没有权限");
			// return SUCCESS;
			// }
			// }
			MerOperInfo merOperInfo = new MerOperInfo();
			merOperInfo.setMerId(new Integer(merId));
			merOperInfo.setOperCode(operCode);
			merOperInfo.setOperPwd(new MD5(operPwd).getStrDigest());
			if(!StringUtils.isEmpty(operName))
			{
				merOperInfo.setOperName(operName);
			}
			if(!StringUtils.isEmpty(operTel))
			{
				merOperInfo.setOperTel(operTel);
			}
			if(!StringUtils.isEmpty(operMp))
			{
				merOperInfo.setOperMp(operMp);
			}
			if(!StringUtils.isEmpty(operEmail))
			{
				merOperInfo.setOperEmail(operEmail);
			}
			merOperInfo.setIsAdmin(Constants.YES_OR_NO_FLAG_N);
			merOperInfo.setOperState(operState);
			merOperInfo.setPwdErrCnt(0);
			merOperInfo.setCreateTime(DateOper.getCurTimestamp());
			merOperInfo.setGrpId(grpId);

			RetInfo retInfo = merInfoSrv.addMerOperInfo(merOperInfo);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("添加商户操作员信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息成功");

			}else
			{
				log.info("添加商户操作员信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息失败");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error("添加商户操作员信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加商户操作员信息异常");
		}
		return SUCCESS;

	}

	public String checkOperCode()
	{
		if(StringUtils.isEmpty(operCode))
		{
			log.info("操作员号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员号错误");
			return SUCCESS;
		}
		try
		{
			if(merInfoSrv.checkOperCode(operCode, getMerOperInfoVo()
					.getMerId()))
			{
				log.info("操作员号已存在，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_MER_CODE_REPEAT);
				resultMap.put(Constants.JSON_RETMSG, "操作员号已存在");
			}else
			{
				log.info("操作员号可用，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "操作员号可用");
			}

		}
		catch (Exception e)
		{
			log.error("检查操作员号异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "检查操作员号异常");
		}
		return SUCCESS;
	}

	public String qryMerInfoDetail()
	{
		if(StringUtils.isEmpty(merId) || !CheckUtil.isNumeric(merId))
		{
			log.info("商户号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户号错误");
			return SUCCESS;
		}
		try
		{
			RetInfo retInfo = merInfoSrv.qryMerInfoById(merId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				MerInfo merInfo = (MerInfo) retInfo.getObject();
				Map<String, ProvCity> map = (Map<String, ProvCity>) DataCacheManager
						.get(Constants.CACHE_KEY_PROVCITY);
				if(StringUtils.isNotEmpty(merInfo.getMerProv()))
				{
					merInfo.setMerProv(map.get(merInfo.getMerProv())
							.getName());
				}
				if(StringUtils.isNotEmpty(merInfo.getMerArea()))
				{
					merInfo.setMerArea(map.get(merInfo.getMerArea())
							.getName());
				}

				log.info("查询商户明细信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商户明细信息成功");
				resultMap.put(Constants.JSON_CONTENT, merInfo);
			}else
			{
				log.info("查询商户信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商户明细信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("查询商户明细信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商户明细信息异常");
		}
		return SUCCESS;
	}

	public String qryMerInfo()
	{
		try
		{
			RetInfo retInfo = merInfoSrv.qryMerInfo(merCode, merName,
					merState);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				List<MerInfoVo> list = new ArrayList<MerInfoVo>();
				MerInfoVo vo = new MerInfoVo();
				for (MerInfo o : (List<MerInfo>) retInfo.getList())
				{
					vo = new MerInfoVo();
					PropertyUtils.copyProperties(vo, o);
					list.add(vo);
				}

				log.info("查询商户信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商户信息成功");
				resultMap.put(Constants.JSON_CONTENT, list);
			}else
			{
				log.info("查询商户信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商户信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("查询商户信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商户信息异常");
		}
		return SUCCESS;
	}

	public String checkMerInfoExist()
	{
		if(StringUtils.isEmpty(merCode) || !CheckUtil.isNumeric(merCode)
				|| merCode.length() != 6)
		{
			log.info("商户号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户号错误");
			return SUCCESS;
		}

		try
		{
			if(merInfoSrv.checkMerCode(merCode))
			{
				log.info("商户号已存在，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_MER_CODE_REPEAT);
				resultMap.put(Constants.JSON_RETMSG, "商户号已存在");
			}else
			{
				log.info("商户号可用，merCode=" + getMerOperInfoVo().getMerCode()
						+ ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "商户号可用");
			}

		}
		catch (Exception e)
		{
			log.error("检查商户号异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "检查商户号异常");
		}
		return SUCCESS;
	}

	public String addMerInfo()
	{

		MerOperInfoVo vo = (MerOperInfoVo) session().getAttribute(
				Constants.USER_INFOVO);
		if(vo == null)
		{
			resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			return SUCCESS;
		}

		if(!(vo.getMerCode()+"").equals("880001"))
		{
			log.info("你没有增加商户的权限");
			resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
			resultMap.put(Constants.JSON_RETMSG, "没有权限");
			return SUCCESS;
		}
		
		if(StringUtils.isEmpty(merCode) || !CheckUtil.isNumeric(merCode)
				|| merCode.length() != 6)
		{
			log.info("商户号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户号错误");
			return SUCCESS;
		}
		
		if(StringUtils.isEmpty(merName))
		{
			log.info("商户名称错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户名称错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(provId))
		{
			log.info("所在省份错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在省份错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(cityId))
		{
			log.info("所在城市错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "所在城市错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(merAddr))
		{
			log.info("商户地址错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商户地址错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(regCode))
		{
			log.info("工商登记号错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "工商登记号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(corpCode))
		{
			log.info("组织机构代码错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "组织机构代码错误");
			return SUCCESS;
		}

		/*
		 * if(StringUtils.isEmpty(unMerId)){ log.info("银联商户号错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "银联商户号错误"); return SUCCESS; }
		 */
		/*
		 * if(StringUtils.isEmpty(cerFileFileName)){ log.info("证书文件错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "证书文件错误"); return SUCCESS; }
		 */
		/*
		 * if(StringUtils.isEmpty(pfxFileFileName)){ log.info("密钥文件错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "密钥文件错误"); return SUCCESS; }
		 */
		/*
		 * if(StringUtils.isEmpty(pfxPass)){ log.info("密钥文件密码错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "密钥文件密码错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(unUserName)){ log.info("交易账号错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "交易账号错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(unUserPass)){ log.info("交易密码错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "交易密码错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(merBankName)){ log.info("开户银行错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "开户银行错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(merBankAcct)){ log.info("开户银行账号错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "开户银行账号错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(isRealWh)){ log.info("是否实时代扣错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "是否实时代扣错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(dayTransCnt)){ log.info("账户日交易次数错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "账户日交易次数错误"); return SUCCESS;
		 * } if(StringUtils.isEmpty(oneLimitAmt)){ log.info("单笔限制金额错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "单笔限制金额错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(totalLimitAmt)){ log.info("总计限制金额错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "总计限制金额错误"); return SUCCESS; }
		 * if(StringUtils.isEmpty(isSendSms)){ log.info("是否发送短信错误");
		 * resultMap.put(Constants.JSON_RETCODE,
		 * Constants.RET_CODE_PARAM_ERROR);
		 * resultMap.put(Constants.JSON_RETMSG, "是否发送短信错误"); return SUCCESS; }
		 */

		try
		{
			MerInfo merInfo = new MerInfo();
			merInfo.setMerCode(merCode);
			merInfo.setMerName(merName);
			merInfo.setMerProv(provId);
			merInfo.setMerArea(cityId);
			merInfo.setMerAddr(merAddr);
			merInfo.setRegCode(regCode);
			merInfo.setCorpCode(corpCode);
			merInfo.setMerZip(merZip);
			merInfo.setUnMerId(unMerId);
			merInfo.setMerState("N");
			merInfo.setMerBankName(merBankName);
			merInfo.setMerBankAcct(merBankAcct);
			merInfo.setIsRealWh(isRealWh);
			merInfo.setIsSendSms(isSendSms);
			merInfo.setDayTransCnt(Integer.valueOf(dayTransCnt));
			merInfo.setOneLimitAmt(Double.valueOf(oneLimitAmt));
			merInfo.setTotalLimitAmt(Double.valueOf(totalLimitAmt));
			merInfo.setMerContName(merContName);
			merInfo.setMerContTel(merContTel);
			merInfo.setMerContMp(merContMp);
			merInfo.setMerContEmail(merContEmail);
			merInfo.setMerContRole(merContRole);
//			merInfo.setMerState(Constants.MER_STATE_A);
			merInfo.setOpenTime(DateOper.getCurTimestamp());
			// merInfo.setCerFile(cerFileFileName);
			// merInfo.setPfxFile(pfxFileFileName);
			merInfo.setPfxPass(SecretUtil.getEncryptedString(pfxPass));
			merInfo.setUnUserName(SecretUtil.getEncryptedString(unUserName));
			merInfo.setUnUserPass(SecretUtil.getEncryptedString(unUserPass));

			RetInfo retInfo = merInfoSrv.addMerInfo(merInfo, cerFile,
					cerFileFileName, pfxFile, pfxFileFileName);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("添加商户信息成功，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "添加商户信息成功");

			}else
			{
				log.info("添加商户信息失败，merCode="
						+ getMerOperInfoVo().getMerCode() + ", operCode="
						+ getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "添加商户信息失败");
			}

		}
		catch (Exception e)
		{
			log.error("添加商户信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加商户信息异常");
		}
		return SUCCESS;

	}

	public String getMerCode()
	{
		return merCode;
	}

	public void setMerCode( String merCode )
	{
		this.merCode = merCode;
	}

	public String getMerName()
	{
		return merName;
	}

	public void setMerName( String merName )
	{
		this.merName = merName;
	}

	public String getProvId()
	{
		return provId;
	}

	public void setProvId( String provId )
	{
		this.provId = provId;
	}

	public String getCityId()
	{
		return cityId;
	}

	public void setCityId( String cityId )
	{
		this.cityId = cityId;
	}

	public String getMerAddr()
	{
		return merAddr;
	}

	public void setMerAddr( String merAddr )
	{
		this.merAddr = merAddr;
	}

	public String getRegCode()
	{
		return regCode;
	}

	public void setRegCode( String regCode )
	{
		this.regCode = regCode;
	}

	public String getCorpCode()
	{
		return corpCode;
	}

	public void setCorpCode( String corpCode )
	{
		this.corpCode = corpCode;
	}

	public String getMerZip()
	{
		return merZip;
	}

	public void setMerZip( String merZip )
	{
		this.merZip = merZip;
	}

	public String getUnMerId()
	{
		return unMerId;
	}

	public void setUnMerId( String unMerId )
	{
		this.unMerId = unMerId;
	}

	public String getMerBankName()
	{
		return merBankName;
	}

	public void setMerBankName( String merBankName )
	{
		this.merBankName = merBankName;
	}

	public String getMerBankAcct()
	{
		return merBankAcct;
	}

	public void setMerBankAcct( String merBankAcct )
	{
		this.merBankAcct = merBankAcct;
	}

	public String getIsRealWh()
	{
		return isRealWh;
	}

	public void setIsRealWh( String isRealWh )
	{
		this.isRealWh = isRealWh;
	}

	public String getDayTransCnt()
	{
		return dayTransCnt;
	}

	public void setDayTransCnt( String dayTransCnt )
	{
		this.dayTransCnt = dayTransCnt;
	}

	public String getOneLimitAmt()
	{
		return oneLimitAmt;
	}

	public void setOneLimitAmt( String oneLimitAmt )
	{
		this.oneLimitAmt = oneLimitAmt;
	}

	public String getTotalLimitAmt()
	{
		return totalLimitAmt;
	}

	public void setTotalLimitAmt( String totalLimitAmt )
	{
		this.totalLimitAmt = totalLimitAmt;
	}

	public String getIsSendSms()
	{
		return isSendSms;
	}

	public void setIsSendSms( String isSendSms )
	{
		this.isSendSms = isSendSms;
	}

	public String getMerContName()
	{
		return merContName;
	}

	public void setMerContName( String merContName )
	{
		this.merContName = merContName;
	}

	public String getMerContTel()
	{
		return merContTel;
	}

	public void setMerContTel( String merContTel )
	{
		this.merContTel = merContTel;
	}

	public String getMerContMp()
	{
		return merContMp;
	}

	public void setMerContMp( String merContMp )
	{
		this.merContMp = merContMp;
	}

	public String getMerContEmail()
	{
		return merContEmail;
	}

	public void setMerContEmail( String merContEmail )
	{
		this.merContEmail = merContEmail;
	}

	public String getMerContRole()
	{
		return merContRole;
	}

	public void setMerContRole( String merContRole )
	{
		this.merContRole = merContRole;
	}

	public String getMerState()
	{
		return merState;
	}

	public void setMerState( String merState )
	{
		this.merState = merState;
	}

	public String getMerId()
	{
		return merId;
	}

	public void setMerId( String merId )
	{
		this.merId = merId;
	}

	public String getOperCode()
	{
		return operCode;
	}

	public void setOperCode( String operCode )
	{
		this.operCode = operCode;
	}

	public String getOperPwd()
	{
		return operPwd;
	}

	public void setOperPwd( String operPwd )
	{
		this.operPwd = operPwd;
	}

	public String getOperName()
	{
		return operName;
	}

	public void setOperName( String operName )
	{
		this.operName = operName;
	}

	public String getOperTel()
	{
		return operTel;
	}

	public void setOperTel( String operTel )
	{
		this.operTel = operTel;
	}

	public String getOperMp()
	{
		return operMp;
	}

	public void setOperMp( String operMp )
	{
		this.operMp = operMp;
	}

	public String getOperEmail()
	{
		return operEmail;
	}

	public void setOperEmail( String operEmail )
	{
		this.operEmail = operEmail;
	}

	public String getOperState()
	{
		return operState;
	}

	public void setOperState( String operState )
	{
		this.operState = operState;
	}

	public String getIsAdmin()
	{
		return isAdmin;
	}

	public void setIsAdmin( String isAdmin )
	{
		this.isAdmin = isAdmin;
	}

	public File getCerFile()
	{
		return cerFile;
	}

	public void setCerFile( File cerFile )
	{
		this.cerFile = cerFile;
	}

	public String getCerFileFileName()
	{
		return cerFileFileName;
	}

	public void setCerFileFileName( String cerFileFileName )
	{
		this.cerFileFileName = cerFileFileName;
	}

	public File getPfxFile()
	{
		return pfxFile;
	}

	public void setPfxFile( File pfxFile )
	{
		this.pfxFile = pfxFile;
	}

	public String getPfxFileFileName()
	{
		return pfxFileFileName;
	}

	public void setPfxFileFileName( String pfxFileFileName )
	{
		this.pfxFileFileName = pfxFileFileName;
	}

	public String getPfxPass()
	{
		return pfxPass;
	}

	public void setPfxPass( String pfxPass )
	{
		this.pfxPass = pfxPass;
	}

	public String getUnUserName()
	{
		return unUserName;
	}

	public void setUnUserName( String unUserName )
	{
		this.unUserName = unUserName;
	}

	public String getUnUserPass()
	{
		return unUserPass;
	}

	public void setUnUserPass( String unUserPass )
	{
		this.unUserPass = unUserPass;
	}

	public String getOperId()
	{
		return operId;
	}

	public void setOperId( String operId )
	{
		this.operId = operId;
	}

	public String getGrpId()
	{
		return grpId;
	}

	public void setGrpId( String grpId )
	{
		this.grpId = grpId;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

}
