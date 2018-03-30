package com.yrtech.wx.capp.framework.core.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.portal.dao.impl.AuthInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.BankInfoDao;
import com.yrtech.wx.capp.portal.dao.impl.GoodsCatDao;
import com.yrtech.wx.capp.portal.dao.impl.LogisticsCorpDao;
import com.yrtech.wx.capp.portal.dao.impl.ProvCityDao;
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.model.BankInfo;
import com.yrtech.wx.capp.portal.model.GoodsCat;
import com.yrtech.wx.capp.portal.model.LogisticsCorp;
import com.yrtech.wx.capp.portal.model.ProvCity;
import com.yrtech.wx.capp.portal.util.Constants;



public class ProductInit {/*

	/**
	 * 初始化银行信息，放到缓存中
	 */
	public static void initBankInfo() {
		List<BankInfo> list = new ArrayList<BankInfo>();
		Map<Integer, BankInfo> map = new HashMap<Integer, BankInfo>();
		
		BankInfoDao dao = (BankInfoDao)SpringInfo.getBean("bankInfoDao");
		list = dao.findAll();
		for(BankInfo obj : list){
			map.put(obj.getId(), obj);
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_BANKINFO, map);
    }
	
	/**
	 * 初始化权限信息，放到缓存中
	 */
	public static void initOperAuthInfo(){
		List<AuthInfo> list = new ArrayList<AuthInfo>();
		Map<Integer, AuthInfo> map = new TreeMap<Integer, AuthInfo>();
		
		AuthInfoDao dao = (AuthInfoDao)SpringInfo.getBean("authInfoDao");
		String hql = "from AuthInfo where state=? and isDefault=? and isToMer=? and isDisplay=? order by authLevel, porder ";
		list = dao.findTbyHql(hql, new Object[]{
				Constants.STATE_FLAG_N, Constants.YES_OR_NO_FLAG_Y, 
				Constants.YES_OR_NO_FLAG_Y, Constants.YES_OR_NO_FLAG_Y
		});
		for(AuthInfo obj : list){
			map.put(obj.getId(), obj);
		}
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_AUTHINFO, map);
	}
	
	/**
	 * 初始化商品分类信息，放到缓存中
	 */
	public static void initGoodsCat(){
		List<GoodsCat> list = new ArrayList<GoodsCat>();
		Map<Integer, GoodsCat> map = new TreeMap<Integer, GoodsCat>();
		
		GoodsCatDao dao = (GoodsCatDao)SpringInfo.getBean("goodsCatDao");
		String hql = "from GoodsCat where state='N' order by merId, porder";
		list = dao.findTbyHql(hql);
		for(GoodsCat obj : list){
			map.put(obj.getId(), obj);
		}
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_GOODSCAT, map);
	}
	
	/**
	 * 初始化省份城市信息，放到缓存中
	 */
	public static void initProvCity(){
		List<ProvCity> list = new ArrayList<ProvCity>();
		Map<String, ProvCity> map = new TreeMap<String, ProvCity>();
		
		ProvCityDao dao = (ProvCityDao)SpringInfo.getBean("provCityDao");
		list = dao.findAll();
		for(ProvCity obj : list){
			map.put(obj.getAreaId(), obj);
		}
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_PROVCITY, map);
	}
	
	/**
	 * 初始化快递公司信息，放到缓存中
	 */
	public static void initLogisticsCorp() {
		List<LogisticsCorp> list = new ArrayList<LogisticsCorp>();
		Map<Integer, LogisticsCorp> map = new HashMap<Integer, LogisticsCorp>();
		
		LogisticsCorpDao dao = (LogisticsCorpDao)SpringInfo.getBean("logisticsCorpDao");
		String hql = "from LogisticsCorp where state='N'"; 
		list = dao.findTbyHql(hql);
		for(LogisticsCorp obj : list){
			map.put(obj.getId(), obj);
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_LOGTCORP, map);
    }
	
	/*
	*//**
	 * 初始化机构角色信息
	 *//*
	public static void initDeptRole() {
		List<DeptRole> list = new ArrayList<DeptRole>();
		Map<Integer, DeptRole> map = new HashMap<Integer, DeptRole>();
		
		DeptRoleDao dao = (DeptRoleDao)SpringInfo.getBean("deptRoleDao");
		list = dao.findAll();
		for(DeptRole obj : list){
			map.put(obj.getDeptId(), obj);
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_DEPTROLE, map);
    }
	
	*//**
	 * 初始化机构级别信息
	 *//*
	public static void initDeptLevel() {
		List<DeptLevel> list = new ArrayList<DeptLevel>();
		Map<String, DeptLevel> map = new HashMap<String, DeptLevel>();
		
		DeptLevelDao dao = (DeptLevelDao)SpringInfo.getBean("deptLevelDao");
		list = dao.findAll();
		for(DeptLevel obj : list){
			map.put(obj.getLevelCode(), obj);
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_DEPTLEVEL, map);
    }
	
	*//**
	 * 初始化地区信息
	 *//*
	public static void initAreaInfo() {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		Map<String, String> map = new HashMap<String, String>();
		
		AreaInfoDao dao = (AreaInfoDao)SpringInfo.getBean("areaInfoDao");
		list = dao.findAll();
		for(AreaInfo obj : list){
			map.put(obj.getAreaCode(), obj.getAreaName());
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_AREAINFO, map);
    }
	
	*//**
	 * 初始化短信模板信息
	 *//*
	public static void initSmsTemplate() {
		List<SmsTemplate> list = new ArrayList<SmsTemplate>();
		Map<Integer, SmsTemplate> map = new HashMap<Integer, SmsTemplate>();
		
		SmsTemplateDao dao = (SmsTemplateDao)SpringInfo.getBean("smsTemplateDao");
		list = dao.findAll();
		for(SmsTemplate obj : list){
			map.put(obj.getId(), obj);
		}
		
		// 放入公共缓存
		DataCacheManager.put(Constants.CACHE_KEY_SMSTEMPLATE, map);
    }
*/}
