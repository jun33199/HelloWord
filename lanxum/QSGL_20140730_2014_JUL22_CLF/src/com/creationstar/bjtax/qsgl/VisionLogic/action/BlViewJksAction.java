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
     * 显示缴款书查询结果的明细
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
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "缴款书信息显示成功！");
        return mapping.findForward("show");

    }


    /**
     * 取消当前操作，
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
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
        // 保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * 取消当前操作，
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
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
        // 保存Token;
        saveToken(request);
        return mapping.findForward("blfhz");
    }

}
