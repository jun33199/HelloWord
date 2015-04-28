/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshWszlrForm;
import com.ttsoft.bjtax.smsb.lszs.web.LszsWszlrForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��˰֤��ӡ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class WszPrintAction
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
                             "<font color=\"#7C9AAB\">�걨����</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "��ӡ��˰֤");

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

        WszPrintForm pf = (WszPrintForm) form;

        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud));
            pf.setZhdm(ud.getXtsbm1()); //�����ʻ�����
            pf.setPzzldm(CodeConstant.SMSB_PZZLDM); //Ʊ֤�������
            pf.setHeadWszh(pf.getHeadWszh()); //������˰֤��

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);

            //����processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
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

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            //��Ԥװ�ص���Ϣ��ò���д
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);

            //����processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
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
        return mapping.findForward("Delete");
    }

    /**
     * ��ӡ�ɹ������ô�ӡ��־
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSuccess (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Success";
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            //��Ԥװ�ص���Ϣ��ò���д
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_PRINTACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);
            //����processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

            //�ɹ��󷵻�¼��ҳ��
            if ( (pf.getFromPage()).indexOf("gsh") > 0)
            {
                GtgshWszlrForm lrForm = new GtgshWszlrForm();
                forwardFlag = "GtgshBack";
                lrForm.setActionType("Show");
                request.setAttribute("gtgshWszlrForm", lrForm);
            }
            else
            {
                LszsWszlrForm lrForm = new LszsWszlrForm();
                forwardFlag = "LszsBack";
                lrForm.setActionType("Show");
                request.setAttribute("lszsWszlrForm", lrForm);
            }
        }
        catch (Exception ex)
        {
            forwardFlag = "Success";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * ȡ�Ŵ�ӡ������ԭ��˰֤���룬��ȡ�µ���˰֤����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doReprint (ActionMapping mapping,
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

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            //��Ԥװ�ص���Ϣ��ò���д
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_REPRINTACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);
            //����processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
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
     * ���ظ��幤�̻�����˰֤¼�����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshBack (ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshWszlrForm pf = new GtgshWszlrForm();
            WszPrintForm pf1 = (WszPrintForm) form;
            pf.setActionType("Show");
            request.setAttribute("gtgshWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshBack");
    }

    /**
     * ������ɢ˰���յ���˰֤¼�����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsBack (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            LszsWszlrForm pf = new LszsWszlrForm();
            WszPrintForm pf1 = (WszPrintForm) form;
            pf.setActionType("Show");
            request.setAttribute("lszsWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsBack");
    }

}
