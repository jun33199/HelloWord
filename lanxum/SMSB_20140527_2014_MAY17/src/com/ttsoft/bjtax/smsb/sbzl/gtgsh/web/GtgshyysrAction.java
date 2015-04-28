/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;//import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.GtgshHelper;
import com.ttsoft.common.model.UserData;
//import java.util.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户营业所得</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;个体工商户营业收入申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "个人所得税申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
            "help/smsb/sbzl/gtgshyysr/gtgshyysr-000.htm");

    }

    /**
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_SHOWACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
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
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
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
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_QUERYACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
        //当前的ActionForm
        Debug.out("Query");
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setUserData(userData);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Query------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_QUERYACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doUpdateQuery (ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        form.setJsjdm(form.getTempJsjdm());
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        //vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setAction(CodeConstant.SMSB_CXWSZACTION); //使用撤销完税证的action id
        vo.setUserData(userData);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (GtgshyysrForm) SbzlProxy.getInstance().process(vo);
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            //Debug.out("ttt="+form.getDataList().size());
            return actionMapping.findForward("UpdateQuery");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、调用GtgshyysrForm的doSave方法取得取业务数据的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doSave方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrrdm(userData.getYhid());
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
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "保存成功！");
            form.setTempSbrq("");
            //保存成功,流转
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Save------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doDelete方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm GtgshyysrForm
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
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        GtgshyysrForm form = (GtgshyysrForm) actionForm;
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
            "com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "删除成功！");
            //流转
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            Debug.out("-------------Delete------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setDataList(GtgshHelper.getShowList());
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得GtgshyysrForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor.GtgshyysrProcessor，
     *         voPackage的Data域为GtgshyysrForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doList方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param mapping struts.action.ActionMapping
     * @param form GtgshyysrForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doList (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshyysrForm pf1 = (GtgshyysrForm) form;
            GtgshyysrCxForm pf = new GtgshyysrCxForm();
            pf.setHeadJsjdm(pf1.getJsjdm());
            pf.setTempJsjdm(pf1.getJsjdm());
            pf.setActionType("Show");
            request.setAttribute("gtgshyysrCxForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("List");
    }

}