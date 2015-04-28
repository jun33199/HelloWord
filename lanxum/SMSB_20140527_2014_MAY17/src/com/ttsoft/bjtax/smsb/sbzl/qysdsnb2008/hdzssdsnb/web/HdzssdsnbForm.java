package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.hdzssdsnb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

public class HdzssdsnbForm extends QysdsNewForm {
	public HdzssdsnbForm() {

	}
	private double jmshj;//附表减免税合计
	private boolean fbreturnbs = false;//附表返回标识
	private String[] sb_columns ={"hc","je"};
	private String lrbs;//可录入标识
	public void setSb_columns(String[] je)
	{
		this.sb_columns = je;
	}
	public String[] getSb_columns()
	{
		return this.sb_columns;
	}

	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsjbList = new ArrayList();
	
	
	
	private String xzqy;
	
	//一般减免税率
	private String ybjmsl;
	
	//减免资格
	private String jmzg;
	
	public String getJmzg() {
		return jmzg;
	}
	
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}
	
	public String getXzqy() {
		return xzqy;
	}
	
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}
	
	public void setYbjmsl(String ybjmsl) {
		this.xzqy = ybjmsl;
	}

	public List getQysdsjbList() {
		return qysdsjbList;
	}

	public void setQysdsjbList(List qysdsjbList) {
		this.qysdsjbList = qysdsjbList;
	}
	public double getJmshj() {
		return jmshj;
	}
	public void setJmshj(double jmshj) {
		this.jmshj = jmshj;
	}
	public boolean isFbreturnbs() {
		return fbreturnbs;
	}
	public void setFbreturnbs(boolean fbreturnbs) {
		this.fbreturnbs = fbreturnbs;
	}
	public String getLrbs() {
		return lrbs;
	}
	public void setLrbs(String lrbs) {
		this.lrbs = lrbs;
	}

}
