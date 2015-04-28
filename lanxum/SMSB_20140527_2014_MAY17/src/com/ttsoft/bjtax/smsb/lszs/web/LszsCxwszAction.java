/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散税征收撤销完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsCxwszAction
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
                             "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                             "<font color=\"#7C9AAB\">零散税征收</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "撤销收现缴款书");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/wszcx-000.htm");
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
        LszsCxwszForm pf = (LszsCxwszForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            pf.setHeadLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
            pf.setHeadZhdm(ud.getXtsbm1());
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
        //获得当前的userData对象
        UserData ud = this.getUserData(request);

        try
        {
            //把预装载的信息获得并填写
            LszsCxwszForm pf = (LszsCxwszForm) form;

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //调用processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
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
        //获得当前的userData对象
        UserData ud = this.getUserData(request);
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsCxwszForm pf = (LszsCxwszForm) form;
        //把预装载的信息获得并填写
        try
        {
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //调用processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Delete");
    }

    /**
     * 设置打印标记
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        //获得当前的userData对象
        UserData ud = this.getUserData(request);
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsCxwszForm pf = (LszsCxwszForm) form;
        //把预装载的信息获得并填写
        try
        {
            //取出dataList的值
            String columns[] = pf.getColumns();
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_UPDATEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_CXWSZ_PROCESSOR);

            //调用processor
            pf = (LszsCxwszForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "设置打印标记成功！");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Update");
    }

    /**
     * 返回完税证录入页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doBack (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //把预装载的信息传递给下一个页面
            LszsWszlrForm pf = new LszsWszlrForm();
            pf.setActionType("Show");
            request.setAttribute("lszsWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Back");
    }

}
