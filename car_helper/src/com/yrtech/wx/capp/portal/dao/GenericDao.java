package com.yrtech.wx.capp.portal.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import com.yrtech.wx.capp.portal.util.PageData;

/** 
 * 通用Dao父类实现的接口，只提供常见的一些功能，其它特殊的功能，可以在子接口中定义。 
 * 事务也在需要的时候加到实现类中 
 * 
 * @param <T> 实体类型 
 */  
public interface GenericDao<T> {
	
	public List<T> findTbyHql(String hql);  
    
    /** 
     * by this method you can get all information about T 
     * @return 
     */  
    public List<T> findAll();  
      
    /** 
     * 和数据库同步 
     * @param entityClass 实体类 
     * @return 
     */  
    public void fulsh();  
    /** 
     * 获取记录总数 
     * @param entityClass 实体类 
     * @return 
     */  
    public long getCount(final String wherejpql,   
              final Object[] queryParams);  
    /** 
     * 清除一级缓存的数据 
     */  
    public void clear();  
    /** 
     * 保存实体 
     * @param entity 实体id 
     */  
    public void save(Object entity);  
    /** 
     * 保存实体集合
     * @param entities 实体id集合 
     */  
    public void batchSave(Collection entities);
    /** 
     * 更新实体 
     * @param entity 实体id 
     */  
    public void update(Object entity);  
    /** 
     * 删除实体 
     * @param entityClass 实体类 
     * @param entityids 实体id数组 
     */  
    public void delete(Serializable ... entityids);  
    
    
    /**
     * 批量删除实体 
     * @param entities
     */
    public void deleteAll(Collection entities);
    
    /** 
     * 获取实体 
     * @param <T> 
     * @param entityClass 实体类 
     * @param entityId 实体id 
     * @return 
     */  
    public T find(Serializable entityId)  ;  
    /** 
     * 获取实体 
     * @param <T> 
     * @param entityClass 实体类 
     * @param entityId 实体id 
     * @return 
     */  
    public T get(Serializable entityId);  
    /** 
     * 获取分页数据 
     * @param <T> 
     * @param entityClass 实体类 
     * @param firstindex 开始索引 
     * @param maxresult 需要获取的记录数 
     * @return 
     */  
    public PageData<T> getScrollData(int firstindex, int       
               maxresult, String wherejpql, Object[]  
               queryParams,LinkedHashMap<String, String> orderby);  
      
    public PageData<T> getScrollData(int firstindex,   
                int maxresult, 
                String wherejpql,   
                Object[] queryParams);  
      
    public PageData<T> getScrollData(int firstindex,  
            int maxresult, LinkedHashMap<String, String> orderby);  
      
    public PageData<T> getScrollData(int firstindex,   
              int maxresult);  
      
    public PageData<T> getScrollData();  
      
    public PageData<T> getScrollDataByHql(  
               final int firstindex,   
               final int maxresult,  
               final String hql_search,   
               final String hql_totalRecords);  
      
    public int executeDML(final String hql);  
    
    public int executeDML(final String sethql, Object[] values);  
    
    public List<T> limitFindByHql(final int firstindex,   
        final int maxresult,  
        final String wherejpql,  
        final Object[] queryParams,  
        final LinkedHashMap<String, String> orderby); 
    
    public void callProcedure(String procString,List<Object> params) throws Exception;
}
