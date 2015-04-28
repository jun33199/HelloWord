package com.ttsoft.bjtax.smsb.wszm.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.framework.form.BaseForm;

/**
*
* <p>Title:作废完税证明的form </p>
*
* <p>Description:作废完税证明的form </p>
*
* <p>Copyright: Copyright (c) 2013</p>
*
* <p>Company: </p>
*
* @author tujb
* @version 1.0
*/
public class WszmCxForm extends BaseForm
{

	/**
     * 页面数据集
     */
    private ArrayList dataList = new ArrayList();
    
	/**
     * 页面列表明细元素
     */
    private String columns[] =
        {
        "pzzldm", "wszh", "swjgzzjgdm",
        "nsrsbh", "nsrmc", "hjje", "ndzb", "printflag", "cjrq", "lrrq", "dybz", "yxbz", "yxflag"};
	
	/**
     * 完税证号码，撤销使用
     */
    private String headWszh;
    
    /**
     * 票征种类代码，查询使用
     */
    private String headPzzldm;
    
    /**
     * 帐户代码，查询使用
     */
    private String headZhdm;
    
    /**
     * 录入人，查询使用
     */
    private String headLrr;
    
    /**
     * 年度字别，查询使用
     */
    private String headNdzb;
    
    /**
     * 处理标记代码，设置打印标记使用
     */
    private String headClbjdm;

    /**
     * 年度字别，临时使用
     */
    private String tempNdzb;
    
    /**
     * 完税证号，临时使用
     */
    private String tempWszh;
    
    /**
     * 处理标记代码，临时使用
     */
    private String tempClbjdm;
    
    /**
     * 起始日期（输入条件）
     */
    private String query_qsrq;
    
    /**
     * 截止日期（输入条件）
     */
    private String query_jzrq;
    
    public WszmCxForm ()
    {
        Calendar c = Calendar.getInstance();
        //tempNdzb = "" + c.get(Calendar.YEAR);
    }
    
    public Object getData()
	 {
		 Map dataMap = new HashMap();
		 dataMap.put("tempWszh",this.tempWszh);
		 dataMap.put("tempNdzb",this.tempNdzb);
		 dataMap.put("tempClbjdm",this.tempClbjdm);
		 dataMap.put("query_qsrq",this.query_qsrq);
		 dataMap.put("query_jzrq",this.query_jzrq);
		 
		 return dataMap;
	 }

	public String getHeadWszh() {
		return headWszh;
	}

	public void setHeadWszh(String headWszh) {
		this.headWszh = headWszh;
	}

	public String getHeadPzzldm() {
		return headPzzldm;
	}

	public void setHeadPzzldm(String headPzzldm) {
		this.headPzzldm = headPzzldm;
	}

	public String getHeadZhdm() {
		return headZhdm;
	}

	public void setHeadZhdm(String headZhdm) {
		this.headZhdm = headZhdm;
	}

	public String getHeadLrr() {
		return headLrr;
	}

	public void setHeadLrr(String headLrr) {
		this.headLrr = headLrr;
	}

	public String getHeadNdzb() {
		return headNdzb;
	}

	public void setHeadNdzb(String headNdzb) {
		this.headNdzb = headNdzb;
	}

	public String getHeadClbjdm() {
		return headClbjdm;
	}

	public void setHeadClbjdm(String headClbjdm) {
		this.headClbjdm = headClbjdm;
	}

	public String getTempNdzb() {
		return tempNdzb;
	}

	public void setTempNdzb(String tempNdzb) {
		this.tempNdzb = tempNdzb;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}

	public String getTempWszh() {
		return tempWszh;
	}

	public void setTempWszh(String tempWszh) {
		this.tempWszh = tempWszh;
	}

	public String getQuery_qsrq() {
		return query_qsrq;
	}

	public void setQuery_qsrq(String query_qsrq) {
		this.query_qsrq = query_qsrq;
	}

	public String getQuery_jzrq() {
		return query_jzrq;
	}

	public void setQuery_jzrq(String query_jzrq) {
		this.query_jzrq = query_jzrq;
	}

	public String getTempClbjdm() {
		return tempClbjdm;
	}

	public void setTempClbjdm(String tempClbjdm) {
		this.tempClbjdm = tempClbjdm;
	}
    
    
    
}
