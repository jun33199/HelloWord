/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.wqyys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税局税务管理模块--上门申报</p>
 * <p>Description: 北京市外国企业营业税纳税申报表－－经费换算－－Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class WqyysjfhsAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm WqyysjfhsForm
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;北京市外国企业营业税纳税申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "经费换算");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/wqyys/wqyys-000.htm");

    }

    /**
     * 取业务信息
     * 1、取得WqyysjfhsForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	 com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor，
     *         voPackage的Data域为WqyysjfhsForm
     * 	voPackage的action域为CodeConstant.SMSB_SHOWACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm WqyysjfhsForm
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
        Debug.out("Execute ActiondoShow()!");
        //当前的ActionForm
        WqyysjfhsForm form = (WqyysjfhsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (WqyysjfhsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //流转
            Debug.out("Forword!");
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得WqyysjfhsForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	 com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor，
     *         voPackage的Data域为WqyysjfhsForm
     * 	voPackage的action域为CodeConstant.SMSB_QUERYACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm WqyysjfhsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("Execute ActiondoQuery()!");
        //当前的ActionForm
        WqyysjfhsForm form = (WqyysjfhsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (WqyysjfhsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Query Error!----------------");
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得WqyysjfhsForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	 com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor，
     *         voPackage的Data域为WqyysjfhsForm
     * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm WqyysjfhsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("Execute ActiondoSave()!");
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        WqyysjfhsForm form = (WqyysjfhsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        //将前台列表的数据赋给ActionForm的DataList
        String columns[] = form.getColumns();
        form.setDataList(getValuesToList(httpServletRequest, columns));
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            //流转
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "保存成功！");
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Save Error!---------------");
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得WqyysjfhsForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	 com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor，
     *         voPackage的Data域为WqyysjfhsForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm WqyysjfhsForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doDelete (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("Execute ActiondoDelete()!");
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            Debug.out("------------doHandle-----------------");
            return forward;
        }
        //当前的ActionForm
        WqyysjfhsForm form = (WqyysjfhsForm) actionForm;
        //将前台列表的数据赋给ActionForm的DataList
        String columns[] = form.getColumns();
        form.setDataList(getValuesToList(httpServletRequest, columns));
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
            "com.ttsoft.bjtax.smsb.sbzl.wqyys.processor.WqyysjfhsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            //流转
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "删除成功！");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}