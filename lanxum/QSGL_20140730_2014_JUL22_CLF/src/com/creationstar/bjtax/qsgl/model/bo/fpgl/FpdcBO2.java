package com.creationstar.bjtax.qsgl.model.bo.fpgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
*
* 发票导出BO2（导出Excel对象用）
* @author tutu
* @version 1.0
* @time 2013-06-02
*/

public class FpdcBO2 implements Serializable{

	String jsjdm;
    String fpkfdm;
    String fpzldm;
    String fphm;
    String kprq;
    String fkdw;
    String skdw;
    String dkdwmc;
    String kplxdm;
    String kplxmc;
    String tpfpzldm;
    String tpfphm;
    String bz;
    String sphm;
    String kpr;
    String htbh;
    String sbbh;
    String czr;
    String czrq;
    
    String pm;
    BigDecimal dj;
    BigDecimal sl;
    BigDecimal je;
    
    BigDecimal kpsl;
    BigDecimal tpsl;
    BigDecimal fpsl;
    BigDecimal kpje;
    BigDecimal tpje;
    String qsrq;
    String jzrq;
    
    ArrayList HzList = new ArrayList();
    
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String getCzrq() {
		return czrq;
	}
	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public BigDecimal getJe() {
		return je;
	}
	public void setJe(BigDecimal je) {
		this.je = je;
	}
	public String getFpkfdm() {
		return fpkfdm;
	}
	public void setFpkfdm(String fpkfdm) {
		this.fpkfdm = fpkfdm;
	}
	public String getFpzldm() {
		return fpzldm;
	}
	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getKprq() {
		return kprq;
	}
	public void setKprq(String kprq) {
		this.kprq = kprq;
	}
	public String getFkdw() {
		return fkdw;
	}
	public void setFkdw(String fkdw) {
		this.fkdw = fkdw;
	}
	public String getSkdw() {
		return skdw;
	}
	public void setSkdw(String skdw) {
		this.skdw = skdw;
	}
	public String getDkdwmc() {
		return dkdwmc;
	}
	public void setDkdwmc(String dkdwmc) {
		this.dkdwmc = dkdwmc;
	}
	public String getKplxdm() {
		return kplxdm;
	}
	public void setKplxdm(String kplxdm) {
		this.kplxdm = kplxdm;
	}
	public String getTpfpzldm() {
		return tpfpzldm;
	}
	public void setTpfpzldm(String tpfpzldm) {
		this.tpfpzldm = tpfpzldm;
	}
	public String getTpfphm() {
		return tpfphm;
	}
	public void setTpfphm(String tpfphm) {
		this.tpfphm = tpfphm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSphm() {
		return sphm;
	}
	public void setSphm(String sphm) {
		this.sphm = sphm;
	}
	public String getKpr() {
		return kpr;
	}
	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getKplxmc() {
		return kplxmc;
	}
	public void setKplxmc(String kplxmc) {
		this.kplxmc = kplxmc;
	}
	public BigDecimal getKpsl() {
		return kpsl;
	}
	public void setKpsl(BigDecimal kpsl) {
		this.kpsl = kpsl;
	}
	public BigDecimal getTpsl() {
		return tpsl;
	}
	public void setTpsl(BigDecimal tpsl) {
		this.tpsl = tpsl;
	}
	public BigDecimal getFpsl() {
		return fpsl;
	}
	public void setFpsl(BigDecimal fpsl) {
		this.fpsl = fpsl;
	}
	public BigDecimal getKpje() {
		return kpje;
	}
	public void setKpje(BigDecimal kpje) {
		this.kpje = kpje;
	}
	public BigDecimal getTpje() {
		return tpje;
	}
	public void setTpje(BigDecimal tpje) {
		this.tpje = tpje;
	}
	public ArrayList getHzList() {
		return HzList;
	}
	public void setHzList(ArrayList hzList) {
		HzList = hzList;
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
    
}
