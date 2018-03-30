package com.yrtech.wx.capp.framework.core.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.yrtech.wx.capp.framework.core.CoreConstantUtil;

/**
 * 类描述：Spring 容器初始化
 * @Package: com.yrtech.wx.capp.framework.core.spring
 * @ClassName: SpringInfo.java
 * @author: wanghui
 * @date： 2012-11-10 上午11:22:09
 * @version 1.0
 */
public class SpringInfo
{
    public static WebApplicationContext SPRING_CONTEXT = null;

    /**
     * 初始化springcontext
     * 
     * @param servletContext
     */
    public static void init(ServletContext servletContext)
    {
        SPRING_CONTEXT = (WebApplicationContext) servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    /**
     * 获取spring bean
     * 
     * @param beanName
     */
    public static Object getBean(String beanName)
    {
        if (SPRING_CONTEXT == null)
        {
//            String springFiles = CoreConstantUtil.getAppSpringFiles();
        	String springFiles = "springxml/applicationContext.xml";
            String[] springFileArr = springFiles.split("\\|");

            /* 非web方式测试使用 */
            ApplicationContext context = new ClassPathXmlApplicationContext(springFileArr);
            // "springxml/applicationContext.xml", "springxml/common_bean.xml"
            // });
            return context.getBean(beanName);
        }
        return SpringInfo.SPRING_CONTEXT.getBean(beanName);
    }

    public static WebApplicationContext getBeanFactory()
    {
        return SPRING_CONTEXT;
    }

}
