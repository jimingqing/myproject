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

import com.yrtech.wx.capp.framework.core.data.EasyTree;
import com.yrtech.wx.capp.framework.core.web.ProductInit;
import com.yrtech.wx.capp.portal.model.AuthInfo;
import com.yrtech.wx.capp.portal.service.AuthInfoSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.AuthInfoVo;

public class AuthAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String authId;
	private String authLevel;
	private String parentId;
	private String authDesc;
	private String porder;
	private String state;
	private String isDefault;
	private String isToMer; 
	private String isDisplay;
	private String action;
	
	private String operId;
	private String grpId;
	
	@Resource
	private AuthInfoSrv authInfoSrv;
	
	public String qryAuthInfoByGrpId(){
		if(StringUtils.isEmpty(grpId)){
			log.info("组权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "组权限id错误");
			return SUCCESS;
		}
		
		try {
			RetInfo retInfo = authInfoSrv.qryAuthInfoByGrpId(grpId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				List<AuthInfo> list = retInfo.getList();
				EasyTree tree = new EasyTree();
				tree.setId("-1");
				tree.setText("系统菜单");
				
				EasyTree tmp = new EasyTree();
				List<EasyTree> onetree = new ArrayList<EasyTree>();
				List<AuthInfo> twoAH = new ArrayList<AuthInfo>();
				List<AuthInfo> threeAH = new ArrayList<AuthInfo>();
				for(AuthInfo o : list){
					if(o.getAuthLevel()==1){
						tmp = new EasyTree();
						tmp.setId(o.getId().toString());
						tmp.setText(o.getAuthDesc());
						onetree.add(tmp);
					}
					else if(o.getAuthLevel()==2){
						twoAH.add(o);
					}
					else{
						threeAH.add(o);
					}
				}
				tree.setChildren(onetree);
				for(EasyTree t:tree.getChildren()){
					for(AuthInfo o : twoAH){
						if(o.getParentId()*1==Integer.valueOf(t.getId())*1){
							if(t.getChildren()==null){
								t.setChildren(new ArrayList<EasyTree>());
							}else{
								tmp = new EasyTree();
								tmp.setId(o.getId().toString());
								tmp.setText(o.getAuthDesc());
								t.getChildren().add(tmp);
							}
						}
					}
				}
				
				List<EasyTree> twotree = new ArrayList<EasyTree>();
				for(EasyTree t1:tree.getChildren()){
					for(EasyTree t2:t1.getChildren()){
						for(AuthInfo o : threeAH){
							if(o.getParentId()*1==Integer.valueOf(t2.getId())*1){
								if(t2.getChildren()==null){
									t2.setChildren(new ArrayList<EasyTree>());
								}else{
									tmp = new EasyTree();
									tmp.setId(o.getId().toString());
									tmp.setText(o.getAuthDesc());
									t2.getChildren().add(tmp);
								}
							}
						}
						
					}
				}
				
				
				log.info("查询组权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询组权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, tree);
			}else{
				log.info("查询组权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询组权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询组权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询组权限信息异常");
		}
		return SUCCESS;
	}
	
	public String qryGrpAuthInfo(){
		try {
			RetInfo retInfo = authInfoSrv.qryGrpAuthInfo();
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询组权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询组权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询组权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询组权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询组权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询组权限信息异常");
		}
		return SUCCESS;
	}
	
	public String editGrpAuthInfo(){
		if(StringUtils.isEmpty(grpId)){
			log.info("组权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "组权限id错误");
			return SUCCESS;
		}
		
		try {
			RetInfo retInfo = authInfoSrv.editGrpAuthInfo(grpId, authId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改组权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改组权限信息成功");
				
			}else{
				log.info("修改组权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改组权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("修改组权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改组权限信息异常");
		}
		return SUCCESS;
	}
	
	public String editAuthInfo(){
		if(StringUtils.isEmpty(authId)){
			log.info("权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(authLevel) || "123".indexOf(authLevel)==-1){
			log.info("权限级别错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限级别错误");
			return SUCCESS;
		}
		if(!authLevel.equals(Constants.AUTH_LEVEL_1) && StringUtils.isEmpty(parentId)){
			log.info("父权限错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "父权限错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(authDesc)){
			log.info("权限描述错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限描述错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(porder)){
			log.info("顺序编号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "顺序编号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(state)){
			log.info("状态标志错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "状态标志错误");
			return SUCCESS;
		}
		
		if(StringUtils.isEmpty(isDefault)){
			log.info("是否为默认对操作员开放错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否为默认对操作员开放错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(isToMer)){
			log.info("是否对商户开放错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否对商户开放错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(isDisplay)){
			log.info("是否作为功能显示在控台错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否作为功能显示在控台错误");
			return SUCCESS;
		}
		
		try {
			AuthInfo auth = new AuthInfo();
			auth.setId(Integer.valueOf(authId));
			auth.setAuthLevel(Integer.valueOf(authLevel));
			if(authLevel.equals(Constants.AUTH_LEVEL_1)){
				auth.setParentId(Constants.AUTH_LEVEL_PARENTID);
			}else{
				auth.setParentId(Integer.valueOf(parentId));
			}
			auth.setHaveSon(Constants.YES_OR_NO_FLAG_N);
			auth.setAuthDesc(authDesc);
			auth.setPorder(Integer.valueOf(porder));
			auth.setState(state.toUpperCase());
			auth.setIsDefault(isDefault.toUpperCase());
			auth.setIsToMer(isToMer.toUpperCase());
			auth.setIsDisplay(isDisplay.toUpperCase());
			auth.setAction(action);
			RetInfo retInfo = authInfoSrv.editAuthInfo(auth);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改权限信息成功");
				
				ProductInit.initOperAuthInfo();
			}else{
				log.info("修改权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("修改权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改权限信息异常");
		}
		return SUCCESS;
	}
	
	public String qryAuthInfoDetail(){
		if(StringUtils.isEmpty(authId)){
			log.info("权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = authInfoSrv.qryAuthInfoById(authId);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getObject());
			}else{
				log.info("查询权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询权限信息异常");
		}
		return SUCCESS;
	}
	
	public String removeAuth(){
		if(StringUtils.isEmpty(authId)){
			log.info("权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = authInfoSrv.deleteAuthInfo(authId);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("删除权限信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "删除权限信息成功");
			}else{
				log.info("删除权限信息失败");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "删除权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("删除权限信息异常", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "删除权限信息异常");
		}
		return SUCCESS;
	}
	
	public String qryAuth(){
		try {
			RetInfo retInfo = authInfoSrv.qryAuthInfo(authDesc, state, authLevel);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				Map<Integer, AuthInfo> map = new HashMap<Integer, AuthInfo>();
				RetInfo retInfo1 = authInfoSrv.qryAuthInfo("", "", "");
				for(AuthInfo o:(List<AuthInfo>)retInfo1.getList()){
					map.put(o.getId(), o);
				}
				List<AuthInfoVo> list = new ArrayList<AuthInfoVo>();
				AuthInfoVo vo = new AuthInfoVo();
				for(AuthInfo o:(List<AuthInfo>)retInfo.getList()){
					vo = new AuthInfoVo();
					PropertyUtils.copyProperties(vo, o);
					if(o.getParentId()>-1){
						vo.setParentName(map.get(o.getParentId()).getAuthDesc());
					}else{
						vo.setParentName("");
					}
					list.add(vo);
				}
				
				log.info("查询权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, list);
			}else{
				log.info("查询权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询权限信息异常");
		}
		return SUCCESS;
	}
	
	/*public String qryOperAuth(){
		if(StringUtils.isEmpty(operId)){
			log.info("操作员id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "操作员id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(parentId)){
			log.info("父权限id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "父权限id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(authLevel)){
			log.info("权限级别错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限级别错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = authInfoSrv.qryOperAuthInfo(authLevel);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询权限信息异常");
		}
		return SUCCESS;
	}*/
	
	/**
	 * 查询上级权限信息
	 * @return
	 */
	public String qryParentAuth(){
		if(StringUtils.isEmpty(authLevel) || "123".indexOf(authLevel)==-1){
			log.info("权限级别错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限级别错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = authInfoSrv.qryAuthInfo(authLevel);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询权限信息异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加权限信息
	 * @return
	 */
	public String addAuthInfo(){
		if(StringUtils.isEmpty(authLevel) || "123".indexOf(authLevel)==-1){
			log.info("权限级别错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限级别错误");
			return SUCCESS;
		}
		if(!authLevel.equals(Constants.AUTH_LEVEL_1) && StringUtils.isEmpty(parentId)){
			log.info("父权限错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "父权限错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(authDesc)){
			log.info("权限描述错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "权限描述错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(porder)){
			log.info("顺序编号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "顺序编号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(state)){
			log.info("状态标志错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "状态标志错误");
			return SUCCESS;
		}
		
		if(StringUtils.isEmpty(isDefault)){
			log.info("是否为默认对操作员开放错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否为默认对操作员开放错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(isToMer)){
			log.info("是否对商户开放错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否对商户开放错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(isDisplay)){
			log.info("是否作为功能显示在控台错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "是否作为功能显示在控台错误");
			return SUCCESS;
		}
		
		try {
			AuthInfo auth = new AuthInfo();
			auth.setAuthLevel(Integer.valueOf(authLevel));
			if(authLevel.equals(Constants.AUTH_LEVEL_1)){
				auth.setParentId(Constants.AUTH_LEVEL_PARENTID);
			}else{
				auth.setParentId(Integer.valueOf(parentId));
			}
			auth.setHaveSon(Constants.YES_OR_NO_FLAG_N);
			auth.setAuthDesc(authDesc);
			auth.setPorder(Integer.valueOf(porder));
			auth.setState(state.toUpperCase());
			auth.setIsDefault(isDefault.toUpperCase());
			auth.setIsToMer(isToMer.toUpperCase());
			auth.setIsDisplay(isDisplay.toUpperCase());
			auth.setAction(action);
			RetInfo retInfo = authInfoSrv.addAuthInfo(auth);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("添加权限信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "添加权限信息成功");
				
				ProductInit.initOperAuthInfo();
			}else{
				log.info("添加权限信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "添加权限信息失败");
			}
			
		} catch (Exception e) {
			log.error("添加权限信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加权限信息异常");
		}
		return SUCCESS;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public String getPorder() {
		return porder;
	}

	public void setPorder(String porder) {
		this.porder = porder;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsToMer() {
		return isToMer;
	}

	public void setIsToMer(String isToMer) {
		this.isToMer = isToMer;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	
}