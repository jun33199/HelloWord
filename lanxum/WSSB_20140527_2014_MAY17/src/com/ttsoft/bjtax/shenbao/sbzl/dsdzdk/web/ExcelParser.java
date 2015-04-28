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
     * 将文件流解析成一个list，本解析器将excel文件的内容
     * @param is 输入流
     * @param outclass 输出类的class
     * @return 输出对象为outclass的list
     * @throws java.lang.Exception
     */
    public List parseExcel(InputStream is, Class outclass) throws Exception
    {

        try
        {
             HSSFWorkbook wb = null;
            try //为了得到更明确的异常所以就在这里加了一个判断是否是excel文件
            {
                POIFSFileSystem fs = new POIFSFileSystem(is); // POI的文件系统句柄，用于打开并读取刚上传的Excel文件
                wb = new HSSFWorkbook(fs); // 得到该Excel文件的工作簿句柄
            }
            catch(Exception ex)
            {
                throw new ApplicationException("您上传是非Excel文件!");
            }

            //检查Excel文件
            Map arrMap = checkExcelFile(wb);

            //解析文件
            List list = getData(wb, arrMap, outclass);

            return list;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * 检查文件并获得文件的定义格式
     * @param wb Excel工作薄
     * @return 所有属性的定义map
     * @throws java.lang.Exception
     */
    private Map checkExcelFile(HSSFWorkbook wb) throws Exception {
        HSSFSheet sheet1 = wb.getSheetAt(1); // 获得第2个Sheet的句柄
        HSSFSheet sheet2 = wb.getSheetAt(2); // 获得第3个Sheet的句柄

        //获得第一行的数据定义
        Iterator rows = sheet1.rowIterator();
        HSSFRow row = null;
        if(rows.hasNext())
        {
            row = sheet1.getRow(0);
        }
        else
        {
            throw new ApplicationException(
                "excel文件不合规则:请在该excel文件中的sheet2页的第一行指定描述信息,"
                                +"如果您不知道上传的文件格式请与数据管理员联系!");
        }
        Map arrMap = new HashMap();

        Iterator cells1 = row.cellIterator(); // 取得该行所有单元格的枚举
        while (cells1.hasNext()) { // 取得每个cell的内容，并按照xls2Ptli的顺序填入pTLI
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
     * 取出数据
     * @param wb Excel工作薄
     * @param arrMap 属性map
     * @param outclass 输出对象类
     * @return  List 代征数据列表
     * @throws java.lang.Exception
     */
    private List getData(HSSFWorkbook wb, Map arrMap, Class outclass) throws
        Exception {

        List datas = new ArrayList();

        HSSFSheet sheet = wb.getSheetAt(0); // 获得第一个Sheet的句柄
        HSSFRow row = null; // 该Sheet的某行的句柄
        Iterator cells = null; // 该行的所有cell的枚举

        short cellNum; // 保存当前cell在row中的位置(0 based)
        String cellContent = null; // String类型的Cell的内容
        double cellNumeric = 0; // Numeric类型的Cell的内容

        // 读取第一个sheet中的所有纪录
        int rows = sheet.getLastRowNum(); // 获得第一个Sheet最后一行的行数

        for (int i = 0; i <= rows; i++) {
            Object item = outclass.newInstance();
            row = sheet.getRow(i);
            cells = row.cellIterator(); // 取得该行所有单元格的遍历器
            while (cells.hasNext()) { // 取得每个cell的内容
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
     * 取出一个CELL的数据
     * @param cell 单元格对象
     * @param type 类型参数
     * @return String，单元格数据
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

        //转换为数值
        try
        {
//            dbl = Double.parseDouble(str);
            bgl = new BigDecimal(str);
        }
        catch(Exception e)
        {
            //如果出现异常,那么忽略
            e.printStackTrace();
        }
        //返回
        return bigDecimal2String(bgl , format);
    }
    /**
     * 将BigDecimal转换为指定格式的字符串
     * @param dnum 数字
     * @param format 格式字符串 like "0.00"
     * @return 格式化后的字符串
     */
    private String bigDecimal2String(BigDecimal dnum, String format)
    {
        // 格式化Class
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