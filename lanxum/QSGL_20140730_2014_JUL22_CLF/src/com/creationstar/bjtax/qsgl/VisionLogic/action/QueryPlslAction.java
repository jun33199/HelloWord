package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.exceptions.TtsoftMessage;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
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
 */
public class QueryPlslAction extends QueryBaseAction {


    /**
     * ��ʱ����--��ѯ�걨�����Ϣ
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
        QueryPlslForm aForm = (QueryPlslForm) form;
        VOPackage vo = new VOPackage();

        try {
            //��ȡ�����ServletContext�еĴ���������
            aForm.setJkfsList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.JSFS));
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
        request.setAttribute(Constants.MESSAGE_KEY, "��ʾ�ɹ�,ֻ�ܶ�δ�������ݽ���ɾ�������������");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
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
            QueryPlslForm queryForm = (QueryPlslForm) form;
            // ��ȡѡ�еļ�¼��
            ArrayList list = queryForm.getSelectedData();
            ArrayList inputList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    PlsbBo bo = (PlsbBo) list.get(i);
                    Drzb dz = bo.getDrzb();
                    PldrBo pdbo = new PldrBo();
                    pdbo = (PldrBo) QsglPcclXmlUtil.getRecord(dz.getDrsjnr());
                    pdbo.setPch(dz.getDrpch());
                    pdbo.setXh(dz.getXh());
                    inputList.add(pdbo);
                }
            }
            //����QsglProxy��check����,���
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage ,����
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.CHECK);
            vo.setProcessor(prop.getProperty(PldrBo.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(inputList);

            HashMap errMap = (HashMap) QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "������֤��ɣ�");
            request.setAttribute("map", errMap);
            request.setAttribute("from", "query");
            queryForm.removeSelectedData(list);
            // ����Token;
            saveToken(request);
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

    /**
     * ִ�в�ѯ
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ��QueryBaseForm�л�ȡ��ѯ������
     *      Object obj = queryForm.getData();
     *  3. ����BaseProxy��query��������ѯ���ݡ�
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      ArrayList list = BaseProxy.query(obj,userData);
     *  4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
     *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
     *      queryForm.setQueryCache(cache);
     *  5. ����Token;
     *       saveToken();
     *  6. ת��ɹ���Ľ��档
     *     return mapping.findForward("query");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);
            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }
            if (list.size() > 200) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�������200��,�뾫ȷ��ѯ����,ֻ�ܶ�δ�������ݽ���ɾ�������������");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�ɹ�,ֻ�ܶ�δ�������ݽ���ɾ�������������");
            }

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("query");
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("query");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("query");
        }
    }


    /**
     * ִ�в�ѯ
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ��QueryBaseForm�л�ȡ��ѯ������
     *      Object obj = queryForm.getData();
     *  3. ����BaseProxy��query��������ѯ���ݡ�
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      ArrayList list = BaseProxy.query(obj,userData);
     *  4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
     *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
     *      queryForm.setQueryCache(cache);
     *  5. ����Token;
     *       saveToken();
     *  6. ת��ɹ���Ľ��档
     *     return mapping.findForward("query");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doQueryCurrentPage(ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);

            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }
            if (list.size() > 200) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�������200��,�뾫ȷ��ѯ����,ֻ�ܶ�δ�������ݽ���ɾ�������������");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�ɹ�,ֻ�ܶ�δ�������ݽ���ɾ�������������");
            }

            //���õ�ǰҳ��ҳ����ת����ҳ
            return doChangePage(mapping, form, request, response);
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("query");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("query");
        }
    }

}
