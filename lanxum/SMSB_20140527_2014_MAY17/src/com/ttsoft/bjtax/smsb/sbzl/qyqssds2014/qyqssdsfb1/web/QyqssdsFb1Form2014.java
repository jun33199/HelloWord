package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb1.web;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsFb1Form2014   
 * 类描述：   附表一：资产处置损益明细表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午3:30:24   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午3:30:24   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsFb1Form2014  extends QyqssdsBaseForm{
	
	private String[] sb_columns1 ={"hc1","zmjz","jsjc","jyjg","zcczxy"};

	public void setSb_columns1(String[] sb_columns1 )
	{
		this.sb_columns1 = sb_columns1;
	}
	public String[] getSb_columns1()
	{
		return this.sb_columns1;
	}
}
