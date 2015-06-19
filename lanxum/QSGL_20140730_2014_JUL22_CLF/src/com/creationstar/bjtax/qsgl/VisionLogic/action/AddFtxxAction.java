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
 * @author ������
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
        if (ftxxForm.getPerson().equals("true")) { //¼����˷�����Ϣ
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
            request.setAttribute(Constants.MESSAGE_KEY, "���ط�����Ϣ��ʾ�ɹ���");
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
        //yangxiao 2008-12-06 start �����Ƿ�Ϊ2�ַ�
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
        // ���ӷ���������������
        ftxxForm.setTdjcList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.SZQY));
        doGetHL(mapping, form, request, response);

        // ����Token;
        saveToken(request);
        request.setAttribute(Constants.MESSAGE_KEY, "���ط�����Ϣ��ʾ�ɹ���");
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
            //
            FtxxForm ftxxForm = (FtxxForm) form;
            System.out.println("˰�����=" + ftxxForm.getSetz());
            // ��Form�л�ȡ����
            Tufwxx obj = (Tufwxx) ((FtxxForm) form).getData();
            Debug.out("AddFtxxAction doAdd form.getData() obj.class is " +
                      obj.getClass().getName());
            FtxxForm ftForm = (FtxxForm) form;
//             if(ftForm.getHyqdsj().compareTo("20050601")<0)
//             {
//                 obj.setSetz(Constants.SETZ_ZC);
//             }
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
            //FtxxForm ftxxForm = (FtxxForm) form;

            QsglProxy.getInstance().process(vo);

            // ����Token;
            saveToken(request);

            //�������ӵĹ���ס����Ϣ�ӵ� �걨form��
            String person = ftxxForm.getPerson();

            // ת��ɹ���Ľ��档
            if (person.equals("true")) {
                SbGrForm sbGrForm = (SbGrForm) session.getAttribute("sbGrForm");
                sbGrForm.setVoTufwxx(obj);
                sbGrForm.setFwjbxxAdded(true);
                session.setAttribute("sbGrForm", sbGrForm);
                request.setAttribute(Constants.MESSAGE_KEY, "���ط�����Ϣ����ɹ���");
                // ɾ��session�е�form
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
                    String msg = "����ʾ��˰�ˣ��뵽˰����ذ������˰�����ٰ�����˰�걨��";
                    sbFgrForm.setAlertMessage(msg);
                    request.setAttribute(Constants.MESSAGE_KEY, msg);
                } else {
                    request.setAttribute(Constants.MESSAGE_KEY, "���ط�����Ϣ����ɹ���");
                }
                // ɾ��session�е�form
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
        FtxxForm ftxxForm = (FtxxForm) form;

        if (ftxxForm.getPerson().equals("true")) {

            return mapping.findForward("returngr");
        } else {

            return mapping.findForward("returnfgr");
        }

    }

    /**
     * ������˰�����ͺͷ��ݵķ����ж��Ƿ���Ҫ�������˰����
     * @param nsrlx String
     * @param fldm String
     * @return boolean
     */
    private boolean needAlertJms(String nsrlx, String fldm) {
        //��˰������Ϊ���һ��ء���ҵ��λ��������塢���µ�λ��
        //�ҷ������ڰ칫����ѧ��ҽ�ơ����С�������ʩ������ģ�
        if ((nsrlx.equals("01") || //���һ���
             nsrlx.equals("02") || //��ҵ��λ
             nsrlx.equals("03") || //�������
             nsrlx.equals("04") //���µ�λ
            ) &&
            (fldm.equals("01") || //�칫
             fldm.equals("02") || //��ѧ
             fldm.equals("03") || //ҽ��
             fldm.equals("04") || //����
             fldm.equals("05") //������ʩ
            )) {
            return true;
        }
        return false;
    }

}
