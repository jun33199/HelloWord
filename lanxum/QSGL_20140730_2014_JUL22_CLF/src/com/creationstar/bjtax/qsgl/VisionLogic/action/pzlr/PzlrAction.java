package com.creationstar.bjtax.qsgl.VisionLogic.action.pzlr;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Pzlr;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.PzlrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PzlrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DESUtil;
import com.creationstar.bjtax.qsgl.util.MakeXMLUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * �����������¼��action
 */
public class PzlrAction extends BaseAction {
    /**
     *ҳ���ʼ��
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
        PzlrForm pzlrForm = (PzlrForm) form;
        UserData ud = this.getUserData();
        //��ʼ��������Ա
        pzlrForm.setCzry(ud.getYhid());
        //��ʼ������˰���������
        pzlrForm.setSwjgzzjgmc(ud.getSsdwmc());
        //��ʼ������˰����ش���
        pzlrForm.setSwjgzzjgdm(ud.getSsdwdm());
        //��ʼ��ҳ�浥ѡ���ֵ��Ϊ��
        pzlrForm.setCzcq("1");
        pzlrForm.setCzcsxx("1");
        pzlrForm.setCzfwjyxx("1");
        //����ҳ��ɾ�������ɰ�ť��״̬Ϊ����ʾ
        pzlrForm.setDel_flag("false");
        //��������������������
        pzlrForm.setTdjcList(ActionUtil.getCodeTables(session.
                getServletContext(), Constants.SZQY));
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ���ݼ���������ѯ������Ϣ
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

        PzlrForm aForm = (PzlrForm) form;
        // ����Token;
        saveToken(request);
        try {
            String jsjdm = aForm.getJsjdm();

            PzlrBo bo = new PzlrBo();
            bo.setJsjdm(jsjdm);
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());

            bo = (PzlrBo) QsglProxy.getInstance().process(vo);
            //������˰������
            aForm.setNsrmc(bo.getNsrmc());
            //������˰��˰�������֯����
            aForm.setZzjgmc(bo.getSsdwmc());
            request.setAttribute(Constants.MESSAGE_KEY, "ȡ����˰����Ϣ�ɹ���");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "ȡ����˰����Ϣʧ�ܣ�");

        }

        return mapping.findForward("show");

    }

    /**
     * ���ݷ��ز���Ŀ���Ʋ�ѯ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doQueryFwxx(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        // ����Token;
        saveToken(request);
        try {

            String fdcxmmc = aForm.getFdcxmmc();
            String jsjdm = aForm.getJsjdm();
            PzlrBo bo = new PzlrBo();

            bo.setJsjdm(jsjdm);
            bo.setFdcxmmc(fdcxmmc);
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.GET);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            List list = new ArrayList();
            //��ѯ�����voΪ�������list��
            list = (List) QsglProxy.getInstance().process(vo);
            //�ж��Ƿ��з�����Ϣ��������ʾ�����ҳ���ɾ����ť��û������ʵɾ����ť��
            if (list.size() > 0) {
                setForm(list, aForm);

                request.setAttribute(Constants.MESSAGE_KEY, "����Ŀ��Ϣ�Ѿ����ڣ�");
            } else {
                request.setAttribute(Constants.MESSAGE_KEY, "δ�и���Ŀ��Ϣ��");
            }

        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "��ȡ��Ŀ��Ϣʧ�ܣ�");

        }

        return mapping.findForward("show");

    }

    /**
     * �������ݷ���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        System.out.println("save action");
        PzlrForm aForm = (PzlrForm) form;
        //���ñ������ݷ���
        saveDate(aForm, request);
        return mapping.findForward("show");

    }

    /**
     * ɾ�����÷���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);

        try {
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.DELETE);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            QsglProxy.getInstance().process(vo);
            request.setAttribute(Constants.MESSAGE_KEY, "ɾ����Ŀ��Ϣ�ɹ���");
            clearForm(aForm);
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "ɾ����Ŀ��Ϣʧ�ܣ�");

        }
        return mapping.findForward("show");
    }

    /**
     * ����form����
     * @param list List
     * @param form PzlrForm
     */
    private void setForm(List list, PzlrForm form) {
        Pzlr vo = (Pzlr) list.get(0);
        System.out.println("id===" + vo.getId());
        form.setId(vo.getId());
        form.setCzcq(vo.getCzcqxx());
        form.setCzcsxx(vo.getCzcsxx());
        form.setCzfwjyxx(vo.getCzfwjyxx());
        form.setRjl(vo.getRjl());
        form.setQsrq(vo.getQsrq());
        form.setJzrq(vo.getJsrq());
        form.setJzmj(vo.getJzmj());
        form.setPjjyjg(vo.getPjjyjg());
        /*added by gaoyh to 20141016*/
        //����ÿ�׼۸�
        form.setFwmtjg(vo.getFwmtjg());
        form.setTdjb(vo.getTdjb());
        form.setDel_flag("true");
    }

    /**
     * ���form����
     * @param list List
     * @param form PzlrForm
     */
    private void clearForm(PzlrForm form) {
        form.setFdcxmmc("");
        form.setJzmj("");
        form.setRjl("");
        form.setQsrq("");
        form.setJzrq("");
        form.setPjjyjg("");
        /*added by gaoyh to 20141016*/
        //����ÿ�׼۸�
        form.setFwmtjg("");
        form.setCzcq("0");
        form.setCzcsxx("0");
        form.setCzfwjyxx("0");
        //�����˰����Ϣ
        form.setJsjdm("");
        form.setNsrmc("");
        form.setZzjgmc("");
        form.setDel_flag("false");
    }

