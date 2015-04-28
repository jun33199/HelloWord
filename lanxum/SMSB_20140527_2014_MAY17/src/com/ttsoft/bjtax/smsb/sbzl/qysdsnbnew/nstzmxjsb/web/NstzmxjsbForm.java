	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
	 * <p>Company: 清华同方软件股份有限公司</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxjsb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Description:企业所得税年报</p>
	 * @author 付江霞
	 * @version 1.1
	 */
	
	public class NstzmxjsbForm 
	 extends QysdsNewForm
	 {
		 /**
		  * 存放固定行字符串数组
		  */
		 private String[] sb_cloumns ={"hc","je"};
		 /**
		  * 存放动态行字符串数组
		  */
		 private String[] nstzjs_columns={"xm","nstzjs_je"};
		 /**
		  * 存放固定行数据list
		  */
		 private List nstzmxjsbsj = new ArrayList(); 
		 /**
		  * 存放动态行数据list
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