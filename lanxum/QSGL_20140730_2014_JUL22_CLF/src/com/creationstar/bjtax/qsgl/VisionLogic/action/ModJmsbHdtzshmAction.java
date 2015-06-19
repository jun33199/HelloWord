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

public class ModJmsbHdtzshmAction extends BaseAction {

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
            // �������е�Form����ΪBaseForm��
            JmsbHdtzsForm aForm = (JmsbHdtzsForm) form;

            // ��BaseForm�л�ȡ��ѯ������
            String sbbh = aForm.getSbbh();
            Debug.out("this is ModifyHdtzsAction sbbh" + sbbh);
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);

            // ��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // �������̨�����Vopackage����
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

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("show");
        } catch (BaseException ye) {
            // ����Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            // ����Token;
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
            // �������е�Form����ΪBaseForm��
            JmsbHdtzsForm aForm = (JmsbHdtzsForm) form;

            // ��BaseForm�л�ȡ��ѯ������
            String sbbh = aForm.getSbbh();
            String hdtzsh = aForm.getHdtzsh();
            String hdtzsh_xg = aForm.getHdtzsh_xg();
            String fwhm = aForm.getFwhm();

            Debug.out("this is ModifyHdtzsAction sbbh" + sbbh);
            UserData userData = (UserData) session
                                .getAttribute(SessionKey.USER_DATA);

            // ��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // �������̨�����Vopackage����
            VOPackage vo = new VOPackage();
            JmsbxxBo jmbo = new JmsbxxBo();
            Hdtzs hdtzs = new Hdtzs();
            HdtzsBo bo = new HdtzsBo();

            hdtzs.setSbbh(sbbh);
            hdtzs.setHdtzsh(hdtzsh);
            hdtzs.setFwhm(fwhm);

            bo.setVoHdtzs(hdtzs);
            bo.setHdtzsh_xg(hdtzsh_xg);
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            //�ж��޸ĺ�ĺ˶�֪ͨ������Ƿ����
            vo.setAction(ActionType.Query_HDTZSBYHDTZSHM);
            vo.setProcessor(prop.getProperty(jmbo.getClass().getName()));
            ArrayList hdtzsList = (ArrayList) QsglProxy.getInstance().process(
                    vo);

            Debug.out("this is ModifyHdtzsAction query hdtzs by hdtzshm end");

            if (hdtzsList == null || hdtzsList.size() == 0) {
                vo.setAction(ActionType.UPDATE_HDTZSHM_BY_HDTZSHM);
                QsglProxy.getInstance().process(vo);
                Debug.out(
                        "this is ModifyHdtzsAction update hdtzs by hdtzshm end");
                request.setAttribute(Constants.MESSAGE_KEY, "�˶�֪ͨ������޸ĳɹ���");
                // ����Token;
                saveToken(request);
                // ת��ɹ���Ľ��档
                return mapping.findForward("update");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "¼��ĺ˶�֪ͨ������Ѿ����ڣ�");
                // ����Token;
                saveToken(request);
                // ת����档
                return mapping.findForward("show");
            }

        } catch (BaseException ye) {
            // ����Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("update");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("update");
        }

    }

}
