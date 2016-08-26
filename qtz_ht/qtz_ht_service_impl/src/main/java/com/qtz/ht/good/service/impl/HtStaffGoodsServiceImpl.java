package com.qtz.ht.good.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.ht.enums.CommodityStatusEnum;
import com.qtz.ht.enums.OperationActionEnum;
import com.qtz.ht.enums.UserType;
import com.qtz.ht.good.dao.HtStaffGoodsDao;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtGoodDescService;
import com.qtz.ht.good.service.HtGoodLogService;
import com.qtz.ht.good.service.HtGoodsAlbumService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtGoodDesc;
import com.qtz.ht.good.vo.HtGoodLog;
import com.qtz.ht.good.vo.HtGoodsAlbum;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.user.service.HtBusinessLogService;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtBusinessLog;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.util.ComparativeAttributeValue;

import cache.BaseProperties;

/**
 * <p>Title:HtStaffGoodsServiceImpl</p>
 * <p>Description:商户商品服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@Service("htStaffGoodsServiceImpl")
public class HtStaffGoodsServiceImpl extends BaseServiceImpl<HtStaffGoods,Long> implements HtStaffGoodsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtStaffGoodsServiceImpl.class);
	/**注入商户商品DAO接口类*/
	@Resource(name="htStaffGoodsDaoImpl")
    private HtStaffGoodsDao dao;
	@Resource(name="htGoodDescServiceImpl")
	private HtGoodDescService htGoodDescService; 
	@Resource(name="htGoodsAlbumServiceImpl")
	private HtGoodsAlbumService htGoodsAlbumService;
	@Resource(name="htGoodLogServiceImpl")
	private HtGoodLogService htGoodLogService;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	@Resource(name="htBusinessServiceImpl")
	private HtBusinessService htBusinessService;
	@Resource(name="htBusinessLogServiceImpl")
	private HtBusinessLogService htBusinessLogService;
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtStaffGoods,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public HtStaffGoods add(HtStaffGoods vo, List<HtGoodCateAsso> cateS, String goodDesc, List<HtGoodsAlbum> albums,
			Long operatorId, String operatorName, Integer operaType) throws ServiceException{
		if (albums == null || albums.size() == 0) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,相册为空");
		}
		//保存商品
		vo.setAuditStatus(Constants.THREE);//目前设计为商品不需要审核，故默认为审核通过
		vo.setMainPicture(albums.get(0).getPhotoAddr());
		vo.setCruser(operatorId);
		vo.setCrtime(System.currentTimeMillis());
		vo.setShelvesTime(vo.getCrtime());
		vo.setStatus(CommodityStatusEnum.under.getValue());
		vo = super.addVo(vo);
		//添加商品详情
		HtGoodDesc desc = new HtGoodDesc();
		desc.setDmId(vo.getDmId());
		desc.setGoodDesc(goodDesc);
		this.htGoodDescService.addVo(desc);
		//添加商品相册
		int i = 0;
		for (HtGoodsAlbum url : albums) {
			i++;
			url.setGoodId(vo.getDmId());
			url.setSort(i);
		}
		this.htGoodsAlbumService.addList(albums);
		//添加商品分类关联
		if (null != cateS && cateS.size() > 0) {
			for (HtGoodCateAsso cateAsso : cateS) {
				cateAsso.setGoodId(vo.getDmId());
				cateAsso.setBusiId(vo.getBusiId());
			}
		}
		this.htGoodCateAssoService.addList(cateS);
		
		//添加操作记录
		HtGoodLog log = new HtGoodLog(vo.getDmId(),
				OperationActionEnum.add.getValue(),
				OperationActionEnum.add.getText(),
				null,
				null,
				operatorId,
				operatorName,
				operaType,
				System.currentTimeMillis());
		this.htGoodLogService.addVo(log);
		return vo;
	}
	@Override
	public void modUpOrDownGood(Long operatorId, String operatorName, Long dmId, Integer status) throws ServiceException{
		HtStaffGoods good = super.findVo(dmId, null);
		if (null == good) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		/*if (status.intValue() == good.getStatus().intValue()) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "已经是当前状态，请勿重复操作！");
		}*/
		HtStaffGoods vo = new HtStaffGoods();
		vo.setDmId(dmId);
		vo.setStatus(status);
		if (CommodityStatusEnum.on.getValue() == status.intValue()) {//如果是上架，则添加上架时间
			vo.setShelvesTime(System.currentTimeMillis());
		}
		super.modVoNotNull(vo);
		//添加操作记录
		JSONArray arr = new JSONArray();
		arr.add("status  由 " + good.getStatus() + " 修改为 " + status);
		HtGoodLog log = new HtGoodLog(vo.getDmId(),
				OperationActionEnum.edit.getValue(),
				OperationActionEnum.edit.getText(),
				null,
				arr.toJSONString(),
				operatorId,
				operatorName,
				UserType.BUSINESS.value(),
				System.currentTimeMillis());
		this.htGoodLogService.addVo(log);
	}
	@Override
	public HtStaffGoods mod(HtStaffGoods vo, List<HtGoodCateAsso> cateS, String goodDesc, List<HtGoodsAlbum> albums,
			Long operatorId, String operatorName,int operaType) throws ServiceException {
		HtStaffGoods old_g = super.findVo(vo.getDmId(), null);
		HtGoodsAlbum a = new HtGoodsAlbum();
		a.setGoodId(vo.getDmId());
		List<HtGoodsAlbum> list1 = this.htGoodsAlbumService.findList(a);
		HtGoodDesc desc = this.htGoodDescService.findVo(vo.getDmId(), null);
		
		List<String> list = new ArrayList<>();
		list.addAll(ComparativeAttributeValue.compare(old_g, vo));
		if (!goodDesc.equals(desc.getGoodDesc())) {
			list.add("goodDesc 由 "+desc.getGoodDesc() + " 修改为 " + goodDesc);
		}
		int o = list1.size(), n = albums.size();
		List<Long> idS = new ArrayList<>();
		if (o < n) {
			for (int i = 0; i < o; i++) {
				list.addAll(ComparativeAttributeValue.compare(list1.get(i), albums.get(i)));
				albums.get(i).setSort(i+1);
				idS.add(list1.get(i).getDmId());
			}
			for (int i = o; i < n; i++) {
				list.addAll(ComparativeAttributeValue.compare(new HtGoodsAlbum(), albums.get(i)));
				albums.get(i).setSort(i+1);
			}
		}else if(o == n){
			for (int i = 0; i < o; i++) {
				list.addAll(ComparativeAttributeValue.compare(list1.get(i), albums.get(i)));
				idS.add(list1.get(i).getDmId());
				albums.get(i).setSort(i+1);
			}
		}else{
			for (int i = 0; i < n; i++) {
				list.addAll(ComparativeAttributeValue.compare(list1.get(i), albums.get(i)));
				idS.add(list1.get(i).getDmId());
				albums.get(i).setSort(i+1);
			}
			for (int i = n; i < o; i++) {
				list.addAll(ComparativeAttributeValue.compare(list1.get(i), new HtGoodsAlbum()));
				idS.add(list1.get(i).getDmId());
			}
		}
		
		if (null != cateS && cateS.size() > 0) {//修改商品分类关联
			HtGoodCateAsso as = new HtGoodCateAsso();
//			as.setBusiId(vo.getBusiId());
			as.setGoodId(vo.getDmId());
			List<HtGoodCateAsso> listA= this.htGoodCateAssoService.findList(as);
			List<Long> idC = new ArrayList<>();
			for (int i = 0;null != listA && i < listA.size(); i++) {
				idC.add(listA.get(i).getDmId());
			}
			if (!idC.isEmpty()) {
				this.htGoodCateAssoService.delList(idC);
			}
			list.addAll(ComparativeAttributeValue.compare(listA, cateS));
			for (HtGoodCateAsso cateAsso : cateS) {
				cateAsso.setGoodId(vo.getDmId());
				cateAsso.setBusiId(vo.getBusiId());
			}
			this.htGoodCateAssoService.addList(cateS);
		}
		
		vo.setMainPicture(albums.get(0).getPhotoAddr());
		super.modVoNotNull(vo);
		
		desc.setGoodDesc(goodDesc);
		this.htGoodDescService.modVoNotNull(desc);
		if(idS.size()>0){
			this.htGoodsAlbumService.delList(idS);
		}
		
		for (HtGoodsAlbum b : albums) {//添加商品相册商品ID
			if (null == b.getGoodId()) {
				b.setGoodId(vo.getDmId());
			}
		}
		this.htGoodsAlbumService.addList(albums);
		//添加操作记录
				HtGoodLog log = new HtGoodLog(vo.getDmId(),
						OperationActionEnum.add.getValue(),
						OperationActionEnum.add.getText(),
						null,
						JSONArray.toJSONString(list),
						operatorId,
						operatorName,
						operaType,
						System.currentTimeMillis());
				this.htGoodLogService.addVo(log);
		return vo;
	}
	
	@Override
	public List<HtStaffGoods> findSpecifyFieldList(Integer status)throws ServiceException{
		try {
			HtStaffGoods obj = new HtStaffGoods();
			obj.setStatus(status);
			return dao.findSpecifyFieldList(obj);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<HtStaffGoods> getPanicBuyingGoodsList(Integer status)throws ServiceException{
		try {
			HtStaffGoods obj = new HtStaffGoods();
			obj.setStatus(status);
			return dao.getPanicBuyingGoodsList(obj);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<HtStaffGoods> findListByGoodsId(Set<Long> idS) throws ServiceException {
		List<HtStaffGoods> list = null;
		if (null != idS && !idS.isEmpty()) {
			try {
				list = dao.findListByGoodsId(idS);
			} catch (DaoException e) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7, "查询失败", e);
			}
		}
		return list;
	}
	@Override
	public void modstock(Long dmId, Integer buyNumber) throws ServiceException {
		try {
			dao.modstock(dmId, buyNumber);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "扣除商品库存失败", e);
		}
	}
	@Override
	public Pager<HtStaffGoods, Long> query(Pager<HtStaffGoods, Long> page, Class<HtStaffGoods> clazz) throws ServiceException {
		page = super.query(page, clazz);
		List<HtStaffGoods> list = page.getList();
		HtBusiness b = null;
		for (HtStaffGoods g : list) {
			b = this.htBusinessService.findVo(g.getBusiId(), null);
			g.setFullName(b.getFullName());
			g.setMobilePhone(b.getMobilePhone());
			g.setLegalPerson(b.getLegalPerson());
		}
		page.setList(list);
		return page;
	}
	@Override
	public void addstock(Long dmId, Integer buyNumber) throws ServiceException {
		try {
			dao.addstock(dmId, buyNumber);
		} catch (DaoException e) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "扣除商品库存失败", e);
		}		
	}
	@Override
	public List<HtStaffGoods> findShelvesTop5List() throws ServiceException {
		// TODO Auto-generated method stub
			try {
				
				return dao.findShelvesTop5List();
				
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		
		}
	@Override
	public void modBatchSupplier(HtStaff staff, List<HtBusiness> list, String operateContent, String voucherAddr) throws ServiceException{
		HtBusiness vo = null;
		Integer frozen = null, shield = null;
		HtBusinessLog log = null;
		List<HtBusinessLog> logList = new ArrayList<>();
		for (HtBusiness htBusiness : list) {
			vo = new HtBusiness();
			if (null == htBusiness.getDmId()) {
				//RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_7, "入参错误,id不能为空", resp, req);
				//break;
				continue;
			}
			vo.setDmId(htBusiness.getDmId());
			frozen = htBusiness.getIsFrozen();
			shield = htBusiness.getIsShield();
			if (null != frozen && frozen.intValue() >= 1 && frozen.intValue() <= 2) {
				vo.setIsFrozen(frozen);
				//上下架商品
				updateStuffShopStatus(staff, htBusiness, frozen);
			}else if(null != shield && shield.intValue() >= 1 && shield.intValue() <= 2){
				vo.setIsShield(shield);
				//上下架商品
				updateStuffShopStatus(staff, htBusiness, shield);
			}else{
				throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
			}
			htBusinessService.modVoNotNull(vo);
			// 保存日志
			log = new HtBusinessLog();
			log.setOperateContent(operateContent);
			log.setVoucherAddr(voucherAddr);
			log.setBusiId(vo.getDmId());
			log.setOperatorId(staff.getDmId());
			log.setOperatorName(staff.getName());
			log.setOperaType(UserType.PPUSER.value());
			log.setOperaTime(System.currentTimeMillis());
			logList.add(log);
		}
		htBusinessLogService.addList(logList);
	}
	
	/**
	 * 根绝商家编号 修改商家的商品上下架状态
	 * updateStuffShopStatus:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param staff  操作人
	 * @param htBusiness  商家
	 * @param businessType 商家状态 1放开 商品状态强制下架，2 放开 
	 */
	private void updateStuffShopStatus(HtStaff staff,HtBusiness htBusiness,int businessType)throws ServiceException{
		int status =0;
		if(businessType == 1){//强制下架
			status =CommodityStatusEnum.force.getValue();
		}else{
			status = CommodityStatusEnum.on.getValue();
		}
		//根据商家编号找到所有商家的商品
		HtStaffGoods htStaffGoods =new HtStaffGoods();
		htStaffGoods.setBusiId(htBusiness.getDmId());
		List<HtStaffGoods> htStaffGoodsList = findList(htStaffGoods);
		if(htStaffGoodsList!=null&&htStaffGoodsList.size()>0){
			for (Iterator<HtStaffGoods> iterator = htStaffGoodsList.iterator(); iterator.hasNext();) {
				HtStaffGoods htStaffGood = iterator.next();
				modUpOrDownGood(staff.getDmId(),staff.getName(),htStaffGood.getDmId(),status);
			}
		}
	}
	@Override
	public List<HtStaffGoods> findListByGoodsIdAndCrtime(Set<Long> idS) throws ServiceException {
		List<HtStaffGoods> list = null;
		if (null != idS && !idS.isEmpty()) {
			try {
				list = dao.findListByGoodsIdAndCrtime(idS);
			} catch (DaoException e) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7, "查询失败", e);
			}
		}
		return list;
	}
	@Override
	public List<HtStaffGoods> findHotSearchGoods() throws ServiceException {
		List<HtStaffGoods> htStaffGoodsList =new ArrayList<HtStaffGoods>();
		String hotGoodsIdStr=BaseProperties.getBaseProperties("HOT_SEARCH");
		if(hotGoodsIdStr!=null&&hotGoodsIdStr.trim().length()>0){
			Set<Long> idS =new HashSet<Long>();
			String[] hotGoodsIdStrArr=hotGoodsIdStr.split(",");
			if(hotGoodsIdStrArr!=null&&hotGoodsIdStrArr.length>0){
				for (int i = 0; i < hotGoodsIdStrArr.length; i++) {
					String hotGoodsIdStrSpl = hotGoodsIdStrArr[i];
					idS.add(Long.parseLong(hotGoodsIdStrSpl));
				}
			}
			//根据编号集合查询出集合
			htStaffGoodsList=findListByGoodsId(idS);
		}
		return htStaffGoodsList;
	}

	@Override
	public Pager<HtStaffGoods, Long> unclassifiedGoods(HtStaffGoodsPage page)throws ServiceException{
		try {
			return dao.unclassifiedGoods(page);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}