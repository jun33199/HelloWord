/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 在原Sbjkmx值对象的基础上添加前台显示需要使用的属性</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SbjkmxDis
    implements Serializable
{
//

    /**
     * 税种税目代码	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
     */
    String szsmdm;
    /**
     * 缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
     */

    String jkpzh;
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

    BigDecimal kssl;
    /**
     * 计税金额	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
     */

    BigDecimal jsje;
    /**
     * 实缴税额	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
     */

    BigDecimal sjse;
    /**
     * 税款所属开始日期	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
     */

    Timestamp skssksrq;
    /**
     * 税款所属结束日期	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
     */

    Timestamp skssjsrq;
    /**
     * 税种代码        SZDM    VARCHAR2(6)     这不是表中的字段
     */

    String szdm;
    /**
     * 税种名称        SZMC    VARCHAR2(6)     这不是表中的字段
     */

    String szmc;
    /**
     * 征期类型代码
     * 这不是数据库中的字段,为了后台获取得到税款所属时期必须有这个信息
     */

    String zqlxdm;
    /**
     * 缴纳期限
     * 这不是数据库中的字段,为了后台获取得到税款所属时期必须有这个信息
     */

    String jnqx;
    /**
     * 入库金额	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
     */

    BigDecimal rkje;
    /**
     * 申报编号    SBBH	VARCHAR2(20)
     */

    String sbbh;
    /**
     * 市局级分成(保留4为小数)
     */

    BigDecimal sjfc;
    /**
     * 区县级分成(保留4为小数)
     */

    BigDecimal qjfc;
    /**
     * 是否附加税标示
     */

    private String sffjs;

    /**
     * 税率
     */
    private java.math.BigDecimal sl;

    /**
     * 来自税费接口
     */
    private boolean fromDqde;

    /**
     * 按数量计标示
     */
    private String asljbs;

    /**
     * 计数基数
     */
    private java.math.BigDecimal jsjs;

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public BigDecimal getJsje ()
    {
        return jsje;
    }

    public BigDecimal getKssl ()
    {
        return kssl;
    }

    public BigDecimal getSjse ()
    {
        return sjse;
    }

    public Timestamp getSkssjsrq ()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq ()
    {
        return skssksrq;
    }

    public String getSzdm ()
    {
        return szdm;
    }

    public String getSzmc ()
    {
        return szmc;
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public String getSzsmmc ()
    {
        return szsmmc;
    }

    public String getYsjcdm ()
    {
        return ysjcdm;
    }

    public String getYsjcmc ()
    {
        return ysjcmc;
    }

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public void setYsjcmc (String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }

    public void setYsjcdm (String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public void setSzsmmc (String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setSzmc (String szmc)
    {
        this.szmc = szmc;
    }

    public void setSzdm (String szdm)
    {
        this.szdm = szdm;
    }

    public void setSkssksrq (Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq (Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSjse (BigDecimal sjse)
    {
        this.sjse = sjse;
    }

    public void setKssl (BigDecimal kssl)
    {
        this.kssl = kssl;
    }

    public void setJsje (BigDecimal jsje)
    {
        this.jsje = jsje;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getZqlxdm ()
    {
        return zqlxdm;
    }

    public void setZqlxdm (String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public String getJnqx ()
    {
        return jnqx;
    }

    public void setJnqx (String jnqx)
    {
        this.jnqx = jnqx;
    }

    public BigDecimal getRkje ()
    {
        return rkje;
    }

    public void setRkje (BigDecimal rkje)
    {
        this.rkje = rkje;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

    public BigDecimal getQjfc ()
    {
        return qjfc;
    }

    public void setQjfc (BigDecimal qjfc)
    {
        this.qjfc = qjfc;
    }

    public BigDecimal getSjfc ()
    {
        return sjfc;
    }

    public void setSjfc (BigDecimal sjfc)
    {
        this.sjfc = sjfc;
    }

    private void writeObject (ObjectOutputStream oos)
        throws IOException
    {
        oos.defaultWriteObject();
    }

    private void readObject (ObjectInputStream ois)
        throws ClassNotFoundException,
        IOException
    {
        ois.defaultReadObject();
    }

    public String getSffjs ()
    {
        return sffjs;
    }

    public void setSffjs (String sffjs)
    {
        this.sffjs = sffjs;
    }

    public java.math.BigDecimal getSl ()
    {
        return sl;
    }

    public void setSl (java.math.BigDecimal sl)
    {
        this.sl = sl;
    }

    public boolean isFromDqde ()
    {
        return fromDqde;
    }

    public void setFromDqde (boolean fromDqde)
    {
        this.fromDqde = fromDqde;
    }

    public String getAsljbs ()
    {
        return asljbs;
    }

    public void setAsljbs (String asljbs)
    {
        this.asljbs = asljbs;
    }

    public java.math.BigDecimal getJsjs ()
    {
        return jsjs;
    }

    public void setJsjs (java.math.BigDecimal jsjs)
    {
        this.jsjs = jsjs;
    }
}