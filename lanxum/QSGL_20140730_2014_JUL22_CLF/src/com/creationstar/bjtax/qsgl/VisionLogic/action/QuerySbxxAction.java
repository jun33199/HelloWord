package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuerySbxxAction extends QueryBaseAction {
    /**
     * 即时办理--查询申报相关信息
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
        QuerySbxxForm aForm = new QuerySbxxForm();
        session.setAttribute(mapping.getName(), aForm);
        VOPackage vo = new VOPackage();

        try {
            //获取存放在ServletContext中的代码表的数据
            HashMap codeMaps = (HashMap) session.getServletContext()
                               .getAttribute(Constants.CodeTables);
            ArrayList zjlxList = (ArrayList) codeMaps.get(Constants.ZJLX);
            ArrayList nsrlxList = (ArrayList) codeMaps.get(Constants.NSRLX);

            Debug.out("zjlxlist size: " + zjlxList.size());
            aForm.setSfzjlxList(zjlxList);
            aForm.setNsrlxList(nsrlxList);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
        request.setAttribute(Constants.MESSAGE_KEY, "申报信息显示成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 即时办理--审核同意,显示详细信息
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShowDetail(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        QuerySbxxForm aForm = (QuerySbxxForm) form;
        request.setAttribute(Constants.MESSAGE_KEY, "申报细节显示成功！");
        return mapping.findForward("showdetail");
    }

    /**
     * 查询--显示详细信息,修改页面
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doView(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // 保存Token;
        saveToken(request);
        QuerySbxxForm aForm = (QuerySbxxForm) form;
        request.setAttribute(Constants.MESSAGE_KEY, "修改页面显示成功！");
        return mapping.findForward("view");
    }


}
