/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.SmExcelParser;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������֪����</p>
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */
public class XzgzsxAction
    extends SmsbAction
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
        
        System.out.println("XzgzsxAction.initialRequest()");
        
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
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	System.out.println("XzgzsxAction.doShow()");
    	
        try
        {
            GzsxwhForm gf = (GzsxwhForm) form;
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
     * ���´���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping,
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

        GzsxwhForm gf = (GzsxwhForm) form;

        userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.GZWH_XZGZSX_PROCESSOR);
        vo.setData(gf);
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            gf.setMxChooseTypeRadio(gf.getMxChooseTypeRadioHidden());
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���³ɹ���");
            return mapping.findForward("Update");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ���洦��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
    	System.out.println("XzgzsxAction.doSave()");
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        GzsxwhForm gf = (GzsxwhForm) form;

        try
        {

        	//System.out.println(gf.getMxJsjdm()+"----"+gf.getMxJsjdm().equals("*")+"---"+gf.getMxJsjdm().indexOf(","));
            if (gf.getMxJsjdm() != null && !gf.getMxJsjdm().equals("*")
                && gf.getMxJsjdm().indexOf(",") < 0)
            {
                //���Ǽǽӿ�
                try
                {
                    if (gf.getMxJsjdm() != null
                        && (gf.getMxJsjdm().trim()).length() != 0)
                    {
                        com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new
                            com.ttsoft.
                            bjtax.
                            dj.proxy.ServiceProxy();
                        HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm());
                        SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                        gf.setMxNsrmc(swdjjbsj.getNsrmc());
                    }
                }
                catch (Exception e)
                { 
                	e.printStackTrace();
                    throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ!");
                }
                //�ж��Ƿ��в���Ȩ��
                try
                {
                    if (gf.getMxJsjdm() != null
                        && (gf.getMxJsjdm().trim()).length() != 0)
                    {
                        com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new
                            com.ttsoft.
                            bjtax.
                            dj.proxy.ServiceProxy();
                        HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm(),
                            this.getUserData(request));
                    }
                }
                catch (Exception e)
                {
                    gf.setNsrmc("");
                    e.printStackTrace();
                    throw new ApplicationException("��û���㹻��Ȩ�޴���˼�������룡");
                }
            }
        }
        catch (Exception e)
        {
            gf.setMxNsrmc("");
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        try
        {
            userData = this.getUserData(request);
            vo.setUserData(this.getUserData(request));

            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setProcessor(CodeConstant.GZWH_XZGZSX_PROCESSOR);
            vo.setData(gf);

            if (gf.getMxChooseTypeRadioHidden().equals("3"))
            {
                //��������뵼��
                List list = this.getJsjdmFromExcel(gf);
                gf.setJsjdmList( (ArrayList) list);
                gf.setExcelFile(null);
            }

            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //���ݳ�ʼ��������ɹ���
            //��֪��ʼ����==������ϵͳ����
            SfDateUtil sfdateUtil = new SfDateUtil();
            gf.setMxGzqsrq(sfdateUtil.getDate());
            gf.setMxJsjdm(null);
            gf.setMxNsrmc(null);
            gf.setMxQylx(null);
            gf.setMxGzlx(null);
            gf.setMxGzjzrq(null);
            gf.setMxGzsxxxxx(null);
            gf.setMxChooseTypeRadio("1");

            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            return mapping.findForward("Save");

        }
        catch (Exception e)
        {
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
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;

        //���Ǽǽӿ�
        try
        {
            try
            {
                if (gf.getMxJsjdm() != null
                    && (gf.getMxJsjdm().trim()).length() != 0)
                {
                    com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.
                        ttsoft.bjtax.
                        dj.proxy.ServiceProxy();
                    HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm());
                    SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                    gf.setMxNsrmc(swdjjbsj.getNsrmc());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ!");
            }
            //�ж��Ƿ��в���Ȩ��
            try
            {
                if (gf.getMxJsjdm() != null
                    && (gf.getMxJsjdm().trim()).length() != 0)
                {
                    com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.
                        ttsoft.bjtax.
                        dj.proxy.ServiceProxy();
                    HashMap ghdwMap = djProxy.getDjInfo(gf.getMxJsjdm(),
                        this.getUserData(request));
                }
            }
            catch (Exception e)
            {
                gf.setNsrmc("");
                e.printStackTrace();
                throw new ApplicationException("��û���㹻��Ȩ�޴���˼�������룡");
            }
        }
        catch (Exception e)
        {
            gf.setMxNsrmc("");
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        return mapping.findForward("GetNsrmc");

    }

    /**
     * ���ش���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doReturn (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GzsxwhForm gf = (GzsxwhForm) form;
            //����form
            request.setAttribute(mapping.getAttribute(), gf);
            if (gf.getSaveType().equals("Update"))
            {
                return mapping.findForward("ReturnSelect");
            }
            else
            {
                return mapping.findForward("Return");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ����ָ����excel�ļ��õ���Ӧ�ļ��������
     * excel�ļ���ֻ����һ�м��������
     * @param form The optional ActionForm bean for this request (if any)
     * @return �����ļ���List
     * @throws BaseException
     */
    private List getJsjdmFromExcel (ActionForm form)
        throws
        BaseException
    {
        SmExcelParser ep = new SmExcelParser();
        GzsxwhForm gf = (GzsxwhForm) form;
        try
        {
            return ep.getColAt(gf.getExcelFile().getInputStream(), 0, 0);
        }
        catch (IOException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}