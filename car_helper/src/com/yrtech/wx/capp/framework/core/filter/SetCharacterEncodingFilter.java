package com.yrtech.wx.capp.framework.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * 编码设置过滤器
 * 
 * 通过init-param配置encoding值，默认取GBK
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: SetCharacterEncodingFilter
 * @author wanghui
 * @date 2012-6-8 上午10:31:30
 * @version: V1.0
 */
public class SetCharacterEncodingFilter implements Filter
{

    private String encoding = null;

    /**
     * Take this filter out of service.
     */
    public void destroy()
    {
    }

    /**
     * Select and set (if specified) the character encoding to be used to
     * interpret request parameters for this request.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        if (encoding == null || "".equals(encoding.trim()))
        {
            request.setCharacterEncoding("GBK");
        }
        else
        {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
        encoding = filterConfig.getInitParameter("encoding");
    }

}
