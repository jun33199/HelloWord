/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报基本信息表
 * </p>
 * 
 * @author lianglw
 * @version 1.1
 */

public class JbxxbAction2008 extends SmsbAction {


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
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税基本信息表</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"企业所得税年度基本信息表");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * 初始化页面数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JskffmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//获取基本信息
		JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
		jbxxbForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
		vo.setUserData(userData);
		try {
			//调用process
			jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);

		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		return mapping.findForward("Show");
	}

	

	/**
	 * 保存申报数据
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            JskffmxbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward的跳转目标
	 * @throws BaseException
	 *             系统捕捉异常，根据异常类型抛出
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		// -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
		ActionForward forward = this.doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		UserData userData = null;
		JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
		userData = this.getUserData(request);		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(jbxxbForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
		vo.setUserData(userData);

		try {

			jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);

		
		} catch (Exception ex) {
			// 系统捕捉异常，根据异常类型抛出
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), jbxxbForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
		return mapping.findForward("Save");
	}
	/**
     * 删除申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        UserData userData = null;

        JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
        vo.setUserData(userData);
        try
        {
            //调用processor
            SbzlProxy.getInstance().process(vo);
            jbxxbForm.reset(mapping, request);
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
        return mapping.findForward("Delete");
    }
	
    /**
     * 查询已申报数据
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
    	//new modify  yugw 
    	//-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
       /* ActionForward forward = this.doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }*/
    	UserData userData = null;

    	JbxxbForm2008 jbxxbForm = (JbxxbForm2008) form;
        userData = this.getUserData(request);
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(jbxxbForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.processor.JbxxbProcessor2008");
        vo.setUserData(userData);
        try
        {
        	//调用校验
			CheckBean checkBean = this.setCheckInf(jbxxbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
        	
        	jbxxbForm = (JbxxbForm2008) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), jbxxbForm);
        }
        catch (Exception ex)
        {
        	jbxxbForm.reset(mapping, request);
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        System.out.println("out Query in JbxxbAction");

        return mapping.findForward("Query");
    }
	/**
	 * 退出页面
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
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * @Description: TODO 设置校验参数
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(JbxxbForm2008 jbxxbForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//校验版本材料
		checkBean.setCurrentTime(jbxxbForm.getSknd());
		checkBean.setVersionStartTime("2008");
		checkBean.setVersionEndTime("2008");
		
		//校验清算期的材料
		checkBean.setJsjdm(jbxxbForm.getJsjdm());
		
		//校验征管范围鉴定类型材料
		checkBean.setSkssrqq(jbxxbForm.getSknd()+"0101");
		checkBean.setSkssrqz(jbxxbForm.getSknd()+"1231");
		return checkBean;
	}
	
}