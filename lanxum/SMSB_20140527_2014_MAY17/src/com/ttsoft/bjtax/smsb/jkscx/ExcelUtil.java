package com.ttsoft.bjtax.smsb.jkscx;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.ttsoft.common.util.Debug;
import java.io.IOException;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;



public class ExcelUtil
{
    public ExcelUtil()
    {
    }
    public static void main(String[] args)
    {
        ExcelUtil excelUtil1 = new ExcelUtil();
    }

    /**
     * 生成excel
     * @param out
     * @param dataTitle 标题
     * @param colList 字段名称
     * @param valueList 值列表
     */
    public static void generateExcel(OutputStream out, String []dataTitle,String []colList,List valueList)
        throws Exception
    {
      int rowNum = 0;
      try {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
         HSSFCell cell = null;
         HSSFRow row =null;
         Object o = null;
         Object v = null;
        //title create
        row =sheet.createRow( (short) (rowNum++));
        for (int i = 0; i < dataTitle.length; i++) {
          cell = row.createCell( (short) i);
          cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          cell.setEncoding(HSSFCell.ENCODING_UTF_16);
          cell.setCellValue(dataTitle[i]);
        }
        //data create

        for (int i = 0; i < valueList.size(); i++) {
          //if (i > 10000) break;
          row = sheet.createRow( (short) (rowNum++));
          o=valueList.get(i);
          for (int j = 0; j < colList.length; j++){
              cell = row.createCell( (short) j);
              cell.setCellType(HSSFCell.CELL_TYPE_STRING);
              cell.setEncoding(HSSFCell.ENCODING_UTF_16);
              v =  BeanUtil.getProperty(o,colList[j]);
              if (v==null){
                  cell.setCellValue("");
              }else{
                  cell.setCellValue(String.valueOf(v));
              }
          }
        }
        wb.write(out);
        out.flush();
        out.close();
      }
      finally  {

      }
    }


}