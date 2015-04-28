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
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 缴款书打印</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class JksPrintAction
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
                             "打印缴款书");

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

        JksPrintForm pf = (JksPrintForm) form;

        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_JKSPRINT_PROCESSOR);

            //调用processor
            pf = (JksPrintForm) ZhsbProxy.getInstance().process(vo);
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

        JksPrintForm pf = (JksPrintForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //把预装载的信息获得并填写
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_JKSPRINT_PROCESSOR);

            //调用processor
            pf = (JksPrintForm) ZhsbProxy.getInstance().process(vo);
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
     * 保存
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
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

        JksPrintForm pf = (JksPrintForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //把预装载的信息获得并填写
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_JKSPRINT_PROCESSOR);
            //调用processor
            pf = (JksPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

            //重新查询
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.GTGSH_JKSPRINT_PROCESSOR);
            //调用processor
            pf = (JksPrintForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Save");
    }

    /**
     * 打印
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doPrint (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        JksPrintForm pf = (JksPrintForm) form;
        try
        {
            //把预装载的信息传递给下一个页面
            //GtgshWszlrForm pf = new GtgshWszlrForm();
            //pf.setActionType("Show");
            request.setAttribute("gtgshWszlrForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Print");
    }

}
