package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.HzJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *
 * <p>Title: ��¼�������ɵĽɿ����Action</p>
 *
 * <p>Description: ��¼�������ɵĽɿ����Action</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class AddHzJksAction extends AddBaseAction {
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        String whichPage = request.getParameter("page");

        if (whichPage.equalsIgnoreCase("add")) {
            HzJksBo bo = new HzJksBo();

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) session.getServletContext().
                              getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

            //�����̨��Ҫ���������ݶ���
            UserData ud = this.getUserData();
            VOPackage vo = new VOPackage();
            vo.setData(bo);
            vo.setUserData(ud);
            vo.setAction(ActionType.QUERY);

            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            try {
                bo = (HzJksBo) QsglProxy.getInstance().process(vo);
                aForm.setSzsmList(ActionUtil.getCodeTables(session.
                        getServletContext(), Constants.TDFWQSZY));
                aForm.setJksInfo(bo.getSbjkzb());
                aForm.setSbjkzb(bo.getSbjkzb());
            } catch (Exception ex) {
                request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            } finally {
                // ����Token;
                saveToken(request);

                return mapping.findForward("show");
            }
        } else {
            return mapping.findForward("show");
        }
    }


    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        HzJksBo bo = aForm.getHzJksBo();

        //�����̨��Ҫ���������ݶ���
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(ud);
        vo.setAction(ActionType.INSERT);

        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "��¼�������ɵĽɿ���ɹ�!");
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } finally {
            // ����Token;
            saveToken(request);
            aForm.setSzsmList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.TDFWQSZY));
            aForm.setMxkssl(null);
            aForm.setMxjsje(null);
            aForm.setMxsjse(null);

            return mapping.findForward("show");
        }

    }


    public ActionForward doUpdate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        hzWsz2JksForm aForm = (hzWsz2JksForm) form;

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        HzJksBo bo = new HzJksBo();
        bo.setJkpzh(aForm.getJkpzhao());

        //�����̨��Ҫ���������ݶ���
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(ud);
        vo.setAction(ActionType.UPDATE);

        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            String sbhzdh = (String) QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY,
                                 "�ɿ����Ʊ�ɹ����걨���ܵ���Ϊ��" + sbhzdh);
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } finally {
            // ����Token;
            saveToken(request);
            aForm.setJkpzhao(null);

            return mapping.findForward("show");
        }

    }

}
