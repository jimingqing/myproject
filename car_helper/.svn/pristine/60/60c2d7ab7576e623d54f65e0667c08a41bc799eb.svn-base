package com.yrtech.wx.capp.framework.core.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.spring.SpringInfo;

/**
 * 类描述：应用启动初始化核心类，初始化核心包及业务侧需要提前加载的内容
 * 
 * 该类必须在web.xml中配置为监听listener 配置如下：
 * 
 * <!-- 应用启动加载项 --> <listener>
 * <listener-class>com.yrtech.wx.capp.framework.core.web.CorePortalStart</listener-class>
 * </listener>
 * 
 * @Package: com.yrtech.wx.capp.framework.core.web
 * @ClassName: CoreDataInit.java
 * @author: wanghui
 * @date： 2012-11-10 下午1:43:42
 * @version 1.0
 */
public class CoreDataInit implements ServletContextListener
{

    static Logger logger = Logger.getLogger(CoreDataInit.class);

    String constantUtilClassName = null;

    String portalInitClassName = null;

    ServletContext servletContext = null;

    /**
     * 
     * 信息初始化
     * 
     * 注意初始化的顺序，先初始化核心包内容，再初始化业务侧内容
     * 
     * @author wanghui
     * @date 2012-11-5 
     * @version: V1.0
     * 
     * @return true-初始化成功，否则，初始化失败
     */
    private boolean init()
    {
        // Spring初始化
        SpringInfo.init(servletContext);
        logger.info("################业务初始化开始");
        // 业务初始化
        ProductInit.initBankInfo();
        ProductInit.initOperAuthInfo();
        ProductInit.initGoodsCat();
        ProductInit.initProvCity();
        ProductInit.initLogisticsCorp();
        logger.info("################业务初始化完成");
        return true;
    }

    public void contextDestroyed(ServletContextEvent arg0)
    {
        logger.info("################应用正在退出......");
        // 应用退出前进行清理，如日志入库
        logger.info("################应用退出结束");
    }

    /**
     * 初始化
     * 
     * @author wanghui
     * @date 2012-11-5 
     * @version: V1.0
     * 
     * @param arg0
     */
    public void contextInitialized(ServletContextEvent arg0)
    {
        servletContext = arg0.getServletContext();
        logger.info("################核心包信息初始化开始.....");
        if (!init())
        {
            logger.info("####初始化失败，应用退出");
            System.exit(-1);
        }
        logger.info("################核心包信息初始化完成.....");
    }

}
