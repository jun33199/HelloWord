package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web;


import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsZbForm2014   
 * ��������    ��������ʣ��Ʋ�����ͷ�����ϸ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:25:43   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:25:43   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsFb3Form2014 extends QyqssdsBaseForm {
	public QyqssdsFb3Form2014() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ�ֵ String[]
	 */
	private String[] zb_columns = { "hc", "ljje" };

	/**
     * ʣ��Ʋ�����ͷ�����ϸ����Ŀ���� �ɶ����ơ�����������ҵȨ����Ͷ�ʱ�����%����Ͷ�ʶ����ĲƲ������У�ȷ��Ϊ��Ϣ��� String[]
     */
    private String[] syccfp_columns = { "gdmc", "tzbl", "tze", "ccje", "gxje" };

    /**
     * ����˰��������
     */
    private List syccfpList = new ArrayList();
	 /**
     * ��ϸ���ݾ����index
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