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
 * Title: 北京地税综合管理系统
 * Description: 上门申报-补充申报资料录入-安置残疾人就业企业营业税减免税查询统计表
 * Copyright: Copyright (c) 2005
 * Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjAciton extends SmsbAction {

	/**
	 * 设置页面导航路径
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request
				.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#7C9AAB\">综合服务管理信息系统&gt;补充申报资料录入&gt;</font>安置残疾人就业企业营业税减免税查询统计表 ");
		request.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	
	/**
	 * 初始化页面显示
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
		// 获得当前的userData对象
		UserData userData = null;
		CjrjyjmscxtjForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (CjrjyjmscxtjForm)form;
			if (cForm == null) {
				cForm = new CjrjyjmscxtjForm();
			}
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			// 调用EJB
			cForm = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			// 回写session数据
			request.setAttribute(mapping.getAttribute(), cForm);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * 查询
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
		// 获得当前的userData对象
		UserData userData = null;
		CjrjyjmscxtjForm cForm = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			userData = this.getUserData(request);
			cForm = (CjrjyjmscxtjForm) form;
			if(cForm == null) {
				cForm = new CjrjyjmscxtjForm();
			}
			// reset页面对象
			// cForm.reset(mapping, request);
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(userData);
			vo.setData(cForm);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			// 调用EJB
			cForm = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			// 回写session数据
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
		// 获得当前的userData对象
		UserData ud = null;
		CjrjyjmscxtjForm pf = null;
		VOPackage vo = new VOPackage();
		// 执行Processor
		try {
			// 初始化
			ud = this.getUserData(request);
			pf = (CjrjyjmscxtjForm) form;
			if (pf == null) {
				pf = new CjrjyjmscxtjForm();
			}
			// 调用EJB
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(ud);
			vo.setData(pf);
			vo.setProcessor(CodeConstant.JMSSB_CJRJYJMSCXTJ_PROCESSOR);
			pf = (CjrjyjmscxtjForm) ZhsbProxy.getInstance().process(vo);
			
			// 回写session数据
			request.setAttribute(mapping.getAttribute(), pf);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("ChangePage");
	}
	
	
	
}
