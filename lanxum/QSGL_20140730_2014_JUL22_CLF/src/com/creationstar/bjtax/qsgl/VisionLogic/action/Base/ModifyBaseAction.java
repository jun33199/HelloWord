package com.creationstar.bjtax.qsgl.VisionLogic.action.Base;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 数据修改基本Action类.
 * 控制一般的数据修改事务（保存，取消返回）
 * 采用模版方法模式。提供一般的修改事务中的控制。
 * 特殊的操作可定义子类，由子类实现具体方法（doXXX）。
 * 更特殊的增加事务可由子类重载此方法提供更多的选择。
 */
public class ModifyBaseAction extends BaseAction {
    /**
     * 保存记录
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((QueryBaseForm)form).getModifiedData();
     * 3. 调用BaseProxy的add方法，修改一条记录。
     *       HttpSession session = request.getSession();
     *       UserData userData =
     *           (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.modify(obj,userData);
     * 4. 更新Form中的结果集。
     *      ((QueryBaseForm)form).modifyData();
     * 5. 保存Token;
     *       saveToken();
     * 6. 转向成功后的界面。
     *     return mapping.findForward("modify");
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
            Object obj = ((QueryBaseForm) form).getModifiedData();

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            //调用QsglProxy的modify方法，修改一条记录。
            /** @todo 加入返回值得判断，以告诉页面是否成功 */
            QsglProxy.getInstance().process(vo);
            ((QueryBaseForm) form).modifyData(obj);

            // 保存
            saveToken(request);
            // 转向成功后的界面。
            return mapping.findForward("modify");
        } catch (BaseException te) {
            return mapping.findForward("show");
        } catch (Exception ex) {
            ex.printStackTrace();
            return mapping.findForward("show");
        }
    }

    /**
     * 取消当前操作
     * 转向取消后应去的页面。
     *
     *  1. 保存Token;
     *       saveToken();
     *  2.  转向View页面。
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //保存Token;
        saveToken(request);
        //  转向View页面。
        return mapping.findForward("return");
    }

    /**
     * 转向到修改页面
     *  1. 将参数中的Form构型为QueryBaseForm。
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. 生成viewForm。
     *      queryForm.createViewForm();
     *  3. 保存Token;
     *       saveToken();
     *  4. 转向显示修改的界面。
     *         return mapping.findForward("show") ;
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // 将参数中的Form构型为QueryBaseForm。
        QueryBaseForm queryForm = (QueryBaseForm) form;
        // 生成viewForm。
        queryForm.createViewForm();
        //. 保存Token;
        saveToken(request);
        // 转向显示修改的界面。
        return mapping.findForward("show");
    }
}
