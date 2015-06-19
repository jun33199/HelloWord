package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx15.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.ActionHelper;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 * 
 * @author ���ǽ�
 * @date  2010-1-8
 *
 */
public class Basx15Action extends SmsbAction{
	/**
	 * ����Ա����
	 */
	private UserData userData = null;
	
	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QysdsnbForm
	 * @param httpServletRequest
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
	
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
		.setAttribute(
				CodeConstant.SMSB_HEADER_POSITION,
		"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"��ҵ����˰�����˰�걨");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	
	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		Basx15Form basx15Form = (Basx15Form) form;
		//���ü��������ͱ������
		if(!"".equals(basx15Form.getAdd_jsjdm())){
			basx15Form.setJsjdm(basx15Form.getAdd_jsjdm());
		}
		if(!"".equals(basx15Form.getAdd_band())){
			basx15Form.setBand(basx15Form.getAdd_band());
			//������ʼ�ͽ�ֹ����
			basx15Form.setQsrq(basx15Form.getBand()+"-01-01");
			basx15Form.setJzrq(basx15Form.getBand()+"-12-31");
			basx15Form.setMr_band(basx15Form.getBand());
		}
		userData = this.getUserData(request);
		
		basx15Form.setMr_lrr(userData.getYhid());
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		basx15Form.setMr_lrrq(sf.format(new Date()));
		
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD).equals(basx15Form.getCzlx())){
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
		}else{
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
		}
		try {
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), basx15Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_CHECK).equals(basx15Form.getCzlx())){
			return mapping.findForward("Check");
		}else if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_VIEW).equals(basx15Form.getCzlx())){
			return mapping.findForward("View");
		}else{
			return mapping.findForward("Show");
		}
		
	}
	
	/**
	 * ����ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doJssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws BaseException {
		Basx15Form basx15Form = (Basx15Form) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setUserData(userData);
		try {
			//����У��
			CheckBean checkBean =QysdsUtil.setCheckInf(basx15Form);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), basx15Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx15Form);
		String czlx=basx15Form.getCzlx();
		if("2".equals(czlx)){
			return mapping.findForward("Show");
		}else{
			return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
		}
	}
	
	/**
	 * ����ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doBg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws BaseException {
		Basx15Form basx15Form = (Basx15Form) form;
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_BGACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setUserData(userData);
		try {
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), basx15Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx15Form);
		
		return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
		
	}
	
	/**
	 * ��������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		Basx15Form basx15Form = (Basx15Form) form;
		
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), basx15Form);
		return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
	}
	
	/**
     * ���Ž������벢��ӡҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doSMShPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx15Form basx15Form = (Basx15Form) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setUserData(userData);
		try {
			//����У��
			CheckBean checkBean =QysdsUtil.setCheckInf(basx15Form);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
			
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx15Form.getBasqwsh()+"&czlx="+basx15Form.getCzlx();
    	return new ActionForward(url);
	}
	
	/**
     * ������˲���ӡҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doWSShPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx15Form basx15Form = (Basx15Form) form;
    	
    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_UPDATEACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setUserData(userData);
		try {	
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx15Form.getBasqwsh()+"&czlx="+basx15Form.getCzlx();
    	return new ActionForward(url);
	}
    
    /**
     * �鿴ʱ��ӡҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doPrint (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx15Form basx15Form = (Basx15Form) form;
    	
    	String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx15Form.getBasqwsh()+"&czlx="+basx15Form.getCzlx();
    	return new ActionForward(url);
	}
    
	 /**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
	}   
    
    /**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QysdsnbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doZfbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws BaseException {
		Basx15Form basx15Form = (Basx15Form) form;	
		userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX15_PROCESSOR);
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		
		try {
			basx15Form = (Basx15Form) SbzlProxy.getInstance().process(vo);	
			ActionHelper.initialZfbgSelectItem(request);
			request.setAttribute(mapping.getAttribute(), basx15Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Zfbg");
		
		
	}
	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doOperate (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	Basx15Form basx15Form = (Basx15Form) form;

    	userData = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_ZFBGACTION);
		vo.setData(basx15Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBAJLMAIN_PROCESSOR);
		vo.setUserData(userData);
		try {	
			String alert=(String)SbzlProxy.getInstance().process(vo);
			if(alert!=null&&alert.length()>0){
				request.setAttribute("ALERT_STR", alert);
			}
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return doZfbg(mapping,basx15Form,request,response);
	}   
    
    
	 /**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBackZfbg (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY_ZFBG);
	}   
}