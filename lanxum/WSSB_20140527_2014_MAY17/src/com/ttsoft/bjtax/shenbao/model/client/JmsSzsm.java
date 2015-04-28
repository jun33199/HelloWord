package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

public class JmsSzsm implements Serializable
{
    // 税种税目代码
    private String szsmdm;
    // 税种税目代码
    private String szsmmc;
    // 是否未最后一层
    private boolean bottom;
    // 是否按数量记
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
