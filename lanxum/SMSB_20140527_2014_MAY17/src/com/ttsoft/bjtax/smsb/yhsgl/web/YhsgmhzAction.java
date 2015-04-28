/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;


import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 汇总印花税购买情况 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzAction extends SmsbAction {
    UserData userData = null;

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>汇总印花税购买情况");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsgmhz-000.htm");
    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm aForm, HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        try {
            //清理页面域，设定基本数据
            YhsgmhzForm yForm = (YhsgmhzForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            // 取登记接口
//            ServiceProxy proxy = new ServiceProxy();
//            // 取得银行信息
//            YHZH yhzh = new YHZH();
//            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//                    "swjgzzjgdm", userData.getSsdwdm(), "jsjdm");
//            Map djMap = proxy.getDjInfo(dsJsjdm);
//            if (djMap != null) {
//                List yhList = (List) djMap.get("YHZH");
//                if (yhList != null && yhList.size() > 0) {
//                    yhzh = (YHZH) yhList.get(0);
//                }
//            }
            // 判断是否联网
            if(LWUtil.isZsjgLW(getServlet().getServletContext(),
                                      userData.getSsdwdm()))
            {
                yForm.setLw("01");
            }
            else
            {
                yForm.setLw("00");
            }
            yForm.setLrr(userData.getYhid());
            yForm.setZsjgdm(userData.getSsdwdm());
            yForm.setHzqsrq(SfDateUtil.getDate());
            yForm.setHzjsrq(SfDateUtil.getDate());
            return mapping.findForward("Show");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 汇总处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doHzjks(ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsgmhzForm yForm = (YhsgmhzForm) aForm;

        vo.setAction(CodeConstant.SMSB_HZJKSACTION);
        vo.setProcessor(CodeConstant.YHSGL_GMHZ_PROCESSOR);
        vo.setData(yForm);

        try { //调用processor
            vo.setUserData(this.getUserData(request));
            yForm = (YhsgmhzForm) ZhsbProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Hzjks");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 撤销处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks(ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            //跳转
            YhsgmhzcxForm yForm = new YhsgmhzcxForm();
            yForm.reset(mapping, request);
            return mapping.findForward("Cxjks");
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 打印处理
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        YhsgmhzForm yForm = (YhsgmhzForm) form;
        userData = this.getUserData(request);
        // 取登记接口
        ServiceProxy proxy = new ServiceProxy();
        // 取得银行信息
        YHZH yhzh = new YHZH();
        String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
        "swjgzzjgdm", userData.getSsdwdm(), "jsjdm");
        Map djMap = proxy.getDjInfo(dsJsjdm);
        if (djMap != null) {
            List yhList = (List) djMap.get("YHZH");
            if (yhList != null && yhList.size() > 0) {
                yhzh = (YHZH) yhList.get(0);
            }
        }
        // 根据是否联网,判断打印缴款书或者缴款申请单
        if (LWUtil.isZsjgLW(getServlet().getServletContext(),
                        userData.getSsdwdm())) {
            //把预装载的信息传递给下一个页面
            JksqdPrintForm pf = new JksqdPrintForm();

            pf.setH_jsjdm(yForm.getDsjsjdm()); // 代售单位计算机代码
            pf.setH_sbbh(yForm.getSbbh()); // 申报编号
            pf.setSbbh(yForm.getSbbh());
            pf.setJsjdm(yForm.getDsjsjdm());

            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //数据来源:印花税汇总
//            pf.setHjjexx(yForm.getSjje());//合计金额小写
//            pf.setHjjedx(Currency.convert(Double.parseDouble(yForm.getSjje())));//合计金额大写
            pf.setSkje(yForm.getSjje()); //收款金额
            pf.setLrr(userData.getYhid()); //录入人
            pf.setYhdm(yhzh.getYhdm()); //银行代码
            pf.setYhmc(yhzh.getKhyhmc()); //银行名称
            pf.setZh(yhzh.getZh()); //银行账号
            pf.setSwjgzzjgdm(userData.getSsdwdm()); //税务机关组织机构代码
            pf.setActionType("Show");

            request.setAttribute("jksqdPrintForm", pf);
            return mapping.findForward("PrintJksqd");
        } else {
            //把预装载的信息传递给下一个页面
            JksPrintForm pf = new JksPrintForm();
            pf.setHeadJkpzh(yForm.getJkpzh());
            pf.setHeadJsjdm(yForm.getDsjsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_YHSHZ); //数据来源:印花税汇总
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
            return mapping.findForward("Print");
        }
    }
}
