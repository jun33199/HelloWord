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
 * ֱ����ʾһ����¼����ϸҳ��Action��
 */
public class ViewOneAction extends BaseAction {

    /**
     * �鿴ĳ����¼����ϸ��Ϣ
     *
     *  1. ��ȡ���ݣ�������ʵ�֣���session�л�ȡ�����ߴӺ�̨��ȡ��
     *      Object data = getData();
     *      form.setData();
     *  2. ����Token;
     *       saveToken();
     *  3.  ת��Viewҳ�档
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
        //��ȡ���ݣ�������ʵ�֣���session�л�ȡ�����ߴӺ�̨��ȡ��
        Object data = null;
        try {
            data = getData(mapping, form, request, response);

            BaseForm baseForm = (BaseForm) form;
            baseForm.setData(data);
            // ����Token;
            saveToken(request);
            //  ת��Viewҳ�档
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
     * ȡ����ǰ��������Form�����Session��ȥ����
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ��Form�����Session��ȥ����
     *      removeForm();
     *  2. ����Token;
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
        //��Form�����Session��ȥ����
        removeForm(mapping, request);
        //����Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * ת��modifyҳ��
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
        //����Token;
        saveToken(request);
        return mapping.findForward("modify");
    }

    /**
     * ��ȡһ����������, ����ɸ��ǡ�ȱʡʵ���Ǵ�session�л�ȡ����
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
