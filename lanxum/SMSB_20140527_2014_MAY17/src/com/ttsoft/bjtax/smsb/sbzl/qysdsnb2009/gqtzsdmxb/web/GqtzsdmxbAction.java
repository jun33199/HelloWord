package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.syax.creports.Constants;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.framework.exception.BaseException;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class GqtzsdmxbAction extends SmsbAction {
    /**
     * ����Ա����
     */

    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param actionForm
     *            QysdsnbForm
     * @param httpServletRequest
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     */

    protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest
                .setAttribute(
                        CodeConstant.SMSB_HEADER_POSITION,
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��ҵ����˰�����˰�걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * ��ʼ��ҳ������
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            QysdsnbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        GqtzsdmxbForm gform = (GqtzsdmxbForm) form;

        this.getBaseForm(request, gform);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setData(gform);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_GQTZSDMXB_PROCESSOR2009);
        vo.setUserData(userData);
        try {

            // ����processor
            gform = (GqtzsdmxbForm) SbzlProxy.getInstance().process(vo);

        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(mapping.getAttribute(), gform);
        return mapping.findForward("Show");
    }

    /**
     * ��ѯ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            JwsdmxbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doQuery(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {

        return null;
    }

    /**
     * �����걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            JwsdmxbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GqtzsdmxbForm gForm = (GqtzsdmxbForm) form;

        gForm.setDataList(SfRequestUtil.getValuesToList(request,
                gForm.getSb_columns()));
        gForm.setSbbczlList(SfRequestUtil.getValuesToList(request,
                gForm.getSbbczl_columns()));
        gForm.setHjList(SfRequestUtil.getValuesToList(request,
                gForm.getHj_columns()));

        this.getBaseForm(request, gForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(gForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_GQTZSDMXB_PROCESSOR2009);
        request.setAttribute(mapping.getAttribute(), gForm);
        try {
            gForm = (GqtzsdmxbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gForm);

        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
        return mapping.findForward("Show");
    }

    /**
     * �˳�ҳ��
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            QysdsnbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doExit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        return mapping.findForward("Return");
    }

    /**
     * ���ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doCheck(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {

        //-------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GqtzsdmxbForm gForm = (GqtzsdmxbForm) form;
        gForm.setDataList(SfRequestUtil.getValuesToList(request,
                gForm.getSb_columns()));
        gForm.setSbbczlList(SfRequestUtil.getValuesToList(request,
                gForm.getSbbczl_columns()));
        gForm.setHjList(SfRequestUtil.getValuesToList(request,
                gForm.getHj_columns()));
        this.getBaseForm(request, gForm); //������˰�˻�����Ϣ
        userData = this.getUserData(request);
        request.setAttribute(mapping.getAttribute(), gForm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_CHECKACTION); //���ò�������
        vo.setData(gForm); //���ò�������
        vo.setUserData(userData);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_GQTZSDMXB_PROCESSOR2009);
        try {
            //����processor
            gForm = (GqtzsdmxbForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), gForm);

            if (gForm.getCheckList() == null) {
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
            } else if (gForm.getCheckList().size() == 0) {
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
            } else if (gForm.getCheckList().size() > 0) {
                request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
                                     QysdsUtil2009.getCheckResults(gForm.
                        getCheckList()));
            }
            return mapping.findForward("Show");
        } catch (Exception ex) {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ɾ���걨����
     *
     * @param mapping
     *            struts.action.ActionMapping
     * @param form
     *            JwsdmxbForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {

//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        GqtzsdmxbForm gForm = (GqtzsdmxbForm) form;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(gForm);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_GQTZSDMXB_PROCESSOR2009);
        this.getBaseForm(request, gForm);
        try {
            // ����processor
            gForm = (GqtzsdmxbForm) SbzlProxy.getInstance().process(vo);

        } catch (Exception ex) {
            // ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(mapping.getAttribute(), gForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
        return mapping.findForward("Show");
    }

    /**
     * ��session�л�ȡ������Ϣ
     *
     * @param request
     * @return
     */
    private void getBaseForm(HttpServletRequest request, GqtzsdmxbForm form) {

        userData = this.getUserData(request);

        QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
                                .getAttribute(CodeConstant.
                                              SESSIONKEY_QYSDSNEWFORM);

        String ksrq = request.getParameter("skksrq");
        String jsrq = request.getParameter("skjsrq");

        if ((ksrq != null && !"".equals(ksrq)) &&
            (jsrq != null && !"".equals(jsrq))) {
            baseForm.setSkssksrq(request.getParameter("skksrq"));
            baseForm.setSkssjsrq(request.getParameter("skjsrq"));
            request.getSession(false).setAttribute(CodeConstant.
                    SESSIONKEY_QYSDSNEWFORM, baseForm);
        }

        if (baseForm != null) {
            form.setJsjdm(baseForm.getJsjdm());
            form.setSbrq(baseForm.getSbrq());
            form.setNsrmc(baseForm.getNsrmc());
            form.setQh("1");
            form.setSknd(baseForm.getSknd());
            form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
            form.setSkssksrq(baseForm.getSkssksrq());
            form.setSkssjsrq(baseForm.getSkssjsrq());
            form.setSwjsjdm(baseForm.getSwjsjdm());
            form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
            form.setQxdm(baseForm.getQxdm());
            form.setLrr(userData.getYhid());
            form.setCkzd(baseForm.getCkzd());
            form.setZsfs(baseForm.getZsfs());
            form.setSsjjlx(baseForm.getSsjjlx());
            form.setSshy(baseForm.getSshy());
            form.setGzglxs(baseForm.getGzglxs());
            form.setJmlx(baseForm.getJmlx());
            form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,
                    "N_" + CodeConstant.TABLE_ID_2009_11));
            form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,
                    "P_" + CodeConstant.TABLE_ID_2009_11));
        }
    }
}
