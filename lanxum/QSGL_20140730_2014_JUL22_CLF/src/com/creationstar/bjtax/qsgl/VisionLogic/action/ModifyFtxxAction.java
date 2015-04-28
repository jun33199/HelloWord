package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.FtxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
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

public class ModifyFtxxAction extends BaseAction {
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");
        FtxxForm ftxxForm = (FtxxForm) form;

        //获取选中的土地住房信息
        Sbzb sbzb = (Sbzb) (sbxxForm.getVoSbzb());
        Tufwxx tufwxx = (Tufwxx) (sbxxForm.getVoTufwxx());
        tufwxx.setSbbh(sbxxForm.getSbbh());
        ftxxForm.setSbrq(sbxxForm.getSbrq());
        ftxxForm.setData(tufwxx);
        ftxxForm.setSetz(sbzb.setz);

        ftxxForm.setTdfwqszylxList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.TDFWQSZY));
        if (ftxxForm.isBzqs()) { //不征的房土信息修改
            //区分是个人进来的还是非个人进来的
            if (ftxxForm.getPerson().equals("false")) {
                ftxxForm.setFlList(ActionUtil.getCodeTables(session.
                        getServletContext(),
                        Constants.BZ_TDFWYT_FGR));
            } else {
                ftxxForm.setFlList(ActionUtil.getCodeTables(session.
                        getServletContext(),
                        Constants.BZ_TDFWYT_GR));
            }
        } else { //征收
            ftxxForm.setFlList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.TDFWYT));
        }
        ftxxForm.setFwlbList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.FWLX));
        ftxxForm.setBzList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.BZ));

        //doGetHL(mapping,form,request,response);

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
            // 从Form中获取对象。
            FtxxForm ftxxForm = (FtxxForm) form;
            Tufwxx obj = (Tufwxx) ftxxForm.getData();

//            if (ftxxForm.getHyqdsj().compareTo("20050601") < 0)
//            {
//                obj.setSetz(Constants.SETZ_ZC);
//
//            }

            Debug.out("ModifyFtxxAction doSave form.getData() obj.class is " +
                      obj.getClass().getName());
            //调用QsglProxy的add方法，向数据库中增加一条记录。
            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.MODIFY);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            HttpSession session = request.getSession(false);
            SbxxForm sbxxForm = (SbxxForm) session.getAttribute("sbxxForm");

            QsglProxy.getInstance().process(vo);

            //将修改后的土地房屋信息更新到 申报form中
            sbxxForm.setVoTufwxx((Tufwxx) obj);
//            if (ftxxForm.getHyqdsj().compareTo("20050601") < 0)
//            {
//                sbxxForm.getVoSbzb().setSetz(Constants.SETZ_ZC);
//            }
//            else
//            {
            sbxxForm.getVoSbzb().setSetz(ftxxForm.getSetz());
//            }
            session.setAttribute("sbxxForm", sbxxForm);
            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            request.setAttribute(Constants.MESSAGE_KEY, "土地房屋信息修改成功！");
            session.removeAttribute(mapping.getAttribute());
            Debug.out("find save page: " + mapping.findForward("save").getPath());
            return mapping.findForward("save");
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
}
