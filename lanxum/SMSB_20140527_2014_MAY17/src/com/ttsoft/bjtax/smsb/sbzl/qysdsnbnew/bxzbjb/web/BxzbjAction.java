/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class BxzbjAction
extends SmsbAction
{
	/**
	 * 操作员数据
	 */
	
	private UserData userData = null;
	
	/**
	 * 公共的前置处理方法，每次进入页面都会被调用<BR>
	 * 设置页面显示时使用的导航信息和标题
	 * @param mapping struts.action.ActionMapping
	 * @param actionForm QysdsnbForm
	 * @param httpServletRequest HttpServletRequest
	 * @param response HttpServletResponse
	 */
	
	protected void initialRequest (ActionMapping mapping,
			ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse response)
	
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
		"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年度纳税申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"企业所得税年度纳税申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	/**
	 * 初始化页面数据
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doShow (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
		
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
		try {
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		} catch (Exception ex) {
			bxzbjForm.reset(mapping, request);
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * 查询已申报数据
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doQuery (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
		
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		userData = this.getUserData(request);
		bxzbjForm.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(bxzbjForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
		vo.setUserData(userData);
		try
		{
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			bxzbjForm.reset(mapping, request);
			//系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return mapping.findForward("Query");
	}
	
	/**
	 * 保存申报数据
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doSave (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		//获取固定行列内容
		bxzbjForm.setDataList(SfRequestUtil.getValuesToList(request,bxzbjForm.getGd_Colums()));
		System.out.println("bxzbjForm.getDataList().size()"+bxzbjForm.getDataList().size());
		
		//获取其他准备金子行列内容
		bxzbjForm.setQtzbjList(SfRequestUtil.getValuesToList(request,bxzbjForm.getQtzbj_Columns()));
		System.out.println("bxzbjForm.getQtzbjList().size()"+bxzbjForm.getQtzbjList().size());
		
		//获取其他子行列内容
		bxzbjForm.setQtList(SfRequestUtil.getValuesToList(request,bxzbjForm.getQt_Columns()));
		System.out.println("bxzbjForm.getQtList().size()"+bxzbjForm.getQtList().size());
		
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
	    vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
	    try
		{
	    	bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			//系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
		return mapping.findForward("Show");
	}
	
	//数据转换函数
	/*
	 * 
	 */
	public  ArrayList getValuesToList(HttpServletRequest request,
			String columns[]) {
		ArrayList list = new ArrayList();
		if (columns == null) {
			System.out.println("columns==null");
			return list;
		}
		System.out.println("request.getParameter(columns[0])--"+request.getParameter(columns[0]));
		if (request.getParameter(columns[0]) != null) {
			System.out.println("request.getParameter(columns[0]) != null");
			int rows = request.getParameterValues(columns[0]).length;
			System.out.println("rows=="+rows);
			for (int i = 0; i < rows; i++) {
				HashMap map = new HashMap();
				for (int j = 0; j < columns.length; j++) {
					if (request.getParameter(columns[j]) == null) {
						continue;
					}
					map.put(columns[j],
							request.getParameterValues(columns[j])[i]);
				}
				list.add(map);
			}
		}
		System.out.println("over");
		return list;
	}
	/**
	 * 审核数据，审核通过后进行保存
	 * @param mapping struts.action.ActionMapping
	 * @param form ZcmxbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doCheck (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		
		//获取固定行列内容
		bxzbjForm.setDataList(SfRequestUtil.getValuesToList(request,bxzbjForm.getGd_Colums()));
		//System.out.println("bxzbjForm.getDataList().size()"+bxzbjForm.getDataList().size());
		
		//获取其他准备金子行列内容
		bxzbjForm.setQtzbjList(this.getValuesToList(request,bxzbjForm.getQtzbj_Columns()));
		//System.out.println("bxzbjForm.getQtzbjList().size()"+bxzbjForm.getQtzbjList().size());
		
		//获取其他子行列内容
		bxzbjForm.setQtList(this.getValuesToList(request,bxzbjForm.getQt_Columns()));
		//System.out.println("bxzbjForm.getQtList().size()"+bxzbjForm.getQtList().size());
		
		//request.setAttribute("ActionForm",hzssmxbform);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		 vo.setUserData(userData);
	    vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
	    try
		{
	    	bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
			if(bxzbjForm.getCheckList()==null)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(bxzbjForm.getCheckList().size()==0)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
			}else if(bxzbjForm.getCheckList().size()>0)
			{
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsNewUtil.getCheckResults(bxzbjForm.getCheckList()));
			}
	    	System.out.println("---------------------Action___doCheck--------------------");
		}
		catch (Exception ex)
		{
			//系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * 删除申报数据
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException 系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doDelete (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
		try
		{
			//调用processor
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			//系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
		return mapping.findForward("Show");
	}
	 /**
     * 退出页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doExit (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
	
	return mapping.findForward("Return");
	}   
    private void getBaseForm(HttpServletRequest request,BxzbjForm form)
	{
		userData = this.getUserData(request);
		QysdsNewForm baseForm =(QysdsNewForm)request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		if(baseForm!=null)
		{
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
			if("no".equals(QysdsNewUtil.isLastTable(baseForm,CodeConstant.TABLE_ID_BXZBJ))){
				form.setIsLastTable("no");
				form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_BXZBJ));
			}else{
				form.setIsLastTable("yes");
			}
			form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_BXZBJ));
		}
	}
}