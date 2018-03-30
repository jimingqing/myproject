package com.yrtech.wx.capp.framework.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * 字符串工具类
 * 
 * @Package: com.market.portal.util
 * @ClassName: StringUtil
 * @author wanghui
 * @date 2012-5-30 下午2:19:41
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 */
public class StringUtil
{

    private static Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * 私有构造方法
     */
    private StringUtil()
    {

    }

    /**
     * 
     * 字符串补全
     * 
     * @author wanghui
     * @date 2012-7-26 下午3:41:31
     * @version: V1.0
     * 
     * @param str
     *            源字符串
     * @param len
     *            字符串总长度
     * @param defaultChar
     *            补全字符
     * @return 补全后的字符串
     */
    public static String fillStr(String str, int len, char defaultChar)
    {
        int strLen = 0;

        if (str != null)
        {
            strLen = str.length();
        }

        StringBuffer targetstr = new StringBuffer(str);

        for (int ii = strLen; ii < len; ii++)
        {
            targetstr.insert(0, defaultChar);
        }

        return targetstr.toString();
    }

    /**
     * 
     * 校验字符串是否为null或空字符串
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:58:05
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return true-是，false-否
     */
    public static boolean empty(String str)
    {
        return str == null || "".equals(str.trim());
    }

    /**
     * 
     * 验证接口返回的是否为空，为空抛出异常
     * 
     * @author wanghui
     * @date 2012-6-2 下午2:57:44
     * @version: V1.0
     * 
     * @param str
     *            验证的参数字符串
     * @param remark
     *            参数备注
     * @return
     * @throws Exception
     */
    public static String checkEmpty(String str, String remark) throws Exception
    {
        if (str == null || "".equals(str.trim()))
        {
            throw new Exception("接口返回参数异常，" + remark + "为空");
        }
        return str;
    }

    /**
     * 
     * 按长度分割字符串
     * 
     * @author wanghui
     * @date 2012-5-30 下午2:46:15
     * @version: V1.0
     * 
     * @param content
     *            被分割的字符串
     * @param len
     *            指定分割长度
     * @return 分割后生成的字符串数组或null， 如果len<=0，则去content.length
     */
    public static String[] split(String content, int len)
    {
        if (empty(content))
        {
            return null;
        }

        int len2 = content.length();

        if (len <= 0)
        {
            len = len2;
        }

        if (len2 <= len)
        {
            return new String[] { content };
        }
        else
        {
            int i = len2 / len;
            if (len2 % len != 0) // u-keld-2012-7-24 只有非整除时，i+1
            {
                i++;
            }

            String[] strA = new String[i];

            int j = 0;
            int begin = 0;
            int end = 0;
            while (j < i)
            {
                begin = j * len;
                end = (j + 1) * len;
                if (end > len2)
                    end = len2;
                strA[j] = content.substring(begin, end);
                j = j + 1;
            }
            return strA;
        }
    }

    /**
     * 
     * 根据指定字符分割字符串
     * 
     * @author wanghui
     * @date 2012-6-2 下午3:38:42
     * @version: V1.0
     * 
     * @param str
     *            被分割的字符串
     * @param delims
     *            指定分隔字符
     * @return 分割后的String[]，如果入参为null或""，则返回null
     */
    public static final String[] split(String str, String delims)
    {
        if (empty(str) || empty(delims))
        {
            return null;
        }

        StringTokenizer st = new StringTokenizer(str, delims);
        ArrayList<String> list = new ArrayList<String>();
        for (; st.hasMoreTokens(); list.add(st.nextToken()))
            ;

        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 
     * 字节码转换成16进制字符串(以":"分割) 内部调试使用
     * 
     * @author wanghui
     * @date 2012-5-30 下午2:58:44
     * @version: V1.0
     * 
     * @param b
     *            字节码数组
     * @return 转换后的字符串，字符间以":"分割
     */
    public static String byte2hex(byte[] b)
    {
        StringBuffer hs = new StringBuffer();
        String stmp = "";

        for (int n = 0; n < b.length; n++)
        {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1)
            {
                hs.append("0").append(stmp);
            }
            else
            {
                hs.append(stmp);
            }

            if (n < b.length - 1)
            {
                hs.append(":");
            }
        }

        return hs.toString().toUpperCase();
    }

