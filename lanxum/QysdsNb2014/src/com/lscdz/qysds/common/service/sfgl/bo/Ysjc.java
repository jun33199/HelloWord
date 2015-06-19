/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.lscdz.qysds.common.service.sfgl.bo;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ�ֱ�����˰�걨ģ�� Ԥ�㼶����Ϣ</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Ysjc
    implements Serializable
{
    /**
     * Ԥ�㼶�δ��������
     */
    public final static String YSJCDM_ZY = "10";

    /**
     * Ԥ�㼶�δ��������
     */

    public final static String YSJCMC_ZY = "���뼶";

    /**
     * Ԥ�㼶�δ��������
     */

    public final static String YSJCDM_DF = "21";

    /**
     * Ԥ�㼶�δ��������
     */

    public final static String YSJCMC_DF = "�ط���";

    /**
     * Ԥ�㼶�δ��������
     */

    public final static String YSJCDM_XZ = "23";

    /**
     * Ԥ�㼶�δ��������
     */

    public final static String YSJCMC_XZ = "����";

    public Ysjc ()
    {
    }

    public Ysjc (String ysjcdm, String ysjcmc)
    {
        this.ysjcdm = ysjcdm;
        this.ysjcmc = ysjcmc;
    }

    public static Ysjc getYsjc (String ysjcdm)
    {
        Ysjc ysjc = null;
        if (ysjcdm.equals(Ysjc.YSJCDM_ZY))
        {
            ysjc = new Ysjc(YSJCDM_ZY, YSJCMC_ZY);
        }

        if (ysjcdm.equals(Ysjc.YSJCDM_DF))
        {
            ysjc = new Ysjc(YSJCDM_DF, YSJCMC_DF);
        }
        if (ysjcdm.equals(Ysjc.YSJCDM_XZ))
        {
            ysjc = new Ysjc(YSJCDM_XZ, YSJCMC_XZ);
        }
        return ysjc;
    }

    /**
     * ���δ���
     */
    private String ysjcdm;

    /**
     * ��������
     */
    private String ysjcmc;

    public String getYsjcdm ()
    {
        return ysjcdm;
    }

    public String getYsjcmc ()
    {
        return ysjcmc;
    }

    public void setYsjcdm (String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public void setYsjcmc (String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }

}