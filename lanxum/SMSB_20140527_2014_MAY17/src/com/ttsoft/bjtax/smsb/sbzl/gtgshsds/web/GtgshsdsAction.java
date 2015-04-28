/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug; //import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.GtgshsdsHelper;
import com.ttsoft.common.model.UserData;
//import java.util.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�����˰</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsAction
    extends SmsbAction
{
    /**
     * ����Ա��Ϣ
     */
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�������ո��幤�̻�����˰��ȣ��·ݣ��걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�������ո��幤�̻�����˰��ȣ��·ݣ��걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/gtgshsds/gtgshsds-000.htm");

    }

    /**
     * ��ʼ��ҳ������
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
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
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
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
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (GtgshsdsForm) SbzlProxy.getInstance().process(vo);
            form.setDataList(GtgshsdsHelper.getShowList());
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
     * ��ѯ���걨����
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
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
        //��ǰ��ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (GtgshsdsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //��ת
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����걨����
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
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

        //��ǰ��ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        //��ǰ̨�б�����ݸ���ActionForm��DataList
        String columns[] = form.getColumns();
        form.setDataList(getValuesToList(httpServletRequest, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setUserData(userData);
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            //��ת
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");

            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ���걨����
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
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
        //��ǰ��ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        //��ǰ̨�б�����ݸ���ActionForm��DataList
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
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            //��ת
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}