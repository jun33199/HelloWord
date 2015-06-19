package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.web;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
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
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author ��־��
 * @version 1.1
 */

public class QysdsMainAction2009 extends SmsbAction {
	/**
	 * ����Ա����
	 */
	private UserData userData = null;
	
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
		"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"��ҵ����˰�����˰�걨");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	/**
	 * ��ʼ��ҳ������
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
	
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsNewForm baseForm = (QysdsNewForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.processor.QysdsMainProcessor2009");
		vo.setUserData(userData);
		try {
			
			baseForm = (QysdsNewForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	/**
	 * ��ѯ���걨����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		HttpSession session=request.getSession(false);
		
		//��ѯǰ���Session
		session.removeAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		QysdsNewForm baseForm = (QysdsNewForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.processor.QysdsMainProcessor2009");
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(baseForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			baseForm.setJdlx(checkBean.getJdlx());
			
			
			baseForm = (QysdsNewForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			session.setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
			
		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return mapping.findForward("Show");
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsNewForm baseForm = (QysdsNewForm) form;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.processor.QysdsMainProcessor2009");
		vo.setUserData(userData);
		try {
			baseForm = (QysdsNewForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), baseForm);
			if(baseForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ȫ�����ͨ����");
			}else if(baseForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ȫ�����ͨ����");
			}else if(baseForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2009.getCheckResults(baseForm.getCheckList()));
			}
		} catch (Exception ex) {
			baseForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return mapping.findForward("Show");
	}
	
	/**
	 * ��ʼ��ҳ������
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
	
	public ActionForward doReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsNewForm baseForm = (QysdsNewForm) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(baseForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.main.processor.QysdsMainProcessor2009");
		vo.setUserData(userData);
		try {
			
			QysdsNewForm sessionForm =(QysdsNewForm)request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
			
			if(sessionForm!=null){
				sessionForm.setLinkUrlHTML(QysdsUtil2009.getLinkUrlHtml(sessionForm.getTableList(),sessionForm));
				request.setAttribute(mapping.getAttribute(), sessionForm);
			}else{
				baseForm = (QysdsNewForm) SbzlProxy.getInstance().process(vo);
				baseForm.setLinkUrlHTML(QysdsUtil2009.getLinkUrlHtml(baseForm.getTableList(),baseForm));
				request.setAttribute(mapping.getAttribute(), baseForm);
			}
			
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	 /**
	 * @Description: TODO ����У�����
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(QysdsNewForm qysdsNewForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(qysdsNewForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2009");
		checkBean.setVersionEndTime("2013");
		checkBean.setJsjdm(qysdsNewForm.getJsjdm());
		checkBean.setSkssrqq(qysdsNewForm.getSkssksrq());
		checkBean.setSkssrqz(qysdsNewForm.getSkssjsrq());
		return checkBean;
	}

}
