/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

//import java.math.BigDecimal;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbForm
    extends BaseForm {
  /**
   * ����ʱ�� String
   */
  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */
  private String fsdm;

  /**
   * �걨���ڵļ��� String
   */
  private String jd;

  /**
   * ˰���������� String
   *
   * ��¼��������ȡ��
   */
  private String jsjdm;

  /**
   * ¼���˴��� String
   *
   * �ӵ�¼������ȡ��
   */
  private String lrr;

  /**
   * ¼������ String
   */
  /**
   * �걨��� String
   */
  private String nd;

  /**
   * �걨���� String
   */
  private String sbrq;

  /**
   * ˰�������������� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssjsrq;

  /**
   * ˰��������ʼ���� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssksrq;

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgdm;

  /**
   * �����������ܶ�����ܶ����˰�ʡ�Ӧ������˰��ڳ�δ������˰���������˰��鲹��ǰ���˰�ʵ���ѽ�������˰������걨�ӽ�����˰�ʵ��Ӧ�����ˣ�����˰��
   * ��ϸ��Ϣ��ʾ String[]
   */
  private String columns[] = {
      "srze", "lrze", "sl", "ynsdse", "qcwjsdse",
      "jmsdse", "cbyqndse", "sjyjsdsse", "bqyjsdse",
      "sjybsdse", "bkhlrze", "mbyqndks"};

  /**
   * ���ڴ洢��ϸ�о�����ֵ List
   */
  private List dataList = new ArrayList();

  /**
   * ��˰������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String nsrmc;

  /**
   * �ɿ���֤�� String
   *
   * ��������õ�
   */
  private String jkpzh;

  //private String jmzg;
  /**
   * ���շ�ʽ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String zsfs;

  /**
   * ������ҵ˰�� String
   *
   * ���ݸ�����ҵ������ѯ�õ�
   */
  private String zssl;

  /**
   * ������ String
   *
   * ����˰�����������ѯ�õ�
   */
  private String cyl;

  /**
   * ���� String
   *
   * ����˰�����������ѯ�õ�
   */
  private String de;

  /**
   * �ۺ��걨���
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

  /**
   * �Żݱ��ʣ�������ҵ�µ�������ҵΪ0.9����1��
   */
  private String yhbl;

  /**
   * һ�����˰��
   */
  private String jmse;

  /**
   * ������ҵ����
   */
  private String xzqyjm;

  /**
   * �ֲ���ǰ��ȿ���
   */
  private String mbyqndks;

  /**
   * �����������ܶ�
   */
  private String bkhlrze;

  /**
   * ��ʾ��˰�˵�˰���϶����
   */
  private String promptStr = "";

  /**
   * һ�����˰��
   */
  private String jmsl;

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

  public String getJd() {
    return jd;
  }

  public void setJd(String jd) {
    this.jd = jd;
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

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
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

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  /**
   *
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   * @return null
   */
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {

    return null;
  }

  /**
   * ҳ��Ҫ�����
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.actionType = "Show";
    this.jsjdm = null; //���������
    this.swjgzzjgdm = null; //˰�������֯��������
    this.nsrmc = null; //��˰������
    this.zssl = null;
    this.nd = null;
    this.jd = null;
    //this.jmzg=null;
    this.jkpzh = null;
    this.cyl = null;
    this.zsfs = null;
    this.zssl = null;
    this.de = null;
    this.promptStr = "";
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public java.util.List getDataList() {
    return dataList;
  }

  public void setDataList(java.util.List dataList) {
    this.dataList = dataList;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getJkpzh() {
    return jkpzh;
  }

  public void setJkpzh(String jkpzh) {
    this.jkpzh = jkpzh;
  }

//  public String getJmzg() {
//    return jmzg;
//  }
//  public void setJmzg(String jmzg) {
//    this.jmzg = jmzg;
//  }
  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
  }

  public String getZssl() {
    return zssl;
  }

  public void setZssl(String zssl) {
    this.zssl = zssl;
  }

  public String getCyl() {
    return cyl;
  }

  public void setCyl(String cyl) {
    this.cyl = cyl;
  }

  public String getDe() {
    return de;
  }

  public void setDe(String de) {
    this.de = de;
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

  public String getYhbl() {
    return yhbl;
  }

  public void setYhbl(String yhbl) {
    this.yhbl = yhbl;
  }

  public String getJmse() {
    return jmse;
  }

  public void setJmse(String jmse) {
    this.jmse = jmse;
  }

  public String getXzqyjm() {
    return xzqyjm;
  }

  public void setXzqyjm(String xzqyjm) {
    this.xzqyjm = xzqyjm;
  }

  public String getMbyqndks() {
    return mbyqndks;
  }

  public void setMbyqndks(String mbyqndks) {
    this.mbyqndks = mbyqndks;
  }

  public String getBkhlrze() {
    return bkhlrze;
  }

  public void setBkhlrze(String bkhlrze) {
    this.bkhlrze = bkhlrze;
  }

  public String getPromptStr() {
    return promptStr;
  }

  public void setPromptStr(String promptStr) {
    this.promptStr = promptStr;
  }

  public String getJmsl() {
    return jmsl;
  }

  public void setJmsl(String jmsl) {
    this.jmsl = jmsl;
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
