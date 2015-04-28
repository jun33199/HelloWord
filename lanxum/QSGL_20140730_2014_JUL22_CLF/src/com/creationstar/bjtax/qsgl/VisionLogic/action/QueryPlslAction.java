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
     * 即时办理--查询申报相关信息
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
            //获取存放在ServletContext中的代码表的数据
            aForm.setJkfsList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.JSFS));
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            saveToken(request);
            return new ActionForward(mapping.getInput());
        }
        request.setAttribute(Constants.MESSAGE_KEY, "显示成功,只能对未受理数据进行删除和受理操作！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * 受理记录
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
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // 从Form中获取对象。
            QueryPlslForm queryForm = (QueryPlslForm) form;
            // 获取选中的记录。
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
            //调用QsglProxy的check方法,检查
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage ,受理
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.CHECK);
            vo.setProcessor(prop.getProperty(PldrBo.class.getName()));
            vo.setUserData(this.getUserData());
            vo.setData(inputList);

            HashMap errMap = (HashMap) QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "数据验证完成！");
            request.setAttribute("map", errMap);
            request.setAttribute("from", "query");
            queryForm.removeSelectedData(list);
            // 保存Token;
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
            VOPackage vo = new VOPackage();

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
            if (list.size() > 200) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询结果大于200条,请精确查询条件,只能对未受理数据进行删除和受理操作！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询成功,只能对未受理数据进行删除和受理操作！");
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
    public ActionForward doQueryCurrentPage(ActionMapping mapping,
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
            VOPackage vo = new VOPackage();

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
            if (list.size() > 200) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询结果大于200条,请精确查询条件,只能对未受理数据进行删除和受理操作！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询成功,只能对未受理数据进行删除和受理操作！");
            }

            //设置当前页面页数，转到此页
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
