package com.ttsoft.bjtax.smsb.lwpk.web;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.DhkscxExcelUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *查询批量扣款action
 *kanght 
 * 201307
 */
public class DhkscxAction extends SmsbAction{

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
	                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">综合查询</font>&gt;单户批量扣税查询&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "单户批量扣税查询");
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
		//判断是否是初始化
		request.getSession().setAttribute("is_show", "true");
		return mapping.findForward("Show");
		
	}
	 /**
	   * 功能：查询批扣信息！
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
		//判断是否是初始化
		request.getSession().setAttribute("is_show", "false");
		   VOPackage vo = new VOPackage(); 
			  try {
			      //清除数据
				  DhkscxForm form = (DhkscxForm)aForm;
			      vo.setData(form);
			      vo.setUserData(this.getUserData(request));
			      //action参数
			      vo.setAction(CodeConstant.SMSB_QUERYACTION);
			      //processor参数
			      vo.setProcessor(CodeConstant.DHKSCX_Processor);
			      //返回form
			      DhkscxForm theForm = (DhkscxForm) ZhsbProxy.getInstance().process(vo);
			      //设置request
			      request.setAttribute(mapping.getAttribute(), theForm);
			      return mapping.findForward("Query");
			    }
			    catch (Exception ex) {
			    	ex.printStackTrace();
			      throw ExceptionUtil.getBaseException(ex);
			    }
		
	}
	
	/**
	 * 分页查询
	 * 
	 * 
	 */
	public ActionForward doChangePage(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws
			BaseException {
			super.doInit(mapping, form, request, response);
			//获得当前的userData对象
			UserData ud = null;
			DhkscxForm zf = null;
			VOPackage vo = new VOPackage();
			//执行Processor
			try {
			//初始化
			ud = this.getUserData(request);
			zf = (DhkscxForm) form;
			if (zf == null) {
			zf = new DhkscxForm();
			}
			//调用EJB
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			vo.setUserData(ud);
			vo.setData(zf);
			vo.setProcessor(CodeConstant.DHKSCX_Processor);
			zf = (DhkscxForm) ZhsbProxy.getInstance().process(vo);
			//回写session数据
			request.setAttribute(mapping.getAttribute(), zf);
			}
			catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
			}
			return mapping.findForward("Query");
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

	  public ActionForward doSaveExcel(ActionMapping mapping, ActionForm form,
	                                   HttpServletRequest request,
	                                   HttpServletResponse response) throws
	         BaseException {
		  super.doInit(mapping, form, request, response);
        //获得当前的userData对象
		  UserData ud = null;
		  DhkscxForm theForm = null;
		  VOPackage vo = new VOPackage();
		  //执行Processor
	      try {
	    	  //初始化
	          ud = this.getUserData(request);
	          theForm = (DhkscxForm) form;
	          //reset页面对象
	          theForm.reset(mapping, request);
	          //调用EJB
	          vo.setAction(CodeConstant.SMSB_QUERYACTION);
	          vo.setUserData(ud);
	          vo.setData(theForm);
	          vo.setProcessor(CodeConstant.DHKSCX_Processor);
	          theForm = (DhkscxForm) ZhsbProxy.getInstance().process(vo); 
	          if (theForm.getPkjbsjModel() != null && theForm.getPlkkdhcxlist().size()>0 ) {
	        	  //当前时间
	            String currDate = TinyTools.Date2String(new Date(System.
	                currentTimeMillis()));
	            //文件名称
	            String fileName = "单户批量扣款状态查询结果".concat(currDate).concat(".xls");
	            //字符集转换
	            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
	                fileName);
	            response.resetBuffer();
	            response.setHeader("Content-disposition",
	                               "attachment; filename=" + encodeFileName);
	            response.setContentType("application/vnd.ms-excel");
	            DhkscxExcelUtil dhkscxExcel = new DhkscxExcelUtil();
	            dhkscxExcel.getDhkscxExcel(response.getOutputStream(), theForm.getPlkkdhcxlist(),theForm.getPkjbsjModel());
	          }
	          else {
	        	  theForm.setMessage("没有查询到数据，无法导出Excel文件");
	          }
	          //清除结果集
	          theForm.setPlkkdhcxlist(new ArrayList());
	          //回写session数据
	          request.setAttribute(mapping.getAttribute(), theForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }
	        
	        return null;
	      }
	  
	  
	  
	  
	  
	  
}
