package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 直接显示一条记录的详细页面Action类
 */
public class ViewOneAction extends BaseAction {

    /**
     * 查看某条记录的详细信息
     *
     *  1. 获取数据，由子类实现，从session中获取，或者从后台获取。
     *      Object data = getData();
     *      form.setData();
     *  2. 保存Token;
     *       saveToken();
     *  3.  转向View页面。
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
        //获取数据，由子类实现，从session中获取，或者从后台获取。
        Object data = null;
        try {
            data = getData(mapping, form, request, response);

            BaseForm baseForm = (BaseForm) form;
            baseForm.setData(data);
            // 保存Token;
            saveToken(request);
            //  转向View页面。
            return mapping.findForward("show");
        } catch (BaseException ex) {
            request.setAttribute(RequestKey.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return new ActionForward(mapping.getInput());
        }
    }

    /**
     * 取消当前操作，将Form对象从Session中去掉。
     * 转向取消后应去的页面。
     *
     *  1. 将Form对象从Session中去掉。
     *      removeForm();
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
        //将Form对象从Session中去掉。
        removeForm(mapping, request);
        //保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * 转向modify页面
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doModify(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //保存Token;
        saveToken(request);
        return mapping.findForward("modify");
    }

    /**
     * 获取一个对象数据, 子类可覆盖。缺省实现是从session中获取对象。
     * return session.getAttribute(SessionKey.VIEW_DATA);
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return Object
     * @throws TtsoftException
     */
    protected Object getData(ActionMapping mapping,
                             ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws BaseException {
        HttpSession session = request.getSession();
        return session.getAttribute(SessionKey.VIEW_DATA);
    }
}
