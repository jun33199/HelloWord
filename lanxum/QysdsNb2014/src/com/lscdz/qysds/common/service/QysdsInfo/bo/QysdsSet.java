package com.lscdz.qysds.common.service.QysdsInfo.bo;


/**
 * <p>Title: ��ҵ����˰�걨����������ݼ�</p>
 * <p>Description:
 * //��ҵ����˰�����շ�ʽ<br>
 *   private Zsfs zsfs;<br>
 *   //���µֿ�<br>
 *   private java.math.BigDecimal sxdk;<br>
 *   //�Ʋ���ʧ<br>
 *   private java.math.BigDecimal ccss;<br>
 *   //�ֲ���ǰ��ȿ���<br>
 *   private java.math.BigDecimal nbyqndks;<br>
 *   //������������豸Ͷ�ʵ���<br>
 *   private java.math.BigDecimal jsgzgcsb;<br>
 *   //��ѯ���¼�����ҵ�϶���һ���϶���ʱ��<br>
 *   private java.sql.Date gxjsqy;<br>
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class QysdsSet{
  //��ҵ����˰�����շ�ʽ
  private Zsfs zsfs;
  //���µֿ�
  private java.math.BigDecimal sxdk;
  //�Ʋ���ʧ
  private java.math.BigDecimal ccss;
  //�ֲ���ǰ��ȿ���
  private java.math.BigDecimal nbyqndks;
  //������������豸Ͷ�ʵ���
  private java.math.BigDecimal jsgzgcsb;
  //��ѯ���¼�����ҵ�϶���һ���϶���ʱ��
  private java.sql.Date gxjsqy;
  //һ������
  private java.math.BigDecimal ybjme;
   //�Ƿ�������ҵ
  private boolean xzqy;
  private java.math.BigDecimal msdjxzrsy;
  //insert by jiqiang 2005-02-18
  //����˰��
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
