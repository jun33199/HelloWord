package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.excel;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web.SbqyInfo;
import com.ttsoft.common.util.Debug;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 无应纳税款申报查询Excel工具</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class ExcelUtil {

  int rowNum = 0;

  SbqyInfo si = null;

  public ExcelUtil() {
  }

  /**
   * 生成excel
   * @param out
   */
  public void generateKKXXExcel(OutputStream out, List dataList) {
    String [] dataTitle={"计算机代码","纳税人名称","企业登记注册类型","企业状态","联系电话","经营地址"};
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
        si=(SbqyInfo)dataList.get(i);
        cell = row.createCell( (short) 0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getJsjdm());
        cell = row.createCell( (short) 1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getNsrmc());
        cell = row.createCell( (short) 2);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getDjzclxmc());
        cell = row.createCell( (short) 3);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getQyztmc());
        cell = row.createCell( (short) 4);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getLxdh());
        cell = row.createCell( (short) 5);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(si.getJydz());
      }
      wb.write(out);
      out.flush();
      out.close();
    }
    catch (Exception ex) {
      Debug.printException(ex);
    }
  }

  /**
   * 成对生成Excel cell
   * @param sheet
   * @param Name 名称
   * @param value  值
   */
  private void createMapCell(HSSFSheet sheet, String Name, String value) {
    HSSFRow row = sheet.createRow( (short) (++rowNum));
    HSSFCell cell;
    cell = row.createCell( (short) 0);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(Name);
    cell = row.createCell( (short) 1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    cell.setCellValue(value);
  }

}