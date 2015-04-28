package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
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
 * ���ݲ�ѯ����Action.
 * ����һ������ݲ�ѯά������
 * ����ʾ��ѯҳ�棬ִ�в�ѯ����ʾ����ҳ�棬
 *     ɾ������ʾ������ϸ��ȡ�����أ�
 * ����ģ�淽��ģʽ���ṩһ��Ĳ�ѯά�������еĿ��ơ�
 * ����Ĳ����ɶ������࣬������ʵ�־��巽����doXXX����
 * �������������������������ش˷����ṩ�����ѡ��
 */
public class QueryBaseAction extends BaseAction {
    private VOPackage vo = new VOPackage();

    /**
     * ��ʾ��ѯ����ҳ�档����Ӧ��Form��Ч��Ϊsession��
     * ȱʡ����һ���յ�Form.
     *   1. ���form.
     *       form.clear();
     *   2. ����Token;
     *       saveToken();
     *       return mapping.findForward("query") ;
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        //���form.
        QueryBaseForm queryForm = (QueryBaseForm) form;
        queryForm.clear();
        // ����Token;
        saveToken(request);
        return mapping.findForward("query");
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
     * ��ҳ����
     *
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ����Form��changPage�ӿ�ʵ�����ݵı任��
     *      queryForm.changePage();
     *  3. ����Token;
     *     saveToken();
     *     return mapping.findForward("query");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doChangePage(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        //�������е�Form����ΪQueryBaseForm��
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // ����Form��changPage�ӿ�ʵ�����ݵı任��
        queryForm.changePage();
        // ����Token;
        saveToken(request);
        return mapping.findForward("query");
    }

    /**
     * ȡ����ǰ��������Form�����Session��ȥ����
     * ת�򷵻غ�Ӧȥ��ҳ�档
     *
     *  1. ���ø��෽����Form�����Session��ȥ����
     *     removeForm(mapping,request);
     *  2. ����Token;
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
        //���ø��෽����Form�����Session��ȥ����
        removeForm(mapping, request);
        //����Token;
        saveToken(request);
        return mapping.findForward("return");
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
     *  3. ��ȡ���еļ�¼��
     *      ArrayList delList = queryForm.getAllResult();
     *  4. ����BaseProxy��delete����ɾ����
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      BaseProxy.delete(delList,userData);
     *  5. ɾ��Cache�еĽ����
     *      queryForm.removeAll();
     *  6. ת��ɾ����Ľ��ҳ�档
     *     return mapping.findForward("delete");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doDeleteAll(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

        // ת��ɾ����Ľ��ҳ�档
        return mapping.findForward("delete");
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
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // ��ȡѡ�еļ�¼��
            ArrayList delList = queryForm.getSelectedData();
            Debug.out("delList size: " + delList.size());
            // ����BaseProxy��delete����ɾ����
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

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
     * ͨ���� struts-confil.xml �����ã�ת��AddXXXXAction�н��д���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doAdd(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);
        return mapping.findForward("add");
    }
}
