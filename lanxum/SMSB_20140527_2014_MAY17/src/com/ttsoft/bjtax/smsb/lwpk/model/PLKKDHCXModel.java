package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *������ѯ�����ۿ�model
 *kanght 
 * 201307
 */
public class PLKKDHCXModel implements Serializable {

	//˰Ʊ����
	private String sphm;
	//˰������
	private String szmc;
	//ʵ�ɽ��
	private BigDecimal sjje;
	//�ۿ�ʱ��
	private Date kksj;
	//�ۿ�״̬
	private String kkzt;
	//�ۿ�ʧ��ԭ��
	private String kksbyy;
	
	
	
	public String getSphm() {
		return sphm;
	}
	public void setSphm(String sphm) {
		this.sphm = sphm;
	}
	public String getSzmc() {
		return szmc;
	}
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	public BigDecimal getSjje() {
		return sjje;
	}
	public void setSjje(BigDecimal sjje) {
		this.sjje = sjje;
	}
	public Date getKksj() {
		return kksj;
	}
	public void setKksj(Date kksj) {
		this.kksj = kksj;
	}
	public String getKkzt() {
		return kkzt;
	}
	public void setKkzt(String kkzt) {
		this.kkzt = kkzt;
	}
	public String getKksbyy() {
		return kksbyy;
	}
	public void setKksbyy(String kksbyy) {
		this.kksbyy = kksbyy;
	}
	
}
