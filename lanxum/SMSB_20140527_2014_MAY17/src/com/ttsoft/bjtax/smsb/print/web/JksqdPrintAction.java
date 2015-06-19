/*
 * <p>Title:������˰�г�����֧��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�Ƽ��ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ��ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ɿ����뵥��ӡ</p>
 * 
 * modified by qianchao 2006.2.12
 * �޸Ľɿ����뵥Ϊ���ӽɿ�ר�ýɿ��顣
 * 
 * @author ����������������
 * @version 1.1
 */
public class JksqdPrintAction
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
                         "<font color=\"#7C9AAB\">�걨����</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                         "��ӡ���ӽɿ�ר�ýɿ���");

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
      BaseException
  {

    JksqdPrintForm pf = (JksqdPrintForm) form;

    try
    {
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(request);
      pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.PRINT_JKSQDPRINT_PROCESSOR);

      //����processor
      pf = (JksqdPrintForm) ZhsbProxy.getInstance().process(vo);
      //���淵��ֵ--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }
}
