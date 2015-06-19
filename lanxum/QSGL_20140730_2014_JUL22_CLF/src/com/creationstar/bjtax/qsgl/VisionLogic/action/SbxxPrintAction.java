package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SbxxPrintAction extends BaseAction {
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

        try {
            Debug.out("this is sbxxprintaction hahaha");
            // �������е�Form����ΪBaseForm��
            SbxxForm aForm = (SbxxForm) form;

            // ��BaseForm�л�ȡ��ѯ������
            String sbbh = aForm.getSbbh();
            Debug.out("this is sbxxprintaction sbbh" + sbbh);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            VOPackage vo = new VOPackage();
            SbxxBo bo = new SbxxBo();
            bo.setSbbh(sbbh);
            vo.setAction(ActionType.PRINT_SBB);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            bo = (SbxxBo) QsglProxy.getInstance().process(vo);

            aForm.setData(bo);
            //��������������ַ�������form��
            aForm.setInputStr(getInputStr(aForm, userData));
            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            request.setAttribute(Constants.MESSAGE_KEY, "�걨��Ϣ��ӡ�ɹ���");
            return mapping.findForward("show");
        } catch (BaseException ye) {
            // ����Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            // ����Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }

    }

    /**
     * ���ݲ�ͬ��ҵ�����ͷ���ǰ̨������������ַ���
     * @param form SbxxForm
     * @return String
     */
    private String getInputStr(SbxxForm sbxxForm, UserData userData) {
        //��ȡ�걨����vo
        Sbzb sbzbvo = sbxxForm.getVoSbzb();
        //ȡ����˰�걨�벻����˰�걨�ı�־�ֶ�
        String bljmsbs = sbzbvo.getBljmsbs();
        //ȡ�ø�����Ǹ��˵ı�־�ֶ�
        String yhbs = sbzbvo.getYhbs();
        //���bljmsbs��Constants.ZB_BLJMSBS_BZ��ȣ�Ϊ������˰�걨;����Ϊ��˰�걨
        if (bljmsbs.equals(Constants.ZB_BLJMSBS_BZ)) {
            //�ж��Ǹ��˻��ǷǸ���
            if (yhbs.equals(Constants.ZB_YHBS_GR)) {
                //ȡ�ò�����˰�걨����ҵ�����������ַ���
                return getTxmxx(sbxxForm, userData, "1");
            } else {
                //ȡ�ò�����˰�걨�Ǹ���ҵ�����������ַ���
                return getTxmxx(sbxxForm, userData, "2");

            }
        } else {
            if (yhbs.equals(Constants.ZB_YHBS_GR)) {
                //ȡ����˰�걨����ҵ�����������ַ���
                return getTxmxx(sbxxForm, userData, "1");

            } else {
                //ȡ����˰�걨�Ǹ���ҵ�����������ַ���
                return getTxmxx(sbxxForm, userData, "2");

            }
        }

    }

//    //��ȡ������˰�걨--���� ҵ��������ַ���
//    private String getBZQSSb_Gr(){
//        System.out.println("��ȡ������˰�걨--���� ҵ��������ַ���");
//        return "";
//    }
//    //��ȡ������˰�걨--�Ǹ��� ҵ��������ַ���
//    private String getBZQSSb_Fgr(){
//        System.out.println("��ȡ������˰�걨--�Ǹ��� ҵ��������ַ���");
//        return "";
//    }
//    //��ȡ��˰�걨--���� ҵ��������ַ���
//    private String getQSSb_Gr(SbxxForm sbxxForm,UserData userData){
//        System.out.println("��ȡ��˰�걨--���� ҵ��������ַ���");
//        //��ȡ�걨����vo
//        Sbzb sbzbvo = sbxxForm.getVoSbzb();
//        //ȡ�����ء�������Ϣ��
//        Tufwxx fwxx = sbxxForm.getVoTufwxx();
//        //ȡ����˰��list
//        List nsrList = sbxxForm.getNsrList();
//        StringBuffer sb = new StringBuffer();
//
//        //����ͷ��ʾ��
//        sb.append("^object_000002_000001^");
//        //���Ӻ�ͬ���
//        if(null!=sbzbvo.getHtbh()){
//            sb.append(sbzbvo.getHtbh() + "^");
//        }else{
//           sb.append(""+"^");
//        }
//
//        //���Ӻ�ͬ����ǩԼ����,���������Ҫ�ж�
//        String qyrq = DataConvert.TimeStamp2String(fwxx.getHtqdsj());
//        sb.append(getRq(qyrq)+"^");
//        //sb.append(DataConvert.TimeStamp2String(qyrq,"yyyy-mm-dd")+"^");
//        //���ӽ��׷��ݵ�ַ���أ�ȡ��˰����Ա��������
//        sb.append(getDzqx(userData.getSsdwdm())+"^");
//        //���ӽ��׷��ݵ�ַ,���������Ҫ�ж�
//        sb.append(fwxx.getTdfwzldz()+"^");
//        //���ӷ���Ȩ��ת������
//        sb.append("162200^");
//        //�����Ƿ�Ϊ�״������ѹ�����
//        sb.append("0^");
//        //���ӷ��ݲ�Ȩ֤��
//        sb.append(""+"^");
//        //���ӷ��ݲ�Ȩ֤�����
//        sb.append(""+"^");
//        //���ӷ��ݽ������
//        String jzmj = DataConvert.BigDecimal2String(fwxx.getFwjzmj(),3);
//        System.out.println("jzmj====" + jzmj);
//        if(null!=jzmj && jzmj.equals("0000")){
//            sb.append(""+"^");
//        }else{
//            sb.append(jzmj+"^");
//        }
//        //���ӽ����ṹ
//        sb.append(""+"^");
//        //���ӹ滮��;
//        sb.append(""+"^");
//       // ���Ӻ�ͬ�ܼ�,����˳�������۸񡢳ɽ��۸��ۺϼ۸�
//       //ȡ�������۸�
//       String pgjg = DataConvert.BigDecimal2String(fwxx.getPgjgrmb());
//       //ȡ�óɽ��۸�
//        String cjjg = DataConvert.BigDecimal2String(fwxx.getCjjgrmb());
//       //ȡ���ۺϼ۸�
//        String zhjg = DataConvert.BigDecimal2String(fwxx.getZhjgrmb());
//       if(null!=pgjg){
//           sb.append(pgjg+"^");
//       }else if(null!=cjjg){
//           sb.append(cjjg+"^");
//       }else if(null!=zhjg){
//           sb.append(zhjg+"^");
//       }
//
//       //����¥��/�ܲ���
//       sb.append(""+"^");
//       //���������
//       sb.append(""+"^");
//       //������ҽ��
//       sb.append(""+"^");
//       //���ӻ���
//       sb.append(""+"^");
//       //��������Ϣ,��˰����Ϣ������Ϊ�գ�����Ҫ�ж�
//       for(int i=0;i<nsrList.size();i++){
//           Grxx grxx = (Grxx)nsrList.get(i);
//           //������������
//           sb.append("^"+grxx.getNsrmc()+"~");
//           //����������𣬴˴�Ϊ1������
//           sb.append("1~");
//           //����֤������
//           sb.append(getZjlxdm(grxx.getSfzjlx())+"~");
//           //����֤������
//           sb.append(grxx.getSfzjhm()+"~");
//           //����Ȩ���˷ݶ�
//           sb.append(""+"~");
//           //�����Ƿ������һ����¼���ж�ĩβ�ķָ����Ƿ���Ҫ
//           if(i==nsrList.size()-1){
//           //������ϵ�˵绰
//           sb.append(grxx.getLxdh());
//           }else{
//               //������ϵ�˵绰
//               sb.append(grxx.getLxdh() + "~");
//           }
//       }
//
//       //���ӷ��ز��н��������
//       sb.append(""+"^");
//       System.out.println("��˰�걨����inputStr ====" + sb.toString());
//       return sb.toString();
//    }
//    //��ȡ��˰�걨--�Ǹ��� ҵ��������ַ���
//    private String getQSSb_Fgr(){
//        System.out.println("��ȡ��˰�걨--�Ǹ��� ҵ��������ַ���");
//        return "";
//    }
    /**
     * ת�����׷��ݵ�ַ���صĴ���
     * @param qxdm String
     * @return String
     */
    private String getDzqx(String qxdm) {
        int dm = Integer.parseInt(qxdm.substring(0, 2));
        System.out.println("���������������===" + dm);
        switch (dm) {
        case (1):
            return "2";

        case (2):
            return "3";

        case (3):
            return "4";

        case (4):
            return "5";

        case (5):
            return "6";

        case (6):
            return "7";

        case (7):
            return "8";

        case (8):
            return "9";

        case (9):
            return "13";

        case (11):
            return "17";

        case (12):
            return "10";

        case (13):
            return "12";

        case (14):
            return "14";

        case (15):
            return "11";

        case (16):
            return "15";

        case (17):
            return "16";

        case (18):
            return "19";

        case (19):
            return "18";

        default:
            return "";

        }
    }

    /**
     * ����֤������ת������
     * @param zjlxdm String
     * @return String
     */
    private String getZjlxdm(String zjlxdm) {
        String zjlx = "";
        if (zjlxdm.equals("02")) {
            zjlx = "1";
        } else if (zjlxdm.equals("03")) {
            zjlx = "4";
        } else if (zjlxdm.equals("04")) {
            zjlx = "3";
        } else if (zjlxdm.equals("06")) {
            zjlx = "7";
        } else if (zjlxdm.equals("11")) {
            zjlx = "10";
        } else if (zjlxdm.equals("90") || zjlxdm.equals("01") ||
                   zjlxdm.equals("05") || zjlxdm.equals("08")) {
            zjlx = "6";
        }
        return zjlx;
    }

    /**
     * ƴ��ǩԼ����
     * @param qyrq String
     * @return String
     */
    private String getRq(String qyrq) {
        String sb = qyrq.substring(0, 4);
        if (qyrq.substring(4, 5).equals("0")) {
            sb = sb + "-" + qyrq.substring(5, 6);
        } else {
            sb = sb + "-" + qyrq.substring(4, 6);
        }
        if (qyrq.substring(6, 7).equals("0")) {
            sb = sb + "-" + qyrq.substring(7, 8);
        } else {
            sb = sb + "-" + qyrq.substring(6, 8);
        }
        return sb;
    }

    /**
     * ��ȡ��������Ϣ
     * @param sbxxForm SbxxForm �걨��Ϣform
     * @param userData UserData ��½��Ա��Ϣ
     * @param lb String  ��𣨸��˻��߷Ǹ��ˣ�
     * @return String
     */
    private String getTxmxx(SbxxForm sbxxForm, UserData userData, String lb) {
        StringBuffer sb = new StringBuffer();
        //��ȡ�걨����vo
        Sbzb sbzbvo = sbxxForm.getVoSbzb();
        //ȡ�����ء�������Ϣ��
        Tufwxx fwxx = sbxxForm.getVoTufwxx();
        //������˰��list
        List nsrList = new ArrayList();
        //����Ǹ���vo����
        Fgrxx fgrxx = new Fgrxx();
        //������Ϊ1���Ǹ���ҵ��ȡ����˰��list�����Ϊ2Ϊ�Ǹ���ҵ��ȡ�÷Ǹ�����Ϣ
        if (lb.equals("1")) {
            //ȡ����˰��list
            nsrList = sbxxForm.getNsrList();
        } else if (lb.equals("2")) {
            fgrxx = sbxxForm.getVoFgrxx();
        }

        //����ͷ��ʾ��
		String sfesf = sbxxForm.getVoTufwxx().getSfesf();
		if(sfesf != null && !sfesf.equals("")){
            if(sfesf.equals("00")){
                //����ͷ��ʾ��
                sb.append("^object_000001_000002^");
            }else{
                //����ͷ��ʾ��
                sb.append("^object_000002_000002^");
            }
        }
        //sb.append("^object_000002_000002^");

        //���Ӻ�ͬ���
        if (null != sbzbvo.getHtbh()) {
            sb.append(sbzbvo.getHtbh() + "^");
        } else {
            sb.append("" + "^");
        }

        //���Ӻ�ͬ����ǩԼ����,���������Ҫ�ж�
        String qyrq = DataConvert.TimeStamp2String(fwxx.getHtqdsj());
        sb.append(getRq(qyrq) + "^");
        //sb.append(DataConvert.TimeStamp2String(qyrq,"yyyy-mm-dd")+"^");
        //���ӽ��׷��ݵ�ַ���أ�ȡ��˰����Ա��������
        sb.append(getDzqx(userData.getSsdwdm()) + "^");
        //���ӽ��׷��ݵ�ַ,���������Ҫ�ж�
        sb.append(fwxx.getTdfwzldz() + "^");
        //���ӷ���Ȩ��ת������
        sb.append("162200^");
        //�����Ƿ�Ϊ�״������ѹ�����
        sb.append("0^");
        //���ӷ��ݲ�Ȩ֤��
        sb.append("" + "^");
        //���ӷ��ݲ�Ȩ֤�����
        sb.append("" + "^");
        //���ӷ��ݽ������
        String jzmj = DataConvert.BigDecimal2String(fwxx.getFwjzmj(), 3);
        //System.out.println("jzmj====" + jzmj);
        if (null != jzmj && jzmj.equals("0000")) {
            sb.append("" + "^");
        } else {
            sb.append(jzmj + "^");
        }
        //���ӽ����ṹ
        sb.append("" + "^");
        //���ӹ滮��;���������Ȩ��ת������Ϊ03����05�������������Ϊ��ѡ���滮��;����ת�븳ֵ��
        //�����Ȩ��ת������Ϊ�����Ļ����滮��;Ϊ��ֵ
        if (fwxx.getTdfwqszylx().equals("03") ||
            fwxx.getTdfwqszylx().equals("05")) {
            //���ӹ滮��;,��������Ϊ��סլ��ת��Ϊд��¥����ֵΪ5
            if (fwxx.getFwlxdm().equals("04")) {
                sb.append("5^");
            } else
            //���ӹ滮��;,��������Ϊ����סլ��ת��Ϊ��ͨסլ����ֵΪ1
            if (fwxx.getFwlxdm().equals("03")) {
                sb.append("1^");
            } else {
                sb.append("" + "^");
            }

        } else {
            //���ӹ滮��;
            sb.append("" + "^");
        }

        // ���Ӻ�ͬ�ܼ�,����˳�������۸񡢳ɽ��۸��ۺϼ۸�
        //ȡ�������۸�
        String pgjg = DataConvert.BigDecimal2String(fwxx.getPgjgrmb());
        //ȡ�óɽ��۸�
        String cjjg = DataConvert.BigDecimal2String(fwxx.getCjjgrmb());
        //ȡ���ۺϼ۸�
        String zhjg = DataConvert.BigDecimal2String(fwxx.getZhjgrmb());
        if (null != cjjg) {
            sb.append(cjjg + "^");
        } else if (null != pgjg) {
            sb.append(pgjg + "^");
        } else if (null != zhjg) {
            sb.append(zhjg + "^");
        }

        //����¥��/�ܲ���
        sb.append("" + "^");
        //���������
        sb.append("" + "^");
        //������ҽ��
        sb.append("" + "^");
        //���ӻ��ʣ����ҿճ�������Ϣ
        sb.append("" + "^^");
        if (lb.equals("1")) {
            //��������Ϣ,��˰����Ϣ������Ϊ�գ�����Ҫ�ж�
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                //������������
                sb.append(grxx.getNsrmc() + "~");
                //����������𣬴˴�Ϊ1������
                sb.append("1~");
                //����֤������
                sb.append(getZjlxdm(grxx.getSfzjlx()) + "~");
                //����֤������
                sb.append(grxx.getSfzjhm() + "~");
                //����Ȩ���˷ݶ�
                sb.append("" + "~");
                //�����Ƿ������һ����¼���ж�ĩβ�ķָ����Ƿ���Ҫ
                if (i == nsrList.size() - 1) {
                    //������ϵ�˵绰,��������һ���Ļ�����Ҫ����^���ţ������н��������
                    sb.append(grxx.getLxdh() + "^");
                } else {
                    //������ϵ�˵绰
                    sb.append(grxx.getLxdh() + "~");
                }
            }

            //���ӷ��ز��н��������,���һ���ַ���û��^����
            sb.append("");
            System.out.println("����inputStr ====" + sb.toString());
        } else if (lb.equals("2")) {
            //������������(��˰������)
            sb.append(fgrxx.getNsrmc() + "~");
            //����������𣬴˴�Ϊ2���Ǹ���
            sb.append("2~");
            //����֤�����ͣ��Ǹ���Ϊ����������ֵΪ6
            sb.append("6~");
            //����֤�����루�Ǹ���Ϊ��������룩
            sb.append(fgrxx.getJsjdm() + "~");
            //����Ȩ���˷ݶ�
            sb.append("" + "~");
            //������ϵ�˵绰
            if (null == fgrxx.getLxdh()) {
                sb.append("" + "^");
            } else {
                sb.append(fgrxx.getLxdh() + "^");
            }
            //���ӷ��ز��н��������,���һ���ַ���û��^����
            sb.append("");
            System.out.println("�Ǹ���inputStr ====" + sb.toString());
        }

        return sb.toString();

    }


}
