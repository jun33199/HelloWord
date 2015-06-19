package com.ttsoft.bjtax.smsb.util.gzsxexcel;




import java.io.OutputStream;
import java.util.List;

//import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.util.PropertyUtils;


import com.ttsoft.bjtax.smsb.model.client.ExcelParameter;
import com.ttsoft.common.util.Debug;
import java.io.IOException;

//import com.syax.sjsh.common.ConstantKey;
//import com.syax.sjsh.common.ExcelParameter;
//import jxl.Sheet;
//import jxl.Cell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
//import jxl.Workbook;
//import com.syax.frame.util.StringUtil;
//import com.syax.sjsh.vo.YdSwjgVo;


/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExcelUtil {

    private static final String content = "Content-disposition";
    private static final String contentType = "application/vnd.ms-excel";

    /**
     *
     */
    public ExcelUtil() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void generateExcel(ExcelParameter parame) throws Exception {
        if(parame == null)
            throw new java.lang.NullPointerException("�޷�ִ�д�ӡ����,��������ΪNULL");

        //parame.getResponse().resetBuffer();


        parame.getResponse().setHeader(content,
                                       parame.getEncodeFileName());
        //parame.getResponse().setContentType("application/x-tar");
        //parame.getResponse().setContentType(contentType);

        generateExcel(parame.getResponse().getOutputStream(),
                    parame.getTITLE(),parame.getCOLUMS(), parame.getDataList());
    }



    public static void generateExcelByColum(ExcelParameter parame) throws Exception {
        if(parame == null)
            throw new java.lang.NullPointerException("�޷�ִ�д�ӡ����,��������ΪNULL");

        parame.getResponse().resetBuffer();


        parame.getResponse().setHeader(content,
                                       parame.getEncodeFileName());
        parame.getResponse().setContentType(contentType);
        System.out.println("UTIL1"+parame.getCOLUMS().length);
       generateExcelByColum(parame.getResponse().getOutputStream(),
                      parame.getCOLUMS(), parame.getDataList());
    }


    public static void generateExcelByList(ExcelParameter parame) throws Exception {
        if(parame == null)
            throw new java.lang.NullPointerException("�޷�ִ�д�ӡ����,��������ΪNULL");

        parame.getResponse().resetBuffer();


        parame.getResponse().setHeader(content,
                                       parame.getEncodeFileName());
        parame.getResponse().setContentType(contentType);

       generateExcelByList(parame.getResponse().getOutputStream(),
                       parame.getDataList());
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
        	
        	/*  File fileLoad = new File("c:\\Documents and Settings\\Administrator\\����\\jsp.zip");
  		byte[] b = new byte[1024];
  		long fileLength = fileLoad.length();
        String length = String.valueOf(fileLength);
        FileInputStream in = new FileInputStream(fileLoad);
        int n = 0;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
       /* FileInputStream in = new FileInputStream("c:\\Documents and Settings\\Administrator\\����\\api.rar");
  	    byte[] b = new byte[1024];
  		while(true){
  			int temp = in.read(b);
  			if(temp==-1) break;
  			out.write(b,0,temp);
  		}*/
  		//String s=re.toString();
  		//in.close();
        //��������txt�ļ����޸�
  		//String temp = "hellf�����ĸ�����dfdfdfdfdfo";
  		//out.write(re.toString().getBytes());
        //out.flush();
  		
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
                    }
                    else {
                        cell.setCellValue(String.valueOf(v));
                    }
                }
            }
            wb.write(out);
            out.flush();
//            out.close();
        
        
         }catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                out.close();
            }catch(Exception ex1){
                ex1.printStackTrace();
            }
        }
    }



    /**
        * ��������excel
        * @param out
        * @param dataTitle ����
        * @param colList �ֶ�����
        * @param valueList ֵ�б�
        */
       public static void generateExcelByColum(OutputStream out, String[] colList, List valueList) throws
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
               //data create
                       System.out.println("UTIL2"+colList.length);
               for (int i = 0; i < colList.length; i++) {
                   //if (i > 10000) break;
                   row = sheet.createRow((short) (rowNum++));
                   for (int j = 0; j < valueList.size(); j++) {
                        o = valueList.get(j);
                       cell = row.createCell((short) j);
                       cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                       cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                       v = PropertyUtils.getProperty(o, colList[i]);
                       if (v == null) {
                           cell.setCellValue("");
                       }
                       else {
                           cell.setCellValue(String.valueOf(v));
                       }
                   }
               }
               wb.write(out);
               out.flush();
               out.close();
           }
           finally {

           }
    }


       /**
        * ��������excel
        * @param out
        * @param valueList ֵ�б�
        */
       public static void generateExcelByList(OutputStream out, List valueList) throws
               Exception {
           int rowNum = 0;
           List record = null;
           try {
               HSSFWorkbook wb = new HSSFWorkbook();
               HSSFSheet sheet = wb.createSheet("sheet1");
               HSSFCell cell = null;
               HSSFRow row = null;
               Object o = null;
               Object v = null;
               //title create
               row = sheet.createRow((short) (rowNum++));
               //data create
               for (int i = 0; i < valueList.size(); i++) {
                   row = sheet.createRow((short) (rowNum++));
                   //��ȡһ����¼
                   record = (List)valueList.get(i);
                   for (int j = 0; j < record.size(); j++) {
                       o = record.get(j);
                       cell = row.createCell((short) j);
                       cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                       cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                       if (o == null) {
                           cell.setCellValue("");
                       } else {
                           cell.setCellValue(o.toString());
                       }
                   }
               }
               wb.write(out);
               out.flush();
               out.close();
           }
           finally {

           }
    }


    /**
     * ��ȡEXCEL�ļ�
     * @param filepath String  �ļ���URL·��
     * @return ArrayList  �ļ���ϢList
     */
//    public static List OpenExcel(String filepath) {
//        List dataList=new ArrayList();
//
//        try {
//            InputStream is = new FileInputStream(filepath);
//            jxl.Workbook rwb = Workbook.getWorkbook(is);
//            //��ȡ��һ��Sheet��
//            Sheet rs = rwb.getSheet(0);
//
//            for(int i=0;i<rs.getRows();i++){
//                YdSwjgVo vo = new YdSwjgVo();
//                vo.setJsjdm(rs.getCell(0, i).getContents());
//                vo.setJgmc(rs.getCell(1, i).getContents());
//                 dataList.add(vo);
//            }
//
//            rwb.close();
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            return dataList;
//        }
//
//    }

}


