/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshJksYpdsForm;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshJksYpysForm;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import java.util.List;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import java.util.Map;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散税征收作废完税证汇总的缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsZfjksAction extends SmsbAction {

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                             "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                             "<font color=\"#7C9AAB\">零散税征收</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "作废缴款书");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/jkscx-000.htm");
    }

    /**
     * 页面初始化
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        LszsZfjksForm pf = (LszsZfjksForm) form;
        pf.setHzfs("1"); //默认的查询方式
        try {
            //获得预装载的信息
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            // 取登记接口
//            ServiceProxy proxy = new ServiceProxy();
//            // 取得银行信息
//            YHZH yhzh = new YHZH();
//            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
//                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
//            Map djMap = proxy.getDjInfo(dsJsjdm);
//            if (djMap != null) {
//                List yhList = (List) djMap.get("YHZH");
//                if (yhList != null && yhList.size() > 0) {
//                    yhzh = (YHZH) yhList.get(0);
//                }
//            }
            // 根据是否联网,判断打印缴款书或者缴款申请单
            if (LWUtil.isZsjgLW(getServlet().getServletContext(), ud.getSsdwdm())) {
                pf.setLw("01");
            } else {
                pf.setLw("00");
            }
            pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //获得本级的计算机代码

            //pf.se
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //调用processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Show");
    }

    /**
     * 查询
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {

        UserData ud = this.getUserData(request);
        LszsZfjksForm pf = (LszsZfjksForm) form;
        try {
            //
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //调用processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Query");
    }

    /**
     * 删除
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
        //防止refresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        LszsZfjksForm pf = (LszsZfjksForm) form;
        //把预装载的信息获得并填写
        try {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);

            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_DELETEACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);

            //调用processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);
            //重新进行查询
            vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_ZFJKS_PROCESSOR);
            //调用processor
            pf = (LszsZfjksForm) ZhsbProxy.getInstance().process(vo);

            //保存返回值--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "作废成功！");
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        pf.reset(mapping, request);
        return mapping.findForward("Delete");
    }

    /**
     * 一票一税
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpys(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        try {
            LszsZfjksForm pf1 = (LszsZfjksForm) form;
            GtgshJksYpysForm pf = new GtgshJksYpysForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh());
            pf.setHzlx(pf1.getHzfs()); //汇总方式，汇总类型
            pf.setGtgshJsjdm(pf1.getJsjdm()); //计算机代码
            pf.setFhbs("LszsZf"); //设置返回标识
            request.setAttribute("gtgshJksYpysForm", pf);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypys");
    }

    /**
     * 一票多税
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doYpds(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        try {
            LszsZfjksForm pf1 = (LszsZfjksForm) form;
            GtgshJksYpdsForm pf = new GtgshJksYpdsForm();
            pf.setActionType("Query");
            pf.setJkpzh(pf1.getJkpzh());
            pf.setHzlx(pf1.getHzfs()); //汇总方式，汇总类型
            pf.setGtgshJsjdm(pf1.getJsjdm()); //计算机代码
            pf.setFhbs("LszsZf"); //设置返回标识
            request.setAttribute("gtgshJksYpdsForm", pf);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Ypds");
    }

    /**
     * 打印
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            LszsZfjksForm zfpf = (LszsZfjksForm) form;
            UserData userData = this.getUserData(request);
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

                pf.setH_jsjdm(zfpf.getJsjdm()); // 代售单位计算机代码
                pf.setH_sbbh(zfpf.getJkpzh()); // 申报编号
                pf.setSbbh(zfpf.getJkpzh());
                System.out.println(zfpf.getSbbh());
                System.out.println("sbbh================="+pf.getSbbh());
                pf.setJsjdm(zfpf.getJsjdm());

                pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSHZ); //数据来源
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
                LszsZfjksForm pf1 = (LszsZfjksForm) form;
                pf.setHeadJkpzh(pf1.getJkpzh());
                pf.setHeadJsjdm(pf1.getJsjdm());
                pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSHZ); //数据来源
                pf.setActionType("Show");
                request.setAttribute("jksPrintForm", pf);
            }
            } catch (Exception ex) {
        }
        return mapping.findForward("Print");
    }
}
