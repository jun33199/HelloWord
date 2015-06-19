package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��Ʊ����form��:FptpForm </p>
 * @author tutu
 * @version 1.1
 */
public class FptpForm extends BaseForm {
	
	/**
     * ��Ʊ�������
     */
    public String fpzldm;
    
    /**
     * ��Ʊ��������
     */
    public String fpzlmc;
    
    /**
     * ǰ̨����Ŀ����ʼ����
     */
    public String qshm;
    
    /**
     * ��Ʊ��Ʊ�������
     */
    public String tpfpzldm;
    
    /**
     * ��Ʊ��Ʊ��������
     */
    public String tpfpzlmc;
    
    /**
     * ��Ʊ��Ʊ����
     */
    public String tpqshm;
    
    /**
     * Ҫ�ύ�ķ�Ʊ������
     */
    public String fphms;
    
	/**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm;

    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgmc;
	
	/**
     * ��ͬ���
     */
    private String htbh;
    
    /**
     * ��Ʊ����
     */
    private String fphm;
    
    /**
     * ��Ʊ�ⷿ����
     */
    public String fpkfdm;
    
    /**
     * �û�ID
     */
    public String yhid;
    
    /**
     * �û�MC
     */
    public String yhmc;
    
    /**
     * Ʊ֤�˻�����
     */
    public String zhdm;
    
    /**
     * ��ҵ����
     */
    private String hyfl;
    
    /**
     * ���ݲ�Ȩ֤��
     */
    private String fwcqzh;
    
    /**
     * ���������ַ
     */
    private String fwzldz;
    
    /**
     * ƷĿ
     */
    private String pm;
    
    /**
     * ����
     */
    private String dj;
    
    /**
     * ����
     */
    private String sl;
    
    /**
     * ���
     */
    private String je;
    
    /**
     * ��˰������(��)
     */
    private String nsrmc_buyer;
    
    /**
     * ��˰������(����)
     */
    private String nsrmc_seller;
    
    /**
     * ��ע
     */
    private String bz;
    
    /**
     * Сд�ϼ�
     */
    private String xxhj;
    
    /**
     * ��д�ϼ�
     */
    private String dxhj;
    
    /**
     * ��˰֤����
     */
    private String wszh;
    
    /**
     * �������
     */
    private String jdhm;
    
    /**
     * ��Ʊ��
     */
    private String kpr;
    
    /**
     * ��Ʊ����
     */
    private String kprq;
    
    /**
     * ¼����
     */
    private String lrr;
    
    /**
     * ¼������
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
     * ��Ʊ������Ϣ����ѯ��
     */
    private String fpbcbc;
    
    /**
     * ��Ʊ������Ϣ�����棩
     */
    private String fpbcxx;
    
    /**
     * ����ɹ���־
     */
    private String bccgbz;
    
    /**
     * ��ѯfwxx������Ϣ
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
