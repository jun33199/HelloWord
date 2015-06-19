/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������۵�λӡ��˰����������� Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsxshzAction
    extends SmsbAction
{
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>���ܴ��۵�λ�������");
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
            YhsxshzForm form = (YhsxshzForm) aForm;
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���ش���
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doTurnback (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//���ص������
            YhsxsdrForm yForm = new YhsxsdrForm();
            yForm.reset(mapping, request);
            return mapping.findForward("Turnback");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��������
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//���������ɿ���
            YhsxshzForm form = (YhsxshzForm) aForm;
            YhsxshzcxForm yForm = new YhsxshzcxForm();
            yForm.reset(mapping, request);
            yForm.setDsjsjdm(form.getDsjsjdm());
            request.setAttribute("yhsxshzcxForm", yForm);
            return mapping.findForward("Cxjks");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ӡ����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            JksPrintForm pf = new JksPrintForm();
            YhsxshzForm yForm = (YhsxshzForm) form;
            pf.setHeadJkpzh(yForm.getJkpzh());
            pf.setHeadJsjdm(yForm.getDsjsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //������Դ:ӡ��˰����
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Print");
    }

}