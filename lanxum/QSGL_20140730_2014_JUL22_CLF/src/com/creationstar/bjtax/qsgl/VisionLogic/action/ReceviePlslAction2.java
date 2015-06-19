package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ReceivePlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
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
 * TODO C 1�����ӷ�������ת����˳���Ĵ��������޸�ҳ��
 * 2�����ӷ�������˳������Ϣ�޸ĺ󣬷��غ�̨����̨��÷��ص����ݣ�
 * 		���º����ҳ������ϲ����е�ҳ������Ϊһ��list����ת��plslAction��
 * 		�������½��������Ѻϲ���������������һ�飬����doCheck�������ƵĹ���
 *
 * ˵������action�����ҳ����"��������(˰����Ա)"ģ��
 */
public class ReceviePlslAction2 extends BaseAction {
    /**
     * �����¼
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReceive(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // ��Request�л�ȡ����
            Object obj = pForm.getOkList();

            //����QsglProxy��check�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.RECEIVE_DRZB);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            QsglProxy.getInstance().process(vo);
            // ����Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
            if (pForm.getBack().equals("query")) {
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
                removeForm(mapping, request);
                return mapping.findForward("receivequery");
            }

        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }


    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            HashMap map = (HashMap) request.getAttribute("map");
            String from = (String) request.getAttribute("from");
            pForm.setBack(from);

            pForm.setCqbceList((ArrayList) map.get("cqbce"));
            pForm.setCqsyeList((ArrayList) map.get("cqsye"));
            pForm.setGfcjjgList((ArrayList) map.get("gfcjjg"));
            pForm.setGfsyeList((ArrayList) map.get("gfsye"));
            pForm.setNsrfgrdmList((ArrayList) map.get("nsrfgrdm"));
            pForm.setNsrfgrmcList((ArrayList) map.get("nsrfgrmc"));
            pForm.setNsrgrList((ArrayList) map.get("nsrgr"));
            pForm.setSpjeList((ArrayList) map.get("spje"));
            pForm.setSpyyList((ArrayList) map.get("spyy"));
            pForm.setSptsList((ArrayList) map.get("spts"));
            pForm.setFgrspList((ArrayList) map.get("fgrsp"));
            pForm.setTufwxxList((ArrayList) map.get("tufwxx"));
            pForm.setOkList((ArrayList) map.get("ok"));

            // ����Token;
            saveToken(request);

            // ת��ɹ���Ľ��档
            if (pForm.getOkList() == null || pForm.getOkList().size() == 0) {
                request.setAttribute(Constants.MESSAGE_KEY, "������֤��ɣ�û�����ݿ�������");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "������֤��ɣ���ʼ����");
            }

            return mapping.findForward("show");
        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }


    /**
     * �˳������¼
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
        try {
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // ����Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
            if (pForm.getBack().equals("query")) {
                // ת��ɹ���Ľ��档
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
                removeForm(mapping, request);
                return mapping.findForward("receivequery");
            }

        }

        catch (Exception ex) {
            removeForm(mapping, request);
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }


    /**
     * �����浼�������˳�
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        try {
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            ReceivePlslForm2 pForm = (ReceivePlslForm2) form;
            // ��Request�л�ȡ����
            ArrayList list = pForm.getOkList();
            String pch = new String();
            if (list != null && list.size() > 0) {
                PldrBo2 bo = (PldrBo2) list.get(0);
                pch = bo.getPch();
            }
            Object obj = pch;
            //����QsglProxy��check�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.DELETE_ALLDR);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            QsglProxy.getInstance().process(vo);
            // ����Token;
            saveToken(request);

            if (pForm.getBack().equals("dr")) {
                // ת��ɹ���Ľ��档
                request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
                removeForm(mapping, request);
                return mapping.findForward("receivedr");
            }
//             if (pForm.getBack().equals("query"))
//             {
//                 // ת��ɹ���Ľ��档
//                 request.setAttribute(Constants.MESSAGE_KEY, "������ɣ�");
//                 removeForm(mapping, request);
//                 return mapping.findForward("receivequery");
//             }

        }

        catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        return null;
    }

}
