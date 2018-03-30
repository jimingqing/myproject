package com.yrtech.wx.capp.framework.core.util;

import java.util.Random;

/**
 * 
 * 随机字符串操作工具类
 * 
 * @Package: com.market.portal.util
 * @ClassName: RandomStringUtils
 * @author wanghui
 * @date 2012-6-16 下午7:27:47
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class RandomStringUtils
{
    private static final Random RANDOM = new Random();

    public static String random(int count)
    {
        return random(count, false, false);
    }

    /**
     * 
     * 获取指定个数的Ascii字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:28:21
     * @version: V1.0
     * 
     * @param count
     *            字符个数
     * @return 指定个数Ascii字符
     */
    public static String randomAscii(int count)
    {
        return random(count, 32, 127, false, false);
    }

    /**
     * 
     * 获取指定个数的字母
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:33:20
     * @version: V1.0
     * 
     * @param count
     *            字母个数
     * @return 指定个数的字母
     */
    public static String randomAlphabetic(int count)
    {
        return random(count, true, false);
    }

    /**
     * 
     * 获取指定个数的字母或数字
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:43:27
     * @version: V1.0
     * 
     * @param count
     *            字母或数字个数
     * @return 指定个数的字母、数字
     */
    public static String randomAlphanumeric(int count)
    {
        return random(count, true, true);
    }

    /**
     * 
     * 获取指定个数的数字
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:45:17
     * @version: V1.0
     * 
     * @param count
     *            数字个数
     * @return 指定个数的数字
     */
    public static String randomNumeric(int count)
    {
        return random(count, false, true);
    }

    /**
     * 
     * 获取指定个数的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:47:02
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param letters
     *            是否含有字母
     * @param numbers
     *            是否含有数字
     * @return 指定个数的字符
     */
    public static String random(int count, boolean letters, boolean numbers)
    {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * 
     * 获取指定个数的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:50:05
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @param letters
     *            是否含有字母
     * @param numbers
     *            是否含有数字
     * @return 指定个数的字符
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers)
    {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    /**
     * 
     * 从指定字符数组中获取一定数量的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:54:41
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @param letters
     *            是否包含字母
     * @param numbers
     *            是否包含数字
     * @param chars
     *            字符数组
     * @return 获取到的字符串
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars)
    {
        return random(count, start, end, letters, numbers, chars, RANDOM);
    }

    /**
     * 
     * 从指定字符数组中获取一定数量的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:57:03
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @param letters
     *            是否包含字母
     * @param numbers
     *            是否包含数字
     * @param chars
     *            字符数组
     * @param random
     *            指定随即函数
     * @return 获取到的字符串
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars,
            Random random)
    {
        if (count == 0)
            return "";

        if (count < 0)
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");

        if ((start == 0) && (end == 0))
        {
            end = 123;
            start = 32;
            if ((!(letters)) && (!(numbers)))
            {
                start = 0;
                end = 2147483647;
            }
        }
        StringBuffer buffer = new StringBuffer();
        int gap = end - start;
        while (count-- != 0)
        {
            char ch;
            if (chars == null)
                ch = (char) (random.nextInt(gap) + start);
            else
                ch = chars[(random.nextInt(gap) + start)];

            if (((letters) && (numbers) && (Character.isLetterOrDigit(ch))) || ((letters) && (Character.isLetter(ch)))
                    || ((numbers) && (Character.isDigit(ch))) || ((!(letters)) && (!(numbers))))
                buffer.append(ch);
            else
                ++count;
        }

        return buffer.toString();
    }

    /**
     * 
     * 从指定字符串中获取指定数量的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:58:47
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param chars
     *            字符串
     * @return 获取到的字符串
     */
    public static String random(int count, String chars)
    {
        if (chars == null)
            return random(count, 0, 0, false, false, null, RANDOM);

        return random(count, chars.toCharArray());
    }

    /**
     * 
     * 从指定字符数组中获取指定数量的字符
     * 
     * @author wanghui
     * @date 2012-6-16 下午7:59:53
     * @version: V1.0
     * 
     * @param count
     *            个数
     * @param chars
     *            字符数组
     * @return 获取到的字符串
     */
    public static String random(int count, char[] chars)
    {
        if (chars == null)
            return random(count, 0, 0, false, false, null, RANDOM);

        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }
}