package com.lscdz.qysds.common.service.QysdsInfo.bo;
/**
 * <p>Title: ��ҵ����˰���շ�ʽ�˶� G2G2.1</p>
 * <p>Description: ZSFS�����(String ,zsfsdm ���շ�ʽ����)(String,zsfsmc, ���շ�ʽ���ƣ���String,sl,˰�ʣ�
 * cyl;//�����ʣ�//����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Zsfs {
  private String zsfsdm;
  private String zsfsmc;
  private String sl;//˰��
  private String cyl;//������
  private String de;//����
  private String xyshm;
  public String getXyshm(){
	  return xyshm;
  }
  public void setXyshm(String xyshm){
	  this.xyshm=xyshm;
  }
  public String getZsfsdm() {
    return zsfsdm;
  }
  public void setZsfsdm(String zsfsdm) {
    this.zsfsdm = zsfsdm;
  }
  public String getZsfsmc() {
    return zsfsmc;
  }
  public void setZsfsmc(String zsfsmc) {
    this.zsfsmc = zsfsmc;
  }
  public String getSl() {
    return sl;
  }
  public void setSl(String sl) {
    this.sl = sl;
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

}