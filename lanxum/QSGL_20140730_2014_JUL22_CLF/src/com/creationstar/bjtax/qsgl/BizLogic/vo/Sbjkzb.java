package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * �걨�ɿ���������ֵ����
 */
public class Sbjkzb implements Serializable {

    /**
     * �ɿ�ƾ֤��
     */
    public java.lang.String jkpzh;

    /**
     * ��˰������
     */
    public java.lang.String nsrmc;

    /**
     * ˰�����ʹ���
     */
    public java.lang.String sklxdm;

    /**
     * ˰����������
     */
    public java.lang.String sklxmc;

    /**
     * ���������
     */
    public java.lang.String jsjdm;

    /**
     * �Ǽ��걨��ʽ����
     */
    public java.lang.String fsdm;

    /**
     * ������ϵ����
     */
    public java.lang.String lsgxdm;

    /**
     * ������ϵ����
     */
    public java.lang.String lsgxmc;

    /**
     * ���д���
     */
    public java.lang.String yhdm;

    /**
     * ��������
     */
    public java.lang.String yhmc;

    /**
     * �ʺ�
     */
    public java.lang.String zh;

    /**
     * �Ǽ�ע�����ʹ���
     */
    public java.lang.String djzclxdm;
    public String djzclxmc;

    /**
     * ˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;
    public String swjgzzjgmc;

    /**
     * ���ջ��ش���
     */
    public java.lang.String zsswjgzzjgdm;
    public String zsswjgzzjgmc;

    /**
     * ���ұ�׼��ҵ����
     */
    public java.lang.String gjbzhydm;
    public String gjbzhymc;

    /**
     * ������֯��������
     */
    public java.lang.String gkzzjgdm;
    public String gkzzjgmc;

    /**
     * Ԥ���Ŀ����
     */
    public java.lang.String yskmdm;
    public String yskmmc;

    /**
     * Ԥ�㼶��
     */
    public java.lang.String ysjcdm;
    public String ysjcmc;

    /**
     * ˰�ִ���
     */
    public java.lang.String szdm;
    public String szmc;

    /**
     * ¼��ʱ��
     */
    public java.sql.Timestamp lrrq;

    /**
     * �걨����
     */
    public java.sql.Timestamp sbrq;

    /**
     * �����տ�ʱ��
     */
    public java.sql.Timestamp jksj;

    /**
     * �޽�����
     */
    public java.sql.Timestamp xjrq;

    /**
     * �����Ǵ���
     */
    public java.lang.String clbjdm;

    /**
     * ʵ�ɽ��
     */
    public java.math.BigDecimal sjje;

    /**
     * ��ҳ����
     */
    public java.sql.Timestamp zyrq;

    /**
     * �����
     */
    public java.math.BigDecimal rkje;

    /**
     * �����ʶ
     */
    public java.lang.String zwbs;

    /**
     * �����˴���
     */
    public java.lang.String hxrdm;

    /**
     * ����������
     */
    public java.lang.String hxrmc;

    /**
     * ¼���˴���
     */
    public java.lang.String lrr;

    /**
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ��������
     */
    public java.sql.Timestamp hxrq;

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * ��Ӫ��ַ��ϵ�绰
     */
    public java.lang.String jydzlxdm;

    /**
     * ˰��������ʼ����
     */
    public java.sql.Timestamp skssksrq;

    /**
     * ˰��������������
     */
    public java.sql.Timestamp skssjsrq;

    /**
     * ������Դ
     */
    public java.lang.String sjly;

    /**
     * ���
     */
    public java.lang.String nd;

    /**
     * ����ʱ��
     */
    public java.sql.Timestamp cjrq;

    /**
     * ���ش���
     */
    public java.lang.String qxdm;

    /**
     * ��������˰֤��������Ҳ����һ�Žɿ���ȫ���Ŀ�˰����
     */
    public int kssl;

    /**
     * ���ز�λ�ã�ֱ�����ɵĽɿ����ӡ��
     */
    public String fdcwz;

    /**
     * ���⽻����
     */
    public String gkjhh;

    /**
     * ��ϸ���ݵļ���
     */
    public ArrayList mxList = new ArrayList();

    /**
     * ��ýɿ�ƾ֤��
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * ���˰�����ʹ���
     */
    public String getSklxdm() {
        return sklxdm;
    }

    /**
     * ��ü��������
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * ��õǼ��걨��ʽ����
     */
    public String getFsdm() {
        return fsdm;
    }

    /**
     * ���������ϵ����
     */
    public String getLsgxdm() {
        return lsgxdm;
    }

    /**
     * ������д���
     */
    public String getYhdm() {
        return yhdm;
    }

