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
        request.setAttribute(Constants.MESSAGE_KEY, "申报信息显示成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // 将参数中的Form构型为QueryBaseForm。
            QuerySbzbForm querySbzbForm = (QuerySbzbForm) form;
            // 从QueryBaseForm中获取查询条件。
            Object obj = querySbzbForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY_SBZT);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            System.out.println("obj.getClass().getName():" +
                               obj.getClass().getName());
            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // 将结果构造成通用的QueryCache对象，以便翻页使用。
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            querySbzbForm.setQueryCache(cache);
            //将页面显示状态设定为显示查询结果
            querySbzbForm.setStatus("Result");
            Debug.out("querySbzbForm.status: " + querySbzbForm.getStatus());

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }

            // 保存Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "查询成功！");
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
            ArrayList sbbh = new ArrayList();
            int size = delList.size();
            for (int i = 0; i < size; i++) {
                QuerySbzbBo bo = (QuerySbzbBo) delList.get(i);
                sbbh.add(bo.getSbbh());
            }

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
            vo.setData(sbbh);
            vo.setUserData(userData);
            vo.setProcessor(prop.getProperty(delList.get(0).getClass().getName()));

            QsglProxy.getInstance().process(vo);
//            QsglProxy.getInstance().delete(delList, userData);
            // 删除Cache中的结果。
            queryForm.removeSelectedData(delList);
            // 转向删除后的结果页面。
            request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
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
