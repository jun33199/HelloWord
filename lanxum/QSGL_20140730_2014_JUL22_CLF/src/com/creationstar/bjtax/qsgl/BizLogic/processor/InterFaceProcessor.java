package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 *
 * <p>Title: ����˰�ṩ�Ľӿ�</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class InterFaceProcessor extends CommonProcessor {

    public static List getWszDataQS(String wspzh, String ndzb) throws
            BaseException {
        if (wspzh == null || wspzh.equals("")) {
            throw ExceptionUtil.getBaseException(
                    new ApplicationException("��˰֤�Ų���Ϊ�գ�"));
        }

        if (ndzb == null || ndzb.equals("")) {
            throw ExceptionUtil.getBaseException(
                    new ApplicationException("����ֱ���Ϊ�գ�"));
        }

        int step = 0;
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = new Qswszz();
            qswszz.setWszh(wspzh);
            qswszz.setNdzb(ndzb);
            qswszz.setPzzldm(Constants.WSZ_PZZLDM);

            step = 1;
            Sbjkzb sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().
                            query(qswszz, conn);

            //�������˰֤δ�������ɽɿ�����߽ɿ���δ��⣬��������˰������һ������Ϊ0��list
            if (sbjkzb.getJkpzh() == null || sbjkzb.getJkpzh().equals("") ||
                sbjkzb.getZwbs().charAt(1) != '1') {
                return new ArrayList(0);
            }

            //�������˰֤�ѻ��ܲ�������⣬�򷵻���˰֤����Ϣ
            step = 2;
            ArrayList wszList = new ArrayList();
            StringBuffer condition = new StringBuffer(" AND a.wszh = '");
            condition.append(wspzh).append("' AND a.ndzb = '").append(ndzb)
                    .append("' AND a.pzzldm in ('")
                    .append(Constants.WSZ_PZZLDM)
                    .append("', '")
                    .append(Constants.WSZ_PZZLDM_OLD)
                    .append("') ");

            wszList = DAOFactory.getInstance().getQswszzDAO().queryAll(
                    condition.toString(), conn);

            return wszList;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (step == 1) {
                throw ExceptionUtil.getBaseException(
                        new ApplicationException("��ѯ��˰֤�Ƿ��ѻ���ʱ����"));
            } else if (step == 2) {
                throw ExceptionUtil.getBaseException(
                        new ApplicationException("�õ���˰֤���ݳ���"));
            } else {
                //step == 0, �õ����ݿ����ӷ�������
                throw ExceptionUtil.getBaseException(
                        new ApplicationException(ex.getMessage()));
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    /**
     * �ṩ��Ʊ֤�Ľӿڣ����ڷ�����˰֤����Ľᱨ����
     * @param ndzb_wszh ArrayList
     * @param jbdh String
     * @param ud UserData
     * @return boolean
     * @throws BaseException
     */
    public static boolean Jiebao(ArrayList ndzb_wszh, String jbdh, UserData ud) throws
            BaseException {
        //���ص��Ƿ�����ɹ��ı�ʶ
        boolean flag = false;

        if (ndzb_wszh.isEmpty()) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "��˰֤�ź�����ֱ���Ϊ�գ�����"));
        }

        if (jbdh == null || jbdh.equals("")) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "�ᱨ���Ų���Ϊ�գ�����"));
        }
        if (ud == null) {
            throw ExceptionUtil.getBaseException(new ApplicationException(
                    "UserData����Ϊ�գ�����"));
        }

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String ndzb = null;
            String wszh = null;

            int size = ndzb_wszh.size();
            for (int i = 0; i < size; i++) {
                String haoma = (String) ndzb_wszh.get(i);
                if (haoma == null || haoma.equals("")) {
                    throw new Exception("��˰֤�ź�����ֱ���Ϊ�գ�����");
                }

                if (haoma.length() != 11 || haoma.length() != 12) {
                    throw new Exception("��˰֤�Ż�����ֱ��λ�����Ϸ�������");
                }
                ndzb = haoma.substring(0, 4);
                wszh = haoma.substring(4);

                Debug.out("��˰ƾ֤�ţ� " + wszh);
                Debug.out("����ֱ� " + ndzb);

                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.jbdh = '").append(jbdh).append(
                        "' WHERE a.wszh = '")
                        .append(wszh).append("' AND ndzb = '").append(ndzb).
                        append("' ");

                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                Debug.out("�����걨���Ÿ���˰֤����....");

                flag = true;
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(ud, "��˰�걨���գ�InterFaceProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
            return flag;
        }

    }

    /**
     * ����ʹ��
     * @param args
     */
    public static void main(String[] args) {
        try {
            /*ArrayList wszList = (ArrayList)InterFaceProcessor.getWszDataQS("0041186", "2003");
                         System.out.println(wszList.size() );
                         if(wszList.size() > 0)
                         {
                Qswszz wsz = ((QueryWszBo) wszList.get(0)).getQswszzVo();
                System.out.println(wsz.getHjsjje());
                         }*/

            /*
                         ArrayList list = new ArrayList();
                         String haoma = "20030000012";
                         String jbdh = "12345678";
                         list.add(haoma);

                         UserData ud = new UserData();
                         ud.yhid = "hanl";
                         ud.ssdwdm = "0601";
                         ud.yhmc = "����";
                         ud.xtsbm1 = "0106010017";


             boolean flag = InterFaceProcessor.Jiebao(list,jbdh, ud);
                         System.out.println(flag);*/
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
