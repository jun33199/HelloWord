package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;


/**
 * ��Ʊ���ʹ����
 * @author tutu
 * 2013-05-07(1)
 */
public class Kplx implements Serializable {
	
	/**
     * ��Ʊ���ʹ���
     */
    public java.lang.String kplxdm;

    /**
     * ��Ʊ��������
     */
    public java.lang.String kplxmc;
    
    /**
     * ע����ʶ
     */
    public java.lang.String zxbs;
    
    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ������
     */
    public java.lang.String cjr;

    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;
    
    

	public java.lang.String getKplxdm() {
		return kplxdm;
	}

	public void setKplxdm(java.lang.String kplxdm) {
		this.kplxdm = kplxdm;
	}

	public java.lang.String getKplxmc() {
		return kplxmc;
	}

	public void setKplxmc(java.lang.String kplxmc) {
		this.kplxmc = kplxmc;
	}

	public java.lang.String getZxbs() {
		return zxbs;
	}

	public void setZxbs(java.lang.String zxbs) {
		this.zxbs = zxbs;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

}
