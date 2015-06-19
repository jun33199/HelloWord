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
 * <p>Title: һƱ��˰�ɿ���</p>
 * <p>Description: ��������2005��˰������������µ�һƱ��˰�ɿ����ʽ</p>
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
   * ˰�����ʹ���
   */
  private String sklxdm = "";

  /**
   * ˰����������
   */
  private String sklxmc = "";

  /**
   * ˰Ʊ����--һƱ��˰��˰Ʊ����,����ɽɿ����ֶβ���ֵ
   */
  private String sphm = "";

  /**
   * �걨���
   */
  private String sbbh = "";

  /**
   * ���������
   */
  private String jsjdm = "";

  /**
   * ��˰������
   */
  private String nsrmc = "";

  /**
   * ���д���
   */
  private String yhdm = "";

  /**
   * ��������
   */
  private String yhmc = "";

  /**
   * �ʻ�
   */
  private String zh = "";

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgdm = "";

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgmc = "";

  /**
   * ʵ�ɽ��Сд
   */
  private String sjjexx = "";

  /**
   * ʵ�ɽ���д
   */
  private String sjjedx = "";

  /**
   * ���յ�λ����
   */
  private String zsdwdm = "";

  /**
   * ���յ�λ����
   */
  private String zsdwmc = "";

  /**
   * ¼����
   */
  private String lrr = "";

  /**
   * ¼������
   */
  private String lrrq = "";

  /**
   * �걨����
   */
  private String sbrq = "";

  /**
   * �޽�����
   */
  private String xjrq = "";

  /**
   * һƱ��˰�ɿ�����ϸ--ָ�ýɿ������������һƱ��˰�걨���ݣ����������ǰ̨
   */
  private ArrayList ypdsJksMx = new ArrayList();

  /**
   * һƱһ˰�ɿ�����ϸ--ָ�ýɿ������������һƱһ˰�걨���ݣ����Ի�ȡ���ݱ�־���и���
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
   * ����˰�ֲ��һƱһ˰�ɿ�����ϸ����
   * @return map��keyΪ˰�ִ���
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
   * ����˰�ֺ�Ԥ���Ŀ���һƱһ˰�ɿ�����ϸ����
   * @return map��keyΪ˰�ִ���
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
   * ���շ����������һƱһ˰�ɿ�����ϸ����(˰���нӿ�ר��)
   * @return map��keyΪ˰�ִ���
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