    /**
     * 
     * 字节码转换成16进制字符串 内部调试使用
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:07:58
     * @version: V1.0
     * 
     * @param b
     *            字节码数组
     * @return 转换后的字符串
     */
    public static String byte2string(byte[] b)
    {
        StringBuffer hs = new StringBuffer();
        String stmp = "";

        for (int n = 0; n < b.length; n++)
        {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
            {
                hs.append("0").append(stmp);
            }
            else
            {
                hs.append(stmp);
            }
        }

        return hs.toString().toUpperCase();
    }

    /**
     * 
     * 字符串转换为byte[]
     * 
     * @author wanghui
     * @date 2012-6-2 下午3:04:15
     * @version: V1.0
     * 
     * @param hs
     *            被转换的字符串
     * @return 转换后的byte[]
     */
    public static byte[] string2byte(String hs)
    {
        byte[] b = new byte[hs.length() / 2];
        for (int i = 0; i < hs.length(); i = i + 2)
        {
            String sub = hs.substring(i, i + 2);
            byte bb = new Integer(Integer.parseInt(sub, 16)).byteValue();
            b[i / 2] = bb;
        }
        return b;
    }

    /**
     * 
     * 根据指定编码，对url进行编码
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:28:41
     * @version: V1.0
     * 
     * @param str
     *            转码前的url
     * @param code
     *            指定的编码，默认为utf8
     * @return 转码后的url或null
     */
    public static String urlEncode(String str, String code)
    {
        if (empty(str))
        {
            return null;
        }

        if (empty(code))
        {
            code = "utf8";
        }

        try
        {
            return URLEncoder.encode(str, code);
        }
        catch (Exception ex)
        {
            ex.fillInStackTrace();
            return null;
        }
    }

    /**
     * 
     * 根据指定编码，对url进行解码
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:32:31
     * @version: V1.0
     * 
     * @param str
     *            被转码的url
     * @param code
     *            指定的编码，默认为utf8
     * @return 解码后的url或null
     */
    public static String urlDecode(String str, String code)
    {
        if (empty(str))
        {
            return null;
        }

        if (empty(code))
        {
            code = "utf8";
        }

        try
        {
            return URLDecoder.decode(str, code);
        }
        catch (Exception ex)
        {
            ex.fillInStackTrace();
            return null;
        }
    }

    /**
     * 
     * 将字符串去空格，若为null，则转换为""
     * 
     * @author wanghui
     * @date 2012-6-2 下午3:07:46
     * @version: V1.0
     * 
     * @param str
     *            被转换的字符串
     * @return 转换后的字符串
     */
    public static String nvl(String str)
    {
        return str != null ? str.trim() : "";
    }

    /**
     * 
     * 字符串转int型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:42:47
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @param d
     *            默认值
     * @return 字符串对应的int型值或默认值d
     */
    public static int parseInt(String str, int d)
    {
        int i = d;
        try
        {
            i = Integer.parseInt(str);
        }
        catch (Exception e)
        {
            logger.error(e);
        }

        return i;
    }

    /**
     * 
     * 字符串转int型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:44:24
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @param d
     *            默认值
     * @return 字符串对应的int型值或默认值0
     */
    public static int parseInt(String str)
    {
        return parseInt(str, 0);
    }

    /**
     * 
     * 字符串转long型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:44:50
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @param d
     *            默认值
     * @return 字符串对应的long型值或默认值d
     */
    public static long parseLong(String str, long d)
    {
        long l = d;
        try
        {
            l = Long.parseLong(str);
        }
        catch (Exception e)
        {
        }
        return l;
    }

