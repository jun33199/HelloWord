package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
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

public class JmsbxxPrintAction extends BaseAction {
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
            Debug.out("this is sbxxprintaction hahaha");
            // �������е�Form����ΪBaseForm��
            JmsbxxForm aForm = (JmsbxxForm) form;

            // ��BaseForm�л�ȡ��ѯ������
            String sbbh = aForm.getSbbh();
            Debug.out("this is sbxxprintaction sbbh" + sbbh);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            VOPackage vo = new VOPackage();
            JmsbxxBo bo = new JmsbxxBo();
            bo.setSbbh(sbbh);
            vo.setAction(ActionType.PRINT_SBB);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            bo = (JmsbxxBo) QsglProxy.getInstance().process(vo);

            aForm.setData(bo);

            ArrayList jmsbbList = aForm.getJmsbbList();

            StringBuffer qsjmlbmc = new StringBuffer();
            for (int i = 0; i < jmsbbList.size(); i++) {

                Jmsbb jmsbb = (Jmsbb) jmsbbList.get(i);

                System.out.println(
                        "===============================================");
                System.out.println("��ѡ��ļ������ߣ�" + jmsbb.getJmzcdm());

                if (i > 0) {
                    qsjmlbmc.append("��<br>");
                }
                HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
                        .getServletContext(), Constants.JMZCMAP);
                Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(jmsbb.getJmzcdm());
                qsjmlbmc.append(qsjmlbVo.getQsjmlbmc());

                // ��������������д�뱸ע
                if (Constants.CXXJM_JMXMDM_QT.equals(jmsbb.getJmzcdm())) {
                    aForm.setQtjmlybeizhu(jmsbb.bz);
                }

            }

            System.out.println("�����������ɱ�ע��" + aForm.getQtjmlybeizhu());

            aForm.setQsjmlbmc(qsjmlbmc.toString());
            System.out.println("����˰���ɣ�" + aForm.getQsjmlbmc());

            request.setAttribute(mapping.getAttribute(), aForm);
            session.setAttribute(mapping.getAttribute(), aForm);
            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣ��ӡ�ɹ���");
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


}
