package com.lscdz.qysds.common.service.qysds;

public class Constants {


    //modified by Guoxh, 2007.01.29
//	public static boolean DEBUG_PROGRAN_FLAG = true;
	public static boolean DEBUG_PROGRAN_FLAG = false;

    /**
     * ϵͳ��ʽ-����
     */
    public static String CREPORTS_SYSTEM_FS_WANGSHANG = "5";
    /**
     * ϵͳ��ʽ-����
     */
    public static String CREPORTS_SYSTEM_FS_SHANGMENG = "1";

    /**
     * Ӧ��ID�� ȫ�������б�
     */
    public static String[] CREPORTS_APPID_QYSDS_GROUP = {"001"};

    /**
     * Ӧ��ID����ҵ����˰
     */
    public static String CREPORTS_APPID_QYSDS = "001";
    /**
     * Ӧ��ID����ҵ����˰����
     */
    public static String CREPORTS_APPID_QYQSSDS = "002";

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ��� ȫ�������б�
     */
    public static String[] CREPORTS_OBJ_ACTIVITY_GROUP = {"0", "1", "2", "3",
            "4"};

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ�������δ����
     */
    public static String CREPORTS_OBJ_ACTIVITY_CREATE_NOINUSE = "0";

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ�������
     */
    public static String CREPORTS_OBJ_ACTIVITY_INUSE = "1";

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ�����ͣ
     */
    public static String CREPORTS_OBJ_ACTIVITY_PAUSE = "2";

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ���ͣ��
     */
    public static String CREPORTS_OBJ_ACTIVITY_STOP = "3";

    /**
     * ��Ŀ����ԣ���������Ӧ�á�����Ŀ�����ԣ�������
     */
    public static String CREPORTS_OBJ_ACTIVITY_DROP = "4";

    /**
     * �������ͣ������ձ����ԣ��� ȫ�������б�
     */
    public static String[] CREPORTS_TABLE_REPORTTYPE_GROUP = {"0", "1", "2"};

    /**
     * �������ͣ������ձ����ԣ�,0-һ��һά��
     */
    public static String CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY =
            "0";

    /**
     * �������ͣ������ձ����ԣ�,1-һά�䳤��
     */
    public static String CREPORTS_TABLE_REPORTTYPE_ONE_DIMENSIONALITY_ALTER =
            "1";

    /**
     * �������ͣ������ձ����ԣ�,2-��ά�䳤��
     */
    public static String CREPORTS_TABLE_REPORTTYPE_TWO_DIMENSIONALITY_ALTER =
            "2";

    /**
     * ���������ͣ�������¼�������ԣ��� ȫ�������б�
     */
    public static String[] CREPORTS_ITEM_INPUT_TYPE_GROUP = {"0", "1", "2", "3",
            "4", "5", "6", "7"};

    /**
     * ���������ͣ�������¼�������ԣ��� �������ӱ��ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_NUMBER_NOALTER = "0";

    /**
     * ���������ͣ�������¼�������ԣ��� �������ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_NUMBER_ALTER = "1";

    /**
     * ���������ͣ�������¼�������ԣ��� �ַ������ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_STRING_NOALTER = "2";

    /**
     * ���������ͣ�������¼�������ԣ��� �ַ������ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_STRING_ALTER = "3";

    /**
     * ���������ͣ�������¼�������ԣ��� �����ӱ��ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_NUMBER_NOALTER = "4";

    /**
     * ���������ͣ�������¼�������ԣ��� �����ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_NUMBER_ALTER = "5";

    /**
     * ���������ͣ�������¼�������ԣ��� �ַ����ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_STRING_NOALTER = "6";

    /**
     * ���������ͣ�������¼�������ԣ��� �ַ����ӱ�ɱ䳤
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_STRING_ALTER = "7";

    /**
     * ���������ͣ���������ʽ�����ԣ��� ȫ�������б�
     */
    public static String[] CREPORTS_ITEM_STYLE_TYPE_GROUP = {"0", "1"};

    /**
     * ���������ͣ���������ʽ�����ԣ��� ��ʽ��
     */
    public static String CREPORTS_ITEM_STYLE_TYPE_STYLE_ITEM = "0";

