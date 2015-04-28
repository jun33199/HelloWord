package com.ttsoft.bjtax.smsb.jkscx.web;

import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JksInfo
   implements Serializable
{


   /**
    * 查询结果序号
    */
   private int index;

   /**
    * 税种税目代码	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
    */
   String szsmdm;
   /**
    * 缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
    */

   String jkpzh;

   /**
    * 税票号
    */
   String sphm;
   /**
    * 计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
    */

   String jsjdm;
   /**
    * 预算科目代码	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
    */

   String yskmdm;
   /**
    * 预算科目名称	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    */

   String yskmmc;
   /**
    * 预算级次代码	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
    */

   String ysjcdm;
   /**
    * 预算级次名称	YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    */

   String ysjcmc;
   /**
    * 税种税目名称	SZSMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    */

   String szsmmc;
   /**
    * 课税数量	KSSL	NUMBER(15,2)	FALSE	FALSE	FALSE
    */

   String kssl;
   /**
    * 计税金额	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
    */

   String jsje;
   /**
    * 实缴税额	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
    */

   String sjje;

   String zbsjje;
   /**
    * 税款所属开始日期	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
    */

   String skssksrq;
   /**
    * 税款所属结束日期	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
    */

   String skssjsrq;

   String sbrq;

   String sklx;

   String sbfs;

   String yh;

   String djzclx;

   String zgswjg;

   String zsswjg;

   String gjbzhy;

   String nsrmc;

   String rkbs;


   /**
    * 税种代码        SZDM    VARCHAR2(6)
    */

   String szdm;
   /**
    * 税种名称        SZMC    VARCHAR2(6)
    */

   String szmc;

    public JksInfo()
    {
    }
    public int getIndex()
    {
        return index;
    }
    public String getJkpzh()
    {
        return jkpzh;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getSzdm()
    {
        return szdm;
    }
    public String getSzmc()
    {
        return szmc;
    }
    public String getSzsmdm()
    {
        return szsmdm;
    }
    public String getSzsmmc()
    {
        return szsmmc;
    }
    public String getYsjcdm()
    {
        return ysjcdm;
    }
    public String getYsjcmc()
    {
        return ysjcmc;
    }
    public String getYskmdm()
    {
        return yskmdm;
    }
    public String getYskmmc()
    {
        return yskmmc;
    }
    public void setYskmmc(String yskmmc)
    {
        this.yskmmc = yskmmc;
    }
    public void setYskmdm(String yskmdm)
    {
        this.yskmdm = yskmdm;
    }
    public void setYsjcmc(String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }
    public void setYsjcdm(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }
    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }
    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }
    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setJkpzh(String jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }
    public String getJsje()
    {
        return jsje;
    }
    public String getKssl()
    {
        return kssl;
    }
    public String getSjje()
    {
        return sjje;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public void setJsje(String jsje)
    {
        this.jsje = jsje;
    }
    public void setKssl(String kssl)
    {
        this.kssl = kssl;
    }
    public void setSjje(String sjje)
    {
        this.sjje = sjje;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getZbsjje()
    {
        return zbsjje;
    }
    public void setZbsjje(String zbsjje)
    {
        this.zbsjje = zbsjje;
    }
    public String getDjzclx()
    {
        return djzclx;
    }
    public String getGjbzhy()
    {
        return gjbzhy;
    }
    public void setDjzclx(String djzclx)
    {
        this.djzclx = djzclx;
    }
    public void setGjbzhy(String gjbzhy)
    {
        this.gjbzhy = gjbzhy;
    }
    public String getNsrmc()
    {
        return nsrmc;
    }
    public String getRkbs()
    {
        return rkbs;
    }
    public String getSbfs()
    {
        return sbfs;
    }
    public String getSbrq()
    {
        return sbrq;
    }
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
    public void setRkbs(String rkbs)
    {
        this.rkbs = rkbs;
    }
    public void setSbfs(String sbfs)
    {
        this.sbfs = sbfs;
    }
    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }
    public String getSklx()
    {
        return sklx;
    }
    public void setSklx(String sklx)
    {
        this.sklx = sklx;
    }
    public String getYh()
    {
        return yh;
    }
    public void setYh(String yh)
    {
        this.yh = yh;
    }
    public String getZgswjg()
    {
        return zgswjg;
    }
    public String getZsswjg()
    {
        return zsswjg;
    }

    public String getSphm() {
        return sphm;
    }

    public void setZgswjg(String zgswjg)
    {
        this.zgswjg = zgswjg;
    }
    public void setZsswjg(String zsswjg)
    {
        this.zsswjg = zsswjg;
    }

    public void setSphm(String sphm) {
        this.sphm = sphm;
    }

}
