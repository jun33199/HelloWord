package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FhzJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AddFhzJksAction extends AddBaseAction {

    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        JksForm aForm = (JksForm) form;

        FhzJksBo bo = new FhzJksBo();
        bo.setSbbh(aForm.getSbbh());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(ud);
        vo.setAction(ActionType.GET);

        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            bo = (FhzJksBo) QsglProxy.getInstance().process(vo);
            aForm.setSbjkzb(bo.getSbjkzb());
            aForm.setSbjkmx(bo.getSbjkmx());
            request.setAttribute(Constants.MESSAGE_KEY,
                                 "缴款书数据创建成功，请点击“保存”按钮完成补录操作");
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        return mapping.findForward("save");

    }


    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        JksForm aForm = (JksForm) form;
        aForm.setJksData();

        FhzJksBo bo = new FhzJksBo();
        bo.setSbbh(aForm.getSbbh());

        bo.setSbjkzb(aForm.getSbjkzb());
        bo.setSbjkmx(aForm.getSbjkmx());

        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //构造后台需要的条件数据对象
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(ud);
        vo.setAction(ActionType.INSERT);

        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "补录汇总生成的缴款书成功!");
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
        } finally {
            return mapping.findForward("save");
        }
    }


    public ActionForward doBack(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        // 删除session中的form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);

        return mapping.findForward("back");
    }


}
