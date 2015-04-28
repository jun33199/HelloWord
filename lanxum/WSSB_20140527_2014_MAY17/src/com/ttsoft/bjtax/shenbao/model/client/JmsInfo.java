package com.ttsoft.bjtax.shenbao.model.client;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.io.Serializable;

public class JmsInfo implements Serializable
{
    // 税种税目代码
    private String szsmdm;
    // 税种代码
    private String szdm;
    // 课税数量
    private BigDecimal kssl;
    // 计税金额
    private BigDecimal jsje;
    // 减免税额
    private BigDecimal jmse;
    // 是否可审批
    private boolean canShenpi;
    // 创建时间
    private Timestamp cjsj;
    // 减免类型代码
    private String jmlxdm;
    // 减免类型名称
    private String jmlxmc;
    // 是否按数量记
    private boolean aslj;
    // 减免项目及代码 减免税项目  tujb 200404
    private String jmxmjdm;
    // 减免优惠开始日期 减免税项目  tujb 200404
    private String jmxmksrq;
    // 减免优惠结束日期 减免税项目  tujb 200404
    private String jmxmjsrq;
    // 税款所属开始日期 减免税项目  tujb 200404
    private String skssksrq;
    // 税款所属结束日期 减免税项目  tujb 200404
    private String skssjsrq;
   
	public JmsInfo()
    {
    }
    public boolean canShenpi()
    {
        return canShenpi;
    }
    public void setcanShenpi(boolean canShenpi)
    {
        this.canShenpi = canShenpi;
    }
    public BigDecimal getJmse()
    {
        return jmse;
    }
    public void setJmse(BigDecimal jmse)
    {
        this.jmse = jmse;
    }
    public BigDecimal getJsje()
    {
        return jsje;
    }
    public void setJsje(BigDecimal jsje)
    {
        this.jsje = jsje;
    }
    public BigDecimal getKssl()
    {
        return kssl;
    }
    public void setKssl(BigDecimal kssl)
    {
        this.kssl = kssl;
    }
    public String getSzsmdm()
    {
        return szsmdm;
    }
    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public Timestamp getCjsj()
    {
        return cjsj;
    }
    public void setCjsj(Timestamp cjsj)
    {
        this.cjsj = cjsj;
    }
    public String getJmlxdm()
    {
        return jmlxdm;
    }
    public void setJmlxdm(String jmlxdm)
    {
        this.jmlxdm = jmlxdm;
    }
    public String getSzdm()
    {
        return szdm;
    }
    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }
    public boolean isAslj()
    {
        return aslj;
    }
    public void setAslj(boolean aslj)
    {
        this.aslj = aslj;
    }
    public String getJmlxmc()
    {
        return jmlxmc;
    }
    public void setJmlxmc(String jmlxmc)
    {
        this.jmlxmc = jmlxmc;
    }
	public String getJmxmjdm() {
		return jmxmjdm;
	}
	public void setJmxmjdm(String jmxmjdm) {
		this.jmxmjdm = jmxmjdm;
	}
	public String getJmxmksrq() {
		return jmxmksrq;
	}
	public void setJmxmksrq(String jmxmksrq) {
		this.jmxmksrq = jmxmksrq;
	}
	public String getJmxmjsrq() {
		return jmxmjsrq;
	}
	public void setJmxmjsrq(String jmxmjsrq) {
		this.jmxmjsrq = jmxmjsrq;
	}

    public String getSkssksrq() {
		return skssksrq;
	}
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	public String getSkssjsrq() {
		return skssjsrq;
	}
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

}