package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx13BForm extends QysdsJmsbajlMainForm {
	/*
	 * ���
	 */
	private String xh;
	/*
	 * �����ʾ0:δ���棻1���������
	 */
	private String clbs = "0";
	/*
	 * ��Ͷ����ҵ�����أ�0�����У�1����ʡ��
	 */
	private String btzqyssd = "0";
	/*
	 * ��Ͷ����ҵ���������
	 */
	private String btzqyjsjdm;
	/*
	 * ��Ͷ����ҵ˰��Ǽ�֤��
	 */
	private String btzqyswdjzh;
	/*
	 * ��Ͷ����ҵ����
	 */
	private String btzqymc;
	/*
	 * ���¼������������б�
	 */
	private List gxjslyList;
	/*
	 * ���¼�������
	 */
	private String gxjsly;
	/*
	 * ���¼����������
	 */
	private String gxjslydm;
	/*
	 * ���¼�����������
	 */
	private String gxjslymc;
	/*
	 * ��������Ͷ�ʶ�
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
	 * �����Ƿ��ѯ��˰������ 0:��ѯ��1������ѯ
	 */
	private String nsrmckz = "0";
	/*
	 * ��ѯͶ����Ϣ��� 0:�н����1���޽��
	 */
	

	
	private String searchAction;
	private String tzxxjg = "1";
	
	
	private String zlState;
	public String getTzxxjg() {
		return tzxxjg;
	}
	public void setTzxxjg(String tzxxjg) {
		this.tzxxjg = tzxxjg;
	}
	public String getBtzqyjsjdm() {
		return btzqyjsjdm;
	}
	public void setBtzqyjsjdm(String btzqyjsjdm) {
		this.btzqyjsjdm = btzqyjsjdm;
	}
	public String getBtzqymc() {
		return btzqymc;
	}
	public void setBtzqymc(String btzqymc) {
		this.btzqymc = btzqymc;
	}
	public String getBtzqyssd() {
		return btzqyssd;
	}
	public void setBtzqyssd(String btzqyssd) {
		this.btzqyssd = btzqyssd;
	}
	public String getBtzqyswdjzh() {
		return btzqyswdjzh;
	}
	public void setBtzqyswdjzh(String btzqyswdjzh) {
		this.btzqyswdjzh = btzqyswdjzh;
	}
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	public String getGxjslydm() {
		return gxjslydm;
	}
	public void setGxjslydm(String gxjslydm) {
		this.gxjslydm = gxjslydm;
	}
	public String getGxjslymc() {
		return gxjslymc;
	}
	public void setGxjslymc(String gxjslymc) {
		this.gxjslymc = gxjslymc;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGxjsly() {
		return gxjsly;
	}
	public void setGxjsly(String gxjsly) {
		this.gxjsly = gxjsly;
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
	public List getGxjslyList() {
		return gxjslyList;
	}
	public void setGxjslyList(List gxjslyList) {
		this.gxjslyList = gxjslyList;
	}
	public String getTze() {
		return tze;
	}
	public void setTze(String tze) {
		this.tze = tze;
	}
	public String getBjkz() {
		return bjkz;
	}
	public void setBjkz(String bjkz) {
		this.bjkz = bjkz;
	}
	public String getNsrmckz() {
		return nsrmckz;
	}
	public void setNsrmckz(String nsrmckz) {
		this.nsrmckz = nsrmckz;
	}
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
	public String getZlState() {
		return zlState;
	}
	public void setZlState(String zlState) {
		this.zlState = zlState;
	}
}