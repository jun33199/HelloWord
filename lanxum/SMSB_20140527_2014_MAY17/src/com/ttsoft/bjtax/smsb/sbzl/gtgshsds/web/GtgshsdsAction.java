/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug; //import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.GtgshsdsHelper;
import com.ttsoft.common.model.UserData;
//import java.util.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户所得税</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsAction
    extends SmsbAction
{
    /**
     * 操作员信息
     */
    private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;查帐征收个体工商户所得税年度（月份）申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "查帐征收个体工商户所得税年度（月份）申报表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/gtgshsds/gtgshsds-000.htm");

    }

    /**
     * 初始化页面数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (GtgshsdsForm) SbzlProxy.getInstance().process(vo);
            form.setDataList(GtgshsdsHelper.getShowList());
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //流转
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 查询已申报数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *         ApplicationException 业务异常类型抛出
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (GtgshsdsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //流转
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 保存申报数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *
     */
    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        //将前台列表的数据赋给ActionForm的DataList
        String columns[] = form.getColumns();
        form.setDataList(getValuesToList(httpServletRequest, columns));
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setUserData(userData);
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "保存成功！");
            //流转
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");

            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除申报数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshsdsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *
     */

    public ActionForward doDelete (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }
        //当前的ActionForm
        GtgshsdsForm form = (GtgshsdsForm) actionForm;
        //将前台列表的数据赋给ActionForm的DataList
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor.GtgshsdsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "删除成功！");
            //流转
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshsdsHelper.getShowList());
            form.setJsjdm("");
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}