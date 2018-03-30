package com.yrtech.wx.capp.portal.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.cache.DataCacheManager;
import com.yrtech.wx.capp.portal.model.ProvCity;
import com.yrtech.wx.capp.portal.service.ProvCitySrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.vo.ProvCityVo;

public class ProvCityAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());

	private String levelCode;
	private String pid;
	
	@Resource
	private ProvCitySrv provCitySrv;
	
	/**
	 * 查询省市区县信息接口
	 * @return
	 */
	public String qryProvCityInfo(){
		if(StringUtils.isEmpty(levelCode)){
			log.info("省市级别错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "省市级别错误");
			return SUCCESS;
		}
		if((levelCode.equals(Constants.PROV_CITY_LEVEL_2) || levelCode.equals(Constants.PROV_CITY_LEVEL_2))
				&& StringUtils.isEmpty(levelCode)){
			log.info("未指定上级id");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "未指定上级id");
			return SUCCESS;
		}
		
		try {
			Map<Integer, ProvCity> map = (Map<Integer, ProvCity>)DataCacheManager.get(Constants.CACHE_KEY_PROVCITY);	
			List<ProvCityVo> list = new ArrayList<ProvCityVo>();
			ProvCityVo vo = new ProvCityVo();
			for(ProvCity pc : map.values()){
				if(pc.getLevelCode().equals(levelCode)){
					if(!StringUtils.isEmpty(pid)){
						if(pc.getPid().equals(pid)){
							vo = new ProvCityVo();
							PropertyUtils.copyProperties(vo, pc);
							list.add(vo);
						}
					}else{
						vo = new ProvCityVo();
						PropertyUtils.copyProperties(vo, pc);
						list.add(vo);
					}
				}
			}
		
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
			resultMap.put(Constants.JSON_RETMSG, "查询省市信息成功");
			resultMap.put(Constants.JSON_CONTENT, list);
		} catch (Exception e) {
			log.error("添加权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加权限信息异常");
		}
		return SUCCESS;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
