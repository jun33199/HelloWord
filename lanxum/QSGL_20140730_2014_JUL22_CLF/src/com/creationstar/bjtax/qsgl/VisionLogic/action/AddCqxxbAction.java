package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.AddCqxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddCqxxbAction extends AddBaseAction {
    /**
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AddCqxxForm aForm = (AddCqxxForm) form;
        aForm.clear();
        String path = mapping.getPath();
        Debug.out("mapping.getPath() is " + path);

        try {
            // 从context获取代码表信息,获取代码map,
            // 从中得到包括纳税人类型、身份证件类型、币种、房屋类别、分类和土地、房屋权属转移类型的arraylist
            aForm.setBcqrlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.NSRLX));

            ArrayList listZjlx = (ArrayList) ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.ZJLX).clone();

            Zjlx zjlxvo = new Zjlx();

            zjlxvo.setZjlxdm("");
            zjlxvo.setZjlxmc("");

            listZjlx.add(0, zjlxvo);

            aForm.setZjlx(listZjlx);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            String err = ex.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "添加拆迁信息显示成功！");
        return mapping.findForward("show");
    }

    /**
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doModify(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AddCqxxForm aForm = (AddCqxxForm) form;
        String cqxxbh = aForm.getCqxxbh();
        Cqxxb bo = new Cqxxb();

        try {
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            // 构造向后台传输的Vopackage对象
            vo.setAction(ActionType.GET);
            vo.setUserData(userData);
            vo.setData(cqxxbh);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            bo = (Cqxxb) QsglProxy.getInstance().process(vo);
            // 从context获取代码表信息,获取代码map,
            // 从中得到包括纳税人类型、身份证件类型、币种、房屋类别、分类和土地、房屋权属转移类型的arraylist
            aForm.setData(bo);
            aForm.setBcqrlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.NSRLX));

            ArrayList listZjlx = (ArrayList) ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.ZJLX).clone();

            Zjlx zjlxvo = new Zjlx();

            zjlxvo.setZjlxdm("");
            zjlxvo.setZjlxmc("");

            listZjlx.add(0, zjlxvo);

            aForm.setZjlx(listZjlx);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            String err = ex.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "修改拆迁信息显示成功！");
        return mapping.findForward("modify");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doSaveMod(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            // 调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            AddCqxxForm aForm = (AddCqxxForm) form;
            // 从Form中获取对象。
            Cqxxb bo = (Cqxxb) aForm.getData();
            bo.setCqxxbh(aForm.getCqxxbh());

            UserData ud = this.getUserData();

            bo.setCjr(ud.yhmc);
            bo.setLrr(ud.yhid);

            // 调用QsglProxy的add方法，向数据库中增加一条记录。


            // 构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.MODIFY);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            QsglProxy.getInstance().process(vo);

            CqxxbForm queryForm = (CqxxbForm) session.getAttribute("cqxxbForm");
            // 将新更新的项目更新结果集中对应的对象
            Cqxxb oldbo = (Cqxxb) queryForm.getViewDataDetail();

            queryForm.modifyData(bo);

            // 保存Token;
            saveToken(request);

            // 转向成功后的view界面。
            request.setAttribute(Constants.MESSAGE_KEY, "拆迁信息修改保存成功！");

            return mapping.findForward("modifysucc");
        } catch (BaseException te) {
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return mapping.findForward("modify");
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("modify");
        }

    }

    /* （非 Javadoc）
     * @see com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction#doSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            // 调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            AddCqxxForm aForm = (AddCqxxForm) form;
            // 从Form中获取对象。
            Cqxxb bo = (Cqxxb) aForm.getData();
            UserData ud = this.getUserData();
            bo.setCjr(ud.yhmc);
            bo.setLrr(ud.yhid);
            bo.setSwjgzzjgdm(ud.getSsdwdm());
            Debug.out("AddBaseAction doAdd form.getData() obj.class is "
                      + bo.getClass().getName());
            // 调用QsglProxy的add方法，向数据库中增加一条记录。


            // 构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            bo = (Cqxxb) QsglProxy.getInstance().process(vo);
            // 保存Token;
            saveToken(request);

            // 转向成功后的view界面。
            request.setAttribute(Constants.MESSAGE_KEY, "拆迁信息录入保存成功！");
            aForm.clear();

            return mapping.findForward("show");
        } catch (BaseException te) {
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }
}
