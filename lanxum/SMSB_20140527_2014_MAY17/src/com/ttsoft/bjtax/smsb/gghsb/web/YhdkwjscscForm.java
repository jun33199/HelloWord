/*
 * <p>Title:������˰��������ϵͳ--�����걨</p>
 * <p>Copyright:  (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾��Ȩ����.</p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Title: ������˰��������ϵͳ--�����걨--���ع��ܻ�</p>
 * <p>Description: ���пۿ��ļ������ϴ�Form</p>
 * @author �������飭�������
 * @version 1.0
 */
public class YhdkwjscscForm
    extends BaseForm {
  public YhdkwjscscForm() {
  }

  //����
  private String ny = "";
  //�����б�
  private List nyList = new ArrayList();
  //�����������б�
  private String yscNY = "";
  //���ش���
  private String qxdm = "";
  //�����б�
  private List qxList = new ArrayList();
  //�������ж�Ӧ����
  private String qxdmOfBccb = "";
  //ũ�����Ӧ����
  private String qxdmOfNxs = "";
  //������
  private String cjr = "";
  //��������
  private String cjrq = "";
  //¼����
  private String lrr = "";
  //¼������
  private String lrrq = "";
  //��װ���ϴ�����
  private HashMap ftpMap;
  //���д���
  private String yhdm = "";
  //�ܻ���
  private String hs = "";

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  public String getCjr() {
    return cjr;
  }

  public String getLrr() {
    return lrr;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public String getCjrq() {
    return cjrq;
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public List getQxList() {
    return qxList;
  }

  public void setQxList(List qxList) {
    this.qxList = qxList;
  }

  public List getNyList() {
    return nyList;
  }

  public void setNyList(List nyList) {
    this.nyList = nyList;
  }

  public String getNy() {
    return ny;
  }

  public void setNy(String ny) {
    this.ny = ny;
  }

  public HashMap getFtpMap() {
    return ftpMap;
  }

  public void setFtpMap(HashMap ftpMap) {
    this.ftpMap = ftpMap;
  }

  public String getYscNY() {
    return yscNY;
  }

  public void setYscNY(String yscNY) {
    this.yscNY = yscNY;
  }

  public String getQxdmOfBccb() {
    return qxdmOfBccb;
  }

  public String getQxdmOfNxs() {
    return qxdmOfNxs;
  }

  public void setQxdmOfBccb(String qxdmOfBccb) {
    this.qxdmOfBccb = qxdmOfBccb;
  }

  public void setQxdmOfNxs(String qxdmOfNxs) {
    this.qxdmOfNxs = qxdmOfNxs;
  }

  public void setYhdm(String yhdm) {
    this.yhdm = yhdm;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getHs() {
    return hs;
  }

  public void setHs(String hs) {
    this.hs = hs;
  }

}