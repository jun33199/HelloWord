/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import com.ttsoft.bjtax.sfgl.common.action.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��_������˰</p>
 * <p>Description: һƱһ˰�ɿ���ά����</p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsjksypysAction
    extends SfglAction
{
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨�ɿ�</font>");
        //������˰
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��˰����˰��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbypys-000.htm");
    }

    /**
     * ��ѯһƱһ˰�ɿ���
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);

        //������˰
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.pgbs.processor.ZhsbPgbsjksypysProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
        	//������˰
            form = (ZhsbPgbsjksypysActionForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�����Ϣʧ��!");
        }
    }

    /**
     * ����һƱһ˰�Ľɿ���ά��
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	//������˰
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.pgbs.processor.ZhsbPgbsjksypysProcessor");
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        vo.setUserData(ud);
        vo.setData(form);
        try
        {
        	//������˰
            form = (ZhsbPgbsjksypysActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�����Ϣʧ��!");
        }
        if (form.getForward().equals("Back"))
        {
        	//������˰
        	ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
            return actionMapping.findForward("Update");
        }

        //������˰
        ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        httpServletRequest.setAttribute("zhsbjkswhActionForm", forwardForm);
        return actionMapping.findForward("Update");

    }

    /**
     * ����һƱһ˰�Ľɿ���ά��
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	//������˰
        ZhsbPgbsjksypysActionForm form = (ZhsbPgbsjksypysActionForm) actionForm;
        if (form.getForward().equals("Back"))
        {
        	//������˰
            ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            //�����걨��ű�־
            if (form.getPresbbh() == null || !form.getPresbbh().equals("1"))
            {
                forwardForm.setSbbh("");
                form.setSbbh("");
            }
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            //������˰
            httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", forwardForm);
            return actionMapping.findForward("Back");
        }

        //������˰
        ZhsbPgbsjkswhActionForm forwardForm = new ZhsbPgbsjkswhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        //������˰
        httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", forwardForm);
        return actionMapping.findForward("Back");

    }

}
