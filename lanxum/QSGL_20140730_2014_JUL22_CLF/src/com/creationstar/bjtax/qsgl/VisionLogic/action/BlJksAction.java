package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
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
public class BlJksAction extends QueryBaseAction {
    /**
     *  ��ʾҳ��,ִ�в�ѯ
     *  1. �������е�Form����ΪQueryBaseForm��
     *      QueryBaseForm queryForm = (QueryBaseForm )form;
     *  2. ��QueryBaseForm�л�ȡ��ѯ������
     *      Object obj = queryForm.getData();
     *  3. ����BaseProxy��query��������ѯ���ݡ�
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *      ArrayList list = BaseProxy.query(obj,userData);
     *  4. ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
     *      QueryCache cache = new QueryCache(list,userData.getMaxRows());
     *      queryForm.setQueryCache(cache);
     *  5. ����Token;
     *       saveToken();
     *  6. ת��ɹ���Ľ��档
     *     return mapping.findForward("query");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doQuery(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // �������е�Form����ΪQueryBaseForm��
            BlJksForm queryForm = (BlJksForm) form;
            // ��QueryBaseForm�л�ȡ��ѯ������
            QueryBlJksBo obj = new QueryBlJksBo();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession();
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);
            VOPackage vo = new VOPackage();

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            obj.setJsje(queryForm.getJsje());
            obj.setSjse(queryForm.getSjse());

            //�������̨�����Vopackage����
            vo.setAction(ActionType.BL_QUERY_JKS);
            vo.setUserData(userData);
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�
            Debug.out("userdata.maxrowperpage: " + userData.myxszds);
            QueryCache cache = new QueryCache(list, userData.myxszds);
            queryForm.setQueryCache(cache);
            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("query");
        } catch (BaseException ye) {
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("query");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("query");
        }
    }

}
