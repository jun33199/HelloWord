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
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��������ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqlxwhAction
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
                             "��������ά��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-003.htm");

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
        try
        {
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
     * ��ѯ����
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm
        ZqlxwhForm zf = (ZqlxwhForm) actionForm;
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        zf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(zf);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), zf);
            //��ת
            return actionMapping.findForward("Query");
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
     * ͣ�ô���
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

        ZqlxwhForm zf = (ZqlxwhForm) form;
        zf.setTyCheckbox(request.getParameterValues("tyCheckbox"));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        vo.setData(zf);
        try
        {
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), zf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ͣ�óɹ���");
            //�����������ʹ����
            com.ttsoft.common.util.HttpUtil.reloadCodes(request);

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

        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm
        ZqlxwhForm zf = (ZqlxwhForm) form;
        //��ǰ̨�б�����ݸ���ActionForm��DataList
        String columns[] = zf.getColumns();
        zf.setDataList(getValuesToList(request, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        //�����������Data����ActionForm
        vo.setData(zf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), zf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            //�����������ʹ����
            com.ttsoft.common.util.HttpUtil.reloadCodes(request);
            //����ɹ�,��ת
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //���ü���ģ��
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(zf);
            vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
            try
            {
                zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
                request.setAttribute(mapping.getAttribute(), zf);
            }
            catch (Exception ex2)
            {
                ex2.printStackTrace();
                //ϵͳ��׽�쳣�������쳣�����׳�
                throw ExceptionUtil.getBaseException(ex);
            }

            //��ӡ������Ϣ
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ������������
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
        ZqlxwhForm zf = (ZqlxwhForm) form;
        String whnf = zf.getWhnf();
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_CREATECALENDAR);
        //�����������Data����,�������������ActionForm
        vo.setData(zf);
        vo.setUserData(this.getUserData(request));
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), zf);
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

    /**
     * �޸Ĵ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doEdit (ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ZqlxwhForm pf1 = (ZqlxwhForm) form;
        ZqlxmxwhForm pf = new ZqlxmxwhForm();
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            pf.setActionType("Query");
            pf.setHeadZqlxdm(pf1.getTempZqlxdm()); //���
            request.setAttribute("zqlxmxwhForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Edit");

    }

}