package com.yrtech.wx.capp.framework.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

/**
 * 
 * 通用工具类
 * 
 * @Package: com.market.portal.util
 * @ClassName: Util
 * @author wanghui
 * @date 2012-6-28 上午9:24:54
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class Util
{
    private static Logger logger = Logger.getLogger(Util.class);

    /**
     * 
     * 私有构造方法
     * 
     * @author wanghui
     * @date 2012-6-28 上午9:33:26
     * @version: V1.0
     * 
     */
    private Util()
    {

    }

    /**
     * 存放主键ID
     */
    private static int preID = 0;

    /**
     * 
     * 获得固定格式字符串，如主键ID
     * 
     * 格式：yyyyMMddHHmmss+6位序列号
     * 
     * 序列号格式：000001,000002，000003依次类推，到达999999后，重新从000001循环
     * 
     * @author wanghui
     * @date 2012-6-27 下午2:25:23
     * @version: V1.0
     * 
     * @return 固定格式字符串
     */
    public synchronized static String getPreID()
    {
        preID++;

        if (preID >= 1000000)
        {
            preID = 1;
        }

        String strPreID = Integer.toString(preID);

        int len = strPreID.length();

        char zero = '0';

        if (len != 5)
        {
            for (int i = 0; i < 6 - len; i++)
            {
                strPreID = zero + strPreID;
            }
        }

        String date = DateOper.now2String("yyyyMMddHHmmss");

        return date + strPreID;
    }

    /**
     * 
     * 校验对象是否为空
     * 
     * @author wanghui
     * @date 2012-6-27 下午3:06:45
     * @version: V1.0
     * 
     * @param obj
     *            被校验的对象
     * @return 若为null，返回true，否则返回false
     */
    public static boolean isNull(Object obj)
    {
        if (obj == null)
        {
            return true;
        }

        return false;
    }

    /**
     * 
     * 金额字符串元转分
     * 
     * @author wanghui
     * @date 2012-6-27 下午3:43:06
     * @version: V1.0
     * 
     * @param yuan
     *            金额字符串（元）
     * @return 金额字符串（分）
     */
    public static String yuanToFen(String yuan)
    {
        String fen = "";

        if (yuan == null || "".equals(yuan.trim()) || ".".equals(yuan.trim()))
        {
            return "0";
        }

        BigDecimal y = new BigDecimal(yuan.trim());
        BigDecimal m = new BigDecimal("100");

        fen = y.multiply(m).toString();

        return removeNumZero(fen);
    }

    /**
     * 
     * 金额字符串分转元，最大精确到2位小数
     * 
     * @author wanghui
     * @date 2012-6-27 下午3:44:03
     * @version: V1.0
     * 
     * @param fen
     *            金额字符串（分）
     * @return 金额字符串（元）
     */
    public static String fenToYuan(String fen)
    {
        String yuan = "";

        if (fen == null || "".equals(fen.trim()) || ".".equals(fen.trim()))
        {
            return "0";
        }

        BigDecimal f = new BigDecimal(fen.trim());
        BigDecimal m = new BigDecimal("100");

        yuan = f.divide(m, 2, BigDecimal.ROUND_DOWN).toString();

        return removeNumZero(yuan);
    }

    /**
     * 
     * 校验一个数组是否为空
     * 
     * @author wanghui
     * @date 2012-6-27 下午3:47:54
     * @version: V1.0
     * 
     * @param os
     *            被校验的数组对象
     * @return 若果为空或长度为0，则返回true，否则返回false
     */
    public static boolean arrayIsNull(Object[] os)
    {
        if (os == null || os.length == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 
     * 取得中文字符长度
     * 
     * @author wanghui
     * @date 2012-6-27 下午4:15:49
     * @version: V1.0
     * 
     * @param chnChars
     *            中文字符串
     * @param incode
     *            字符串编码格式
     * @return 字符长度。 出现异常，则返回0
     */
    public static int getChnCharLength(String chnChars, String incode)
    {
        int len = 0;

        try
        {
            len = chnChars.getBytes(incode).length;

            logger.debug(chnChars + "转为" + incode + "类型后的长度为：" + len);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("转码异常:", e);
        }

        return len;
    }

    /**
     * 截取部分返回XML字符串(3000长度以内)
     * 
     * @author wanghui
     * @date 2012-6-27 下午4:24:41
     * @version: V1.0
     * 
     * @param xmlRsp
     *            要处理的字符串
     * 
     * @return 截取后的字符串
     */
    public static String getPartOfXmlRsp(String xmlRsp)
    {
        if (getChnCharLength(xmlRsp, "GBK") > 4000)
        {
            return xmlRsp.substring(0, 3000);
        }

        return xmlRsp;
    }

    /**
     * 
     * 删除数值字符串中多余的0和小数点
     * 
     * @author wanghui
     * @date 2012-7-3 下午8:31:22
     * @version: V1.0
     * 
     * @param s
     *            被处理的数值字符串
     * @return 处理后的字符串
     */
    public static String removeNumZero(String s)
    {

        if (s.charAt(0) == '.')
        {
            s = "0" + s;
        }

        /**
         * 处理特殊情况:
         * 
         * 1)前面多个0的情况,例如001111000.00
         * 
         * 2)s="0"
         * 
         * 3)s="0.0" 或全为0
         */
        if (s.charAt(0) == '0' && !"0".equals(s))
        {
            DecimalFormat df = new DecimalFormat("0.000000");
            s = df.format(Double.valueOf(s));
        }

        if ("0".equals(s) || "0.0".equals(s))
        {
            return "0";
        }

        return removeTailZero(s);
    }

    /**
     * 
     * 删除数值字符串中多余的0和小数点
     * 
     * 例如：0000111100.0000，处理后为：111100
     * 
     * @author wanghui
     * @date 2012-7-3 下午5:43:13
     * @version: V1.0
     * 
     * @param s
     *            被处理的数值字符串
     * @return 处理后的字符串
     */
    private static String removeTailZero(String s)
    {
        int len = 0;

        len = s.length();
        for (int ii = 0; ii < len; ii++) // 删除最前面的0，小数点前一位0除外
        {
            if (s.charAt(ii) == '0' && s.charAt(ii + 1) != '.')
            {
                s = s.substring(ii + 1);

                removeTailZero(s);
            }
            else
            {
                break;
            }
        }

        if (s.indexOf('.') >= 0) // 如果有小数点
        {

            len = s.length();
            for (int ii = len - 1; ii >= 0; ii--) // 删除最后面的0
            {
                if (s.charAt(ii) == '0')
                {
                    s = s.substring(0, ii);

                    removeTailZero(s);
                }
                else
                {
                    break;
                }
            }

            len = s.length();
            if (s.charAt(len - 1) == '.') // 删除最后一位的小数点
            {
                s = s.substring(0, len - 1);
            }
        }

        return s;
    }
}
