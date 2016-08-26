package com.qtz.ht.report.controller.goods;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.qtz.ht.report.controller.BaseController;
import com.qtz.ht.report.good.model.GoodStockModel;
import com.qtz.ht.report.good.service.GoodService;
import com.qtz.ht.report.order.service.OrderService;
import com.qtz.ht.report.util.ExportExcel;
import com.qtz.ht.user.vo.HtUser;

/**
 * <p>Title:HtStaffGoodsController</p>
 * <p>Description:商户商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息技术有限公司</p>
 * @author tanglijun
 * @version v1.0 2016-04-07
 */
@RestController
@RequestMapping("v1.0/good")
public class HtStaffGoodsController extends BaseController {
	/**注入商户商品Service类*/
	@Autowired
	private GoodService goodService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value="exportStock")
	public void exportOrderList(@RequestParam("token") String sid, HttpServletRequest request, HttpServletResponse response) 
	{
		ExportExcel<GoodStockModel> ex = new ExportExcel<GoodStockModel>();
		String[] headers = { "商品编号", "商品名称","成本价","销售价格", "库存"};
		try {
			HtUser user = getUser(sid);
//			response.setContentType("octets/stream");
			response.setContentType("application/vnd.ms-excel");
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			String fileName = orderService.getBusinessName(user.getBusiId()) + "上架商品";
			if (agent.contains("firefox")) {
				response.setCharacterEncoding("utf-8");
				response.setHeader("content-disposition", "attachment;filename=" + new String(URLEncoder.encode(fileName, "UTF-8").getBytes(), "ISO8859-1") + ".xls");
			} else {
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
			}
			OutputStream out = response.getOutputStream();
			List<GoodStockModel> dataset = goodService.findListGoods(user.getBusiId(), 1, 3);
			ex.exportExcel("上架商品库存", headers, dataset, out);
			out.close();
			log.debug("商品excel导出成功！");
		} catch (ServiceException e) {
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (IOException e) {
			RespJsonPHandler.respError(ExceptionConstants.ERRORCODE_NEGATIVE1, "导出商品库存数据失败！", response, request);
		}
	}
	
}