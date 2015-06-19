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
 * <p>Description:��������˰֤��Action </p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmsCancleAction extends SmsbAction{
	 /**
	   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
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
	                         "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
	                        // "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
	                         "<font color=\"#7C9AAB\">���ؼ���˰����</font>&gt;"+
	                         "<font color=\"#7C9AAB\">��������˰֤��</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "��������˰֤��");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  public ActionForward doShow(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws BaseException{
			GdzysJmsCancleForm gdzysJmsCancleForm=new GdzysJmsCancleForm();// (GdzysJmsCancleForm) form;
			System.out.println(gdzysJmsCancleForm.getSbbxlh()+"����˰֤�������걨���к�########");
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
				//���ò�ѯ�����ʾ��ϢΪ0����ʾ
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
			//System.out.println("�ܽ���##########");
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
