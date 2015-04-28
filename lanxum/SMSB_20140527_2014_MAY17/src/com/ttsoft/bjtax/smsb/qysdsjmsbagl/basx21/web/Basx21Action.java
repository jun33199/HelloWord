package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx21.web;

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
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web.Basx01ActionHelper;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web.Basx01Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.ActionHelper;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


public class Basx21Action extends SmsbAction
{
	//  ����Ա����
	private UserData userData = null;

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping struts.action.ActionMapping
	 * @param actionForm QysdsnbForm
	 * @param httpServletRequest  HttpServletRequest
	 * @param response  HttpServletResponse
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
	
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
		.setAttribute(
				CodeConstant.SMSB_HEADER_POSITION,
		"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;¼����˰����ҵ����˰����˰��������</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"¼����˰����ҵ����˰����˰��������");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	/**
	 * ��ʼ��ҳ������
	 *  
	 * @param mapping   struts.action.ActionMapping
	 * @param form  QysdsnbForm
	 * @param request   HttpServletRequest
	 * @param response    HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException    ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doShow(ActionMapping mapping, 
								ActionForm form,
								HttpServletRequest request, 
								HttpServletResponse response)	throws BaseException 
	{
		Basx21Form basx21Form = (Basx21Form) form;
		userData = this.getUserData(request);
		
		/*<1>����¼�뱸��ҳ��Ļ�����ʾ��Ϣ*/
		if(!"".equals(basx21Form.getAdd_jsjdm()))
		{		
			basx21Form.setJsjdm(basx21Form.getAdd_jsjdm());				//���ü��������ͱ������
		}
		if(!"".equals(basx21Form.getAdd_band()))
		{
			basx21Form.setBand(basx21Form.getAdd_band());				//������ʼ�ͽ�ֹ����
			basx21Form.setQsrq(basx21Form.getBand()+"-01-01");
			basx21Form.setJzrq(basx21Form.getBand()+"-12-31");
			basx21Form.setMr_band(basx21Form.getBand());
		}
		basx21Form.setMr_lrr(userData.getYhid());						//¼����
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		basx21Form.setMr_lrrq(sf.format(new Date()));					//¼��ʱ��
		
		/*<2>���ú�̨�߼�*/
		VOPackage vo = new VOPackage();
		vo.setUserData(userData);
		vo.setData(basx21Form);
		vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
		
		//���ݲ������͵Ĳ�ͬѡ��ͬ��pro���������߼�
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD).equals(basx21Form.getCzlx())) 
		{
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
		}else{
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
		}
		try {
			basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), basx21Form);
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
		
		/*<3>���ݲ������Ͳ�ͬ��ѡ��ͬ����ͼ����Դ*/
		if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_CHECK).equals(basx21Form.getCzlx())){
			return mapping.findForward("Check");
		}else if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_VIEW).equals(basx21Form.getCzlx())){
			return mapping.findForward("View");
		}else{
			if(String.valueOf(CodeConstant.QYSDSJMSBAJL_CZLX_ADD).equals(basx21Form.getCzlx()))
			{
				basx21Form.setMzqsnd(basx21Form.getBand());									//���������������Ĭ��������ʼ���Ϊ�������
			}
			return mapping.findForward("Show");
		}	
	}
	
		/**
		 * ��������ҳ������
		 * 
		 * @param mapping  struts.action.ActionMapping
		 * @param form   QysdsnbForm
		 * @param request   HttpServletRequest
		 * @param response  HttpServletResponse
		 * @return actionMapping.findForward����תĿ��
		 * @throws BaseException
		 *             ϵͳ��׽�쳣�������쳣�����׳�
		 */	
		public ActionForward doJssq(ActionMapping mapping, 
									ActionForm form,
									HttpServletRequest request, 
									HttpServletResponse response)throws BaseException
		{
			Basx21Form basx21Form = (Basx21Form) form;
			ActionForward forward = null;
			if(!basx21Form.getMzzznd().equals("2015"))
			{basx21Form.setMzzznd("2015");}
			userData = this.getUserData(request);
			
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setData(basx21Form);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setUserData(userData);
			try {
				
				//����У��
				CheckBean checkBean =QysdsUtil.setCheckInf(basx21Form);	
				QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
				
			/*--�����߼�����--*/
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
				request.setAttribute(mapping.getAttribute(), basx21Form);
			} catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);		// ϵͳ��׽�쳣�������쳣�����׳�
			}
			
			/*--ѡ����ͼ��Դ--*/
			if("2".equals(basx21Form.getCzlx())){
	            forward=mapping.findForward("Show");
	        }else{
	            forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
	        }     
			request.setAttribute(mapping.getAttribute(), basx21Form);		
			return forward;
		}
		
		/**
		 * ���ҳ������
		 * 
		 * @param mapping    struts.action.ActionMapping
		 * @param form    QysdsnbForm
		 * @param request   HttpServletRequest
		 * @param response   HttpServletResponse
		 * @return actionMapping.findForward����תĿ��
		 * @throws BaseException      ϵͳ��׽�쳣�������쳣�����׳�
		 */
		public ActionForward doBg(ActionMapping mapping, 
								  ActionForm form,
								  HttpServletRequest request, 
								  HttpServletResponse response)throws BaseException 
		{
			Basx21Form basx21Form = (Basx21Form) form;
			ActionForward forward = null;
			if(!basx21Form.getMzzznd().equals("2015"))
			{basx21Form.setMzzznd("2015");}
			userData = this.getUserData(request);
			
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_BGACTION);
			vo.setData(basx21Form);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setUserData(userData);
			try {
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
				request.setAttribute(mapping.getAttribute(), basx21Form);
			} catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			}

	        forward=new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
			request.setAttribute(mapping.getAttribute(), basx21Form);		
			return forward;
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
	    	Basx21Form basx21Form = (Basx21Form) form;
	    	if(!basx21Form.getMzzznd().equals("2015"))
			{basx21Form.setMzzznd("2015");}
	    	userData = this.getUserData(request);
	    	
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setData(basx21Form);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setUserData(userData);
			try {	
				//����У��
				CheckBean checkBean =QysdsUtil.setCheckInf(basx21Form);	
				QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject4, checkBean);
				
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
			} catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			}
			String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx21Form.getBasqwsh()+"&czlx="+basx21Form.getCzlx();
	    	return new ActionForward(url);
		}
	    
	    /**
		 * ��������
		 * 
		 * @param mapping       struts.action.ActionMapping
		 * @param form       QysdsnbForm
		 * @param request        HttpServletRequest
		 * @param response     HttpServletResponse
		 * @return actionMapping.findForward����תĿ��
		 * @throws BaseException        ϵͳ��׽�쳣�������쳣�����׳�
		 */
		public ActionForward doCheck(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		throws BaseException {
			Basx21Form basx21Form = (Basx21Form) form;
			
			//�������ݿ����
			userData = this.getUserData(request);
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_UPDATEACTION);
			vo.setData(basx21Form);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setUserData(userData);
			try {	
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
			} catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			}
			request.setAttribute(mapping.getAttribute(), basx21Form);
			
			//ѡ����ͼ��Դ
			return new ActionForward(ForwardPath.PAGE_JMSBAJL_QUERY);
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
	    public ActionForward doWSShPrint (ActionMapping mapping,
	    								  ActionForm form,
	    								  HttpServletRequest request,
	    								  HttpServletResponse response)throws BaseException
		{
	    	Basx21Form basx21Form = (Basx21Form) form;
	    	
	    	userData = this.getUserData(request);
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_UPDATEACTION);
			vo.setData(basx21Form);
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setUserData(userData);
			try {	
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);
			} catch (Exception ex) {
				// ϵͳ��׽�쳣�������쳣�����׳�
				throw ExceptionUtil.getBaseException(ex);
			}
			String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx21Form.getBasqwsh()+"&czlx="+basx21Form.getCzlx();
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
	    	Basx21Form basx21Form = (Basx21Form) form;
	    	
	    	String url = ForwardPath.PAGE_JMSBAJL_PRINT+"&basqwsh="+basx21Form.getBasqwsh()+"&czlx="+basx21Form.getCzlx();
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
		 * ����
		 * 
		 * @param mapping       struts.action.ActionMapping
		 * @param form     QysdsnbForm
		 * @param request    HttpServletRequest
		 * @param response     HttpServletResponse
		 * @return actionMapping.findForward����תĿ��
		 * @throws BaseException
		 *             ϵͳ��׽�쳣�������쳣�����׳�
		 */
		public ActionForward doZfbg(ActionMapping mapping, 
									ActionForm form,
									HttpServletRequest request, 
									HttpServletResponse response)
		throws BaseException 
		{
			Basx21Form basx21Form = (Basx21Form) form;	
			userData = this.getUserData(request);
			VOPackage vo = new VOPackage();
			vo.setUserData(userData);
			vo.setData(basx21Form);
			
			//��ѯ���м�¼�ͻ�����Ϣ
			vo.setProcessor(CodeConstant.SMSB_QYSDSJMSBA_BASX21_PROCESSOR);
			vo.setAction(CodeConstant.SMSB_QUERYACTION);
			
			try {
				basx21Form = (Basx21Form) SbzlProxy.getInstance().process(vo);	
				ActionHelper.initialZfbgSelectItem(request);
				request.setAttribute(mapping.getAttribute(), basx21Form);
			} catch (Exception ex) {
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
	    	Basx21Form basx21Form = (Basx21Form) form;

	    	userData = this.getUserData(request);
			VOPackage vo = new VOPackage();
			vo.setAction(CodeConstant.SMSB_ZFBGACTION);
			vo.setData(basx21Form);
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
			return doZfbg(mapping,basx21Form,request,response);
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
