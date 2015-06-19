package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Kplx;

/**
*
* ��Ʊ����BO
* @author tutu
* @version 1.0
* @time 2013-05-02
*/
public class FpdcBO implements Serializable{

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
     * ��ʼ����
     */
    private String qsrq;
    
    /**
     * ��ֹ����
     */
    private String jzrq;
    
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
     * ����״̬
     */
    private String dczt;
    
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
     * ��Ʊ����
     */
    private String fphm;
    
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
     * fpkpList
     */
    public ArrayList fpkpList = new ArrayList();
    
    /**
     * fphzList
     */
    public ArrayList fphzList = new ArrayList();
	
	
    /**
     * ��Ʊ����vo
     */
    private Kplx kplx = new Kplx();
    
    /**
     * ˰�������֯����vo
     */
    private Swjgzzjg swjgzzjg = new Swjgzzjg();
    
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

	public ArrayList getFpkpList() {
		return fpkpList;
	}

	public void setFpkpList(ArrayList fpkpList) {
		this.fpkpList = fpkpList;
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

	public String getDczt() {
		return dczt;
	}

	public void setDczt(String dczt) {
		this.dczt = dczt;
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

	public Kplx getKplx() {
		return kplx;
	}

	public void setKplx(Kplx kplx) {
		this.kplx = kplx;
	}

	public Swjgzzjg getSwjgzzjg() {
		return swjgzzjg;
	}

	public void setSwjgzzjg(Swjgzzjg swjgzzjg) {
		this.swjgzzjg = swjgzzjg;
	}

	public ArrayList getFphzList() {
		return fphzList;
	}

	public void setFphzList(ArrayList fphzList) {
		this.fphzList = fphzList;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
}
