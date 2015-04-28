package com.ttsoft.bjtax.smsb.lwpk.common;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PLKKPLCXModel;
import com.ttsoft.common.util.Debug;

public class PlkscxExcelUtil {
	int num = 0;
	 public void getPlkscxExcel(OutputStream os, List dataList) throws Exception{
		 try {
			 HSSFWorkbook book=new HSSFWorkbook(); 
			 HSSFSheet sheet=book.createSheet("sheet1");
			//第一行
			HSSFRow row0=sheet.createRow((short)num++);
			   HSSFCell cell = null;
			      HSSFRow row =null;
			 //表头
		    String[] title1 = {"序号","扣款银行","分局","税务所","扣款时间","成功笔数","失败笔数","总笔数","成功率","成功金额","失败金额","总金额","成功率"};	    
		    //打表头第一行
		    for(short i = 0 ;i<title1.length;i++){
		    	if(i==0){
		    		setCell(sheet,i,row0,title1[i],setTitleCellStyle(book));
		    		//sheet.setColumnWidth(i, (short)5);
		    	}
		    	else{
		    		setCell(sheet,i,row0,title1[i],setTitleCellStyle(book));
		    		//sheet.setColumnWidth(i, (short)10);
		    	}
		    }
		    for(int i=0;i<dataList.size();i++){
	    		 PLKKPLCXModel pLKKPLCXModel=(PLKKPLCXModel)dataList.get(i);
	    		 row = sheet.createRow( (short) (num++));
	    		 //编号
	    		 cell = row.createCell( (short) 0);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(new Integer(pLKKPLCXModel.getRownum()).toString());
			     //扣款银行	
			     cell = row.createCell( (short) 1);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getYh());
			     //分局	
			     cell = row.createCell( (short) 2);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getFj());
			     //税务所
			     cell = row.createCell( (short) 3);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSws());
			     //扣款时间
			     cell = row.createCell( (short) 4);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     //if(pLKKPLCXModel.getKksj()==null || "".equals(pLKKPLCXModel.getKksj())){
				   //  cell.setCellValue("");
			     //}else{
				     cell.setCellValue(pLKKPLCXModel.getKksj());
			     //}
			     //成功笔数
			     cell = row.createCell( (short) 5);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getCgbs());
			     //失败笔数
			     cell = row.createCell( (short) 6);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSbbs());
			     //总笔数
			     cell = row.createCell( (short) 7);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getZbs());
			     //笔数成功率
			     cell = row.createCell( (short) 8);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getBscgl());
			     //成功金额
			     cell = row.createCell( (short) 9);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getCgje());
			     //失败金额
			     cell = row.createCell( (short) 10);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSbje());
			     //总金额
			     cell = row.createCell( (short) 11);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getZje());
			     //金额成功率
			     cell = row.createCell( (short) 12);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getJecgl());
			     
		    }		 
		    book.write(os);
		    os.flush();
		    os.close();
		    
		 } catch (Exception ex) {
		      Debug.printException(ex);
		 }
    }	
	 
	 /**
		 * 获取单元格 
		 * @param sheet sheet对象
		 * @param rowIndex 行索引值
		 * @param cellIndex 列索引值
		 * @return
		 */
		private static HSSFCell setCell(HSSFSheet sheet,short cellIndex,HSSFRow row,String cellValue,HSSFCellStyle cellStyle)
		{
			HSSFCell cell=row.createCell(cellIndex);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(cellValue);
			cell.setCellStyle(cellStyle);		
			return cell;
		} 
		/**
		 * 设置标题单元格格式
		 * @return
		 */
		private static HSSFCellStyle setTitleCellStyle(HSSFWorkbook workbok)
		{
			HSSFCellStyle cellStyle = workbok.createCellStyle();
			//设置单元格边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			HSSFFont font = workbok.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)11);//设置字体大小
			cellStyle.setFont(font);
			return cellStyle;
		}
		
		public void getPlkscxDetailExcel(OutputStream out, List dataList){
			String[] title = {"序号","计算机代码","纳税人名称","法定代表人（责任人）","固定电话","移动电话","注册地址","经营地址","税票号码","税种","实缴金额","扣款时间","扣款状态","扣款结果描述"};
			int num = 0;
			Map map = null;
			try {
			      HSSFWorkbook wb = new HSSFWorkbook();
			      HSSFSheet sheet = wb.createSheet("sheet1");
			      HSSFCell cell = null;
			      HSSFRow row =null;
			      
			      row =sheet.createRow( (short) (num++));
			      //基本数据
			      for (int i = 0; i < title.length; i++) {
				        cell = row.createCell( (short) i);
				        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				        cell.setCellValue(title[i]);
				      }

				 
			      for (int i = 0; i < dataList.size(); i++) {
			        row = sheet.createRow( (short) (num++));
			        map=(HashMap)dataList.get(i);
			        cell = row.createCell( (short) 0);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(String.valueOf(i+1));
			        cell = row.createCell( (short) 1);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("jsjdm").toString());
			        cell = row.createCell( (short) 2);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("nsrmc").toString());
			        cell = row.createCell( (short) 3);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("xm").toString());
			        cell = row.createCell( (short) 4);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("gddh").toString());
			        cell = row.createCell( (short) 5);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("yddh").toString());
			        cell = row.createCell( (short) 6);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("zcdz").toString());
			        
			        cell = row.createCell( (short) 7);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("jydz").toString());
			        cell = row.createCell( (short) 8);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("sphm").toString());
			        cell = row.createCell( (short) 9);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("szmc").toString());
			 
			        cell = row.createCell( (short) 10);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("sjje").toString());
			        
			        cell = row.createCell( (short) 11);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("kkrq").toString());
			     
			        cell = row.createCell( (short) 12);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("kkjg").toString());
			       
			        cell = row.createCell( (short) 13);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			        cell.setCellValue(map.get("yhkkjgms").toString());
			        
			        
			      }
			      
			      wb.write(out);
			      out.flush();
			      out.close();
			      System.out.println("导出excel成功");
			    }
			    catch (Exception ex) {
			    	System.out.println("导出Exvcel抛异常");
			    }
		}
		
		
}
