/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author 曹刚
 * @version 1.1
 */

public class JwsdmxbAction2009 extends SmsbAction {
	/**
	 * 操作员数据
	 */

	private UserData userData = null;

	/**
	 * 公共的前置处理方法，每次进入页面都会被调用<BR>
	 * 设置页面显示时使用的导航信息和标题
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年度纳税申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		
		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) form;

		this.getBaseForm(request,jwsdmxbForm);

		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(jwsdmxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor.JwsdmxbProcessor2009");
		vo.setUserData(userData);
		try {

			// 调用processor
			jwsdmxbForm = (JwsdmxbForm2009) SbzlProxy.getInstance().process(vo);

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jwsdmxbForm);
		return mapping.findForward("Show");
	}

	/**
	 * 查询已申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return null;
	}

	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) form;
		
		jwsdmxbForm.setZjdmList(SfRequestUtil.getValuesToList(request,jwsdmxbForm.getZjdm_columns()));
		jwsdmxbForm.setJjdmList(SfRequestUtil.getValuesToList(request,jwsdmxbForm.getJjdm_columns()));
		jwsdmxbForm.setHjList(SfRequestUtil.getValuesToList(request,jwsdmxbForm.getHj_columns()));

		this.getBaseForm(request,jwsdmxbForm);

		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(jwsdmxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor.JwsdmxbProcessor2009");
		request.setAttribute(mapping.getAttribute(), jwsdmxbForm);
		try {
			jwsdmxbForm = (JwsdmxbForm2009) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), jwsdmxbForm);

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
		return mapping.findForward("Show");
	}

	/**
	 * 退出页面
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * 审核页面数据
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doCheck(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
		
		//-------对数据库操作的Method进行修改，防止刷新或重复提交----------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		
		JwsdmxbForm2009 jwsdsmxbForm = (JwsdmxbForm2009)form;
		jwsdsmxbForm.setZjdmList(SfRequestUtil.getValuesToList(request,jwsdsmxbForm.getZjdm_columns()));
		jwsdsmxbForm.setJjdmList(SfRequestUtil.getValuesToList(request,jwsdsmxbForm.getJjdm_columns()));
		jwsdsmxbForm.setHjList(SfRequestUtil.getValuesToList(request,jwsdsmxbForm.getHj_columns()));
		this.getBaseForm(request, jwsdsmxbForm); //加入纳税人基本信息
	    userData = this.getUserData(request);
		request.setAttribute(mapping.getAttribute(),jwsdsmxbForm);
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_CHECKACTION); //设置操作类型
		vo.setData(jwsdsmxbForm); //设置操作数据
		vo.setUserData(userData);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor.JwsdmxbProcessor2009"); //设置processor
		try
		{
			//调用processor
			jwsdsmxbForm =(JwsdmxbForm2009)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), jwsdsmxbForm);
			
			if(jwsdsmxbForm.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(jwsdsmxbForm.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(jwsdsmxbForm.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2009.getCheckResults(jwsdsmxbForm.getCheckList()));
			}
			return mapping.findForward("Show");
		}
		catch (Exception ex)
		{
			//系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
	
	}  
	
	/**
	 * 删除申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JwsdmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) form;

		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(jwsdmxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor.JwsdmxbProcessor2009");
		this.getBaseForm(request, jwsdmxbForm);
		try {
			// 调用processor
			jwsdmxbForm=(JwsdmxbForm2009)SbzlProxy.getInstance().process(vo);

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jwsdmxbForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
		return mapping.findForward("Show");
	}

	/**
	 * 从session中获取基本信息
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, JwsdmxbForm2009 form) {

		userData = this.getUserData(request);

		QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
				.getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		if (baseForm != null) {
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setCkzd(baseForm.getCkzd());
			form.setZsfs(baseForm.getZsfs());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2009_6));
			form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2009_6));
		}
	}
}