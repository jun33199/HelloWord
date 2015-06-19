package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.common.model.UserData;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import java.util.ArrayList;
import java.util.Date;

import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.excel.ExcelUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;
import com.ttsoft.bjtax.smsb.gghsb.KkztcxVo;
import java.io.OutputStream;

/**
 * <p>Title: ������˰��������ϵͳ--���ڶ������пۿ�-�ۿ�״̬��ѯ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)�����еط�˰��֣�������һ���ſƼ����޹�˾ ��Ȩ���� 2005</p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 * @author not attributable
 * @version 1.0
 */

public class KkztcxAction
    extends SmsbAction
{
    public KkztcxAction()
    {
    }

    /**
     * ���ݼ����󣨰���Form��UserData����
     */
    private VOPackage vo = new VOPackage();

    /**
     * �û���Ϣ����
     */
    UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param response The HTTP response we are creating
     */

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)

    {
        System.out.println("--print--KkztcxAction doInit");
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">ί�����пۿ�����</font>&gt;�ۿ�״̬��ѯ&gt;</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�ۿ�״̬��ѯ");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/gzwh/gzwh-000.htm");
    }

    /**
     * ��ѯ�ۿ�״̬��Ϣ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
        BaseException
    {
        KkztcxForm kf = (KkztcxForm) form;
        try
        {
            //��ѯ
            System.out.println("doQuery---------action--------begin---");
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.GTGSH_KKZTCX_PROCESSOR);
            vo.setData(kf);
            vo.setUserData(this.getUserData(request));
            kf = (KkztcxForm) ZhsbProxy.getInstance().process(vo);
            if (kf.getDataList().size() == 0)
            {
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                                     , getPrintMessage("û�з��������Ŀۿ���Ϣ", true));
            }

            //����form
            request.setAttribute(mapping.getAttribute(), kf);
            System.out.println("doQuery---------action--------end---");
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            //����form
            request.setAttribute(mapping.getAttribute(), kf);
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ����Excel�ļ�����
     * @param mapping The ActionMapping used to select this instance
     * @param aFrom The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */

    public ActionForward doSaveExcel(ActionMapping mapping, ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
        BaseException
    {
        KkztcxForm kf = (KkztcxForm) aForm;
        try
        {
            vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
            vo.setProcessor(CodeConstant.GTGSH_KKZTCX_PROCESSOR);
            vo.setData(kf);
            vo.setUserData(this.getUserData(request));
            kf = (KkztcxForm) ZhsbProxy.getInstance().process(vo);
            if (kf.getDataList() == null || kf.getDataList().isEmpty())
            {
                doShow(mapping, aForm, request, response);
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            else
            {
                String currDate = TinyTools.Date2String(new Date(System.
                                                                 currentTimeMillis()));
                String fileName = "�ۿ�״̬".concat(currDate).concat("��ѯ.xls");
                String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
                    fileName);
                response.resetBuffer();
                response.setHeader("Content-disposition",
                                   "attachment; filename=" + encodeFileName);
                response.setContentType("application/vnd.ms-excel");
                HSSFWorkbook wb = new HSSFWorkbook();
                HSSFSheet sheet = wb.createSheet("new sheet");
                HSSFRow row = null;
                ArrayList al = kf.getDataList();
                int length = al.size();
                row = sheet.createRow( (short) 0);
                createCell(wb, row, (short) 0, HSSFCellStyle.ALIGN_CENTER, "���ط־�");
                createCell(wb, row, (short) 1, HSSFCellStyle.ALIGN_CENTER, "���ɿۿ���Ϣʱ��");
                createCell(wb, row, (short) 2, HSSFCellStyle.ALIGN_CENTER, "���ɿۿ���Ϣ����");
                createCell(wb, row, (short) 3, HSSFCellStyle.ALIGN_CENTER, "���ɿۿ���Ϣ����");
                createCell(wb, row, (short) 4, HSSFCellStyle.ALIGN_CENTER, "���ɿۿ���Ϣ���");
                createCell(wb, row, (short) 5, HSSFCellStyle.ALIGN_CENTER, "���е�һ�οۿ�����");
                createCell(wb, row, (short) 6, HSSFCellStyle.ALIGN_CENTER, "���е�һ�οۿ�ɹ�����");
                createCell(wb, row, (short) 7, HSSFCellStyle.ALIGN_CENTER, "���е�һ�οۿ�ɹ����");
                createCell(wb, row, (short) 8, HSSFCellStyle.ALIGN_CENTER, "���еڶ��οۿ�����");
                createCell(wb, row, (short) 9, HSSFCellStyle.ALIGN_CENTER, "���еڶ��οۿ�ɹ�����");
                createCell(wb, row, (short) 10, HSSFCellStyle.ALIGN_CENTER, "���еڶ��οۿ�ɹ����");
                createCell(wb, row, (short) 11, HSSFCellStyle.ALIGN_CENTER, "���пۿ�ɹ������ϼ�");
                createCell(wb, row, (short) 12, HSSFCellStyle.ALIGN_CENTER, "���пۿ�ɹ����ϼ�");
                createCell(wb, row, (short) 13, HSSFCellStyle.ALIGN_CENTER, "���пۿ�ɹ�����");
                createCell(wb, row, (short) 14, HSSFCellStyle.ALIGN_CENTER, "���пۿ�ɹ����");

                for (int i = 1; i <= length; i++)
                {
                    KkztcxVo kvo = (KkztcxVo) al.get(i - 1);
                    row = sheet.createRow( (short) i);
                    createCell(wb, row, (short) 0, HSSFCellStyle.ALIGN_CENTER, kvo.getQxfj());
                    createCell(wb, row, (short) 1, HSSFCellStyle.ALIGN_CENTER, kvo.getSckkxxsj());
                    createCell(wb, row, (short) 2, HSSFCellStyle.ALIGN_CENTER, kvo.getKkzhs());
                    createCell(wb, row, (short) 3, HSSFCellStyle.ALIGN_CENTER, kvo.getKkxxzbs());
                    createCell(wb, row, (short) 4, HSSFCellStyle.ALIGN_CENTER, kvo.getSckkxxje());
                    createCell(wb, row, (short) 5, HSSFCellStyle.ALIGN_CENTER, kvo.getYckkcgrq());
                    createCell(wb, row, (short) 6, HSSFCellStyle.ALIGN_CENTER, kvo.getYckkcgbs());
                    createCell(wb, row, (short) 7, HSSFCellStyle.ALIGN_CENTER, kvo.getYckkcgje());
                    createCell(wb, row, (short) 8, HSSFCellStyle.ALIGN_CENTER, kvo.getEckkcgrq());
                    createCell(wb, row, (short) 9, HSSFCellStyle.ALIGN_CENTER, kvo.getEckkcgbs());
                    createCell(wb, row, (short) 10, HSSFCellStyle.ALIGN_CENTER, kvo.getEckkcgje());
                    createCell(wb, row, (short) 11, HSSFCellStyle.ALIGN_CENTER, kvo.getKkcgzbs());
                    createCell(wb, row, (short) 12, HSSFCellStyle.ALIGN_CENTER, kvo.getKkcgzje());
                    createCell(wb, row, (short) 13, HSSFCellStyle.ALIGN_CENTER, kvo.getKkbcgzbs());
                    createCell(wb, row, (short) 14, HSSFCellStyle.ALIGN_CENTER, kvo.getKkbcgzje());
                }

                // Write the output to a file
                //FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                OutputStream out = response.getOutputStream();
                wb.write(out);
                out.close();

                return null;
            }
        }
        catch (Exception e)
        {
            try
            {
                vo.setAction(CodeConstant.SMSB_SHOWACTION);
                vo.setProcessor(CodeConstant.GTGSH_KKZTCX_PROCESSOR);
                vo.setData(kf);
                vo.setUserData(this.getUserData(request));
                kf = (KkztcxForm) ZhsbProxy.getInstance().process(vo);
            }
            catch (Exception ex)
            {
            }

            //����form
            request.setAttribute(mapping.getAttribute(), kf);
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ʾ����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm aForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {
        try
        {
            System.out.println("--print--KkztcxAction doShow");
            //�������
            //removeForm(mapping, request);
            KkztcxForm kf = (KkztcxForm) aForm;
            kf.setDataList(new ArrayList());
//     vo.setData(kf);
//     vo.setUserData(this.getUserData(request));
//     vo.setAction(CodeConstant.SMSB_SHOWACTION);
            //vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
            //kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
            //request.setAttribute(mapping.getAttribute(), kf);
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ת�������Ϣ��ʽ
     * @param message �������
     * @param successFlag �ɹ�|ʧ��
     * @return
     */
    private String getPrintMessage(String message, boolean successFlag)
    {
        StringBuffer printMessage = new StringBuffer();
        if (successFlag)
        { //�ɹ���Ϣ
            printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
                .append(message).append("��</font></strong>");
        }
        else
        { //ʧ����Ϣ
            printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
                .append(message).append("��</font></strong>");
        }
        return printMessage.toString();
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb        the workbook
     * @param row       the row to create the cell in
     * @param column    the column number to create the cell in
     * @param align     the alignment for the cell.
     */
    private static void createCell(HSSFWorkbook wb, HSSFRow row, short column, short align,
                                   String value)
    {
        HSSFCell cell = row.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(value);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(align);
        cell.setCellStyle(cellStyle);
    }

}