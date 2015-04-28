package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.OperationType;
import com.creationstar.bjtax.qsgl.model.bo.JmsbBo;
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
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class AddJmsbAction extends AddBaseAction {
    /**
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        JmsbForm aForm = (JmsbForm) form;
        String path = mapping.getPath();
        Debug.out("mapping.getPath() is " + path);

        try {

            // 从context获取代码表信息,获取代码map,
            // 从中得到包括纳税人类型、身份证件类型、币种、房屋类别、分类和土地、房屋权属转移类型的arraylist
            aForm.setNsrlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.NSRLX));
            aForm.setSfzjlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.ZJLX));
            aForm.setBzList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.BZ));
            aForm.setFwlbList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.FWLX));


            //zzb 20090820 modify begin   减免
            aForm.setFlList(ActionUtil.getCodeTables(session
                   .getServletContext(), Constants.TDFWYTJM));
            //zzb 20090820 modify end


            aForm.setTdfwqszylxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.TDFWQSZY));
            aForm.setGjdqList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.GJ));

            // aForm.setQsjmlbList(ActionUtil.getCodeTables(session
            // .getServletContext(), Constants.JMZC));

            // 区分是个人进来的还是非个人进来的
            if (path.equals("/jmsb/addJmsbFgr")) {
                aForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_FGR));
            } else {
                aForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_GR));
            }
            //设置契税文号下拉
            aForm.setQsjmxzList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.QS_JMXZ));
            
            aForm.setYmczlxdm(OperationType.INSERT);
            doGetHL(mapping, form, request, response);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "减免申报显示成功！");
        return mapping.findForward("show");

    }

    /* (non-Javadoc)
     * @see com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction#doSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            // 调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            JmsbForm aForm = (JmsbForm) form;
            // 从Form中获取对象。
            String[][] dataSource_grxx = ParameterUtil.getTableDataSource(
                    request, "dataSource_gm", 9);

            List l = ActionUtil.getGrData(dataSource_grxx, ActionUtil
                                          .getCodeMaps(request.getSession(false).
                    getServletContext(),
                    Constants.ZJLXMAP), ActionUtil.getCodeMaps(request
                    .getSession(false).getServletContext(), Constants.GJMAP));

            String[] jmSelect = aForm.getQsjmlbSelect();

            StringBuffer qsjmlbmc = new StringBuffer();
            for (int i = 0; i < jmSelect.length; i++) {
                System.out.println(
                        "===============================================");
                System.out.println("所选择的减免类型：" + jmSelect[i]);
                if (i > 0) {
                    qsjmlbmc.append("；<br>");
                }
                HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
                        .getServletContext(), Constants.JMZCMAP);
                Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(jmSelect[i]);
                qsjmlbmc.append(qsjmlbVo.getQsjmlbmc());

            }

            System.out.println("其它减免理由备注：" + aForm.getQtjmlybeizhu());

            aForm.setQsjmlbmc(qsjmlbmc.toString());

            JmsbBo bo = (JmsbBo) aForm.getData(l);
            Debug.out("AddBaseAction doAdd form.getData() obj.class is "
                      + bo.getClass().getName());
            // 调用QsglProxy的add方法，向数据库中增加一条记录。
            // 获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // 构造向后台传输用的VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            /** @todo 加入返回值得判断，以告诉页面是否成功 */
            bo = (JmsbBo) QsglProxy.getInstance().process(vo);
            // 给view页面现在没有的数据负值
            aForm.setSbbh(bo.getSbbh());
            aForm.setCjrq(bo.getCjrq());
            // 保存Token;
            saveToken(request);

            // 转向成功后的view界面。
            request.setAttribute(Constants.MESSAGE_KEY, "减免申报保存成功！");

            // 格式化view页面中的信息
            Timestamp time = DataConvert.String2Timestamp(aForm.getHyqdsj());
            aForm.setHyqdsj(DataConvert.TimeStamp2String(time));

            BigDecimal zymj = DataConvert.String2BigDecimal(aForm
                    .getTdfwqszymj());
            aForm.setTdfwqszymj(DataConvert.BigDecimal2String(zymj));

            BigDecimal jzmj = DataConvert.String2BigDecimal(aForm.getFwjzmj());
            aForm.setFwjzmj(DataConvert.BigDecimal2String(jzmj));

            BigDecimal cjjg = DataConvert
                              .String2BigDecimal(aForm.getCjjgyrmb());
            aForm.setCjjgyrmb(DataConvert.BigDecimal2String(cjjg));

            BigDecimal pgjg = DataConvert.String2BigDecimal(aForm.getPgjg());
            aForm.setPgjg(DataConvert.BigDecimal2String(pgjg));

            BigDecimal cjjgwb = DataConvert.String2BigDecimal(aForm
                    .getCjjgywb());
            aForm.setCjjgywb(DataConvert.BigDecimal2String(cjjgwb));

            BigDecimal hl = DataConvert.String2BigDecimal(aForm.getHn());
            aForm.setHn(DataConvert.BigDecimal2String(hl, 5));

            BigDecimal zhyrmb = DataConvert
                                .String2BigDecimal(aForm.getZhyrmb());
            aForm.setZhyrmb(DataConvert.BigDecimal2String(zhyrmb));

            return mapping.findForward("view");
        } catch (BaseException te) {
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
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doReturnPri(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        JmsbForm aForm = (JmsbForm) form;
        Debug.out("return pri jinru");
        aForm.clear();
        doGetHL(mapping, form, request, response);

        // 保存Token;
        saveToken(request);
        if (aForm.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            request.setAttribute(Constants.MESSAGE_KEY, "减免申报显示成功！");
            return mapping.findForward("printreturngr");
        } else {
            request.setAttribute(Constants.MESSAGE_KEY, "减免申报显示成功！");
            return mapping.findForward("printreturnfgr");
        }

    }

    /**
     * 根据计算机代码获取个人信息，开户银行等
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGetNsrxx(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        JmsbForm aForm = (JmsbForm) form;
        // 保存Token;
        saveToken(request);
        try {
            // 清除form,
            aForm.setNsrmc("");
            aForm.setKhyhList(new ArrayList());
            aForm.setYhzh("");
            // 获取新信息
            HashMap djinfo = CommonUtil.getFgrDjInfo(aForm.getJsjdm());
            SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
            aForm.setNsrmc(jbsj.getNsrmc());
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
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }

        request.setAttribute(Constants.MESSAGE_KEY, "取得纳税人信息成功！");
        return mapping.findForward("show");

    }

    /**
     * 获取汇率
     *
     * 1. 调用父类方法处理Token. ActionForward forward = doHandleToken(request); if
     * (forward != null) return forward; 2. 从Form中获取对象。 Object obj =
     * ((BaseForm)form).getData(); 3. 4. 保存Token; saveToken(); 5. 转向成功后的界面。
     * return mapping.findForward("add");
     *
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request (if any)
     * @param request
     *            The HTTP request we are processing
     * @param response
     *            The HTTP response we are creating
     * @return ActionForward
     */
    public ActionForward doGetHL(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            // 调用父类方法处理Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // 从Form中获取对象。
            JmsbForm ftForm = (JmsbForm) form;
            String bzdm = ftForm.getBz();
            Wbhs wbhs = new Wbhs();
            wbhs.bzdm = bzdm;
            Calendar cd = Calendar.getInstance();
            cd.add(Calendar.MONTH, -1);
            wbhs.nd = String.valueOf(cd.get(Calendar.YEAR));
            wbhs.yf = String.valueOf(cd.get(Calendar.MONTH));
            // 获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // 构造向后台传输用的VoPackage
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
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doMod(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        JmsbxxForm jmsbxxForm = (JmsbxxForm) session.getAttribute("jmsbxxForm");
        JmsbForm jmsbForm = (JmsbForm) form;
        String path = mapping.getPath();
        Debug.out("mapping.getPath() is " + path);

        try {

            // 从context获取代码表信息,获取代码map,
            // 从中得到包括纳税人类型、身份证件类型、币种、房屋类别、分类和土地、房屋权属转移类型的arraylist
            jmsbForm.setNsrlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.NSRLX));
            jmsbForm.setSfzjlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.ZJLX));
            jmsbForm.setBzList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.BZ));
            jmsbForm.setFwlbList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.FWLX));

            //zzb 20090820 modify begin
            jmsbForm.setFlList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.TDFWYTJM));
            //zzb 20090820 modify  end



            jmsbForm.setTdfwqszylxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.TDFWQSZY));
            jmsbForm.setGjdqList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.GJ));

            // aForm.setQsjmlbList(ActionUtil.getCodeTables(session
            // .getServletContext(), Constants.JMZC));

            // 区分是个人进来的还是非个人进来的
            if (path.equals("/jmsb/addJmsbFgr")) {
                jmsbForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_FGR));
            } else {
                jmsbForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_GR));
            }
            //设置契税文号下拉
            jmsbForm.setQsjmxzList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.QS_JMXZ));

            doGetHL(mapping, form, request, response);

            jmsbForm.bz = jmsbxxForm.getVoTufwxx().bz;
            jmsbForm.bzmc = jmsbxxForm.getVoTufwxx().bzmc;
            jmsbForm.cjjgyrmb = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().cjjgrmb, 2, false);
            jmsbForm.cjjgywb = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().cjjgwb, 2, false);
            jmsbForm.fdcxmmc = jmsbxxForm.getVoTufwxx().fdcxmmc;
            jmsbForm.fl = jmsbxxForm.getVoTufwxx().fldm;
            jmsbForm.flmc = jmsbxxForm.getVoTufwxx().flmc;
            jmsbForm.fwjzmj = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().fwjzmj, 2, false);
            jmsbForm.fwlb = jmsbxxForm.getVoTufwxx().fwlxdm;
            jmsbForm.fwlbmc = jmsbxxForm.getVoTufwxx().fwlxdm;
            jmsbForm.hn = DataConvert.BigDecimal2String(jmsbxxForm.getVoTufwxx().
                    hldm, 2, false);
            jmsbForm.hyqdsj = DataConvert.TimeStamp2String(jmsbxxForm.
                    getVoTufwxx().htqdsj);

            jmsbForm.pgjg = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().pgjgrmb, 2, false);
            jmsbForm.tdfwqszylx = jmsbxxForm.getVoTufwxx().tdfwqszylx;
            jmsbForm.tdfwqszylxmc = jmsbxxForm.getVoTufwxx().tdfwqszymc;
            jmsbForm.tdfwqszymj = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().tdfwqszymj, 2, false);
            jmsbForm.tdfwzldz = jmsbxxForm.getVoTufwxx().tdfwzldz;
            jmsbForm.zhyrmb = DataConvert.BigDecimal2String(jmsbxxForm.
                    getVoTufwxx().zhjgrmb, 2, false);
            jmsbForm.beizhu = jmsbxxForm.getVoSbzb().bz;
            jmsbForm.fwtdbmslh = jmsbxxForm.getVoTufwxx().fwtdbmdm;

            jmsbForm.ztbs = jmsbxxForm.getVoSbzb().ztbs; // 前台页面上获得
            jmsbForm.yhbs = jmsbxxForm.getVoSbzb().yhbs; //用户标识
            jmsbForm.setRjl(jmsbxxForm.getVoTufwxx().rjl);
            jmsbForm.setTdjc(jmsbxxForm.getVoTufwxx().tdjc);
            jmsbForm.setSbrq(DataConvert.TimeStamp2String(jmsbxxForm.getVoSbzb().
                    sbrq));

            jmsbForm.setSbbh(jmsbxxForm.getVoSbzb().sbbh);

            // 契税减免类别
            jmsbForm.qsjmlb = jmsbxxForm.qsjmlb;
            jmsbForm.qsjmlbmc = jmsbxxForm.qsjmlbmc;
            jmsbForm.qtjmlybeizhu = jmsbxxForm.qtjmlybeizhu;
            jmsbForm.qsjmlbSelect = jmsbxxForm.qsjmlbSelect;
            if(jmsbForm.qsjmlb==null && jmsbForm.qsjmlbSelect!=null && jmsbForm.qsjmlbSelect.length>0){
            	jmsbForm.qsjmlb = jmsbForm.qsjmlbSelect[0];
            }
            jmsbForm.qsjmxzdm = jmsbxxForm.jmxzdm;

            if (jmsbForm.yhbs.equals(Constants.YHBZ_FGR)) { //非个人
                jmsbForm.lxdh = jmsbxxForm.getVoFgrxx().lxdh;
                jmsbForm.setNsrmc(jmsbxxForm.getVoFgrxx().nsrmc);
                jmsbForm.setNsrlx(jmsbxxForm.getVoFgrxx().nsrlxdm);
                jmsbForm.nsrlxmc = jmsbxxForm.getVoFgrxx().nsrlxmc;
                jmsbForm.lxrxm = jmsbxxForm.getVoFgrxx().lxrxm;
                jmsbForm.setJsjdm(jmsbxxForm.getVoFgrxx().jsjdm);
                jmsbForm.khyhdm = jmsbxxForm.getVoFgrxx().khyhdm;
                jmsbForm.khyhmc = jmsbxxForm.getVoFgrxx().khyhmc;
                jmsbForm.yhzh = jmsbxxForm.getVoFgrxx().yhzh;

                HashMap djinfo = CommonUtil.getFgrDjInfo(jmsbForm.getJsjdm());
                SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
                jmsbForm.setNsrmc(jbsj.getNsrmc());
                ArrayList yhList = (ArrayList) djinfo.get("YHZH");

                if ((yhList != null) && (yhList.size() > 0)) {
                    for (int i = 0; i < yhList.size(); i++) {
                        YHZH yhzh = (YHZH) yhList.get(i);
                        String khyhmc = yhzh.getKhyhmc();
                        khyhmc = khyhmc + "--" + yhzh.getZh();
                        yhzh.setKhyhmc(khyhmc);
                    }
                }

                jmsbForm.setKhyhList(yhList);
            }
            if (jmsbForm.yhbs.equals(Constants.YHBZ_GR)) { //个人
                jmsbForm.lxdh = jmsbxxForm.getVoGrxx().lxdh;
                jmsbForm.setNsrmc(jmsbxxForm.getVoGrxx().nsrmc);
                jmsbForm.setJsjdm(jmsbxxForm.getVoGrxx().jsjdm);
                jmsbForm.nsrList = jmsbxxForm.nsrList;
                Debug.out("ActionUtil.displayMNsrDS(jmsbForm.nsrList) is :" +
                          ActionUtil.displayMNsrDS(jmsbForm.nsrList));
                jmsbForm.setCqrJs(ActionUtil.displayMNsrDS(jmsbForm.nsrList));
                jmsbForm.nsrlxList = jmsbxxForm.nsrlxList;
            }

            jmsbForm.setYmczlxdm(OperationType.MODIFY);

        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // 保存Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "减免申报显示成功！");
        return mapping.findForward("show");

    }


}
