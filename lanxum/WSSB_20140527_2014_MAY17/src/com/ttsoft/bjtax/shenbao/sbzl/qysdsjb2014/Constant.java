package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014;

/**
 * 
 * ��Ŀ���ƣ�wssb_20140528   
 * �����ƣ�Constant   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-4-16 ����2:32:02   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-4-16 ����2:32:02   
 * �޸ı�ע��   
 * @version  1.0
 */
public class Constant
{
	
	public static String JUMP_FLAG_NAME="QYSDS_2014_JUMP_FLAG_NAME";
	
    /**
     * ��ҵ����˰��(��)��Ԥ����˰�걨��(A��)id
     */
    public static String TABLE_ID_CZZSSDS_2014 = "28";
    public static String TABLE_NAME_CZZSSDS_2014 = "��ҵ����˰��(��)��Ԥ����˰�걨��(A��)";
    /**
     * ������REQUEST�еķ��ص�ַ2012��
     */
    public static final String REQ_KEY_RETURN_CZZSSDS_QYJB2014 ="/shenbao/czzsqyjb2014.dc?actionType=Show"; 
    public static final String REQ_KEY_RETURN_HDZSSDS_QYJB2014 ="/shenbao/hdzsqyjb2014.dc?actionType=Show";
    /**
     * ��ҵ����˰�£�������Ԥ����˰�걨��B�ࣩid
     */
    public static String TABLE_ID_HDZSSDS_2014 = "29";
    public static String TABLE_NAME_HDZSSDS_2014 = "��ҵ����˰�£�������Ԥ����˰�걨��B�ࣩid";
    public static String ZFJGSDSJB_2014_MAX_ROW = "rows";
    public static int ZFJGSDSJB_2014_DEFAULT_MX_ROW_NUMBER = 15;
    /**
     * ��ҵ����˰������˰��֧���������id
     */
    public static String TABLE_ID_ZFJGSDS_2014 = "30";
    public static String TABLE_NAME_ZFJGSDS_2014 = "��ҵ����˰������˰��֧���������";
    
    /**�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�  ID��NAME,added by zhangj,2014.12.01*/
    public final static String TABLE_ID_GDZCJSZJYJQK_2014 = "33";
    public final static String TABLE_NAME_GDZCJSZJYJQK_2014 = "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�";


	/******************begin add by wangcy 2014-03-13**************************/
	/**
	 * 2014��ҵ����˰�����汾��
	 */
	public static String QYSDSJB_VERSION_2014 = "20140101";
	/**
	 * 2014������������˰����ҵ�����
	 */
	public static String CA_YWLXDM_CZZSSDSJB_2014="010031";
	public static String CA_SCHEMADM_CZZSSDSJB_2014="20140603";
	public static String CA_XSLTDM_CZZSSDSJB_2014="20140603";
	
    /**
     * 2014��ҵ����˰������˰��֧�����������ҵ�����
     */
    public static String CA_YWLXDM_ZFJGSDSJB_2014 = "010030";
    public static String CA_SCHEMADM_ZFJGSDSJB_2014 = "20140603";
    public static String CA_XSLTDM_ZFJGSDSJB_2014 = "20140603";


	/**
	 * 2014�˶���������˰����ҵ�����
	 */
	public static String CA_YWLXDM_HDZSSDSJB_2014="010029";	
	public static String CA_SCHEMADM_HDZSSDSJB_2014="20140603";
	public static String CA_XSLTDM_HDZSSDSJB_2014="20140603";
	
	/******************end add by wangcy 2014-06-03***************************/



//	******************���շ�ʽ�����*****************************************
    /**
     * ����������
     */
    public static final String ZSFSDM_CYLZS = "01";

    /**
     * ��������
     */
    public static final String ZSFSDM_DEZS = "02";

    /**
     * ��������
     */
    public static final String ZSFSDM_CZZS = "03";

    //***********************************************************


//	******************���շ�ʽ���ƴ����*****************************************
    /**
     * ����������
     */
    public static final String ZSFSNAME_CYLZS = "����������";

    /**
     * ��������
     */
    public static final String ZSFSNAME_DEZS = "��������";

    /**
     * ��������
     */
    public static final String ZSFSNAME_CZZS = "��������";


    //***********************************************************
     /**
      * ������˰������س�������ʾ��Ϣ
      */
     //������˰
     public final static String HZNSFF_QYSDSJB2008_CZZSSDS_HZNS = "1";
     //������˰
     public final static String HZNSFF_QYSDSJB2008_CZZSSDS_DLNS = "2";
     //������˰��ʽ-�ܻ���
     public final static String HZNSFS_QYSDSJB2008_CZZSSDS_ZJG = "1";
     //������˰��ʽ-��֧����
     public final static String HZNSFS_QYSDSJB2008_CZZSSDS_FZJG = "2";
     /**
      * У��������ʾ
      */
     //������˰-�ܻ���
     public final static int CHECK_HZNSFF_TYPE_HZNS_ZJG = -1;
     //û������
     public final static int CHECK_HZNSFF_TYPE_NO_DATA = 0;
     public final static String CHECK_HZNSFF_MESSAGE_NO_DATA = "����ҵ��δ��������ռ����걨�������ڴ�¼�룬����¼��������ռ����걨��";
     //������˰
     public final static int CHECK_HZNSFF_TYPE_DLNS = 1;
     public final static String CHECK_HZNSFF_MESSAGE_DLNS = "����ҵ�Ĳ������ջ�����˰����Ϊ������˰�������ڴ�¼�룡";
     //������˰-��֧����
     public final static int CHECK_HZNSFF_TYPE_HZNS_FZJG = 2;
     public final static String CHECK_HZNSFF_MESSAGE_FZJG = "����ҵ�Ĳ������ջ�����˰����Ϊ��֧�������ɣ������ڴ�¼�룡";
     
     /**
      * �̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�ҵ�����2014,ADDED BY ZHANGJ 2014.12.01
      */
     public static String CA_XSLTDM_GDZCJSZJYJQKJB_2014 = "20141201";
     public static String CA_YWLXDM_GDZCJSZJYJQKJB_2014 = "010037";
     public static String CA_SCHEMADM_GDZCJSZJYJQKJB_2014 = "20141201";
     
     
     public static String CZZSSDSJB_CBSJ_MSSRXM="cbMssrxmList";
     public static String CZZSSDSJB_CBSJ_JZMZXM="cbJzmzxmList";
     public static String CZZSSDSJB_CBSJ_JMXM="cbJmxmList";
     
     public static String CZZSSDSJB_DM_MSSRXM="MssrxmDmList";
     public static String CZZSSDSJB_DM_JZMZXM="JzmzxmDmList";
     public static String CZZSSDSJB_DM_JMXM="JmxmDmList";
     
     public static String CZZSSDSJB_DM_MAPS="CZZSSDSJB_DM_MAPS";
}
