package com.yrtech.wx.capp.portal.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.web.context.WebApplicationContext;

import com.yrtech.wx.capp.portal.dao.GenericDao;
import com.yrtech.wx.capp.portal.util.PageData;

public class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T>{  
	
	protected Log logger = LogFactory.getLog(GenericDaoImpl.class);  
    
    protected Class<T> entityClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
      
    public void clear() {  
        getHibernateTemplate().clear();   
    }  
      
    public void fulsh(){  
        getHibernateTemplate().flush();  
    }  
    
    public void deleteAll(Collection entities){
    	getHibernateTemplate().deleteAll(entities); 
    }
  
    public void delete(Serializable... entityids) {  
        for (int i = 0; i < entityids.length; i++) {  
            T t = get(entityids[i]);  
               
            if(t != null){  
                getHibernateTemplate().delete(t);  
            }   
        }  
    }
  
    public T find(Serializable entityId) {  
        if(entityId  == null){  
            throw new RuntimeException(this.entityClass.getName()+ ":id is empty or null");  
        }  
        T t = null;   
        t = get(entityId);  
        return  t;  
    }  
      
    public T get(Serializable entityId){  
        if(entityId  == null){  
            throw new RuntimeException(this.entityClass.getName()+ ":id is empty or null");  
        }  
        return (T) getHibernateTemplate().get(this.entityClass, entityId);  
    }  
  
    public long getCount(final String wherejpql, final Object[] queryParams) {  
        final String hql = "select count(o) from "+   
        getEntityName(this.entityClass)+ " o " + (wherejpql==null || "".equals(wherejpql.trim())? "": "where 1=1 "+ wherejpql);  
      return (Long)getHibernateTemplate().execute(new HibernateCallback(){  
          public Object doInHibernate(Session session)  
            throws HibernateException, SQLException {   
        Query query = session.createQuery(hql);  
        setQueryParams(query, queryParams);  
        return query.uniqueResult();  
    }  
      });  
    }  
  
  
    public void save(Object entity) {  
        getHibernateTemplate().save(entity);  
    }  
  
    public void batchSave(final Collection entities) {  
    	getHibernateTemplate().execute(new HibernateCallback(){  
            public Object doInHibernate(Session session) throws HibernateException, SQLException {  
            	int index = 0;
            	for(Object o:entities){
            		session.save(o);
            		if ( index % 20 == 0 ) { //20, same as the JDBC batch size //20,与JDBC批量设置相同
            			//flush a batch of inserts and release memory:
            			//将本批插入的对象立即写入数据库并释放内存
            			session.flush();
            			session.clear();
            		}
            	}
            	session.close();
            	return null;
            }
        });  
    }  
  
