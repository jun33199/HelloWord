package com.ttsoft.bjtax.shenbao.jiekou.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * �ṩ����˰������ѯ����ֵ��ֵ����
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class Nspg implements Serializable
{
    public Nspg()
    {

    }

    //�����ֶ�
    //�ɿ�ƾ֤��
    String jkpzh;
    //��ҳ���ڣ���������ڣ�
    Timestamp zyrq;
    //�����
    BigDecimal rkje;
    //�Ƿ������
    Boolean isYrk;

    public Boolean getIsYrk()
    {
        return isYrk;
    }
    public String getJkpzh()
    {
        return jkpzh;
    }
    public BigDecimal getRkje()
    {
        return rkje;
    }
    public Timestamp getZyrq()
    {
        return zyrq;
    }
    public void setIsYrk(Boolean isYrk)
    {
        this.isYrk = isYrk;
    }
    public void setJkpzh(String jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setRkje(BigDecimal rkje)
    {
        this.rkje = rkje;
    }
    public void setZyrq(Timestamp zyrq)
    {
        this.zyrq = zyrq;
    }
}