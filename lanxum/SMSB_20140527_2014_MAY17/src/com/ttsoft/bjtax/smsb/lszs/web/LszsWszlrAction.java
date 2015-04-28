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
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.WszPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ˰������˰֤¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsWszlrAction
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
                             "���ֽɿ���¼��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/wszlr-000.htm");
        //��ʼ��ҳ����ϸʹ��js����
        try
        {
            LszsWszlrForm pf = (LszsWszlrForm) form;

            //���ó�ʼ����js����
            //���ó�ʼ�������Ĳ���
            ZhsbActionForm temp = new ZhsbActionForm();
            //���ü��������
            UserData ud = this.getUserData(request);
            temp.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��ñ����ļ��������
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);

            pf.setScriptStr(zhsb.getScriptStr());
        }
        catch (Exception ex)
        {
        }

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

        LszsWszlrForm pf = (LszsWszlrForm) form;
        pf.reset(mapping, request);

        try
        {
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            }
            pf.setZhdm(ud.getXtsbm1()); //�����ʻ�����
            pf.setPzzldm(CodeConstant.SMSB_PZZLDM); //Ʊ֤�������
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //��ñ����ļ��������

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //����processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
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

        LszsWszlrForm pf = (LszsWszlrForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            //
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //����processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
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
     * ����
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Save";
        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsWszlrForm pf = (LszsWszlrForm) form;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);

            String columns[] = pf.getColumns();
            pf.setDataList(getValuesToList(request, columns));

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //����processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
                /********************Modified by lufeng 2003-11-11********************/
            //����ɹ���򿪴�ӡҳ��
            WszPrintForm wszPF = new WszPrintForm();
            wszPF.setActionType("Show");
            wszPF.setFromPage("LszsBack");
            wszPF.setNdzb(pf.getNdzb()); //����ֱ�
            wszPF.setHeadWszh(pf.getWszh()); //��˰֤��
            request.setAttribute("wszPrintForm", wszPF);
            forwardFlag = "Print";
        }
        catch (Exception ex)
        {
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * ������˰֤
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDismiss (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            //GtgshWszlrForm pf = (GtgshWszlrForm) form;
            LszsCxwszForm pf = new LszsCxwszForm();
            //pf1.setNsrjsjdm(pf.getNsrjsjdm());
            pf.setActionType("Show");
            request.setAttribute("lszsCxwszForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Dismiss");
    }

    /**
     * ȡ�ð���˰��˰Ŀlist��Ӫҵ˰����˰list��list
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @return the element previously at the specified position.
     * @exception Exception
     */
    private ActionForm getInitList (ActionForm actionForm)
        throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        //�����ۺ��걨��processorȡ��ǰ̨��ʾ�õ�js����
        vo.setProcessor(CodeConstant.GTGSH_WSZLR_PROCESSOR);
        vo.setData(actionForm);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
        }
    }

    /**
     * ��ӡ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
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
            WszPrintForm pf = new WszPrintForm();
            LszsWszlrForm pf1 = (LszsWszlrForm) form;
            pf.setActionType("Show");
            pf.setFromPage("LszsBack");
            pf.setHeadWszh(pf1.getWszh()); //��˰֤��(pf1.getWszh());
            request.setAttribute("wszPrintForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Print");
    }

}
