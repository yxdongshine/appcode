package com.qtz.ht.system.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.RegexUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.dao.HtGoodCateAssoDao;
import com.qtz.ht.good.page.HtGoodCateAssoPage;
import com.qtz.ht.good.page.HtStaffGoodsPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.system.dao.CategoryDao;
import com.qtz.ht.system.page.HtCategoryPage;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.service.HtFloorGoodsService;
import com.qtz.ht.system.vo.HtCategory;
import com.qtz.ht.system.vo.HtFloorGoods;
import com.qtz.ht.util.SeaAmoConstants;

@Service(value="categoryServiceImpl")
public class CategoryServiceImpl extends BaseServiceImpl<HtCategory, Long> implements CategoryService {

	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(CategoryServiceImpl.class);
	@Autowired
	private CategoryDao dao;
	@Resource(name="htGoodCateAssoServiceImpl")
	private HtGoodCateAssoService htGoodCateAssoService;
	@Resource(name="htGoodCateAssoDaoImpl")
    private HtGoodCateAssoDao htGoodCateAssoDao; 
	
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Resource(name="htFloorGoodsServiceImpl")
	private HtFloorGoodsService htFloorGoodsService;
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	protected BizDao<HtCategory, Long> getDao() {
		return dao;
	}

	@Override
	public HtCategory findVoByMark() throws ServiceException {
		try {
			return dao.findVoByMark(SeaAmoConstants.HT_MARK);
		} catch (DaoException e) {
			throw new ServiceException(-1,"海淘分类被删除，无法添加分类，请联系胖胖生活后台管理员",e);
		}
	}

