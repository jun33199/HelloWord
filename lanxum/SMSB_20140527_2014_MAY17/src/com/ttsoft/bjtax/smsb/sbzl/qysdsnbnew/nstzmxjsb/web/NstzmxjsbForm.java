	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
	 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Description:��ҵ����˰�걨</p>
	 * @author ����ϼ
	 * @version 1.1
	 */
	
	public class NstzmxjsbForm 
	 extends QysdsNewForm
	 {
		 /**
		  * ��Ź̶����ַ�������
		  */
		 private String[] sb_cloumns ={"hc","je"};
		 /**
		  * ��Ŷ�̬���ַ�������
		  */
		 private String[] nstzjs_columns={"xm","nstzjs_je"};
		 /**
		  * ��Ź̶�������list
		  */
		 private List nstzmxjsbsj = new ArrayList(); 
		 /**
		  * ��Ŷ�̬������list
		  */
		 private List nstzjs_List = new ArrayList();
		 
		 public void setSb_cloumns(String[] je)
		 {
			 this.sb_cloumns = je;
		 }
		 public void setNstzjs_columns(String[] je)
		 {
			 this.nstzjs_columns = je;
		 }
		public String[] getSb_cloumns()
		{
			return this.sb_cloumns;
		}
		public String[] getNstzjs_columns()
		{
			return this.nstzjs_columns;
		}
		public void setDataList(List list)
		{
			this.nstzmxjsbsj = list;
		}
		public void setNstzjs_List(List list)
		{
			this.nstzjs_List = list;
		}
		public List getDataList()
		{
			return this.nstzmxjsbsj;
		}
		public List getNstzjs_List()
		{
			return this.nstzjs_List;
		}
	 }