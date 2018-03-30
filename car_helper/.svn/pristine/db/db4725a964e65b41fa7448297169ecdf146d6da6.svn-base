package com.yrtech.wx.capp.framework.core.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ralasafe.Factory;
import org.ralasafe.Ralasafe;
import org.ralasafe.privilege.Privilege;
import org.ralasafe.privilege.PrivilegeManager;
import org.ralasafe.user.User;

import com.yrtech.wx.capp.framework.core.net.IPUtil;

/**
 * 
 * 权限检查过滤器
 * 
 * @Package: com.yrtech.wx.capp.framework.core.filter
 * @ClassName: PrivCheckFilter
 * @author wanghui
 * @date 2013-4-1 
 * @version: V1.0
 */
public class PrivCheckFilter implements Filter
{

    private static Logger logger = Logger.getLogger(PrivCheckFilter.class);

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
        String basePath = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        String errorUrl = (basePath+"/privError.action").replaceAll("\\:80/", "/").replaceAll("\\:443/", "/");
        
        // 获取客户端ip地址
        String ip = IPUtil.getClinetIpByReq(request);
        String url = request.getServletPath();
        
        if(url.indexOf("privError.action")>-1
        		|| url.indexOf("login/login.action")>-1){
        	arg2.doFilter(arg0, arg1);
        	return;
        }
        
        User user = (User)request.getSession().getAttribute("rls_user");
        if(user == null){
        	response.sendRedirect(errorUrl);
        	return;
        }

        PrivilegeManager privm = Factory.getPrivilegeManager("ralasafe");
        Collection<Privilege> list = privm.getLikelyPrivilegesByUrl(url);
        if(list != null && list.size()>0){
        	Privilege priv = list.iterator().next();
        	if(Ralasafe.hasPrivilege(priv.getId(), user)){
        		arg2.doFilter(arg0, arg1);
        		return;
        	}else{
        		response.sendRedirect(errorUrl);
        		return;
        	}
        }else{
        	arg2.doFilter(arg0, arg1);
        }

    }


    public void destroy()
    {
    }
}
