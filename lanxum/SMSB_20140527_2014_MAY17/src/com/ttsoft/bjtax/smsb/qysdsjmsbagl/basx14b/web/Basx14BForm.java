package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx14BForm extends QysdsJmsbajlMainForm {
	/*
	 * ���
	 */
	private String xh;
	/*
	 * �����ʾ0:δ���棻1���������
	 */
	private String clbs = "0";
	/*
	 * ���걸��������
	 */
	private String wnbasqbh;
	/*
	 * ר���豸���������б�
	 */
	private List zysblxList;
	/*
	 * ר���豸����
	 */
	private String zysblx;
	/*
	 * ר���豸���ʹ���
	 */
	private String zysblxdm;
	/*
	 * ר���豸��������
	 */
	private String zysblxmc;
	/*
	 * ר���豸����
	 */
	private String zysbmc;
	/*
	 * �������
	 */
	private String gznd;
	/*
	 * ���깺���豸Ͷ�ʶ�
	 */
	private String tze;
	/*
	 * ����ɵֿ�Ӧ��˰���ö�
	 */
	private String kdke;
	/*
	 * ����ʵ�ʵֿ�Ӧ��˰���ö�
	 */
	private String sjdke;
	/*
	 * ��ת�Ժ���ȵֿ�Ӧ��˰���ö�
	 */
	private String jzdke;
	/*
	 * ���Ƹ�Ͷ����ȼ�¼�Ƿ���Ա༭
	 */
	private String bjkz;
	/*
	 * ��ѯͶ����Ϣ��� 0:�н����1���޽��
	 */
	private String tzxxjg = "1";
	private String searchAction;
	private String zlState;
	public String getZlState() {
		return zlState;
	}
	public void setZlState(String zlState) {
		this.zlState = zlState;
	}
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
	public String getZysbmc() {
		return zysbmc;
	}
	public String getBjkz() {
		return bjkz;
	}
	public void setBjkz(String bjkz) {
		this.bjkz = bjkz;
	}
	public String getJzdke() {
		return jzdke;
	}
	public void setJzdke(String jzdke) {
		this.jzdke = jzdke;
	}
	public String getKdke() {
		return kdke;
	}
	public void setKdke(String kdke) {
		this.kdke = kdke;
	}
	public String getSjdke() {
		return sjdke;
	}
	public void setSjdke(String sjdke) {
		this.sjdke = sjdke;
	}
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getTzxxjg() {
		return tzxxjg;
	}
	public void setTzxxjg(String tzxxjg) {
		this.tzxxjg = tzxxjg;
	}
	public void setZysbmc(String zysbmc) {
		this.zysbmc = zysbmc;
	}
	public String getZysblx() {
		return zysblx;
	}
	public void setZysblx(String zysblx) {
		this.zysblx = zysblx;
	}
	public String getZysblxdm() {
		return zysblxdm;
	}
	public void setZysblxdm(String zysblxdm) {
		this.zysblxdm = zysblxdm;
	}
	public List getZysblxList() {
		return zysblxList;
	}
	public void setZysblxList(List zysblxList) {
		this.zysblxList = zysblxList;
	}
	public String getZysblxmc() {
		return zysblxmc;
	}
	public void setZysblxmc(String zysblxmc) {
		this.zysblxmc = zysblxmc;
	}
	public String getWnbasqbh() {
		return wnbasqbh;
	}
	public void setWnbasqbh(String wnbasqbh) {
		this.wnbasqbh = wnbasqbh;
	}
	public String getGznd() {
		return gznd;
	}
	public void setGznd(String gznd) {
		this.gznd = gznd;
	}
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
}