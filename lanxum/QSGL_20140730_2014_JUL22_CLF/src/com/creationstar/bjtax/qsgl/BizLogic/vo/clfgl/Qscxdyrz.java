package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;

/**
 * ������������˰��ѯ��ӡ������¼��־VO
 * @author tutu
 * 2013-08-19(1)
 */
public class Qscxdyrz implements Serializable {

	/**
     * ���
     */
    public java.lang.String xh;
    /**
     * ��ͬ���
     */
    public java.lang.String htbh;
    
    /**
     * �걨���(��ͬ��Ŷ�Ӧ���걨���)
     */
    public java.lang.String sbbh_clf;
    
    /**
     * �걨���(��˰��ѯ��ӡѡ�е���˰�걨���)
     */
    public java.lang.String sbbh_qs;
    
    /**
     * ������(��Ϊ�����ˣ�Ϊ��yhid+:+yhmc��)
     */
    public java.lang.String cjr;
    
    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;

	public java.lang.String getXh() {
		return xh;
	}

	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getSbbh_clf() {
		return sbbh_clf;
	}

	public void setSbbh_clf(java.lang.String sbbh_clf) {
		this.sbbh_clf = sbbh_clf;
	}

	public java.lang.String getSbbh_qs() {
		return sbbh_qs;
	}

	public void setSbbh_qs(java.lang.String sbbh_qs) {
		this.sbbh_qs = sbbh_qs;
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
