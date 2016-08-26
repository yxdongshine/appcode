package com.qtz.ht.manage.controller.wallet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.dm.userwallet.enums.ExtractApplyStatusEnum;
import com.qtz.dm.userwallet.enums.UserType;
import com.qtz.ht.enums.PresentStateEnum;
import com.qtz.ht.good.service.HtStaffGoodsService;
import com.qtz.ht.manage.controller.BaseController;
import com.qtz.ht.manage.model.HtCheckRecordModel;
import com.qtz.ht.manage.model.HtCheckRecordResponseModel;
import com.qtz.ht.user.service.HtBusinessService;
import com.qtz.ht.user.service.HtStaffService;
import com.qtz.ht.user.vo.HtBusiness;
import com.qtz.ht.user.vo.HtStaff;
import com.qtz.ht.util.ProcessingCheckExceptionUtil;
import com.qtz.ht.wallet.page.HtCheckRecordPage;
import com.qtz.ht.wallet.service.HtCheckRecordService;
import com.qtz.ht.wallet.service.HtWalletService;
import com.qtz.ht.wallet.vo.HtCheckRecord;
import com.qtz.ht.wallet.vo.HtWallet;

/**
 * <p>Title:HtCheckRecordController</p>
 * <p>Description:商户提现记录表Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-29
 */
