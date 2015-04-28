package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.SbState;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.ParameterUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewSbxxAction extends BaseAction {
    /**
     * 即时办理--根据申报表号取得相关信息
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
        SbxxForm sbxxForm = (SbxxForm) form;
        sbxxForm.setJkfsList(ActionUtil.getCodeTables(session.getServletContext(),
                Constants.JSFS));
        sbxxForm.setSfzjlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.ZJLX));
        sbxxForm.setNsrlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.NSRLX));
        sbxxForm.setGjdqList(ActionUtil.getCodeTables(session.getServletContext(),
                Constants.GJ));
        VOPackage vo = new VOPackage();

        QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                "querySbxxForm");
        JsblBo bo = (JsblBo) aForm.getViewDataDetail();
        SbxxBo sbxxBo = new SbxxBo();
        sbxxBo.setSbbh(bo.getSbbh());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.GET);
        vo.setData(sbxxBo);
        vo.setProcessor(prop.getProperty(sbxxBo.getClass().getName()));
        try {
            SbxxBo detailBo = (SbxxBo) QsglProxy.getInstance().process(vo);
            sbxxForm.setData(detailBo);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
        }
        request.setAttribute(Constants.MESSAGE_KEY, "申报信息细节显示成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 查询--保存修改信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        //调用父类方法处理Token.
        try {
            ActionForward forward = doHandleToken(mapping, request);
            VOPackage vo = new VOPackage();

            SbxxForm aForm = (SbxxForm) form;
            // 从Form中获取对象。
            String[][] dataSource_grxx = ParameterUtil.
                                         getTableDataSource(request,
                    "dataSource_gm", 9);

            List l = ActionUtil.getGrData(dataSource_grxx,
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.ZJLXMAP),
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.GJMAP));
            SbxxBo bo = (SbxxBo) aForm.getData(l);
            if (!bo.isPerson()) {
                Fgrxx fgrxx = bo.getVoFgrxx();
                if (fgrxx.getKhyhmc() != null && !fgrxx.getKhyhmc().equals("")) {
                    String sz[] = DataConvert.splitYh(fgrxx.getKhyhmc());
                    fgrxx.khyhmc = sz[0];
                    fgrxx.yhzh = sz[1];
                } else {
                    fgrxx.khyhmc = "";
                    fgrxx.yhzh = "";
                }
            }
            if (bo.getVoSbzb().getBlbs().equals(Constants.ZB_BLBS_BL)) {
                bo.getVoSbzb().setSbrq(DataConvert.String2Timestamp(aForm.
                        getSbrq()));
            }

            Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.MODIFY);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            bo = (SbxxBo) QsglProxy.getInstance().process(vo);
            HttpSession session = request.getSession(false);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            //将新更新的项目更新结果集中对应的对象
            JsblBo jsblbo = (JsblBo) queryForm.getViewDataDetail();

            if (bo.isPerson()) {
                jsblbo.setNsrlxdm(Constants.NSRLX_GR);
                jsblbo.setNsrmc(bo.getVoZcqrxx().getNsrmc());
                jsblbo.setJsjdm(bo.getVoZcqrxx().getJsjdm());
            } else {
                jsblbo.setNsrlxdm(bo.getVoFgrxx().getNsrlxdm());
                jsblbo.setNsrmc(bo.getVoFgrxx().getNsrmc());
                jsblbo.setJsjdm(bo.getVoFgrxx().getJsjdm());

            }
            jsblbo.setBz(bo.getVoSbzb().getBz());

            queryForm.modifyData(jsblbo);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("modify");
        }
        request.setAttribute(Constants.MESSAGE_KEY, "保存成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("save");

    }

    /**
     * 撤销上次操作,恢复上次状态
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doRollBack(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        SbxxForm aForm = (SbxxForm) form;
        SbxxBo bo = (SbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.ROLLBACK);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            //更新当前页面数据
            bo.getVoSbzb().setZtbs(SbState.getCancelStateCode(
                    bo.getVoSbzb().getZtbs(), bo.getVoSbzb().getBljmsbs()));
            //因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            //queryForm.removeData(queryForm.getViewIndex());
            // request.setAttribute(Constants.MESSAGE_KEY,"恢复1成功！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "撤消成功！");
        return mapping.findForward("rollback");

    }

    /**
     * 作废
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCancel(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        SbxxForm aForm = (SbxxForm) form;
        SbxxBo bo = (SbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.CANCEL);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            //更新当前页面数据
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            //因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            //queryForm.removeData(queryForm.getViewIndex());
            request.setAttribute(Constants.MESSAGE_KEY, "作废显示成功！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("cancel");

    }

    /**
     * 删除
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        SbxxForm aForm = (SbxxForm) form;
        SbxxBo bo = (SbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.DELETE);
        vo.setUserData(this.getUserData());
        ArrayList delList = new ArrayList();
        delList.add(bo.getSbbh());
        vo.setData(delList);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            //更新当前页面数据
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            //因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            queryForm.removeData(queryForm.getViewIndex());
            request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
            //调用父类方法将Form对象从Session中去掉。
            removeForm(mapping, request);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // 保存Token;
        saveToken(request);
        //可能从不征查询页面过来
        if (bo.isBZ()) {
            return mapping.findForward("deletebz");
        } else {
            return mapping.findForward("delete");
        }
        // return mapping.findForward("delete");

    }


    /**
     * 修改申报--修改拆迁等信息
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
        String subaction = request.getParameter("subaction");
        if (subaction.equals("modify")) {
            SbxxForm sbxxForm = (SbxxForm) form;
            SbxxBo bo = (SbxxBo) sbxxForm.getData();

            if (!bo.isPerson()) {
                try {
                    HashMap djinfo = CommonUtil.getFgrDjInfo(bo.getVoFgrxx().
                            getJsjdm());
                    ArrayList yhList = (ArrayList) djinfo.get("YHZH");
                    if ((yhList != null) && (yhList.size() > 0)) {
                        for (int i = 0; i < yhList.size(); i++) {
                            YHZH yhzh = (YHZH) yhList.get(i);
                            String khyhmc = yhzh.getKhyhmc();
                            khyhmc = khyhmc + "--" + yhzh.getZh();
                            yhzh.setKhyhmc(khyhmc);
                        }
                    }
                    sbxxForm.setKhyhList(yhList);

                    Debug.out("yhList.size: " + yhList.size());

                } catch (Exception ex) {
                    // 保存Token;
                    saveToken(request);
                    ex.printStackTrace();
                    request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
                    return new ActionForward(mapping.getInput());
                }

            }
            request.setAttribute(Constants.MESSAGE_KEY, "修改显示成功！");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward(subaction);

    }

    /**
     * 即时办理--审核同意
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doConfirm(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        HttpSession session = request.getSession(false);
        VOPackage vo = new VOPackage();

        SbxxForm aForm = (SbxxForm) form;
        SbxxBo bo = (SbxxBo) aForm.getData();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.CONFIRM);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_JS_TY);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            queryForm.removeData(queryForm.getViewIndex());

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());

        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "审核成功！");
        return mapping.findForward("confirm");

    }

    /**
     * 即时办理--审核不同意
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doReject(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        HttpSession session = request.getSession(false);
        VOPackage vo = new VOPackage();

        SbxxForm aForm = (SbxxForm) form;
        SbxxBo bo = (SbxxBo) aForm.getData();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.REJECT);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            QuerySbxxForm queryForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            queryForm.removeData(queryForm.getViewIndex());

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());

        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "审核成功！");
        return mapping.findForward("reject");

    }

    /**
     * 取消当前操作，将Form对象从Session中去掉。
     * 转向返回后应去的页面。
     *
     *  1. 调用父类方法将Form对象从Session中去掉。
     *     removeForm(mapping,request);
     *  2. 保存Token;
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
        SbxxBo bo = (SbxxBo) ((SbxxForm) form).getData();
        //调用父类方法将Form对象从Session中去掉。
        removeForm(mapping, request);
        //保存Token;
        saveToken(request);

        Debug.out("doreturn bo.isBZ(): " + bo.isBZ());
        //可能从不征查询页面过来
        if (bo.isBZ()) {
            return mapping.findForward("returnbz");
        } else {
            return mapping.findForward("return");
        }
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
        SbxxForm sbxxForm = (SbxxForm) form;

        HashMap map = CommonUtil.getZcspjg(sbxxForm.getVoSpjgxx().getHdtzszh());
        if (map == null) {
            request.setAttribute(Constants.MESSAGE_KEY, "没有指定审批结果表编号的减免信息！");
        } else {
            sbxxForm.setJmsje((String) map.get("jmse"));
            sbxxForm.getVoSpjgxx().setJmlydm((String) map.get("qtjmly"));
            request.setAttribute(Constants.MESSAGE_KEY, "指定审批结果表编号的减免信息获取成功！");
        }
        // 保存Token;
        saveToken(request);
        return mapping.findForward("modify");

    }


}
