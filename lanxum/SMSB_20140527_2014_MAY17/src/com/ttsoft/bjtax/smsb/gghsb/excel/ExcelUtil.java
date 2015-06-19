package com.ttsoft.bjtax.smsb.gghsb.excel;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.smsb.gghsb.web.DqdehrkcxForm;
import com.ttsoft.bjtax.smsb.gghsb.web.KkxxcxForm;
import com.ttsoft.common.util.Debug;

/**
 * <p>Title: ����������Excel��ʽ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)thunis 2004</p>
 * <p>Company: </p>
 * @author zhou jinguang
 * @version 1.0
 */

public class ExcelUtil {
    public ExcelUtil() {
    }

    int rowNum = 0;

    /**
     * ���ɿۿ���Ϣexcel
     * @param out
     */
    public void generateKKXXExcel(OutputStream out, KkxxcxForm kf) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            createMapCell(sheet, "���:", kf.getNd());
            createMapCell(sheet, "����:", kf.getZq());
            String qxmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", kf.getQx(), "swjgzzjgmc");
            createMapCell(sheet, "����:", qxmc);
            String swjsmc = "";
            if ("0".equals(kf.getSwjs())) {
                swjsmc = "����˰����";
            } else {
                swjsmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", kf.getSwjs(), "swjgzzjgmc");
            }
            createMapCell(sheet, "˰����:", swjsmc);
            createMapCell(sheet, "����:", kf.getJxmc());
            String kkbz = "";
            if ("0".equals(kf.getKkbz())) {
                kkbz = "���пۿ���Ϣ";
            } else if ("1".equals(kf.getKkbz())) {
                kkbz = "���пۿ�����Ϣ";
            } else if ("2".equals(kf.getKkbz())) {
                kkbz = "�ۿ�ɹ���Ϣ";
            } else if ("3".equals(kf.getKkbz())) {
                kkbz = "�ۿ�ɹ���Ϣ";
            }
            createMapCell(sheet, "�ۿ��־:", kkbz);
            if (kf.getKkbcgyy() != null) {
                String kkbcgyy = "";
                if ("0".equals(kf.getKkbcgyy())) {
                    kkbcgyy = "����ԭ��";
                } else {
                    kkbcgyy = CodeUtils.getCodeBeanLabel("GTGSH_KKSBYY",
                        kf.getKkbcgyy());
                }
                createMapCell(sheet, "�ۿ�ʧ��ԭ��:", kkbcgyy);
            }

