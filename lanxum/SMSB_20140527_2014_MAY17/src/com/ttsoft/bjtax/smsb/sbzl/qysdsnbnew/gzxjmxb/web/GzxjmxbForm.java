/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.web;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GzxjmxbForm  extends QysdsNewForm
{
	
	private String[] sb_columns1 ={"hc1","gzxj","ghjf","zgflf","zgjyjf","xj"};
	private String[] sb_columns2 ={"hc2","L1","L2","L3","L4","L5"};
	
	private String gzglxs_gzxj;
	
	public void setGzglxs_gzxj(String gzglxs_gzxj )
	{
		this.gzglxs_gzxj = gzglxs_gzxj;
	}
	public String getGzglxs_gzxj()
	{
		return this.gzglxs_gzxj;
	}	
	public void setSb_columns1(String[] sb_columns1 )
	{
		this.sb_columns1 = sb_columns1;
	}
	public String[] getSb_columns1()
	{
		return this.sb_columns1;
	}
	public void setSb_columns2(String[] sb_columns2 )
	{
		this.sb_columns2 = sb_columns2;
	}
	public String[] getSb_columns2()
	{
		return this.sb_columns2;
	}
}