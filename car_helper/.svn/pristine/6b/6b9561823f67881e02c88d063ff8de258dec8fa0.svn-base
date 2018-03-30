package com.yrtech.wx.capp.framework.core;

/**
 * 
 * 核心包常量工具类，该类主要通过常量工具接口，获取业务系统侧常量信息
 * 
 * @Package: com.yrtech.wx.capp.framework.core
 * @ClassName: ConstantUtil
 * @author wanghui
 * @date 2012-6-12 上午9:06:31
 * @version: V1.0
 */
public class CoreConstantUtil
{

    private static IConstantUtil constantUtil = null;

    private CoreConstantUtil()
    {
    }

    /**
     * 
     * 设置IConstantUtil
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:45:43
     * @version: V1.0
     * 
     * @param tconstantUtil
     */
    public static void setConstantUtil(IConstantUtil tconstantUtil)
    {
        constantUtil = tconstantUtil;
    }

    /**
     * 
     * 校验CoreConstantUtil是否完成初始化
     * 
     * @author wanghui
     * @date 2012-6-13 下午1:43:35
     * @version: V1.0
     * 
     * @return true-已初始化,false-未初始化
     */
    public static boolean isInit()
    {
        if (constantUtil != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 
     * 获取用户登录Session信息Key
     * 
     * @author wanghui
     * @date 2012-6-12 下午4:20:31
     * @version: V1.0
     * 
     * @return 用户登录Session信息Key
     */
    public static String getLoginSessionKey()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getLoginSessionKey();
        }

        return ret;
    }

