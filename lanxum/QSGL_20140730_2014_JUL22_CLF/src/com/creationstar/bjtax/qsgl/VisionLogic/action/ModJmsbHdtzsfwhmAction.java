package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbHdtzsForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
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

public class ModJmsbHdtzsfwhmAction extends BaseAction {

    /**
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
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        try {
            Debug.out("this is ModifyHdtzsAction ");
            // 将参数中的Form构型为BaseForm。
            JmsbHdtzsForm aForm = (JmsbHdtzsForm) form;

            // 从BaseForm中获取查询条件。
            String sbbh = aForm.getSbbh();
            Debug.out("this is ModifyHdtzsAction sbbh" + sbbh);
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);

            // 获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // 构造向后台传输的Vopackage对象
            VOPackage vo = new VOPackage();
            JmsbxxBo bo = new JmsbxxBo();
            bo.setSbbh(sbbh);
            vo.setAction(ActionType.Query_HDTZS);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            Object obj = QsglProxy.getInstance().process(vo);
            Debug.out("get object: " + obj);
            HdtzsBo hdtzsbo = (HdtzsBo) obj;

            aForm.setHdtzsbo(hdtzsbo);

            aForm.setHdtzsh(hdtzsbo.getVoHdtzs().getHdtzsh());

            aForm.setFwhm(hdtzsbo.getVoHdtzs().getFwhm());

            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
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

    /**
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
    public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        try {
            Debug.out("this is ModifyHdtzsAction ");
            // 将参数中的Form构型为BaseForm。
            JmsbHdtzsForm aForm = (JmsbHdtzsForm) form;

            // 从BaseForm中获取查询条件。
            String sbbh = aForm.getSbbh();
            String hdtzsh = aForm.getHdtzsh();
            String fwhm = aForm.getFwhm();

            Debug.out("this is ModifyHdtzsAction sbbh" + sbbh);
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);

            // 获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // 构造向后台传输的Vopackage对象
            VOPackage vo = new VOPackage();
            JmsbxxBo jmbo = new JmsbxxBo();
            Hdtzs bo = new Hdtzs();
            bo.setSbbh(sbbh);
            bo.setHdtzsh(hdtzsh);
            bo.setFwhm(fwhm);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            vo.setAction(ActionType.Query_HDTZSBYFWHM);
            vo.setProcessor(prop.getProperty(jmbo.getClass().getName()));
            ArrayList hdtzsList = (ArrayList) QsglProxy.getInstance().process(
                    vo);

            if (hdtzsList == null || hdtzsList.size() == 0) {
                vo.setAction(ActionType.UPDATE_HDTZS);
                vo.setProcessor(prop.getProperty(jmbo.getClass().getName()));
                QsglProxy.getInstance().process(vo);
                request.setAttribute(Constants.MESSAGE_KEY, "保存核定通知书防伪号码成功！");
                // 保存Token;
                saveToken(request);
                // 转向成功后的界面。
                return mapping.findForward("update");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "录入的防伪号码已经存在！");
                // 保存Token;
                saveToken(request);
                // 转向界面。
                return mapping.findForward("show");
            }

        } catch (BaseException ye) {
            // 保存Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("update");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("update");
        }

    }

}
