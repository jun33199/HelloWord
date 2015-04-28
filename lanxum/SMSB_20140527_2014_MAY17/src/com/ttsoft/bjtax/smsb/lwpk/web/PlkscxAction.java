package com.ttsoft.bjtax.smsb.lwpk.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.PlkscxExcelUtil;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 *查询统计批量扣税情况action
 *wangxq 
 * 20130718
 */
public class PlkscxAction extends SmsbAction{
	/**
	   * 公共的前置处理方法，每次进入页面都会被调用<BR>
	   * 设置页面显示时使用的导航信息和标题
	   * @param mapping The ActionMapping used to select this instance
	   * @param actionForm The optional ActionForm bean for this request (if any)
	   * @param httpServletRequest The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */

	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm actionForm,
	                                HttpServletRequest httpServletRequest,
	                                HttpServletResponse response)

	  {
	    super.initialRequest(mapping, actionForm, httpServletRequest, response);
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">综合查询</font>&gt;批量扣税综合查询&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "统计批量扣税查询");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                                    "help/smsb/gzwh/gzwh-000.htm");
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
		//System.out.println("PlkscxAction################doShow");
		PlkscxForm pf = (PlkscxForm) aForm;
		pf.setQueryFlag(false);
		VOPackage vo = new VOPackage();
		UserData userData = this.getUserData(request);
		String yhjb=userData.yhjb;
		/*
		System.out.println("################userData.jdid="+userData.jdid);
		System.out.println("################userData.ssdwdm="+userData.ssdwdm);
		System.out.println("################userData.ssdwmc="+userData.ssdwmc);
		System.out.println("################userData.yhid="+userData.yhid);
		System.out.println("################userData.yhmc="+userData.yhmc);
		*/
		//System.out.println("################yhjb="+yhjb);	
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
		vo.setProcessor(CodeConstant.PLBCX_Processor);
		vo.setData(pf);
		vo.setUserData(userData);
		pf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
		
		
		//获取银行数据
		vo.setAction(CodeConstant.SMSB_CXYHLIST);
		vo.setProcessor(CodeConstant.PLBCX_Processor);
		vo.setData(pf);
		vo.setUserData(userData);
		pf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
		
		pf.setDataList(new ArrayList());
		request.setAttribute(mapping.getAttribute(), pf);
		return mapping.findForward("Show");
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}
	
	//获取税务所数据
	public ActionForward dogetsws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PlkscxForm gf = (PlkscxForm) form;
		VOPackage vo = new VOPackage();
		vo.setData(gf);
                vo.setUserData(this.getUserData(request));
		vo.setProcessor(CodeConstant.PLBCX_Processor);

		vo.setAction(CodeConstant.SMSB_CXDQJSLIST);
		gf = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
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

	
	
	
	 /**
	   * 功能：批量扣税信息！
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
				  PlkscxForm form = (PlkscxForm)aForm;
				  form.setYhjb(userData.yhjb);
				  form.setQueryFlag(true);
				 // form.setPageSize(1);
				//获取分局数据
					vo.setAction(CodeConstant.SMSB_DQJSLIST);
					vo.setProcessor(CodeConstant.PLBCX_Processor);
					vo.setData(form);
					vo.setUserData(userData);
					form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
					
					
					//获取银行数据
					vo.setAction(CodeConstant.SMSB_CXYHLIST);
					vo.setProcessor(CodeConstant.PLBCX_Processor);
					vo.setData(form);
					vo.setUserData(userData);
					form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
					form.setPageSize(10);
			      vo.setData(form);
			      vo.setUserData(this.getUserData(request));
			      //action参数
			      vo.setAction(CodeConstant.SMSB_QUERYACTION);
			      //processor参数
			      vo.setProcessor(CodeConstant.PLBCX_Processor);
			      //返回form
			      form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
			      
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
	
	 /**
	   * 功能：批量扣税详细信息！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	public ActionForward doQueryKkDetail(
		ActionMapping mapping,
		ActionForm aForm, 
		HttpServletRequest request,
		HttpServletResponse response) throws BaseException {
		
		   VOPackage vo = new VOPackage(); 
			  try {
				  UserData userData = this.getUserData(request);
			      //清除数据
				  PlkscxForm form = (PlkscxForm)aForm;
				  form.setYhjb(userData.yhjb);
				  vo.setData(form);
			      vo.setAction(CodeConstant.SMSB_QUERYA_DETATILACTION);
			      //processor参数
			      vo.setProcessor(CodeConstant.PLBCX_Processor);
			      //返回form
			      form = (PlkscxForm) ZhsbProxy.getInstance().process(vo);
			      
			      System.out.println("doQueryKkDetail#####form.getDataList().size()="+form.getDataList().size());
			      //设置request
			      request.setAttribute(mapping.getAttribute(), form);
			      return mapping.findForward("QueryDetail");
			    }
			    catch (Exception e) {
			      Debug.printException(e);
			      throw ExceptionUtil.getBaseException(e);
			    }
	}
	
	
	/**
	   * 生成Excel文件处理
	   * @param mapping The ActionMapping used to select this instance
	   * @param aFrom The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */

	  public ActionForward doExport(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         Exception {
		  super.doInit(mapping, form, request, response);
      //获得当前的userData对象
		  UserData ud = null;
		  VOPackage vo = new VOPackage();
		  //执行Processor
		  OutputStream os = response.getOutputStream();
	      try {
	    	 
	    	  
	    	  //初始化
	          ud = this.getUserData(request);
	          PlkscxForm theForm = (PlkscxForm)form;
	          theForm.setYhjb(ud.yhjb);
	          //reset页面对象
	         // theForm.reset(mapping, request);
	          //调用EJB
	          vo.setAction(CodeConstant.SMSB_EXPORT);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.PLBCX_Processor);
	          theForm = (PlkscxForm) ZhsbProxy.getInstance().process(vo); 
	          String currDate = TinyTools.Date2String(new Date(System.
		                currentTimeMillis()));
		            //文件名称
		            String fileName = "批量扣款查询结果".concat(currDate).concat(".xls");
		            //字符集转换
		            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
		                fileName);
	        	  response.addHeader("Content-Disposition",
	  			"attachment; filename=\"" + encodeFileName + "\"");
	            PlkscxExcelUtil plkscxExcelUtil = new PlkscxExcelUtil();
	            plkscxExcelUtil.getPlkscxExcel(os, (List)theForm.getDataList());
	          //}
	          //else {
	        	//  theForm.setMessage("没有查询到数据，无法导出Excel文件");
	         // }
	          //request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }finally{
	        	os.flush();
				os.close();
				os = null ;
	        }
	        
	        return null;
	      }
	  /**
	   * 生成Excel文件处理
	   * @param mapping The ActionMapping used to select this instance
	   * @param aFrom The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */

	  public ActionForward doExportDetail(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         Exception {
		  super.doInit(mapping, form, request, response);
      //获得当前的userData对象
		  UserData ud = null;
		  VOPackage vo = new VOPackage();
		  //执行Processor
		  OutputStream os = response.getOutputStream();
	      try {
	    	 
	    	  
	    	  //初始化
	          ud = this.getUserData(request);
	          PlkscxForm theForm = (PlkscxForm)form;
	          theForm.setYhjb(ud.yhjb);
	          //reset页面对象
	         // theForm.reset(mapping, request);
	          //调用EJB
	          vo.setAction(CodeConstant.SMSB_EXPORTDETAIL);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.PLBCX_Processor);
	          theForm = (PlkscxForm) ZhsbProxy.getInstance().process(vo); 
	          String currDate = TinyTools.Date2String(new Date(System.
		                currentTimeMillis()));
		            //文件名称
		            String fileName = "批量扣款查询结果明细".concat(currDate).concat(".xls");
		            //字符集转换
		            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
		                fileName);
	        	  response.addHeader("Content-Disposition",
	  			"attachment; filename=\"" + encodeFileName + "\"");
	            PlkscxExcelUtil plkscxExcelUtil = new PlkscxExcelUtil();
	            plkscxExcelUtil.getPlkscxDetailExcel(os, (List)theForm.getDataList());
	          //}
	          //else {
	        	//  theForm.setMessage("没有查询到数据，无法导出Excel文件");
	         // }
	          //request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }finally{
	        	os.flush();
				os.close();
				os = null ;
	        }
	        
	        return null;
	      }
	
}
