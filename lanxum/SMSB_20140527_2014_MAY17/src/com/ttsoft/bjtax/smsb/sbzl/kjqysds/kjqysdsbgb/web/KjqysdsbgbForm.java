/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.FJMQYXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰�����-����ģ��</p>
 * <p>Description:�۽���ҵ����˰�����</p>
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbForm extends BaseForm {
	public KjqysdsbgbForm() {

		
	}

	/**
	 * ���汨�б������Ŀ���� �дΡ������걨����
	 * String[]
	 */
	private String[] bgb_columns = { "hc","sj" };

	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsbgbList = new ArrayList();
	/**
	 * �۽���������Ϣ
	 */
	private KJYWRXX kjywrxx = new KJYWRXX();
	/**
	 * �۽���ҵ������ͬ��Ϣ
	 */
	private BAHTXX bahtxx = new BAHTXX();
	/**
	 * �Ǿ�����ҵ��Ϣ
	 */
	private FJMQYXX fjmqyxx = new FJMQYXX();
	/**
	 * ¼����
	 */
	private String lrr;
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
	  * ������
	  */
    private String zts = null;
    /**
     * ��ҳ��
     */
    private String zys = null;
   /**
    * ��ǰҳ��
    */
    private String dqy = null;
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
     * ��������
     */
    private String bgbxh;
    /**
     * ��һ�ε�ǰ״̬
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
