package com.lscdz.qysds.common.codetable;

import java.util.ArrayList;
import java.util.List;
import com.lscdz.util.codetable.CodeTableConfig;


public class CodeTableKey implements CodeTableConfig {
	// ��ֵ����
	@SuppressWarnings("rawtypes")
	private List keyList;
	// ����ID����
	@SuppressWarnings("rawtypes")
	private List idList;
	
	/**
	 * ˰�������֯����
	 */
	public final static String GY_DM_SWJGZZJG = "com.lscdz.qysds.common.codetable.vo.gy_dm_swjgzzjg";

	/**
	 * ˰Դ�������ʹ����
	 */
	public final static String DJ_DM_QYSDSZGFWJDLX = "com.lscdz.qysds.common.codetable.vo.dj_dm_qysdszgfwjdlx";
	/**
	 * ��˰��״̬
	 */
	public final static String DJ_DM_NSRZT="com.lscdz.qysds.common.codetable.vo.dj_dm_nsrzt";
	
	/**
	 * ����˰�����������
	 */
	public final static String SF_DM_JMBASXDM_2014="com.lscdz.qysds.common.codetable.vo.sf_dm_jmbasxdm";
	
	/**
	 * ��Դ�ۺ�������������
	 */
	public final static String SF_DM_ZYZHLYZL="com.lscdz.qysds.common.codetable.vo.sf_dm_zyzhlyzl";
	
	/**
	 * ֤������
	 */
	public final static String GY_DM_ZJLX = "com.lscdz.qysds.common.codetable.vo.gy_dm_zjlx";
	/**
	 * ��������
	 */
	public final static String GY_DM_GJDQ = "com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq";
	
	/**
	 * ���ұ�׼��ҵ�����
	 */
	public final static String GY_DM_GJBZHY = "com.lscdz.qysds.common.codetable.vo.gy_dm_gjbzhy";
	/**
	 * ��������ƶ�
	 */
	public final static String SB_DM_QTKJZZ="com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_qtkjzz";
	/**
	 * ˰��˰Ŀ����
	 */ 
	public final static int RELATION_CONFIG_ZSXM = 1;
	 /**
     * �Ǽ�ע������
     */
    public final static String DJ_DM_DJZCLX = "com.lscdz.qysds.common.codetable.vo.dj_dm_djzclx";
	/**
	 * ���׼������ƶ�
	 */
    public final static String SB_DM_QYSDS_KJZZ = "com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_kjzz";
    /**
     * ���¼�����ϸ
     */
    public final static String SB_DM_QYSDS_GXJSLYMX= "com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_gxjslymx";
    /**
	 * ������
	 */
	public CodeTableKey() {
		// ʵ��������ʱ����ֵ���ϳ�ʼ��Ϊ��
		keyList = null;
	}
	/**
	 * ʵ�ֽӿڷ���
	 */
	public String getPropertiesFileName() {
		//		return "bjtax_dj.properties";
		return "";
	}

	/**
	 * ��ȡ��ֵ����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllKeyList() {
		if (keyList == null) {
			keyList = new ArrayList();
			//˰�������֯����
			keyList.add(GY_DM_SWJGZZJG);
			//˰Դ�������ʹ����
			keyList.add(DJ_DM_QYSDSZGFWJDLX);
			//��˰��״̬
			keyList.add(DJ_DM_NSRZT);
			//����˰������������
			keyList.add(SF_DM_JMBASXDM_2014);
			//��Դ�ۺ�������������
			keyList.add(SF_DM_ZYZHLYZL);
			//֤������
			keyList.add(GY_DM_ZJLX);
			//��������
			keyList.add(GY_DM_GJDQ);
			//���ұ�׼��ҵ�����
			keyList.add(GY_DM_GJBZHY);
			//��������ƶ�
			keyList.add(SB_DM_QTKJZZ);
			//�Ǽ�ע������
			keyList.add(DJ_DM_DJZCLX);
			//����ƶ�
			keyList.add(SB_DM_QYSDS_KJZZ);
			//���¼���
			keyList.add(SB_DM_QYSDS_GXJSLYMX);
		}
		return keyList;
	}
	
	/**
	 * ��ȡ����ID����
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRelationIDList() {
		if (idList == null) {
			idList = new ArrayList();
			//����˰�հ���״̬�����
			idList.add(String.valueOf(RELATION_CONFIG_ZSXM));
		}
		return idList;
	}
}
