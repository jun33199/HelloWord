package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: ��Ϊ��ѯ�걨���ݵ�һ��ҵ�����</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JmsbblBo implements Serializable {
    /**
     * JmsbblBo��Ϊ��ѯ����ʱ��ָ�����Ǹ���ѯҳ�����
     * 0�������걨��ѯ
     */
    private int fromPage;

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ����ǰ��������˰�˵ļ��������
     */
    private String jsjdm;

    /**
     * ����ǰ��������˰�˵�����
     */
    private String nsrmc;

    /**
     * ����ǰ��������˰�˵���ϵ�绰
     */
    private String lxdh;

    /**
     * ����ǰ��������˰�˵�����
     * ���һ��ء���ҵ��λ������Ŷӡ����µ�λ������������
     */
    private String nsrlxdm;

    /**
     * �����걨���
     */
//    private String jmsbbh;

    /**
     * ��˰֤��
     */
    private String wszh;

    /**
     * �ɿ����
     */
    private String jksh;

    /**
     * ���֤������
     */
    private String sfzjlx;

    /**
     * ���˺Ϸ����֤������
     */
    private String sfzjhm;

    /**
     * ���ز���Ŀ����
     */
    private String fdcxmmc;


    /**
     * ���ء����������ַ
     */
    private String tdfwzldz;

    /**
     * �ɽ��۸������
     */
    private BigDecimal cjjgrmb;

    /**
     * ��ע
     */
    private String bz;

    /**
     * �û���ʶ 0������ˣ�1����Ǹ���
     */
    private String yhbs;

    /**
     * ״̬��ʶ
     */
    private String ztbs;

    /**
     * ����˰��ʶ
     */
    private String jmsbs;

    /**
     * �ٵ�״̬��ʶ,��ѯ������,��������տ��ѯҳ��ʹ��
     */
    private String fztbs;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * �������κ�
     */
    private String drpch;


    /**
     * �������ߴ���
     */
    private java.lang.String jmzcdm;
    /**
     * �������ʹ���
     */
    private java.lang.String jmlxdm;

    /**
     * �˶�֪ͨ���
     */
    private String hdtzsh;

    public String getFdcxmmc() {
        return fdcxmmc;
    }


//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    public String getJsjdm() {
        return jsjdm;
    }


    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }


    public String getJksh() {
        return jksh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getSfzjlx() {
        return sfzjlx;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public String getBz() {
        return bz;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getYhbs() {
        return yhbs;
    }

    public String getNsrlxdm() {
        return nsrlxdm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getJmsbs() {
        return jmsbs;
    }

    public String getSbrq() {
        return sbrq;
    }

    public int getFromPage() {
        return fromPage;
    }

    public String getFztbs() {
        return fztbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }


//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }


    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }


    public void setJksh(String jksh) {
        this.jksh = jksh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setSfzjlx(String sfzjlx) {
        this.sfzjlx = sfzjlx;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setJmsbs(String jmsbs) {
        this.jmsbs = jmsbs;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public void setFztbs(String fztbs) {
        this.fztbs = fztbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    /**
     * ���ص�ǰ���bo����˰�������Ƿ�Ϊ����
     * @param nsrlxdm String
     * @return boolean
     */
    public boolean ifPersonal() {
        Debug.out("bo.nsrlxdm: " + this.nsrlxdm);
        if (this.nsrlxdm.equals("99")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @return jmlxdm
     */
    public java.lang.String getJmlxdm() {
        return jmlxdm;
    }


    /**
     * @param jmlxdm Ҫ���õ� jmlxdm
     */
    public void setJmlxdm(java.lang.String jmlxdm) {
        this.jmlxdm = jmlxdm;
    }


    /**
     * @return jmzcdm
     */
    public java.lang.String getJmzcdm() {
        return jmzcdm;
    }


    /**
     * @param jmzcdm Ҫ���õ� jmzcdm
     */
    public void setJmzcdm(java.lang.String jmzcdm) {
        this.jmzcdm = jmzcdm;
    }


    public String getHdtzsh() {
        return hdtzsh;
    }


    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }
}
