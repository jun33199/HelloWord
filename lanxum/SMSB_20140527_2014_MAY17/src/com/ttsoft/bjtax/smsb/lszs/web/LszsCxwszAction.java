/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ˰���ճ�����˰֤</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsCxwszAction
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
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">��ɢ˰����</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "�������ֽɿ���");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/wszcx-000.htm");
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
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        LszsCxwszForm pf = (LszsCxwszForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            pf.setHeadLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            pf.setHeadZhdm(ud.getXtsbm1());
        }
        catch (Exception ex)
        {
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
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(request);

        try
        {
            //��Ԥװ�ص���Ϣ��ò���д
            LszsCxwszForm pf = (LszsCxwszForm) form;

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //����processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
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
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(request);
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsCxwszForm pf = (LszsCxwszForm) form;
        //��Ԥװ�ص���Ϣ��ò���д
        try
        {
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //����processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
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
     * ���ô�ӡ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(request);
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsCxwszForm pf = (LszsCxwszForm) form;
        //��Ԥװ�ص���Ϣ��ò���д
        try
        {
            //ȡ��dataList��ֵ
            String columns[] = pf.getColumns();
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_UPDATEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //����processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ô�ӡ��ǳɹ���");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Update");
    }

    /**
     * ������˰֤¼��ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doBack (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            LszsWszlrForm pf = new LszsWszlrForm();
            pf.setActionType("Show");
            request.setAttribute("lszsWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Back");
    }

}
