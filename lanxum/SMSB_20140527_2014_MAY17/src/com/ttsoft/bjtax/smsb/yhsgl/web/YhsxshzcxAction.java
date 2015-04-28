/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 撤销印花税代售单位销售汇总 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsxshzcxAction
    extends SmsbAction
{
    UserData userData = null;

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>撤销印花税代售单位销售汇总");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsxshzcx-000.htm");

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
            YhsxshzcxForm yForm = (YhsxshzcxForm) aForm;
            yForm.reset(mapping, request);
            //如果有代售单位计算机代码，则带出名称\电话
            if (!yForm.getDsjsjdm().equals(""))
            {
                try
                { //调登记接口
                    ServiceProxy djProxy = new ServiceProxy();
                    HashMap ghdwMap = djProxy.getDjInfo(yForm.getDsjsjdm(),
                        this.getUserData(request));
                    SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                    yForm.setDsdwmc(swdjjbsj.getNsrmc());
                    yForm.setDsdwlxdh(swdjjbsj.getJydzlxdm());
                }
                catch (Exception e2)
                {
                    throw ExceptionUtil.getBaseException(e2);
                }
            }
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
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
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        VOPackage vo = new VOPackage();
        YhsxshzcxForm yForm = (YhsxshzcxForm) aForm;
        //如果有代售单位计算机代码，则带出名称\电话
        if (!yForm.getDsjsjdm().equals(""))
        {
            try
            { //调登记接口
                ServiceProxy djProxy = new ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(yForm.getDsjsjdm(),
                    this.getUserData(request));
                SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                yForm.setDsdwmc(swdjjbsj.getNsrmc());
                yForm.setDsdwlxdh(swdjjbsj.getJydzlxdm());
            }
            catch (Exception e2)
            {
                yForm.setDsjsjdm("");
                throw ExceptionUtil.getBaseException(e2);
            }
        }

        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(CodeConstant.YHSGL_XSHZCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));

        try
        {
            yForm = (YhsxshzcxForm) ZhsbProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Query");
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
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsxshzcxForm yForm = (YhsxshzcxForm) aForm;

        vo.setAction(CodeConstant.SMSB_CXJKSACTION);
        vo.setProcessor(CodeConstant.YHSGL_XSHZCX_PROCESSOR);
        vo.setData(yForm);

        try
        {//调撤销过程，如果成功，转到查询状态
            vo.setUserData(this.getUserData(request));
            yForm = (YhsxshzcxForm) ZhsbProxy.getInstance().process(vo);
            yForm.setIsFromCx(true);
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.YHSGL_XSHZCX_PROCESSOR);
            vo.setData(yForm);
            yForm = (YhsxshzcxForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), yForm);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取名称处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doReader (ActionMapping mapping,
                                   ActionForm aForm, HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        YhsxshzcxForm yForm = (YhsxshzcxForm) aForm;
        try
        { //调登记接口
            ServiceProxy djProxy = new ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(yForm.getDsjsjdm(),
                                                this.getUserData(request));
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            yForm.setDsdwmc(swdjjbsj.getNsrmc());
            yForm.setDsdwlxdh(swdjjbsj.getJydzlxdm());
        }
        catch (Exception e2)
        {
            yForm.setDsjsjdm("");
            yForm.setDsdwlxdh("");
            yForm.setDsdwmc("");
            throw ExceptionUtil.getBaseException(e2);
        }
        return mapping.findForward("Reader");
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

}