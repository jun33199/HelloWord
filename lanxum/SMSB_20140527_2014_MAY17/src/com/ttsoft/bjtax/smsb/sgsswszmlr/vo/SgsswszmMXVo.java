package com.ttsoft.bjtax.smsb.sgsswszmlr.vo;

import java.io.Serializable;
/**
 * (��Ʊ�� - �ֹ�¼��˰����˰֤��) add by  tangchangfu  2013-11-24 
 * @author admin
 *
 */
public class SgsswszmMXVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String old_pzh;//ԭƾ֤��
	private String select_szdm;//˰�ִ���
	private String select_szmc;//˰������
	private String select_zssmdm;//ѡ��˰��
	private String select_zssmmc;//˰��˰Ŀ����
	private String skssksrq;//˰��������ʼ����
	private String skssjsrq;//˰��������������
	private String skssrq;//˰���������� 
	private String rk_tkrq;//�루�ˣ�������
	private String sjje;//ʵ�ɽ��
	private String swjgzzjgdm;
	private String lrr ;
	private String cjr;
	
	
	
	
	public String getOld_pzh() {
		return old_pzh;
	}
	public void setOld_pzh(String old_pzh) {
		this.old_pzh = old_pzh;
	}
	public String getSelect_zssmdm() {
		String[] szsmArr = this.select_zssmdm.split("-");
		return szsmArr[0];
	}
	public void setSelect_zssmdm(String select_zssmdm) {
		this.select_zssmdm = select_zssmdm;
	}
	public String getSelect_zssmmc() {
		return select_zssmmc;
	}
	public void setSelect_zssmmc(String select_zssmmc) {
		this.select_zssmmc = select_zssmmc;
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
	public String getRk_tkrq() {
		return rk_tkrq;
	}
	public void setRk_tkrq(String rk_tkrq) {
		this.rk_tkrq = rk_tkrq;
	}
	public String getSjje() {
		return sjje;
	}
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSelect_szdm() {
		return this.getSelect_zssmdm().substring(0, 2);
	}
	public void setSelect_szdm(String select_szdm) {
		this.select_szdm = select_szdm;
	}
	public String getSelect_szmc() {
		return select_szmc;
	}
	public void setSelect_szmc(String select_szmc) {
		this.select_szmc = select_szmc;
	}
	public String getSkssrq() {
		return skssrq;
	}
	public void setSkssrq(String skssrq) {
		this.skssrq = skssrq;
	}
	
	
	
	
}
