package com.ttsoft.bjtax.smsb.sbzl.yhs.web;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.ttsoft.framework.form.BaseForm;
import java.util.*;

/**
 * ӡ��˰�����˰�걨��ActionForm
 * <p>Title: ������˰��˰�����ģ��--�����걨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: �廪ͬ��</p>
 * @author ��������
 * @version 1.0
 */

public class YhsForm
    extends BaseForm {
  /**
   * ������˰��˰Ŀ���롢˰��˰Ŀ���ơ���˰������˰��˰�ʡ�ʵ��˰��
   * ��ϸ��Ϣ��ʾ String[]
   */
  private String columns[] = {
      "szsmdm", "szsmmc", "fs", "jsje", "sl", "sjse"};

  /**
   * ˰���������� String
   *
   * ��¼��������ȡ��
   */
  private String jsjdm; //���������

  /**
   * �걨��� String
   */
  private String nd; //���

  /**
   * ˰��������ʼ���� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssksrq; //�걨������ʼ��������

  /**
   * ˰�������������� String
   *
   * ϵͳ�����걨�����Զ�����
   */
  private String skssjsrq; //�걨����������������

  /**
   * ��˰������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String nsrmc; //��˰������

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgmc; //˰�������֯��������

  /**
   * ¼���˴��� String
   *
   * �ӵ�¼������ȡ��
   */
  private String lrr; //¼����

  /**
   * �걨���� String
   */
  private String sbrq; //�걨����

  /**
   * ��ע String
   */
  private String bz; //��ע

  private String swjgzzjgdm2; //˰�������֯��������

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgdm; //˰�������֯��������

  /**
   * ��ҵ��ϵ�绰 String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String qylxdh; //��ҵ��ϵ�绰�� �ӵǼǵõ� ����ʱ��Ҫ��д����ϸ����

  /**
   * �ϼ�Ӧ��˰�� Double
   */
  private Double hjynse; //�ϼ�Ӧ��˰��

  /**
   * �ϼƼ�˰��� Double
   */
  private Double hjjsje; //�ϼƼ�˰���

  /**
   * �ϼƷ��� String
   */
  private String hjfs; //�ϼƷ���

  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */
  private String fsdm; //�Ǽ��걨��ʽ����

  /**
   * ���ڴ洢��ϸ�о�����ֵ List
   */
  private List dataList = new ArrayList();

  /**
   * �ۺ��걨��־
   */
  private String iszhsb;

  /**
   * ��������
   */
  private String cjrq;

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
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

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getQylxdh() {
    return qylxdh;
  }

  public void setQylxdh(String qylxdh) {
    this.qylxdh = qylxdh;
  }

  public String getHjfs() {
    return hjfs;
  }

  public void setHjfs(String hjfs) {
    this.hjfs = hjfs;
  }

  public Double getHjjsje() {
    return hjjsje;
  }

  public void setHjjsje(Double hjjsje) {
    this.hjjsje = hjjsje;
  }

  public Double getHjynse() {
    return hjynse;
  }

  public void setHjynse(Double hjynse) {
    this.hjynse = hjynse;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getSwjgzzjgdm2() {
    return swjgzzjgdm2;
  }

  public void setSwjgzzjgdm2(String swjgzzjgdm2) {
    this.swjgzzjgdm2 = swjgzzjgdm2;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
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
    swjgzzjgdm = null;
    lrr = null;
    fsdm = null;
    nsrmc = null;
    qylxdh = null;
  }

  public String getIszhsb() {
    return iszhsb;
  }

  public void setIszhsb(String iszhsb) {
    this.iszhsb = iszhsb;
  }

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
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
