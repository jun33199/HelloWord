/*
 * Created on 2006-2-21
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SjqyForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SjqyAction extends BaseAction {

    /**
     *
     */
    public SjqyAction() {
        super();
        //
    }

    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SjqyForm sForm = (SjqyForm) form;
        sForm.setQybs("0");
        sForm.setRet(new ArrayList());
        sForm.setJsfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));

        request.setAttribute(Constants.MESSAGE_KEY, "��ʾ�ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * Ǩ�Ƶ�һ��
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        //HttpSession session = request.getSession(false);
        SjqyForm sForm = (SjqyForm) form;
        sForm.setQybs("1");
        VOPackage vo = new VOPackage();
        List ret = new ArrayList();
        Map data = new HashMap();
        data.put("qxdm", sForm.getQxdm());
        data.put("xxzl", sForm.getXxzl());
        data.put("jsfsdm", sForm.getJsfsdm());
        data.put("ztbs", sForm.getZtbs());
        data.put("blbs", sForm.getBlbs());
        data.put("cc", sForm.getCc());

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //�������̨�����Vopackage����
        vo.setAction(ActionType.INSERT);
        vo.setUserData(this.getUserData());
        vo.setData(data);
        vo.setProcessor(
                "com.creationstar.bjtax.qsgl.BizLogic.processor.SjqyProcessor");
        try {
            ret = (List) QsglProxy.getInstance().process(vo);

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.out("-----------QS SJQY ERROR INFO-------");
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, "��һ��Ǩ�ư���ʧ�ܣ�");
        }

        sForm.setRet(ret);
        request.setAttribute(Constants.MESSAGE_KEY, "��һ��Ǩ�ư���ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("save");
    }

    /**
     * Ǩ�Ƶڶ���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doUpdate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        //HttpSession session = request.getSession(false);
        SjqyForm sForm = (SjqyForm) form;
        sForm.setQybs("2");
        VOPackage vo = new VOPackage();
        List ret = new ArrayList();
        Map data = new HashMap();
        data.put("qxdm", sForm.getQxdm());
        data.put("xxzl", sForm.getXxzl());
        data.put("jsfsdm", sForm.getJsfsdm());
        data.put("cc", sForm.getCc());

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //�������̨�����Vopackage����
        vo.setAction(ActionType.UPDATE);
        vo.setUserData(this.getUserData());
        vo.setData(data);
        vo.setProcessor(
                "com.creationstar.bjtax.qsgl.BizLogic.processor.SjqyProcessor");
        try {
            ret = (List) QsglProxy.getInstance().process(vo);

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.out("-----------QS SJQY ERROR INFO-------");
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, "�ڶ���Ǩ�ư���ʧ�ܣ�");
        }

        sForm.setRet(ret);
        request.setAttribute(Constants.MESSAGE_KEY, "�ڶ���Ǩ�ư���ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("save");
    }

    /**
     * Ǩ�Ƶ�����
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQyhz(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        //HttpSession session = request.getSession(false);
        SjqyForm sForm = (SjqyForm) form;
        sForm.setQybs("3");
        VOPackage vo = new VOPackage();
        List ret = new ArrayList();
        Map data = new HashMap();
        data.put("qxdm", sForm.getQxdm());
        data.put("jsfsdm", sForm.getJsfsdm());
        data.put("cc", sForm.getCc());

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //�������̨�����Vopackage����
        vo.setAction(ActionType.BL_CREATECONNECT_HZJKS);
        vo.setUserData(this.getUserData());
        vo.setData(data);
        vo.setProcessor(
                "com.creationstar.bjtax.qsgl.BizLogic.processor.SjqyProcessor");
        try {
            ret = (List) QsglProxy.getInstance().process(vo);

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.out("-----------QS SJQY ERROR INFO-------");
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, "������Ǩ�ư���ʧ�ܣ�");
        }

        sForm.setRet(ret);
        request.setAttribute(Constants.MESSAGE_KEY, "������Ǩ�ư���ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("save");
    }

}
