package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * <p>Title: ������˰��������ϵͳ-˰����</p>
 * <p>Description: �����������</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */
public class PKTaskModel implements Serializable {


	//���
	private String xh;
	//����
	private String zxrq; 
	//ʱ��
	private String sj;
	//ִ��ʱ��
	private String zxsj;
	//ִ��״̬
	private String zxzt;
	//ִ�н��
	private String zxjg;
	//�������� 01--���ɴ���02--���Ϳۿ�
	private String rwlx;
	//������������
	private String rwlxmc;
	//������
	private String cjr;
	//��������
	private Date cjrq;
	//¼����
	private String lrr;
	//¼������
	private Date lrrq;
	/*�������۷�������*/
	//�б�
	private List pkfsList;
	
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZxsj() {
		return zxsj;
	}
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	public String getZxzt() {
		return zxzt;
	}
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}
	public String getZxjg() {
		return zxjg;
	}
	public void setZxjg(String zxjg) {
		this.zxjg = zxjg;
	}
	public String getRwlx() {
		return rwlx;
	}
	public void setRwlx(String rwlx) {
		this.rwlx = rwlx;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Date getCjrq() {
		return cjrq;
	}
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
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
	public String getZxrq() {
		return zxrq;
	}
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getRwlxmc() {
		return rwlxmc;
	}
	public void setRwlxmc(String rwlxmc) {
		this.rwlxmc = rwlxmc;
	}
	public List getPkfsList() {
		return pkfsList;
	}
	public void setPkfsList(List pkfsList) {
		this.pkfsList = pkfsList;
	}
	
}
