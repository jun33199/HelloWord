/*
 * <p>Title:������˰��������ϵͳ--�����걨</p>
 * <p>Copyright:  (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾��Ȩ����.</p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>Title: ������˰��������ϵͳ--�����걨--���ع��ܻ�</p>
 * <p>Description: ���пۿ��ļ������ϴ�Form</p>
 * @author �������飭�������
 * @version 1.0
 */
public class YhdkwjxzbcForm
    extends BaseForm {
  //���ش���
  private String qxdm;
  //���
  private String nd;
  //�¶�
  private String yd;
  //����
  private String zq;
  //����
  private String ny;
  //�����б�
  private List nyList = new ArrayList();
  //�����������б�
  private String yscNY = "";
  //�����б�
  private List qxList = new ArrayList();
  //�ѻ�ȡ�ļ����б�
  private String[] fileNames = null;
  //���λ�ȡ���ļ�����
  private int fileCount = 0;
  //�������ж�Ӧ����
  private String qxdmOfBccb = "";
  //ũ�����Ӧ����
  private String qxdmOfNxs = "";
  //������
  private String cjr = "";
  //��������
  private String cjrq = "";
  //��װ����������
  private HashMap ftpMap;
  //¼����
  private String lrr;
  //¼������
  private String lrrq;
  //�ɹ���Ϣ
  private String cgxx;

  public YhdkwjxzbcForm() {
  }

  public String getQxdm() {
    return qxdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
  }

  public String getZq() {
    return zq;
  }

  public void setZq(String zq) {
    this.zq = zq;
  }

  public String getNy() {
    return ny;
  }

  public void setNy(String ny) {
    this.ny = ny;
  }

  public String getYd() {
    return yd;
  }

  public void setYd(String yd) {
    this.yd = yd;
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

  public String getCjr() {
    return cjr;
  }

  public String getCjrq() {
    return cjrq;
  }

  public HashMap getFtpMap() {
    return ftpMap;
  }

  public void setFtpMap(HashMap ftpMap) {
    this.ftpMap = ftpMap;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public void setCjr(String cjr) {
    this.cjr = cjr;
  }

  public List getNyList() {
    return nyList;
  }

  public void setNyList(List nyList) {
    this.nyList = nyList;
  }

  public String getQxdmOfBccb() {
    return qxdmOfBccb;
  }

  public String getQxdmOfNxs() {
    return qxdmOfNxs;
  }

  public List getQxList() {
    return qxList;
  }

  public void setQxList(List qxList) {
    this.qxList = qxList;
  }

  public void setQxdmOfNxs(String qxdmOfNxs) {
    this.qxdmOfNxs = qxdmOfNxs;
  }

  public void setQxdmOfBccb(String qxdmOfBccb) {
    this.qxdmOfBccb = qxdmOfBccb;
  }

  public String getYscNY() {
    return yscNY;
  }

  public void setYscNY(String yscNY) {
    this.yscNY = yscNY;
  }

  public String[] getFileNames() {
    return fileNames;
  }

  public void setFileNames(String[] fileNames) {
    this.fileNames = fileNames;
  }

  public int getFileCount() {
    return fileCount;
  }

  public void setFileCount(int fileCount) {
    this.fileCount = fileCount;
  }

  public String getCgxx() {
    return cgxx;
  }

  public void setCgxx(String cgxx) {
    this.cgxx = cgxx;
  }

}