package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PldyForm2;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
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
 *
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
 *
 * 说明：该action是针对页面中"批量受理(税务人员)"模块
 */
public class QueryPldyAction2 extends QueryBaseAction {
    private VOPackage vo = new VOPackage();
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // 将参数中的Form构型为QueryBaseForm。
            PldyForm2 queryForm = (PldyForm2) form;
            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            // 把查询条件放入session中供返回时使用
            queryForm.setQueryObj(obj);
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
            request.setAttribute(Constants.MESSAGE_KEY, "查询成功！");
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

    public ActionForward doView(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            // 将参数中的Form构型为PldyForm。
            PldyForm2 queryForm = (PldyForm2) form;
            QueryCache dyCache = queryForm.getQueryCache();
            // 从QueryBaseForm中获取查询条件。
            Object obj = queryForm.getData();
            // 调用BaseProxy的query方法，查询数据。
            HttpSession session = request.getSession(false);
            // 把查询结果Cache放入form的临时字段以便返回时使用
            queryForm.setTmpQyeryCache(dyCache);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) session.getServletContext().
                              getAttribute(Constants.
                                           PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.GET);
            vo.setUserData(userData);
            vo.setData(obj);

            System.out.println("####################" + obj.getClass().getName());

            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // 将查询结果集放入form供打印使用
            queryForm.setResultList(list);
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
            request.setAttribute(Constants.MESSAGE_KEY, "显示明细成功！");
            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward("detail");
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
        // 回填查询条件和结果集
        PldyForm2 dyForm = (PldyForm2) form;
        PldyBo2 pldyBo = (PldyBo2) dyForm.getQueryObj();
        dyForm.setDrsjBegin(pldyBo.getDrsjBegin());
        dyForm.setDrsjEnd(pldyBo.getDrsjEnd());
        dyForm.setPch(pldyBo.getPch());
        dyForm.setTgzgjmc(pldyBo.getTgzgjmc());
        dyForm.setTgzjsjdm(pldyBo.getTgzjsjdm());
        dyForm.setQueryCache(dyForm.getTmpQyeryCache());
        //保存Token;
        saveToken(request);
        return mapping.findForward("back");
    }

    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        PldyForm2 pldyForm = (PldyForm2) form;
        // 需要导出的xml文件内容
        String xmlContent = "";
        try {
            xmlContent = QsglPcclXmlUtil.createXML2(pldyForm.getResultList());
            String fname = pldyForm.getPch(); //要下载到本地的文件名
            OutputStream os = response.getOutputStream(); //取得输出流
            response.reset(); //清空输出流
            response.setHeader("Content-disposition",
                               "attachment; filename=" + fname + ".xml"); //设定输出文件头
            response.setContentType("application/xml;charset=GB2312"); //定义输出类型
            //向输出流里写入string字符串
            os.write(xmlContent.getBytes());
            os.flush();
            os.close();

            // 更新本批次数据状态
            // 从QueryBaseForm中获取查询条件。
            Object obj = pldyForm.getData();
            HttpSession session = request.getSession(false);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) session.getServletContext().
                              getAttribute(Constants.
                                           PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            vo.setAction(ActionType.UPDATE);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "导出打印文件成功！");
            return mapping.findForward(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            request.setAttribute(Constants.MESSAGE_KEY, "导出打印文件失败！");
            return mapping.findForward("detail");
        }
    }

    public ActionForward doChangePageDetail(ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        //将参数中的Form构型为QueryBaseForm。
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // 调用Form的changPage接口实现数据的变换。
        queryForm.changePage();
        // 保存Token;
        saveToken(request);
        return mapping.findForward("detail");
    }

}
