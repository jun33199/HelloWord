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

import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.BzqsForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BzqsBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Arith;
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
 * @author not attributable
 * @version 1.0
 */
public class AddBzqsAction extends AddBaseAction {
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
        BzqsForm aForm = (BzqsForm) form;
        String path = mapping.getPath();
        Debug.out("mapping.getPath() is " + path);

        try {

            //��context��ȡ�������Ϣ,��ȡ����map,
            //���еõ�������˰�����͡����֤�����͡����֡�������𡢷�������ء�����Ȩ��ת�����͵�arraylist
            aForm.setNsrlxList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.NSRLX));
            aForm.setSfzjlxList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.ZJLX));
            aForm.setBzList(ActionUtil.getCodeTables(session.getServletContext(),
                    Constants.BZ));
            aForm.setFwlbList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.FWLX));

            //�����Ǹ��˽����Ļ��ǷǸ��˽�����
            if (path.equals("/bzqslr/addBzqsFgr")) {
                aForm.setFlList(ActionUtil.getCodeTables(session.
                        getServletContext(),
                        Constants.BZ_TDFWYT_FGR));
            } else {
                aForm.setFlList(ActionUtil.getCodeTables(session.
                        getServletContext(),
                        Constants.BZ_TDFWYT_GR));
            }
            aForm.setTdfwqszylxList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.TDFWQSZY));
            aForm.setGjdqList(ActionUtil.getCodeTables(session.
                    getServletContext(), Constants.GJ));

            doGetHL(mapping, form, request, response);

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "������ʾ�ɹ���");
        return mapping.findForward("show");

    }


    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            BzqsForm aForm = (BzqsForm) form;
            // ��Form�л�ȡ����
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

            BzqsBo bo = (BzqsBo) aForm.getData(l);
            Debug.out("AddBaseAction doAdd form.getData() obj.class is " +
                      bo.getClass().getName());
            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(bo);

            /** @todo ���뷵��ֵ���жϣ��Ը���ҳ���Ƿ�ɹ� */
            bo = (BzqsBo) QsglProxy.getInstance().process(vo);
            //��viewҳ������û�е����ݸ�ֵ
            aForm.setSbbh(bo.getSbbh());
            aForm.setCjrq(bo.getCjrq());
            // ����Token;
            saveToken(request);

            // ת��ɹ����view���档
            request.setAttribute(Constants.MESSAGE_KEY, "��������ɹ���");

            //��ʽ��viewҳ���е���Ϣ
            Timestamp time = DataConvert.String2Timestamp(aForm.getHyqdsj());
            aForm.setHyqdsj(DataConvert.TimeStamp2String(time));

            //�޸Ľ���������������ΪС�������λ
            //modify by fujx,20081126
            //���뿪ʼ
            BigDecimal zymj = null;
            BigDecimal jzmj = null;
            if (null == aForm.getTdfwqszymj() || "".equals(aForm.getTdfwqszymj())) {
                zymj = DataConvert.String2BigDecimal(Arith.roundStr("0.000", 3));
            } else {
                zymj = DataConvert.String2BigDecimal(Arith.roundStr(aForm.
                        getTdfwqszymj(), 3));
            }

            aForm.setTdfwqszymj(DataConvert.BigDecimal2String(zymj, 3));
            if (null == aForm.getFwjzmj() || "".equals(aForm.getFwjzmj())) {
                jzmj = DataConvert.String2BigDecimal(Arith.roundStr("0.000", 3));
            } else {
                jzmj = DataConvert.String2BigDecimal(Arith.roundStr(aForm.
                        getFwjzmj(), 3));
            }

            aForm.setFwjzmj(DataConvert.BigDecimal2String(jzmj, 3));
            //�޸Ľ���������������ΪС�������λ���������


            BigDecimal cjjg = DataConvert.String2BigDecimal(aForm.getCjjgyrmb());
            aForm.setCjjgyrmb(DataConvert.BigDecimal2String(cjjg));

            BigDecimal pgjg = DataConvert.String2BigDecimal(aForm.getPgjg());
            aForm.setPgjg(DataConvert.BigDecimal2String(pgjg));

            BigDecimal cjjgwb = DataConvert.String2BigDecimal(aForm.getCjjgywb());
            aForm.setCjjgywb(DataConvert.BigDecimal2String(cjjgwb));

            BigDecimal hl = DataConvert.String2BigDecimal(aForm.getHn());
            aForm.setHn(DataConvert.BigDecimal2String(hl, 5));

            BigDecimal zhyrmb = DataConvert.String2BigDecimal(aForm.getZhyrmb());
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
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doReturnPri(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        BzqsForm aForm = (BzqsForm) form;
        Debug.out("return pri jinru");
        aForm.clear();
        doGetHL(mapping, form, request, response);

        // ����Token;
        saveToken(request);
        if (aForm.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            request.setAttribute(Constants.MESSAGE_KEY, "������ʾ�ɹ���");
            return mapping.findForward("printreturngr");
        } else {
            request.setAttribute(Constants.MESSAGE_KEY, "������ʾ�ɹ���");
            return mapping.findForward("printreturnfgr");
        }

    }

    /**
     * ���ݼ���������ȡ������Ϣ���������е�
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
        BzqsForm aForm = (BzqsForm) form;
        // ����Token;
        saveToken(request);
        try {
            //���form,
            aForm.setNsrmc("");
            aForm.setKhyhList(new ArrayList());
            aForm.setYhzh("");
            //��ȡ����Ϣ
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
     * 1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. ��Form�л�ȡ����
     *     Object obj = ((BaseForm)form).getData();
     * 3.
     * 4. ����Token;
     *       saveToken();
     * 5. ת��ɹ���Ľ��档
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
            //���ø��෽������Token.
            ActionForward forward = doHandleToken(mapping, request);
            if (forward != null) {
                return forward;
            }
            // ��Form�л�ȡ����
            BzqsForm ftForm = (BzqsForm) form;
            String bzdm = ftForm.getBz();
            Wbhs wbhs = new Wbhs();
            wbhs.bzdm = bzdm;
            Calendar cd = Calendar.getInstance();
            cd.add(Calendar.MONTH, -1);
            wbhs.nd = String.valueOf(cd.get(Calendar.YEAR));
            wbhs.yf = String.valueOf(cd.get(Calendar.MONTH));
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
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


}
