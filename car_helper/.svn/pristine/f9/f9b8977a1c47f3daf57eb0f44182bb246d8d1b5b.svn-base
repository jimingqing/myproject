package com.yrtech.wx.capp.framework.core.cache;

import java.util.HashMap;

/**
 * 
 * Object对象缓存操作类
 * 
 * 将缓存信息已key-value形式存放到Map中
 * 
 * 主要操作包括：add，get，remove，clear，size，keys，values等
 * 
 * @Package: com.yrtech.wx.capp.framework.core.cache
 * @ClassName: ObjectCache
 * @author wanghui
 * @date 2012-7-3 下午2:05:38
 * @version: V1.0
 */
public class ObjectCache
{

    protected HashMap<Object, Object> cachedObjectsHash;

    private static final int cachedsize = 100;

    /**
     * 
     * 构造方法,初始化缓存对象
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:10:33
     * @version: V1.0
     * 
     */
    public ObjectCache()
    {
        cachedObjectsHash = new HashMap<Object, Object>(cachedsize);
    }

    /**
     * 
     * 添加缓存对象
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:10:53
     * @version: V1.0
     * 
     * @param key
     *            缓存对象key值
     * @param obj
     *            缓存对象
     */
    public synchronized void add(Object key, Object obj)
    {
        remove(key);
        cachedObjectsHash.put(key, obj);
    }

    /**
     * 
     * 获取缓存对象
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:11:54
     * @version: V1.0
     * 
     * @param key
     *            缓存对象key值
     * @return key对应的缓存对象
     */
    public synchronized Object get(Object key)
    {
        return cachedObjectsHash.get(key);
    }

    /**
     * 
     * 删除缓存对象
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:12:40
     * @version: V1.0
     * 
     * @param key
     *            缓存对象key值
     * @return key对应的缓存对象
     */
    public synchronized Object remove(Object key)
    {
        Object cacheObject = cachedObjectsHash.get(key);

        if (cacheObject == null)
        {
            return null;
        }

        return cachedObjectsHash.remove(key);
    }

    /**
     * 
     * 获取当前缓存大小
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:13:20
     * @version: V1.0
     * 
     * @return 缓存对象数量
     */
    public synchronized int size()
    {
        return cachedObjectsHash.size();
    }

    /**
     * 
     * 清空缓存
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:14:05
     * @version: V1.0
     * 
     */
    public synchronized void clear()
    {
        Object[] keys = cachedObjectsHash.keySet().toArray();
        for (int i = 0; i < keys.length; i++)
        {
            remove(keys[i]);
        }

        cachedObjectsHash.clear();

        cachedObjectsHash = new HashMap<Object, Object>(cachedsize);
    }

    /**
     * 
     * 获取缓存所有key值
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:14:20
     * @version: V1.0
     * 
     * @return key值数组
     */
    public Object[] keys()
    {
        return cachedObjectsHash.keySet().toArray();
    }

    /**
     * 
     * 获取缓存所有对象
     * 
     * @author wanghui
     * @date 2012-7-3 下午4:14:46
     * @version: V1.0
     * 
     * @return 缓存对象数组
     */
    public Object[] values()
    {
        return cachedObjectsHash.values().toArray();
    }
}
