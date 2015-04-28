/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.FJMQYXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税报告表-上门模块</p>
 * <p>Description:扣缴企业所得税报告表</p>
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbForm extends BaseForm {
	public KjqysdsbgbForm() {

		
	}

	/**
	 * 报告报列表标题项目代码 行次、依法申报数据
	 * String[]
	 */
	private String[] bgb_columns = { "hc","sj" };

	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsbgbList = new ArrayList();
	/**
	 * 扣缴义务人信息
	 */
	private KJYWRXX kjywrxx = new KJYWRXX();
	/**
	 * 扣缴企业备案合同信息
	 */
	private BAHTXX bahtxx = new BAHTXX();
	/**
	 * 非居民企业信息
	 */
	private FJMQYXX fjmqyxx = new FJMQYXX();
	/**
	 * 录入人
	 */
	private String lrr;
	/**
	 * 税款所属开始日期
	 */
	private String skssksrq;
	/**
	 * 税款所属结束日期
	 */
	private String skssjsrq;
	/**
	 * 税款申报日期
	 */
	private String sbrq;
	/**
	 * 计算机代码
	 */
	private String jsjdm;
	 /**
	  * 总条数
	  */
    private String zts = null;
    /**
     * 总页数
     */
    private String zys = null;
   /**
    * 当前页数
    */
    private String dqy = null;
   /**
    * 备案登记序号
    */
    private String badjxh;
    /**
     * 申报所得类型代码
     */
    private String sbsdlxdm;
    /**
     * 申报所得取得日期
     */
    private String sbsdqdrq;
    /**
     * 当前状态
     */
    private String dqzt;
    /**
     * 报告表序号
     */
    private String bgbxh;
    /**
     * 上一次当前状态
     */
    private String oldzt;
	
	
	public String getBadjxh() {
	return badjxh;
}

public void setBadjxh(String badjxh) {
	this.badjxh = badjxh;
}

	public String getZts() {
	return zts;
}

public void setZts(String zts) {
	this.zts = zts;
}

public String getZys() {
	return zys;
}

public void setZys(String zys) {
	this.zys = zys;
}

public String getDqy() {
	return dqy;
}

public void setDqy(String dqy) {
	this.dqy = dqy;
}

	public String[] getBgb_columns() {
		return bgb_columns;
	}
	
	public void setBgb_columns(String[] bgb_columns) {
		this.bgb_columns = bgb_columns;
	}
	
	public List getQysdsbgbList() {
		return qysdsbgbList;
	}
	
	public void setQysdsbgbList(List qysdsbgbList) {
		this.qysdsbgbList = qysdsbgbList;
	}
	
	public KJYWRXX getKjywrxx() {
		return kjywrxx;
	}
	
	public void setKjywrxx(KJYWRXX kjywrxx) {
		this.kjywrxx = kjywrxx;
	}
	
	public BAHTXX getBahtxx() {
		return bahtxx;
	}
	
	public void setBahtxx(BAHTXX bahtxx) {
		this.bahtxx = bahtxx;
	}
	
	public FJMQYXX getFjmqyxx() {
		return fjmqyxx;
	}
	
	public void setFjmqyxx(FJMQYXX fjmqyxx) {
		this.fjmqyxx = fjmqyxx;
	}
	
	public String getLrr() {
		return lrr;
	}
	
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	
	public String getSkssksrq() {
		return skssksrq;
	}
	
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	
	public String getSkssjsrq() {
		return skssjsrq;
	}
	
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	
	public String getSbrq() {
		return sbrq;
	}
	
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	
	public String getJsjdm() {
		return jsjdm;
	}
	
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getSbsdlxdm() {
		return sbsdlxdm;
	}

	public void setSbsdlxdm(String sbsdlxdm) {
		this.sbsdlxdm = sbsdlxdm;
	}

	public String getSbsdqdrq() {
		return sbsdqdrq;
	}

	public void setSbsdqdrq(String sbsdqdrq) {
		this.sbsdqdrq = sbsdqdrq;
	}

	public String getDqzt() {
		return dqzt;
	}

	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}

	public String getBgbxh() {
		return bgbxh;
	}

	public void setBgbxh(String bgbxh) {
		this.bgbxh = bgbxh;
	}

	public String getOldzt() {
		return oldzt;
	}

	public void setOldzt(String oldzt) {
		this.oldzt = oldzt;
	}
	
}
