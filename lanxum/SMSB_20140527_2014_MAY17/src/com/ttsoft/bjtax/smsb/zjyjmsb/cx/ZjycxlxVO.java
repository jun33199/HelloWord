package com.ttsoft.bjtax.smsb.zjyjmsb.cx;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 再就业减免税申报查询Vo</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjycxlxVO 
        implements Serializable {
	public ZjycxlxVO() {
	  }
    //纳税人名称
	private String nsrmc;
	//计算机代码
	private String jsjdm;
	//吸纳下岗失业人员人数
	private String xnxgsyrs;
	//减免税类别
	private String jmslb;
	//享受减免时间  --暂时写成申报日期
	private String xsjmsj;
	//营业税
	private String yys;
	//城市维护建设税
	private String cjs;
	//教育费附加
	private String jyffj;
	//个人所得税
	private String grsds;
	//企业所得税
	private String qysds;
	//合计
	private String hj;
	//税务机关组织机构名称
	private String swjgzzjgmc;
	
	public String getCjs() {
		return cjs;
	}
	public void setCjs(String cjs) {
		this.cjs = cjs;
	}
	public String getGrsds() {
		return grsds;
	}
	public void setGrsds(String grsds) {
		this.grsds = grsds;
	}
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getJmslb() {
		return jmslb;
	}
	public void setJmslb(String jmslb) {
		this.jmslb = jmslb;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getJyffj() {
		return jyffj;
	}
	public void setJyffj(String jyffj) {
		this.jyffj = jyffj;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getQysds() {
		return qysds;
	}
	public void setQysds(String qysds) {
		this.qysds = qysds;
	}
	public String getXnxgsyrs() {
		return xnxgsyrs;
	}
	public void setXnxgsyrs(String xnxgsyrs) {
		this.xnxgsyrs = xnxgsyrs;
	}
	public String getXsjmsj() {
		return xsjmsj;
	}
	public void setXsjmsj(String xsjmsj) {
		this.xsjmsj = xsjmsj;
	}
	public String getYys() {
		return yys;
	}
	public void setYys(String yys) {
		this.yys = yys;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

}
