package com.yrtech.wx.capp.framework.core.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

	private static Properties props = new Properties();
	private static Logger logger = Logger.getLogger(Config.class);
	
	static {
		InputStream in = Config.class.getResourceAsStream("/config.properties");
	    if (in != null) {
	    	try {
	    		props.load(in);
	    		in.close();
	    	} catch(Exception e) {
	    		logger.error("read config error", e);
	    	}
	    } else {
	    	logger.error("config.properties not found");
	    }
	}
	
	public static String getProperty(String key) {
		return getProperty(key, "");
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	public static void main(String[] args) {
		System.out.println(Config.getProperty("static.address"));
	}
	
}
