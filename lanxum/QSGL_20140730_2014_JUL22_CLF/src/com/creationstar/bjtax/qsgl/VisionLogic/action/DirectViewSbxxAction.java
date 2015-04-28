package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DirectViewSbxxAction extends BaseAction {
    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        try {
            Debug.out("this is DirectViewSbxxAction ");
            // 将参数中的Form构型为BaseForm。
            SbxxForm aForm = (SbxxForm) form;

            // 从BaseForm中获取查询条件。
            String sbbh = aForm.getSbbh();
            Debug.out("this is DirectViewSbxxAction sbbh" + sbbh);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            VOPackage vo = new VOPackage();
            SbxxBo bo = new SbxxBo();
            bo.setSbbh(sbbh);
            vo.setAction(ActionType.GET);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            bo = (SbxxBo) QsglProxy.getInstance().process(vo);

            aForm.setData(bo);

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            request.setAttribute(Constants.MESSAGE_KEY, "申报详细信息显示成功！");
            return mapping.findForward("show");
        } catch (BaseException ye) {
            // 保存Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }

    }


}
