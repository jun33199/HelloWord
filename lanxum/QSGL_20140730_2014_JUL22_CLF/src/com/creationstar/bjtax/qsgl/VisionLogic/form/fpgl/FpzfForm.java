package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

public class FpzfForm extends BaseForm {
	
	/**
     * 发票库房代码
     */
    public String fpkfdm;
	
	/**
     * 发票种类代码
     */
    public String fpzldm;
    
    /**
     * 发票种类名称
     */
    public String fpzlmc;
    
    /**
     * 前台输入的库存起始号码
     */
    public String qshm;
    
    /**
     * 确认发票种类代码
     */
    public String cxfpzldm;
    
    /**
     * 确认发票种类名称
     */
    public String cxfpzlmc;
    
    /**
     * 确认的库存起始号码
     */
    public String cxqshm;
    
    /**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm;

    /**
     * 税务机关组织结构名称
     */
    private String swjgzzjgmc;
    
    /**
     * 用户ID
     */
    public String yhid;
    
    /**
     * 用户MC
     */
    public String yhmc;
    
    /**
     * 票证账户代码
     */
    public String zhdm;
    
    /**
     * 行业分类
     */
    private String hyfl;
    
    /**
     * 房屋产权证号
     */
    private String fwcqzh;
    
    /**
     * 房屋坐落地址
     */
    private String fwzldz;
    
    /**
     * 品目
     */
    private String pm;
    
    /**
     * 单价
     */
    private String dj;
    
    /**
     * 数量
     */
    private String sl;
    
    /**
     * 金额
     */
    private String je;
    
    /**
     * 纳税人名称(买方)
     */
    private String nsrmc_buyer;
    
    /**
     * 纳税人名称(卖方)
     */
    private String nsrmc_seller;
    
    /**
     * 备注
     */
    private String bz;
    
    /**
     * 小写合计
     */
    private String xxhj;
    
    /**
     * 大写合计
     */
    private String dxhj;
    
    /**
     * 完税证号码
     */
    private String wszh;
    
    /**
     * 机打号码
     */
    private String jdhm;
    
    /**
     * 开票人
     */
    private String kpr;
    
    /**
     * 开票日期
     */
    private String kprq;
    
    /**
     * 代开人
     */
    private String lrr;
    
    /**
     * 代开日期
     */
    private String lrrq;
    
    /**
     * 发票报错信息（保存）
     */
    private String fpbcxx;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * cxfpzlList
     */
    public ArrayList cxfpzlList = new ArrayList();
    

	/**
     * resetPage.
     */
    public void resetPage()
    {
        this.setQshm("");
        this.setCxqshm("");
    }


	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}


	public String getFpzldm() {
		return fpzldm;
	}


	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}


	public String getFpzlmc() {
		return fpzlmc;
	}


	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}


	public String getQshm() {
		return qshm;
	}


	public void setQshm(String qshm) {
		this.qshm = qshm;
	}


	public String getCxfpzldm() {
		return cxfpzldm;
	}


	public void setCxfpzldm(String cxfpzldm) {
		this.cxfpzldm = cxfpzldm;
	}


	public String getCxfpzlmc() {
		return cxfpzlmc;
	}


	public void setCxfpzlmc(String cxfpzlmc) {
		this.cxfpzlmc = cxfpzlmc;
	}


	public String getCxqshm() {
		return cxqshm;
	}


	public void setCxqshm(String cxqshm) {
		this.cxqshm = cxqshm;
	}


	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}


	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}


	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}


	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}


	public String getYhid() {
		return yhid;
	}


	public void setYhid(String yhid) {
		this.yhid = yhid;
	}


	public String getYhmc() {
		return yhmc;
	}


	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}


	public String getHyfl() {
		return hyfl;
	}


	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}


	public String getFwcqzh() {
		return fwcqzh;
	}


	public void setFwcqzh(String fwcqzh) {
		this.fwcqzh = fwcqzh;
	}


	public String getFwzldz() {
		return fwzldz;
	}


	public void setFwzldz(String fwzldz) {
		this.fwzldz = fwzldz;
	}


	public String getPm() {
		return pm;
	}


	public void setPm(String pm) {
		this.pm = pm;
	}


	public String getDj() {
		return dj;
	}


	public void setDj(String dj) {
		this.dj = dj;
	}


	public String getSl() {
		return sl;
	}


	public void setSl(String sl) {
		this.sl = sl;
	}


	public String getJe() {
		return je;
	}


	public void setJe(String je) {
		this.je = je;
	}


	public String getNsrmc_buyer() {
		return nsrmc_buyer;
	}


	public void setNsrmc_buyer(String nsrmc_buyer) {
		this.nsrmc_buyer = nsrmc_buyer;
	}


	public String getNsrmc_seller() {
		return nsrmc_seller;
	}


	public void setNsrmc_seller(String nsrmc_seller) {
		this.nsrmc_seller = nsrmc_seller;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getXxhj() {
		return xxhj;
	}


	public void setXxhj(String xxhj) {
		this.xxhj = xxhj;
	}


	public String getDxhj() {
		return dxhj;
	}


	public void setDxhj(String dxhj) {
		this.dxhj = dxhj;
	}


	public String getWszh() {
		return wszh;
	}


	public void setWszh(String wszh) {
		this.wszh = wszh;
	}


	public String getJdhm() {
		return jdhm;
	}


	public void setJdhm(String jdhm) {
		this.jdhm = jdhm;
	}


	public String getKpr() {
		return kpr;
	}


	public void setKpr(String kpr) {
		this.kpr = kpr;
	}


	public String getKprq() {
		return kprq;
	}


	public void setKprq(String kprq) {
		this.kprq = kprq;
	}


	public String getZhdm() {
		return zhdm;
	}


	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
	}


	public String getFpkfdm() {
		return fpkfdm;
	}


	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	
	public ArrayList getFpzlList() {
		return fpzlList;
	}


	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
	}


	public ArrayList getCxfpzlList() {
		return cxfpzlList;
	}


	public void setCxfpzlList(ArrayList cxfpzlList) {
		this.cxfpzlList = cxfpzlList;
	}


	public String getFpbcxx() {
		return fpbcxx;
	}


	public void setFpbcxx(String fpbcxx) {
		this.fpbcxx = fpbcxx;
	}
}
