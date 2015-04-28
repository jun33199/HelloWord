package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.ShskBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShskAction extends BaseAction {
    /**
     * ��ʱ����--�����걨���ȡ�������Ϣ
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
        ShskForm shskForm = (ShskForm) form;
        VOPackage vo = new VOPackage();

        QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                "querySbxxForm");
        JsblBo bo = (JsblBo) aForm.getViewDataDetail();

        //��������տ�bo
        ShskBo shskbo = new ShskBo();
        shskbo.setSbbh(bo.getSbbh());
        shskbo.setNsrmc(bo.getNsrmc());
        shskbo.setCjjgrmb(bo.getCjjgrmb());
        shskbo.setTdfwzldz(bo.getTdfwzldz());

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //�������̨�����Vopackage����
        vo.setAction(ActionType.GET);
        vo.setUserData(this.getUserData());
        vo.setData(shskbo);
        vo.setProcessor(prop.getProperty(shskbo.getClass().getName()));
        try {
            shskbo = (ShskBo) QsglProxy.getInstance().process(vo);
            if (bo.getDrpch() != null && !bo.getDrpch().equals("")) {
                shskbo.setPc(true);
            } else {
                shskbo.setPc(false);
            }
            shskForm.setData(shskbo);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
        }
        request.setAttribute(Constants.MESSAGE_KEY, "��ʾ�ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ��ʱ����--�����걨���ȡ�������Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCheck(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ShskForm shskForm = (ShskForm) form;
        VOPackage vo = new VOPackage();

        //��������տ�bo
        ShskBo shskbo = (ShskBo) shskForm.getData();

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);

        //�������̨�����Vopackage����
        vo.setAction(ActionType.CHECK);
        vo.setUserData(this.getUserData());
        vo.setData(shskbo);
        vo.setProcessor(prop.getProperty(shskbo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            shskbo.setChecked(true);
            shskForm.setChecked(true);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
        }
        request.setAttribute(Constants.MESSAGE_KEY, "������Ϣȡ�óɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("check");
    }

    /**
     * ת��ɿ����ӡҳ��
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintJks(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ����Token;
        saveToken(request);
        return mapping.findForward("printJks");
    }

    /**
     * ת����˰֤��ӡҳ��
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doPrintWsz(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ����Token;
        saveToken(request);
        return mapping.findForward("printWsz");
    }
}
