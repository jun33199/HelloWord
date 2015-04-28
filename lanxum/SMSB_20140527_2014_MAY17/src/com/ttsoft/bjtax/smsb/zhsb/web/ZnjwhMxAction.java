package com.ttsoft.bjtax.smsb.zhsb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SfglAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class ZnjwhMxAction  extends SfglAction{
	   /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">上门申报</font>&gt;<font color=\"#7C9AAB\">滞纳金维护明细</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "综合申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbypys-000.htm");
    }

    /**
     * 查询缴款书
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	ZnjwhMxActionForm form = (ZnjwhMxActionForm) actionForm;
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZnjwhMxProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
            form = (ZnjwhMxActionForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
    }

    /**
     * 更新一票一税的缴款书维护
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	ZnjwhMxActionForm form = (ZnjwhMxActionForm) actionForm;
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZnjwhMxProcessor");
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        vo.setUserData(ud);
        vo.setData(form);
        try
        {
            form = (ZnjwhMxActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
        if (form.getForward().equals("Back"))
        {
            ZnjwhActionForm forwardForm = new ZnjwhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setQsrq(form.getQsrq());
            forwardForm.setJzrq(form.getJzrq());
            forwardForm.setSjly(form.getSjly());
            httpServletRequest.setAttribute("znjwhActionForm", forwardForm);
            return actionMapping.findForward("Update");
        }
        ZnjwhActionForm forwardForm = new ZnjwhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        forwardForm.setSbbh(form.getSbbh());
        forwardForm.setQsrq(form.getQsrq());
        forwardForm.setJzrq(form.getJzrq());
        httpServletRequest.setAttribute("znjwhActionForm", forwardForm);
        return actionMapping.findForward("Update");

    }

    /**
     * 更新一票一税的缴款书维护
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	ZnjwhMxActionForm form = (ZnjwhMxActionForm) actionForm;
        if (form.getForward().equals("Back"))
        {
        	ZnjwhActionForm forwardForm = new ZnjwhActionForm();
            forwardForm.setActionType("Query");
            forwardForm.setJsjdm(form.getJsjdm());
            forwardForm.setNsrmc(form.getNsrmc());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setQsrq(form.getQsrq());
            forwardForm.setJzrq(form.getJzrq());
            forwardForm.setPresbbh(form.getPresbbh());
            forwardForm.setSbbh(form.getSbbh());
            forwardForm.setSjly(form.getSjly());
            httpServletRequest.setAttribute("znjwhActionForm", forwardForm);
            return actionMapping.findForward("Back");
        }
        ZnjwhActionForm forwardForm = new ZnjwhActionForm();
        forwardForm.setActionType("Query");
        forwardForm.setJsjdm(form.getJsjdm());
        forwardForm.setNsrmc(form.getNsrmc());
        forwardForm.setSbbh(form.getSbbh());
        forwardForm.setQsrq(form.getQsrq());
        forwardForm.setJzrq(form.getJzrq());

        httpServletRequest.setAttribute("znjwhActionForm", forwardForm);
        return actionMapping.findForward("Back");

    }

}
