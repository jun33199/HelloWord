	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
	 * <p>Company: 清华同方软件股份有限公司</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Description:企业所得税年报</p>
	 * @author 付江霞
	 * @version 1.1
	 */
	
	public class NstzmxzjbForm 
	 extends QysdsNewForm
	 {
		/**
		 * 存放固定信息的字符串数组
		 */
		 private String[] sb_columns ={"hc","bqfss","sqkcxe","nstzje"};
		 /**
		  * 存放动态行信息的字符串数组
		  */
		 private String[] nstzzj_columns={"xm","nstzzj_bqfss","nstzzj_sqkcxe","nstzzj_nstzje",};
		 /**
		  * 存放固定行数据的LIST
		  */
		 private List nstzmxzjbsj = new ArrayList(); 
		 /**
		  * 存放动态行数据的LIST
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