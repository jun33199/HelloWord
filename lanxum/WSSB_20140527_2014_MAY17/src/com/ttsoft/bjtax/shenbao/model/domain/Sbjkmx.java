package com.ttsoft.bjtax.shenbao.model.domain;

import com.ekernel.db.or.ORObject;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0
 */
public class Sbjkmx implements ORObject
{

//税种税目代码	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
    String szsmdm;
//缴款凭证号	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
    String jkpzh;
//计算机代码	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
    String jsjdm;
//预算科目代码	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
    String yskmdm;
//预算科目名称	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String yskmmc;
//预算级次代码	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
    String ysjcdm;
//预算级次名称	YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String ysjcmc;
//税种税目名称	SZSMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
    String szsmmc;
//课税数量	KSSL	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal kssl;
//计税金额	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal jsje;
//实z  [缴税额	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
    BigDecimal sjse;
//税款所属开始日期	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssksrq;
//税款所属结束日期	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
    Timestamp skssjsrq;
//税种代码        SZDM    VARCHAR2(6)     这不是表中的字段
    String szdm;
//税种名称        SZMC    VARCHAR2(6)     这不是表中的字段
    String szmc;
//征期类型代码
    String zqlxdm; //这不是数据库中的字段,为了后台获取得到税款所属时期必须有这个信息
//入库金额	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
    BigDecimal rkje;
//申报编号    SBBH	VARCHAR2(20)
    String sbbh;
    //市局级分成(保留4为小数)
    BigDecimal sjfc;
    //区县级分成(保留4为小数)
    BigDecimal qjfc;
    String swjgzzjgdm;//税务机关组织机构代码
    String nd;//年度
    BigDecimal sl;//税率

    Timestamp cjrq;
    Timestamp lrrq;
    String qxdm;
    
    String sbfs;

    public String getSbfs() {
		return sbfs;
	}

	public void setSbfs(String sbfs) {
		this.sbfs = sbfs;
	}

	public String getJkpzh()
    {
        return jkpzh;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public BigDecimal getJsje()
    {
        return jsje;
    }

    public BigDecimal getKssl()
    {
        return kssl;
    }

    public BigDecimal getSjse()
    {
        return sjse;
    }

    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq()
    {
        return skssksrq;
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

    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSjse(BigDecimal sjse)
    {
        this.sjse = sjse;
    }

    public void setKssl(BigDecimal kssl)
    {
        this.kssl = kssl;
    }

    public void setJsje(BigDecimal jsje)
    {
        this.jsje = jsje;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh(String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getZqlxdm()
    {
        return zqlxdm;
    }

    public void setZqlxdm(String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public BigDecimal getRkje()
    {
        return rkje;
    }

    public void setRkje(BigDecimal rkje)
    {
        this.rkje = rkje;
    }

    public String getSbbh()
    {
        return sbbh;
    }

    public void setSbbh(String sbbh)
    {
        this.sbbh = sbbh;
    }

    public BigDecimal getQjfc()
    {
        return qjfc;
    }

    public void setQjfc(BigDecimal qjfc)
    {
        this.qjfc = qjfc;
    }

    public BigDecimal getSjfc()
    {
        return sjfc;
    }

    public void setSjfc(BigDecimal sjfc)
    {
        this.sjfc = sjfc;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public java.math.BigDecimal getSl()
    {
        return sl;
    }
    public void setSl(java.math.BigDecimal sl)
    {
        this.sl = sl;
    }
    public Timestamp getCjrq()
    {
        return cjrq;
    }
    public void setCjrq(Timestamp cjrq)
    {
        this.cjrq = cjrq;
    }
    public Timestamp getLrrq()
    {
        return lrrq;
    }
    public void setLrrq(Timestamp lrrq)
    {
        this.lrrq = lrrq;
    }
    public String getQxdm()
    {
        return qxdm;
    }
    public void setQxdm(String qxdm)
    {
        this.qxdm = qxdm;
    }
}