package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ</p>
 * <p>Description: �����걨-�걨����-��ҵ��������ָ����걨���Ȳ�ѯ</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class QyjbcwzbCxActionForm
    extends BaseForm {

  /**
   * �����б�����
   */
  private List dataList = new ArrayList();

  /**
   * �����б�����1
   */
  private List dataList1 = new ArrayList();

  /**
   * ˰�����б�����
   */
  private List swsList = new ArrayList();

  /**
   * �־��б�����
   */
  private List fjList = new ArrayList();
  
  /**
   * �걨���
   */
  private String nd;

  /**
   * ��ҵ״̬�б�
   */
  private List qyztList = new ArrayList();

  /**
   * ¼����
   */
  private String headLrr;

  /**
   * ¼���˼���
   */
  private String headLrrjb;

  /**
   * ¼����˰�������֯��������
   */
  private String headSwjgzzjgdm;

  /**
   * ˰�������֯��������
   */
  private String headSwjgzzjgmc;

  /**
   * ��ǰ����
   */
  private String headDqrq;

  /**
   * ��ѯ�־�
   */
  private String queryFj;

  /**
   * ��ѯ˰����
   */
  private String querySwjgzzjg;

  /**
   * ��ѯ��ҵ״̬
   */
  private String queryQyzt;

  /**
   * ��ѯ������������
   */
  private String queryJsjdm;

  /**
   * ��ѯ�걨������
   */
  private String querySbrqq;

  /**
   * ��ѯ�걨����ֹ
   */
  private String querySbrqz;

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

  //*******add by zhangshubing***********
   /**
    * ��ѯ�Ǽ�ע������
    */
   private String queryDjzclx;
  /**
   * �걨�����б�
   */
  private List sblxList = new ArrayList();

  /**
   * �Ǽ�ע�������б�
   */
  private List djzclx = new ArrayList();

  /**
   * �Ǽ�ע������û�б�ѡ�б�
   */
  private List djzclx2 = new ArrayList();

  /**
   * �걨���Ͳ�ѯ
   */
  private String querySblx;

  /**
   * �걨���Ͳ�ѯ
   */
  private String queryDjzclx2;

  /**
   *�������б�ѡ�Ǽ�ע�����ʹ���
   */
  private String alldjzclx;
//*************************************
  
  private String showFjQueryButton="1";
  
  private String showSjQueryButton="1";

   public void reset(ActionMapping actionMapping,
                     HttpServletRequest httpServletRequest) {
     this.pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);
     this.nextPage = "1";
     this.totalpage = "0";
     this.message = "";
   }

  public List getDataList() {
    return dataList;
  }

  public String getNd() {
    return nd;
  }
  
  public void setNd(String nd){
	  this.nd=nd;
  }

  public String getHeadDqrq() {
    return headDqrq;
  }

  public String getHeadLrr() {
    return headLrr;
  }

  public String getHeadLrrjb() {
    return headLrrjb;
  }

  public String getHeadSwjgzzjgdm() {
    return headSwjgzzjgdm;
  }

  public String getHeadSwjgzzjgmc() {
    return headSwjgzzjgmc;
  }

  public String getTotalpage() {
    return totalpage;
  }

  public String getNextPage() {
    return nextPage;
  }

  public String getPageSize() {
    return pageSize;
  }

  public String getQueryJsjdm() {
    return queryJsjdm;
  }

  public String getQuerySbrqq() {
    return querySbrqq;
  }

  public String getQuerySbrqz() {
    return querySbrqz;
  }

  public List getSwsList() {
    return swsList;
  }

  public List getFjList() {
    return fjList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public void setHeadDqrq(String headDqrq) {
    this.headDqrq = headDqrq;
  }

  public void setHeadLrr(String headLrr) {
    this.headLrr = headLrr;
  }

  public void setHeadLrrjb(String headLrrjb) {
    this.headLrrjb = headLrrjb;
  }

  public void setHeadSwjgzzjgdm(String headSwjgzzjgdm) {
    this.headSwjgzzjgdm = headSwjgzzjgdm;
  }

  public void setHeadSwjgzzjgmc(String headSwjgzzjgmc) {
    this.headSwjgzzjgmc = headSwjgzzjgmc;
  }

  public void setTotalpage(String totalpage) {
    this.totalpage = totalpage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public void setQueryJsjdm(String queryJsjdm) {
    this.queryJsjdm = queryJsjdm;
  }

  public void setQuerySbrqq(String querySbrqq) {
    this.querySbrqq = querySbrqq;
  }

  public void setQuerySbrqz(String querySbrqz) {
    this.querySbrqz = querySbrqz;
  }

  public void setSwsList(List swsList) {
    this.swsList = swsList;
  }

  public void setFjList(List fjList) {
    this.fjList = fjList;
  }

  public List getQyztList() {
    return qyztList;
  }

  public void setQyztList(List qyztList) {
    this.qyztList = qyztList;
  }

  //********add by zhangshubing***********
   public void setSblxList(List sblxList) {
     this.sblxList = sblxList;
   }

  public void setDjzclx(List djzclx) {
    this.djzclx = djzclx;
  }

  public List getSblxList() {
    return sblxList;
  }

  public List getDjzclx() {
    return djzclx;
  }

  public String getQueryDjzclx() {
    return queryDjzclx;
  }

  public void setQueryDjzclx(String queryDjzclx) {
    this.queryDjzclx = queryDjzclx;
  }

  public String getQuerySblx() {
    return querySblx;
  }

  public void setQuerySblx(String querySblx) {
    this.querySblx = querySblx;
  }

  public void setAlldjzclx(String alldjzclx) {
    this.alldjzclx = alldjzclx;
  }

  public String getAlldjzclx() {
    return alldjzclx;
  }

  public void setDjzclx2(List djzclx2) {
    this.djzclx2 = djzclx2;
  }

  public List getDjzclx2() {
    return djzclx2;
  }

  public void setQueryDjzclx2(String queryDjzclx2){
    this.queryDjzclx2 = queryDjzclx2;
  }

  public String getQueryDjzclx2(){
    return queryDjzclx2;
  }


  //*************************************


  public String getQueryQyzt() {
    return queryQyzt;
  }

  public String getQuerySwjgzzjg() {
    return querySwjgzzjg;
  }

  public String getQueryFj() {
    return queryFj;
  }

  public void setQueryQyzt(String queryQyzt) {
    this.queryQyzt = queryQyzt;
  }

  public void setQuerySwjgzzjg(String querySwjgzzjg) {
    this.querySwjgzzjg = querySwjgzzjg;
  }

  public void setQueryFj(String queryFj) {
    this.queryFj = queryFj;
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

public List getDataList1() {
	return dataList1;
}

public void setDataList1(List dataList1) {
	this.dataList1 = dataList1;
}

public String getShowFjQueryButton() {
	return showFjQueryButton;
}

public void setShowFjQueryButton(String showFjQueryButton) {
	this.showFjQueryButton = showFjQueryButton;
}

public String getShowSjQueryButton() {
	return showSjQueryButton;
}

public void setShowSjQueryButton(String showSjQueryButton) {
	this.showSjQueryButton = showSjQueryButton;
}

}