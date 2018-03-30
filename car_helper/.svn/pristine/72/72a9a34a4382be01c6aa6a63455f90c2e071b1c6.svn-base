package com.yrtech.wx.capp.framework.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 屏蔽get请求，禁止get请求获取参数
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: PostRequestWarpper
 * @author wanghui
 * @date 2010-8-5 上午10:07:18
 * @version: V1.0
 */
public class PostRequestWarpper extends HttpServletRequestWrapper
{
    public PostRequestWarpper(HttpServletRequest request)
    {
        super(request);
    }

    public String getParameter(String name)
    {

        if (super.getMethod().equalsIgnoreCase("GET"))
        {
            return null;
        }
        return super.getParameter(name);
    }

}
