package com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊģ��ķ�Ʊ��������form��:FpdcForm </p>
 * @author tutu
 * @version 1.1
 */
public class FpdcForm extends BaseForm {
	
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
     * ��ѯ�����Ʊ�������
     */
    public String cxfpzldm;
    
    /**
     * ��ѯ�����Ʊ��������
     */
    public String cxfpzlmc;
    
    /**
     * ǰ̨��ѯ����Ŀ����ʼ����
     */
    public String cxqshm;
    
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
     * ����״̬
     */
    private String dczt;
    
    /**
     * ��ʼ����
     */
    private String qsrq;
    
    /**
     * ��ֹ����
     */
    private String jzrq;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * cxfpzlList
     */
    public ArrayList cxfpzlList = new ArrayList();
    
    /**
     * fpkcList
     */
    public ArrayList fpkcList = new ArrayList();
    
    /**
     * cxList
     */
    public ArrayList cxList = new ArrayList();
    
    /**
     * dcztList
     */
    public ArrayList dczbList = new ArrayList();
    
    /**
     * dcztList
     */
    public ArrayList dcmxList = new ArrayList();
    
    /**
     * ��Ʊ������Ϣ����ѯ��
     */
    private String fpbcbc;
    
	/**
     * resetPage.
     */
    public void resetPage()
    {
    	this.setFpbcbc("test");
        this.setHtbh("");
        this.setQshm("");
        this.setQsrq("");
        this.setJzrq("");
        this.setDczt("");
        this.setCxList(new ArrayList());
    }
    
    /**
     * clear.
     */
    public void clear()
    {
        this.setCxList(new ArrayList());
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

	public String getFphms() {
		return fphms;
	}

	public void setFphms(String fphms) {
		this.fphms = fphms;
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

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
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

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getZhdm() {
		return zhdm;
	}

	public void setZhdm(String zhdm) {
		this.zhdm = zhdm;
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

	public ArrayList getFpkcList() {
		return fpkcList;
	}

	public void setFpkcList(ArrayList fpkcList) {
		this.fpkcList = fpkcList;
	}

	public ArrayList getCxList() {
		return cxList;
	}

	public void setCxList(ArrayList cxList) {
		this.cxList = cxList;
	}

	public String getDczt() {
		return dczt;
	}

	public void setDczt(String dczt) {
		this.dczt = dczt;
	}

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	
	 public String getFpbcbc() {
			return fpbcbc;
		}
		public void setFpbcbc(String fpbcbc) {
			this.fpbcbc = fpbcbc;
		}

	public ArrayList getDczbList() {
		return dczbList;
	}

	public void setDczbList(ArrayList dczbList) {
		this.dczbList = dczbList;
	}

	public ArrayList getDcmxList() {
		return dcmxList;
	}

	public void setDcmxList(ArrayList dcmxList) {
		this.dcmxList = dcmxList;
	}
	
	
}
