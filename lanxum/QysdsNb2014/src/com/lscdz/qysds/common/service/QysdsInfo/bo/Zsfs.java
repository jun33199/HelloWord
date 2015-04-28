package com.lscdz.qysds.common.service.QysdsInfo.bo;
/**
 * <p>Title: 企业所得税征收方式核定 G2G2.1</p>
 * <p>Description: ZSFS类包括(String ,zsfsdm 征收方式代码)(String,zsfsmc, 征收方式名称）（String,sl,税率）
 * cyl;//纯益率，//定额</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Zsfs {
  private String zsfsdm;
  private String zsfsmc;
  private String sl;//税率
  private String cyl;//纯益率
  private String de;//定额
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