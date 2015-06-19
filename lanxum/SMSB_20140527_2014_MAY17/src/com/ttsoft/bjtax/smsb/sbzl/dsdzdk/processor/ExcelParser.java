/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;//import com.ttsoft.framework.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
//import com.ttsoft.framework.util.*;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ExcelParser
{

    /**
     * �����ļ�
     * @param is �����ļ���
     * @return ����б�
     * @throws BaseException
     */

    public List parseExcel (InputStream is)
        throws BaseException
    {

        HSSFWorkbook wb = null;
        try
        { //Ϊ�˵õ�����ȷ���쳣���Ծ����������һ���ж��Ƿ���excel�ļ�
            POIFSFileSystem fs = new POIFSFileSystem(is); // POI���ļ�ϵͳ��������ڴ򿪲���ȡ���ϴ���Excel�ļ�
            wb = new HSSFWorkbook(fs); // �õ���Excel�ļ��Ĺ��������
        }
        catch (Exception ex)
        {
            throw new ApplicationException("���ϴ��Ƿ�Excel�ļ�!");
        }

        //���Excel�ļ�
        Map arrMap = checkExcelFile(wb);

        //�����ļ�
        List list = getData(wb, arrMap);

        return list;
    }

    /**
     * ����ļ�������ļ��Ķ����ʽ
     * @param wb Excel������
     * @return �������ԵĶ���map
     * @throws BaseException
     */
    private Map checkExcelFile (HSSFWorkbook wb)
        throws BaseException
    {
        HSSFSheet sheet1 = wb.getSheetAt(1); // ��õ�2��Sheet�ľ��
        HSSFSheet sheet2 = wb.getSheetAt(2); // ��õ�3��Sheet�ľ��

        //��õ�һ�е����ݶ���
        Iterator rows = sheet1.rowIterator();
        HSSFRow row = null;
        if (rows.hasNext())
        {
            row = sheet1.getRow(0);
        }
        else
        {
            throw new ApplicationException(
                "excel�ļ����Ϲ���:���ڸ�excel�ļ��е�sheet2ҳ�ĵ�һ��ָ��������Ϣ,"
                + "�������֪���ϴ����ļ���ʽ����ϵͳ����Ա��ϵ!");
        }
        Map arrMap = new HashMap();

        Iterator cells1 = row.cellIterator(); // ȡ�ø������е�Ԫ���ö��
        while (cells1.hasNext())
        { // ȡ��ÿ��cell�����ݣ�������xls2Ptli��˳������pTLI
            HSSFCell cell = (HSSFCell) cells1.next();
            String cellContent = cell.getStringCellValue();
            if (cellContent != null && cellContent.trim().length() > 0)
            {
                Short num = new Short(cell.getCellNum());
                arrMap.put(num, cellContent);
            }
        }
        Debug.out("�ı��ϴ���ʽ" + arrMap);
        return arrMap;

    }

    /**
     * ȡ������
     * @param wb Excel������
     * @param attribMap ����map
     * @return  List ���������б�
     * @throws BaseException
     */
    private List getData (HSSFWorkbook wb, Map attribMap)
        throws BaseException
    {
        //��:HashMapΪ�ڵ��ŵĵ�����ϸ��¼����
        List mxList = new ArrayList();
        //��:Ʊ֤����+��˰֤��+˰��˰Ŀ����Ϊ������ŵ�������
        HashMap indexMap = new HashMap();

        HSSFSheet sheet = wb.getSheetAt(0); // ��õ�һ��Sheet�ľ��
        HSSFRow row = null; // ��Sheet��ĳ�еľ��
        Iterator cells = null; // ���е�����cell��ö��

        short cellNum; // ���浱ǰcell��row�е�λ��(0 based)
        String cellContent = null; // String���͵�Cell������
        double cellNumeric = 0; // Numeric���͵�Cell������

        // ��ȡ��һ��sheet�е����м�¼
        int rows = sheet.getLastRowNum(); // ��õ�һ��Sheet���һ�е�����

        for (int i = 0; i <= rows; i++)
        {
            // ����м�¼��������
            HashMap mxMap = new HashMap();

            row = sheet.getRow(i);
            cells = row.cellIterator(); // ȡ�ø������е�Ԫ��ı�����
            while (cells.hasNext())
            { // ȡ��ÿ��cell������
                HSSFCell cell = (HSSFCell) cells.next();

                Object num = new Short(cell.getCellNum());
                String attrib = (String) attribMap.get(num);
                if (attrib != null && attrib.trim().length() > 0)
                {
                    int index = attrib.indexOf(":");
                    String attribName = attrib;
                    String attribType = "String";

                    if (index > 1)
                    {
                        attribName = attrib.substring(0, index);
                        attribType = attrib.substring(index + 1);
                    }

                    if ( (attribName != null && attribName.trim().length() > 0) &&
                        (attribType != null && attribType.trim().length() > 0))
                    {

                        String attribValue = getCellValue(cell, attribType);
                        mxMap.put(attribName, attribValue);
                    }
                }

            }
            //Debug.out("RECORD=" + mxMap.toString());

            //������ݵĺϷ���
            checkData(mxMap, i);

            //����Ƿ����ظ���¼
            //��������ظ���¼������
            String indexKey = (String) mxMap.get("pzzldm") +
                              (String) mxMap.get("wszh") +
                              (String) mxMap.get("szdm") +
                              (String) mxMap.get("szsmdm");
            if (indexMap.containsKey(indexKey))
            {
                String index = (String) indexMap.get(indexKey);
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "��" + (i + 1) + "����¼��" +
                    "��" + index + "����¼��ͬһ����ظ��걨."));
            }
            indexMap.put(indexKey, String.valueOf(i));

            //������ϸ��¼����
            mxList.add(mxMap);
        }
        //���������
        indexMap.clear();

        return mxList;
    }

    /**
     * ����ϴ����ݵĺϷ���
     * @param record HashMap��ϸ��¼����
     * @param i ��ǰ��¼���к�
     * @throws BaseException
     */
    private void checkData (HashMap record, int i)
        throws BaseException
    {
        String err_msg = "�����¼,ȷ��������ȷ��,Ȼ�����µ���!";

        String szdm = (String) record.get("szdm"); // ˰�ִ���
        String szsmdm = (String) record.get("szsmdm"); //˰Ŀ����
        String wszh = (String) record.get("wszh"); //��˰֤��
        String pzzldm = (String) record.get("pzzldm"); //Ʊ֤�������
        String sl = (String) record.get("sl"); //˰��
        String jsje = (String) record.get("jsje"); //��˰���
        String sjse = (String) record.get("sjse"); //ʵ��˰��

        //���˰�ִ������Ϊ��λ
        if (szdm == null || szdm.length() != 2)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "��" + (i + 1) + "����¼��:˰�ִ������Ϊ��λ." + err_msg));
        }

        //���szsmdm����Ϊ6λ
        if (szsmdm == null || szsmdm.length() != 6)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:˰Ŀ�������Ϊ6λ." + err_msg));
        }

        //���˰��˰Ŀ�����˰�ִ�����ƥ��
        if (!szsmdm.substring(0, 2).equals(szdm))
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:˰�ִ����˰Ŀ���벻ƥ��." + err_msg));
        }

        //���ʵ�ɽ�Ϊ��
        if (sjse == null)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:" + "û��ʵ�ɽ��." + err_msg));
        }
        else
        {
            BigDecimal thisSjse;
            try
            {
                thisSjse = new BigDecimal(sjse);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݺ��зǷ��ַ�." + err_msg));
            }
            if (thisSjse.scale() > 2)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݵ�С�����ֲ��Ϸ�:���ֻ����2λ." +
                    err_msg));
            }
            if (thisSjse.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݲ���С��0." + err_msg));
            }
            if (thisSjse.doubleValue() > 99999999999999.99)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݵ�ֵ����ϵͳ�޷��洢." + err_msg));
            }

        }

        //����˰��Ϊ��
        if (jsje == null)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:" + "û�м�˰���." + err_msg));
        }
        else
        {
            BigDecimal thisJsje;
            try
            {
                thisJsje = new BigDecimal(jsje);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:��˰�������ݺ��зǷ��ַ�." + err_msg));
            }
            if (thisJsje.scale() > 2)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݵ�С�����ֲ��Ϸ�:���ֻ����2λ." +
                    err_msg));
            }
            if (thisJsje.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݲ���С��0." + err_msg));
            }
            if (thisJsje.doubleValue() > 99999999999999.99)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:ʵ�ɽ������ݵ�ֵ����ϵͳ�޷��洢." + err_msg));
            }

        }
        //���˰������еĻ���������ֵ���ұ������0.00
        if (sl != null)
        {
            BigDecimal thisSl;
            try
            {
                thisSl = new BigDecimal(sl);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:˰�ʺ��зǷ��ַ�." + err_msg));

            }
            if (thisSl.scale() > 5)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:˰�ʵ�С�����ֲ��Ϸ�:���ֻ����5λ." +
                    err_msg));
            }
            if (thisSl.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:˰�ʲ���С��0." + err_msg));
            }
            if (thisSl.doubleValue() > 99999.99999)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "�ڵ�" + (i + 1) + "����¼��:˰�ʵ�ֵ����ϵͳ�޷��洢." + err_msg));
            }
        }

        //�����˰֤�ű���Ϊ8λ
        if (wszh == null || wszh.length() != 8)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:����Ϊ8λ��˰֤��." + err_msg));
        }
        //���Ʊ֤����
        if (pzzldm == null || pzzldm.length() > 4)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "�ڵ�" + (i + 1) + "����¼��:Ʊ֤������볤�ȱ���С��4λ." + err_msg));
        }

    }

    /**
     * ȡ��һ��CELL������
     * @param cell ��Ԫ�����
     * @param type ���Ͳ���
     * @return String����Ԫ������
     */
    private String getCellValue (HSSFCell cell, String type)
    {
        String s = null;

        int cellType = cell.getCellType();

        switch (cellType)
        {
            case HSSFCell.CELL_TYPE_BLANK:
                s = "";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                s = String.valueOf(cell.getBooleanCellValue());
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                if (type.equalsIgnoreCase("Datetime"))
                {
                    s = new java.sql.Timestamp(cell.getDateCellValue().getTime()).
                        toString();
                }
                else
                {
                    if (type.equals("Text"))
                    {
                        long t = (long) cell.getNumericCellValue();
                        s = t + "";
                    }
                    else
                    {
                        s = cell.getNumericCellValue() + "";
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
    private String formatNumber (String str, int point)
    {
        double dbl = 0;
        String format = new String();
        for (int i = 0; i < point; i++)
        {
            format += "0";
        }
        if (format.length() > 0)
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
            dbl = Double.parseDouble(str);
        }
        catch (Exception e)
        {
            //��������쳣,��ô����
            e.printStackTrace();
        }
        //����
        return double2String(dbl, format);
    }

    /**
     * ��doubleת��Ϊָ����ʽ���ַ���
     * @param dnum ����
     * @param format ��ʽ�ַ��� like "0.00"
     * @return ��ʽ������ַ���
     */
    private String double2String (double dnum, String format)
    {
        // ��ʽ��Class
        DecimalFormat nf = new DecimalFormat(format);
        return nf.format(dnum);
    }

    public static void main (String[] args)
    {
        try
        {
            InputStream is = new FileInputStream(
                "E:\\dev_home\\doc\\sdwszsb.xls");
            ExcelParser ep = new ExcelParser();
            List mxList = ep.parseExcel(is);
            is.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}