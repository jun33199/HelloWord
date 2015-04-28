package com.ttsoft.bjtax.smsb.gghsb.web;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gghsb.excel.ExcelUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description:���ڶ����������ѯAction </p>
 * @author zhou jinguang
 * @version 1.0
 */

public class DqdehrkcxAction
    extends SmsbAction {

  public DqdehrkcxAction() {
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
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;���ڶ����������ѯ&gt;</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "���ڶ����������ѯ");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/gzwh/gzwh-000.htm");
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
  public ActionForward doShow(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    try {
      //�������
      removeForm(mapping, request);
      DqdehrkcxForm df = (DqdehrkcxForm) actionForm;
      df.setDataList(new ArrayList());
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      request.setAttribute(mapping.getAttribute(), df);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      Debug.printException(e);
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * ��ѯ����
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
      BaseException {
    DqdehrkcxForm df = (DqdehrkcxForm) form;
    try {
      //��ѯ
      System.out.println("execu doquery!");
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      System.out.println("df.getDataList().size():" + df.getDataList().size());
      if (df.getDataList().size() == 0) {
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("û�з�����������Ϣ", true));
      }

      //����form
      request.setAttribute(mapping.getAttribute(), df);
      return mapping.findForward("Query");
    }
    catch (Exception e) {
      df.setPgNum(0);
      df.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
        vo.setData(df);
        vo.setUserData(this.getUserData(request));
        df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }
      //����form
      request.setAttribute(mapping.getAttribute(), df);
      Debug.printException(e);
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
      BaseException {
    DqdehrkcxForm df = (DqdehrkcxForm) aForm;
    try {
      vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      if (df.getDataList() == null || df.getDataList().isEmpty() ||
          df.getSumList() == null || df.getSumList().isEmpty()) {
        df.setPgNum(0);
        df.setPgSum(0);
        doShow(mapping, aForm, request, response);
        throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
      }
      else {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "���ڶ��������".concat(currDate).concat("��ѯ.xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.
            GBK2ISO(fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.generateDQDEHRKCXExcel(response.getOutputStream(), df);
        return null;
      }
    }
    catch (Exception e) {
      df.setPgNum(0);
      df.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
        vo.setData(df);
        vo.setUserData(this.getUserData(request));
        df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //����form
      request.setAttribute(mapping.getAttribute(), df);
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ���ش���
   * @param mapping The ActionMapping used to select this instance
   * @param aFrom The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */
  public ActionForward doReturn(ActionMapping mapping, ActionForm aForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException {
    try {
      removeForm(mapping, request);
      DqdehrkcxForm df = (DqdehrkcxForm) aForm;
      df.reset(mapping, request);
      //�ͷ�session�ռ�
      request.getSession().removeAttribute("DataList");
      return mapping.findForward("Return");

    }
    catch (Exception e) {
      Debug.printException(e);
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * ת�������Ϣ��ʽ
   * @param message �������
   * @param successFlag �ɹ�|ʧ��
   * @return
   */
  private String getPrintMessage(String message, boolean successFlag) {
    StringBuffer printMessage = new StringBuffer();
    if (successFlag) { //�ɹ���Ϣ
      printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
          .append(message).append("��</font></strong>");
    }
    else { //ʧ����Ϣ
      printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
          .append(message).append("��</font></strong>");
    }
    return printMessage.toString();
  }

}