    /**
     * �����������
     */
    public String getYhmc() {
        return yhmc;
    }

    /**
     * ����ʺ�
     */
    public String getZh() {
        return zh;
    }

    /**
     * ��õǼ�ע�����ʹ���
     */
    public String getDjzclxdm() {
        return djzclxdm;
    }

    /**
     * ���˰�������֯��������
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * ������ջ��ش���
     */
    public String getZsswjgzzjgdm() {
        return zsswjgzzjgdm;
    }

    /**
     * ��ù��ұ�׼��ҵ����
     */
    public String getGjbzhydm() {
        return gjbzhydm;
    }

    /**
     * ��ù�����֯��������
     */
    public String getGkzzjgdm() {
        return gkzzjgdm;
    }

    /**
     * ���Ԥ���Ŀ����
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * ���Ԥ�㼶��
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * ���˰�ִ���
     */
    public String getSzdm() {
        return szdm;
    }

    /**
     * ���¼��ʱ��
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * ����걨����
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * ��������տ�ʱ��
     */
    public Timestamp getJksj() {
        return jksj;
    }

    /**
     * ����޽�����
     */
    public Timestamp getXjrq() {
        return xjrq;
    }

    /**
     * ��ô����Ǵ���
     */
    public String getClbjdm() {
        return clbjdm;
    }

    /**
     * ���ʵ�ɽ��
     */
    public BigDecimal getSjje() {
        return sjje;
    }

    /**
     * �����ҳ����
     */
    public Timestamp getZyrq() {
        return zyrq;
    }

    /**
     * ��������
     */
    public BigDecimal getRkje() {
        return rkje;
    }

    /**
     * ��������ʶ
     */
    public String getZwbs() {
        return zwbs;
    }

    /**
     * ��ú����˴���
     */
    public String getHxrdm() {
        return hxrdm;
    }

    /**
     * ��ú���������
     */
    public String getHxrmc() {
        return hxrmc;
    }

    /**
     * ���¼���˴���
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
    }

    /**
     * ��ú�������
     */
    public Timestamp getHxrq() {
        return hxrq;
    }

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ��þ�Ӫ��ַ��ϵ�绰
     */
    public String getJydzlxdm() {
        return jydzlxdm;
    }

    /**
     * ���˰��������ʼ����
     */
    public Timestamp getSkssksrq() {
        return skssksrq;
    }

    /**
     * ���˰��������������
     */
    public Timestamp getSkssjsrq() {
        return skssjsrq;
    }

