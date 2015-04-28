package com.creationstar.bjtax.qsgl.VisionLogic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.QueryCache;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BlViewJksAction extends BaseAction {
    /**
     * ��ʾ�ɿ����ѯ�������ϸ
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
        try {
            BlJksForm blJksForm = (BlJksForm) form;
            String index = request.getParameter("viewIndex");
            QueryCache cache = blJksForm.getQueryCache();
            QueryBlJksBo bo = (QueryBlJksBo) cache.getDataDetail(Integer.
                    parseInt(index));
            blJksForm.setRemoveBo(bo);
            JksBo jksBo = new JksBo();
            jksBo.setSbjkzb(bo.getSbjkzb());
            jksBo.setResultList(bo.getMxList());
            blJksForm.setJksBo(jksBo);
            blJksForm.setJkpzh(bo.getSbjkzb().getJkpzh());
            blJksForm.setType(bo.getType());
            blJksForm.setZbxh(bo.getZbxh());
            blJksForm.setSklxdm(bo.getSklxdm());
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�ɿ�����Ϣ��ʾ�ɹ���");
        return mapping.findForward("show");

    }


    /**
     * ȡ����ǰ������
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ����Token;
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
        // ����Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * ȡ����ǰ������
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ����Token;
     *     saveToken();
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doBlFhz(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        // ����Token;
        saveToken(request);
        return mapping.findForward("blfhz");
    }

}
