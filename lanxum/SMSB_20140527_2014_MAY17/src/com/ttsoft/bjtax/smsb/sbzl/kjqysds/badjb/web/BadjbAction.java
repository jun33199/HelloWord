package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


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

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ApplicationException;



public class BadjbAction extends SmsbAction
{
    public BadjbAction()
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
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">扣缴企业所得税</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "扣缴企业所得税合同备案登记表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
    }

    /**
     * 初始化审核页面数据
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
    public ActionForward doInit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doInit===========");
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        /**
         * 初始化页面信息
         */
        // 记录数
        badjForm.setTotalCount(0);
        // 总页数
        badjForm.setTotalPage(0);
        // 当前页
        badjForm.setCurrentPage(0);

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Init");
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
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
     // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        
        if(!badjForm.getModifyFlag()){
        	badjForm = new BadjbForm();
        }
        else
        {
        	// 初始化bo
            BadjbBO bo = new BadjbBO();

            try
            {
                ActionHelper.form2Bo(badjForm, bo);

                //设置查询参数
                VOPackage vo = new VOPackage();
                vo.setAction(CodeConstant.SMSB_VIEWMX);
                vo.setData(bo);
                vo.setUserData(userData);
                vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
                // 将前台对象转发至后台处理
                bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
                bo.getHtxx().setHtyxq((bo.getHtxx().getHtyxq()).replaceAll("-", ""));
                bo.getHtxx().setQyrq((bo.getHtxx().getQyrq()).replaceAll("-", ""));
            }
            catch (Exception ex)
            {
                throw new ApplicationException("查询备案登记信息错误，请与管理员联系！");
            }
            // 将bo转换成form
            ActionHelper.BO2form(bo, badjForm);
        }
        /**
         * 初始化页面下拉菜单
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("初始化页面下拉菜单错误!");
        }

        // 填报日期
		badjForm.setTbrq(SfDateUtil.getDate());

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Show");
    }

    /**
     * 查询扣缴义务人信息
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
    public ActionForward doQueryKjrInfo(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doQueryKjrInfo===========");
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("tbrq = " + badjForm.getTbrq());
        System.out.println("gjdq = " + badjForm.getFjmqyxx().getFjmgb());
        System.out.println("bz = " + badjForm.getHtxx().getBz());
        // 初始化bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // 将前台对象转发至后台处理
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("备案登记表查询纳税人信息错误，请与管理员联系！");
        }
        // 将bo转换成form
        ActionHelper.BO2form(bo, badjForm);
        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Show");
    }

    /**
     * 查询扣缴义务人备案信息
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
    public ActionForward doQueryKjrRecords(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doQueryKjrRecords===========");
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("isModify = " + badjForm.getModifyFlag());
        // 初始化bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION2);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // 将前台对象转发至后台处理
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            System.out.println("查询扣缴义务人备案登记信息错误，请与管理员联系！");
            ex.printStackTrace();
            badjForm = new BadjbForm();
            request.setAttribute(mapping.getAttribute(), badjForm);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "查询扣缴义务人备案登记信息错误，请与管理员联系！");
            return mapping.findForward("Init");
        }
        // 将bo转换成form
        ActionHelper.BO2form(bo, badjForm);
        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);
        
        String returnStr = new String();
        if(badjForm.getModifyFlag())
        {
        	returnStr = "showModify";
        }
        else
        {
        	returnStr = "Init";
        }

        return mapping.findForward(returnStr);
    }


    /**
     * 保存备案登记表信息
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
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doSave===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("gb = " + badjForm.getFjmqyxx().getFjmgjdq());
        // 初始化bo
        BadjbBO bo = new BadjbBO();

        try
        {
        	// 处理其它资料名称格式
        	String qtzlmc = badjForm.getHtxx().getQtzlmc();
        	if (qtzlmc != null && !"".equals(qtzlmc)) 
        	{
    			//替换其它资料名称中的格式符为对应格式字符串
        		qtzlmc = qtzlmc.replaceAll(" ", "&nbsp;");
        		qtzlmc = qtzlmc.replaceAll("\r\n", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\r", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\n", "<br/>");
        		
        		badjForm.getHtxx().setQtzlmc(qtzlmc);
    		}
        	// 将form内容转换成bo对象供后台使用
            ActionHelper.form2Bo(badjForm, bo);

            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // 将前台对象转发至后台处理
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            // 将bo转换成form
            badjForm = new BadjbForm();
            // 填报日期
            badjForm.setTbrq(SfDateUtil.getDate());
            // 将badjForm 放入request中
            request.setAttribute(mapping.getAttribute(), badjForm);
            throw new ApplicationException("保存备案登记表信息错误，请与管理员联系！");
        }
        // 将bo转换成form
        badjForm = new BadjbForm();
		String message = new String();
		if(bo == null)
		{
	        // 将badjForm 放入request中
	        request.setAttribute(mapping.getAttribute(), badjForm);
	        message = "备案登记表信息保存成功！";
		}
		else
		{
			if(bo.getZtbz().equals("3"))
			{
				ActionHelper.BO2form(bo, badjForm);
				// 将badjForm 放入request中
				message = bo.getMessage();
			}
		}
		// 填报日期
		badjForm.setTbrq(SfDateUtil.getDate());
		request.setAttribute(mapping.getAttribute(), badjForm);
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, message);

        return mapping.findForward("Show");
    }

    /**
     * 查看待审核备案登记表明细信息
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
    public ActionForward doViewMX(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doViewMX===========");
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("jsjdm = " + badjForm.getJsjdm());
        System.out.println("badjxh = " + badjForm.getBadjxh());
        // 初始化bo
        BadjbBO bo = new BadjbBO();

        try
        {
            ActionHelper.form2Bo(badjForm, bo);

            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_VIEWMX);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // 将前台对象转发至后台处理
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("查询备案登记信息错误，请与管理员联系！");
        }
        // 将bo转换成form
        ActionHelper.BO2form(bo, badjForm);
        //把合同金额按千位分割
        ActionHelper.splitJe(badjForm);
        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("ViewMX");
    }

    /**
     * 审核备案登记信息
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
    public ActionForward doShenhe(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doShenhe===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("badjxh = " + badjForm.getBadjxh());
        System.out.println("badjbbh = " + badjForm.getBadjbbh());


        try
        {
            if(null == badjForm.getBadjbbh() || "".equals(badjForm.getBadjbbh()))
            {
                // 初始化bo
                BadjbBO bo = new BadjbBO();
                ActionHelper.form2Bo(badjForm, bo);

                //设置查询参数
                VOPackage vo = new VOPackage();
                vo.setAction(CodeConstant.SMSB_SHENHE);
                vo.setData(bo);
                vo.setUserData(userData);
                vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
                // 将前台对象转发至后台处理
                bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
                // 将bo转换成form
                ActionHelper.BO2form(bo, badjForm);
            }
        }
        catch (Exception ex)
        {
            System.out.println("审核备案登记信息错误，请与管理员联系！");
            ex.printStackTrace();
            return mapping.findForward("Shbtg");
        }
        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);
        System.out.println("jsjdm = " + badjForm.getJsjdm());

        String forwardStr = new String();
        if("1".equals(badjForm.getZtbz()))
        {
            // 审核通过跳转路径
            forwardStr = "ViewMX";
            // 操作成功流转
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核备案登记操作成功！");
        }
        else if("2".equals(badjForm.getZtbz()))
        {
            // 审核不通过跳转路径
            forwardStr = "Shbtg";
        }

        return mapping.findForward(forwardStr);
    }

    /**
     * 打印备案登记表
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
    public ActionForward doPrint(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doPrint===========");
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("Print");
    }
    
    /**
     * 初始化修改查询页面
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
    public ActionForward doShowModify(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doShowModify===========");
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        /**
         * 初始化页面信息
         */
        // 记录数
        badjForm.setTotalCount(0);
        // 总页数
        badjForm.setTotalPage(0);
        // 当前页
        badjForm.setCurrentPage(0);

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);

        return mapping.findForward("showModify");
    }
    
    /**
     * 删除备案登记表信息
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
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("=========coming in to doDelete===========");
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        // 获取BadjbForm
        BadjbForm badjForm = (BadjbForm) form;
        System.out.println("gb = " + badjForm.getFjmqyxx().getFjmgjdq());
        // 初始化bo
        BadjbBO bo = new BadjbBO();

        try
        {

        	// 将form内容转换成bo对象供后台使用
            ActionHelper.form2Bo(badjForm, bo);

            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(bo);
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_HTBADJB_PROCESSOR);
            // 将前台对象转发至后台处理
            bo = (BadjbBO) SbzlProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            // 将badjForm 放入request中
            request.setAttribute(mapping.getAttribute(), badjForm);
            throw new ApplicationException("删除备案登记表信息错误，请与管理员联系！");
        }
        // 将bo转换成form
        badjForm = new BadjbForm();
		String message = new String();

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);
        message = "备案登记表信息删除成功！";
		request.setAttribute(CodeConstant.SMSB_DELETE_SUCCESS, message);
		return doQueryKjrRecords(mapping,form,request,response);

        //return mapping.findForward("Show");
    }
}
