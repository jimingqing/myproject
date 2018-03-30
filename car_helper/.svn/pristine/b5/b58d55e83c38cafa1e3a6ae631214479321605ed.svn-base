package com.yrtech.wx.capp.framework.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * 
 * 各种日期操作
 * 
 * @Package: com.market.portal.util
 * @ClassName: DateOper
 * @author wanghui
 * @date 2012-5-28 下午01:36:29
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 */
public class DateOper{
    /**
     * 默认时间格式
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 定义时间格式1
     */
    private final static String DATE_FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日志输出
     */
    private static Logger logger = Logger.getLogger(DateOper.class);

    /**
     * 
     * 私有构造方法
     * 
     * @author wanghui
     * @date 2012-6-27 上午10:14:56
     * @version: V1.0
     * 
     */
    private DateOper()
    {

    }

    /**
     * 
     * 获取当前年份
     * 
     * @author wanghui
     * @date 2012-6-2 上午11:51:02
     * @version: V1.0
     * 
     * @return 当前年份
     */
    public static String getCurYear()
    {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.get(1));
    }

    /**
     * 
     * 获取当前月份（两位）
     * 
     * @author wanghui
     * @date 2012-6-2 上午11:52:44
     * @version: V1.0
     * 
     * @return 当前月份
     */
    public static String getCurMonth()
    {
        Calendar cal = Calendar.getInstance();

        int curMonth = cal.get(Calendar.MONTH);

        String curMonthStr = null;

        if (curMonth < 10)
        {
            curMonthStr = "0" + (curMonth + 1);
        }
        else
        {
            curMonthStr = String.valueOf(curMonth + 1);
        }

        return curMonthStr;
    }

    /**
     * 
     * 获取指定年月份的最大天数
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param yearmonth
     *            年月串（yyyyMM）
     * @return 最大天数
     */
    public static String getMaxDay(String yearmonth)
    {

        String tmp = evalTime(yearmonth, "yyyyMM", "yyyyMM", "month", 1);
        String tmp2 = tmp + "01";
        String tmp3 = evalTime(tmp2, "yyyyMMdd", "dd", "day", -1);
        return tmp3;
    }

    /**
     * 
     * 获取下月月初日期串，格式yyyyMMdd
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @return 返回下月月初日期字符串（yyyyMMdd）
     */
    public static String getNextMonthBeginDay()
    {
        String nextYear = "";
        String nextMonth = "";
        String nextDay = "01";

        int curYear = 0;
        int curMonth = 0;

        Calendar cal = Calendar.getInstance();

        curYear = cal.get(Calendar.YEAR);
        curMonth = cal.get(Calendar.MONTH);

        if (curMonth + 1 == 12) // 当月为12月时，下月应该为下一年的1月，年份应该加1
        {
            nextYear = String.valueOf(curMonth + 1);
            nextMonth = "01";
        }
        else
        {
            nextYear = String.valueOf(curYear);

            if (curMonth + 2 < 10) // 当下月为小于10月时，应该为下月的前面加0，如02到09
            {
                nextMonth = "0" + (curMonth + 2);
            }
            else
            {
                nextMonth = String.valueOf(curMonth + 2);
            }
        }

        return nextYear + nextMonth + nextDay;
    }

    /**
     * 
     * 获得上一个月的月份Calendar对象
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param cal
     * @return 上月月份对象Calendar
     */
    public static Calendar getPreviousMonth(Calendar cal)
    {
        if (cal == null)
        {
            cal = Calendar.getInstance();
        }

        cal.add(Calendar.MONTH, -1);// 减一个月，变为上一月

        return cal;
    }

    /**
     * 
     * 当前时间换为时间戳java.sql.Timestamp
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @return Timestamp时间戳对象
     * @throws ParseException
     *             异常
     */
    public static Timestamp getCurTimestamp() throws ParseException
    {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 
     * 日期字符串转换为时间戳java.sql.Timestamp
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            被转换日期字符串
     * @param format
     *            日期格式
     * @return Timestamp时间戳对象
     * @throws ParseException
     *             异常
     */
    public static Timestamp string2Timestamp(String date, String format) throws ParseException
    {
        Date d = new SimpleDateFormat(format, Locale.getDefault()).parse(date);

        return new java.sql.Timestamp(d.getTime());
    }

    /**
     * 
     * 比较两个日期的大小
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date1
     *            日期参数1
     * @param date2
     *            日期参数2
     * @return 如果date1大于date2，返回true，否则返回false
     */
    public static boolean compare(Date date1, Date date2)
    {
        if (date1.after(date2))
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
     * 比较两个日期字符串的大小
     * 
     * @author wanghui
     * @date 2012-6-26 下午2:33:59
     * @version: V1.0
     * 
     * @param date1
     *            日期参数1
     * @param format1
     *            日期格式1 默认格式：yyyy-MM-dd
     * @param date2
     *            日期参数2
     * @param format2
     *            日期格式2 默认格式：yyyy-MM-dd
     * @return 如果date1大于date2，返回true，否则返回false
     * @throws ParseException
     *             异常
     */
    public static boolean compare(String date1, String format1, String date2, String format2) throws ParseException
    {
        if (null == format1 || "".equals(format1))
        {
            format1 = "yyyy-MM-dd";
        }

        if (null == format2 || "".equals(format2))
        {
            format2 = "yyyy-MM-dd";
        }

        Date formatDate1 = (Date) new SimpleDateFormat(format1).parse(date1);

        Date formatDate2 = (Date) new SimpleDateFormat(format2).parse(date2);

        if (formatDate1.after(formatDate2))
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
     * 将指定时间转为字符串格式（默认格式：yyyy-MM-dd HH:mm:ss）
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            被转换的时间对象
     * @return 转换后的字符串
     */
    public static String date2String(Date date)
    {
        return new SimpleDateFormat(DATE_FORMAT1).format(date);
    }

    /**
     * 
     * 根据指定格式，将日期时间转换为字符串（默认格式：yyyy-MM-dd HH:mm:ss）
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            被转换的时间对象
     * @param format
     *            转换后格式，若format为null或""，则取默认格式yyyy-MM-dd HH:mm:ss
     * @return 转换后的字符串，若date为null，则返回""
     */
    public static String date2String(Date date, String format)
    {
        if (null == date)
        {
            return "";
        }

        if (null == format || "".equals(format))
        {
            format = DATE_FORMAT1;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 
     * 日期格式转化
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param srcDate
     *            源日期字符串
     * @param srcFormat
     *            源日期格式，默认为yyyy-MM-dd
     * @param destFormat
     *            目的日期格式，默认为yyyy-MM-dd
     * @return 转换格式后的日期字符串。当srcDate为null或 空字符串时，则返回空字符串；当srcDate解析错误时，则返回当前日期字符串
     */
    public static String dateFormat(String srcDate, String srcFormat, String destFormat)
    {
        if (null == srcFormat || "".equals(srcFormat))
        {
            srcFormat = "yyyy-MM-dd";
        }

        if (null == destFormat || "".equals(destFormat))
        {
            destFormat = "yyyy-MM-dd";
        }

        if (null == srcDate || "".equals(srcDate))
        {
            return "";
        }

        Calendar cal = Calendar.getInstance();

        Date srcFormatDate = null;

        try
        {
            srcFormatDate = (Date) new SimpleDateFormat(srcFormat).parse(srcDate);
        }
        catch (ParseException e)
        {
            logger.error("", e);
            srcFormatDate = new Date();
        }

        cal.setTime(srcFormatDate);

        return new SimpleDateFormat(destFormat).format(cal.getTime());

    }

    /**
     * 
     * 将当前时间转为字符串格式（默认格式：yyyy-MM-dd HH:mm:ss）
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @return 转换后的字符串
     */
    public static String now2String()
    {
        return new SimpleDateFormat(DATE_FORMAT1).format(new Date());
    }

    /**
     * 
     * 将当前时间转为字符串格式（默认格式：yyyy-MM-dd HH:mm:ss）
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param format
     *            转换后格式，若format为null或""，则取默认格式yyyy-MM-dd HH:mm:ss
     * @return 转换后的字符串
     */
    public static String now2String(String format)
    {
        if (null == format || "".equals(format))
        {
            format = DATE_FORMAT1;
        }

        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 
     * 字符串日期转化为java.util.Date（字符串格式:yyyy-MM-dd HH:mm:ss）
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param srcDate
     *            源日期字符串
     * @return 转换后的Date对象，若出现异常，则返回当前日期
     */
    public static Date string2Date(String date)
    {
        if (date == null || "".equals(date))
        {
            return new Date();
        }

        try
        {
            return new SimpleDateFormat(DATE_FORMAT1).parse(date);
        }
        catch (ParseException e)
        {
            logger.error(e);
        }

        return new Date();
    }

    /**
     * 
     * 字符串日期转化为java.util.Date
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param srcDate
     *            源日期字符串
     * @param srcFormat
     *            源日期格式，默认为yyyy-MM-dd HH:mm:ss
     * @return 转换后的Date对象。如果srcDate为空或异常，则返回当前日期
     */
    public static Date string2Date(String date, String format)
    {
        if (null == date || "".equals(date))
        {
            return new Date();
        }

        if (null == format || "".equals(format))
        {
            format = DATE_FORMAT1;
        }

        Calendar cal = Calendar.getInstance();

        Date currentTime = null;

        try
        {
            currentTime = (Date) new java.text.SimpleDateFormat(format).parse(date);
        }
        catch (ParseException e)
        {
            logger.error("", e);

            return new Date();
        }

        cal.setTime(currentTime);

        return cal.getTime();
    }

    /**
     * 
     * java.util.Date转化为java.sql.Date
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            被转换的日期对象
     * @return 转换后的日期对象, 若入参为null，则返回null
     */
    public static java.sql.Date date2SqlDate(Date date)
    {
        if (null == date)
        {
            return null;
        }

        return new java.sql.Date(date.getTime());
    }

    /**
     * 
     * java.util.Date转化为java.sql.Timestamp
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            被转换的日期对象
     * @return 转换后的时间戳对象, 若入参为null，则返回null
     */
    public static java.sql.Timestamp date2Timestamp(Date date)
    {
        if (null == date)
        {
            return null;
        }

        return new java.sql.Timestamp(date.getTime());
    }

    /**
     * 
     * 将字符串转换为java.sql.Date对象
     * 
     * @author wanghui
     * @date 2012-6-4 上午10:44:10
     * @version: V1.0
     * 
     * @param strDate
     *            原日期串
     * @param pattern
     *            转换格式
     * @return 转换后的日期对象, 若入参为null，则返回null
     * @throws ParseException
     */
    public static java.sql.Date string2SqlDate(String date, String format) throws ParseException
    {
        if (date == null || format == null || "".equals(date) | "".equals(format))
        {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date formatDate = simpleDateFormat.parse(date);

        return new java.sql.Date(formatDate.getTime());
    }

    /**
     * 
     * 判断是否是闰年
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param year
     * @return true-year是闰年，false-year不是闰年
     */
    public static boolean isLeapyear(int year)
    {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
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
     * 判断当前时间是否在两个时间点之间，时间格式：HH:mm:ss
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param time1
     *            起始时间串(HH:mm:ss)
     * @param time2
     *            终止时间串(HH:mm:ss)
     * @return true-当前时间在tiem1与time2之间，false-当前时间不在tiem1与time2之间
     */
    public static boolean checkTimeShort(String time1, String time2)
    {
        /**
         * 时间是否跨天，true-是，false-否
         */
        boolean flag = false;

        // 若开始时间>结束时间，则表示时间跨天
        if (time1.compareTo(time2) > 0)
        {
            flag = true;
        }

        // 当前时间
        Calendar calendar = Calendar.getInstance();
        Date defaultDate = calendar.getTime();

        Date date11 = string2Date(date2String(defaultDate, "yyyy-MM-dd") + " " + time1, DEFAULT_PATTERN);// 起始时间

        if (flag)
        {
            calendar.add(Calendar.DATE, 1);
            defaultDate = calendar.getTime();
        }

        Date date22 = string2Date(date2String(defaultDate, "yyyy-MM-dd") + " " + time2, DEFAULT_PATTERN);// 终止时间

        Calendar scalendar = Calendar.getInstance();
        scalendar.setTime(date11);// 起始时间

        Calendar ecalendar = Calendar.getInstance();
        ecalendar.setTime(date22);// 终止时间

        Calendar calendarnow = Calendar.getInstance();

        if (calendarnow.after(scalendar) && calendarnow.before(ecalendar))
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
     * 判断时间是否在两个时间点之间，时间格式：yyyy-MM-dd HH:mm:ss
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param time1
     *            起始时间串 (yyyy-MM-dd HH:mm:ss)
     * @param time2
     *            终止时间串 (yyyy-MM-dd HH:mm:ss)
     * @return true-当前时间在tiem1与time2之间，false-当前时间不在tiem1与time2之间
     */
    public static boolean checkTimeLong(String time1, String time2)
    {
        Date beginTime = DateOper.string2Date(time1, DATE_FORMAT1);
        Date endTime = DateOper.string2Date(time2, DATE_FORMAT1);

        // 起始时间
        Calendar scalendar = Calendar.getInstance();
        scalendar.setTime(beginTime);

        // 终止时间
        Calendar ecalendar = Calendar.getInstance();
        ecalendar.setTime(endTime);

        Calendar calendarnow = Calendar.getInstance();

        if (calendarnow.after(scalendar) && calendarnow.before(ecalendar))
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
     * 根据操作类型更改日期
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date
     *            源日期对象
     * @param type
     *            操作类型：date, year, month
     * @param value
     *            增加值(正数为增加，负数为减少)
     * @return 更改后的日期对象
     */
    public static Date changeDate(Date date, String type, int value)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        if (type.equals("month"))
        {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
        }
        else if (type.equals("date"))
        {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
        }
        else if (type.endsWith("year"))
        {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);
        }

        return calendar.getTime();
    }

    /**
     * 
     * 两日期相减，返回相差月数
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param startDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return 相差月数,startDate<endDate时，返回负数值，startDate>endDate时，返回正数值
     */
    public static int divMonth(Date startDate, Date endDate)
    {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int yearDiv = startCalendar.get(Calendar.YEAR) - endCalendar.get(Calendar.YEAR);

        int monthDiv = startCalendar.get(Calendar.MONTH) - endCalendar.get(Calendar.MONTH);

        return monthDiv + yearDiv * 12;
    }

    /**
     * 
     * 日期的算术操作，可以增加或者减少，可以某一部分进行操作 year--年 month-月 1-12 day-天 1-31 hour -小时 0-23
     * minute 分钟 0-59 second 秒 0-59 millisecond 毫秒 显示格式，可以任意组合
     * 
     * G Era designator Text AD y Year Year 1996; 96 M Month in year Month July;
     * Jul; 07 w Week in year Number 27 W Week in month Number 2 D Day in year
     * Number 189 d Day in month Number 10 F Day of week in month Number 2 E Day
     * in week Text Tuesday; Tue a Am/pm marker Text PM H Hour in day (0-23)
     * Number 0 k Hour in day (1-24) Number 24 K Hour in am/pm (0-11) Number 0 h
     * Hour in am/pm (1-12) Number 12 m Minute in hour Number 30 s Second in
     * minute Number 55 S Millisecond Number 978 z Time zone General time zone
     * Pacific Standard Time; PST; GMT-08:00 Z Time zone RFC 822 time zone -0800
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param srcDate
     * @param srcFormat
     * @param destFormat
     * @param operType
     * @param operValue
     * @return
     */
    public static String evalTime(String srcDate, String srcFormat, String destFormat, String operType, int operValue)
    {
        if (srcDate == null || srcDate.equals(""))
            return "";
        if (srcFormat == null || srcFormat.equals(""))
            srcFormat = "yyyy-MM-dd";
        if (destFormat == null || destFormat.equals(""))
            destFormat = "yyyy-MM-dd";
        if (operType == null || operType.equals(""))
            operType = "day";

        // Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("AST"));
        Calendar cal = Calendar.getInstance();
        Date currentTime = null;
        try
        {
            currentTime = (Date) new java.text.SimpleDateFormat(srcFormat).parse(srcDate);
        }
        catch (ParseException e)
        {
            logger.error("", e);
            currentTime = new Date();
        }
        cal.setTime(currentTime);
        if (operType.equals("year"))
        {
            cal.add(Calendar.YEAR, operValue);
        }
        else if (operType.equals("month"))
        {
            cal.add(Calendar.MONTH, operValue);
        }
        else if (operType.equals("day"))
        {
            cal.add(Calendar.DAY_OF_MONTH, operValue);
        }
        else if (operType.equals("hour"))
        {
            cal.add(Calendar.HOUR_OF_DAY, operValue);
        }
        else if (operType.equals("minute"))
        {
            cal.add(Calendar.MINUTE, operValue);
        }
        else if (operType.equals("second"))
        {
            cal.add(Calendar.SECOND, operValue);
        }
        else if (operType.equals("millisecond"))
        {
            cal.add(Calendar.MILLISECOND, operValue);
        }
        String curDay = new java.text.SimpleDateFormat(destFormat).format(cal.getTime());
        return curDay;
    }

    /**
     * 
     * 判断日期是否即将到期失效
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date1
     *            失效时间
     * @param date2
     *            当前时间
     * @param delay
     *            失效阀值（天）
     * @return ture-已失效， false-未失效
     */
    public static boolean toRemind(Date date1, Date date2, int delay)
    {

        Long time = diffDate(date1, date2);
        if (delay > time / (60 * 60 * 24 * 1000))
        {
            return false;// 未失效
        }
        else
        {
            return true;// 即将失效，提醒
        }
    }

    /**
     * 
     * 日期相减，返回毫秒数差
     * 
     * @author wanghui
     * @date 2012-5-28 下午01:36:29
     * @version: V1.0
     * 
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return 返回相减后的日期毫秒数，若入参为null，则返回0
     */
    private static long diffDate(java.util.Date date1, java.util.Date date2)
    {
        if (date1 == null)
            return 0;
        if (date2 == null)
            return 0;

        return date1.getTime() - date2.getTime();
    }
    
    /**
	 * 获取当前日期，格式YYYYMMDD
	 */
	public static String getDateFormat1 ( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
 		java.util.Date dateCurrent = new java.util.Date();
 		String strCurrentDate = formatter.format( dateCurrent );
 		return strCurrentDate;	
	}
	
	/**
	 * 获取当前日期，格式 YYYY-MM-DD
	 */
	public static String getDateFormat2 ( )
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 		java.util.Date dateCurrent = new java.util.Date();
 		String strCurrentDate = formatter.format( dateCurrent );
 		return strCurrentDate;	
	}
	
	/**
	 * 获取昨天的日期，格式 YYYY-MM-DD
	 */
	public static String getYesterday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(new Date());
		startDT.add(Calendar.DAY_OF_MONTH, -1);
		String strCurrentDate = formatter.format( startDT.getTime() );
 		return strCurrentDate;
	}
	
	public static void main(String[] args) {
		System.out.println(DateOper.evalTime(DateOper.date2String(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "minute", -2));
	}
}
