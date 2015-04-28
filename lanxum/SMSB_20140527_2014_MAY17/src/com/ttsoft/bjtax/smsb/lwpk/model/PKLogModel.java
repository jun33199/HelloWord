package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
import java.util.Date;

public class PKLogModel implements Serializable {

	//序号
	private int xh;
	//业务类型
	private String ywlx;
	//中文异常描述
	private String zwycms;
	//异常信息
	private String ycxx;
	//创建时间
	private Date cjsj;
	//处理标志
	private String clbz;
	//处理时间
	private Date clsj;
	//处理人
	private String clr;
	//处理结果描述
	private String cljgms;
	
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public String getZwycms() {
		return zwycms;
	}
	public void setZwycms(String zwycms) {
		this.zwycms = zwycms;
	}
	public String getYcxx() {
		return ycxx;
	}
	public void setYcxx(String ycxx) {
		this.ycxx = ycxx;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getClbz() {
		return clbz;
	}
	public void setClbz(String clbz) {
		this.clbz = clbz;
	}
	public Date getClsj() {
		return clsj;
	}
	public void setClsj(Date clsj) {
		this.clsj = clsj;
	}
	public String getClr() {
		return clr;
	}
	public void setClr(String clr) {
		this.clr = clr;
	}
	public String getCljgms() {
		return cljgms;
	}
	public void setCljgms(String cljgms) {
		this.cljgms = cljgms;
	}
	
}
