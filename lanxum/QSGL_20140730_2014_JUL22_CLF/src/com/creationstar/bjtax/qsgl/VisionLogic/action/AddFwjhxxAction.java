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
 * @author ������
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
        // ���ӷ���������������
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

        if (fwjhxxForm.isPerson()) { //¼����˲�Ǩ��Ϣ
            SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
            fwjhxxForm.setSbbh(sbGrForm.getSbbh());
        } else {
            SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute("sbFgrForm");
            fwjhxxForm.setSbbh(sbFgrForm.getSbbh());
        }

        doGetHL(mapping, form, request, response);
        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "���ݽ�����ʾ�ɹ���");
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

        // ����Token;
        saveToken(request);
        return mapping.findForward("show");

    }

    /**
     * �����¼
     *
     * 1. ���ø��෽������Token.
     *     ActionForward forward = doHandleToken(request);
     *     if (forward != null)
     *        return forward;
     * 2. ��Form�л�ȡ����
     *     Object obj = ((BaseForm)form).getData();
     * 3. ����BaseProxy��add�����������ݿ�������һ����¼��
     *       HttpSession session = request.getSession();
     *       UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
     *     BaseProxy.add(obj,userData);
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
            // ��Form�л�ȡ����
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
            //����QsglProxy��add�����������ݿ�������һ����¼��
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));
            vo.setUserData(this.getUserData());
            vo.setData(obj);

            HttpSession session = request.getSession(false);

            QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);
            removeForm(mapping, request);
            request.setAttribute(Constants.MESSAGE_KEY, "���ݽ�������ɹ���");

            if (fwjhxxForm.isPerson()) {
                //�������ӵĹ���ס����Ϣ�ӵ� �걨form��
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                //�������ӵĸ���ס��������Ϣ�ӵ� �걨form��
                sbGrForm.setFwjhxxBo(obj);
                sbGrForm.setFwjhAdded(true);
                session.setAttribute("sbGrForm", sbGrForm);
                // ת��ɹ���Ľ��档
                return mapping.findForward("savegr");
            } else {
                SbFgrForm sbFgrForm = (SbFgrForm) session.getAttribute(
                        "sbFgrForm");
                //�������ӵĸ���ס��������Ϣ�ӵ� �걨form��
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
            request.setAttribute(Constants.MESSAGE_KEY, "ȡ����˰����Ϣʧ�ܣ�");
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
        }

        request.setAttribute(Constants.MESSAGE_KEY, "ȡ����˰����Ϣ�ɹ���");
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");

    }

    /**
     * ȡ����ǰ������
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     *
     *  1. ����Token;
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
        // ɾ��session�е�form
        removeForm(mapping, request);
        // ����Token;
        saveToken(request);
        FwjhxxForm fwjhxxForm = (FwjhxxForm) form;

        if (fwjhxxForm.isPerson()) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
        }

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
            FwjhxxForm ftForm = (FwjhxxForm) form;
            String bzdm = ftForm.getBzdm();
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
