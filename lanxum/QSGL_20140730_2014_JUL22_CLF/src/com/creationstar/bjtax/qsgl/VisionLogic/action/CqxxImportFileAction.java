package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxImportFileForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.LogUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 *
 * <p>
 * Title: 导入拆迁文件信息的action类
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author llw
 * @version 1.0
 */
public class CqxxImportFileAction extends BaseAction {


    /**
     * doUploadExcel
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doUploadExcel(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        CqxxImportFileForm aForm = (CqxxImportFileForm) form;

        aForm.reset(mapping, request);

        HashMap szqxMap = new HashMap(); // '所在区县'
        szqxMap.put("01", "东城");
        szqxMap.put("02", "西城");
        szqxMap.put("03", "崇文");
        szqxMap.put("04", "宣武");
        szqxMap.put("05", "朝阳");
        szqxMap.put("06", "海淀");
        szqxMap.put("07", "丰台");
        szqxMap.put("08", "石景山");
        szqxMap.put("09", "门头沟");
        szqxMap.put("10", "燕山");
        szqxMap.put("11", "昌平");
        szqxMap.put("12", "通州");
        szqxMap.put("13", "顺义");
        szqxMap.put("14", "大兴");
        szqxMap.put("15", "房山");
        szqxMap.put("16", "怀柔");
        szqxMap.put("17", "密云");
        szqxMap.put("18", "平谷");
        szqxMap.put("19", "延庆");
        szqxMap.put("20", "开发区");
        szqxMap.put("21", "西站");
        szqxMap.put("22", "涉外");
        szqxMap.put("90", "市局");

        // 构造后台需要的条件数据对象
        int liDrMaxBs = 2000; // 导入最大笔数
        // 上传的文件列表
        Hashtable files = new Hashtable();

        // 上传文件名
        String fname = "";

        FormFile ff = null;

        ArrayList cqxxList = new ArrayList();

        HashMap loData = new HashMap();

        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();

        vo.setUserData(ud);

        vo.setAction(ActionType.UPLOAD_FILE);
        vo.setProcessor(Constants.CQXXB_PROCESSOR);

        try {

            // 读取上传文件
            MultipartRequestHandler mrh = aForm.getMultipartRequestHandler();

            if (mrh == null) {

                LogUtil.log("MultipartRequestHandler is null  " + "\n");

            } else if (mrh != null) {

                try {

                    files = mrh.getFileElements();

                    if (files == null || files.size() == 0) {

                        // LogUtil.log("没有上传文件！" + "\n");

                        throw new ApplicationException("没有上传文件！");

                    } else {

                        java.util.Enumeration enum_filekey = files.keys();

                        if (enum_filekey.hasMoreElements()) {

                            fname = (String) enum_filekey.nextElement();
                            ff = (FormFile) files.get(fname);

                        }

                        // LogUtil.log("test########## "+ff.getFileName());
                        //						
                        // LogUtil.log("test##########
                        // "+ff.getFileName().substring(ff.getFileName().indexOf(".")));

                        if (ff == null || ff.getFileSize() == 0
                            || ff.getFileName() == null
                            || ff.getFileName().equals("")) {

                            // LogUtil.log("上传文件没有文件名称或者文件没有内容！" + "\n");

                            throw new ApplicationException(
                                    "上传文件没有文件名称或者文件没有内容！");

                        } else if (!(ff.getFileName().substring(
                                ff.getFileName().indexOf(".")).equals(".xls"))) {

                            // LogUtil.log("上传文件格式非EXCEL格式！" + "\n");

                            throw new ApplicationException("上传文件格式非EXCEL格式！");

                        } else {

                            cqxxList = read(ff, ud, szqxMap);

                        }

                    }
                } catch (ApplicationException ioe) {

                    ioe.printStackTrace();

                    request.setAttribute(Constants.MESSAGE_KEY, ioe
                                         .getMessage());

                    request.setAttribute(mapping.getAttribute(), aForm);

                    return mapping.findForward("show");

                    // System.out.println("IOException:读写文件时发生错误");
                } catch (Exception ioe) {

                    ioe.printStackTrace();

                    request.setAttribute(Constants.MESSAGE_KEY, "读取文件错误!");

                    request.setAttribute(mapping.getAttribute(), aForm);

                    return mapping.findForward("show");

                    // System.out.println("IOException:读写文件时发生错误");
                }
            }

            // 判断是否超过2000条的最大限制
            if (cqxxList.size() > liDrMaxBs + 1) {

                request.setAttribute(Constants.MESSAGE_KEY, "导入的笔数不能大于2000条！");

                throw new Exception("导入的笔数不能大于2000条！");

            }

            // 设置导入数据list
            loData.put("new", cqxxList);

            vo.setData(loData);
            Map lmReturnedData = (HashMap) QsglProxy.getInstance().process(vo);

            ArrayList llErrrecords = (ArrayList) lmReturnedData.get("Error");
            ArrayList llSucceedRecords = (ArrayList) lmReturnedData
                                         .get("Succeed");

            aForm.setDrsj(llSucceedRecords);
            aForm.setSize(llSucceedRecords.size());

            if (llErrrecords.size() > 0) {

                aForm.setImportErrRecords(llErrrecords);

                aForm.setErrSize(llErrrecords.size());

                aForm.setImportIsSucceed(false);

                if (aForm.getSize() > 0) {
                    request.setAttribute(Constants.MESSAGE_KEY,
                                         "数据导入成功，但文件中存在部分错误数据");
                } else {
                    request.setAttribute(Constants.MESSAGE_KEY, "文件中存在错误数据");
                }

            } else {

                request.setAttribute(Constants.MESSAGE_KEY, "数据导入成功");

                request.setAttribute(mapping.getAttribute(), aForm);

                return mapping.findForward("continue");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());

            request.setAttribute(mapping.getAttribute(), aForm);

            return mapping.findForward("show");

        }

        // 保存Token;
        this.saveToken(request);

        request.setAttribute(mapping.getAttribute(), aForm);

        return mapping.findForward("show");

    }

    /*
     * （非 Javadoc）
     *
     * @see com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction#doShow(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        CqxxImportFileForm aForm = (CqxxImportFileForm) form;

        aForm.reset(mapping, request);

        aForm.setImportIsSucceed(false);

        request.setAttribute(mapping.getAttribute(), aForm);

        return mapping.findForward("show");

    }

    /**
     * 读取上传的文件
     *
     * @param ff
     */
    public ArrayList read(FormFile ff, UserData ud, HashMap szqxMap) throws
            BaseException, ApplicationException {

        // String xmmc = "";// 项目名称
        // String jsdw = "";// 建设单位
        // String cqxkzh = "";// 拆迁许可证号
        // String cqxkzspsj = "";// 拆迁许可证号审批时间
        // String cqfw = "";// 拆迁范围
        String[] cqxxbt = new String[6];

        ArrayList cqxxListRead = new ArrayList();

        try {
            // 读入文件内容
            InputStream stream = ff.getInputStream();

            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(stream));
            HSSFSheet sheet = wb.getSheetAt(0);

            // 读取表头内容
            for (short i = 1; i < 6; i++) {

                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = null;

                if (row == null) {

                    throw new ApplicationException("上传文件中第" + i + "行内容不允许为空！");

                } else {

                    if (!(row.getCell((short) 1) == null || "".equals(row
                            .getCell((short) 1)))) {

                        cell = row.getCell((short) 1);
                        String lStr = "";
                        lStr = ExcelUtil.getCellValue(cell);
                        // String lStr = getCellValue(cell);

                        cqxxbt[i] = lStr;

//						if (i == 4) {
//
//							// LogUtil.log("校验上传文件中拆迁许可证审批时间格式 \n");
//
//							if (!(RegexUtil
//									.isRegex(
//											"[1-2]\\d{3}-(0[1-9])?(1[0-2])?-(0[1-9])?([1-2][0-9])?(3[0-1])?",
//											cqxxbt[4]))) {
//
//								// LogUtil.log("校验上传文件中拆迁许可证审批时间格式 is false
//								// \n");
//
//								throw new ApplicationException(
//										"上传文件中拆迁许可证审批时间格式不正确！");
//
//							}
//						}

                    }
//					else if (i == 3) {
//
//						// LogUtil.log("上传文件中拆迁许可证号为空！\n");
//
//						throw new ApplicationException("上传文件中拆迁许可证号为空！");
//
//					}
//					else if (i == 4) {
//
//						// LogUtil.log("上传文件中拆迁许可证审批时间为空！\n");
//
//						throw new ApplicationException("上传文件中拆迁许可证审批时间为空！");
//
//					}
                    else if (i != 0 && i != 4 && i != 3) {

                        // LogUtil.log("cell is null" + "\n");

                        int r = i + 1;

                        throw new ApplicationException("上传文件中第" + r
                                + "行第2列字段内容为空！");
                    }

                }
            }

            // 读取详细内容
            for (short i = 8; i <= sheet.getLastRowNum(); i++) {

                CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();

                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = null;

                if (row == null) {

                    LogUtil.log("上传文件中第" + i + "行内容为空！");

                }

                else {

//					LogUtil.log("row.getRowNum() "
//							+ String.valueOf(row.getRowNum()));
//
//					LogUtil.log("row.getFirstCellNum() "
//							+ String.valueOf(row.getFirstCellNum()));
//
//					LogUtil.log("row.getLastCellNum() "
//							+ String.valueOf(row.getLastCellNum()));

                    for (short j = 0; j < 8; j++) {

                        // if (!(row.getCell(j) == null)) {

                        cell = row.getCell(j);
                        String lStr = " ";
                        lStr = ExcelUtil.getCellValue(cell);
                        // lStr = getCellValue(cell);

                        switch (j) {
                        case 0:

                            // LogUtil.log(" 序号" + lStr + "\n");
                            break;
                        case 1:

                            // LogUtil.log(" 姓名 被拆迁人姓名" + lStr + "\n");
                            cqxxImportErrbvo.setBcqrmc(lStr); // 姓名\被拆迁人姓名
                            break;
                        case 2:

                            // LogUtil.log(" 身份证号 证件号码" + lStr + "\n");
                            cqxxImportErrbvo.setZjhm(lStr); // 身份证号\证件号码
                            break;
                        case 3:

                            // LogUtil.log(" 共居人名称" + lStr + "\n");
                            cqxxImportErrbvo.setGjrmc(lStr); // 共居人名称
                            break;
                        case 4:

                            // LogUtil.log(" 正式房屋间数" + lStr + "\n");
                            cqxxImportErrbvo.setZsfwjs(lStr); // 正式房屋间数
                            break;
                        case 5:

                            // LogUtil.log(" 正式房屋建筑面积 拆迁面积" + lStr + "\n");
                            cqxxImportErrbvo.setCqmj(lStr); // 正式房屋建筑面积\拆迁面积
                            break;
                        case 6:

                            // LogUtil.log(" 房屋自建面积 " + lStr + "\n");
                            break;
                        case 7:

                            // LogUtil.log(" 房屋详细地址(50) 拆迁详细地址" + lStr +
                            // "\n\n");
                            cqxxImportErrbvo.setCqxxdz(lStr); // 房屋详细地址(50)\拆迁详细地址
                            break;
                        }

                        // } else {
                        // LogUtil.log("cell is null" + "\n");
                        // int r = i + 1;
                        // int c = j + 1;
                        // throw new ApplicationException("上传文件中第" + r + "行第" +
                        // c
                        // + "列字段内容为空！");
                        // }

                        // 项目名称\拆迁项目名称
                        cqxxImportErrbvo.setCqxmmc(cqxxbt[1]);

                        // 建设单位\拆迁人名称
                        cqxxImportErrbvo.setCqrmc(cqxxbt[2]);

                        // 拆迁许可证号
                        cqxxImportErrbvo.setCqxkzh(cqxxbt[3]);

                        // 拆迁许可证号审批时间
                        cqxxImportErrbvo.setCqxkzspsj(cqxxbt[4]);

                        // 拆迁范围
                        cqxxImportErrbvo.setCqfw(cqxxbt[5]);

                        cqxxImportErrbvo.setBcqrlxdm("0"); // 被拆迁人类型代码

                        cqxxImportErrbvo.setBcqrlxmc("个人"); // 被拆迁人类型名称

                        cqxxImportErrbvo.setZjlxdm("02"); // 证件类型代码

                        cqxxImportErrbvo.setZjlxmc("身份证"); // 证件类型名称

                        cqxxImportErrbvo.setBcje("0"); // 补偿金额

                        cqxxImportErrbvo.setBclxdm("3"); // 补偿类型代码

                        cqxxImportErrbvo.setBclxmc("混合"); // 补偿类型名称

                        cqxxImportErrbvo.setBcmj("0"); // 补偿面积

                        cqxxImportErrbvo.setBcfwdz(""); // 补偿房屋地址

                        cqxxImportErrbvo
                                .setQxdm(ud.getSsdwdm().substring(0, 2)); // 区县代码

                        cqxxImportErrbvo.setLrr(ud.getYhid()); // 录入人

                        cqxxImportErrbvo.setCjr(ud.getYhid()); // 创建人

                        cqxxImportErrbvo.setLrrq(DateTimeUtil
                                                 .stringToTimestamp(
                                DateTimeUtil
                                .getCurrentDate(), "yyyyMMdd")); // 录入日期

                        cqxxImportErrbvo.setCjrq(DateTimeUtil
                                                 .stringToTimestamp(
                                DateTimeUtil
                                .getCurrentDate(), "yyyyMMdd")); // 创建日期

                        cqxxImportErrbvo.setSjly(Constants.CQXXB_SJLY_DR); // 数据来源导入

                        // 所在区县
                        if (szqxMap.get(ud.getSsdwdm().substring(0, 2)) == null) {
                            cqxxImportErrbvo.setSzqx("");
                        } else {
                            cqxxImportErrbvo.setSzqx(szqxMap.get(
                                    ud.getSsdwdm().substring(0, 2)).toString());
                        }

                        cqxxImportErrbvo.setSwjgzzjgdm(ud.getSsdwdm()); // 税务机关组织机构代码

                    }

                    cqxxListRead.add(cqxxImportErrbvo);
                }

            }

            try {

                stream.close();
                LogUtil.log("Pkjb002Action uploadfile close input" + "\n");

            } catch (Exception ex) {

                ex.printStackTrace();

                LogUtil.log("文件关闭失败" + "\n");

                // throw ExceptionUtil.getBaseException(ex);
            } finally {

                if (stream != null) {
                    stream.close();
                }
            }

        } catch (FileNotFoundException fnfe) {

            // LogUtil.log("FileNotFoundException:找不到指定文件" + "\n");

            throw new ApplicationException("FileNotFoundException:找不到指定文件"
                                           + "\n");

        } catch (IOException ioe) {

            // LogUtil.log("IOException:读写文件时发生错误" + "\n");

            throw new ApplicationException("IOException:读写文件时发生错误" + "\n");

        } catch (ApplicationException ex) {

            throw new ApplicationException(ex.getMessage());

        } catch (Exception ex) {

            throw ExceptionUtil.getBaseException(ex);
        }
        return cqxxListRead;
    }

}
