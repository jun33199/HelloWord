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
 * Title: �����Ǩ�ļ���Ϣ��action��
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

        HashMap szqxMap = new HashMap(); // '��������'
        szqxMap.put("01", "����");
        szqxMap.put("02", "����");
        szqxMap.put("03", "����");
        szqxMap.put("04", "����");
        szqxMap.put("05", "����");
        szqxMap.put("06", "����");
        szqxMap.put("07", "��̨");
        szqxMap.put("08", "ʯ��ɽ");
        szqxMap.put("09", "��ͷ��");
        szqxMap.put("10", "��ɽ");
        szqxMap.put("11", "��ƽ");
        szqxMap.put("12", "ͨ��");
        szqxMap.put("13", "˳��");
        szqxMap.put("14", "����");
        szqxMap.put("15", "��ɽ");
        szqxMap.put("16", "����");
        szqxMap.put("17", "����");
        szqxMap.put("18", "ƽ��");
        szqxMap.put("19", "����");
        szqxMap.put("20", "������");
        szqxMap.put("21", "��վ");
        szqxMap.put("22", "����");
        szqxMap.put("90", "�о�");

        // �����̨��Ҫ���������ݶ���
        int liDrMaxBs = 2000; // ����������
        // �ϴ����ļ��б�
        Hashtable files = new Hashtable();

        // �ϴ��ļ���
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

            // ��ȡ�ϴ��ļ�
            MultipartRequestHandler mrh = aForm.getMultipartRequestHandler();

            if (mrh == null) {

                LogUtil.log("MultipartRequestHandler is null  " + "\n");

            } else if (mrh != null) {

                try {

                    files = mrh.getFileElements();

                    if (files == null || files.size() == 0) {

                        // LogUtil.log("û���ϴ��ļ���" + "\n");

                        throw new ApplicationException("û���ϴ��ļ���");

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

                            // LogUtil.log("�ϴ��ļ�û���ļ����ƻ����ļ�û�����ݣ�" + "\n");

                            throw new ApplicationException(
                                    "�ϴ��ļ�û���ļ����ƻ����ļ�û�����ݣ�");

                        } else if (!(ff.getFileName().substring(
                                ff.getFileName().indexOf(".")).equals(".xls"))) {

                            // LogUtil.log("�ϴ��ļ���ʽ��EXCEL��ʽ��" + "\n");

                            throw new ApplicationException("�ϴ��ļ���ʽ��EXCEL��ʽ��");

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

                    // System.out.println("IOException:��д�ļ�ʱ��������");
                } catch (Exception ioe) {

                    ioe.printStackTrace();

                    request.setAttribute(Constants.MESSAGE_KEY, "��ȡ�ļ�����!");

                    request.setAttribute(mapping.getAttribute(), aForm);

                    return mapping.findForward("show");

                    // System.out.println("IOException:��д�ļ�ʱ��������");
                }
            }

            // �ж��Ƿ񳬹�2000�����������
            if (cqxxList.size() > liDrMaxBs + 1) {

                request.setAttribute(Constants.MESSAGE_KEY, "����ı������ܴ���2000����");

                throw new Exception("����ı������ܴ���2000����");

            }

            // ���õ�������list
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
                                         "���ݵ���ɹ������ļ��д��ڲ��ִ�������");
                } else {
                    request.setAttribute(Constants.MESSAGE_KEY, "�ļ��д��ڴ�������");
                }

            } else {

                request.setAttribute(Constants.MESSAGE_KEY, "���ݵ���ɹ�");

                request.setAttribute(mapping.getAttribute(), aForm);

                return mapping.findForward("continue");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());

            request.setAttribute(mapping.getAttribute(), aForm);

            return mapping.findForward("show");

        }

        // ����Token;
        this.saveToken(request);

        request.setAttribute(mapping.getAttribute(), aForm);

        return mapping.findForward("show");

    }

    /*
     * ���� Javadoc��
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
     * ��ȡ�ϴ����ļ�
     *
     * @param ff
     */
    public ArrayList read(FormFile ff, UserData ud, HashMap szqxMap) throws
            BaseException, ApplicationException {

        // String xmmc = "";// ��Ŀ����
        // String jsdw = "";// ���赥λ
        // String cqxkzh = "";// ��Ǩ���֤��
        // String cqxkzspsj = "";// ��Ǩ���֤������ʱ��
        // String cqfw = "";// ��Ǩ��Χ
        String[] cqxxbt = new String[6];

        ArrayList cqxxListRead = new ArrayList();

        try {
            // �����ļ�����
            InputStream stream = ff.getInputStream();

            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(stream));
            HSSFSheet sheet = wb.getSheetAt(0);

            // ��ȡ��ͷ����
            for (short i = 1; i < 6; i++) {

                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = null;

                if (row == null) {

                    throw new ApplicationException("�ϴ��ļ��е�" + i + "�����ݲ�����Ϊ�գ�");

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
//							// LogUtil.log("У���ϴ��ļ��в�Ǩ���֤����ʱ���ʽ \n");
//
//							if (!(RegexUtil
//									.isRegex(
//											"[1-2]\\d{3}-(0[1-9])?(1[0-2])?-(0[1-9])?([1-2][0-9])?(3[0-1])?",
//											cqxxbt[4]))) {
//
//								// LogUtil.log("У���ϴ��ļ��в�Ǩ���֤����ʱ���ʽ is false
//								// \n");
//
//								throw new ApplicationException(
//										"�ϴ��ļ��в�Ǩ���֤����ʱ���ʽ����ȷ��");
//
//							}
//						}

                    }
//					else if (i == 3) {
//
//						// LogUtil.log("�ϴ��ļ��в�Ǩ���֤��Ϊ�գ�\n");
//
//						throw new ApplicationException("�ϴ��ļ��в�Ǩ���֤��Ϊ�գ�");
//
//					}
//					else if (i == 4) {
//
//						// LogUtil.log("�ϴ��ļ��в�Ǩ���֤����ʱ��Ϊ�գ�\n");
//
//						throw new ApplicationException("�ϴ��ļ��в�Ǩ���֤����ʱ��Ϊ�գ�");
//
//					}
                    else if (i != 0 && i != 4 && i != 3) {

                        // LogUtil.log("cell is null" + "\n");

                        int r = i + 1;

                        throw new ApplicationException("�ϴ��ļ��е�" + r
                                + "�е�2���ֶ�����Ϊ�գ�");
                    }

                }
            }

            // ��ȡ��ϸ����
            for (short i = 8; i <= sheet.getLastRowNum(); i++) {

                CqxxImportErrbvo cqxxImportErrbvo = new CqxxImportErrbvo();

                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = null;

                if (row == null) {

                    LogUtil.log("�ϴ��ļ��е�" + i + "������Ϊ�գ�");

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

                            // LogUtil.log(" ���" + lStr + "\n");
                            break;
                        case 1:

                            // LogUtil.log(" ���� ����Ǩ������" + lStr + "\n");
                            cqxxImportErrbvo.setBcqrmc(lStr); // ����\����Ǩ������
                            break;
                        case 2:

                            // LogUtil.log(" ���֤�� ֤������" + lStr + "\n");
                            cqxxImportErrbvo.setZjhm(lStr); // ���֤��\֤������
                            break;
                        case 3:

                            // LogUtil.log(" ����������" + lStr + "\n");
                            cqxxImportErrbvo.setGjrmc(lStr); // ����������
                            break;
                        case 4:

                            // LogUtil.log(" ��ʽ���ݼ���" + lStr + "\n");
                            cqxxImportErrbvo.setZsfwjs(lStr); // ��ʽ���ݼ���
                            break;
                        case 5:

                            // LogUtil.log(" ��ʽ���ݽ������ ��Ǩ���" + lStr + "\n");
                            cqxxImportErrbvo.setCqmj(lStr); // ��ʽ���ݽ������\��Ǩ���
                            break;
                        case 6:

                            // LogUtil.log(" �����Խ���� " + lStr + "\n");
                            break;
                        case 7:

                            // LogUtil.log(" ������ϸ��ַ(50) ��Ǩ��ϸ��ַ" + lStr +
                            // "\n\n");
                            cqxxImportErrbvo.setCqxxdz(lStr); // ������ϸ��ַ(50)\��Ǩ��ϸ��ַ
                            break;
                        }

                        // } else {
                        // LogUtil.log("cell is null" + "\n");
                        // int r = i + 1;
                        // int c = j + 1;
                        // throw new ApplicationException("�ϴ��ļ��е�" + r + "�е�" +
                        // c
                        // + "���ֶ�����Ϊ�գ�");
                        // }

                        // ��Ŀ����\��Ǩ��Ŀ����
                        cqxxImportErrbvo.setCqxmmc(cqxxbt[1]);

                        // ���赥λ\��Ǩ������
                        cqxxImportErrbvo.setCqrmc(cqxxbt[2]);

                        // ��Ǩ���֤��
                        cqxxImportErrbvo.setCqxkzh(cqxxbt[3]);

                        // ��Ǩ���֤������ʱ��
                        cqxxImportErrbvo.setCqxkzspsj(cqxxbt[4]);

                        // ��Ǩ��Χ
                        cqxxImportErrbvo.setCqfw(cqxxbt[5]);

                        cqxxImportErrbvo.setBcqrlxdm("0"); // ����Ǩ�����ʹ���

                        cqxxImportErrbvo.setBcqrlxmc("����"); // ����Ǩ����������

                        cqxxImportErrbvo.setZjlxdm("02"); // ֤�����ʹ���

                        cqxxImportErrbvo.setZjlxmc("���֤"); // ֤����������

                        cqxxImportErrbvo.setBcje("0"); // �������

                        cqxxImportErrbvo.setBclxdm("3"); // �������ʹ���

                        cqxxImportErrbvo.setBclxmc("���"); // ������������

                        cqxxImportErrbvo.setBcmj("0"); // �������

                        cqxxImportErrbvo.setBcfwdz(""); // �������ݵ�ַ

                        cqxxImportErrbvo
                                .setQxdm(ud.getSsdwdm().substring(0, 2)); // ���ش���

                        cqxxImportErrbvo.setLrr(ud.getYhid()); // ¼����

                        cqxxImportErrbvo.setCjr(ud.getYhid()); // ������

                        cqxxImportErrbvo.setLrrq(DateTimeUtil
                                                 .stringToTimestamp(
                                DateTimeUtil
                                .getCurrentDate(), "yyyyMMdd")); // ¼������

                        cqxxImportErrbvo.setCjrq(DateTimeUtil
                                                 .stringToTimestamp(
                                DateTimeUtil
                                .getCurrentDate(), "yyyyMMdd")); // ��������

                        cqxxImportErrbvo.setSjly(Constants.CQXXB_SJLY_DR); // ������Դ����

                        // ��������
                        if (szqxMap.get(ud.getSsdwdm().substring(0, 2)) == null) {
                            cqxxImportErrbvo.setSzqx("");
                        } else {
                            cqxxImportErrbvo.setSzqx(szqxMap.get(
                                    ud.getSsdwdm().substring(0, 2)).toString());
                        }

                        cqxxImportErrbvo.setSwjgzzjgdm(ud.getSsdwdm()); // ˰�������֯��������

                    }

                    cqxxListRead.add(cqxxImportErrbvo);
                }

            }

            try {

                stream.close();
                LogUtil.log("Pkjb002Action uploadfile close input" + "\n");

            } catch (Exception ex) {

                ex.printStackTrace();

                LogUtil.log("�ļ��ر�ʧ��" + "\n");

                // throw ExceptionUtil.getBaseException(ex);
            } finally {

                if (stream != null) {
                    stream.close();
                }
            }

        } catch (FileNotFoundException fnfe) {

            // LogUtil.log("FileNotFoundException:�Ҳ���ָ���ļ�" + "\n");

            throw new ApplicationException("FileNotFoundException:�Ҳ���ָ���ļ�"
                                           + "\n");

        } catch (IOException ioe) {

            // LogUtil.log("IOException:��д�ļ�ʱ��������" + "\n");

            throw new ApplicationException("IOException:��д�ļ�ʱ��������" + "\n");

        } catch (ApplicationException ex) {

            throw new ApplicationException(ex.getMessage());

        } catch (Exception ex) {

            throw ExceptionUtil.getBaseException(ex);
        }
        return cqxxListRead;
    }

}
