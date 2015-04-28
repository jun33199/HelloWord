/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰������ Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmlrcxAction
    extends SmsbAction
{
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm aform,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>¼��ӡ��˰�����������");
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
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsgmlrcxForm yForm = (YhsgmlrcxForm) aForm;
            UserData userData = this.getUserData(request);
            //¼����
            yForm.setLrr(userData.getYhid());
            //¼����ʼ����
            yForm.setLrqsrq(SfDateUtil.getDate());
            //¼���������
            yForm.setLrjzrq(SfDateUtil.getDate());
            yForm.setDataList(new ArrayList());
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm aForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) aForm;

        VOPackage vo = new VOPackage();

        try
        {
            //��ѯ
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
            vo.setData(yForm);
            vo.setUserData(this.getUserData(request));
            yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            yForm.setPgNum(0);
            yForm.setPgSum(0);
            //����form
            request.setAttribute(mapping.getAttribute(), yForm);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ɾ������
     * @param mapping The ActionMapping used to select this instance
     * @param from The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doDelete (ActionMapping mapping, ActionForm from,
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
        VOPackage vo = new VOPackage();
        try
        {
            YhsgmlrcxForm yForm = (YhsgmlrcxForm) from;
            System.out.println(request.getParameterValues("deleteCheckbox"));
            //�õ�Ӧɾ��������
            yForm.setDeleteCheckbox(request.getParameterValues("deleteCheckbox"));
            if (yForm.getDeleteCheckbox() != null)
            {
                for (int i = 0; i < yForm.getDeleteCheckbox().length; i++)
                {
                }
                vo.setAction(CodeConstant.SMSB_DELETEACTION);
                vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
                vo.setData(yForm);
                vo.setUserData(this.getUserData(request));
                yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            }
            yForm.setFromDelete(true);
            //����form
            request.setAttribute(mapping.getAttribute(), yForm);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
            return mapping.findForward("Delete");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ȫ��ɾ������
     * @param mapping The ActionMapping used to select this instance
     * @param from The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doDeleteAll (ActionMapping mapping, ActionForm from,
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

        VOPackage vo = new VOPackage();

        YhsgmlrcxForm yForm = (YhsgmlrcxForm) from;
        vo.setAction(CodeConstant.SMSB_DELETEALLACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));
        try
        {
            yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), yForm);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
            return mapping.findForward("DeleteAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * �鿴����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doView (ActionMapping mapping,
                                 ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            return mapping.findForward("View");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

}