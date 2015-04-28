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
     * ��ʾ��ѯ����ҳ�档����Ӧ��Form��Ч��Ϊsession�� ȱʡ����һ���յ�Form. 1. ���form. form.clear(); 2.
     * ����Token; saveToken(); return mapping.findForward("query") ;
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
        // ���form.
        // QueryBaseForm queryForm = (QueryBaseForm) form;
        // queryForm.clear();
        // ����Token;
        saveToken(request);
        return mapping.findForward("modify");
    }

    /**
     * ִ�в�ѯ 1. �������е�Form����ΪQueryBaseForm�� QueryBaseForm queryForm =
     * (QueryBaseForm )form; 2. ��QueryBaseForm�л�ȡ��ѯ������ Object obj =
     * queryForm.getData(); 3. ����BaseProxy��query��������ѯ���ݡ� HttpSession session =
     * request.getSession(); UserData userData =
     * (UserData)session.getAttribute(SessionKey.USER_DATA); ArrayList list =
     * BaseProxy.query(obj,userData); 4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
     * QueryCache cache = new QueryCache(list,userData.getMaxRows());
     * queryForm.setQueryCache(cache); 5. ����Token; saveToken(); 6. ת��ɹ���Ľ��档
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
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;

            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            /*
             * if (((Cqxxb)obj).getSfwh().equals("1")){ forpage="querywh"; }
             */
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            // �������̨�����Vopackage����
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);
            // ��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");

            if (list instanceof QueryArrayList) {
                TtsoftMessage msg = ((QueryArrayList) list).getMsg();
                if (msg != null) {
                    request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
                }
            }
            if (list.size() > 2000) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�������2000��,�뾫ȷ��ѯ������");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "��ѯ�ɹ���");
            }

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
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
     * ɾ��doDelete
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
        // ���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        VOPackage vo = new VOPackage();

        CqxxbForm queryForm = (CqxxbForm) form;

        LogUtil.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
        LogUtil.log(String.valueOf(queryForm.getSelectedIndex().length) + "\n");
        for (int i = 0; i < queryForm.getSelectedIndex().length; i++) {

            LogUtil.log(String.valueOf(queryForm.getSelectedIndex()[i]));
            LogUtil.log("\n");
        }

        // �������̨�����Vopackage����
        vo.setAction(ActionType.DELETE);
        vo.setUserData(this.getUserData());

        ArrayList delList = queryForm.getSelectedData();
        vo.setData(delList);
        vo.setProcessor(Constants.CQXXB_PROCESSOR);
        try {
            QsglProxy.getInstance().process(vo);

            // ��Ϊ״̬�ı䣬�Ӳ�ѯ�����ɾ��
            queryForm.removeSelectedData(delList);
            request.setAttribute(Constants.MESSAGE_KEY, "ɾ���ɹ���");
            // ���ø��෽����Form�����Session��ȥ����
            // removeForm(mapping, request);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
        // ����Token;
        saveToken(request);
        // ���ܴӲ�����ѯҳ�����
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

            // �������е�Form����ΪQueryBaseForm��
            CqxxbForm cqxxbForm = (CqxxbForm) form;

            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = cqxxbForm.getData();
            // if (((Cqxxb)obj).getSfwh().equals("1")){
            // forpage="querywh";
            // }
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            // �������̨�����Vopackage����
            vo.setAction(ActionType.QUERYERR);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            Map lmReturnedData = (HashMap) QsglProxy.getInstance().process(vo);

            // ��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
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

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
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

            // �������е�Form����ΪQueryBaseForm��
            CqxxbForm cqxxbForm = (CqxxbForm) form;

            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);
            VOPackage vo = new VOPackage();

            //ȡ�����ǡ�������ʾ
            Object obj1 = cqxxbForm.getTestIs();

            cqxxbForm.setUseIs(StringUtil.divideString(obj1.toString(), ","));

            Object obj2 = cqxxbForm.getTestNo();

            cqxxbForm.setUseNo(StringUtil.divideString(obj2.toString(), ","));

            // ��QueryBaseForm�л�ȡ��ѯ������
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

            // �������̨�����Vopackage����
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

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
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
