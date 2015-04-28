package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class JkInfor implements Serializable
{
    public JkInfor()
    {
    }

    String sklxdm;  // 税款类型代码
    String sklxmc;  // 税款类型名称
    String szsmdm;  // 税种税目代码
    String szsmmc;  // 税种税目名称
    BigDecimal sjse;  // 实缴税额

    public BigDecimal getSjse()  {
        return sjse;
    }
    public void setSjse(BigDecimal sjse)   {
        this.sjse = sjse;
    }
    public String getSklxdm()  {
        return sklxdm;
    }
    public void setSklxdm(String sklxdm)   {
        this.sklxdm = sklxdm;
    }
    public String getSklxmc()  {
        return sklxmc;
    }
    public void setSklxmc(String sklxmc)  {
        this.sklxmc = sklxmc;
    }
    public String getSzsmdm()  {
        return szsmdm;
    }
    public void setSzsmdm(String szsmdm)  {
        this.szsmdm = szsmdm;
    }
    public String getSzsmmc()  {
        return szsmmc;
    }
    public void setSzsmmc(String szsmmc)  {
        this.szsmmc = szsmmc;
    }
}