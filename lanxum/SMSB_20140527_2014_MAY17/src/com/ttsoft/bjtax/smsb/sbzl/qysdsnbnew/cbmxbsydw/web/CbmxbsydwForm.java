	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
	 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbsydw.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Description:��ҵ����˰�걨</p>
	 * @author ����ϼ
	 * @version 1.1
	 */
	
	public class CbmxbsydwForm 
	 extends QysdsNewForm
	 {
		/**
		 * ���ҳ���дκͽ�������
		 */
		 private String[] sb_columns ={"hc","je"};
		 
		 /**
		  * ������ݽ��list
		  */
		 private List cbmxbsj = new ArrayList(); 
		 
		 public void setSb_columns(String[] je)
		 {
			 this.sb_columns = je;
		 }
		public String[] getSb_columns()
		{
			return this.sb_columns;
		}
		public void setDataList(List list)
		{
			this.cbmxbsj = list;
		}
		public List getDataList()
		{
			return this.cbmxbsj;
		}
		
	 }