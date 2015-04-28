/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;//import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.GtgshHelper;
import com.ttsoft.common.model.UserData;
//import java.util.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�Ӫҵ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;���幤�̻�Ӫҵ�����걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��������˰�걨");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/gtgshyysr/gtgshyysr-000.htm");

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
        //��ǰ��ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //��ת
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
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm
        Debug.out("Query");
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setUserData(userData);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Query------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doUpdateQuery (ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ǰ��ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        form.setJsjdm(form.getTempJsjdm());
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        //vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setAction(CodeConstant.SMSB_CXWSZACTION); //ʹ�ó�����˰֤��action id
        vo.setUserData(userData);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //Debug.out("ttt="+form.getDataList().size());
            return actionMapping.findForward("UpdateQuery");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2������GtgshyysrForm��doSave����ȡ��ȡҵ�����ݵĲ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
     * 	����SbzlProxy��process������
     * 4������doSave��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
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

        //��ǰ��ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        //��ǰ̨�б�����ݸ���ActionForm��DataList
        String columns[] = form.getColumns();
        form.setDataList(getValuesToList(httpServletRequest, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            form.setTempSbrq("");
            //����ɹ�,��ת
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Save------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doDelete��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
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

        //��ǰ��ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            //��ת
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Delete------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��GtgshyysrForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor��
     *         voPackage��Data��ΪGtgshyysrForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doList��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param mapping struts.action.ActionMapping
     * @param form GtgshyysrForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doList (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshyysrForm pf1 = (GtgshyysrForm) form;
            GtgshyysrCxForm pf = new GtgshyysrCxForm();
            pf.setHeadJsjdm(pf1.getJsjdm());
            pf.setTempJsjdm(pf1.getJsjdm());
            pf.setActionType("Show");
            request.setAttribute("gtgshyysrCxForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("List");
    }

}