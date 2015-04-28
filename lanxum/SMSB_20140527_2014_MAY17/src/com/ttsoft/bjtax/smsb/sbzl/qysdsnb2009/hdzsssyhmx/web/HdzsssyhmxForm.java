package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzsssyhmx.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

public class HdzsssyhmxForm  extends QysdsNewForm{
	private String wlrdbs; //对小型微利企业的认定标识
	private String[] sb_columns ={"hc","je"};
	private double jmshj;  //减免税合计
	private double zbh3;   //主表行3的值
	private String lrbs;//可录入标识
	public String getLrbs() {
		return lrbs;
	}
	public void setLrbs(String lrbs) {
		this.lrbs = lrbs;
	}
	public double getZbh3() {
		return zbh3;
	}
	public void setZbh3(double zbh3) {
		this.zbh3 = zbh3;
	}
	public void setSb_columns(String[] je)
	{
		this.sb_columns = je;
	}
	public String[] getSb_columns()
	{
		return this.sb_columns;
	}
	public String getWlrdbs() {
		return wlrdbs;
	}
	public void setWlrdbs(String wlrdbs) {
		this.wlrdbs = wlrdbs;
	}
	public double getJmshj() {
		return jmshj;
	}
	public void setJmshj(double jmshj) {
		this.jmshj = jmshj;
	}
}