/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.DJXX;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.SBMXModel;
import com.ttsoft.bjtax.smsb.lwpk.web.DhkscxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Action。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class GdzysSydjxxlrAction extends SmsbAction {
    public GdzysSydjxxlrAction() {
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
                                        "<font color=\"#A4B9C6\">耕地资源占用税系统</font>&gt;<font color=\"#7C9AAB\">税源登记</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "新建税源登记");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");


    }
    
    /**
     * 新建税源登记初始页面
     */
    public ActionForward doInit(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
    	VOPackage vo = new VOPackage();
    	vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_INIT);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    		request.setAttribute("sydjxxlrForm", form);
    	   return mapping.findForward("Init");
    }
    /**
     * 保存税源登记信息
     */
    public ActionForward doSave(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
    	VOPackage vo = new VOPackage();
    	vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_SAVE);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if("error".equals(form.getErrors())){
			vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_INIT);
			vo.setUserData(ud);
			vo.setData(form);
			vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
			try {
				form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(e.getMessage());
			}
			request.setAttribute("sydjxxlrForm", form);
			return mapping.findForward("Error");
		}else{
			request.setAttribute("sydjxxlrForm", form);
			request.getSession().removeAttribute("sydjxxlrForm");
			request.getSession().setAttribute("sydjxxlrForm", form);
			return mapping.findForward("Save");	
		}
		
    }
    
  
    
    /**
     * 打印税源登记信息
     */
    public ActionForward doPrint(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
    	VOPackage vo = new VOPackage();
    	vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_PRINT_SBB);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	   return mapping.findForward("print");
    }
   
    /**
     * 确认税源登记信息
     */
    public ActionForward doConfirm(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
    	VOPackage vo = new VOPackage();
    	vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_CONFIRM);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	   return mapping.findForward("confirm");
    }
    //添加备注
    public ActionForward doAddbz(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) theForm;
		return mapping.findForward("Addbz");
		}
    /**
     * 添加备注信息
     */
    public ActionForward doRemark(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form2 = (GdzysSydjxxlrForm) theForm;
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
    	request.getSession().removeAttribute("sydjxxlrForm");
    	form.setBzlxdm(form2.getBzlxdm());
    	form.setBzms(form2.getBzms());
		UserData ud = this.getUserData(request);
    	VOPackage vo = new VOPackage();
    	vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_REMARK);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
			form.setFlag("");
			request.setAttribute("sydjxxlrForm", form);
			request.getSession().setAttribute("sydjxxlrForm", form);
    	   return mapping.findForward("Remark");
    }
    //ajax获取登记信息
    
    public ActionForward dogetDJXX(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
		GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		System.out.println(form.getJsjdm()+"======");
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_DJXX);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			DJXX djxx = (DJXX) ZhsbProxy.getInstance().process(vo);
			//构造xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
			responseOut = response.getWriter();
			responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
			responseOut.write("<mb>");
			
			responseOut.write("<nsrmc>"+djxx.getNsrmc()+"</nsrmc>");
			responseOut.write("<nsrsshy>"+djxx.getNsrsshy()+"</nsrsshy>");
			responseOut.write("<nsrsshymc>"+djxx.getNsrsshymc()+"</nsrsshymc>");
			responseOut.write("<qydjzclxdm>"+djxx.getQydjzclxdm()+"</qydjzclxdm>");
			responseOut.write("<qydjzclxmc>"+djxx.getQydjzclxmc()+"</qydjzclxmc>");
			responseOut.write("<nsrxxdz>"+djxx.getNsrxxdz()+"</nsrxxdz>");
			responseOut.write("<nsrsbh>"+djxx.getNsrsbh()+"</nsrsbh>");
			responseOut.write("<yhmc>"+djxx.getYhmc()+"</yhmc>");
			responseOut.write("<yhzh>"+djxx.getYhzh()+"</yhzh>");
			responseOut.write("<yhdm>"+djxx.getYhdm()+"</yhdm>");
			String[] yhdm_list = djxx.getYhdm_list();
			String[] yhmc_list = djxx.getYhmc_list();
			String[] yhzh_list = djxx.getYhzh_list();
			
			for(int i=0;i<yhdm_list.length;i++){
				responseOut.write("<mc>"+yhmc_list[i]+"</mc>");
				responseOut.write("<zh>"+yhzh_list[i]+"</zh>");
				responseOut.write("<dm>"+yhdm_list[i]+"</dm>");
			}
			
			responseOut.write("</mb>");
			responseOut.flush();
			responseOut.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
			return null;
		}
    
    
    //
    
 //ajax获取适用税额信息
    
    public ActionForward dogetSYSE(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
		GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		List list = new ArrayList();
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_SYSE);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			list = (List) ZhsbProxy.getInstance().process(vo);
			//构造xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
		
				responseOut = response.getWriter();
				responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
				responseOut.write("<mb>");
				//纳税人名称
				for(int i = 0;i<list.size();i++){
					LabelValueBean bean = (LabelValueBean) list.get(i);
					responseOut.write("<sysedm>"+bean.getLabel()+"</sysedm>");
					responseOut.write("<syseval>"+bean.getValue()+"</syseval>");
				}
				responseOut.write("</mb>");
				responseOut.flush();
				responseOut.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return null;
		}
    
