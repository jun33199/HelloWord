package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:2012 ��ҵ����˰��֧���������˰�걨��</p>
 * @author wangcy
 * 
 * @version 1.0
 */
public class CzzsfzjgNbForm  extends QysdsNewForm{
	
	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ���
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje" };

	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsnbList = new ArrayList();
	
	private String SAVE_FLAG="0";//0-��ʼֵ,1-����ɹ�	
	
	private String xzqy;
	
	//һ�����˰��
	private String ybjmsl;
	
	//�����ʸ�
	private String jmzg;
	
	//˰Դ��ʶ
	private String sybs;
	
	//��˰��ʽ
	private String nsfs;
	//�ֻܷ���
	private String zfjg;
	//�Ƿ�ִ���˲�ѯ   �����ѯ�������µ�˰Դ��ʶ 1 �����ѯ��
	private String isQuery;
	//���ڱ�ʶ�Ƿ��б�������� �����ݲ�ͬ�ı�ʶ�߲�ͬ�ķ���
	private String queryFlag;
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

	public List getQysdsnbList() {
		return qysdsnbList;
	}

	public void setQysdsnbList(List qysdsnbList) {
		this.qysdsnbList = qysdsnbList;
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

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	
}
