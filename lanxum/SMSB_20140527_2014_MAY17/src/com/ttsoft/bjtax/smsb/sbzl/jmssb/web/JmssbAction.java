/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
//import java.util.*;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����м���˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class JmssbAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm JmssbForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;����˰�걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "����˰�걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/jmssb/jmssb-000.htm");
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��JmssbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor��
     *         voPackage��Data��ΪJmssbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
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
        JmssbForm form = (JmssbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
//    //�õ���ǰʱ��
//    //Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (JmssbForm) SbzlProxy.getInstance().process(vo);
//      form.setCjrq(now);
//      form.setSbrq(SfDateUtil.getDate());
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            Debug.out("-------------------------");
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��JmssbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor��
     *         voPackage��Data��ΪJmssbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doQuery��������ȡҵ�����ݵĽ����
     * 5�����ݵõ���zsfs(���շ�ʽ)����ֵ��������תҳ���Լ�ҵ���쳣�����׳�
     * 6��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
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
        JmssbForm form = (JmssbForm) actionForm;
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
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            form = (JmssbForm) SbzlProxy.getInstance().process(vo);
            //��ת
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            Debug.out("-------------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��JmssbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor��
     *    voPackage��Data��ΪJmssbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
     * 	����SbzlProxy��process������
     * 4������doSave��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
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
        JmssbForm form = (JmssbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        //��ǰ̨�б�����ݸ���ActionForm��DataList
        String columns[] = form.getColumns();
        //�õ���ϸ�ύ���ݣ����ҹ��˲��������������ݣ���˰��˰ĿΪ�յ�����
        SfHashList tmpList = new SfHashList(getValuesToList(httpServletRequest,
            columns));
        tmpList.filter("szsmdm", "");
        String pks[] = new String[tmpList.getRecords().size()];
        for(int i=0; i<tmpList.getRecords().size(); i++) {
        	String pk = ((Map)tmpList.getRecords().get(i)).get("jmlx") + "-" +
        	((Map)tmpList.getRecords().get(i)).get("szsmdm") + "-" +
        	((Map)tmpList.getRecords().get(i)).get("jmxmjdm");

        	int temp = i + 1;
        	for(int ii=0; ii<pks.length; ii++) {
        		if(pk.equals(pks[ii])) {
        			throw ExceptionUtil.getBaseException(new Exception("��" + temp + "�������ظ��ˣ�������ࡢ����˰�֣�˰Ŀ�����롢������Ŀ�����벻����ȫ��ͬ��"));
        		}
        	}
        	pks[i] = pk;
        }
        
        form.setDataList(tmpList.getRecords());
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setUserData(userData);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            JmssbForm waringform = (JmssbForm) SbzlProxy.getInstance().process(
                vo);
            //��ת
            Debug.out("waringform=" + waringform);

            if (waringform != null)
            {
                httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                                waringform);
                return actionMapping.findForward("Error");
            }

            form.reset(actionMapping, httpServletRequest);
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "����ɹ���");
            return actionMapping.findForward("Save");
            //��ת

        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��JmssbForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor��
     *        voPackage��Data��ΪJmssbForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doDelete��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
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
        JmssbForm form = (JmssbForm) actionForm;
        /*����Ĭ��չ����Ҫ����������*/
        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //�����������Data����,�������������ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //����Proxy����ʼ��ActionForm��ֵ
            SbzlProxy.getInstance().process(vo);

            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            //��ת
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "ɾ���ɹ���");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}