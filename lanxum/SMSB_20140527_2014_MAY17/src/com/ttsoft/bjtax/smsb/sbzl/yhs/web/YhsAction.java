/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.yhs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 印花税年度纳税申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class YhsAction
    extends SmsbAction
{
    /**
     * 操作员数据
     */

    private UserData userData = null;
    /**
     * 明细数据列表
     */
    private List dataList = new ArrayList();

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;印花税年度纳税申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "印花税年度申报表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/yhs/yhs-000.htm");

    }

    /**
     * 初始化页面数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        YhsForm yhsForm = (YhsForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

//Insert  Start  Zhou kejing 20031107
        yhsForm.setJsjdm("");
//Insert  End    Zhou kejing 20031107

        //补充默认展现需要带出的内容
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
            dataList = yhsForm.getDataList();
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            yhsForm);
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
     * @param actionForm YhsForm
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
        Debug.out("--- Debug --- Here is YhsAction" + ".doQuery");
        //把预装载的信息获得并填写
        YhsForm yhsForm = (YhsForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());
//Insert  Start  Zhou kejing 20031121
        yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            //调用Proxy，初始化ActionForm中值
            yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            yhsForm);
            //流转
        }
        catch (Exception ex)
        {
//Insert  Start  Zhou kejing 20031107
            yhsForm.setJsjdm("");
            //补充默认展现需要带出的内容
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(yhsForm);
            vo.setProcessor(
                "com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
            try
            {
                //调用Proxy，初始化ActionForm中值
                yhsForm = (YhsForm) SbzlProxy.getInstance().process(vo);
                httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                                yhsForm);
            }
            catch (Exception ex1)
            {
                //系统捕捉异常，根据异常类型抛出
                throw ExceptionUtil.getBaseException(ex1);
            }
//Insert  End    Zhou kejing 20031107
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);

        }
        return actionMapping.findForward("Query");
    }

    /**
     * 保存申报数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        Debug.out("--- Debug --- Here is YhsAction" + ".doSave");
        //防止刷新或重复提交
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }
        YhsForm yhsForm = (YhsForm) actionForm;
        String columns[] = yhsForm.getColumns();

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

        //获得输入的值，同时放到dataList中
        yhsForm.setDataList(getValuesToList(httpServletRequest, columns));
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        //调用processor
        try
        {
            SbzlProxy.getInstance().process(vo);
            yhsForm.reset(actionMapping, httpServletRequest);
            yhsForm.setDataList(dataList);
//Insert  Start  Zhou kejing 20031121
            yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "保存成功！");
            //流转
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除申报数据
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm YhsForm
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
        YhsForm yhsForm = (YhsForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        yhsForm.setLrr(userData.getYhid());

        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(yhsForm);
        vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.yhs.processor.YhsProcessor");
        vo.setUserData(this.getUserData(httpServletRequest));
        //调用processor
        try
        {
            SbzlProxy.getInstance().process(vo);
            yhsForm.reset(actionMapping, httpServletRequest);
            yhsForm.setDataList(dataList);
//Insert  Start  Zhou kejing 20031121
            yhsForm.setCjrq("");
//Insert  End    Zhou kejing 20031121
            //流转
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "删除成功！");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}