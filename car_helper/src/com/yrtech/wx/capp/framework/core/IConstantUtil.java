package com.yrtech.wx.capp.framework.core;

/**
 * 普通常量工具类
 * 
 * 业务侧必须有对应的实现类，实现该接口
 * 
 * 然后，将实现类作为参数配置到web.xml中， 注意参数名称为constantUtilClassName，不能修改！！ 例如：
 * 
 * <context-param> <param-name>constantUtilClassName</param-name>
 * <param-value>com.yrtech.wx.capp.framework.core.ConstantUtil</param-value> </context-param>
 * 
 * @Package: com.yrtech.wx.capp.framework.core
 * @ClassName: IConstantUtil
 * @author wanghui
 * @date 2012-6-12 上午9:02:44
 * @version: V1.0
 */
public interface IConstantUtil
{

    /**
     * 
     * 获取用户登录Session信息Key
     * 
     * @author wanghui
     * @date 2012-6-12 下午4:18:35
     * @version: V1.0
     * 
     * @return 用户登录Session信息Key
     */
    public String getLoginSessionKey();

    /**
     * 
     * 获取系统ID，如区分哪台机器日志等 10.1:16000
     * 
     * @author wanghui
     * @date 2012-6-12 下午1:29:44
     * @version: V1.0
     * 
     * @return 系统ID值
     */
    public String getSysId();


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
    public String getJdbcBeanId();

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
    public String getJdbcTemplateBeanId();

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
    public int getAcctTypeUnbind();

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
    public int getAcctTypeBind();

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
    public String getUrlPrivKey();

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
    public String getUrlAndRoleKey();

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
    public String getAppSpringFiles();

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
    public String getLogDomain();

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
    public int getLogQueueSize();

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
    public int getLogPoolSize();

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
    public String getLogDebugSwitch();

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
    public int getLogPeakPeriod();

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
    public String getLogSeqName();

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
     *         deny: 以黑名单方式，过滤记录Access日志的文件类型（过滤列表通过getAccessLogFilterWay()获取）
     * 
     *         其他: 取默认none
     */
    public String getAccessLogFilterWay();

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
    public String getAccessLogFileTypes();

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
    public String getJndiName();

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
    public String getJndiUrl();

    /**
     * 
     * 获取IP过滤白名单文件（带路径）
     * 
     * @author wanghui
     * @date 2012-7-9 下午7:32:19
     * @version: V1.0
     * 
     * @return IP过滤白名单文件（带路径）
     */
    public String getAllowIpFile();

    /**
     * 
     * 获取IP过滤黑名单文件（带路径）
     * 
     * @author wanghui
     * @date 2012-7-9 下午7:32:53
     * @version: V1.0
     * 
     * @return IP过滤黑名单文件（带路径）
     */
    public String getDenyIpFile();

    /**
     * 
     * 获取XSS过滤器排除URL（以|分割）
     * 
     * @author wanghui
     * @date 2012-7-9 下午8:58:01
     * @version: V1.0
     * 
     * @return XSS过滤器排除URL（以|分割）
     */
    public String getXssExcludeUrl();

    /**
     * 
     * 获取Access日志表名称
     * 
     * @author wanghui
     * @date 2012-7-10 下午1:50:35
     * @version: V1.0
     * 
     * @return Access日志表名称
     */
    public String getAccessLogTableName();

    /**
     * 
     * 获取Access日志表序列名称
     * 
     * @author wanghui
     * @date 2012-7-13 下午3:01:58
     * @version: V1.0
     * 
     * @return Access日志表序列名称
     */
    public String getAccessLogSeqName();

}
