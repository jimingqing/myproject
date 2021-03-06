package com.yrtech.wx.capp.portal.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

public class TimerManager {
	
	private static Logger log = Logger.getLogger(TimerManager.class);
	
	// 时间间隔
	private static final long PERIOD_DAY = 60*60 * 1000;

	public TimerManager() {
		Timer timer = new Timer();
		System.out.println("定时器已启动"); 
		TGDataTimerTask task = new TGDataTimerTask();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task, 0, PERIOD_DAY);
		System.out.println("更新商户证书信息任务已经添加任务调度表");
		
		Calendar calendar = Calendar.getInstance();

		/*** 定制每日3:00执行方法 ***/

		calendar.set(Calendar.HOUR_OF_DAY, 3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		AcctSumTimerTask acctTask = new AcctSumTimerTask();
		timer.schedule(acctTask, date, 24 * 60 * 60 * 1000);
//		timer.schedule(acctTask, date, 1 * 6 * 60 * 10);
		System.out.println("更新账务统计日志信息任务已经添加任务调度表");
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}
