package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.ShskBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShskAction extends BaseAction {
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
        ShskForm shskForm = (ShskForm) form;
        VOPackage vo = new VOPackage();

        QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                "querySbxxForm");
        JsblBo bo = (JsblBo) aForm.getViewDataDetail();

        //构造审核收款bo
        ShskBo shskbo = new ShskBo();
        shskbo.setSbbh(bo.getSbbh());
        shskbo.setNsrmc(bo.getNsrmc());
        shskbo.setCjjgrmb(bo.getCjjgrmb());
        shskbo.setTdfwzldz(bo.getTdfwzldz());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.GET);
        vo.setUserData(this.getUserData());
        vo.setData(shskbo);
        vo.setProcessor(prop.getProperty(shskbo.getClass().getName()));
        try {
            shskbo = (ShskBo) QsglProxy.getInstance().process(vo);
            if (bo.getDrpch() != null && !bo.getDrpch().equals("")) {
                shskbo.setPc(true);
            } else {
                shskbo.setPc(false);
            }
            shskForm.setData(shskbo);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
        }
        request.setAttribute(Constants.MESSAGE_KEY, "显示成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 即时办理--根据申报表号取得相关信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCheck(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ShskForm shskForm = (ShskForm) form;
        VOPackage vo = new VOPackage();

        //构造审核收款bo
        ShskBo shskbo = (ShskBo) shskForm.getData();

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //构造向后台传输的Vopackage对象
        vo.setAction(ActionType.CHECK);
        vo.setUserData(this.getUserData());
        vo.setData(shskbo);
        vo.setProcessor(prop.getProperty(shskbo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            shskbo.setChecked(true);
            shskForm.setChecked(true);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
        }
        request.setAttribute(Constants.MESSAGE_KEY, "办理信息取得成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("check");
    }

    /**
     * 转向缴款书打印页面
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintJks(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        return mapping.findForward("printJks");
    }

    /**
     * 转向完税证打印页面
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintWsz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        return mapping.findForward("printWsz");
    }
}
