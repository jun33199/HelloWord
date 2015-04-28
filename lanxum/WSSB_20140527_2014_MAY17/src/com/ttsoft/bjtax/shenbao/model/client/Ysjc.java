package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:实现北京地税申报模块 预算级次信息 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */

public class Ysjc implements Serializable
{
    //预算级次代码和名称
    public final static String YSJCDM_ZY = "10";
//    public final static String YSJCMC_ZY = "中央级";
    public final static String YSJCDM_DF = "21";
//    public final static String YSJCMC_DF = "地方级";
    public final static String YSJCDM_XZ = "23";
//    public final static String YSJCMC_XZ = "乡镇级";

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

    //级次代码
    private String ysjcdm;
    //级次名称
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