package com.ttsoft.bjtax.smsb.gghsb.web;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003-2004�����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @version 1.0
 */

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

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description:�ۿ���Ϣ��ѯAction </p>
 * @author not attributable
 * @version 1.0
 */

public class KkxxcxAction
    extends SmsbAction {
  public KkxxcxAction() {
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
    System.out.println("--print--action doInit");
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">ί�����пۿ�����</font>&gt;�ۿ���Ϣ��ѯ&gt;</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "�ۿ���Ϣ��ѯ");
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
  public ActionForward doShow(ActionMapping mapping, ActionForm aForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    try {
      System.out.println("--print--action doShow");
      //�������
      removeForm(mapping, request);
      KkxxcxForm kf = (KkxxcxForm) aForm;
      kf.setDataList(new ArrayList());
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      request.setAttribute(mapping.getAttribute(), kf);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      e.printStackTrace();
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
    KkxxcxForm kf = (KkxxcxForm) form;
    try {
      //��ѯ
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      if (kf.getDataList().size() == 0) {
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("û�з��������Ŀۿ���Ϣ", true));
      }
      //����form
      request.setAttribute(mapping.getAttribute(), kf);
      return mapping.findForward("Query");
    }
    catch (Exception e) {
      kf.setPgNum(0);
      kf.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
        vo.setData(kf);
        vo.setUserData(this.getUserData(request));
        kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //����form
      request.setAttribute(mapping.getAttribute(), kf);
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
      KkxxcxForm kf = (KkxxcxForm) aForm;
      kf.reset(mapping, request);
      //�ͷ�session�ռ�
      request.getSession().removeAttribute("DataList");
      return mapping.findForward("Return");

    }
    catch (Exception e) {
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
    KkxxcxForm kf = (KkxxcxForm) aForm;
    try {
      vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      if (kf.getDataList() == null || kf.getDataList().isEmpty() ||
          kf.getSumList() == null || kf.getSumList().isEmpty()) {
        kf.setPgNum(0);
        kf.setPgSum(0);
        doShow(mapping, aForm, request, response);
        throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
      }
      else {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "ί�����пۿ�����".concat(currDate).concat("��ѯ.xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
            fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.generateKKXXExcel(response.getOutputStream(), kf);
        return null;
      }
    }
    catch (Exception e) {
      kf.setPgNum(0);
      kf.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
        vo.setData(kf);
        vo.setUserData(this.getUserData(request));
        kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //����form
      request.setAttribute(mapping.getAttribute(), kf);
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