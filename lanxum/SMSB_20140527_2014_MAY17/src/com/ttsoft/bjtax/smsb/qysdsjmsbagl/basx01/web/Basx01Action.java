package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web;

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
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class Basx01Action extends SmsbAction{
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
		Basx01Form basx01Form = (Basx01Form) form;		
		//设置计算机代码和备案年度
		if(!"".equals(basx01Form.getAdd_jsjdm())){
			basx01Form.setJsjdm(basx01Form.getAdd_jsjdm());
		}
		if(!"".equals(basx01Form.getAdd_band())){
			basx01Form.setBand(basx01Form.getAdd_band());
			//设置起始和截止日期
			basx01Form.setQsrq(basx01Form.getBand()+"-01-01");
			basx01Form.setJzrq(basx01Form.getBand()+"-12-31");
			basx01Form.setMr_band(basx01Form.getBand());
		}
		userData = this.getUserData(request);
		
		basx01Form.setMr_lrr(userData.getYhid());
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		basx01Form.setMr_lrrq(sf.format(new Date()));
		
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD).equals(basx01Form.getCzlx())){
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
		}else{
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
		}
		try {
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx01ActionHelper.initialPageSelectItem(request,basx01Form.getZyzhlyzlList());
			request.setAttribute(mapping.getAttribute(), basx01Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_CHECK).equals(basx01Form.getCzlx())){
			return mapping.findForward("Check");
		}else if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_VIEW).equals(basx01Form.getCzlx())){
			return mapping.findForward("View");
		}else{
			return mapping.findForward("Show");
		}
		
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
		Basx01Form basx01Form = (Basx01Form) form;	
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		
		try {
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);	
			ActionHelper.initialZfbgSelectItem(request);
			request.setAttribute(mapping.getAttribute(), basx01Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Zfbg");
		
		
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
		Basx01Form basx01Form = (Basx01Form) form;
		ActionForward forward = null;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean =QysdsUtil.setCheckInf(basx01Form);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx01ActionHelper.initialPageSelectItem(request,basx01Form.getZyzhlyzlList());
			request.setAttribute(mapping.getAttribute(), basx01Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		if("2".equals(basx01Form.getCzlx())){
            forward=mapping.findForward("Show");
        }else{
            forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
        }     
		request.setAttribute(mapping.getAttribute(), basx01Form);		
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
		Basx01Form basx01Form = (Basx01Form) form;
		ActionForward forward = null;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_BGACTION);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setUserData(userData);
		try {
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
			//设置资源利用类型下拉框
			Basx01ActionHelper.initialPageSelectItem(request,basx01Form.getZyzhlyzlList());
			request.setAttribute(mapping.getAttribute(), basx01Form);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}

        forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
		request.setAttribute(mapping.getAttribute(), basx01Form);		
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
		Basx01Form basx01Form = (Basx01Form) form;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx01Form);
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
    	Basx01Form basx01Form = (Basx01Form) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setUserData(userData);
		try {
			
			//调用校验
			CheckBean checkBean =QysdsUtil.setCheckInf(basx01Form);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx01Form.getBasqwsh()+"&czlx="+basx01Form.getCzlx();
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
    	Basx01Form basx01Form = (Basx01Form) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx01Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX01_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx01Form = (Basx01Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx01Form.getBasqwsh()+"&czlx="+basx01Form.getCzlx();
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
    	Basx01Form basx01Form = (Basx01Form) form;
    	
    	String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx01Form.getBasqwsh()+"&czlx="+basx01Form.getCzlx();
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
    	Basx01Form basx01Form = (Basx01Form) form;

    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_ZFBGACTION);
		vo.setData(basx01Form);
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
		return doZfbg(mapping,basx01Form,request,response);
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