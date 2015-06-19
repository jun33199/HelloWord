package com.syax.bjtax.shenbao.jmba.qygzb.web;

import com.syax.common.web.action.DcBaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;

public class QygzbAction extends DcBaseAction{
    public QygzbAction() {
    }

    /**
     * ¼�뱸������ ��ʼ������
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {

        // ȡ��userdata
        UserData userdata = (UserData) this.getUserData(request);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.QYGZB16_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
        voPackage.setUserData(userdata);
        //@todo �ӵڶ�����תҳ���request�л�ȡ
        String BASQWSH = "062008000002";
        String jsjdm = userdata.getYhid();
        //��˰�˵�����˰�����
        String zgswjg = userdata.getSsdwdm();
        Map map = new HashMap();
        map.put("BASQWSH",BASQWSH);
        map.put("jsjdm",jsjdm);

        voPackage.setData(map);

        // ���ú�̨������ȡ�÷���ֵ
        voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
                voPackage);

        JmbaVO vo  = this.convertToXmlVO((Map)voPackage.getData(),userdata);
        //��������
        String strXml = vo.toXML();
        Debug.out(strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());

        // ת����ʾҳ��
        return CAConstants.SHOW;
    }

    private JmbaVO convertToXmlVO(Map map,UserData ud) {
        //1 ���ϲ�VO
        JmbaVO vo = new JmbaVO();
        //2 ��˰��VO 1.set 2
        NsrxxVO nsrxx = new NsrxxVO();
        nsrxx.setJsjdm(ud.getYhid());
        nsrxx.setNsrmc(ud.getYhmc());
        nsrxx.setSwjgzzjgdm(ud.getSsdwdm());
        //1.set 2
        vo.setNsrxx(nsrxx);
        //3 ����VO 1-n ��������� 1.list.add 3s
        JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
        //db query result,set zbvo data
        //zbvo.setJmbasxdm

        //SF_JL_QYSDSJMSBAJLZLQD
        List zlqdVOs = (List) map.get("JmbaZlqdVO");
        //4 ��ϸ����VO 1-ns   3.list.add 4s
//        Jmba12VO vo13 = (Jmba13VO)map.get("Jmba12VO");
        //3.list.add 4s
//        zbvo.getQysdsjmba().add(vo12);
//        �����嵥��ѯVO����,����ҳ����ʱ������
//        zbvo.getBajlzlqd().add(zlqdVOs);
        //1.list.add 3s
        vo.getJmsbajl().add(zbvo);
        vo.setNsrxx(nsrxx);
        vo.setXsltVersion("091216");
        return vo;
    }



    /**
     * �������Ȩ�޽��м��
     */
    protected String beforePerform(HttpServletRequest request,
            HttpServletResponse response) {
        // ���Ȩ�� ������û�м������
        // System.out.println("beforePerform");
//        if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
//        {
//
//            return CAConstants.NOPERMISSION;
//        }
        return null;
    }

}
