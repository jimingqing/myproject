package com.yrtech.wx.capp.framework.core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.CoreConstantUtil;
import com.yrtech.wx.capp.framework.core.util.CheckUtil;

/**
 * 
 * 非法字符过滤器（防SQL注入，防XSS漏洞）
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: XssFilter
 * @author wanghui
 * @date 2012-6-7 下午6:07:03
 * @version: V1.0
 */
@SuppressWarnings("unchecked")
public class XssFilter implements Filter
{
    private static Logger logger = Logger.getLogger(XssFilter.class);

    /**
     * 排除部分URL不做过滤
     */
    private static ArrayList<String> excludeUrls = new ArrayList<String>();

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException
    {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String pathInfo = req.getPathInfo() == null ? "" : req.getPathInfo();
        String url = req.getServletPath() + pathInfo;

        /**
         * 
         * 排除部分URL不做过滤。 如：天翼LIVE转入网厅登录，不能进行安全过滤，否则密文被改无法解密。
         */
        if (excludeUrls.contains(url))
        {
            arg2.doFilter(req, response);
            return;
        }

        /**
         * 获取请求所有参数，校验防止SQL注入
         */
        Map parameters = new HashMap(req.getParameterMap());

        /**
         * 防止XSS漏洞
         */
        XssHttpServletRequestWrapper request = new XssHttpServletRequestWrapper(req);

        for (Object key : parameters.keySet())
        {
            Enumeration params = request.getParameterNames();
            String paramN = null;
            while (params.hasMoreElements())
            {
                paramN = (String) params.nextElement();
                String paramVale = request.getParameter(paramN);
                logger.debug(paramN + "==" + paramVale);

                /**
                 * 校验是否存在SQL注入信息
                 */
                if (CheckUtil.checkSQLInject(paramVale))
                {
                    String warning = "输入项中不能包含非法字符。";

                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter out = response.getWriter();

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('" + warning + "');");
                    out.println("history.go(-1);");
                    out.println("</script>");
                    out.flush();
                    out.close();
                    return;
                }
            }

        }

        arg2.doFilter(request, response);

    }

    public void destroy()
    {
    }

    public void init(FilterConfig filterconfig1) throws ServletException
    {
        setExcludeUrl();
    }

    /**
     * 
     * 从业务侧读取XSS排除URL
     * 
     * @author wanghui
     * @date 2012-7-9 下午9:03:58
     * @version: V1.0
     * 
     */
    private void setExcludeUrl()
    {
        String tempStr = null;
        String[] urlArr = null;

        // 获取排除URL，以|分割
        tempStr = CoreConstantUtil.getXssExcludeUrl();

        if (tempStr != null)
        {
            urlArr = tempStr.split("\\|");
            for (int ii = 0; ii < urlArr.length; ii++)
            {
                if (urlArr[ii] != null && !"".equals(urlArr[ii].trim()))
                {
                    excludeUrls.add(urlArr[ii].trim());
                }
            }
        }
    }
}
