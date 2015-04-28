/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Action。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class DzwszAction extends SmsbAction {
    public DzwszAction() {
    }

    /**
     * 从后台取出告知事项信息,设置form,调转到告知事项页面
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
        //初始化完税证列表
        theForm.setWszList(new ArrayList());
        //初始化参数
        theForm.setFkpzjsjdm("");
        theForm.setFkpzsphm("");
        theForm.setCzcxqrq("");
        theForm.setCzcxzrq("");
        theForm.setCzjsjdm("");
        return mapping.findForward("Show");
    }
    
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报征收</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "打印电子转帐专用完税证");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");

    }

    /**
     * 查询电子电子转账专用完税证
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
     * 20130527待修改
     * 查询电子电子转账专用完税证
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
        //打印完税证model
        DzwszBO bo = new DzwszBO();
        //创建session
        HttpSession session = request.getSession();
        session.setAttribute("cx_flag", theForm.getFlag());
        //如果是按存折查询
        if(theForm.getCzjsjdm()=="" || "".equals(theForm.getCzjsjdm())){
        	//计算机代码
            bo.setJsjdm(theForm.getFkpzjsjdm());
            //税票号码
            bo.setSphm(theForm.getFkpzsphm());
            //初始化
            theForm.setCzcxqrq("");
            theForm.setCzcxzrq("");
            //给session赋值
            session.setAttribute("fkpzjsjdm", theForm.getFkpzjsjdm());
            session.setAttribute("fkpzsphm", theForm.getFkpzsphm());
        }else{
        	//计算机代码
            bo.setJsjdm(theForm.getCzjsjdm());
            //初始化
            theForm.setFkpzjsjdm("");
            theForm.setFkpzsphm("");
            //给session赋值
            session.setAttribute("czjsjdm", theForm.getCzjsjdm());
            session.setAttribute("czcxqrq", theForm.getCzcxqrq());
            session.setAttribute("czcxzrq", theForm.getCzcxzrq());
        }
        
        //放到form中
        theForm.setBo(bo);
        
        //完税证列表
        List wszList = new ArrayList();
        
        VOPackage vo = new VOPackage();
        vo.setData(theForm);
        vo.setUserData(this.getUserData(request));
        vo.setProcessor(CodeConstant.DYDZWSZ_PROCESSOR);
        vo.setAction(CodeConstant.SMSB_QUERYACTION1);
        try {
        	//返回查询结果list列表
        	wszList = (List) ZhsbProxy.getInstance().process(vo);
            theForm.setWszList(wszList);
          //放置到session中
            request.getSession().setAttribute("wszlist", wszList);

            return mapping.findForward("Show");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 201306 kanght
     * 打印电子电子转账专用完税证
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
    	//移除session
    	request.getSession().removeAttribute(SessionKey.SESSION_KEY_DZWSZBO);
    	
        DzwszForm theForm = (DzwszForm) form;
        List wszlist =  (List)request.getSession().getAttribute("wszlist");
        for(int i=0;i<wszlist.size();i++){
        	//获取完税证信息对象
        	 DzwszBO bo = (DzwszBO) wszlist.get(i);
        	 //根据计算机代码，交易流水号，税票号码获取要打印的完税证信息对象
//        	if(bo.getJsjdm().trim().equals(theForm.getDyjsjdm()) && bo.getJylsh().trim().equals(theForm.getDyjylsh()) && bo.getSphm().trim().equals(theForm.getDysphm())){
//        		theForm.setBo(bo);
//        		//放置要打印的bo值session中
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
            		//放置要打印的bo值session中
            		theForm.setDy(bo.getSfdybz());
            		request.getSession().setAttribute(SessionKey.SESSION_KEY_DZWSZBO,bo);
            	}
        	
        }
        return mapping.findForward("Print");
    }
    
    
    
    /**
     * 打印电子电子转账专用完税证
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
            //将打印数据放在session中留作原号打印
            request.getSession().setAttribute("dylist", boList);
            request.getSession().setAttribute("dybo", bo);
            return mapping.findForward("Print");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
	    /**
	     * 打印成功
	     * 20130703 kanght
	     *    
	     */
		    public ActionForward doPrintCG(ActionMapping mapping, ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) throws
		            BaseException {
		    	 DzwszForm theForm = (DzwszForm) form;
		    	 //获取session
		    	 HttpSession session = request.getSession();
		    	 //设置查询类型
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
		    //原号打印
		    public ActionForward doRePrint(ActionMapping mapping, ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) throws
		            BaseException {
		    	System.out.println("原号打印==============================================");
		    	 DzwszForm theForm = (DzwszForm) form;
		    	 //获取session
		    	 HttpSession session = request.getSession();
		    	 //获取打印信息
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
