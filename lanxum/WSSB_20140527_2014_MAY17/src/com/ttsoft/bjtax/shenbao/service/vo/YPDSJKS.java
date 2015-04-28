package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.util.StringUtils;

/**
 * <p>Title: 一票多税缴款书</p>
 * <p>Description: 用来定义2005年税库银联网后的新的一票多税缴款书格式</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class YPDSJKS
    implements Serializable {

  public static String[] JKS_YPDS_MX_KEYS = ApplicationConstant.
      JKS_YPDS_MX_KEYS;

  /**
   * 税款类型代码
   */
  private String sklxdm = "";

  /**
   * 税款类型名称
   */
  private String sklxmc = "";

  /**
   * 税票号码--一票多税的税票号码,在完成缴款后该字段才有值
   */
  private String sphm = "";

  /**
   * 申报编号
   */
  private String sbbh = "";

  /**
   * 计算机代码
   */
  private String jsjdm = "";

  /**
   * 纳税人名称
   */
  private String nsrmc = "";

  /**
   * 银行代码
   */
  private String yhdm = "";

  /**
   * 银行名称
   */
  private String yhmc = "";

  /**
   * 帐户
   */
  private String zh = "";

  /**
   * 税务机关组织机构代码
   */
  private String swjgzzjgdm = "";

  /**
   * 税务机关组织机构名称
   */
  private String swjgzzjgmc = "";

  /**
   * 实缴金额小写
   */
  private String sjjexx = "";

  /**
   * 实缴金额大写
   */
  private String sjjedx = "";

  /**
   * 征收单位代码
   */
  private String zsdwdm = "";

  /**
   * 征收单位名称
   */
  private String zsdwmc = "";

  /**
   * 录入人
   */
  private String lrr = "";

  /**
   * 录入日期
   */
  private String lrrq = "";

  /**
   * 申报日期
   */
  private String sbrq = "";

  /**
   * 限缴日期
   */
  private String xjrq = "";

  /**
   * 一票多税缴款书明细--指该缴款书包含的所有一票多税申报数据，用以输出至前台
   */
  private ArrayList ypdsJksMx = new ArrayList();

  /**
   * 一票一税缴款书明细--指该缴款书包含的所有一票一税申报数据，用以获取数据标志进行更新
   */
  private ArrayList ypysJksMx = new ArrayList();

  public String getNsrmc() {
    return nsrmc;
  }

  public String getYhdm() {
    return yhdm;
  }

  public String getYhmc() {
    return yhmc;
  }

  public String getZh() {
    return zh;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public String getSjjexx() {
    return sjjexx;
  }

  public String getSjjedx() {
    return sjjedx;
  }

  public String getZsdwdm() {
    return zsdwdm;
  }

  public String getZsdwmc() {
    return zsdwmc;
  }

  public String getSbrq() {
    return sbrq;
  }

  public String getXjrq() {
    return xjrq;
  }

  public String getSphm() {
    return sphm;
  }

  public String getSbbh() {
    return sbbh;
  }

  public ArrayList getYpdsJksMx() {
    return ypdsJksMx;
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException,
      IOException {
    ois.defaultReadObject();
  }

  public void printContent() {
    System.out.println("JKS_CONTENT[" + sphm + "|" + this.sbbh + "|" +
                       this.jsjdm + "|" + this.nsrmc + "|" + this.yhdm + "|" +
                       this.yhmc + "|" + this.zh + "|" + this.swjgzzjgdm + "|" +
                       this.swjgzzjgmc + "|" + this.sjjexx + "|" + this.sjjedx +
                       "|" + this.zsdwdm + "|" + this.zsdwmc + "|" + this.lrr +
                       "|" + this.lrrq + "|" + this.sbrq + "|" + this.xjrq +
                       "]");
    System.out.println("ypdsJksMx's size=" + ypdsJksMx.size());
    System.out.println("ypysJksMx's size=" + ypysJksMx.size());
  }

  public YPDSJKS cloneJKSMainContent() {
    YPDSJKS tmp = new YPDSJKS();
    tmp.setSklxdm(new String(this.getSklxdm()));
    tmp.setSklxmc(new String(this.getSklxmc()));
    tmp.setSphm(new String(this.getSphm()));
    tmp.setSbbh(new String(this.getSbbh()));
    tmp.setJsjdm(new String(this.getJsjdm()));
    tmp.setNsrmc(new String(this.getNsrmc()));
    tmp.setYhdm(new String(this.getYhdm()));
    tmp.setYhmc(new String(this.getYhmc()));
    tmp.setZh(new String(this.getZh()));
    tmp.setSwjgzzjgdm(new String(this.getSwjgzzjgdm()));
    tmp.setSwjgzzjgmc(new String(this.getSwjgzzjgmc()));
    tmp.setSjjexx(new String(this.getSjjexx()));
    tmp.setSjjedx(new String(this.getSjjedx()));
    tmp.setZsdwdm(new String(this.getZsdwdm()));
    tmp.setZsdwmc(new String(this.getZsdwmc()));
    tmp.setLrr(new String(this.getLrr()));
    tmp.setLrrq(new String(this.getLrrq()));
    tmp.setSbrq(new String(this.getSbrq()));
    tmp.setXjrq(new String(this.getXjrq()));
    return tmp;
  }

  public String[] getPropertyJkpzhFfYpdsJks() {
    String[] returnStrs = {};
    List tmpList = new ArrayList();
    String jkpzh = null;
    for (int i = 0; i < this.getYpysJksMx().size(); i++) {
      //System.out.println(i + "jkpzh=" + jkpzh);
      jkpzh = (String) ( (Map)this.getYpysJksMx().get(i)).get("jkpzh");
      //System.out.println(i + "jkpzh=" + jkpzh);
      if (!tmpList.contains(jkpzh)) {
        tmpList.add(jkpzh);
      }
    }
    //System.out.println("jkpzh's count is" + tmpList.size());
    returnStrs = (String[]) tmpList.toArray(returnStrs);
    return returnStrs;
  }

  /**
   * 按照税种拆分一票一税缴款书明细数据
   * @return map的key为税种代码
   */
  public Map splitCurJksBySz() {
    Map rtnMap = new HashMap();
    List ypysJksmxs = null;
    Map ypysJksMap = null;
    String szdm = null;
    for (int i = 0; i < this.ypysJksMx.size(); i++) {
      szdm = (String) ( (Map) ypysJksMx.get(i)).get("szdm");
      if (rtnMap.get(szdm) == null) {
        ypysJksmxs = new ArrayList();
        rtnMap.put(szdm, ypysJksmxs);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
      else {
        ypysJksmxs = (List) rtnMap.get(szdm);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
    }
    return rtnMap;
  }



  /**
   * 按照税种和预算科目拆分一票一税缴款书明细数据
   * @return map的key为税种代码
   */
  public Map splitCurJksBySzAndYskm() {
    Map rtnMap = new HashMap();
    List ypysJksmxs = null;
    Map ypysJksMap = null;
    String szdm = null;
    String yskmdm = null;
    String key=null;
    for (int i = 0; i < this.ypysJksMx.size(); i++) {
      szdm = (String) ( (Map) ypysJksMx.get(i)).get("szdm");
      yskmdm = (String) ( (Map) ypysJksMx.get(i)).get("yskmdm");
      key=szdm+yskmdm;
      if (rtnMap.get(key) == null) {
        ypysJksmxs = new ArrayList();
        rtnMap.put(key, ypysJksmxs);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
      else {
        ypysJksmxs = (List) rtnMap.get(key);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
    }
    return rtnMap;
  }
  /**
   * 按照分行条件拆分一票一税缴款书明细数据(税库行接口专用)
   * @return map的key为税种代码
   */
  public Map splitCurJksBySKH() {
    Map rtnMap = new HashMap();
    List ypysJksmxs = null;
    Map ypysJksMap = null;
    String szdm = null;
    String yskmdm = null;
    String ysjcdm = null;
    String skssksrq = null;
    String skssjsrq = null;
    String xjrq = null;

    String key=null;
    for (int i = 0; i < this.ypysJksMx.size(); i++) {
      szdm = (String) ( (Map) ypysJksMx.get(i)).get("szdm");
      yskmdm = (String) ( (Map) ypysJksMx.get(i)).get("yskmdm");
      ysjcdm = (String) ( (Map) ypysJksMx.get(i)).get("ysjcdm");
      skssksrq = (String) ( (Map) ypysJksMx.get(i)).get("skssksrq");
      skssjsrq = (String) ( (Map) ypysJksMx.get(i)).get("skssjsrq");
      xjrq = (String) ( (Map) ypysJksMx.get(i)).get("xjrq");
      key=szdm+yskmdm+ysjcdm+skssksrq+skssjsrq+xjrq;
      if (rtnMap.get(key) == null) {
        ypysJksmxs = new ArrayList();
        rtnMap.put(key, ypysJksmxs);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
      else {
        ypysJksmxs = (List) rtnMap.get(key);
        ypysJksmxs.add(ypysJksMx.get(i));
      }
    }
    return rtnMap;
  }


  public int getCurJksSzCount() {
    int count = 0;
    ArrayList list = new ArrayList();
    String szdm = null;
    for (int i = 0; i < this.ypysJksMx.size(); i++) {
      szdm = (String) ( (Map) ypysJksMx.get(i)).get("szdm");
      if (!list.contains(szdm)) {
        list.add(szdm);
      }
    }
    count = list.size();
    return count;
  }

  public int getCurJksSzAndYskmCount() {
    int count = 0;
    ArrayList list = new ArrayList();
    String szdm = null;
    String yskmdm = null;
    String key = null;
    for (int i = 0; i < this.ypysJksMx.size(); i++) {
      szdm = (String) ( (Map) ypysJksMx.get(i)).get("szdm");
      yskmdm = (String) ( (Map) ypysJksMx.get(i)).get("yskmdm");
      key=szdm+yskmdm;
      if (!list.contains(key)) {
        list.add(key);
      }
    }
    count = list.size();
    return count;
  }


  public ArrayList getYpysJksMx() {
    return ypysJksMx;
  }

  public String getSklxdm() {
    return sklxdm;
  }

  public String getSklxmc() {
    return sklxmc;
  }

  public String getLrrq() {
    return lrrq;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public String getLrr() {
    return lrr;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = StringUtils.killNull(jsjdm);
  }

  public void setLrr(String lrr) {
    this.lrr = StringUtils.killNull(lrr);
  }

  public void setLrrq(String lrrq) {
    this.lrrq = StringUtils.killNull(lrrq);
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = StringUtils.killNull(nsrmc);
  }

  public void setSbbh(String sbbh) {
    this.sbbh = StringUtils.killNull(sbbh);
  }

  public void setSbrq(String sbrq) {
    this.sbrq = StringUtils.killNull(sbrq);
  }

  public void setSjjedx(String sjjedx) {
    this.sjjedx = StringUtils.killNull(sjjedx);
  }

  public void setSjjexx(String sjjexx) {
    this.sjjexx = StringUtils.killNull(sjjexx);
  }

  public void setSklxdm(String sklxdm) {
    this.sklxdm = StringUtils.killNull(sklxdm);
  }

  public void setSklxmc(String sklxmc) {
    this.sklxmc = StringUtils.killNull(sklxmc);
  }

  public void setSphm(String sphm) {
    this.sphm = StringUtils.killNull(sphm);
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = StringUtils.killNull(swjgzzjgdm);
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = StringUtils.killNull(swjgzzjgmc);
  }

  public void setXjrq(String xjrq) {
    this.xjrq = StringUtils.killNull(xjrq);
  }

  public void setYhdm(String yhdm) {
    this.yhdm = StringUtils.killNull(yhdm);
  }

  public void setYhmc(String yhmc) {
    this.yhmc = StringUtils.killNull(yhmc);
  }

  public void setYpdsJksMx(ArrayList ypdsJksMx) {
    this.ypdsJksMx = ypdsJksMx;
  }

  public void setYpysJksMx(ArrayList ypysJksMx) {
    this.ypysJksMx = ypysJksMx;
  }

  public void setZh(String zh) {
    this.zh = StringUtils.killNull(zh);
  }

  public void setZsdwdm(String zsdwdm) {
    this.zsdwdm = StringUtils.killNull(zsdwdm);
  }

  public void setZsdwmc(String zsdwmc) {
    this.zsdwmc = StringUtils.killNull(zsdwmc);
  }

}
