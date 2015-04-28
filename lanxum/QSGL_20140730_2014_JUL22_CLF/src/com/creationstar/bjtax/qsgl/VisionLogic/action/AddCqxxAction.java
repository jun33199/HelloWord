package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
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

public class AddCqxxAction extends AddBaseAction {
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
        CqxxForm cqxxForm = (CqxxForm) form;
        String entrypage = cqxxForm.getEntrypage();
        try {
            if ((entrypage != null) && (entrypage.equals("EDIT"))) { //修改申报页面
                SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");
                cqxxForm.setSbbh(sbxxForm.getSbbh());
            } else { //新增申报
                if (cqxxForm.getPerson().equals("true")) { //录入个人拆迁信息
                    SbGrForm sbGrForm = (SbGrForm) session.getAttribute(
                            "sbGrForm");
                    cqxxForm.setSbbh(sbGrForm.getSbbh());
                } else {
                    SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                            "sbFgrForm");
                    cqxxForm.setSbbh(sbFgrForm.getSbbh());
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
                obj = (Swjgzzjg) QsglProxy.getInstance().process(vo);
                cqxxForm.setFjmc(obj.getWsjc());
                cqxxForm.setNd(DateUtil.getDate().substring(0, 4));

            }
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "获取分局简称失败");
        }

        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "拆迁显示成功！");
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
            Object obj = ((CqxxForm) form).getData();
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
            CqxxForm cqxxForm = (CqxxForm) form;

            QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);

            //将新增加的拆迁信息加到 申报form中 目前没有用到
            String entrypage = cqxxForm.getEntrypage();
            if ((entrypage != null) && (entrypage.equals("EDIT"))) { //修改申报页面
                SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");
                sbxxForm.getCqList().add(obj);
                if (sbxxForm.getVoSpjgxx() != null &&
                    sbxxForm.getVoSpjgxx().getHdtzszh() != null &&
                    !sbxxForm.getVoSpjgxx().getHdtzszh().equals("")) {
                    request.setAttribute(Constants.MESSAGE_KEY, "拆迁保存成功！");
                } else {
                    String msg = "请提示纳税人：请到税务机关办理减免税手续再办理纳税申报！";
                    sbxxForm.setAlertMessage(msg);
                    request.setAttribute(Constants.MESSAGE_KEY, msg);
                }

                return mapping.findForward("savemod");
            } else {
                String person = cqxxForm.getPerson();
                Debug.out("is person: " + person);
                if (person.equals("true")) {
                    SbGrForm sbGrForm = (SbGrForm) session.getAttribute(
                            "sbGrForm");
                    sbGrForm.cqList.add(obj);
                    session.setAttribute("sbGrForm", sbGrForm);
                    // 转向成功后的界面。
                    request.setAttribute(Constants.MESSAGE_KEY, "拆迁保存成功！");
                    return mapping.findForward("savegr");
                } else {
                    SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                            "sbFgrForm");
                    sbFgrForm.cqList.add(obj);
                    session.setAttribute("sbFgrForm", sbFgrForm);
                    if (sbFgrForm.getHdtzszh() != null &&
                        !sbFgrForm.getHdtzszh().equals("")) {
                        request.setAttribute(Constants.MESSAGE_KEY, "拆迁保存成功！");
                    } else {
                        String msg = "请提示纳税人：请到税务机关办理减免税手续再办理纳税申报！";
                        sbFgrForm.setAlertMessage(msg);
                        request.setAttribute(Constants.MESSAGE_KEY, msg);
                    }
                    return mapping.findForward("savefgr");
                }
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
            Object obj = ((CqxxForm) form).getData();
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
            CqxxForm cqxxForm = (CqxxForm) form;

            Jsblcq jsblcq = (Jsblcq) QsglProxy.getInstance().process(vo);
            if (jsblcq != null) {
                cqxxForm.setData(jsblcq);
                cqxxForm.setFirst(false);
                request.setAttribute(Constants.MESSAGE_KEY, "请录入本次使用补偿额！");
            } else {
                cqxxForm.setFirst(true);
                cqxxForm.setBcqfwzldz("");
                cqxxForm.setBcsybce("0");
                cqxxForm.setCqbce("0");
                cqxxForm.setCqbcsye("0");
                cqxxForm.setSyeywbz(Constants.JSBL_SYEYWBZ_WEIYONGWAN);
                request.setAttribute(Constants.MESSAGE_KEY, "指定拆迁信息不存在！");
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
        CqxxForm cqxxForm = (CqxxForm) form;

        String entrypage = cqxxForm.getEntrypage();
        if ((entrypage != null) && (entrypage.equals("EDIT"))) { //修改申报页面
            return mapping.findForward("returnmod");
        } else { //新增申报
            if (cqxxForm.getPerson().equals("true")) {
                return mapping.findForward("returngr");
            } else {
                return mapping.findForward("returnfgr");
            }
        }
    }


}
