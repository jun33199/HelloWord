package com.ttsoft.bjtax.smsb.gghsb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: ���ڶ����������ѯForm</p>
 * @author zhou jinguang
 * @version 1.0
 */

public class DqdehrkcxForm
    extends BaseForm {
  public DqdehrkcxForm() {
    dataList = new ArrayList();
  }

  /**
   * ��ϸ��Ŀ����
   */
  private java.util.ArrayList dataList;

  /**
   * ��ҳ��ÿҳ��¼��
   */
  private int length;

  /**
   * ��ҳ����ǰҳ��
   */
  private int pgNum;

  /**
   * ��ҳ����ҳ��
   */
  private int pgSum;

  /**
   * ���������
   */
  private String jsjdm;

  /**
   * ��˰��״̬
   */
  private String nsrzt;

  /**
   * ���ط־�
   */
  private String qx;

  /**
   * ˰����
   */
  private String swjs;

  /**
   * ��������
   */
  private String jx;

  /**
   * ˰��
   */
  private String sz;

  /**
   * �϶����
   */
  private String nd;

  /**
   * ��˰����
   */
  private String nsqj;

  /**
 * ��˰����list
 */
  private List nsqjList = null;

  /**
   * �Ǽ�ע������
   */
  private String[] dkzclx;

  /**
   * ��ⷽʽ
   */
  private String rkfs;

  /**
   * ������
   */
  private String rkqk;

  /**
   * ��ʼ����
   */
  private String fromzq;

  /**
   * ��������
   */
  private String endzq;

  /**
   * �����б�
   */
  private ArrayList jxList = null;

  /**
   * ˰�����б�
   */
  private ArrayList swjsList = null;

  /**
   * ��˰��״̬list
   */
  private List nsrztList = null;

  /**
   * ��ⷽʽlist
   */
  private List rkfsList = null;

  /**
   * ������list
   */
  private List rkqkList = null;

  /**
   * �����б�
   */
  private ArrayList qxList = null;

  /**�˶����ϼ�*/
  private String hdjehj;
  /** �ɿ���ϼ� */
  private String jkjehj;
  /**��¼��*/
  private String jls;

  /**����ѯ��Ϣ�������� */
  private String[] dataListTitle;
  /**����ѯ��Ϣkey */
  private String[] dataListKey;
  /**�ϼ���Ϣ���ݼ�*/
  private List sumList;
  /** �ϼ���Ϣ����*/
  private String[] sumListTitle;
  /** �ϼ���Ϣkey*/
  private String[] sumListKey;

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    pgNum = 0;
    pgSum = 0;
    length = 0;
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public String[] getDkzclx() {
    return dkzclx;
  }

  public void setDkzclx(String[] dkzclx) {
    this.dkzclx = dkzclx;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getJx() {
    return jx;
  }

  public void setJx(String jx) {
    this.jx = jx;
  }

  public ArrayList getJxList() {
    return jxList;
  }

  public void setJxList(ArrayList jxList) {
    this.jxList = jxList;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getNd() {
    return nd;
   }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getNsrzt() {
    return nsrzt;
  }

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public int getPgNum() {
    return pgNum;
  }

  public void setPgNum(int pgNum) {
    this.pgNum = pgNum;
  }

  public int getPgSum() {
    return pgSum;
  }

  public void setPgSum(int pgSum) {
    this.pgSum = pgSum;
  }

  public String getQx() {
    return qx;
  }

  public void setQx(String qx) {
    this.qx = qx;
  }

  public String getRkfs() {
    return rkfs;
  }

  public void setRkfs(String rkfs) {
    this.rkfs = rkfs;
  }

  public String getSwjs() {
    return swjs;
  }

  public void setSwjs(String swjs) {
    this.swjs = swjs;
  }

  public ArrayList getSwjsList() {
    return swjsList;
  }

  public void setSwjsList(ArrayList swjsList) {
    this.swjsList = swjsList;
  }

  public String getSz() {
    return sz;
  }

  public void setSz(String sz) {
    this.sz = sz;
  }

  public String getEndzq() {
    return endzq;
  }

  public void setEndzq(String endzq) {
    this.endzq = endzq;
  }

  public String getFromzq() {
    return fromzq;
  }

  public void setFromzq(String fromzq) {
    this.fromzq = fromzq;
  }

  public List getNsrztList() {
    return nsrztList;
  }

  public void setNsrztList(List nsrztList) {
    this.nsrztList = nsrztList;
  }

  public List getRkfsList() {
    return rkfsList;
  }

  public void setRkfsList(List rkfsList) {
    this.rkfsList = rkfsList;
  }

  public String[] getDataListKey() {
    return dataListKey;
  }

  public void setDataListKey(String[] dataListKey) {
    this.dataListKey = dataListKey;
  }

  public String[] getDataListTitle() {
    return dataListTitle;
  }

  public void setDataListTitle(String[] dataListTitle) {
    this.dataListTitle = dataListTitle;
  }

  public String getHdjehj() {
    return hdjehj;
  }

  public void setHdjehj(String hdjehj) {
    this.hdjehj = hdjehj;
  }

  public String getJkjehj() {
    return jkjehj;
  }

  public void setJkjehj(String jkjehj) {
    this.jkjehj = jkjehj;
  }

  public String getJls() {
    return jls;
  }

  public void setJls(String jls) {
    this.jls = jls;
  }

  public List getSumList() {
    return sumList;
  }

  public void setSumList(List sumList) {
    this.sumList = sumList;
  }

  public String[] getSumListKey() {
    return sumListKey;
  }

  public void setSumListKey(String[] sumListKey) {
    this.sumListKey = sumListKey;
  }

  public String[] getSumListTitle() {
    return sumListTitle;
  }

  public void setSumListTitle(String[] sumListTitle) {
    this.sumListTitle = sumListTitle;
  }

  public ArrayList getQxList() {
    return qxList;
  }

  public void setQxList(ArrayList qxList) {
    this.qxList = qxList;
  }

  public List getRkqkList() {
    return rkqkList;
  }


  public void setRkqkList(List rkqkList) {
    this.rkqkList = rkqkList;
  }

//add by hsm


  public String getNsqj() {
    return nsqj;
  }

  public void setNsqj(String nsqj) {
    this.nsqj = nsqj;
  }


  public void setNsqjList(List nsqjList) {
    this.nsqjList = nsqjList;
  }

  public List getNsqjList() {
    return nsqjList;
  }

//hsm code finish

  public String getRkqk() {
    return rkqk;
  }

  public void setRkqk(String rkqk) {
    this.rkqk = rkqk;
  }
}