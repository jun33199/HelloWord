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
	 * 设置页面导航路径
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">综合服务管理信息系统&gt;税收完税证明管理&gt;</font>手工税收完税证明 ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * 入口显示
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doShow......");
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * 保存录入信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doSave......");
		
		//防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			// 
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			cForm.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_N);
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
			
		}
		
		//返回显示
		//未保存成功
		if(Constant.CONS_SGLR_SAVEISSUCC_N.equals(cForm.getSaveIsSucc())){
			return mapping.findForward("Show");
		}
		
		
		return mapping.findForward("ShowWH");
	}
	
	/**
	 * 保存录入信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doUpdate......");
		
		//防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(CodeConstant.SMSB_UPDATEACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			cForm.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_N);
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		
		//未保存成功
		if(Constant.CONS_SGLR_SAVEISSUCC_N.equals(cForm.getSaveIsSucc())){
			return mapping.findForward("Show");
		}
			
		//返回显示
		return mapping.findForward("ShowWH");
	}
	
	/**
	 * 维护界面入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doShowWH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doShow......");
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("ShowWH");
	}
	
	
	/**
	 * 作废一张票
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doCancel......");
		
		//防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.CONS_SGLR_CANCLE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			ZhsbProxy.getInstance().process(vo);
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
			cForm.setMessage("已成功作废！");
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return this.doQuery(mapping, cForm, request, response);
	}
	
	/**
	 * 获得打印信息到打印预览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.QUERYONE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			// 
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Print");
	}
	
	/**
	 * 打印成功
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doPrintSuccess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		//防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.CONS_SGLR_PRINT_SUCCESS);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return this.doQuery(mapping, cForm, request, response);
	}
	
	/**
	 * 取号打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doReprintNewPH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doReprintNewPH......");
		
		//防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			cForm.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_1);
			vo.setAction(Constant.CONS_SGLR_PRINT_NEWPH);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			cForm.setLrrmc(userData.getYhmc());//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		
		if(cForm != null && cForm.getMessage() != null && !"".equals(cForm.getMessage())){
			return this.doQuery(mapping, cForm, request, response);
		}
		return mapping.findForward("Print");
	}
	
	
	
	
	
	
	
	/**
	 * 执行查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			cForm.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("ShowWH");
	}
	
	
	/**
	 * 执行查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doShowOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Debug.out("Call SgsswszmlrAction_doPrint......");
		// 获得当前的userData对象
		UserData userData = null;
		SgsswszmlrForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (SgsswszmlrForm)form;
			vo.setAction(Constant.QUERYONE);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
			cForm = (SgsswszmlrForm)ZhsbProxy.getInstance().process(vo);
			init(cForm, userData);
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			init(cForm, userData);
			//错误消息
			cForm.setMessage(ex.getMessage());
			request.setAttribute(mapping.getAttribute(), cForm);
		}
		return mapping.findForward("Show");
	}
	
	
	
	
	/**
	 * 初始化
	 * @param cForm
	 * @param userData
	 */
	private void init(SgsswszmlrForm cForm,UserData userData){
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			vo.setAction(CodeConstant.SMSB_INIT);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.SMSSB_SGSSWSZMLR_PROCESSOR);
			// 调用EJB
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
			//错误消息
			cForm.setMessage(ex.getMessage());
		}
	}
	
	
	

}
