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
import com.ttsoft.bjtax.smsb.proxy.ZqrlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ˰�����������Ͷ��չ�ϵά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class SzzqdzAction
    extends SmsbAction
{

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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>&gt;<font color=\"#6F8EA2\">�걨����</font>&gt;��������ά��&gt;</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "˰�����������Ͷ��չ�ϵά��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
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
        SzzqdzForm sf = (SzzqdzForm) actionForm;
        //��ʱ���
        sf.setTempNd(sf.getHeadNd());
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(sf);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_SZZQDZ_PROCESSOR);
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            sf = (SzzqdzForm) ZqrlProxy.getInstance().process(vo);
            //����form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), sf);

        }
        catch (Exception ex)
        {
//      sf.setDataList(new ArrayList());
//      //����form
//      httpServletRequest.setAttribute(actionMapping.getAttribute(),sf);
            //���淵��ֵ
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        //��ת
        return actionMapping.findForward("Query");
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
    public ActionForward doDelete (ActionMapping mapping, ActionForm form,
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

        SzzqdzForm sf = (SzzqdzForm) form;
        //����Ҫɾ������������
        sf.setDeleteCheckBox(request.getParameterValues("deleteCheckBox"));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setProcessor(CodeConstant.ZQWH_SZZQDZ_PROCESSOR);
        vo.setData(sf);
        try
        {
            sf = (SzzqdzForm) ZqrlProxy.getInstance().process(vo);
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
        SzzqdzForm sf = (SzzqdzForm) form;
        //��ǰ̨�б�����ݸ���ActionForm��DataList
        String columns[] = sf.getColumns();
        sf.setDataList(getValuesToList(request, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_SZZQDZ_PROCESSOR);
        //�����������Data����ActionForm
        vo.setData(sf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            sf = (SzzqdzForm) ZqrlProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), sf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                 "����ɹ������ɹ�����˰������������");
            //����ɹ�,��ת
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //��ӡ������Ϣ
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ������ձ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doImport (ActionMapping mapping,
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
        SzzqdzForm sf = (SzzqdzForm) form;
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_LOADACTION);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(CodeConstant.ZQWH_SZZQDZ_PROCESSOR);
        //�����������Data����ActionForm
        vo.setData(sf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            sf = (SzzqdzForm) ZqrlProxy.getInstance().process(vo);
            //����form
            request.setAttribute(mapping.getAttribute(), sf);
            //��ʾ�ɹ���Ϣ
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                 "�������ݵ���ɹ���ͬʱ�������������ɹ���");
            //����ɹ�,��ת
            return mapping.findForward("Import");
        }
        catch (Exception ex)
        {
            //��ӡ������Ϣ
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

}