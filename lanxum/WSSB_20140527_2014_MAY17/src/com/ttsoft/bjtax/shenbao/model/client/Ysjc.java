package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description:ʵ�ֱ�����˰�걨ģ�� Ԥ�㼶����Ϣ </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */

public class Ysjc implements Serializable
{
    //Ԥ�㼶�δ��������
    public final static String YSJCDM_ZY = "10";
//    public final static String YSJCMC_ZY = "���뼶";
    public final static String YSJCDM_DF = "21";
//    public final static String YSJCMC_DF = "�ط���";
    public final static String YSJCDM_XZ = "23";
//    public final static String YSJCMC_XZ = "����";

    public Ysjc()
    {
    }

    public Ysjc(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
//        this.ysjcmc = ysjcmc;
    }

    public Ysjc(String ysjcdm, String ysjcmc)
    {
        this.ysjcdm = ysjcdm;
        this.ysjcmc = ysjcmc;
    }

    public static Ysjc getYsjc(String ysjcdm)
    {
        Ysjc ysjc = null;
        if(ysjcdm.equals(Ysjc.YSJCDM_ZY))
        {
            ysjc = new Ysjc(YSJCDM_ZY);
        }

        if(ysjcdm.equals(Ysjc.YSJCDM_DF))
        {
            ysjc = new Ysjc(YSJCDM_DF);
        }
        if(ysjcdm.equals(Ysjc.YSJCDM_XZ))
        {
            ysjc = new Ysjc(YSJCDM_XZ);
        }
        return ysjc;
    }

    //���δ���
    private String ysjcdm;
    //��������
    private String ysjcmc;
    public String getYsjcdm()
    {
        return ysjcdm;
    }

    public String getYsjcmc()
    {
        return ysjcmc;
    }

    public void setYsjcdm(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public void setYsjcmc(String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }
}