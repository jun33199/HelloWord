package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.web;

import java.util.Map;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:2009����������ҵ����˰����</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class SsyhmxForm  extends QysdsNewForm{
	private String wlrdbs; //��С��΢����ҵ���϶���ʶ
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
