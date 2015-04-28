package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo;


/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 汇总纳税分支机构分配表BO</p>
 *
 * <p>Description: 用于ejb交互的数据对象</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class ZfjgqysdsjbBO implements Serializable
{
    /**
     * 总机构计算机代码
     */
    private String jsjdm = "";

    /**
     * 总机构纳税人识别号
     */
    private String nsrsbh = "";

    /**
     * 总机构纳税人名称
     */
    private String nsrmc = "";

    /**
     * 申报日期
     */
    private Timestamp sbrq;

    /**
     * 页面展示的申报日期
     */
    private String sbrqshow = "";

    /**
     * 税款所属开始日期
     */
    private Timestamp skssksrq;

    /**
     * 税款所属结束日期
     */
    private Timestamp skssjsrq;

    /**
     * 季度
     */
    private String jd = "";

    /**
     * 所属年度
     */
    private String nd = "";

    /**
     * 申报信息
     */
    private HashMap sbsj = new HashMap();

    /**
     * 方式代码
     */
    private String fsdm = "";

    /**
     * 分配比例有效期起
     */
    private String fpblyxqq = "";

    /**
     * 分配比例有效期止
     */
    private String fpblyxqz = "";

    /**
     * 报表期类型
     */
    private String bbqlx = "";

    /**
     * 分摊税额
     */
    private String ftse = "";

	public ZfjgqysdsjbBO()
    {
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getNsrsbh()
    {
        return nsrsbh;
    }

    public Timestamp getSbrq()
    {
        return sbrq;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }

    public String getSbrqshow()
    {
        return sbrqshow;
    }

    public String getJd()
    {
        return jd;
    }

    public String getNd()
    {
        return nd;
    }

    public HashMap getSbsj()
    {
        return sbsj;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getBbqlx()
    {
        return bbqlx;
    }

    public String getFtse()
    {
        return ftse;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setSbrq(Timestamp sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSbrqshow(String sbrqshow)
    {
        this.sbrqshow = sbrqshow;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public void setSbsj(HashMap sbsj)
    {
        this.sbsj = sbsj;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setBbqlx(String bbqlx)
    {
        this.bbqlx = bbqlx;
    }

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
    }

}
