package com.ttsoft.bjtax.smsb.wszm.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.framework.form.BaseForm;

/**
*
* <p>Title:������˰֤����form </p>
*
* <p>Description:������˰֤����form </p>
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
     * ҳ�����ݼ�
     */
    private ArrayList dataList = new ArrayList();
    
	/**
     * ҳ���б���ϸԪ��
     */
    private String columns[] =
        {
        "pzzldm", "wszh", "swjgzzjgdm",
        "nsrsbh", "nsrmc", "hjje", "ndzb", "printflag", "cjrq", "lrrq", "dybz", "yxbz", "yxflag"};
	
	/**
     * ��˰֤���룬����ʹ��
     */
    private String headWszh;
    
    /**
     * Ʊ��������룬��ѯʹ��
     */
    private String headPzzldm;
    
    /**
     * �ʻ����룬��ѯʹ��
     */
    private String headZhdm;
    
    /**
     * ¼���ˣ���ѯʹ��
     */
    private String headLrr;
    
    /**
     * ����ֱ𣬲�ѯʹ��
     */
    private String headNdzb;
    
    /**
     * �����Ǵ��룬���ô�ӡ���ʹ��
     */
    private String headClbjdm;

    /**
     * ����ֱ���ʱʹ��
     */
    private String tempNdzb;
    
    /**
     * ��˰֤�ţ���ʱʹ��
     */
    private String tempWszh;
    
    /**
     * �����Ǵ��룬��ʱʹ��
     */
    private String tempClbjdm;
    
    /**
     * ��ʼ���ڣ�����������
     */
    private String query_qsrq;
    
    /**
     * ��ֹ���ڣ�����������
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
