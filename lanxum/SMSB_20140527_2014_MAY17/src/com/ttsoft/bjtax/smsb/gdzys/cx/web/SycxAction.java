package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class SycxAction extends SmsbAction{

	/**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
	protected void initialRequest(ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse response)
			{
			super.initialRequest(mapping, actionForm, httpServletRequest, response);
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
			                  "<font color=\"#A4B9C6\">������Դռ��˰ϵͳ</font>&gt;<font color=\"#7C9AAB\">˰Դ��ѯ </font>");
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
			                  "˰Դ��ѯ");
			httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
			                  "help/smsb/zhsb/zhsb-000.htm");
			}
/*------------------------------------------------------------------------------------------------------------------------------*/	
	/**
     * <1>��ѯҳ��չʾ
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doShow(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//���ݴ���-��ѯ�־���Ϣ
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_DQJSLIST,request);
			
			//����־��б�ֻ��һ����Ӧ��ȡ��Ĭ��˰�����б�
			List fjList = sycxForm.getFjlist();
			if(fjList.size()==1)
			{
				sycxForm.setFjdm(((swdwmodel)fjList.get(0)).getSwdwid());
				sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			}
			
			//JSPȡ�����̿���
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("Show");		
		}
	
	/**
     * <2>��ѯ����չʾ��˰�����б�
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQuerysws(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
					
			//���ݴ���-��ѯ˰������Ϣ
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			
			//��ӻ���xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
			try {
				responseOut = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//˰�����б�
			List swsList = sycxForm.getSwslist();
			responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
			responseOut.write("<mb>");
			for (int i = 0; i < swsList.size(); i++) {
				swdwmodel v = (swdwmodel) swsList.get(i);
				
				responseOut.write("<swsid>" + v.getSwdwid() + "</swsid>");
				responseOut.write("<swsmc>" + v.getSwdwmc() + "</swsmc>");
			}
			
			responseOut.write("</mb>");
			responseOut.close();		
			return null;	
		}
	
	/**
     * <3>��ѯ��Ϣ�б�
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQueryinf(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//��ȡ�־�
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_DQJSLIST,request);
			
			//����־��б�ֻ��һ��  ��������ԭ������Ӧ��ȡ��Ĭ��˰�����б�
			List fjList = sycxForm.getFjlist();
			if(fjList.size()==1 || (sycxForm.getFjdm()!=null && !"".equals(sycxForm.getFjdm())))
			{
				if(fjList.size()==1)
				{
					sycxForm.setFjdm(((swdwmodel)fjList.get(0)).getSwdwid());
				}
				sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_CXDQJSLIST,request);
			}
			
			//���ݴ���-��ѯ��Ϣ�б�
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_QUERYACTION,request);

			//JSPȡ�����̿���
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("QueryList");		
		}
	
	/**
     * <4>��ѯ��Ϣ����
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     * @return
     * @throws BaseException
     */
	public ActionForward doQueryinfxx(
			ActionMapping actionMapping,
			ActionForm actionFormForm, 
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
			SycxForm sycxForm = (SycxForm) actionFormForm;
			
			//���ݴ���-��ѯ˰������Ϣ
			sycxForm = sendVO(sycxForm ,CodeConstant.SMSB_QUERYA_DETATILACTION,request);
			
			//JSPȡ�����̿���
			request.setAttribute("sycxForm", sycxForm);
			return actionMapping.findForward("QueryDetail");		
		}
	
	/*-----------------------------------------���ܴ�����----------------------------------------------------------*/
		    /**
		     * �����߼�������
		     * @param data
		     * @return
		     * @throws BaseException
		     */
		   private  SycxForm sendVO(Object data ,int actionName ,HttpServletRequest request) throws BaseException
		   {
			   //��װ������Ϣ
			   VOPackage vo = new VOPackage();
			   vo.setData(data);
			   vo.setUserData(this.getUserData(request));
			   vo.setAction(actionName);
			   vo.setProcessor("com.ttsoft.bjtax.smsb.gdzys.cx.processor.SycxProcessor");
			   
			   //����Ҫ��������
			   try {
				   	return (SycxForm)ZhsbProxy.getInstance().process(vo);
			   		} 
			   catch (Exception e) 
			   {
				   e.printStackTrace();
				   throw ExceptionUtil.getBaseException(e, "���ݴ����쳣");
			   }
		   }
}
