package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- 08��ҵ����˰������</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: </p>
 *
 * @author wangcy
 * @version 1.0
 */
public class QysdsNbConstant2014
{
	//��ʱ��֪������ʹ��
	public static String JUMP_FLAG_NAME="QYSDS_2014_JUMP_FLAG_NAME";
	public static String REQ_KEY_QYSBSNB_2014="REQ_KEY_QYSBSNB_2014";
	/**
	 * 2014������������˰�걨ҵ�����
	 */
	public static String CA_YWLXDM_CZZSSDSNB_2014="010033";
	public static String CA_SCHEMADM_CZZSSDSNB_2014="20141203";
	public static String CA_XSLTDM_CZZSSDSNB_2014="20141203";
	
	
    /**
     * 2014��ҵ����˰������˰��֧�����������ҵ�����
    */
    public static String CA_YWLXDM_ZFJGSDSNB_2014 = "010034";
    public static String CA_SCHEMADM_ZFJGSDSNB_2014 = "20141203";
    public static String CA_XSLTDM_ZFJGSDSNB_2014 = "20141203";
	
    public static String ZFJGSDSNB_2014_MAX_ROW = "rows";
    public static int ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER = 15;

	/**
    * ��ҵ����˰���Ԥ����˰�걨��id
    */
   public static String TABLE_ID_CZZSSDSNB_2014 = "31";
   public static String TABLE_NAME_CZZSSDSNB_2014 = "��ҵ����˰���Ԥ����˰�걨��";

   /**
    * ��ҵ����˰������˰��֧���������id
    */
   public static String TABLE_ID_ZFJGSDSNB_2014 = "32";
   public static String TABLE_NAME_ZFJGSDSNB_2014 = "��ҵ����˰������˰��֧���������";

    /**
	 * 2014��ҵ����˰�걨�汾��
	 */
	public static String QYSDSNB_VERSION_2014 = "20140101";
	
	
	/**
	 * �������
	 */
	public static String STRING_KEY_NDLX="NDLX";
	
	/**
	 * �������ֵ���ں���ֵΪ��
	 */
	public static String STRING_KEY_NDLX_VALUE="1";
    /**
     * ������˰������س�������ʾ��Ϣ
     */
    //������˰
    public final static String HZNSFF_QYSDSNB2014_CZZSSDS_HZNS = "1";
    //������˰
    public final static String HZNSFF_QYSDSNB2014_CZZSSDS_DLNS = "2";
    //������˰��ʽ-�ܻ���
    public final static String HZNSFS_QYSDSNB2014_CZZSSDS_ZJG = "1";
    //������˰��ʽ-��֧����
    public final static String HZNSFS_QYSDSNB2014_CZZSSDS_FZJG = "2";
    
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
	 * 2014����������ҵ����˰��������������
	 */
	public static final String CZZSQYSDSNB_PROCESSOR_2014 = "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor.CzzsfzjgNbProcessor";
	/**
	 * 2014��ҵ����˰������˰��֧���������������������
	 */
	public static final String ZFJGQYSDSNB_2014_PROCESSOR = "com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor.ZfjgfzjgNbProcessor";
}
