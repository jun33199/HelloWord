package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
/**
 * 
 * <p>Title: ������˰��������ϵͳ--����ռ��˰����˰���� </p>
 * <p>Description:��ӡ����˰֤��Action </p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmszmPrintAction extends SmsbAction{
	
	public GdzysJmszmPrintAction(){
		
	}
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws BaseException{
		
		GdzysJmscxForm gdzysJmscxForm =(GdzysJmscxForm) form;
		String sbbxlh=request.getParameter("sbxlh");
		String jmszmbh=request.getParameter("jmszmbh");
		gdzysJmscxForm.setSbxlh(sbbxlh);
		gdzysJmscxForm.setJmszmbh(jmszmbh);
		VOPackage vo = new VOPackage();
		UserData ud =this.getUserData(request);
		System.out.println("��ǰ�걨���кţ�"+gdzysJmscxForm.getSbxlh()+"#######����˰֤�����"+gdzysJmscxForm.getJmszmbh());
		
		try {
			vo.setAction(GdzysCodeConstant.SMSB_SAVEACTION);
			vo.setProcessor(com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant.GDZYS_JMSGL_JMSZM_PROCESSOR);
			vo.setData(gdzysJmscxForm);
			vo.setUserData(ud);
			gdzysJmscxForm = (GdzysJmscxForm) ZhsbProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), gdzysJmscxForm);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        System.out.println("��ӡ�ܽ���̨##############");
        System.out.println("�ȴ���ת");
		return  mapping.findForward("Query");
//        try {
//        	response.setContentType("text/html;charset=GBK");
//			PrintWriter out =response.getWriter();
//			out.print("success");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        return null;
	}

}
