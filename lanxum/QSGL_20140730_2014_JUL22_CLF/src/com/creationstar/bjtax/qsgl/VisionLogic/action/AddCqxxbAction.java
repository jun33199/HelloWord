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
            // ��context��ȡ�������Ϣ,��ȡ����map,
            // ���еõ�������˰�����͡����֤�����͡����֡�������𡢷�������ء�����Ȩ��ת�����͵�arraylist
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
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            String err = ex.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��Ӳ�Ǩ��Ϣ��ʾ�ɹ���");
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

            // �������̨�����Vopackage����
            vo.setAction(ActionType.GET);
            vo.setUserData(userData);
            vo.setData(cqxxbh);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            bo = (Cqxxb) QsglProxy.getInstance().process(vo);
            // ��context��ȡ�������Ϣ,��ȡ����map,
            // ���еõ�������˰�����͡����֤�����͡����֡�������𡢷�������ء�����Ȩ��ת�����͵�arraylist
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
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            String err = ex.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�޸Ĳ�Ǩ��Ϣ��ʾ�ɹ���");
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
            // ���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            AddCqxxForm aForm = (AddCqxxForm) form;
            // ��Form�л�ȡ����
            Cqxxb bo = (Cqxxb) aForm.getData();
            bo.setCqxxbh(aForm.getCqxxbh());

            UserData ud = this.getUserData();

            bo.setCjr(ud.yhmc);
            bo.setLrr(ud.yhid);

            // ����QsglProxy��add�����������ݿ�������һ����¼��


            // �������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.MODIFY);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            QsglProxy.getInstance().process(vo);

            CqxxbForm queryForm = (CqxxbForm) session.getAttribute("cqxxbForm");
            // ���¸��µ���Ŀ���½�����ж�Ӧ�Ķ���
            Cqxxb oldbo = (Cqxxb) queryForm.getViewDataDetail();

            queryForm.modifyData(bo);

            // ����Token;
            saveToken(request);

            // ת��ɹ����view���档
            request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ��Ϣ�޸ı���ɹ���");

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

    /* ���� Javadoc��
     * @see com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction#doSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            // ���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            AddCqxxForm aForm = (AddCqxxForm) form;
            // ��Form�л�ȡ����
            Cqxxb bo = (Cqxxb) aForm.getData();
            UserData ud = this.getUserData();
            bo.setCjr(ud.yhmc);
            bo.setLrr(ud.yhid);
            bo.setSwjgzzjgdm(ud.getSsdwdm());
            Debug.out("AddBaseAction doAdd form.getData() obj.class is "
                      + bo.getClass().getName());
            // ����QsglProxy��add�����������ݿ�������һ����¼��


            // �������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            bo = (Cqxxb) QsglProxy.getInstance().process(vo);
            // ����Token;
            saveToken(request);

            // ת��ɹ����view���档
            request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ��Ϣ¼�뱣��ɹ���");
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
