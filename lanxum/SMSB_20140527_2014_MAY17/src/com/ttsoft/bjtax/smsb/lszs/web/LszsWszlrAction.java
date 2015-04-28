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
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.WszPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散税征收完税证录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsWszlrAction
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
                             "收现缴款书录入");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/wszlr-000.htm");
        //初始化页面明细使用js数组
        try
        {
            LszsWszlrForm pf = (LszsWszlrForm) form;

            //设置初始化的js数组
            //设置初始化方法的参数
            ZhsbActionForm temp = new ZhsbActionForm();
            //设置计算机代码
            UserData ud = this.getUserData(request);
            temp.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //获得本级的计算机代码
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);

            pf.setScriptStr(zhsb.getScriptStr());
        }
        catch (Exception ex)
        {
        }

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

        LszsWszlrForm pf = (LszsWszlrForm) form;
        pf.reset(mapping, request);

        try
        {
            //获得预装载的信息
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
            }
            pf.setZhdm(ud.getXtsbm1()); //设置帐户代码
            pf.setPzzldm(CodeConstant.SMSB_PZZLDM); //票证种类代码
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //获得本级的计算机代码

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //调用processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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

        LszsWszlrForm pf = (LszsWszlrForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            //
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //调用processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
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
        String forwardFlag = "Save";
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        LszsWszlrForm pf = (LszsWszlrForm) form;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);

            String columns[] = pf.getColumns();
            pf.setDataList(getValuesToList(request, columns));

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SAVEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_WSZLR_PROCESSOR);
            //调用processor
            pf = (LszsWszlrForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
                /********************Modified by lufeng 2003-11-11********************/
            //保存成功后打开打印页面
            WszPrintForm wszPF = new WszPrintForm();
            wszPF.setActionType("Show");
            wszPF.setFromPage("LszsBack");
            wszPF.setNdzb(pf.getNdzb()); //年度字别
            wszPF.setHeadWszh(pf.getWszh()); //完税证号
            request.setAttribute("wszPrintForm", wszPF);
            forwardFlag = "Print";
        }
        catch (Exception ex)
        {
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * 返回完税证
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDismiss (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //把预装载的信息传递给下一个页面
            //GtgshWszlrForm pf = (GtgshWszlrForm) form;
            LszsCxwszForm pf = new LszsCxwszForm();
            //pf1.setNsrjsjdm(pf.getNsrjsjdm());
            pf.setActionType("Show");
            request.setAttribute("lszsCxwszForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Dismiss");
    }

    /**
     * 取得包含税种税目list和营业税附加税list的list
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @return the element previously at the specified position.
     * @exception Exception
     */
    private ActionForm getInitList (ActionForm actionForm)
        throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        //调用综合申报的processor取得前台显示用的js数组
        vo.setProcessor(CodeConstant.GTGSH_WSZLR_PROCESSOR);
        vo.setData(actionForm);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
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
        try
        {
            WszPrintForm pf = new WszPrintForm();
            LszsWszlrForm pf1 = (LszsWszlrForm) form;
            pf.setActionType("Show");
            pf.setFromPage("LszsBack");
            pf.setHeadWszh(pf1.getWszh()); //完税证号(pf1.getWszh());
            request.setAttribute("wszPrintForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Print");
    }

}
