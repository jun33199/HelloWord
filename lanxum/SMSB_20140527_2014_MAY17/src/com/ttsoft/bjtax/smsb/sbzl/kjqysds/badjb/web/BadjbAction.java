package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Action</p>
 *
 * <p>Description: �������ǼǱ�ҳ��Ӧ���¼���Ӧ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ApplicationException;



public class BadjbAction extends SmsbAction
{
    public BadjbAction()
    {
    }

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param actionForm
     *            QysdsnbForm
     * @param httpServletRequest
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */
    protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest, HttpServletResponse response)
    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�۽���ҵ����˰</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "�۽���ҵ����˰��ͬ�����ǼǱ�");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
    }

    /**
     * ��ʼ�����ҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doInit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doInit===========");
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        /**
         * ��ʼ��ҳ����Ϣ
         */
        // ��¼��
        badjForm.setTotalCount(0);
        // ��ҳ��
        badjForm.setTotalPage(0);
        // ��ǰҳ
        badjForm.setCurrentPage(0);

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Init");
    }

    /**
     * ��ʼ��ҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
     // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        
        if(!badjForm.getModifyFlag()){
        	badjForm = new BadjbForm();
        }
        else
        {
        	// ��ʼ��bo
            BadjbBO bo = new BadjbBO();

            try
            {
                ActionHelper.form2Bo(badjForm, bo);

                //���ò�ѯ����
                VOPackage vo = new VOPackage();
                vo.setAction(CodeConstant.SMSB_VIEWMX);
                vo.setData(bo);
                vo.setUserData(userData);
                vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
                // ��ǰ̨����ת������̨����
                bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
                bo.getHtxx().setHtyxq((bo.getHtxx().getHtyxq()).replaceAll("-", ""));
                bo.getHtxx().setQyrq((bo.getHtxx().getQyrq()).replaceAll("-", ""));
            }
            catch (Exception ex)
            {
                throw new ApplicationException("��ѯ�����Ǽ���Ϣ�����������Ա��ϵ��");
            }
            // ��boת����form
            ActionHelper.BO2form(bo, badjForm);
        }
        /**
         * ��ʼ��ҳ�������˵�
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("��ʼ��ҳ�������˵�����!");
        }

        // �����
		badjForm.setTbrq(SfDateUtil.getDate());

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Show");
    }

    /**
     * ��ѯ�۽���������Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doQueryKjrInfo(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doQueryKjrInfo===========");
        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("tbrq = " + badjForm.getTbrq());
        System.out.println("gjdq = " + badjForm.getFjmqyxx().getFjmgb());
        System.out.println("bz = " + badjForm.getHtxx().getBz());
        // ��ʼ��bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // ��ǰ̨����ת������̨����
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("�����ǼǱ��ѯ��˰����Ϣ�����������Ա��ϵ��");
        }
        // ��boת����form
        ActionHelper.BO2form(bo, badjForm);
        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Show");
    }

    /**
     * ��ѯ�۽������˱�����Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doQueryKjrRecords(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doQueryKjrRecords===========");
        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("isModify = " + badjForm.getModifyFlag());
        // ��ʼ��bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION2);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // ��ǰ̨����ת������̨����
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            System.out.println("��ѯ�۽������˱����Ǽ���Ϣ�����������Ա��ϵ��");
            ex.printStackTrace();
            badjForm = new BadjbForm();
            request.setAttribute(mapping.getAttribute(), badjForm);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��ѯ�۽������˱����Ǽ���Ϣ�����������Ա��ϵ��");
            return mapping.findForward("Init");
        }
        // ��boת����form
        ActionHelper.BO2form(bo, badjForm);
        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);
        
        String returnStr = new String();
        if(badjForm.getModifyFlag())
        {
        	returnStr = "showModify";
        }
        else
        {
        	returnStr = "Init";
        }

        return mapping.findForward(returnStr);
    }


    /**
     * ���汸���ǼǱ���Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doSave===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("gb = " + badjForm.getFjmqyxx().getFjmgjdq());
        // ��ʼ��bo
        BadjbBO bo = new BadjbBO();

        try
        {
        	// ���������������Ƹ�ʽ
        	String qtzlmc = badjForm.getHtxx().getQtzlmc();
        	if (qtzlmc != null && !"".equals(qtzlmc)) 
        	{
    			//�滻�������������еĸ�ʽ��Ϊ��Ӧ��ʽ�ַ���
        		qtzlmc = qtzlmc.replaceAll(" ", "&nbsp;");
        		qtzlmc = qtzlmc.replaceAll("\r\n", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\r", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\n", "<br/>");
        		
        		badjForm.getHtxx().setQtzlmc(qtzlmc);
    		}
        	// ��form����ת����bo���󹩺�̨ʹ��
            ActionHelper.form2Bo(badjForm, bo);

            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // ��ǰ̨����ת������̨����
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            // ��boת����form
            badjForm = new BadjbForm();
            // �����
            badjForm.setTbrq(SfDateUtil.getDate());
            // ��badjForm ����request��
            request.setAttribute(mapping.getAttribute(), badjForm);
            throw new ApplicationException("���汸���ǼǱ���Ϣ�����������Ա��ϵ��");
        }
        // ��boת����form
        badjForm = new BadjbForm();
		String message = new String();
		if(bo == null)
		{
	        // ��badjForm ����request��
	        request.setAttribute(mapping.getAttribute(), badjForm);
	        message = "�����ǼǱ���Ϣ����ɹ���";
		}
		else
		{
			if(bo.getZtbz().equals("3"))
			{
				ActionHelper.BO2form(bo, badjForm);
				// ��badjForm ����request��
				message = bo.getMessage();
			}
		}
		// �����
		badjForm.setTbrq(SfDateUtil.getDate());
		request.setAttribute(mapping.getAttribute(), badjForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, message);

        return mapping.findForward("Show");
    }

    /**
     * �鿴����˱����ǼǱ���ϸ��Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doViewMX(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doViewMX===========");
        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("badjxh = " + badjForm.getBadjxh());
        // ��ʼ��bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_VIEWMX);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // ��ǰ̨����ת������̨����
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("��ѯ�����Ǽ���Ϣ�����������Ա��ϵ��");
        }
        // ��boת����form
        ActionHelper.BO2form(bo, badjForm);
        //�Ѻ�ͬ��ǧλ�ָ�
        ActionHelper.splitJe(badjForm);
        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("ViewMX");
    }

    /**
     * ��˱����Ǽ���Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShenhe(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doShenhe===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("badjxh = " + badjForm.getBadjxh());
        System.out.println("badjbbh = " + badjForm.getBadjbbh());


        try
        {
            if(null == badjForm.getBadjbbh() || "".equals(badjForm.getBadjbbh()))
            {
                // ��ʼ��bo
                BadjbBO bo = new BadjbBO();
                ActionHelper.form2Bo(badjForm, bo);

                //���ò�ѯ����
                VOPackage vo = new VOPackage();
                vo.setAction(CodeConstant.SMSB_SHENHE);
                vo.setData(bo);
                vo.setUserData(userData);
                vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
                // ��ǰ̨����ת������̨����
                bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
                // ��boת����form
                ActionHelper.BO2form(bo, badjForm);
            }
        }
        catch (Exception ex)
        {
            System.out.println("��˱����Ǽ���Ϣ�����������Ա��ϵ��");
            ex.printStackTrace();
            return mapping.findForward("Shbtg");
        }
        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);
        System.out.println("jsjdm = " + badjForm.getJsjdm());

        String forwardStr = new String();
        if("1".equals(badjForm.getZtbz()))
        {
            // ���ͨ����ת·��
            forwardStr = "ViewMX";
            // �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��˱����Ǽǲ����ɹ���");
        }
        else if("2".equals(badjForm.getZtbz()))
        {
            // ��˲�ͨ����ת·��
            forwardStr = "Shbtg";
        }

        return mapping.findForward(forwardStr);
    }

    /**
     * ��ӡ�����ǼǱ�
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doPrint(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doPrint===========");
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Print");
    }
    
    /**
     * ��ʼ���޸Ĳ�ѯҳ��
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShowModify(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doShowModify===========");
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        /**
         * ��ʼ��ҳ����Ϣ
         */
        // ��¼��
        badjForm.setTotalCount(0);
        // ��ҳ��
        badjForm.setTotalPage(0);
        // ��ǰҳ
        badjForm.setCurrentPage(0);

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("showModify");
    }
    
    /**
     * ɾ�������ǼǱ���Ϣ
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doDelete===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // ��ȡ��ϵͳ�û���Ϣ
        UserData userData = this.getUserData(request);
        // ��ȡBadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("gb = " + badjForm.getFjmqyxx().getFjmgjdq());
        // ��ʼ��bo
        BadjbBO bo = new BadjbBO();

        try
        {

        	// ��form����ת����bo���󹩺�̨ʹ��
            ActionHelper.form2Bo(badjForm, bo);

            //���ò�ѯ����
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // ��ǰ̨����ת������̨����
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            // ��badjForm ����request��
            request.setAttribute(mapping.getAttribute(), badjForm);
            throw new ApplicationException("ɾ�������ǼǱ���Ϣ�����������Ա��ϵ��");
        }
        // ��boת����form
        badjForm = new BadjbForm();
		String message = new String();

        // ��badjForm ����request��
        request.setAttribute(mapping.getAttribute(), badjForm);
        message = "�����ǼǱ���Ϣɾ���ɹ���";
		request.setAttribute(CodeConstant.SMSB_DELETE_SUCCESS, message);
		return doQueryKjrRecords(mapping,form,request,response);

        //return mapping.findForward("Show");
    }
}
