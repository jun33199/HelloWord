/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zczjtxnstzmxb.web;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author zhangyj
 * @version 1.1
 */

public class ZczjtxnstzmxbForm2008 extends QysdsNewForm
{

	private String[] sb_columns ={"hc","zcyzzzje","zcyzjsjc","zjtxnxkj","zjtxnxss","bqzjtxekj",
			"bqzjtxess","nstze"};
	
	public void setSb_columns(String[] je)
	{
		this.sb_columns = je;
	}
	public String[] getSb_columns()
	{
		return this.sb_columns;
	}
}