package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class JkInfor implements Serializable
{
    public JkInfor()
    {
    }

    String sklxdm;  // ˰�����ʹ���
    String sklxmc;  // ˰����������
    String szsmdm;  // ˰��˰Ŀ����
    String szsmmc;  // ˰��˰Ŀ����
    BigDecimal sjse;  // ʵ��˰��

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