    /**
     * ����PzlrBo����
     * @param list List
     * @param form PzlrForm
     */
    private PzlrBo setPzlrBo(PzlrBo bo, PzlrForm form) {

        //����id
        bo.setId(form.getId());
        //���������
        bo.setJsjdm(form.getJsjdm());
        //��˰������
        bo.setNsrmc(form.getNsrmc());
        //��˰��˰�������֯����
        bo.setSsdwmc(form.getZzjgmc());
        //���ز���Ŀ����
        bo.setFdcxmmc(form.getFdcxmmc());
        //���ؼ���
        bo.setTdjb(form.getTdjb());
        //�ݻ���
        bo.setRjl(form.getRjl());
        //�������
        bo.setJzmj(form.getJzmj());
        //ƽ�����׼۸�
        bo.setPjjyjg(form.getPjjyjg());
        /*added by gaoyh to 20141016*/
        //����ÿ�׼۸�
        bo.setFwmtjg(form.getFwmtjg());
        //ʹ��������
        bo.setQsrq(form.getQsrq());
        //ʹ������ֹ
        bo.setJzrq(form.getJzrq());

        //�Ƿ�ɲ�����Ǩ��Ϣ
        bo.setCzcq(form.getCzcq());
        //�Ƿ�ɲ����ѹ�����ס����Ϣ
        bo.setCzcsxx(form.getCzcsxx());
        //�Ƿ�ɲ������ݽ�����Ϣ
        bo.setCzfwjyxx(form.getCzfwjyxx());
        //������Ա
        bo.setCzry(form.getCzry());
        //����ʱ��
        bo.setXtdqsj(form.getXtdqsj());

        return bo;
    }

//    /**
//     * ����id��ѯ���ز���Ŀ����
//     * @param id String
//     * @return String
//     */
//    private String getFdcxmmc(String id ,HttpServletRequest request){
//    PzlrBo bo = new PzlrBo();
//    bo.setId(id);
//    String fdcxmmc="";
//    try{
//              //��ȡ�����ServletContext�е�processor-map.properties������
//              Properties prop = (Properties) request.getSession(false).
//                                getServletContext().getAttribute(Constants.
//                      PROCESSOR_MAP_FILE_NAME);
//
//              //�������̨�����õ�VoPackage
//              VOPackage vo = new VOPackage();
//              vo.setAction(ActionType.QUERY_DRZB);
//              vo.setProcessor(prop.getProperty(bo.getClass().getName()));
//              vo.setData(bo);
//              vo.setUserData(this.getUserData());
//              fdcxmmc=(String)QsglProxy.getInstance().process(vo);
//
//
//          }catch(Exception ex){
//                  // ����Token;
//                       saveToken(request);
//                       ex.printStackTrace();
//
//
//          }
//          return fdcxmmc;
//
//}
    /**
     * ����xml�ļ�
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doMakeXML(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        PzlrForm aForm = (PzlrForm) form;
        //���������ļ�֮ǰ�Ƚ��б��������modify by fujx��20081217
        saveDate(aForm, request);

        // String xmlName=aForm.getJsjdm()+"pzxx";
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);
        List list = bo.getItemList();
        //ƴ���ļ������ַ���
        String fileName = aForm.getJsjdm() + "_" + aForm.getFdcxmmc() +
                          "_pzxx.dat";

//         for(int i=0;i<2;i++){
//             PzxxXMLItem item = (PzxxXMLItem)list.get(i);
//             System.out.println("name==="+item.getItemName());
//             System.out.println("value==="+item.getItemValue());
//         }
        try {
            //�����ļ�����Ϊ'iso-8859-1'�����ʽ
            response.setHeader("Content-Disposition",
                               "attachment;filename=" +
                               new String(fileName.getBytes("GBK"),
                                          "ISO-8859-1"));
            response.setContentType("text/html;charset=gb2312");
            //response.setchar
            //response.setCharacterEncoding("gbk");
            OutputStream servletOutput = response.getOutputStream();
            //FileOutputStream servletOutput = new FileOutputStream(xmlName);
            PrintWriter printWrite = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(new BufferedOutputStream(
                            servletOutput))));
            //��ȡxml�ַ���
            String buff = MakeXMLUtil.createXML("pzxx", list);
            System.out.println("�����ַ���=" + buff);
            //���ַ�������
            DESUtil des = new DESUtil();
            buff = des.encrypt(buff);
            //���
            printWrite.println(buff);
            printWrite.flush();
            printWrite.close();
            servletOutput.flush();
            servletOutput.close();
            request.setAttribute(Constants.MESSAGE_KEY, "����xml�ļ��ɹ���");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "����xml�ļ�����");

        }
        return null;
    }

//    public static void main(){
//
//    }

    /**
     * �������ݵĹ��з������������������ļ�֮ǰ���н��б���ĵ���
     * @param aForm PzlrForm
     * @param request HttpServletRequest
     */
    private void saveDate(PzlrForm aForm, HttpServletRequest request) {
        PzlrBo bo = new PzlrBo();
        bo = setPzlrBo(bo, aForm);
        try {
            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����õ�VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.INSERT);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            vo.setData(bo);
            vo.setUserData(this.getUserData());
            String result = (String) QsglProxy.getInstance().process(vo);
            aForm.setDel_flag("true");
            //����ҳ���id����
            aForm.setId(result);
            if (!result.equals("false")) {
                request.setAttribute(Constants.MESSAGE_KEY, "������Ŀ��Ϣ�ɹ���");
            }
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, "������Ŀ��Ϣʧ�ܣ�");

        }

    }


}
