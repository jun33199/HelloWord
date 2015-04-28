/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 * <p>
 * Description: 撤销印花税购买情况汇总 Action
 * </p>
 *
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzcxAction extends SmsbAction {
    UserData userData = null;

    protected void initialRequest(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request
                .setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                              "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>撤销印花税购买情况汇总");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsgmhzcx-000.htm");
    }

    /**
     * 显示处理
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm aForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            return mapping.findForward("Show");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 查询处理
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        VOPackage vo = new VOPackage();
        YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;

        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));

        try { // 转到查询
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            // 标记：是从 查询 到 查询
            yForm.setIsFromCx(false);
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Query");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 撤销处理
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks(ActionMapping mapping, ActionForm aForm,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsgmhzcxForm yForm = (YhsgmhzcxForm) aForm;

        vo.setAction(CodeConstant.SMSB_CXJKSACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
        vo.setData(yForm);
        vo.setUserData(this.getUserData(request));
        try { // 若撤销成功则直接转到查询
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            // 标记：是从 撤销 到 查询
            yForm.setIsFromCx(true);
            vo.setUserData(this.getUserData(request));
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.YHSGL_GMHZCX_PROCESSOR);
            vo.setData(yForm);
            yForm = (YhsgmhzcxForm) ZhsbProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), yForm);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
            return mapping.findForward("Query");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 返回处理
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param aForm
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doTurnback(ActionMapping mapping, ActionForm aForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws
            BaseException {
        try {
            // 清理并预设页面域 转到 印花税购买汇总 页面
            YhsgmhzForm yForm = new YhsgmhzForm();
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(userData.getYhid());
            yForm.setZsjgdm(userData.getSsdwdm());
            return mapping.findForward("Turnback");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 打印处理
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            YhsgmhzcxForm yForm = (YhsgmhzcxForm) form;
            // 获得当前的userData对象
            UserData ud = this.getUserData(request);
            // 取登记接口
            ServiceProxy proxy = new ServiceProxy();
            // 取得银行信息
            YHZH yhzh = new YHZH();
            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
            Map djMap = proxy.getDjInfo(dsJsjdm);
            if (djMap != null) {
                List yhList = (List) djMap.get("YHZH");
                if (yhList != null && yhList.size() > 0) {
                    yhzh = (YHZH) yhList.get(0);
                }
            }

            if (LWUtil.isZsjgLW(getServlet().getServletContext(), ud.getSsdwdm())) {
                //把预装载的信息传递给下一个页面
                JksqdPrintForm pf = new JksqdPrintForm();

                pf.setH_jsjdm(yForm.getDsjsjdm()); // 代售单位计算机代码
                pf.setH_sbbh(yForm.getCxsbbh()); // 申报编号
                pf.setSbbh(yForm.getCxsbbh());
                pf.setJsjdm(yForm.getDsjsjdm());

                pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //数据来源
                pf.setLrr(ud.getYhid()); //录入人
                pf.setYhdm(yhzh.getYhdm()); //银行代码
                pf.setYhmc(yhzh.getKhyhmc()); //银行名称
                pf.setZh(yhzh.getZh()); //银行账号
                pf.setSwjgzzjgdm(ud.getSsdwdm()); //税务机关组织机构代码
                pf.setActionType("Show");
                request.setAttribute("jksqdPrintForm", pf);
                return mapping.findForward("PrintJksqd");
            } else {
                //把预装载的信息传递给下一个页面
                JksPrintForm pf = new JksPrintForm();
                pf.setHeadJkpzh(yForm.getCxjkpzh());
                pf.setHeadJsjdm(yForm.getDsjsjdm());
                pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); // 数据来源:印花税汇总
                pf.setActionType("Show");
                request.setAttribute("jksPrintForm", pf);
            }
        } catch (Exception ex) {
        }
        return mapping.findForward("Print");
    }
}
