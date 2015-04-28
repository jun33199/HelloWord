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
 * 数据查询基本Action.
 * 控制一般的数据查询维护事务
 * （显示查询页面，执行查询，显示增加页面，
 *     删除，显示数据详细，取消返回）
 * 采用模版方法模式。提供一般的查询维护事务中的控制。
 * 特殊的操作可定义子类，由子类实现具体方法（doXXX）。
 * 更特殊的增加事务可由子类重载此方法提供更多的选择。
 */
public class QueryBaseAction extends BaseAction {
    private VOPackage vo = new VOPackage();

    /**
     * 显示查询条件页面。（对应的Form有效期为session）
     * 缺省返回一个空的Form.
     *   1. 清空form.
     *       form.clear();
     *   2. 保存Token;
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
        //清空form.
        QueryBaseForm queryForm = (QueryBaseForm) form;
        queryForm.clear();
        // 保存Token;
        saveToken(request);
        return mapping.findForward("query");
    }

    /**
     * 执行查询
     *  1. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. 从QueryBaseForm中获取查询条件。
     *      Object obj = queryForm.getData();
     *  3. 调用BaseProxy的query方法，查询数据。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      ArrayList list = BaseProxy.query(obj,userData);
     *  4. 将结果构造成通用的QueryCache对象，以便翻页使用。
     *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
     *      queryForm.setQueryCache(cache);
     *  5. 保存Token;
     *       saveToken();
     *  6. 转向成功后的界面。
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
            // 将参数中的Form构型为QueryBaseForm。
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // 将结果构造成通用的QueryCache对象，以便翻页使用。
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);
            //将页面显示状态设定为显示查询结果
            queryForm.setStatus("Result");

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
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
     * 翻页操作
     *
     *  1. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. 调用Form的changPage接口实现数据的变换。
     *      queryForm.changePage();
     *  3. 保存Token;
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
        //将参数中的Form构型为QueryBaseForm。
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // 调用Form的changPage接口实现数据的变换。
        queryForm.changePage();
        // 保存Token;
        saveToken(request);
        return mapping.findForward("query");
    }

    /**
     * 取消当前操作，将Form对象从Session中去掉。
     * 转向返回后应去的页面。
     *
     *  1. 调用父类方法将Form对象从Session中去掉。
     *     removeForm(mapping,request);
     *  2. 保存Token;
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
        //调用父类方法将Form对象从Session中去掉。
        removeForm(mapping, request);
        //保存Token;
        saveToken(request);
        return mapping.findForward("return");
    }

    /**
     * 删除选中的记录
     *
     *  1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     *  2. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  3. 获取所有的记录。
     *      ArrayList delList = queryForm.getAllResult();
     *  4. 调用BaseProxy的delete方法删除。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      BaseProxy.delete(delList,userData);
     *  5. 删除Cache中的结果。
     *      queryForm.removeAll();
     *  6. 转向删除后的结果页面。
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

        // 转向删除后的结果页面。
        return mapping.findForward("delete");
    }

    /**
     * 删除选中的记录
     *
     *  1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     *  2. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  3. 获取选中的记录。
     *      ArrayList delList = queryForm.getSelectedData();
     *  4. 调用BaseProxy的delete方法删除。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      BaseProxy.delete(delList,userData);
     *  5. 删除Cache中的结果。
     *      queryForm.removeSeletedData(delList);
     *  6. 转向删除后的结果页面。
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
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // 将参数中的Form构型为QueryBaseForm。
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // 获取选中的记录。
            ArrayList delList = queryForm.getSelectedData();
            Debug.out("delList size: " + delList.size());
            // 调用BaseProxy的delete方法删除。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.DELETE);
            vo.setData(delList);
            vo.setUserData(userData);
            vo.setProcessor(prop.getProperty(delList.get(0).getClass().getName()));

            QsglProxy.getInstance().process(vo);
//            QsglProxy.getInstance().delete(delList, userData);
            // 删除Cache中的结果。
            queryForm.removeSelectedData(delList);
            // 转向删除后的结果页面。
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
     * 通过在 struts-confil.xml 中配置，转向到AddXXXXAction中进行处理
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
        // 保存Token;
        saveToken(request);
        return mapping.findForward("add");
    }
}
