/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ�ֱ�����˰�걨ģ�� Ԥ���Ŀ��Ϣ</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Yskm
    implements Serializable
{
    public Yskm (String yskmdm, String yskmmc)
    {
        this.yskmdm = yskmdm;
        this.yskmmc = yskmmc;
    }

    public Yskm ()
    {
    }

    /**
     * Ԥ���Ŀ����
     */
    public String yskmdm;

    /**
     * Ԥ���Ŀ����
     */
    private String yskmmc;

    /**
     * �оֳַɱ���
     */
    private BigDecimal sjfcbl;

    /**
     * ���طֳɱ���
     */
    private BigDecimal qxfcbl;

    /**
     * ȱʡ�ֳɱ���
     */
    private BigDecimal defaultBl = new BigDecimal(0);

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public BigDecimal getQxfcbl ()
    {
        return (qxfcbl == null ? defaultBl : qxfcbl);
    }

    public void setQxfcbl (BigDecimal qxfcbl)
    {
        this.qxfcbl = qxfcbl;
    }

    public BigDecimal getSjfcbl ()
    {
        return (sjfcbl == null ? defaultBl : sjfcbl);
    }

    public void setSjfcbl (BigDecimal sjfcbl)
    {
        this.sjfcbl = sjfcbl;
    }
}