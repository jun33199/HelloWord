	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
	 * <p>Company: 清华同方软件股份有限公司</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbsydw.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Description:企业所得税年报</p>
	 * @author 付江霞
	 * @version 1.1
	 */
	
	public class CbmxbsydwForm 
	 extends QysdsNewForm
	 {
		/**
		 * 存放页面行次和金额的数组
		 */
		 private String[] sb_columns ={"hc","je"};
		 
		 /**
		  * 存放数据结果list
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