package com.ttsoft.bjtax.smsb.model.kjqysds;

import java.io.Serializable;

/**
 * 扣缴义务人基本信息
 * @author wangxm
 *
 */
public class KJYWRXX
	implements Serializable{

	public KJYWRXX(){
	}
	/**
	 * 计算机代码
	 */
	private String kjrjsjdm;
	/**
	 * 扣缴义务人纳税识别号
	 */
	private String kjrnssbh;
	/**
	 * 扣缴人中文名称
	 */
	private String kjrmc_cn;
	/**
	 * 扣缴人英文名称
	 */
	private String kjrmc_en;
	/**
	 * 扣缴人中文地址
	 */
	private String kjrdz_cn;
	/**
	 * 扣缴人英文地址
	 */
	private String kjrdz_en;
	/**
	 * 扣缴人财务负责人
	 */
	private String kjrcwfzr;
	/**
	 * 联系人
	 */
	private String kjrlxr;
	/**
	 * 联系电话
	 */
	private String kjrlxdh;
	/**
	 * 扣缴人邮政编码
	 */
	private String kjryzbm;
	/**
	 * 扣缴人传真号码
	 */
	private String kjrczhm;
    /**
     * 经济类型代码
     */
    private String kjrjjlxdm;
	/**
	 * 经济类型名称
	 */
	private String kjrjjlxmc;
    /**
     * 经济行业分类代码
     */
    private String kjrjjxlfldm;
	/**
	 * 经济行业分类名称
	 */
	private String kjrjjxlflmc;


	public String getKjrjsjdm() {
		return kjrjsjdm;
	}
	public void setKjrjsjdm(String kjrjsjdm) {
		this.kjrjsjdm = kjrjsjdm;
	}
	public String getKjrnssbh() {
		return kjrnssbh;
	}
	public void setKjrnssbh(String kjrnssbh) {
		this.kjrnssbh = kjrnssbh;
	}

	public String getKjrdz_en() {
		return kjrdz_en;
	}
	public void setKjrdz_en(String kjrdz_en) {
		this.kjrdz_en = kjrdz_en;
	}
	public String getKjrcwfzr() {
		return kjrcwfzr;
	}
	public void setKjrcwfzr(String kjrcwfzr) {
		this.kjrcwfzr = kjrcwfzr;
	}
	public String getKjrlxr() {
		return kjrlxr;
	}
	public void setKjrlxr(String kjrlxr) {
		this.kjrlxr = kjrlxr;
	}
	public String getKjrlxdh() {
		return kjrlxdh;
	}
	public void setKjrlxdh(String kjrlxdh) {
		this.kjrlxdh = kjrlxdh;
	}
	public String getKjryzbm() {
		return kjryzbm;
	}
	public void setKjryzbm(String kjryzbm) {
		this.kjryzbm = kjryzbm;
	}
	public String getKjrczhm() {
		return kjrczhm;
	}
	public void setKjrczhm(String kjrczhm) {
		this.kjrczhm = kjrczhm;
	}
	public String getKjrjjlxmc() {
		return kjrjjlxmc;
	}
	public void setKjrjjlxmc(String kjrjjlxmc) {
		this.kjrjjlxmc = kjrjjlxmc;
	}
	public String getKjrjjxlflmc() {
		return kjrjjxlflmc;
	}

    public String getKjrdz_cn()
    {
        return kjrdz_cn;
    }

    public String getKjrjjlxdm()
    {
        return kjrjjlxdm;
    }

    public String getKjrjjxlfldm()
    {
        return kjrjjxlfldm;
    }

    public String getKjrmc_cn()
    {
        return kjrmc_cn;
    }

    public String getKjrmc_en()
    {
        return kjrmc_en;
    }

    public void setKjrjjxlflmc(String kjrjjxlflmc) {
		this.kjrjjxlflmc = kjrjjxlflmc;
	}

    public void setKjrmc_en(String kjrmc_en)
    {
        this.kjrmc_en = kjrmc_en;
    }

    public void setKjrmc_cn(String kjrmc_cn)
    {
        this.kjrmc_cn = kjrmc_cn;
    }

    public void setKjrjjxlfldm(String kjrjjxlfldm)
    {
        this.kjrjjxlfldm = kjrjjxlfldm;
    }

    public void setKjrjjlxdm(String kjrjjlxdm)
    {
        this.kjrjjlxdm = kjrjjlxdm;
    }

    public void setKjrdz_cn(String kjrdz_cn)
    {
        this.kjrdz_cn = kjrdz_cn;
    }


}
