package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryYggyzfForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryYggyzfBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QueryYggyzfAction extends BaseAction {
    /**
     * 获取查询条件，调用后台接口，返回查询结果（QueryYggyzfBo）。将查询结果赋值给form（调用form.setData()）
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryYggyzfForm queryYggyzfForm = (QueryYggyzfForm) form;
        String yggyzfqszsh = queryYggyzfForm.getYggyzfqszsh();
        // 保存Token;
        saveToken(request);

        try {
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            Jsblgyzf jsblgyzf = new Jsblgyzf();
            jsblgyzf.yggyzfqszsh = yggyzfqszsh;
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY_USAGE);
            vo.setProcessor(prop.getProperty(jsblgyzf.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(jsblgyzf);
            Debug.out("yggyzfqszsh:" + yggyzfqszsh);
            Object obj = QsglProxy.getInstance().process(vo);
            Debug.out("aaa: " + obj);
            QueryYggyzfBo bo = (QueryYggyzfBo) obj;
            queryYggyzfForm.setAfterQuery(true);
            if (bo != null) {
                queryYggyzfForm.setData(bo);
                queryYggyzfForm.setExist(true);
                request.setAttribute(Constants.MESSAGE_KEY, "查询已购公有住房使用情况成功！");
            } else {
                queryYggyzfForm.clearResult();
                queryYggyzfForm.setExist(false);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "不存在指定已购公有住房协议号码的信息！");
            }
        } catch (BaseException ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "查询拆迁使用情况失败！");
        }

        return mapping.findForward("show");

    }

    /**
     * 保存记录
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((BaseForm)form).getData();
     * 3. 调用BaseProxy的add方法，向数据库中增加一条记录。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. 保存Token;
     *       saveToken();
     * 5. 转向成功后的界面。
     *     return mapping.findForward("add");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
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
            QueryYggyzfForm aForm = (QueryYggyzfForm) form;
            Jsblgyzf jsblgyzf = new Jsblgyzf();
            jsblgyzf.setYggyzfqszsh(aForm.getYggyzfqszsh());
            jsblgyzf.setSye(DataConvert.String2BigDecimal(aForm.getSye()));

            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.UPDATE_SYE);
            vo.setProcessor(prop.getProperty(jsblgyzf.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(jsblgyzf);

            QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "保存成功");
            return new ActionForward(mapping.getInput());
        } catch (BaseException te) {
            Debug.printException(te);
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

}
