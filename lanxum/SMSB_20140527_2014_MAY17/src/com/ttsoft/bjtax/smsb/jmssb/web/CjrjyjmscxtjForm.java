package com.ttsoft.bjtax.smsb.jmssb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ
 * <p>Description: �����걨-�����걨����¼��-���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�
 * <p>Copyright: Copyright (c) 2005
 * <p>Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjForm extends BaseForm {
	
	/**
	 * ��ѯ���������
	 */
	private String queryJsjdm;
	
	/**
	 * ��ѯ��ҵ����
	 */
	private String queryQymc;
	
	/**
	 * ��ѯ����˰����ش���
	 */
	private String queryZgswjgdm;
	
	/**
	 * ��ѯ��ʼ����
	 */
	private String queryCxqsrq;
	
	/**
	 * ��ѯ��ֹ����
	 */
	private String queryCxjzrq;
	
	/**
	 * ��ʾ���������
	 */
	private String showJsjdm;
	
	/**
	 * ��ʾ��ҵ����
	 */
	private String showQymc;
	
	/**
	 * ��ѯ��õ���Ҫ��ʾ�����
	 */
	private List dataList = new ArrayList();
	
	/**
	 * �����Ӫҵ˰�޶�ϼ�
	 */
	private String njzyysxeTotal;
	
	/**
	 * ʵ�ʼ���Ӫҵ˰��ϼ�
	 */
	private String sjjzyyseTotal;
	
	/**
	 * ���òм�ְ�������ϼ�
	 */
	private String azcjzgrsTotal;
	
	/**
	  * ¼����˰�������֯��������
	  */
	private String headSwjgzzjgdm;
	
	/**
	  * ˰�����б�����
	  */
	private List zgswjgList = new ArrayList();
	
	/**
	  * ҳ����ʾ�ߴ�
	  */
	private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

	/**
	  * ҳ��
	  */
	private String nextPage = "1";

	/**
	  * ҳ��
	  */
	private String totalpage = "0";

	/**
	  * ҳ����ʾ��Ϣ
	  */
	private String message;

	/**
	  * ��ǰҳ��
	  */
	private String curPage;
	

	public String getQueryJsjdm() {
		return queryJsjdm;
	}

	public void setQueryJsjdm(String queryJsjdm) {
		this.queryJsjdm = queryJsjdm;
	}

	public String getQueryQymc() {
		return queryQymc;
	}

	public void setQueryQymc(String queryQymc) {
		this.queryQymc = queryQymc;
	}

	public String getQueryZgswjgdm() {
		return queryZgswjgdm;
	}

	public void setQueryZgswjgdm(String queryZgswjgdm) {
		this.queryZgswjgdm = queryZgswjgdm;
	}

	public String getQueryCxqsrq() {
		return queryCxqsrq;
	}

	public void setQueryCxqsrq(String queryCxqsrq) {
		this.queryCxqsrq = queryCxqsrq;
	}

	public String getQueryCxjzrq() {
		return queryCxjzrq;
	}

	public void setQueryCxjzrq(String queryCxjzrq) {
		this.queryCxjzrq = queryCxjzrq;
	}

	public String getShowJsjdm() {
		return showJsjdm;
	}

	public void setShowJsjdm(String showJsjdm) {
		this.showJsjdm = showJsjdm;
	}

	public String getShowQymc() {
		return showQymc;
	}

	public void setShowQymc(String showQymc) {
		this.showQymc = showQymc;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getNjzyysxeTotal() {
		return njzyysxeTotal;
	}

	public void setNjzyysxeTotal(String njzyysxeTotal) {
		this.njzyysxeTotal = njzyysxeTotal;
	}

	public String getSjjzyyseTotal() {
		return sjjzyyseTotal;
	}

	public void setSjjzyyseTotal(String sjjzyyseTotal) {
		this.sjjzyyseTotal = sjjzyyseTotal;
	}

	public String getAzcjzgrsTotal() {
		return azcjzgrsTotal;
	}

	public void setAzcjzgrsTotal(String azcjzgrsTotal) {
		this.azcjzgrsTotal = azcjzgrsTotal;
	}

	public String getHeadSwjgzzjgdm() {
		return headSwjgzzjgdm;
	}

	public void setHeadSwjgzzjgdm(String headSwjgzzjgdm) {
		this.headSwjgzzjgdm = headSwjgzzjgdm;
	}

	public List getZgswjgList() {
		return zgswjgList;
	}

	public void setZgswjgList(List zgswjgList) {
		this.zgswjgList = zgswjgList;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	
}
