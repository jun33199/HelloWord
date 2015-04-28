/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨������Ϣ��
 * </p>
 * 
 * @author lianglw
 * @version 1.1
 */

public class JbxxbAction2008 extends SmsbAction {


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
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰������Ϣ��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ����˰��Ȼ�����Ϣ��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JskffmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
		jbxxbForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
		vo.setUserData(userData);
		try {
			//����process
			jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		return mapping.findForward("Show");
	}

	

	/**
	 * �����걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JskffmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = this.doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		UserData userData = null;
		JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
		userData = this.getUserData(request);		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
		vo.setUserData(userData);

		try {

			jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);

		
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
		return mapping.findForward("Save");
	}
	/**
     * ɾ���걨����
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        UserData userData = null;

        JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //����processor
            SbzlProxy.getInstance().process(vo);
            jbxxbForm.reset(mapping, request);
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
        return mapping.findForward("Delete");
    }
	
    /**
     * ��ѯ���걨����
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
    	//new modify  yugw 
    	//-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
       /* ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }*/
    	UserData userData = null;

    	JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
        vo.setUserData(userData);
        try
        {
        	//����У��
			CheckBean checkBean = this.setCheckInf(jbxxbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
        	
        	jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), jbxxbForm);
        }
        catch (Exception ex)
        {
        	jbxxbForm.reset(mapping, request);
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        System.out.println("out Query in JbxxbAction");

        return mapping.findForward("Query");
    }
	/**
	 * �˳�ҳ��
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * @Description: TODO ����У�����
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(JbxxbForm2008 jbxxbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//У��汾����
		checkBean.setCurrentTime(jbxxbForm.getSknd());
		checkBean.setVersionStartTime("2008");
		checkBean.setVersionEndTime("2008");
		
		//У�������ڵĲ���
		checkBean.setJsjdm(jbxxbForm.getJsjdm());
		
		//У�����ܷ�Χ�������Ͳ���
		checkBean.setSkssrqq(jbxxbForm.getSknd()+"0101");
		checkBean.setSkssrqz(jbxxbForm.getSknd()+"1231");
		return checkBean;
	}
	
}