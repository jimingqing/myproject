package com.yrtech.wx.capp.framework.core.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 在Struts2 Action中调用可以方便获取Request和Response
 * 
 * @Package: com.yrtech.wx.capp.framework.core.struts
 * @ClassName: Struts2Util
 * @author wanghui
 * @date 2012-6-12 下午5:03:56
 * @version: V1.0
 */
public class Struts2Util
{

    /**
     * 
     * 获取request对象，只能在action中调用
     * 
     * @author wanghui
     * @date 2012-6-12 下午5:04:41
     * @version: V1.0
     * 
     * @return HttpServletRequest对象
     */
    public static HttpServletRequest getRequest()
    {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        return request;
    }

    /**
     * 
     * 获取response对象，只能在action中调用
     * 
     * @author wanghui
     * @date 2012-6-12 下午5:05:40
     * @version: V1.0
     * 
     * @return HttpServletResponse对象
     */
    public static HttpServletResponse getResponse()
    {
        ActionContext ctx = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
        return response;
    }
}
