package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 数据查看基本Action.
 * 控制一般的数据查看事务
 * （显示修改页面，取消返回）
 * 采用模版方法模式。提供一般的查看事务中的控制。
 * 特殊的操作可定义子类，由子类实现具体方法（doXXX）。
 * 更特殊的增加事务可由子类重载此方法提供更多的选择。
 */
public class ViewBaseAction extends BaseAction {

    /**
     * 取消当前操作，将Form对象从Session中去掉。
     * 转向取消后应去的页面。
     *
     *  1. 将ViewForm置为空。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *      queryForm.setViewForm(null);
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
        //将ViewForm置为空。
        QueryBaseForm queryForm = (QueryBaseForm) form;
        queryForm.setViewForm(null);
        // 保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * 查看选中记录的详细信息
     *
     *  1. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. 生成viewForm。
     *      queryForm.createViewForm();
     *  3. 保存Token;
     *       saveToken();
     *  4.  转向View页面。
     *     return mapping.findForward("show");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        //将参数中的Form构型为QueryBaseForm。
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // 生成viewForm。
        queryForm.createViewForm();
        // 保存Token;
        saveToken(request);
        //  转向View页面。
        return mapping.findForward("show");
    }

    public ActionForward doModify(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //保存Token
        saveToken(request);
        //  转向modify页面。
        return mapping.findForward("modify");
    }

    public ActionForward doModifyPasswd(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        //保存Token;
        saveToken(request);
        //转向modifypasswd页面
        return mapping.findForward("modifypasswd");
    }
}
