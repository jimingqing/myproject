package com.yrtech.wx.capp.portal.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TGDataTaskListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent event) {
		new TimerManager();
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}
