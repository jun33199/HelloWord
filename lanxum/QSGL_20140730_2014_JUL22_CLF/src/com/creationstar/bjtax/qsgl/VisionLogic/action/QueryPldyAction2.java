package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PldyForm2;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
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
 *
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
public class QueryPldyAction2 extends QueryBaseAction {
    private VOPackage vo = new VOPackage();
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            PldyForm2 queryForm = (PldyForm2) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            // �Ѳ�ѯ��������session�й�����ʱʹ��
            queryForm.setQueryObj(obj);
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
            request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�ɹ���");
            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("show");
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }
    }

    public ActionForward doView(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            // �������е�Form����ΪPldyForm��
            PldyForm2 queryForm = (PldyForm2) form;
            QueryCache dyCache = queryForm.getQueryCache();
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession(false);
            // �Ѳ�ѯ���Cache����form����ʱ�ֶ��Ա㷵��ʱʹ��
            queryForm.setTmpQyeryCache(dyCache);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) session.getServletContext().
                              getAttribute(Constants.
                                           PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.GET);
            vo.setUserData(userData);
            vo.setData(obj);

            System.out.println("####################" + obj.getClass().getName());

            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����ѯ���������form����ӡʹ��
            queryForm.setResultList(list);
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
            request.setAttribute(Constants.MESSAGE_KEY, "��ʾ��ϸ�ɹ���");
            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("detail");
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }
    }

    public ActionForward doBack(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        Debug.out("come in============================");
        // �����ѯ�����ͽ����
        PldyForm2 dyForm = (PldyForm2) form;
        PldyBo2 pldyBo = (PldyBo2) dyForm.getQueryObj();
        dyForm.setDrsjBegin(pldyBo.getDrsjBegin());
        dyForm.setDrsjEnd(pldyBo.getDrsjEnd());
        dyForm.setPch(pldyBo.getPch());
        dyForm.setTgzgjmc(pldyBo.getTgzgjmc());
        dyForm.setTgzjsjdm(pldyBo.getTgzjsjdm());
        dyForm.setQueryCache(dyForm.getTmpQyeryCache());
        //����Token;
        saveToken(request);
        return mapping.findForward("back");
    }

    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        PldyForm2 pldyForm = (PldyForm2) form;
        // ��Ҫ������xml�ļ�����
        String xmlContent = "";
        try {
            xmlContent = QsglPcclXmlUtil.createXML2(pldyForm.getResultList());
            String fname = pldyForm.getPch(); //Ҫ���ص����ص��ļ���
            OutputStream os = response.getOutputStream(); //ȡ�������
            response.reset(); //��������
            response.setHeader("Content-disposition",
                               "attachment; filename=" + fname + ".xml"); //�趨����ļ�ͷ
            response.setContentType("application/xml;charset=GB2312"); //�����������
            //���������д��string�ַ���
            os.write(xmlContent.getBytes());
            os.flush();
            os.close();

            // ���±���������״̬
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = pldyForm.getData();
            HttpSession session = request.getSession(false);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) session.getServletContext().
                              getAttribute(Constants.
                                           PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            vo.setAction(ActionType.UPDATE);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "������ӡ�ļ��ɹ���");
            return mapping.findForward(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            request.setAttribute(Constants.MESSAGE_KEY, "������ӡ�ļ�ʧ�ܣ�");
            return mapping.findForward("detail");
        }
    }

    public ActionForward doChangePageDetail(ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        //�������е�Form����ΪQueryBaseForm��
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // ����Form��changPage�ӿ�ʵ�����ݵı任��
        queryForm.changePage();
        // ����Token;
        saveToken(request);
        return mapping.findForward("detail");
    }

}