    /**
     * 
     * 字符串转long型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:44:50
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @return 字符串对应的long型值或默认值0
     */
    public static long parseLong(String str)
    {
        return parseLong(str, 0L);
    }

    /**
     * 
     * 字符串转double型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:48:21
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @param t
     *            默认值
     * @return 字符串对应的double型值或默认值t
     */
    public static double parseDouble(String str, double t)
    {
        double tmp = 0.0;
        try
        {
            tmp = Double.parseDouble(str.trim());
        }
        catch (Exception e)
        {
            tmp = t;
        }
        return tmp;
    }

    /**
     * 
     * 字符串转double型
     * 
     * @author wanghui
     * @date 2012-5-30 下午3:52:18
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @return 字符串对应的double型值或默认值1.0
     */
    public static double parseDouble(String str)
    {
        return parseDouble(str, 1.0);
    }

    /**
     * 
     * 字符串转boolean型
     * 
     * @author wanghui
     * @date 2012-5-30 下午4:00:46
     * @version: V1.0
     * 
     * @param str
     *            被转字符串
     * @return 如果字符串首字符为"1", "T", "Y", "t", "y"则为true，否则为false
     */
    public static boolean parseBoolean(String str)
    {
        if (empty(str))
        {
            return false;
        }

        switch (str.charAt(0))
        {
            case 49: // '1'
            case 84: // 'T'
            case 89: // 'Y'
            case 116: // 't'
            case 121: // 'y'

                return true;
        }

        return false;
    }

    /**
     * 
     * 将字符串转换为整型数
     * 
     * @author wanghui
     * @date 2012-6-27 下午2:49:59
     * @version: V1.0
     * 
     * @param str
     *            要转换的字符串
     * @return 转换后的整型数。如果如此为null或空字符串，则返回0
     */
    public static int string2Int(String str)
    {
        if (empty(str))
        {
            return 0;
        }

        BigDecimal amcount = new BigDecimal(str.trim());

        String samcount = amcount.toString();

        if (samcount.indexOf(".") != -1)
        {
            samcount = samcount.substring(0, samcount.indexOf("."));
        }

        return Integer.parseInt(samcount);
    }

    /**
     * 
     * 将浮点字符串(元)转换为分，即扩大100倍,然后取整
     * 
     * @author wanghui
     * @date 2012-6-27 下午2:57:37
     * @version: V1.0
     * 
     * @param str
     *            要转换的字符串
     * @return 转换后的整型数
     */
    public static int string2Fen(String str)
    {
        if (empty(str))
        {
            return 0;
        }

        BigDecimal amcount = new BigDecimal(str.trim());

        BigDecimal a = new BigDecimal("100");

        String samcount = amcount.multiply(a).toString();

        return string2Int(samcount);
    }

    /**
     * 
     * 判断字符串是否int串
     * 
     * @author wanghui
     * @date 2012-6-2 下午4:54:39
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return true-该串为int串，false-非int串
     */
    public static boolean validateInt(String str)
    {
        if (str == null || str.trim().equals(""))
            return false;

        char c;
        for (int i = 0, l = str.length(); i < l; i++)
        {
            c = str.charAt(i);
            if (!((c >= '0') && (c <= '9')))
                return false;
        }

        return true;
    }

    /**
     * 
     * 判断字符串是否Double串
     * 
     * @author wanghui
     * @date 2012-6-2 下午4:59:20
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return true-该串为int串，false-非int串
     */
    public static boolean validateDouble(String str)
    {
        if (str == null)
            return false;
        char c;
        int k = 0;
        for (int i = 0, l = str.length(); i < l; i++)
        {
            c = str.charAt(i);
            if (!((c >= '0') && (c <= '9')))
            {
                if (!(i == 0 && (c == '-' || c == '+')))
                {
                    if (!(c == '.' && i < l - 1 && k < 1))
                        return false;
                    else
                        k++;
                }
            }
        }

        return true;
    }

