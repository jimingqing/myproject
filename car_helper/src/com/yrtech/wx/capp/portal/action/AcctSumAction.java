package com.yrtech.wx.capp.portal.action;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.portal.model.UserAcctSumLog;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.service.AcctSumLogSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.UserAcctSumLogVo;
public class AcctSumAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String startDate;
	private String endDate;
	private String merId;
	
	public String getMerId()
	{
		return merId;
	}

	public void setMerId( String merId )
	{
		this.merId = merId;
		System.out.println(merId);
	}

	@Resource
	private AcctSumLogSrv acctSumLogSrv;
	
	public String qryUserAcctSumLog(){
		if(StringUtils.isEmpty(startDate)){
			startDate = DateOper.getYesterday();
		}
		if(StringUtils.isEmpty(endDate)){
			endDate = DateOper.getYesterday();
		}
		if(merId==null || merId.length()==0)
		{
			log.info("商户id不能为空");
			resultMap.put(Constants.JSON_RETCODE, Constants.BLANK);
			resultMap.put(Constants.JSON_RETMSG, "商户id不能为空");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = acctSumLogSrv.qryUserAcctSumLog(new Integer(merId), startDate, endDate);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				List list = retInfo.getList();
				UserInfo userInfo = new UserInfo();
				UserAcctSumLog acctlog = new UserAcctSumLog();
				List<UserAcctSumLogVo> volist = new ArrayList<UserAcctSumLogVo>();
				UserAcctSumLogVo vo = new UserAcctSumLogVo();
				for(Object o : list){
					vo = new UserAcctSumLogVo();
					acctlog = (UserAcctSumLog)((Object[])o)[0];
					PropertyUtils.copyProperties(vo, acctlog);
					if(acctlog.getUserInfo()==null){
						vo.setUserName(acctlog.getResv());
						vo.setUserCode("");
					}else{
						vo.setUserName(acctlog.getUserInfo().getUserName());
						vo.setUserCode(acctlog.getUserInfo().getUserCode());
					}
					
					volist.add(vo);
				}
				log.info("查询用户账务汇总日志信息成功，merId="+getMerOperInfoVo().getMerId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询用户账务汇总日志信息成功");
				resultMap.put(Constants.JSON_CONTENT, volist);
			}else{
				log.info("查询用户用户账务汇总日志信息失败，merId="+getMerOperInfoVo().getMerId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询用户账务汇总日志信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询用户账务汇总日志信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询用户账务汇总日志信息异常");
		}
		return SUCCESS;
	}
	
	public String qryMerAcctSumLog(){
		if(StringUtils.isEmpty(startDate)){
			startDate = DateOper.getYesterday();
		}
		if(StringUtils.isEmpty(endDate)){
			endDate = DateOper.getYesterday();
		}
		if(merId==null || merId.length()==0)
		{
			log.info("商户id不能为空");
			resultMap.put(Constants.JSON_RETCODE, Constants.BLANK);
			resultMap.put(Constants.JSON_RETMSG, "商户id不能为空");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = acctSumLogSrv.qryMerAcctSumLog(new Integer(merId), startDate, endDate);
			
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询商户账务汇总日志信息成功");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商户账务汇总日志信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询商户账务汇总日志信息失败，merId="+getMerOperInfoVo().getMerId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商户账务汇总日志信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询商户账务汇总日志信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商户账务汇总日志信息异常");
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
	
	
}