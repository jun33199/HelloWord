package com.ttsoft.bjtax.smsb.wrkcx.web;

/*
 * <p>Title: ������˰��������ϵͳ�����ƻ�ͳ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */


import java.util.Map;
import java.util.HashMap;

/**
 * <p>Title: ������˰��������ϵͳ�����ƻ�ͳ��</p>
 * <p>Description: ͨ�ò�ѯ�ͻ��˳�������</p>
 * @author �������飭���¾�
 * @version 1.1
 */
public class WebConstantKey
{
    /**
     * Ĭ�Ϲ��캯��
     */
    public WebConstantKey()
    {
    }

    /**
     * ��ѯ���ط־�˰Դ�仯����Processor��·��
     */
    public final static String SYBDCX_PROCESSOR =
        "com.ttsoft.bjtax.jhtj.sybdcx.processor.SybdcxProcessor";

    /**
     * �嵥��ѯ
     */
    public final static int ACTION_QUERY_FHQD = 1;

    /**
     * ���ܲ�ѯ
     */
    public final static int ACTION_QUERY_HZ = 2;

    /**
     * ��ϸ��Ϣ��ѯ
     */
    public final static int ACTION_QUERY_MXQD = 3;

    /**
     * ��ѯҳ����SYBDCXBO ��ŵ� session �е�Key
     */
    public final static String SESSION_KEY_SYBDCXBO = "SESSION_KEY_SYBDCXBO";
    /**
     * ��ѯҳ����DJXXXXBO ��ŵ� session �е�Key
     */
    public final static String SESSION_KEY_DJXXXXBO = "SESSION_KEY_DJXXXXBO";

    /**
     * �嵥��ѯFY_LIST
     */
    public final static String FY_LIST = "FY_LIST";
    /**
     * ���ܲ�ѯHZ_LIST
     */
    public final static String HZ_LIST = "HZ_LIST";
    /**
     * ��ϸ��Ϣ��ѯHZ_LIST
     */
    public final static String MX_LIST = "MX_LIST";

    /**
     * �־���������ѯ
     */
    public final static int FJRKQKCX = 21;

    /**
     * �־���˰�����ѯ
     */
    public final static int FJTSQKCX = 22;
    /**
     * ˰������������ѯ
     */
    public final static int SWSRKQKCX = 23;

    /**
     * ˰������˰�����ѯ
     */
    public final static int SWSTSQKCX = 24;

    /**
     * �־����ת�����嵥
     */
    public final static int FJRKZCHCX = 1;

    /**
     * �־���˰ת�����嵥
     */
    public final static int FJTSZCHCX = 2;
    /**
     * ˰�������ת�����嵥
     */
    public final static int SWSRKZCHCX = 3;

    /**
     * ˰������˰ת�����嵥
     */
    public final static int SWSTSZCHCX = 4;

    /**
     * �־����ת�뻧�嵥
     */
    public final static int FJRKZRHCX = 5;

    /**
     * �־���˰ת�뻧�嵥
     */
    public final static int FJTSZRHCX = 6;
    /**
     * ˰�������ת�뻧�嵥
     */
    public final static int SWSRKZRHCX = 7;

    /**
     * ˰������˰ת�뻧�嵥
     */
    public final static int SWSTSZRHCX = 8;

    /**
     * �־������ٻ��嵥
     */
    public final static int FJRKJSHCX = 9;

    /**
     * �־���˰���ٻ��嵥
     */
    public final static int FJTSJSHCX = 10;
    /**
     * ˰���������ٻ��嵥
     */
    public final static int SWSRKJSHCX = 11;

    /**
     * ˰������˰���ٻ��嵥
     */
    public final static int SWSTSJSHCX = 12;

    /**
     * �־�����������嵥
     */
    public final static int FJRKXZHCX = 13;

    /**
     * �־���˰�������嵥
     */
    public final static int FJTSXZHCX = 14;
    /**
     * ˰��������������嵥
     */
    public final static int SWSRKXZHCX = 15;