//ajax获取减免税依据
    
    public ActionForward dogetJMSYJ(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
		GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
		StringBuffer yj =  new StringBuffer();
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_JMSYJ);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			yj =  (StringBuffer) ZhsbProxy.getInstance().process(vo);
			//构造xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
		
				responseOut = response.getWriter();
				responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
				responseOut.write("<mb>");
				responseOut.write("<jmsyjmc>"+yj.toString()+"</jmsyjmc>");
				responseOut.write("</mb>");
				responseOut.flush();
				responseOut.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return null;
		}
    
    
    
    //打印申报表
    public ActionForward doPrintSBB(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
		GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_PRINT_SBB);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		request.setAttribute("sydjxxlrForm", form);
		return mapping.findForward("Printsbb");
		}
    
    //打印减免申报表
    public ActionForward doPrintJMSBB(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_PRINT_SBB);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm) ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		request.setAttribute("sydjxxlrForm", form);
		return mapping.findForward("Printjmsbb");
		}
    
    //查看是否进行了自然人登记
    public ActionForward dogetZRR(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
		GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_ZRR);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			DJXX djxx =  (DJXX) ZhsbProxy.getInstance().process(vo);
			if(djxx.getNsrxxdz() == null || "".equals(djxx.getNsrxxdz())){
				djxx.setNsrxxdz("");
			}
			//构造xml
			response.setContentType("text/xml;charset=GB2312");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter responseOut = null;
		
				responseOut = response.getWriter();
				responseOut.write("<?xml version='1.0' encoding='GB2312' ?>");
				responseOut.write("<mb>");
				responseOut.write("<dm>"+djxx.getDm()+"</dm>");
				responseOut.write("<mc>"+djxx.getNsrmc()+"</mc>");
				if(djxx.getNsrxxdz() == "" || djxx.getNsrxxdz() ==null || "".equals(djxx.getNsrxxdz())){
					responseOut.write("<dz>null</dz>");
				}else{
					responseOut.write("<dz>"+djxx.getNsrxxdz()+"</dz>");
				}
				responseOut.write("<jsjdm>"+djxx.getJsjdm()+"</jsjdm>");
				responseOut.write("</mb>");
				responseOut.flush();
				responseOut.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return null;
		}
    
    /**
     * 打印返回
     */
    public ActionForward dogoBack(ActionMapping mapping,
                                  ActionForm theForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
                                  BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) theForm;
		form = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
		request.getSession().removeAttribute("sydjxxlrForm");
		form.setFlag("");
		request.getSession().setAttribute("sydjxxlrForm", form);
		request.setAttribute("sydjxxlrForm", form);
    	return mapping.findForward("Save");
    }
    
    
    //区县确认
    public ActionForward doQxsh(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
    	request.getSession().removeAttribute("sydjxxlrForm");
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_QXSH);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		request.setAttribute("sydjxxlrForm", form);
		request.getSession().setAttribute("sydjxxlrForm", form);
		return mapping.findForward("Qxsh");
		}
    
    //市局审核
    public ActionForward doSjsh(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	GdzysSydjxxlrForm form1  = (GdzysSydjxxlrForm) theForm;
    	GdzysSydjxxlrForm form  = (GdzysSydjxxlrForm) request.getSession().getAttribute("sydjxxlrForm");
    	request.getSession().removeAttribute("sydjxxlrForm");
    	form.setSjqrsj(form1.getSjqrsj());
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_SJSH);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		request.getSession().setAttribute("sydjxxlrForm",form);
		request.setAttribute("sydjxxlrForm", form);
		return mapping.findForward("Sjsh");
		}
    //税源等级信息查询
    public ActionForward doQuery(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) theForm;
    	
    	System.out.println(form.getBg_flag()+"=====bg_flag===");
    	System.out.println(form.getSbbxlh()+"======sbbxlh===");
    	System.out.println(form.getSh_flag()+"====sh_flag===");
    	
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_QUERY);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	
		request.setAttribute("sydjxxlrForm", form);
		if("bg_flag".equals(form.getBg_flag())){
			
		}else if("sh_flag".equals(form.getSh_flag())){
			
		}else{
			request.getSession().removeAttribute("sydjxxlrForm");			
		}
		request.getSession().setAttribute("sydjxxlrForm", form);
		
		return mapping.findForward("Query");
		}
    //修改申报信息
    
    public ActionForward doUpdate(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_UPDATE);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("error".equals(form.getFlag())){
			vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_BGQUERY);
			vo.setUserData(ud);
			vo.setData(form);
			vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
			try {
				form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(e.getMessage());
			}
			form.setErrors("error");
			request.setAttribute("sydjxxlrForm", form);
			return mapping.findForward("Bgcx");
		}else{
			request.setAttribute("sydjxxlrForm", form);
			request.getSession().removeAttribute("sydjxxlrForm");
			request.getSession().setAttribute("sydjxxlrForm", form);
			return mapping.findForward("Update");
		}
		
		}
    
  //税源等级信息查询
    public ActionForward doBgcx(ActionMapping mapping,
            ActionForm theForm,
            HttpServletRequest request,
            HttpServletResponse response) throws
            BaseException{
    	
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) theForm;
		UserData ud = this.getUserData(request);
		VOPackage vo = new VOPackage();
		vo.setAction(GdzysCodeConstant.GDZYS_SYXXLRACTION_BGQUERY);
		vo.setUserData(ud);
		vo.setData(form);
		vo.setProcessor(GdzysCodeConstant.GDZYS_SYXXLRPROCESSOR);
		try {
			form = (GdzysSydjxxlrForm)ZhsbProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		System.out.println(form.getYhdm()+"========bgcx_yhdm");
		request.setAttribute("sydjxxlrForm", form);
		return mapping.findForward("Bgcx");
		}
    
}
