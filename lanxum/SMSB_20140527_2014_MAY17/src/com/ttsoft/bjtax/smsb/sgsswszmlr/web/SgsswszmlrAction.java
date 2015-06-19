package com.ttsoft.bjtax.smsb.sgsswszmlr.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;


public class SgsswszmlrAction extends SmsbAction{
	/**
	 * ����ҳ�浼��·��
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;˰����˰֤������&gt;</font>�ֹ�˰����˰֤�� ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * �����ʾ
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doShow......");
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * ����¼����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doSave......");
		
		//��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			// 
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			cForm.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_N);
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
			
		}
		
		//������ʾ
		//δ����ɹ�
		if(Constant.CONS_SGLR_SAVEISSUCC_N.equals(cForm.getSaveIsSucc())){
			return mapping.findForward("Show");
		}
		
		
		return mapping.findForward("ShowWH");
	}
	
	/**
	 * ����¼����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doUpdate......");
		
		//��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(CodeConstant.SMSB_UPDATEACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			cForm.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_N);
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		
		//δ����ɹ�
		if(Constant.CONS_SGLR_SAVEISSUCC_N.equals(cForm.getSaveIsSucc())){
			return mapping.findForward("Show");
		}
			
		//������ʾ
		return mapping.findForward("ShowWH");
	}
	
	/**
	 * ά���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doShowWH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doShow......");
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("ShowWH");
	}
	
	
	/**
	 * ����һ��Ʊ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doCancel......");
		
		//��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.CONS_SGLR_CANCLE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			ZhsbProxy.getInstance().process(vo);
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
			cForm.setMessage("�ѳɹ����ϣ�");
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return this.doQuery(mapping, cForm, request, response);
	}
	
	/**
	 * ��ô�ӡ��Ϣ����ӡԤ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.QUERYONE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Print");
	}
	
	/**
	 * ��ӡ�ɹ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doPrintSuccess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		//��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.CONS_SGLR_PRINT_SUCCESS);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return this.doQuery(mapping, cForm, request, response);
	}
	
	/**
	 * ȡ�Ŵ�ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doReprintNewPH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doReprintNewPH......");
		
		//��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			cForm.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_1);
			vo.setAction(Constant.CONS_SGLR_PRINT_NEWPH);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU ��Ʊ����Ŀ�������� ��Ʊ�� 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		
		if(cForm != null && cForm.getMessage() != null && !"".equals(cForm.getMessage())){
			return this.doQuery(mapping, cForm, request, response);
		}
		return mapping.findForward("Print");
	}
	
	
	
	
	
	
	
	/**
	 * ִ�в�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			cForm.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("ShowWH");
	}
	
	
	/**
	 * ִ�в�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doShowOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// ��õ�ǰ��userData����
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.QUERYONE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			//������Ϣ
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Show");
	}
	
	
	
	
	/**
	 * ��ʼ��
	 * @param cForm
	 * @param userData
	 */
	private void init(SgsswszmlrForm cForm,UserData userData){
		VOPackage vo = new VOPackage();
		// ִ��Processor
		try {
			// ��ʼ��
			vo.setAction(CodeConstant.SMSB_INIT);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// ����EJB
			ArrayList rs = (ArrayList)ZhsbProxy.getInstance().process(vo);
			// 
			cForm.setSmsmList(rs);
			cForm.setLrrdm(userData.getYhid());
			cForm.setLrrmc(userData.getYhmc());
			cForm.setSwjgmc(userData.getSsdwmc());
			cForm.setTfrq(DateTimeUtil.getCurrentDate());
			cForm.setLrrq(DateTimeUtil.getCurrentDate());
		} catch (Exception ex) {
			ex.printStackTrace();
			//������Ϣ
			cForm.setMessage(ex.getMessage());
		}
	}
	
	
	

}
