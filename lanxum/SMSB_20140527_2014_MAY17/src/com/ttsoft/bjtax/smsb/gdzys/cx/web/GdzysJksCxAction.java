package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.web.PlkscxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统--耕地占用税查询管理</p>
 * <p>Description:缴款书查询Action </p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysJksCxAction extends SmsbAction{
	 /**
	   * 公共的前置处理方法，每次进入页面都会被调用<BR>
	   * 设置页面显示时使用的导航信息和标题
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */
	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm form,
	                                HttpServletRequest request,
	                                HttpServletResponse response)

	  {
	    super.initialRequest(mapping, form, request, response);
	    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	                         "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
	                         "<font color=\"#7C9AAB\">耕地占用税业务查询</font>&gt;"+
	                         "<font color=\"#7C9AAB\">缴款书查询</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "缴款书查询");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  
	  
	  /**
	  * 功能：显示页面！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doShow(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		try {
		GdzysJksCxForm pf = (GdzysJksCxForm) aForm;
		pf.setQueryFlag(false);
		VOPackage vo = new VOPackage();
		UserData userData = this.getUserData(request);
		String yhjb=userData.yhjb;

		pf.setYhjb(yhjb);
		if(!yhjb.equals("50")){
			pf.setDqjs(userData.ssdwdm.substring(0, 2)+"00");	
			//dqjs
		}
		if(!yhjb.equals("50")&&!yhjb.equals("40")){
			pf.setCxdqjs(userData.ssdwdm);
		}
		//获取分局数据
		vo.setAction(CodeConstant.SMSB_DQJSLIST);
		vo.setProcessor(GdzysCodeConstant.GDZYS_YWCX_JKSCX_PROCESSOR);
		vo.setData(pf);
		vo.setUserData(userData);
		pf = (GdzysJksCxForm) ZhsbProxy.getInstance().process(vo);
		
		pf.setDataList(new ArrayList());
		request.setAttribute(mapping.getAttribute(), pf);
		return mapping.findForward("Show");
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}
	
	
	 /**
	   * 功能：查询缴款书信息！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doQuery(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		
		   VOPackage vo = new VOPackage(); 
			  try {
				  UserData userData = this.getUserData(request);
			      //清除数据
				  GdzysJksCxForm form = (GdzysJksCxForm)aForm;
				  form.setYhjb(userData.yhjb);
				  form.setQueryFlag(true);
				 // form.setPageSize(1);
				//获取分局数据
					vo.setAction(CodeConstant.SMSB_DQJSLIST);
					vo.setProcessor(GdzysCodeConstant.GDZYS_YWCX_JKSCX_PROCESSOR);
					vo.setData(form);
					vo.setUserData(userData);
					form = (GdzysJksCxForm) ZhsbProxy.getInstance().process(vo);
				
					form.setPageSize(10);
			      vo.setData(form);
			      vo.setUserData(this.getUserData(request));
			      //action参数
			      vo.setAction(CodeConstant.SMSB_QUERYACTION);
			      //processor参数
			      vo.setProcessor(GdzysCodeConstant.GDZYS_YWCX_JKSCX_PROCESSOR);
			      //返回form
			      form = (GdzysJksCxForm) ZhsbProxy.getInstance().process(vo);
			      
			      System.out.println("#####form.getDataList().size()="+form.getDataList().size());
			      //设置request
			      request.setAttribute(mapping.getAttribute(), form);
			      return mapping.findForward("Query");
			    }
			    catch (Exception e) {
			      Debug.printException(e);
			      throw ExceptionUtil.getBaseException(e);
			    }
	}
	
	
	//获取税务所数据
	public ActionForward dogetsws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GdzysJksCxForm gf = (GdzysJksCxForm) form;
		VOPackage vo = new VOPackage();
		vo.setData(gf);
                vo.setUserData(this.getUserData(request));
		vo.setProcessor(GdzysCodeConstant.GDZYS_YWCX_JKSCX_PROCESSOR);

		vo.setAction(CodeConstant.SMSB_CXDQJSLIST);
		gf = (GdzysJksCxForm) ZhsbProxy.getInstance().process(vo);
		List sws = gf.getCxswdwlist();
		response.setContentType("text/xml;charset=GB2312");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter responseOut = null;
		try {
			responseOut = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
		responseOut.write("<mb>");
		for (int i = 0; i < sws.size(); i++) {
			swdwmodel v = (swdwmodel) sws.get(i);
			responseOut.write("<gV>" + v.getSwdwid() + "</gV>");
			responseOut.write("<gU>" + v.getSwdwmc() + "</gU>");

		}
		
		responseOut.write("</mb>");
		// responseOut.flush();
		responseOut.close();

		return null;
	}
	
	
	
	  
}
