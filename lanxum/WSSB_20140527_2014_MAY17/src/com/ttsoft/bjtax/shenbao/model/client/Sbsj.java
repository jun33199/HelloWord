package com.ttsoft.bjtax.shenbao.model.client;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ����ģ������걨ģ��Ľɿ��������ɵ�������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Stone
 * @version 1.0 2003-8-28
 */

public class Sbsj implements Serializable
{
    public Sbsj()
    {
    }

    //˰��˰Ŀ���룻
    String szsmdm;
    //˰��˰Ŀ����
    String szsmmc;
    //���������
    String jsjdm;
    //��˰������
    String nsrmc;
    //���
    BigDecimal je;
    //˰�����ʹ���
    String sklxdm;
    //˰����������
    String sklxmc;
    //���ջ��ش���
    String zsjgdm;
    //���ջ�������
    String zsjgmc;
    //�޽�����
    Timestamp xjrq;
    //˰�ִ���
    String szdm;
    //˰������
    String szmc;
    //¼����
    String lrr;
    //˰��������ʼ����
    Timestamp skssksrq;
    //˰��������������
    Timestamp skssjsrq;
    //������Դ
    String sjly;
    //��ע
    String bz;

    //add by wanghw :(
    //Ԥ�㼶�δ���
    String ysjcdm;
    //���ұ�׼��ҵ
    String gjbzhydm;
    //�Ǽ�ע�����ʹ���
    String djzclxdm;
    //������ϵ����
    String lsgxdm;

    public BigDecimal getJe()
    {
        return je;
    }

    public void setJe(BigDecimal je)
    {
        this.je = je;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getSklxdm()
    {
        return sklxdm;
    }

    public String getLrr()
    {
        return lrr;
    }

    public String getSklxmc()
    {
        return sklxmc;
    }

    public String getSzdm()
    {
        return szdm;
    }

    public String getSzmc()
    {
        return szmc;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public String getSzsmmc()
    {
        return szsmmc;
    }

    public Timestamp getXjrq()
    {
        return xjrq;
    }

    public String getZsjgdm()
    {
        return zsjgdm;
    }

    public String getZsjgmc()
    {
        return zsjgmc;
    }

    public void setZsjgmc(String zsjgmc)
    {
        this.zsjgmc = zsjgmc;
    }

    public void setZsjgdm(String zsjgdm)
    {
        this.zsjgdm = zsjgdm;
    }

    public void setXjrq(Timestamp xjrq)
    {
        this.xjrq = xjrq;
    }

    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }

    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }

    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }

    public void setSklxmc(String sklxmc)
    {
        this.sklxmc = sklxmc;
    }

    public void setSklxdm(String sklxdm)
    {
        this.sklxdm = sklxdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSjly()
    {
        return sjly;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public void setSjly(String sjly)
    {
        this.sjly = sjly;
    }

    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getBz()
    {
        return bz;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public String getYsjcdm()
    {
        return ysjcdm;
    }

    public void setYsjcdm(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public String getDjzclxdm()
    {
        return djzclxdm;
    }

    public String getGjbzhydm()
    {
        return gjbzhydm;
    }

    public void setDjzclxdm(String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public void setGjbzhydm(String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
    }

    public String getLsgxdm()
    {
        return lsgxdm;
    }

    public void setLsgxdm(String lsgxdm)
    {
        this.lsgxdm = lsgxdm;
    }
}