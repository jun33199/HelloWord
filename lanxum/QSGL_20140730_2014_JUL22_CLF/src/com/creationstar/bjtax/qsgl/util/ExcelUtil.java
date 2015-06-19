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
     * ����excel
     * @param out
     * @param dataTitle ����
     * @param colList �ֶ�����
     * @param valueList ֵ�б�
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
	 * ����Excel
	 * 
	 * By Junbing Tu 2013.06.16
	 * 
	 * @param wb    HSSFWorkbook
	 * @param sheetNum    ��������
	 * @param sheetNameENG    ������������д
	 * @param sheetNameCHN    ��������������
	 * @param dataTitle    ���ֵ����
	 * @param colList      �ֶ�����
	 * @param valueList    ֵ�б�
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
			
			HSSFCellStyle style = wb.createCellStyle(); // ��ʽ����
			HSSFFont font = wb.createFont();  //�������
			
			// ������Ԫ����ʽ
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ����
		    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //���Ҿ���
		    // ���ñ߿�
		    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    // ��������
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //�������
			style.setFont(font);
			
			//�������б���
			HSSFRow row1 = sheet.createRow((short) 0); 
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (dataTitle.length-1)));
			HSSFCell ce = row1.createCell((short) 0); 
            ce.setEncoding(HSSFCell.ENCODING_UTF_16);// ���Ĵ���   
            ce.setCellValue(sheetNameCHN); // ���ĵ�һ����ʾ������
            ce.setCellStyle(style); //�����ʽ
            
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
				HSSFCellStyle styleValue = wb.createCellStyle(); // ��ʽ����
				
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
