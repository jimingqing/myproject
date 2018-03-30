package com.yrtech.wx.capp.framework.core.net;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Cookie操作工具类
 * 
 * @Package: com.yrtech.wx.capp.framework.core.net
 * @ClassName: CookieUtil
 * @author wanghui
 * @date 2012-6-17 下午6:59:15
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class CookieUtil
{

    public CookieUtil()
    {
    }

    /**
     * 
     * 根据key获取value值
     * 
     * @author wanghui
     * @date 2012-6-17 下午6:59:26
     * @version: V1.0
     * 
     * @param request
     *            HttpServletRequest对象
     * @param key
     *            key值
     * @return value值
     */
    public static String getString(HttpServletRequest request, String key)
    {
        String value = null;
        Cookie cookie = null;
        Cookie cookies[] = request.getCookies();
        if (cookies != null)
        {
            int i = 0;
            do
            {
                if (i >= cookies.length)
                    break;
                if (cookies[i].getName().equals(key))
                {
                    cookie = cookies[i];
                    break;
                }
                i++;
            }
            while (true);
        }
        if (cookie != null)
            value = cookie.getValue();
        if (value == null)
            value = "";
        return value;
    }

    /**
     * 
     * 设置cookie信息
     * 
     * @author wanghui
     * @date 2012-6-17 下午7:00:14
     * @version: V1.0
     * 
     * @param response
     *            HttpServletResponse对象
     * @param name
     *            cookie名称
     * @param value
     *            cookie值
     * @param seconds
     *            有效期限
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int seconds)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(seconds);
        cookie.setPath("/");
        // cookie.setDomain(Contants.DOMAIN);
        // cookie.setDomain("myyule.com");
        response.addCookie(cookie);
    }

    /**
     * 
     * 设置cookie信息
     * 
     * @author wanghui
     * @date 2012-6-17 下午7:01:52
     * @version: V1.0
     * 
     * @param response
     *            HttpServletResponse对象
     * @param name
     *            cookie名称
     * @param value
     *            cookie值
     */
    public static void setCookie(HttpServletResponse response, String name, String value)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        // cookie.setDomain(Contants.DOMAIN);
        // cookie.setDomain("myyule.com");
        // cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
    }

    /**
     * 
     * 删除cookie信息
     * 
     * @author wanghui
     * @date 2012-6-17 下午7:02:40
     * @version: V1.0
     * 
     * @param request
     *            HttpServletRequest对象
     * @param response
     *            HttpServletResponse对象
     * @param cookieName
     *            cookie名称
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName)
    {
        // invalidate the cookie
        Cookie cookie = new Cookie(cookieName, "");

        // delete the cookie when the user closes their webbrowser
        // cookie.setPath("/");
        cookie.setMaxAge(0);
        // cookie.setDomain(Contants.DOMAIN);
        // cookie.setDomain("myyule.com");
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
