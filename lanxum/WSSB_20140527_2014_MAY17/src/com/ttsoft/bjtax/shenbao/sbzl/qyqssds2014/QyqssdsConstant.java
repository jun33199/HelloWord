package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014;

public class QyqssdsConstant {

	/**
	 * ��ѯ
	 */
	public static final int INT_ACTION_QUERY = 1;

	/**
	 * ����
	 */
	public static final int INT_ACTION_SAVE = 2;

	/**
	 * ɾ��
	 */
	public static final int INT_ACTION_DELETE = 3;
	/**
	 * ���������
	 */
	public static String STRING_KEY_JSJDM = "JSJDM";

	/**
	 * ����
	 */
	public static String STRING_KEY_DATE = "DATE";

	/**
	 * �Ǽ����ݶ���
	 */
	public static String OBJECT_KEY_DJSJ = "DJSJ";
	
	public static String REPORT_VERSION_QYQSSDS_NSRJBXXB="20140101";
	/**
	 *  ����ϵͳ�������
	 */
	public static String QSSDS_1="1";
	public static String QSSDS_2="2";
	public static String QSSDS_3="3";
	/**
	 * ��ҵ��������˰����
	 */
	public static String CA_YWLXDM_QYQSSDS = "010036";
	public static String CA_SCHEMADM_QYQSSDS = "20140101";
	public static String CA_XSLTDM_QYQSSDS = "20140101";
	public static String REPORT_VERSION_QYQSSDS = "20140101";
	
	public static String QYQSSDS_PROCESSOR="com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.processor.QyqssdsBaProcessor";
	/**
     * ������REQUEST�еķ��ص�ַ
     */
    public static final String REQ_KEY_RETURN_QYQSSDSBA_SAVE =
        "/shenbao//qyqssdsba2014.dc?actionType=Show";
}
