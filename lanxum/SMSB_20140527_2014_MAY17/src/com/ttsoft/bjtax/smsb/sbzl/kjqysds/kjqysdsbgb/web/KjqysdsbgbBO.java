package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.*;

/**
 * 扣缴企业所得税报告表BO
 * @author Administrator
 *
 */
public class KjqysdsbgbBO implements Serializable{

	public KjqysdsbgbBO() {
	}
	/**
	 * 扣缴企业备案合同信息
	 */
	private BAHTXX bahtxx = new BAHTXX();
	/**
	 * 非居民企业信息
	 */
	private FJMQYXX fjmqyxx = new FJMQYXX();
	/**
	 * 扣缴义务人信息
	 */
	private KJYWRXX kjywrxx = new KJYWRXX();
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
	 * 录入人员
	 */
	private String lrr;
	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsbgbList = new ArrayList();
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
	  * 总条数
	  */
   private String zts;
   /**
    * 总页数
    */
   private String zys;
  /**
   * 当前页数
   */
   private String dqy;
   /**
    * 报告表序号
    */
   private String bgbxh;
   /**
    * 申报所得类型List
    */
   private List sbsdlxList = new ArrayList();
   /**
    * 是否已备案已确认且生成代扣代缴信息
    */
   private boolean flag;
	
	
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

	public List getQysdsbgbList() {
		return qysdsbgbList;
	}

	public void setQysdsbgbList(List qysdsbgbList) {
		this.qysdsbgbList = qysdsbgbList;
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
	
	public KJYWRXX getKjywrxx() {
		return kjywrxx;
	}
	
	public void setKjywrxx(KJYWRXX kjywrxx) {
		this.kjywrxx = kjywrxx;
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

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getBadjxh() {
		return badjxh;
	}

	public void setBadjxh(String badjxh) {
		this.badjxh = badjxh;
	}

	public String getDqzt() {
		return dqzt;
	}

	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
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

	public String getBgbxh() {
		return bgbxh;
	}

	public void setBgbxh(String bgbxh) {
		this.bgbxh = bgbxh;
	}

	public List getSbsdlxList() {
		return sbsdlxList;
	}

	public void setSbsdlxList(List sbsdlxList) {
		this.sbsdlxList = sbsdlxList;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}


}
