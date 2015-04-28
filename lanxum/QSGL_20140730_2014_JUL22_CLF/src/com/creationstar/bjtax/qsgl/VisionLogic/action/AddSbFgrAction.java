package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbFgrBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 蒋勇
 * @version 1.0
 */
public class AddSbFgrAction extends AddBaseAction {

    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbFgrForm sbFgrForm = (SbFgrForm) form;
        sbFgrForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        Debug.out("SbFgrForm.getJkfsList().size = " +
                  sbFgrForm.getJkfsList().size());

        sbFgrForm.setNsrlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.NSRLX));
        //sbFgrForm.setYhList(ActionUtil.getCodeTables(session.
        // getServletContext(), Constants.YH));
        //判断来自补录还是正常申报
        String bl = (String) request.getParameter("bl");
        if (bl != null && bl.equals("1")) {
            sbFgrForm.setBl(true);
        } else {
            sbFgrForm.setBl(false);
        }
        Debug.out("SbFgrForm.getNsrlxList().size = " +
                  sbFgrForm.getNsrlxList().size());

        request.setAttribute(Constants.MESSAGE_KEY, "非个人申报显示成功！");
        // 保存Token;
        saveToken(request);

        return mapping.findForward("show");

    }

    /**
     * 根据输入的核定通知书字号获取减免税金额
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetJmsje(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbFgrForm sbFgrForm = (SbFgrForm) form;
        // 保存Token;
        saveToken(request);

        Debug.out("add sbfgr action getJmsje....");

        try {
            HashMap map = CommonUtil.getZcspjg(sbFgrForm.getHdtzszh());
            if (map == null) {
                request.setAttribute(Constants.MESSAGE_KEY, "没有指定审批结果表编号的减免信息！");
            } else {
                sbFgrForm.setJmsje((String) map.get("jmse"));
                sbFgrForm.setJmlydm((String) map.get("qtjmly"));
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "指定审批结果表编号的减免信息获取成功！");
            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "获取审批结果减免信息失败!");
            return new ActionForward(mapping.getInput());
        }
        return mapping.findForward("show");

    }

    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetNsrxx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbFgrForm sbFgrForm = (SbFgrForm) form;

        try {
            //清除form,
            sbFgrForm.setNsrmc("");
            sbFgrForm.setYhList(new ArrayList());
            sbFgrForm.setYhzh("");

            HashMap djinfo = CommonUtil.getFgrDjInfo(sbFgrForm.getJsjdm());
            SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
            sbFgrForm.setNsrmc(jbsj.getNsrmc());
            ArrayList yhList = (ArrayList) djinfo.get("YHZH");

            if ((yhList != null) && (yhList.size() > 0)) {
                for (int i = 0; i < yhList.size(); i++) {
                    YHZH yhzh = (YHZH) yhList.get(i);
                    String khyhmc = yhzh.getKhyhmc();
                    khyhmc = khyhmc + "--" + yhzh.getZh();
                    yhzh.setKhyhmc(khyhmc);
                }
            }
            sbFgrForm.setYhList(yhList);
            request.setAttribute(Constants.MESSAGE_KEY, "成功获得纳税人的登记信息！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");

    }

    /**
     * 保存记录
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((BaseForm)form).getData();
     * 3. 调用BaseProxy的add方法，向数据库中增加一条记录。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. 保存Token;
     *       saveToken();
     * 5. 转向成功后的界面。
     *     return mapping.findForward("add");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */

    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            Debug.out("come in ");
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            Debug.out("come in 1");
            // 从Form中获取对象。
            Object obj = ((SbFgrForm) form).getData();
            Debug.out("AddSbFgrAction doSave form.getData() obj.class is " +
                      obj.getClass().getName());
            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            Debug.out("form bo nsrmc: " + ((SbFgrBo) obj).nsrmc);

            String sbbh = (String) QsglProxy.getInstance().process(vo);
            Debug.out("after add sbbh: " + sbbh);
            ((SbFgrForm) form).setSbbh(sbbh);
            // 保存Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "非个人申报保存成功！");
            // 转向成功后的界面。
            return mapping.findForward("save");
        } catch (BaseException te) {
            saveToken(request);
            te.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            Debug.out(ex);
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }

    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doRedirect(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        String subaction = request.getParameter("subAction");

        return mapping.findForward(subaction);
    }

    /**
     * 取消当前操作。
     * 如果没有录土地房屋基本信息,删除这条申报数据
     *
     * 转向取消后应去的页面。
     *
     *
     *  1. 保存Token;
     *     saveToken();
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // 从Form中获取对象。
        SbFgrForm sbForm = (SbFgrForm) form;
        String sbbh = sbForm.getSbbh();
        if (!sbForm.isFwjbxxAdded()) {
            VOPackage vo = new VOPackage();
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.DELETE);
            vo.setUserData(this.getUserData());
            ArrayList delList = new ArrayList();
            delList.add(sbbh);
            vo.setData(delList);
            SbxxBo bo = new SbxxBo();
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            try {
                QsglProxy.getInstance().process(vo);
            } catch (Exception ex) {
                Debug.printException(ex);
                request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
                return new ActionForward(mapping.getInput());
            }
        }
        // 删除session中的form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

}
