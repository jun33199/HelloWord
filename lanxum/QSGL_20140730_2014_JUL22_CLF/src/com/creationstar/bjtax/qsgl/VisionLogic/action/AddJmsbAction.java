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

            // ��context��ȡ�������Ϣ,��ȡ����map,
            // ���еõ�������˰�����͡����֤�����͡����֡�������𡢷�������ء�����Ȩ��ת�����͵�arraylist
            aForm.setNsrlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.NSRLX));
            aForm.setSfzjlxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.ZJLX));
            aForm.setBzList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.BZ));
            aForm.setFwlbList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.FWLX));


            //zzb 20090820 modify begin   ����
            aForm.setFlList(ActionUtil.getCodeTables(session
                   .getServletContext(), Constants.TDFWYTJM));
            //zzb 20090820 modify end


            aForm.setTdfwqszylxList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.TDFWQSZY));
            aForm.setGjdqList(ActionUtil.getCodeTables(session
                    .getServletContext(), Constants.GJ));

            // aForm.setQsjmlbList(ActionUtil.getCodeTables(session
            // .getServletContext(), Constants.JMZC));

            // �����Ǹ��˽����Ļ��ǷǸ��˽�����
            if (path.equals("/jmsb/addJmsbFgr")) {
                aForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_FGR));
            } else {
                aForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_GR));
            }
            //������˰�ĺ�����
            aForm.setQsjmxzList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.QS_JMXZ));
            
            aForm.setYmczlxdm(OperationType.INSERT);
            doGetHL(mapping, form, request, response);

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�����걨��ʾ�ɹ���");
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
            // ���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            JmsbForm aForm = (JmsbForm) form;
            // ��Form�л�ȡ����
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
                System.out.println("��ѡ��ļ������ͣ�" + jmSelect[i]);
                if (i > 0) {
                    qsjmlbmc.append("��<br>");
                }
                HashMap jmsbbMap = (HashMap) ActionUtil.getCodeMaps(session
                        .getServletContext(), Constants.JMZCMAP);
                Qsjmlb qsjmlbVo = (Qsjmlb) jmsbbMap.get(jmSelect[i]);
                qsjmlbmc.append(qsjmlbVo.getQsjmlbmc());

            }

            System.out.println("�����������ɱ�ע��" + aForm.getQtjmlybeizhu());

            aForm.setQsjmlbmc(qsjmlbmc.toString());

            JmsbBo bo = (JmsbBo) aForm.getData(l);
            Debug.out("AddBaseAction doAdd form.getData() obj.class is "
                      + bo.getClass().getName());
            // ����QsglProxy��add�����������ݿ�������һ����¼��
            // ��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // �������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            /** @todo ���뷵��ֵ���жϣ��Ը���ҳ���Ƿ�ɹ� */
            bo = (JmsbBo) QsglProxy.getInstance().process(vo);
            // ��viewҳ������û�е����ݸ�ֵ
            aForm.setSbbh(bo.getSbbh());
            aForm.setCjrq(bo.getCjrq());
            // ����Token;
            saveToken(request);

            // ת��ɹ����view���档
            request.setAttribute(Constants.MESSAGE_KEY, "�����걨����ɹ���");

            // ��ʽ��viewҳ���е���Ϣ
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

        // ����Token;
        saveToken(request);
        if (aForm.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            request.setAttribute(Constants.MESSAGE_KEY, "�����걨��ʾ�ɹ���");
            return mapping.findForward("printreturngr");
        } else {
            request.setAttribute(Constants.MESSAGE_KEY, "�����걨��ʾ�ɹ���");
            return mapping.findForward("printreturnfgr");
        }

    }

    /**
     * ���ݼ���������ȡ������Ϣ���������е�
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
        // ����Token;
        saveToken(request);
        try {
            // ���form,
            aForm.setNsrmc("");
            aForm.setKhyhList(new ArrayList());
            aForm.setYhzh("");
            // ��ȡ����Ϣ
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
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }

        request.setAttribute(Constants.MESSAGE_KEY, "ȡ����˰����Ϣ�ɹ���");
        return mapping.findForward("show");

    }

    /**
     * ��ȡ����
     *
     * 1. ���ø��෽������Token. ActionForward forward = doHandleToken(request); if
     * (forward != null) return forward; 2. ��Form�л�ȡ���� Object obj =
     * ((BaseForm)form).getData(); 3. 4. ����Token; saveToken(); 5. ת��ɹ���Ľ��档
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
            // ���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // ��Form�л�ȡ����
            JmsbForm ftForm = (JmsbForm) form;
            String bzdm = ftForm.getBz();
            Wbhs wbhs = new Wbhs();
            wbhs.bzdm = bzdm;
            Calendar cd = Calendar.getInstance();
            cd.add(Calendar.MONTH, -1);
            wbhs.nd = String.valueOf(cd.get(Calendar.YEAR));
            wbhs.yf = String.valueOf(cd.get(Calendar.MONTH));
            // ��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false)
                              .getServletContext().getAttribute(
                                      Constants.PROCESSOR_MAP_FILE_NAME);

            // �������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET_HL);
            vo.setProcessor(prop.getProperty(wbhs.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(wbhs);

            wbhs = (Wbhs) QsglProxy.getInstance().process(vo);

            ftForm.setHn(DataConvert.BigDecimal2String(wbhs.getWhpj(), 5));

            if (wbhs.getWhpj() == null) {
                request.setAttribute(Constants.MESSAGE_KEY, "ָ������ҵĻ��ʲ����ڣ�");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "��ȡ��һ��ʳɹ���");
            }

            // ����Token;
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

            // ��context��ȡ�������Ϣ,��ȡ����map,
            // ���еõ�������˰�����͡����֤�����͡����֡�������𡢷�������ء�����Ȩ��ת�����͵�arraylist
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

            // �����Ǹ��˽����Ļ��ǷǸ��˽�����
            if (path.equals("/jmsb/addJmsbFgr")) {
                jmsbForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_FGR));
            } else {
                jmsbForm.setQsjmlbList(ActionUtil.getCodeTables(session
                        .getServletContext(), Constants.JMZC_GR));
            }
            //������˰�ĺ�����
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

            jmsbForm.ztbs = jmsbxxForm.getVoSbzb().ztbs; // ǰ̨ҳ���ϻ��
            jmsbForm.yhbs = jmsbxxForm.getVoSbzb().yhbs; //�û���ʶ
            jmsbForm.setRjl(jmsbxxForm.getVoTufwxx().rjl);
            jmsbForm.setTdjc(jmsbxxForm.getVoTufwxx().tdjc);
            jmsbForm.setSbrq(DataConvert.TimeStamp2String(jmsbxxForm.getVoSbzb().
                    sbrq));

            jmsbForm.setSbbh(jmsbxxForm.getVoSbzb().sbbh);

            // ��˰�������
            jmsbForm.qsjmlb = jmsbxxForm.qsjmlb;
            jmsbForm.qsjmlbmc = jmsbxxForm.qsjmlbmc;
            jmsbForm.qtjmlybeizhu = jmsbxxForm.qtjmlybeizhu;
            jmsbForm.qsjmlbSelect = jmsbxxForm.qsjmlbSelect;
            if(jmsbForm.qsjmlb==null && jmsbForm.qsjmlbSelect!=null && jmsbForm.qsjmlbSelect.length>0){
            	jmsbForm.qsjmlb = jmsbForm.qsjmlbSelect[0];
            }
            jmsbForm.qsjmxzdm = jmsbxxForm.jmxzdm;

            if (jmsbForm.yhbs.equals(Constants.YHBZ_FGR)) { //�Ǹ���
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
            if (jmsbForm.yhbs.equals(Constants.YHBZ_GR)) { //����
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
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "�����걨��ʾ�ɹ���");
        return mapping.findForward("show");

    }


}
