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
import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.LogisticsCorp;
import com.yrtech.wx.capp.portal.model.OrderItem;
import com.yrtech.wx.capp.portal.model.OrderLog;
import com.yrtech.wx.capp.portal.model.ProvCity;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.service.OrderLogSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;
import com.yrtech.wx.capp.portal.vo.OrderLogVo;

public class OrderAction extends BaseAction
{

	private Log log = LogFactory.getLog(this.getClass());

	private String merCode;
	private String payPhoneNo;
	private String recvPhoneNo;
	private String ordAmt;
	private String[] itemNames;
	private String[] itemAmts;
	private String[] itemNums;
	private String m;

	private int merId;
	
	private String ordId;
	private String userId;
	private String ordState;
	private String ordOrg;
	private String startDate;
	private String endDate;
	private String payUserName;
	private String payUserCode;
	private String timePeriod;// 查询时间段，01：近一天；02：近三天；03：近一周；04：近一月；05：近一年
	private String curPage;
	private String resv;

	private String userType;
	private String userCode;
	private String userName;
	private String bankCode;
	private String bankName;
	private String openBankCard;
	private String ordDesc;

	private String cityId;
	private String areaId;
	private String deptName;
	private String qryType;
	private String shipState;
	private String logtCode;
	private String shipNo;
	
	

	public int getMerId()
	{
		return merId;
	}

	public void setMerId( int merId )
	{
		this.merId = merId;
	}

	@Resource
	private OrderLogSrv orderLogSrv;

