package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.ActionHelper;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class Basx13BAction extends SmsbAction{
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
		"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;录入纳税人企业所得税减免税备案申请</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"录入纳税人企业所得税减免税备案申请");
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
		Basx13BForm basx13BForm = (Basx13BForm) form;
		basx13BForm.setSearchAction("0");
		//设置计算机代码和备案年度
		if(!"".equals(basx13BForm.getAdd_jsjdm())){
			basx13BForm.setJsjdm(basx13BForm.getAdd_jsjdm());
		}
		if(!"".equals(basx13BForm.getAdd_band())){
			basx13BForm.setBand(basx13BForm.getAdd_band());
			//设置起始和截止日期
			basx13BForm.setQsrq(basx13BForm.getBand()+"-01-01");
			basx13BForm.setJzrq(basx13BForm.getBand()+"-12-31");
			basx13BForm.setMr_band(basx13BForm.getBand());
		}
		userData = this.getUserData(request);
		
		basx13BForm.setMr_lrr(userData.getYhid());
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		basx13BForm.setMr_lrrq(sf.format(new Date()));
		
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD).equals(basx13BForm.getCzlx())){
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
		}else{
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
		}
		try {
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx13BActionHelper.initialPageSelectItem(request,basx13BForm.getGxjslyList());
			request.setAttribute(mapping.getAttribute(), basx13BForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_CHECK).equals(basx13BForm.getCzlx())){
			
			return mapping.findForward("Check");
		}else if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_VIEW).equals(basx13BForm.getCzlx())){


			return mapping.findForward("View");
		}else if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_MODIFY).equals(basx13BForm.getCzlx())||String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_BGZX).equals(basx13BForm.getCzlx())){

			return mapping.findForward("Edit");
		}else{
			return mapping.findForward("Show");
		}
		
	}
	
	/**
	 * 根据计算机代码查询税务登记证号数据及相关投资信息
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
	public ActionForward doJsjdmSeach(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		Basx13BForm basx13BForm = (Basx13BForm) form;
		String zl=basx13BForm.getZl();
		String zlState=basx13BForm.getZlState();
		
		basx13BForm.setSearchAction("1");
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
			Basx13BActionHelper.initialPageSelectItem(request,basx13BForm.getGxjslyList());
			basx13BForm.setZl(zl);
			basx13BForm.setZlState(zlState);
		} catch (Exception ex) {
			
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx13BForm);
		return mapping.findForward("Show");
	}
	
	/**
	 * 根据税务登记证号查询相关投资信息
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
	public ActionForward doSwdjzhSeach(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		
		Basx13BForm basx13BForm = (Basx13BForm) form;
		basx13BForm.setSearchAction("1");
		String zl=basx13BForm.getZl();
		String zlState=basx13BForm.getZlState();
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION2);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
			Basx13BActionHelper.initialPageSelectItem(request,basx13BForm.getGxjslyList());
			basx13BForm.setZl(zl);
			basx13BForm.setZlState(zlState);
		} catch (Exception ex) {
			
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx13BForm);
		return mapping.findForward("Show");
	}
	
	/**
	 * 保存页面数据
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
	
	public ActionForward doJssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws BaseException {
		ActionForward forward=null;
		
		Basx13BForm basx13BForm = (Basx13BForm) form;

		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean =QysdsUtil.setCheckInf(basx13BForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx13BActionHelper.initialPageSelectItem(request,basx13BForm.getGxjslyList());
			request.setAttribute(mapping.getAttribute(), basx13BForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx13BForm);
		if("2".equals(basx13BForm.getCzlx())){
			forward=mapping.findForward("Edit");
		}else{
			forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
		}
		return forward;
	}
	
	/**
	 * 保存页面数据
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
	
	public ActionForward doBg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws BaseException {
		ActionForward forward=null;
		
		Basx13BForm basx13BForm = (Basx13BForm) form;

		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_BGACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx13BActionHelper.initialPageSelectItem(request,basx13BForm.getGxjslyList());
			request.setAttribute(mapping.getAttribute(), basx13BForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx13BForm);
		
		forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
		
		return forward;
	}	
	
	/**
	 * 审批数据
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
	
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		Basx13BForm basx13BForm = (Basx13BForm) form;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx13BForm);
		return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
	}
	
	/**
     * 上门接受申请并打印页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doSMShPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx13BForm basx13BForm = (Basx13BForm) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {
			//调用校验
			CheckBean checkBean =QysdsUtil.setCheckInf(basx13BForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx13BForm.getBasqwsh()+"&czlx="+basx13BForm.getCzlx();
    	return new ActionForward(url);
	}
	
	/**
     * 网上审核并打印页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doWSShPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx13BForm basx13BForm = (Basx13BForm) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx13BForm.getBasqwsh()+"&czlx="+basx13BForm.getCzlx();
    	return new ActionForward(url);
	}
    
    /**
     * 查看时打印页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx13BForm basx13BForm = (Basx13BForm) form;
    	
    	String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx13BForm.getBasqwsh()+"&czlx="+basx13BForm.getCzlx();
    	return new ActionForward(url);
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
    	return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
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
	
	public ActionForward doZfbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		Basx13BForm basx13BForm = (Basx13BForm) form;	
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX13B_PROCESSOR);
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		
		try {
			basx13BForm = (Basx13BForm) SbzlProxy.getInstance().process(vo);	
			ActionHelper.initialZfbgSelectItem(request);
			request.setAttribute(mapping.getAttribute(), basx13BForm);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Zfbg");
		
		
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
    public ActionForward doOperate (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx13BForm basx13BForm = (Basx13BForm) form;

    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_ZFBGACTION);
		vo.setData(basx13BForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		vo.setUserData(userData);
		try {	
			String alert=(String)SbzlProxy.getInstance().process(vo);
			if(alert!=null&&alert.length()>0){
				request.setAttribute("ALERT_STR", alert);
			}
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return doZfbg(mapping,basx13BForm,request,response);
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
    public ActionForward doBackZfbg (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY_ZFBG);
	}   
}