package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
/**
 * 
 * <p>Title: </p>
 * <p>Description:撤销减免税证明Action </p>
 * @author 开发部-霍洪波
 * @version 1.0
 */
public class GdzysJmsCancleAction extends SmsbAction{
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
	                        // "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
	                         "<font color=\"#7C9AAB\">耕地减免税管理</font>&gt;"+
	                         "<font color=\"#7C9AAB\">撤销减免税证明</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "撤销减免税证明");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  public ActionForward doShow(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			GdzysJmsCancleForm gdzysJmsCancleForm=new GdzysJmsCancleForm();// (GdzysJmsCancleForm) form;
			System.out.println(gdzysJmsCancleForm.getSbbxlh()+"减免税证明撤销申报序列号########");
			UserData ud=this.getUserData(request);
			String user =ud.getYhid();
			String selfswjgdm=ud.ssdwdm;
			VOPackage vo = new VOPackage();
			vo.setAction(GdzysCodeConstant.SMSB_SHOWACTION);
			vo.setProcessor(GdzysCodeConstant.GDZYS_JMSGL_JMSCANCLE_PROCESSOR);
			gdzysJmsCancleForm.setLocalUser(user);
			vo.setData(gdzysJmsCancleForm);
			vo.setUserData(ud);
			try {
				gdzysJmsCancleForm = (GdzysJmsCancleForm) ZhsbProxy.getInstance().process(vo);
				//设置查询结果提示信息为0不提示
				gdzysJmsCancleForm.setCxJgTsFlag("0");
				gdzysJmsCancleForm.setLocalUser(user);
				gdzysJmsCancleForm.setSelfswjgdm(selfswjgdm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute(mapping.getAttribute(), gdzysJmsCancleForm);
			return mapping.findForward("Show");
		}
	  public ActionForward doQuery(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			GdzysJmsCancleForm gdzysJmsCancleForm=(GdzysJmsCancleForm) form;
			UserData ud=this.getUserData(request);
			String user =ud.getYhid();
			String selfswjgdm=ud.ssdwdm;
			VOPackage vo = new VOPackage();
			vo.setAction(GdzysCodeConstant.SMSB_QUERYACTION);
			vo.setProcessor(GdzysCodeConstant.GDZYS_JMSGL_JMSCANCLE_PROCESSOR);
			gdzysJmsCancleForm.setLocalUser(user);
			vo.setData(gdzysJmsCancleForm);
			vo.setUserData(ud);
			try {
				gdzysJmsCancleForm = (GdzysJmsCancleForm) ZhsbProxy.getInstance().process(vo);
				if(gdzysJmsCancleForm.getDataList().size()==0){
					gdzysJmsCancleForm.setCxJgTsFlag("1");
				}else{
					gdzysJmsCancleForm.setCxJgTsFlag("0");
				}
				gdzysJmsCancleForm.setLocalUser(user);
				gdzysJmsCancleForm.setSelfswjgdm(selfswjgdm);
				gdzysJmsCancleForm.setJsjdm("");
				gdzysJmsCancleForm.setPzzdwh("");
				gdzysJmsCancleForm.setNsrmc("");
				gdzysJmsCancleForm.setSbbxlh("");
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute(mapping.getAttribute(), gdzysJmsCancleForm);
			System.out.println(gdzysJmsCancleForm.getDataList().size());
			return mapping.findForward("Query");
		}
	  public ActionForward doView(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			GdzysJmsCancleForm gdzysJmsCancleForm=(GdzysJmsCancleForm) form;
			UserData ud=this.getUserData(request);
			//String sbbxlh=gdzysJmsCancleForm.getSbbxlh();
			//String jmszmbh=gdzysJmsCancleForm.getJmszmbh();
			VOPackage vo = new VOPackage();
			vo.setAction(GdzysCodeConstant.SMSB_SAVEACTION);
			vo.setProcessor(GdzysCodeConstant.GDZYS_JMSGL_JMSCANCLE_PROCESSOR);
			
			vo.setData(gdzysJmsCancleForm);
			vo.setUserData(ud);
			try {
				gdzysJmsCancleForm = (GdzysJmsCancleForm) ZhsbProxy.getInstance().process(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute(mapping.getAttribute(), gdzysJmsCancleForm);
			return mapping.findForward("View");
		}
	  public ActionForward doCancle(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			GdzysJmsCancleForm gdzysJmsCancleForm=(GdzysJmsCancleForm) form;
			UserData ud=this.getUserData(request);
			VOPackage vo = new VOPackage();
			String user =ud.getYhid();
			gdzysJmsCancleForm.setLocalUser(user);
			vo.setAction(GdzysCodeConstant.SMSB_DELETEACTION);
			vo.setProcessor(GdzysCodeConstant.GDZYS_JMSGL_JMSCANCLE_PROCESSOR);
			vo.setData(gdzysJmsCancleForm);
			String selfswjgdm=ud.ssdwdm;
			vo.setUserData(ud);
			try {
				gdzysJmsCancleForm = (GdzysJmsCancleForm) ZhsbProxy.getInstance().process(vo);
				gdzysJmsCancleForm.setSelfswjgdm(selfswjgdm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute(mapping.getAttribute(), gdzysJmsCancleForm);
			//System.out.println("能进来##########");
			response.setContentType("text/html;charset=GBK");  
			PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(gdzysJmsCancleForm.getFlag());
			out.print(gdzysJmsCancleForm.getFlag());
			//return mapping.findForward("Cancle");
            return null;
		}
}
