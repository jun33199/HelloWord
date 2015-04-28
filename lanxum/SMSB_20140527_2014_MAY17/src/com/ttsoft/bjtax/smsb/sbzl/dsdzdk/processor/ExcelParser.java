/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 三代申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ExcelParser
{

    /**
     * 分析文件
     * @param is 输入文件流
     * @return 结果列表
     * @throws BaseException
     */

    public List parseExcel (InputStream is)
        throws BaseException
    {

        HSSFWorkbook wb = null;
        try
        { //为了得到更明确的异常所以就在这里加了一个判断是否是excel文件
            POIFSFileSystem fs = new POIFSFileSystem(is); // POI的文件系统句柄，用于打开并读取刚上传的Excel文件
            wb = new HSSFWorkbook(fs); // 得到该Excel文件的工作簿句柄
        }
        catch (Exception ex)
        {
            throw new ApplicationException("您上传是非Excel文件!");
        }

        //检查Excel文件
        Map arrMap = checkExcelFile(wb);

        //解析文件
        List list = getData(wb, arrMap);

        return list;
    }

    /**
     * 检查文件并获得文件的定义格式
     * @param wb Excel工作薄
     * @return 所有属性的定义map
     * @throws BaseException
     */
    private Map checkExcelFile (HSSFWorkbook wb)
        throws BaseException
    {
        HSSFSheet sheet1 = wb.getSheetAt(1); // 获得第2个Sheet的句柄
        HSSFSheet sheet2 = wb.getSheetAt(2); // 获得第3个Sheet的句柄

        //获得第一行的数据定义
        Iterator rows = sheet1.rowIterator();
        HSSFRow row = null;
        if (rows.hasNext())
        {
            row = sheet1.getRow(0);
        }
        else
        {
            throw new ApplicationException(
                "excel文件不合规则:请在该excel文件中的sheet2页的第一行指定描述信息,"
                + "如果您不知道上传的文件格式请与系统管理员联系!");
        }
        Map arrMap = new HashMap();

        Iterator cells1 = row.cellIterator(); // 取得该行所有单元格的枚举
        while (cells1.hasNext())
        { // 取得每个cell的内容，并按照xls2Ptli的顺序填入pTLI
            HSSFCell cell = (HSSFCell) cells1.next();
            String cellContent = cell.getStringCellValue();
            if (cellContent != null && cellContent.trim().length() > 0)
            {
                Short num = new Short(cell.getCellNum());
                arrMap.put(num, cellContent);
            }
        }
        Debug.out("文本上传格式" + arrMap);
        return arrMap;

    }

    /**
     * 取出数据
     * @param wb Excel工作薄
     * @param attribMap 属性map
     * @return  List 代征数据列表
     * @throws BaseException
     */
    private List getData (HSSFWorkbook wb, Map attribMap)
        throws BaseException
    {
        //以:HashMap为节点存放的导入明细记录数据
        List mxList = new ArrayList();
        //以:票证种类+完税证号+税种税目代码为索引存放的索引表
        HashMap indexMap = new HashMap();

        HSSFSheet sheet = wb.getSheetAt(0); // 获得第一个Sheet的句柄
        HSSFRow row = null; // 该Sheet的某行的句柄
        Iterator cells = null; // 该行的所有cell的枚举

        short cellNum; // 保存当前cell在row中的位置(0 based)
        String cellContent = null; // String类型的Cell的内容
        double cellNumeric = 0; // Numeric类型的Cell的内容

        // 读取第一个sheet中的所有纪录
        int rows = sheet.getLastRowNum(); // 获得第一个Sheet最后一行的行数

        for (int i = 0; i <= rows; i++)
        {
            // 存放行记录解析数据
            HashMap mxMap = new HashMap();

            row = sheet.getRow(i);
            cells = row.cellIterator(); // 取得该行所有单元格的遍历器
            while (cells.hasNext())
            { // 取得每个cell的内容
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

            //检查数据的合法性
            checkData(mxMap, i);

            //检查是否有重复记录
            //用来检查重复记录的索引
            String indexKey = (String) mxMap.get("pzzldm") +
                              (String) mxMap.get("wszh") +
                              (String) mxMap.get("szdm") +
                              (String) mxMap.get("szsmdm");
            if (indexMap.containsKey(indexKey))
            {
                String index = (String) indexMap.get(indexKey);
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "第" + (i + 1) + "条记录与" +
                    "第" + index + "条记录属同一类别重复申报."));
            }
            indexMap.put(indexKey, String.valueOf(i));

            //保存明细记录数据
            mxList.add(mxMap);
        }
        //清除索引表
        indexMap.clear();

        return mxList;
    }

    /**
     * 检查上传数据的合法性
     * @param record HashMap明细记录属性
     * @param i 当前记录的行号
     * @throws BaseException
     */
    private void checkData (HashMap record, int i)
        throws BaseException
    {
        String err_msg = "请检查记录,确保数据正确性,然后重新导入!";

        String szdm = (String) record.get("szdm"); // 税种代码
        String szsmdm = (String) record.get("szsmdm"); //税目代码
        String wszh = (String) record.get("wszh"); //完税证号
        String pzzldm = (String) record.get("pzzldm"); //票证种类代码
        String sl = (String) record.get("sl"); //税率
        String jsje = (String) record.get("jsje"); //记税金额
        String sjse = (String) record.get("sjse"); //实缴税额

        //检查税种代码必须为两位
        if (szdm == null || szdm.length() != 2)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "第" + (i + 1) + "条记录中:税种代码必须为两位." + err_msg));
        }

        //检查szsmdm必须为6位
        if (szsmdm == null || szsmdm.length() != 6)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:税目代码必须为6位." + err_msg));
        }

        //检查税种税目代码和税种代码相匹配
        if (!szsmdm.substring(0, 2).equals(szdm))
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:税种代码和税目代码不匹配." + err_msg));
        }

        //检查实缴金额不为空
        if (sjse == null)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:" + "没有实缴金额." + err_msg));
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
                    "在第" + (i + 1) + "条记录中:实缴金额的数据含有非法字符." + err_msg));
            }
            if (thisSjse.scale() > 2)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据的小数部分不合法:最多只允许2位." +
                    err_msg));
            }
            if (thisSjse.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据不能小于0." + err_msg));
            }
            if (thisSjse.doubleValue() > 99999999999999.99)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据的值过大，系统无法存储." + err_msg));
            }

        }

        //检查记税金额不为空
        if (jsje == null)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:" + "没有记税金额." + err_msg));
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
                    "在第" + (i + 1) + "条记录中:记税金额的数据含有非法字符." + err_msg));
            }
            if (thisJsje.scale() > 2)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据的小数部分不合法:最多只允许2位." +
                    err_msg));
            }
            if (thisJsje.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据不能小于0." + err_msg));
            }
            if (thisJsje.doubleValue() > 99999999999999.99)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:实缴金额的数据的值过大，系统无法存储." + err_msg));
            }

        }
        //检查税率如果有的话必须是数值型且必须大于0.00
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
                    "在第" + (i + 1) + "条记录中:税率含有非法字符." + err_msg));

            }
            if (thisSl.scale() > 5)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:税率的小数部分不合法:最多只允许5位." +
                    err_msg));
            }
            if (thisSl.doubleValue() < 0.00)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:税率不能小于0." + err_msg));
            }
            if (thisSl.doubleValue() > 99999.99999)
            {
                throw ExceptionUtil.getBaseException(
                    new ApplicationException(
                    "在第" + (i + 1) + "条记录中:税率的值过大，系统无法存储." + err_msg));
            }
        }

        //检查完税证号必须为8位
        if (wszh == null || wszh.length() != 8)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:必须为8位完税证号." + err_msg));
        }
        //检查票证种类
        if (pzzldm == null || pzzldm.length() > 4)
        {
            throw ExceptionUtil.getBaseException(
                new ApplicationException(
                "在第" + (i + 1) + "条记录中:票证种类代码长度必须小于4位." + err_msg));
        }

    }

    /**
     * 取出一个CELL的数据
     * @param cell 单元格对象
     * @param type 类型参数
     * @return String，单元格数据
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
                        s = formatNumber(s, 2); //转换字符类型为'0.00' //add by wanghw
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
     * 进行数字的格式化
     * @param str String 需要格式化的字符串
     * @param point        格式化的小数位数
     * @return String    格式化以后的字符串
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

        //转换为数值
        try
        {
            dbl = Double.parseDouble(str);
        }
        catch (Exception e)
        {
            //如果出现异常,那么忽略
            e.printStackTrace();
        }
        //返回
        return double2String(dbl, format);
    }

    /**
     * 将double转换为指定格式的字符串
     * @param dnum 数字
     * @param format 格式字符串 like "0.00"
     * @return 格式化后的字符串
     */
    private String double2String (double dnum, String format)
    {
        // 格式化Class
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