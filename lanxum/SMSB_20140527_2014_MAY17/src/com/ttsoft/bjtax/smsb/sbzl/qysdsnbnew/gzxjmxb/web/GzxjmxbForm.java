/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.web;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
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