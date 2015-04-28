/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

import com.ttsoft.framework.util.PropertiesUtil;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ��˰ʹ�õĳ�����</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class Constants {
    public static final String CQRJS_INIT =
            "var aryDataSource=[[\"\",\"\",\"\",\"02\",\"\",\"CHN\",\"0\",\"\",1]];";
    /** String "true" */
    public static final String TRUE = "true";

    /** String "false" */
    public static final String FALSE = "false";

    /** STATIC_CONTEXT_PATH ϵͳ����ľ�̬HTTP�ļ�·�� */
    private static String STATIC_CONTEXT_PATH = null;

    /** ҳ����Ϣ��ʾ��KEY */
    public static final String MESSAGE_KEY = "message";

    /** STACK_MSG_CAP ���쳣��ջ��Ϣת��Ϊ�ַ���ʱ��Ҫ�����ݿ��ж�Ӧ�ֶεĳ��� */
    public final static int STACK_MSG_CAP = 1000;

    /**===========================================================================*/
    /** ��ʾ QsglEJBSessionBean ��JNDI���� ,�Ա�������Ҫ��ø�EJB��ʱ���ѯ��*/
    public static final String JNDI_QsglEJBSessionBean_HOME =
            "QsglEJBHome_JNDI_Name";

    /** ��ʾ QsglEJBSessionBean ��JNDI���� ,�Ա�������Ҫ��ø�EJB��ʱ���ѯ��*/
    public static final String JNDI_DS_Name = "datasource_jndi_name";


    /**===========================================================================*/
    /** ��ʾ processor-map.properties �ļ������ƣ��Թ������ڵ��� */
    public static final String PROCESSOR_MAP_FILE_NAME =
            "qsgl_processor-map.properties";

    /** ��˰������ϵͳ�������ļ����ƣ��Թ������ڵ���*/
    public static final String QSGL_PROPERTIES_FILE_NAME =
            "bjtax_qsgl.properties";

    //ȡ�����processor��Ӧ�ĳ���
    public static final String Load_CodeTables = "load_codeTables";

    /**===========================================================================*/
    /**
     * ����DAOFactory�ж�λ��ͬDAO��
     */
    public static final String HdtzsDAO = "HdtzsDAO";
    public static final String HdjmmxDAO = "HdjmmxDAO";
    public static final String FgrxxDAO = "Fgrsb_DAO";
    public static final String GrxxDAO = "Grsb_DAO";
    //public static final String JmblcqDAO = "JmblcqDAO";
    public static final String JmsbbDAO = "JmsbbDAO";
    public static final String JsblcqDAO = "JsblcqDAO";
    public static final String JsblgyzfDAO = "JsblgyzfDAO";
    public static final String SbtdfwglDAO = "Jmsb_DAO";
    public static final String SbzbDAO = "Sbzb_DAO";
    public static final String SpjgxxDAO = "SpjgxxDAO";
    public static final String TufwxxDAO = "Fwtdxx_DAO";
    public static final String SbcqglDAO = "SbcqglDAO";
    public static final String SbgyzfDAO = "SbgyzfDAO";
    public static final String Zcwh_DAO = "Zcwh_DAO"; //����ά��DAO
    public static final String CodeTables_DAO = "CodeTable_DAO"; //�����DAO
    public static final String Wsz_DAO = "WszDAO";
    public static final String Wszmx_DAO = "WszmxDAO";
    public static final String Wszhz_DAO = "WszhzDAO";
    public static final String Jkszb_DAO = "SbjkzbDAO";
    public static final String Jksmx_DAO = "SbjkmxDAO";
    public static final String Szsm_DAO = "SzsmDAO";
    public static final String Yskm_DAO = "YskmDAO";
    public static final String Ysjc_DAO = "YsjcDAO";
    public static final String SzsmYskm_DAO = "SzsmYskmDAO";
    public static final String Swjgzzjg_DAO = "SwjgzzjgDAO";
    public static final String Zh_DAO = "ZhDAO"; //Ʊ֤�ʻ�DAO
    public static final String Wbhs_DAO = "WbhsDAO"; //Ʊ֤�ʻ�DAO
    public static final String Drzb_DAO = "DrzbDAO";
    public static final String DrpcInfo_DAO = "DrpcInfoDAO";
    public static final String Plcx_DAO = "PlcxDAO";
    public static final String CQXXB_DAO = "CqxxbDAO";
    public static final String CqxxCwb_DAO = "CqxxCwbDAO";
    public static final String Jsfs_DAO = "JsfsDAO";
    public static final String Bl_QueryJksDAO = "BlQueryJksDAO";
    public static final String PLSL_PZLRDAO = "PzlrDAO";
    
    //����������DAO
    public static final String Fwxx_DAO = "FwxxDAO";  //������Ϣ
    public static final String ClfjyxxCX_DAO="ClfjyxxCXDAO";//���ݽ�����Ϣ��ѯDao
    public static final String MfgrxxBuyer_DAO = "MfgrxxBuyerDAO";  //�򷽸�����Ϣ
    public static final String MfgrxxSeller_DAO = "MfgrxxSellerDAO";  //����������Ϣ
    public static final String MfsbxxzbSeller_DAO = "MfsbxxzbSellerDAO";  //�����걨��Ϣ������
    public static final String MfsbxxmxSeller_DAO = "MfsbxxmxSellerDAO";  //�����걨��Ϣ���ӱ�
    public static final String Htypzdzgxb_DAO = "HtypzdzgxbDAO";  //��ͬ��ƾ֤���չ�ϵ��
    public static final String HtypzdzgxbLs_DAO = "HtypzdzgxbLsDAO";  //��ͬ��ƾ֤���չ�ϵ��ʱ��
    public static final String SzsmInit_DAO = "SzsmInitDAO";  //˰��˰Ŀ��ʼ��    
    public static final String MfsbxxprintSeller_DAO = "MfsbxxprintSellerDAO";  //�����걨��Ϣ��ѯ 
    public static final String Fwhdxxb_DAO = "FwhdxxbDAO";  //���ݺ˶���Ϣ��
    public static final String Qscxdyrz_DAO = "QscxdyrzDAO";  //������������˰��ѯ��ӡ������¼��־
    public static final String Clfjycs_DAO = "ClfjycsDAO"; //���������ײ�����
    
    //��Ʊ����DAO
    public static final String Fpkf_DAO = "FpkfDAO";  //��Ʊ�ⷿ
    public static final String Fpzl_DAO = "FpzlDAO";  //��Ʊ����
    public static final String Kplx_DAO = "KplxDAO";  //��Ʊ����
    public static final String Fpczz_DAO = "FpczzDAO";  //��Ʊ��Ʊ����
    public static final String Fpczmx_DAO = "FpczmxDAO";  //��Ʊ��Ʊ��ϸ
    public static final String Fpkc_DAO = "FpkcDAO";  //��Ʊ���
    public static final String Fpkpczrz_DAO = "FpkpczrzDAO"; //��Ʊ��Ʊ������ʱ��

    //������servlet context�д�Ŵ�����hashmap
    public static final String CodeTables = "CodeTablesMap";

    //��������HashMap�е�����ά��������key
    public static final String ZCWH = "zcwh";
    //��������HashMap�еĻ��ʴ�����key
    public static final String HL = "hl";
    //��������HashMap�еı��ִ�����key
    public static final String BZ = "bz";
    //��������HashMap�еķ������ʹ�����key
    public static final String FWLX = "fwlx";
    //��������HashMap�е�֤�����ʹ�����key
    public static final String ZJLX = "zjlx";
    //��������HashMap�е�֤�����ʹ�����key
    public static final String ZJLXMAP = "zjlxmap";
    //��������HashMap�еĽ�˰��ʽ������key
    public static final String JSFS = "jsfs";
    //��������HashMap�е���˰�����ʹ�����key
    public static final String NSRLX = "nsrlx";
    //��������HashMap�е���˰���ط�����;������key
    public static final String TDFWYT = "tdfwyt";
    //��������HashMap�еĲ���������˰���ط�����;������key
    public static final String BZ_TDFWYT_GR = "bz_tdfwyt_gr";
    //��������HashMap�еĲ����Ǹ�����˰���ط�����;������key
    public static final String BZ_TDFWYT_FGR = "bz_tdfwyt_fgr";
    //��������HashMap�еĲ������������key
    public static final String BZQK = "bzqk";
    //��������HashMap�е����ط���Ȩ��ת�����������key
    public static final String TDFWQSZY = "tdfwqszy";
    //��������HashMap�еļ������ߴ�����key
    public static final String JMZC = "jmzc";
    //��������HashMap�еļ������ߴ�����key
    public static final String JMZC_GR = "jmzc_gr";
    //��������HashMap�еļ������ߴ�����key
    public static final String JMZC_FGR = "jmzc_fgr";
    //��������HashMap�еļ������ߴ�����key
    public static final String JMZCMAP = "jmzcmap";
    //��������HashMap�еĹ���������key
    public static final String GJ = "gj";
    //��������HashMap�еı��ִ�����key
    public static final String GJMAP = "gjmap";
    //��������HashMap�е����д�����key
    public static final String YH = "yh";
    //��������HashMap�е�˰�������֯����������key
    public static final String SWJGZZJG = "swjgzzjg";
    //��������HashMap�е����ؼ��δ�����key
    public static final String TDJC = "tdjc";
    //��������HashMap�е��������������key
    public static final String SZQY = "szqy";
    //��������HashMap�еķ�Ʊ���������key
    public static final String FPZL = "fpzl";
    //��������HashMap�еķ�Ʊ���������key
    public static final String KPLX = "kplx";
    //��������HashMap�еķ�Ʊ���������key
    public static final String FWCQZBZZFLX = "fwcqzbzzflx";

    //��������HashMap�е��������������key
    public static final String TDFWYTJM = "tdfwytjm"; //zzb add 090820 ����
    
    //��������HashMap�еķ������ʴ�����key
    public static final String FWXZ = "fwxz";//tangchangfu  add 20131013 ������
    
    //��������HashMap�еķ������ʴ�����key
    public static final String FWSZQY = "fwszqy";//tangchangfu  add 20131013 ������(������������)
  //��������HashMap�еķ���Ȩ��ת����ϸ������key
    public static final String QSZYXSMX="qszyxsmx";////added by zhangj
    
    //��������HashMap�еļ�����˰�������ʵ�key
    public static final String QS_JMXZ = "qs_jmxz";//pengyouhua  add 20140714 ��˰���õļ������ʴ���
 


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
     * 99--�����
     */
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

    /**
     * ������־
     * 0������
     * 1������
     */
    public static final String BZBS_JM = "0";
    public static final String BZBS_BZ = "1";

    /**
     * ��˰������ ����
     */
    public static final String NSRLX_GR = "99";

    /**
     * �û���־
     *
     */
    public static final String YHBZ_GR = "0";
    public static final String YHBZ_FGR = "1";

    /**
     * ��������
     */
    public static final int BCLX_HB = 1; //����
    public static final int BCLX_SW = 2; //ʵ��
    public static final int BCLX_HH = 3; //���

    /**
     * �������˰��ʶ��
     * 0--�����ϰ������� û�в�Ǩ�͹���ס��
     * 1--���ϰ���������δ¼�� �в�Ǩ����ס��  TODO�� 20070523��ע��ֻҪ¼�룬�����øñ�ʶ ��
     * 2--���ϰ�����������¼�� TODO��20070523��ע��δ���øô��룩
     * 3--��������Լ���˰(20070525����)
     * 99--����
     */
    public static final String ZB_BLJMSBS_BFHBLTJ = "0";
    public static final String ZB_BLJMSBS_FHBL_WLR = "1";
    public static final String ZB_BLJMSBS_FHBL_YLR = "2";
    public static final String ZB_BLJMSBS_CXXJM = "3";
    public static final String ZB_BLJMSBS_BZ = "99";

    /**
     * ��¼��ʾ��
     * 0--�ǲ�¼����ʽ¼��
     * 1--��¼
     */
    public static final String ZB_BLBS_FBL = "0"; //�ǲ�¼Ϊ0
    public static final String ZB_BLBS_BL = "1"; //��¼Ϊ1
    public static final String ZB_BLBS_SJQYBL = "2"; //����Ǩ�Ʋ�¼Ϊ2

    /**
     * �û���ʾ
     */
    public static final String ZB_YHBS_GR = "0"; //�û���ʶ-����
    public static final String ZB_YHBS_FGR = "1"; //�û���ʶ-�Ǹ���

    public static final String ZB_NSRLX_GR = "99"; //��˰������-����
    /**=========================�걨�����и����ʶ��˵��==����===============================*/

    /**=========================�����걨��״̬��ʶ˵��====================================*/
    /**
     * �����걨����
     * ״̬��ʾ��
     * 0--����
     * 1--��ӡ
     *
     * 21--���ͬ�⣨��ʱ����
     * 22--��˲�ͬ�⣨��ʱ����
     * 3--������ˣ���ʱ����
     *
     * 41--���ͬ�⣨����������
     * 42--��˲�ͬ�⣨����������
     * 5--������ˣ�����������
     *
     * 6--������ӡ
     */
    public static final String JMSBB_ZTBS_BC = "0"; //����
    public static final String JMSBB_ZTBS_DY = "1"; //��ӡ

    public static final String JMSBB_ZTBS_JSBL_SHTY = "21";
    public static final String JMSBB_ZTBS_JSBL_SHBTY = "22";
    public static final String JMSBB_ZTBS_JSBL_CX_SH = "3";

    public static final String JMSBB_ZTBS_ZCSP_SHTY = "41";
    public static final String JMSBB_ZTBS_ZCSP_SHBTY = "42";
    public static final String JMSBB_ZTBS_ZCSP_CX_SH = "5"; //������ˣ���ʱ����

    public static final String JMSBB_ZTBS_CXDY = "6";

    /**=========================�����걨��״̬��ʶ˵��====����==============================*/


    //���ˡ��Ǹ�����Ϣ��ķ��ݽ�����ʶ
    public static final String FFWJHBS = "0"; //�Ƿ��ݽ�����ʶΪ0
    public static final String FWJHBS = "1"; //���ݽ�����ʶΪ1

    //���ط���Ȩ��ת������
    public static final String TDFWQSZY_MM = "03"; //���ط���ת������Ϊ����
    public static final String TDFWQSZY_JH = "05"; //���ط���ת������Ϊ����

    //���֤������
    public static final String SFZJLX_HZ = "04"; //����

    //���ط������
    public static final String TDFWLB = "03"; //�������Ϊ��������סլ

    //������Ŀ����
    public static final String JMXMDM = "7499";

    /**=========================��˰֤��ʶ˵��====��ʼ==============================*/
    public static final String YSJCDM_DF = "21"; //Ԥ�㼶��
    public static final String YSJCDM_DF_MC = "�ط���"; //Ԥ�㼶������
    public static final String WSZ_DJZCLX = "410"; //�Ǽ�ע������--41������������ҵ
    public static final String WSZ_DJZCLX_MC = "���幤�̻�";
    //public static final String WSZ_GJBZHYDM = "8390"; //���ұ�׼��ҵ����--8390������� //2006-09-13���ұ�׼��ҵ�����޸�
    //2012��1�¿�ʼ���� Modified by Zhangyj 20120111
    public static final String WSZ_GJBZHYDM = "8190";
    public static final String WSZ_GJBZHYMC = "����δ�����ķ���"; //���ұ�׼��ҵ����--8390�������--8190�������

    public static final String WSZ_PZZLDM_OLD = "1015"; //��˰��˰֤��Ӧ��Ʊ֤�������----��˰��˰֤���������Ʊ��
    public static final String WSZ_PZZLDM = "1104"; ////Ʊ����� ��˰֤��˰ר�ýӿ� tujb-20131212
    public static final String WSZ_PZZLDM_SG = "1014"; //��˰��˰֤��Ӧ��Ʊ֤�������----��˰��˰֤���ֹ�Ʊ��

    public static final String WSZ_CLBJDM_WCL = "10"; //δ����
    public static final String WSZ_CLBJDM_YSB = "11"; //���걨
    public static final String WSZ_CLBJDM_YJK = "12"; //�ѽɿ�
    public static final String WSZ_CLBJDM_YXH = "13"; //������
    public static final String WSZ_CLBJDM_YWS = "14"; //����˰�����Ѵ�ӡ��
    public static final String WSZ_CLBJDM_YJZ = "15"; //�Ѽ���
    public static final String WSZ_CLBJDM_YZF = "16"; //������

    public static final String WSZ_FSDM = "1"; //�Ǽ��걨��ʽ���롪����˰����ط�ʽ
    public static final String WSZ_FSMC = "��˰����ط�ʽ"; //�Ǽ��걨��ʽ���ơ�����˰����ط�ʽ

    public static final String WSZ_QSSZDM = "74"; //��˰˰�ִ���
    public static final String WSZ_QSSZMC = "��˰"; //��˰˰������
    public static final String WSZ_JZBZ_DEFAULT = "000000"; //���ʱ�־
    public static final String WSZ_JZBZ_BL = "000001"; //�Ѽ���

    public static final String WSZ_HZFS_PL = "3"; //��˰֤���ܷ�ʽ�����������λ���
    public static final String WSZ_HZFS_SWS = "2"; //��˰֤���ܷ�ʽ����˰����
    public static final String WSZ_HZFS_GR = "1"; //��˰֤���ܷ�ʽ��������
    public static final String WSZ_HZFS_ZSD = "0"; //��˰֤���ܷ�ʽ�������յ�

    public static final String WSZ_JSFS_XJ = "01"; //��˰��ʽ���ֽ�
    public static final String WSZ_JSFS_SK = "02"; //��˰��ʽ��ˢ����
    public static final String WSZ_JSFS_ZP = "03"; //��˰��ʽ��֧Ʊ��
    public static final String WSZ_JSFS_ZXJN = "04"; //��˰��ʽ�����н��ɣ�

    public static final String WSZ_FPJE = "9999999.99"; //��˰֤��Ʊ��һ����˰֤���Ľ�
    //��¼�������ɵĽɿ���ʱ������з�Ʊ����������ɵ���˰֤���ܱ�ļ�¼��sbhzdh��ʱ�ø�ֵ
    public static final String WSZ_SBHZDH_DEFAULT = "FP000000";

    /**=========================��˰֤��ʶ˵��====����==============================*/

    /**=========================�ɿ����ʶ˵��====��ʼ==============================*/
    /** @todo ��Ҫȷ����˰�����ʹ��� ������Դ �����ʻ���ʾ*/
    public static final String JKS_SKLXDM_XHJK = "100";
    public static final String JKS_SKLXDM_XHJK_MC = "����";
    public static final String JKS_SKLXDM_ZCJK = "110";
    public static final String JKS_SKLXDM_ZCJK_MC = "һ������";
    public static final String JKS_SKLXDM_HZJK = "120";
    public static final String JKS_SKLXDM_HZJK_MC = "��ɢ";
    public static final String JKS_SJLY_HZ = "1"; //���ܷ�ʽ���� �ɿ������ɷ�ʽ����������˰֤���ɽɿ���
    public static final String JKS_SJLY_FHZ = "2"; //�ǻ��ܷ�ʽ �ɿ������ɷ�ʽ����ֱ��
    public static final String JKS_SJLY_XH = "51"; //���ŷ�ʽ
    public static final String JKS_JBZHBS = "";
    public static final String JKS_ZWBS_DEFAULT = "00000000000000000000"; //���ʱ�־'00000000000000000000'
    public static final String JKS_ZWBS_XJ = "00000000000000000010"; //���ʱ�־'00000000000000000010'
    public static final String JKS_ZWBS_ZP = "00000000000000000020"; //���ʱ�־'00000000000000000020'
    public static final String JKS_ZWBS_SK = "00000000000000000120"; //���ʱ�־'00000000000000000120'

    /**=========================�ɿ����ʶ˵��====����==============================*/

    /**=========================�����йصĳ���====��ʼ==============================*/
    public static final String JE_CJJG = "JE_CJJG"; //�ɽ��۸�
    public static final String JE_JSYJ = "JE_JSYJ"; //��˰����
    public static final String JE_JZQS = "JE_JZQS"; //������˰
    public static final String JE_SJYZ = "JE_SJYZ"; //ʵ��Ӧ��
    public static final String JE_JZSE = "JE_JZSE"; //����˰��
    public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE"; //�����ѹ�����ס���ı��εֿ۶�
    public static final String JE_FWJHJG = "JE_FWJHJG"; //���ݽ����۸�
    public static final String JE_CQJMJE = "JE_CQJMJE"; //��Ǩ������
    public static final String JE_PTZZJMJE = "JE_PTZZJMJE"; //��ͨסլ��˰���
    public static final String JE_JMSZE = "JE_JMSZE"; //����˰�ܽ��
    public static final String JE_YNSE = "JE_YNSE"; //Ӧ��˰��ڸ��˼��տ��У�������������ս�˰�
    public static final String JE_ZCSPJMJE = "JE_ZCSPJMJE"; //��Ǩ������

    /**=============================����ά��==============================================*/
    public static final String ZCWH_PTZZJMBZ = "0002"; //��ͨסլ����ĵ�λ����ı�׼
    public static final String ZCWH_SL = "0001"; //����ά���е�˰��
    public static final String ZCWH_JBZS = "0003"; //����ά���е���ͨסլ��������
    public static final String ZCWH_SCGMPTZFSL = "0004"; //�״ι���90�O������ͨס��˰��

    /**=============================��Ȩ������=========================================*/
    public static final String CQRLX_ZCQR = "0"; //����Ȩ��
    public static final String CQRLX_CCQR = "1"; //�β�Ȩ��


    /**===========================================================================*/
    /**
     * ��ȡϵͳ������ָ���ľ�̬HTTP�ļ���·����
     *
     * @return ϵͳ������ָ����static_contextpath��ֵ
     */
    public static String getStaticContextPath() {
        if (STATIC_CONTEXT_PATH == null) {
            STATIC_CONTEXT_PATH = PropertiesUtil.getProperty(
                    "static_contextpath");
        }
        return STATIC_CONTEXT_PATH;
    }

    /**
     * @todo
     * �������ߴ���
     * JMZC_GYZF  ����ס��
     * JMZC_CQFW  ��Ǩ����
     * JMZC_PTZZ  ��ͨסլ
     * JMZC_JJSYZF  ��������סլ
     */
    public static String JMZC_GYZF = "7452";
    public static String JMZC_CQFW = "7451";
    public static String JMZC_PTZZ = "7450";
    //add by zhangyj 20090223
    public static String JMZC_JJSYZF = "7453";

    /**
     * �������������״̬λ
     */
    public static String DRZB_ZT_XINZENG = "0"; //����
    public static String DRZB_ZT_CHECKED = "1"; //���
    public static String DRZB_ZT_CHECKED_NO = "1N"; //���
    public static String DRZB_ZT_RECIVE = "2"; //����
    public static String DRZB_ZT_PRINT = "6"; //��ӡ
    public static String SBZB_ZT_SHOULI = "3"; //����δʹ�ã�
    public static String SBZB_ZT_SHENHE = "4"; //��ˣ�δʹ�ã�
    public static String SBZB_ZT_FUHE = "5"; //���ˣ�δʹ�ã�
    public static String PC = "99";

    public static String PT_JKS = "1"; //������ͨ�ɿ���
    public static String DZ_JKS = "2"; //������ʵĽɿ���

    /**
     * ˰�����
     */
    public static final String SETZ_ZC = "0"; //����
    public static final String SETZ_JBZS = "1"; //��������
    public static final String SETZ_QEZS = "2"; //ȫ������


    public static final String SETZ_SCGMPTZF = "5"; //�״ι���90ƽ��������ͨס��
    //����˰�������(�������÷�) modify by fujx��20081217
    public static final String SETZ_JJSYF = "6"; //�������÷�
    /**
     * �ݻ��ʺ����ؼ���
     */
    public static final String TUFWXX_RJL_LOW = "00"; //1.0����
    public static final String TUFWXX_RJL_HIGH = "01"; //1.0���Ϻ�1.0
    public static final String TUFWXX_TDJC_ONE = "01";
    public static final String TUFWXX_TDJC_TWO = "02";
    public static final String TUFWXX_TDJC_THREE = "03";
    public static final String TUFWXX_TDJC_FOUR = "04";
    public static final String TUFWXX_TDJC_FIVE = "05";
    public static final String TUFWXX_TDJC_SIX = "06";
    public static final String TUFWXX_TDJC_SEVEN = "07";
    public static final String TUFWXX_TDJC_EIGHT = "08";
    public static final String TUFWXX_TDJC_NINE = "09";
    public static final String TUFWXX_TDJC_TEN = "10";
    public static final String TUFWXX_TDJC_SIX_TEN = "11";

    /**
     * �Ƿ�Ϊ���ַ�
     */
    public static final String TUFWXX_SFESF_TRUE = "01";
    public static final String TUFWXX_SFESF_FALSE = "00";

    /**
     * ʣ��������־
     */
    public static final String JSBL_SYEYWBZ_YONGWAN = "01"; //����
    public static final String JSBL_SYEYWBZ_WEIYONGWAN = "00"; //δ����


    //����
    public static String GJ_CHN = "CHN"; //����

    /**
     * Ǩ��״̬λ
     */
    public static String QY_ZT_XINZENG = "0"; //����
    public static String QY_ZT_SUCCES_1 = "1"; //��һ���������걨��Ϣ�ɹ�
    public static String QY_ZT_SUCCES_2 = "2"; //�ڶ�������˰֤��ɿ��鵼��ɹ�
    public static String QY_ZT_SUCCES_3 = "3"; //��������������˰֤����ɹ�

    public static String QY_ZT_FAILURE = "99"; //��һ��ʧ��
    public static String QY_ZT_FAILURE_2 = "98"; //�ڶ���ʧ��
    public static String QY_ZT_FAILURE_3 = "97"; //������ʧ��

    /**
     * Ǩ�ƴ����ʾ
     */
    public static String QY_ERROR_0 = "00"; //�޴�
    public static String QY_ERROR_1 = "01"; //��˰�����Ƴ���
    public static String QY_ERROR_2 = "02"; //���ݹ��࣬db
    public static String QY_ERROR_3 = "03"; //���ݲ����ڣ�db
    public static String QY_ERROR_4 = "04"; //�ɿ����ʣ�db
    public static String QY_ERROR_5 = "05"; //Υ����Ψһ��Լ����db
    public static String QY_ERROR_6 = "06"; //�����ֵ�����й���,��ϵ�绰�������֤�ţ�db
    public static String QY_ERROR_7 = "07"; //ֵΪ�գ����ܱ���,�˶�֪ͨ��ŵȣ�db
    public static String QY_ERROR_8 = "08"; //֤��Ϊ����
    public static String QY_ERROR_9 = "09"; //�����ʺŲ�����
    public static String QY_ERROR_10 = "10"; //����ǰ������
    //public static String QY_ERROR_11 = "11";   //�ɿ�����

    public static String QY_ERROR_98 = "98"; //��������db
    public static String QY_ERROR_99 = "99"; //��������

    public static String QY_ERRORMSG_0 = "Ǩ����ȷ"; //�޴�
    public static String QY_ERRORMSG_1 = "��˰�˵Ǽ���Ϣ����"; //��˰�����Ƴ���
    public static String QY_ERRORMSG_2 = "�����ظ�"; //���ݹ��࣬db
    public static String QY_ERRORMSG_3 = "���ݲ�����"; //���ݲ����ڣ�db
    public static String QY_ERRORMSG_4 = "�ɿ�����"; //�ɿ����ʣ�db
    public static String QY_ERRORMSG_5 = "Υ����Ψһ��Լ��"; //Υ����Ψһ��Լ����db
    public static String QY_ERRORMSG_6 = "���ݹ���"; //�����ֵ�����й���,��ϵ�绰�������֤�ţ�db
    public static String QY_ERRORMSG_7 = "�ֶ�Ϊ��"; //�˶�֪ͨ��ţ��ɿ����������룬��˰֤�ֱ�Ϊ�գ�db
    public static String QY_ERRORMSG_8 = "֤��Ϊ����"; //֤��Ϊ����
    public static String QY_ERRORMSG_9 = "�����ʺŲ�����"; //�����ʺŲ�����
    public static String QY_ERRORMSG_10 = "�˶�֪ͨ���ظ������ݽ�������Ϣ����˰֤�ź�ר�ýɿ���Ŷ�����"; //����ǰ������

    // public static String QY_ERRORMSG_11 = "�ɿ��鲻����";   //�ɿ�����

    public static String QY_ERRORMSG_98 = "���ݿⷢ������"; //��������db
    public static String QY_ERRORMSG_99 = "��������"; //��������

    /**
     * ��Ǩ��Ϣ������Դ
     */
    public static String CQXXB_SJLY_LR = "0"; //¼��
    public static String CQXXB_SJLY_DR = "1"; //����

    public final static String MODHDTZSFWHM_PROCESSOR =
            "com.creationstar.bjtax.qsgl.BizLogic.processor.SbxxProcessor";

    public final static String CQXXB_PROCESSOR =
            "com.creationstar.bjtax.qsgl.BizLogic.processor.CqxxbProcessor";

    /** �޸ĵ�������ѯ�����������ֶΣ�������Ϣ��: XGDRCWXX_QUERYSIZE_DR */
    public static final int XGDRCWXX_QUERYSIZE_DR = 20;

    /**
     * �����Ǩ��Ϣ���ݴ�������
     */
    public static String CQXXCWB_CWKX_01 = "01"; // ��ʽ���ݼ�������ʽ���ݽ������������

    public static String CQXXCWB_CWKX_02 = "02"; // ���֤����ͬ��������һ��

    /**
     * �����Ǩ��Ϣ���ݴ�������˵��
     */
    public static String CQXXCWB_CWKX_01_CH = "��ʽ���ݼ�������ʽ���ݽ������������"; // ��ʽ���ݼ�������ʽ���ݽ������������

    public static String CQXXCWB_CWKX_02_CH = "���֤����ͬ��������һ��"; // ���֤����ͬ��������һ��

    /**
     * �����Ǩ��Ϣ���ݴ���������˵��
     */
    public static String CQXXCWB_CWLX_01 = "01"; // ����

    public static String CQXXCWB_CWLX_02 = "02"; // ����


    //�����Լ�����Ŀ����
    public static final String CXXJM_JMXMDM_QT = "7499";

    //��ά������Ĭ�Ͽ�����
    public static final String QRCODE_DEFAULT_NULL = "00000000000000000000";
    
    //�ͻ������ɵ�XML�汾
    //������ͨסլ��׼���������ͻ����������ʹ���µĿͻ��˰汾��
    //modified by gaoyh to 20141020
    //public static final String XML_VERSION = "3.0.0";
    public static final String XML_VERSION = "4.0.0";
    
    //��Ʊ��������
    public static final String FP_CZZL_LR = "0"; //¼��czzldm
    public static final String FP_CZZL_ZTBC = "1"; //¼��ztbs
    
    public static final String FP_NWYH = "1"; //�����û���ʶ
    public static final String FP_WWYH = "0"; //�����û���ʶ
    
    public static final String FP_RKBZ_RK = "1"; //����ʶ����⣩
    public static final String FP_RKBZ_FRK = "0"; //����ʶ������⣩
    
    public static final String FP_SL_ZERO = "0"; //��Ʊ���Ϊ�㣨0��
    
    public static final String FP_TPBZ_WTP = "0"; //��Ʊ��Ʊ��ʶ��0��:δ������Ʊ
    public static final String FP_TPBZ_YTP = "1"; //��Ʊ��Ʊ��ʶ��1��:�Ѱ�����Ʊ
    
    public static final String FP_DCBZ_WTP = "0"; //��Ʊ������ʶ��0��:δ����
    public static final String FP_DCBZ_YTP = "1"; //��Ʊ������ʶ��1��:�ѵ���
    
    public static final String FP_KPLX_KP = "1"; //��Ʊ��Ʊ���ͣ�1��:��Ʊ
    public static final String FP_KPLX_TP = "2"; //��Ʊ��Ʊ���ͣ�2��:��Ʊ
    public static final String FP_KPLX_FP = "3"; //��Ʊ��Ʊ���ͣ�3��:��Ʊ
    
    public static final String FP_MMFBZ_SF = "1"; //��������ʶ��1��������
    public static final String FP_MMFBZ_BF = "0"; //��������ʶ��0������
    
    public static final String FP_PZFLDM_WSZ = "01"; //��ƾ֤������루01������˰֤
    public static final String FP_PZFLDM_JKS = "02"; //��ƾ֤������루02�����ɿ���
    public static final String FP_PZFLDM_FP = "11"; //��ƾ֤������루11������Ʊ
    public static final double FP_MAX_JE = 99999999.00; //��Ʊ��ӡ�����
    
    public static final String ONLY_ROOM_YES = "0"; //�Ƿ�Ϊ��ͥΨһ�����÷�����
    public static final String ONLY_ROOM_NOT = "1"; //�Ƿ�Ϊ��ͥΨһ�����÷�����
    public static final String FWLX_BUILDINGS = "0"; //�������ͣ�¥��
    public static final String FWLX_BUNGALOW = "1"; //�������ͣ�ƽ��
    public static final String FWLX_BASEMENT = "2"; //�������ͣ�������
    public static final String TDZSS_SB_GFFP = "0"; //������ֵ˰�걨��ʽ���ṩ������Ʊ
    public static final String TDZSS_SB_GFQSP= "3"; //������ֵ˰�걨��ʽ���ṩ������˰Ʊ
    public static final String TDZSS_SB_GFHT = "4"; //������ֵ˰�걨��ʽ���ṩԭ������ͬ
    public static final String TDZSS_SB_PGBG = "1"; //������ֵ˰�걨��ʽ���ṩ��������
    public static final String TDZSS_SB_WPJ = "5"; //������ֵ˰�걨��ʽ�����κ�Ʊ��
    public static final String TDZSS_SB_FLWS = "6"; //������ֵ˰�걨��ʽ���ṩ��������
    public static final String TDZSS_SB_HDZS = "2"; //������ֵ˰�걨��ʽ���˶�����
    public static final String CQZBZ_JLMX_LOW = "0"; //��Ȩ֤��ע���������140ƽ�ף���������
    public static final String CQZBZ_JLMX_HIGH = "1"; //��Ȩ֤��ע���������140ƽ������
    public static final String FWRJL_LOW = "0"; //�����ݻ��ʣ�1.0����
    public static final String FWRJL_HIGH = "1"; //�����ݻ��ʣ�1.0����������
    public static final String HFBZ_PT = "0"; //���ֱ�׼����ͨס��
    public static final String HFBZ_NOTPT = "1"; //���ֱ�׼������ͨס��
    public static final String HFBZ_FZF = "2"; //���ֱ�׼����ס��  by zhangj
    public static final String HFBZ_GRWCZY = "3"; //���ֱ�׼�������޳�����
    public static final String HFBZ_GRJC = "4"; //���ֱ�׼�����˼̳�
    public static final String ZFSYSJ_FIVE = "0"; //ס��ʹ��ʱ�䣺5�꣨��������
    public static final String ZFSYSJ_THREETOFOIVE = "1"; //ס��ʹ��ʱ�䣺5������3������
    public static final String ZFSYSJ_THREE = "2"; //ס��ʹ��ʱ�䣺3�꣨��������
    public static final String YSSZSFS_NOT = "0"; //Ӫҵ˰���շ�ʽ������Ӫҵ˰
    public static final String YSSZSFS_ALL = "1"; //Ӫҵ˰���շ�ʽ��ȫ������Ӫҵ˰
    public static final String YSSZSFS_MINUS = "2"; //Ӫҵ˰���շ�ʽ���������Ӫҵ˰
    public static final String GRSDSZSFS_FREE = "0"; //��������˰���շ�ʽ:������������˰
    public static final String GRSDSZSFS_ZS = "1"; //��������˰���շ�ʽ:���ո�������˰
    public static final String TDZZSZSFS_NOT = "0"; //������ֵ˰���շ�ʽ:������������ֵ˰
    public static final String TDZZSZSFS_FREE = "1"; //������ֵ˰���շ�ʽ:����������ֵ˰
    public static final String TDZZSZSFS_ZS = "2"; //������ֵ˰���շ�ʽ:ȫ������������ֵ˰
    public static final String TDZZSZSFS_QT = "3"; //������ֵ˰���շ�ʽ:����
    public static final String TDZZSZSFS_GFFPZS = "4"; //������ֵ˰���շ�ʽ:�ṩ������Ʊ����������ֵ˰
    public static final String TDZZSZSFS_HDZS = "5"; //������ֵ˰���շ�ʽ:�˶�����������ֵ˰
    public static final String TDZZSZSFS_PGBGZS = "6"; //������ֵ˰���շ�ʽ:�ṩ������������������ֵ˰
    public static final String JSSRQRFS_HTJG = "0"; //��˰����ȷ�Ϸ�ʽ:��ͬ�۸�
    public static final String JSSRQRFS_HDJSJG = "1"; //��˰����ȷ�Ϸ�ʽ:�˶���˰�۸�
    
    public static final String CJS_SZSMDM = "100010"; //�ǽ�˰˰��˰Ŀ
    public static final String TDZZS_SZSMDM = "080040"; //������ֵ˰��˰Ŀ
    //����ס��
    public static final String FWCQZBZZFLX_06 = "06";//	�״����н��׵��ѹ�����
    public static final String FWCQZBZZFLX_07 = "07";	//Σ�Ļ�Ǩ��
    public static final String FWCQZBZZFLX_08 = "08";	//�����缯�ʽ���ס��
    public static final String FWCQZBZZFLX_09 = "09";	//���ӷ�
    public static final String FWCQZBZZFLX_10 ="10";	//���ӷ�
    public static final String FWCQZBZZFLX_11 ="11";	//�̻��������ũ����ס��
    public static final String FWCQZBZZFLX_13 ="13";	//��ʷ�Ļ������е�����˽��,added by zhagnj,2014.10.17

    public static final String PZZLCD = "10"; //Ʊ֤���೤������
    public static final String FWHDLX_HOUSING="0";//���ݺ˶����� 0��ס����1����ס��
    public static final String FWHDLX_NONHOUSING="1";////���ݺ˶����� 0��ס����1����ס��
    public static final String YHSZSFS_ZS="0";//ӡ��˰���շ�ʽ������ӡ��˰
    public static final String YHSZSFS_FREE="1";//ӡ��˰���շ�ʽ������ӡ��˰
    public static final String FPCXLINK="QSGL_FPCX_LINK";//��Ʊ��ѯ�����ӵ�ַ
    
    public static final String JSJM_JMXZDM_CQJM = "15129902"; //��Ǩ����
    public static final String JSJM_JMXZDM_SLJM = "15011799"; //˰�ʼ���
    
}
