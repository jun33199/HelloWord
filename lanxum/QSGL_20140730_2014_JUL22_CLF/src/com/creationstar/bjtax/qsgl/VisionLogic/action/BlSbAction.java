package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlAllSbWszForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BlSbAction extends AddBaseAction {

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
        BlAllSbWszForm aForm = (BlAllSbWszForm) form;
        BlJksForm bForm = (BlJksForm) session.getAttribute("blJksForm");
        try {

            aForm.setJksh(bForm.getJksBo().getSbjkzb().getJkpzh());
            aForm.setZwbs(bForm.getJksBo().getSbjkzb().getZwbs());
            aForm.setType(bForm.getType());
            aForm.setZbxh(bForm.getZbxh());
            aForm.setSklxdm(bForm.getSklxdm());
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��¼��ʾ�ɹ���");
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
    public ActionForward doChangeBllx(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Debug.out("look what form it is:");
        Debug.out(form);
        BlAllSbWszForm blAllSbWszForm = (BlAllSbWszForm) form;
        if (blAllSbWszForm.getBllx().equals("1")) { //����
            // ����Token;
            saveToken(request);
        }
        return mapping.findForward("showhz");

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
            BlAllSbWszForm aForm = (BlAllSbWszForm) form;
            // ��Form�л�ȡ����
            Object obj = aForm.getBo();

            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.BL_CHECK_SBJKS);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            Boolean success = (Boolean) QsglProxy.getInstance().process(vo);
            //��֤�걨���ͽɿ������Ƿ����
            if (success.booleanValue() == false) {
                // ����Token;
                saveToken(request);
                request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣ�е�Ӧ��˰��ͽɿ��鲻��");
                return new ActionForward(mapping.getInput());
            } else {
                vo.setAction(ActionType.BL_CREATECONNECT_SBJKS);
                BlJksBo blJksBo = new BlJksBo();
                blJksBo.setJkpzh(aForm.getJksh());
                blJksBo.setSbbh(aForm.getSbbh());
                blJksBo.setType(aForm.getType());
                blJksBo.setZbxh(aForm.getZbxh());
                blJksBo.setSklxdm(aForm.getSklxdm());
                blJksBo.setZwbs(aForm.getZwbs());
                vo.setData(blJksBo);
                ArrayList sbhdList = (ArrayList) QsglProxy.getInstance().
                                     process(vo);
                aForm.setSbhdList(sbhdList);

                //����ѯҳ��Ĳ�¼�ɹ�������ɾȥ
                HttpSession session = request.getSession(false);
                BlJksForm queryForm = (BlJksForm) session.getAttribute(
                        "blJksForm");
                ArrayList delList = new ArrayList();
                delList.add(queryForm.getRemoveBo());
                queryForm.removeSelectedData(delList);

                // ����Token;
                saveToken(request);
                if (sbhdList != null && sbhdList.size() > 0) {
                    request.setAttribute(Constants.MESSAGE_KEY, "��ʾ��Ӧ�˶�֪ͨ���");
                    // ת��ɹ���Ľ��档
                    return mapping.findForward("sbhd");

                } else {
                    //���ø��෽����Form�����Session��ȥ����
                    removeForm(mapping, request);
                    request.setAttribute(Constants.MESSAGE_KEY, "��¼�ɹ�");
                    // ת��ɹ���Ľ��档
                    return mapping.findForward("save");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }

}
