package handout;

import java.util.List;

import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.OrderLog;


public class TestThreadPool {
	
	public static void main(String[] args) throws Exception{
		ThreadPool threadPool = ThreadPool.getInstance();
		threadPool.start();
		
		String hql = "from OrderLog o, MerInfo m where o.merId=m.id and o.ordState='I' order by o.createTime desc";
		OrderLogDao dao = (OrderLogDao)SpringInfo.getBean("orderLogDao");
		List list = dao.findTbyHql(hql);
		OrderLog ordlog = new OrderLog();
		MerInfo merInfo = new MerInfo();
		for(Object o : list){
			ordlog = (OrderLog)((Object[])o)[0];
			merInfo = (MerInfo)((Object[])o)[1];
			System.out.println(ordlog.getOrdId());
			HandoutTask task = new HandoutTask();
			task.setMerInfo(merInfo);
			task.setOrdlog(ordlog);
			threadPool.addTask(task);
		}
//		threadPool.addTask(new HandoutTask());
	}
}
