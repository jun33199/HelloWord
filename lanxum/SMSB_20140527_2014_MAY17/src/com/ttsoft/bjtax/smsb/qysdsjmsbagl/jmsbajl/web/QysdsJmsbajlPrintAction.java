package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税减免税备案管理
 * </p>
 * 
 * @author 开发二部：刘超
 * @version 1.1
 */
public class QysdsJmsbajlPrintAction extends SmsbAction {
	/**
	 * 操作员数据
	 */
	private UserData userData = null;
	
	/**
	 * 打印页面
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		
		QysdsJmsbajlPrintForm qysdsJmsbajlPrintForm = (QysdsJmsbajlPrintForm) form;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_PRINTACTION);
		vo.setData(qysdsJmsbajlPrintForm);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLPRINT_PROCESSOR);
		vo.setUserData(userData);
		try {
			qysdsJmsbajlPrintForm = (QysdsJmsbajlPrintForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), qysdsJmsbajlPrintForm);
		} catch (Exception ex) {
			
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		if("4".equals(qysdsJmsbajlPrintForm.getSqzt())){
			return mapping.findForward("Sdhz");
		}else{
			return mapping.findForward("Bysltzs");
		}
		
	}
	
	/**
     * 返回页面
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	QysdsJmsbajlPrintForm qysdsJmsbajlPrintForm = (QysdsJmsbajlPrintForm) form;
    	String jmbasxdm = qysdsJmsbajlPrintForm.getJmbasxdm();
    	String basqwsh = qysdsJmsbajlPrintForm.getBasqwsh();
    	String czlx = qysdsJmsbajlPrintForm.getCzlx();
    	String basqbh = qysdsJmsbajlPrintForm.getBasqbh();
    	if("4".equals(czlx)){
    		String url = ActionHelper.getForwardPath(jmbasxdm,basqwsh,czlx,basqbh);
    		
    		return new ActionForward(url);
    	}else{
    		return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
    	}
	}   
	
}
