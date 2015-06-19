package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryJmsbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.SbState;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewJmsbxxAction extends BaseAction {
    /**
     * �����걨--�����걨���ȡ�������Ϣ
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
        JmsbxxForm jmsbxxForm = (JmsbxxForm) form;
        // jmsbxxForm.setJkfsList(ActionUtil.getCodeTables(session.getServletContext(),
        // Constants.JSFS));
        jmsbxxForm.setSfzjlxList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.ZJLX));
        jmsbxxForm.setNsrlxList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.NSRLX));
        jmsbxxForm.setGjdqList(ActionUtil.getCodeTables(session
                .getServletContext(), Constants.GJ));

        VOPackage vo = new VOPackage();

        QueryJmsbxxForm aForm = (QueryJmsbxxForm) session
                                .getAttribute("queryJmsbxxForm");
        JmsbblBo bo = (JmsbblBo) aForm.getViewDataDetail();
        JmsbxxBo jmsbxxBo = new JmsbxxBo();
        jmsbxxBo.setSbbh(bo.getSbbh());

        // ��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // �������̨�����Vopackage����
        vo.setAction(ActionType.GET);
        vo.setUserData(this.getUserData());
        vo.setData(jmsbxxBo);
        vo.setProcessor(prop.getProperty(jmsbxxBo.getClass().getName()));
        try {
            JmsbxxBo detailBo = (JmsbxxBo) QsglProxy.getInstance().process(vo);
            jmsbxxForm.setData(detailBo);

            ArrayList jmsbbList = jmsbxxForm.getJmsbbList();

            if (jmsbbList.size() > 0) {
                StringBuffer qsjmlbmc = new StringBuffer();
                String[] qsjmlbSelect = new String[jmsbbList.size()];

                for (int i = 0; i < jmsbbList.size(); i++) {

                    Jmsbb jmsbb = (Jmsbb) jmsbbList.get(i);

                    System.out.println(
                            "===============================================");
                    System.out.println("��ѡ��ļ������ߣ�" + jmsbb.getJmzcdm());

                    if (i > 0) {
                        qsjmlbmc.append("��<br>");
                    }

                    HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
                            .getServletContext(), Constants.JMZCMAP);
                    Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(jmsbb.getJmzcdm());
                    qsjmlbmc.append(qsjmlbVo.getQsjmlbmc());
                    // ��������������д�뱸ע
                    if (Constants.CXXJM_JMXMDM_QT.equals(jmsbb.getJmzcdm())) {
                        jmsbxxForm.setQtjmlybeizhu(jmsbb.bz);
                    }
                    qsjmlbSelect[i] = jmsbb.getJmzcdm();
                    jmsbxxForm.setJmxzdm(jmsbb.getJmxzdm());
                }

                System.out.println("�����������ɱ�ע��" + jmsbxxForm.getQtjmlybeizhu());

                jmsbxxForm.setQsjmlbmc(qsjmlbmc.toString());
                jmsbxxForm.setQsjmlbSelect(qsjmlbSelect);
            }

            String path = mapping.getPath();
            Debug.out("mapping.getPath() is " + path);

            // �����������������Ҫ�����������ء�����״̬list
            // д��
            if (path.equals("/jmsb/jmsbSp")) {

                jmsbxxForm.setSpjgList(detailBo.getSpjgList());

                jmsbxxForm.setSpztList(detailBo.getSpztList());
            }

            request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣϸ����ʾ�ɹ���");

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            // return new ActionForward(mapping.getInput());
        }

        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ����
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
    public ActionForward doCancel(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // ���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // ��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // �������̨�����Vopackage����
        vo.setAction(ActionType.CANCEL);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // ���µ�ǰҳ������
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            aForm.setData(bo);
            // ��Ϊ״̬�ı䣬�Ӳ�ѯ�����ɾ��
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());
            request.setAttribute(Constants.MESSAGE_KEY, "������ʾ�ɹ���");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("cancel");

    }

    /**
     * ɾ��
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
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // ���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // ��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // �������̨�����Vopackage����
        vo.setAction(ActionType.DELETE);
        vo.setUserData(this.getUserData());
        ArrayList delList = new ArrayList();
        delList.add(bo.getSbbh());
        vo.setData(delList);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // ���µ�ǰҳ������
            bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_ZF);
            aForm.setData(bo);
            // ��Ϊ״̬�ı䣬�Ӳ�ѯ�����ɾ��
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            queryForm.removeData(queryForm.getViewIndex());
            request.setAttribute(Constants.MESSAGE_KEY, "ɾ���ɹ���");
            // ���ø��෽����Form�����Session��ȥ����
            removeForm(mapping, request);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("delete");
        // return mapping.findForward("delete");

    }

//	/**
//	 * �����걨--���ͬ��
//	 *
//	 * @param mapping
//	 *            ActionMapping
//	 * @param form
//	 *            ActionForm
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @return ActionForward
//	 */
//	public ActionForward doConfirm(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		// ���ø��෽������Token.
//		ActionForward forward = doHandleToken(mapping, request);
//		HttpSession session = request.getSession(false);
//		VOPackage vo = new VOPackage();
//
//		JmsbxxForm aForm = (JmsbxxForm) form;
//		JmsbxxBo bo = (JmsbxxBo) aForm.getData();
//
//		// ��ȡ�����ServletContext�е�processor-map.properties������
//		Properties prop = (Properties) request.getSession(false)
//				.getServletContext().getAttribute(
//						Constants.PROCESSOR_MAP_FILE_NAME);
//
//		// �������̨�����Vopackage����
//		vo.setAction(ActionType.CONFIRM);
//		vo.setUserData(this.getUserData());
//		vo.setData(bo);
//		vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//		try {
//			QsglProxy.getInstance().process(vo);
//			bo.getVoSbzb().setZtbs(Constants.ZB_ZTBS_JS_TY);
//			QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
//					.getAttribute("queryJmsbxxForm");
//			// queryForm.removeData(queryForm.getViewIndex());
//
//		} catch (Exception ex) {
//			// ����Token;
//			saveToken(request);
//			ex.printStackTrace();
//			request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
//			return new ActionForward(mapping.getInput());
//
//		}
//		// ����Token;
//		saveToken(request);
//		request.setAttribute(Constants.MESSAGE_KEY, "����ͬ��ɹ���");
//		return mapping.findForward("confirm");
//
//	}

//	/**
//	 * �����걨--��˲�ͬ��
//	 *
//	 * @param mapping
//	 *            ActionMapping
//	 * @param form
//	 *            ActionForm
//	 * @param request
//	 *            HttpServletRequest
//	 * @param response
//	 *            HttpServletResponse
//	 * @return ActionForward
//	 */
//	public ActionForward doReject(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		// ���ø��෽������Token.
//		ActionForward forward = doHandleToken(mapping, request);
//		HttpSession session = request.getSession(false);
//		VOPackage vo = new VOPackage();
//
//		JmsbxxForm aForm = (JmsbxxForm) form;
//		JmsbxxBo bo = (JmsbxxBo) aForm.getData();
//
//		// ��ȡ�����ServletContext�е�processor-map.properties������
//		Properties prop = (Properties) request.getSession(false)
//				.getServletContext().getAttribute(
//						Constants.PROCESSOR_MAP_FILE_NAME);
//
//		// �������̨�����Vopackage����
//		vo.setAction(ActionType.REJECT);
//		vo.setUserData(this.getUserData());
//		vo.setData(bo);
//		vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//		try {
//			QsglProxy.getInstance().process(vo);
//			QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
//					.getAttribute("queryJmsbxxForm");
//			// queryForm.removeData(queryForm.getViewIndex());
//
//		} catch (Exception ex) {
//			// ����Token;
//			saveToken(request);
//			ex.printStackTrace();
//			request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
//			return new ActionForward(mapping.getInput());
//
//		}
//		// ����Token;
//		saveToken(request);
//		request.setAttribute(Constants.MESSAGE_KEY, "������ͬ��ɹ���");
//		return mapping.findForward("reject");
//
//	}

    /**
     * ȡ����ǰ��������Form�����Session��ȥ���� ת�򷵻غ�Ӧȥ��ҳ�档
     *
     * 1. ���ø��෽����Form�����Session��ȥ���� removeForm(mapping,request); 2. ����Token;
     * saveToken(); return mapping.findForward("return");
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        JmsbxxBo bo = (JmsbxxBo) ((JmsbxxForm) form).getData();
        // ���ø��෽����Form�����Session��ȥ����
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);

        return mapping.findForward("return");

    }

    /**
     * �����ϴβ���,�ָ��ϴ�״̬
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
    public ActionForward doRollBack(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();
        Debug.out("ViewSbxxAction bo sbbh: " + bo.getSbbh());

        // ��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // �������̨�����Vopackage����
        vo.setAction(ActionType.ROLLBACK);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);

            // ���µ�ǰҳ������
            bo.getVoSbzb().setZtbs(
                    SbState.getCancelStateCode(bo.getVoSbzb().getZtbs(), bo
                                               .getVoSbzb().getBljmsbs()));
            // ��Ϊ״̬�ı䣬�Ӳ�ѯ�����ɾ��
            HttpSession session = request.getSession(false);
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());
            // request.setAttribute(Constants.MESSAGE_KEY,"�ָ�1�ɹ���");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }

        // // ����Token;
        // saveToken(request);
        // request.setAttribute(Constants.MESSAGE_KEY, "�����ɹ���");
        // return mapping.findForward("rollback");

        // ������ʾ
        return doShow(mapping, form, request, response);

    }

    /**
     * �޸��걨--�޸ļ����걨����Ϣ
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
    public ActionForward doRedirect(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String subaction = request.getParameter("subaction");

        saveToken(request);

        JmsbxxForm sbxxForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) sbxxForm.getData();
        // �����Ǹ��˽����Ļ��ǷǸ���
        if (!bo.isPerson()) { // �Ǹ���
            return mapping.findForward("modjmsbxxfgr");
        } else { // ����
            return mapping.findForward("modjmsbxxgr");
        }

        // return mapping.findForward(subaction);

    }


    /**
     * �����걨--���ͬ��
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
    public ActionForward doSavesp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // ���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        HttpSession session = request.getSession(false);
        VOPackage vo = new VOPackage();

        JmsbxxForm aForm = (JmsbxxForm) form;
        JmsbxxBo bo = (JmsbxxBo) aForm.getData();

        // ��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) request.getSession(false)
                          .getServletContext().getAttribute(
                                  Constants.PROCESSOR_MAP_FILE_NAME);

        // �������̨�����Vopackage����
        vo.setAction(ActionType.SAVE_CXXJMSP);
        vo.setUserData(this.getUserData());
        vo.setData(bo);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            bo.getVoSbzb().setZtbs(bo.getSpzt());
            QueryJmsbxxForm queryForm = (QueryJmsbxxForm) session
                                        .getAttribute("queryJmsbxxForm");
            // queryForm.removeData(queryForm.getViewIndex());

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());

        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�����ɹ���");
        return mapping.findForward("savesp");

    }

}