    @SuppressWarnings("unchecked")  
    public PageData<T> getScrollData(final int firstindex, final int maxresult,  
        final String wherejpql, final Object[] queryParams,  
        final LinkedHashMap<String, String> orderby) {  
        final PageData qr = new PageData<T>();  
        final String entityname = getEntityName(this.entityClass);  
        return (PageData<T>)getHibernateTemplate().execute(new HibernateCallback(){  
             public Object doInHibernate(Session session)  
                throws HibernateException, SQLException {  
                 Query query = session.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where 1=1 "+ wherejpql)+ buildOrderby(orderby));  
                    setQueryParams(query, queryParams);  
                    if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);  
                    qr.setResultlist(query.list());  
                    query = session.createQuery("select count(o) from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where 1=1 "+ wherejpql));  
                    setQueryParams(query, queryParams);  
                    qr.setTotalRecords((Long)query.uniqueResult());  
                    return qr;  
             }  
        });  
    }  
      
      
      
    @SuppressWarnings("unchecked")  
    public PageData<T> getScrollDataByHql(final int firstindex, final int maxresult,final String hql_search, final String hql_totalRecords) {  
        final PageData qr = new PageData<T>();  
        //final String entityname = getEntityName(this.entityClass);  
        return (PageData<T>)getHibernateTemplate().execute(new HibernateCallback(){  
             public Object doInHibernate(Session session)  
                throws HibernateException, SQLException {  
                 Query query = null;  
                 query = session.createQuery(hql_search);  
                 if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);  
                 qr.setResultlist(query.list());   
                 query = session.createQuery(hql_totalRecords);  
                 qr.setTotalRecords((Long)query.uniqueResult());   
                 return qr;  
             }  
        });  
    }  
      
    public PageData<Object[]> getScrollDataByHqlForMutlRel(final int firstindex, final int maxresult,final String hql_search, final String hql_totalRecords) {  
        final PageData qr = new PageData<Object[]>();  
        //final String entityname = getEntityName(this.entityClass);  
        return (PageData<Object[]>)getHibernateTemplate().execute(new HibernateCallback(){  
             public Object doInHibernate(Session session)  
                throws HibernateException, SQLException {  
                 Query query = null;  
                 query = session.createQuery(hql_search);  
                 if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);  
                 qr.setResultlist(query.list());   
                 query = session.createQuery(hql_totalRecords);  
                 qr.setTotalRecords((Long)query.uniqueResult());   
                 return qr;  
             }  
        });  
    }
      
      
      
    /** 
     * ���ò�ѯ�����Ĳ��� 
     * @param query 
     * @param queryParams 
     */  
    protected static void setQueryParams(Query query, Object[] queryParams){  
        if(queryParams!=null && queryParams.length>0){  
            for(int i=0; i<queryParams.length; i++){  
                query.setParameter(i, queryParams[i]);  
            }  
        }  
    }  
    /** 
     * ��װorder by��� 
     * @param orderby 
     * @return 
     */  
    protected static String buildOrderby(LinkedHashMap<String, String> orderby){  
        StringBuffer orderbyql = new StringBuffer("");  
        if(orderby!=null && orderby.size()>0){  
            orderbyql.append(" order by ");  
            for(String key : orderby.keySet()){  
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");  
            }  
            orderbyql.deleteCharAt(orderbyql.length()-1);  
        }  
        return orderbyql.toString();  
    }  
  
    public PageData<T> getScrollData(int firstindex, int maxresult,  
            String wherejpql, Object[] queryParams) {  
          
        return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);  
    }  
  
    public PageData<T> getScrollData(int firstindex, int maxresult,  
            LinkedHashMap<String, String> orderby) {  
        return getScrollData(firstindex, maxresult, null, null, orderby);  
    }  
  
    public PageData<T> getScrollData(int firstindex, int maxresult) {  
          
        return getScrollData(firstindex, maxresult, null, null, null);  
    }  
  
    public PageData<T> getScrollData() {  
        return getScrollData(-1, -1);  
    }  
      
    /** 
     * ��ȡʵ������ 
     * @param <E> 
     * @param clazz ʵ���� 
     * @return 
     */  
    protected static <E> String getEntityName(Class<E> clazz){  
        String entityname = clazz.getSimpleName();  
        return entityname;  
    }  
      
    public List<T> findAll(){  
        List<T> list = getHibernateTemplate().loadAll(entityClass);  
        return list;  
    }  
    public List<T> findTbyHql(String hql){  
        List<T> list = getHibernateTemplate().find(hql);
        return list;  
    }  
    
    public List<T> findTbyHql(String hql, Object[] o){  
        List<T> list = getHibernateTemplate().find(hql, o);
        return list;  
    }
      
    public int executeDML(final String hql){  
        Integer result = 0;   
        result = getHibernateTemplate().bulkUpdate(hql);  
        return result;  
    }  
      
    public int executeDML(final String sethql, Object[] values){  
        Integer result = 0;  
        final String entityname = getEntityName(this.entityClass);  
        String hql = "update " + entityname + " o " + sethql ;  
        result = getHibernateTemplate().bulkUpdate(hql, values);  
        return result;  
    }  
      
      
    @SuppressWarnings("unchecked")  
    public List<T> limitFindByHql(final int firstindex, final int maxresult,  
        final String wherejpql, final Object[] queryParams,  
        final LinkedHashMap<String, String> orderby) {  
        final String entityname = getEntityName(this.entityClass);  
        return (List<T>)getHibernateTemplate().execute(new HibernateCallback(){  
             public Object doInHibernate(Session session)  
                throws HibernateException, SQLException {  
                 Query query = session.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where 1=1 "+ wherejpql)+ buildOrderby(orderby));  
                    setQueryParams(query, queryParams);  
                    if(firstindex!=-1 && maxresult!=-1)   
                        query.setFirstResult(firstindex).setMaxResults(maxresult);   
                    return query.list();  
             }  
        });  
    }

	/** 创建与会话无关的检索标准 */
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	/** 创建与会话绑定的检索标准 */
	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getSession());
	}
	
	/** 检索满足标准的数据 */
	public List findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	/** 检索满足标准的数据，返回指定范围的记录 */
	public List findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}
	
    @SuppressWarnings("unchecked")   
    public static GenericDao getFromApplicationContext(WebApplicationContext context) {   
        return (GenericDao)context.getBean("GenericDao");   
    }

	@Override
	public void update(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void callProcedure(final String procString, final List<Object> params) throws Exception{
        getHibernateTemplate().execute(new HibernateCallback() { 
            public Object doInHibernate(Session session) { 
                try { 
                    Connection conn = session.connection(); 
                    CallableStatement stmt = conn.prepareCall(procString); 
                    stmt.setString(2, (String)params.get(1)); 
                    stmt.setInt(3, (Integer)params.get(2)); 
                    stmt.setInt(4, (Integer)params.get(3)); 
                    stmt.execute(); 
                } catch (Exception e) { 
                	e.printStackTrace(); 
                } 
                return null; 
            } 
        }); 
	}
}
