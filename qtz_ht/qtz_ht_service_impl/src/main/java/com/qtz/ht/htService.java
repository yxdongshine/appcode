package com.qtz.ht;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cache.BaseProperties;

/**
 * <p>Title:StartService</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市爱免费信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年2月15日
 */
public class htService {
	private static Logger log = LoggerFactory.getLogger(htService.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		try {
			BaseProperties.writePidToFile();
			final URL url = htService.class.getResource("/config/log4j.xml");
			DOMConfigurator.configure(url);
			BaseProperties.initMap("config/base.properties");
			log.warn("========htService准备启动服务========");
			
			log.info("基础配置文件初始化");
			new ClassPathXmlApplicationContext(BaseProperties.getBaseProperties("START_FILES").split(","));
			log.warn("========htService启动服务成功========");
			while (true){
				TimeUnit.HOURS.sleep(1);
			}
		} catch (Exception e) {
			log.error("服务启动失败！",e);
		}
	}
}
