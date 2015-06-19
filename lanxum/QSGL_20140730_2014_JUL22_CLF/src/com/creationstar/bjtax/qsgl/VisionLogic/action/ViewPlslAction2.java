package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PlslForm2;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.Dom4jXMLTool;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
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
 *
 * ˵������action�����ҳ����"��������(˰����Ա)"ģ��
 */
public class ViewPlslAction2 extends BaseAction {
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm actionform,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");

        // aForm.createViewForm();
        String sfesf = request.getParameter("sfesf");
        String setz = request.getParameter("setz");

        /*
                 System.out.println("///////////////////////////");
                 System.out.println("��������:"+aForm.getDrlx());
                 System.out.println("�µ��������:"+aForm.getDrsj().size());
                 System.out.println("�ظ����������:"+aForm.getResultList().size());
                 System.out.println("˰�������"+setz);
                 System.out.println("�Ƿ���ַ���"+sfesf);
                 System.out.println("///////////////////////////");
         */

        if (sfesf == null || sfesf.equals("")) {
            sfesf = "00";
        }
        Drzb zb = null;
        //0:�µ���1���ظ�����
        if (aForm.getDrlx().equals("0")) {
            aForm.createViewForm();
            zb = (Drzb) aForm.getViewDataDetail();
        } else {
            ArrayList list = aForm.getResultList();
            zb = (Drzb) list.get(aForm.getViewIndex());
        }
        Dom4jXMLTool xmlTool = new Dom4jXMLTool();
        xmlTool.openXML(zb.getDrsjnr());
        String[] attrname = {"fieldName", "maxLength"};
        String[] attrvalue1 = {"SFESF", "20"};
        String[] attrvalue2 = {"SETZ", "20"};
        xmlTool.appendFinalNode("//row[@tableName='QSDB.QS_JL_TUFWXX']",
                                "Field", attrname, attrvalue1, sfesf);
        xmlTool.appendFinalNode("//row[@tableName='QSDB.QS_JL_TUFWXX']",
                                "Field", attrname, attrvalue2, setz);
        //System.out.println(xmlTool.toXMLString());
        zb.setDrsjnr(xmlTool.toXMLString());

