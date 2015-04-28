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
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨���� Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsnbAction
    extends SmsbAction
{

    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm CzzsnbForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/czzsnd/czzsnd-000.htm");

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsnbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	 com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor��
     *         voPackage��Data��ΪCzzsnbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsnbForm
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
        CzzsnbForm form = (CzzsnbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());

        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (CzzsnbForm) SbzlProxy.getInstance().process(vo);
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
     * 1��ȡ��CzzsnbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	 com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor��
     *         voPackage��Data��ΪCzzsnbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsnbForm
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
        CzzsnbForm form = (CzzsnbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());

        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //�����������Data����,�������������ActionForm
        vo.setUserData(userData);
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (CzzsnbForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsnbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	 com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor��
     *         voPackage��Data��ΪCzzsnbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsnbForm
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
        if (forward != null) {
            return forward;
        }
        //��ǰ��ActionForm
        CzzsnbForm form = (CzzsnbForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());

        //��ǰ̨�б�����ݸ���ActionForm��DataList
        //qyColumns����:��ϸ��������{"qyhc", "qyxmmc", "qybqljs"}
        String qyColumns[] = form.getQyColumns();
        form.setQyList(getValuesToList(httpServletRequest, qyColumns));
        
        //GrColumns����:�дΡ������ۼ�����֤�����ʹ��롢֤�����롢Ͷ������������������
        String grColumns[] = form.getGrColumns();
        form.setGrDataList(getValuesToList(httpServletRequest, grColumns));

        //��ʼ�����ݴ�������
        //VOPackage:�洢Processor��Action��(Object)Data��UserDate
        VOPackage vo = new VOPackage();
        
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor");

        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");

            //��ת
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Save------------------");
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsnbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	 com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor��
     *         voPackage��Data��ΪCzzsnbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm CzzsnbForm
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
            Debug.out("------------doHandle-----------------");
            return forward;
        }
        //��ǰ��ActionForm
        CzzsnbForm form = (CzzsnbForm) actionForm;
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.grsds.processor.CzzsnbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            //��ת
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}
