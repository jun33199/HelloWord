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
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
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
public class BlSbWszAction extends AddBaseAction {
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
        if (blAllSbWszForm.getBllx().equals("0")) { //�ǻ���
            // ����Token;
            saveToken(request);
        }
        return mapping.findForward("showfhz");

    }

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
     * ��ʾȷ������
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doConfirm(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        BlAllSbWszForm aForm = (BlAllSbWszForm) form;
        try {
            String[] aList = aForm.getSbbhList();
            String[] bList = aForm.getWszList();
            String[] cList = aForm.getNdzbList();
            String[] dList = aForm.getPzzlList();
            ArrayList showList = new ArrayList();
            for (int i = 0; i < aForm.getSbbhList().length; i++) {
                BlJksBo bo = new BlJksBo();
                bo.setSbbh(aList[i]);
                bo.setWszh(bList[i]);
                bo.setNdzb(cList[i]);
                bo.setPzzldm(dList[i]);
                showList.add(bo);
            }
            aForm.setDataList(showList);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��¼��ʾ�ɹ���");
        return mapping.findForward("confirm");

    }

    /**
     * ����ȷ������
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
            vo.setAction(ActionType.BL_CHECK_HZJKS);
            QueryBlJksBo qbo = new QueryBlJksBo();
            vo.setProcessor(prop.getProperty(qbo.getClass().getName()));
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
                vo.setAction(ActionType.BL_CREATECONNECT_HZJKS);
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

    /**
     * ����ȷ������
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doBack(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        saveToken(request);
        // ת��ɹ���Ľ��档
        return mapping.findForward("back");

    }
}