@RestController
@RequestMapping("v1.0/withdrawals")
public class HtCheckRecordController extends BaseController {
	
	
	/**初始化日志对象*/
	private final static Logger log= Logger.getLogger(HtCheckRecordController.class);
	/**注入商户提现记录表Service类*/
	@Resource(name="htCheckRecordServiceImpl")
	private HtCheckRecordService htCheckRecordService;
	@Autowired
	private HtBusinessService htBusinessService;
	@Resource(name="htStaffGoodsServiceImpl")
	private HtStaffGoodsService htStaffGoodsService;
	@Autowired
	private HtWalletService htWalletService;
	@Resource(name="htStaffServiceImpl")
	private HtStaffService htStaffService;
	/**
	 * APPLY(0, "对账中"), APPLIED(1, "已提现"), APPLYBACK(2, "驳回"), APPLYCANCEL(3, "取消"),RECOVER(4,"追回");
	 */
	/** 
	* 【分页】
	* @param req
	* @param page
	* @return
	*/
	@RequestMapping(value="page")
	public void page(@RequestParam("token") String sid,
			HtCheckRecordPage page,
			HttpServletRequest request, HttpServletResponse response){
		try {
			/*Long shoppingNo =page.getShoppingNo();
			if(shoppingNo!=null&&shoppingNo.intValue()>0){
				//根据商品编号找到商家
				HtStaffGoods htStaffGoods =new HtStaffGoods();
				htStaffGoods.setDmId(shoppingNo);
				List<HtStaffGoods> listHtStaffGoods = this.htStaffGoodsService.findList(htStaffGoods);
				if(listHtStaffGoods!=null&&listHtStaffGoods.size()>0){
					HtStaffGoods htStaffGoodsInDb =listHtStaffGoods.get(0);
					page.setBusiId(htStaffGoodsInDb.getBusiId());
				}
			}*/
			Pager<HtCheckRecord, Long> pageList=htCheckRecordService.query(page,HtCheckRecord.class);
			Pager<HtCheckRecordResponseModel, Long> result = new Pager<>();	//返回结果
			BeanUtils.copyProperties(pageList, result);
			List<HtCheckRecord> list = pageList.getList();
			HtCheckRecordResponseModel model = null;
			if (null != list && list.size() > 0) {	//添加流水信息及供应商手机号
				List<HtCheckRecordResponseModel> listOrder = new ArrayList<>();
				for (HtCheckRecord htCheckRecord : list) {
					model = new HtCheckRecordResponseModel();
					//找到商家
					HtBusiness b = this.htBusinessService.findVo(htCheckRecord.getBusiId(), null);
					BeanUtils.copyProperties(htCheckRecord, model);
					if(b!=null){
						model.setFullName(b.getFullName());
						model.setMobilePhone(b.getMobilePhone());
					}
					listOrder.add(model);
				}	
				result.setList(listOrder);
				
			}
			RespJsonPHandler.respOK(result,response,request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/** 
	* 【编辑】
	* @param req
	* @param id
	* @return
	*/
	@RequestMapping(value="input")
	public void input(@RequestParam("token") String sid,@RequestParam("dmId")Long dmId,
			HttpServletRequest request, HttpServletResponse response){
		HtCheckRecord vo = new HtCheckRecord();
		try {
			if(null != dmId)
				vo = htCheckRecordService.findVo(dmId,null);
			RespJsonPHandler.respOK(vo,response,request);	
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}

	/**
	 * 
	 * 驳回操作
	 */
	@RequestMapping(value="reject")
	public void reject(@RequestParam("token") String sid,
			@Valid @ModelAttribute HtCheckRecordModel model,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		List<HtCheckRecord> htCheckRecordlist = model.getList();//JSON.parseArray(htCheckRecordListStr, HtCheckRecord.class);  
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()),  response,request);
			    return;
			}
			
			checkRecordOperation(sid, htCheckRecordlist, PresentStateEnum.APPLYBACK.getValue(),model.getOperateContent());
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 同意提现
	 * agreeWithdrawals:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param htCheckRecordList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="agreeWithdrawals")
	public void agreeWithdrawals(@RequestParam("token") String sid,
			@Valid @ModelAttribute HtCheckRecordModel model,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		List<HtCheckRecord> htCheckRecordlist = model.getList();//JSON.parseArray(htCheckRecordListStr, HtCheckRecord.class);  
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()),  response,request);
			    return;
			}
			
			checkRecordOperation(sid, htCheckRecordlist, PresentStateEnum.APPLIED.getValue(),model.getOperateContent());
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 不同意提现
	 * agreeWithdrawals:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param htCheckRecordList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="notAgreeWithdrawals")
	public void notAgreeWithdrawals(@RequestParam("token") String sid,
			@Valid @ModelAttribute HtCheckRecordModel model,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){
		List<HtCheckRecord> htCheckRecordlist = model.getList();//JSON.parseArray(htCheckRecordListStr, HtCheckRecord.class);  
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()),  response,request);
			    return;
			}
			
			checkRecordOperation(sid, htCheckRecordlist, PresentStateEnum.APPLYCANCEL.getValue(),model.getOperateContent());
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	 * 追回订单操作
	 * recoverWithdrawals:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param model
	 * @param result
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="recoverWithdrawals")
	public void recoverWithdrawals(@RequestParam("token") String sid,
			@Valid @ModelAttribute HtCheckRecordModel model,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response){

		List<HtCheckRecord> htCheckRecordlist = model.getList();//JSON.parseArray(htCheckRecordListStr, HtCheckRecord.class);  
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()),  response,request);
			    return;
			}
			
			checkRecordOperation(sid, htCheckRecordlist, ExtractApplyStatusEnum.RECOVER.getValue(),model.getOperateContent());
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	
	}
	
	/**
	 * 操作记录查询
	 * page:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param sid
	 * @param sourceId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="operationRecordPage")
	public void operationRecordPage(@RequestParam("token") String sid,@RequestParam("sourceId") Long sourceId,
			HttpServletRequest request, HttpServletResponse response){
			try {
				HtCheckRecord ht= new HtCheckRecord();
				ht.setSourceId(sourceId);
				List<HtCheckRecord> listHt = htCheckRecordService.findList(ht);
				List<HtCheckRecordResponseModel> listHtMode = new ArrayList<HtCheckRecordResponseModel>();
				if(listHt!=null && listHt.size()>0){
					for (int i = 0; i < listHt.size(); i++) {
						HtCheckRecord htCheckRecord = listHt.get(i);
						HtCheckRecordResponseModel htCheckRecordResponseModel =new HtCheckRecordResponseModel();
						BeanUtils.copyProperties(htCheckRecord, htCheckRecordResponseModel);
						if(htCheckRecord.getDealon()==null){//处理时间
							htCheckRecordResponseModel.setDealon(0L);
							htCheckRecordResponseModel.setStatus(0);
						}
						if(htCheckRecord.getDealby()==null){//操作人名称
							htCheckRecordResponseModel.setCorPersonName(" ");
						}else if(htCheckRecord.getDealby().longValue()>0){
							//根据编号查出用户名称
							HtStaff htstaff = htStaffService.findVo(htCheckRecord.getDealby().longValue(), null);
							if(htstaff !=null){
								htCheckRecordResponseModel.setCorPersonName(htstaff.getName());
							}
						}

						if(htCheckRecord.getDealremark()==null){
							htCheckRecordResponseModel.setDealremark(" ");
						}
						listHtMode.add(htCheckRecordResponseModel);
					}
				}
				RespJsonPHandler.respOK(listHtMode,response,request);	
			} catch (ServiceException e) {
				log.error(e);
				RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
			}
	}
	/**
	 * 验证每一个htCheckRecord 中的需要穿的参数是否是齐全
	 * isParaAll:(). <br/> 
	 * TODO().<br/> 
	 * 
	 * @author yxd 
	 * @param htCheckRecord
	 * @return
	 */
	private boolean isParaAll(HtCheckRecord htCheckRecord){
		boolean isParaAll =false;
		if(htCheckRecord.getDmId()>0/*&&!StringUtils.isEmpty(htCheckRecord.getDealremark())*/){
			isParaAll = true;
		}
		return isParaAll;
	}
	
    /**
     * 操作类型放大
     * checkRecordOperation:(). <br/> 
     * TODO().<br/> 
     * 
     * @author yxd 
     * @param sid
     * @param htCheckRecordList
     * @param operationType
     * @throws ServiceException
     */
	private void checkRecordOperation(String sid,List<HtCheckRecord> htCheckRecordList,int operationType,String rensonStr) throws ServiceException{
		if(htCheckRecordList!=null&&htCheckRecordList.size()>0){
			for (Iterator<HtCheckRecord> iterator = htCheckRecordList.iterator(); iterator.hasNext();) {
				HtCheckRecord htCheckRecord = iterator.next();
				htCheckRecord.setDealremark(rensonStr);
				//循环验证参数是否齐全
				if(isParaAll(htCheckRecord)){//齐全再往下走
					//先查询下是否存在 安全性
					HtCheckRecord vo = htCheckRecordService.findVo(htCheckRecord.getDmId(),null);
                    if(vo!=null&&vo.getStatus()!=null){
                    	HtCheckRecord newhr = new HtCheckRecord();
                    	//先将vo状态修改为对应的操作 更新数据库
                    	vo.setStatus(operationType);
                    	//根据sid取出管理账号
                    	HtStaff htStaff  = getUser(sid);
                    	if(htStaff == null){
                            log.error("该管理不存在");
                    		return ;
                    	}
                    	vo.setUpdateby(htStaff.getDmId());
                    	vo.setUpdateon(System.currentTimeMillis());
                    	vo.setDealremark(htCheckRecord.getDealremark());
                    	htCheckRecordService.modVoNotNull(vo);
                    	
                    	BeanUtils.copyProperties(vo, newhr);
                    	//long newhrDemid = htCheckRecordService.fifteenLongId.nextId();
                    	//newhr.setDmId(newhrDemid);
                    	long sourceId =vo.getDmId();
                    	//设置新对象的源编号
                    	newhr.setSourceId(sourceId);
                    	//设置处理人
                    	newhr.setDealby(htStaff.getDmId());
                    	//设置处理时间
                    	newhr.setDealon(System.currentTimeMillis());
                    	//设置备注
                    	newhr.setDealremark(htCheckRecord.getDealremark());
                    	//添加再一条
                    	htCheckRecordService.addOnceMore(newhr);
                    	
//            			//修改钱包  如果是驳回的话 提现减去，应该收货的金额增加
        				if(ExtractApplyStatusEnum.APPLYBACK.getValue()==operationType || operationType==PresentStateEnum.APPLYCANCEL.getValue()){
        					HtWallet wallet =htWalletService.getWalletByUser(vo.getBusiId(),UserType.BUSINESS.value());
                            if(ExtractApplyStatusEnum.APPLYBACK.getValue()==operationType){//如果是驳回状态 必须保证该条记录是已经同意
                            	if(vo.getStatus() != ExtractApplyStatusEnum.APPLIED.getValue()){ //
            		    			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "只有处于同意提现状态下才能驳回操作");
                            	}
                            }else if(PresentStateEnum.APPLYCANCEL.getValue()==operationType){//如果是驳回状态 必须保证该条记录是已经同意
                            	if(vo.getStatus() != ExtractApplyStatusEnum.APPLY.getValue()){ //
            		    			throw new ServiceException(ExceptionConstants.ERRORCODE_NEGATIVE1, "只有处于对账中状态下才能不同意操作");
                            	}
                            }
        					if(wallet.getTotalCash()>=vo.getAmount()){//保证不能出现负数 在减少
            					htWalletService.modWalletTotalPaymentGoods(wallet.getDmId(), -vo.getAmount());
                            }       					
        				}else if(ExtractApplyStatusEnum.RECOVER.getValue()==operationType){//追回
        					HtWallet wallet =htWalletService.getWalletByUser(vo.getBusiId(),UserType.BUSINESS.value());
        					htWalletService.modWalletTotalPaymentGoods(wallet.getDmId(), vo.getAmount());
        				}
                    }
				}else{
					log.error("钱包提现审核编号："+htCheckRecord.getDmId()+"  参数不齐全");
				}
			}
		}
	}
}