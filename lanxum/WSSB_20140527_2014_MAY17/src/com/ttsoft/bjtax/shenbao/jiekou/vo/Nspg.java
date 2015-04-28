package com.ttsoft.bjtax.shenbao.jiekou.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * 提供给纳税评估查询返回值的值对象
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
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

    //定义字段
    //缴款凭证号
    String jkpzh;
    //帐页日期（即入库日期）
    Timestamp zyrq;
    //入库金额
    BigDecimal rkje;
    //是否已入库
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