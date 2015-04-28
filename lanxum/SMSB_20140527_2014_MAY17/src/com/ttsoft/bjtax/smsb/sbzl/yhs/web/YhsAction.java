/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.yhs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ӡ��˰�����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class YhsAction
    extends SmsbAction
{
    /**
     * ����Ա����
     */

    private UserData userData = null;
    /**
     * ��ϸ�����б�
     */
    private List dataList = new ArrayList();

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;ӡ��˰�����˰�걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "ӡ��˰����걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/yhs/yhs-000.htm");

    }

    /**
     * ��ʼ��ҳ������
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        YhsForm yhsForm = (YhsForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

//Insert  Start  Zhou kejing 20031107
        yhsForm.setJsjdm("");
//Insert  End    Zhou kejing 20031107

        //����Ĭ��չ����Ҫ����������
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
            dataList = yhsForm.getDataList();
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            yhsForm);
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
     * @param actionForm YhsForm
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
        Debug.out("--- Debug --- Here is YhsAction" + ".doQuery");
        //��Ԥװ�ص���Ϣ��ò���д
        YhsForm yhsForm = (YhsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());
//Insert  Start  Zhou kejing 20031121
        yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            yhsForm);
            //��ת
        }
        catch (Exception ex)
        {
//Insert  Start  Zhou kejing 20031107
            yhsForm.setJsjdm("");
            //����Ĭ��չ����Ҫ����������
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(yhsForm);
            vo.setProcessor(
                "com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
            try
            {
                //����Proxy����ʼ��ActionForm��ֵ
                yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
                httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                                yhsForm);
            }
            catch (Exception ex1)
            {
                //ϵͳ��׽�쳣�������쳣�����׳�
                throw ExceptionUtil.getBaseException(ex1);
            }
//Insert  End    Zhou kejing 20031107
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);

        }
        return actionMapping.findForward("Query");
    }

    /**
     * �����걨����
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        Debug.out("--- Debug --- Here is YhsAction" + ".doSave");
        //��ֹˢ�»��ظ��ύ
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }
        YhsForm yhsForm = (YhsForm) actionForm;
        String columns[] = yhsForm.getColumns();

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

        //��������ֵ��ͬʱ�ŵ�dataList��
        yhsForm.setDataList(getValuesToList(httpServletRequest, columns));
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        //����processor
        try
        {
            SbzlProxy.getInstance().process(vo);
            yhsForm.reset(actionMapping, httpServletRequest);
            yhsForm.setDataList(dataList);
//Insert  Start  Zhou kejing 20031121
            yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            //��ת
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ���걨����
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        YhsForm yhsForm = (YhsForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        //����processor
        try
        {
            SbzlProxy.getInstance().process(vo);
            yhsForm.reset(actionMapping, httpServletRequest);
            yhsForm.setDataList(dataList);
//Insert  Start  Zhou kejing 20031121
            yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
            //��ת
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}