package handout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.framework.core.util.CPublic;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.dao.impl.OrderItemDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.model.BankInfo;
import com.yrtech.wx.capp.portal.model.Goods;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.OrderItem;
import com.yrtech.wx.capp.portal.model.OrderLog;
import com.yrtech.wx.capp.portal.util.Constants;

/**
 * 交易发布任务
 */
public class HandoutTask extends Task {
	
	private static Logger log = Logger.getLogger(HandoutTask.class);
	
	private OrderLog ordlog;
	private MerInfo merInfo;
	private String transType;
	
	public void run() {
		if(transType.equals("pay")){
			transPay();
		}else{
			transQry();
		}
	}
	
	private void transQry(){
		//发送支付请求
		TransOrder order = new TransOrder();
		order.setMerCode(merInfo.getMerCode());
		order.setOrdId(ordlog.getOrdId());
		TransProcess.qryReq(order);
		
		//更新订单状态
		//交易处理成功，订单状态更新成功
		if(order.getRetCode()==null){
			log.info("查询交易结果异常, ordId="+order.getOrdId());
			return;
		}
		if(order.getRetCode().equals(TransRetCode.RET_CODE_SUCCESS)){
			if(order.getRetCodeDetail().equals(TransRetCode.RET_CODE_SUCCESS)){
				ordlog.setOrdState(Constants.ORDER_LOG_STATE_S);
			}else{
				ordlog.setOrdState(Constants.ORDER_LOG_STATE_F);
			}
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCodeDetail());
			ordlog.setBankRespMsg("errMsg="+order.getErrMsgDetail()+"; costTime="+order.getCostTime());
		}
		//交易处理中，需要轮询订单进行重发
		else if(order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2000)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2007)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2001)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2003)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2005)
				){
			ordlog.setOrdState(Constants.ORDER_LOG_STATE_I);
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCode());
			ordlog.setBankRespMsg("errMsg="+order.getErrMsg()+"; costTime="+order.getCostTime());
		}
		//交易处理失败
		else{
			ordlog.setOrdState(Constants.ORDER_LOG_STATE_F);
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCode());
			ordlog.setBankRespMsg("errMsg="+order.getErrMsg()+"; costTime="+order.getCostTime());
		}
