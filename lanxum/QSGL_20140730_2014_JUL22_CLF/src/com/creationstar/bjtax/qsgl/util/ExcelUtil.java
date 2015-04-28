/*
 * Created on 2006-1-10
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.util;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.ttsoft.framework.util.LogUtil;
import com.ttsoft.framework.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts.util.PropertyUtils;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExcelUtil {

    /**
     *
     */
    public ExcelUtil() {
        super();
        //
    }

    /**
     * 生成excel
     * @param out
     * @param dataTitle 标题
     * @param colList 字段名称
     * @param valueList 值列表
     */
    public static void generateExcel(OutputStream out, String[] dataTitle,
                                     String[] colList, List valueList) throws
            Exception {
		int rowNum = 0;
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFCell cell = null;
            HSSFRow row = null;
            Object o = null;
            Object v = null;
            //title create
            row = sheet.createRow((short) (rowNum++));
            for (int i = 0; i < dataTitle.length; i++) {
                cell = row.createCell((short) i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(dataTitle[i]);
            }
            //data create

            for (int i = 0; i < valueList.size(); i++) {
                //if (i > 10000) break;
                row = sheet.createRow((short) (rowNum++));
                o = valueList.get(i);
                for (int j = 0; j < colList.length; j++) {
                    cell = row.createCell((short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    v = PropertyUtils.getProperty(o, colList[j]);
                    if (v == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(v));
                    }
                }
            }
            wb.write(out);
            out.flush();
            out.close();
        } finally {

        }
    }
	
	
	/**
	 * 生成Excel
	 * 
	 * By Junbing Tu 2013.06.16
	 * 
	 * @param wb    HSSFWorkbook
	 * @param sheetNum    工作表编号
	 * @param sheetNameENG    工作表名称缩写
	 * @param sheetNameCHN    工作表名称中文
	 * @param dataTitle    输出值标题
	 * @param colList      字段名称
	 * @param valueList    值列表
	 * @param out    
	 * @throws Exception
	 */
	public static void generateExcel(HSSFWorkbook wb, int sheetNum, String sheetNameENG, String sheetNameCHN, String[] dataTitle,
			String[] colList, List valueList, OutputStream out) throws Exception {
		int rowNum = 1;
		try {
			
			HSSFSheet sheet = wb.createSheet(sheetNameENG);
			
			//wb.setSheetName(sheetNum,sheetName);
			//wb.setSheetName(a,sheetName,(short)HSSFCell.ENCODING_UTF_16);
			
			HSSFCell cell = null;
			HSSFRow row = null;
			Object o = null;
			Object v = null;
			
			HSSFCellStyle style = wb.createCellStyle(); // 样式对象
			HSSFFont font = wb.createFont();  //字体对象
			
			// 创建单元格样式
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //左右居中
		    // 设置边框
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    // 设置字体
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体粗体
			style.setFont(font);
			
			//设置首行标题
			HSSFRow row1 = sheet.createRow((short) 0); 
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (dataTitle.length-1)));
			HSSFCell ce = row1.createCell((short) 0); 
            ce.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文处理   
            ce.setCellValue(sheetNameCHN); // 表格的第一行显示的数据
            ce.setCellStyle(style); //添加样式
            
			// title create
			row = sheet.createRow((short) (rowNum++));
			for (int i = 0; i < dataTitle.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(style);
				cell.setCellValue(dataTitle[i]);
			}
			// data create 
			for (int i = 0; i < valueList.size(); i++) {
				// if (i > 10000) break;
				HSSFCellStyle styleValue = wb.createCellStyle(); // 样式对象
				
				styleValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				styleValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				styleValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
				styleValue.setBorderTop(HSSFCellStyle.BORDER_THIN);
				
				row = sheet.createRow((short) (rowNum++));
				o = valueList.get(i);
				for (int j = 0; j < colList.length; j++) {
					cell = row.createCell((short) j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(styleValue);
					v = PropertyUtils.getProperty(o, colList[j]);
					if (v == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(String.valueOf(v));
						if(String.valueOf(v).length() > 7)
						{
							sheet.setColumnWidth((short) j,(short) (String.valueOf(v).length()*300));							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	


	/**
     * getCellValue
     *
     * @param cell
     * @return
     */
    public static String getCellValue(HSSFCell cell) {
        String lStr = " ";
        if (cell != null) {
            switch (cell.getCellType()) {
            case 1:

                // LogUtil.log("1 " + cell.getStringCellValue() + "\n");

                lStr = StringUtil.getReplaceString(cell.getStringCellValue()
                        .trim(), " ", "");

                break;
            case 0:

                // LogUtil.log("0 " + cell.getNumericCellValue() + "\n");

                DecimalFormat format = (DecimalFormat) NumberFormat
                                       .getPercentInstance();

                format.applyPattern("#####0");

                lStr = StringUtil.getReplaceString(format.format(
                        cell.getNumericCellValue()).trim(), " ", "");
                break;

            case 3:
                LogUtil.log("3    blank" + "\n");
                break;
            default:
                LogUtil.log("blank" + "\n");
                break;
            }
        }
        return lStr;
    }

}
