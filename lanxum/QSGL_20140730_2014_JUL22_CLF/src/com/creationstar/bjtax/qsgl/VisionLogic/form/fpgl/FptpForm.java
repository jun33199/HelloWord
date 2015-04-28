package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票退票功能form类:FptpForm </p>
 * @author tutu
 * @version 1.1
 */
public class FptpForm extends BaseForm {
	
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
     * 退票发票种类代码
     */
    public String tpfpzldm;
    
    /**
     * 退票发票种类名称
     */
    public String tpfpzlmc;
    
    /**
     * 退票发票号码
     */
    public String tpqshm;
    
    /**
     * 要提交的发票号码组
     */
    public String fphms;
    
	/**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm;

    /**
     * 税务机关组织结构名称
     */
    private String swjgzzjgmc;
	
	/**
     * 合同编号
     */
    private String htbh;
    
    /**
     * 发票号码
     */
    private String fphm;
    
    /**
     * 发票库房代码
     */
    public String fpkfdm;
    
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
     * 录入人
     */
    private String lrr;
    
    /**
     * 录入日期
     */
    private String lrrq;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * cxfpzlList
     */
    public ArrayList tpfpzlList = new ArrayList();
    
    /**
     * fpkcList
     */
    public ArrayList fpkcList = new ArrayList();
    
    /**
     * cxList
     */
    public ArrayList cxList = new ArrayList();
    
    /**
     * 发票报错信息（查询）
     */
    private String fpbcbc;
    
    /**
     * 发票报错信息（保存）
     */
    private String fpbcxx;
    
    /**
     * 保存成功标志
     */
    private String bccgbz;
    
    /**
     * 查询fwxx返回信息
     */
    public String messagefwx;
    


	public String getMessagefwx() {
		return messagefwx;
	}

	public void setMessagefwx(String messagefwx) {
		this.messagefwx = messagefwx;
	}

	/**
     * resetPage.
     */
    public void resetPage()
    {
    	this.setFpbcbc("test");
    	this.setBccgbz("0");
        this.setHtbh("");
        this.setQshm("");
        this.setTpqshm("");
        this.setTpfpzlList(new ArrayList());
        this.setCxList(new ArrayList());
    }
    
    /**
     * clear.
     */
    public void clear()
    {
        this.setBccgbz("0");
        this.setCxList(new ArrayList());
    }
    
    /**
     * reset.
     */
    public void reset()
    {
        this.setTpfpzlList(new ArrayList());
    }
    
    /**
     * resetforSave.
     */
    public void resetforSave()
    {
    	this.setHtbh("");
    	this.setFpbcbc("test");
		this.setBccgbz("1");
		this.setTpqshm("");
		this.setCxList(new ArrayList());
    }

	public String getLrr() {
		return lrr;
	}
	
	public String getFpbcbc() {
		return fpbcbc;
	}

	public void setFpbcbc(String fpbcbc) {
		this.fpbcbc = fpbcbc;
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

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public String getTpfpzldm() {
		return tpfpzldm;
	}

	public void setTpfpzldm(String tpfpzldm) {
		this.tpfpzldm = tpfpzldm;
	}

	public String getTpfpzlmc() {
		return tpfpzlmc;
	}

	public void setTpfpzlmc(String tpfpzlmc) {
		this.tpfpzlmc = tpfpzlmc;
	}

	public String getTpqshm() {
		return tpqshm;
	}

	public void setTpqshm(String tpqshm) {
		this.tpqshm = tpqshm;
	}

	public ArrayList getTpfpzlList() {
		return tpfpzlList;
	}

	public void setTpfpzlList(ArrayList tpfpzlList) {
		this.tpfpzlList = tpfpzlList;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getZhdm() {
		return zhdm;
	}

	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
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

	public ArrayList getFpzlList() {
		return fpzlList;
	}

	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
	}

	public ArrayList getFpkcList() {
		return fpkcList;
	}

	public void setFpkcList(ArrayList fpkcList) {
		this.fpkcList = fpkcList;
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

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	
	public ArrayList getCxList() {
		return cxList;
	}

	public void setCxList(ArrayList cxList) {
		this.cxList = cxList;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getFphms() {
		return fphms;
	}

	public void setFphms(String fphms) {
		this.fphms = fphms;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getKprq() {
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
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

	public String getFpbcxx() {
		return fpbcxx;
	}

	public void setFpbcxx(String fpbcxx) {
		this.fpbcxx = fpbcxx;
	}

	public String getBccgbz() {
		return bccgbz;
	}

	public void setBccgbz(String bccgbz) {
		this.bccgbz = bccgbz;
	}


}
