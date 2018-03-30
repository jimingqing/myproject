package com.yrtech.wx.capp.framework.core.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.CoreConstant;
import com.yrtech.wx.capp.framework.core.CoreConstantUtil;
import com.yrtech.wx.capp.framework.core.net.IPUtil;

/**
 * 
 * IP过滤器
 * 
 * 业务侧可配置黑白名单文件
 * 
 * 注意：白名单优先级高于黑名单，即若果黑白名中都配置了某个IP地址，则将忽略黑名单中的配置
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: IpCrackFilter
 * @author wanghui
 * @date 2012-6-7 下午9:01:36
 * @version: V1.0
 */
public class IpCrackFilter implements Filter
{
    /**
     * IP白名单列表
     */
    private static ArrayList<String> allowIps = new ArrayList<String>();

    /**
     * IP黑名单列表
     */
    private static ArrayList<String> denyIps = new ArrayList<String>();

    private static Logger logger = Logger.getLogger(IpCrackFilter.class);

    /**
     * 
     * 过滤器初始化
     * 
     * @author wanghui
     * @date 2012-7-9 下午5:41:05
     * @version: V1.0
     * 
     * @param filterConfig
     *            过滤器配置对象
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // 读取黑白名单文件，初始化黑白名单列表
        setIps();
    }

    /**
     * 
     * 执行过滤
     * 
     * @author wanghui
     * @date 2012-7-9 下午5:41:38
     * @version: V1.0
     * 
     * @param arg0
     *            ServletRequest对象
     * @param arg1
     *            ServletResponse对象
     * @param arg2
     *            FilterChain对象
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException
    {
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpServletRequest request = (HttpServletRequest) arg0;

        // 获取客户端ip地址
        String ip = IPUtil.getClinetIpByReq(request);

        HttpServletResponse httpResp = response;

        boolean flag = true;

        if (allowIps.contains(ip)) // 检查ip是否在白名单中
        {
            flag = true;
        }
        else if (denyIps.contains(ip)) // 检查ip是否在黑白单中
        {
            flag = false;
            httpResp.sendError(HttpServletResponse.SC_FORBIDDEN, "That means goodbye forever!");
        }

        // 判断是否需要更新ip黑白名单列表，当refresh参数为COM_CORE_REFRESH_IP时，则表示更新
        String refresh = request.getParameter("refresh");
        if (CoreConstant.IP_CRACK_FILTER_COMMAND_REFRESH.equals(refresh))
        {
            setIps();
        }

        /**
         * 
         * 判断flag，如果为true表示可以进行通过
         */
        if (flag)
        {
            arg2.doFilter(arg0, arg1);
        }

    }

    /**
     * 
     * 读取过滤IP黑白名单
     * 
     * @author wanghui
     * @date 2012-7-9 下午8:07:50
     * @version: V1.0
     * 
     */
    private void setIps()
    {
        BufferedReader in = null;

        try
        {
            int ii = 0;

            String[] ipArr = null;

            String tempStr = "";

            /**
             * 读取禁止IP列表
             */
            File file = new File(CoreConstantUtil.getDenyIpFile());
            if (file != null && file.isFile())
            {
                denyIps.clear();

                in = new BufferedReader(new FileReader(file));

                while ((tempStr = in.readLine()) != null)
                {
                    ipArr = tempStr.split("\\|");
                    for (ii = 0; ii < ipArr.length; ii++)
                    {
                        if (ipArr[ii] != null && !"".equals(ipArr[ii].trim()))
                        {
                            denyIps.add(ipArr[ii].trim());
                        }
                    }
                }

                in.close();
            }

            /**
             * 读取允许IP列表
             */
            file = new File(CoreConstantUtil.getAllowIpFile());
            if (file != null && file.isFile())
            {
                allowIps.clear();

                in = new BufferedReader(new FileReader(file));

                while ((tempStr = in.readLine()) != null)
                {
                    ipArr = tempStr.split("\\|");
                    for (ii = 0; ii < ipArr.length; ii++)
                    {
                        if (ipArr[ii] != null && !"".equals(ipArr[ii].trim()))
                        {
                            allowIps.add(ipArr[ii].trim());
                        }
                    }
                }

                in.close();
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                    in = null;
                }
            }
            catch (Exception e)
            {
                logger.error("", e);
            }
        }

    }

    public void destroy()
    {
    }
}