            HSSFCell cell = null;
            String[] sumTitle = kf.getSumListTitle();
            String[] sumKey = kf.getSumListKey();
            List sumList = kf.getSumList();
            String[] dataTitle = kf.getDataListTitle();
            String[] dataKey = kf.getDataListKey();
            List dataList = kf.getDataList();
            //sum create
            HSSFRow row = sheet.createRow( (short) (++rowNum));
            row = sheet.createRow( (short) (++rowNum));
            if (sumList.size() > 0) {
                HashMap hashMap = (HashMap) sumList.get(0);
                for (int i = 0; i < sumTitle.length; i++) {
                    cell = row.createCell( (short) i);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(sumTitle[i].concat(":" +
                        (String) hashMap.get(sumKey[i])));
                }
            }
            //date create
            row = sheet.createRow( (short) (++rowNum));
            row = sheet.createRow( (short) (++rowNum));
            for (int i = 0; i < dataTitle.length; i++) {
                cell = row.createCell( (short) i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(dataTitle[i]);
            }
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow( (short) (++rowNum));
                HashMap hashMap = (HashMap) dataList.get(i);
                for (int j = 0; j < dataKey.length; j++) {
                    cell = row.createCell( (short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue( (String) hashMap.get(dataKey[j]));
                }
            }
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }

    /**
     * ���ɶ��ڶ����������ѯexcel
     * @param out
     */
    public void generateDQDEHRKCXExcel(OutputStream out, DqdehrkcxForm df) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            createMapCell(sheet, "���������:", df.getJsjdm());

            String nsrztmc = CodeUtils.getCodeBeanLabel("GTGSH_NSRZT",df.getNsrzt());
            createMapCell(sheet, "��˰��״̬:", nsrztmc);

            String qxmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", df.getQx(), "swjgzzjgmc");
            createMapCell(sheet, "����:", qxmc);

            String swjsmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", df.getSwjs(), "swjgzzjgmc");
            createMapCell(sheet, "����˰����:", swjsmc);

            if (df.getJx().equals("0")) {
                createMapCell(sheet, "��������:", "���н���");
            } else {
                String jxmc = CodeUtils.getCodeBeanLabel("GTGSH_JX",df.getJx());
                createMapCell(sheet, "��������:", jxmc);
            }

            if (df.getSz().equals("0")) {
                createMapCell(sheet, "˰��:", "ȫ��˰��");
            } else {
                String szmc = CodeUtils.getCodeBeanLabel("CODE1",df.getSz());
                createMapCell(sheet, "˰��:", szmc);
            }

            //createMapCell(sheet, "�϶����:", df.getNd());

            if (df.getRkfs().equals("0")) {
                createMapCell(sheet, "��ⷽʽ:", "���з�ʽ");
            } else if (df.getRkfs().equals("1")) {
                createMapCell(sheet, "��ⷽʽ:", "");
            } else {
                String rkfsmc = CodeUtils.getCodeBeanLabel("GTGSH_SKRKFS",df.getRkfs());
                createMapCell(sheet, "��ⷽʽ:", rkfsmc);
            }

            if (df.getDkzclx() != null) {
                String djzclxmc = "";
                for (int i = 0; i < df.getDkzclx().length; i++) {
                    djzclxmc +=
                        CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",df.getDkzclx()[i]) +",";
                }
                djzclxmc = djzclxmc.substring(0, djzclxmc.length() - 1);
                createMapCell(sheet, "�Ǽ�ע������:", djzclxmc);
            } else {
                createMapCell(sheet, "�Ǽ�ע������:", "ȫ��");
            }

            createMapCell(sheet, "������ʼ����:", df.getFromzq());
            createMapCell(sheet, "���ڽ�ֹ����:", df.getEndzq());
            if(df.getRkqk().equals("0")){
              createMapCell(sheet, "������:", "�����");
            }else if(df.getRkqk().equals("1")){
              createMapCell(sheet, "������:", "�ѽɿ�δ���");
            }else {
              createMapCell(sheet, "������:", "δ�ɿ�");
            }

            HSSFCell cell = null;
            String[] sumTitle = df.getSumListTitle();
            String[] sumKey = df.getSumListKey();
            List sumList = df.getSumList();
            String[] dataTitle = df.getDataListTitle();
            String[] dataKey = df.getDataListKey();
            List dataList = df.getDataList();
            //sum create
            HSSFRow row = sheet.createRow( (short) (++rowNum));
            row = sheet.createRow( (short) (++rowNum));
            if (sumList.size() > 0) {
                HashMap hashMap = (HashMap) sumList.get(0);
                for (int i = 0; i < sumTitle.length; i++) {
                    cell = row.createCell( (short) i);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(sumTitle[i].concat(":" +
                        (String) hashMap.get(sumKey[i])));
                }
            }
            //date create
            row = sheet.createRow( (short) (++rowNum));
            row = sheet.createRow( (short) (++rowNum));
            for (int i = 0; i < dataTitle.length; i++) {
                cell = row.createCell( (short) i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(dataTitle[i]);
            }
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow( (short) (++rowNum));
                HashMap hashMap = (HashMap) dataList.get(i);
                for (int j = 0; j < dataKey.length; j++) {
                    cell = row.createCell( (short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue( (String) hashMap.get(dataKey[j]));
                }
            }
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }

    /**
     * �ɶ�����Excel cell
     * @param sheet
     * @param Name ����
     * @param value  ֵ
     */
    private void createMapCell(HSSFSheet sheet, String Name, String value) {
        HSSFRow row = sheet.createRow( (short) (++rowNum));
        HSSFCell cell;
        cell = row.createCell( (short) 0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(Name);
        cell = row.createCell( (short) 1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(value);
    }

}