package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.FwjhxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.ParameterUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class AddFwjhxxAction extends AddBaseAction {
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
        FwjhxxForm fwjhxxForm = (FwjhxxForm) form;
        fwjhxxForm.clear();
        fwjhxxForm.setTdfwqszylxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.TDFWQSZY));
        fwjhxxForm.setSfzjlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.ZJLX));
        // 增加房屋所属区域代码表
        fwjhxxForm.setTdjcList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.SZQY));

        if (fwjhxxForm.getJhperson().equals("0")) { //geren
            fwjhxxForm.setFlList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.BZ_TDFWYT_GR));
        } else {
            fwjhxxForm.setFlList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.BZ_TDFWYT_FGR));
        }
        fwjhxxForm.setFwlbList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.FWLX));
        fwjhxxForm.setBzList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.BZ));
        fwjhxxForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        fwjhxxForm.setNsrlxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.NSRLX));
        Debug.out("person=" + fwjhxxForm.getJhperson());
        fwjhxxForm.setGjdqList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.GJ));

        if (fwjhxxForm.isPerson()) { //录入个人拆迁信息
            SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
            fwjhxxForm.setSbbh(sbGrForm.getSbbh());
        } else {
            SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute("sbFgrForm");
            fwjhxxForm.setSbbh(sbFgrForm.getSbbh());
        }

        doGetHL(mapping, form, request, response);
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "房屋交换显示成功！");
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
    public ActionForward doChangePerson(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Debug.out("look what form it is:");
        Debug.out(form);
        FwjhxxForm fwjhxxForm = (FwjhxxForm) form;
        if (fwjhxxForm.getJhperson().equals("0")) { //geren
            fwjhxxForm.setFlList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.BZ_TDFWYT_GR));
            fwjhxxForm.setJkfsdm("01");
            fwjhxxForm.setNsrList(new ArrayList());
            fwjhxxForm.setCqrJs(Constants.CQRJS_INIT);
        } else {
            fwjhxxForm.setFlList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.BZ_TDFWYT_FGR));
            fwjhxxForm.setJkfsdm("03");
        }

        // 保存Token;
        saveToken(request);
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
            FwjhxxForm fwjhxxForm = (FwjhxxForm) form;
            String[][] dataSource_grxx = ParameterUtil.
                                         getTableDataSource(request,
                    "dataSource_gm", 9);
            List l = ActionUtil.getGrData(dataSource_grxx,
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.ZJLXMAP),
                                          ActionUtil.getCodeMaps(request.
                    getSession(false).
                    getServletContext(), Constants.GJMAP));
            FwjhxxBo obj = (FwjhxxBo) ((FwjhxxForm) form).getData(l);
            Debug.out("AddFwjhxxAction doAdd form.getData() obj.class is " +
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

            QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);
            removeForm(mapping, request);
            request.setAttribute(Constants.MESSAGE_KEY, "房屋交换保存成功！");

            if (fwjhxxForm.isPerson()) {
                //将新增加的公有住房信息加到 申报form中
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                //将新增加的个人住房交换信息加到 申报form中
                sbGrForm.setFwjhxxBo(obj);
                sbGrForm.setFwjhAdded(true);
                session.setAttribute("sbGrForm", sbGrForm);
                // 转向成功后的界面。
                return mapping.findForward("savegr");
            } else {
                SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                        "sbFgrForm");
                //将新增加的个人住房交换信息加到 申报form中
                sbFgrForm.setFwjhxxBo(obj);
                sbFgrForm.setFwjhAdded(true);
                session.setAttribute("sbFgrForm", sbFgrForm);
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
     * 根据计算机代码获取个人信息，开户银行等
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetNsrxx(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        FwjhxxForm aForm = (FwjhxxForm) form;

        try {
            HashMap djinfo = CommonUtil.getFgrDjInfo(aForm.getJsjdm());
            SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
            Debug.out("jbsj: " + jbsj);
            aForm.setFgrnsrmc(jbsj.getNsrmc());
            Debug.out("jbsj: " + jbsj.getNsrmc());
            ArrayList yhList = (ArrayList) djinfo.get("YHZH");
            if ((yhList != null) && (yhList.size() > 0)) {
                for (int i = 0; i < yhList.size(); i++) {
                    YHZH yhzh = (YHZH) yhList.get(i);
                    String khyhmc = yhzh.getKhyhmc();
                    khyhmc = khyhmc + "--" + yhzh.getZh();
                    yhzh.setKhyhmc(khyhmc);
                }
            }
            aForm.setKhyhList(yhList);
            Debug.out("form jhperson value = " + aForm.getJhperson());
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, "取得纳税人信息失败！");
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }

        request.setAttribute(Constants.MESSAGE_KEY, "取得纳税人信息成功！");
        // 保存Token;
        saveToken(request);
        return mapping.findForward("show");

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
        FwjhxxForm fwjhxxForm = (FwjhxxForm) form;

        if (fwjhxxForm.isPerson()) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
        }

    }

    /**
     * 获取汇率
     *
     * 1. 调用父类方法处理Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. 从Form中获取对象。
     *     Object obj = ((BaseForm)form).getData();
     * 3.
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
    public ActionForward doGetHL(ActionMapping mapping,
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
            FwjhxxForm ftForm = (FwjhxxForm) form;
            String bzdm = ftForm.getBzdm();
            Wbhs wbhs = new Wbhs();
            wbhs.bzdm = bzdm;
            Calendar cd = Calendar.getInstance();
            cd.add(Calendar.MONTH, -1);
            wbhs.nd = String.valueOf(cd.get(Calendar.YEAR));
            wbhs.yf = String.valueOf(cd.get(Calendar.MONTH));
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET_HL);
            vo.setProcessor(prop.getProperty(wbhs.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(wbhs);

            wbhs = (Wbhs) QsglProxy.getInstance().process(vo);

            ftForm.setHn(DataConvert.BigDecimal2String(wbhs.getWhpj(), 5));

            if (wbhs.getWhpj() == null) {
                request.setAttribute(Constants.MESSAGE_KEY, "指定的外币的汇率不存在！");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "获取外币汇率成功！");
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
