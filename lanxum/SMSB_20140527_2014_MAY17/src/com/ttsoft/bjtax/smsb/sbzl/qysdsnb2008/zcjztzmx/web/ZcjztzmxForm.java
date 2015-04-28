package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2008查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class ZcjztzmxForm  extends QysdsNewForm{
	
	private String[] sb_columns1 ={"hc1","gzxj","ghjf","zgflf","zgjyjf","xj"};

	public void setSb_columns1(String[] sb_columns1 )
	{
		this.sb_columns1 = sb_columns1;
	}
	public String[] getSb_columns1()
	{
		return this.sb_columns1;
	}
}
