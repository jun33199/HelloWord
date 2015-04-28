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
    static final String[] TITLE = {"拆迁人名称", "拆迁项目名称", "拆迁许可证", "拆迁范围", "所在区县",
                                  "被拆迁人名称",
                                  "被拆迁人证件号码", "被拆迁人证件类型", "被拆迁详细地址", "补偿金额",
                                  "补偿类型", "补偿面积", "补偿房屋明细座落地址", "共居人姓名", "录入时间",
                                  "录入机构"};

    static final String[] COLUMS = {"cqrmc", "cqxmmc", "cqxkzh", "cqfw", "szqx",
                                   "bcqrmc",
                                   "zjhm",
                                   "zjlxmc", "cqxxdz", "bcje", "bclxmc", "bcmj",
                                   "bcfwdz", "gjrmc", "lrrq", "swjgzzjgmc"};

    public CqxxbAction() {
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
        String forpage = "query";
        try {
            // 将参数中的Form构型为QueryBaseForm。
            QueryBaseForm queryForm = (QueryBaseForm) form;

            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            //if (((Cqxxb)obj).getSfwh().equals("1")){
            //	forpage="querywh";
            //}
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

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
            if (list.size() > 2000) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询结果大于2000条,请精确查询条件！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "查询成功！");
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
            // 将参数中的Form构型为QueryBaseForm。
            CqxxbForm queryForm = (CqxxbForm) form;
            Cqxxb cqxxb = new Cqxxb();
            cqxxb.setCqxxbh(queryForm.getCqxxbh());
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //构造向后台传输的Vopackage对象
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
            // 将参数中的Form构型为QueryBaseForm。
            QueryBaseForm queryForm = (QueryBaseForm) form;
            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(Constants.CQXXB_PROCESSOR);

            ArrayList tmpList = (ArrayList) QsglProxy.getInstance().process(vo);

            //输出文件
            System.out.println("输出Excel文件记录数：" + tmpList.size());
            if (tmpList == null || tmpList.size() <= 0) {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "没有查询到数据，无法导出Excel文件");
                return mapping.findForward("query");
            }
            String currDate = DataConvert.Date2String(new Date(System.
                    currentTimeMillis()));

            String fileName = "拆迁信息查询结果".concat(currDate).concat(".xls");
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
//  * 执行查询
//  *  1. 将参数中的Form构型为QueryBaseForm。
//  *      QueryBaseForm queryForm = (QueryBaseForm )form;
//  *  2. 从QueryBaseForm中获取查询条件。
//  *      Object obj = queryForm.getData();
//  *  3. 调用BaseProxy的query方法，查询数据。
//  *       HttpSession session = request.getSession();
//  *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
//  *      ArrayList list = BaseProxy.query(obj,userData);
//  *  4. 将结果构造成通用的QueryCache对象，以便翻页使用。
//  *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
//  *      queryForm.setQueryCache(cache);
//  *  5. 保存Token;
//  *       saveToken();
//  *  6. 转向成功后的界面。
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
//         // 将参数中的Form构型为QueryBaseForm。
//         QueryBaseForm queryForm = (QueryBaseForm) form;
//         // 从QueryBaseForm中获取查询条件。
//         Object obj = queryForm.getData();
//         //if (((Cqxxb)obj).getSfwh().equals("1")){
//         //	forpage="querywh";
//         //}
//         // 调用BaseProxy的query方法，查询数据。
//         HttpSession session = request.getSession();
//         UserData userData = (UserData) session.getAttribute(SessionKey.
//                 USER_DATA);
//         VOPackage vo = new VOPackage();
//
//         //获取存放在ServletContext中的processor-map.properties的数据
//         Properties prop = (Properties) request.getSession(false).
//                           getServletContext().getAttribute(Constants.
//                 PROCESSOR_MAP_FILE_NAME);
//
//         //构造向后台传输的Vopackage对象
//         vo.setAction(ActionType.PRINT_SBB);
//         vo.setUserData(userData);
//         vo.setData(obj);
//         vo.setProcessor(prop.getProperty(obj.getClass().getName()));
//
//         ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
//         // 将结果构造成通用的QueryCache对象，以便翻页使用。
//         Debug.out("userdata.maxrowperpage: " + userData.myxszds);
//         QueryCache cache = new QueryCache(list, userData.myxszds);
//         queryForm.setQueryCache(cache);
//
//         //将页面显示状态设定为显示查询结果
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
//                                  "查询结果大于200条,请精确查询条件！");
//         } else {
//             request.setAttribute(Constants.MESSAGE_KEY,
//                                  "查询成功！");
//         }
//
//         //设置当前页面页数，转到此页
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
