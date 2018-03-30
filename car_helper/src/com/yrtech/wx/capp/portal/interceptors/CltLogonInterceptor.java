package com.yrtech.wx.capp.portal.interceptors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.vo.UserInfoVo;

public class CltLogonInterceptor extends AbstractInterceptor {
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求的Action名
		String name = invocation.getInvocationContext().getName();
		
		if (name.equals("userLogin") || name.equals("regist") || name.equals("listDeptInfo")) {
			// 如果用户想登录，则使之通过
			return invocation.invoke();
		} else {
			// 取得Session。
			ActionContext ac = invocation.getInvocationContext();
			Map session = (Map) ac.get(ServletActionContext.SESSION);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "用户未登录");
			String outStr = JSONArray.fromObject(resultMap).toString();
			outStr = outStr.substring(1, outStr.length()-1);
			if (session == null) {
				writeJson(outStr);
				return null;
			} else {
				UserInfoVo user = (UserInfoVo) session.get(Constants.USER_INFOVO);
				if (user == null) {
					// Session不为空，但Session中没有用户信息，
					// 则让用户登陆
					writeJson(outStr);
					return null;
				} else {
					// 用户已经登陆，放行~
					return invocation.invoke();
				}
			}
		}
	}
	
	private void writeJson(String json){
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);//将封装后的callback(json)回调函数返回到页面
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
