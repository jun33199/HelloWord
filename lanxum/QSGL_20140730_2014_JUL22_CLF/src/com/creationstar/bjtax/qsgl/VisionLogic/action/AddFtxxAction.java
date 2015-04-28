package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.FtxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
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
public class AddFtxxAction extends AddBaseAction {
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
        FtxxForm ftxxForm = (FtxxForm) form;
		String sfesf = "00";
        if (ftxxForm.getPerson().equals("true")) { //录入个人房屋信息
            SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
            ftxxForm.setSbbh(sbGrForm.getSbbh());
            //yangxiao 2008-12-06 start
            ftxxForm.setSmbs(sbGrForm.getSmbs());
            ftxxForm.setHyqdsj(sbGrForm.getTime());
            ftxxForm.setTdfwqszylxdm(sbGrForm.getDivertType());
            ftxxForm.setTdfwzldz(sbGrForm.getAddress());
            ftxxForm.setFwjzmj(sbGrForm.getArea());
//            ftxxForm.setFwlbdm(sbGrForm.tenementType);
            ftxxForm.setCjjgyrmb(sbGrForm.rmbPrice);
            ftxxForm.setSbrq(sbGrForm.getSbrq());
			sfesf = sbGrForm.sfesf;
            //yangxiao 2008-12-06 end
            request.setAttribute(Constants.MESSAGE_KEY, "土地房屋信息显示成功！");
        } else {
            SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute("sbFgrForm");

            ftxxForm.setSbbh(sbFgrForm.getSbbh());
            //yangxiao 2008-12-06 start
            ftxxForm.setSmbs(sbFgrForm.getSmbs());
            ftxxForm.setHyqdsj(sbFgrForm.getTime());
            ftxxForm.setTdfwqszylxdm(sbFgrForm.getDivertType());
            ftxxForm.setTdfwzldz(sbFgrForm.getAddress());
            ftxxForm.setFwjzmj(sbFgrForm.getArea());
//            ftxxForm.setFwlbdm(sbFgrForm.tenementType);
            ftxxForm.setCjjgyrmb(sbFgrForm.rmbPrice);
            ftxxForm.setSbrq(sbFgrForm.getSbrq());
			sfesf = sbFgrForm.sfesf;
            //yangxiao 2008-12-06 end

        }
        //yangxiao 2008-12-06 start 设置是否为2手房
        if (ftxxForm.smbs != null && !ftxxForm.smbs.equals("") &&
            ftxxForm.smbs.equals("1")) {
            ftxxForm.setSfesf(sfesf);
        }
        //yangxiao 2008-12-06 end

        ftxxForm.setTdfwqszylxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.TDFWQSZY));
        ftxxForm.setFlList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.TDFWYT));
        ftxxForm.setFwlbList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.FWLX));
        ftxxForm.setBzList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.BZ));
        // 增加房屋所在区域代码表
        ftxxForm.setTdjcList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.SZQY));
        doGetHL(mapping, form, request, response);

        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "土地房屋信息显示成功！");
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
            //
            FtxxForm ftxxForm = (FtxxForm) form;
            System.out.println("税额调整=" + ftxxForm.getSetz());
            // 从Form中获取对象。
            Tufwxx obj = (Tufwxx) ((FtxxForm) form).getData();
            Debug.out("AddFtxxAction doAdd form.getData() obj.class is " +
                      obj.getClass().getName());
            FtxxForm ftForm = (FtxxForm) form;
//             if(ftForm.getHyqdsj().compareTo("20050601")<0)
//             {
//                 obj.setSetz(Constants.SETZ_ZC);
//             }
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
            //FtxxForm ftxxForm = (FtxxForm) form;

            QsglProxy.getInstance().process(vo);

            // 保存Token;
            saveToken(request);

            //将新增加的公有住房信息加到 申报form中
            String person = ftxxForm.getPerson();

            // 转向成功后的界面。
            if (person.equals("true")) {
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                sbGrForm.setVoTufwxx(obj);
                sbGrForm.setFwjbxxAdded(true);
                session.setAttribute("sbGrForm", sbGrForm);
                request.setAttribute(Constants.MESSAGE_KEY, "土地房屋信息保存成功！");
                // 删除session中的form
                removeForm(mapping, request);
                return mapping.findForward("savegr");
            } else {
                SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                        "sbFgrForm");
                sbFgrForm.setVoTufwxx(obj);
                sbFgrForm.setFwjbxxAdded(true);

                session.setAttribute("sbFgrForm", sbFgrForm);
                if (needAlertJms(sbFgrForm.getNsrlxdm(), obj.getFldm()) &&
                    (sbFgrForm.getHdtzszh() == null ||
                     sbFgrForm.getHdtzszh().equals(""))) {
                    String msg = "请提示纳税人：请到税务机关办理减免税手续再办理纳税申报！";
                    sbFgrForm.setAlertMessage(msg);
                    request.setAttribute(Constants.MESSAGE_KEY, msg);
                } else {
                    request.setAttribute(Constants.MESSAGE_KEY, "土地房屋信息保存成功！");
                }
                // 删除session中的form
                removeForm(mapping, request);
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
            FtxxForm ftForm = (FtxxForm) form;
            String bzdm = ftForm.getBzdm();
            Wbhs wbhs = new Wbhs();
            wbhs.bzdm = bzdm;
            Calendar cd = Calendar.getInstance();
            cd.add(Calendar.MONTH, -1);
            wbhs.nd = String.valueOf(cd.get(Calendar.YEAR));
            wbhs.yf = String.valueOf(cd.get(Calendar.MONTH));
            Debug.out("get wbhl bzdm: " + bzdm);
            Debug.out("get wbhl nd  : " + wbhs.nd);
            Debug.out("get wbhl yf  : " + wbhs.yf);
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
        FtxxForm ftxxForm = (FtxxForm) form;

        if (ftxxForm.getPerson().equals("true")) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
        }

    }

    /**
     * 根据纳税人类型和房屋的分类判断是否需要办理减免税审批
     * @param nsrlx String
     * @param fldm String
     * @return boolean
     */
    private boolean needAlertJms(String nsrlx, String fldm) {
        //纳税人类型为国家机关、事业单位、社会团体、军事单位，
        //且分类属于办公、教学、医疗、科研、军事设施等情况的；
        if ((nsrlx.equals("01") || //国家机关
             nsrlx.equals("02") || //事业单位
             nsrlx.equals("03") || //社会团体
             nsrlx.equals("04") //军事单位
            ) &&
            (fldm.equals("01") || //办公
             fldm.equals("02") || //教学
             fldm.equals("03") || //医疗
             fldm.equals("04") || //科研
             fldm.equals("05") //军事设施
            )) {
            return true;
        }
        return false;
    }

}
