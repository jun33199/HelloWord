package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * �����޸Ļ���Action��.
 * ����һ��������޸����񣨱��棬ȡ�����أ�
 * ����ģ�淽��ģʽ���ṩһ����޸������еĿ��ơ�
 * ����Ĳ����ɶ������࣬������ʵ�־��巽����doXXX����
 * �������������������������ش˷����ṩ�����ѡ��
 */
public class ModifyBaseAction extends BaseAction {
    /**
     * �����¼
     *
     * 1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. ��Form�л�ȡ����
     *     Object obj = ((QueryBaseForm)form).getModifiedData();
     * 3. ����BaseProxy��add�������޸�һ����¼��
     *       HttpSession session = request.getSession();
     *       UserData userData =
     *           (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.modify(obj,userData);
     * 4. ����Form�еĽ������
     *      ((QueryBaseForm)form).modifyData();
     * 5. ����Token;
     *       saveToken();
     * 6. ת��ɹ���Ľ��档
     *     return mapping.findForward("modify");
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
            Object obj = ((QueryBaseForm) form).getModifiedData();

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

            //����QsglProxy��modify�������޸�һ����¼��
            /** @todo ���뷵��ֵ���жϣ��Ը���ҳ���Ƿ�ɹ� */
            QsglProxy.getInstance().process(vo);
            ((QueryBaseForm) form).modifyData(obj);

            // ����
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("modify");
        } catch (BaseException te) {
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            return mapping.findForward("show");
        }
    }

    /**
     * ȡ����ǰ����
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *  1. ����Token;
     *       saveToken();
     *  2.  ת��Viewҳ�档
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
        //����Token;
        saveToken(request);
        //  ת��Viewҳ�档
        return mapping.findForward("return");
    }

    /**
     * ת���޸�ҳ��
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ����viewForm��
     *      queryForm.createViewForm();
     *  3. ����Token;
     *       saveToken();
     *  4. ת����ʾ�޸ĵĽ��档
     *         return mapping.findForward("show") ;
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
        // �������е�Form����ΪQueryBaseForm��
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // ����viewForm��
        queryForm.createViewForm();
        //. ����Token;
        saveToken(request);
        // ת����ʾ�޸ĵĽ��档
        return mapping.findForward("show");
    }
}
