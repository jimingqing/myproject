package com.yrtech.wx.capp.framework.core.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpClientUtil {

	public static Logger log = Logger.getLogger(HttpClientUtil.class);

	private static HttpClientUtil client = null;
	
	private static int TIMEOUT = 30000;
	
	public static HttpClientUtil getInstance() {
		if(client == null) {
			client = new HttpClientUtil();
		}
		
		return client;
	}
	
	public static String requestGet(String url, String ...charset) throws Exception {
		String resp = "";
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
		log.info("请求url：" + url);
		GetMethod getMethod = new GetMethod(url);
		try {
			String chars = "";
			if(charset == null || charset.length == 0) {
				chars = "UTF-8";
			} else {
				chars = charset[0];
			}
			
			httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, chars);
			int statusCode = httpclient.executeMethod(getMethod);
			log.info("statusCode:"+statusCode);
			resp = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			getMethod.releaseConnection();
		}
		return resp;
	}
	
	public static String requestGet(String url) throws Exception {
		String resp = "";
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
		log.info("请求url：" + url);
		GetMethod getMethod = new GetMethod(url);
		try {
			int statusCode = httpclient.executeMethod(getMethod);
			log.info("statusCode:"+statusCode);
			resp = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			getMethod.releaseConnection();
		}
		return resp;
	}
	
	public static String requestGet(String url, int outTime) throws Exception {
		String resp = "";
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
		GetMethod getMethod = new GetMethod(url);
		try {
			int statusCode = httpclient.executeMethod(getMethod);
			log.info("url:" + url);
			resp = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			getMethod.releaseConnection();
		}
		return resp;
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值List
	 * @return 调用得到的字符串
	 * @throws Exception 
	 */
	public String httpClientPost(String url,List<NameValuePair[]> values,String ... charset) throws Exception{
		log.debug("post url: "+url);
		StringBuilder sb =new StringBuilder();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		String str1=null;
		if(charset==null||charset.length==0){
			str1="UTF-8";
		}else if("GBK".equals(charset[0].toUpperCase())){
			str1="GBK";
		}
		//将表单的值放入postMethod中
		for (NameValuePair[] value : values) {
			postMethod.addParameters(value);
		}
		
		try {
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, str1);
			//执行postMethod
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
			httpClient.executeMethod(postMethod);
			//以流的行式读入，避免中文乱码
			InputStream is = postMethod.getResponseBodyAsStream();
			BufferedReader dis=new BufferedReader(new InputStreamReader(is,str1));   
			 String str ="";                           
			 while((str =dis.readLine())!=null){
				 sb.append(str);
				 //sb.append("\r\n"); // 默认这里没有换行，而是让所有的字符出现在一行里面。如需要换行，请去掉前面的注释
			 }
		} catch (Exception e) {
			log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
			throw e;
		}finally{
			postMethod.releaseConnection();
		}
		return sb.toString();
	}
	
	
	/**
	 * 使用post方式调用  请求体内容设置为@param xml
	 * @param url 调用的URL
	 * @param xml 传递的XML字符串
	 * @param charset 字符编码
	 * @param type 解析类型  如text/html;text/xml;
	 * @param headType 0：不需要加入head
	 * @return 调用得到的字符串
	 * @throws Exception 
	 */
	public String httpClientPostWithXML(String url,String xml,String charset,String type,int headType) throws Exception{
		log.debug("post url: "+url);
		StringBuilder sb =new StringBuilder();
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		if(headType==1){  // 违章记录查询
			List<Header> headers = new ArrayList<Header>();  
			headers.add(new Header("SOAPAction", "http://www.ahjtxx.com/TrafficService/GetIllegalInfoOfCar"));  
			httpClient.getHostConfiguration().getParams().setParameter(  
					"http.default-headers", headers); 
		}
		
		if(headType==2){  // 查询是否有违章记录
			List<Header> headers = new ArrayList<Header>();  
			headers.add(new Header("SOAPAction", "http://www.ahjtxx.com/TrafficService/GetIllegalInfoOfCarNoCheck"));  
			httpClient.getHostConfiguration().getParams().setParameter(  
					"http.default-headers", headers); 
		}
		
		if(headType==3){  // 查询是否有违章记录
			List<Header> headers = new ArrayList<Header>();  
			headers.add(new Header("SOAPAction", "http://www.ahjtxx.com/TrafficService/GetBasicInfoOfDriver"));  
			httpClient.getHostConfiguration().getParams().setParameter(  
					"http.default-headers", headers); 
		}
	    
		// 设置请求内容
		try {
			RequestEntity request=new StringRequestEntity(xml, type, charset);
			postMethod.setRequestEntity(request);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
			//执行postMethod
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
			log.debug("**********"+postMethod.getRequestEntity().getContentLength());
			log.debug("**********"+postMethod.getRequestEntity().getContentType());
			httpClient.executeMethod(postMethod);
			//以流的行式读入，避免中文乱码
			InputStream is = postMethod.getResponseBodyAsStream();
			BufferedReader dis=new BufferedReader(new InputStreamReader(is,charset));   
			 String str ="";                           
			 while((str =dis.readLine())!=null){
				 sb.append(str);
				 //sb.append("\r\n"); // 默认这里没有换行，而是让所有的字符出现在一行里面。如需要换行，请去掉前面的注释
			 }
		} catch (Exception e) {
			log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
			throw e;
		}finally{
			postMethod.releaseConnection();
		}
		return sb.toString();
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值
	 * @return 调用得到的字符串
	 * @throws Exception 
	 */
	public String httpClientPost(String url,NameValuePair[] values,String ... charset) throws Exception{
		List<NameValuePair[]> list = new ArrayList<NameValuePair[]>();
		list.add(values);
		return httpClientPost(url, list,charset);
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值
	 * @return 调用得到的字符串
	 * @throws Exception 
	 */
	public static String post(String url, String[][] params, String ... charset) throws Exception{
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		StringBuilder sb =new StringBuilder();
		log.info("post url:" + url);
		String str1=null;
		if(charset==null||charset.length==0){
			str1="UTF-8";
		} else if("GBK".equals(charset[0].toUpperCase())){
			str1="GBK";
		} else {
			str1 = charset[0];
		}
		
		for(int i = 0; i < params.length; i++) {
			postMethod.addParameter(params[i][0], params[i][1]);
		}
		
		try {
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, str1);
			//执行postMethod
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
			httpClient.executeMethod(postMethod);
			//以流的行式读入，避免中文乱码
			InputStream is = postMethod.getResponseBodyAsStream();
			BufferedReader dis=new BufferedReader(new InputStreamReader(is,str1));   
			String str ="";                           
			while((str =dis.readLine())!=null){
				sb.append(str);
			}
		} catch (Exception e) {
			log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
			throw e;
		}finally{
			postMethod.releaseConnection();
		}
		return sb.toString();
	}

	public static String post(String url, String[][] params, int timeout, String ... charset) throws Exception{
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		StringBuilder sb =new StringBuilder();
		log.info("post url:" + url);
		String str1=null;
		if(charset==null||charset.length==0){
			str1="UTF-8";
		} else if("GBK".equals(charset[0].toUpperCase())){
			str1="GBK";
		} else {
			str1 = charset[0];
		}
		
		for(int i = 0; i < params.length; i++) {
			postMethod.addParameter(params[i][0], params[i][1]);
		}
		
		try {
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, str1);
			//执行postMethod
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			httpClient.executeMethod(postMethod);
			//以流的行式读入，避免中文乱码
			InputStream is = postMethod.getResponseBodyAsStream();
			BufferedReader dis=new BufferedReader(new InputStreamReader(is,str1));   
			String str ="";                           
			while((str =dis.readLine())!=null){
				sb.append(str);
			}
		} catch (Exception e) {
			log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
			throw e;
		}finally{
			postMethod.releaseConnection();
		}
		return sb.toString();
	}
	
	/**
	 * 使用get方式调用
	 * @param url调用的URL
	 * @return 调用得到的字符串
	 * @throws Exception 
	 */
	public String httpClientGet(String url,String ...  charset ) throws Exception{
		log.debug("get url: "+url);
		StringBuilder sb =new StringBuilder();
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		String str1=null;
		if(charset==null||charset.length==0){
			str1="UTF-8";
		}else if("GBK".equals(charset[0].toUpperCase())){
			str1="GBK";
		}
		try {
			// 执行getMethod
			httpClient.executeMethod(getMethod);
			log.debug("==httpClient httpClientGet response code:"+getMethod.getStatusCode());
			//以流的行式读入，避免中文乱码
			InputStream is = getMethod.getResponseBodyAsStream();
			BufferedReader dis=new BufferedReader(new InputStreamReader(is,str1));   
			 String str ="";                           
			 while((str =dis.readLine())!=null){
				 sb.append(str);
			 }
		} catch (Exception e) {
			log.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
			throw e;
		} finally {
			// 关闭连接
			getMethod.releaseConnection();
		}
		return sb.toString(); 
	}
	
	
	/**
	 * 将MAP转换成HTTP请求参数
	 * @param pairArr
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static NameValuePair[] praseParameterMap(Map map){
		
		NameValuePair[] nvps = new NameValuePair[map.size()];
		
		Set<String> keys = map.keySet();
		int i=0;
		for(String key:keys){
			nvps[i] = new NameValuePair();
			nvps[i].setName(key);
			nvps[i].setValue(String.valueOf(map.get(key)));
			i++;
		}
		              
		return nvps;
	}
	
	/**
	 * 将request中的参数值对封装到map里返回
	 * @param request
	 * @return
	 */
	public static Map<String, String> requestParamToMap(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		Map<String, String> ret = new HashMap<String, String>();
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			ret.put(key, params.get(key)[0]);
		}
		
		return ret;
	}
	
}
