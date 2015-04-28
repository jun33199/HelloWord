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
 * <p>Title: 北京地税核心征管系统--耕地占有税减免税管理 </p>
 * <p>Description:打印减免税证明Action </p>
 * @author 开发部-霍洪波
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
		System.out.println("当前申报序列号："+gdzysJmscxForm.getSbxlh()+"#######减免税证明编号"+gdzysJmscxForm.getJmszmbh());
		
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
        System.out.println("打印能进后台##############");
        System.out.println("等待跳转");
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
