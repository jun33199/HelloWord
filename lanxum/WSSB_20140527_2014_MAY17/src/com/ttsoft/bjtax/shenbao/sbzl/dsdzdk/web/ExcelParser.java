package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import com.ttsoft.bjtax.shenbao.model.client.*;
import com.ttsoft.bjtax.shenbao.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.common.util.Debug;

public class ExcelParser {

    /**
     * ���ļ���������һ��list������������excel�ļ�������
     * @param is ������
     * @param outclass ������class
     * @return �������Ϊoutclass��list
     * @throws java.lang.Exception
     */
    public List parseExcel(InputStream is, Class outclass) throws Exception
    {

        try
        {
             HSSFWorkbook wb = null;
            try //Ϊ�˵õ�����ȷ���쳣���Ծ����������һ���ж��Ƿ���excel�ļ�
            {
                POIFSFileSystem fs = new POIFSFileSystem(is); // POI���ļ�ϵͳ��������ڴ򿪲���ȡ���ϴ���Excel�ļ�
                wb = new HSSFWorkbook(fs); // �õ���Excel�ļ��Ĺ��������
            }
            catch(Exception ex)
            {
                throw new ApplicationException("���ϴ��Ƿ�Excel�ļ�!");
            }

            //���Excel�ļ�
            Map arrMap = checkExcelFile(wb);

            //�����ļ�
            List list = getData(wb, arrMap, outclass);

            return list;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * ����ļ�������ļ��Ķ����ʽ
     * @param wb Excel������
     * @return �������ԵĶ���map
     * @throws java.lang.Exception
     */
    private Map checkExcelFile(HSSFWorkbook wb) throws Exception {
        HSSFSheet sheet1 = wb.getSheetAt(1); // ��õ�2��Sheet�ľ��
        HSSFSheet sheet2 = wb.getSheetAt(2); // ��õ�3��Sheet�ľ��

        //��õ�һ�е����ݶ���
        Iterator rows = sheet1.rowIterator();
        HSSFRow row = null;
        if(rows.hasNext())
        {
            row = sheet1.getRow(0);
        }
        else
        {
            throw new ApplicationException(
                "excel�ļ����Ϲ���:���ڸ�excel�ļ��е�sheet2ҳ�ĵ�һ��ָ��������Ϣ,"
                                +"�������֪���ϴ����ļ���ʽ�������ݹ���Ա��ϵ!");
        }
        Map arrMap = new HashMap();

        Iterator cells1 = row.cellIterator(); // ȡ�ø������е�Ԫ���ö��
        while (cells1.hasNext()) { // ȡ��ÿ��cell�����ݣ�������xls2Ptli��˳������pTLI
            HSSFCell cell = (HSSFCell) cells1.next();
            String cellContent = cell.getStringCellValue();
            if (cellContent != null && cellContent.trim().length() > 0) {
                Short num = new Short(cell.getCellNum());
                arrMap.put(num, cellContent);
            }
        }

        return arrMap;

    }

    /**
     * ȡ������
     * @param wb Excel������
     * @param arrMap ����map
     * @param outclass ���������
     * @return  List ���������б�
     * @throws java.lang.Exception
     */
    private List getData(HSSFWorkbook wb, Map arrMap, Class outclass) throws
        Exception {

        List datas = new ArrayList();

        HSSFSheet sheet = wb.getSheetAt(0); // ��õ�һ��Sheet�ľ��
        HSSFRow row = null; // ��Sheet��ĳ�еľ��
        Iterator cells = null; // ���е�����cell��ö��

        short cellNum; // ���浱ǰcell��row�е�λ��(0 based)
        String cellContent = null; // String���͵�Cell������
        double cellNumeric = 0; // Numeric���͵�Cell������

        // ��ȡ��һ��sheet�е����м�¼
        int rows = sheet.getLastRowNum(); // ��õ�һ��Sheet���һ�е�����

        for (int i = 0; i <= rows; i++) {
            Object item = outclass.newInstance();
            row = sheet.getRow(i);
            cells = row.cellIterator(); // ȡ�ø������е�Ԫ��ı�����
            while (cells.hasNext()) { // ȡ��ÿ��cell������
                HSSFCell cell = (HSSFCell) cells.next();

                Object num = new Short(cell.getCellNum());
                String attr = (String) arrMap.get(num);
                if (attr != null && attr.trim().length() > 0) {
                    int index = attr.indexOf(":");
                    String arrName = attr;
                    String arrType = "String";

                    if (index > 1) {
                        arrName = attr.substring(0, index);
                        arrType = attr.substring(index + 1);
                    }

                    if ( (arrName != null && arrName.trim().length() > 0) &&
                        (arrType != null && arrType.trim().length() > 0)) {

                        cellContent = getCellValue(cell, arrType);
                        org.apache.struts.util.PropertyUtils.setSimpleProperty(
                            item, arrName, cellContent);
                    }
                }

            }
            datas.add(item);
        }

        return datas;
    }

    /**
     * ȡ��һ��CELL������
     * @param cell ��Ԫ�����
     * @param type ���Ͳ���
     * @return String����Ԫ������
     */
    private String getCellValue(HSSFCell cell, String type) {
        String s = null;

        int cellType = cell.getCellType();

        switch (cellType) {
            case HSSFCell.CELL_TYPE_BLANK:
                s = "";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                s = String.valueOf(cell.getBooleanCellValue());
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                if (type.equalsIgnoreCase("Datetime")) {
                    s = new java.sql.Timestamp(cell.getDateCellValue().getTime()).
                        toString();
//                if(type.equalsIgnoreCase("Date")){
//                    s = new java.sql.Timestamp(cell.getDateCellValue().getTime()).toLocaleString();
//                    s = new java.text.SimpleDateFormat("yyyy-MM-dd").format(
//                        cell.getDateCellValue());
//                }else if(type.equalsIgnoreCase("time")){
//                        s = new java.text.SimpleDateFormat("HH-mm-ss").format(
//                    cell.getDateCellValue());
                }
                else {
                    if(type.equals("Text"))
                    {
                        long t = (long)cell.getNumericCellValue();
                        s = t + "";
                    }
                    else
                    {
                        s = cell.getNumericCellValue() + "";
                        if(s.indexOf(".") != -1 &&
                           (s.length() - s.indexOf(".")) > 3
                           && s.indexOf("E") == -1)
                            s = formatNumber(s, 5);
                        else if(s.indexOf("E-") != -1)
                            s = formatNumber(s, 5);
                        else
                            s = formatNumber(s, 2); //ת���ַ�����Ϊ'0.00' //add by wanghw
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                s = cell.getStringCellValue();
                break;

            default:
                s = "";
                break;
        }

        return s.trim();
    }

    /**
     * �������ֵĸ�ʽ��
     * @param str String ��Ҫ��ʽ�����ַ���
     * @param point        ��ʽ����С��λ��
     * @return String    ��ʽ���Ժ���ַ���
     */
    private String formatNumber(String str, int point)
    {
//        double dbl = 0;
        BigDecimal bgl = new BigDecimal("0");
        String format = new String();
        for(int i = 0; i< point; i++)
        {
            format += "0";
        }
        if(format.length()>0)
        {
            format = "0." + format;
        }
        else
        {
            format = "0";
        }

        //ת��Ϊ��ֵ
        try
        {
//            dbl = Double.parseDouble(str);
            bgl = new BigDecimal(str);
        }
        catch(Exception e)
        {
            //��������쳣,��ô����
            e.printStackTrace();
        }
        //����
        return bigDecimal2String(bgl , format);
    }
    /**
     * ��BigDecimalת��Ϊָ����ʽ���ַ���
     * @param dnum ����
     * @param format ��ʽ�ַ��� like "0.00"
     * @return ��ʽ������ַ���
     */
    private String bigDecimal2String(BigDecimal dnum, String format)
    {
        // ��ʽ��Class
        DecimalFormat nf = new DecimalFormat(format);
        return  nf.format(dnum);
    }


    public static void main(String[] args)
    {
        try
        {
            InputStream is = new FileInputStream("d:\\stone.xls");
            ExcelParser ep = new ExcelParser();
            List dsdzdkmxList = ep.parseExcel(is, DsdzdkmxItem.class);
            Debug.out(dsdzdkmxList);
            is.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}