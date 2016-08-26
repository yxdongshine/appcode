package com.qtz.ht.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>Title:ComparativeAttributeValue</p>
 * <p>Description:(比较新旧两个对象属性的变化情况)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月11日
 */
public class ComparativeAttributeValue {

	/**
	* 【比较方法】
	* @param oldObject
	* 					旧对象
	* @param newObject
	* 					新对象
	* @return  
	*/
	public static List<String> compare(Object oldObject, Object newObject)
	{
		List<String> list = new ArrayList<>();
		JSONObject old_1 = JSONObject.parseObject(JSONObject.toJSONString(oldObject));
		JSONObject new_1 = JSONObject.parseObject(JSONObject.toJSONString(newObject));
		for (Map.Entry<String, Object> entry : new_1.entrySet()) {
			Object new_value = entry.getValue();
			Object old_value = old_1.get(entry.getKey());
			if (null != new_value) {
				if (new_value instanceof Long) {
					if(old_value==null){
						list.add(entry.getKey() +" 由 null 新增为 " + new_value);
					}else if (Long.valueOf(String.valueOf(new_value)).longValue() != Long.valueOf(String.valueOf(old_value)).longValue()) {
						list.add(entry.getKey() +" 由 "+old_value + " 修改为 " + new_value);
					}
				}else if (new_value instanceof Double) {
					if(old_value==null){
						list.add(entry.getKey() +" 由 null 新增为 " + new_value);
					}else if (Double.valueOf(String.valueOf(new_value)).doubleValue() != Double.valueOf(String.valueOf(old_value)).doubleValue()) {
						list.add(entry.getKey() +" 由 "+old_value + "修改为 " + new_value);
					}
				}else if (new_value instanceof Integer) {
					if(old_value==null){
						list.add(entry.getKey() +" 由 null 新增为 " + new_value);
					}else {
                        if(old_value instanceof Integer ){
                        	if (Integer.valueOf(String.valueOf(new_value)).intValue() != Integer.valueOf(String.valueOf(old_value)).intValue()) {
        						list.add(entry.getKey() +" 由 "+old_value + "修改为 " + new_value);
                        	}						
                        }else {
        					list.add(entry.getKey() +" 由 "+old_value + "修改为 " + new_value);
                        }
                    }
				}else{
					if(old_value==null){
						list.add(entry.getKey() +" 由 null 新增为 " + new_value);
					}else if (!new_value.equals(old_value)) {
						list.add(entry.getKey() +" 由 "+old_value + " 修改为 " + new_value);
					}
				}
			}
		}
		return list;
	}
}
