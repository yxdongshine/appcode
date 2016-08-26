package com.qtz.ht.report.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.common.RegexUtil;
import com.qtz.ht.report.order.model.BulkShippingModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportExcel {
	public static List<BulkShippingModel> loadScoreInfo(String xlsPath) throws IOException, BiffException{

		//导入已存在的Excel文件，获得只读的工作薄对象
        FileInputStream fis=new FileInputStream(xlsPath);
        Workbook wk=Workbook.getWorkbook(fis);
        Sheet[] sheets = wk.getSheets();
        if (sheets.length <=0) {
			return null;
		}
        List<BulkShippingModel> list = new ArrayList<>();
        for (int i = 0; i < sheets.length; i++) {
        	Sheet sheet=wk.getSheet(i);
        	//获取总行数
        	int rowNum=sheet.getRows();
        	//从数据行开始迭代每一行
        	for(int j=1;j<rowNum;j++){
        		String orderId = sheet.getCell(0, j).getContents();
        		if (null != orderId && RegexUtil.isInteger(orderId)) {
        			BulkShippingModel info=new BulkShippingModel();
        			info.setOrderId(Long.valueOf(orderId));
        			info.setLogisticsCompany(sheet.getCell(1, j).getContents());
        			info.setLogisticsNumber(sheet.getCell(2, j).getContents());
        			list.add(info);
				}
        	}
		}
        fis.close();
        wk.close();
        return list;
		//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格
		//使用实体类封装单元格数据
            //判断单元格的类型，单元格主要类型LABEL、NUMBER、DATE 
//	        if(sheet.getCell(2,i).getType() == CellType.NUMBER){
//
//		//转化为数值型单元格
//	        NumberCell numCell=(NumberCell)sheet.getCell(2,i);
//		//NumberCell的getValue()方法取得单元格的数值型数据
//	
//			}
//		if(sheet.getCell(3,i).getType()==CellType.NUMBER){
//		NumberCell numCell=(NumberCell)sheet.getCell(3,i);
//		info.setRscore(numCell.getValue);
//		}

//		if(sheet.getCell(4,i).getType() == CellType.DATE){
//		DateCell dateCell=(DateCell)sheet.getCell(4,i);
//		//DateCell的getDate()方法取得单元格的日期型数据
//		info.setDate(dateCell.getDate());
//		}
		}
}

