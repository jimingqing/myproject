package com.yrtech.wx.capp.framework.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 公共核心校验类, 提供公共内容校验方法
 * 
 * @Package: com.market.portal.util
 * @ClassName: CheckUtil
 * @author wanghui
 * @date 2012-5-28 上午10:07:40
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 */
public class CheckUtil
{

    private static Logger logger = Logger.getLogger(CheckUtil.class);

    private static String TELECOM_PHONE_REGX = "^(" + UtilConstant.TELECOM_PHONE_SEGEMENT + ")\\d{8}$";
    private static String UNICOM_PHONE_REGX = "^(" + UtilConstant.UNICOM_PHONE_SEGEMENT + ")\\d{8}$";
    private static String MOBILE_PHONE_REGX = "^(" + UtilConstant.MOBILE_PHONE_SEGEMENT + ")\\d{8}$";
    private static String TELFAX_PHONE_REGX = "^((0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    private static String POST_NO_REGX = "^\\d{6}$";

    private static String IS_NUMERIC_REGX = "[0-9]*";
    private static String IS_LOWER_LETTER_REGX = "[a-z]*";
    private static String IS_UPPER_LETTER_REGX = "[A-Z]*";
    private static String IS_LETTER_REGX = "[a-zA-Z]*";
    private static String IS_LETTER_NUM_REGX = "[a-zA-Z0-9]*";
    private static String IS_DATE_REGX = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    private static String IS_EMAIL_REGX = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+[.]([a-zA-Z0-9_-])+";

    /**
     * 私有构造方法
     */
    private CheckUtil()
    {

    }
    
