/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�Ӫҵ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrForm
    extends BaseForm {
  /**
   * ˰���������� String
   *
   * ��¼��������ȡ��
   */
  private String jsjdm;

  /**
   * ����ʱ�� String
   */
  /**
   * �걨���� String
   */
  private String sbrq;

  /**
   * ˰��Ǽ�֤�� String
   *
   * ��¼��˰�����������ѯ������ȡ��
   */
  private String swdjzh;

  /**
   * ��˰������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String nsrmc;

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgdm;

  /**
   * ¼������ String
   */
  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */
  private String fsdm;

  /**
   * ¼���˴��� String
   *
   * �ӵ�¼������ȡ��
   */
  private String lrrdm;

  /**
   * ������˰��˰Ŀ���롢˰��˰Ŀ���ơ����ڿ��߷�Ʊ������δ���߷�Ʊ������Ӫҵ������ϡ�����Ӫҵ����
   * ��ϸ��Ϣ��ʾ String[]
   */
  private String[] columns = {
      "szsmdm", "szsmmc", "dqkjfpje", "dqwkjfpje",
      "dqyysrjehj", "jzyysr"};

  /**
   * ���ڴ洢��ϸ�о�����ֵ List
   */
  private List dataList = new ArrayList();

  /**
   * ˰��������ʼ���� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssksrq;

  /**
   * ˰�������������� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssjsrq;

  /**
   * �걨�������
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String nd;

  /**
   * �Ƿ������ۺ��걨
   */
  private String iszhsb;

  /**
   * ���ش���
   */
  private String qxdm;

  /**
   * ��������
   */
  private String cjrq;

  /**
   *¼������
   */
  private String lrrq;

  /**
   * �걨����
   */
  private String tempSbrq;

  /**
   * ���������
   */
  private String tempJsjdm;

  /**
   * ҳ�����
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.jsjdm = null;
    this.nsrmc = null;
    this.actionType = "Show";
    this.swdjzh = null;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSwdjzh() {
    return swdjzh;
  }

  public void setSwdjzh(String swdjzh) {
    this.swdjzh = swdjzh;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getLrrdm() {
    return lrrdm;
  }

  public void setLrrdm(String lrrdm) {
    this.lrrdm = lrrdm;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getIszhsb() {
    return iszhsb;
  }

  public void setIszhsb(String iszhsb) {
    this.iszhsb = iszhsb;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public String getTempSbrq() {
    return tempSbrq;
  }

  public void setTempSbrq(String tempSbrq) {
    this.tempSbrq = tempSbrq;
  }

  public String getTempJsjdm() {
    return tempJsjdm;
  }

  public void setTempJsjdm(String tempJsjdm) {
    this.tempJsjdm = tempJsjdm;
  }

  /* start added by huxiaofeng 2005.8.16*/
  /**
   * ��˰��״̬
   */
  private String nsrzt; //��˰��״̬

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public String getNsrzt() {
    return nsrzt;
  }

  /* end added by huxiaofeng 2005.8.16*/

}
