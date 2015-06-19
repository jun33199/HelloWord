package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;

/**
*
* ��Ʊ����BO
* @author tutu
* @version 1.0
* @time 2013-05-02
*/
public class FpdkBO implements Serializable {

	
	
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
     * Ʊ֤���루���չ�ϵ��
     */
    private String pzhm;
    
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
    private String kprq;
	
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
     * ��ѯ��˰ƾ֤������Ϣ
     */
    public String message;
    
    /**
     * ��ѯfwxx������Ϣ
     */
    public String messagefwx="";
    
    /**
     * ��Ʊ������Ϣ����ѯ��
     */
    private String fpbcxx;
    
    /**
     * ��ӡ����
     */
    private int pageNum;
    
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * fpkcList
     */
    public ArrayList fpkcList = new ArrayList();
    
    /**
     * fwxxList
     */
    public ArrayList fwxxList = new ArrayList();
    
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

	public ArrayList getFwxxList() {
		return fwxxList;
	}

	public void setFwxxList(ArrayList fwxxList) {
		this.fwxxList = fwxxList;
	}

	public ArrayList getFpkcList() {
		return fpkcList;
	}

	public void setFpkcList(ArrayList fpkcList) {
		this.fpkcList = fpkcList;
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

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
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

	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
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

	public ArrayList getFpkpList() {
		return fpkpList;
	}

	public void setFpkpList(ArrayList fpkpList) {
		this.fpkpList = fpkpList;
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

	public String getFpbcxx() {
		return fpbcxx;
	}

	public void setFpbcxx(String fpbcxx) {
		this.fpbcxx = fpbcxx;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getHdjg() {
		return hdjg;
	}

	public void setHdjg(String hdjg) {
		this.hdjg = hdjg;
	}

	public String getMessagefwx() {
		return messagefwx;
	}

	public void setMessagefwx(String messagefwx) {
		this.messagefwx = messagefwx;
	}


}
