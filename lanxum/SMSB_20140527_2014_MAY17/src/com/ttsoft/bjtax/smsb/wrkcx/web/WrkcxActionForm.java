package com.ttsoft.bjtax.smsb.wrkcx.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.*;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WrkcxActionForm
    extends BaseForm
    implements Serializable {
  public WrkcxActionForm() {
  }

  /**
   * ��ϸ��Ŀ����
   */
  private java.util.ArrayList dataList;

  /**
   * �־��б�����
   */
  private List fjList = new ArrayList();

  /**
   * ˰�����б�����
   */
  private List swsList = new ArrayList();
  /** �����б�*/
  private ArrayList jxList = null;
  /**
   * ��ѯ������������
   */
  private String queryjx;

  /**
   * ��ѯ������������
   */
  private String jsjdm;

  /**
   * ��ѯ������������
   */
  private String yhjb;

  /**
   * ��ѯ�걨������
   */
  private String sbrqq;

  /**
   * ��ѯ�걨����ֹ
   */
  private String sbrqz;

  /**
   * ˰�����ʹ���
   */
  private String[] sklxdm;

  /**
   * �ɿ�ƾ֤��
   */
  private String jkpzh;

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
   private String[] queryDjzclx;

  /**
   * ��ѯ�Ǽ�ע������2
   */
  private String queryDjzclx2;

  /**
   *�������б�ѡ�Ǽ�ע�����ʹ���
   */
  private String alldjzclx;

  /**
   * �Ǽ�ע�������б�
   */
  private List djzclx = new ArrayList();

  /**
   * �Ǽ�ע������û�б�ѡ�б�
   */
  private List djzclx2 = new ArrayList();

  /**
   * ��ѯ�־�
   */
  private String queryfj;

  /**
   * ��ѯ˰����
   */
  private String querysws;
  /**
   * �޽�����
   */
  private String xjrq;
  /**
   * �ܱ���
   */
  private String bs;
  /**
   * �걨���ϼ�
   */
  private String sbjehj;
  /**
   * �����ϼ�
   */
  private String rkjehj;
  /**
   * ���ϼ�
   */
  private String cehj;
  /**
   * ��ѯ��ϸ��Ϣ�ļ��������
   */
  private String dj_jsjdm;
  /**
   * ����˰����ش���
   */
  private String swjgdm;

  /**
   * ����˰��������
   */
  private String swsdm;

  Map zgswjg;
  Map zgsws;
  Map scjx;
  String hs;
  private List sklxlist = new ArrayList();
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);
    this.nextPage = "1";
    this.totalpage = "0";
    this.message = "";
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  /**
   * �������ݼ�
   * @param dataList
   */
  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public List getDjzclx2() {
    return djzclx2;
  }

  public void setDjzclx2(List djzclx2) {
    this.djzclx2 = djzclx2;
  }

  public String getCurPage() {
    return curPage;
  }

  public void setCurPage(String curPage) {
    this.curPage = curPage;
  }

  public List getFjList() {
    return fjList;
  }

  public void setFjList(List fjList) {
    this.fjList = fjList;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
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

  public String[] getQueryDjzclx() {
    return queryDjzclx;
  }

  public void setQueryDjzclx(String[] queryDjzclx) {
    this.queryDjzclx = queryDjzclx;
  }

  public String getSbrqq() {
    return sbrqq;
  }

  public String getSbrqz() {
    return sbrqz;
  }

  public void setSbrqz(String sbrqz) {
    this.sbrqz = sbrqz;
  }

  public void setSbrqq(String sbrqq) {
    this.sbrqq = sbrqq;
  }

  public String[] getSklxdm() {
    return sklxdm;
  }

  public void setSklxdm(String[] sklxdm) {
    this.sklxdm = sklxdm;
  }

  public List getSwsList() {
    return swsList;
  }

  public void setSwsList(List swsList) {
    this.swsList = swsList;
  }

  public String getTotalpage() {
    return totalpage;
  }

  public String getYhjb() {
    return yhjb;
  }

  public void setYhjb(String yhjb) {
    this.yhjb = yhjb;
  }

  public void setTotalpage(String totalpage) {
    this.totalpage = totalpage;
  }

  public List getDjzclx() {
    return djzclx;
  }

  public void setDjzclx(List djzclx) {
    this.djzclx = djzclx;
  }

  public String getQuerysws() {
    return querysws;
  }

  public String getQueryfj() {
    return queryfj;
  }

  public void setQueryfj(String queryfj) {
    this.queryfj = queryfj;
  }

  public void setQuerysws(String querysws) {
    this.querysws = querysws;
  }

  public String getQueryDjzclx2() {
    return queryDjzclx2;
  }

  public void setQueryDjzclx2(String queryDjzclx2) {
    this.queryDjzclx2 = queryDjzclx2;
  }

  public String getAlldjzclx() {
    return alldjzclx;
  }

  public void setAlldjzclx(String alldjzclx) {
    this.alldjzclx = alldjzclx;
  }

  public ArrayList getJxList() {
    return jxList;
  }

  public void setJxList(ArrayList jxList) {
    this.jxList = jxList;
  }

  public String getQueryjx() {
    return queryjx;
  }

  public void setQueryjx(String queryjx) {
    this.queryjx = queryjx;
  }

  public String getXjrq() {
    return xjrq;
  }

  public void setXjrq(String xjrq) {
    this.xjrq = xjrq;
  }

  public String getBs() {
    return bs;
  }

  public String getCehj() {
    return cehj;
  }

  public void setBs(String bs) {
    this.bs = bs;
  }

  public void setCehj(String cehj) {
    this.cehj = cehj;
  }

  public String getRkjehj() {
    return rkjehj;
  }

  public String getSbjehj() {
    return sbjehj;
  }

  public void setRkjehj(String rkjehj) {
    this.rkjehj = rkjehj;
  }

  public void setSbjehj(String sbjehj) {
    this.sbjehj = sbjehj;
  }

  public List getSklxlist() {
    return sklxlist;
  }

  public void setSklxlist(List sklxlist) {
    this.sklxlist = sklxlist;
  }

  public String getDj_jsjdm() {
    return dj_jsjdm;
  }

  public void setDj_jsjdm(String dj_jsjdm) {
    this.dj_jsjdm = dj_jsjdm;
  }

  public Map getZgswjg() {
    return zgswjg;
  }

  public Map getZgsws() {
    return zgsws;
  }

  public void setZgswjg(Map zgswjg) {
    this.zgswjg = zgswjg;
  }

  public void setZgsws(Map zgsws) {
    this.zgsws = zgsws;
  }
  public String getSwjgdm() {
    return swjgdm;
  }
  public String getSwsdm() {
    return swsdm;
  }
  public void setSwjgdm(String swjgdm) {
    this.swjgdm = swjgdm;
  }
  public void setSwsdm(String swsdm) {
    this.swsdm = swsdm;
  }
  public Map getScjx() {
    return scjx;
  }
  public void setScjx(Map scjx) {
    this.scjx = scjx;
  }
  public String getHs() {
    return hs;
  }
  public void setHs(String hs) {
    this.hs = hs;
  }

}