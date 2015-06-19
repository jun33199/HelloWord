package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryCqxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryCqxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QueryCqxxAction extends BaseAction {
    /**
     * ��ȡ��ѯ���������ú�̨�ӿڣ����ز�ѯ�����QueryCqxxBo��������ѯ�����ֵ��form������form.setData()��
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
        QueryCqxxForm cqxxForm = (QueryCqxxForm) form;
        String cqxyh = cqxxForm.getCqxyh();
        // ����Token;
        saveToken(request);

        try {
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            Jsblcq jscq = new Jsblcq();
            jscq.cqxyh = cqxyh;
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY_USAGE);
            vo.setProcessor(prop.getProperty(jscq.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(jscq);
            Debug.out("cqxyh:" + cqxyh);
            Object obj = QsglProxy.getInstance().process(vo);
            Debug.out("aaa: " + obj);
            QueryCqxxBo bo = (QueryCqxxBo) obj;
            cqxxForm.setAfterQuery(true);
            if (bo != null) {
                cqxxForm.setData(bo);
                cqxxForm.setExist(true);
                request.setAttribute(Constants.MESSAGE_KEY, "��ѯ��Ǩʹ������ɹ���");
            } else {
                cqxxForm.clearResult();
                cqxxForm.setExist(false);
                request.setAttribute(Constants.MESSAGE_KEY, "������ָ����ǨЭ�����Ĳ�Ǩ��Ϣ��");
            }
        } catch (BaseException ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "��ѯ��Ǩʹ�����ʧ�ܣ�");
        }

        return mapping.findForward("show");

    }

    /**
     * �����¼
     *
     * 1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. ��Form�л�ȡ����
     *     Object obj = ((BaseForm)form).getData();
     * 3. ����BaseProxy��add�����������ݿ�������һ����¼��
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. ����Token;
     *       saveToken();
     * 5. ת��ɹ���Ľ��档
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
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // ��Form�л�ȡ����
            QueryCqxxForm aForm = (QueryCqxxForm) form;
            Jsblcq jsblcq = new Jsblcq();
            jsblcq.setCqxyh(aForm.getCqxyh());
            jsblcq.setCqbcsye(DataConvert.String2BigDecimal(aForm.getSye()));

            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.UPDATE_SYE);
            vo.setProcessor(prop.getProperty(jsblcq.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(jsblcq);

            QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, "����ɹ�");
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
