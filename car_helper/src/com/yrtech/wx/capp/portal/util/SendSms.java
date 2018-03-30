package com.yrtech.wx.capp.portal.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yrtech.wx.capp.framework.core.security.Base64;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.portal.model.SmsLog;

public class SendSms {
	
	//错误次数
	private int errhttp = 0;
	
	private static Logger log = Logger.getLogger(SendSms.class);
	
	private static String url = Config.getProperty("sms_chanel.url");
	private static String account = Config.getProperty("sms_chanel.account");
	private static String password = Config.getProperty("sms_chanel.password");
	

	public SmsLog send(SmsLog smsLog) {
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);

			// client.getParams().setContentCharset("GBK");
			client.getParams().setContentCharset("UTF-8");
			method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

			NameValuePair[] data = {// 提交短信
				new NameValuePair("account", new String(Base64.decode(SecretUtil.getDecryptedString(account)))),
				new NameValuePair("password", new String(Base64.decode(SecretUtil.getDecryptedString(password)))),
				new NameValuePair("mobile", smsLog.getPhoneNo()),
				new NameValuePair("content", smsLog.getContent()) };

			method.setRequestBody(data);

			client.executeMethod(method);

			String submitResult = method.getResponseBodyAsString();
			log.info(submitResult);
			Document doc = DocumentHelper.parseText(submitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			if (code.equals("2")) {
				log.info("短信发送成功，phoneNo="+smsLog.getPhoneNo());
				smsLog.setState(Constants.SMS_LOG_STATE_SUCC);
				smsLog.setRecv(submitResult);
			}
			
		} catch (HttpException e) {
			errhttp++;
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (DocumentException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
		return smsLog;
	}

	public int getErrhttp() {
		return errhttp;
	}

	public void setErrhttp(int errhttp) {
		this.errhttp = errhttp;
	}

	public static void main(String[] args) throws Exception {
	}
}