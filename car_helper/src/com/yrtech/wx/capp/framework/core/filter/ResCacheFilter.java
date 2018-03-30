package com.yrtech.wx.capp.framework.core.filter;

import java.io.IOException;
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

/**
 * 
 * 用Filter 缓存页面图片、js脚本等静态资源
 * 
 * 试用方法：部署filter 在web.xml中加入
 * 
 * <filter> <filter-name>rescachefilter</filter-name>
 * <filter-class>com.util.filters.ResCacheFilter</filter-class>
 * <init-param> <param-name>js</param-name> <param-value>3600</param-value>
 * </init-param> <init-param> <param-name>gif</param-name>
 * <param-value>3600</param-value> </init-param> <init-param>
 * <param-name>jpg</param-name> <param-value>3600</param-value> </init-param>
 * <init-param> <param-name>css</param-name> <param-value>3600</param-value>
 * </init-param> </filter> <filter-mapping>
 * <filter-name>rescachefilter</filter-name> <url-pattern>*.js</url-pattern>
 * </filter-mapping> <filter-mapping> <filter-name>rescachefilter</filter-name>
 * <url-pattern>*.gif</url-pattern> </filter-mapping> <filter-mapping>
 * <filter-name>rescachefilter</filter-name> <url-pattern>*.jpg</url-pattern>
 * </filter-mapping> <filter-mapping> <filter-name>rescachefilter</filter-name>
 * <url-pattern>*.css</url-pattern> </filter-mapping>
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: ResCacheFilter
 * @author wanghui
 * @date 2012-6-8 上午10:18:33
 * @version: V1.0
 */
@SuppressWarnings("unchecked")
public class ResCacheFilter implements Filter
{
    private FilterConfig config = null;
    private Map<String, Integer> expiresMap = new HashMap<String, Integer>();

    /**
     * 
     * 读取配置文件缓存资源类型与时长，初始化资源缓存列表
     * 
     * @author wanghui
     * @date 2012-6-8 上午10:22:38
     * @version: V1.0
     * 
     * @param filterConfig
     *            过滤器配置对象
     */
    public void init(FilterConfig filterConfig)
    {
        this.config = filterConfig;
        expiresMap.clear();
        Enumeration names = config.getInitParameterNames();
        while (names.hasMoreElements())
        {
            try
            {
                String name = (String) names.nextElement();
                String value = config.getInitParameter(name);
                Integer expire = Integer.valueOf(value);
                expiresMap.put(name, expire);
            }
            catch (Exception ex)
            {
            }
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String ext = null;

        // 获取访问资源类型，即文件扩展名
        int dot = uri.lastIndexOf(".");
        if (dot != -1)
        {
            ext = uri.substring(dot + 1);
        }

        // 根据资源类型设置缓存时长
        setResponseHeader(res, uri, ext);

        chain.doFilter(request, response);
    }

    public void destroy()
    {
    }

    protected FilterConfig getFilterConfig()
    {
        return (config);
    }

    /**
     * 
     * 根据资源类型，查询缓存资料列表，修改response对象中的缓存时长
     * 
     * @author wanghui
     * @date 2012-6-8 上午10:26:07
     * @version: V1.0
     * 
     * @param response
     *            response对象
     * @param uri
     *            请求uri
     * @param ext
     *            请求资源类型
     */
    private void setResponseHeader(HttpServletResponse response, String uri, String ext)
    {
        if (ext != null && ext.length() > 0)
        {
            Integer expires = (Integer) expiresMap.get(ext);
            if (expires != null)
            {
                if (expires.intValue() > 0)
                {
                    // HTTP 1.1
                    response.setHeader("Cache-Control", "max-age=" + expires.intValue());
                }
                else
                {
                    // HTTP 1.0
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                }
            }
        }
    }
}
