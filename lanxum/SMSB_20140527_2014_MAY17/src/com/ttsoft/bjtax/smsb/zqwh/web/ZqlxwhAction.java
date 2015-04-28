/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期类型维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqlxwhAction
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
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报征收</font>&gt;<font color=\"#6F8EA2\">申报控制</font>&gt;征期日历维护&gt;</td>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "征期类型维护");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-003.htm");

    }

    /**
     * 显示处理
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        try
        {
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 查询处理
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        ZqlxwhForm zf = (ZqlxwhForm) actionForm;
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        zf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(zf);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), zf);
            //流转
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
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
            return mapping.findForward("Return");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 停用处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doUpdate (ActionMapping mapping,
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

        ZqlxwhForm zf = (ZqlxwhForm) form;
        zf.setTyCheckbox(request.getParameterValues("tyCheckbox"));
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        vo.setData(zf);
        try
        {
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), zf);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "停用成功！");
            //更新征期类型代码表
            com.ttsoft.common.util.HttpUtil.reloadCodes(request);

            return mapping.findForward("Update");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 保存处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
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

        //当前的ActionForm
        ZqlxwhForm zf = (ZqlxwhForm) form;
        //将前台列表的数据赋给ActionForm的DataList
        String columns[] = zf.getColumns();
        zf.setDataList(getValuesToList(request, columns));
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        //设置容器里的Data数据ActionForm
        vo.setData(zf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //调用Proxy，初始化ActionForm中值
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), zf);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
            //更新征期类型代码表
            com.ttsoft.common.util.HttpUtil.reloadCodes(request);
            //保存成功,流转
            return mapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //调用检索模块
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(zf);
            vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
            try
            {
                zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
                request.setAttribute(mapping.getAttribute(), zf);
            }
            catch (Exception ex2)
            {
                ex2.printStackTrace();
                //系统捕捉异常，根据异常类型抛出
                throw ExceptionUtil.getBaseException(ex);
            }

            //打印错误信息
            ex.printStackTrace();
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 生成征期日历
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doCreateCalendar (ActionMapping mapping,
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

        //当前的ActionForm
        ZqlxwhForm zf = (ZqlxwhForm) form;
        String whnf = zf.getWhnf();
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_CREATECALENDAR);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(zf);
        vo.setUserData(this.getUserData(request));
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZQWH_ZQLXWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            zf = (ZqlxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), zf);
            //向征期日历查询传递form
            ZqrlcxForm zqrlcxf = new ZqrlcxForm();
            zqrlcxf.setWhnf(whnf);
            zqrlcxf.setHeadZqlx("*");
            zqrlcxf.setSelectTypeRadio("1");
            request.setAttribute("zqrlcxForm", zqrlcxf);
            //流转
            return mapping.findForward("CreateCalendar");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }

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
    public ActionForward doEdit (ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ZqlxwhForm pf1 = (ZqlxwhForm) form;
        ZqlxmxwhForm pf = new ZqlxmxwhForm();
        try
        {
            //把预装载的信息传递给下一个页面
            pf.setActionType("Query");
            pf.setHeadZqlxdm(pf1.getTempZqlxdm()); //年度
            request.setAttribute("zqlxmxwhForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Edit");

    }

}