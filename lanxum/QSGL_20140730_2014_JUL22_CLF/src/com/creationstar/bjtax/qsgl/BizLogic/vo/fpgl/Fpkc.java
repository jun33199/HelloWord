package com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ��Ʊ�����ϸ��
 * @author tutu
 * 2013-05-07(1)
 */
public class Fpkc implements Serializable {
	
	/**
     * ��Ʊ�ⷿ����
     */
    public java.lang.String fpkfdm;
    
    /**
     * ��Ʊ�������
     */
    public java.lang.String fpzldm;
    
    /**
     * ���ʱ��
     */
    public java.sql.Timestamp jcsj;
    
    /**
     * ��ʼ����
     */
    public java.lang.String qshm;
    
    /**
     * ��ֹ����
     */
    public java.lang.String jzhm;
    
    /**
     * ����
     */
    public java.math.BigDecimal sl;
    
    /**
     * �ϼ�����
     */
    public java.math.BigDecimal hjsl;
    
    /**
     * ˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;
    
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
    
    /**
     * ��ע
     */
    public java.lang.String bz;
    
    /**
     * ����ʾ������������¼��������棩
     */
    public java.lang.String rkbs;
    
    

	public java.lang.String getRkbs() {
		return rkbs;
	}

	public void setRkbs(java.lang.String rkbs) {
		this.rkbs = rkbs;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(java.lang.String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public java.lang.String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(java.lang.String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public java.sql.Timestamp getJcsj() {
		return jcsj;
	}

	public void setJcsj(java.sql.Timestamp jcsj) {
		this.jcsj = jcsj;
	}

	public java.lang.String getQshm() {
		return qshm;
	}

	public void setQshm(java.lang.String qshm) {
		this.qshm = qshm;
	}

	public java.lang.String getJzhm() {
		return jzhm;
	}

	public void setJzhm(java.lang.String jzhm) {
		this.jzhm = jzhm;
	}

	public java.lang.String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public java.math.BigDecimal getSl() {
		return sl;
	}

	public void setSl(java.math.BigDecimal sl) {
		this.sl = sl;
	}

	public void setSwjgzzjgdm(java.lang.String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
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

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.math.BigDecimal getHjsl() {
		return hjsl;
	}

	public void setHjsl(java.math.BigDecimal hjsl) {
		this.hjsl = hjsl;
	}

}
