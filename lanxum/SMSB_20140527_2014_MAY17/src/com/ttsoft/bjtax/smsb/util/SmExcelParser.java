/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ����Excel�ļ�</p>
 * <p>Description: ����ָ����excel�ļ�</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SmExcelParser
{
  public SmExcelParser()
  {
  }

  /**
   * ���ļ���������һ��list������������excel�ļ�������
   * @param is ������  *
   * @return �������Ϊlist
   * @throws BaseException
   */
  public HSSFWorkbook parseExcel(InputStream is) throws BaseException
  {

    HSSFWorkbook wb = null;
    try    
    {
    	//Ϊ�˵õ�����ȷ���쳣���Ծ����������һ���ж��Ƿ���excel�ļ�
    	POIFSFileSystem fs = new POIFSFileSystem(is); // POI���ļ�ϵͳ��������ڴ򿪲���ȡ���ϴ���Excel�ļ�
    	wb = new HSSFWorkbook(fs); // �õ���Excel�ļ��Ĺ��������
    }catch (Exception ex) {
    	ex.printStackTrace();
      throw new ApplicationException("��ֻ���ϴ�Excel�ļ���");
    }
    return wb;
  }

  /**
   * �õ�ָ��excel�ļ���ָ��������
   * @param  wb excel�ļ����
   * @param  index �ƶ�������
   * @return HSSFSheet ���������
   */
  public HSSFSheet getSheet(HSSFWorkbook wb, int index)
  {
    return wb.getSheetAt(index);
  }

  /**
   * �õ�ָ��excel�ļ���ָ����
   * @param  is �ļ�������
   * @param  sheetNum ��������
   * @param  colNum �б���
   * @throws BaseException
   * @return List
   */
  public List getColAt(InputStream is, int sheetNum, int colNum) throws
      BaseException
  {
    List ret = new ArrayList();
    HSSFSheet sheet = this.getSheet(this.parseExcel(is), 0);
    //�õ�����
    int rowNum = sheet.getLastRowNum();
    //��
    HSSFRow row = null;
    for (int i = 0; i <= rowNum; i++)
    {
      //�����������õ�����������
      row = sheet.getRow(i);
      if (row == null)
      {
        throw new ApplicationException("���ϴ���excel�ļ�Ϊ�գ�");
      }

      //�õ���Ԫ��
      HSSFCell cell = row.getCell( (short) colNum);
      //���õ�Ԫ������

      int cellType = cell.getCellType();
      String cellValue = "";

      if (HSSFCell.CELL_TYPE_NUMERIC == cellType)
      {
        //����CELL_TYPE_NUMERIC��ʽ�ļ��������
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