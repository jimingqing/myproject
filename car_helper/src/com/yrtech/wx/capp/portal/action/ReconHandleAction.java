package com.yrtech.wx.capp.portal.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.OrderItem;
import com.yrtech.wx.capp.portal.model.OrderLog;
import com.yrtech.wx.capp.portal.service.OrderLogSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.OrderLogVo;

public class ReconHandleAction extends BaseAction{

	private Log log = LogFactory.getLog(this.getClass());
	
	private String startDate;
	private String endDate;
	private String ordId;
	public String reconHandle(){
		if(StringUtils.isEmpty(startDate)){
			log.info("开始时间错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "开始时间错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(endDate)){
			log.info("结束时间错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "结束时间错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = orderLogSrv.qryOrdLogDetail(getMerOperInfoVo().getMerId(), ordId);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				OrderLogVo vo = new OrderLogVo();
				OrderLog ordlog = (OrderLog)retInfo.getList().get(0);
				List<OrderItem> items = (List<OrderItem>)retInfo.getList().get(1);
				PropertyUtils.copyProperties(vo, ordlog);
				vo.setMerName(getMerOperInfoVo().getMerName());
				if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_S)){
					vo.setOrdState("成功");
				}else if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_F)){
					vo.setOrdState("失败");
				}else{
					vo.setOrdState("处理中");
				}
				if(ordlog.getLiqState().equals(Constants.ORDER_LOG_LIQ_STATE_Y)){
					vo.setLiqState("已结算");
				}else{
					vo.setLiqState("未结算");
				}
				if(ordlog.getOrdOrg().equals(Constants.ORDER_LOG_ORG_C)){
					vo.setOrdOrg("客户端");
				}else{
					vo.setOrdOrg("控台导入");
				}
				vo.setOrdAmtStr(GmsUtil.double2amt(ordlog.getOrdAmt()));
				vo.setActAmtStr(GmsUtil.double2amt(ordlog.getActAmt()));
				vo.setPayUserCode(ordlog.getUserCode());
				vo.setOrdItems(items);
				
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "OK");
				resultMap.put(Constants.JSON_CONTENT, vo);
			}else{
				log.info("订单信息查询失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "订单信息查询失败！");
			}
		} catch (Exception e) {
			log.error("查询订单明细异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询订单明细异常");
		}
		return SUCCESS;
	}
	
	@Resource
	private OrderLogSrv orderLogSrv;

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

	public OrderLogSrv getOrderLogSrv() {
		return orderLogSrv;
	}

	public void setOrderLogSrv(OrderLogSrv orderLogSrv) {
		this.orderLogSrv = orderLogSrv;
	}
	
}