        session.setAttribute("queryPlslForm2", aForm);
        return mapping.findForward("Save");
    }


    /**
     * ��������--�쿴������ϸ��Ϣ(��ʾ�ظ����������)
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO C ���ӱ����޸ĵ�����Ϣ�ķ�����������Ϣ�޸ĺ󣬷��غ�̨����̨��÷��ص����ݣ����º��޸�QueryPlslForm�еĶ�Ӧ�Ļ������ݣ�
     * �����ص�plslDetail.jsp plslDetail.jsp ҳ���ж����ÿһ���ֶ���PlslForm�о���Ҫ���¶���
     */
    public ActionForward doShowPage(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        PlslForm2 pform = (PlslForm2) form;
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");
        /*
                         System.out.println("///////////////////////////");
                         System.out.println("��������:"+aForm.getDrlx());
                         System.out.println("�µ��������:"+aForm.getDrsj().size());
         System.out.println("�ظ����������:"+aForm.getResultList().size());
                         System.out.println("///////////////////////////");
         */
        //aForm.createViewForm();
        //pform = (PlslForm) aForm.getViewForm();

        //����Ҫ��ʾ����Ϣ
        ArrayList list = aForm.getResultList();
        Drzb zb = (Drzb) list.get(aForm.getViewIndex());
        PldrBo bo = new PldrBo();
        try {
            bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.getDrsjnr());
        } catch (Exception ex) {
            Debug.out("queryPlslForm�л�ȡviewformʱ����xml����");
            ex.printStackTrace();
        }

        pform.setCqxxList(bo.getCqxxList());
        //�Ǹ��˶��Ȩ��
        //bo.getFgrxx().setNsrmc(bo.getFgrnsrmc());
        //bo.getFgrxx().setJsjdm(bo.getFgrjsjdm());
        pform.setFgrxx(bo.getFgrxx());

        pform.setFwjhxxList(bo.getFwjhxxList());
        //���˶��Ȩ��ʱƴ��
        bo.getGrxx().setNsrmc(bo.getNsrmc());
        bo.getGrxx().setSfzjhm(bo.getZjhm());
        bo.getGrxx().setLxdh(bo.getLxdh());
        pform.setGrxx(bo.getGrxx());

        pform.setGyzfxxList(bo.getGyzfxxList());
        pform.setSpjgxx(bo.getSpjgxx());
        pform.setTufwxx(bo.getTufwxx());
        //˰���������
        pform.setSetz(bo.getSetz());
        //System.out.println("˰���������:" + bo.getSetz());
        if (bo.getGrxx() != null) {
            //����
            pform.setPerson("1");
        } else {
            //�Ǹ���
            pform.setPerson("0");
        }
        if (bo.getSpjgxx() != null) {
            //������
            pform.setSp("1");
        } else {
            //������
            pform.setSp("0");
        }
        if (bo.getCqxxList() != null && bo.getCqxxList().size() > 0) {
            //�в�Ǩ
            pform.setCq("1");
        } else {
            //�޲�Ǩ
            pform.setCq("0");
        }
        if (bo.getGyzfxxList() != null && bo.getGyzfxxList().size() > 0) {
            //�й���
            pform.setGf("1");
        } else {
            //�޹���
            pform.setGf("0");
        }
        if (bo.getFwjhxxList() != null && bo.getFwjhxxList().size() > 0) {
            //�з���
            pform.setFj("1");
        } else {
            //�޷���
            pform.setFj("0");
        }
        if (bo.getTufwxx().getFwtdbmdm() != null &&
            !bo.getTufwxx().getFwtdbmdm().equals("")) {
            pform.setTdbm("1");
        } else {
            pform.setTdbm("0");
        }
        pform.setBack(aForm.getFrom());
        if (aForm.getFrom().equals("dr")) {
            pform.setDel("0");
        }
        if (aForm.getFrom().equals("cx") && aForm.getSl().equals("unreceived")) {
            pform.setDel("1");
        }

        request.setAttribute("plslForm", pform);
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��ϸ��Ϣ��ʾ�ɹ���");
        return mapping.findForward("show");

    }


    /**
     * ��������--�쿴������ϸ��Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO C ���ӱ����޸ĵ�����Ϣ�ķ�����������Ϣ�޸ĺ󣬷��غ�̨����̨��÷��ص����ݣ����º��޸�QueryPlslForm�еĶ�Ӧ�Ļ������ݣ�
     * �����ص�plslDetail.jsp plslDetail.jsp ҳ���ж����ÿһ���ֶ���PlslForm�о���Ҫ���¶���
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        PlslForm2 pform = (PlslForm2) form;
        QueryPlslForm2 aForm = (QueryPlslForm2) session.getAttribute(
                "queryPlslForm2");
        aForm.createViewForm();
        pform = (PlslForm2) aForm.getViewForm();
        ArrayList list = pform.getGrxxList();
        //���˶��Ȩ�˵����
        if (list.size() > 1) {
            Grxx grxx = null;
            String nsrmc = "";
            String zjhm = "";
            String lxdh = "";
            for (int i = 0; i < list.size(); i++) {
                grxx = (Grxx) list.get(i);
                if (nsrmc.length() > 2) {
                    nsrmc = nsrmc + "," + grxx.getNsrmc();
                } else {
                    nsrmc = nsrmc + grxx.getNsrmc();
                }
                if (zjhm.length() > 2) {
                    zjhm = zjhm + "," + grxx.getSfzjhm();
                } else {
                    zjhm = zjhm + grxx.getSfzjhm();
                }
                if (lxdh.length() > 2) {
                    lxdh = lxdh + "," + grxx.getLxdh();
                } else {
                    lxdh = lxdh + grxx.getLxdh();
                }
            }
            pform.getGrxx().setNsrmc(nsrmc);
            pform.getGrxx().setSfzjhm(zjhm);
            pform.getGrxx().setLxdh(lxdh);
        }
        pform.setBack(aForm.getFrom());
        if (aForm.getFrom().equals("dr")) {
            pform.setDel("0");

        }
        if (aForm.getFrom().equals("cx") && aForm.getSl().equals("unreceived")) {
            pform.setDel("1");
        }
        request.setAttribute("plslForm2", pform);
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "��ϸ��Ϣ��ʾ�ɹ���");
        return mapping.findForward("show");

    }


    /**
     * ɾ��ѡ�еļ�¼
     *
     *  1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     *  2. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  3. ��ȡѡ�еļ�¼��
     *      ArrayList delList = queryForm.getSelectedData();
     *  4. ����BaseProxy��delete����ɾ����
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      BaseProxy.delete(delList,userData);
     *  5. ɾ��Cache�еĽ����
     *      queryForm.removeSeletedData(delList);
     *  6. ת��ɾ����Ľ��ҳ�档
     *     return mapping.findForward("delete");
     *
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
            HttpSession session = request.getSession(false);
            QueryPlslForm2 queryForm = (QueryPlslForm2) session.getAttribute(
                    "queryPlslForm2");

            // �������е�Form����ΪQueryBaseForm��
            PlslForm2 pForm = (PlslForm2) form;
            // ��ȡѡ�еļ�¼��
            ArrayList delList = new ArrayList();
            delList.add(queryForm.getPlBo());
            Debug.out("delList size: " + delList.size());
            // ����BaseProxy��delete����ɾ����
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);
            VOPackage vo = new VOPackage();
            //�������̨�����Vopackage����
            vo.setAction(ActionType.DELETE);
            vo.setData(delList);
            vo.setUserData(userData);
            vo.setProcessor(prop.getProperty(delList.get(0).getClass().getName()));

            QsglProxy.getInstance().process(vo);
//            QsglProxy.getInstance().delete(delList, userData);
            // ɾ��Cache�еĽ����
            queryForm.removeSelectedData(delList);
            // ת��ɾ����Ľ��ҳ�档
            return mapping.findForward("delete");
        } catch (BaseException te) {
            request.setAttribute(RequestKey.MESSAGE_KEY, te.getMessage());
            saveToken(request);
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
    }


    /**
     * ȡ����ǰ����
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ����Token;
     *       saveToken();
     *  2.  ת�򷵻غ��ҳ�档
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturncx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ɾ��form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);

        //ת�򷵻غ��ҳ�档
        return mapping.findForward("returncx");
    }
}