    /**
     * ���������ͣ���������ʽ�����ԣ��� ¼����
     */
    public static String CREPORTS_ITEM_STYLE_TYPE_INPUT_ITEM = "1";

    /**
     * ��ʽ���ͣ���������ʽ�����ԣ��� ȫ�������б�
     */
    public static String[] CREPORTS_FORMULA_TYPE_GROUP = {"0", "1", "2", "3"};

    /**
     * ��ʽ���ͣ���������ʽ�����ԣ��� ������֤��ʽ
     */
    public static String CREPORTS_FORMULA_TYPE_IN_CHECK = "0";

    /**
     * ��ʽ���ͣ���������ʽ�����ԣ��� �����֤��ʽ
     */
    public static String CREPORTS_FORMULA_TYPE_BETWEEN_CHECK = "1";

    /**
     * ��ʽ���ͣ���������ʽ�����ԣ��� ������˹�ʽ
     */
    public static String CREPORTS_FORMULA_TYPE_IN_FORBIDDEN = "2";

    /**
     * ��ʽ���ͣ���������ʽ�����ԣ��� �����˹�ʽ
     */
    public static String CREPORTS_FORMULA_TYPE_BETWEEN_FORBIDDEN = "3";

    /**
     * ��ֵ��ʶ������
     */
    public static String CREPORTS_ITEM_FIELD_FLAG_SINGLELINE = "0";

    /**
     * ��ֵ��ʶ������
     */
    public static String CREPORTS_ITEM_FIELD_FLAG_MULITLINES = "1";


    /**
     * ���������ͣ����� ȫ�������б�
     */
    public static String[] CREPORTS_BBQLX_TYPE_GROUP = {"0", "1", "2"};

    /**
     * ���������ͣ����� �±�
     */
    public static String CREPORTS_IBBQLX_TYPE_MONTH = "0";

    /**
     * ���������ͣ����� ����
     */
    public static String CREPORTS_IBBQLX_TYPE_QUARTOR = "1";

    /**
     * ���������ͣ����� �걨
     */
    public static String CREPORTS_IBBQLX_TYPE_YEAR = "2";

    /**
     * ��ҵ����˰����걨�����״̬-����ɹ�
     */
    public static final String QYSDS_SHZT_SAVE ="0";
    /**
     * ��ҵ����˰����걨�����״̬-�������ͨ��
     */
    public static final String QYSDS_SHZT_SINGLE_PASS ="1";
    /**
     * ��ҵ����˰����걨�����״̬-ȫ�����ͨ��
     */
    public static final String QYSDS_SHZT_ALL_PASS ="2";

    /**
     * �걨��¼����Ҫ���Ƶ������ַ���Java��ʹ�ã�
     */
    public static final String QYSDS_CONTROL_CHAR_FOR_JAVA = "\"=%=_=*='=&=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * �걨��¼����Ҫ���Ƶ������ַ���Js��ʹ�ã�
     */
    public static final String QYSDS_CONTROL_CHAR_FOR_JS = "\\\"=%=_=*=\\'=&=\\\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";

    
    
    /**
     * ��ҵ����˰����걨�����״̬-����ɹ�
     */
    public static final String QYQSSDS_SHZT_SAVE ="0";
    /**
     * ��ҵ����˰����걨�����״̬-�������ͨ��
     */
    public static final String QYQSSDS_SHZT_SINGLE_PASS ="1";
    /**
     * ��ҵ����˰����걨�����״̬-ȫ�����ͨ��
     */
    public static final String QYQSSDS_SHZT_ALL_PASS ="2";
    /**
     * �걨��¼����Ҫ���Ƶ������ַ���Java��ʹ�ã�
     */
    public static final String QYQSSDS_CONTROL_CHAR_FOR_JAVA = "\"=%=_=*='=&=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * �걨��¼����Ҫ���Ƶ������ַ���Js��ʹ�ã�
     */
    public static final String QYQSSDS_CONTROL_CHAR_FOR_JS = "\\\"=%=_=*=\\'=&=\\\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * ���������ͣ�����ҵ���㶨λ3 ����ҵ����˰����
     */
    public static String CREPORTS_IBBQLX_TYPE_QYQSSDS = "3";
    /**
     * ����ʽ--�걨��01��
     */
    public static String QYSDS_NB_BBFS="01";
    
}
