package com.yrtech.wx.capp.framework.core.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：数据缓存管理
 * @Package: com.yrtech.wx.capp.framework.core.cache
 * @ClassName: DataCacheManager.java
 * @author: wanghui
 * @date： 2012-11-10 下午3:24:13
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class DataCacheManager
{
    private static Map dataCacheHashMap = new HashMap();

    /**
     * 增加
     * @param key
     * @param value
     */
    public static void put(Object key, Object value)
    {
        synchronized (dataCacheHashMap)
        {
        	dataCacheHashMap.put(key, value);
        }
    }

    /**
     * 删除
     * 
     * @param key
     */
    public static void remove(Object key)
    {
        synchronized (dataCacheHashMap)
        {
        	dataCacheHashMap.remove(key);
        }
    }

    /**
     * 获取数值
     * 
     * @param key
     */
    public static Object get(Object key)
    {
        Object ele = null;
        synchronized (dataCacheHashMap)
        {
            ele = dataCacheHashMap.get(key);
        }
        
        // 如果缓存过期，则为null
        if (ele == null)
            return null;
        
        return ele;
    }
}
