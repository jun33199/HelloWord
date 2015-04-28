/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 汇总印花税购买撤销 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmlrcxAction
    extends SmsbAction
{
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm aform,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>录入印花税购买情况作废");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/yhsgmlr-000.htm");
    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsgmlrcxForm yForm = (YhsgmlrcxForm) aForm;
            UserData userData = this.getUserData(request);
            //录入人
            yForm.setLrr(userData.getYhid());
            //录入起始日期
            yForm.setLrqsrq(SfDateUtil.getDate());
            //录入截至日期
            yForm.setLrjzrq(SfDateUtil.getDate());
            yForm.setDataList(new ArrayList());
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 查询处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm aForm,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) aForm;

        VOPackage vo = new VOPackage();

        try
        {
            //查询
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
            vo.setData(yForm);
            vo.setUserData(this.getUserData(request));
            yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            yForm.setPgNum(0);
            yForm.setPgSum(0);
            //传递form
            request.setAttribute(mapping.getAttribute(), yForm);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 删除处理
     * @param mapping The ActionMapping used to select this instance
     * @param from The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doDelete (ActionMapping mapping, ActionForm from,
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
        VOPackage vo = new VOPackage();
        try
        {
            YhsgmlrcxForm yForm = (YhsgmlrcxForm) from;
            System.out.println(request.getParameterValues("deleteCheckbox"));
            //得到应删除项的序号
            yForm.setDeleteCheckbox(request.getParameterValues("deleteCheckbox"));
            if (yForm.getDeleteCheckbox() != null)
            {
                for (int i = 0; i < yForm.getDeleteCheckbox().length; i++)
                {
                }
                vo.setAction(CodeConstant.SMSB_DELETEACTION);
                vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
                vo.setData(yForm);
                vo.setUserData(this.getUserData(request));
                yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            }
            yForm.setFromDelete(true);
            //传递form
            request.setAttribute(mapping.getAttribute(), yForm);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
            return mapping.findForward("Delete");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 全部删除处理
     * @param mapping The ActionMapping used to select this instance
     * @param from The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doDeleteAll (ActionMapping mapping, ActionForm from,
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

        VOPackage vo = new VOPackage();

        YhsgmlrcxForm yForm = (YhsgmlrcxForm) from;
        vo.setAction(CodeConstant.SMSB_DELETEALLACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMLRCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));
        try
        {
            yForm = (YhsgmlrcxForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), yForm);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
            return mapping.findForward("DeleteAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 查看处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doView (ActionMapping mapping,
                                 ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            return mapping.findForward("View");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

}