package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.creationstar.bjtax.qsgl.util.StringUtils;
import com.ttsoft.common.exceptions.TtsoftMessage;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.QueryArrayList;
import com.ttsoft.common.util.QueryCache;
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
public class PlslAction2 extends QueryBaseAction {
    /**
     * ��ʱ����--��ѯ�걨�����Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * TODO �������������ReceviePlslAction�����ص����ݣ�һ��list���������list����������ˡ�
     *
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            QueryPlslForm2 queryForm = (QueryPlslForm2) form;
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            Connection conn = null;
            boolean flag;
            //����ӵ�����
            ArrayList newdata = new ArrayList();
            //�Ѿ����ڵľ�����
            ArrayList olddata = new ArrayList();

            //��ȡ����
            ArrayList list = new ArrayList();
            list = queryForm.getDrsj();
            if (list == null || list.size() == 0) {
                request.setAttribute(Constants.MESSAGE_KEY, "û�е������ݣ�");
                return mapping.findForward("back");
            }
            //��ѯ�Ѿ�������
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Drzb zb = (Drzb) list.get(i);
                    try {
                        conn = QSDBUtil.getConnection();
                        //���˵����
                        if (zb.getNsrjsjdm() == null) {
                            //����ʱ��ȡ֤�����͡�֤������
                            PldrBo bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.
                                    getDrsjnr());
                            Grxx gr = bo.getGrxx();
                            flag = DAOFactory.getInstance().getDrzbDAO().
                                   querydrGrxx2(zb.getFdcxmmc(), zb.getFdcdz(),
                                                gr.getSfzjlx(), gr.getSfzjhm(),
                                                gr.getNsrmc(), conn);
                            //�Ǹ���
                        } else {
                            flag = DAOFactory.getInstance().getDrzbDAO().
                                   querydrFgrxx2(zb.getFdcxmmc(), zb.getFdcdz(),
                                                 zb.getNsrjsjdm(), zb.getNsrmc(),
                                                 conn);
                        }

                        if (!flag) {
                            newdata.add(zb);
                        } else {
                            olddata.add(zb);
                        }
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        QSDBUtil.freeConnection(conn);
                    }
                }
            }
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            QueryCache cache = new QueryCache(newdata, userData.myxszds);
            queryForm.setQueryCache(cache);
            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");
            queryForm.setSize(olddata.size());
            //������
            queryForm.setDrsj(newdata);
            //�������������
            queryForm.setResultList(olddata);
            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }
    }

    /**
     * ��ѯ--��ʾ��ϸ��Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doView(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // ����Token;
        saveToken(request);
        QueryPlslForm2 aForm = (QueryPlslForm2) form;
        request.setAttribute(Constants.MESSAGE_KEY, "��ϸҳ����ʾ�ɹ���");
        if (aForm.getDrlx().equals("0")) {
            //��ʾ�����µ��������
            return mapping.findForward("view");
        } else {
            //��ʾ�����ظ����������
            return mapping.findForward("viewOld");
        }
    }

    /**
     * �����¼
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doCheck(ActionMapping mapping,
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
            QueryPlslForm2 queryForm = (QueryPlslForm2) form;
            ArrayList list = queryForm.getDrsj();
            ArrayList olddat = queryForm.getResultList();

            String selstr = queryForm.getSelcfdr();
            //���¹�������
            if (StringUtils.killNull2(selstr) != null) {
                if (selstr.length() >= 2) {
                    selstr = selstr.substring(0, selstr.length() - 1);
                    String[] arr = selstr.split(",");
                    if (arr.length > 0) {
                        for (int i = 0; i < arr.length; i++) {
                            Drzb zb = (Drzb) olddat.get(Integer.parseInt(arr[i]));
                            list.add(zb);
                        }
                    }
                }
            }
            ArrayList inputList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Drzb dz = (Drzb) list.get(i);
                    PldrBo2 bo = new PldrBo2();
                    bo = (PldrBo2) QsglPcclXmlUtil.getRecord2(dz.getDrsjnr());
                    bo.setPch(dz.getDrpch());
                    bo.setXh(dz.getXh());
                    inputList.add(bo);
                }
            }
            //����QsglProxy��check�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.CHECK);
            vo.setProcessor(prop.getProperty(PldrBo2.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(inputList);

            HashMap errMap = (HashMap) QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);
            request.setAttribute("map", errMap);
            request.setAttribute("from", "dr");
            // ɾ��session�е�form
            removeForm(mapping, request);
            // ת��ɹ���Ľ��档
            request.setAttribute(Constants.MESSAGE_KEY, "������֤��ɣ�");
            return mapping.findForward("receive");
        } catch (BaseException te) {
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
