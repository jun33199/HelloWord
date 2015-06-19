package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>Title:������˰��������ϵͳ--����ռ��˰����˰���� </p>
 * <p>Description: ����˰��ѯAction</p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmscxAction extends SmsbAction{
	public GdzysJmscxAction(){
		
	}
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
	                         "<font color=\"#7C9AAB\">����˰��ѯ</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "����˰��ѯ");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws BaseException{
		GdzysJmscxForm gdzysJmscxForm=(GdzysJmscxForm) form;
		UserData ud=this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.SMSB_SAVEACTION);
		vo.setProcessor(GdzysCodeConstant.GDZYS_JMSGL_JMSCX_PROCESSOR);
		vo.setData(gdzysJmscxForm);
		vo.setUserData(ud);
		try {
			gdzysJmscxForm = (GdzysJmscxForm) ZhsbProxy.getInstance().process(vo);		
			gdzysJmscxForm.setSelfswjgdm(ud.ssdwdm);
			//���ý����ʾ��ϢΪ0(����ʾ)
			gdzysJmscxForm.setCxJgTsFlag("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute(mapping.getAttribute(), gdzysJmscxForm);
		return mapping.findForward("Show");
	}
	
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		GdzysJmscxForm gdzysJmscxForm =(GdzysJmscxForm) form;
		VOPackage vo = new VOPackage();
		UserData ud =this.getUserData(request);
		
		try {
			vo.setAction(GdzysCodeConstant.SMSB_QUERYACTION);
			vo.setProcessor(com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant.GDZYS_JMSGL_JMSCX_PROCESSOR);
			vo.setData(gdzysJmscxForm);
			vo.setUserData(ud);
			gdzysJmscxForm = (GdzysJmscxForm) ZhsbProxy.getInstance().process(vo);
			gdzysJmscxForm.setSelfswjgdm(ud.ssdwdm);
			gdzysJmscxForm.setJsjdm("");
			gdzysJmscxForm.setNsrmc("");
			gdzysJmscxForm.setPzzdwh("");
			gdzysJmscxForm.setSbxlh("");
			if(gdzysJmscxForm.getDataList().size()==0){
				gdzysJmscxForm.setCxJgTsFlag("1");
			}else{
				gdzysJmscxForm.setCxJgTsFlag("0");
			}
			request.setAttribute(mapping.getAttribute(), gdzysJmscxForm);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return  mapping.findForward("Query");
	}
	public ActionForward doPrintView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws BaseException{
		System.out.println("PrintView################");
		GdzysJmscxForm gdzysJmscxForm=(GdzysJmscxForm) form;
		String sbbxlh =(String)request.getParameter("sbxlh");
		String jmszmbh=(String)request.getParameter("jmszmbh");
		gdzysJmscxForm.setSbxlh(sbbxlh);
		gdzysJmscxForm.setJmszmbh(jmszmbh);
		System.out.println(sbbxlh+"���Ե�����");
		VOPackage vo = new VOPackage();
		UserData ud =this.getUserData(request);
		
		try {
			vo.setAction(GdzysCodeConstant.SMSB_SHOWACTION);
			vo.setProcessor(com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant.GDZYS_JMSGL_JMSCX_PROCESSOR);
			vo.setData(gdzysJmscxForm);
			vo.setUserData(ud);
			gdzysJmscxForm = (GdzysJmscxForm) ZhsbProxy.getInstance().process(vo);
			System.out.println("Ҫ���ؽ����##########");
			request.setAttribute(mapping.getAttribute(), gdzysJmscxForm);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return mapping.findForward("PrintView");
	}

	

}
