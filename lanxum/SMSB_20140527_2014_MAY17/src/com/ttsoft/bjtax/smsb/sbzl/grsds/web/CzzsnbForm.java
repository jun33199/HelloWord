/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨���� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsnbForm
    extends BaseForm {
  /**
   * ���������
   */
  private String jsjdm;

  /**
   * ���
   */
  private String nd;

  /**
   * ����ʱ��
   */
  private String cjsj;

  /**
   * ¼������
   */
  private String lrrq;

  /**
   * ˰��������ʼ����
   */
  private String skssksrq;

  /**
   * ˰��������������
   */
  private String skssjsrq;

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgdm;

  /**
   * ¼����
   */
  private String lrr;

  /**
   * �걨����
   */
  private String sbrq;

  /**
   * ��ʽ����
   */
  private String fsdm;

  /**
   * ��˰������
   */
  private String nsrmc;

  /**
   * ��Ӫ��ַ��ϵ�绰
   */
  private String jydzlxdm;

  /**
   * ����
   */
  private String[] bl = {};

  /**
   * ��ϸ��������
   */
  private String[] qyColumns = {
      "qyhc", "qyxmmc", "qybqljs"};

  /**
   * �дΡ������ۼ�����֤�����ʹ��롢֤�����롢Ͷ������������������
   */
  private String[] grColumns = {
      "grhc", "grbqljs", "zjlxdm", "zjhm", "tzzxm",
      "cwfzr"};

  /**
   * ��ҵ�걨�����б�
   */
  private List qyList = new ArrayList();

  /**
   * ��ҵ��������б�
   */
  private List qyfpbl = new ArrayList();

  /**
   * ��ҵͶ�����б�
   */
  private HashMap grList = new HashMap();

  /**
   *  �����걨��Ŀ�б�
   */
  private HashMap grsbxm = new HashMap();

  /**
   * �����걨�����б�
   */
  private List grDataList = new ArrayList();

  /**
   * ���շ�ʽ
   */
  private String zsfs;

  /**
   * �Ƿ������ۺ��걨
   */
  private String iszhsb;

  /**
   * ���ش���
   */
  private String qxdm;

  public String getQxdm() {
    return this.qxdm;
  }

  public void setQxdm(String _qxdm) {
    this.qxdm = _qxdm;
  }

  public String getIszhsb() {
    return this.iszhsb;
  }

  public void setIszhsb(String _iszhsb) {
    this.iszhsb = _iszhsb;
  }

  public String[] getBl() {
    return bl;
  }

  public void setBl(String[] bl) {
    this.bl = bl;
  }

  public List getQyList() {
    return qyList;
  }

  public void setQyList(List qyList) {
    this.qyList = qyList;
  }

  public List getGrDataList() {
    return this.grDataList;
  }

  public void setGrDataList(List _grDataList) {
    this.grDataList = _grDataList;
  }

  public HashMap getGrList() {
    return grList;
  }

  public void setGrList(HashMap grList) {
    this.grList = grList;
  }

  public List getQyfpbl() {
    return this.qyfpbl;
  }

  public void setQyfpbl(List _qyfpbl) {
    this.qyfpbl = _qyfpbl;
  }

  public HashMap getGrsbxm() {
    return this.grsbxm;
  }

  public void setGrsbxm(HashMap _grsbxm) {
    this.grsbxm = _grsbxm;
  }

  public String getJydzlxdm() {
    return this.jydzlxdm;
  }

  public void setJydzlxdm(String _jydzlxdm) {
    this.jydzlxdm = _jydzlxdm;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getCjrq() {
    return cjsj;
  }

  public void setCjrq(String cjsj) {
    this.cjsj = cjsj;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
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

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String[] getQyColumns() {
    return qyColumns;
  }

  public String[] getGrColumns() {
    return grColumns;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  /**
   * ҳ��Ҫ�����
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    jsjdm = null;
    nd = null;
    cjsj = null;
    lrrq = null;
    swjgzzjgdm = null;
    lrr = null;
    fsdm = null;
    nsrmc = null;
    jydzlxdm = null;
    getGrList().clear();
    getQyList().clear();
    getGrDataList().clear();
    getGrsbxm().clear();
  }

  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
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
