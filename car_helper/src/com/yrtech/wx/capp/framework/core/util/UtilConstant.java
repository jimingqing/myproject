/**
 * 
 *
 */
package com.yrtech.wx.capp.framework.core.util;

/**
 * 工具常量类
 * 
 * @Package: com.market.portal.util
 * @ClassName: UtilConstant
 * @author wanghui
 */
public class UtilConstant
{
    /**
     * 电信手机号码号段
     */
    public final static String TELECOM_PHONE_SEGEMENT = "133|153|180|189";

    /**
     * 联通手机号码号段
     */
    public final static String UNICOM_PHONE_SEGEMENT = "130|131|132|155|156|185|186|145";

    /**
     * 移动手机号码号段
     */
    public final static String MOBILE_PHONE_SEGEMENT = "134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|187|188";

    /**
     * 防SQL注入关键字定义
     */
    public final static String[] INJ_STRA = { "*", "script", "mid", "master", "truncate", "char", "insert", "select",
            "delete", "update", "declare", "iframe", "'", "onreadystatechange", "alert", "atestu", "xss" };

    public final static String ILLEGALSTR = "%\\/()><;#-";
}
