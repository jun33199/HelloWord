package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.*;
import java.sql.*;

import com.ekernel.db.or.*;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �걨�ɿ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */
public class Sbjkzb
    implements ORObject {

  //˰Ʊ����     SPHM	VARCHAR2(18)	FALSE	FALSE	FALSE
  String sphm;
//�ɿ�ƾ֤��	JKPZH	VARCHAR2(20)	TRUE	FALSE	TRUE
  String jkpzh;
//˰�����ʹ���	SKLXDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String sklxdm;
//˰����������	SKLXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String sklxmc;
//���������	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
  String jsjdm;
//�Ǽ��걨��ʽ����	FSDM	VARCHAR2(2)	FALSE	FALSE	TRUE
  String fsdm;
//������ϵ����	LSGXDM	VARCHAR2(4)	FALSE	FALSE	FALSE
  String lsgxdm;
//������ϵ����	LSGXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String lsgxmc;
//���д���	YHDM	VARCHAR2(12)	FALSE	FALSE	FALSE
  String yhdm;
//��������	YHMC	VARCHAR2(100)	FALSE	FALSE	FALSE
  String yhmc;
//�ʺ�	ZH	VARCHARz   2(50)	FALSE	FALSE	FALSE
  String zh;
//�Ǽ�ע�����ʹ���	DJZCLXDM	VARCHAR2(6)	FALSE	FALSE	TRUE
  String djzclxdm;
//�Ǽ�ע����������	DJZCLXMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String djzclxmc;
//˰�������֯��������	SWJGZZJGDM	VARCHAR2(8)	FALSE	FALSE	TRUE
  String swjgzzjgdm;
//˰�������֯��������	SWJGZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String swjgzzjgmc;
//���ջ��ش���	ZSSWJGZZJGDM	VARCHAR2(8)	FALSE	FALSE	TRUE
  String zsswjgzzjgdm;
//���ջ�������	ZSSWJGZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String zsswjgzzjgmc;
//���ұ�׼��ҵ����	GJBZHYDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String gjbzhydm;
//���ұ�׼��ҵ����	GJBZHYMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String gjbzhymc;
//������֯��������	GKZZJGDM	VARCHAR2(10)	FALSE	FALSE	FALSE
  String gkzzjgdm;
//������֯��������	GKZZJGMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String gkzzjgmc;
//Ԥ���Ŀ����	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
  String yskmdm;
//Ԥ���Ŀ����	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String yskmmc;
//Ԥ�㼶�δ���	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String ysjcdm;
//Ԥ�㼶����z  �?YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String ysjcmc;
//˰�ִ���	SZDM	VARCHAR2(9)	FALSE	FALSE	TRUE
  String szdm;
//˰������	SZMC	VARCHAR2(60)	FALSE	FALSE	FALSE
  String szmc;
//¼������	LRRQ	DATE	FALSE	FALSE	TRUE
  Timestamp lrrq;
//�걨����	SBRQ	DATE	FALSE	FALSE	TRUE
  Timestamp sbrq;
//�����տ�ʱ��	JKSJ	DATE	FALSE	FALSE	FALSE
  Timestamp jksj;
//�޽�����	XJRQ	DATE	FALSE	FALSE	FALSE
  Timestamp xjrq;
//�����Ǵ���	CLBJDM	VARCHAR2(4)	FALSE	FALSE	TRUE
  String clbjdm;
//ʵ�ɽ��	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
  BigDecimal sjje;
//��ҳ����	ZYRQ	DATE	FALSE	FALSE	FALSE
  Timestamp zyrq;
//�����	RKJE	NUMBER(15,2)	FALSE	FALSE	TRUE
  BigDecimal rkje;
//�����ʶ	ZWBS	VARCHAR2(20)	FALSE	FALSE	TRUE
  String zwbs;
//�����˴���	HXRDM	VARCHAR2(20)	FALSE	FALSE	FALSE
  String hxrdm;
//����������	HXRMC	VARCHAR2(30)	FALSE	FALSE	FALSE
  String hxrmc;
//¼���˴���	LRR	VARCHAR2(30)	FALSE	FALSE	TRUE
  String lrr;
//��ע	BZ	VARCHAR2(100)	FALSE	FALSE	FALSE
  String bz;
//����ʱ��	CJSJ	DATE	FALSE	FALSE	TRUE
  Timestamp cjrq;
//��������
  Timestamp hxrq;
//�걨���    SBBH	VARCHAR2(20)
  String sbbh;
  //��Ӫ��ַ��ϵ�绰
  String jydzlxdm;

  Timestamp skssksrq; // ˰��������ʼ����

  Timestamp skssjsrq; // ˰��������������
  String sjly; //������Դ

  //��˰������	NSRMC	VARCHAR2(200)	FALSE	FALSE	TRUE
  String nsrmc;
  String nd; //���
  String qxdm;

  public String getBz() {
    return bz;
  }

  public Timestamp getCjrq() {
    return cjrq;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setCjrq(Timestamp cjrq) {
    this.cjrq = cjrq;
  }

  public void setClbjdm(String clbjdm) {
    this.clbjdm = clbjdm;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public void setDjzclxmc(String djzclxmc) {
    this.djzclxmc = djzclxmc;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
  }

  public void setGjbzhymc(String gjbzhymc) {
    this.gjbzhymc = gjbzhymc;
  }

  public void setGkzzjgdm(String gkzzjgdm) {
    this.gkzzjgdm = gkzzjgdm;
  }

  public void setGkzzjgmc(String gkzzjgmc) {
    this.gkzzjgmc = gkzzjgmc;
  }

  public void setHxrdm(String hxrdm) {
    this.hxrdm = hxrdm;
  }

  public void setHxrmc(String hxrmc) {
    this.hxrmc = hxrmc;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public void setJksj(Timestamp jksj) {
    this.jksj = jksj;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setLrrq(Timestamp lrrq) {
    this.lrrq = lrrq;
  }

  public void setLsgxdm(String lsgxdm) {
    this.lsgxdm = lsgxdm;
  }

  public void setLsgxmc(String lsgxmc) {
    this.lsgxmc = lsgxmc;
  }

  public Timestamp getZyrq() {
    return zyrq;
  }

  public String getZwbs() {
    return zwbs;
  }

  public void setZwbs(String zwbs) {
    this.zwbs = zwbs;
  }

  public void setZyrq(Timestamp zyrq) {
    this.zyrq = zyrq;
  }

  public void setZsswjgzzjgmc(String zsswjgzzjgmc) {
    this.zsswjgzzjgmc = zsswjgzzjgmc;
  }

  public void setZsswjgzzjgdm(String zsswjgzzjgdm) {
    this.zsswjgzzjgdm = zsswjgzzjgdm;
  }

  public String getZsswjgzzjgdm() {
    return zsswjgzzjgdm;
  }

  public String getZsswjgzzjgmc() {
    return zsswjgzzjgmc;
  }

  public String getZh() {
    return zh;
  }

  public String getYskmmc() {
    return yskmmc;
  }

  public String getYskmdm() {
    return yskmdm;
  }

  public String getYsjcmc() {
    return ysjcmc;
  }

  public String getYsjcdm() {
    return ysjcdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getYhdm() {
    return yhdm;
  }

  public Timestamp getXjrq() {
    return xjrq;
  }

  public String getSzmc() {
    return szmc;
  }

  public String getSzdm() {
    return szdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSklxmc() {
    return sklxmc;
  }

  public String getSklxdm() {
    return sklxdm;
  }

  public BigDecimal getSjje() {
    return sjje;
  }

  public void setSjje(BigDecimal sjje) {
    this.sjje = sjje;
  }

  public void setSklxdm(String sklxdm) {
    this.sklxdm = sklxdm;
  }

  public void setSklxmc(String sklxmc) {
    this.sklxmc = sklxmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

  public void setXjrq(Timestamp xjrq) {
    this.xjrq = xjrq;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public void setYhmc(String yhmc) {
    this.yhmc = yhmc;
  }

  public void setYsjcdm(String ysjcdm) {
    this.ysjcdm = ysjcdm;
  }

  public void setYsjcmc(String ysjcmc) {
    this.ysjcmc = ysjcmc;
  }

  public void setYskmdm(String yskmdm) {
    this.yskmdm = yskmdm;
  }

  public void setYskmmc(String yskmmc) {
    this.yskmmc = yskmmc;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public void setSbrq(Timestamp sbrq) {
    this.sbrq = sbrq;
  }

  public void setRkje(BigDecimal rkje) {
    this.rkje = rkje;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public Timestamp getSbrq() {
    return sbrq;
  }

  public BigDecimal getRkje() {
    return rkje;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public String getLsgxmc() {
    return lsgxmc;
  }

  public String getLsgxdm() {
    return lsgxdm;
  }

  public Timestamp getLrrq() {
    return lrrq;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public Timestamp getJksj() {
    return jksj;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public String getHxrmc() {
    return hxrmc;
  }

  public String getHxrdm() {
    return hxrdm;
  }

  public String getGkzzjgmc() {
    return gkzzjgmc;
  }

  public String getGkzzjgdm() {
    return gkzzjgdm;
  }

  public String getGjbzhymc() {
    return gjbzhymc;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public String getFsdm() {
    return fsdm;
  }

  public String getDjzclxmc() {
    return djzclxmc;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public String getClbjdm() {
    return clbjdm;
  }

  //�õ���ǰֵ�����clone����
  public Sbjkzb getCopy() {
    try {
      return (Sbjkzb)this.clone();
    }
    catch (Exception ex) {
      return null;
    }

  }

  public Timestamp getHxrq() {
    return hxrq;
  }

  public void setHxrq(Timestamp hxrq) {
    this.hxrq = hxrq;
  }

  public String getSbbh() {
    return sbbh;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public String getJydzlxdm() {
    return jydzlxdm;
  }

  public void setJydzlxdm(String jydzlxdm) {
    this.jydzlxdm = jydzlxdm;
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

  public String getSjly() {
    return sjly;
  }

  public void setSjly(String sjly) {
    this.sjly = sjly;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }
  public String getSphm() {
    return sphm;
  }
  public void setSphm(String sphm) {
    this.sphm = sphm;
  }
}