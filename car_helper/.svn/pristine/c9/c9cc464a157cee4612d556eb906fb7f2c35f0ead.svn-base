package com.yrtech.wx.capp.portal.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.vo.MerOperInfoVo;

public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private InputStream excelStream;

	protected static final Log log = LogFactory.getLog(BaseAction.class);
	
	public MerOperInfoVo getMerOperInfoVo(){
		return (MerOperInfoVo)session().getAttribute(Constants.USER_INFOVO);
	}
	
	public HttpServletRequest request() {
		return ServletActionContext.getRequest();
	}

	public HttpSession session() {
		return ServletActionContext.getRequest().getSession();
	}

	public ServletContext application() {
		return ServletActionContext.getServletContext();
	}

	public HttpServletResponse response() {
		return ServletActionContext.getResponse();
		
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	
}
