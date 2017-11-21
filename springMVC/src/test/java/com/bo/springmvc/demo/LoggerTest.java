package com.bo.springmvc.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * @Description 日志打印测试
 * @author 王博
 * @version 2017年11月21日　下午5:02:29
 */
public class LoggerTest {
	
	private Log log = LogFactory.getLog(LoggerTest.class);
	
	public void log() {
		log.debug("Debug info.");
		log.info("Info info");
		log.warn("Warn info");
		log.error("Error info");
		log.fatal("Fatal info");
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
		LoggerTest test = new LoggerTest();
		test.log();
	}
}
