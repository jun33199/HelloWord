package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *����������Ϣ��
 */
public class Drpcinfo implements Serializable {
    //�������κ�
    public String drpch;
    //�������
    public BigDecimal drbs;
    //�����ṩ������
    public String tgzlx;
    //�����ṩ������
    public String tgzmc;
    //�����ṩ�߹�������
    public String tgzgjdm;
    //�����ṩ�߹�������
    public String tgzgjmc;
    //�����ṩ�����֤������
    public String tgzsfzjlx;
    //�����ṩ�����֤������
    public String tgzsfzjhm;
    //�����ṩ�߼��������
    public String tgzjsjdm;
    //�ύʱ��
    public java.sql.Timestamp tjsj;
    //����ʱ��
    public java.sql.Timestamp drsj;
    //��˰��ʽ����
    public String jsfsdm;
    //��˰��ʽ����
    public String jsfmc;

    public BigDecimal getDrbs() {
        return drbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public Timestamp getDrsj() {
        return drsj;
    }

    public String getTgzgjdm() {
        return tgzgjdm;
    }

    public String getTgzgjmc() {
        return tgzgjmc;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getTgzlx() {
        return tgzlx;
    }

    public String getTgzmc() {
        return tgzmc;
    }

    public String getTgzsfzjhm() {
        return tgzsfzjhm;
    }

    public String getTgzsfzjlx() {
        return tgzsfzjlx;
    }

    public Timestamp getTjsj() {
        return tjsj;
    }

    public String getJsfmc() {
        return jsfmc;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public void setDrbs(BigDecimal drbs) {
        this.drbs = drbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setDrsj(Timestamp drsj) {
        this.drsj = drsj;
    }

    public void setTgzgjdm(String tgzgjdm) {
        this.tgzgjdm = tgzgjdm;
    }

    public void setTgzgjmc(String tgzgjmc) {
        this.tgzgjmc = tgzgjmc;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setTgzlx(String tgzlx) {
        this.tgzlx = tgzlx;
    }

    public void setTgzmc(String tgzmc) {
        this.tgzmc = tgzmc;
    }

    public void setTgzsfzjhm(String tgzsfzjhm) {
        this.tgzsfzjhm = tgzsfzjhm;
    }

    public void setTgzsfzjlx(String tgzsfzjlx) {
        this.tgzsfzjlx = tgzsfzjlx;
    }

    public void setTjsj(Timestamp tjsj) {
        this.tjsj = tjsj;
    }

    public void setJsfmc(String jsfmc) {
        this.jsfmc = jsfmc;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }
}
