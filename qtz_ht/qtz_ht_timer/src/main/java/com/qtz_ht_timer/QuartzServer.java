package com.qtz_ht_timer;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cache.BaseProperties;


/**
 * 启动定时器服务
 * ClassName: QuartzServer <br/> 
 * Function: TODO (). <br/> 
 * Reason: TODO (). <br/> 
 * date: 2016年6月6日 下午5:39:54 <br/> 
 * 
 * @author yxd 
 * @version
 */
public class QuartzServer {

	private static Logger log = LoggerFactory.getLogger(QuartzServer.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		BaseProperties.writePidToFile();
		final URL url = QuartzServer.class.getResource("/config/log4j.xml");
		DOMConfigurator.configure(url);
		log.warn("========QuartzServer准备启动服务========");
		
		BaseProperties.initMap("config/base.properties");
			
		new ClassPathXmlApplicationContext("beans.xml");
		
		log.info("QuartzServer启动服务成功！");
		
		while (true){
			TimeUnit.HOURS.sleep(1);
		}
	}
}
