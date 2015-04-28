package com.creationstar.bjtax.qsgl.VisionLogic.action;


import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbzbForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo;
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

public class QuerySbzbAction extends QueryBaseAction {
    private VOPackage vo = new VOPackage();

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QuerySbzbForm querySbzbForm = new QuerySbzbForm();
        session.setAttribute(mapping.getName(), querySbzbForm);
        VOPackage vo = new VOPackage();
        request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣ��ʾ�ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            QuerySbzbForm querySbzbForm = (QuerySbzbForm) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = querySbzbForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.QUERY_SBZT);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            System.out.println("obj.getClass().getName():" +
                               obj.getClass().getName());
            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            querySbzbForm.setQueryCache(cache);
            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            querySbzbForm.setStatus("Result");
            Debug.out("querySbzbForm.status: " + querySbzbForm.getStatus());

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }

            // ����Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�ɹ���");
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
            ArrayList sbbh = new ArrayList();
            int size = delList.size();
            for (int i = 0; i < size; i++) {
                QuerySbzbBo bo = (QuerySbzbBo) delList.get(i);
                sbbh.add(bo.getSbbh());
            }

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
            vo.setData(sbbh);
            vo.setUserData(userData);
            vo.setProcessor(prop.getProperty(delList.get(0).getClass().getName()));

            QsglProxy.getInstance().process(vo);
//            QsglProxy.getInstance().delete(delList, userData);
            // ɾ��Cache�еĽ����
            queryForm.removeSelectedData(delList);
            // ת��ɾ����Ľ��ҳ�档
            request.setAttribute(Constants.MESSAGE_KEY, "ɾ���ɹ���");
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


}
