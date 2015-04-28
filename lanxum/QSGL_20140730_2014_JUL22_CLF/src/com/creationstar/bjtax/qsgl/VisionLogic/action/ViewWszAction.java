package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryWszForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.QueryCache;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewWszAction extends BaseAction {
    /**
     * 不征契税——查看
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
        QueryWszForm aForm = (QueryWszForm) form;
        int index = aForm.getViewIndex();
        QueryCache cache = aForm.getQueryCache();
        QueryWszBo bo = (QueryWszBo) cache.getDataDetail(index);

        aForm.setMxbo(bo);
        //明细信息组织
        ArrayList mxlist = bo.getResultList();
        int size = mxlist.size();
        ArrayList smlist = new ArrayList();
        ArrayList mjlist = new ArrayList();
        ArrayList jsjelist = new ArrayList();
        ArrayList sllist = new ArrayList();
        ArrayList snjelist = new ArrayList();
        ArrayList skssrqlist = new ArrayList();
        for (int i = 0; i < size; i++) {
            Qswszmx mx = new Qswszmx();
            mx = (Qswszmx) mxlist.get(i);
            smlist.add(mx.getSzsmmc());
            mjlist.add(mx.getQszymj());
            jsjelist.add(mx.getJsje());
            sllist.add(mx.getSl());
            snjelist.add(mx.getSjse());
            skssrqlist.add(DataConvert.TimeStamp2String(mx.getSkssksrq())+"-"+DataConvert.TimeStamp2String(mx.getSkssjsrq()));
        }
        //
        aForm.setJsjelist(jsjelist);
        aForm.setMjlist(mjlist);
        aForm.setSllist(sllist);
        aForm.setSmlist(smlist);
        aForm.setSnjelist(snjelist);
        aForm.setSkssrqlist(skssrqlist);
        aForm.setZsjg(this.getUserData().ssdwmc);
        aForm.setSbbh(bo.getSbbh());

        // 保存Token
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 取消当前操作
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向返回后的页面。
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
        QueryWszForm aForm = (QueryWszForm) form;
        String subAct = aForm.getYuan();
        if (subAct != null && subAct.equals("cxwsz")) {
            return mapping.findForward("returncx");
        } else {
            return mapping.findForward("return");
        }
    }

}
