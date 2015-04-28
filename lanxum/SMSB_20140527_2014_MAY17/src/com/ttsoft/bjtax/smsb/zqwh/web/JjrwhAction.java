/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ڼ���ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class JjrwhAction
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
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>&gt;<font color=\"#6F8EA2\">�걨����</font>&gt;��������ά��&gt;</td>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "�ڼ���ά��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-002.htm");

    }

    /**
     * ��ʾ����
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        removeForm(actionMapping, httpServletRequest);
        //��ǰ��ActionForm
        JjrwhForm jf = (JjrwhForm) actionForm;
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(jf);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_JJRWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            jf = (JjrwhForm) ZhsbProxy.getInstance().process(vo);
            httpServletRequest.setAttribute("holiday", jf.getHolyArr());
            httpServletRequest.setAttribute("CYEAR", jf.getWhnf());
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), jf);
            //��ת
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ���洦��
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ֹrefresh
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm
        JjrwhForm jf = (JjrwhForm) actionForm;
        jf.setSaveHoliday(httpServletRequest.getParameterValues("holiday-id"));
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(jf);
        vo.setUserData(this.getUserData(httpServletRequest));
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_JJRWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            jf = (JjrwhForm) ZhsbProxy.getInstance().process(vo);
            httpServletRequest.setAttribute("holiday", jf.getHolyArr());
            httpServletRequest.setAttribute("CYEAR", jf.getWhnf());
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), jf);
            //��ʾ�ɹ���Ϣ
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            //��ת
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
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
    public ActionForward doReturn (ActionMapping mapping, ActionForm aFrom,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            removeForm(mapping, request);
            return mapping.findForward("Return");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ����������������
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doCreateCalendar (ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response)
        throws
        BaseException
    {
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        //��ǰ��ActionForm
        JjrwhForm jf = (JjrwhForm) form;
        String whnf = jf.getWhnf();
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_CREATECALENDAR);
        //�����������Data����,�������������ActionForm
        vo.setData(jf);
        vo.setUserData(this.getUserData(request));
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_JJRWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            jf = (JjrwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), jf);
            //������������ѯ����form
            ZqrlcxForm zqrlcxf = new ZqrlcxForm();
            zqrlcxf.setWhnf(whnf);
            zqrlcxf.setHeadZqlx("*");
            zqrlcxf.setSelectTypeRadio("1");
            request.setAttribute("zqrlcxForm", zqrlcxf);
            //��ת
            return mapping.findForward("CreateCalendar");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

}