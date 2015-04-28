package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx15.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx15Form extends QysdsJmsbajlMainForm {
	//1、缩短折就-固定资产名称
	private String gdzcmc_sd;
	//4、缩短折就-固定资产原值
	private String gdzcyz_sd;
	//5、缩短折就-固定资产计税基础
	private String gdzcjsjc_sd;
	//6、缩短折就-税法规定的最低年限
	private String sfgdzdnx_sd;
	//7、缩短折就-加速折旧的最低年限
	private String jszjzdnx_sd;
	//8、缩短折就-计提折旧年度起
	private String zjqsnd_sd;
	//9、缩短折就-计提折旧年度止
	private String zjzznd_sd;
	//10、缩短折就-每年可扣除的折旧额
	private String kkczje_sd;
	//11、缩短折就-已计提折旧的年限
	private String yjtzjnx_sd;
	//12、缩短折就-已计提的折旧额
	private String yjtzje_sd;
	
	
	//13、加速折就-固定资产名称
	private String gdzcmc_js;
	//15、加速折就-固定资产原值
	private String gdzcyz_js;
	//16、加速折就-固定资产计税基础
	private String gdzcjsjc_js;
	//17、加速折就-计算折旧的方法
	private String jszjff_js;
	//18、加速折就-年折旧额
	private String nzje_js;
	
	//20、处理标识
	private String clbs = "0";
	/*
	 * 缩短或加速 0:缩短；1：加速;
	 */
	private String sdhjs = "0";
	//23、序号
	private String xh;
	
	public String getGdzcmc_sd() {
		return gdzcmc_sd;
	}
	public void setGdzcmc_sd(String gdzcmc_sd) {
		this.gdzcmc_sd = gdzcmc_sd;
	}
	public String getGdzcyz_sd() {
		return gdzcyz_sd;
	}
	public void setGdzcyz_sd(String gdzcyz_sd) {
		this.gdzcyz_sd = gdzcyz_sd;
	}
	public String getGdzcjsjc_sd() {
		return gdzcjsjc_sd;
	}
	public void setGdzcjsjc_sd(String gdzcjsjc_sd) {
		this.gdzcjsjc_sd = gdzcjsjc_sd;
	}
	public String getSfgdzdnx_sd() {
		return sfgdzdnx_sd;
	}
	public void setSfgdzdnx_sd(String sfgdzdnx_sd) {
		this.sfgdzdnx_sd = sfgdzdnx_sd;
	}
	public String getJszjzdnx_sd() {
		return jszjzdnx_sd;
	}
	public void setJszjzdnx_sd(String jszjzdnx_sd) {
		this.jszjzdnx_sd = jszjzdnx_sd;
	}
	public String getZjqsnd_sd() {
		return zjqsnd_sd;
	}
	public void setZjqsnd_sd(String zjqsnd_sd) {
		this.zjqsnd_sd = zjqsnd_sd;
	}
	public String getZjzznd_sd() {
		return zjzznd_sd;
	}
	public void setZjzznd_sd(String zjzznd_sd) {
		this.zjzznd_sd = zjzznd_sd;
	}
	public String getKkczje_sd() {
		return kkczje_sd;
	}
	public void setKkczje_sd(String kkczje_sd) {
		this.kkczje_sd = kkczje_sd;
	}
	public String getYjtzjnx_sd() {
		return yjtzjnx_sd;
	}
	public void setYjtzjnx_sd(String yjtzjnx_sd) {
		this.yjtzjnx_sd = yjtzjnx_sd;
	}
	public String getYjtzje_sd() {
		return yjtzje_sd;
	}
	public void setYjtzje_sd(String yjtzje_sd) {
		this.yjtzje_sd = yjtzje_sd;
	}
	public String getGdzcmc_js() {
		return gdzcmc_js;
	}
	public void setGdzcmc_js(String gdzcmc_js) {
		this.gdzcmc_js = gdzcmc_js;
	}
	public String getGdzcyz_js() {
		return gdzcyz_js;
	}
	public void setGdzcyz_js(String gdzcyz_js) {
		this.gdzcyz_js = gdzcyz_js;
	}
	public String getGdzcjsjc_js() {
		return gdzcjsjc_js;
	}
	public void setGdzcjsjc_js(String gdzcjsjc_js) {
		this.gdzcjsjc_js = gdzcjsjc_js;
	}
	public String getJszjff_js() {
		return jszjff_js;
	}
	public void setJszjff_js(String jszjff_js) {
		this.jszjff_js = jszjff_js;
	}
	public String getNzje_js() {
		return nzje_js;
	}
	public void setNzje_js(String nzje_js) {
		this.nzje_js = nzje_js;
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
	public String getSdhjs() {
		return sdhjs;
	}
	public void setSdhjs(String sdhjs) {
		this.sdhjs = sdhjs;
	}
}