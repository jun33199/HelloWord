package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlAllSbWszForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BlJksForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
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
public class BlSbAction extends AddBaseAction {

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
        BlAllSbWszForm aForm = (BlAllSbWszForm) form;
        BlJksForm bForm = (BlJksForm) session.getAttribute("blJksForm");
        try {

            aForm.setJksh(bForm.getJksBo().getSbjkzb().getJkpzh());
            aForm.setZwbs(bForm.getJksBo().getSbjkzb().getZwbs());
            aForm.setType(bForm.getType());
            aForm.setZbxh(bForm.getZbxh());
            aForm.setSklxdm(bForm.getSklxdm());
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "补录显示成功！");
        return mapping.findForward("show");

    }


    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doChangeBllx(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Debug.out("look what form it is:");
        Debug.out(form);
        BlAllSbWszForm blAllSbWszForm = (BlAllSbWszForm) form;
        if (blAllSbWszForm.getBllx().equals("1")) { //汇总
            // 保存Token;
            saveToken(request);
        }
        return mapping.findForward("showhz");

    }


    /**
     * 保存记录
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((BaseForm)form).getData();
     * 3. 调用BaseProxy的add方法，向数据库中增加一条记录。
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
     * 4. 保存Token;
     *       saveToken();
     * 5. 转向成功后的界面。
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
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            BlAllSbWszForm aForm = (BlAllSbWszForm) form;
            // 从Form中获取对象。
            Object obj = aForm.getBo();

            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.BL_CHECK_SBJKS);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            Boolean success = (Boolean) QsglProxy.getInstance().process(vo);
            //验证申报金额和缴款书金额是否符合
            if (success.booleanValue() == false) {
                // 保存Token;
                saveToken(request);
                request.setAttribute(Constants.MESSAGE_KEY, "申报信息中的应纳税额和缴款书不符");
                return new ActionForward(mapping.getInput());
            } else {
                vo.setAction(ActionType.BL_CREATECONNECT_SBJKS);
                BlJksBo blJksBo = new BlJksBo();
                blJksBo.setJkpzh(aForm.getJksh());
                blJksBo.setSbbh(aForm.getSbbh());
                blJksBo.setType(aForm.getType());
                blJksBo.setZbxh(aForm.getZbxh());
                blJksBo.setSklxdm(aForm.getSklxdm());
                blJksBo.setZwbs(aForm.getZwbs());
                vo.setData(blJksBo);
                ArrayList sbhdList = (ArrayList) QsglProxy.getInstance().
                                     process(vo);
                aForm.setSbhdList(sbhdList);

                //将查询页面的补录成功的那条删去
                HttpSession session = request.getSession(false);
                BlJksForm queryForm = (BlJksForm) session.getAttribute(
                        "blJksForm");
                ArrayList delList = new ArrayList();
                delList.add(queryForm.getRemoveBo());
                queryForm.removeSelectedData(delList);

                // 保存Token;
                saveToken(request);
                if (sbhdList != null && sbhdList.size() > 0) {
                    request.setAttribute(Constants.MESSAGE_KEY, "显示对应核定通知书号");
                    // 转向成功后的界面。
                    return mapping.findForward("sbhd");

                } else {
                    //调用父类方法将Form对象从Session中去掉。
                    removeForm(mapping, request);
                    request.setAttribute(Constants.MESSAGE_KEY, "补录成功");
                    // 转向成功后的界面。
                    return mapping.findForward("save");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return new ActionForward(mapping.getInput());
        }
    }

}
