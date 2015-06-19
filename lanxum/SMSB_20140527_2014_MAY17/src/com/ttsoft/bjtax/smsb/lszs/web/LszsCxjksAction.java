/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.LWHelper;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ˰���ճ����ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsCxjksAction
    extends SmsbAction
{

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
                         "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                         "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                         "<font color=\"#7C9AAB\">��ɢ˰����</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                         "�����ɿ���");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "help/smsb/lszs/jkscx-000.htm");
  }

  /**
   * ��ʼ��
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
      BaseException
  {
    LszsCxjksForm pf = (LszsCxjksForm) form;
    Debug.out("in to doshow");
    try
    {
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(request);
      pf.setLrrdm(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
      pf.setHeadJsjdm(InterfaceDj.getZrrJsjdm(ud)); //�������ؼ�������룬
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_SHOWACTION);

      //start add by qianchao 2005.10.26
      boolean lwstate = LWHelper.getLWState(request, pf.getHeadJsjdm());
      if (lwstate)
      {
        pf.setJksType(CodeConstant.PRINT_YPDS_KM);
      }
      else
      {
        pf.setJksType(CodeConstant.PRINT_YPYS);
      }
      //vo.setData(pf);
      vo.setData(pf);
      //end add by qianchao 2005.10.26


      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //����processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);

      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27

      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
      return mapping.findForward("Show");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
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
      BaseException
  {
    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);

    LszsCxjksForm pf = (LszsCxjksForm) form;
    try
    {
      pf.setHeadJsjdm(InterfaceDj.getZrrJsjdm(ud)); //�������ؼ�������룬
      //��Ԥװ�ص���Ϣ��ò���д
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);

      //����processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);


      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27


      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);

      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Query");
  }

  /**
   * ɾ��
   * modified by qianchao 2005.10.26
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doDelete(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException
  {
    //��ֹrefresh
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null)
    {
      return forward;
    }
    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);
    LszsCxjksForm pf = (LszsCxjksForm) form;
    //��Ԥװ�ص���Ϣ��ò���д
    try
    {

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_DELETEACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //����processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);

      //���²�ѯ
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //����processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);
      //���淵��ֵ--------Shi Yanfeng 20031029-------

      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27

      request.setAttribute(mapping.getAttribute(), pf);
      pf.reset(mapping, request);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�����ɹ���");
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Delete");
  }

  /**
   * ���ؽɿ���¼��ҳ��
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doBack(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
      LszsJkslrForm pf = new LszsJkslrForm();
      pf.setActionType("Show");
      request.setAttribute("LszsJkslrForm", pf);
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Back");
  }

  /**
   * ��ӡ
   *
   * modified by qianchao 2005.10.27
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doPrint(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
      LszsCxjksForm pf1 = (LszsCxjksForm) form;

      //start modifying by qianchao 2005.10.27
      if (pf1.getJksType() == CodeConstant.PRINT_YPYS)
      {
        JksPrintForm pf = new JksPrintForm();
        pf.setHeadJkpzh(pf1.getHeadJkpzh());
        pf.setHeadJsjdm(pf1.getHeadJsjdm());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSZSLR); //������Դ
        pf.setActionType("Show");
        request.setAttribute("jksPrintForm", pf);
        return mapping.findForward("Print");
      }
      else
      {
        JksqdPrintForm pf = new JksqdPrintForm();
        pf.setH_jsjdm(pf1.getHeadJsjdm());
        pf.setH_sbbh(pf1.getHeadSbbh());
        pf.setJsjdm(pf1.getHeadJsjdm());
        pf.setSbbh(pf1.getHeadSbbh());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSZSLR); //������Դ
        pf.setActionType("Show");
        request.setAttribute("jksqdPrintForm", pf);
        return mapping.findForward("PrintSQD");
      }

      //end modifying by qianchao 2005.10.27

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

}
