package com.ttsoft.bjtax.smsb.zjyjmsb.cx.web;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.*;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: �پ�ҵ����˰�걨��ѯForm</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjyjmcxForm extends BaseForm{
	public ZjyjmcxForm(){
		
	}
	
	  /**
	   * ��ϸ��Ŀ����
	   */
	  private ArrayList dataList;

	  /**
	   * ���ط־��б�
	   */
	  private List fjList ;
	  /**
	   * ˰�����б�
	   */
	  private List swsList;
	  /**
	   * ��˰������
	   */
	  private String nsrmc;
	  /**
	   * ���������
	   */
	  private String jsjdm;
	  /**
	   * ��ѯ�־�
	   */
	  private String queryfj;

	  /**
	   * ��ѯ˰����
	   */
	  private String querysws;
	  /**
	   * ����˰����ش���
	   */
	  private String swjgdm;

	  /**
	   * ����˰��������
	   */
	  private String swsdm;
	  /**
	   * ����˰���list
	   */
	  private List jmslblist;
	  /**
	   * ����˰��List
	   */
	  private List jmszArray;
	  /**
	   * ����List
	   */
	  private List jdlist;
	  /**
	   * ���
	   */
	  private String nd;
	  /**
	   * ѡ�еļ���˰���
	   */
	  private String[] jmslb;
	  /**
	   * ѡ�еļ���˰��
	   */
	  private String jmsz;
	  /**
	   * ����
	   */
	  private String jd;
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
	public java.util.ArrayList getDataList() {
		return dataList;
	}
	public void setDataList(java.util.ArrayList dataList) {
		this.dataList = dataList;
	}
	public List getFjList() {
		return fjList;
	}
	public void setFjList(List fjList) {
		this.fjList = fjList;
	}
	public String[] getJmslb() {
		return jmslb;
	}
	public void setJmslb(String[] jmslb) {
		this.jmslb = jmslb;
	}
	public String getJmsz() {
		return jmsz;
	}
	public void setJmsz(String jmsz) {
		this.jmsz = jmsz;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public List getSwsList() {
		return swsList;
	}
	public void setSwsList(List swsList) {
		this.swsList = swsList;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getQueryfj() {
		return queryfj;
	}
	public void setQueryfj(String queryfj) {
		this.queryfj = queryfj;
	}
	public String getQuerysws() {
		return querysws;
	}
	public void setQuerysws(String querysws) {
		this.querysws = querysws;
	}
	public String getSwjgdm() {
		return swjgdm;
	}
	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}
	public String getSwsdm() {
		return swsdm;
	}
	public void setSwsdm(String swsdm) {
		this.swsdm = swsdm;
	}
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public List getJmslblist() {
		return jmslblist;
	}
	public void setJmslblist(List jmslblist) {
		this.jmslblist = jmslblist;
	}
	public List getJmszArray() {
		return jmszArray;
	}
	public void setJmszArray(List jmszArray) {
		this.jmszArray = jmszArray;
	}
	public List getJdlist() {
		return jdlist;
	}
	public void setJdlist(List jdlist) {
		this.jdlist = jdlist;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
}
