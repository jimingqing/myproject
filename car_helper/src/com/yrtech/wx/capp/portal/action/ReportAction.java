package com.yrtech.wx.capp.portal.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.portal.service.OrderLogSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;

public class ReportAction extends BaseAction
{
	private Log log = LogFactory.getLog(this.getClass());

	private String curPage;
	
	private String startDate;
	
	private String merId;
	
	
    
	public String getMerId()
	{
		return merId;
	}

	public void setMerId( String merId )
	{
		this.merId = merId;
	}

	private String endDate;
	
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

	public String getCurPage()
	{
		return curPage;
	}

	public void setCurPage( String curPage )
	{
		this.curPage = curPage;
	}

	@Resource
	private OrderLogSrv orderLogSrv;

	public String merReport() throws Exception
	{
          
		Pager pager = new Pager();
		pager.setCurrentPage(Integer.valueOf(curPage == null ? "1"
				: curPage));
		MerOperInfoVo vo = (MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		
		
		
		RetInfo retInfo = orderLogSrv.merReport(vo.getMerId()+"",vo.getMerCode(),startDate,endDate);
		try
		{
                if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
                {
               	 Map<String, Object> retmap = new HashMap<String, Object>();
					retmap.put("datalist", retInfo.getList());
					retmap.put("pager", pager);
					// retmap.put("totalAmt", "0");
					resultMap.put(Constants.JSON_RETCODE,
							Constants.RET_CODE_SUCCESS);
               	 resultMap.put(Constants.JSON_RETMSG, "查询订单成功");
               	 resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
               	 return SUCCESS;
                }
		}
		catch (Exception e)
		{
			e.printStackTrace();
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询订单异常");
			return SUCCESS;
		}
		return SUCCESS;
	}

	public String userReport()
	{
		try
		{
			  MerOperInfoVo vo = (MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
		       RetInfo retInfo =orderLogSrv.userReport(merId+"",vo.getMerCode(),startDate,endDate );
		       if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS))
		       {
		     	  resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
		     	  resultMap.put(Constants.JSON_RETMSG, "查询成功");
		     	  resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
		     	  log.info("查询用户报表成功");
		       }
			  return SUCCESS;
		  
		}
		catch (Exception e) {
//			e.printStackTrace();
			log.error("查询用户报表异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
	     	  resultMap.put(Constants.JSON_RETMSG, "查询异常");
	     	  return  SUCCESS;
		}
	}

}
