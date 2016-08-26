//package com.qtz.ht.controller.system;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mall.core.common.RespKey;
//import com.mall.core.common.encryption.Base64Encryption;
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.Pager;
//import com.mall.core.vo.VO.Status;
//import com.mongodb.BasicDBObject;
//import com.qtz.dm.index.page.IndexManagePage;
//import com.qtz.dm.index.service.IndexManageService;
//import com.qtz.dm.index.vo.IndexManage;
//import com.qtz.dm.store.page.StoreCategoryPage;
//import com.qtz.dm.store.service.StoreCategoryService;
//import com.qtz.dm.store.vo.StoreCategory;
//import com.qtz.dm.system.model.CategoryKey;
//import com.qtz.dm.system.model.SysConfigurUtil;
//import com.qtz.dm.system.page.AdvertPage;
//import com.qtz.dm.system.service.AdvertService;
//import com.qtz.dm.system.service.CategoryService;
//import com.qtz.dm.system.service.RegionService;
//import com.qtz.dm.system.service.RongService;
//import com.qtz.dm.system.service.ShareMessgeService;
//import com.qtz.dm.system.service.SysConfigurService;
//import com.qtz.dm.system.vo.Advert;
//import com.qtz.dm.system.vo.Category.CategoryType;
//import com.qtz.dm.system.vo.ShareMessge;
//import com.qtz.dm.system.vo.SysConfigur;
//import com.qtz.dm.user.services.DefaultHeadImgService;
//import com.qtz.dm.user.services.UsersService;
//import com.qtz.dm.user.vo.DefaultHeadImg;
//import com.qtz.dm.userwallet.enums.PaymentMethodEnum;
//import com.qtz.dm.userwallet.enums.PlatFormEnum;
//import com.qtz.dm.userwallet.enums.StatusEnum;
//import com.qtz.ht.controller.BaseController;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//
///**
// * 
// * <p>
// * Title:ConfigController
// * </p>
// * <p>
// * Description:(获取系统配置)
// * </p>
// * <p>
// * Copyright: Copyright (c) 2015
// * </p>
// * <p>
// * Company: 深圳市擎天柱信息科技有限公司
// * </p>
// * 
// * @author 涂鑫 - changbo.li
// * @version v1.0 2015年3月14日
// */
//@RestController
//@RequestMapping(value = "/v1.0/config", produces = {"application/json;charset=UTF-8"})
//public class ConfigController extends BaseController {
//  @Resource(name = "categoryService")
//  private CategoryService categoryService;
//  @Autowired
//  private RegionService regionService;
//  @Autowired
//  private SysConfigurService sysConfigurService;// 系统配置
//  @Autowired
//  private UsersService usersService;
//  @Autowired
//  private ShareMessgeService shareMessgeService;// 分享服务
//  @Autowired
//  private StoreCategoryService storeCategoryService;
//  @Autowired
//  private RongService rongService;
//  @Autowired
//  private IndexManageService indexManageService;
//  @Autowired
//  private AdvertService advertManageService;
//  @Autowired
//  private DefaultHeadImgService defaultHeadImgService;
//
//  /**
//   * 
//   * 【android 得到启动页图片】
//   * 
//   * @param response
//   * @throws IOException
//   * @time:2015年9月29日 下午2:59:36
//   * @author 涂鑫
//   * @version
//   */
//  @ApiOperation(value = "得到 android 启动页", notes = "得到 android启动图片路径 ")
//  @RequestMapping(value = "/getStartAndroidImg", method = RequestMethod.GET)
//  public void getStartAndroidImg(HttpServletResponse response, @RequestParam(value = "type",
//      required = false) Integer type) throws IOException {
//    SysConfigur sys;
//    try {
//      sys = this.sysConfigurService.findSysConfigur(SysConfigurUtil.EXAMPLE_IMG_START_APP, type);
//    } catch (ServiceException e) {
//      sys = null;
//    }
//    if (null == sys) {
//      RespHandler.respOK("", response);
//      return;
//    }
//    SysConfigur syss = new SysConfigur();
//    syss.setKeyValue(sys.getKeyValue());
//    syss.setUptime(sys.getUptime());
//    RespHandler.respOK(syss, response);
//  }
//  /**
//   * 
//    * 【分辨率获取图片】
//    * @param response
//    * @param type
//    * @param resolution
//    * @throws IOException  
//    * @time:2016年1月4日 上午11:45:21
//    * @author 涂鑫  
//    * @version
//   */
//  @RequestMapping(value="getStartImg/{resolution}/{type}",method=RequestMethod.GET)
//  public void getStartImg(HttpServletResponse response, @PathVariable(value = "type") Integer type,@PathVariable(value = "resolution") String resolution) throws IOException{
//	  SysConfigur sys;
//	    try {
//	      sys =
//	          this.sysConfigurService.findSysConfigur(
//	              SysConfigurUtil.EXAMPLE_IMG_START_APP_+""+type+"_"+resolution);
//	    } catch (ServiceException e) {
//	      sys = null;
//	    }
//	    if (null == sys) {
//	      RespHandler.respOK("", response);
//	      return;
//	    }
//	    SysConfigur syss = new SysConfigur();
//	    syss.setKeyValue(sys.getKeyValue());
//	    syss.setUptime(sys.getUptime());
//	    RespHandler.respOK(syss, response);
//  }
//  /**
//   * 
//   * 【得到 ios 启动页】
//   * 
//   * @param response
//   * @throws IOException
//   * @time:2015年9月29日 下午2:51:19
//   * @author 涂鑫
//   * @version
//   */
//  @ApiOperation(value = "得到 ios 启动页", notes = "得到 ios启动图片路径 ")
//  @RequestMapping(value = "/getStartIosImg", method = RequestMethod.GET)
//  public void getStartIosImg(HttpServletResponse response, @RequestParam(value = "type",
//      required = false) Integer type) throws IOException {
//    SysConfigur sys;
//    try {
//      sys =
//          this.sysConfigurService.findSysConfigur(
//              SysConfigurUtil.EXAMPLE_IMG_START_APP_IOS_540_960, type);
//    } catch (ServiceException e) {
//      sys = null;
//    }
//    if (null == sys) {
//      RespHandler.respOK("", response);
//      return;
//    }
//    SysConfigur syss = new SysConfigur();
//    syss.setKeyValue(sys.getKeyValue());
//    syss.setUptime(sys.getUptime());
//    RespHandler.respOK(syss, response);
//
//  }
//
////  /**
////   * 
////   * 【获取启动页加载图片】
////   * 
////   * @param response
////   * @throws IOException
////   */
////  @ApiOperation(value = "获取启动页加载图片", notes = "这个接口获取启动页加载图片路径 ")
////  @RequestMapping(value = "/getStartImg", method = RequestMethod.GET)
////  public void getStartImg(HttpServletResponse response) throws IOException {
////    try {
////      SysConfigur sys =
////          this.sysConfigurService.findSysConfigur(SysConfigurUtil.EXAMPLE_IMG_START_APP);
////      if (null == sys) {
////        RespHandler.respOK("", response);
////      }
////      sys.setChildList(null);
////      sys.setDataType(null);
////      sys.setDmId(null);
////      sys.setKeyName(null);
////      sys.setLevel(null);
////      sys.setName(null);
////      sys.setParentId(null);
////      sys.setUserId(null);
////      sys.setUptime(sys.getUptime() + DateUtil.MILLISECOND_PER_DAY);
////      RespHandler.respOK(sys, response);
////    } catch (ServiceException e) {
////      log.error(e);
////      RespHandler.respServerError(response);
////    }
////  }
//
//  /**
//   * 
//   * 【获取商家设备】(这里用一句话描述这个方法的作用)
//   * 
//   * @throws IOException
//   */
//  @ApiOperation(value = "获取商家设备信息", notes = "这个接口获取商家设备信息 ")
//  @RequestMapping(value = "/getMerchantMent", method = RequestMethod.GET)
//  public void getMerchantMent(@RequestHeader("token") String sid, HttpServletResponse response)
//      throws IOException {
//    try {
//      List<SysConfigur> list = this.sysConfigurService.queryNext(SysConfigurUtil.MERCHANTEQUIPMENT);
//      for (SysConfigur sysConfigur : list) {
//        sysConfigur.setChildList(null);
//        sysConfigur.setName(null);
//        // sysConfigur.setDmId(null);
//        sysConfigur.setLevel(null);
//        sysConfigur.setParentId(null);
//        sysConfigur.setUptime(null);
//        sysConfigur.setUserId(null);
//        sysConfigur.setKeyName(null);
//      }
//
//      RespHandler.respOK(list, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取分享信息】(这里用一句话描述这个方法的作用)
//   * 
//   * @param id 第三方id（用户分享 =用户ID，活动分享=活动id，券分享=券id）
//   * @param stype 0 用户分享 1 活动分享 2 券分享
//   * @param response
//   * @throws IOException
//   */
//  @ApiOperation(value = "新版获取分享信息", response = ShareMessge.class,
//      notes = "id  第三方id（用户分享 =用户ID，活动分享=活动id，券分享=券id）  stype (0 用户分享     1 活动分享      2券分享  )")
//  @RequestMapping(value = "/getShareMessage/{stype}/{id}", method = RequestMethod.GET)
//  public void getShareMessage(@RequestHeader("token") String sid, @PathVariable("id") String id,
//      @PathVariable("stype") Integer stype, HttpServletResponse response) throws IOException {
//    try {
//      ShareMessge sm = this.shareMessgeService.findNewShare(stype, id);
//      if (null == sm) {
//        RespHandler.respOK(null, response);
//        return;
//      }
//      JSONObject json = new JSONObject();
//      sm.setDmId(null);
//      sm.setCrtime(null);
//      sm.setEmpid(null);
//      sm.setStatus(null);
//      User u = getUser(sid);
//      if (null != u) {
//        sm.setUrl(sm.getUrl() + "&tucode=" + u.getMphonenum());
//      }
//      json.put("shareMes", sm);
//      RespHandler.respOK(json, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(500, RespMsg.server_throws_exception, response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取分享信息】(这里用一句话描述这个方法的作用)
//   * 
//   * @param sid
//   * @param id 第三方id（用户分享 =0，活动分享=活动id）
//   * @param stype 0 用户分享 1 活动分享
//   * @param response
//   * @throws IOException
//   */
//  @ApiOperation(value = "不建议使用获取分享信息", response = ShareMessge.class,
//      notes = "这个接口 用来获取分享信息 URL:/v1.0/config/getShareMes")
//  @RequestMapping(value = "/getShareMes/{stype}/{id}", method = RequestMethod.GET)
//  @Deprecated
//  public void getShareMes(@RequestHeader("token") String sid, @PathVariable("id") String id,
//      @PathVariable("stype") Integer stype, HttpServletResponse response) throws IOException {
//    try {
//      if (0 == stype && "0".equals(id)) {
//        User u = getUser(sid);
//        id = String.valueOf(u.getDmId());
//      }
//      ShareMessge sm = this.shareMessgeService.findNewShare(stype, id);
//      JSONObject json = new JSONObject();
//      if (sm == null) {
//        RespHandler.respOK(json, response);;
//        return;
//      }
//      sm.setDmId(null);
//      sm.setCrtime(null);
//      sm.setEmpid(null);
//      sm.setStatus(null);
//      if (ShareMessge.Type.ACTIVE_SHARE.value() == stype) {
//        User user = getUser(sid);
//        sm.setUrl(sm.getUrl() + user.getVistCode());
//      }
//      json.put("shareMes", sm);
//      RespHandler.respOK(json, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(500, RespMsg.server_throws_exception, response);
//    }
//  }
//
//  /**
//   * 获取动态可选背景图片
//   * 
//   * @param response
//   * @throws IOException
//   */
//  @ApiOperation(value = "获取动态可选背景图片", notes = "这个接口 用来获取动态可选背景图片  /v1.0/config/getDynamicBackImg")
//  @RequestMapping(value = "/getDynamicBackImg", method = RequestMethod.GET)
//  public void getDynamicBackImg(HttpServletResponse response) throws IOException {
//    try {
//      List<SysConfigur> list =
//          this.sysConfigurService.queryNext(SysConfigurUtil.UserDynamicBACKIMG);
//      StringBuffer imgs = new StringBuffer();
//      JSONObject json = new JSONObject();
//      if (null != list && !list.isEmpty()) {
//        int i = 0;
//        for (SysConfigur sysConfigur : list) {
//          if (i > 0)
//            imgs.append(",");
//          imgs.append(sysConfigur.getKeyValue());
//          i++;
//        }
//      }
//      json.put("imgs", imgs.toString());
//      RespHandler.respOK(json, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【得到下一级行政区】(这里用一句话描述这个方法的作用)
//   * 
//   * @param id 1 代表中国
//   * @param response
//   * @throws IOException
//   */
//  @RequestMapping(value = "/getRegion/{id}", method = RequestMethod.GET)
//  public void getRegion(@PathVariable("id") Long id, HttpServletResponse response)
//      throws IOException {
//    log.debug("正在获取行政区数据...");
//    try {
//      List<Region> list = regionService.findNextRegions(id);
//      JSONObject json = new JSONObject();
//      json.put(RespKey.regions, list);
//      RespHandler.respOK(json, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//
//  }
//  
//  /**
//   * 
//   * 【得到下一级行政区】(这里用一句话描述这个方法的作用)
//   * 
//   * @param id 1 代表中国
//   * @param response
//   * @throws IOException
//   */
//  @RequestMapping(value = "/getRegionForAndroid", method = RequestMethod.GET)
//  public void getRegionForAndroid( HttpServletResponse response)
//      throws IOException {
//    log.debug("正在获取行政区数据...");
//    try {
//      List<Region> list = regionService.findAllRegions();
//      RespHandler.respOK(list, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//
//  }
//
//  /**
//   * 
//   * 【获取行业下级标签】
//   * 
//   * @param id
//   * @param response
//   * @time:2015年5月11日 下午4:52:26
//   * @author 涂鑫
//   * @version
//   */
//  @ApiOperation(value = "获取下级标签")
//  @RequestMapping(value = "/getNextCategory/{id}", method = RequestMethod.GET)
//  public void getNextCategory(@ApiParam(value = "行业id") @PathVariable("id") Long id,
//      HttpServletResponse response) {
//    try {
//      Category cg = new Category();
//      cg.setStatus(StatusEnum.OK.value());
//      cg.setParentID(id);
//      BasicDBObject fields = new BasicDBObject(CategoryKey.name, "");
//      fields.put(CategoryKey.dmId, "");
//      List<Category> findList = categoryService.findList(cg, fields);
//      // JSONObject json = new JSONObject();
//      // json.put(RespKey.categorys, findList);
//      RespHandler.respOK(findList, response);
//    } catch (ServiceException e) {
//      log.error(e.getMessage());
//      RespHandler.respError(RespMsg.getNextCategory_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      log.error("获取行业信息出错...", e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取商家店铺分类 客服端写死】
//   * 
//   * @param response
//   * @param pageIndex
//   * @time:2015年9月10日 下午2:28:54
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "/getStoreCategory/{pageIndex}", method = RequestMethod.GET)
//  public void getStoreCategory(HttpServletResponse response,
//      @PathVariable("pageIndex") Integer pageIndex) {
//    try {
//      StoreCategoryPage page = new StoreCategoryPage();
//      page.setNowPage(pageIndex);
//      Pager<StoreCategory, Long> query = storeCategoryService.query(page, null);
//      RespHandler.respOK(query.getList(), response);
//    } catch (ServiceException e) {
//      RespHandler.respError(RespMsg.getStoreCategory_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      RespHandler.respServerError(response);
//    }
//
//
//  }
//
//  /**
//   * 
//   * 【获取行业类别】
//   * 
//   * @param sid
//   * @param response
//   */
//  @RequestMapping(value = "/getCategory/{type}", method = RequestMethod.GET)
//  public void getCategory(@PathVariable("type") int type, HttpServletResponse response) {
//    try {
//      if (type != CategoryType.person_job.value() && type != CategoryType.Business_job.value()) {
//        RespHandler.respError(400, RespMsg.category_id_error, response);
//        return;
//      }
//      Category cg = new Category();
//      cg.setStatus(StatusEnum.OK.value());
//      cg.setType(type);
//      List<Category> findList = categoryService.findParentCategory(cg);
//      for (Category category : findList) {
//        category.setCrtime(null);
//        // category.setStatus(null);
//        // category.setType(null);
//      }
//      JSONObject json = new JSONObject();
//      json.put(RespKey.categorys, findList);
//      RespHandler.respOK(json, response);
//      return;
//    } catch (Exception e) {
//      log.error("获取行业信息出错...", e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//
//  /**
//   * 
//   * 【获取行业类别】
//   * 
//   * @param sid
//   * @param response
//   */
//  @RequestMapping(value = "/getAllCategory/{type}", method = RequestMethod.GET)
//  public void getAllCategory(@PathVariable("type") int type, HttpServletResponse response) {
//    try {
//      if (type != CategoryType.person_job.value() && type != CategoryType.Business_job.value()) {
//        RespHandler.respError(400, RespMsg.category_id_error, response);
//        return;
//      }
//      List<Category> allCategoryByType = categoryService.getAllCategoryByType(type);
//      RespHandler.respOK(allCategoryByType, response);
//      return;
//    } catch (Exception e) {
//      log.error("获取行业信息出错...", e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【提交code值】
//   * 
//   * @param sid
//   * @param pushCode
//   * @param response
//   * @time:2015年5月6日 下午12:50:27
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "/subPushCode", method = RequestMethod.POST)
//  @ApiOperation(value = "提交推送code值")
//  public void subPushCode(@RequestHeader("token") String sid,
//      @RequestParam("pushCode") String pushCode, HttpServletResponse response) {
//    try {
//      User user = getUser(sid);
//      if (user == null) {
//        RespHandler.respError(401, RespMsg.user_not_login, response);
//        return;
//      }
//      if (StringUtils.isEmpty(pushCode)) {
//        RespHandler.respError(400, RespMsg.pushCode_param_null, response);
//        return;
//      }
//      usersService.savePushCode(user.getDmId(), pushCode);
//      RespHandler.respOK(response);
//    } catch (ServiceException e) {
//      RespHandler.respError(RespMsg.subPushCode_error(e.getErrorType()), response);
//    } catch (Exception e) {
//      RespHandler.respServerError(response);
//    }
//  }
//
//
//
//  /**
//   * 
//   * 【获取用户自己的荣云token】(这里用一句话描述这个方法的作用)
//   * 
//   * @param sid
//   * @param response
//   * @throws IOException
//   */
//  @ApiOperation(value = "获取融云token")
//  @RequestMapping(value = "/rtoken", method = RequestMethod.GET)
//  public void getRongToken(@RequestHeader("token") String sid, HttpServletResponse response)
//      throws IOException {
//    try {
//      log.debug("获取token。。。");
//      User user = getUser(sid);
//      if (user == null) {
//        RespHandler.respError(401, RespMsg.user_not_login, response);
//        return;
//      }
//      String rtoken = rongService.getToken(user.getDmId());
//      JSONObject rjson = new JSONObject();
//      rjson.put(RespKey.rtoken, Base64Encryption.encode(rtoken));
//      RespHandler.respOK(rjson, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.rtoken_error(e.getErrorType()), response);
//    }
//
//  }
//
//  @ApiOperation(value = "获取服务器时间")
//  @RequestMapping(value = "/getServerTime", method = RequestMethod.GET)
//  public void getServerTime(HttpServletResponse response) {
//    try {
//      log.debug("获取系统时间中...");
//      String nowTime = String.valueOf(System.currentTimeMillis());
//      RespHandler.respOK(nowTime, response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取支付配置】
//   * 
//   * @param sid
//   * @param payType
//   * @param response
//   * @time:2015年9月25日 上午9:15:01
//   * @author 涂鑫
//   * @version
//   */
//  @ApiOperation(value = "获取支付参数信息")
//  @RequestMapping(value = "getPayParameter/{payType}", method = RequestMethod.GET)
//  public void getPayParameter(@RequestHeader("token") String sid,
//      @PathVariable("payType") Integer payType, HttpServletResponse response) {
//    try {
//      if (getUser(sid) == null) {
//        RespHandler.respError(401, RespMsg.user_not_login, response);
//        return;
//      }
//      JSONObject json = new JSONObject();
//      if (payType.intValue() == PaymentMethodEnum.ALIPAY.getValue()) {
//        String partnerId =
//            Base64Encryption.encode(sysConfigurService
//                .getBaseProperties("pay.alipy.partnerId.phone"));// 合作者id
//        String seller =
//            Base64Encryption.encode(sysConfigurService.getBaseProperties("pay.alipy.seller"));// 收款商家
//        String privateKey =
//            Base64Encryption.encode(sysConfigurService.getBaseProperties("pay.alipy.privateKey"));// 私钥
//        String publicKey =
//            Base64Encryption.encode(sysConfigurService.getBaseProperties("pay.alipy.publicKey"));// 公钥
//        String backUrl = sysConfigurService.getBaseProperties("pay.alipy.backUrl");// 返回路径
//
//        json.put(RespKey.alipay_default_partner, partnerId);
//        json.put(RespKey.alipay_default_seller, seller);
//        json.put(RespKey.alipay_private_key, privateKey);
//        json.put(RespKey.alipay_public_key, publicKey);
//        json.put(RespKey.callback, backUrl);
//        RespHandler.respOK(json, response);
//      }
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getPayParameter_error(e.getErrorType()), response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//
//  /**
//   * 
//   * 【获取主页数据】
//   * 
//   * @time:2015年11月12日 下午5:47:32
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "getIndexDate/{userType}", method = RequestMethod.GET)
//  public void getIndexDate(@PathVariable(value = "userType") Integer userType,
//      HttpServletResponse response) {
//    try {
//
//      IndexManagePage wherePager = new IndexManagePage();
//      wherePager.setStatus(Status.OK.value());
//      wherePager.setVersionType(userType.intValue());
//      wherePager.setPageSize(30);
//      wherePager.setOrderDirection(true);
//      wherePager.setOrderField("sort");
//      List<IndexManage> findList = indexManageService.query(wherePager, null).getList();
//      RespHandler.respOK(findList, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getIndexDate_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取广告数据】
//   * 
//   * @param sid
//   * @param response
//   * @time:2015年11月13日 下午2:37:02
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "getAdvertDate/{userType}", method = RequestMethod.GET)
//  public void getAdvertDate(@PathVariable("userType") Integer userType, HttpServletResponse response,HttpServletRequest request) {
//    try {
//      AdvertPage whereAdvertManagePage = new AdvertPage();
//      whereAdvertManagePage.setStatus(Status.OK.value());
//      whereAdvertManagePage.setUserType(userType.intValue());
//      String userAgent = request.getHeader("User-Agent").toLowerCase(); 
//      if (userAgent.contains("android")) {
//		//安卓
//    	  whereAdvertManagePage.setClientType(PlatFormEnum.an.value());
//      }else if (userAgent.contains("ios")) {
//    	  whereAdvertManagePage.setClientType(PlatFormEnum.ios.value());
//	}
//      List<Advert> list = advertManageService.query(whereAdvertManagePage, null).getList();
//      RespHandler.respOK(list, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getAdvertDate_error(e.getErrorType()), response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取默认头像】
//   * 
//   * @param sid
//   * @param response
//   * @time:2015年11月13日 下午2:37:02
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "getDefaultHeadImg/{userType}", method = RequestMethod.GET)
//  public void getDefaultHeadImg(@PathVariable(value = "userType") Integer userType,
//      HttpServletResponse response) {
//    try {
//      DefaultHeadImg whereDefaultHeadImg = new DefaultHeadImg();
//      whereDefaultHeadImg.setUserType(userType);
//      whereDefaultHeadImg.setStatus(Status.OK.value());
//      List<DefaultHeadImg> findList = defaultHeadImgService.findList(whereDefaultHeadImg);
//      RespHandler.respOK(findList, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getDefaultHeadImg_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  /**
//   * 
//   * 【获取折扣】
//   * 
//   * @param sid
//   * @param response
//   * @time:2015年11月13日 下午2:37:02
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "getScale", method = RequestMethod.GET)
//  public void getScale(HttpServletResponse response) {
//    try {
//      List<SysConfigur> list = sysConfigurService.queryNext(SysConfigurUtil.PROFIT_SCALE);
//      for (SysConfigur sysConfigur : list) {
//        sysConfigur.setChildList(null);
//        sysConfigur.setName(null);
//        // sysConfigur.setDmId(null);
//        sysConfigur.setLevel(null);
//        sysConfigur.setParentId(null);
//        sysConfigur.setUptime(null);
//        sysConfigur.setUserId(null);
//        sysConfigur.setKeyName(null);
//      }
//      RespHandler.respOK(list, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getScale_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//
//  
//  /**
//   * 
//   * 【获取银行名字】
//   * 
//   * @param sid
//   * @param response
//   * @time:2015年11月13日 下午2:37:02
//   * @author 涂鑫
//   * @version
//   */
//  @RequestMapping(value = "getBankList", method = RequestMethod.GET)
//  public void getBankList(HttpServletResponse response) {
//    try {
//      List<SysConfigur> list = sysConfigurService.queryNext(SysConfigurUtil.BANK_LIST);
//      for (SysConfigur sysConfigur : list) {
//        sysConfigur.setChildList(null);
//        sysConfigur.setName(null);
//        // sysConfigur.setDmId(null);
//        sysConfigur.setLevel(null);
//        sysConfigur.setParentId(null);
//        sysConfigur.setUptime(null);
//        sysConfigur.setUserId(null);
//        sysConfigur.setKeyName(null);
//      }
//      RespHandler.respOK(list, response);
//    } catch (ServiceException e) {
//      log.error(e);
//      RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()),response);
//    } catch (Exception e) {
//      log.error(e);
//      RespHandler.respServerError(response);
//    }
//  }
//}
