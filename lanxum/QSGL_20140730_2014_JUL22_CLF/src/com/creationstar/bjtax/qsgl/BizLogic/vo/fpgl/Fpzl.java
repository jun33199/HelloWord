package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;

/**
 * ��Ʊ��������
 * @author tutu
 * 2013-05-07(1)
 */
public class Fpzl implements Serializable{

	/**
     * ��Ʊ�������
     */
    public java.lang.String fpzldm;
    
    /**
     * ��Ʊ��������
     */
    public java.lang.String fpzlmc;
    
    /**
     * ������ʾ
     */
    public java.lang.String zxbs;
    
    /**
     * ��Ʊ���볤��
     */
    public java.lang.String fphmcd;
    
    /**
     * ������
     */
    public java.lang.String cjr;
    
    /**
     * ¼����
     */
    public java.lang.String lrr;
    
    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

	public java.lang.String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(java.lang.String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public java.lang.String getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(java.lang.String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public java.lang.String getZxbs() {
		return zxbs;
	}

	public void setZxbs(java.lang.String zxbs) {
		this.zxbs = zxbs;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getFphmcd() {
		return fphmcd;
	}

	public void setFphmcd(java.lang.String fphmcd) {
		this.fphmcd = fphmcd;
	}
	
}
