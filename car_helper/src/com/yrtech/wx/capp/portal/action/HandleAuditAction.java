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

import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.HandleAuditLog;
import com.yrtech.wx.capp.portal.model.MerOperInfo;
import com.yrtech.wx.capp.portal.service.HandleAuditSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.HandleAuditLogVo;

public class HandleAuditAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String userType;
	private String userCode;
	private String userName;
	private String bankCode;
	private String bankName;
	private String openBankCard;
	private String ordDesc;
	private String procState;
	private String startDate;
	private String endDate;
	
	private String curPage;
	private String handleIds;
	private String type;
	
	@Resource
	private HandleAuditSrv handleAuditSrv;
	
	/**
	 * 审核订单信息
	 */
	public String auditOrder(){
		if(StringUtils.isEmpty(type)){
			log.info("审核类型参数错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "审核类型参数错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(handleIds)){
			log.info("参数错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "参数错误");
			return SUCCESS;
		}
		try {
			String[] strs = handleIds.split(",");
			String ids = "";
			for(int i=0;i<strs.length;i++){
				if(i==strs.length-1){
					ids += strs[i];
				}else{
					ids += strs[i]+",";
				}
			}
			RetInfo retInfo = handleAuditSrv.saveOrderHandle(type, getMerOperInfoVo().getMerId(), getMerOperInfoVo().getMerCode(), ids);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("审核订单信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "审核订单信息成功");
			}else{
				log.info("审核订单信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "审核订单信息失败");
			}
		} catch (Exception e) {
			log.error("审核订单信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "审核订单信息异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询订单经办信息
	 */
	public String qryOrderHandle(){
		try{
			Pager pager = new Pager();
			pager.setCurrentPage(Integer.valueOf(curPage==null?"1":curPage));
			RetInfo retInfo = handleAuditSrv.qryOrderHandAudit(getMerOperInfoVo().getMerId(), userType, procState, startDate, endDate, pager);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				List<Object[]> list = retInfo.getList();
				List<HandleAuditLogVo> volist = new ArrayList<HandleAuditLogVo>();
				HandleAuditLogVo vo = new HandleAuditLogVo();
				HandleAuditLog hal = new HandleAuditLog();
				MerOperInfo oper = new MerOperInfo();
				String cardNo = "";
				for(Object[] o : list){
					vo = new HandleAuditLogVo();
					hal = (HandleAuditLog)o[0];
					oper = (MerOperInfo)o[1];
					PropertyUtils.copyProperties(vo, hal);
					if(vo.getProcCont3()==null){
						vo.setProcCont3("");
					}
					vo.setOperCode(oper.getOperCode());
					cardNo = SecretUtil.getDecryptedString(vo.getProcCont7());
					vo.setProcCont7("**** "+cardNo.substring(cardNo.length()-4));
					vo.setProcCont8(GmsUtil.double2amt(vo.getProcCont8()));
					volist.add(vo);
				}
				Map<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("datalist", volist);
				retmap.put("pager", pager);
				
				log.info("查询订单经办信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询订单经办信息成功");
				resultMap.put(Constants.JSON_CONTENT, retmap);
			}else{
				log.info("查询订单经办信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询订单经办信息失败");
			}
		} catch (Exception e) {
			log.error("查询订单经办信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询订单经办信息异常");
		}
		return SUCCESS;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOpenBankCard() {
		return openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}

	public String getOrdDesc() {
		return ordDesc;
	}

	public void setOrdDesc(String ordDesc) {
		this.ordDesc = ordDesc;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProcState() {
		return procState;
	}

	public void setProcState(String procState) {
		this.procState = procState;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getHandleIds() {
		return handleIds;
	}

	public void setHandleIds(String handleIds) {
		this.handleIds = handleIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
