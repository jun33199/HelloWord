/**
 * 
 */
package com.ttsoft.bjtax.smsb.jmssb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * Title: ������˰�ۺϹ���ϵͳ
 * Description: �����걨-�����걨����¼��-���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�
 * Copyright: Copyright (c) 2005
 * Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjAciton extends SmsbAction {

	/**
	 * ����ҳ�浼��·��
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨����¼��&gt;</font>���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ� ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * ��ʼ��ҳ����ʾ
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request (if any)
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @return the element previously at the specified position.
	 * @exception BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
			throws BaseException {
		Debug.out("Call CjrjyjmscxtjAciton_doShow......");
		super.doInit(mapping, form, request, response);
		// ��õ�ǰ��userData����
		UserData userData = null;
		CjrjyjmscxtjForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (CjrjyjmscxtjForm)form;
			if (cForm == null) {
				cForm = new CjrjyjmscxtjForm();
			}
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			// ����EJB
			cForm = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			// ��дsession����
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * ��ѯ
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request (if any)
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @return the element previously at the specified position.
	 * @exception BaseException
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
			throws BaseException {
		Debug.out("Call CjrjyjmscxtjAciton_doQuery......");
		super.doInit(mapping, form, request, response);
		// ��õ�ǰ��userData����
		UserData userData = null;
		CjrjyjmscxtjForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (CjrjyjmscxtjForm) form;
			if(cForm == null) {
				cForm = new CjrjyjmscxtjForm();
			}
			// resetҳ�����
			// cForm.reset(mapping, request);
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			// ����EJB
			cForm = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			// ��дsession����
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Query");
	}

	/**
	 * doChangePageA
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request (if any)
	 * @param request
	 *            The HTTP request we are processing
	 * @param response
	 *            The HTTP response we are creating
	 * @return the element previously at the specified position.
	 * @exception BaseException
	 */
	public ActionForward doChangePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		Debug.out("Call doChangePageA......");
		super.doInit(mapping, form, request, response);
		// ��õ�ǰ��userData����
		UserData ud = null;
		CjrjyjmscxtjForm pf = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			ud = this.getUserData(request);
			pf = (CjrjyjmscxtjForm) form;
			if (pf == null) {
				pf = new CjrjyjmscxtjForm();
			}
			// ����EJB
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(ud);
			vo.setData(pf);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			pf = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			
			// ��дsession����
			request.setAttribute(mapping.getAttribute(), pf);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("ChangePage");
	}
	
	
	
}