	@Override
	public void addVo(Long parentID, String name, String mainPicture) throws ServiceException {
		if (null == name) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类名称不能为空");
		}
		name = name.trim();
//		if(name.length() < 2 || name.length() > 4)
//		{
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类名称 2-4个字符");
//		}
		try {
			name=URLDecoder.decode(name, "UTF-8");  
		} catch (UnsupportedEncodingException e) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "中文乱码");
		}
		if (null != mainPicture&&!"".equals(mainPicture) && !RegexUtil.isUrl(mainPicture)) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类图片地址不正确");
		}
		HtCategory parent = null;
		if (null  == parentID) {
			HtCategory vo = this.findVoByMark();
			parentID = vo.getDmId();
			parent = vo;
		}else{
			parent = super.findVo(parentID, new HtCategory());
		}
		List<HtCategory> list=null;
		try {
			list=dao.queryOrder(parentID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		int order=1;
		if(list!=null&&list.size()>0){
			order=list.get(0).getOrder()+Constants.ONE;
		}
		HtCategory entity = new HtCategory(); 
		entity.setName(name);
		entity.setParentID(parentID);
		entity.setSysHeadImg(mainPicture);
		entity.setType(Constants.TWO);
		entity.setCrtime(System.currentTimeMillis());
		entity.setStatus(Constants.ZERO);
		entity.setGoodNumber(Constants.ZERO);
		entity.setFloorNumber(Constants.ZERO);
		entity.setOrder(order);
		if (SeaAmoConstants.HT_MARK.equals(parent.getMark())) {	//设置分类级别
			entity.setShow(Constants.ONE);
			entity.setMark(SeaAmoConstants.ONE_HT_MARK);
		}else if (SeaAmoConstants.ONE_HT_MARK.equals(parent.getMark())) {
			entity.setMark(SeaAmoConstants.TWO_HT_MARK);
		}else if (SeaAmoConstants.TWO_HT_MARK.equals(parent.getMark())) {
			entity.setMark(SeaAmoConstants.THREE_HT_MARK);
		}
		super.addVo(entity);
	}

	@Override
	public List<HtCategory> findList(Long parentID) throws ServiceException {
		boolean flag=false;
		if (null == parentID) {
			flag=true;
			HtCategory vo = this.findVoByMark();
			if (null != vo) {
				parentID = vo.getDmId();
			}
		}
		List<HtCategory> list=null;
		HtCategory vo = new HtCategory();
		vo.setParentID(parentID);
		HtGoodCateAsso htGoodCateAsso=null;
		try {
			list=dao.findAllByparentId(parentID,null,null);
			if(list!=null&&list.size()>0){
				for (HtCategory htCategory : list) {
					htGoodCateAsso=new HtGoodCateAsso();
					if(htCategory.getMark().equals(SeaAmoConstants.ONE_HT_MARK)){
						htGoodCateAsso.setOneCate(htCategory.getDmId());
					}else if(htCategory.getMark().equals(SeaAmoConstants.TWO_HT_MARK)){
						htGoodCateAsso.setTwoCate(htCategory.getDmId());
					}else if(htCategory.getMark().equals(SeaAmoConstants.THREE_HT_MARK)){
						htGoodCateAsso.setThreeCate(htCategory.getDmId());
					}
					Long count=htGoodCateAssoDao.findCount(htGoodCateAsso);
					htCategory.setGoodNumber(Integer.parseInt(count+""));
				}
				if(flag){
					int tatle=htStaffGoodsService.findList(new HtStaffGoods()).size();
					int category=htGoodCateAssoService.queryGoodsCount();
					vo.setParentID(null);
					vo.setName("未分类");
					vo.setGoodNumber(tatle-category);
					vo.setOrder(Constants.ZERO);
					list.add(0, vo);
				}
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return list;
	}

	
	
	@Override
	public void modVoNotNull(Long id, String name, String mainPicture) throws ServiceException {
		if (null == name) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类名称不能为空");
		}
		name = name.trim();
//		if(name.length() < 2 || name.length() > 4)
//		{
//			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类名称o 2-4个字符");
//		}
		if (null != mainPicture&&"".equals(mainPicture) && !RegexUtil.isUrl(mainPicture)) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类图片地址不正确");
		}
		HtCategory vo = new HtCategory();
		vo.setDmId(id);
		vo.setName(name);
		if (null != mainPicture) {
			vo.setSysHeadImg(mainPicture);
		}
		super.modVoNotNull(vo);
	}

	@Override
	public void delCategoryById(Long id) throws ServiceException {
		HtCategory vo = super.findVo(id, new HtCategory());
		if (null == vo) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类不存在");
		}
		List<HtCategory> listCate = this.findList(id);
		if (null != listCate && listCate.size() > 0) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "有下级分类，不能删除");
		}
		HtGoodCateAsso query = new HtGoodCateAsso();
		if (SeaAmoConstants.ONE_HT_MARK.equals(vo.getMark())) {
			query.setOneCate(id);
		}else if (SeaAmoConstants.TWO_HT_MARK.equals(vo.getMark())) {
			query.setTwoCate(id);
		}else if (SeaAmoConstants.THREE_HT_MARK.equals(vo.getMark())) {
			query.setThreeCate(id);
		}else{
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		List<HtGoodCateAsso> list = htGoodCateAssoService.findList(query);
		if (null != list && list.size() > 0) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "分类下有商品，不能删除");
		}
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void modStatus(Long id, Boolean status) throws ServiceException {
		if (null == id) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		if (null == status) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		HtCategory vo = new HtCategory();
		vo.setDmId(id);
		vo.setStatus(status.booleanValue() == true? 0 : 1);
		super.modVoNotNull(vo);
	}
	
	@Override
	public void modCategoryStatus(Long id, int show) throws ServiceException {
		if (null == id) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		
		HtCategory category=super.findVo(id, new HtCategory());
		if(category!=null&&category.getOrder()==-1){
			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, category.getName()+"为App首页，不能禁用！");
		}
		HtCategory vo = new HtCategory();
		vo.setDmId(id);
		vo.setShow(show);
		super.modVoNotNull(vo);
	}

	@Override
	public void modSort(String sortStr) throws ServiceException {
		if (null == sortStr) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		JSONArray json = JSONArray.parseArray(sortStr);
		for (int i = 0; i < json.size(); i++) {
			JSONObject result = json.getJSONObject(i);
			HtCategory vo = new HtCategory();
			vo.setDmId(result.getLong("dmId"));
			vo.setOrder(result.getInteger("sort"));
			super.modVoNotNull(vo);
		}
	}

	@Override
	public void modFloorNumber(Long dmId, Integer number) throws ServiceException {
		if (null == dmId || null == number) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		HtCategory vo = new HtCategory();
		vo.setDmId(dmId);
		vo.setFloorNumber(number);
		super.modVoNotNull(vo);
	}

	@Override
	public Pager<HtCategory, Long> queryPage(HtCategoryPage page) throws ServiceException {
		try {
			if (null == page.getParentID()) {
				HtCategory vo = this.findVoByMark();
				if (null != vo) {
					page.setParentID(vo.getDmId());
				}
			}
			return dao.queryPage(page);
		} catch (DaoException e) {
			throw new ServiceException(-1,"分页查询错误",e);
		}
	}

	@Override
	public List<HtCategory> findListStatusAndPra(HtCategory htCategory) throws ServiceException {
		if (null == htCategory.getParentID()) {
			HtCategory vo = this.findVoByMark();
			if (null != vo) {
				htCategory.setParentID(vo.getDmId());
			}
		}
		List<HtCategory> list=null;
		try {
			list=dao.findAllByparentId(htCategory.getParentID(),htCategory.getStatus(),htCategory.getShow());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Pager<HtStaffGoods, Long> findAllByCategory(HtGoodCateAssoPage page)throws ServiceException{
		if(page.getDmId()==null){
			Pager<HtStaffGoods, Long> list=unclassifiedGoods(page);
			return list;
		}
		HtCategory vo=super.findVo(page.getDmId(), new HtCategory());
		if (SeaAmoConstants.ONE_HT_MARK.equals(vo.getMark())) {
			page.setOneCate(page.getDmId());
		}else if (SeaAmoConstants.TWO_HT_MARK.equals(vo.getMark())) {
			page.setTwoCate(page.getDmId());
		}else if (SeaAmoConstants.THREE_HT_MARK.equals(vo.getMark())) {
			page.setThreeCate(page.getDmId());
		}else{
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		page.setDmId(null);
		Pager<HtStaffGoods, Long> list=htGoodCateAssoService.findAllByCategory(page);
		
		return list;
	}
	
	public Pager<HtStaffGoods, Long> unclassifiedGoods(HtGoodCateAssoPage page)throws ServiceException{
		HtStaffGoodsPage goodPage=new HtStaffGoodsPage(); 
		Pager<HtStaffGoods, Long> pager=new Pager<HtStaffGoods, Long>();
		goodPage.setNowPage(page.getNowPage());
		goodPage.setPageSize(page.getPageSize());
		pager=htStaffGoodsService.unclassifiedGoods(goodPage);
		return pager;
	}
	
	@Override
	public List<Map<String, Object>>  categoryGoods(Long floorId)throws ServiceException{
		Long l=null;
		List<HtCategory> list=findList(l);
		List<Map<String, Object>> lMap = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lMap1 = null;
		// 集合对象保存多个构造的 map
		Map<String, Object> map = null;
		Map<String, Object> map1 = null;
		HtFloorGoods obj=new HtFloorGoods();
		obj.setLinkUrl(floorId);
		List<HtFloorGoods> floorGoods=htFloorGoodsService.findList(obj);
		Map<Long,HtFloorGoods> fgMap =new HashMap<Long,HtFloorGoods>();
		if(floorGoods!=null&&floorGoods.size()>0){
			for(HtFloorGoods f:floorGoods){
				fgMap.put(f.getGoodId(), f);
			}
		}
		// map 对象模拟 json 键值对
		HtGoodCateAsso htGoodCateAsso=null;
		HtStaffGoods goods=null;
		list.remove(0);
		for (HtCategory gds : list) {
			map = new HashMap<String, Object>();
			map.put("id", gds.getDmId());
			map.put("text", gds.getName());
			htGoodCateAsso=new HtGoodCateAsso();
			htGoodCateAsso.setOneCate(gds.getDmId());
			List<HtGoodCateAsso> htGoodCateAssoList=htGoodCateAssoService.findList(htGoodCateAsso);
			if (htGoodCateAssoList != null && htGoodCateAssoList.size() > 0) {
				lMap1 = new ArrayList<Map<String, Object>>();
				for (HtGoodCateAsso htGoodCateAsso2 : htGoodCateAssoList) {
					goods=htStaffGoodsService.findVo(htGoodCateAsso2.getGoodId(), null);
					if(fgMap.get(goods.getDmId())==null){
						map1=new HashMap<String, Object>();
						map1.put("id", goods.getDmId());
						map1.put("text", goods.getGoodName());
						lMap1.add(map1);
					}
				}
				map.put("children",lMap1 );
			}
			lMap.add(map);
			
		}
		return lMap;
		
	}

}
