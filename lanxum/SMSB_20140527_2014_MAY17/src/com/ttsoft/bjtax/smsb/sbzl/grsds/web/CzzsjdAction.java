/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨�� Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsjdAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm CzzsjdForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/czzsjd/czzsjd-000.htm");

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor��
     *         voPackage��Data��ΪCzzsjdForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsjdForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm---CzzsjdForm
        try
        {
            CzzsjdForm czzsjdForm = (CzzsjdForm) actionForm;
            //��ʼ�����ݴ�������
            userData = this.getUserData(httpServletRequest);
            czzsjdForm.setLrr(userData.getYhid());

            VOPackage vo = new VOPackage();
            //���ú�̨����Actionֵ---SHOWACTION
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            //�����������Data����,������CzzsjdForm
            vo.setData(czzsjdForm);
            vo.setUserData(userData);
            //����ProxyҪ���õ�processor����---CzzsjdProcessor
            vo.setProcessor(
                "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor");
            //����Proxy����ʼ��CzzsjdForm��ֵ
            czzsjdForm = (CzzsjdForm) SbzlProxy.getInstance().process(vo);
            //��ת
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            //��õ�ǰ��½���û�id����Ϊ¼����id
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            czzsjdForm);
            czzsjdForm.setLrr(userData.getYhid());
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor��
     *         voPackage��Data��ΪCzzsjdForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doQuery��������ȡҵ�����ݵĽ����
     * 5�����ݵõ���zsfs(���շ�ʽ)����ֵ��������תҳ���Լ�ҵ���쳣�����׳�
     * 6��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsjdForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *         ApplicationException ҵ���쳣�����׳�
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm---CzzsjdForm
        CzzsjdForm czzsjdForm = (CzzsjdForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        czzsjdForm.setLrr(userData.getYhid());
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ----QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,������CzzsjdForm
        vo.setData(czzsjdForm);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor");
        try
        {
            //����Proxy����ʼ��CzzsjdForm��ֵ
            czzsjdForm = (CzzsjdForm) SbzlProxy.getInstance().process(vo);

            Debug.out("���ﴫ�ݵ���DataList�е�����" + czzsjdForm.getDataList());
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            czzsjdForm);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            czzsjdForm.reset(actionMapping, httpServletRequest);
            czzsjdForm.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor��
     *         voPackage��Data��ΪCzzsjdForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
     * 	����SbzlProxy��process������
     * 4������doSave��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsjdForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *
     */
    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm---------CzzsjdForm
        CzzsjdForm czzsjdForm = (CzzsjdForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        czzsjdForm.setLrr(userData.getYhid());
        //����columns�е��ֶ���ȡ����Ҫǰ̨�б����ݸ���czzsjdForm��DataList�У�
        String columns[] = czzsjdForm.getColumns();
        czzsjdForm.setDataList(getValuesToList(httpServletRequest, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ-----SAVEACTION
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�����������Data����,�������������CzzsjdForm
        vo.setData(czzsjdForm);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor");
        try
        {
            //����Proxy����ʼ��CzzsjdForm��ֵ
            SbzlProxy.getInstance().process(vo);
            //����ɹ�,��ת
            czzsjdForm.reset(actionMapping, httpServletRequest);
            czzsjdForm.setJsjdm("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            czzsjdForm.reset(actionMapping, httpServletRequest);
            czzsjdForm.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor��
     *         voPackage��Data��ΪCzzsjdForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doDelete��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsjdForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *
     */
    public ActionForward doDelete (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm---------CzzsjdForm
        CzzsjdForm czzsjdForm = (CzzsjdForm) actionForm;
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ-----SAVEACTION
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //�����������Data����,�������������CzzsjdForm
        vo.setData(czzsjdForm);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsjdProcessor");
        try
        {
            //����Proxy����ʼ��CzzsjdForm��ֵ
            SbzlProxy.getInstance().process(vo);
            czzsjdForm.reset(actionMapping, httpServletRequest);
            czzsjdForm.setJsjdm("");
            //����ɹ�,��ת
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            czzsjdForm.reset(actionMapping, httpServletRequest);
            czzsjdForm.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}
