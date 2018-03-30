package com.yrtech.wx.capp.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yrtech.wx.capp.portal.model.OrderLog;

public class OrderLogDao extends GenericDaoImpl<OrderLog> {
       
	public Double  getTotleMoney( Integer merId , String ordState ,
			String startDate , String endDate , String qryType ,
			String ordAmt)
	{
		final StringBuffer sql = new StringBuffer(" from OrderLog  where ");
		if(merId != null)
		{
			// sql = new
			// StringBuffer("from OrderLog  where  payUserId  in(select id  from  UserInfo  where merId = "+merId+")");
			sql
					.append("payUserId  in(select id  from  UserInfo  where merId = "
							+ merId + ")");
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate))
		{
			sql.append("  and  createTime   between '" + startDate
					+ " 00:00:00" + "' and '" + endDate + " 23:59:59"
					+ "'");
		}
		if(!StringUtils.isEmpty(ordAmt))
		{
			if(qryType.equals("eq"))
			{
				sql.append("  and  ordAmt =" + ordAmt);
			}else if(qryType.equals("gt"))
			{
				sql.append("  and  ordAmt > " + ordAmt);
			}else
			{
				sql.append("  and  ordAmt < " + ordAmt);
			}
		}

		if(!StringUtils.isEmpty(ordState))
		{
			sql.append("  and  ordState ='" + ordState + "'");
		}
		
		return (Double) getHibernateTemplate().execute(new HibernateCallback()
     	{
     		public Object doInHibernate( Session session )
     				throws HibernateException, SQLException
     		{
     			Query query = session.createQuery("select  sum(ordAmt)   "+sql.toString() );
     			if(query.list().get(0)==null)
     			{
     				return new Double(0);
     			}
     			else
     			{
     				return  query.list().get(0);
     			}
     			
     		}
     	}); 
		
	}

	 public List findBySql(final String sql)
	 {
		 return (List) getHibernateTemplate().execute(new HibernateCallback()
	     	{
	     		public Object doInHibernate( Session session )
	     				throws HibernateException, SQLException
	     		{
	     			Query query = session.createSQLQuery(sql);
	     			
	     			return  query.list();
	     			
	     		}
	     	}); 	 }

}
