package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.YggyzfForm;
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

public class AddYggyzfAction extends AddBaseAction {
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
        Debug.out("look what form it is:");
        Debug.out(form);
        YggyzfForm yggyzfForm = (YggyzfForm) form;

        if (yggyzfForm.isPerson()) { //¼����˲�Ǩ��Ϣ
            SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
            yggyzfForm.setSbbh(sbGrForm.getSbbh());
        } else {
            SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute("sbFgrForm");
            yggyzfForm.setSbbh(sbFgrForm.getSbbh());
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
        try {
            obj = (Swjgzzjg) QsglProxy.getInstance().process(vo);
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "��ȡ�־ּ��ʧ��");
        }

        yggyzfForm.setFjmc(obj.getWsjc());
        yggyzfForm.setNd(DateUtil.getDate().substring(0, 4));

        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�ѹ�����ס����ʾ�ɹ���");
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
            Object obj = ((YggyzfForm) form).getData();
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
            YggyzfForm yggyzfFrom = (YggyzfForm) form;

            QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);
            if (yggyzfFrom.isPerson()) {
                //�������ӵĹ���ס����Ϣ�ӵ� �걨form��
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                sbGrForm.gyList.add(obj);
                session.setAttribute("sbGrForm", sbGrForm);
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "�ѹ�����ס������ɹ���");
                return mapping.findForward("savegr");
            } else {
                SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                        "sbFgrForm");
                sbFgrForm.gyList.add(obj);
                session.setAttribute("sbFgrForm", sbFgrForm);
                request.setAttribute(Constants.MESSAGE_KEY, "�ѹ�����ס������ɹ���");
                return mapping.findForward("savefgr");
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
        YggyzfForm yggyzfForm = (YggyzfForm) form;

        if (yggyzfForm.isPerson()) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
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
            Object obj = ((YggyzfForm) form).getData();
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
            YggyzfForm yggyzfForm = (YggyzfForm) form;

            Jsblgyzf jsblgyzf = (Jsblgyzf) QsglProxy.getInstance().process(vo);
            if (jsblgyzf != null) {
                yggyzfForm.setData(jsblgyzf);
                yggyzfForm.setFirst(false);
                request.setAttribute(Constants.MESSAGE_KEY, "��¼�뱾�εֿ۶");
            } else {
                yggyzfForm.setFirst(true);
                yggyzfForm.setZldz("");
                yggyzfForm.setFwqszsh("");
                yggyzfForm.setJzmj("0");
                yggyzfForm.setBcdke("0");
                yggyzfForm.setBckdke("0");
                yggyzfForm.setCjjg("0");
                yggyzfForm.setSye("0");
                yggyzfForm.setCshtqdsj("");
                yggyzfForm.setSyeywbz(Constants.JSBL_SYEYWBZ_WEIYONGWAN);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "ָ������ס����Ϣ�����ڣ���ֱ��¼�롣");
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


}
