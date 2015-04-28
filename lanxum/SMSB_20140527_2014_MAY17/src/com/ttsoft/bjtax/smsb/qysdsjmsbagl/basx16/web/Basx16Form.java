package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx16.web;



import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
/**
 * 
 * @author wangzhijiang
 * @date   2010-2-1
 *
 */
public class Basx16Form extends QysdsJmsbajlMainForm {
	//序号
	private String xh;
	//企业购进软件的凭证名称
	private String pzmc;
	
	//外购软件缩短折旧或摊销的年限
	private String sdtxnx;
	//计提折旧年度起
	private String jtzjqsnd;
	//计提折旧年度止
	private String jtzjzznd;
	//每年可扣除的折旧额
	private String kkczje;
	//固定资产（无形资产）原值
	private String gdzcyz;
	//已计提折旧的年限
	private String yjtzjnx;
	//已计提的折旧额
	private String yjtzje;
	//处理标识
	private String clbs = "0";
	
	
	
	public String getPzmc() {
		return pzmc;
	}
	public void setPzmc(String pzmc) {
		this.pzmc = pzmc;
	}
	
	public String getSdtxnx() {
		return sdtxnx;
	}
	public void setSdtxnx(String sdtxnx) {
		this.sdtxnx = sdtxnx;
	}
	public String getJtzjqsnd() {
		return jtzjqsnd;
	}
	public void setJtzjqsnd(String jtzjqsnd) {
		this.jtzjqsnd = jtzjqsnd;
	}
	public String getJtzjzznd() {
		return jtzjzznd;
	}
	public void setJtzjzznd(String jtzjzznd) {
		this.jtzjzznd = jtzjzznd;
	}
	public String getKkczje() {
		return kkczje;
	}
	public void setKkczje(String kkczje) {
		this.kkczje = kkczje;
	}
	public String getGdzcyz() {
		return gdzcyz;
	}
	public void setGdzcyz(String gdzcyz) {
		this.gdzcyz = gdzcyz;
	}
	public String getYjtzjnx() {
		return yjtzjnx;
	}
	public void setYjtzjnx(String yjtzjnx) {
		this.yjtzjnx = yjtzjnx;
	}
	public String getYjtzje() {
		return yjtzje;
	}
	public void setYjtzje(String yjtzje) {
		this.yjtzje = yjtzje;
	}
	public String getClbs() {
		return clbs;
	}
	public void setClbs(String clbs) {
		this.clbs = clbs;
	}
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}

	
	


}