package com.ttsoft.bjtax.smsb.jccswh.vo;

import java.io.Serializable;
import java.util.Date;

public class ZjjmsdmVo implements Serializable {
	private int indexId;
	/**
	 * ����˰���ʹ���
	 */
	private String jmslxdm;
	/**
	 * �ĺ�
	 */
	private String wh;
	/**
	 * ����˰��������
	 */
	private String jmslxmc;
	/**
	 * ˰��
	 */
	private String sz;
	/**
	 * ע����ʶ
	 */
	private String zxbz;	
	/**
	 * ¼����
	 */
	private String lrr;	
	/**
	 * ¼������
	 */
	private Date lrrq;
	/**
	 * ����˰������ʼ���
	 */
	private String jmszcqsnd;
	/**
	 * ˰�ִ���
	 */
	private String szdm;
	
	/**
	 * ����˰���ʹ������
	 */
	private String jmslxdldm;
	private String jmslxdldmmc;//��������
	/**
	 * ����˰����С�����
	 */
	private String jmslxxldm;
	private String jmslxxldmmc;//С������
	/**
	 * ��Ч��ʶ��0-��Ч��9-��Ч��
	 */
	private String yxbs;
	/**
	 * ��ע
	 */
	private String bz;
	/**
	 * ��ʷ��ţ���ʼֵΪ0��ÿ�α���Զ���һ��
	 */
	private String lsbh;
	
	//�����޸�����¼����Ϣ
	private String reShowStr="";
	
	
	
	
	
	
	public String getJmslxdm() {
		return jmslxdm;
	}
	public void setJmslxdm(String jmslxdm) {
		this.jmslxdm = jmslxdm;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getJmslxmc() {
		return jmslxmc;
	}
	public void setJmslxmc(String jmslxmc) {
		this.jmslxmc = jmslxmc;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public String getZxbz() {
		return zxbz;
	}
	public void setZxbz(String zxbz) {
		this.zxbz = zxbz;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Date getLrrq() {
		return lrrq;
	}
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	public String getJmszcqsnd() {
		return jmszcqsnd;
	}
	public void setJmszcqsnd(String jmszcqsnd) {
		this.jmszcqsnd = jmszcqsnd;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getJmslxdldm() {
		return jmslxdldm;
	}
	public void setJmslxdldm(String jmslxdldm) {
		this.jmslxdldm = jmslxdldm;
	}
	public String getJmslxxldm() {
		return jmslxxldm;
	}
	public void setJmslxxldm(String jmslxxldm) {
		this.jmslxxldm = jmslxxldm;
	}
	public String getYxbs() {
		return yxbs;
	}
	public void setYxbs(String yxbs) {
		this.yxbs = yxbs;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getLsbh() {
		return lsbh;
	}
	public void setLsbh(String lsbh) {
		this.lsbh = lsbh;
	}
	public String getJmslxdldmmc() {
		return jmslxdldmmc;
	}
	public void setJmslxdldmmc(String jmslxdldmmc) {
		this.jmslxdldmmc = jmslxdldmmc;
	}
	public String getJmslxxldmmc() {
		return jmslxxldmmc;
	}
	public void setJmslxxldmmc(String jmslxxldmmc) {
		this.jmslxxldmmc = jmslxxldmmc;
	}
	public int getIndexId() {
		return indexId;
	}
	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}
	public String getReShowStr() {
		return reShowStr;
	}
	public void setReShowStr(String reShowStr) {
		this.reShowStr = reShowStr;
	}
}
