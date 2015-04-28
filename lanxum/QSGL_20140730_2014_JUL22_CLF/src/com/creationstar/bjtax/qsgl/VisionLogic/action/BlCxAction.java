package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
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
 */
public class BlCxAction extends BaseAction {

    /**
     * ��ҳ��ͨ����˰֤�Ż����˰֤�Ļ�����Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        BlJksForm aForm = (BlJksForm) form;

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        QueryBlJksBo bo = (QueryBlJksBo) aForm.getBo();

        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.GET);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QueryBlJksBo resultBo = (QueryBlJksBo) QsglProxy.getInstance().
                                    process(vo);

            if (resultBo != null) {
                aForm.setResultBo(resultBo);
                Sbjkzb sbjkzb = resultBo.getSbjkzb();
                ArrayList aList = new ArrayList();
                aList.add(sbjkzb);
                QueryCache cache = new QueryCache(aList,
                                                  this.getUserData().myxszds);
                aForm.setQueryCache(cache);
                //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
                aForm.setStatus("Result");
                request.setAttribute(Constants.MESSAGE_KEY, "�Ѿ��ӿ��л������Ҫ�Ľɿ������ݣ�");
            } else {
                QueryCache cache = aForm.getQueryCache();
                cache.removeAll();
                aForm.setStatus("Query");
                request.setAttribute(Constants.MESSAGE_KEY, "û�з��������Ľɿ������ݣ�");
            }
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    /**
     * �����ɿ��飨�������ܷ�ʽ���ǻ��ܷ�ʽ��
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCxJks(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }

        HttpSession session = request.getSession(false);
        BlJksForm aForm = (BlJksForm) form;
        VOPackage vo = new VOPackage();

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //����ǻ�����˰֤��ʽ���ɵĽɿ��飬����wszProcessor�ĳ�������
        QueryBlJksBo bo = aForm.getResultBo();
        bo.setSjly(aForm.getScfs());

        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.BL_REMOVECONNECT);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY,
                                 "�ɹ������ɿ����Ϊ" + aForm.getJkpzh() + "�Ľɿ��飡");
            QueryCache cache = aForm.getQueryCache();
            cache.removeAll();
            aForm.setStatus("Query");

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("cx");
    }

}
