/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshWszlrForm;
import com.ttsoft.bjtax.smsb.lszs.web.LszsWszlrForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 完税证打印</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class WszPrintAction
    extends SmsbAction
{

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                             "<font color=\"#7C9AAB\">申报征收</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "打印完税证");

    }

    /**
     * 页面初始化
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        WszPrintForm pf = (WszPrintForm) form;

        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud));
            pf.setZhdm(ud.getXtsbm1()); //设置帐户代码
            pf.setPzzldm(CodeConstant.SMSB_PZZLDM); //票证种类代码
            pf.setHeadWszh(pf.getHeadWszh()); //重设完税证号

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);

            //调用processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Show");
    }

    /**
     * 查询
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //把预装载的信息获得并填写
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);

            //调用processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }

    /**
     * 删除
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        return mapping.findForward("Delete");
    }

    /**
     * 打印成功后设置打印标志
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSuccess (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Success";
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //把预装载的信息获得并填写
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_PRINTACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);
            //调用processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

            //成功后返回录入页面
            if ( (pf.getFromPage()).indexOf("gsh") > 0)
            {
                GtgshWszlrForm lrForm = new GtgshWszlrForm();
                forwardFlag = "GtgshBack";
                lrForm.setActionType("Show");
                request.setAttribute("gtgshWszlrForm", lrForm);
            }
            else
            {
                LszsWszlrForm lrForm = new LszsWszlrForm();
                forwardFlag = "LszsBack";
                lrForm.setActionType("Show");
                request.setAttribute("lszsWszlrForm", lrForm);
            }
        }
        catch (Exception ex)
        {
            forwardFlag = "Success";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * 取号打印，作废原完税证号码，获取新的完税证号马
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doReprint (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        WszPrintForm pf = (WszPrintForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //把预装载的信息获得并填写
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_REPRINTACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_WSZPRINT_PROCESSOR);
            //调用processor
            pf = (WszPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");

    }

    /**
     * 返回个体工商户的完税证录入界面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshBack (ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshWszlrForm pf = new GtgshWszlrForm();
            WszPrintForm pf1 = (WszPrintForm) form;
            pf.setActionType("Show");
            request.setAttribute("gtgshWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshBack");
    }

    /**
     * 返回零散税征收的完税证录入界面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsBack (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            LszsWszlrForm pf = new LszsWszlrForm();
            WszPrintForm pf1 = (WszPrintForm) form;
            pf.setActionType("Show");
            request.setAttribute("lszsWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsBack");
    }

}
