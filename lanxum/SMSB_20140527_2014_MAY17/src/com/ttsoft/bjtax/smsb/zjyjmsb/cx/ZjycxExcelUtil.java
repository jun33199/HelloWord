package com.ttsoft.bjtax.smsb.zjyjmsb.cx;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ttsoft.common.util.Debug;
public class ZjycxExcelUtil {

	  int rowNum = 0;

	  ZjycxlxVO zjyvo = null;

	  public ZjycxExcelUtil() {
	  }

	  /**
	   * 生成excel
	   * @param out
	   */
	  public void getZjycxExcel(OutputStream out, List dataList) {
	    String [] dataTitle={"纳税人名称","计算机代码","吸纳下岗人员人数","减免税类别","申报日期","营业税",
	    		             "城建税","教育费附加","个人所得税","企业所得税","合计","主管税务所名称"};
	    try {
	      HSSFWorkbook wb = new HSSFWorkbook();
	      HSSFSheet sheet = wb.createSheet("sheet1");
	      HSSFCell cell = null;
	      HSSFRow row =null;
	      //title create
	      row =sheet.createRow( (short) (++rowNum));
	      for (int i = 0; i < dataTitle.length; i++) {
	        cell = row.createCell( (short) i);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(dataTitle[i]);
	      }
	      //date create
	      for (int i = 0; i < dataList.size(); i++) {
	        row = sheet.createRow( (short) (++rowNum));
	        zjyvo=(ZjycxlxVO)dataList.get(i);
	        cell = row.createCell( (short) 0);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getNsrmc());
	        cell = row.createCell( (short) 1);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getJsjdm());
	        cell = row.createCell( (short) 2);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getXnxgsyrs());
	        cell = row.createCell( (short) 3);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getJmslb());
	        cell = row.createCell( (short) 4);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getXsjmsj());
	        cell = row.createCell( (short) 5);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getYys());
	        cell = row.createCell( (short) 6);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getCjs());
	        cell = row.createCell( (short) 7);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getJyffj());
	        cell = row.createCell( (short) 8);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getGrsds());
	        cell = row.createCell( (short) 9);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getQysds());
	        cell = row.createCell( (short) 10);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getHj());
	        cell = row.createCell( (short) 11);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(zjyvo.getSwjgzzjgmc());
	      }
	      wb.write(out);
	      out.flush();
	      out.close();
	    }
	    catch (Exception ex) {
	      Debug.printException(ex);
	    }
	  }
}