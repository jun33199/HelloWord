/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�����˰</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsForm
    extends BaseForm {
  /**
   * ˰���������� String
   *
   * ��¼��������ȡ��
   */
  private String jsjdm;

  /**
   * �걨���� String
   */
  private String sbrq;

  /**
   * ��˰������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String nsrmc;

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
   * �걨������  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String xm;

  /**
   * �걨�˵�ַ  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String dz;

  /**
   * �걨�˿�ʼ������Ӫ����  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String scjyrq;

  /**
   * �걨�����д���  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String yhdm;

  /**
   * �걨���˺�  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String zh;

  /**
   * �걨����ϵ�绰  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String lxdh;

  /**
   * �걨���ʱ�  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String qyyb;

  /**
   * �걨��˰�������֯��������  String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String swjgzzjgdm;

  /**
   * ¼���� String
   *
   * ���ݵ�½��Ϣ����
   */
  private String lrr;

  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */
  private String fsdm;

  /**
   * �������дΡ���Ŀ���ơ����
   * ��ϸ��Ϣ��ʾ String[]
   */
  private String[] columns = {
      "hc", "xmmc", "je"};

  /**
   * ���ڴ洢��ϸ�о�����ֵ List
   */
  private List dataList = new ArrayList();

  /**
   * ��˰�����֤������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String zjhm;

  /**
   * ��˰��֤������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String zjlxdm;

  /**
   * �걨��� String
   *
   * �����걨���ڵõ�
   */
  private String nd;

  /**
   * ҵ�� String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String gjbzhydm;

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
   * ¼������
   */
  private String lrrq;

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {

    return null;
  }

  /**
   * ���ҳ��Ҫ��
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    super.reset(actionMapping, httpServletRequest);
    this.dataList.clear();
    this.nsrmc = null;
    this.xm = null;
    this.dz = null;
    this.jsjdm = null;
    this.actionType = "Show";
    this.zjlxdm = null;
    this.zjhm = null;
    this.scjyrq = null;
    this.zh = null;
    this.qyyb = null;
    this.lxdh = null;
    this.gjbzhydm = null;
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

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
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

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getDz() {
    return dz;
  }

  public void setDz(String dz) {
    this.dz = dz;
  }

  public String getScjyrq() {
    return scjyrq;
  }

  public void setScjyrq(String scjyrq) {
    this.scjyrq = scjyrq;
  }

  public String getYhdm() {
    return yhdm;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public String getZh() {
    return zh;
  }

  public void setZh(String zh) {
    this.zh = zh;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getQyyb() {
    return qyyb;
  }

  public void setQyyb(String qyyb) {
    this.qyyb = qyyb;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String[] getColumns() {
    return columns;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public String getZjhm() {
    return zjhm;
  }

  public void setZjhm(String zjhm) {
    this.zjhm = zjhm;
  }

  public String getZjlxdm() {
    return zjlxdm;
  }

  public void setZjlxdm(String zjlxdm) {
    this.zjlxdm = zjlxdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
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
