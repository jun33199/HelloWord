package com.ttsoft.bjtax.smsb.sjjh.web;

/**
 * <p>Title: 扣缴企业所得税-备案登记表Action</p>
 *
 * <p>Description: 处理备案登记表页面应用事件响应</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.frame.util.TypeChecker;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.ActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web.DztzBO;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web.DztzForm;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web.DztzHelper;


import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;



public class SjjhAction extends SmsbAction
{
	
    public SjjhAction()
    {
    }

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
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
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">数据交换XML报文下载</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "数据交换XML报文下载");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
    }

    /**
     * 初始化页面数据
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        SjjhForm sjjhForm = new SjjhForm();
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);        
        try
        {
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SJJH_QUERYFJJG);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SMSB_SJJH_PROCESSOR);
            List fjjgList = (List) SbzlProxy.getInstance().process(vo);   
            //初始化页面下拉菜单
            SjjhHelper.initialPageSelectItem(request,fjjgList);
        }
        catch (Exception ex) {
            System.out.println("初始化页面下拉菜单错误!");
        }

        // 将sjjhForm 放入request中
        request.setAttribute(mapping.getAttribute(), sjjhForm);
        return mapping.findForward("Show");
    } 
    
    


    public ActionForward doQueryTZXX(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        SjjhForm sjjhForm=(SjjhForm)form;
        try
        {
            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SJJH_BUILDXML);
            vo.setData(sjjhForm);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SMSB_SJJH_PROCESSOR);
            SjjhBO bo = (SjjhBO) ZhsbProxy.getInstance().process(vo);
            
            
            List list=bo.getResultList();
            getBwsjList(sjjhForm.getBwlx(),userData.getYhid(),list,response,vo);
            

          
            
        }
        catch (Exception ex)
        {	
        	ex.printStackTrace();
            throw new ApplicationException("备案登记表查询纳税人信息错误，请与管理员联系！");
        }      
        return mapping.findForward(null);

    }
    
    private void getBwsjList(String bwlx,String userid,List list,HttpServletResponse response,VOPackage vo) throws IOException{
    	
		
		
		String downloadName="bwxx.xml";			
		
		if(bwlx.equals(SjjhHelper.SBZSXX))	  downloadName="taxMLsbZsxx_hzsds.xml";    
		if(bwlx.equals(SjjhHelper.HZSBSWDJXX))  downloadName="taxMLdwnsr_hzsds.xml";   	                  
		if(bwlx.equals(SjjhHelper.JDALYWBW))	  downloadName="taxMLsbbQysds2008JdA_hzsds.xml"; 
		if(bwlx.equals(SjjhHelper.FZJGFPBYWBW)) downloadName="taxMLsbbQysds2008HznsFzjgFpb_hzsds.xml";
		
		String fileName="./"+userid+"_"+downloadName;     
		
		response.resetBuffer();
		response.setHeader("Content-disposition", "attachment; filename=".concat(GBK2ISO(downloadName)));
		response.setContentType("text/xml");
		OutputStream out = response.getOutputStream();
		
		try {		
			Object tml=SjjhHelper.rsToBean(list,bwlx);	
			bean2xml(tml,fileName,bwlx);
			
			  vo.setAction(CodeConstant.SJJH_BWBC);
			  SjjhForm sjjhForm=(SjjhForm)vo.getData();
			  sjjhForm.setBwlxmc("bwlxmc");
			  sjjhForm.setContext(fileName);
//	          ZhsbProxy.getInstance().process(vo);
	          
	          
			File fileLoad = new File(fileName);
			byte[] b = new byte[1024];	
			FileInputStream in = new FileInputStream(fileLoad);
			int n = 0;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
    }

    
	  private void bean2xml(Object tml,String fileName,String bwlx){		
			try {
	     		OutputStreamWriter xmlwriter =new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
	     		if(bwlx.equals(SjjhHelper.SBZSXX))
					((com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxML)tml).marshal(xmlwriter);	     		
	     		if(bwlx.equals(SjjhHelper.HZSBSWDJXX))
					((com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxML)tml).marshal(xmlwriter);
	     		if(bwlx.equals(SjjhHelper.JDALYWBW))
					((com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxML)tml).marshal(xmlwriter);
	     		if(bwlx.equals(SjjhHelper.FZJGFPBYWBW))
	     			((com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.TaxML)tml).marshal(xmlwriter);

			} catch (IOException ex) {
				ex.printStackTrace(System.err);
			} catch (MarshalException ex) {
				ex.printStackTrace(System.err);
			} catch (ValidationException ex) {
				ex.printStackTrace(System.err);
			}	  
	  }

	public static String GBK2ISO(String strIn) {
		String strOut = null;
		if (TypeChecker.isEmpty(strIn)) {
			return strIn;
		}
		try {
			// 将字符串作内码转换
			byte[] b = strIn.getBytes("GBK");
			strOut = new String(b, "ISO8859_1");
		} catch (java.io.UnsupportedEncodingException e) {
			return "";
		} //end try - catch
		return strOut;
	}

}
