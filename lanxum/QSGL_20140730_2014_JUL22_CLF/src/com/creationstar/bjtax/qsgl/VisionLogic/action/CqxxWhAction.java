package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm;
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
import com.ttsoft.framework.util.LogUtil;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CqxxWhAction extends QueryBaseAction {
    public CqxxWhAction() {
    }

    /**
     * 显示查询条件页面。（对应的Form有效期为session） 缺省返回一个空的Form. 1. 清空form. form.clear(); 2.
     * 保存Token; saveToken(); return mapping.findForward("query") ;
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doModify(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 清空form.
        // QueryBaseForm queryForm = (QueryBaseForm) form;
        // queryForm.clear();
        // 保存Token;
        saveToken(request);
        return mapping.findForward("modify");
    }

    /**
     * 执行查询 1. 将参数中的Form构型为QueryBaseForm。 QueryBaseForm queryForm =
     * (QueryBaseForm )form; 2. 从QueryBaseForm中获取查询条件。 Object obj =
     * queryForm.getData(); 3. 调用BaseProxy的query方法，查询数据。 HttpSession session =
     * request.getSession(); UserData userData =
     * (UserData)session.getAttribute(SessionKey.USER_DATA); ArrayList list =
     * BaseProxy.query(obj,userData); 4. 将结果构造成通用的QueryCache对象，以便翻页使用。
     * QueryCache cache = new QueryCache(list,userData.getMaxRows());
     * queryForm.setQueryCache(cache); 5. 保存Token; saveToken(); 6. 转向成功后的界面。
     * return mapping.findForward("query");
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String forpage = "query";
        try {
            System.out.println("11111111111111111111111");
            // 将参数中的Form构型为QueryBaseForm。
            QueryBaseForm queryForm = (QueryBaseForm) form;

            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            /*
             * if (((Cqxxb)obj).getSfwh().equals("1")){ forpage="querywh"; }
             */
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            // 构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // 将结果构造成通用的QueryCache对象，以便翻页使用。
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);
            // 将页面显示状态设定为显示查询结果
            queryForm.setStatus("Result");

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }
            if (list.size() > 2000) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询结果大于2000条,请精确查询条件！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "查询成功！");
            }

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward(forpage);
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward(forpage);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward(forpage);
        }
    }

    /**
     * 删除doDelete
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 调用父类方法处理Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        CqxxbForm queryForm = (CqxxbForm) form;

        LogUtil.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
        LogUtil.log(String.valueOf(queryForm.getSelectedIndex().length) + "\n");
        for (int i = 0; i < queryForm.getSelectedIndex().length; i++) {

            LogUtil.log(String.valueOf(queryForm.getSelectedIndex()[i]));
            LogUtil.log("\n");
        }

        // 构造向后台传输的Vopackage对象
        vo.setAction(ActionType.DELETE);
        vo.setUserData(this.getUserData());

        ArrayList delList = queryForm.getSelectedData();
        vo.setData(delList);
        vo.setProcessor(Constants.CQXXB_PROCESSOR);
        try {
            QsglProxy.getInstance().process(vo);

            // 因为状态改变，从查询结果中删除
            queryForm.removeSelectedData(delList);
            request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
            // 调用父类方法将Form对象从Session中去掉。
            // removeForm(mapping, request);
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // 保存Token;
        saveToken(request);
        // 可能从不征查询页面过来
        return mapping.findForward("delete");

    }

    /**
     * doQueryErr
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doQueryErr(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        String forpage = "queryErr";

        try {

            // 将参数中的Form构型为QueryBaseForm。
            CqxxbForm cqxxbForm = (CqxxbForm) form;

            // 从QueryBaseForm中获取查询条件。
            Object obj = cqxxbForm.getData();
            // if (((Cqxxb)obj).getSfwh().equals("1")){
            // forpage="querywh";
            // }
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            // 构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERYERR);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            Map lmReturnedData = (HashMap) QsglProxy.getInstance().process(vo);

            // 将页面显示状态设定为显示查询结果
            cqxxbForm.setStatus("Result");

            cqxxbForm.setResultListErr((ArrayList) lmReturnedData
                                       .get("resultListErr"));

            cqxxbForm.setResultListErrIs((ArrayList) lmReturnedData
                                         .get("resultListErrIs"));

            cqxxbForm.setResultListErrNo((ArrayList) lmReturnedData
                                         .get("resultListErrNo"));

            if (cqxxbForm.getResultListErr().size() > 0) {

                cqxxbForm.setSize(cqxxbForm.getResultListErr().size());

            }

            if (cqxxbForm.getResultListErrIs().size() > 0) {

                cqxxbForm.setSizeErrIs(cqxxbForm.getResultListErrIs().size());

            }

            if (cqxxbForm.getResultListErrNo().size() > 0) {

                cqxxbForm.setSizeErrNo(cqxxbForm.getResultListErrNo().size());

            }

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrIs().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrIs().size()));

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrIs().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrIs().size()));

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrNo().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrNo().size()));

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrNo().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrNo().size()));

            request.setAttribute(Constants.MESSAGE_KEY, lmReturnedData
                                 .get("message"));

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward(forpage);
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward(forpage);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward(forpage);
        }
    }

    /**
     * doSaveErr
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doSaveErr(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        String forpage = "saveErr";

        try {

            // 将参数中的Form构型为QueryBaseForm。
            CqxxbForm cqxxbForm = (CqxxbForm) form;

            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            //取出覆盖、废弃标示
            Object obj1 = cqxxbForm.getTestIs();

            cqxxbForm.setUseIs(StringUtil.divideString(obj1.toString(), ","));

            Object obj2 = cqxxbForm.getTestNo();

            cqxxbForm.setUseNo(StringUtil.divideString(obj2.toString(), ","));

            // 从QueryBaseForm中获取查询条件。
            HashMap hash = new HashMap();

            HashMap hashreIs = new HashMap();

            HashMap hashreNo = new HashMap();

            hashreIs = cqxxbForm.getSelectedDataIs();

            if (hashreIs != null) {

                hash.put("fgIs", hashreIs.get("fgIs"));

                hash.put("fqIs", hashreIs.get("fqIs"));

            }

            hashreNo = cqxxbForm.getSelectedDataNo();

            if (hashreNo != null) {

                hash.put("fgNo", hashreNo.get("fgNo"));

                hash.put("fqNo", hashreNo.get("fqNo"));

            }

            // 构造向后台传输的Vopackage对象
            vo.setAction(ActionType.SAVEERR);
            vo.setUserData(userData);
            vo.setData(hash);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            Map lmReturnedData = (HashMap) QsglProxy.getInstance().process(vo);

            request.setAttribute(Constants.MESSAGE_KEY, lmReturnedData
                                 .get("message"));

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrIs().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrIs().size()));

            if (hashreIs != null) {

                cqxxbForm.deleteIs((ArrayList) hashreIs.get("fgIs"));
                cqxxbForm.deleteIs((ArrayList) hashreIs.get("fqIs"));

            }

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrIs().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrIs().size()));

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrNo().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrNo().size()));

            if (hashreNo != null) {

                cqxxbForm.deleteNo((ArrayList) hashreNo.get("fgNo"));
                cqxxbForm.deleteNo((ArrayList) hashreNo.get("fqNo"));

            }

            LogUtil.log(
                    "String.valueOf(cqxxbForm.getResultListErrNo().size() :" +
                    String.valueOf(cqxxbForm.getResultListErrNo().size()));

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward(forpage);
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward(forpage);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward(forpage);
        }
    }

}
