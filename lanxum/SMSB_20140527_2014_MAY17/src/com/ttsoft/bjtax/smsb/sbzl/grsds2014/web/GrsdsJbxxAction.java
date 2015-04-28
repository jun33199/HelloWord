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

public class GrsdsJbxxAction extends SmsbAction  {

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;个人所得税基础信息表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"个人所得税基础信息表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	
	/**
	 * @Description: TODO 显示基础信息 如果当年有信息则取当年信息，如果没有则取上一年信息，如果在没有则空；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
		GrsdsJbxxActionForm grsdsJbxxActionForm = (GrsdsJbxxActionForm)actionForm;
	
		String jsjdm = grsdsJbxxActionForm.getQyxx_jsjdm();
		String sfzjhm = grsdsJbxxActionForm.getGrxx_sfzjhm();
		String sfzjlx = grsdsJbxxActionForm.getGrxx_sfzjlx();

		//较验一下
		if(jsjdm==null||"".equals(jsjdm)||sfzjlx==null||"".equals(sfzjlx)||sfzjhm==null||"".equals(sfzjhm))
		{
			throw ExceptionUtil.getBaseException(new Exception("基本查询信息不正确"));
		}
		
		try {
			grsdsJbxxActionForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));	//身份证件类型代码表
			grsdsJbxxActionForm.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			grsdsJbxxActionForm.setSwjgzzjgList(GrsdsUtil.getSwjgzzjgList(request));			//税务机关
			grsdsJbxxActionForm.setHyList(GrsdsUtil.getHyList(request));				//行业
			grsdsJbxxActionForm.setDjzclxList(GrsdsUtil.getDjzclxList(request));			//登记注册类型
			
			//获取基本信息数据
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_JBXXB);
			vo.setData(grsdsJbxxActionForm);
			grsdsJbxxActionForm = (GrsdsJbxxActionForm)SbzlProxy.getInstance().process(vo);
			
			request.setAttribute(mapping.getAttribute(), grsdsJbxxActionForm);		
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	
		return mapping.findForward("Show");
	}
	
	public ActionForward doChangeNd(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
		//需要新建一个form要不总会带出以前年度数据
		GrsdsJbxxActionForm oldForm = (GrsdsJbxxActionForm)actionForm;
		GrsdsJbxxActionForm jbxxForm = new GrsdsJbxxActionForm();
		
		jbxxForm.setQyxx_jsjdm(oldForm.getQyxx_jsjdm());
		jbxxForm.setGrxx_sfzjlx(oldForm.getGrxx_sfzjlx());
		jbxxForm.setGrxx_sfzjhm(oldForm.getGrxx_sfzjhm());
		jbxxForm.setNd(oldForm.getNd());
		
		return doShow(mapping, jbxxForm, request, response);
	}
	
	
	/**
	 * @Description: TODO 保存基础信息 ；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)actionForm;
		
		UserData ud = getUserData(request);

		try {		
			//获取基本信息数据
			
			jbxxForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));	//身份证件类型代码表
			jbxxForm.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			jbxxForm.setSwjgzzjgList(GrsdsUtil.getSwjgzzjgList(request));			//国籍地区代码表
			jbxxForm.setHyList(GrsdsUtil.getHyList(request));			//国籍地区代码表
			jbxxForm.setDjzclxList(GrsdsUtil.getDjzclxList(request));			//登记注册类型
			if(!GrsdsUtil.checkDjzclx(jbxxForm.getQyxx_jsjdm())){
				 return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&msg=该企业登记注册类型不符，不能填写该申报表&jsjdm="+jbxxForm.getQyxx_jsjdm());
			}
			
			if(!GrsdsUtil.checkZsfs(jbxxForm.getQyxx_jsjdm() ,jbxxForm.getNd())){
				 return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&msg=该企业征收方式不符，不能填写该申报表&jsjdm="+jbxxForm.getQyxx_jsjdm());
			}
			
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_SAVE);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_JBXXB);
			vo.setData(jbxxForm);
			vo.setUserData(ud);
			jbxxForm = (GrsdsJbxxActionForm)SbzlProxy.getInstance().process(vo);
			jbxxForm.setMsg("保存成功");
			request.setAttribute(mapping.getAttribute(), jbxxForm);	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);	
		}	
		return mapping.findForward("Show");
	}
	
	/**
	 * @Description: TODO 显示基础信息 如果当年有信息则取当年信息，如果没有则取上一年信息，如果在没有则空；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doNextSave(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)actionForm;
		
		UserData ud = getUserData(request);

		try {
			
			if(!GrsdsUtil.checkDjzclx(jbxxForm.getQyxx_jsjdm())){
				 return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&msg=该企业登记注册类型不符，不能填写该申报表&jsjdm="+jbxxForm.getQyxx_jsjdm());
			}
			
			if(!GrsdsUtil.checkZsfs(jbxxForm.getQyxx_jsjdm() ,jbxxForm.getNd())){
				 return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&msg=该企业征收方式不符，不能填写该申报表&jsjdm="+jbxxForm.getQyxx_jsjdm());
			}
			
			
			//获取基本信息数据
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_SAVE);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_JBXXB);
			vo.setData(jbxxForm);
			vo.setUserData(ud);
			jbxxForm = (GrsdsJbxxActionForm)SbzlProxy.getInstance().process(vo);
			jbxxForm.setMsg("保存成功");
			request.setAttribute(mapping.getAttribute(), jbxxForm);	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);	
		}	
		ActionForward result = new ActionForward("/webapp/smsb/grsds/grsdsNdsbbAction2014.do?msg=保存成功&btzzxx_jsjdm="+jbxxForm.getQyxx_jsjdm()+"&tzzxx_sfzjlx="+jbxxForm.getGrxx_sfzjlx()+"&tzzxx_sfzjhm="+jbxxForm.getGrxx_sfzjhm()+"&nd="+jbxxForm.getNd());
		result.setRedirect(true);
		return result;
	}
	
	/**
	 * @Description: TODO ；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doNext(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)actionForm;
			
		ActionForward result = new ActionForward("/webapp/smsb/grsds/grsdsNdsbbAction2014.do?actionType=Show&btzzxx_jsjdm="+jbxxForm.getQyxx_jsjdm()+"&tzzxx_sfzjlx="+jbxxForm.getGrxx_sfzjlx()+"&tzzxx_sfzjhm="+jbxxForm.getGrxx_sfzjhm()+"&nd="+jbxxForm.getNd());
		return result;
	}
	
	/**
	 * @Description: TODO 显示基础信息 如果当年有信息则取当年信息，如果没有则取上一年信息，如果在没有则空；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doBack(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		GrsdsJbxxActionForm jbxxForm = (GrsdsJbxxActionForm)actionForm;
	    return new ActionForward("/webapp/smsb/grsds/grsdsTzflbAction2014.do?actionType=Show&jsjdm="+jbxxForm.getQyxx_jsjdm());
	
	}
}
