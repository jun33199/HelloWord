package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;

/**
 * <p>Title: 扣缴企业所得税-备案登记表Action</p>
 *
 * <p>Description: 处理备案登记表页面应用事件响应</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ApplicationException;


public class DztzAction extends SmsbAction
{

    public DztzAction()
    {
    }

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param actionForm
     *            QysdsnbForm
     * @param httpServletRequest
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */
    protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)
    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">扣缴企业所得税管理台帐</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "扣缴企业所得税管理台帐");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
    }

    /**
     * 初始化页面数据
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            HdzssdsjbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {

        DztzForm badjForm = new DztzForm();
        /**
         * 初始化页面下拉菜单
         */

        // 将badjForm 放入request中
        request.setAttribute(mapping.getAttribute(), badjForm);
        String tzxxHtml = DztzHelper.boToHtml(new DztzBO());
        request.setAttribute("QUERY_HTML", tzxxHtml);

        return mapping.findForward("Show");
    }


    public ActionForward doQueryTZXX(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
        BaseException
    {
        System.out.println("=========coming in to doQueryTZXX===========");
        // 获取本系统用户信息
        UserData userData = this.getUserData(request);
        DztzForm theForm = (DztzForm) form;
        try {
            //设置查询参数
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION1);
            vo.setData(theForm.getJsjdm());
            vo.setUserData(userData);
            vo.setProcessor(CodeConstant.SBZL_KJQYSDS_DZTZ_PROCESSOR);
            DztzBO bo = (DztzBO) SbzlProxy.getInstance().process(vo);
            String tzxxHtml = DztzHelper.boToHtml(bo);
            request.setAttribute("QUERY_HTML", tzxxHtml);

        }
        catch (Exception ex) {
            throw new ApplicationException("备案登记表查询纳税人信息错误，请与管理员联系！");
        }

        return mapping.findForward("Show");
    }


}
