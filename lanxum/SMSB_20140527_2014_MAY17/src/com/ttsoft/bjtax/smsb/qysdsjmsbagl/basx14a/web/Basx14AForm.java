package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14a.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx14AForm extends QysdsJmsbajlMainForm {
	/*
	 * ���
	 */
	private String xh;
	
	/*
	 * �����ʾ0:δ���棻1���������2���ٴα���
	 */
	private String clbs = "0";
	
	/*
	 * ��ҵ���ò�ʵ��ʹ�õ�ר���豸�����б�
	 */
	private List zysblxList = new ArrayList();
	/*
	 * ��ҵ���ò�ʵ��ʹ�õ�ר���豸����
	 */
	private String zysblx;
	/*
	 * ��ҵ���ò�ʵ��ʹ�õ�ר���豸���ʹ���
	 */
	private String zysblxdm;
	/*
	 * ��ҵ���ò�ʵ��ʹ�õ�ר���豸��������
	 */
	private String zysblxmc;
	/*
	 * ��ҵ���ò�ʵ��ʹ�õ�ר���豸����
	 */
	private String zysbmc;
	/*
	 * �������
	 */
	private String gznd;
	
	/*
	 * ����ר���豸��Ͷ�ʶ�
	 */
	private String tze;
	/*
	 * ����ר���豸Ͷ�ʶ�ɵ����Ӧ��˰��
	 */
	private String dmynse;
	
	
	
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	public String getDmynse() {
		return dmynse;
	}
	public void setDmynse(String dmynse) {
		this.dmynse = dmynse;
	}
	public String getGznd() {
		return gznd;
	}
	public void setGznd(String gznd) {
		this.gznd = gznd;
	}
	
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getZysblxdm() {
		return zysblxdm;
	}
	public void setZysblxdm(String zysblxdm) {
		this.zysblxdm = zysblxdm;
	}
	public String getZysbmc() {
		return zysbmc;
	}
	public void setZysbmc(String zysbmc) {
		this.zysbmc = zysbmc;
	}
	public String getZysblxmc() {
		return zysblxmc;
	}
	public void setZysblxmc(String zysblxmc) {
		this.zysblxmc = zysblxmc;
	}
	public String getZysblx() {
		return zysblx;
	}
	public void setZysblx(String zysblx) {
		this.zysblx = zysblx;
	}
	public List getZysblxList() {
		return zysblxList;
	}
	public void setZysblxList(List zysblxList) {
		this.zysblxList = zysblxList;
	}
	
	
	


}