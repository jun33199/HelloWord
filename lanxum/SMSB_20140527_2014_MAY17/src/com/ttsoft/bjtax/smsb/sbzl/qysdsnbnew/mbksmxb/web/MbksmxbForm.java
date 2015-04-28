	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
	 * <p>Company: 清华同方软件股份有限公司</p>
	 */
	
	package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mbksmxb.web;
	

	
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;
	
	/**
	 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
	 * <p>Description:企业所得税年报</p>
	 * @author 付江霞
	 * @version 1.1
	 */
	
	public class MbksmxbForm 
	 extends QysdsNewForm
	 {
		/**
		 * 存放页面信息的字符串叔数组
		 */
		 private String[] sb_columns ={"hc","nd","kshyle","kmbkse","hj",
				 "d2n","d3n","d4n","d5n","ymbgksehj","bnkmbkse","xynmbkse"};
		 /**
		  * 存放数据结果的List
		  */
		 private List mbksmxbsj = new ArrayList(); 
		 
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
		
	 }