package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;

/**
*
* ��Ʊ��ƱBO
* @author tutu
* @version 1.0
* @time 2013-05-02
*/
public class FptpBO implements Serializable {
	
	
	/**
     * ��ѯ�����Ʊ�������
     */
    public String cxfpzldm;
    
    /**
     * ǰ̨��ѯ����Ŀ����ʼ����
     */
    public String cxqshm;
    
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
     * Ҫ�ύ�ķ�Ʊ������
     */
    public String fphms;
    
    /**
     * ��Ʊ��Ʊ�������
     */
    public String tpfpzldm;
    
    /**
     * ��Ʊ��Ʊ��������
     */
    public String tofpzlmc;
    
    /**
     * ��Ʊ��Ʊ����
     */
    public String tpqshm;
    
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
     * ���ݲ�Ȩ֤��
     */
    private String fwcqzh;
    
    /**
     * ���������ַ
     */
    private String fwzldz;
    
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
     * ��Ʊ��
     */
    private String kpr;
    
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
     * fpkpList
     */
    public ArrayList fpkpList = new ArrayList();
	
	
	/**
     * ��Ʊ��Ʊ����vo
     */
    private Fpczz fpczz = new Fpczz();

    /**
     * ��ϸ��vo
     */
    private Fpczmx fpczmx = new Fpczmx();
    
    /**
     * ��ͬ��ƾ֤���չ�ϵ��vo
     */
    private Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
    
    /**
     * ��ѯfwxx������Ϣ
     */
    public String messagefwx="";


	public String getMessagefwx() {
		return messagefwx;
	}


	public void setMessagefwx(String messagefwx) {
		this.messagefwx = messagefwx;
	}


	public Fpczz getFpczz() {
		return fpczz;
	}


	public void setFpczz(Fpczz fpczz) {
		this.fpczz = fpczz;
	}


	public Fpczmx getFpczmx() {
		return fpczmx;
	}


	public void setFpczmx(Fpczmx fpczmx) {
		this.fpczmx = fpczmx;
	}


	public Htypzdzgxb getHtypzdzgxb() {
		return htypzdzgxb;
	}


	public void setHtypzdzgxb(Htypzdzgxb htypzdzgxb) {
		this.htypzdzgxb = htypzdzgxb;
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

	public String getYhmc() {
		return yhmc;
	}


	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}


	public ArrayList getFpkpList() {
		return fpkpList;
	}


	public void setFpkpList(ArrayList fpkpList) {
		this.fpkpList = fpkpList;
	}


	public String getTpfpzldm() {
		return tpfpzldm;
	}


	public void setTpfpzldm(String tpfpzldm) {
		this.tpfpzldm = tpfpzldm;
	}


	public String getTofpzlmc() {
		return tofpzlmc;
	}


	public void setTofpzlmc(String tofpzlmc) {
		this.tofpzlmc = tofpzlmc;
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


	public String getFphms() {
		return fphms;
	}


	public void setFphms(String fphms) {
		this.fphms = fphms;
	}


	public String getCxfpzldm() {
		return cxfpzldm;
	}


	public void setCxfpzldm(String cxfpzldm) {
		this.cxfpzldm = cxfpzldm;
	}


	public String getCxqshm() {
		return cxqshm;
	}


	public void setCxqshm(String cxqshm) {
		this.cxqshm = cxqshm;
	}

}
