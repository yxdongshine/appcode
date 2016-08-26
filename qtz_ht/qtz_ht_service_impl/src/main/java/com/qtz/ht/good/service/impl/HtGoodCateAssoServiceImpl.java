package com.qtz.ht.good.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.Constants;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.ht.good.dao.HtGoodCateAssoDao;
import com.qtz.ht.good.page.HtGoodCateAssoPage;
import com.qtz.ht.good.service.HtGoodCateAssoService;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.good.vo.HtGoodCateAsso;
import com.qtz.ht.good.vo.HtStaffGoods;
import com.qtz.ht.system.service.CategoryService;
import com.qtz.ht.system.vo.HtCategory;

/**
 * <p>Title:HtGoodCateAssoServiceImpl</p>
 * <p>Description:商家商品与分类关联服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-08-10
 */
@Service("htGoodCateAssoServiceImpl")
public class HtGoodCateAssoServiceImpl extends BaseServiceImpl<HtGoodCateAsso,Long> implements HtGoodCateAssoService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(HtGoodCateAssoServiceImpl.class);
	/**注入商家商品与分类关联DAO接口类*/
	@Resource(name="htGoodCateAssoDaoImpl")
    private HtGoodCateAssoDao dao;
	@Resource(name="categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<HtGoodCateAsso,Long> getDao() {
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
	public List<HtGoodCateAsso> findList(Long goodId) throws ServiceException {
		if (null == goodId) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		HtGoodCateAsso obj1 = new HtGoodCateAsso();//商品分类
		obj1.setGoodId(goodId);
		List<HtGoodCateAsso> cateS = super.findList(obj1);
		HtCategory gc = null;
		if (null != cateS && !cateS.isEmpty()) {
			for (HtGoodCateAsso cate : cateS) {
				gc = categoryService.findVo(cate.getOneCate(), new HtCategory());
				cate.setOneCateName(gc.getName());
				gc = categoryService.findVo(cate.getTwoCate(), new HtCategory());
				cate.setTwoCateName(gc.getName());
				gc = categoryService.findVo(cate.getThreeCate(), new HtCategory());
				cate.setThreeCateName(gc.getName());
			}
		}
		return cateS;
	}
	@Override
	public void modGoodCate(Long goodId, Long cateId, Integer type) throws ServiceException {
		if (null == goodId || null == cateId || null == type) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误");
		}
		if (Constants.ONE == type.intValue()) {
			HtGoodCateAsso gca = new HtGoodCateAsso();
			gca.setGoodId(goodId);
			gca.setThreeCate(cateId);
			List<HtGoodCateAsso> list = super.findList(gca);
			if (null != list && list.size() > 0) {
				throw new ServiceException(ExceptionConstants.ERRORCODE_7,"该分类下已有此商品");
			}
		}
		HtStaffGoods good = htStaffGoodsService.findVo(goodId, null);
		if (null == good) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,没有此商品");
		}
		HtCategory cate = categoryService.findVo(cateId, new HtCategory());
		if (null == cate) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,没有此分类");
		}
		HtCategory two_cate = categoryService.findVo(cate.getParentID(), new HtCategory());	//二级分类
		HtGoodCateAsso gca = new HtGoodCateAsso();
		gca.setGoodId(goodId);
		gca.setThreeCate(cateId);
		List<HtGoodCateAsso> list = super.findList(gca);
		if (null != list && list.size() > 0) {	//有此分类，则不作任何操作
			return;
		}
		HtGoodCateAsso vo = new HtGoodCateAsso();
		vo.setBusiId(good.getBusiId());
		vo.setGoodId(goodId);
		vo.setOneCate(two_cate.getParentID());
		vo.setTwoCate(cate.getParentID());
		vo.setThreeCate(cateId);
		super.addVo(vo);
	}
	
	@Override
	public Pager<HtStaffGoods, Long> findAllByCategory(HtGoodCateAssoPage page)throws ServiceException{
		Pager<HtStaffGoods, Long> staffGoodsPage=new  Pager<HtStaffGoods, Long>();
		List<HtStaffGoods> htStaffGoodsList=new ArrayList<HtStaffGoods>();
		page=(HtGoodCateAssoPage) super.query(page,null);
		HtStaffGoods vo=null;
		if(page!=null&&page.getList().size()>0){
			for (HtGoodCateAsso htGoodCateAsso2 : page.getList()) {
				vo=htStaffGoodsService.findVo(htGoodCateAsso2.getGoodId(), new HtStaffGoods());
//				BeanUtils.copyProperties(vo, htFloor);
				htStaffGoodsList.add(vo);
			}
		}
		staffGoodsPage.setList(htStaffGoodsList);
		return staffGoodsPage;
	}
	@Override
	public Integer queryGoodsCount() throws ServiceException {
		try {
			return dao.queryGoodsCount();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}