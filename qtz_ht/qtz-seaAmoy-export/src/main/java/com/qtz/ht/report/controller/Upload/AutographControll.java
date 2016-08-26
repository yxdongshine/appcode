package com.qtz.ht.report.controller.Upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qtz.ht.report.controller.BaseController;

/**
 * <p>Title:AutographControll</p>
 * <p>Description:(生成上传文件签名)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月6日
 */
@RestController
@RequestMapping("/v1.0/")
public class AutographControll extends BaseController {
	
//	private static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//	private static String accessId = "By7dyOluMW7WHTJ1";
//	private static String accessKey = "7Y95lRniK8hgZrkS8w31DjfbVJriXD";
//	private static String bucket = "htimg01";

//	@RequestMapping("getAutograph")
//	public void getAutograph(@RequestParam(value="token") String sid,
//			HttpServletResponse response, HttpServletRequest request)
//	{
//        String dir = "user/" + DateUtil.dateToStrid(new Date()) + "/" + UUIDFactory.newUUID();
//        String host = "http://" + bucket + "." + endpoint;
//        OSSClient client = new OSSClient(host, accessId, accessKey);
//        try { 	
//        	long expireTime = 300;
//        	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;    
//            Date expiration = new Date(expireEndTime);
//            PolicyConditions policyConds = new PolicyConditions();
//            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000l);
//            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//
//            String postPolicy = client.generatePostPolicy(expiration, policyConds);
//            byte[] binaryData = postPolicy.getBytes("utf-8");
//            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
//            String postSignature = client.calculatePostSignature(postPolicy);
//            
//            Map<String, String> respMap = new LinkedHashMap<String, String>();
//            respMap.put("accessid", accessId);
//            respMap.put("policy", encodedPolicy);
//            respMap.put("signature", postSignature);
//            respMap.put("dir", dir);
//            respMap.put("host", host);
//            respMap.put("bucket", bucket);
//            respMap.put("expire", String.valueOf(expireEndTime / 1000));
//            log.debug(respMap.toString());
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
//            RespJsonPHandler.respOK(respMap, response, request);
//            
//        } catch (Exception e) {
//        	RespJsonPHandler.respError(-1, "获取签名失败", response, request);
//        }
//	}
}