//		ordlog.setBankDate(CPublic.getDateAndTime());
//		ordlog.setQryCnt(ordlog.getQryCnt()+1);
		
		OrderLogDao dao = (OrderLogDao)SpringInfo.getBean("orderLogDao");
		dao.update(ordlog);
		
		try{
			if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_F)){
				//更新商品库存
				String hql = "from OrderItem o, Goods g where o.itemName=g.goodsName and o.ordId=?";
				OrderItemDao itemdao = (OrderItemDao)SpringInfo.getBean("orderItemDao");
				List list = itemdao.findTbyHql(hql, new Object[]{ordlog.getId()});
				List params = new ArrayList();
				OrderItem item = new OrderItem();
				Goods goods = new Goods();
				for(Object o : list){
					item = (OrderItem)((Object[])o)[0];
					goods = (Goods)((Object[])o)[1];
					params = new ArrayList();
					params.add("");
					params.add("add");
					params.add(item.getItemNum());
					params.add(goods.getId());
					itemdao.callProcedure("{?=Call edit_goods_store(?,?,?)}", params);
				}
			}
		}catch(Exception e){
			log.error("更新商品库存异常"+e);
		}
	}
	
	private void transPay(){
		Map<Integer, BankInfo> bankmap = (Map<Integer, BankInfo>)DataCacheManager.get(Constants.CACHE_KEY_BANKINFO);
		String bankCode = "";
		for(BankInfo o : bankmap.values()){
			if(o.getBankName().equals(ordlog.getBankName())){
				bankCode = o.getBankCode();
			}
		}
		//发送支付请求
		TransOrder order = new TransOrder();
		order.setMerCode(merInfo.getMerCode());
		order.setOrdId(ordlog.getOrdId());
		order.setUnMerId(merInfo.getUnMerId());
		order.setSubmitTime(DateOper.date2String(ordlog.getCreateTime(), "yyyyMMddHHmmss"));
		order.setTotalItem("1");
		order.setTotalAmt(GmsUtil.amt2str(String.valueOf(ordlog.getOrdAmt().doubleValue())));
		order.setBankCode(bankCode);
		order.setAcctName(ordlog.getAcctName());
		order.setAcctNo(SecretUtil.getDecryptedString(ordlog.getAcctNo()));
		order.setBankName(ordlog.getBankName());
		order.setAmount(GmsUtil.amt2str(String.valueOf(ordlog.getOrdAmt().doubleValue())));
		order.setCustUserId(merInfo.getMerCode()+ordlog.getUserCode());
		TransProcess.payReq(order);
		
		// 更新订单状态
		// 交易处理成功，订单状态更新成功
		if (order.getRetCode() == null) {
			log.info("查询交易结果异常, ordId=" + order.getOrdId());
			return;
		}
		if (order.getRetCode().equals(TransRetCode.RET_CODE_SUCCESS)) {
			if (order.getRetCodeDetail().equals(TransRetCode.RET_CODE_SUCCESS)) {
				ordlog.setOrdState(Constants.ORDER_LOG_STATE_S);
			} else {
				ordlog.setOrdState(Constants.ORDER_LOG_STATE_F);
			}
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCodeDetail());
			ordlog.setBankRespMsg("errMsg=" + order.getErrMsgDetail()
					+ "; costTime=" + order.getCostTime());
		}
		// 交易处理中，需要轮询订单进行重发
		else if (order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2000)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2007)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2001)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2003)
				|| order.getRetCode().equals(TransRetCode.RET_CODE_FAIL_2005)) {
			ordlog.setOrdState(Constants.ORDER_LOG_STATE_I);
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCode());
			ordlog.setBankRespMsg("errMsg=" + order.getErrMsg() + "; costTime="
					+ order.getCostTime());
		}
		// 交易处理失败
		else {
			ordlog.setOrdState(Constants.ORDER_LOG_STATE_F);
			ordlog.setLiqState(Constants.ORDER_LOG_LIQ_STATE_N);
			ordlog.setBankRespCode(order.getRetCode());
			ordlog.setBankRespMsg("errMsg=" + order.getErrMsg() + "; costTime="
					+ order.getCostTime());
		}
//		ordlog.setBankDate(CPublic.getDateAndTime());
		ordlog.setQryCnt(ordlog.getQryCnt() + 1);

		OrderLogDao dao = (OrderLogDao) SpringInfo.getBean("orderLogDao");
		dao.update(ordlog);	
		
		try{
			if(ordlog.getOrdState().equals(Constants.ORDER_LOG_STATE_F)){
				//更新商品库存
				String hql = "from OrderItem o, Goods g where o.itemName=g.goodsName and ordId=?";
				OrderItemDao itemdao = (OrderItemDao)SpringInfo.getBean("orderItemDao");
				List list = itemdao.findTbyHql(hql, new Object[]{ordlog.getId()});
				List params = new ArrayList();
				OrderItem item = new OrderItem();
				Goods goods = new Goods();
				for(Object o : list){
					item = (OrderItem)((Object[])o)[0];
					goods = (Goods)((Object[])o)[1];
					params = new ArrayList();
					params.add("");
					params.add("add");
					params.add(item.getItemNum());
					params.add(goods.getId());
					itemdao.callProcedure("{?=Call edit_goods_store(?,?,?)}", params);
				}
			}
		}catch(Exception e){
			log.error("更新商品库存异常"+e);
		}
	}

	public OrderLog getOrdlog() {
		return ordlog;
	}

	public void setOrdlog(OrderLog ordlog) {
		this.ordlog = ordlog;
	}

	public MerInfo getMerInfo() {
		return merInfo;
	}

	public void setMerInfo(MerInfo merInfo) {
		this.merInfo = merInfo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
