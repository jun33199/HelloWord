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
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸά����ѯ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxcxAction
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
                             "����������ϸ��Ϣά��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-005.htm");

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
        ZqrlmxcxForm sf = (ZqrlmxcxForm) actionForm;
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(sf);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_MXCX_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            sf = (ZqrlmxcxForm) ZhsbProxy.getInstance().process(vo);
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), sf);
            //��ת
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //���淵��ֵ
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ������
     * @param mapping The ActionMapping used to select this instance
     * @param from form The optional ActionForm bean for this request (if any)
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

        ZqrlmxcxForm sf = (ZqrlmxcxForm) from;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setProcessor(CodeConstant.ZQWH_MXCX_PROCESSOR);
        vo.setData(sf);
        try
        {
            sf = (ZqrlmxcxForm) ZhsbProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), sf);
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
     * ɾ������
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        ZqrlmxcxForm pf1 = (ZqrlmxcxForm) form;
        ZqrlmxwhForm pf = new ZqrlmxwhForm();
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            pf.setActionType("Query");
            pf.setNd(pf1.getTempNd()); //���
            pf.setDjzclxdm(pf1.getTempDjzclx()); //�Ǽ�ע�����ʹ���
            pf.setSzsmdm(pf1.getTempSz()); //˰��˰Ŀ����
            pf.setZqlxdm(pf1.getTempZqlx()); //�������ʹ���
            pf.setZqqsrq(pf1.getTempZqqsrq()); //������ʼ����
            //�����ѯ����
            pf.setQueryDjzclx(pf1.getHeadDjzclx());
            pf.setQueryNd(pf1.getHeadNd());
            pf.setQuerySz(pf1.getHeadSz());
            pf.setQueryZqlx(pf1.getHeadZqlx());
            pf.setQueryMonth(pf1.getHeadMonth());
            request.setAttribute("zqrlmxwhForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Update");

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
        return mapping.findForward("Save");
    }
}
