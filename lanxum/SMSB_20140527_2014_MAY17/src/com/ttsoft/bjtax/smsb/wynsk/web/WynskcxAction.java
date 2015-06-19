package com.ttsoft.bjtax.smsb.wynsk.web;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.wynsk.excel.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class WynskcxAction
    extends SmsbAction {

  public static final String SESSION_SWSLIST_NAME =
      "sessionswslistname20050224";

  public static final String SESSION_QYZTLIST_NAME =
      "sessionqyztlistname20050224";

  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
        "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>��Ӧ��˰���ѣ����걨��ѯ ");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "");
  }

  /**
   * doShow
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      //����EJB
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setUserData(ud);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
      pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }

  /**
   * doQueryA
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQueryA(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException {
    debug("Call doQueryA......");
    super.doInit(mapping, form, request, response);
    //У���������
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      //****************add by zhangshubing
       pf.getQueryDjzclx();
      //***********************************

      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      //resetҳ�����
      pf.reset(mapping, request);
      //���������鲢�����Ƿ����EJB
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ��β��Ϸ������޸ĺ����²�ѯ.");
      }
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("QueryA");
  }

  /**
   * doQueryB
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQueryB(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException {
    debug("Call doQueryB......");
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      //****************add by zhangshubing
      pf.getQueryDjzclx();
      //***********************************

      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      //resetҳ�����
      pf.reset(mapping, request);
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_QUERYACTION1);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ�䲻�Ϸ������޸ĺ����²�ѯ.");
      }
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("QueryB");
  }

  /**
   * doChangePageA
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doChangePageA(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      BaseException {
    debug("Call doChangePageA......");
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ�䲻�Ϸ������޸ĺ����²�ѯ.");
      }
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("ChangePageA");
  }

  /**
   * doChangePageA
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doChangePageB(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      BaseException {
    debug("Call doChangePageB......");
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_QUERYACTION1);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ�䲻�Ϸ������޸ĺ����²�ѯ.");
      }
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("ChangePageB");
  }

  /**
   * doExportA
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doExportA(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
      BaseException {
    debug("Call doExportA......");
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      //resetҳ�����
      pf.reset(mapping, request);
      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ�䲻�Ϸ������޸ĺ����²�ѯ.");
      }
      //����ļ�
      debug("���Excel�ļ�A��¼����" + pf.getDataList().size());
      if (pf.getDataList().size() > 0) {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "�걨��ѯ���".concat(currDate).concat(".xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
            fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil eu = new ExcelUtil();
        eu.generateKKXXExcel(response.getOutputStream(), pf.getDataList());
      }
      else {
        pf.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
      }
      //��������
      pf.setDataList(new ArrayList());
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    if (pf.getDataList().size() > 0) {
      return null;
    }
    else {
      return mapping.findForward("ExportA");
    }
  }

  /**
   * doExportB
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doExportB(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
      BaseException {
    debug("Call doExportB......");
    super.doInit(mapping, form, request, response);
    //��õ�ǰ��userData����
    UserData ud = null;
    WynskcxActionForm pf = null;
    VOPackage vo = new VOPackage();
    //ִ��Processor
    try {
      //��ʼ��
      ud = this.getUserData(request);
      pf = (WynskcxActionForm) form;
      if (pf == null) {
        pf = new WynskcxActionForm();
      }
      //resetҳ�����
      pf.reset(mapping, request);
      if (this.checkInputValid(pf.getQuerySbrqq(), pf.getQuerySbrqz())) {
        //����EJB
        vo.setAction(CodeConstant.SMSB_TOEXCELACTION1);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
      }
      else {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setUserData(ud);
        vo.setData(pf);
        vo.setProcessor(CodeConstant.WYNSKCX_PROCESSOR);
        pf = (WynskcxActionForm) ZhsbProxy.getInstance().process(vo);
        pf.setMessage("��ѯ�����걨������ֹʱ�䲻�Ϸ������޸ĺ����²�ѯ.");
      }
      //����ļ�
      debug("���Excel�ļ�B��¼����" + pf.getDataList().size());
      if (pf.getDataList().size() > 0) {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "�걨��ѯ���".concat(currDate).concat(".xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
            fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil eu = new ExcelUtil();
        eu.generateKKXXExcel(response.getOutputStream(), pf.getDataList());
      }
      else {
        pf.setMessage("û�в�ѯ�����ݣ��޷�����Excel�ļ�");
      }
      //��������
      pf.setDataList(new ArrayList());
      //��дsession����
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    if (pf.getDataList().size() > 0) {
      return null;
    }
    else {
      return mapping.findForward("ExportA");
    }

  }

  /**
   * ��������ѯ�����Ϸ���
   * @param sbrqq �걨������
   * @param sbrqz �걨����ֹ
   * @return �Ϸ���־
   */
  private boolean checkInputValid(String sbrqq, String sbrqz) throws
      BaseException {
    //1.�������
    boolean returnFlag = true;
    int offset = -1;
    //2.��ʼ����ֵ
    debug("��ѯ�����걨������" + sbrqq + "|");
    debug("��ѯ�����걨����ֹ��" + sbrqz + "|");
    sbrqq = SBStringUtils.trim(sbrqq);
    sbrqz = SBStringUtils.trim(sbrqz);
    Date dsbrqq = SBStringUtils.getDateValue(sbrqq, "yyyyMMdd");
    Date dsbrqz = SBStringUtils.getDateValue(sbrqz, "yyyyMMdd");
    int startYear = dsbrqq.getYear();
    int startMonth = dsbrqq.getMonth();
    int endYear = dsbrqz.getYear();
    int endMonth = dsbrqz.getMonth();
    //3.��ʼҵ��
    offset = (endYear * 12 + endMonth) - (startYear * 12 + startMonth);
    debug("��ѯ����ƫ����=" + offset);
    if (offset > 1 || offset < 0)
      returnFlag = false;
      //99.����ֵ
    return returnFlag;
  }

  private void debug(String str) {
    //System.out.println("SMSB DEBUG:" + str);
  }

  private void debugCore(String str) {
    System.out.println("SMSB CORE:" + str);
  }

}