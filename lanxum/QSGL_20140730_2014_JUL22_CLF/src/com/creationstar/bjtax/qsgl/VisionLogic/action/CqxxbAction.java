package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ExcelUtil;
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


public class CqxxbAction extends QueryBaseAction {
    static final String[] TITLE = {"��Ǩ������", "��Ǩ��Ŀ����", "��Ǩ���֤", "��Ǩ��Χ", "��������",
                                  "����Ǩ������",
                                  "����Ǩ��֤������", "����Ǩ��֤������", "����Ǩ��ϸ��ַ", "�������",
                                  "��������", "�������", "����������ϸ�����ַ", "����������", "¼��ʱ��",
                                  "¼�����"};

    static final String[] COLUMS = {"cqrmc", "cqxmmc", "cqxkzh", "cqfw", "szqx",
                                   "bcqrmc",
                                   "zjhm",
                                   "zjlxmc", "cqxxdz", "bcje", "bclxmc", "bcmj",
                                   "bcfwdz", "gjrmc", "lrrq", "swjgzzjgmc"};

    public CqxxbAction() {
    }


    /**
     * ִ�в�ѯ
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ��QueryBaseForm�л�ȡ��ѯ������
     *      Object obj = queryForm.getData();
     *  3. ����BaseProxy��query��������ѯ���ݡ�
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      ArrayList list = BaseProxy.query(obj,userData);
     *  4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
     *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
     *      queryForm.setQueryCache(cache);
     *  5. ����Token;
     *       saveToken();
     *  6. ת��ɹ���Ľ��档
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
        String forpage = "query";
        try {
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;

            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            //if (((Cqxxb)obj).getSfwh().equals("1")){
            //	forpage="querywh";
            //}
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //�������̨�����Vopackage����
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

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
            if (list.size() > 2000) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�������2000��,�뾫ȷ��ѯ������");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "��ѯ�ɹ���");
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


    /**doPrint
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            CqxxbForm queryForm = (CqxxbForm) form;
            Cqxxb cqxxb = new Cqxxb();
            cqxxb.setCqxxbh(queryForm.getCqxxbh());
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //�������̨�����Vopackage����
            vo.setAction(ActionType.PRINT_CQQKB);
            vo.setUserData(userData);
            vo.setData(cqxxb);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);
            cqxxb = (Cqxxb) QsglProxy.getInstance().process(vo);
            queryForm.setCqxx(cqxxb);
            return mapping.findForward("print");
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

    /**doExport
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward doExport(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)

    {
        try {
            // �������е�Form����ΪQueryBaseForm��
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            Object obj = queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //�������̨�����Vopackage����
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            ArrayList tmpList = (ArrayList) QsglProxy.getInstance().process(vo);

            //����ļ�
            System.out.println("���Excel�ļ���¼����" + tmpList.size());
            if (tmpList == null || tmpList.size() <= 0) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "û�в�ѯ�����ݣ��޷�����Excel�ļ�");
                return mapping.findForward("query");
            }
            String currDate = DataConvert.Date2String(new Date(System.
                    currentTimeMillis()));

            String fileName = "��Ǩ��Ϣ��ѯ���".concat(currDate).concat(".xls");
            String encodeFileName = com.ttsoft.framework.util.StringUtil.
                                    GBK2ISO(
                                            fileName);
            response.resetBuffer();
            response.setHeader("Content-disposition",
                               "attachment; filename=" + encodeFileName);
            response.setContentType("application/vnd.ms-excel");

            ExcelUtil.generateExcel(response.getOutputStream(), TITLE, COLUMS,
                                    tmpList);

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

        return null;
    }


//  /**
//  * ִ�в�ѯ
//  *  1. �������е�Form����ΪQueryBaseForm��
//  *      QueryBaseForm queryForm = (QueryBaseForm )form;
//  *  2. ��QueryBaseForm�л�ȡ��ѯ������
//  *      Object obj = queryForm.getData();
//  *  3. ����BaseProxy��query��������ѯ���ݡ�
//  *       HttpSession session = request.getSession();
//  *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
//  *      ArrayList list = BaseProxy.query(obj,userData);
//  *  4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
//  *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
//  *      queryForm.setQueryCache(cache);
//  *  5. ����Token;
//  *       saveToken();
//  *  6. ת��ɹ���Ľ��档
//  *     return mapping.findForward("query");
//  *
//  * @param mapping The ActionMapping used to select this instance
//  * @param form The optional ActionForm bean for this request (if any)
//  * @param request The HTTP request we are processing
//  * @param response The HTTP response we are creating
//  * @return ActionForward
//  */
// public ActionForward doQueryCurrentPage(ActionMapping mapping,
//                                         ActionForm form,
//                                         HttpServletRequest request,
//                                         HttpServletResponse response) {
//     String forpage="query";
//     try {
//         // �������е�Form����ΪQueryBaseForm��
//         QueryBaseForm queryForm = (QueryBaseForm) form;
//         // ��QueryBaseForm�л�ȡ��ѯ������
//         Object obj = queryForm.getData();
//         //if (((Cqxxb)obj).getSfwh().equals("1")){
//         //	forpage="querywh";
//         //}
//         // ����BaseProxy��query��������ѯ���ݡ�
//         HttpSession session = request.getSession();
//         UserData userData = (UserData) session.getAttribute(SessionKey.
//                 USER_DATA);
//         VOPackage vo = new VOPackage();
//
//         //��ȡ�����ServletContext�е�processor-map.properties������
//         Properties prop = (Properties) request.getSession(false).
//                           getServletContext().getAttribute(Constants.
//                 PROCESSOR_MAP_FILE_NAME);
//
//         //�������̨�����Vopackage����
//         vo.setAction(ActionType.PRINT_SBB);
//         vo.setUserData(userData);
//         vo.setData(obj);
//         vo.setProcessor(prop.getProperty(obj.getClass().getName()));
//
//         ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
//         // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
//         Debug.out("userdata.maxrowperpage: " + userData.myxszds);
//         QueryCache cache = new QueryCache(list, userData.myxszds);
//         queryForm.setQueryCache(cache);
//
//         //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
//         queryForm.setStatus("Result");
//
//         if (list instanceof QueryArrayList) {
//             TtsoftMessage msg = ((QueryArrayList) list).getMsg();
//             if (msg != null) {
//                 request.setAttribute(RequestKey.MESSAGE_KEY, msg.getMsg());
//             }
//         }
//         if (list.size() > 200) {
//             request.setAttribute(Constants.MESSAGE_KEY,
//                                  "��ѯ�������200��,�뾫ȷ��ѯ������");
//         } else {
//             request.setAttribute(Constants.MESSAGE_KEY,
//                                  "��ѯ�ɹ���");
//         }
//
//         //���õ�ǰҳ��ҳ����ת����ҳ
//         return doChangePage(mapping, form, request, response);
//     } catch (BaseException ye) {
//         String err = ye.getMessage();
//         request.setAttribute(RequestKey.MESSAGE_KEY, err);
//         return mapping.findForward(forpage);
//     } catch (Exception ex) {
//         ex.printStackTrace();
//         request.setAttribute(RequestKey.MESSAGE_KEY,
//                              CodeConstants.MSG_ERROR);
//         return mapping.findForward(forpage);
//     }
// }


}
