package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx05.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx05Form extends QysdsJmsbajlMainForm {
	
	/*
	 * ���
	 */
	private String xh;
	/*
	 * ������ҵ����˰�б�
	 */
	private List mzqysdsList;
	/*
	 * ������ҵ����˰
	 */
	private String mzqysds;
	/*
	 * ������ҵ����˰����
	 */
	private String mzqysdsmc;
	/*
	 * ������ҵ����˰����
	 */
	private String mzqysdsdm;
	/*
	 * �������ö� 	
	 */
	private String mzsde;
	/*
	 * ����������ҵ����˰�б�
	 */
	private List jbzsqysdsList;
	/*
	 * ����������ҵ����˰
	 */
	private String jbzsqysds;
	/*
	 * ����������ҵ����˰����
	 */
	private String jbzsqysdsmc;
	/*
	 * ����������ҵ����˰����
	 */
	private String jbzsqysdsdm;
	/*
	 * �������ö�
	 */
	private String jzsde;
	/*
	 * �����ʾ
	 */
	private String clbs = "0";
	/*
	 * ��������� 0:������1������;
	 */
	private String mzhjb = "0";
	
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public String getJbzsqysdsdm() {
		return jbzsqysdsdm;
	}
	public void setJbzsqysdsdm(String jbzsqysdsdm) {
		this.jbzsqysdsdm = jbzsqysdsdm;
	}
	public List getJbzsqysdsList() {
		return jbzsqysdsList;
	}
	public void setJbzsqysdsList(List jbzsqysdsList) {
		this.jbzsqysdsList = jbzsqysdsList;
	}
	public String getJbzsqysdsmc() {
		return jbzsqysdsmc;
	}
	public void setJbzsqysdsmc(String jbzsqysdsmc) {
		this.jbzsqysdsmc = jbzsqysdsmc;
	}
	public String getJzsde() {
		return jzsde;
	}
	public void setJzsde(String jzsde) {
		this.jzsde = jzsde;
	}
	public String getMzqysdsdm() {
		return mzqysdsdm;
	}
	public void setMzqysdsdm(String mzqysdsdm) {
		this.mzqysdsdm = mzqysdsdm;
	}
	public List getMzqysdsList() {
		return mzqysdsList;
	}
	public void setMzqysdsList(List mzqysdsList) {
		this.mzqysdsList = mzqysdsList;
	}
	public String getMzqysdsmc() {
		return mzqysdsmc;
	}
	public void setMzqysdsmc(String mzqysdsmc) {
		this.mzqysdsmc = mzqysdsmc;
	}
	public String getMzsde() {
		return mzsde;
	}
	public void setMzsde(String mzsde) {
		this.mzsde = mzsde;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJbzsqysds() {
		return jbzsqysds;
	}
	public void setJbzsqysds(String jbzsqysds) {
		this.jbzsqysds = jbzsqysds;
	}
	public String getMzqysds() {
		return mzqysds;
	}
	public void setMzqysds(String mzqysds) {
		this.mzqysds = mzqysds;
	}
	public String getMzhjb() {
		return mzhjb;
	}
	public void setMzhjb(String mzhjb) {
		this.mzhjb = mzhjb;
	}
	
}