package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web;


import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsZbForm2014   
 * 类描述：    附表三：剩余财产计算和分配明细表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:25:43   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:25:43   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsFb3Form2014 extends QyqssdsBaseForm {
	public QyqssdsFb3Form2014() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、值 String[]
	 */
	private String[] zb_columns = { "hc", "ljje" };

	/**
     * 剩余财产计算和分配明细表项目代码 股东名称、持有清算企业权益性投资比例（%）、投资额、分配的财产金额、其中：确认为股息金额 String[]
     */
    private String[] syccfp_columns = { "gdmc", "tzbl", "tze", "ccje", "gxje" };

    /**
     * 所得税季报数据
     */
    private List syccfpList = new ArrayList();
	 /**
     * 明细数据据最大index
     */
    private int maxIndex;
    
	public String[] getColumns() {
		return zb_columns;
	}

	public void setColumns(String[] zb_columns) {
		this.zb_columns = zb_columns;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public String[] getSyccfp_columns() {
		return syccfp_columns;
	}

	public void setSyccfp_columns(String[] syccfp_columns) {
		this.syccfp_columns = syccfp_columns;
	}

	public List getSyccfpList() {
		return syccfpList;
	}

	public void setSyccfpList(List syccfpList) {
		this.syccfpList = syccfpList;
	}
	
}