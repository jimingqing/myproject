package com.yrtech.wx.capp.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.portal.dao.impl.AuthInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.GrpAuthInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.model.GrpAuthInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class ReconHandleSrv {

private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private OrderLogDao orderLogDao;
	
	@Resource
	private AuthInfoDao authInfoDao;
	
	public RetInfo reconHandle(Integer merId, String startDate, String endDate,String grpId){
		RetInfo retInfo = new RetInfo();
		String hql = "select a from AuthInfo a, GrpAuthInfo g where a.id=g.authId and g.state='N' and a.state='N' and g.grpId='"+grpId+"'";
		List list = authInfoDao.findTbyHql(hql);
		log.info("查询组权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询组权限信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
}
