package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class SycxAction extends SmsbAction{

	/**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
	protected void initialRequest(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse response)
			{
			super.initialRequest(mapping, actionForm, httpServletRequest, response);
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
			                  "<font color=\"#A4B9C6\">耕地资源占用税系统</font>&gt;<font color=\"#7C9AAB\">税源查询 </font>");
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
			                  "税源查询");
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
			                  "help/smsb/zhsb/zhsb-000.htm");
			}
/*------------------------------------------------------------------------------------------------------------------------------*/	
	/**
     * <1>查询页面展示
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doShow(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//数据处理-查询分局信息
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_DQJSLIST,request);
			
			//如果分局列表只有一条还应该取得默认税务所列表
			List fjList = sycxForm.getFjlist();
			if(fjList.size()==1)
			{
				sycxForm.setFjdm(((swdwmodel)fjList.get(0)).getSwdwid());
				sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			}
			
			//JSP取来流程控制
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("Show");		
		}
	
	/**
     * <2>查询可以展示的税务所列表
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQuerysws(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
					
			//数据处理-查询税务所信息
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			
			//添加回送xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
			try {
				responseOut = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//税务所列表
			List swsList = sycxForm.getSwslist();
			responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
			responseOut.write("<mb>");
			for (int i = 0; i < swsList.size(); i++) {
				swdwmodel v = (swdwmodel) swsList.get(i);
				
				responseOut.write("<swsid>" + v.getSwdwid() + "</swsid>");
				responseOut.write("<swsmc>" + v.getSwdwmc() + "</swsmc>");
			}
			
			responseOut.write("</mb>");
			responseOut.close();		
			return null;	
		}
	
	/**
     * <3>查询信息列表
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQueryinf(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//获取分局
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_DQJSLIST,request);
			
			//如果分局列表只有一条  或者已有原条件还应该取得默认税务所列表
			List fjList = sycxForm.getFjlist();
			if(fjList.size()==1 || (sycxForm.getFjdm()!=null && !"".equals(sycxForm.getFjdm())))
			{
				if(fjList.size()==1)
				{
					sycxForm.setFjdm(((swdwmodel)fjList.get(0)).getSwdwid());
				}
				sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			}
			
			//数据处理-查询信息列表
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_QUERYACTION,request);

			//JSP取来流程控制
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("QueryList");		
		}
	
	/**
     * <4>查询信息详情
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQueryinfxx(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//数据处理-查询税务所信息
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_QUERYA_DETATILACTION,request);
			
			//JSP取来流程控制
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("QueryDetail");		
		}
	
	/*-----------------------------------------功能处理函数----------------------------------------------------------*/
		    /**
		     * 发送逻辑处理函数
		     * @param data
		     * @return
		     * @throws BaseException
		     */
		   private  SycxForm sendVO(Object data ,int actionName ,HttpServletRequest request) throws BaseException
		   {
			   //封装数据信息
			   VOPackage vo = new VOPackage();
			   vo.setData(data);
			   vo.setUserData(this.getUserData(request));
			   vo.setAction(actionName);
			   vo.setProcessor("com.ttsoft.bjtax.smsb.gdzys.cx.processor.SycxProcessor");
			   
			   //传输要处理数据
			   try {
				   	return (SycxForm)ZhsbProxy.getInstance().process(vo);
			   		} 
			   catch (Exception e) 
			   {
				   e.printStackTrace();
				   throw ExceptionUtil.getBaseException(e, "数据处理异常");
			   }
		   }
}
