/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.qysdsmbksmxb.web;



import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author zhangyj
 * @version 1.1
 */

public class QysdsmbksmxbForm2009 
 extends QysdsNewForm
 {
	/**
	 * ���ҳ����Ϣ���ַ���������
	 */
	 private String[] sb_columns ={"hc","nd","kshyle","kmbkse","hj",
			 "d2n","d3n","d4n","d5n","ymbgksehj","bnkmbkse","xynmbkse"};
	 /**
	  * ������ݽ����List
	  */
	 private List mbksmxbsj = new ArrayList(); 
	 
	 private String sknd;
	 
	 public void setSb_cloumns(String[] je)
	 {
		 this.sb_columns = je;
	 }
	public String[] getSb_cloumns()
	{
		return this.sb_columns;
	}
	public void setDataList(List list)
	{
		this.mbksmxbsj = list;
	}
	public List getDataList()
	{
		return this.mbksmxbsj;
	}
    public void setSknd(String sknd) {
        this.sknd = sknd;
    }  
    public String getSknd() {
        return this.getSkssksrq().substring(0,4);
    }
 }