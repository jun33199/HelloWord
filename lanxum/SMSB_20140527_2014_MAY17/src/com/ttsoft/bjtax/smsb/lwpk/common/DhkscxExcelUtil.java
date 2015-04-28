package com.ttsoft.bjtax.smsb.lwpk.common;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel;
public class DhkscxExcelUtil {

	  int rowNum = 0;

	  PLKKDHCXModel model = null;

	  public DhkscxExcelUtil() {
	  }

	  /**
	   * ����excel
	   * @param out
	   */
	  public void getDhkscxExcel(OutputStream out, List dataList,PKJBSJModel jbsjModel) {
	    String [] dataTitle={"���","˰Ʊ����","˰��","ʵ�ɽ��","�ۿ�ʱ��","�ۿ�״̬","�ۿ�ʧ��ԭ��"};
	    String[] jbsj = {"��˰������","����������(������)","�̶��绰","�ƶ��绰","ע���ַ","��Ӫ��ַ"};
	    try {
	      HSSFWorkbook wb = new HSSFWorkbook();
	      HSSFSheet sheet = wb.createSheet("sheet1");
	      HSSFCell cell = null;
	      HSSFRow row =null;
	      
	      
	      //title create
	      row =sheet.createRow( (short) (++rowNum));
	      cell = row.createCell((short)0);
	      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	      cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	      cell.setCellValue("��˰�˻�����Ϣ");
	      row =sheet.createRow( (short) (++rowNum));
	      //��������
	      for (int i = 0; i < jbsj.length; i++) {
		        cell = row.createCell( (short) i);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsj[i]);
		      }
	      if(jbsjModel!=null){
		        row = sheet.createRow( (short) (++rowNum));
		        cell = row.createCell( (short) 0);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getNsrmc());
		        cell = row.createCell( (short) 1);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getZrr());
		        cell = row.createCell( (short) 2);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getGddh());
		        cell = row.createCell( (short) 3);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getYddh());
		        cell = row.createCell( (short) 4);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getZcdz());
		        cell = row.createCell( (short) 5);
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        cell.setCellValue(jbsjModel.getJydz());
	      		}
	      
	      row =sheet.createRow( (short) (++rowNum));
	      cell = row.createCell((short)0);
	      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	      cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	      cell.setCellValue("��˰�˽ɿ���Ϣ");
	      
		  row =sheet.createRow( (short) (++rowNum));
	      for (int i = 0; i < dataTitle.length; i++) {
	        cell = row.createCell( (short) i);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(dataTitle[i]);
	      }
	      for (int i = 0; i < dataList.size(); i++) {
	        row = sheet.createRow( (short) (++rowNum));
	        model=(PLKKDHCXModel)dataList.get(i);
	        cell = row.createCell( (short) 0);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(String.valueOf(i+1));
	        cell = row.createCell( (short) 1);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(model.getSphm());
	        cell = row.createCell( (short) 2);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(model.getSzmc());
	        cell = row.createCell( (short) 3);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(model.getSjje().toString());
	        cell = row.createCell( (short) 4);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        if("".equals(model.getKksj()) || model.getKksj()==null){
	        	 cell.setCellValue("");
	        }else{
	        	cell.setCellValue(model.getKksj());
	        }
	        
	        cell = row.createCell( (short) 5);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        if("".equals(model.getKkzt()) || model.getKkzt()==""){
	        	 cell.setCellValue("");
	        }else{
	        cell.setCellValue(model.getKkzt());
	        }
	        cell = row.createCell( (short) 6);
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        if("".equals(model.getKksbyy()) || model.getKksbyy()==""){
	        	 cell.setCellValue("");
	        }else{
	        cell.setCellValue(model.getKksbyy());
	        }
	        System.out.println(model.getSphm());
	      }
	      
	      wb.write(out);
	      out.flush();
	      out.close();
	      System.out.println("����excel�ɹ�");
	    }
	    catch (Exception ex) {
	    	System.out.println("����Exvcel���쳣");
	    }
	  }
}