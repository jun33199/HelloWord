package com.lscdz.qysds.common.codetable;

import java.util.ArrayList;
import java.util.List;
import com.lscdz.util.codetable.CodeTableConfig;


public class CodeTableKey implements CodeTableConfig {
	// 键值集合
	@SuppressWarnings("rawtypes")
	private List keyList;
	// 级联ID集合
	@SuppressWarnings("rawtypes")
	private List idList;
	
	/**
	 * 税务机关组织机构
	 */
	public final static String GY_DM_SWJGZZJG = "com.lscdz.qysds.common.codetable.vo.gy_dm_swjgzzjg";

	/**
	 * 税源鉴定类型代码表
	 */
	public final static String DJ_DM_QYSDSZGFWJDLX = "com.lscdz.qysds.common.codetable.vo.dj_dm_qysdszgfwjdlx";
	/**
	 * 纳税人状态
	 */
	public final static String DJ_DM_NSRZT="com.lscdz.qysds.common.codetable.vo.dj_dm_nsrzt";
	
	/**
	 * 减免税备案事项代码
	 */
	public final static String SF_DM_JMBASXDM_2014="com.lscdz.qysds.common.codetable.vo.sf_dm_jmbasxdm";
	
	/**
	 * 资源综合利用种类代码表
	 */
	public final static String SF_DM_ZYZHLYZL="com.lscdz.qysds.common.codetable.vo.sf_dm_zyzhlyzl";
	
	/**
	 * 证件类型
	 */
	public final static String GY_DM_ZJLX = "com.lscdz.qysds.common.codetable.vo.gy_dm_zjlx";
	/**
	 * 国籍地区
	 */
	public final static String GY_DM_GJDQ = "com.lscdz.qysds.common.codetable.vo.gy_dm_gjdq";
	
	/**
	 * 国家标准行业代码表
	 */
	public final static String GY_DM_GJBZHY = "com.lscdz.qysds.common.codetable.vo.gy_dm_gjbzhy";
	/**
	 * 其他会计制度
	 */
	public final static String SB_DM_QTKJZZ="com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_qtkjzz";
	/**
	 * 税种税目联动
	 */ 
	public final static int RELATION_CONFIG_ZSXM = 1;
	 /**
     * 登记注册类型
     */
    public final static String DJ_DM_DJZCLX = "com.lscdz.qysds.common.codetable.vo.dj_dm_djzclx";
	/**
	 * 会计准则或会计制度
	 */
    public final static String SB_DM_QYSDS_KJZZ = "com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_kjzz";
    /**
     * 高新技术明细
     */
    public final static String SB_DM_QYSDS_GXJSLYMX= "com.lscdz.qysds.common.codetable.vo.sb_dm_qysds_gxjslymx";
    /**
	 * 构造器
	 */
	public CodeTableKey() {
		// 实例化对象时将键值集合初始化为空
		keyList = null;
	}
	/**
	 * 实现接口方法
	 */
	public String getPropertiesFileName() {
		//		return "bjtax_dj.properties";
		return "";
	}

	/**
	 * 获取键值集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllKeyList() {
		if (keyList == null) {
			keyList = new ArrayList();
			//税务机关组织机构
			keyList.add(GY_DM_SWJGZZJG);
			//税源鉴定类型代码表
			keyList.add(DJ_DM_QYSDSZGFWJDLX);
			//纳税人状态
			keyList.add(DJ_DM_NSRZT);
			//减免税备案事项代码表
			keyList.add(SF_DM_JMBASXDM_2014);
			//资源综合利用种类代码表
			keyList.add(SF_DM_ZYZHLYZL);
			//证件类型
			keyList.add(GY_DM_ZJLX);
			//国籍地区
			keyList.add(GY_DM_GJDQ);
			//国家标准行业代码表
			keyList.add(GY_DM_GJBZHY);
			//其他会计制度
			keyList.add(SB_DM_QTKJZZ);
			//登记注册类型
			keyList.add(DJ_DM_DJZCLX);
			//会计制度
			keyList.add(SB_DM_QYSDS_KJZZ);
			//高新技术
			keyList.add(SB_DM_QYSDS_GXJSLYMX);
		}
		return keyList;
	}
	
	/**
	 * 获取级联ID集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRelationIDList() {
		if (idList == null) {
			idList = new ArrayList();
			//国际税收案件状态代码表
			idList.add(String.valueOf(RELATION_CONFIG_ZSXM));
		}
		return idList;
	}
}
