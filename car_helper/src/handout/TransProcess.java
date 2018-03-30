package handout;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.EasyLink.security.Crypt;
import com.yrtech.wx.capp.framework.core.security.SecretUtil;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;

public class TransProcess {
	private static Log log = LogFactory.getLog(TransProcess.class);
	
	//测试机公网地址
	private static final String SERVER_URL = Config.getProperty("unpay_server_url");
	
	private static Map<String, String> cerFileMap;
	private static Map<String, String> pfxFileMap;
	private static Map<String, String> pfxPassMap;
	private static Map<String, String> userNameMap;
	private static Map<String, String> userPassMap;
	
	/**
	 * 验证签名信息
	 */
	private static boolean verifySign(String strXML, TransOrder order) {
		//签名
		Crypt  crypt = new Crypt();
		String cerPath = Config.getProperty("mer_cer_pfx_file_dir")+order.getMerCode()+"/"+cerFileMap.get(order.getMerCode());
		int iStart = strXML.indexOf("<SIGNED_MSG>");
		if (iStart != -1) {
			int end = strXML.indexOf("</SIGNED_MSG>");
			String signedMsg = strXML.substring(iStart+12, end);
			String strMsg = strXML.substring(0, iStart) + strXML.substring(end+13);
			log.debug(signedMsg);
			log.debug(strMsg);
			
			if (crypt.VerifyMsg(signedMsg, strMsg, cerPath)) {
				return true;
			}
			else {
				log.error("verify error ---- "+crypt.getLastErrMsg());
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 数据签名
	 * comment here
	 * @param strData
	 * @return
	 * @since gnete-ora 0.0.0.1
	 */
	private static String signMsg(String strData, TransOrder order) {
		String strRnt = "";
		//签名
		Crypt  crypt = new Crypt( );
		String pfxPath = Config.getProperty("mer_cer_pfx_file_dir")+order.getMerCode()+"/"+pfxFileMap.get(order.getMerCode());
		System.out.println(pfxPath);
		String strMsg = strData.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "");
		System.out.println("签名原文:"+strMsg);
		String pass = SecretUtil.getDecryptedString(pfxPassMap.get(order.getMerCode()));
		if (crypt.SignMsg(strMsg, pfxPath, pass)) {			
			String signedMsg = crypt.getLastSignMsg();
			strRnt = strData.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "<SIGNED_MSG>"+signedMsg+"</SIGNED_MSG>");
//			System.out.println("请求交易报文:"+strRnt);
		}
		else {
			log.error(crypt.getLastErrMsg());
			strRnt = strData;
		}
		return strRnt;
	}
	
	private static String getPayReqContent(TransOrder order) {
		String username = SecretUtil.getDecryptedString(userNameMap.get(order.getMerCode()));
		String pass = SecretUtil.getDecryptedString(userPassMap.get(order.getMerCode()));
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		sb.append("<GZELINK>");
		sb.append("<INFO>");
		sb.append("<TRX_CODE>100004</TRX_CODE>");
		sb.append("<VERSION>04</VERSION>");
		sb.append("<DATA_TYPE>2</DATA_TYPE>");
		sb.append("<LEVEL>0</LEVEL>");
		sb.append("<USER_NAME>"+username+"</USER_NAME>");
		sb.append("<USER_PASS>"+pass+"</USER_PASS>");
		sb.append("<REQ_SN>"+order.getOrdId()+"</REQ_SN>");
		sb.append("<SIGNED_MSG></SIGNED_MSG>");
		sb.append("</INFO>");
		sb.append("<BODY>");
		sb.append("<TRANS_SUM>");
		sb.append("<BUSINESS_CODE>14902</BUSINESS_CODE>");
		sb.append("<MERCHANT_ID>"+order.getUnMerId()+"</MERCHANT_ID>");
		sb.append("<SUBMIT_TIME>"+order.getSubmitTime()+"</SUBMIT_TIME>");
		sb.append("<TOTAL_ITEM>"+order.getTotalItem()+"</TOTAL_ITEM>");
		sb.append("<TOTAL_SUM>"+order.getTotalAmt()+"</TOTAL_SUM>");
		sb.append("</TRANS_SUM>");
		sb.append("<TRANS_DETAILS>");
		sb.append("<TRANS_DETAIL>");
		sb.append("<SN>0001</SN>");
		sb.append("<E_USER_CODE/>");
		sb.append("<BANK_CODE>"+order.getBankCode()+"</BANK_CODE>");
		sb.append("<ACCOUNT_TYPE>00</ACCOUNT_TYPE>");
		sb.append("<ACCOUNT_NO>"+order.getAcctNo()+"</ACCOUNT_NO>");
		sb.append("<ACCOUNT_NAME>"+order.getAcctName()+"</ACCOUNT_NAME>");
		sb.append("<PROVINCE>"+order.getProvince()+"</PROVINCE>");
//		sb.append("<PROVINCE />");
		sb.append("<CITY>"+order.getCity()+"</CITY>");
//		sb.append("<CITY />");
		sb.append("<BANK_NAME>"+order.getBankName()+"</BANK_NAME>");
		sb.append("<ACCOUNT_PROP>0</ACCOUNT_PROP>");
		sb.append("<AMOUNT>"+order.getAmount()+"</AMOUNT>");
		sb.append("<CURRENCY>CNY</CURRENCY>");
		sb.append("<PROTOCOL />");
		sb.append("<PROTOCOL_USERID />");
//		sb.append("<ID_TYPE />");
//		sb.append("<ID />");
		sb.append("<ID_TYPE>"+order.getIdType()+"</ID_TYPE>");
		sb.append("<ID>"+order.getId()+"</ID>");
		sb.append("<TEL />");
		sb.append("<CUST_USERID>"+order.getCustUserId()+"</CUST_USERID>");
		sb.append("<REMARK>货物款</REMARK>");
		sb.append("</TRANS_DETAIL>");
		sb.append("</TRANS_DETAILS>");
		sb.append("</BODY>");
		sb.append("</GZELINK>");
		return sb.toString();
	}
	
	private static String getQryReqContent(TransOrder order) {
		String username = SecretUtil.getDecryptedString(userNameMap.get(order.getMerCode()));
		String pass = SecretUtil.getDecryptedString(userPassMap.get(order.getMerCode()));
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		sb.append("<GZELINK>");
		sb.append("<INFO>");
		sb.append("<TRX_CODE>200001</TRX_CODE>");
		sb.append("<VERSION>04</VERSION>");
		sb.append("<DATA_TYPE>2</DATA_TYPE>");
		sb.append("<USER_NAME>"+username+"</USER_NAME>");
		sb.append("<USER_PASS>"+pass+"</USER_PASS>");
		sb.append("<REQ_SN>"+System.currentTimeMillis()+"</REQ_SN>");
		sb.append("<SIGNED_MSG></SIGNED_MSG>");
		sb.append("</INFO>");
		sb.append("<BODY>");
		sb.append("<QUERY_TRANS>");
		sb.append("<QUERY_SN>"+order.getOrdId()+"</QUERY_SN>");
		sb.append("</QUERY_TRANS>");
		sb.append("</BODY>");
		sb.append("</GZELINK>");
		return sb.toString();
	}
	
	/**
	 * 支付请求
	 */
	public static void payReq(TransOrder order) {
		//请求报文
		String strSendData = getPayReqContent(order);
		HttpClient httpClient = new HttpClient( );
		//url
		PostMethod postMethod = new PostMethod(SERVER_URL);
		//设置编码
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GBK");
		
		strSendData = signMsg(strSendData, order);
		
		postMethod.setRequestBody(strSendData);
		try {
			long start = System.currentTimeMillis();
			//执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
//			System.out.println("cost:"+(System.currentTimeMillis()-start));
			//失败
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed: " + postMethod.getStatusLine());
				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
				//处理内容
				String strResp = new String(responseBody, "GBK");
				log.error(strResp);			
				order.setRetCode("-1");
				order.setErrMsg(strResp);
			}
			else {
//				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
//				
				String strResp = new String(responseBody, "GBK");
				System.out.println("服务器返回:" + strResp);
				Digester digester = new Digester();
				// 不进行XML与相应的DTD的合法性验证
				digester.setValidating(false);
				digester.addObjectCreate("GZELINK", TransOrder.class);
				verifySign(strResp, order);
				//验签失败
				digester.addBeanPropertySetter("GZELINK/INFO/RET_CODE", "retCode");
				digester.addBeanPropertySetter("GZELINK/INFO/ERR_MSG", "errMsg");
				digester.addBeanPropertySetter("GZELINK/BODY/RET_DETAILS/RET_DETAIL/RET_CODE", "retCodeDetail");
				digester.addBeanPropertySetter("GZELINK/BODY/RET_DETAILS/RET_DETAIL/ERR_MSG", "errMsgDetail");
				
				InputStream is = null;
				try {
				    // responseInfo是服务响应的xml字符串
				    is = new ByteArrayInputStream(strResp.getBytes("GBK"));

				    TransOrder tmp = (TransOrder) digester.parse(is);
				    order.setRetCode(tmp.getRetCode());
				    order.setErrMsg(tmp.getErrMsg());
				    order.setRetCodeDetail(tmp.getRetCodeDetail());
				    order.setErrMsgDetail(tmp.getErrMsgDetail());
				} catch (Exception e) {
				    e.printStackTrace();
				} finally {
				    if (is != null) {
				        try {
				            is.close();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				    }
				}
			}
			order.setCostTime(String.valueOf(System.currentTimeMillis()-start));
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("Please check your provided http address!", e);
			e.printStackTrace( );
		} catch (IOException e) {
			//发生网络异常
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		} finally {
			//释放连接
			postMethod.releaseConnection();
		}
		
	}
	
	/**
	 * 查询请求
	 */
	public static void qryReq(TransOrder order) {
		//请求报文
		String strSendData = getQryReqContent(order);
		HttpClient httpClient = new HttpClient( );
		//url
		PostMethod postMethod = new PostMethod(SERVER_URL);
		//设置编码
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GBK");
		
		strSendData = signMsg(strSendData, order);
		
		postMethod.setRequestBody(strSendData);
		try {
			long start = System.currentTimeMillis();
			//执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
//			System.out.println("cost:"+(System.currentTimeMillis()-start));
			//失败
			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed: " + postMethod.getStatusLine());
				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
				//处理内容
				String strResp = new String(responseBody, "GBK");
				log.error(strResp);			
				order.setRetCode("-1");
				order.setErrMsg(strResp);
			}
			else {
//				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
//				
				String strResp = new String(responseBody, "GBK");
				System.out.println("服务器返回:" + strResp);
				Digester digester = new Digester();
				// 不进行XML与相应的DTD的合法性验证
				digester.setValidating(false);
				digester.addObjectCreate("GZELINK", TransOrder.class);
				verifySign(strResp, order);
				//验签失败
				digester.addBeanPropertySetter("GZELINK/INFO/RET_CODE", "retCode");
				digester.addBeanPropertySetter("GZELINK/INFO/ERR_MSG", "errMsg");
				digester.addBeanPropertySetter("GZELINK/BODY/RET_DETAILS/RET_DETAIL/RET_CODE", "retCodeDetail");
				digester.addBeanPropertySetter("GZELINK/BODY/RET_DETAILS/RET_DETAIL/ERR_MSG", "errMsgDetail");
				
				InputStream is = null;
				try {
				    // responseInfo是服务响应的xml字符串
				    is = new ByteArrayInputStream(strResp.getBytes("GBK"));

				    TransOrder tmp = (TransOrder) digester.parse(is);
				    order.setRetCode(tmp.getRetCode());
				    order.setErrMsg(tmp.getErrMsg());
				    order.setRetCodeDetail(tmp.getRetCodeDetail());
				    order.setErrMsgDetail(tmp.getErrMsgDetail());
				} catch (Exception e) {
				    e.printStackTrace();
				} finally {
				    if (is != null) {
				        try {
				            is.close();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				    }
				}
			}
			order.setCostTime(String.valueOf(System.currentTimeMillis()-start));
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("Please check your provided http address!", e);
			e.printStackTrace( );
		} catch (IOException e) {
			//发生网络异常
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		} finally {
			//释放连接
			postMethod.releaseConnection();
		}
		
	}
	
	/**
	 * 测试验证
	 * comment here
	 * @since gnete-ora 0.0.0.1
	 */
	/*private void TestValidReq( ) {
		String req = System.currentTimeMillis()+"";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strNow = sdf.format(new Date());
		String strSum = "2";
		//请求报文
		String strSendData = "<?xml version=\"1.0\" encoding=\"GBK\"?><GZELINK><INFO><TRX_CODE>200001</TRX_CODE><VERSION>04</VERSION><DATA_TYPE>2</DATA_TYPE><REQ_SN>20090416110841051</REQ_SN><USER_NAME>operator</USER_NAME><USER_PASS>1</USER_PASS></INFO><BODY><QUERY_TRANS><QUERY_SN>1234</QUERY_SN></QUERY_TRANS></BODY></GZELINK>";		
		HttpClient httpClient = new HttpClient( );
		//url
		PostMethod postMethod =
			new PostMethod(SERVER_URL);
		//设置编码
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"GBK");
		
		strSendData = this.signMsg(strSendData, order);

		log.info(strSendData);
		
		postMethod.setRequestBody(strSendData);
		try {
			long start = System.currentTimeMillis();
			//执行getMethod
			int statusCode = httpClient.executeMethod(postMethod);
//			System.out.println("cost:"+(System.currentTimeMillis()-start));
			//失败
			if (statusCode != HttpStatus.SC_OK) {
				log.error(
					"Method failed: " + postMethod.getStatusLine());
				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
				//处理内容
				String strResp = new String(responseBody, "GBK");
				log.error(strResp);		
				System.out.println(strResp);
			}
			else {
				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();
				
				String strResp = new String(responseBody, "GBK");
				log.info("服务器返回:" + strResp);
				System.out.println("验签正确，处理服务器返回的报文");
				//验签
				if (this.verifySign(strResp)) {
					log.info("验签正确，处理服务器返回的报文");
					System.out.println("验签正确，处理服务器返回的报文");
				}
			}
			System.out.println("cost:"+(System.currentTimeMillis()-start));
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("Please check your provided http address!", e);
			e.printStackTrace( );
		} catch (IOException e) {
			//发生网络异常
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		} finally {
			//释放连接
			postMethod.releaseConnection();
		}
		
	}*/
	
	public static void main(String[] args) {
		TransProcess tp = new TransProcess( );
		TransOrder order = new TransOrder();
		order.setOrdId(System.currentTimeMillis()+"");
		order.setUnMerId("001053110000001");
		order.setSubmitTime(DateOper.date2String(new Date(), "yyyyMMddHHmmss"));
		order.setTotalItem("1");
		order.setTotalAmt("1");
		order.setBankCode("105");
		order.setAcctName("张三");
		order.setAcctNo("60138270140042110021");
		order.setBankName("中国建设银行");
		order.setAmount("1");
		order.setCustUserId("88001_155");
//		tp.payReq(order);
		
		System.out.println("ret_code="+order.getRetCode());
		System.out.println("err_msg="+order.getErrMsg());
		
		String resp = "<?xml version=\"1.0\" encoding=\"GBK\"?><GZELINK><INFO><TRX_CODE>100004</TRX_CODE><VERSION>04</VERSION><DATA_TYPE>2</DATA_TYPE><REQ_SN>1401868473416</REQ_SN><RET_CODE>0000</RET_CODE><ERR_MSG>处理完成</ERR_MSG><SIGNED_MSG>705bc921d0e0eb2347568b03b110227951ea926e643f0bdc4995a69c41d3f12059e4d0ec746dd63675e3881f943ca71545d47a8e62a16f555089de49438d50a07ea030bfb27fa01d7b85596ed77a6c18a95c8cfd7d1f640e65ced8486736f72a1c8f2105179dcb001433c1ccb01a1c1a2609550f3905f3089fc14c6f3a5d526d</SIGNED_MSG></INFO><BODY><RET_DETAILS><RET_DETAIL><SN>0001</SN><ACCOUNT_NO>60138270140042110021</ACCOUNT_NO><ACCOUNT_NAME>张三</ACCOUNT_NAME><AMOUNT>1</AMOUNT><CUST_USERID></CUST_USERID><REMARK>保险理赔</REMARK><RET_CODE>0000</RET_CODE><ERR_MSG>交易成功</ERR_MSG></RET_DETAIL></RET_DETAILS></BODY></GZELINK>";
//		tp.verifySign(resp);
		
		System.out.println();
	}

	public static Map<String, String> getCerFileMap() {
		return cerFileMap;
	}

	public static void setCerFileMap(Map<String, String> cerFileMap) {
		TransProcess.cerFileMap = cerFileMap;
	}

	public static Map<String, String> getPfxFileMap() {
		return pfxFileMap;
	}

	public static void setPfxFileMap(Map<String, String> pfxFileMap) {
		TransProcess.pfxFileMap = pfxFileMap;
	}

	public static Map<String, String> getPfxPassMap() {
		return pfxPassMap;
	}

	public static void setPfxPassMap(Map<String, String> pfxPassMap) {
		TransProcess.pfxPassMap = pfxPassMap;
	}

	public static Map<String, String> getUserNameMap() {
		return userNameMap;
	}

	public static void setUserNameMap(Map<String, String> userNameMap) {
		TransProcess.userNameMap = userNameMap;
	}

	public static Map<String, String> getUserPassMap() {
		return userPassMap;
	}

	public static void setUserPassMap(Map<String, String> userPassMap) {
		TransProcess.userPassMap = userPassMap;
	}
	
	
}
