/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻��ɿ���¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJkslrAction
    extends SmsbAction {

  /**
   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, form, request, response);
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                     "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;"+
                     "<font color=\"#7C9AAB\">�걨����</font>&gt;"+
                     "<font color=\"#7C9AAB\">���幤�̻�</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "�ɿ���¼��");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/gtgsh/Gjkslr-000.htm");
  }


  /**
   * ҳ���ʼ��
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

    GtgshJkslrForm pf = (GtgshJkslrForm) form;

    try {
      //���Ԥװ�ص���Ϣ
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(request);
      if (ud != null) {
        pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
      }
      pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud));//�õ����������

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.GTGSH_JKSLR_PROCESSOR);
      //����processor
      pf = (GtgshJkslrForm) ZhsbProxy.getInstance().process(vo);
      //
      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }

  /**
   * ��ѯ
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQuery(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException {

    GtgshJkslrForm pf = (GtgshJkslrForm) form;
    pf.setJkpzh(""); //��ѯ��������� �ɿ���ϼƽ��ɿ����
    pf.setJkshjje("");
    try {
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(request);

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.GTGSH_JKSLR_PROCESSOR);
      //����processor
      pf = (GtgshJkslrForm) ZhsbProxy.getInstance().process(vo);
      //���ó�ʼ����js����
      //���ó�ʼ�������Ĳ���
      ZhsbActionForm temp = new ZhsbActionForm();
      //���ü��������
      temp.setJsjdm(pf.getNsrjsjdm());
      //�����걨����
      temp.setSbrq(SfDateUtil.getDate());
      ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
      //����ǰ̨��ʾ��js����
      pf.setScriptStr(zhsb.getScriptStr());
      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      pf.reset(mapping, request);
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Query");
  }

  /**
   * ����
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doSave(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    String forwardFlag = "Save";

    GtgshJkslrForm pf = (GtgshJkslrForm) form;

    //��ֹrefresh
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null) {
      return forward;
    }
    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);

    String columns[] = pf.getColumns();
    pf.setDataList(this.getValuesToList(request, columns));
    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_SAVEACTION);
    vo.setProcessor(CodeConstant.GTGSH_JKSLR_PROCESSOR);
    vo.setData(pf);
    vo.setUserData(ud);
    //����ͨ���ܿصõ����û���Ϣ
    vo.setUserData(this.getUserData(request));
    try {
      pf = (GtgshJkslrForm)ZhsbProxy.getInstance().process(vo);
      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"����ɹ���");
      /********************Modified by lufeng 2003-12-11********************/
      //����ɹ�����б��ӡҳ��
      GtgshCxjksForm wszPF = new GtgshCxjksForm();
      wszPF.setActionType("Show");
      wszPF.setHeadJsjdm(pf.getNsrjsjdm());//��˰�˼��������
      request.setAttribute("gtgshCxjksForm", wszPF);
      forwardFlag = "Dismiss";
      //pf.reset(mapping, request);
    }
    catch (Exception ex) {
      forwardFlag = "Save";
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward(forwardFlag);
  }

  /**
   * ��ת������ҳ��
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doDismiss(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
      BaseException {
    GtgshJkslrForm pf1 = (GtgshJkslrForm) form;
    GtgshCxjksForm pf = new GtgshCxjksForm();
    try {
      //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
      pf.setActionType("Show");
      pf.setHeadJsjdm(pf1.getNsrjsjdm());//��˰�˼��������
      request.setAttribute("gtgshCxjksForm", pf);
    }
    catch (Exception ex) {
    }
    return mapping.findForward("Dismiss");
  }


  /**
   * ȡ�ð���˰��˰Ŀlist��Ӫҵ˰����˰list��list
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @return the element previously at the specified position.
   * @exception Exception
   */
  private ActionForm getInitList(ActionForm actionForm) throws Exception {
    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
    //�����ۺ��걨��processorȡ��ǰ̨��ʾ�õ�js����
    vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    vo.setData(actionForm);
    try {
      return (ActionForm) ZhsbProxy.getInstance().process(vo);
    }
    catch (BaseException ex) {
      throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
    }
  }

}
