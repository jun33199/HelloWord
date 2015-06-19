package com.creationstar.bjtax.qsgl.util;

/**
 * <p>Title: </p>
 *
 * <p>Description: ��˰�걨���ݵ�״̬�����ṩ��ȡÿ��״̬�����ƣ��Լ�ÿ��װ̬��ǰһ״̬�Ƚӿ�
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SbState {
    /**=========================�걨�����и����ʶ��˵��====================================*/
    /**
     * �걨������
     * ״̬��ʾ��
     * 0--����
     * 1--��ӡ�걨��
     * 2--�Ѵ�ӡ�����걨��
     * 3--������ӡ  ���ڲ���
     * 4--��ӡ�˶�֪ͨ��
     * 5--��ʱ�������ͬ��
     * 6--��ʱ������˲�ͬ��
     * 7--�Ѹ��ˣ���ӡ��˰֤/�ɿ��飩
     * 91--����
     * 99--�����걨����
     * ��Դ��Constants
             public static final String ZB_ZTBS_BC = "0";
             public static final String ZB_ZTBS_DY = "1";
             public static final String ZB_ZTBS_DY_JMSBB = "2";
             public static final String ZB_ZTBS_CX_DY = "3";
             public static final String ZB_ZTBS_DY_HD = "4";
             public static final String ZB_ZTBS_JS_TY = "5";
             public static final String ZB_ZTBS_JS_BTY = "6";
             public static final String ZB_ZTBS_YFH = "7";
             public static final String ZB_ZTBS_ZF = "91";
             public static final String ZB_ZTBS_YRK = "99";
     */

    /**
     * ��ȡ״̬���ƣ�˵����
     * @param code String ״̬����
     * @return String ״̬˵��
     */
    public static String getStateName(String code) {
        if (code == null) {
            return "";
        }
        if (code.equals(Constants.ZB_ZTBS_BC)) {
            return "δ��ӡ";
        } else if (code.equals(Constants.ZB_ZTBS_DY)) {
            return "�Ѵ�ӡ�걨��";
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            return "�Ѵ�ӡ�����걨��";
        } else if (code.equals(Constants.ZB_ZTBS_CX_DY)) {
            return "������ӡ";
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            return "��ӡ�˶�֪ͨ��";
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            return "��ʱ�������ͬ��";
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            return "��ʱ������˲�ͬ��";
        } else if (code.equals(Constants.ZB_ZTBS_YFH)) {
            return "�Ѹ��ˣ���ӡ��˰֤��ɿ��飩";
        } else if (code.equals(Constants.ZB_ZTBS_ZF)) {
            return "����";
        } else if (code.equals(Constants.ZB_ZTBS_YRK)) {
            return "�����걨����";
        } else {
            return "";
        }
    }

    /**
     * ��ȡ������ǰ״̬���ظ����״̬����
     * @param code ����״̬��־
     * @param jmsbs ����˰��־
     * @return null �����ǰ״̬�Ƿ����ߵ�ǰ״̬���ܻظ�
     */
    public static String getCancelStateCode(String code, String jmsbs) {
        if ((code == null) || (jmsbs == null)) {
            return "";
        }

        if (code.equals(Constants.ZB_ZTBS_BC)) {
            //"δ��ӡ";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY)) {
            //"�Ѵ�ӡ�걨��";
            return Constants.ZB_ZTBS_BC;
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            //"�Ѵ�ӡ�����걨��";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_CX_DY)) {
            // "������ӡ";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            // "��ӡ�˶�֪ͨ��";
            return Constants.ZB_ZTBS_JS_TY;
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            // "��ʱ�������ͬ��";
            return Constants.ZB_ZTBS_DY;
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            // "��ʱ������˲�ͬ��";
            return Constants.ZB_ZTBS_DY;
        } else if (code.equals(Constants.ZB_ZTBS_YFH)) {
            // "�Ѹ��ˣ���ӡ��˰֤��ɿ��飩";
            if (jmsbs.equals(Constants.ZB_BLJMSBS_BFHBLTJ)) { //�����ϣ�û�д�ӡ�˶�֪ͨ��
                return Constants.ZB_ZTBS_DY;
            } else {
                return Constants.ZB_ZTBS_DY_HD;
            }
        } else if (code.equals(Constants.ZB_ZTBS_ZF)) {
            // "����";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_YRK)) {
            // "�����";
            return "";
        } else {
            return "";
        }
    }

    /**
     * ��ȡ������ǰ״̬���ظ����״̬���� Ϊ�����걨ר�ŵķ���
     * @param code ����״̬��־
     * @param jmsbs ����˰��־
     * @return null �����ǰ״̬�Ƿ����ߵ�ǰ״̬���ܻظ�
     */
    public static String getCancelStateCode4Jmsb(String code, String jmsbs) {
        if ((code == null) || (jmsbs == null)) {
            return "";
        }

        if (code.equals(Constants.ZB_ZTBS_BC)) {
            //"δ��ӡ";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            //"�Ѵ�ӡ�����걨��";
            return Constants.ZB_ZTBS_BC;
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            // "��ӡ�˶�֪ͨ��";
            return Constants.ZB_ZTBS_DY_JMSBB;
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            // "��ʱ�������ͬ��";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            // "��ʱ������˲�ͬ��";
            return Constants.ZB_ZTBS_DY_HD;
        } else {
            return "";
        }
    }

    /**
     * ��ȡ������ǰ״̬���ظ����״̬����
     * @param code String
     * @return String
     */
    public static String getCancelStateName(String code, String jmsbs) {
        return getStateName(getCancelStateCode(code, jmsbs));
    }

    /**
     * ��ȡ������ǰ״̬���ظ����״̬���� Ϊ�����걨ר�ŵķ���
     * @param code String
     * @return String
     */
    public static String getCancelStateName4Jmsb(String code, String jmsbs) {
        return getStateName(getCancelStateCode4Jmsb(code, jmsbs));
    }

}
