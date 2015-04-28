/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.util.ArrayList;
import java.util.List;
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
 * <p>Description: 告知事项维护</p>
 * @author 开发六组－－石岩峰
 * @version 1.1
 */
public class GzsxwhAction extends SmsbAction
{
    /**
     * 数据集对象（包括Form和UserData对象）
     */
    private VOPackage vo = new VOPackage();

    /**
     * 用户信息对象
     */
    UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param response The HTTP response we are creating
     */

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报征收</font>&gt;<font color=\"#6F8EA2\">申报控制</font>&gt;告知事项维护&gt;</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "告知事项维护");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/gzwh/gzwh-000.htm");
    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param aFrom The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping, ActionForm aFrom,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            //清除数据
            removeForm(mapping, request);

            GzsxwhForm gf = (GzsxwhForm) aFrom;
            //清除数据
            gf.setChooseTypeRadio("1");
            gf.setJsjdm("");
            gf.setNsrmc("");
            gf.setQylx("");
            gf.setGzlx("");
            gf.setDqjs("");
            gf.setHylb("");
            gf.setJhfs("");
            gf.setGzjzrq("");

            //得到所有的导入批号
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
            vo.setData(gf);
            vo.setUserData(this.getUserData(request));
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);

            //告知起始日期==服务器系统日期
            SfDateUtil sfdateUtil = new SfDateUtil();
            gf.setGzqsrq(sfdateUtil.getDate());
            gf.setDataList(new ArrayList());
            
            //重设form
            request.setAttribute(mapping.getAttribute(), gf);
            
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
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        List phList = new ArrayList();
        VOPackage vo = new VOPackage();

        try
        {
            if (gf.getJsjdm() != null && !gf.getJsjdm().equals("*"))
            {
                //取名称
                vo.setAction(CodeConstant.SMSB_READERACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                vo.setUserData(this.getUserData(request));
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            }
        }
        catch (Exception e)
        {
            gf.setNsrmc("");
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        try
        {
            //查询
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
            vo.setData(gf);
            vo.setUserData(this.getUserData(request));
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("Query");
        }
        catch (Exception e)
        {
            gf.setPgNum(0);
            gf.setPgSum(0);
            try
            {
                //得到所有的导入批号
                vo.setAction(CodeConstant.SMSB_SHOWACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                vo.setUserData(this.getUserData(request));
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
                phList = gf.getPhList();
            }
            catch (Exception ex)
            {
            }

            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 取名称处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doGetNsrmc (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException {
        GzsxwhForm gf = (GzsxwhForm) form;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_READERACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        vo.setUserData(this.getUserData(request));
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("GetNsrmc");
        }
        catch (Exception e) {
            gf.setNsrmc("");
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * @return the element previously at the specified position.
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
        try
        {
            GzsxwhForm gf = (GzsxwhForm) from;
            //得到应删除项的序号
            gf.setDeleteCheckbox(request.getParameterValues("deleteCheckbox"));
            if (gf.getDeleteCheckbox() != null)
            {
                vo.setAction(CodeConstant.SMSB_DELETEACTION);
                vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
                vo.setData(gf);
                gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            }
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * @return the element previously at the specified position.
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

        GzsxwhForm gf = (GzsxwhForm) from;
        vo.setAction(CodeConstant.SMSB_DELETEALLACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        vo.setUserData(this.getUserData(request));
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
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
     * 新增处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doAdd (ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        //数据初始化（新增）
        //告知起始日期==服务器系统日期
        SfDateUtil sfdateUtil = new SfDateUtil();
        gf.setMxGzqsrq(sfdateUtil.getDate());
        gf.setMxJsjdm(null);
        gf.setMxNsrmc(null);
        gf.setMxQylx(null);
        gf.setMxGzlx(null);
        gf.setMxGzjzrq(null);
        gf.setMxGzsxxxxx(null);

        gf.setMxJhfs(null); //结合方式（与）
        gf.setMxQylx(null);
        gf.setMxHylb(null);
        gf.setMxDqjs(null);
        gf.setMxChooseTypeRadio("1");
        return mapping.findForward("Add");
    }

    /**
     * 修改处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doModify (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) form;
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.GZWH_GZSXWH_PROCESSOR);
        vo.setData(gf);
        try
        {
            gf = (GzsxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);
            return mapping.findForward("Modify");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 返回处理
     * @param mapping The ActionMapping used to select this instance
     * @param aFrom The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doReturn (ActionMapping mapping, ActionForm aFrom,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            removeForm(mapping, request);
            GzsxwhForm gf = (GzsxwhForm) aFrom;
            gf.reset(mapping, request);
            //释放session空间
            request.getSession().removeAttribute("DataList");
            return mapping.findForward("Return");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }
    public ActionForward docometomain (ActionMapping mapping, ActionForm aFrom,
            HttpServletRequest request,
            HttpServletResponse response)
throws
BaseException
{
try
{
return mapping.findForward("cxmian");

}
catch (Exception e)
{
e.printStackTrace();
throw ExceptionUtil.getBaseException(e);
}

}

}
