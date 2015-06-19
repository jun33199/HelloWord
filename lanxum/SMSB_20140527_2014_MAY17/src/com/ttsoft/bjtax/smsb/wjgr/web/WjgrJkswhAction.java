/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

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
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.Debug;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrJkswhAction
    extends SmsbAction
{
  /**
   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
   * @param mapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨�ɿ�</font>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "��Ȼ�˸�˰�걨");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/zhsb/zhsbjkswh-000.htm");
  }

  /**
   * ��ʼʱ��ʾҳ��
   * ��ʼ���ɿ����б�
   *
   * modified by qianchao 2005.10.31
   *
   *
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doShow(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    try
    {
      WjgrJkswhActionForm form = (WjgrJkswhActionForm) actionForm; //��ʼ�������Ϣ
      //System.out.println("-----------"+form.getSbbh());
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(httpServletRequest);
      //�������ݰ�
      VOPackage vo = new VOPackage();
      //����
      vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
      //����processor
      vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
      //����actionForm
      //form.setJsjdm("06123456");
      vo.setData(form);

      //����userDate
      vo.setUserData(ud);
      form = (WjgrJkswhActionForm) ZhsbProxy.getInstance().process(vo);

      //��ȫ˰��˰Ŀ����

      Debug.out("form.getDataList().size()=" + form.getDataList().size());

      TranslateHelper.completeSzsmmc(form.getDataList(), httpServletRequest);
      //������������״̬����ֳ�2��list
      List dlist = TranslateHelper.splitMAPInto2(form.getDataList(), httpServletRequest);

      form.setDataList( (List) dlist.get(0));
      form.setNlwDataList( (List) dlist.get(1));

      
      
      Debug.out("������" + form.getDataList().size());      
      Debug.out("��������" + form.getNlwDataList().size());      

      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      form);
      return (new ActionForward(actionMapping.getInput()));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "���ɽɿ����б�ʧ��!");
    }
  }

  /** doPrint��ӡ�ɿ���
   * @param mapping  The ActionMapping used to select this instance
   * @param form  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   * */
  public ActionForward doPrint(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
    try
    {
      WjgrJkswhActionForm pf1 = (WjgrJkswhActionForm) form;
      //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
      if (pf1.getJksType() == CodeConstant.PRINT_YPYS)
      {
        JksPrintForm pf = new JksPrintForm();
        pf.setHeadJkpzh(pf1.getHeadJkpzh());
        pf.setHeadJsjdm(pf1.getJsjdm());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_ZRRLR); //������Դ
        pf.setActionType("Show");
        request.setAttribute("jksPrintForm", pf);
        return mapping.findForward("Print");
      }
      else
      {
        JksqdPrintForm pf = new JksqdPrintForm();

        Debug.out("pf1.getJsjdm()=" + pf1.getJsjdm());
        Debug.out("pf1.getSbbh()=" + pf1.getSbbh());

        pf.setH_jsjdm(pf1.getJsjdm());
        pf.setH_sbbh(pf1.getSbbh());
        pf.setJsjdm(pf1.getJsjdm());
        pf.setSbbh(pf1.getSbbh());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_ZRRLR); //������Դ
        pf.setActionType("Show");
        request.setAttribute("jksqdPrintForm", pf);
        return mapping.findForward("PrintSQD");
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /** doBack���ص���Ȼ���걨����
   * @param mapping  The ActionMapping used to select this instance
   * @param form  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   * */
  public ActionForward doBack(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��

      WjgrJkswhActionForm pf1 = (WjgrJkswhActionForm) form;
      return mapping.findForward("Back");

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "���ɽɿ����б�ʧ��!");
    }

  }

  /*
   * doCx,�����ɿ���
   * @param mapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   */
  public ActionForward doCx(ActionMapping actionMapping,
                            ActionForm actionForm,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //��ֹrefresh
      ActionForward forward = doHandleToken(actionMapping,
                                            httpServletRequest);
      if (forward != null)
      {
        return forward;
      }

      WjgrJkswhActionForm form = (WjgrJkswhActionForm) actionForm; //��ʼ�������Ϣ
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(httpServletRequest);
      //�������ݰ�
      VOPackage vo = new VOPackage();
      //����
      vo.setAction(CodeConstant.SMSB_CXJKSACTION);
      //����processor
      vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
      //����actionForm
      vo.setData(form);
      //����userDate
      vo.setUserData(ud);
      form = (WjgrJkswhActionForm) ZhsbProxy.
          getInstance().
          process(vo);

      form.setSbbh("");
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      form);

      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�����ɹ���");

      return this.doShow(actionMapping, form, httpServletRequest,
                         response);

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "���ɽɿ����б�ʧ��!");
    }

  }

}
