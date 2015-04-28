package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlcxMxForm;
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

public class ViewPlcxAction extends QueryBaseAction {
    private VOPackage vo = new VOPackage();
    static final String[] TITLE = {"批次号", "状态信息", "申报表号", "纳税人名称", "纳税人计算机代码",
                                  "房地产项目名称",
                                  "房地产地址", "合同签订日期", "计税金额", "应纳税额"
    };

    static final String[] COLUMS = {"pch", "zt", "sbbh", "nsrmc", "jsjdm",
                                   "fdcxmmc",
                                   "fdcdz", "htqdrq", "jsje", "ynse"
    };

    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // 将参数中的Form构型为QueryBaseForm。
            QueryPlcxMxForm queryForm = (QueryPlcxMxForm) form;
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
            vo.setAction(ActionType.GET);
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
            request.setAttribute(Constants.MESSAGE_KEY, "显示明细记录成功！");
            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward("show");
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }
    }

    public ActionForward doBack(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        Debug.out("come in============================");
        //调用父类方法将Form对象从Session中去掉。
        removeForm(mapping, request);
        //保存Token;
        saveToken(request);
        return mapping.findForward("back");
    }

    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        Debug.out("come in============================");
        //将查询Form对象从Session中去掉。
        request.getSession().removeAttribute("queryPlcxForm");
        //保存Token;
        saveToken(request);
        return super.doReturn(mapping, form, request, response);
    }

    /**
     * doExport
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doExport(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)

    {

        UserData userData = null;
        QueryPlcxMxForm queryForm = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            // 将参数中的Form构型为QueryBaseForm。
            queryForm = (QueryPlcxMxForm) form;
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.GET);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

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

            String fileName = "批量统计结果".concat(currDate).concat(".xls");
            String encodeFileName = com.ttsoft.framework.util.StringUtil.
                                    GBK2ISO(
                                            fileName);
            response.resetBuffer();
            response.setHeader("Content-disposition",
                               "attachment; filename=" + encodeFileName);
            response.setContentType("application/vnd.ms-excel");

            ExcelUtil.generateExcel(response.getOutputStream(), TITLE, COLUMS,
                                    tmpList);

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }

    }


}
