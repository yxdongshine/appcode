package com.qtz_ht_timer.util;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.exception.ServiceException;

public class TaskUtils {


	/**(BeanId为key的安全Map)*/
	private static ConcurrentHashMap<String, Task> BEAN_MAP = new ConcurrentHashMap<String, Task>();
	/** 
	* 【初始化】(这里用一句话描述这个方法的作用)
	* @throws ParserConfigurationException
	* @throws SAXException
	* @throws IOException  
	 * @throws ServiceException 
	*/
	private static synchronized void initHandleMap() throws ServiceException {
		DocumentBuilder db;
		Document document = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = db.parse(TaskUtils.class.getResourceAsStream("/config/xml/task.xml"));
		}catch (Exception e) {
			throw new ServiceException("解析xml错误",e);
		}
		//把文件解析成DOCUMENT类型
		Element root = document.getDocumentElement();
		NodeList nl = root.getElementsByTagName("task");
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);//得到父节点
			
			NamedNodeMap attributes = n.getAttributes();
			Task t = new Task();
			for (int j = 0; j < attributes.getLength(); j++) {
				Node attribute = attributes.item(j);
				
				String attributeName = attribute.getNodeName();// 属性名
				String attributeValue = attribute.getNodeValue();// 属性值
				
				if ("name".equalsIgnoreCase(attributeName)){
					if(null == attributeValue || attributeValue.trim().length() == 0)
						throw new ServiceException(ExceptionConstants.ERRORCODE_7,"name无效","attribute="+attribute.toString());
					t.setName(attributeValue);
				}
				if ("beanId".equalsIgnoreCase(attributeName)){
					if(null == attributeValue || attributeValue.trim().length() == 0)
						throw new ServiceException(ExceptionConstants.ERRORCODE_7,"beanId无效","attribute="+attribute.toString());
					t.setBeanId(attributeValue);
				}
				if ("exeTime".equalsIgnoreCase(attributeName)){
					if(null == attributeValue || attributeValue.trim().length() == 0)
						throw new ServiceException(ExceptionConstants.ERRORCODE_7,"exeTime无效","exeTime="+attribute.toString());
					t.setExeTimeStr(attributeValue);
				}
			}
			//将task放置同步容器中
			putTaskToBeanMap(t);
		}
	}

	
	/**
	 * 将task放置在容器中
	 * putTaskToBeanMap:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param t
	 */
	private static void putTaskToBeanMap(Task t){
		BEAN_MAP.put(t.getBeanId(), t);
	}

	
	/**
	 * 获取特定的名称字符串
	 * getTaskByBeanId:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param beanId
	 * @return
	 * @throws ServiceException
	 */
	public static Task getTaskByBeanId(String beanId) throws ServiceException {
		if( 0 == BEAN_MAP.size()){
			initHandleMap();
		}
		Task t = BEAN_MAP.get(beanId);
		return t;
	}
	
	public static void main(String[] args) throws ServiceException {
		try {
			System.out.println(getTaskByBeanId(TimeConfig.SUPP_THAW_FORZEN_RECONCILIACTION));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}