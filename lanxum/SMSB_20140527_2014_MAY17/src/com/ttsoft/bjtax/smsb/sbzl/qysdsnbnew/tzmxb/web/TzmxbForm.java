	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
	 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
	 * <p>Description:��ҵ����˰�걨</p>
	 * @author ����ϼ
	 * @version 1.1
	 */
	
	public class TzmxbForm 
	 extends QysdsNewForm
	 {
		/**
		 * �̶���������
		 */
		 private String[] sb_columns ={"hc","btzqyszd","qybl",
				 "sl","tzsy","ynqysds","tzzrjsr","cstzcb",
				 "jscbtz","tzzrcb","tzzrsd"};
		 /**
		  * ����ծȨͶ������������
		  */
		 private String[] dqzqtz_columns={"dqzqtz_tzzczl","dqzqtz_btzqyszd","dqzqtz_qybl","dqzqtz_sl",
				 "dqzqtz_tzsy","dqzqtz_ynqysds","dqzqtz_tzzrjsr",
				 "dqzqtz_cstzcb","dqzqtz_jscbtz","dqzqtz_tzzrcb","dqzqtz_tzzrsd"};
		 
		 /**
		  * ����ծȨͶ������������
		  */
		 private String[] cqzqtz_columns={"cqzqtz_tzzczl","cqzqtz_btzqyszd","cqzqtz_qybl","cqzqtz_sl",
				 "cqzqtz_tzsy","cqzqtz_ynqysds","cqzqtz_tzzrjsr",
				 "cqzqtz_cstzcb","cqzqtz_jscbtz","cqzqtz_tzzrcb","cqzqtz_tzzrsd"};
		 /**
		  * ���ڹ�ȨͶ������������
		  */
		 private String[] dqgqtz_columns={"dqgqtz_tzzczl","dqgqtz_btzqyszd","dqgqtz_qybl","dqgqtz_sl",
				 "dqgqtz_tzsy","dqgqtz_ynqysds","dqgqtz_tzzrjsr",
				 "dqgqtz_cstzcb","dqgqtz_jscbtz","dqgqtz_tzzrcb","dqgqtz_tzzrsd"};
		 /**
		  * ���ڹ�ȨͶ������������
		  */
		 private String[] cqgqtz_columns={"cqgqtz_tzzczl","cqgqtz_btzqyszd","cqgqtz_qybl","cqgqtz_sl",
				 "cqgqtz_tzsy","cqgqtz_ynqysds","cqgqtz_tzzrjsr",
				 "cqgqtz_cstzcb","cqgqtz_jscbtz","cqgqtz_tzzrcb","cqgqtz_tzzrsd"};
		 /**
		  * �̶���������
		  */
		 private List tzmxbsj = new ArrayList(); 
		 /**
		  * ����ծȨͶ������������
		  */
		 private List dqzqtzList=new ArrayList();
		 /**
		  * ����ծȨͶ������������
		  */
		 private List cqzqtzList=new ArrayList();
		 /**
		  * ���ڹ�ȨͶ������������
		  */
		 private List dqgqtzList=new ArrayList();
		 /**
		  * ���ڹ�ȨͶ������������
		  */
		 private List cqgqtzList=new ArrayList();
		 
		 /**
		  * ������������
		  * @param je
		  */
		 private String gqtzzrss ; //��ǰ��Ƚ�ת�ڱ����˰ǰ�۳��Ĺ�ȨͶ��ת����ʧ
		 
		 private String gqsszrsqkcqe; //����ȹ�ȨͶ��ת����ʧ˰ǰ�۳��޶
		 
		 private String nstze; // Ͷ��ת�þ���ʧ��˰������
		 
		 private String jzhkce; //Ͷ��ת�þ���ʧ��ת�Ժ���ȿ۳����ۼƣ�
		 
		 public String getGqsszrsqkcqe() {
			return gqsszrsqkcqe;
		}
		public void setGqsszrsqkcqe(String gqsszrsqkcqe) {
			this.gqsszrsqkcqe = gqsszrsqkcqe;
		}
		public String getGqtzzrss() {
			return gqtzzrss;
		}
		public void setGqtzzrss(String gqtzzrss) {
			this.gqtzzrss = gqtzzrss;
		}
		public String getJzhkce() {
			return jzhkce;
		}
		public void setJzhkce(String jzhkce) {
			this.jzhkce = jzhkce;
		}
		public String getNstze() {
			return nstze;
		}
		public void setNstze(String nstze) {
			this.nstze = nstze;
		}
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
			this.tzmxbsj = list;
		}
		public List getDataList()
		{
			return this.tzmxbsj;
		}
		public void setDqzqtz_columns(String[] dqzqtz_columns)
		{
			this.dqzqtz_columns = dqzqtz_columns;
		}
		public String[] getDqzqtz_columns()
		{
			return this.dqzqtz_columns;
		}
		public void setCqzqtz_columns(String[] cqzqtz_columns)
		{
			this.cqzqtz_columns = cqzqtz_columns;
		}
		public String[] getCqzqtz_columns()
		{
			return this.cqzqtz_columns;
		}
		public void setDqgqtz_columns(String[] dqgqtz_columns)
		{
			this.dqgqtz_columns = dqgqtz_columns;
		}
		public String[] getDqgqtz_columns()
		{
			return this.dqgqtz_columns;
		}
		public void setCqgqtz_columns(String[] cqgqtz_columns)
		{
			this.cqgqtz_columns = cqgqtz_columns;
		}
		public String[] getCqgqtz_columns()
		{
			return this.cqgqtz_columns;
		}
		public void setDqzqtzList(List dqzqtzList)
		{
			this.dqzqtzList = dqzqtzList;
		}
		public List getDqzqtzList()
		{
			return this.dqzqtzList;
		}
		public void setCqzqtzList(List cqzqtzList)
		{
			this.cqzqtzList = cqzqtzList;
		}
		public List getCqzqtzList()
		{
			return this.cqzqtzList;
		}
		public void setDqgqtzList(List dqgqtzList)
		{
			this.dqgqtzList = dqgqtzList;
		}
		public List getDqgqtzList()
		{
			return this.dqgqtzList;
		}
		public void setCqgqtzList(List cqgqtzList)
		{
			this.cqgqtzList = cqgqtzList;
		}
		public List getCqgqtzList()
		{
			return this.cqgqtzList;
		}
	 }