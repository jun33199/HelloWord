package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Action������</p>
 *
 * <p>Description: ��ع��÷���</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import javax.servlet.http.HttpServletRequest;
import com.ttsoft.framework.exception.ApplicationException;
import javax.servlet.http.HttpSession;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;

import java.text.NumberFormat;
import java.util.List;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.dj.model.dm.GJ;
import com.ttsoft.bjtax.dj.model.dm.BZ;



public class ActionHelper
{
    public ActionHelper()
    {
    }

    /**
     * ��ʼ��ҳ��������Ϣ
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request) throws ApplicationException
    {
        // ��ȡsession
        HttpSession session = request.getSession(false);
        // ��ȡ�����б�
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) == null) {
            List gjdqList = CodeTableUtil.getCodeTableList(CodeTableKey.GJ);
//            System.out.println("gjdqList size = " + gjdqList.size());
            // ת��֮��Ĺ��ҵ���
            String[][] gjdq = new String[gjdqList.size()][2];
            for (int i = 0; i < gjdqList.size(); i++) {
                GJ gj = (GJ) gjdqList.get(i);
                if("CHN".equals(gj.getGjdm()))
                {
                    gjdqList.remove(i);
                    i--;
                }
                else{
                    gjdq[i][0] = gj.getGjdm();
                    gjdq[i][1] = gj.getGjmc();
                }
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ, gjdq);
        }
        // ��ȡ�����б�
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
            List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
//            System.out.println("bzList size = " + bzList.size());
            // ת��֮��ı���
            String[][] bz = new String[bzList.size()][2];
            for (int i = 0; i < bzList.size(); i++) {
                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
        }
    }

    /**
     * ��form����ת����BO���󹩺�̨ʹ��
     * @param form BadjbForm
     * @param bo BadjbBO
     */
    public static void form2Bo(BadjbForm form, BadjbBO bo)
    {
        // �����ǼǱ���ţ�������
        bo.setBadjxh(form.getBadjxh());
        // ���������
        bo.setJsjdm(form.getJsjdm());
        // �����
        bo.setTbrq(form.getTbrq());
        // �����ǼǱ��
        bo.setBadjbbh(form.getBadjbbh());
        // �۽���������Ϣ
        bo.setKjywrxx(form.getKjywrxx());
        // �Ǿ�����ҵ��Ϣ
        bo.setFjmqyxx(form.getFjmqyxx());
        // ��ͬ��Ϣ
        bo.setHtxx(form.getHtxx());
        // ��¼��
        bo.setTotalCount(form.getTotalCount());
        // ��ҳ��
        bo.setTotalPage(form.getTotalPage());
        // ��ǰҳ
        bo.setCurrentPage(form.getCurrentPage());
        // ״̬��ʶ
        bo.setZtbz(form.getZtbz());
        // �޸ı�ʶ
        bo.setModifyFlag(form.getModifyFlag());
    }

    /**
     * ��bo����ת����form����ǰ̨ʹ��
     * @param bo BadjbBO
     * @param form BadjbForm
     */
    public static void BO2form(BadjbBO bo, BadjbForm form)
    {
        if(bo != null)
        {
            // �����ǼǱ���ţ�������
            form.setBadjxh(bo.getBadjxh());
            // ���������
            form.setJsjdm(bo.getJsjdm());
            // �����
            form.setTbrq(bo.getTbrq());
            // �����ǼǱ��
            form.setBadjbbh(bo.getBadjbbh());
            // �۽���������Ϣ
            form.setKjywrxx(bo.getKjywrxx());
            // �Ǿ�����ҵ��Ϣ
            form.setFjmqyxx(bo.getFjmqyxx());
            /**
             * ��ͬ��Ϣ
             */
            // ����������������
            // ������������
            String qtzlmc = bo.getHtxx().getQtzlmc();
            //�������������Ƹ�ʽ��ȥ��
            qtzlmc = qtzlmc.replaceAll("<br/>","\r\n");
            qtzlmc = qtzlmc.replaceAll("<br>","\r\n");
            qtzlmc = qtzlmc.replaceAll("&nbsp;"," ");
            
            bo.getHtxx().setQtzlmc(qtzlmc);
            form.setHtxx(bo.getHtxx());
            // ��¼��
            form.setTotalCount(bo.getTotalCount());
            // ��ҳ��
            form.setTotalPage(bo.getTotalPage());
            // ��ǰҳ
            form.setCurrentPage(bo.getCurrentPage());
            // ��ѯ���
            form.setRecordList(bo.getRecordList());

        }
    }
    
    /**
     * 
     */
    public  static void splitJe(BadjbForm form)
    {	
    	if(form.getHtxx().getHtje()!=null){
    	double money=Double.parseDouble(form.getHtxx().getHtje());
    	NumberFormat nf=NumberFormat.getInstance(); 
		String ss=nf.format(money); 
		form.getHtxx().setHtje(ss);
    	}
    }
}
