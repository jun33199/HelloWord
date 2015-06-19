package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryPlslForm2;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DESUtil;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ResourceLocator;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * <p>Title: �����ļ���action��</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author κ����
 * @version 1.0
 *
 * ˵������action�����ҳ����"��������(˰����Ա)"ģ��
 */
public class ImportFileAction2 extends BaseAction {
    public ActionForward doUpload(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm2 aForm = (QueryPlslForm2) form;
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        int liDrMaxBs = 200; //����������
        HashMap loData = new HashMap();
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setUserData(ud);
        FormFile loFf = aForm.getMofile();
        vo.setAction(ActionType.UPLOAD_FILE);
        vo.setProcessor(prop.getProperty("uploadFileProcessor2"));
        try {

            aForm.setJkfsmc(StringUtil.ISO2GBK(aForm.getJkfsmc()));
            String lsPch;
            int aiLen = loFf.getFileSize();
            InputStream in = loFf.getInputStream();
            byte[] lbBB = new byte[aiLen];
            in.read(lbBB);
            in.close();

            String lsS = new String(lbBB, "GBK");
            //�����ļ�
            DESUtil des = new DESUtil();
            lsS = des.decrypt(lsS.toString());

            QsglPcclXmlUtil util = new QsglPcclXmlUtil();
            ArrayList testList = util.getPcclMx(lsS);
            //�ж��Ƿ񳬹�200�����������
            if (testList.size() > liDrMaxBs + 1) {
                throw new Exception("����ı������ܴ���200����");
            }
            //��Ϊ׷��
            if (aForm.getMsBLDrpch().length() > 0) {

                lsPch = aForm.getMsBLDrpch();
                loData.put("pch", lsPch);
                loData.put("append", testList);
                //��session�з��õ������κ�
                request.setAttribute("drpch", aForm.getMsBLDrpch());
            } else {

                //���õ������κ�
                lsPch = aForm.getMsDrpch();
                loData.put("pch", lsPch);
                loData.put("jsfsdm", aForm.getJkfsdm());
                loData.put("jsfsmc", aForm.getJkfsmc());
                loData.put("new", testList);
                //��session�з��õ������κ�
                request.setAttribute("drpch", aForm.getMsDrpch());
            }

            vo.setData(loData);
            Map lmReturnedData = (HashMap) QsglProxy.getInstance().
                                 process(vo);
            ArrayList llErrrecords = (ArrayList) lmReturnedData.get("Error");
            ArrayList llSucceedRecords = (ArrayList) lmReturnedData.get(
                    "Succeed");
            aForm.setDrsj(llSucceedRecords);
            aForm.setSize(llSucceedRecords.size());
            if (llErrrecords.size() > 0) {
                aForm.setMlErrRecords(llErrrecords);
                aForm.setMbIsSucceed(false);
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "�ļ���ʽ����");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY,
                                     "�ļ��ϴ��ɹ�");
                //return mapping.findForward("continue");
                //modify by CA program
                response.sendRedirect(ResourceLocator.getServerURL(ud.getCaflag()) +
                        "/qsgl/plsl_swry/plsl.do?operationType=Show");
                return null;
            }

        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        return mapping.findForward("show");

    }

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryPlslForm2 aForm = (QueryPlslForm2) form;
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        aForm.setJkfsList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.JSFS));
        aForm.setMofile(null);
        //�����̨��Ҫ���������ݶ���
        UserData ud = this.getUserData();
        VOPackage vo = new VOPackage();
        vo.setUserData(ud);
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty("uploadFileProcessor2"));
        try {
//            String[] lsBLDrpchs = (String[]) QsglProxy.getInstance().process(vo);
            vo.setAction(ActionType.GET);
            String plh = (String) QsglProxy.getInstance().process(vo);
//            aForm.setMsBLDrpchs(lsBLDrpchs);
            aForm.setMsDrpch(plh);

        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        return mapping.findForward("show");

    }

    public ActionForward doContinue(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        Debug.out("ImportFileAction continue");
        HttpSession session = request.getSession(false);
        QueryPlslForm2 aForm = (QueryPlslForm2) form;
        Debug.out("continue");
        return mapping.findForward("continue");

    }
}
