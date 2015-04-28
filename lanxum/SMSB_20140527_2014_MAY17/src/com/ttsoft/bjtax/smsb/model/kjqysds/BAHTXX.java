package com.ttsoft.bjtax.smsb.model.kjqysds;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.Serializable;

public class BAHTXX implements Serializable
{
    /**
     * 合同编号
     */
    private String htbh;

    /**
     * 合同名称
     */
    private String htmc;

    /**
     * 签约日期
     */
    private String qyrq;

    /**
     * 合同执行起始日期
     */
    private String htzxqsrq;

    /**
     * 合同执行终止日期
     */
    private String htzxzzrq;

    /**
     * 合同有效期
     */
    private String htyxq;

    /**
     * 合同金额
     */
    private String htje;

    /**
     * 币种
     */
    private String bz;
    
    /**
     * 币种名称
     */
    private String bzmc;

    /**
     * 支付项目
     */
    private String zfxm;

    /**
     * 付款次数
     */
    private String fkcs;

    /**
     * 其它资料名称
     */
    private String qtzlmc;

    public BAHTXX()
    {
    }

    public String getBz()
    {
        return bz;
    }

    public String getFkcs()
    {
        return fkcs;
    }

    public String getHtbh()
    {
        return htbh;
    }

    public String getHtje()
    {
        return htje;
    }

    public String getHtyxq()
    {
        return htyxq;
    }

    public String getHtzxqsrq()
    {
        return htzxqsrq;
    }

    public String getHtzxzzrq()
    {
        return htzxzzrq;
    }

    public String getQtzlmc()
    {
    	if(qtzlmc == null || "".equals(qtzlmc)){
    		qtzlmc = "无";
    	}
        return qtzlmc;
    }

    public String getQyrq()
    {
        return qyrq;
    }

    public String getZfxm()
    {
        return zfxm;
    }

    public String getHtmc()
    {
        return htmc;
    }

    public void setZfxm(String zfxm)
    {
        this.zfxm = zfxm;
    }

    public void setQtzlmc(String qtzlmc)
    {
        this.qtzlmc = qtzlmc;
    }

    public void setQyrq(String qyrq)
    {
        this.qyrq = qyrq;
    }

    public void setHtzxzzrq(String htzxzzrq)
    {
        this.htzxzzrq = htzxzzrq;
    }

    public void setHtzxqsrq(String htzxqsrq)
    {
        this.htzxqsrq = htzxqsrq;
    }

    public void setHtyxq(String htyxq)
    {
        this.htyxq = htyxq;
    }

    public void setHtje(String htje)
    {
        this.htje = htje;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

    public void setFkcs(String fkcs)
    {
        this.fkcs = fkcs;
    }

    public void setBz(String bz)
    {
        this.bz = bz;
    }

    public void setHtmc(String htmc)
    {
        this.htmc = htmc;
    }

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

}
