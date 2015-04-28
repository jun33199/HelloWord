/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ��ҵ��������ָ��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QyjbcwzbAction
    extends SmsbAction
{
    /**
     * ����Ա����
     */
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ��������ָ�������</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��ҵ��������ָ�������");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/qyjbcwzb/qyjbcwzb-000.htm");
    }

    /**
     * ��ʼ��ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //����processor
            sbForm = (QyjbcwzbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sbForm);
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ��ѯ���걨����
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //����processor
            sbForm = (QyjbcwzbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sbForm);
        }
        catch (Exception ex)
        {
            sbForm.reset(mapping, request);
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Query");
    }

    /**
     * �������걨����
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;
        sbForm.setDataList(SfRequestUtil.getValuesToList(request,
            sbForm.getColumns()));
        userData = this.getUserData(request);
        sbForm.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //����processor
            SbzlProxy.getInstance().process(vo);
            sbForm.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ�����걨����
     * @param mapping struts.action.ActionMapping
     * @param form QyjbcwzbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        QyjbcwzbForm sbForm = (QyjbcwzbForm) form;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(sbForm);
        vo.setProcessor(CodeConstant.SBZL_QYJBCWZB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //����processor
            SbzlProxy.getInstance().process(vo);
            sbForm.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
            return mapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}