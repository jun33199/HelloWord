package com.ttsoft.bjtax.shenbao.sbzl.wqyys.web;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysActionConstant;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import javax.servlet.http.HttpSession;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import java.util.Date;
import java.util.Calendar;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import java.util.*;
import com.ttsoft.bjtax.shenbao.util.*;
import java.sql.*;
import com.ttsoft.bjtax.dj.model.*;

/**
 * 外企营业税申报action，支持按实申报、核定征收、经费换算三种方式
 */
public class WqyysAction extends ShenbaoAction
{

    // 转向的常量的定义
    public static final String SELF = "Show";
    public static final String SUCCESS = "Success";
    public static final String WQYYS = "Wqyys";

    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.WQ;
    }

    /**
     * 取业务信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeQuery方法取得业务数据的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_QUERY调用
     * 	ShenBaoProxy的process方法。
     * 4、调用ShenbaoRapidForm的afterQuery方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        //获取Form对象
        WqyysForm vicForm = (WqyysForm)form;

        try
        {
            HttpSession httpSession = request.getSession();

            // 设置代码表到form
            setCodeTable(request, vicForm);

            // 取得UserData
            UserData userData = this.getUserData(request);

            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);

            VOPackage voPackage = new VOPackage();
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_QUERY);

            voPackage.setData(vicForm.beforeQuery(jbsj));

            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

            // 进行处理结果的前台处理
            Map saveData = vicForm.afterQuery((Map)voPackage.getData(), jbsj);

            // 保存数据到session中
            httpSession.setAttribute(WqyysForm.SHENBAO, saveData.get(WqyysForm.SHENBAO));
            httpSession.setAttribute(WqyysForm.JBXX, saveData.get(WqyysForm.JBXX));
            vicForm.setDone(true);

            // 转向到本页面
            String wqzsfsdm = FriendHelper.getWqzsfsInfo(request).getWqzsfsdm();
            if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
            {
                //核定征收
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_HDZS));
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
            {
                //经费换算
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_JFHS));
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
            {
                //按实申报
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_ASSB));
            }
            else
            {
                //没有找到合适的征收方式
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "未知的外企营业税征收方式！");
                return mapping.findForward(SUCCESS);
            }
        }
        catch(Exception e)
        {
            vicForm.setDone(false);
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 保存申报信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeSave方法取得保存申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_SAVE
     * 	调用ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        //获取Form对象
        WqyysForm vicForm = (WqyysForm)form;
        //生成VOPackage
        VOPackage voPackage = new VOPackage();

        try
        {
            HttpSession httpSession = request.getSession();
            setCodeTable(request, vicForm);
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_SAVE);
            // 从session中取得数据模板设置到form中去
            vicForm.setWqyysTemplate((Wqyys)request.getSession().getAttribute(WqyysForm.JBXX));

            // 取保存的参数
            Map data = vicForm.beforeSave(userData.yhid);
            httpSession.setAttribute(WqyysForm.SHENBAO, data.get(WqyysMapConstant.LIST_WQYYS));
            voPackage.setData(data);

            // 调用后台程序保存
            ShenbaoProxy.getInstance().process(voPackage);

            // 清除session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "外企营业税申报资料保存成功！");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 删除申报信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeDelete方法取得删除申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_DELETE调用
     * 	ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        //获取Form对象
        WqyysForm vicForm = (WqyysForm)form;
        //生成VOPackage
        VOPackage voPackage = new VOPackage();

        try
        {
            HttpSession httpSession = request.getSession();
            setCodeTable(request, vicForm);

            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_DELETE);

            // 从session中取得数据模板设置到form中去
            vicForm.setWqyysTemplate((Wqyys)httpSession.getAttribute(WqyysForm.JBXX));

            // 取删除的参数
            Map data = vicForm.beforeDelete(userData.yhid);
            httpSession.setAttribute(WqyysForm.SHENBAO, data.get(WqyysMapConstant.LIST_WQYYS));
            voPackage.setData(data);

            // 调用后台程序
            ShenbaoProxy.getInstance().process(voPackage);

            // 清除session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "外企营业税申报资料删除成功！");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 设置代码表到form。
     * @param request HttpServletRequest
     * @param vicForm WqyysForm
     */
    private void setCodeTable(HttpServletRequest request, WqyysForm vicForm)
    {
        List szsmList =
            CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);
        vicForm.setSzsmList(szsmList);
    }

    /**
     * 处理异常
     * @param mapping Struts变量
     * @param e 异常变量
     * @param request 请求
     * @param response 回应
     * @return ActionForward 返回值
     */
    public ActionForward processForwardByException(
        ActionMapping mapping,
        Exception e,
        HttpServletRequest request,
        HttpServletResponse response)
    {
        ActionErrors errors = new ActionErrors();

        if(e instanceof InvocationTargetException &&
           ((InvocationTargetException)e).getTargetException() instanceof
           ApplicationException)
        {
            // 从反射机制中取其包含的异常信息
            e.printStackTrace();
            String msg = ((InvocationTargetException)e).getTargetException().
                getMessage();
            errors.add("err1", new ActionError("error.server.custom", msg));
        }
        else
        {
            // 普通异常
            errors.add("err1", new ActionError("error.server.default"));
        }
        saveErrors(request, errors);
        Wqzsf wqzsf = null;
        try
        {
            // 取外企征收方式
            wqzsf = FriendHelper.getWqzsfsInfo(request);
        }
        catch(BaseException be)
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "系统出错！");
            return mapping.findForward(SUCCESS);
        }
        // 按照外企征收方式转向
        return mapping.findForward("Failure" + wqzsf.getWqzsfsdm());
    }

    /**
     * 取消当前操作
     * 转向取消后应去的页面。
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        clearSession(request.getSession());

        //转向返回后的页面。
        return mapping.findForward("Return");
    }

    /**
     * 清除session中保留的数据
     * @param httpSession HttpSession
     */
    private void clearSession(HttpSession httpSession)
    {
        httpSession.removeAttribute(WqyysForm.SHENBAO);
        httpSession.removeAttribute(WqyysForm.JBXX);
    }
}