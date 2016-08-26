package com.qtz.ht.report.controller.order;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.controller.BaseController;
import com.qtz.ht.report.order.model.BulkShippingModel;
import com.qtz.ht.report.order.model.HtOrderRequestModel;
import com.qtz.ht.report.order.model.HtOrderResponseModel;
import com.qtz.ht.report.order.service.OrderService;
import com.qtz.ht.report.util.ExportExcel;
import com.qtz.ht.report.util.ImportExcel;
import com.qtz.ht.user.vo.HtUser;


/**
 * 
 * 
 * <p>Title:HtOrderController</p>
 * <p>Description:(订单操作表控制类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年5月25日
 */
@RestController
@RequestMapping(value="/v1.0/order")
public class HtOrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="modOrderIdCard")
	public void modOrderIdCard(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			orderService.modOrderIdCard();
		} catch (ServiceException e) {
			log.error(e);
		}
	}
	
	/**
	 * 商家批量 发货
	 * bulkShipping:(). <br/> 
	 * @author yxd 
	 * @param sid
	 * @param file 名称要求为商家编号 保证唯一
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/bulkShipping",method= RequestMethod.POST)
    public void bulkShipping(@RequestParam("token") String sid,
    	    MultipartFile otherfile,
    		HttpServletRequest request, HttpServletResponse response) {
		
		log.error("上传文件来了   " + sid);
    	HtUser user = null;
		try {
			request.setCharacterEncoding("UTF-8");
			user = getUser(sid);
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
			return;
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
   	 	if(user==null){
			RespHandler.respError(RespMsg.user_not_login, response);
			return;
		}
		String path = request.getSession().getServletContext().getRealPath("upload");
		if(path==null){
			File pathFile = new File(request.getSession().getServletContext().getContextPath()+"/"+"upload");
			pathFile.mkdirs();
			path = pathFile.getAbsolutePath();
		}
        String fileName = otherfile.getOriginalFilename();
        log.debug(path);
        File targetFile = new File(path + "/" + DateUtil.getDatePath(), fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }else{
        	targetFile.delete();
        }
        try {
        	//保存 
        	otherfile.transferTo(targetFile);
            List<BulkShippingModel> list = ImportExcel.loadScoreInfo(targetFile.getAbsolutePath());
//            orderService.modBulkShipping(null, list);
            orderService.modBulkShipping(user.getBusiId(), list);
            RespHandler.respOK(response);
        } catch (ServiceException e) {
        	RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
        } catch (Exception e) {
		RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "上传文件失败！", response, request);
	}

    }
	
	/**
	 * 导出订单
	 * exportHtOrderSpileSupp:(). <br/> 
	 * @author yxd 
	 * @param sid 
	 * @param htOrderRequestModel 请求参数列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportHtOrderSpileSupp")
	public void exportHtOrderSpileSupp(@RequestParam("token") String sid, 
		    HtOrderRequestModel htOrderRequestModel,
			HttpServletRequest request, HttpServletResponse response){
		
		ExportExcel<HtOrderResponseModel> ex = new ExportExcel<HtOrderResponseModel>();
		String[] headers = { "订单编号","商品编号", "订单状态", "买家昵称 ","商品名称","商品成本价","商品售价","商品数量","订单金额","订单成本价","收货人姓名","收货人地址-省市","收货人街道地址","收货人手机","身份证号","身份证姓名","下单时间","付款时间","补贴抵扣","支付信息","供应商信息","备注"};
		try {
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			OutputStream out = response.getOutputStream();
			HtUser user = getUser(sid);
			if(user == null){
				RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "请登录", response, request);
				return ;
			}
//			response.setContentType("octets/stream");
			response.setContentType("application/vnd.ms-excel");
			String sheet = "订单";
			if (null != htOrderRequestModel && null != htOrderRequestModel.getStatus()) {
				switch (htOrderRequestModel.getStatus()) {
				case 0:
					sheet = "已完成";
					break;
				case 4:
					sheet = "已发货";
					break;
				case 5:
					sheet = "待发货";
					break;

				default:
					sheet = "全部订单";;
				}
			}
			String fileName = orderService.getBusinessName(user.getBusiId()) + sheet + "订单";
	//		String fileName = orderService.getBusinessName(htOrderRequestModel.getBusiId()) + sheet + "订单";

			if (agent.contains("firefox")) {
				response.setCharacterEncoding("utf-8");
				response.setHeader("content-disposition", "attachment;filename=" + new String(URLEncoder.encode(fileName, "UTF-8").getBytes(), "ISO8859-1") + ".xls");
			} else {
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
			}
			List<HtOrderResponseModel> dataset = orderService.findHtOrderResponseModels(user.getBusiId(), htOrderRequestModel.getStatus(), htOrderRequestModel.getDetailAddr(), htOrderRequestModel.getDmId(), htOrderRequestModel.getConsignee(), htOrderRequestModel.getMphoneNo(), htOrderRequestModel.getStartCrtime(), htOrderRequestModel.getEndCrtime());
			//		List<HtOrderResponseModel> dataset = orderService.findHtOrderResponseModels(htOrderRequestModel.getBusiId(), htOrderRequestModel.getStatus(), htOrderRequestModel.getDetailAddr(), htOrderRequestModel.getDmId(), htOrderRequestModel.getConsignee(), htOrderRequestModel.getMphoneNo(), htOrderRequestModel.getStartCrtime(), htOrderRequestModel.getEndCrtime());
			List<HtOrderResponseModel> transDataset = orderService.transHtOrderResponseModels(dataset);
			ex.exportExcel(sheet, headers, transDataset, out);
			out.close();
			log.debug("订单excel导出成功！");
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (IOException e) {
			RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "导出订单数据失败！", response, request);
		}
	}
}
