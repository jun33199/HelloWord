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
 * 北京地税管理信息系统基本Action类。
 */
public class BaseAction extends Action {
    /**
     * 保存日志代码和日志数据的属性值对
     */
    static protected Properties logMap;

    /**
     * 日志代码与日志数据的对照文件名，该文件保存在ClassPath中。
     */
    static protected String LOG_MESSAGE_FILE = "qsgl_log-message.properties";

    private UserData userData = null;

    /**
     * 构造函数
     * 从配置文件中读取action与方法名的对照
     * actionMap = new Properties();
     * actionMap.load(this.getClass().getClassLoader().
     *                   getResourceAsStream(ACTION_MAP_FILE)) ;
     * 从配置文件中读取日志数据的对照
     * logMap = new Properties();
     * logMap.load(this.getClass().getClassLoader().
     *                   getResourceAsStream(LOG_MESSAGE_FILE)) ;
     *
     */
    public BaseAction() {
        Debug.out("into BaseAction : BaseAction() called ");
        try {
            // 从配置文件中读取日志数据的对照
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
     * 从Session中清除Form对象，释放相应资源。
     * if (mapping.getAttribute() != null)
     * {
     *    (request.getSession(false)).removeAttribute(mapping.getAttribute());
     * }
     *
     * @param mapping ActionMapping
     * @param request HttpServletRequest
     */
    protected void removeForm(ActionMapping mapping, HttpServletRequest request) {
        //从Session中清除Form对象，释放相应资源。
        if (mapping.getAttribute() != null) {
            (request.getSession(false)).removeAttribute(mapping.getAttribute());
        }
    }

    /**
     * 判断Token是否有效，无效返回无效Token页面。
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
        //判断Token是否有效，无效返回无效Token页面。
        if (!isTokenValid(request)) {
            return mapping.findForward("invalidToken");
        }
        return null;
    }

    /**
     *   //将业务权限的参数，利用Struts提供的工具类，添到form中。
     *     HttpSession session = httpRequest.getSession(false);
     *     UserData data = (UserData )session.getAttribute(SessionKey.User_Data)
     *     BeanUtils.populate(form,data.parameter);
     *
     *
     *   //获取action.
     *   String action = ((BaseForm)form).getAction();
     *
     *   //必要时记录日志
     *   String logAction = (mapping.getPath() + "." + action).subString(1);
     *   makeLog(userData,logAction);
     *
     *   //根据Form的action调用不同的方法。
     *   if (action == "") action = "Show";
     *   //获取方法名
     *   String methodName = actionMap.getProperty(action);
     *   //利用反射调用相应方法
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
            //取得session中的UserData对象
            HttpSession session = request.getSession(false);
            userData = (UserData) session.getAttribute(SessionKey.USER_DATA);

            //获取action.
            String operationType = request.getParameter("operationType");
            if ((operationType == null) || (operationType.equals(""))) {
                operationType = ((BaseForm) form).getOperationType();
            }

            //根据Form的action调用不同的方法。
            if (operationType.equals("")) {
                operationType = "Show";
            }
            if (operationType.equals("show")) {
                operationType = "Show";
            }
            //必要时记录日志
            String logAction = (mapping.getPath() + "." + operationType).
                               substring(1);

            makeLog(userData, logAction);

            Debug.out("In BaseAction perform action: " + operationType);
            //获取方法名
            String methodName = "do" + operationType;
            Debug.out("BaseAction perform methodName: " + methodName);
            //利用反射调用相应方法
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
     * 显示页面
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向show页面。
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
        //保存Token;
        saveToken(request);

        //转向show页面。
        return mapping.findForward("show");
    }

    /**
     * 取消当前操作
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向返回后的页面。
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
        // 删除form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);

        //转向返回后的页面。
        return mapping.findForward("return");
    }

    /**
     * 记录日志
     *
     * //获取日志操作名称
     * String logMsg = logMap.getProperties(action);
     * if (logMsg != null)
     * {
     *   //获取日志级别
     *   String logLevel = logMap.getProperties(action + ".Level");
     *   //构造LogData
     *   LogData logData = new LogData();
     *   logData.xxx = userData.xxx;
     *   logData.yxsj = new Date();
     *   logData.jbdm = logLevel;
     *   logData.czlx  = logMsg;
     *   logData.czms  = desc;
     *   logData.czjg  = result;
     *
     *   //通过LogBeanClient向消息Bean发送消息，记录日志
     *   LogBeanClient.ttftLog(logData);
     * }
     *
     * @param userData 总控数据
     * @param action 操作类型代码
     * @param desc 操作描述
     * @param result 操作结果
     */
    public void makeLog(UserData userData, String action,
                        String desc, String result) {
    }

    /**
     * 重定向页面。
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向返回后的页面。
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
        // 保存Token;
        saveToken(request);

        String subaction = request.getParameter("subAction");
        //转向返回后的页面。
        if ((subaction == null) || (subaction == "")) {
            return mapping.findForward("redirect");
        } else {
            return mapping.findForward(subaction);
        }
    }

    /**
     * 记录日志
     *
     * makeLog(userData,action,"","");
     *
     * @param userData 总控数据
     * @param action 操作类型
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
