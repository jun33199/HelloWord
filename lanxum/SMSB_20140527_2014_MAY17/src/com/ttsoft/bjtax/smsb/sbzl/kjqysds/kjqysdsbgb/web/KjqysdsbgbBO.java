package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.*;

/**
 * �۽���ҵ����˰�����BO
 * @author Administrator
 *
 */
public class KjqysdsbgbBO implements Serializable{

	public KjqysdsbgbBO() {
	}
	/**
	 * �۽���ҵ������ͬ��Ϣ
	 */
	private BAHTXX bahtxx = new BAHTXX();
	/**
	 * �Ǿ�����ҵ��Ϣ
	 */
	private FJMQYXX fjmqyxx = new FJMQYXX();
	/**
	 * �۽���������Ϣ
	 */
	private KJYWRXX kjywrxx = new KJYWRXX();
	/**
	 * ˰��������ʼ����
	 */
	private String skssksrq;
	/**
	 * ˰��������������
	 */
	private String skssjsrq;
	/**
	 * ˰���걨����
	 */
	private String sbrq;
	/**
	 * ���������
	 */
	private String jsjdm;
	/**
	 * ¼����Ա
	 */
	private String lrr;
	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsbgbList = new ArrayList();
	/**
	 * �����Ǽ����
	 */
	private String badjxh;
	/**
     * �걨�������ʹ���
     */
    private String sbsdlxdm;
    /**
     * �걨����ȡ������
     */
    private String sbsdqdrq;
    /**
     * ��ǰ״̬
     */
    private String dqzt;
	 /**
	  * ������
	  */
   private String zts;
   /**
    * ��ҳ��
    */
   private String zys;
  /**
   * ��ǰҳ��
   */
   private String dqy;
   /**
    * ��������
    */
   private String bgbxh;
   /**
    * �걨��������List
    */
   private List sbsdlxList = new ArrayList();
   /**
    * �Ƿ��ѱ�����ȷ�������ɴ��۴�����Ϣ
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
