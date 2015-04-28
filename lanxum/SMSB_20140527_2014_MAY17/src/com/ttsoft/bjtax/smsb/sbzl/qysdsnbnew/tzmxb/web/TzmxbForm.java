	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
	 * <p>Company: 清华同方软件股份有限公司</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Description:企业所得税年报</p>
	 * @author 付江霞
	 * @version 1.1
	 */
	
	public class TzmxbForm 
	 extends QysdsNewForm
	 {
		/**
		 * 固定行列内容
		 */
		 private String[] sb_columns ={"hc","btzqyszd","qybl",
				 "sl","tzsy","ynqysds","tzzrjsr","cstzcb",
				 "jscbtz","tzzrcb","tzzrsd"};
		 /**
		  * 短期债权投资子行列内容
		  */
		 private String[] dqzqtz_columns={"dqzqtz_tzzczl","dqzqtz_btzqyszd","dqzqtz_qybl","dqzqtz_sl",
				 "dqzqtz_tzsy","dqzqtz_ynqysds","dqzqtz_tzzrjsr",
				 "dqzqtz_cstzcb","dqzqtz_jscbtz","dqzqtz_tzzrcb","dqzqtz_tzzrsd"};
		 
		 /**
		  * 长期债权投资子行列内容
		  */
		 private String[] cqzqtz_columns={"cqzqtz_tzzczl","cqzqtz_btzqyszd","cqzqtz_qybl","cqzqtz_sl",
				 "cqzqtz_tzsy","cqzqtz_ynqysds","cqzqtz_tzzrjsr",
				 "cqzqtz_cstzcb","cqzqtz_jscbtz","cqzqtz_tzzrcb","cqzqtz_tzzrsd"};
		 /**
		  * 短期股权投资子行列内容
		  */
		 private String[] dqgqtz_columns={"dqgqtz_tzzczl","dqgqtz_btzqyszd","dqgqtz_qybl","dqgqtz_sl",
				 "dqgqtz_tzsy","dqgqtz_ynqysds","dqgqtz_tzzrjsr",
				 "dqgqtz_cstzcb","dqgqtz_jscbtz","dqgqtz_tzzrcb","dqgqtz_tzzrsd"};
		 /**
		  * 长期股权投资子行列内容
		  */
		 private String[] cqgqtz_columns={"cqgqtz_tzzczl","cqgqtz_btzqyszd","cqgqtz_qybl","cqgqtz_sl",
				 "cqgqtz_tzsy","cqgqtz_ynqysds","cqgqtz_tzzrjsr",
				 "cqgqtz_cstzcb","cqgqtz_jscbtz","cqgqtz_tzzrcb","cqgqtz_tzzrsd"};
		 /**
		  * 固定行列内容
		  */
		 private List tzmxbsj = new ArrayList(); 
		 /**
		  * 短期债权投资子行列内容
		  */
		 private List dqzqtzList=new ArrayList();
		 /**
		  * 长期债权投资子行列内容
		  */
		 private List cqzqtzList=new ArrayList();
		 /**
		  * 短期股权投资子行列内容
		  */
		 private List dqgqtzList=new ArrayList();
		 /**
		  * 长期股权投资子行列内容
		  */
		 private List cqgqtzList=new ArrayList();
		 
		 /**
		  * 补充资料数据
		  * @param je
		  */
		 private String gqtzzrss ; //以前年度结转在本年度税前扣除的股权投资转让损失
		 
		 private String gqsszrsqkcqe; //本年度股权投资转让损失税前扣除限额　
		 
		 private String nstze; // 投资转让净损失纳税调整额
		 
		 private String jzhkce; //投资转让净损失结转以后年度扣除金额（累计）
		 
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