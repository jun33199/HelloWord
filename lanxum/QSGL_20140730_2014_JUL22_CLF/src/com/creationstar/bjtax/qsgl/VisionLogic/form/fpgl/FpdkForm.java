package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��������form��:FpdkForm </p>
 * @author tutu
 * @version 1.1
 */
public class FpdkForm extends BaseForm {
	
	
	
	/**
     * ��ע�˶�ֵ�ύֵ
     */
    public String bzValueSubm;
	
	/**
     * ��ע�˶�ֵ
     */
    public String bzValue;
	
	/**
     * �˶��۸�
     */
    public String hdjg;
	
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
     * ��ͬ���
     */
    private String htbh;
    
    /**
     * �걨���
     */
    private String sbbh;
    
    /**
     * ���ݲ�Ȩ֤��
     */
    private String fwcqzh;
    
    /**
     * ���������ַ
     */
    private String fwzldz;
    
    /**
     * ��ͬ�ܼ�
     */
    private String htzj;
    
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
     * ��ҵ����
     */
    private String hyfl;
    
    /**
     * ��˰������(��)
     */
    private String nsrmc_buyer;
    
    /**
     * ��˰������(����)
     */
    private String nsrmc_seller;
    
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
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm;

    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgmc;
    
    /**
     * ��Ʊ�������
     */
    public String fpzldm;
    
    /**
     * ��Ʊ��������
     */
    public String fpzlmc;
    
    /**
     * ǰ̨��ʾ�Ŀ����ʼ����
     */
    public String qshm;
    
    /**
     * ��ӡʹ�÷�Ʊ����
     */
    public String fphm;
    
    /**
     * ��ӡʹ�÷�Ʊ����
     */
    public String fpdm;
    
    /**
     * Ʊ֤���루���չ�ϵ��
     */
    private String pzhm;
    
    /**
     * ��ѯ��˰ƾ֤������Ϣ
     */
    public String message;
    
    /**
     * ��ѯfwxx������Ϣ
     */
    public String messagefwx;
    
    /**
     * ��Ʊ������Ϣ����ѯ��
     */
    private String fpbcxx;
    
    /**
     * ��Ʊ������Ϣ�����棩
     */
    private String fpbcbc;
    
    /**
     * ����ɹ���־
     */
    private String bccgbz;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * fpkcList
     */
    public ArrayList fpkcList = new ArrayList();
    
    /**
     * kplxList
     */
    public ArrayList kplxList = new ArrayList();
    
    
    
    private ArrayList printList = new ArrayList();
	public ArrayList getPrintList() {
		return printList;
	}

	public void setPrintList(ArrayList printList) {
		this.printList = printList;
	}

	/**
     * resetPage.
     */
    public void resetPage()
    {
    	this.setHdjg("0.00");
    	this.setBzValue("");
    	this.setBzValueSubm("");
    	this.setFpbcxx("test");
    	this.setBccgbz("0");
        this.setHtbh("");
        this.setSbbh("");
        this.setHyfl("");
        this.setNsrmc_buyer("");
        this.setNsrmc_seller("");
        this.setPm("");
        this.setDj("");
        this.setJe("");
        this.setFwcqzh("");
        this.setFwzldz("");
        this.setBz("");
        this.setXxhj("");
        this.setDxhj("");
        this.setWszh("");
        this.setQshm("");
        this.setKpr("");
        this.setKprq("");
        this.setMessage("");
        this.setBccgbz("0");
    }
    
    /**
     * clear.
     */
    public void clear()
    {
    	this.setHdjg("0.00");
    	this.setBzValue("");
    	this.setBzValueSubm("");
    	this.setNsrmc_buyer("");
        this.setNsrmc_seller("");
        this.setBccgbz("0");
        this.setDj("");
        this.setJe("");
        this.setFwcqzh("");
        this.setFwzldz("");
        this.setBz("");
        this.setXxhj("");
        this.setDxhj("");
        this.setWszh("");
        this.setJdhm("");
        this.setMessage("");
    }
    
    /**
     * reset.
     */
    public void reset()
    {
    	this.setFpbcxx("");
        this.setFpzlList(new ArrayList());
    }
    
    /**
     * resetforSave.
     */
    public void resetforSave()
    {
    	this.setHtbh("");
    	this.setQshm("");
		this.setFpbcxx("test");
		this.setBccgbz("1");
    }
    
    /**
     * resetforBeSave.
     */
    public void resetforBeSave()
    {
		this.setBccgbz("0");
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

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
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

	public String getHtzj() {
		return htzj;
	}

	public void setHtzj(String htzj) {
		this.htzj = htzj;
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

	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
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

	public ArrayList getFpzlList() {
		return fpzlList;
	}

	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
	}

	public String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
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
	
	public String getQshm() {
		return qshm;
	}

	public void setQshm(String qshm) {
		this.qshm = qshm;
	}

	public ArrayList getFpkcList() {
		return fpkcList;
	}

	public void setFpkcList(ArrayList fpkcList) {
		this.fpkcList = fpkcList;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	
	public ArrayList getKplxList() {
		return kplxList;
	}

	public void setKplxList(ArrayList kplxList) {
		this.kplxList = kplxList;
	}

	public String getFpbcxx() {
		return fpbcxx;
	}

	public void setFpbcxx(String fpbcxx) {
		this.fpbcxx = fpbcxx;
	}

	public String getFpbcbc() {
		return fpbcbc;
	}

	public void setFpbcbc(String fpbcbc) {
		this.fpbcbc = fpbcbc;
	}

	public String getBccgbz() {
		return bccgbz;
	}

	public void setBccgbz(String bccgbz) {
		this.bccgbz = bccgbz;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getKprq() {
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	public String getPzhm() {
		return pzhm;
	}

	public void setPzhm(String pzhm) {
		this.pzhm = pzhm;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHdjg() {
		return hdjg;
	}

	public void setHdjg(String hdjg) {
		this.hdjg = hdjg;
	}

	public String getBzValue() {
		return bzValue;
	}

	public void setBzValue(String bzValue) {
		this.bzValue = bzValue;
	}

	public String getBzValueSubm() {
		return bzValueSubm;
	}

	public void setBzValueSubm(String bzValueSubm) {
		this.bzValueSubm = bzValueSubm;
	}

	public String getMessagefwx() {
		return messagefwx;
	}

	public void setMessagefwx(String messagefwx) {
		this.messagefwx = messagefwx;
	}

}
