/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * <p>Title: 解析Excel文件</p>
 * <p>Description: 解析指定的excel文件</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SmExcelParser
{
  public SmExcelParser()
  {
  }

  /**
   * 将文件流解析成一个list，本解析器将excel文件的内容
   * @param is 输入流  *
   * @return 输出对象为list
   * @throws BaseException
   */
  public HSSFWorkbook parseExcel(InputStream is) throws BaseException
  {

    HSSFWorkbook wb = null;
    try    
    {
    	//为了得到更明确的异常所以就在这里加了一个判断是否是excel文件
    	POIFSFileSystem fs = new POIFSFileSystem(is); // POI的文件系统句柄，用于打开并读取刚上传的Excel文件
    	wb = new HSSFWorkbook(fs); // 得到该Excel文件的工作簿句柄
    }catch (Exception ex) {
    	ex.printStackTrace();
      throw new ApplicationException("您只能上传Excel文件！");
    }
    return wb;
  }

  /**
   * 得到指定excel文件的指定工作表
   * @param  wb excel文件句柄
   * @param  index 制定的索引
   * @return HSSFSheet 工作表对象
   */
  public HSSFSheet getSheet(HSSFWorkbook wb, int index)
  {
    return wb.getSheetAt(index);
  }

  /**
   * 得到指定excel文件的指定列
   * @param  is 文件输入流
   * @param  sheetNum 工作表编号
   * @param  colNum 列表编号
   * @throws BaseException
   * @return List
   */
  public List getColAt(InputStream is, int sheetNum, int colNum) throws
      BaseException
  {
    List ret = new ArrayList();
    HSSFSheet sheet = this.getSheet(this.parseExcel(is), 0);
    //得到行数
    int rowNum = sheet.getLastRowNum();
    //行
    HSSFRow row = null;
    for (int i = 0; i <= rowNum; i++)
    {
      //遍历工作表，得到所有行数据
      row = sheet.getRow(i);
      if (row == null)
      {
        throw new ApplicationException("您上传的excel文件为空！");
      }

      //得到单元隔
      HSSFCell cell = row.getCell( (short) colNum);
      //设置单元格类型

      int cellType = cell.getCellType();
      String cellValue = "";

      if (HSSFCell.CELL_TYPE_NUMERIC == cellType)
      {
        //处理CELL_TYPE_NUMERIC各式的计算机代码
        cellValue = String.valueOf(new Double(cell.getNumericCellValue()).
                                   intValue());
//        ret.add(String.valueOf(cell.getNumericCellValue()));
      }
      else
      {
        cellValue = cell.getStringCellValue();
//        ret.add(cell.getStringCellValue());
      }
      if(cellValue!=null && !cellValue.trim().equals(""))
        ret.add(cellValue);
    }
    return ret;
  }

}