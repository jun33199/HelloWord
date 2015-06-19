package com.ttsoft.bjtax.shenbao.model.client;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * ˰�������ϸǰֵ̨����
 */
public class SbjkmxData implements Serializable
{
    public SbjkmxData()
    {
    }
    
    /**
     * �걨��ʽ������1������0��
     */
    private String sbfs;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmdm;

    /**
     * ˰������
     */
    private String szmc;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmmc;

    /**
     * ��˰����
     */
    private BigDecimal kssl;

    /**
     * ��˰���
     */
    private BigDecimal jsje;

    /**
     * ʵ��˰��
     */
    private BigDecimal sjse;

    /**
     * ˰��
     */
    private BigDecimal sl;

    /**
     * ��������
     */
    private String coefficient = "1";

    /**
     * �������Ʊ�ʶ
     */
    private String asljbs;

    /**
     * �Ƿ񰴿�˰������
     */
    private boolean aksslj = false;

    /**
     * �����Ƿ�ɱ༭
     */
    private boolean readOnly = false;

    /**
     * �Ƿ��Ƕ��ڶ�������
     */
    private boolean fromDqde = false;

    /**
     * �Ƿ��Ǹ���˰
     */
    private boolean isFjs = false;

    /**
     * ��sbjkmxData����sbjkmx
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
                                   this.asljbs.equals("5") ))  // ����˰������
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