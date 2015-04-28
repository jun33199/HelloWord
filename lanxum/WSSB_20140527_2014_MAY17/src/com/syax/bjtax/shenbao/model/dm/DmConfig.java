/*
 * Created on 2009-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.model.dm;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DmConfig {


	private String tableName="";
	private String beanName="";
	public static final String DM_PACKAGE = "com.syax.bjtax.shenbao.model.dm.";

	public static final String DM_PREFIX = "JMBA_";
	//public static final String DM_POSTFIX = "com.syax.bjtax.shenbao.model.dm.";

	public static DmConfig[] dmConfigs={new DmConfig("DMDB.SF_DM_JMBASXDM","JMBASXDM"),
			new DmConfig("DMDB.SF_DM_DMQYLX","DMQYLX"),
			new DmConfig("DMDB.SF_DM_GGJCSSXMLX","GGJCSSXMLX"),
			new DmConfig("DMDB.SF_DM_GXJSLY","GXJSLY"),
			new DmConfig("DMDB.SF_DM_JNJSXMLX","JNJSXMLX"),
			new DmConfig("DMDB.SF_DM_JSZRLX","JSZRLX"),
			new DmConfig("DMDB.SF_DM_WHSYDWLX","WHSYDWLX"),
			new DmConfig("DMDB.SF_DM_YFFYLY","YFFYLY"),
			new DmConfig("DMDB.SF_DM_ZYSBLX","ZYSBLX"),
			new DmConfig("DMDB.SF_DM_ZYZHLYZL","ZYZHLYZL"),
			new DmConfig("DMDB.SF_DM_NLMYJMXM","NLMYJMXM"),
			new DmConfig("DMDB.SF_DM_FWYWFW","FWYWFW")};


	public static void main(String[] args) {
	}
	public DmConfig(String tableName,String beanName){
		this.tableName = tableName;
		this.beanName = beanName;
	}
	/**
	 * @return Returns the beanName.
	 */
	public String getBeanName() {
		return beanName;
	}

	public String getBeanClassName() {
		return DM_PACKAGE+beanName;
	}
	/**
	 * @param beanName The beanName to set.
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	/**
	 * @return Returns the tableName.
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName The tableName to set.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
