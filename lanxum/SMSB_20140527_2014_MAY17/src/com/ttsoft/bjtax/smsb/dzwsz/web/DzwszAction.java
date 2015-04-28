/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.dzwsz.processor.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

import javax.ejb.EJBHome;
/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Action��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class DzwszAction extends SmsbAction {
    public DzwszAction() {
    }

    /**
     * �Ӻ�̨ȡ����֪������Ϣ,����form,��ת����֪����ҳ��
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        DzwszForm theForm = (DzwszForm) form;
        //��ʼ����˰֤�б�
        theForm.setWszList(new ArrayList());
        //��ʼ������
        theForm.setFkpzjsjdm("");
        theForm.setFkpzsphm("");
        theForm.setCzcxqrq("");
        theForm.setCzcxzrq("");
        theForm.setCzjsjdm("");
        return mapping.findForward("Show");
    }
    
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��ӡ����ת��ר����˰֤");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");

    }

    /**
     * ��ѯ���ӵ���ת��ר����˰֤
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        DzwszForm theForm = (DzwszForm) form;
        DzwszBO bo = new DzwszBO();
        
        bo.setJsjdm(theForm.getJsjdm());
        bo.setJylsh(theForm.getJylsh());
        bo.setSphm(theForm.getSphm());

        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData(request));
        vo.setProcessor(CodeConstant.DYDZWSZ_PROCESSOR);
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        
        try {
            bo = (DzwszBO) ZhsbProxy.getInstance().process(vo);
            theForm.setBo(bo);
            theForm.setDy(bo.getSfdybz());
            request.getSession().setAttribute(SessionKey.SESSION_KEY_DZWSZBO,
                                              bo);
            return mapping.findForward("Show");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 20130527���޸�
     * ��ѯ���ӵ���ת��ר����˰֤
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doNewQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        DzwszForm theForm = (DzwszForm) form;
        //��ӡ��˰֤model
        DzwszBO bo = new DzwszBO();
        //����session
        HttpSession session = request.getSession();
        session.setAttribute("cx_flag", theForm.getFlag());
        //����ǰ����۲�ѯ
        if(theForm.getCzjsjdm()=="" || "".equals(theForm.getCzjsjdm())){
        	//���������
            bo.setJsjdm(theForm.getFkpzjsjdm());
            //˰Ʊ����
            bo.setSphm(theForm.getFkpzsphm());
            //��ʼ��
            theForm.setCzcxqrq("");
            theForm.setCzcxzrq("");
            //��session��ֵ
            session.setAttribute("fkpzjsjdm", theForm.getFkpzjsjdm());
            session.setAttribute("fkpzsphm", theForm.getFkpzsphm());
        }else{
        	//���������
            bo.setJsjdm(theForm.getCzjsjdm());
            //��ʼ��
            theForm.setFkpzjsjdm("");
            theForm.setFkpzsphm("");
            //��session��ֵ
            session.setAttribute("czjsjdm", theForm.getCzjsjdm());
            session.setAttribute("czcxqrq", theForm.getCzcxqrq());
            session.setAttribute("czcxzrq", theForm.getCzcxzrq());
        }
        
        //�ŵ�form��
        theForm.setBo(bo);
        
        //��˰֤�б�
        List wszList = new ArrayList();
        
        VOPackage vo = new VOPackage();
        vo.setData(theForm);
        vo.setUserData(this.getUserData(request));
        vo.setProcessor(CodeConstant.DYDZWSZ_PROCESSOR);
        vo.setAction(CodeConstant.SMSB_QUERYACTION1);
        try {
        	//���ز�ѯ���list�б�
        	wszList = (List) ZhsbProxy.getInstance().process(vo);
            theForm.setWszList(wszList);
          //���õ�session��
            request.getSession().setAttribute("wszlist", wszList);

            return mapping.findForward("Show");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 201306 kanght
     * ��ӡ���ӵ���ת��ר����˰֤
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws Exception 
     */
    public ActionForward doNewPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            Exception {
    	//�Ƴ�session
    	request.getSession().removeAttribute(SessionKey.SESSION_KEY_DZWSZBO);
    	
        DzwszForm theForm = (DzwszForm) form;
        List wszlist =  (List)request.getSession().getAttribute("wszlist");
        for(int i=0;i<wszlist.size();i++){
        	//��ȡ��˰֤��Ϣ����
        	 DzwszBO bo = (DzwszBO) wszlist.get(i);
        	 //���ݼ�������룬������ˮ�ţ�˰Ʊ�����ȡҪ��ӡ����˰֤��Ϣ����
//        	if(bo.getJsjdm().trim().equals(theForm.getDyjsjdm()) && bo.getJylsh().trim().equals(theForm.getDyjylsh()) && bo.getSphm().trim().equals(theForm.getDysphm())){
//        		theForm.setBo(bo);
//        		//����Ҫ��ӡ��boֵsession��
//        		theForm.setDy(bo.getSfdybz());
//        		request.getSession().setAttribute(SessionKey.SESSION_KEY_DZWSZBO,bo);
//        	}
        		if(bo.getSphm().trim().equals(theForm.getDysphm())){
        			VOPackage vo = new VOPackage();
        	        vo.setData(bo);
        	        vo.setUserData(this.getUserData(request));
        	        vo.setProcessor(CodeConstant.DYDZWSZ_PROCESSOR);
        	        vo.setAction(CodeConstant.SMSB_SFDYWSZ);
        	        
        			Map map = (HashMap) ZhsbProxy.getInstance().process(vo);
        			bo.setSfdybz((String) map.get("jsjdm"));
        			bo.setDyrq((String) map.get("dyrq"));
            		theForm.setBo(bo);
            		//����Ҫ��ӡ��boֵsession��
            		theForm.setDy(bo.getSfdybz());
            		request.getSession().setAttribute(SessionKey.SESSION_KEY_DZWSZBO,bo);
            	}
        	
        }
        return mapping.findForward("Print");
    }
    
    
    
    /**
     * ��ӡ���ӵ���ת��ר����˰֤
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        request.getSession().removeAttribute("dylist");
        request.getSession().removeAttribute("dybo");
        
        DzwszForm theForm = (DzwszForm) form;
        DzwszBO bo = (DzwszBO) request.getSession().getAttribute(SessionKey.
                SESSION_KEY_DZWSZBO);
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData(request));
        vo.setProcessor(CodeConstant.DYDZWSZ_PROCESSOR);
        vo.setAction(CodeConstant.SMSB_PRINTACTION);
        try {
            List boList = (List) ZhsbProxy.getInstance().process(vo);
            theForm.setBoList(boList);
            theForm.setDy(bo.getSfdybz());
            theForm.setBo(bo);
            //����ӡ���ݷ���session������ԭ�Ŵ�ӡ
            request.getSession().setAttribute("dylist", boList);
            request.getSession().setAttribute("dybo", bo);
            return mapping.findForward("Print");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
	    /**
	     * ��ӡ�ɹ�
	     * 20130703 kanght
	     *    
	     */
		    public ActionForward doPrintCG(ActionMapping mapping, ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) throws
		            BaseException {
		    	 DzwszForm theForm = (DzwszForm) form;
		    	 //��ȡsession
		    	 HttpSession session = request.getSession();
		    	 //���ò�ѯ����
		    	 theForm.setFlag((String) session.getAttribute("cx_flag"));
		    	 theForm.setWszList((List) session.getAttribute("wszlist"));
		    	 if(theForm.getFlag().equals("cz")){
			    	 theForm.setFkpzjsjdm("");
			    	 theForm.setFkpzsphm("");
			    	 theForm.setCzcxqrq((String) session.getAttribute("czcxqrq"));
			    	 theForm.setCzcxzrq((String) session.getAttribute("czcxzrq"));
			    	 theForm.setCzjsjdm((String) session.getAttribute("czjsjdm"));
		    	 }else{
			    	 theForm.setFkpzjsjdm((String) session.getAttribute("fkpzjsjdm"));
			    	 theForm.setFkpzsphm((String) session.getAttribute("fkpzsphm"));
			    	 theForm.setCzcxqrq("");
			    	 theForm.setCzcxzrq("");
			    	 theForm.setCzjsjdm("");
		    	 }
		    	 return mapping.findForward("Show");
		}
		    //ԭ�Ŵ�ӡ
		    public ActionForward doRePrint(ActionMapping mapping, ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) throws
		            BaseException {
		    	System.out.println("ԭ�Ŵ�ӡ==============================================");
		    	 DzwszForm theForm = (DzwszForm) form;
		    	 //��ȡsession
		    	 HttpSession session = request.getSession();
		    	 //��ȡ��ӡ��Ϣ
		    	theForm.setBo((DzwszBO)session.getAttribute("dybo"));
		    	theForm.setBoList((List)session.getAttribute("dylist"));
		    	return mapping.findForward("Print");
		    	
		    }
	    
    public ActionForward doReturn(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
        request.getSession().removeAttribute(SessionKey.SESSION_KEY_DZWSZBO);
        return super.doReturn(mapping, form, request, response);
    }
}
