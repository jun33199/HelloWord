	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
	 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Description:��ҵ����˰�걨</p>
	 * @author ����ϼ
	 * @version 1.1
	 */
	
	public class NstzmxzjbForm 
	 extends QysdsNewForm
	 {
		/**
		 * ��Ź̶���Ϣ���ַ�������
		 */
		 private String[] sb_columns ={"hc","bqfss","sqkcxe","nstzje"};
		 /**
		  * ��Ŷ�̬����Ϣ���ַ�������
		  */
		 private String[] nstzzj_columns={"xm","nstzzj_bqfss","nstzzj_sqkcxe","nstzzj_nstzje",};
		 /**
		  * ��Ź̶������ݵ�LIST
		  */
		 private List nstzmxzjbsj = new ArrayList(); 
		 /**
		  * ��Ŷ�̬�����ݵ�LIST
		  */
		 private List nstzzj_List = new ArrayList();
		 
		 public void setSb_cloumns(String[] je)
		 {
			 this.sb_columns = je;
		 }
		 public void setNstzzj_columns(String[] je)
		 {
			 this.nstzzj_columns = je;
		 }
		public String[] getSb_cloumns()
		{
			return this.sb_columns;
		}
		public String[] getNstzzj_columns()
		{
			return this.nstzzj_columns;
		}
		public void setDataList(List list)
		{
			this.nstzmxzjbsj = list;
		}
		public void setNstzzj_List(List list)
		{
			this.nstzzj_List = list;
		}
		public List getDataList()
		{
			return this.nstzmxzjbsj;
		}
		public List getNstzzj_List()
		{
			return this.nstzzj_List;
		}
		
	 }