    /**
     * ˰������˰�������嵥
     */
    public final static int SWSTSXZHCX = 16;

    /**
     * �־������ʱ�Ǽǻ��嵥
     */
    public final static int FJRKLSDJHCX = 17;

    /**
     * �־���˰��ʱ�Ǽǻ��嵥
     */
    public final static int FJTSLSDJHCX = 18;
    /**
     * ˰���������ʱ�Ǽǻ��嵥
     */
    public final static int SWSRKLSDJHCX = 19;

    /**
     * ˰������˰��ʱ�Ǽǻ��嵥
     */
    public final static int SWSTSLSDJHCX = 20;
    /**
     * ��ѯURI
     */
    public static final String RAK_REQUEST_URI = "RAK_REQUEST_URI";
    /**
     * ��ѯACTION
     */
    public static final String RAK_ACTION = "RAK_ACTION";

    /**
     * ���ܲ�ѯ
     */
    public static final String ACTION_HZCX = "SYBDCX_HZCX";

    /**
     * �嵥��ѯ
     */
    public static final String ACTION_QDCX = "SYBDCX_QDCX";
    /**
     * ÿҳ��ʾ������
     */
    public static final int PAGENUMB = 20;

    /**
     * ˰Դ�䶯��ѯ�־ֻ���ģ��
     */
    public static final String SYBDHZ_TEMPLET = "/jh/webapp/sybdcx/templates/SybdhzTempleExcel.xls";
    /**
     * ˰Դ�䶯��ѯ�ֻ�����ģ��
     */
    public static final String SYBDFH_TEMPLET = "/jh/webapp/sybdcx/templates/SybdfhTempleExcel.xls";
    /**
     * ˰Դ�䶯��ѯ��ϸģ��
     */
    public static final String SYBDMX_TEMPLET = "/jh/webapp/sybdcx/templates/SybdmxTempleExcel.xls";

    //��������
    public static final Map czlxdm = new HashMap();
    //��������
    public static final Map mclx = new HashMap();
    //˰���������
    public static final Map swjglx = new HashMap();
    //��ѯ����
    public static final Map cxlx = new HashMap();

    static
    {
        //���ò�������1��ת�룬2��ת����3�����٣�4��������5����ʱ�Ǽ�
        czlxdm.put("1", "ת��");
        czlxdm.put("2", "ת��");
        czlxdm.put("3", "����");
        czlxdm.put("4", "����");
        czlxdm.put("5", "��ʱ�Ǽ�");
        //������������
        mclx.put("1", "ת������");
        mclx.put("2", "ת�����");
        mclx.put("3", "˰��־�");
        mclx.put("4", "˰��־�");
        mclx.put("5", "˰��־�");
        //����˰�����
        swjglx.put("1", "�����أ��־֣�");
        swjglx.put("2", "�����أ��־֣�");
        swjglx.put("3", "˰����");
        swjglx.put("4", "˰����");
        swjglx.put("5", "�����أ��־֣�");
        swjglx.put("6", "�����أ��־֣�");
        swjglx.put("7", "˰����");
        swjglx.put("8", "˰����");
        swjglx.put("9", "�����أ��־֣�");
        swjglx.put("10", "�����أ��־֣�");
        swjglx.put("11", "˰����");
        swjglx.put("12", "˰����");
        swjglx.put("13", "�����أ��־֣�");
        swjglx.put("14", "�����أ��־֣�");
        swjglx.put("15", "˰����");
        swjglx.put("16", "˰����");
        swjglx.put("17", "�����أ��־֣�");
        swjglx.put("18", "�����أ��־֣�");
        swjglx.put("19", "˰����");
        swjglx.put("20", "˰����");
        swjglx.put("21", "�����أ��־֣�");
        swjglx.put("22", "�����أ��־֣�");
        swjglx.put("23", "˰����");
        swjglx.put("24", "˰����");

        //���ò�ѯ����
        cxlx.put("1", "���");
        cxlx.put("2", "��˰");
    }

}
