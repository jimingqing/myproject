package com.yrtech.wx.capp.framework.core.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * UrlConnection通讯辅助类
 * 
 * @Package: com.yrtech.wx.capp.framework.core.net
 * @ClassName: CallbackUtils
 * @author wanghui
 * @date 2012-6-14 下午3:09:03
 * @version: V1.0
 */
public class CallbackUtils
{
    // 设置URL缓存时间为1小时
    static
    {
        System.setProperty("sun.net.inetaddr.ttl", "3600");
    }

    /**
     * 默认字符编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * POST请求方式
     */
    public static final String HTTP_METHOD_POST = "POST";

    /**
     * GET请求方式
     */
    public static final String HTTP_METHOD_GET = "GET";

    /**
     * 默认超时设置(60秒)
     */
    public static final int DEFAULT_TIMEOUT = 60000;

    /**
     * 默认提交方式
     */
    public static final String HTTP_METHOD_DEFAULT = "GET";

    public static final String HTTP_PREFIX = "http://";

    public static final String HTTPS_PREFIX = "https://";

    /**
     * 
     * 发送请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午1:23:01
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param method
     *            请求方法，如：GET/POST
     * @return 响应结果
     */
    public static String httpRequest(String url, String method)
    {
        return httpRequest(url, "", method, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午1:28:54
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param queryString
     *            请求参数串
     * @param method
     *            请求方法，如：GET/POST
     * @return 响应结果
     */
    public static String httpRequest(String url, String queryString, String method)
    {
        return httpRequest(url, queryString, method, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:19:22
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param params
     *            请求参数
     * @param method
     *            请求方法，如：GET/POST
     * @return 响应结果
     */
    public static String httpRequest(String url, Map<String, String> params, String method)
    {
        return httpRequest(url, params, method, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送POST请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:20:06
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param params
     *            请求参数
     * @return 响应结果
     */
    public static String httpPost(String url, Map<String, String> params)
    {
        return httpRequest(url, params, HTTP_METHOD_POST, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送POSTS请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:20:46
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param queryString
     *            请求参数
     * @return 响应结果
     */
    public static String httpPost(String url, String queryString)
    {
        return httpRequest(url, queryString, HTTP_METHOD_POST, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送GET请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:22:34
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param params
     *            请求参数
     * @return 响应结果
     */
    public static String httpGet(String url, Map<String, String> params)
    {
        return httpRequest(url, params, HTTP_METHOD_GET, DEFAULT_CHARSET);
    }

    /**
     * 
     * 发送GET请求，接受响应结果
     * 
     * 默认编码为GBK
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:23:05
     * @version: V1.0
     * 
     * @param url
     *            请求URL，如：http://bj.189.cn
     * @param queryString
     *            请求参数
     * @return 响应结果
     */
    public static String httpGet(String url, String queryString)
    {
        return httpRequest(url, queryString, HTTP_METHOD_GET, DEFAULT_CHARSET);
    }

    /**
     * 
     * 以建立HttpURLConnection方式发送请求
     * 
     * @author wanghui
     * @date 2012-6-14 下午3:12:29
     * @version: V1.0
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param method
     *            请求方式
     * @param charSet
     *            字符编码
     * @return 通讯失败返回null, 否则返回服务端输出
     */
    public static String httpRequest(String url, Map<String, String> params, String method, String charSet)
    {
        String queryString = parseQueryString(params, charSet);
        return httpRequest(url, queryString, method, charSet);
    }

    /**
     * 
     * 以建立HttpURLConnection方式发送请求
     * 
     * @author wanghui
     * @date 2012-6-14 下午3:14:53
     * @version: V1.0
     * 
     * @param targetUrl
     *            请求地址
     * @param queryString
     *            请求参数
     * @param method
     *            请求方式
     * @param charSet
     *            字符编码
     * @return 通讯失败返回null, 否则返回服务端输出
     */
    public static String httpRequest(String targetUrl, String queryString, String method, String charSet)
    {
        HttpURLConnection urlConn = null;
        URL destURL = null;
        boolean httpsFlag = false;
        if (targetUrl == null || targetUrl.trim().length() == 0)
        {
            throw new IllegalArgumentException("invalid targetUrl : " + targetUrl);
        }
        targetUrl = targetUrl.trim();

        // if(targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)){
        // throw new IllegalArgumentException("unsupport protocal : https");
        // }

        if (targetUrl.toLowerCase().startsWith(HTTPS_PREFIX))
        {
            httpsFlag = true;
        }
        else if (!targetUrl.toLowerCase().startsWith(HTTP_PREFIX))
        {
            targetUrl = HTTP_PREFIX + targetUrl;
        }

        // if(!targetUrl.toLowerCase().startsWith(HTTP_PREFIX) &&
        // !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)){
        // targetUrl = HTTP_PREFIX+targetUrl;
        // }
        if (queryString != null)
        {
            queryString = queryString.trim();
        }
        if (method == null || !(method.equals(HTTP_METHOD_POST) || method.equals(HTTP_METHOD_GET)))
        {
            throw new IllegalArgumentException("invalid http method : " + method);
        }

        String baseUrl = "";
        String params = "";
        String fullUrl = "";

        int index = targetUrl.indexOf("?");
        if (index != -1)
        {
            baseUrl = targetUrl.substring(0, index);
            params = targetUrl.substring(index + 1);
        }
        else
        {
            baseUrl = targetUrl;
        }

        if (queryString != null && queryString.trim().length() != 0)
        {
            if (params.trim().length() > 0)
            {
                params += "&" + queryString;
            }
            else
            {
                params += queryString;
            }
        }

        fullUrl = baseUrl + (params.trim().length() == 0 ? "" : ("?" + params));
        StringBuffer result = new StringBuffer(2000);
        try
        {
            if (method.equals(HTTP_METHOD_POST))
            {
                destURL = new URL(baseUrl);
            }
            else
            {
                destURL = new URL(fullUrl);
            }

            if (httpsFlag)
            {
                // urlConn = (HttpsURLConnection) destURL.openConnection();
                urlConn = (HttpURLConnection) destURL.openConnection();
            }
            else
            {
                urlConn = (HttpURLConnection) destURL.openConnection();
            }
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + charSet);
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod(method);
            urlConn.setConnectTimeout(DEFAULT_TIMEOUT);
            urlConn.setReadTimeout(DEFAULT_TIMEOUT);

            if (method.equals(HTTP_METHOD_POST))
            {
                OutputStream os = urlConn.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, charSet);
                osw.write(params);
                osw.flush();
                osw.close();
            }

            BufferedInputStream is = new BufferedInputStream(urlConn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is, charSet));
            String temp = null;
            while ((temp = br.readLine()) != null)
            {
                result.append(temp);
                result.append("\n");
            }
            int responseCode = urlConn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
            {
                return null;
            }
            return result.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (urlConn != null)
            {
                urlConn.disconnect();
            }
        }
    }

    /**
     * 
     * 字符串串转为Map对象
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:35:04
     * @version: V1.0
     * 
     * @param str
     *            字符串
     * @param splitChar
     *            分割字符
     * @return 转换后的Map对象， 如果入参为null或""，则返回空Map对象
     */
    public static Map<String, String> queryString(String str, String splitChar)
    {

        Map<String, String> map = new HashMap<String, String>();

        if (str == null || "".equals(str))
        {
            return map;
        }

        if (splitChar == null || "".equals(splitChar))
        {
            return map;
        }

        String[] keyValuePairs = str.split(splitChar);

        for (String keyValue : keyValuePairs)
        {
            if (keyValue.indexOf("=") == -1)
            {
                continue;
            }

            String[] args = keyValue.split("=");
            if (args.length == 2)
            {
                map.put(args[0], args[1]);
            }
            if (args.length == 1)
            {
                map.put(args[0], "");
            }
        }

        return map;
    }

    /**
     * 
     * 将Map类型参数转换成参数串
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:24:56
     * @version: V1.0
     * 
     * @param params
     *            Map型参数
     * @param charSet
     *            编码格式
     * @return 返回转换后的参数串
     */
    public static String parseQueryString(Map<String, String> params, String charSet)
    {
        if (null == params || params.keySet().size() == 0)
        {
            return "";
        }

        String key = null;
        Object obj = null;
        String value = "";

        StringBuffer queryString = new StringBuffer(2000);

        Iterator<String> i = params.keySet().iterator();

        while (i.hasNext())
        {
            key = String.valueOf(i.next());
            obj = params.get(key);
            value = "";

            if (obj != null)
            {
                value = obj.toString();
            }
            try
            {
                value = URLEncoder.encode(value, charSet);
            }
            catch (UnsupportedEncodingException ex)
            {
            }

            queryString.append(key);
            queryString.append("=");
            queryString.append(value);
            queryString.append("&");

        }

        String result = queryString.toString();

        if (result.endsWith("&"))
        {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    /**
     * 
     * URL拼接新参数
     * 
     * @author wanghui
     * @date 2012-7-26 下午2:49:09
     * @version: V1.0
     * 
     * @param targetUrl
     *            URL地址
     * @param queryString
     *            拼接参数
     * @return 新URL地址
     */
    public static String parseUrl(String targetUrl, String queryString)
    {
        if (targetUrl == null || "".equals(targetUrl.trim()))
        {
            throw new IllegalArgumentException("invalid targetUrl : " + targetUrl);
        }

        targetUrl = targetUrl.trim();

        if (!targetUrl.toLowerCase().startsWith(HTTP_PREFIX) && !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX))
        {
            targetUrl = HTTP_PREFIX + targetUrl;
        }

        if (queryString != null)
        {
            queryString = queryString.trim();
        }

        String baseUrl = "";
        String paramString = "";
        String fullUrl = "";

        int index = targetUrl.indexOf("?");

        if (index != -1)
        {
            baseUrl = targetUrl.substring(0, index);
            paramString = targetUrl.substring(index + 1);
        }
        else
        {
            baseUrl = targetUrl;
        }

        if (queryString != null && !"".equals(queryString))
        {
            if (!"".equals(paramString.trim()))
            {
                paramString += "&" + queryString;
            }
            else
            {
                paramString += queryString;
            }
        }

        fullUrl = baseUrl + (paramString.trim().length() == 0 ? "" : ("?" + paramString));

        return fullUrl;
    }

    /**
     * 
     * URL拼接新参数
     * 
     * @author wanghui
     * @date 2012-7-26 下午3:01:40
     * @version: V1.0
     * 
     * @param targetUrl
     *            URL地址
     * @param params
     *            拼接参数
     * @param charSet
     *            字符编码
     * @return 新URL地址
     */
    public static String parseUrl(String targetUrl, Map<String, String> params, String charSet)
    {
        String queryString = parseQueryString(params, charSet);
        return parseUrl(targetUrl, queryString);
    }

    /**
     * 
     * 将URL参数串转换为Map对象
     * 
     * @author wanghui
     * @date 2012-7-26 下午3:04:06
     * @version: V1.0
     * 
     * @param queryString
     *            URL参数串
     * @return 转换后的Map对象
     */
    public static Map<String, String> parseQueryString(String queryString)
    {
        if (queryString == null)
        {
            throw new IllegalArgumentException("queryString must be specified");
        }

        int index = queryString.indexOf("?");
        if (index > 0)
        {
            queryString = queryString.substring(index + 1);
        }

        String[] keyValuePairs = queryString.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String keyValue : keyValuePairs)
        {
            if (keyValue.indexOf("=") == -1)
            {
                continue;
            }
            String[] args = keyValue.split("=");
            if (args.length == 2)
            {
                map.put(args[0], args[1]);
            }
            if (args.length == 1)
            {
                map.put(args[0], "");
            }
        }
        return map;
    }

    /**
     * 
     * 去掉URL中的参数串
     * 
     * @author wanghui
     * @date 2012-7-26 下午3:06:40
     * @version: V1.0
     * 
     * @param url
     *            URL地址
     * @return 去除参数串的URL
     */
    public static String parseUrl(String url)
    {
        if (url == null)
        {
            throw new IllegalArgumentException("queryString must be specified");
        }

        int index = url.indexOf("?");

        String targetUrl = null;

        if (index > 0)
        {
            targetUrl = url.substring(0, index);
        }
        else
        {
            targetUrl = url;
        }

        return targetUrl;
    }

    public static void main(String[] args) throws Exception
    {
        String urlStr = "http://home.bj189.cn/TelecomSNSTestInterface/3GAPP/ShareSoftWare.ashx";
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("PhoneNumber", "13311566185");
        // ?PhoneNumber=13311566185&content=sdgsdfdfhhdffhfgdgdfhdfhfh&BlogID=2&key=0ecec9f1a40cedc936bb34d24853ea85
        hm.put("content", "sdgsdfdfhhdffhfgdgdfhdfhfh");
        hm.put("BlogID", "2");
        hm.put("key", "0ecec9f1a40cedc936bb34d24853ea85");

        String resultXml = CallbackUtils.httpRequest(urlStr, hm, "POST", "UTF-8");

        System.out.println(resultXml);

        int i = resultXml.indexOf(">");
        resultXml = resultXml.substring(i + 1);
        System.out.println(resultXml);
        // resultXml =
        // "<Result><BlogName>新浪微博</BlogName><Value>0</Value></Result>";
        Document document;
        document = DocumentHelper.parseText(resultXml);
        Element root = document.getRootElement();
        String BlogName = root.elementTextTrim("BlogName");
        String value = root.elementTextTrim("Value");
        System.out.println(BlogName);
        System.out.println(value);
    }

}