/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.wqyys.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �����������ҵӪҵ˰��˰�걨�����˶����գ���ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class WqyyshdzsForm
    extends BaseForm {
  /**
   * �Ǽ��걨��ʽ����
   */
  private String fsdm;

  /**
   * ���������
   */
  private String jsjdm;

  /**
   * ¼���˴���
   */
  private String lrr;

  /**
   * ��˰������
   */
  private String nsrmc;

  /**
   * �걨����
   */
  private String sbrq;

  /**
   * ˰��������������
   */
  private String skssjsrq;

  /**
   * ˰��������ʼ����
   */
  private String skssksrq;

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgdm;

  /**
   * ˰�ִ���
   */
  private String szdm;

  /**
   * ˰������
   */
  private String szmc;

  /**
   * ��˰��������
   */
  private String zsffdm;

  /**
   * ��˰��������
   */
  private String zsffmc;

  /**
   * js����
   */
  private String scriptStr;

  /**
   * ��ϸ�����б�
   */
  private ArrayList dataList = new ArrayList();

  /**
   * ˰��˰Ŀ���롢˰��˰Ŀ���ơ�������ͬ�ɽ��Ӷ���ʡ��˶���������֧�������������˰��˰�ʡ�Ӧ��˰�����˰�����Ӧ��˰��
   */
  private String columns[] = {
      "szsmdm", "szsmmc", "sre", "htcje", "yjl",
      "hdsre", "jfzce", "hssre", "jsje", "sl", "ynse",
      "yinse", "bqybse"};

  /**
   * ���շ�ʽ
   */
  private String zsfs;

  /**
   * �Ƿ����ۺ��걨����
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

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
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

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public void setSzdm(String szdm) {
    this.szdm = szdm;
  }

  public String getSzdm() {
    return szdm;
  }

  public void setSzmc(String szmc) {
    this.szmc = szmc;
  }

  public String getSzmc() {
    return szmc;
  }

  public void setZsffdm(String zsffdm) {
    this.zsffdm = zsffdm;
  }

  public String getZsffdm() {
    return zsffdm;
  }

  public void setZsffmc(String zsffmc) {
    this.zsffmc = zsffmc;
  }

  public String getZsffmc() {
    return zsffmc;
  }

  public String getScriptStr() {
    return scriptStr;
  }

  public void setScriptStr(String scriptStr) {
    this.scriptStr = scriptStr;
  }

  public ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.nsrmc = null;
    this.jsjdm = null;

  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
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
