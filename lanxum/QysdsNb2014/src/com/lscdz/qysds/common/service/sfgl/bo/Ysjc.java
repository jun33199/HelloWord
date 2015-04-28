/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.lscdz.qysds.common.service.sfgl.bo;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现北京地税申报模块 预算级次信息</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Ysjc
    implements Serializable
{
    /**
     * 预算级次代码和名称
     */
    public final static String YSJCDM_ZY = "10";

    /**
     * 预算级次代码和名称
     */

    public final static String YSJCMC_ZY = "中央级";

    /**
     * 预算级次代码和名称
     */

    public final static String YSJCDM_DF = "21";

    /**
     * 预算级次代码和名称
     */

    public final static String YSJCMC_DF = "地方级";

    /**
     * 预算级次代码和名称
     */

    public final static String YSJCDM_XZ = "23";

    /**
     * 预算级次代码和名称
     */

    public final static String YSJCMC_XZ = "乡镇级";

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
     * 级次代码
     */
    private String ysjcdm;

    /**
     * 级次名称
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