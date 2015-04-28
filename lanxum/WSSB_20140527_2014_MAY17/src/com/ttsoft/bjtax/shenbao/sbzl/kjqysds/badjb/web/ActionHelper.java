package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.web;


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
import java.util.List;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.dj.model.dm.GJ;
import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbListVO;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbVO;
import com.syax.bjtax.shenbao.model.kjqysds.KJYWRXX;
import com.syax.bjtax.shenbao.model.kjqysds.FJMQYXX;
import com.syax.bjtax.shenbao.model.kjqysds.BAHTXX;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo.BadjbBO;



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
        if (session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) == null) {
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
            session.setAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ, gjdq);
        }
        // ��ȡ�����б�
        if (session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
            List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
//            System.out.println("bzList size = " + bzList.size());
            // ת��֮��ı���
            String[][] bz = new String[bzList.size()][2];
            for (int i = 0; i < bzList.size(); i++) {
                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
            }
            session.setAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
        }
    }

    /**
     * ��bo����ת����VO����ǰ̨ʹ��
     * @param bo BadjbBO
     * @param vo BadjbVO
     */
    public static void XMLVO2BO(BadjbVO vo, BadjbBO bo)
    {
        if(bo != null)
        {
        	// �����Ǽ����
        	bo.setBadjxh(vo.getBadjbxh());
            // �����
            bo.setTbrq(vo.getTbrq());
            // չʾ�����
            bo.setTbrqShow(vo.getTbrqShow());
            // ��ǰ����
            bo.setCurrentPage(Integer.parseInt(vo.getCurrentPage()));
            // �޸ı��
            bo.setModifyFlag(vo.getModifyFlag());
            // �۽���������Ϣ
            bo.setKjywrxx(vo.getKjywrxx() == null ? new KJYWRXX() : vo.getKjywrxx());
            // �Ǿ�����ҵ��Ϣ
            bo.setFjmqyxx(vo.getFjmqyxx() == null ? new FJMQYXX() : vo.getFjmqyxx());
            // ��ͬ��Ϣ
            bo.setHtxx(vo.getHtxx() == null ? new BAHTXX() : vo.getHtxx());
        }
    }


    /**
     * ��bo����ת����VO����ǰ̨ʹ��
     * @param bo BadjbBO
     * @param vo BadjbVO
     */
    public static void BO2XMLVO(BadjbBO bo, BadjbVO vo)
    {
        if(bo != null)
        {
            // �����
            vo.setTbrq(bo.getTbrq());
            // չʾ�����
            vo.setTbrqShow(bo.getTbrqShow());
            vo.setModifyFlag(bo.getModifyFlag());
            // �۽���������Ϣ
            vo.setKjywrxx(bo.getKjywrxx() == null ? new KJYWRXX() : bo.getKjywrxx());
            // �Ǿ�����ҵ��Ϣ
            vo.setFjmqyxx(bo.getFjmqyxx() == null ? new FJMQYXX() : bo.getFjmqyxx());
            /**
             * ��ͬ��Ϣ
             */
            // ����������������
            if(bo.getHtxx() != null)
            {
            	// ������������
	            String qtzlmc = bo.getHtxx().getQtzlmc();
	            //�������������Ƹ�ʽ��ȥ��
	            qtzlmc = qtzlmc.replaceAll("<br/>","\r\n");
	            qtzlmc = qtzlmc.replaceAll("<br>","\r\n");
	            qtzlmc = qtzlmc.replaceAll("&nbsp;"," ");
	            
	            bo.getHtxx().setQtzlmc(qtzlmc);
            }
            vo.setHtxx(bo.getHtxx() == null ? new BAHTXX() : bo.getHtxx());
            // ������ͬ��Ϣ
            BadjbListVO listVO = new BadjbListVO();
            listVO.setMxxx(bo.getRecordList());
            vo.setMxxx(listVO);

            // XML�ĵ���Ϣ
            vo.setXsltVersion(KjqysdsConstant.CA_XSLTDM_BADJB_2009);
            vo.setSchemaVersion(KjqysdsConstant.CA_SCHEMADM_BADJB_2009);
            vo.setYwlx(KjqysdsConstant.CA_YWLXDM_BADJB_2009);
            vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        }
    }
}
