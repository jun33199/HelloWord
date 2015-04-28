/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsHzjksForm;
import com.ttsoft.bjtax.smsb.lszs.web.LszsZfjksForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsAction;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 一票多税的查看页面</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshJksYpdsAction
    extends ZhsbjksypdsAction
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
                             "<font color=\"#7C9AAB\">个体工商户</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "缴款书");

    }

    /**
     * 返回个体工商户的作废页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshZf (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        Debug.out("--- Debug --- Here is GtgshJksYpdsAction"
                  + ".doYpds");

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            GtgshZfjksForm pf = new GtgshZfjksForm();
            pf.setActionType("Query");
            pf.setHzfs(pf1.getHzlx()); //汇总类型 或汇总方式
            pf.setJsjdm(pf1.getGtgshJsjdm()); //计算机代码
            request.setAttribute("gtgshZfjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshZf");
    }

    /**
     * 返回个体工商户的汇总页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshHz (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            GtgshHzjksForm pf = new GtgshHzjksForm();
            pf.setSbhzdh(pf1.getSbhzdh()); //申报汇总单号
            pf.setHzlx(pf1.getHzlx()); //汇总类型
            pf.setHzjsrq(pf1.getHzjsrq()); //汇总结束日期
            pf.setHzksrq(pf1.getHzksrq()); //汇总开始日期
            pf.setYpys(pf1.getYpys()); //一票一税标识

            pf.setActionType("Query");

            request.setAttribute("gtgshHzjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshHz");
    }

    /**
     * 返回零散证收的作废页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsZf (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            LszsZfjksForm pf = new LszsZfjksForm();
            pf.setActionType("Query");
            pf.setHzfs(pf1.getHzlx()); //汇总类型 或汇总方式
            pf.setJsjdm(pf1.getGtgshJsjdm()); //计算机代码
            request.setAttribute("lszsZfjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsZf");
    }

    /**
     * 返回零散证收的汇总页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsHz (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            LszsHzjksForm pf = new LszsHzjksForm();
            pf.setSbhzdh(pf1.getSbhzdh()); //申报汇总单号
            pf.setHzlx(pf1.getHzlx()); //汇总类型
            pf.setHzjsrq(pf1.getHzjsrq()); //汇总结束日期
            pf.setHzksrq(pf1.getHzksrq()); //汇总开始日期
            pf.setYpys(pf1.getYpys()); //一票一税标识

            pf.setActionType("Query");
            request.setAttribute("lszsHzjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsHz");
    }

}