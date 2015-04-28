package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jrqysrmx.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2008查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class JrqysrmxForm  extends QysdsNewForm{
	private String[] sb_columns ={"hc","je"};
	public void setSb_columns(String[] je)
	{
		this.sb_columns = je;
	}
	public String[] getSb_columns()
	{
		return this.sb_columns;
	}
}
