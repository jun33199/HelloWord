/*
 * Created on 2006-1-10
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PltjAction extends QueryBaseAction {


    static final String[] TITLE = {"批次号", "导入时间", "提交时间", "导入笔数", "提交笔数",
                                  "受理通过笔数",
                                  "审核通过笔数", "复核通过笔数", "复核通过应纳税额"
    };

    static final String[] COLUMS = {"pch", "drsj", "tjsj", "drbs", "tjbs",
                                   "sltgbs",
                                   "shtgbs", "fstgbs", "sumYnse"
    };

    /**
     *
     */
    public PltjAction() {
        super();
        //
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
        QueryBaseForm queryForm = null;
        VOPackage vo = new VOPackage();
        //执行Processor
        try {
            //初始化
            // 将参数中的Form构型为QueryBaseForm。
            queryForm = (QueryBaseForm) form;
            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            userData = (UserData) session.getAttribute(SessionKey.USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.QUERY);
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
            return mapping.findForward("query");
        }

    }


}