    /**
     * 
     * 获取系统ID，如日志记录区分哪台主机等 10.1:16000
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:29:44
     * @version: V1.0
     * 
     * @return 系统ID值
     */
    public static String getSysId()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getSysId();
        }

        return ret;
    }

    /**
     * 
     * 获取JDBC数据源Spring配置Bean ID
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:33:11
     * @version: V1.0
     * 
     * @return JDBC数据源Bean ID
     */
    public static String getJdbcBeanId()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getJdbcBeanId();
        }

        return ret;
    }

    /**
     * 
     * 获取JDBC模板Spring配置Bean ID
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:38:45
     * @version: V1.0
     * 
     * @return JDBC模板Bean ID
     */
    public static String getJdbcTemplateBeanId()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getJdbcTemplateBeanId();
        }

        return ret;
    }

    /**
     * 
     * 获取未绑定注册用户的类型值
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:41:34
     * @version: V1.0
     * 
     * @return 未绑定注册用户类型值
     */
    public static int getAcctTypeUnbind()
    {
        int ret = -9999;

        if (constantUtil != null)
        {
            ret = constantUtil.getAcctTypeUnbind();
        }

        return ret;
    }

    /**
     * 
     * 获取绑定注册用户的类型值
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:42:15
     * @version: V1.0
     * 
     * @return 绑定注册用户类型值
     */
    public static int getAcctTypeBind()
    {
        int ret = -9999;

        if (constantUtil != null)
        {
            ret = constantUtil.getAcctTypeBind();
        }

        return ret;
    }

    /**
     * 
     * 获取权限信息在session中存放的key值
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:56:59
     * @version: V1.0
     * 
     * @return 权限信息在session中存放的key值
     */
    public static String getUrlPrivKey()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getUrlPrivKey();
        }

        return ret;
    }

    /**
     * 
     * 获取角色信息在session中存放的key值
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:57:55
     * @version: V1.0
     * 
     * @return 角色信息在session中存放的key值
     */
    public static String getUrlAndRoleKey()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getUrlAndRoleKey();
        }

        return ret;
    }

    /**
     * 
     * 获取非WEB方式需要加载的spring配置文件
     * 
     * @author wanghui
     * @date 2012-6-12 下午3:06:23
     * @version: V1.0
     * 
     * @return 非WEB方式需要加载的spring配置文件 (以|分割)
     */
    public static String getAppSpringFiles()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAppSpringFiles();
        }

        return ret;
    }

    /**
     * 
     * 获取日志Domain名称
     * 
     * @author wanghui
     * @date 2012-6-13 下午2:10:59
     * @version: V1.0
     * 
     * @return 日志Domain名称
     */
    public static String getLogDomain()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogDomain();
        }

        return ret;
    }

    /**
     * 
     * 获取日志队列大小
     * 
     * @author wanghui
     * @date 2012-6-13 下午1:44:49
     * @version: V1.0
     * 
     * @return 日志队列大小
     */
    public static int getLogQueueSize()
    {
        int ret = -9999;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogQueueSize();
        }

        return ret;
    }

    /**
     * 
     * 获取日志PooledExecutor支持线程数
     * 
     * @author wanghui
     * @date 2012-6-13 下午1:49:41
     * @version: V1.0
     * 
     * @return 日志PooledExecutor最大线程数
     */
    public static int getLogPoolSize()
    {
        int ret = -9999;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogPoolSize();
        }

        return ret;
    }

    /**
     * 
     * 获取日志Debug开关
     * 
     * @author wanghui
     * @date 2012-6-13 下午1:57:27
     * @version: V1.0
     * 
     * @return Debug开关值，Y-打开，其他-关闭
     */
    public static String getLogDebugSwitch()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogDebugSwitch();
        }

        return ret;
    }

    /**
     * 
     * 获取accessPeak日志统计间隔时间(秒)
     * 
     * @author wanghui
     * @date 2012-6-13 下午2:05:21
     * @version: V1.0
     * 
     * @return accessPeak日志统计间隔时间(秒)
     */
    public static int getLogPeakPeriod()
    {
        int ret = -9999;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogPeakPeriod();
        }

        return ret;
    }

    /**
     * 
     * 获取日志sequence名称
     * 
     * @author wanghui
     * @date 2012-6-13 下午2:08:41
     * @version: V1.0
     * 
     * @return 日志sequence名称
     */
    public static String getLogSeqName()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getLogSeqName();
        }

        return ret;
    }

    /**
     * 
     * 获取Acces日志过滤方式
     * 
     * @author wanghui
     * @date 2012-7-6 下午3:07:10
     * @version: V1.0
     * 
     * @return Access日志过滤方式，方式类型：
     * 
     *         none:所有请求均不记录Access日志，通过getAccessLogFilterWay()获取的类型列表将被忽视
     * 
     *         all:所有请求均需记录Access日志，通过getAccessLogFilterWay()获取的类型列表将被忽视
     * 
     *         allow: 以白名单方式，过滤不记录Access日志的文件类型（过滤列表通过getAccessLogFilterWay()获取）
     * 
     *         deny: 以黑名单方式，过滤记录Access日志的文件类型（过滤列表通过getAccessLogFilterWay()获取） *
     * 
     *         其他: 取默认none
     */
    public static String getAccessLogFilterWay()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAccessLogFilterWay();
        }

        return ret;
    }

    /**
     * 
     * 获取Access日志过滤文件类型，格式：以"|"分割的字符串，例如：.jpg|.txt|.js
     * 
     * 根据getAccessLogFilterWay()的返回值，判断返回内容的作用
     * 
     * getAccessLogFilterWay()返回none，该方法返回内容将被忽视
     * 
     * getAccessLogFilterWay()返回all，该方法返回内容将被忽视
     * 
     * getAccessLogFilterWay()返回allow，表示该方法返回的文件类型请求，均不记录日志，其他文件类型需记录
     * 
     * getAccessLogFilterWay()返回dany，表示该方法返回的文件类型请求，均需记录日志，其他文件类型不再记录
     * 
     * @author wanghui
     * @date 2012-7-6 下午2:06:55
     * @version: V1.0
     * 
     * @return Access日志过滤文件类型列表，以"|"分割
     */
    public static String getAccessLogFileTypes()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAccessLogFileTypes();
        }

        return ret;
    }

    /**
     * 
     * 获取JNDI名称
     * 
     * @author wanghui
     * @date 2012-7-7 下午7:26:53
     * @version: V1.0
     * 
     * @return JNDI名称
     */
    public static String getJndiName()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getJndiName();
        }

        return ret;
    }

    /**
     * 
     * 获取JNDI URL
     * 
     * @author wanghui
     * @date 2012-7-7 下午7:27:11
     * @version: V1.0
     * 
     * @return JNDI URL
     */
    public static String getJndiUrl()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getJndiUrl();
        }

        return ret;
    }

    /**
     * 
     * 获取IP过滤白名单文件（带路径）
     * 
     * @author wanghui
     * @date 2012-7-9 下午7:33:37
     * @version: V1.0
     * 
     * @return IP过滤白名单文件（带路径）
     */
    public static String getAllowIpFile()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAllowIpFile();
        }

        return ret;
    }

    /**
     * 
     * 获取IP过滤黑名单文件（带路径）
     * 
     * @author wanghui
     * @date 2012-7-9 下午7:34:14
     * @version: V1.0
     * 
     * @return IP过滤黑名单文件（带路径）
     */
    public static String getDenyIpFile()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getDenyIpFile();
        }

        return ret;
    }

    /**
     * 
     * 获取XSS过滤器排除URL（以|分割）
     * 
     * @author wanghui
     * @date 2012-7-9 下午8:59:04
     * @version: V1.0
     * 
     * @return XSS过滤器排除URL（以|分割）
     */
    public static String getXssExcludeUrl()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getXssExcludeUrl();
        }

        return ret;
    }

    /**
     * 
     * 获取Access日志表名称
     * 
     * @author wanghui
     * @date 2012-7-10 下午1:51:59
     * @version: V1.0
     * 
     * @return Access日志表名称
     */
    public static String getAccessLogTableName()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAccessLogTableName();
        }

        return ret;
    }

    /**
     * 
     * 获取Access日志表序列名称
     * 
     * @author wanghui
     * @date 2012-7-13 下午3:02:48
     * @version: V1.0
     * 
     * @return Access日志表序列名称
     */
    public static String getAccessLogSeqName()
    {
        String ret = null;

        if (constantUtil != null)
        {
            ret = constantUtil.getAccessLogSeqName();
        }

        return ret;
    }
}
