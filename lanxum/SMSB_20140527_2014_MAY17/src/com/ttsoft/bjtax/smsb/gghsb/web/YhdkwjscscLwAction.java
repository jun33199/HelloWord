/*
 * <p>Title:������˰��������ϵͳ--�����걨</p>
 * <p>Copyright:  (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾��Ȩ����.</p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjscscForm;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.ftp.BankFTPMag;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;
import com.ttsoft.bjtax.shenbao.service.adaptor.SKHAdaptor;
import java.util.Date;
import java.util.Map;

/**
 * <p>Title: ������˰��������ϵͳ--�����걨--���ع��ܻ�</p>
 * <p>Description: </p>
 * @author �������飭�������
 * @version 1.0
 */
public class YhdkwjscscLwAction
    extends SmsbAction {

  public YhdkwjscscLwAction() {
  }

  UserData userData = null;

  /**
   * ���ܣ�������ǰ�ô�������
   * @param mapping Struct ActionMapping
   * @param form  instance of BaseForm
   * @param request request from HTML
   * @param response response from HTML
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                         "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�����걨</font>&gt;<font color=\"#567587\">ί�����пۿ�����</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "help/smsb/yhsgl/yhsgmlr-000.htm");
  }

  /**
   * ��ʾ����
   * @param mapping The ActionMapping used to select this instance
   * @param aForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return ActionForward
   * @throws BaseException
   */
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    HttpSession session = request.getSession(false);
    VOPackage vo = new VOPackage();
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    userData = this.getUserData(request);
    vo.setAction(CodeConstant.SMSB_SHOWACTION);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjscscForm tForm =
          (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      log(tForm.getNyList().size()+"");
      log(tForm.getQxList().size()+"");
      session.setAttribute("GGHSB_YHDK_QXLIST", tForm.getQxList()); // ���ؾ��б�
      session.setAttribute("GGHSB_YHDK_NYLIST", tForm.getNyList()); // �����������б�
      tForm.setLrr(String.valueOf(userData.getYhid()));
      tForm.setLrrq(SfDateUtil.getDate());
      tForm.setCjr(String.valueOf(userData.getYhid()));
      tForm.setCjrq(SfDateUtil.getDate());
      request.setAttribute(mapping.getAttribute(), tForm);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      e.printStackTrace();
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("��ʼ��ҳ��ʧ��:" + e.getMessage(), false));
      return mapping.findForward("Show");
    }
  }

  /**
   * ���洦��
   * @param mapping The ActionMapping used to select this instance
   * @param aForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return ActionForward
   * @throws BaseException
   */
  public ActionForward doSave(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null) {
      return forward;
    }
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    String msg=null;
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setUserData(userData);
    try {
      try {

        vo.setAction(CodeConstant.SMSB_SAVEACTION); //���淽��
        Map map=new HashMap();
        map.put("form",yForm);
        vo.setData(map);
        log(map);
        map = (HashMap) ZhsbProxy.getInstance().process(vo);
        //����ֵӦ������form��msg
        yForm=(YhdkwjscscForm)map.get("form");
        msg=(String)map.get("msg");
      }
      catch (Exception ex) {
                ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("���ɴ�����Ϣʧ��:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }
      //�ɹ���
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(msg, true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("Save");
    }
  }



  /**
   * ���洦��
   * @param mapping The ActionMapping used to select this instance
   * @param aForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return ActionForward
   * @throws BaseException
   */
  public ActionForward doSend(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null) {
      return forward;
    }
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    String msg=null;
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setUserData(userData);
    try {
      try {
        vo.setAction(CodeConstant.SMSB_UPLOADACTION); //���淽��
        Map map=new HashMap();
        map.put("form",yForm);
        vo.setData(map);
        map = (HashMap) ZhsbProxy.getInstance().process(vo);
        //����ֵӦ������form��msg
        yForm=(YhdkwjscscForm)map.get("yForm");
        msg=(String)map.get("msg");
      }
      catch (Exception ex) {
                ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("���ʹ�����Ϣʧ��:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }
      //�ɹ���
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(msg, true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("Save");
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

  private void log(Object o){
    System.out.println("[SMSB GTGSH YhdkwjscscLwAction (" + (new Date()) + ")]" + o);
  }


}
