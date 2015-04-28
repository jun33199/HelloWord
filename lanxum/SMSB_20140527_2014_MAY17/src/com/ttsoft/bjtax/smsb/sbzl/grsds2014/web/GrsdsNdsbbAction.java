package com.ttsoft.bjtax.smsb.sbzl.grsds2014.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsNdsbbAction extends SmsbAction  {

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;个人所得税年度申报表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"个人所得税年度申报表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}
	
	/**
	 * @Description: TODO 显示信息
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
		GrsdsNdsbbActionForm ndsbbForm = (GrsdsNdsbbActionForm)actionForm;
		String query_jsjdm=ndsbbForm.getBtzzxx_jsjdm();
		String query_sfzjhm=ndsbbForm.getTzzxx_sfzjhm();
		String query_sfzjlx=ndsbbForm.getTzzxx_sfzjlx();
	
		try {
			if(query_jsjdm==null || "".equals(query_jsjdm) || query_sfzjlx==null || "".equals(query_sfzjlx) || query_sfzjhm==null || "".equals(query_sfzjhm)){
				throw ExceptionUtil.getBaseException(new Exception("查询信息不完整"));
			}
			
			//检验是否填写基本信息表 如未填写跳转至投资者列表页 
			if (!GrsdsUtil.hasItemJcxxTzz(ndsbbForm.getNd(), query_jsjdm, query_sfzjlx, query_sfzjhm)) {	
				return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&jsjdm="+query_jsjdm+"&msg=未完成基本信息表");
			}
			
			ndsbbForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));		//身份证件类型
			ndsbbForm.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			
			//查询年报信息
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_NDSBB);
			vo.setData(ndsbbForm);
			ndsbbForm = (GrsdsNdsbbActionForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ndsbbForm);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
			
		return mapping.findForward("Show");
	}

	/**
	 * @Description: TODO 保存
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsNdsbbActionForm ndsbbForm =(GrsdsNdsbbActionForm)actionForm;
		UserData ud = getUserData(request);
		String nd = ndsbbForm.getSkssqq().substring(0,4);
		ndsbbForm.setNd(nd);
		
		try {
			ndsbbForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));		//身份证件类型
			ndsbbForm.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			
			//检验是否填写基本信息表 如未填写跳转至投资者列表页 
			if (!GrsdsUtil.hasItemJcxxTzz(ndsbbForm.getNd(), ndsbbForm.getBtzzxx_jsjdm(), ndsbbForm.getTzzxx_sfzjlx(), ndsbbForm.getTzzxx_sfzjhm())) {	
				return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&jsjdm="+ndsbbForm.getBtzzxx_jsjdm()+"&msg=未完成基本信息表");
			}
			
			//保存年度申报表
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_SAVE);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_NDSBB);
			vo.setData(ndsbbForm);
			vo.setUserData(ud);
			ndsbbForm = (GrsdsNdsbbActionForm)SbzlProxy.getInstance().process(vo);
			ndsbbForm.setMsg("保存成功");
			request.setAttribute(mapping.getAttribute(), ndsbbForm);
		
		} catch (Exception e) {
			ndsbbForm.setMsg("保存失败");
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}		
		return mapping.findForward("Show");
	}
	
	/**
	 * @Description: TODO 上一页
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doPrevious(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsNdsbbActionForm ndsbbForm =(GrsdsNdsbbActionForm)actionForm;
			
		ActionForward result = new ActionForward("/webapp/smsb/grsds/grsdsJbxxAction2014.do?actionType=Show&qyxx_jsjdm="+ndsbbForm.getBtzzxx_jsjdm()+"&grxx_sfzjlx="+ndsbbForm.getTzzxx_sfzjlx()+"&grxx_sfzjhm="+ndsbbForm.getTzzxx_sfzjhm()+"&nd="+ndsbbForm.getNd());
		return result;
	}
	
	/**
	 * @Description: TODO 返回
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doBack(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {
		GrsdsNdsbbActionForm ndsbbForm =(GrsdsNdsbbActionForm)actionForm;
		return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&jsjdm="+ndsbbForm.getBtzzxx_jsjdm());
	}
	
}