    /**
     * ���������Դ
     */
    public String getSjly() {
        return sjly;
    }

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ��ô���ʱ��
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * ������ش���
     */
    public String getQxdm() {
        return qxdm;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getLsgxmc() {
        return lsgxmc;
    }

    public String getSklxmc() {
        return sklxmc;
    }

    public String getDjzclxmc() {
        return djzclxmc;
    }

    public String getGjbzhymc() {
        return gjbzhymc;
    }

    public String getGkzzjgmc() {
        return gkzzjgmc;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getZsswjgzzjgmc() {
        return zsswjgzzjgmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public int getKssl() {
        return kssl;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public String getGkjhh() {
        return gkjhh;
    }

    /**
     * ��ֵ�ɿ�ƾ֤��
     * @param jkpzh �ɿ�ƾ֤��
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * ��ֵ˰�����ʹ���
     * @param sklxdm ˰�����ʹ���
     */
    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    /**
     * ��ֵ���������
     * @param jsjdm ���������
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * ��ֵ�Ǽ��걨��ʽ����
     * @param fsdm �Ǽ��걨��ʽ����
     */
    public void setFsdm(String fsdm) {
        this.fsdm = fsdm;
    }

    /**
     * ��ֵ������ϵ����
     * @param lsgxdm ������ϵ����
     */
    public void setLsgxdm(String lsgxdm) {
        this.lsgxdm = lsgxdm;
    }

    /**
     * ��ֵ���д���
     * @param yhdm ���д���
     */
    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    /**
     * ��ֵ��������
     * @param yhmc ��������
     */
    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    /**
     * ��ֵ�ʺ�
     * @param zh �ʺ�
     */
    public void setZh(String zh) {
        this.zh = zh;
    }

    /**
     * ��ֵ�Ǽ�ע�����ʹ���
     * @param djzclxdm �Ǽ�ע�����ʹ���
     */
    public void setDjzclxdm(String djzclxdm) {
        this.djzclxdm = djzclxdm;
    }

    /**
     * ��ֵ˰�������֯��������
     * @param swjgzzjgdm ˰�������֯��������
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * ��ֵ���ջ��ش���
     * @param zsswjgzzjgdm ���ջ��ش���
     */
    public void setZsswjgzzjgdm(String zsswjgzzjgdm) {
        this.zsswjgzzjgdm = zsswjgzzjgdm;
    }

    /**
     * ��ֵ���ұ�׼��ҵ����
     * @param gjbzhydm ���ұ�׼��ҵ����
     */
    public void setGjbzhydm(String gjbzhydm) {
        this.gjbzhydm = gjbzhydm;
    }

    /**
     * ��ֵ������֯��������
     * @param gkzzjgdm ������֯��������
     */
    public void setGkzzjgdm(String gkzzjgdm) {
        this.gkzzjgdm = gkzzjgdm;
    }

    /**
     * ��ֵԤ���Ŀ����
     * @param yskmdm Ԥ���Ŀ����
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * ��ֵԤ�㼶��
     * @param ysjcdm Ԥ�㼶��
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * ��ֵ˰�ִ���
     * @param szdm ˰�ִ���
     */
    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    /**
     * ��ֵ¼��ʱ��
     * @param lrrq ¼��ʱ��
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * ��ֵ�걨����
     * @param sbrq �걨����
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * ��ֵ�����տ�ʱ��
     * @param jksj �����տ�ʱ��
     */
    public void setJksj(Timestamp jksj) {
        this.jksj = jksj;
    }

    /**
     * ��ֵ�޽�����
     * @param xjrq �޽�����
     */
    public void setXjrq(Timestamp xjrq) {
        this.xjrq = xjrq;
    }

    /**
     * ��ֵ�����Ǵ���
     * @param clbjdm �����Ǵ���
     */
    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    /**
     * ��ֵʵ�ɽ��
     * @param sjje ʵ�ɽ��
     */
    public void setSjje(BigDecimal sjje) {
        this.sjje = sjje;
    }

    /**
     * ��ֵ��ҳ����
     * @param zyrq ��ҳ����
     */
    public void setZyrq(Timestamp zyrq) {
        this.zyrq = zyrq;
    }

    /**
     * ��ֵ�����
     * @param rkje �����
     */
    public void setRkje(BigDecimal rkje) {
        this.rkje = rkje;
    }

    /**
     * ��ֵ�����ʶ
     * @param zwbs �����ʶ
     */
    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

    /**
     * ��ֵ�����˴���
     * @param hxrdm �����˴���
     */
    public void setHxrdm(String hxrdm) {
        this.hxrdm = hxrdm;
    }

    /**
     * ��ֵ����������
     * @param hxrmc ����������
     */
    public void setHxrmc(String hxrmc) {
        this.hxrmc = hxrmc;
    }

    /**
     * ��ֵ¼���˴���
     * @param lrr ¼���˴���
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ��ֵ��������
     * @param hxrq ��������
     */
    public void setHxrq(Timestamp hxrq) {
        this.hxrq = hxrq;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ��Ӫ��ַ��ϵ�绰
     * @param jydzlxdm ��Ӫ��ַ��ϵ�绰
     */
    public void setJydzlxdm(String jydzlxdm) {
        this.jydzlxdm = jydzlxdm;
    }

    /**
     * ��ֵ˰��������ʼ����
     * @param skssksrq ˰��������ʼ����
     */
    public void setSkssksrq(Timestamp skssksrq) {
        this.skssksrq = skssksrq;
    }

    /**
     * ��ֵ˰��������������
     * @param skssjsrq ˰��������������
     */
    public void setSkssjsrq(Timestamp skssjsrq) {
        this.skssjsrq = skssjsrq;
    }

    /**
     * ��ֵ������Դ
     * @param sjly ������Դ
     */
    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * ��ֵ����ʱ��
     * @param cjrq ����ʱ��
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * ��ֵ���ش���
     * @param qxdm ���ش���
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setLsgxmc(String lsgxmc) {
        this.lsgxmc = lsgxmc;
    }

    public void setSklxmc(String sklxmc) {
        this.sklxmc = sklxmc;
    }

    public void setDjzclxmc(String djzclxmc) {
        this.djzclxmc = djzclxmc;
    }

    public void setGjbzhymc(String gjbzhymc) {
        this.gjbzhymc = gjbzhymc;
    }

    public void setGkzzjgmc(String gkzzjgmc) {
        this.gkzzjgmc = gkzzjgmc;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setZsswjgzzjgmc(String zsswjgzzjgmc) {
        this.zsswjgzzjgmc = zsswjgzzjgmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setKssl(int kssl) {
        this.kssl = kssl;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setGkjhh(String gkjhh) {
        this.gkjhh = gkjhh;
    }


}
