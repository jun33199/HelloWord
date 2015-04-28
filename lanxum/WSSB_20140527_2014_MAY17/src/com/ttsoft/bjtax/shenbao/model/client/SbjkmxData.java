package com.ttsoft.bjtax.shenbao.model.client;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * 税款缴纳明细前台值对象
 */
public class SbjkmxData implements Serializable
{
    public SbjkmxData()
    {
    }
    
    /**
     * 申报方式（按次1，按月0）
     */
    private String sbfs;

    /**
     * 税种税目代码
     */
    private String szsmdm;

    /**
     * 税种名称
     */
    private String szmc;

    /**
     * 税种税目名称
     */
    private String szsmmc;

    /**
     * 课税数量
     */
    private BigDecimal kssl;

    /**
     * 计税金额
     */
    private BigDecimal jsje;

    /**
     * 实缴税额
     */
    private BigDecimal sjse;

    /**
     * 税率
     */
    private BigDecimal sl;

    /**
     * 缴纳期限
     */
    private String coefficient = "1";

    /**
     * 按数量计标识
     */
    private String asljbs;

    /**
     * 是否按课税数量计
     */
    private boolean aksslj = false;

    /**
     * 数据是否可编辑
     */
    private boolean readOnly = false;

    /**
     * 是否是定期定额数据
     */
    private boolean fromDqde = false;

    /**
     * 是否是附加税
     */
    private boolean isFjs = false;

    /**
     * 从sbjkmxData生成sbjkmx
     * return mx;
     * @return Sbjkmx
     */
    public Sbjkmx getSbjkmx()
    {
        Sbjkmx mx = new Sbjkmx();
        mx.setSzsmdm(szsmdm);
        mx.setKssl(kssl);
        mx.setJsje(jsje);
        mx.setSjse(sjse);
        mx.setSl(this.sl);
        mx.setSbfs(sbfs);
//        mx.setJnqx(String.valueOf(this.jnqx));

        return mx;
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

    public void setReadOnly(boolean readOnly)
    {
        this.readOnly = readOnly;
    }

    public BigDecimal getSjse()
    {
        return sjse;
    }

    public void setSjse(BigDecimal sjse)
    {
        this.sjse = sjse;
    }

    public BigDecimal getSl()
    {
        return sl;
    }

    public void setSl(BigDecimal sl)
    {
        this.sl = sl;
    }

    public String getSzmc()
    {
        return szmc;
    }

    public void setSzmc(String szmc)
    {
        this.szmc = szmc;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public String getSzsmmc()
    {
        return szsmmc;
    }

    public void setSzsmmc(String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public boolean isReadOnly()
    {
        return readOnly;
    }

    public boolean isAksslj()
    {
        return aksslj;
    }

    public void setAksslj(boolean aksslj)
    {
        this.aksslj = aksslj;
    }
    public boolean isFromDqde()
    {
        return fromDqde;
    }
    public boolean isIsFjs()
    {
        return isFjs;
    }
    public void setFromDqde(boolean fromDqde)
    {
        this.fromDqde = fromDqde;
        this.readOnly = this.fromDqde || this.isFjs;
    }
    public void setIsFjs(boolean isFjs)
    {
        this.isFjs = isFjs;
        this.readOnly = this.fromDqde || this.isFjs;
    }
    public String getAsljbs()
    {
        return asljbs;
    }
    public void setAsljbs(String asljbs)
    {
        this.asljbs = asljbs;
        if (this.asljbs !=null && (this.asljbs.equals("2") ||
                                   this.asljbs.equals("4") ||
                                   this.asljbs.equals("5") ))  // 按课税数量计
        {
            this.aksslj = true;
        }
    }
    public String getCoefficient()
    {
        return coefficient;
    }
    public void setCoefficient(String coefficient)
    {
        this.coefficient = coefficient;
    }

	public String getSbfs() {
		return sbfs;
	}

	public void setSbfs(String sbfs) {
		this.sbfs = sbfs;
	}
}