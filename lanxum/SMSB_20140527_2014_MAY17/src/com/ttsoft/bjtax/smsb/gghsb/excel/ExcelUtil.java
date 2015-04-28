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
 * <p>Title: 将数据生成Excel格式</p>
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
     * 生成扣款信息excel
     * @param out
     */
    public void generateKKXXExcel(OutputStream out, KkxxcxForm kf) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            createMapCell(sheet, "年度:", kf.getNd());
            createMapCell(sheet, "征期:", kf.getZq());
            String qxmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", kf.getQx(), "swjgzzjgmc");
            createMapCell(sheet, "区县:", qxmc);
            String swjsmc = "";
            if ("0".equals(kf.getSwjs())) {
                swjsmc = "所有税务所";
            } else {
                swjsmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", kf.getSwjs(), "swjgzzjgmc");
            }
            createMapCell(sheet, "税务所:", swjsmc);
            createMapCell(sheet, "街乡:", kf.getJxmc());
            String kkbz = "";
            if ("0".equals(kf.getKkbz())) {
                kkbz = "所有扣款信息";
            } else if ("1".equals(kf.getKkbz())) {
                kkbz = "所有扣款结果信息";
            } else if ("2".equals(kf.getKkbz())) {
                kkbz = "扣款成功信息";
            } else if ("3".equals(kf.getKkbz())) {
                kkbz = "扣款不成功信息";
            }
            createMapCell(sheet, "扣款标志:", kkbz);
            if (kf.getKkbcgyy() != null) {
                String kkbcgyy = "";
                if ("0".equals(kf.getKkbcgyy())) {
                    kkbcgyy = "所有原因";
                } else {
                    kkbcgyy = CodeUtils.getCodeBeanLabel("GTGSH_KKSBYY",
                        kf.getKkbcgyy());
                }
                createMapCell(sheet, "扣款失败原因:", kkbcgyy);
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
     * 生成定期定额户入库情况查询excel
     * @param out
     */
    public void generateDQDEHRKCXExcel(OutputStream out, DqdehrkcxForm df) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            createMapCell(sheet, "计算机代码:", df.getJsjdm());

            String nsrztmc = CodeUtils.getCodeBeanLabel("GTGSH_NSRZT",df.getNsrzt());
            createMapCell(sheet, "纳税人状态:", nsrztmc);

            String qxmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", df.getQx(), "swjgzzjgmc");
            createMapCell(sheet, "区县:", qxmc);

            String swjsmc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                "swjgzzjgdm", df.getSwjs(), "swjgzzjgmc");
            createMapCell(sheet, "主管税务所:", swjsmc);

            if (df.getJx().equals("0")) {
                createMapCell(sheet, "所处街乡:", "所有街乡");
            } else {
                String jxmc = CodeUtils.getCodeBeanLabel("GTGSH_JX",df.getJx());
                createMapCell(sheet, "所处街乡:", jxmc);
            }

            if (df.getSz().equals("0")) {
                createMapCell(sheet, "税种:", "全部税种");
            } else {
                String szmc = CodeUtils.getCodeBeanLabel("CODE1",df.getSz());
                createMapCell(sheet, "税种:", szmc);
            }

            //createMapCell(sheet, "认定年度:", df.getNd());

            if (df.getRkfs().equals("0")) {
                createMapCell(sheet, "入库方式:", "所有方式");
            } else if (df.getRkfs().equals("1")) {
                createMapCell(sheet, "入库方式:", "");
            } else {
                String rkfsmc = CodeUtils.getCodeBeanLabel("GTGSH_SKRKFS",df.getRkfs());
                createMapCell(sheet, "入库方式:", rkfsmc);
            }

            if (df.getDkzclx() != null) {
                String djzclxmc = "";
                for (int i = 0; i < df.getDkzclx().length; i++) {
                    djzclxmc +=
                        CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",df.getDkzclx()[i]) +",";
                }
                djzclxmc = djzclxmc.substring(0, djzclxmc.length() - 1);
                createMapCell(sheet, "登记注册类型:", djzclxmc);
            } else {
                createMapCell(sheet, "登记注册类型:", "全部");
            }

            createMapCell(sheet, "征期起始日期:", df.getFromzq());
            createMapCell(sheet, "征期截止日期:", df.getEndzq());
            if(df.getRkqk().equals("0")){
              createMapCell(sheet, "入库情况:", "已入库");
            }else if(df.getRkqk().equals("1")){
              createMapCell(sheet, "入库情况:", "已缴款未入库");
            }else {
              createMapCell(sheet, "入库情况:", "未缴款");
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
     * 成对生成Excel cell
     * @param sheet
     * @param Name 名称
     * @param value  值
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