    /**
     * 
     * 截取指定长度(length-3)个字符，多余字符用"..."代替
     * 
     * @author wanghui
     * @date 2012-5-30 下午5:16:22
     * @version: V1.0
     * 
     * @param str
     *            被截取的字符串
     * @param len
     *            截取长度
     * @return 截取后的字符串或null，如果len<=0，则返回原str值
     */
    public static String substring(String str, int len)
    {
        if (empty(str))
        {
            return null;
        }

        if (len <= 0)
        {
            return str;
        }

        if (str.length() > len)
        {
            return (str.substring(0, len - 3) + "...");
        }
        else
        {
            return str;
        }
    }

    /**
     * 
     * 字符串编码转换
     * 
     * @author wanghui
     * @date 2012-5-30 下午5:35:51
     * @version: V1.0
     * 
     * @param s
     *            被转换的字符串
     * @param code1
     *            源字符编码
     * @param code2
     *            目的字符编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String covertCode(String s, String code1, String code2) throws UnsupportedEncodingException
    {
        if (s == null)
            return null;
        else if (s.trim().equals(""))
            return "";
        else
            return new String(s.getBytes(code1), code2);
    }

    /**
     * 
     * 字符串编码转换，GB2312转为ISO8859-1
     * 
     * @author wanghui
     * @date 2012-5-30 下午5:32:46
     * @version: V1.0
     * 
     * @param s
     *            被转换的字符串
     * @return 转换后的字符串或null或""
     * @throws UnsupportedEncodingException
     */
    public static String gbToIso(String s) throws UnsupportedEncodingException
    {
        return covertCode(s, "GB2312", "ISO8859-1");
    }

    /**
     * 
     * 字符串编码转换，UTF-8转为ISO8859-1
     * 
     * @author wanghui
     * @date 2012-5-30 下午5:42:28
     * @version: V1.0
     * 
     * @param s
     *            被转换的字符串
     * @return 转换后的字符串或null或""
     * @throws UnsupportedEncodingException
     */
    public static String utf8ToIso(String s) throws UnsupportedEncodingException
    {
        return covertCode(s, "UTF-8", "ISO8859-1");
    }

    /**
     * 
     * 字符串编码转换，GB2312转为Unicode
     * 
     * @author wanghui
     * @date 2012-5-30 下午5:48:28
     * @version: V1.0
     * 
     * @param s
     *            被转换的字符串
     * @return 转换后的字符串或null
     */
    public static String gbToUnicode(String s)
    {
        try
        {
            StringBuffer out = new StringBuffer("");
            byte[] bytes = s.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2)
            {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++)
                {
                    out.append("0");
                }
                out.append(str);
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                for (int j = str1.length(); j < 2; j++)
                {
                    out.append("0");
                }

                out.append(str1);
            }

            return out.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * 用指定字符串替换字符数组中的某些字符
     * 
     * @author wanghui
     * @date 2012-6-2 下午5:27:38
     * @version: V1.0
     * 
     * @param obj
     *            字符串数组
     * @param oldString
     *            原字符串
     * @param newString
     *            新字符串
     * @return 替换后的字符串数字
     */
    public static final String[] replaceAll(String[] obj, String oldString, String newString)
    {
        if (obj == null)
        {
            return null;
        }

        int length = obj.length;
        String[] returnStr = new String[length];

        for (int i = 0; i < length; i++)
        {
            returnStr[i] = replaceAll(obj[i], oldString, newString);
        }
        return returnStr;
    }

