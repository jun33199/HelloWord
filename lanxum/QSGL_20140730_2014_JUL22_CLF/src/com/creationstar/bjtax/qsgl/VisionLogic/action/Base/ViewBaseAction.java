package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ���ݲ鿴����Action.
 * ����һ������ݲ鿴����
 * ����ʾ�޸�ҳ�棬ȡ�����أ�
 * ����ģ�淽��ģʽ���ṩһ��Ĳ鿴�����еĿ��ơ�
 * ����Ĳ����ɶ������࣬������ʵ�־��巽����doXXX����
 * �������������������������ش˷����ṩ�����ѡ��
 */
public class ViewBaseAction extends BaseAction {

    /**
     * ȡ����ǰ��������Form�����Session��ȥ����
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ��ViewForm��Ϊ�ա�
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *      queryForm.setViewForm(null);
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
        //��ViewForm��Ϊ�ա�
        QueryBaseForm queryForm = (QueryBaseForm) form;
        queryForm.setViewForm(null);
        // ����Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * �鿴ѡ�м�¼����ϸ��Ϣ
     *
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ����viewForm��
     *      queryForm.createViewForm();
     *  3. ����Token;
     *       saveToken();
     *  4.  ת��Viewҳ�档
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
        //�������е�Form����ΪQueryBaseForm��
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // ����viewForm��
        queryForm.createViewForm();
        // ����Token;
        saveToken(request);
        //  ת��Viewҳ�档
        return mapping.findForward("show");
    }

    public ActionForward doModify(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //����Token
        saveToken(request);
        //  ת��modifyҳ�档
        return mapping.findForward("modify");
    }

    public ActionForward doModifyPasswd(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        //����Token;
        saveToken(request);
        //ת��modifypasswdҳ��
        return mapping.findForward("modifypasswd");
    }
}
