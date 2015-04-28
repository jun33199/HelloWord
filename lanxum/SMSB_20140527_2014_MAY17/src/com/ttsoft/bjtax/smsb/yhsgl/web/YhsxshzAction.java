/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 导入代售单位印花税销售情况汇总 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsxshzAction
    extends SmsbAction
{
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>汇总代售单位销售情况");
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
            YhsxshzForm form = (YhsxshzForm) aForm;
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 返回处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doTurnback (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//返回导入界面
            YhsxsdrForm yForm = new YhsxsdrForm();
            yForm.reset(mapping, request);
            return mapping.findForward("Turnback");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 撤销处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//跳至撤销缴款书
            YhsxshzForm form = (YhsxshzForm) aForm;
            YhsxshzcxForm yForm = new YhsxshzcxForm();
            yForm.reset(mapping, request);
            yForm.setDsjsjdm(form.getDsjsjdm());
            request.setAttribute("yhsxshzcxForm", yForm);
            return mapping.findForward("Cxjks");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 打印处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
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
            //把预装载的信息传递给下一个页面
            JksPrintForm pf = new JksPrintForm();
            YhsxshzForm yForm = (YhsxshzForm) form;
            pf.setHeadJkpzh(yForm.getJkpzh());
            pf.setHeadJsjdm(yForm.getDsjsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //数据来源:印花税汇总
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Print");
    }

}