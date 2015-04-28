package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.YggyzfForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AddYggyzfAction extends AddBaseAction {
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
        Debug.out("look what form it is:");
        Debug.out(form);
        YggyzfForm yggyzfForm = (YggyzfForm) form;

        if (yggyzfForm.isPerson()) { //录入个人拆迁信息
            SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
            yggyzfForm.setSbbh(sbGrForm.getSbbh());
        } else {
            SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute("sbFgrForm");
            yggyzfForm.setSbbh(sbFgrForm.getSbbh());
        }
        //构造向后台传输用的VoPackage
        Properties prop = (Properties) request.getSession(false).
                          getServletContext().getAttribute(Constants.
                PROCESSOR_MAP_FILE_NAME);
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.GET);
        Swjgzzjg obj = new Swjgzzjg();
        vo.setProcessor(prop.getProperty(obj.getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(obj);
        try {
            obj = (Swjgzzjg) QsglProxy.getInstance().process(vo);
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "获取分局简称失败");
        }

        yggyzfForm.setFjmc(obj.getWsjc());
        yggyzfForm.setNd(DateUtil.getDate().substring(0, 4));

        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "已购公有住房显示成功！");
        return mapping.findForward("show");

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
            // 从Form中获取对象。
            Object obj = ((YggyzfForm) form).getData();
            Debug.out("AddSbGrAction doAdd form.getData() obj.class is " +
                      obj.getClass().getName());
            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            HttpSession session = request.getSession(false);
            YggyzfForm yggyzfFrom = (YggyzfForm) form;

            QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);
            if (yggyzfFrom.isPerson()) {
                //将新增加的公有住房信息加到 申报form中
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                sbGrForm.gyList.add(obj);
                session.setAttribute("sbGrForm", sbGrForm);
                // 转向成功后的界面。
                request.setAttribute(Constants.MESSAGE_KEY, "已购公有住房保存成功！");
                return mapping.findForward("savegr");
            } else {
                SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                        "sbFgrForm");
                sbFgrForm.gyList.add(obj);
                session.setAttribute("sbFgrForm", sbFgrForm);
                request.setAttribute(Constants.MESSAGE_KEY, "已购公有住房保存成功！");
                return mapping.findForward("savefgr");
            }
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

    /**
     * 取消当前操作。
     * 转向取消后应去的页面。
     *
     *
     *  1. 保存Token;
     *     saveToken();
     *     return mapping.findForward("return");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 删除session中的form
        removeForm(mapping, request);
        // 保存Token;
        saveToken(request);
        YggyzfForm yggyzfForm = (YggyzfForm) form;

        if (yggyzfForm.isPerson()) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
        }

    }

    /**
     * 根据拆迁协议号,获取拆迁信息
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
    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            //调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // 从Form中获取对象。
            Object obj = ((YggyzfForm) form).getData();
            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            HttpSession session = request.getSession(false);
            YggyzfForm yggyzfForm = (YggyzfForm) form;

            Jsblgyzf jsblgyzf = (Jsblgyzf) QsglProxy.getInstance().process(vo);
            if (jsblgyzf != null) {
                yggyzfForm.setData(jsblgyzf);
                yggyzfForm.setFirst(false);
                request.setAttribute(Constants.MESSAGE_KEY, "请录入本次抵扣额！");
            } else {
                yggyzfForm.setFirst(true);
                yggyzfForm.setZldz("");
                yggyzfForm.setFwqszsh("");
                yggyzfForm.setJzmj("0");
                yggyzfForm.setBcdke("0");
                yggyzfForm.setBckdke("0");
                yggyzfForm.setCjjg("0");
                yggyzfForm.setSye("0");
                yggyzfForm.setCshtqdsj("");
                yggyzfForm.setSyeywbz(Constants.JSBL_SYEYWBZ_WEIYONGWAN);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "指定公有住房信息不存在，请直接录入。");
            }
            // 保存Token;
            saveToken(request);
            return mapping.findForward("show");

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
