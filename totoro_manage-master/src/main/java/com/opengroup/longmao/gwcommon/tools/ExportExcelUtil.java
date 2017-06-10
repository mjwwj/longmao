package com.opengroup.longmao.gwcommon.tools;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

/**
 * 导出excel工具类
 * @author liunan
 *
 */
@SuppressWarnings("deprecation")
public class ExportExcelUtil {
	
	/**
	 * 导出Excel
	 * @param excelTitle  excel标题
	 * @param excelHeader excel列表头
	 * @param listData    excel列表数据  数据之间“,”分隔
	 * @param extInfo 	  excel额外行信息
	 * @param listColumn  excel列表列数
	 * @return
	 */
	public static HSSFWorkbook export(String excelTitle, String[] excelHeader, List<String[]> listData, List<String> extInfo, int listColumn) {    
		//第一步，创建一个webbook，对应一个Excel文件 
		HSSFWorkbook wb = new HSSFWorkbook();
		//第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(excelTitle);
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，文字居中 
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //上下居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
        //设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);	// 下边框   
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);		// 左边框   
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);	// 右边框   
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);		// 上边框   
        short rowHeight = 400;
        int rowWidth = 3500;
        //设置行高
        int extlen = 0;
        //创建额外展示行
        if(null!=extInfo){
        	extlen = extInfo.size();
        	for(int i=0; i<extInfo.size();i++){
        		//合并单元格 开始行 结束行 开始列 结束列  
        		sheet.addMergedRegion(new CellRangeAddress(i, i, 0, listColumn));  
        		row = sheet.createRow(i);
        		row.setHeight(rowHeight);
        		HSSFCell cell = row.createCell(0);  
        		cell.setCellValue(extInfo.get(i));  
        		cell.setCellStyle(style);  
        	}
        }
        //创建列表头
        if(null!=excelHeader){
        	row = sheet.createRow(extlen);
        	row.setHeight(rowHeight);
        	for (int i = 0; i < excelHeader.length; i++) {  
        		HSSFCell cell = row.createCell(i);    
        		cell.setCellValue(excelHeader[i]);    
        		cell.setCellStyle(style);    
        		sheet.setColumnWidth(i,rowWidth);  
        	}    
        }
        //创建列表数据
        if(null!=listData){
        	for (int i = 0; i < listData.size(); i++) {    
        		row = sheet.createRow(i + extlen + 1);
        		row.setHeight(rowHeight);
//        		String dataStr = listData.get(i);
//        		String[] list = dataStr.split(",");
        		String[] list = listData.get(i);
        		for(int e=0; e<list.length; e++){
        			HSSFCell cell = row.createCell(e);    
        			cell.setCellValue(list[e]);  
        			cell.setCellStyle(style);
        		}
        	}    
        }
        return wb;    
    }    
}
