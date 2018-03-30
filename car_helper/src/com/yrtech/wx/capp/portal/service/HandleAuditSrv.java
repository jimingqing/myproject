package com.yrtech.wx.capp.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.StringUtil;
import com.yrtech.wx.capp.portal.dao.impl.HandleAuditLogDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.dao.impl.UserInfoDao;
import com.yrtech.wx.capp.portal.model.HandleAuditLog;
import com.yrtech.wx.capp.portal.model.OrderLog;
import com.yrtech.wx.capp.portal.model.UserInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.PageData;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class HandleAuditSrv {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private HandleAuditLogDao handleAuditLogDao;
	@Resource
	private OrderLogDao orderLogDao;
	@Resource
	private UserInfoDao userInfoDao;
	
	public RetInfo saveOrderHandle(String type, Integer merId, String merCode, String handleIds) throws Exception{
		RetInfo retInfo = new RetInfo();
		if(type.equals("1")){
			OrderLog ordlog = new OrderLog();
			List<OrderLog> ordList = new ArrayList<OrderLog>();
			String hql = "from HandleAuditLog where merId="+merId+" and procStat='"+Constants.HANDLE_AUDIT_STATE_H+"' and id in ("+handleIds+")";
			List<HandleAuditLog> list = handleAuditLogDao.findTbyHql(hql);
			for(HandleAuditLog o : list){
				ordlog = new OrderLog();
				ordlog.setMerId(o.getMerId());
				if(o.getProcCont10()!=null){
					UserInfo userInfo = userInfoDao.find(Integer.valueOf(o.getProcCont10()));
					ordlog.setUserInfo(userInfo);
				}
				ordlog.setOrdAmt(Double.valueOf(o.getProcCont8()));
				ordlog.setActAmt(Double.valueOf(o.getProcCont8()));
				ordlog.setOrdState(Constants.ORDER_LOG_STATE_A);
				ordlog.setOrdOrg(Constants.ORDER_LOG_ORG_S);
				ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
				ordlog.setChkFlag(Constants.ORDER_LOG_CHK_FLAG_I);
				ordlog.setCreateTime(DateOper.getCurTimestamp());
				ordlog.setResv(o.getProcCont9());
				String time = String.valueOf(DateOper.getCurTimestamp().getTime());
				ordlog.setOrdId(merCode+time.substring(time.length()-6)+StringUtil.randomString(4));
				ordlog.setBankName(o.getProcCont6());
				ordlog.setAcctName(o.getProcCont4());
				ordlog.setAcctNo(o.getProcCont7());
				ordlog.setUserCode(o.getProcCont3());
				ordlog.setQryCnt(0);
				ordList.add(ordlog);
			}
			orderLogDao.batchSave(ordList);
			hql = "update HandleAuditLog set procStat='"+Constants.HANDLE_AUDIT_STATE_A+"' where id in ("+handleIds+") and procStat='"+Constants.HANDLE_AUDIT_STATE_H+"'";
			handleAuditLogDao.executeDML(hql);
			
			//更新商品库存
		}else{
			String hql = "update HandleAuditLog set procStat='"+Constants.HANDLE_AUDIT_STATE_F+"' where id in ("+handleIds+") and procStat='"+Constants.HANDLE_AUDIT_STATE_H+"'";
			handleAuditLogDao.executeDML(hql);
		}
		
		log.info("订单审核信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("订单审核信息成功");
		return retInfo;
	}
	
	/**
	 * 查询订单经办信息
	 */
	public RetInfo qryOrderHandAudit(Integer merId, String userType, String procState, String startDate, String endDate, Pager pager) throws Exception{
		RetInfo retInfo = new RetInfo();
		String select = "select h, o ";
		String totalselect = "select count(h.id) ";
		String wherehql = "from HandleAuditLog h, MerOperInfo o where h.operId=o.id and h.merId="+merId+" and h.procType='"+Constants.HANDLE_AUDIT_TYPE_10+"'";
		if(!StringUtils.isEmpty(userType)){
			wherehql += " and h.procCont2 ='"+userType+"'";
		}
		if(!StringUtils.isEmpty(procState)){
			wherehql += " and h.procStat ='"+procState+"'";
		}
		if(!StringUtils.isEmpty(startDate)&&!StringUtils.isEmpty(endDate)){
			wherehql += " and h.handleDate between '"+startDate+" 00:00:00"+"' and '"+endDate+" 23:59:59"+"'";
		}
		wherehql += " order by h.handleDate desc";
		select = wherehql;
		totalselect += wherehql;
		
		PageData data = handleAuditLogDao.getScrollDataByHqlForMutlRel(pager.getStartRow(), pager.getPageSize(), select, totalselect);
		pager.setTotalRows(Integer.parseInt(String.valueOf(data.getTotalRecords())));
		log.info("查询订单经办信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询订单经办信息成功");
		retInfo.setList(data.getResultlist());
		return retInfo;
	}
	
	public static void main(String[] args) {
		try{
			HandleAuditSrv srv = (HandleAuditSrv)SpringInfo.getBean("handleAuditSrv");
			Pager pager = new Pager();
			pager.setCurrentPage(1);
			RetInfo retInfo = srv.qryOrderHandAudit(1, "R", "H", "", "",pager);
			System.out.println(retInfo.getRetMsg());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
