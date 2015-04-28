package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.nstzmxb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import java.util.HashMap;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.framework.exception.BaseException;
import java.util.ArrayList;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;

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
public class NstzmxbAction2009 extends SmsbAction {
    /**
     * ����Ա����
     */

    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm QysdsnbForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */

    protected void initialRequest(ActionMapping mapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��ҵ����˰�����˰�걨��");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

    }

    /**
     * ��ʼ��ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
        NstzmxbForm2009 nstzmxzjbForm = (NstzmxbForm2009) form;
        this.getBaseForm(request, nstzmxzjbForm); //������˰�˻�������
        userData = this.getUserData(request);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION); //���ò�������
        vo.setData(nstzmxzjbForm); //���ò�������
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_NSTZMXB_PROCESSOR2009); //����Processor
        vo.setUserData(userData);
        try {
            nstzmxzjbForm = (NstzmxbForm2009) SbzlProxy.getInstance().process(
                    vo);
            request.setAttribute(mapping.getAttribute(), nstzmxzjbForm); //�Ѳ�ѯ�������REQUEST
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Show");

    }

    /**
     * ����ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        NstzmxbForm2009 nstzmxzjbform = (NstzmxbForm2009) form;
        userData = this.getUserData(request);
        String[] columns = nstzmxzjbform.getSb_cloumns();
        nstzmxzjbform.setDataList(SfRequestUtil.getValuesToList(request,
                columns)); //���ù̶�������
        this.getBaseForm(request, nstzmxzjbform); //������˰�˻�������
        request.setAttribute(mapping.getAttribute(), nstzmxzjbform);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION); //���ò�������
        vo.setData(nstzmxzjbform); //���ò�������
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_NSTZMXB_PROCESSOR2009); //PROCESSOR
        vo.setUserData(userData);
        try {
            //����processor
            nstzmxzjbform = (NstzmxbForm2009) SbzlProxy.getInstance().process(
                    vo);
            request.setAttribute(mapping.getAttribute(), nstzmxzjbform);
        } catch (Exception ex) {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���"); //���ر�����
        return mapping.findForward("Show");
    }

    /**
     * ɾ��ҳ������
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            BaseException {
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        NstzmxbForm2009 nstzmxzjbform = (NstzmxbForm2009) form;
        userData = this.getUserData(request);
        this.getBaseForm(request, nstzmxzjbform); //������˰�˻�����������

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION); //���ò�������
        vo.setData(nstzmxzjbform); //���ò�������
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_NSTZMXB_PROCESSOR2009); //����PROCESSOR
        vo.setUserData(userData);
        try {
            //����processor
            nstzmxzjbform = (NstzmxbForm2009) SbzlProxy.getInstance().process(
                    vo);
            request.setAttribute(mapping.getAttribute(), nstzmxzjbform);
        } catch (Exception ex) {
//		ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���"); //����ɾ�����
        return mapping.findForward("Show");
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
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        NstzmxbForm2009 nstzmxzjbform = (NstzmxbForm2009) form;
        String[] columns = nstzmxzjbform.getSb_cloumns();
        nstzmxzjbform.setDataList(SfRequestUtil.getValuesToList(request,
                columns)); //���ù̶�������
        this.getBaseForm(request, nstzmxzjbform); //������˰�˻�������
        request.setAttribute(mapping.getAttribute(), nstzmxzjbform);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_CHECKACTION); //���ò�������
        vo.setData(nstzmxzjbform); //���ò�������
        vo.setUserData(userData);
        vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_NSTZMXB_PROCESSOR2009); //����PROCESSOR
        try {
//			����processor
            nstzmxzjbform = (NstzmxbForm2009) SbzlProxy.getInstance().process(
                    vo);

            request.setAttribute(mapping.getAttribute(), nstzmxzjbform);

            if (nstzmxzjbform.getCheckList() == null) {
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
            } else if (nstzmxzjbform.getCheckList().size() == 0) {
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
            } else if (nstzmxzjbform.getCheckList().size() > 0) {
                request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
                                     QysdsUtil2009.getCheckResults(
                                             nstzmxzjbform.
                                             getCheckList()));
            }
            return mapping.findForward("Show");
        } catch (Exception ex) {
//			ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ��ҳ������ת����LIST���ݽṹ�ķ���
     */
    public ArrayList getValuesToList(HttpServletRequest request,
                                     String columns[]) {
        ArrayList list = new ArrayList();
        if (columns == null) {

            return list;
        }
        if (request.getParameter(columns[0]) != null) {
            int rows = request.getParameterValues(columns[0]).length;
            for (int i = 0; i < rows; i++) {
                HashMap map = new HashMap();
                for (int j = 0; j < columns.length; j++) {
                    if (request.getParameter(columns[j]) == null) {
                        continue;
                    }
                    map.put(columns[j],
                            request.getParameterValues(columns[j])[i]);
                }
                list.add(map);
            }
        }
        return list;
    }

    /**
     * �˳�ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doExit(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
            BaseException {

        return mapping.findForward("Return");
    }

    /**
     * ��ȡ��˰�˻�����Ϣ
     * @param request
     * @return
     */
    private void getBaseForm(HttpServletRequest request, NstzmxbForm2009 form) {

        userData = this.getUserData(request);

        QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false).
                                getAttribute(CodeConstant.
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
            form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,
                    "N_" + CodeConstant.TABLE_ID_2009_3));
            form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,
                    "P_" + CodeConstant.TABLE_ID_2009_3));
        }
    }
}
