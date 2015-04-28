package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbFgrBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class AddSbFgrAction extends AddBaseAction {

    /**
     *
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
        SbFgrForm sbFgrForm = (SbFgrForm) form;
        sbFgrForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        Debug.out("SbFgrForm.getJkfsList().size = " +
                  sbFgrForm.getJkfsList().size());

        sbFgrForm.setNsrlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.NSRLX));
        //sbFgrForm.setYhList(ActionUtil.getCodeTables(session.
        // getServletContext(), Constants.YH));
        //�ж����Բ�¼���������걨
        String bl = (String) request.getParameter("bl");
        if (bl != null && bl.equals("1")) {
            sbFgrForm.setBl(true);
        } else {
            sbFgrForm.setBl(false);
        }
        Debug.out("SbFgrForm.getNsrlxList().size = " +
                  sbFgrForm.getNsrlxList().size());

        request.setAttribute(Constants.MESSAGE_KEY, "�Ǹ����걨��ʾ�ɹ���");
        // ����Token;
        saveToken(request);

        return mapping.findForward("show");

    }

    /**
     * ��������ĺ˶�֪ͨ���ֺŻ�ȡ����˰���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetJmsje(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbFgrForm sbFgrForm = (SbFgrForm) form;
        // ����Token;
        saveToken(request);

        Debug.out("add sbfgr action getJmsje....");

        try {
            HashMap map = CommonUtil.getZcspjg(sbFgrForm.getHdtzszh());
            if (map == null) {
                request.setAttribute(Constants.MESSAGE_KEY, "û��ָ������������ŵļ�����Ϣ��");
            } else {
                sbFgrForm.setJmsje((String) map.get("jmse"));
                sbFgrForm.setJmlydm((String) map.get("qtjmly"));
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "ָ������������ŵļ�����Ϣ��ȡ�ɹ���");
            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "��ȡ�������������Ϣʧ��!");
            return new ActionForward(mapping.getInput());
        }
        return mapping.findForward("show");

    }

    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetNsrxx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbFgrForm sbFgrForm = (SbFgrForm) form;

        try {
            //���form,
            sbFgrForm.setNsrmc("");
            sbFgrForm.setYhList(new ArrayList());
            sbFgrForm.setYhzh("");

            HashMap djinfo = CommonUtil.getFgrDjInfo(sbFgrForm.getJsjdm());
            SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
            sbFgrForm.setNsrmc(jbsj.getNsrmc());
            ArrayList yhList = (ArrayList) djinfo.get("YHZH");

            if ((yhList != null) && (yhList.size() > 0)) {
                for (int i = 0; i < yhList.size(); i++) {
                    YHZH yhzh = (YHZH) yhList.get(i);
                    String khyhmc = yhzh.getKhyhmc();
                    khyhmc = khyhmc + "--" + yhzh.getZh();
                    yhzh.setKhyhmc(khyhmc);
                }
            }
            sbFgrForm.setYhList(yhList);
            request.setAttribute(Constants.MESSAGE_KEY, "�ɹ������˰�˵ĵǼ���Ϣ��");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");

    }

    /**
     * �����¼
     *
     * 1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. ��Form�л�ȡ����
     *     Object obj = ((BaseForm)form).getData();
     * 3. ����BaseProxy��add�����������ݿ�������һ����¼��
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. ����Token;
     *       saveToken();
     * 5. ת��ɹ���Ľ��档
     *     return mapping.findForward("add");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */

    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            Debug.out("come in ");
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            Debug.out("come in 1");
            // ��Form�л�ȡ����
            Object obj = ((SbFgrForm) form).getData();
            Debug.out("AddSbFgrAction doSave form.getData() obj.class is " +
                      obj.getClass().getName());
            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            Debug.out("form bo nsrmc: " + ((SbFgrBo) obj).nsrmc);

            String sbbh = (String) QsglProxy.getInstance().process(vo);
            Debug.out("after add sbbh: " + sbbh);
            ((SbFgrForm) form).setSbbh(sbbh);
            // ����Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "�Ǹ����걨����ɹ���");
            // ת��ɹ���Ľ��档
            return mapping.findForward("save");
        } catch (BaseException te) {
            saveToken(request);
            te.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            Debug.out(ex);
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }

    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doRedirect(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ����Token;
        saveToken(request);
        String subaction = request.getParameter("subAction");

        return mapping.findForward(subaction);
    }

    /**
     * ȡ����ǰ������
     * ���û��¼���ط��ݻ�����Ϣ,ɾ�������걨����
     *
     * ת��ȡ����Ӧȥ��ҳ�档
     *
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
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        // ��Form�л�ȡ����
        SbFgrForm sbForm = (SbFgrForm) form;
        String sbbh = sbForm.getSbbh();
        if (!sbForm.isFwjbxxAdded()) {
            VOPackage vo = new VOPackage();
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.DELETE);
            vo.setUserData(this.getUserData());
            ArrayList delList = new ArrayList();
            delList.add(sbbh);
            vo.setData(delList);
            SbxxBo bo = new SbxxBo();
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            try {
                QsglProxy.getInstance().process(vo);
            } catch (Exception ex) {
                Debug.printException(ex);
                request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
                return new ActionForward(mapping.getInput());
            }
        }
        // ɾ��session�е�form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);
        return mapping.findForward("return");
    }

}
