package com.ttsoft.bjtax.smsb.zjyjmsb.cx.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.ZjycxExcelUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.web.ZjyjmcxForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 再就业减免税申报查询Action</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0
 */
public class ZjyjmcxAction 
        extends SmsbAction{
	 
	   /**
	   * 数据集对象（包括Form和UserData对象）
	   */
	  private VOPackage vo = new VOPackage();
	   int rowNum = 0;
	   
	  public ZjyjmcxAction (){
		  
	  }
	  /**
	   * 公共的前置处理方法，每次进入页面都会被调用
	   * 设置页面显示时使用的导航信息和标题
	   * @param mapping The ActionMapping used to select this instance
	   * @param actionForm The optional ActionForm bean for this request (if any)
	   * @param httpServletRequest The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   */

	  protected void initialRequest(ActionMapping mapping,
	                                ActionForm actionForm,
	                                HttpServletRequest  request,
	                                HttpServletResponse response)

	  {  
		request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	           "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>再就业减免税申报表查询 ");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "");
	  }
	  /**
	   * doShow
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @exception BaseException
	   */
	  public ActionForward doShow(ActionMapping mapping,
	                              ActionForm form,
	                              HttpServletRequest request,
	                              HttpServletResponse response) throws
	                              BaseException {
        //清除数据
		removeForm(mapping, request);
	    ZjyjmcxForm zjycxForm = null;
	    VOPackage vo = new VOPackage(); 
	  try {
	      //清除数据
		  zjycxForm = (ZjyjmcxForm)form;
		  zjycxForm.setDataList(new ArrayList());
	      vo.setData(zjycxForm);
	      vo.setUserData(this.getUserData(request));
	      vo.setAction(CodeConstant.SMSB_SHOWACTION);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      return mapping.findForward("Show");
	    }
	    catch (Exception e) {
	      Debug.printException(e);
	      throw ExceptionUtil.getBaseException(e);
	    }

	  }
	  /**
	   * 查询处理
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request 
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @throws BaseException
	   */
	  public ActionForward doQuery(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) throws
	      BaseException {
		  ZjyjmcxForm zjycxForm = (ZjyjmcxForm)form;
		  //每次查询页码初始化成第一页
		  zjycxForm.setNextPage("1");
	    try {
	      vo.setAction(CodeConstant.SMSB_QUERYACTION);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      vo.setData(zjycxForm);
	      vo.setUserData(this.getUserData(request));
	      zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      System.out.println("zjycxForm.getDataList().size():" + zjycxForm.getDataList().size());
	      if (zjycxForm.getDataList().size() == 0) {
	        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
	                             , getPrintMessage("没有符合条件的信息", true));
	      }

	      //传递form
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      return mapping.findForward("Query");
	    }
	    catch (Exception e) {
	    	zjycxForm.setNextPage("0");
	    	zjycxForm.setTotalpage("0");
	      try {
	        vo.setAction(CodeConstant.SMSB_SHOWACTION);
	        vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	        vo.setData(zjycxForm);
	        vo.setUserData(this.getUserData(request));
	        zjycxForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      }
	      catch (Exception ex) {
	      }
	      //传递form
	      request.setAttribute(mapping.getAttribute(), zjycxForm);
	      Debug.printException(e);
	      throw ExceptionUtil.getBaseException(e);
	    }

	  }
	  /**
	   * doChangePage
	   * @param mapping The ActionMapping used to select this instance
	   * @param form The optional ActionForm bean for this request (if any)
	   * @param request The HTTP request we are processing
	   * @param response The HTTP response we are creating
	   * @return the element previously at the specified position.
	   * @exception BaseException
	   */
	  public ActionForward doChangePage(ActionMapping mapping,
	                                    ActionForm form,
	                                    HttpServletRequest request,
	                                    HttpServletResponse response) throws
	      BaseException {
	    super.doInit(mapping, form, request, response);
	    //获得当前的userData对象
	    UserData ud = null;
	    ZjyjmcxForm zf = null;
	    VOPackage vo = new VOPackage();
	    //执行Processor
	    try {
	      //初始化
	      ud = this.getUserData(request);
	      zf = (ZjyjmcxForm) form;
	      if (zf == null) {
	    	  zf = new ZjyjmcxForm();
	      }
	      //调用EJB
	      vo.setAction(CodeConstant.SMSB_QUERYACTION);
	      vo.setUserData(ud);
	      vo.setData(zf);
	      vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	      zf = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);
	      //回写session数据
	      request.setAttribute(mapping.getAttribute(), zf);


	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	      throw ExceptionUtil.getBaseException(ex);
	    }
	    return mapping.findForward("Show");
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
		  ZjyjmcxForm zjyForm = null;
		  VOPackage vo = new VOPackage();
		  //执行Processor
	      try {
	    	  //初始化
	          ud = this.getUserData(request);
	          zjyForm = (ZjyjmcxForm) form;
	          //reset页面对象
	          zjyForm.reset(mapping, request);
	          if (zjyForm == null) {
	        	  zjyForm = new ZjyjmcxForm();
	          }
	          //调用EJB
	          vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
	          vo.setUserData(ud);
	          vo.setData(zjyForm);
	          vo.setProcessor(CodeConstant.ZJYJMCX_PROCESSOR);
	          zjyForm = (ZjyjmcxForm) ZhsbProxy.getInstance().process(vo);        	         
	          if (zjyForm.getDataList().size() > 0) {
	            String currDate = TinyTools.Date2String(new Date(System.
	                currentTimeMillis()));
	            String fileName = "再就业减免税申报查询结果".concat(currDate).concat(".xls");
	            String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
	                fileName);
	            response.resetBuffer();
	            response.setHeader("Content-disposition",
	                               "attachment; filename=" + encodeFileName);
	            response.setContentType("application/vnd.ms-excel");
	            ZjycxExcelUtil zjyExcel = new ZjycxExcelUtil();
	            zjyExcel.getZjycxExcel(response.getOutputStream(), zjyForm.getDataList());
	          }
	          else {
	        	  zjyForm.setMessage("没有查询到数据，无法导出Excel文件");
	          }
	          //清除结果集
	          zjyForm.setDataList(new ArrayList());
	          //回写session数据
	          request.setAttribute(mapping.getAttribute(), zjyForm);
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	          throw ExceptionUtil.getBaseException(ex);
	        }
	        /**
	        if (zjyForm.getDataList().size() > 0) {
	          return null;
	        }
	        else {
	        	System.out.println("SaveExcel************");
	          return mapping.findForward("SaveExcel");
	        }
	        **/
	        return null;
	      }
	  /**
	   * 转换输出信息格式
	   * @param message 输出内容
	   * @param successFlag 成功|失败
	   * @return
	   */
	  private String getPrintMessage(String message, boolean successFlag) {
	    StringBuffer printMessage = new StringBuffer();
	    if (successFlag) { //成功信息
	      printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
	          .append(message).append("！</font></strong>");
	    }
	    else { //失败信息
	      printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
	          .append(message).append("！</font></strong>");
	    }
	    return printMessage.toString();
	  }
}
