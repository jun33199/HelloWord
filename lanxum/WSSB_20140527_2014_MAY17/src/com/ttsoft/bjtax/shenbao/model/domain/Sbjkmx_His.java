package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

public class Sbjkmx_His implements ORObject{
	
//	ZRLXDM	VARCHAR2(2)	N			ת�����ʹ���,������ʶ��ʷ���ݵ�����
	String zrlxdm;
//	ZRRQ	TIMESTAMP(6)	N			ת������
	Timestamp zrrq;
//	ZRR	VARCHAR2(12)	Y			ת����
	String zrr;
	
//	˰��˰Ŀ����	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
    String szsmdm;
//�ɿ�ƾ֤��	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
    String jkpzh;
//���������	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
    String jsjdm;
//Ԥ���Ŀ����	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
    String yskmdm;
//Ԥ���Ŀ����	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String yskmmc;
//Ԥ�㼶�δ���	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
    String ysjcdm;
//Ԥ�㼶������	YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String ysjcmc;
//˰��˰Ŀ����	SZSMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String szsmmc;
//��˰����	KSSL	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal kssl;
//��˰���	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal jsje;
//ʵz  [��˰��	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal sjse;
//˰��������ʼ����	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssksrq;
//˰��������������	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssjsrq;
//˰�ִ���        SZDM    VARCHAR2(6)     �ⲻ�Ǳ��е��ֶ�
    String szdm;
//˰������        SZMC    VARCHAR2(6)     �ⲻ�Ǳ��е��ֶ�
    String szmc;
//�������ʹ���
    String zqlxdm; //�ⲻ�����ݿ��е��ֶ�,Ϊ�˺�̨��ȡ�õ�˰������ʱ�ڱ����������Ϣ
//�����	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
    BigDecimal rkje;
//�걨���    SBBH	VARCHAR2(20)
    String sbbh;
    //�оּ��ֳ�(����4ΪС��)
    BigDecimal sjfc;
    //���ؼ��ֳ�(����4ΪС��)
    BigDecimal qjfc;
    String swjgzzjgdm;//˰�������֯��������
    String nd;//���
    BigDecimal sl;//˰��

    Timestamp cjrq;
    Timestamp lrrq;
    String qxdm;
    
    /**
     * @Description: TODO ����bean����ʷ��
     * @param sbjkmx
     */
    public void copyFrom(Sbjkmx sbjkmx)
    {
    	if(sbjkmx==null)
    	{
    		return;
    	}
//    	this.zrlxdm = sbjkmx.zrlxdm;
//    	this.zrrq = sbjkmx.zrrq;
//    	this.zrr = sbjkmx.zrr;
    	this.szsmdm = sbjkmx.szsmdm;
    	this.jkpzh = sbjkmx.jkpzh;
    	this.jsjdm = sbjkmx.jsjdm;
    	this.yskmdm = sbjkmx.yskmdm;
    	this.yskmmc = sbjkmx.yskmmc;
    	this.ysjcdm = sbjkmx.ysjcdm;
    	this.ysjcmc = sbjkmx.ysjcmc;
    	this.szsmmc = sbjkmx.szsmmc;
    	this.kssl = sbjkmx.kssl;
    	this.jsje = sbjkmx.jsje;
    	this.sjse = sbjkmx.sjse;
    	this.skssksrq = sbjkmx.skssksrq;
    	this.skssjsrq = sbjkmx.skssjsrq;
    	this.szdm = sbjkmx.szdm;
    	this.szmc = sbjkmx.szmc;
    	this.zqlxdm = sbjkmx.zqlxdm;
    	this.rkje = sbjkmx.rkje;
    	this.sbbh = sbjkmx.sbbh;
    	this.sjfc  = sbjkmx.sjfc;
    	this.qjfc = sbjkmx.qjfc;
    	this.swjgzzjgdm = sbjkmx.swjgzzjgdm;
    	this.nd = sbjkmx.nd;
    	this.sl = sbjkmx.sl;
    	this.cjrq = sbjkmx.cjrq;
    	this.lrrq = sbjkmx.lrrq;
    	this.qxdm = sbjkmx.qxdm;

    }
    
	public Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public String getJkpzh() {
		return jkpzh;
	}
	public void setJkpzh(String jkpzh) {
		this.jkpzh = jkpzh;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public BigDecimal getJsje() {
		return jsje;
	}
	public void setJsje(BigDecimal jsje) {
		this.jsje = jsje;
	}
	public BigDecimal getKssl() {
		return kssl;
	}
	public void setKssl(BigDecimal kssl) {
		this.kssl = kssl;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public BigDecimal getQjfc() {
		return qjfc;
	}
	public void setQjfc(BigDecimal qjfc) {
		this.qjfc = qjfc;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public BigDecimal getRkje() {
		return rkje;
	}
	public void setRkje(BigDecimal rkje) {
		this.rkje = rkje;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public BigDecimal getSjfc() {
		return sjfc;
	}
	public void setSjfc(BigDecimal sjfc) {
		this.sjfc = sjfc;
	}
	public BigDecimal getSjse() {
		return sjse;
	}
	public void setSjse(BigDecimal sjse) {
		this.sjse = sjse;
	}
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	public Timestamp getSkssksrq() {
		return skssksrq;
	}
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getSzmc() {
		return szmc;
	}
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	public String getSzsmdm() {
		return szsmdm;
	}
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	public String getSzsmmc() {
		return szsmmc;
	}
	public void setSzsmmc(String szsmmc) {
		this.szsmmc = szsmmc;
	}
	public String getYsjcdm() {
		return ysjcdm;
	}
	public void setYsjcdm(String ysjcdm) {
		this.ysjcdm = ysjcdm;
	}
	public String getYsjcmc() {
		return ysjcmc;
	}
	public void setYsjcmc(String ysjcmc) {
		this.ysjcmc = ysjcmc;
	}
	public String getYskmdm() {
		return yskmdm;
	}
	public void setYskmdm(String yskmdm) {
		this.yskmdm = yskmdm;
	}
	public String getYskmmc() {
		return yskmmc;
	}
	public void setYskmmc(String yskmmc) {
		this.yskmmc = yskmmc;
	}
	public String getZqlxdm() {
		return zqlxdm;
	}
	public void setZqlxdm(String zqlxdm) {
		this.zqlxdm = zqlxdm;
	}
	public String getZrlxdm() {
		return zrlxdm;
	}
	public void setZrlxdm(String zrlxdm) {
		this.zrlxdm = zrlxdm;
	}
	public String getZrr() {
		return zrr;
	}
	public void setZrr(String zrr) {
		this.zrr = zrr;
	}
	public Timestamp getZrrq() {
		return zrrq;
	}
	public void setZrrq(Timestamp zrrq) {
		this.zrrq = zrrq;
	}
	

}
