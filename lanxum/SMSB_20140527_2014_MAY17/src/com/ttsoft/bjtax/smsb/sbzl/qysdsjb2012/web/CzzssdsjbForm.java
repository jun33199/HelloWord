package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:2008����������ҵ����˰����</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class CzzssdsjbForm  extends QysdsNewForm{
	
	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ���
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje" };

	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsjbList = new ArrayList();
	
	private String SAVE_FLAG="0";//0-��ʼֵ,1-����ɹ�	
	
	private String xzqy;
	
	//һ�����˰��
	private String ybjmsl;
	
	//�����ʸ�
	private String jmzg;
	
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}

	//��ȡ���ݿ��е���˰�������ֻܷ�������ֵ
	private HashMap nsfs_fsjg = new HashMap();
	
	public String getJmzg() {
		return jmzg;
	}
	
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}
	
	public String getXzqy() {
		return xzqy;
	}
	
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}
	
	public void setYbjmsl(String ybjmsl) {
		this.xzqy = ybjmsl;
	}
	
	public String[] getColumns() {
		return hdjb_columns;
	}

	public void setColumns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public List getQysdsjbList() {
		return qysdsjbList;
	}

	public void setQysdsjbList(List qysdsjbList) {
		this.qysdsjbList = qysdsjbList;
	}

	/**
	 * @return Returns the sAVE_FLAG.
	 */
	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	/**
	 * @param save_flag The sAVE_FLAG to set.
	 */
	public void setSAVE_FLAG(String save_flag) {
		SAVE_FLAG = save_flag;
	}

}
