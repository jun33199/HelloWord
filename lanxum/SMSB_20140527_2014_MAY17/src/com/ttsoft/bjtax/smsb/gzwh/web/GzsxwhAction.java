/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.util.ArrayList;
import java.util.List;
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
 * <p>Description: ��֪����ά��</p>
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */
public class GzsxwhAction extends SmsbAction
{
    /**
     * ���ݼ����󣨰���Form��UserData����
     */
    private VOPackage vo = new VOPackage();

    /**
     * �û���Ϣ����
     */
    UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param response The HTTP response we are creating
     */

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>&gt;<font color=\"#6F8EA2\">�걨����</font>&gt;��֪����ά��&gt;</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��֪����ά��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/gzwh/gzwh-000.htm");
    }

    /**
     * ��ʾ����
     * @param mapping The ActionMapping used to select this instance
     * @param aFrom The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping, ActionForm aFrom,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //�������
            removeForm(mapping, request);

            GzsxwhForm gf = (GzsxwhForm) aFrom;
            //�������
            gf.setChooseTypeRadio("1");
            gf.setJsjdm("");
            gf.setNsrmc("");
            gf.setQylx("");
            gf.setGzlx("");
            gf.setDqjs("");
            gf.setHylb("");
            gf.setJhfs("");
            gf.setGzjzrq("");

            //�õ����еĵ�������
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
            vo.setData(gf);
            vo.setUserData(this.getUserData(request));
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);

            //��֪��ʼ����==������ϵͳ����
            SfDateUtil sfdateUtil = new SfDateUtil();
            gf.setGzqsrq(sfdateUtil.getDate());
            gf.setDataList(new ArrayList());
            
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            
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
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        List phList = new ArrayList();
        VOPackage vo = new VOPackage();

        try
        {
            if (gf.getJsjdm() != null && !gf.getJsjdm().equals("*"))
            {
                //ȡ����
                vo.setAction(CodeConstant.SMSB_READERACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                vo.setUserData(this.getUserData(request));
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            }
        }
        catch (Exception e)
        {
            gf.setNsrmc("");
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        try
        {
            //��ѯ
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
            vo.setData(gf);
            vo.setUserData(this.getUserData(request));
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            gf.setPgNum(0);
            gf.setPgSum(0);
            try
            {
                //�õ����еĵ�������
                vo.setAction(CodeConstant.SMSB_SHOWACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                vo.setUserData(this.getUserData(request));
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
                phList = gf.getPhList();
            }
            catch (Exception ex)
            {
            }

            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ȡ���ƴ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doGetNsrmc (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException {
        GzsxwhForm gf = (GzsxwhForm) form;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_READERACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        vo.setUserData(this.getUserData(request));
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("GetNsrmc");
        }
        catch (Exception e) {
            gf.setNsrmc("");
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * @return the element previously at the specified position.
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
        try
        {
            GzsxwhForm gf = (GzsxwhForm) from;
            //�õ�Ӧɾ��������
            gf.setDeleteCheckbox(request.getParameterValues("deleteCheckbox"));
            if (gf.getDeleteCheckbox() != null)
            {
                vo.setAction(CodeConstant.SMSB_DELETEACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            }
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * @return the element previously at the specified position.
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

        GzsxwhForm gf = (GzsxwhForm) from;
        vo.setAction(CodeConstant.SMSB_DELETEALLACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        vo.setUserData(this.getUserData(request));
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * ��������
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doAdd (ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        //���ݳ�ʼ����������
        //��֪��ʼ����==������ϵͳ����
        SfDateUtil sfdateUtil = new SfDateUtil();
        gf.setMxGzqsrq(sfdateUtil.getDate());
        gf.setMxJsjdm(null);
        gf.setMxNsrmc(null);
        gf.setMxQylx(null);
        gf.setMxGzlx(null);
        gf.setMxGzjzrq(null);
        gf.setMxGzsxxxxx(null);

        gf.setMxJhfs(null); //��Ϸ�ʽ���룩
        gf.setMxQylx(null);
        gf.setMxHylb(null);
        gf.setMxDqjs(null);
        gf.setMxChooseTypeRadio("1");
        return mapping.findForward("Add");
    }

    /**
     * �޸Ĵ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doModify (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("Modify");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
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
            GzsxwhForm gf = (GzsxwhForm) aFrom;
            gf.reset(mapping, request);
            //�ͷ�session�ռ�
            request.getSession().removeAttribute("DataList");
            return mapping.findForward("Return");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }
    public ActionForward docometomain (ActionMapping mapping, ActionForm aFrom,
            HttpServletRequest request,
            HttpServletResponse response)
throws
BaseException
{
try
{
return mapping.findForward("cxmian");

}
catch (Exception e)
{
e.printStackTrace();
throw ExceptionUtil.getBaseException(e);
}

}

}
