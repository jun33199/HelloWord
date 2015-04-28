package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class QyqsSdsBaAction extends SmsbAction {

	/**
	 * 公共的前置处理方法，每次进入页面都会被调用<BR>
	 * 设置页面显示时使用的导航信息和标题
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QyqssdsBaForm
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
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">税费管理</font>&gt;企业清算所得税备案表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,"企业清算所得税备案表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,"help/smsb/sbzl/qyqssds/qyqssdsba-000.htm");
	}
	
	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
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
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		qyqssdsBaForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			qyqssdsBaForm.setTbrq(sdf.format(curTime));
			qyqssdsBaForm.setQsbaksrq(sdf.format(curTime));
			//以下是默认选中否
			qyqssdsBaForm.setJyqxjm("N");
			qyqssdsBaForm.setGdjyjs("N");
			qyqssdsBaForm.setYfdxgb("N");
			qyqssdsBaForm.setYfxgpc("N");
			qyqssdsBaForm.setYfgdqs("N");
			qyqssdsBaForm.setQtyy("N");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Show");
	}
	
	/**
	 * 保存信息
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
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
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	
	/**
	 * 录入计算机代码查询相关信息
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
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
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		String saveSuccess=qyqssdsBaForm.getSaveSuccess();
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			qyqssdsBaForm.setSaveSuccess(saveSuccess);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(null==qyqssdsBaForm.getTbrq()||"".equals(qyqssdsBaForm.getTbrq())){
				qyqssdsBaForm.setTbrq(sdf.format(curTime));
			}
			if(null==qyqssdsBaForm.getQsbaksrq()||"".equals(qyqssdsBaForm.getQsbaksrq())){
				qyqssdsBaForm.setQsbaksrq(sdf.format(curTime));
			}			
			qyqssdsBaForm.setIsQuery("1");
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Show");
	}
	/**
	 * 接受申请
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(5);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		Map map=QyqssdsActionHelper.getShztbs(qyqssdsBaForm.getJsjdm());
		if(!map.isEmpty() && map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("2")){
			throw new ApplicationException("该企业的企业清算所得税备案审核已通过，不能重复审核！");
		}
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
	 * 拒绝申请
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doRefuse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(6);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		Map map=QyqssdsActionHelper.getShztbs(qyqssdsBaForm.getJsjdm());
		if(map.isEmpty() || !map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("1")){
			throw new ApplicationException("该企业的企业清算所得税备案不是提交未审核状态，不能执行审核驳回！");
		}
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	
	/**
	 * 删除记录
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
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
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(7);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
	 * 撤销记录
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doCancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//获取基本信息
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);
		
		VOPackage vo = new VOPackage();
		vo.setAction(8);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		
		try {
			//调用process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
     * 返回页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDS_QUERY);
	}
	/**
     * 返回页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doPrintHz (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return mapping.findForward("PrintHz");
	}
}
