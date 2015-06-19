package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * ������˰������Ϣϵͳ����Action�ࡣ
 */
public class BaseAction extends Action {
    /**
     * ������־�������־���ݵ�����ֵ��
     */
    static protected Properties logMap;

    /**
     * ��־��������־���ݵĶ����ļ��������ļ�������ClassPath�С�
     */
    static protected String LOG_MESSAGE_FILE = "qsgl_log-message.properties";

    private UserData userData = null;

    /**
     * ���캯��
     * �������ļ��ж�ȡaction�뷽�����Ķ���
     * actionMap = new Properties();
     * actionMap.load(this.getClass().getClassLoader().
     *                   getResourceAsStream(ACTION_MAP_FILE)) ;
     * �������ļ��ж�ȡ��־���ݵĶ���
     * logMap = new Properties();
     * logMap.load(this.getClass().getClassLoader().
     *                   getResourceAsStream(LOG_MESSAGE_FILE)) ;
     *
     */
    public BaseAction() {
        Debug.out("into BaseAction : BaseAction() called ");
        try {
            // �������ļ��ж�ȡ��־���ݵĶ���
            if (logMap == null) {
                logMap = new Properties();
                logMap.load(this.getClass().getClassLoader().
                            getResourceAsStream(LOG_MESSAGE_FILE));

                Debug.out("init logMap");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ��Session�����Form�����ͷ���Ӧ��Դ��
     * if (mapping.getAttribute() != null)
     * {
     *    (request.getSession(false)).removeAttribute(mapping.getAttribute());
     * }
     *
     * @param mapping ActionMapping
     * @param request HttpServletRequest
     */
    protected void removeForm(ActionMapping mapping, HttpServletRequest request) {
        //��Session�����Form�����ͷ���Ӧ��Դ��
        if (mapping.getAttribute() != null) {
            (request.getSession(false)).removeAttribute(mapping.getAttribute());
        }
    }

    /**
     * �ж�Token�Ƿ���Ч����Ч������ЧTokenҳ�档
     *
     *     if (!isTokenValid(request))
     *     {
     *         return mapping.findForward("invalidToken");
     *     }
     *     return null;
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @return ActionForward
     */
    protected ActionForward doHandleToken(ActionMapping mapping,
                                          HttpServletRequest request) {
        //�ж�Token�Ƿ���Ч����Ч������ЧTokenҳ�档
        if (!isTokenValid(request)) {
            return mapping.findForward("invalidToken");
        }
        return null;
    }

    /**
     *   //��ҵ��Ȩ�޵Ĳ���������Struts�ṩ�Ĺ����࣬��form�С�
     *     HttpSession session = httpRequest.getSession(false);
     *     UserData data = (UserData )session.getAttribute(SessionKey.User_Data)
     *     BeanUtils.populate(form,data.parameter);
     *
     *
     *   //��ȡaction.
     *   String action = ((BaseForm)form).getAction();
     *
     *   //��Ҫʱ��¼��־
     *   String logAction = (mapping.getPath() + "." + action).subString(1);
     *   makeLog(userData,logAction);
     *
     *   //����Form��action���ò�ͬ�ķ�����
     *   if (action == "") action = "Show";
     *   //��ȡ������
     *   String methodName = actionMap.getProperty(action);
     *   //���÷��������Ӧ����
     *   Class[] param = {
     *                 Class.forName("org.apache.struts.action.ActionMapping"),
     *                 Class.forName("org.apache.struts.action.ActionForm"),
     *                 Class.forName("javax.servlet.http.HttpServletRequest"),
     *                 Class.forName("javax.servlet.http.HttpServletResponse")};
     *   Object[] obj = {mapping,form,request,response};
     *   Method method = this.getClass().getMethod(methodName,param);
     *   return (ActionForward)method.invoke(this,obj) ;
     *
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @return ActionForward
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            IOException, ServletException {
        try {
            //ȡ��session�е�UserData����
            HttpSession session = request.getSession(false);
            userData = (UserData) session.getAttribute(SessionKey.USER_DATA);

            //��ȡaction.
            String operationType = request.getParameter("operationType");
            if ((operationType == null) || (operationType.equals(""))) {
                operationType = ((BaseForm) form).getOperationType();
            }

            //����Form��action���ò�ͬ�ķ�����
            if (operationType.equals("")) {
                operationType = "Show";
            }
            if (operationType.equals("show")) {
                operationType = "Show";
            }
            //��Ҫʱ��¼��־
            String logAction = (mapping.getPath() + "." + operationType).
                               substring(1);

            makeLog(userData, logAction);

            Debug.out("In BaseAction perform action: " + operationType);
            //��ȡ������
            String methodName = "do" + operationType;
            Debug.out("BaseAction perform methodName: " + methodName);
            //���÷��������Ӧ����
            Class[] param = {
                    Class.forName("org.apache.struts.action.ActionMapping"),
                    Class.forName("org.apache.struts.action.ActionForm"),
                    Class.forName("javax.servlet.http.HttpServletRequest"),
                    Class.forName("javax.servlet.http.HttpServletResponse")};
            Object[] obj = {
                    mapping, form, request, response};
            Method method = this.getClass().getMethod(methodName, param);
            Debug.out("action class name: " + this.getClass().getName());
            return (ActionForward) method.invoke(this, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return (mapping.findForward("failure"));
        }
    }

    /**
     * ��ʾҳ��
     *
     *  1. ����Token;
     *       saveToken();
     *  2.  ת��showҳ�档
     *     return mapping.findForward("show");
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
        //����Token;
        saveToken(request);

        //ת��showҳ�档
        return mapping.findForward("show");
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
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // ɾ��form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);

        //ת�򷵻غ��ҳ�档
        return mapping.findForward("return");
    }

    /**
     * ��¼��־
     *
     * //��ȡ��־��������
     * String logMsg = logMap.getProperties(action);
     * if (logMsg != null)
     * {
     *   //��ȡ��־����
     *   String logLevel = logMap.getProperties(action + ".Level");
     *   //����LogData
     *   LogData logData = new LogData();
     *   logData.xxx = userData.xxx;
     *   logData.yxsj = new Date();
     *   logData.jbdm = logLevel;
     *   logData.czlx  = logMsg;
     *   logData.czms  = desc;
     *   logData.czjg  = result;
     *
     *   //ͨ��LogBeanClient����ϢBean������Ϣ����¼��־
     *   LogBeanClient.ttftLog(logData);
     * }
     *
     * @param userData �ܿ�����
     * @param action �������ʹ���
     * @param desc ��������
     * @param result �������
     */
    public void makeLog(UserData userData, String action,
                        String desc, String result) {
    }

    /**
     * �ض���ҳ�档
     *
     *  1. ����Token;
     *       saveToken();
     *  2.  ת�򷵻غ��ҳ�档
     *     return mapping.findForward("redirect");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doRedirect(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        // ����Token;
        saveToken(request);

        String subaction = request.getParameter("subAction");
        //ת�򷵻غ��ҳ�档
        if ((subaction == null) || (subaction == "")) {
            return mapping.findForward("redirect");
        } else {
            return mapping.findForward(subaction);
        }
    }

    /**
     * ��¼��־
     *
     * makeLog(userData,action,"","");
     *
     * @param userData �ܿ�����
     * @param action ��������
     */
    public void makeLog(UserData userData, String action) {
        makeLog(userData, action, "", "");
    }

    private void jbInit() throws Exception {
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
