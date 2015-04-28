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
			//��һ��
			HSSFRow row0=sheet.createRow((short)num++);
			   HSSFCell cell = null;
			      HSSFRow row =null;
			 //��ͷ
		    String[] title1 = {"���","�ۿ�����","�־�","˰����","�ۿ�ʱ��","�ɹ�����","ʧ�ܱ���","�ܱ���","�ɹ���","�ɹ����","ʧ�ܽ��","�ܽ��","�ɹ���"};	    
		    //���ͷ��һ��
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
	    		 //���
	    		 cell = row.createCell( (short) 0);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(new Integer(pLKKPLCXModel.getRownum()).toString());
			     //�ۿ�����	
			     cell = row.createCell( (short) 1);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getYh());
			     //�־�	
			     cell = row.createCell( (short) 2);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getFj());
			     //˰����
			     cell = row.createCell( (short) 3);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSws());
			     //�ۿ�ʱ��
			     cell = row.createCell( (short) 4);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     //if(pLKKPLCXModel.getKksj()==null || "".equals(pLKKPLCXModel.getKksj())){
				   //  cell.setCellValue("");
			     //}else{
				     cell.setCellValue(pLKKPLCXModel.getKksj());
			     //}
			     //�ɹ�����
			     cell = row.createCell( (short) 5);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getCgbs());
			     //ʧ�ܱ���
			     cell = row.createCell( (short) 6);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSbbs());
			     //�ܱ���
			     cell = row.createCell( (short) 7);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getZbs());
			     //�����ɹ���
			     cell = row.createCell( (short) 8);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getBscgl());
			     //�ɹ����
			     cell = row.createCell( (short) 9);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getCgje());
			     //ʧ�ܽ��
			     cell = row.createCell( (short) 10);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getSbje());
			     //�ܽ��
			     cell = row.createCell( (short) 11);
			     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			     cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			     cell.setCellValue(pLKKPLCXModel.getZje());
			     //���ɹ���
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
		 * ��ȡ��Ԫ�� 
		 * @param sheet sheet����
		 * @param rowIndex ������ֵ
		 * @param cellIndex ������ֵ
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
		 * ���ñ��ⵥԪ���ʽ
		 * @return
		 */
		private static HSSFCellStyle setTitleCellStyle(HSSFWorkbook workbok)
		{
			HSSFCellStyle cellStyle = workbok.createCellStyle();
			//���õ�Ԫ��߿�
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����
			HSSFFont font = workbok.createFont();
			font.setFontName("����");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)11);//���������С
			cellStyle.setFont(font);
			return cellStyle;
		}
		
		public void getPlkscxDetailExcel(OutputStream out, List dataList){
			String[] title = {"���","���������","��˰������","���������ˣ������ˣ�","�̶��绰","�ƶ��绰","ע���ַ","��Ӫ��ַ","˰Ʊ����","˰��","ʵ�ɽ��","�ۿ�ʱ��","�ۿ�״̬","�ۿ�������"};
			int num = 0;
			Map map = null;
			try {
			      HSSFWorkbook wb = new HSSFWorkbook();
			      HSSFSheet sheet = wb.createSheet("sheet1");
			      HSSFCell cell = null;
			      HSSFRow row =null;
			      
			      row =sheet.createRow( (short) (num++));
			      //��������
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
			      System.out.println("����excel�ɹ�");
			    }
			    catch (Exception ex) {
			    	System.out.println("����Exvcel���쳣");
			    }
		}
		
		
}
