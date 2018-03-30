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
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.model.GrpAuthInfo;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class AuthInfoSrv {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private AuthInfoDao authInfoDao;
	@Resource
	private GrpAuthInfoDao grpAuthInfoDao;
	
	public RetInfo qryAuthInfoByGrpId(String grpId){
		RetInfo retInfo = new RetInfo();
		String hql = "select a from AuthInfo a, GrpAuthInfo g where a.id=g.authId and g.state='N' and a.state='N' and g.grpId='"+grpId+"'";
		List list = authInfoDao.findTbyHql(hql);
		log.info("查询组权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询组权限信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo qryGrpAuthInfo(){
		RetInfo retInfo = new RetInfo();
		List list = grpAuthInfoDao.findAll();
		log.info("查询组权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询组权限信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo editGrpAuthInfo(String grpId, String authIds){
		RetInfo retInfo = new RetInfo();
		/*Integer[] arr = new Integer[authIds.length];
		for(int i=0;i<authIds.length;i++){
			arr[i] = Integer.valueOf(authIds[i]);
		}*/
		if(StringUtils.isEmpty(authIds)){
			String hql = "update GrpAuthInfo set state='C' where grpId='"+grpId+"'";
			grpAuthInfoDao.executeDML(hql);
		}else{
			String hql = "update GrpAuthInfo set state='N' where grpId='"+grpId+"' and authId in ("+authIds+")";
			grpAuthInfoDao.executeDML(hql);
			hql = "update GrpAuthInfo set state='C' where grpId='"+grpId+"' and authId not in ("+authIds+")";
			grpAuthInfoDao.executeDML(hql);
		}
		
		log.info("修改组权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改组权限信息成功");
		return retInfo;
	}
	
	public RetInfo qryAuthInfoById(String authId){
		RetInfo retInfo = new RetInfo();
		AuthInfo authInfo = authInfoDao.find(Integer.valueOf(authId));
		log.info("查询权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询权限信息成功");
		retInfo.setObject(authInfo);
		return retInfo;
	}
	
	public RetInfo deleteAuthInfo(String authId){
		RetInfo retInfo = new RetInfo();
		String hql = "from AuthInfo where parentId=?";
		List list = authInfoDao.findTbyHql(hql, new Object[]{Integer.valueOf(authId)});
		if(list.size()>0){
			log.info("删除权限有子权限，无法删除");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("删除权限有子权限，无法删除");
			return retInfo;
		}
		
		authInfoDao.delete(Integer.valueOf(authId));
		
		hql = "delete from GrpAuthInfo where authId="+authId;
		grpAuthInfoDao.executeDML(hql);
		log.info("删除权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("删除权限信息成功");
		return retInfo;
	}
	
	public RetInfo qryAuthInfo(String authDesc, String state, String authLevel){
		RetInfo retInfo = new RetInfo();
		String hql = "from AuthInfo where 1=1 ";
		if(!StringUtils.isEmpty(state) && !Constants.SELECT_ALL.equals(state.toUpperCase())){
			hql += "and state='"+state+"' ";
		}
		if(!StringUtils.isEmpty(authLevel) && !Constants.SELECT_ALL.equals(authLevel.toUpperCase())){
			hql += "and authLevel="+authLevel+" ";
		}
		if(!StringUtils.isEmpty(authDesc)){
			hql += "and authDesc like '%"+authDesc+"%' ";
		}
		hql += "order by parentId, authLevel, porder";
		List<AuthInfo> list = authInfoDao.findTbyHql(hql);
		log.info("查询权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询权限信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	/**
	 * 查询权限信息
	 * @param level
	 * @return
	 */
	public RetInfo qryAuthInfo(String level){
		RetInfo retInfo = new RetInfo();
		String hql = "from AuthInfo where authLevel=?";
		List<AuthInfo> list = authInfoDao.findTbyHql(hql, new Object[]{Integer.valueOf(level)});
		log.info("查询权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询权限信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	/**
	 * 新增权限信息
	 */
	public RetInfo addAuthInfo(AuthInfo authInfo){
		RetInfo retInfo = new RetInfo();
		authInfoDao.save(authInfo);
		
		List<GrpAuthInfo> list = new ArrayList<GrpAuthInfo>();
		GrpAuthInfo gai = new GrpAuthInfo();
		gai.setGrpId("00");
		gai.setAuthId(authInfo.getId());
		gai.setState("C");
		list.add(gai);
		
		gai = new GrpAuthInfo();
		gai.setGrpId("01");
		gai.setAuthId(authInfo.getId());
		gai.setState("C");
		list.add(gai);
		
		gai = new GrpAuthInfo();
		gai.setGrpId("02");
		gai.setAuthId(authInfo.getId());
		gai.setState("C");
		list.add(gai);
		
		gai = new GrpAuthInfo();
		gai.setGrpId("03");
		gai.setAuthId(authInfo.getId());
		gai.setState("C");
		list.add(gai);
		grpAuthInfoDao.batchSave(list);
		
		log.info("新增权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("新增权限信息成功");
		return retInfo;
	}
	
	/**
	 * 修改权限信息
	 */
	public RetInfo editAuthInfo(AuthInfo authInfo) throws Exception{
		RetInfo retInfo = new RetInfo();
		AuthInfo ai = authInfoDao.find(authInfo.getId());
		if(ai==null){
			log.info("查询权限信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询权限信息失败");
			return retInfo;
		}
		PropertyUtils.copyProperties(ai, authInfo);
		authInfoDao.save(ai);
		
		log.info("修改权限信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改权限信息成功");
		return retInfo;
	}
}
