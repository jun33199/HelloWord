package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryJmsbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.SbState;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewJmsbxxAction extends BaseAction {
    /**
     * 减免申报--根据申报表号取得相关信息
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        JmsbxxForm jmsbxxForm = (JmsbxxForm) form;
        // jmsbxxForm.setJkfsList(ActionUtil.getCodeTables(session.getServletContext(),
        // Constants.JSFS));
        jmsbxxForm.setSfzjlxList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.ZJLX));
        jmsbxxForm.setNsrlxList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.NSRLX));
        jmsbxxForm.setGjdqList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.GJ));

        VOPackage vo = new VOPackage();

        QueryJmsbxxForm aForm = (QueryJmsbxxForm) session
                                .getAttribute("queryJmsbxxForm");
        JmsbblBo bo = (JmsbblBo) aForm.getViewDataDetail();
        JmsbxxBo jmsbxxBo = new JmsbxxBo();
        jmsbxxBo.setSbbh(bo.getSbbh());

        // 获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.GET);
        vo.setUserData(this.getUserData());
        vo.setData(jmsbxxBo);
        vo.setProcessor(prop.getProperty(jmsbxxBo.getClass().getName()));
        try {
            JmsbxxBo detailBo = (JmsbxxBo) QsglProxy.getInstance().process(vo);
            jmsbxxForm.setData(detailBo);

            ArrayList jmsbbList = jmsbxxForm.getJmsbbList();

            if (jmsbbList.size() > 0) {
                StringBuffer qsjmlbmc = new StringBuffer();
                String[] qsjmlbSelect = new String[jmsbbList.size()];

                for (int i = 0; i < jmsbbList.size(); i++) {

                    Jmsbb jmsbb = (Jmsbb) jmsbbList.get(i);

                    System.out.println(
                            "===============================================");
                    System.out.println("所选择的减免政策：" + jmsbb.getJmzcdm());

                    if (i > 0) {
                        qsjmlbmc.append("；<br>");
                    }

                    HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
                            .getServletContext(), Constants.JMZCMAP);
                    Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(jmsbb.getJmzcdm());
                    qsjmlbmc.append(qsjmlbVo.getQsjmlbmc());
                    // 其它类减免的理由写入备注
                    if (Constants.CXXJM_JMXMDM_QT.equals(jmsbb.getJmzcdm())) {
                        jmsbxxForm.setQtjmlybeizhu(jmsbb.bz);
                    }
                    qsjmlbSelect[i] = jmsbb.getJmzcdm();
                    jmsbxxForm.setJmxzdm(jmsbb.getJmxzdm());
                }

                System.out.println("其它减免理由备注：" + jmsbxxForm.getQtjmlybeizhu());

                jmsbxxForm.setQsjmlbmc(qsjmlbmc.toString());
                jmsbxxForm.setQsjmlbSelect(qsjmlbSelect);
            }

            String path = mapping.getPath();
            Debug.out("mapping.getPath() is " + path);

            // 如果是审批进来的需要设置审批机关、审批状态list
            // 写死
            if (path.equals("/jmsb/jmsbSp")) {

                jmsbxxForm.setSpjgList(detailBo.getSpjgList());

                jmsbxxForm.setSpztList(detailBo.getSpztList());
            }

            request.setAttribute(Constants.MESSAGE_KEY, "申报信息细节显示成功！");

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            // return new ActionForward(mapping.getInput());
        }

        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 作废
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCancel(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // 获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.CANCEL);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // 更新当前页面数据
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            aForm.setData(bo);
            // 因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());
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
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // 获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.DELETE);
        vo.setUserData(this.getUserData());
        ArrayList delList = new ArrayList();
        delList.add(bo.getSbbh());
        vo.setData(delList);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // 更新当前页面数据
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            aForm.setData(bo);
            // 因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            queryForm.removeData(queryForm.getViewIndex());
            request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
            // 调用父类方法将Form对象从Session中去掉。
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
        return mapping.findForward("delete");
        // return mapping.findForward("delete");

    }

//	/**
//	 * 减免申报--审核同意
//	 *
//	 * @param mapping
//	 *            ActionMapping
//	 * @param form
//	 *            ActionForm
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @return ActionForward
//	 */
//	public ActionForward doConfirm(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		// 调用父类方法处理Token.
//		ActionForward forward = doHandleToken(mapping, request);
//		HttpSession session = request.getSession(false);
//		VOPackage vo = new VOPackage();
//
//		JmsbxxForm aForm = (JmsbxxForm) form;
//		JmsbxxBo bo = (JmsbxxBo) aForm.getData();
//
//		// 获取存放在ServletContext中的processor-map.properties的数据
//		Properties prop = (Properties) request.getSession(false)
//				.getServletContext().getAttribute(
//						Constants.PROCESSOR_MAP_FILE_NAME);
//
//		// 构造向后台传输的Vopackage对象
//		vo.setAction(ActionType.CONFIRM);
//		vo.setUserData(this.getUserData());
//		vo.setData(bo);
//		vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//		try {
//			QsglProxy.getInstance().process(vo);
//			bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_JS_TY);
//			QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
//					.getAttribute("queryJmsbxxForm");
//			// queryForm.removeData(queryForm.getViewIndex());
//
//		} catch (Exception ex) {
//			// 保存Token;
//			saveToken(request);
//			ex.printStackTrace();
//			request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
//			return new ActionForward(mapping.getInput());
//
//		}
//		// 保存Token;
//		saveToken(request);
//		request.setAttribute(Constants.MESSAGE_KEY, "审批同意成功！");
//		return mapping.findForward("confirm");
//
//	}

//	/**
//	 * 减免申报--审核不同意
//	 *
//	 * @param mapping
//	 *            ActionMapping
//	 * @param form
//	 *            ActionForm
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @return ActionForward
//	 */
//	public ActionForward doReject(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		// 调用父类方法处理Token.
//		ActionForward forward = doHandleToken(mapping, request);
//		HttpSession session = request.getSession(false);
//		VOPackage vo = new VOPackage();
//
//		JmsbxxForm aForm = (JmsbxxForm) form;
//		JmsbxxBo bo = (JmsbxxBo) aForm.getData();
//
//		// 获取存放在ServletContext中的processor-map.properties的数据
//		Properties prop = (Properties) request.getSession(false)
//				.getServletContext().getAttribute(
//						Constants.PROCESSOR_MAP_FILE_NAME);
//
//		// 构造向后台传输的Vopackage对象
//		vo.setAction(ActionType.REJECT);
//		vo.setUserData(this.getUserData());
//		vo.setData(bo);
//		vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//		try {
//			QsglProxy.getInstance().process(vo);
//			QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
//					.getAttribute("queryJmsbxxForm");
//			// queryForm.removeData(queryForm.getViewIndex());
//
//		} catch (Exception ex) {
//			// 保存Token;
//			saveToken(request);
//			ex.printStackTrace();
//			request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
//			return new ActionForward(mapping.getInput());
//
//		}
//		// 保存Token;
//		saveToken(request);
//		request.setAttribute(Constants.MESSAGE_KEY, "审批不同意成功！");
//		return mapping.findForward("reject");
//
//	}

    /**
     * 取消当前操作，将Form对象从Session中去掉。 转向返回后应去的页面。
     *
     * 1. 调用父类方法将Form对象从Session中去掉。 removeForm(mapping,request); 2. 保存Token;
     * saveToken(); return mapping.findForward("return");
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
     */
    public ActionForward doReturn(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        JmsbxxBo bo = (JmsbxxBo) ((JmsbxxForm) form).getData();
        // 调用父类方法将Form对象从Session中去掉。
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);

        return mapping.findForward("return");

    }

    /**
     * 撤销上次操作,恢复上次状态
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doRollBack(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // 获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.ROLLBACK);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // 更新当前页面数据
            bo.getVoSbzb().setZtbs(
                    SbState.getCancelStateCode(bo.getVoSbzb().getZtbs(), bo
                                               .getVoSbzb().getBljmsbs()));
            // 因为状态改变，从查询结果中删除
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());
            // request.setAttribute(Constants.MESSAGE_KEY,"恢复1成功！");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }

        // // 保存Token;
        // saveToken(request);
        // request.setAttribute(Constants.MESSAGE_KEY, "撤消成功！");
        // return mapping.findForward("rollback");

        // 重新显示
        return doShow(mapping, form, request, response);

    }

    /**
     * 修改申报--修改减免申报等信息
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doRedirect(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String subaction = request.getParameter("subaction");

        saveToken(request);

        JmsbxxForm sbxxForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) sbxxForm.getData();
        // 区分是个人进来的还是非个人
        if (!bo.isPerson()) { // 非个人
            return mapping.findForward("modjmsbxxfgr");
        } else { // 个人
            return mapping.findForward("modjmsbxxgr");
        }

        // return mapping.findForward(subaction);

    }


    /**
     * 减免申报--审核同意
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doSavesp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        HttpSession session = request.getSession(false);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();

        // 获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.SAVE_CXXJMSP);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            bo.getVoSbzb().setZtbs(bo.getSpzt());
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());

        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "审批成功！");
        return mapping.findForward("savesp");

    }

}
