package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;

/**
*
* ��Ʊ¼��BO
* @author tutu
* @version 1.0
* @time 2013-05-02
*/
public class FplrBO implements Serializable {
	
	/**
     * ��Ʊ�ⷿ����
     */
    public String fpkfdm;
    
    /**
     * �û�����
     */
    public String yhmc;
    
    /**
     * �û�id
     */
    public String yhid;
    
	/**
     * ��Ʊ�������
     */
    public String fpzldm;
    
    /**
     * ˰�������֯��������
     */
    public String swjgzzjgdm;
    
    /**
     * ��Ʊ��ʼ����
     */
    public String qshm;
    
    /**
     * ��Ʊ��ֹ����
     */
    public String jzhm;
    
    /**
     * ��Ʊ��ʼ����
     */
    public String fpkcqzhm;
    
    /**
     * ��Ʊ��ֹ����
     */
    public String fpkcjzhm;
    
    /**
     * ��Ʊ����
     */
    public BigDecimal sl;
    
    /**
     * ¼����
     */
    public String lrr;
    
    /**
     * ¼������
     */
    public String lrrq;
    
    /**
     * ¼������
     */
    public String czsj;
    
    /**
     * ��Ʊ�������vo
     */
    public Fpczz fpcz;

    /**
     * ��Ʊ�����vo
     */
    public Fpkc fpkc;
    
    /**
     * fpzlList
     */
    public ArrayList fpzlList = new ArrayList();
    
    /**
     * fplrList
     */
    public ArrayList fplrList = new ArrayList();

	public ArrayList getFpzlList() {
		return fpzlList;
	}

	public void setFpzlList(ArrayList fpzlList) {
		this.fpzlList = fpzlList;
	}

	public ArrayList getFplrList() {
		return fplrList;
	}

	public void setFplrList(ArrayList fplrList) {
		this.fplrList = fplrList;
	}

	public String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getQshm() {
		return qshm;
	}

	public void setQshm(String qshm) {
		this.qshm = qshm;
	}

	public String getJzhm() {
		return jzhm;
	}

	public void setJzhm(String jzhm) {
		this.jzhm = jzhm;
	}

	public String getFpkcqzhm() {
		return fpkcqzhm;
	}

	public void setFpkcqzhm(String fpkcqzhm) {
		this.fpkcqzhm = fpkcqzhm;
	}

	public String getFpkcjzhm() {
		return fpkcjzhm;
	}

	public void setFpkcjzhm(String fpkcjzhm) {
		this.fpkcjzhm = fpkcjzhm;
	}

	public BigDecimal getSl() {
		return sl;
	}

	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}

	public Fpczz getFpcz() {
		return fpcz;
	}

	public void setFpcz(Fpczz fpcz) {
		this.fpcz = fpcz;
	}

	public Fpkc getFpkc() {
		return fpkc;
	}

	public void setFpkc(Fpkc fpkc) {
		this.fpkc = fpkc;
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

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public String getFpkfdm() {
		return fpkfdm;
	}

	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

}
