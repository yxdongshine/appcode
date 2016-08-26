package com.qtz.ht.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mall.core.common.StringUtil;

public class SystemUtil {
	
	private static Log log = LogFactory.getLog(SystemUtil.class);
	
	private static String filePro = "/config/base.properties";  //属性文件路径
	private static Map<String,String> map= null;  //缓存属性文件夹
	
	public static String getBaseValueByBaseKey(String key){
		map = SystemUtil.readProperties(filePro);  //缓存属性文件夹
		return map.get(key);
	}
	
	/** 
	* 【获取配置值】(info)
	* @param key
	* @param defaultValue 默认的value，当配置文件值不存在，返回该值
	* @return  
	*/
	public static String getBaseValueByBaseKey(String key,String defaultValue){
		String valstr = getBaseValueByBaseKey(key);
		if (StringUtil.isEmpty(valstr)){
			valstr = defaultValue;
		}
		return valstr;
	}
	
	public static Map<String,String> readProperties(String filePath) {
    	if(filePath == null || filePath.equals("filePath")) return null;
         Map<String,String> map = new HashMap<String, String>();
         String key = "",Property="";
         Properties props =readPropertiesP(filePath);
         Enumeration<?> en = props.propertyNames();
         while (en.hasMoreElements()) 
         {
        	 key = (String) en.nextElement();
             Property = props.getProperty (key);
             map.put(key, Property);
             log.debug(">>>>>>>>>>>>"+key+" = "+Property);
         }
         return map;
    }
	
	public static Properties readPropertiesP(String filePath){
		if(filePath == null || filePath.equals("filePath")) return null;
		 Properties props = new Properties();
		 InputStreamReader in = null;
		 try {
			in = new InputStreamReader(SystemUtil.class.getResourceAsStream(filePath), "UTF-8");
			try {
				props.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 return props;
	}
	
	/** 
	* 【Linux下和shell脚本配合】(info)  
	*/
	public static void writePidToFile() {
		if(System.getProperty("os.name").toLowerCase().equals("linux")){
			String fileName = System.getProperty("pid.file");
			if(null == fileName)
				return;//console 模式下不需要
//				throw new IllegalStateException("for the linux system,you need to set the system env \"pid.file\" ");
			File file = new File(fileName);
			file.deleteOnExit();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
				fw.flush();
				fw.close();
			}
			catch (IOException e) {
				throw new IllegalAccessError("can't write to the pid.file \""
						+ file.getAbsolutePath() + "\"");
			}
		}
	}
	
}