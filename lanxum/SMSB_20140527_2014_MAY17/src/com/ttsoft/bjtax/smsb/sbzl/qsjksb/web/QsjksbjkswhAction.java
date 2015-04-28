/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypysActionForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ��Ƿ˰�ɿ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjkswhAction
    extends SmsbAction
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
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�ۺ��걨");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbjkswh-000.htm");
    }

    /**
     * ��ѯ����������Ӧ��δ���ɿ���
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;


        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        
         form = (QsjksbjkswhActionForm) vo.getData();
        try
        {
            form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);
            //System.out.println("========222======="+form.getDataList().size());

            List ar = TranslateHelper.splitMAP(form.getDataList(),httpServletRequest);
            
            //Debug.out("((List)ar.get(0)).size()=" + ((List)ar.get(0)).size());
            //Debug.out("((List)ar.get(1)).size()=" + ((List)ar.get(1)).size());
            
            form.setDataList((List)ar.get(0));
            form.setNlwDataList((List)ar.get(1));

            //��ѯ�ɿ��鳷������
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setProcessor(
                "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
            vo.setData(form);
            vo.setUserData(ud);           
            try{
            	form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ��鳷������ʧ��!");
            }
            
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�����Ϣʧ��!");
        }
    }

    /**
     * �쿴һƱһ˰�ɿ�����Ϣ
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpys (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;
        QsjksbjksypysActionForm form1 = new QsjksbjksypysActionForm();
        form1.setJkpzh(form.getJkpzhStr());
        form1.setJsjdm(form.getJsjdm());
        form1.setForward("Back");
        //�����걨��ű�־
        httpServletRequest.setAttribute("qsjksbjksypysActionForm", form1);
        return actionMapping.findForward("Ypys");
    }

    /**
     * �쿴һƱ��˰�ɿ�����Ϣ
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpds (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm pf1 = (QsjksbjkswhActionForm) actionForm;
      JksqdPrintForm pf = new JksqdPrintForm();

      pf.setH_jsjdm(pf1.getJsjdm());
      pf.setH_sbbh(pf1.getSbbh());
      pf.setJsjdm(pf1.getJsjdm());
      pf.setSbbh(pf1.getSbbh());
      pf.setHeadSjly(CodeConstant.SMSB_SJLY_BJQS); 
      pf.setActionType("Show");
      httpServletRequest.setAttribute("jksqdPrintForm", pf);
      return actionMapping.findForward("PrintSQD");

    }

    /**
     * �����ɿ���
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doCx (ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��ֹrefresh
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;

        form.setCxStr("");
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor.QsjksbjkswhProcessor");
        vo.setData(form);
        //�ܿ���Ϣ
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            form = (QsjksbjkswhActionForm) ZhsbProxy.getInstance().process(vo);

            form.setSbbh("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�����ɹ���");
 
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Cx");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
        }
    }

    /**
     * �����ۺ��걨��ҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) actionForm;


        return actionMapping.findForward("Back");
    }

}
