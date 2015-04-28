/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zcmxb.web;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZcmxbForm extends QysdsNewForm
{
	
	private String[] sb_columns ={"hc","zyjz","xj","zzfy","glfy","yyfy",
			"zjgc","jrqt","zcpjjz","zcjscb","zjtxe","nstze","sjxcy","ndkczjtx",
			"ndjze","sqkce","ljjzhndkc"};
	
	public void setSb_columns(String[] je)
	{
		this.sb_columns = je;
	}
	public String[] getSb_columns()
	{
		return this.sb_columns;
	}
}