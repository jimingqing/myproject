package com.yrtech.wx.capp.framework.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 把入参实体构造成 key:value,key:value,...形式记录日志 改进了对Date类型的处理和对继承类型参数的处理
 * 
 * @author wanghui
 * 
 */
@SuppressWarnings("all")
public class InParams
{
    private static Logger logger = Logger.getLogger(InParams.class);

    /* 私有构造 */
    private InParams()
    {
    }

    /* 绑定类,输出结果 */

    public static String bind(Object o)
    {
        StringBuffer str = new StringBuffer();
        Method[] ms = o.getClass().getMethods();
        for (int i = 0; i < ms.length; i++)
        {
            if (ms[i].getName().startsWith("get")
            // && !ms[i].getName().startsWith("getLong")
                    && !ms[i].getName().startsWith("getClass") && !ms[i].getName().startsWith("getChars")
                    // && !ms[i].getName().startsWith("getSerializer")
                    // && !ms[i].getName().startsWith("getDeserializer")
                    // && !ms[i].getName().startsWith("getTypeDescForClass")
                    && !ms[i].getName().equals("get"))
            {
                Method method = null;
                /*
                 * 只支持三层继承！
                 */
                try
                {
                    try
                    {
                        /* 找不到?去找它爹 */
                        method = o.getClass().getDeclaredMethod(ms[i].getName(), null);
                    }
                    catch (NoSuchMethodException e)
                    {
                        try
                        {
                            /* 还找不到?去找它爷爷 */
                            method = o.getClass().getSuperclass().getDeclaredMethod(ms[i].getName(), null);
                        }
                        catch (Exception ex)
                        {
                            try
                            {
                                /* 最后一次机会,它曾爷爷 */
                                method = o.getClass().getSuperclass().getSuperclass()
                                        .getDeclaredMethod(ms[i].getName(), null);
                            }
                            catch (Exception exx)
                            {
                                logger.error("无此方法.", exx);
                                return "";
                            }
                        }
                    }
                    /* 得到 bean 的get 方法返回值 */
                    Object object = method.invoke(o, null);
                    if (object instanceof Integer || object instanceof Float || object instanceof String
                            || object instanceof Double || object instanceof Long || object instanceof Character)
                    {
                        str.append(ms[i].getName().substring(3, ms[i].getName().length()));
                        str.append(":");
                        str.append(object);
                        str.append(",");
                    }
                    else if (object instanceof Date)
                    {
                        str.append(ms[i].getName().substring(3, ms[i].getName().length()));
                        str.append(":");
                        str.append(DateOper.date2String((Date) object, "yyyy-MM-dd HH:mm:ss"));
                        str.append(",");
                    }
                    else
                    {
                        if (object != null)
                        {
                            if (object instanceof List)
                            {
                                List l = (List) object;
                                if (l != null && l.size() != 0)
                                {
                                    for (int j = 0; j < l.size(); j++)
                                    {
                                        logger.debug("列表i=" + (j + 1) + ":\n");
                                        str.append("<L");
                                        str.append(i);
                                        str.append(">");
                                        str.append(InParams.bind((l.get(j))));
                                    }
                                }
                            }
                            else
                            {
                                str.append(InParams.bind((object)));
                            }
                        }

                    }
                }
                catch (SecurityException e)
                {
                    logger.error("", e);
                }
                catch (IllegalArgumentException e)
                {
                    logger.error("", e);
                }
                catch (IllegalAccessException e)
                {
                    logger.error("", e);
                }
                catch (InvocationTargetException e)
                {
                    logger.error("", e);
                }
            }
        }
        return str.toString();
    }
}
