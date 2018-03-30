package handout;

import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.yrtech.wx.capp.framework.core.spring.SpringInfo;
import com.yrtech.wx.capp.portal.dao.impl.OrderLogDao;
import com.yrtech.wx.capp.portal.model.MerInfo;
import com.yrtech.wx.capp.portal.model.OrderLog;

public class TransQryTimerTask extends TimerTask {

	private static Logger log = Logger.getLogger(TransQryTimerTask.class);
	
	public void run() {
		
		String hql = "from OrderLog o, MerInfo m where o.merId=m.id and o.ordState='I' order by o.createTime desc";
		OrderLogDao dao = (OrderLogDao)SpringInfo.getBean("orderLogDao");
		List list = dao.findTbyHql(hql);
		OrderLog ordlog = new OrderLog();
		MerInfo merInfo = new MerInfo();
		for(Object o : list){
			ordlog = (OrderLog)((Object[])o)[0];
			merInfo = (MerInfo)((Object[])o)[1];
			HandoutTask task = new HandoutTask();
			task.setTransType("qry");
			task.setMerInfo(merInfo);
			task.setOrdlog(ordlog);
			ThreadPool.getInstance().addTask(task);
		}
	}

}
