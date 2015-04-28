package com.lscdz.qysds.common.service.QysdsInfo.bo;


/**
 * <p>Title: 企业所得税年报所需相关数据集</p>
 * <p>Description:
 * //企业所得税的征收方式<br>
 *   private Zsfs zsfs;<br>
 *   //三新抵扣<br>
 *   private java.math.BigDecimal sxdk;<br>
 *   //财产损失<br>
 *   private java.math.BigDecimal ccss;<br>
 *   //弥补以前年度亏损<br>
 *   private java.math.BigDecimal nbyqndks;<br>
 *   //技术改造国产设备投资抵免<br>
 *   private java.math.BigDecimal jsgzgcsb;<br>
 *   //查询高新技术企业认定第一次认定的时间<br>
 *   private java.sql.Date gxjsqy;<br>
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class QysdsSet{
  //企业所得税的征收方式
  private Zsfs zsfs;
  //三新抵扣
  private java.math.BigDecimal sxdk;
  //财产损失
  private java.math.BigDecimal ccss;
  //弥补以前年度亏损
  private java.math.BigDecimal nbyqndks;
  //技术改造国产设备投资抵免
  private java.math.BigDecimal jsgzgcsb;
  //查询高新技术企业认定第一次认定的时间
  private java.sql.Date gxjsqy;
  //一般减免额
  private java.math.BigDecimal ybjme;
   //是否乡镇企业
  private boolean xzqy;
  private java.math.BigDecimal msdjxzrsy;
  //insert by jiqiang 2005-02-18
  //减免税率
  private java.math.BigDecimal ybjmsl;


  public Zsfs getZsfs() {
    return zsfs;
  }
  public void setZsfs(Zsfs zsfs) {
    this.zsfs = zsfs;
  }
  public java.math.BigDecimal getSxdk() {
    return sxdk;
  }
  public void setSxdk(java.math.BigDecimal sxdk) {
    this.sxdk = sxdk;
  }
  public java.math.BigDecimal getCcss() {
    return ccss;
  }
  public void setCcss(java.math.BigDecimal ccss) {
    this.ccss = ccss;
  }
  public java.math.BigDecimal getNbyqndks() {
    return nbyqndks;
  }
  public void setNbyqndks(java.math.BigDecimal nbyqndks) {
    this.nbyqndks = nbyqndks;
  }
  public java.math.BigDecimal getJsgzgcsb() {
    return jsgzgcsb;
  }
  public void setJsgzgcsb(java.math.BigDecimal jsgzgcsb) {
    this.jsgzgcsb = jsgzgcsb;
  }
  public java.sql.Date getGxjsqy() {
    return gxjsqy;
  }
  public void setGxjsqy(java.sql.Date gxjsqy) {
    this.gxjsqy = gxjsqy;
  }
  public java.math.BigDecimal getYbjme() {
    return ybjme;
  }
  public void setYbjme(java.math.BigDecimal ybjme) {
    this.ybjme = ybjme;
  }
  public boolean isXzqy() {
    return xzqy;
  }
  public void setXzqy(boolean xzqy) {
    this.xzqy = xzqy;
  }
  public java.math.BigDecimal getMsdjxzrsy() {
    return msdjxzrsy;
  }
  public void setMsdjxzrsy(java.math.BigDecimal msdjxzrsy) {
    this.msdjxzrsy = msdjxzrsy;
  }
  public void setYbjmsl(java.math.BigDecimal ybjmsl){
    this.ybjmsl=ybjmsl;
  }
  public java.math.BigDecimal getYbjmsl(){
    return this.ybjmsl;
  }
}
