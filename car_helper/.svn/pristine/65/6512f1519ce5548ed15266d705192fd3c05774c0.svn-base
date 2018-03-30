package handout;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import org.apache.log4j.Logger;
import com.yrtech.wx.capp.framework.core.util.Config;

public class HandoutTaskManager {
	
	private static Logger log = Logger.getLogger(HandoutTaskManager.class);
	
	// 时间间隔
	private static final long QRY_TRANS_PERIOD = Integer.parseInt(Config.getProperty("handout_qry_period")) * 60 * 1000; //5分钟发起一次查询任务
	private static final long PAY_TRANS_PERIOD = Integer.parseInt(Config.getProperty("handout_pay_period")) * 60 * 1000; //1分钟发起一次支付任务

	public HandoutTaskManager() {
		ThreadPool threadPool = ThreadPool.getInstance();
		threadPool.start();
		Timer timer = new Timer();
		log.info("************* 交易发布任务已启动 **************"); 
		TransQryTimerTask qrytask = new TransQryTimerTask();
		timer.schedule(qrytask, 0, QRY_TRANS_PERIOD);
		log.info("************* 已经添加交易查询任务调度表 **************");
		
		TransPayTimerTask paytask = new TransPayTimerTask();
		timer.schedule(paytask, 0, PAY_TRANS_PERIOD);
		log.info("************* 已经添加交易支付任务调度表 **************");
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}
