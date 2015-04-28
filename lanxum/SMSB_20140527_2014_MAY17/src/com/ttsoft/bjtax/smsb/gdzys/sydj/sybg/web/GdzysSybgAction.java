/*
 * <p>Title: 北京地税核心征管系统－－ 上门申报-- 耕地占用税</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web.GdzysSydjcxForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 耕地资源占用税-税源登记查询</p>
 * <p>Description: 查询税源登记</p>
 * @author 
 * @version 1.0
 */
public class GdzysSybgAction extends SmsbAction {

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
                                        "税源登记查询");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");
    }
    
/*----------------------------变更模块--页面流转函数--2个---------------------------------------------------------*/

    /**
     * <1>变更页面展示
     * @param mapping
     * @param 查询传入数据
     * @param request
     * @param response
     * @return 
     * @throws BaseException（SmsbAction处理）
     */
    public ActionForward doShow(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
				//转换传进来的数据表单
				GdzysSybgForm gdzysSybgForm = (GdzysSybgForm)actionForm;
				gdzysSybgForm.setNum(-1);

				//JSP取来流程控制
				request.setAttribute("gdzysSybgForm", gdzysSybgForm);
				return actionMapping.findForward("Show");			
            } 
    
    /**
     * <2>税源变更查询函数
     * @param actionMapping
     * @param actionForm
     * @param request
     * @param response
     * @return 1.未查到 --空的查询页面
     * 		   2.一条记录--直接显示
     * 		   3.多条记录--记录列表
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping actionMapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws BaseException 
            {
    	        
        		//转换传进来的数据表单
    			GdzysSybgForm gdzysSybgForm = (GdzysSybgForm)actionForm;
    			
    			//数据处理
    			gdzysSybgForm = sendVO(gdzysSybgForm ,GdzysCodeConstant.SMSB_GDZYS_SYBGQUERY,request);

    			//JSP取来流程控制
    			request.setAttribute("gdzysSybgForm", gdzysSybgForm);
    			return actionMapping.findForward("BgResult");		
            }
/*---------------------------------------变更模块--功能处理函数--1个--------------------------------------------------------*/
    /**
     * 变更时数据传送函数
     * @param data
     * @return
     * @throws BaseException
     */
   protected GdzysSybgForm sendVO(Object data ,int actionName ,HttpServletRequest request) throws BaseException
   {
	   //封装数据信息
	   VOPackage vo = new VOPackage();
	   vo.setData(data);
	   vo.setUserData(this.getUserData(request));
	   vo.setAction(actionName);
	   vo.setProcessor("com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.processor.GdzysSybgProcessor");
	   
	   //传输要处理数据
	   try {
		   	return (GdzysSybgForm)ZhsbProxy.getInstance().process(vo);
	   		} 
	   catch (Exception e) 
	   {
		   e.printStackTrace();
		   throw ExceptionUtil.getBaseException(e, "数据处理异常");
	   }
   }
}