    /**
     * 检查手机号码合法性
     * @param str
     * @return
     */
    public static boolean isPhoneNo(String str)
    {
    	if(patternUtil(TELECOM_PHONE_REGX, str) 
    			|| patternUtil(MOBILE_PHONE_REGX, str)
    			|| patternUtil(UNICOM_PHONE_REGX, str)){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 
     * 校验电信手机号码合法性
     * 
     * @author wanghui
     * @date 2012-5-28 下午1:51:52
     * @version: V1.0
     * 
     * @param str
     *            被校验的手机号码串
     * @return true-成功，false-失败
     */
    public static boolean isTelPhNo(String str)
    {
        return patternUtil(TELECOM_PHONE_REGX, str);
    }

    /**
     * 
     * 校验联通手机号码合法性
     * 
     * @author wanghui
     * @date 2012-5-28 下午1:51:52
     * @version: V1.0
     * 
     * @param str
     *            被校验的手机号码串
     * @return true-成功，false-失败
     */
    public static boolean isUniPhNo(String str)
    {
        return patternUtil(UNICOM_PHONE_REGX, str);
    }

    /**
     * 
     * 校验移动手机号码合法性
     * 
     * @author wanghui
     * @date 2012-5-28 下午1:51:52
     * @version: V1.0
     * 
     * @param str
     *            被校验的手机号码串
     * @return true-成功，false-失败
     */
    public static boolean isMbPhNo(String str)
    {
        return patternUtil(MOBILE_PHONE_REGX, str);
    }

    /**
     * 
     * 校验固话/传真号码合法性
     * 
     * @author wanghui
     * @date 2012-5-28 下午1:51:52
     * @version: V1.0
     * 
     * @param str
     *            被校验的固话/传真号码串
     * @return true-成功，false-失败
     */
    public static boolean isTelFax(String str)
    {
        return patternUtil(TELFAX_PHONE_REGX, str);
    }

    /**
     * 
     * 校验邮编合法性
     * 
     * @author wanghui
     * @date 2012-5-28 下午1:51:52
     * @version: V1.0
     * 
     * @param str
     *            被校验的邮编号码串
     * @return true-成功，false-失败
     */
    public static boolean isPost(String str)
    {
        return patternUtil(POST_NO_REGX, str);
    }

    /**
     * 
     * 判断字符串是否为数字[0-9]
     * 
     * @author wanghui
     * @date 2012-5-28 上午10:33:21
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return true-成功，false-失败
     */
    public static boolean isNumeric(String str)
    {
        return patternUtil(IS_NUMERIC_REGX, str);
    }

    /**
     * 
     * 判断字符串是否为日期格式
     * 
     * @author wanghui
     * @date 2012-5-28 上午10:33:10
     * @version: V1.0
     * 
     * @param str
     *            被校验的日期字符串
     * @return ture-成功， false-失败
     */
    public static boolean isDate(String str)
    {
        return patternUtil(IS_DATE_REGX, str);
    }

    /**
     * 
     * 检查电子邮箱的合法性
     * 
     * @author wanghui
     * @date 2012-5-30 下午2:30:37
     * @version: V1.0
     * 
     * @param str
     *            被校验的电子邮箱字符串
     * @return ture-成功， false-失败
     */
    public static boolean isEmail(String str)
    {
        return patternUtil(IS_EMAIL_REGX, str);
    }

    /**
     * 
     * 检验字符串所有字符是否全为小写字母
     * 
     * @author wanghui
     * @date 2012-6-4 上午11:22:17
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return ture-成功， false-失败
     */
    public static boolean isa2z(String str)
    {
        return patternUtil(IS_LOWER_LETTER_REGX, str);
    }

    /**
     * 
     * 检验字符串所有字符是否全为大写字母
     * 
     * @author wanghui
     * @date 2012-6-4 上午11:22:17
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return ture-成功， false-失败
     */
    public static boolean isA2Z(String str)
    {
        return patternUtil(IS_UPPER_LETTER_REGX, str);
    }

    /**
     * 
     * 检验字符串所有字符是否全为英文字母
     * 
     * @author wanghui
     * @date 2012-6-4 上午11:22:17
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return ture-成功， false-失败
     */
    public static boolean isA2z(String str)
    {
        return patternUtil(IS_LETTER_REGX, str);
    }

    /**
     * 
     * 检验字符串所有字符是否全为字母或数字
     * 
     * @author wanghui
     * @date 2012-6-4 上午11:22:17
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return ture-成功， false-失败
     */
    public static boolean isA2zNum(String str)
    {
        return patternUtil(IS_LETTER_NUM_REGX, str);
    }

    /**
     * 检验字符串所有字符是否全为字母或数字或汉字
     * 
     * @author wanghui
     * @date 2012-6-2 下午3:44:20
     * @version: V1.0
     * 
     * @param str
     *            被校验的字符串
     * @return true-字符串为字母或数字或汉字，false-存在非字母,数字和汉字字符
     */
    public static boolean isA2zNumOrChinese(String s)
    {
        if (s == null || s.equals(""))
        {
            return false;
        }
        for (int i = 0; i < s.length(); i++)
        {
            if (!Character.isLetterOrDigit(s.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * 身份证合法性校验
     * 
     * @author wanghui
     * @date 2012-5-28 上午11:23:21
     * @version: V1.0
     * 
     * @param IDStr
     *            被校验的身份证号码
     * @return errorInfo ""-合法，其他-错误提示
     * @throws ParseException
     */
    public static String isIdCard(String IDStr) throws ParseException
    {
        /**
         * 返回错误信息: ""-无错误，其他-错误信息
         */
        String errorInfo = "";

        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        String Ai = "";

        if (IDStr == null || "".equals(IDStr.trim()))
        {
            errorInfo = "身份证号码为空。";
            return errorInfo;
        }

        // 身份证转换为小写
        IDStr = IDStr.toLowerCase();

        // 身份证长度合法性（15或18位）
        if (IDStr.length() != 15 && IDStr.length() != 18)
        {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }

        // 身份证最后一位是否为数字
        if (IDStr.length() == 18)
        {
            Ai = IDStr.substring(0, 17);
        }
        else if (IDStr.length() == 15)
        {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false)
        {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }

        // 身份证出生年月日合法性校验
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false)
        {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0)
        {
            errorInfo = "身份证生日不在有效范围。";
            return errorInfo;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0)
        {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0)
        {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }

        // 身份证地区编码合法性校验
        Hashtable<String, String> h = getAreaCode();
        if (h.get(Ai.substring(0, 2)) == null)
        {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }

        // 18位身份证最后一位校验位检查
        if (IDStr.length() == 18)
        {
            int TotalmulAiWi = 0;
            for (int i = 0; i < 17; i++)
            {
                TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
            }
            int modValue = TotalmulAiWi % 11;
            String strVerifyCode = ValCodeArr[modValue];
            Ai = Ai + strVerifyCode;

            if (Ai.equals(IDStr) == false)
            {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        }

        return errorInfo;
    }

    /**
     * 
     * 检查是否存在非法字符，防止SQL注入
     * 
     * @author wanghui
     * @date 2012-5-28 下午3:24:19
     * @version: V1.0
     * 
     * @param str
     *            被检查的字符串
     * @return ture-字符串中存在非法字符，false-不存在非法字符
     */
    public static boolean checkSQLInject(String str)
    {
        String[] inj_stra = UtilConstant.INJ_STRA;

        str = str.toLowerCase(); // sql不区分大小写

        for (int i = 0; i < inj_stra.length; i++)
        {
            if (str.indexOf(inj_stra[i]) >= 0)
            {
                logger.info("特殊字符，传入str=" + str + ",特殊字符：" + inj_stra[i]);
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * $&[]{}' 检查输入项是否有不合法字符 返回false代表输入字符不合法，反之相反
     * 
     * @author wanghui
     * @date 2012-6-2 下午2:34:19
     * @version: V1.0
     * 
     * @param str
     *            检查字符串
     * @return true-合法， false-存在非法字符
     */
    public static boolean checkStr(String str)
    {
        String strtmp = UtilConstant.ILLEGALSTR;

        boolean bl = true;
        if (str != null && !str.equals(""))
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (strtmp.indexOf(str.charAt(i)) > -1)
                {
                    bl = false;
                    break;
                }
            }
        }
        else
        {
            bl = false;
        }
        return bl;

    }

    /************************** 私有方法 **********************************/

    /**
     * 
     * 根据正则表达式校验字符串是否匹配
     * 
     * @author wanghui
     * @date 2012-5-28 下午2:05:38
     * @version: V1.0
     * 
     * @param regx
     *            正则表达式
     * @param str
     *            被校验字符串
     * @return true-成功，false-失败
     */
    private static boolean patternUtil(String regx, String str)
    {
        if (regx == null || "".equals(regx) || str == null || "".equals(str.trim()))
        {
            return false;
        }

        Pattern pattern = Pattern.compile(regx);

        Matcher m = pattern.matcher(str);
        if (m.matches())
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
     * 设置地区编码
     * 
     * @author wanghui
     * @date 2012-5-28 上午11:03:27
     * @version: V1.0
     * 
     * @return hashtable 全国各省份地区编码
     */
    private static Hashtable<String, String> getAreaCode()
    {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();

        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");

        return hashtable;
    }
    
	public static boolean validAmt(String TransAmt) {
		int ret, len = TransAmt.length();
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

	public static void main(String[] args) {
		System.out.println(validAmt("30.00"));
	}
}
