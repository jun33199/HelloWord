package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddCqxxAction extends AddBaseAction {
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
        CqxxForm cqxxForm = (CqxxForm) form;
        String entrypage = cqxxForm.getEntrypage();
        try {
            if ((entrypage != null) && (entrypage.equals("EDIT"))) { //�޸��걨ҳ��
                SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");
                cqxxForm.setSbbh(sbxxForm.getSbbh());
            } else { //�����걨
                if (cqxxForm.getPerson().equals("true")) { //¼����˲�Ǩ��Ϣ
                    SbGrForm sbGrForm = (SbGrForm) session.getAttribute(
                            "sbGrForm");
                    cqxxForm.setSbbh(sbGrForm.getSbbh());
                } else {
                    SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                            "sbFgrForm");
                    cqxxForm.setSbbh(sbFgrForm.getSbbh());
                }
                //�������̨�����õ�VoPackage
                Properties prop = (Properties) request.getSession(false).
                                  getServletContext().getAttribute(Constants.
                        PROCESSOR_MAP_FILE_NAME);
                VOPackage vo = new VOPackage();
                vo.setAction(ActionType.GET);
                Swjgzzjg obj = new Swjgzzjg();
                vo.setProcessor(prop.getProperty(obj.getClass().getName()));
                vo.setUserData(this.getUserData());
                vo.setData(obj);
                obj = (Swjgzzjg) QsglProxy.getInstance().process(vo);
                cqxxForm.setFjmc(obj.getWsjc());
                cqxxForm.setNd(DateUtil.getDate().substring(0, 4));

            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "��ȡ�־ּ��ʧ��");
        }

        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ��ʾ�ɹ���");
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
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // ��Form�л�ȡ����
            Object obj = ((CqxxForm) form).getData();
            Debug.out("AddSbGrAction doAdd form.getData() obj.class is " +
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

            HttpSession session = request.getSession(false);
            CqxxForm cqxxForm = (CqxxForm) form;

            QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);

            //�������ӵĲ�Ǩ��Ϣ�ӵ� �걨form�� Ŀǰû���õ�
            String entrypage = cqxxForm.getEntrypage();
            if ((entrypage != null) && (entrypage.equals("EDIT"))) { //�޸��걨ҳ��
                SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");
                sbxxForm.getCqList().add(obj);
                if (sbxxForm.getVoSpjgxx() != null &&
                    sbxxForm.getVoSpjgxx().getHdtzszh() != null &&
                    !sbxxForm.getVoSpjgxx().getHdtzszh().equals("")) {
                    request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ����ɹ���");
                } else {
                    String msg = "����ʾ��˰�ˣ��뵽˰����ذ������˰�����ٰ�����˰�걨��";
                    sbxxForm.setAlertMessage(msg);
                    request.setAttribute(Constants.MESSAGE_KEY, msg);
                }

                return mapping.findForward("savemod");
            } else {
                String person = cqxxForm.getPerson();
                Debug.out("is person: " + person);
                if (person.equals("true")) {
                    SbGrForm sbGrForm = (SbGrForm) session.getAttribute(
                            "sbGrForm");
                    sbGrForm.cqList.add(obj);
                    session.setAttribute("sbGrForm", sbGrForm);
                    // ת��ɹ���Ľ��档
                    request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ����ɹ���");
                    return mapping.findForward("savegr");
                } else {
                    SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                            "sbFgrForm");
                    sbFgrForm.cqList.add(obj);
                    session.setAttribute("sbFgrForm", sbFgrForm);
                    if (sbFgrForm.getHdtzszh() != null &&
                        !sbFgrForm.getHdtzszh().equals("")) {
                        request.setAttribute(Constants.MESSAGE_KEY, "��Ǩ����ɹ���");
                    } else {
                        String msg = "����ʾ��˰�ˣ��뵽˰����ذ������˰�����ٰ�����˰�걨��";
                        sbFgrForm.setAlertMessage(msg);
                        request.setAttribute(Constants.MESSAGE_KEY, msg);
                    }
                    return mapping.findForward("savefgr");
                }
            }
        } catch (BaseException te) {
            Debug.printException(te);
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

    /**
     * ���ݲ�ǨЭ���,��ȡ��Ǩ��Ϣ
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
    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // ��Form�л�ȡ����
            Object obj = ((CqxxForm) form).getData();
            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            HttpSession session = request.getSession(false);
            CqxxForm cqxxForm = (CqxxForm) form;

            Jsblcq jsblcq = (Jsblcq) QsglProxy.getInstance().process(vo);
            if (jsblcq != null) {
                cqxxForm.setData(jsblcq);
                cqxxForm.setFirst(false);
                request.setAttribute(Constants.MESSAGE_KEY, "��¼�뱾��ʹ�ò����");
            } else {
                cqxxForm.setFirst(true);
                cqxxForm.setBcqfwzldz("");
                cqxxForm.setBcsybce("0");
                cqxxForm.setCqbce("0");
                cqxxForm.setCqbcsye("0");
                cqxxForm.setSyeywbz(Constants.JSBL_SYEYWBZ_WEIYONGWAN);
                request.setAttribute(Constants.MESSAGE_KEY, "ָ����Ǩ��Ϣ�����ڣ�");
            }
            // ����Token;
            saveToken(request);
            return mapping.findForward("show");

        } catch (BaseException te) {
            Debug.printException(te);
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

    /**
     * ȡ����ǰ������
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
        // ɾ��session�е�form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);
        CqxxForm cqxxForm = (CqxxForm) form;

        String entrypage = cqxxForm.getEntrypage();
        if ((entrypage != null) && (entrypage.equals("EDIT"))) { //�޸��걨ҳ��
            return mapping.findForward("returnmod");
        } else { //�����걨
            if (cqxxForm.getPerson().equals("true")) {
                return mapping.findForward("returngr");
            } else {
                return mapping.findForward("returnfgr");
            }
        }
    }


}
