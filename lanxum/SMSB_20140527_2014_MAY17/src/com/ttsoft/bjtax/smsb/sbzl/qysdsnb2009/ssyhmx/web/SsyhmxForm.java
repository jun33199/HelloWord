package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.web;

import java.util.Map;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2009查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class SsyhmxForm  extends QysdsNewForm{
	private String wlrdbs; //对小型微利企业的认定标识
	private String[] sb_columns ={"hc","je"};
	private Map readOnlyMap;
	
	public Map getReadOnlyMap() {
		return readOnlyMap;
	}
	public void setReadOnlyMap(Map readOnlyMap) {
		this.readOnlyMap = readOnlyMap;
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
}
