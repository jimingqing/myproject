/**
 * 
 *
 */
package com.yrtech.wx.capp.framework.core.util;

import java.math.BigDecimal;

/**
 * 数值类型工具类
 * 
 * @Package: com.market.portal.util
 * @ClassName: NumUtil
 * @author wanghui
 * @date 2012-6-27 下午2:12:48
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class NumUtil
{

    private NumUtil()
    {

    }

    /**
     * 
     * Long型转换为int型
     * 
     * @author wanghui
     * @date 2012-6-2 下午3:13:27
     * @version: V1.0
     * 
     * @param l
     *            被转换的Long值
     * @param d
     *            默认值
     * @return Long对应的int型值或默认值d
     */
    public static int long2Int(Long l, int d)
    {
        int i = d;
        try
        {
            i = Integer.parseInt(String.valueOf(l));
        }
        catch (Exception e)
        {
        }
        return i;
    }

    /**
     * 
     * Long型转换为int型
     * 
     * @author wanghui
     * @date 2012-6-4 上午11:43:50
     * @version: V1.0
     * 
     * @param l
     *            被转换的Long值
     * @return Long对应的int型值或默认值0
     */
    public static int long2Int(Long l)
    {
        return long2Int(l, 0);
    }

    /**
     * 
     * double类型四舍五入
     * 
     * @author wanghui
     * @date 2012-6-27 下午2:19:44
     * @version: V1.0
     * 
     * @param num
     *            要调整的数值
     * @param x
     *            保留小数位数
     * @return 四舍五入后的double值，如果x<0，则返回原值num
     */
    public static double round(double num, int x)
    {
        if (x < 0)
        {
            return num;
        }

        BigDecimal b = new BigDecimal(num);
        return b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