    /**
     * 
     * 用指定字符串替换字符串中的某些字符
     * 
     * @author wanghui
     * @date 2012-6-2 下午5:27:44
     * @version: V1.0
     * 
     * @param s0
     *            字符串
     * @param oldstr
     *            原字符串
     * @param newstr
     *            新字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String s0, String oldstr, String newstr)
    {
        if (s0 == null || s0.trim().equals(""))
            return null;
        StringBuffer sb = new StringBuffer(s0);
        int begin = 0;
        // int from = 0;
        begin = s0.indexOf(oldstr);
        while (begin > -1)
        {
            sb = sb.replace(begin, begin + oldstr.length(), newstr);
            s0 = sb.toString();
            begin = s0.indexOf(oldstr, begin + newstr.length());
        }
        return s0;
    }

    /**
     * 将特定字符替换为HTML字符(&,<,>,\r\n,\n,\t,' ')
     * 
     * @author wanghui
     * @date 2012-6-2 下午6:06:25
     * @version: V1.0
     * 
     * @param str
     *            被替换的字符串
     * @return 替换后的字符串
     */
    public static String toHtml(String str)
    {
        String html = str;
        if (str == null || str.length() == 0)
        {
            return str;
        }
        html = replaceAll(html, "&", "&amp;");
        html = replaceAll(html, "<", "&lt;");
        html = replaceAll(html, ">", "&gt;");
        html = replaceAll(html, "\r\n", "\n");
        html = replaceAll(html, "\n", "<br>\n");
        html = replaceAll(html, "\t", "         ");
        html = replaceAll(html, " ", "&nbsp;");
        html = replaceAll(html, "\"", "&quot;");
        html = replaceAll(html, "'", "&apos;");
        html = replaceAll(html, "/", "&8260;");

        return html;
    }

    /**
     * 
     * 字符串替换(区分大小写)
     * 
     * @author wanghui
     * @date 2012-6-2 下午6:12:35
     * @version: V1.0
     * 
     * @param line
     *            指定字符串
     * @param oldString
     *            原字符串
     * @param newString
     *            新字符串
     * @return 替换后的字符串
     */
    public static final String replace(String line, String oldString, String newString)
    {
        if (line == null)
        {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0)
        {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0)
            {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * 
     * 字符串替换(忽略大小写)
     * 
     * @author wanghui
     * @date 2012-6-4 上午9:53:55
     * @version: V1.0
     * 
     * @param line
     *            指定字符串
     * @param oldString
     *            原字符串
     * @param newString
     *            新字符串
     * @return 替换后的字符串
     */
    public static final String replaceIgnoreCase(String line, String oldString, String newString)
    {
        if (line == null)
        {
            return null;
        }

        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();

        int i = 0;

        if ((i = lcLine.indexOf(lcOldString, i)) >= 0)
        {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0)
            {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    private static Random randGen = null;
    private static Object initLock = new Object();
    private static char[] numbersAndLetters = null;

    /**
     * 
     * 获取指定长度的数字+字母的随机串
     * 
     * @author wanghui
     * @date 2012-6-4 上午10:06:15
     * @version: V1.0
     * 
     * @param length
     *            随机串长度
     * @return 随机字符串或null
     */
    public static final String randomString(int length)
    {
        if (length < 1)
        {
            return null;
        }
        // Init of pseudo random number generator.
        if (randGen == null)
        {
            synchronized (initLock)
            {
                if (randGen == null)
                {
                    randGen = new Random();
                    // Also initialize the numbersAndLetters array
                    numbersAndLetters = ("0123456789").toCharArray();
                }
            }
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++)
        {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
        }
        return new String(randBuffer);
    }
    
    static public String double2amt(String s_amt) {
		return (double2amt((new Double(s_amt)).doubleValue()));
	}

	static public String double2amt(double f) {
		java.text.DecimalFormat dmf = new java.text.DecimalFormat("0.00");
		return (dmf.format(f));
	}
	
	public static boolean validAmt(String TransAmt) {
		int ret, len = TransAmt.length();
		if(len < 4)//小数点后必须有2位数字
			return false;
		char ch;
		for (ret = 0; ret < len; ret++) {
			ch = TransAmt.charAt(ret);
			if (ret == 0) {
				if (ch > '9' || ch < '0')
					return false;
				if (ch == '0' && len != 4)
					return false;
			}
			if (ret == len - 3) {
				if (ch != '.')
					return false;
				else
					continue;
			}
			if (ch > '9' || ch < '0')
				return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否全是数字组合
	 * 
	 * @return true,是 false,否
	 */
	static public boolean isNum(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = sb.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}
	
}
