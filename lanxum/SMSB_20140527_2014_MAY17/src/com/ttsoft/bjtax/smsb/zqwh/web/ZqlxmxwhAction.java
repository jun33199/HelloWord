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
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 税种与征期类型明细维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqlxmxwhAction
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
                             "征期日历维护");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-005.htm");

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
        ZqlxmxwhForm sf = (ZqlxmxwhForm) actionForm;
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(sf);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZQWH_ZQLXMXWH_PROCESSOR);
        try
        {
            //调用Proxy，初始化ActionForm中值
            sf = (ZqlxmxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            httpServletRequest.setAttribute(actionMapping.getAttribute(), sf);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //系统捕捉异常，根据异常类型抛出
            throw ExceptionUtil.getBaseException(ex);
        }
        //流转
        return actionMapping.findForward("Query");
    }

    /**
     * 删除处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @throws BaseException
     */
    public ActionForward doDelete (ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        return null;
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
        String forwardFlag = "Save";
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        ZqlxmxwhForm pf = (ZqlxmxwhForm) form;
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZQWH_ZQLXMXWH_PROCESSOR);
        //设置容器里的Data数据ActionForm
        vo.setData(pf);

        UserData userData = this.getUserData(request);
        vo.setUserData(this.getUserData(request));

        try
        {
            //调用Proxy，初始化ActionForm中值
            pf = (ZqlxmxwhForm) ZhsbProxy.getInstance().process(vo);
            //传递form
            request.setAttribute(mapping.getAttribute(), pf);
            //显示成功信息
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
            //保存成功,流转

            //保存成功后打开查询页面
            ZqlxwhForm cxpf = new ZqlxwhForm();
            cxpf.setActionType("Query");
            request.setAttribute("zqlxwhForm", cxpf);
            forwardFlag = "Back";
        }
        catch (Exception ex)
        {
            //打印错误信息
            ex.printStackTrace();
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * 返回征期日历查询页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doBack (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ZqlxmxwhForm pf1 = (ZqlxmxwhForm) form;
        ZqrlmxcxForm pf = new ZqrlmxcxForm();
        try
        {
            //把预装载的信息传递给下一个页面
            pf.setActionType("Query");
            request.setAttribute("zqlxwhForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Back");
    }

}
