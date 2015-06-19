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

/**
 * <p>Title: ������˰��������ϵͳ--�����걨--���ع��ܻ�</p>
 * <p>Description: ���пۿ��ļ������ϴ�Action</p>
 * @author �������飭�������
 * @version 1.0
 */
public class YhdkwjscscAction
    extends SmsbAction {
  public YhdkwjscscAction() {
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
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjscscForm tForm =
          (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
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

    BankFTPMag ftpManager = new BankFTPMag(); //ftp������
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;

    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscProcessor");
    vo.setUserData(userData);
    String qxdm = yForm.getQxdm(); //���ش���
    String yhdm = ""; //���д���
    String ny = yForm.getNy(); //����
    String nd = ny.substring(0, 4); //���
    String yd = ny.substring(4, 6); //�¶�
    String ssny = ""; // ��������
    String ssnd = ""; // �������
    String ssyd = ""; // �����¶�
    if (yd.equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(nd) + 9999).substring(1, 5);
      ssyd = "12";
    }
    else {
      ssnd = nd;
      ssyd = String.valueOf(Integer.parseInt(yd) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    StringBuffer lsLogName = new StringBuffer(GghConstant.FILE_PREFIX_LS).
        append(ssny);
    // 1. �ж����ļ�����
    if (yForm.getQxdmOfBccb().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_BJ;
    }
    else if (yForm.getQxdmOfNxs().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_NX;
    }
    lsLogName.append(yhdm).append(GghConstant.FILE_POSTFIX_LOG); //��ʾ���п�ʼ��ȡ�ۿ��ļ�����־�ļ�
    yForm.setYhdm(yhdm);
    // 2. �жϸ����ظ����ļ��Ƿ��ѱ���ȡ
    try {
      if (ftpManager.checkLsLogFile(lsLogName.toString())) {
        // 2.1 ��������ȡ��ʶ
        try {
          vo.setData(yForm);
          vo.setAction(CodeConstant.SMSB_UPDATEACTION);
          ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("������ȡ���´�����Ϣ�������ٴ�����", false));
          /* start added by huxiaofeng 2005.8.1*/
          ex.printStackTrace();
          /* end added by huxiaofeng 2005.8.1*/
          throw ExceptionUtil.getBaseException(ex);
        }

        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("������ȡ���´�����Ϣ�������ٴ�����", false));
        return mapping.findForward("Save");
      }
    }
    catch (Exception ex5) {
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("��ȡ��־�ļ�ʧ��:" + ex5.getMessage(), false));
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("��ȡ��־�ļ�ʧ��");
      ex5.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      return mapping.findForward("Save");
    }

    // 3. ɾ�����пۿ��ļ�
    try {
      String fileName = new StringBuffer(ssny).append(qxdm.substring(0, 2)).
          append(yhdm).append(GghConstant.FILE_POSTFIX_DATA).toString();
      ftpManager.delete(new StringBuffer(GghConstant.FILE_PREFIX_MX).append(
          fileName).toString(), 0);
      ftpManager.delete(new StringBuffer(GghConstant.FILE_PREFIX_HZ).append(
          fileName).toString(), 0);
    }
    catch (Exception ex5) {
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("ɾ�����д�����Ϣʧ��:" + ex5.getMessage(), false));
      return mapping.findForward("Save");
    }
    long time2 = System.currentTimeMillis();
    // 4. ���沢��װ�ۿ�����
    try {
      // 4.1 ����ۿ�����
      try {
        vo.setData(yForm);
        vo.setAction(CodeConstant.SMSB_SAVEACTION); //���淽��
        yForm = (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
        ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("���ɴ�����Ϣʧ��:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }

      long time3 = System.currentTimeMillis();
      System.out.println(qxdm.substring(0, 2) + " [���д����ļ��ϴ�] ���ɿۿ��ܺ�ʱ:  " +
                         (time3 - time2) + " ����");

      // 4.2 �ϴ��ۿ��ļ�
      List list = (List) ( (HashMap) (yForm.getFtpMap()).get("MXWJ")).get(
          "CONTENT");
      if (list.size() > 0) { //����пۿ���Ϣ���ϴ�
        try {
          ftpManager.putKkData(yForm.getFtpMap());
        }
        catch (Exception ex5) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("�ϴ�������Ϣʧ��:" + ex5.getMessage(), false));
          throw ExceptionUtil.getBaseException(ex5);
        }
      }

      long time4 = System.currentTimeMillis();
      System.out.println(qxdm.substring(0, 2) + " [���д����ļ��ϴ�] �ϴ��ļ���ʱ:  " +
                         (time4 - time3) + " ����");
      yForm.getFtpMap().remove("MXWJ"); //������õĴ����
      System.gc();

      // 4.3 ���¿ۿ���Ϣ״̬
      vo.setAction(CodeConstant.SMSB_UPLOADACTION); //���·���
      vo.setData(yForm);
      try {
        yForm = (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex1) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("���¿ۿ���Ϣ״̬ʧ��", false));
        throw ExceptionUtil.getBaseException(ex1);
      }

      //�ɹ���
      StringBuffer sb = new StringBuffer();
      if (list.size() > 0) {
        HashMap sum = (HashMap) (yForm.getFtpMap()).get("HZWJ");
        sb.append("���ɲ��ϴ�������Ϣ�ɹ���������")
            .append(yForm.getHs()).append("����")
            .append( (String) sum.get("COUNT")).append("�ʿۿ���Ϣ���ܼ�")
            .append( (String) sum.get("TOTALAMOUNT")).append("Ԫ");
      }
      else {
        sb.append("�����ر���û�д�����Ϣ");
      }
      System.out.println(qxdm.substring(0, 2) + " [���д����ļ��ϴ�] " + sb.toString());
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(sb.toString(), true));
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

}