	public String saveOrderShipInfo()
	{
		if(StringUtils.isEmpty(ordId))
		{
			log.info("订单id错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "订单id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(shipState))
		{
			log.info("发货状态错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "发货状态错误");
			return SUCCESS;
		}
		if(shipState.equals("Y"))
		{
			if(StringUtils.isEmpty(logtCode))
			{
				log.info("快递公司编号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "快递公司编号错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(shipNo))
			{
				log.info("发货单号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "发货单号错误");
				return SUCCESS;
			}
		}
		try
		{
			RetInfo retInfo = orderLogSrv.saveOrderLogshipInfo(
					getMerOperInfoVo().getMerId(), ordId, shipState,
					logtCode, shipNo);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("订单发货更新成功，ordId=" + ordId);
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "订单提交成功");
			}else
			{
				log.info("订单发货更新失败，ordId=" + ordId);
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单发货更新失败");
			}
		}
		catch (Exception e)
		{
			log.error("订单发货更新异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "订单发货更新异常");
		}
		return SUCCESS;
	}

	public String qryLogtCorp()
	{
		try
		{
			Map<Integer, LogisticsCorp> map = (Map<Integer, LogisticsCorp>) DataCacheManager
					.get(Constants.CACHE_KEY_LOGTCORP);
			resultMap
					.put(Constants.JSON_RETCODE,
							Constants.RET_CODE_SUCCESS);
			resultMap.put(Constants.JSON_RETMSG, "查询物流公司信息成功");
			resultMap.put(Constants.JSON_CONTENT, map.values());
		}
		catch (Exception e)
		{
			log.error("订单提交异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "订单提交异常");
		}
		return SUCCESS;
	}

	/**
	 * 提交订单经办信息
	 */
	public String addOrderHandle()
	{
		if(StringUtils.isEmpty(userType))
		{
			log.info("用户类型错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户类型错误");
			return SUCCESS;
		}
		if(userType.equals("R"))
		{
			if(StringUtils.isEmpty(userCode))
			{
				log.info("用户编号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户编号错误");
				return SUCCESS;
			}
		}else
		{
			if(StringUtils.isEmpty(userName))
			{
				log.info("用户姓名错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户姓名错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(bankCode))
			{
				log.info("银行编号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "银行编号错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(bankName))
			{
				log.info("银行名称错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "银行名称错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(openBankCard))
			{
				log.info("用户银行账号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户银行账号错误");
				return SUCCESS;
			}
		}
		if(StringUtils.isEmpty(ordAmt))
		{
			log.info("订单金额错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "订单金额错误");
			return SUCCESS;
		}

		try
		{
			RetInfo retInfo = orderLogSrv.addOrderHandAudit(
					getMerOperInfoVo().getMerId().toString(),
					getMerOperInfoVo().getId().toString(),
					getMerOperInfoVo().getMerCode(), userType, userCode,
					userName, bankCode, bankName, openBankCard, ordAmt,
					ordDesc);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("订单提交成功，merCode" + merCode);
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "订单提交成功");
			}else
			{
				log.info("订单提交失败，merCode=" + merCode);
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单提交失败");
			}
		}
		catch (Exception e)
		{
			log.error("订单提交异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "订单提交异常");
		}
		return SUCCESS;
	}

	/**
	 * 提交订单付款
	 */
	public String orderPay()
	{
		if(StringUtils.isEmpty(userType))
		{
			log.info("用户类型错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户类型错误");
			return SUCCESS;
		}
		if(userType.equals("R"))
		{
			if(StringUtils.isEmpty(userCode))
			{
				log.info("用户编号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户编号错误");
				return SUCCESS;
			}
		}else
		{
			if(StringUtils.isEmpty(userName))
			{
				log.info("用户姓名错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户姓名错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(bankCode))
			{
				log.info("银行编号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "银行编号错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(bankName))
			{
				log.info("银行名称错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "银行名称错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(openBankCard))
			{
				log.info("用户银行账号错误");
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "用户银行账号错误");
				return SUCCESS;
			}
		}
		if(StringUtils.isEmpty(ordAmt))
		{
			log.info("订单金额错误");
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "订单金额错误");
			return SUCCESS;
		}
		try
		{
			RetInfo retInfo = orderLogSrv.orderPay(getMerOperInfoVo()
					.getMerCode(), userType, userCode, userName, bankCode,
					bankName, openBankCard, ordAmt, ordDesc);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
			{
				log.info("订单提交成功，merCode" + merCode + "; ordId="
						+ retInfo.getContent());
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "订单提交成功，订单号为："
						+ retInfo.getContent());
			}else
			{
				log.info("订单提交失败，merCode=" + merCode);
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单提交失败");
			}
		}
		catch (Exception e)
		{
			log.error("订单提交异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "订单提交异常");
		}
		return SUCCESS;
	}

	public String qryOrdInfoDetail()
	{
		MerOperInfoVo  vo1 =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		 if(vo1==null)
		 {
			 resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
			 resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
			 return SUCCESS;
		 }
		 
		 if(!(vo1.getMerCode()+"").equals("880001") )
		 {
//			 System.out.println(vo1.getMerId());
			if(!(vo1.getMerId()+"").equals(merId+""))
			{
			     log.info("你没查询其他商户订单的权限");
			     resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
			     resultMap.put(Constants.JSON_RETMSG, "没有权限");
			     return SUCCESS;
			}
		 }
		 
		 
		 
		 
		if(StringUtils.isEmpty(ordId))
		{
			log.info("订单编号错误，  ordId=" + ordId);
			resultMap.put(Constants.JSON_RETCODE,
					Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "订单编号错误");
			return SUCCESS;
		}
		try
		{
			RetInfo retInfo = orderLogSrv.qryOrdLogDetail( ordId);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode()))
			{
				OrderLog ordlog = (OrderLog) retInfo.getList().get(0);
//				UserInfo userInfo  =(UserInfo)retInfo.getObject();
				if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_S))
				{
					ordlog.setOrdState("成功");
				}else if(ordlog.getOrdState().equals(
						Constants.ORDER_LOG_STATE_F))
				{
					ordlog.setOrdState("失败");
				}else
				{
					ordlog.setOrdState("处理中");
				}
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询订单明细成功");
//				resultMap.put("userInfo", userInfo);
				resultMap.put(Constants.JSON_CONTENT, ordlog);
			}else
			{
				log.info("订单信息查询失败！");
//				resultMap.put("userInfo", user)
				resultMap.put(Constants.JSON_RETCODE,
						Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单信息查询失败！");
			}
		}
		catch (Exception e)
		{
			log.error("查询订单明细异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询订单明细异常");
		}
		return SUCCESS;
	}

	public String qryOrdInfo()
	{
		try
		{
			
			MerOperInfoVo  vo1 =(MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
			 if(vo1==null)
			 {
				 resultMap.put(Constants.JSON_RETCODE, Constants.NO_LOGIN);
				 resultMap.put(Constants.JSON_RETMSG, "用户没有登录");
				 return SUCCESS;
			 }
			 
			 if(!(vo1.getMerCode()+"").equals("880001") )
			 {
//				 System.out.println(vo1.getMerId());
				if(!(vo1.getMerId()+"").equals(merId+""))
				{
				     log.info("你没查询其他商户订单的权限");
				     resultMap.put(Constants.JSON_RETCODE, Constants.NO_PRO);
				     resultMap.put(Constants.JSON_RETMSG, "没有权限");
				     return SUCCESS;
				}
			 }
			 
			Pager pager = new Pager();
			pager.setCurrentPage(Integer.valueOf(curPage == null ? "1"
					: curPage));
			if(merId==0)
			{
				resultMap.put(Constants.JSON_RETCODE, Constants.BLANK);
				resultMap.put(Constants.JSON_RETMSG, "商户id不能为空");
			}
			RetInfo retInfo = orderLogSrv.qryOrderInfo(merId, ordState,
					startDate, endDate, qryType, ordAmt,cityId,areaId,userCode,payUserName, pager);
//			
//			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode()))
//			{
//				
//				log.info("订单查询成功");
//				if(retInfo.getList() == null)
//				{
//					Map<String, Object> retmap = new HashMap<String, Object>();
//					retmap.put("datalist", "");
//					retmap.put("pager", pager);
//					// retmap.put("totalAmt", "0");
//					resultMap.put(Constants.JSON_RETCODE,
//							Constants.RET_CODE_SUCCESS);
//					resultMap.put(Constants.JSON_RETMSG, "OK");
//					resultMap.put(Constants.JSON_CONTENT, retmap);
//				}else
//				{
//					List list = retInfo.getList();
//					Map<String, Object> retmap = new HashMap<String, Object>();
//					retmap.put("datalist", list);
//					retmap.put("pager", pager);
////					retmap.put("totalAmt", retInfo.getObject());
//
//					resultMap.put(Constants.JSON_RETCODE,
//							Constants.RET_CODE_SUCCESS);
//					resultMap.put(Constants.JSON_RETMSG, "OK");
//					resultMap.put(Constants.JSON_CONTENT, retmap);
//					resultMap.put("totleMoney", retInfo.getObject());
//					System.out.println(retInfo.getObject());
//				}
//			}else
//			{
//				log.info("订单信息查询失败！");
//				resultMap.put(Constants.JSON_RETCODE,
//						Constants.RET_CODE_ERROR);
//				resultMap.put(Constants.JSON_RETMSG, "订单信息查询失败！");
//			}
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("订单查询成功");
				
				if(retInfo.getList() == null){
					Map<String, Object> retmap = new HashMap<String, Object>();
					retmap.put("datalist", "");
					retmap.put("pager", pager);
					retmap.put("totalAmt", "0");
					resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
					resultMap.put(Constants.JSON_RETMSG, "OK");
					resultMap.put(Constants.JSON_CONTENT, retmap);
				}else{
					List list = retInfo.getList();
					List<OrderLogVo> tmplist = new ArrayList<OrderLogVo>();
					Object[] objs;
					OrderLogVo vo = new OrderLogVo();
					OrderLog ordlog = new OrderLog();
					Map<String, ProvCity> map = (Map<String, ProvCity>)DataCacheManager.get(Constants.CACHE_KEY_PROVCITY);
					for(Object o : list){
						vo = new OrderLogVo();
						objs = (Object[])o;
						ordlog = (OrderLog)objs[0];
						PropertyUtils.copyProperties(vo, ordlog);
//						vo.setMerName(getMerOperInfoVo().getMerName());
						if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_S)){
							vo.setOrdState("成功");
						}else if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_F)){
							vo.setOrdState("失败");
						}else if(ordlog.getOrdState().equals("C")){
							vo.setOrdState("撤销");
						}else if(ordlog.getOrdState().equals("I")){
							vo.setOrdState("初始化");
						}
						if(ordlog.getLiqState().equals(Constants.ORDER_LOG_LIQ_STATE_Y)){
							vo.setLiqState("已结算");
						}else{
							vo.setLiqState("未结算");
						}
						if(ordlog.getOrdOrg().equals(Constants.ORDER_LOG_ORG_C)){
							vo.setOrdOrg("客户端");
						}else{
							vo.setOrdOrg("控台");
						}
						if(ordlog.getShipState()!=null && ordlog.getShipState().equals(Constants.YES_OR_NO_FLAG_Y)){
							vo.setShipState("已发货");
						}else{
							vo.setShipState("未发货");
						}
						vo.setOrdAmtStr(GmsUtil.double2amt(ordlog.getOrdAmt()));
						vo.setActAmtStr(GmsUtil.double2amt(ordlog.getActAmt()));
						vo.setPayUserCode(ordlog.getUserCode());
						vo.setAcctNo(SecretUtil.getDecryptedString(ordlog.getAcctNo()).substring(SecretUtil.getDecryptedString(ordlog.getAcctNo()).length()-4));
						if(ordlog.getUserInfo()!=null){
							vo.setDeptName(ordlog.getUserInfo().getDeptName());
							vo.setUserCity(map.get(ordlog.getUserInfo().getUserCity()).getName());
							vo.setUserArea(map.get(ordlog.getUserInfo().getUserArea()).getName());
							vo.setUserProv(map.get(ordlog.getUserInfo().getUserProv()).getName());
						}else{
							vo.setDeptName("");
							vo.setUserCity("");
							vo.setUserArea("");
							vo.setUserProv("");
						}
						tmplist.add(vo);
					}
					
					Map<String, Object> retmap = new HashMap<String, Object>();
					retmap.put("datalist", tmplist);
					retmap.put("pager", pager);
					retmap.put("totalAmt", retInfo.getObject());
					
					resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
					resultMap.put(Constants.JSON_RETMSG, "OK");
					resultMap.put(Constants.JSON_CONTENT, retmap);
				}
			}else{
				log.info("订单信息查询失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单信息查询失败！");
			}
		} catch (Exception e) {
			log.error("订单信息查询异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "订单信息查询异常！");
		}
		return SUCCESS;
		}

	public String getRecvPhoneNo()
	{
		return recvPhoneNo;
	}

	public void setRecvPhoneNo( String recvPhoneNo )
	{
		this.recvPhoneNo = recvPhoneNo;
	}

	public String getOrdAmt()
	{
		return ordAmt;
	}

	public void setOrdAmt( String ordAmt )
	{
		this.ordAmt = ordAmt;
	}

	public String[] getItemNames()
	{
		return itemNames;
	}

	public void setItemNames( String[] itemNames )
	{
		this.itemNames = itemNames;
	}

	public String[] getItemAmts()
	{
		return itemAmts;
	}

	public void setItemAmts( String[] itemAmts )
	{
		this.itemAmts = itemAmts;
	}

	public String[] getItemNums()
	{
		return itemNums;
	}

	public void setItemNums( String[] itemNums )
	{
		this.itemNums = itemNums;
	}

	public String getMerCode()
	{
		return merCode;
	}

	public void setMerCode( String merCode )
	{
		this.merCode = merCode;
	}

	public String getPayPhoneNo()
	{
		return payPhoneNo;
	}

	public void setPayPhoneNo( String payPhoneNo )
	{
		this.payPhoneNo = payPhoneNo;
	}


	public String getUserId()
	{
		return userId;
	}

	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public String getTimePeriod()
	{
		return timePeriod;
	}

	public void setTimePeriod( String timePeriod )
	{
		this.timePeriod = timePeriod;
	}

	public String getOrdState()
	{
		return ordState;
	}

	public void setOrdState( String ordState )
	{
		this.ordState = ordState;
	}

	public String getCurPage()
	{
		return curPage;
	}

	public void setCurPage( String curPage )
	{
		this.curPage = curPage;
	}

	public String getResv()
	{
		return resv;
	}

	public void setResv( String resv )
	{
		this.resv = resv;
	}

	public String getOrdOrg()
	{
		return ordOrg;
	}

	public void setOrdOrg( String ordOrg )
	{
		this.ordOrg = ordOrg;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate( String startDate )
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate( String endDate )
	{
		this.endDate = endDate;
	}

	public String getPayUserName()
	{
		return payUserName;
	}

	public void setPayUserName( String payUserName )
	{
		this.payUserName = payUserName;
	}

	public String getPayUserCode()
	{
		return payUserCode;
	}

	public void setPayUserCode( String payUserCode )
	{
		this.payUserCode = payUserCode;
	}

	public String getOrdId()
	{
		return ordId;
	}

	public void setOrdId( String ordId )
	{
		this.ordId = ordId;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType( String userType )
	{
		this.userType = userType;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode( String userCode )
	{
		this.userCode = userCode;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getOpenBankCard()
	{
		return openBankCard;
	}

	public void setOpenBankCard( String openBankCard )
	{
		this.openBankCard = openBankCard;
	}

	public String getOrdDesc()
	{
		return ordDesc;
	}

	public void setOrdDesc( String ordDesc )
	{
		this.ordDesc = ordDesc;
	}

	public String getBankCode()
	{
		return bankCode;
	}

	public void setBankCode( String bankCode )
	{
		this.bankCode = bankCode;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName( String bankName )
	{
		this.bankName = bankName;
	}

	public String getCityId()
	{
		return cityId;
	}

	public void setCityId( String cityId )
	{
		this.cityId = cityId;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId( String areaId )
	{
		this.areaId = areaId;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName( String deptName )
	{
		this.deptName = deptName;
	}

	public String getQryType()
	{
		return qryType;
	}

	public void setQryType( String qryType )
	{
		this.qryType = qryType;
	}

	public String getShipState()
	{
		return shipState;
	}

	public void setShipState( String shipState )
	{
		this.shipState = shipState;
	}

	public String getLogtCode()
	{
		return logtCode;
	}

	public void setLogtCode( String logtCode )
	{
		this.logtCode = logtCode;
	}

	public String getShipNo()
	{
		return shipNo;
	}

	public void setShipNo( String shipNo )
	{
		this.shipNo = shipNo;
	}

}
