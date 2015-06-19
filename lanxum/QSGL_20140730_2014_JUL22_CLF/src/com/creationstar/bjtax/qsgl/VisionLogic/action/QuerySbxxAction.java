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
     * ��ʱ����--��ѯ�걨�����Ϣ
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
            //��ȡ�����ServletContext�еĴ���������
            HashMap codeMaps = (HashMap) session.getServletContext()
                               .getAttribute(Constants.CodeTables);
            ArrayList zjlxList = (ArrayList) codeMaps.get(Constants.ZJLX);
            ArrayList nsrlxList = (ArrayList) codeMaps.get(Constants.NSRLX);

            Debug.out("zjlxlist size: " + zjlxList.size());
            aForm.setSfzjlxList(zjlxList);
            aForm.setNsrlxList(nsrlxList);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
        request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣ��ʾ�ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ��ʱ����--���ͬ��,��ʾ��ϸ��Ϣ
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
        // ����Token;
        saveToken(request);
        QuerySbxxForm aForm = (QuerySbxxForm) form;
        request.setAttribute(Constants.MESSAGE_KEY, "�걨ϸ����ʾ�ɹ���");
        return mapping.findForward("showdetail");
    }

    /**
     * ��ѯ--��ʾ��ϸ��Ϣ,�޸�ҳ��
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
        // ����Token;
        saveToken(request);
        QuerySbxxForm aForm = (QuerySbxxForm) form;
        request.setAttribute(Constants.MESSAGE_KEY, "�޸�ҳ����ʾ�ɹ���");
        return mapping.findForward("view");
    }


}
