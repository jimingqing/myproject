package com.yrtech.wx.capp.framework.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * HttpURL工具类
 * 
 * @Package: com.yrtech.wx.capp.framework.core.net
 * @ClassName: HttpURL
 * @author wanghui
 * @date 2012-6-14 下午5:25:16
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class HttpURL
{

    private static Log log = LogFactory.getLog(HttpURL.class);

    /**
     * 请求URL,返回URL响应信息
     * @param url
     *            访问URL地址
     * @return 响应信息
     * @throws IOException
     */
    public static String retrieve(String url) throws IOException
    {
        if (log.isTraceEnabled())
        {
            log.trace("entering retrieve(" + url + ")");
        }

        BufferedReader r = null;

        try
        {
            URL u = new URL(url);

            // if (!u.getProtocol().equals("http")){ // IOException may not be
            // the best exception we could throw here // since the problem is
            // with the URL argument we were passed, not // IO. -awp9
            // log.error("retrieve(" + url +
            // ") on an illegal URL since protocol was not http."); throw new
            // IOException("only 'http' URLs are valid for this method"); }

            URLConnection uc = u.openConnection();
            uc.setRequestProperty("Connection", "close");

            r = new BufferedReader(new InputStreamReader(uc.getInputStream()));

            String line;
            StringBuffer buf = new StringBuffer();

            while ((line = r.readLine()) != null)
                buf.append(line + "\n");

            return buf.toString();
        }
        finally
        {
            try
            {
                if (r != null)
                    r.close();
            }
            catch (IOException ex)
            {
                // ignore
            }
        }
    }

    /**
     * 
     * HTTP请求,并获取响应信息
     * 
     * 只返回一行响应内容，可用于校验请求连接是否正常
     * 
     * @author keliande keld@si-tech.com.cn
     * @date 2012-6-16 下午8:20:51
     * @version: V1.0
     * 
     * @param urlvalue
     *            URL地址
     * @return http响应信息
     */
    public static String check(String urlvalue)
    {
        String inputLine = "";

        try
        {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            if (in != null)
            {
                inputLine = in.readLine();
                in.close();
                in = null;
            }

        }
        catch (Exception e)
        {
            log.error("com.yrtech.wx.capp.framework.core.net.HttpURL调用check方法异常", e);
        }
        return inputLine;
    }
}
