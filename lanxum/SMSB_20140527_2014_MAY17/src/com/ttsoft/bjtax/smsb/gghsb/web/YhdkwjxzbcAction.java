/*
 * <p>Title:������˰��������ϵͳ--�����걨</p>
 * <p>Copyright:  (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾��Ȩ����.</p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjscscForm;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.ftp.BankFTPMag;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;

/**
 * <p>Title: ������˰��������ϵͳ--�����걨--���ع��ܻ�</p>
 * <p>Description: ���пۿ��ļ����ر���Action</p>
 * @author �������飭�������
 * @version 1.0
 */
public class YhdkwjxzbcAction
    extends SmsbAction {
  public YhdkwjxzbcAction() {
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
    YhdkwjxzbcForm yForm = (YhdkwjxzbcForm) aForm;
    userData = this.getUserData(request);
    vo.setAction(CodeConstant.SMSB_SHOWACTION);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjxzbcProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjxzbcForm tForm =
          (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
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
    userData = this.getUserData(request);
    VOPackage vo = new VOPackage();
    YhdkwjxzbcForm yForm = (YhdkwjxzbcForm) aForm;
    String ny = yForm.getNy();
    String qxdm = yForm.getQxdm().substring(0, 2);
    yForm.setNd(ny.substring(0, 4));
    yForm.setYd(ny.substring(4, 6));
    String ssny = ""; // ��������
    String ssnd = ""; // �������
    String ssyd = ""; // �����¶�
    if (yForm.getYd().equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(yForm.getNd()) + 9999).substring(1, 5);
      ssyd = "12";
    }
    else {
      ssnd = yForm.getNd();
      ssyd = String.valueOf(Integer.parseInt(yForm.getYd()) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    String yhdm = "";
    //�ж����ļ�����
    if (yForm.getQxdmOfBccb().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_BJ;
    }
    else if (yForm.getQxdmOfNxs().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_NX;
    }
    String flag = ssny + qxdm + yhdm; //���ɴ��͵ı��
    String[] files = null; //�Ѿ�ȡ�����ļ���...���ϴ�״̬����ȡ��

    try {
      // 1. ��ȡ�������ļ����б�
      try {
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjxzbcProcessor");
        vo.setData(yForm);
        vo.setUserData(userData);
        yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        files = yForm.getFileNames();
      }
      catch (Exception ex) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("��ȡ�������ļ��б�ʧ��", false));
        return mapping.findForward("Save");
      }

      // 2. ��ftp Server��ȡ�ļ�....
      HashMap fileHash = null;
      try {
        BankFTPMag bankFtp = new BankFTPMag();
        fileHash = bankFtp.getFtpYhfk(flag, files);
      }
      catch (Exception ex1) {
        ex1.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             ,
                             getPrintMessage("��FTP�����������ļ�ʧ��:" + ex1.getMessage(), false));
        return mapping.findForward("Save");
      }
      if (fileHash.isEmpty()) {
        request.setAttribute(mapping.getAttribute(), yForm);
        if (files == null) {
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("������û�пɹ����ص����пۿ��ļ�", false));
        }
        else {
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("�����ڵ����пۿ��ļ��Ѿ����؎ձ����", false));
        }
        return mapping.findForward("Save");
      }
      else if (files == null || files.length < 4) {
        //����ϴ���ȡ�ļ���С��4,����Ҫ��� ״̬�����ȡ�ļ�״̬
        try {
          yForm.setFileCount(fileHash.keySet().size());
          vo.setAction(CodeConstant.SMSB_UPDATEACTION);
          vo.setData(yForm);
          yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex3) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               ,
                               getPrintMessage("�޸�״̬�����ȡ�ļ�״̬ʧ��:" +
                                               ex3.getMessage(), false));
          return mapping.findForward("Save");
        }
      }

      // 3. ����ۿ��ִ��Ϣ
      try {
        yForm.setFtpMap(fileHash);
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(yForm);
        yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        yForm.setFtpMap(null);
        fileHash=null;
        System.gc();
      }
      catch (Exception ex2) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                             getPrintMessage("������������пۿ��ִ��Ϣʧ��:" + ex2.getMessage(), false));
        return mapping.findForward("Save");
      }
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(yForm.getCgxx(), true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
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