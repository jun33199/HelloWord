package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

public class JmsSzsm implements Serializable
{
    // ˰��˰Ŀ����
    private String szsmdm;
    // ˰��˰Ŀ����
    private String szsmmc;
    // �Ƿ�δ���һ��
    private boolean bottom;
    // �Ƿ�������
    private boolean aslj;

    public String getSzsmdm()
    {
        return szsmdm;
    }
    public String getSzsmmc()
    {
        return szsmmc;
    }
    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }
    public boolean isBottom()
    {
        return bottom;
    }
    public void setBottom(boolean bottom)
    {
        this.bottom = bottom;
    }
    public boolean isAslj()
    {
        return aslj;
    }
    public void setAslj(boolean aslj)
    {
        this.aslj = aslj;
    }
}
