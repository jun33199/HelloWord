package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * �������ӻ���Action.
 * ����һ�����������������ʾ����ҳ�棬���棬ȡ�����أ�
 * ����ģ�淽��ģʽ���ṩһ������������еĿ��ơ�
 * ����Ĳ����ɶ������࣬������ʵ�־��巽����doXXX����
 * �������������������������ش˷����ṩ�����ѡ��
 */
public class AddBaseAction extends BaseAction {

    public AddBaseAction() {

    }

    /**
     * ��ʾ����ҳ�档����Ӧ��Form��Ч��Ϊrequest��
     *   1. form.reset();
     *   2. ����Token;
     *       saveToken();
     *   3. ȱʡ����һ���յ�Form.
     *       return mapping.findForward("show") ;
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
        //��ʾ����ҳ��
        form.reset(mapping, request);
        Debug.out(form.getClass().getName());
        BaseForm baseForm = (BaseForm) form;
        baseForm.clear();
        // ����Token;
        saveToken(request);
        //ȱʡ����һ���յ�Form.
        return mapping.findForward("show");
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
            // ��Form�л�ȡ����
            Object obj = ((BaseForm) form).getData();
            Debug.out("AddBaseAction doAdd form.getData() obj.class is " +
                      obj.getClass().getName());
            //����QsglProxy��add�����������ݿ�������һ����¼��
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

            /** @todo ���뷵��ֵ���жϣ��Ը���ҳ���Ƿ�ɹ� */
            QsglProxy.getInstance().process(vo);
            // ����Token;
            saveToken(request);
            // ���form ����
            ((BaseForm) form).clear();
            // ת��ɹ���Ľ��档
            return mapping.findForward("add");
        } catch (BaseException te) {
            saveToken(request);
            return new ActionForward(mapping.getInput());
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
    }

    /**
     * ȡ����ǰ������
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *
     *  1. ����Token;
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
        // ɾ��session�е�form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);
        return mapping.findForward("return");
    }
}
