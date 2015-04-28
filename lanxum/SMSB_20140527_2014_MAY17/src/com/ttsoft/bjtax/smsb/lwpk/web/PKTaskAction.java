package com.ttsoft.bjtax.smsb.lwpk.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.SJJHWHCommon;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统--税库行</p>
 * <p>Description: 定时计划表Action</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 技术研发部门－－康洪涛
 * @version 1.0
 */
public class PKTaskAction extends SmsbAction{

	
	 public PKTaskAction() {
		super();
	}
	 
	 UserData userData = null;

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
	                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">委托银行扣款征收</font>&gt;定时计划表维护&gt;</td>");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                                    "定时计划表维护");
	    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                                    "help/smsb/gzwh/gzwh-000.htm");
	  }
	  /**
	   * 功能：初始化定时计划表！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doInit(
			ActionMapping mapping,
            ActionForm aForm, 
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
		
		    VOPackage vo = new VOPackage();
		    PKTaskForm yForm = (PKTaskForm) aForm;
		    userData = this.getUserData(request);
		    vo.setAction(CodeConstant.SMSB_INIT);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		    vo.setData(yForm);
		    vo.setUserData(userData);
		    //初始化结果是否已经初始化
		    String isinit = null;
		  //实例化时间计划维护通用对象
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
		    try {
				isinit = (String)ZhsbProxy.getInstance().process(vo);
				yForm.setIsinit(isinit);
				//设置查询月度列表
				yForm.setCxydList(sjjhwh.getCxydList());
				//设置查询时间列表
				yForm.setZxsjList(sjjhwh.getZxsjList());
				request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return mapping.findForward("Init");
		
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
		//是否是初始化
		request.getSession().setAttribute("is_plkksjjh_show", "true");
		 	//实例化时间计划维护通用对象
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
			PKTaskForm yForm = (PKTaskForm) aForm;
			yForm.setYd("00");
			yForm.setCxrwlx("00");
			//任务类型list
			yForm.setCxlxList(sjjhwh.getCxlxList());
			//任务名称类型list
			yForm.setCxlxmcList(sjjhwh.getCxlxmcList());
			//设置查询月度列表
			yForm.setCxydList(sjjhwh.getCxydList());
			//设置查询时间列表
			yForm.setZxsjList(sjjhwh.getZxsjList());
			//初始化可用
			yForm.setIsinit("true");
			request.setAttribute("pkTaskForm", yForm);
			return mapping.findForward("Show");
		
	}
	
	
	 /**
	   * 功能：根据条件查询定时计划表！
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
		
		   request.getSession().setAttribute("is_plkksjjh_show", "false");
		   VOPackage vo = new VOPackage();
		   PKTaskForm yForm = (PKTaskForm) aForm;
		    userData = this.getUserData(request);
		    vo.setAction(CodeConstant.SMSB_QUERYACTION);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		    vo.setData(yForm);
		    vo.setUserData(userData);
		  //实例化时间计划维护通用对象
			SJJHWHCommon sjjhwh = new SJJHWHCommon();
		    try {
				List pkTaskList = (List)ZhsbProxy.getInstance().process(vo);
			    //时间计划列表
				//任务类型list
				yForm.setCxlxList(sjjhwh.getCxlxList());
				//任务名称类型list
				yForm.setCxlxmcList(sjjhwh.getCxlxmcList());
			  //设置查询月度列表
			    yForm.setCxydList(sjjhwh.getCxydList());
			  //设置查询时间列表
			    yForm.setZxsjList(sjjhwh.getZxsjList());
			    yForm.setPkTaskList(pkTaskList);
			    //判断是否能够初始化
			    if(pkTaskList.size()>0){
			    	yForm.setIsinit("false");
			    }else{
			    	yForm.setIsinit("true");
			    }

			    request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		return mapping.findForward("Query");
	}
	
	
	 /**
	   * 功能：修改定时计划表！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doSaveModify(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		VOPackage vo = new VOPackage();
		PKTaskForm yForm = (PKTaskForm) aForm;
		userData = this.getUserData(request);
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		vo.setData(yForm);
		vo.setUserData(userData);
			try {
				
				ZhsbProxy.getInstance().process(vo);
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return mapping.findForward("SaveModify");
	}
	
	
	 /**
	   * 功能：删除定时计划表！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doDelete(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 
		 System.out.println(yForm.getXh());
		 
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_DELETEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 //本年度的时间计划列表
			 List pkTaskList = (List)ZhsbProxy.getInstance().process(vo);
			 //将结果放置到前台页面
			 yForm.setPkTaskList(pkTaskList);
			 request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return mapping.findForward("Delete");
	}
	
	
	 /**
	   * 功能：添加新的定时计划！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doSaveAdd(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_SAVEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 ZhsbProxy.getInstance().process(vo);
			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		return mapping.findForward("SaveAdd");
	}
	
	 /**
	   * 功能：打印定时计划表！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doPrint(
			ActionMapping mapping,
         ActionForm aForm, 
         HttpServletRequest request,
         HttpServletResponse response) throws BaseException {

		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_PRINTDATA);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.PKTaskProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 
			 List pkTaskList = (List) ZhsbProxy.getInstance().process(vo);
			 yForm.setPkTaskList(pkTaskList);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		
		request.setAttribute("pkTaskForm", yForm);
		return mapping.findForward("Print");
	}
	
	/**
	   * 功能：测试生成待扣信息功能！
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	
	public ActionForward doGenDKXX(ActionMapping mapping,
								 ActionForm aForm, 
								 HttpServletRequest request,
								 HttpServletResponse response) throws BaseException {
		 
		System.out.println("生成批扣action");
		
		 VOPackage vo = new VOPackage();
		 PKTaskForm yForm = (PKTaskForm) aForm;
		 userData = this.getUserData(request);
		 vo.setAction(CodeConstant.SMSB_SAVEACTION);
		 vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
		 vo.setData(yForm);
		 vo.setUserData(userData);
		 try {
			 ZhsbProxy.getInstance().process(vo);
			 
			 System.out.println("生成待扣返回action");
//			 yForm.setPkTaskList(pkTaskList);
//			 request.setAttribute("pkTaskForm", yForm);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		return mapping.findForward("Test1");
	}
	
	
	
}
