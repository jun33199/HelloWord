/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����м���˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JmssbForm
    extends BaseForm {
  /**
   * ˰���������� String
   *
   * ��¼��������ȡ��
   */
  private String jsjdm;

  /**
   * ����ʱ�� String
   */
  /**
   * �걨���� String
   */
  private String sbrq;

  /**
   * ¼������ String
   */
  /**
   * ��˰������ String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String nsrmc;

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgdm;

  /**
   * ¼���˴��� String
   *
   * �ӵ�¼������ȡ��
   */
  private String lrr;

  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */
  private String fsdm;

  /**
   * ���˱�ʾ String
   *
   * ��ʼֵ000000
   */
  private String jzbz;

  /**
   * �Ǽ�ע�����ʹ��� String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String djzclxdm;

  /**
   * ���ұ�׼��ҵ���� String
   *
   * ���������˰�����������ѯ�õ�
   */
  private String gjbzhydm;

  /**
   * ���ڴ洢��ϸ�о�����ֵ List
   */
  private ArrayList dataList = new ArrayList();

  /**
   * ������˰��˰Ŀ���ơ�˰��˰Ŀ���롢��˰����˰����������˰��������ʹ��롢Ԥ���Ŀ���롢Ԥ�㼶�δ��롢˰�������������ڡ�˰��������ʼ���ڡ���������
   * ��ϸ��Ϣ��ʾ String[]
   * modify by tangchangfu 2014-04-04 ��˰����˰��Ŀ  �����ֶ� "jmxmjdm","jmxmksrq","jmxmjsrq"
   */
  private String[] columns = {
      "szsmmc", "szsmdm", "jsje", "kssl", "jmse",
      "jmxmdm", "yskmdm", "ysjcdm", "skssjsrq",
      "skssksrq", "jmlx","jmxmjdm","jmxmksrq","jmxmjsrq"};

  //private String[] columns = {"szsmdm","jsje","kssl","jmse","skssjsrq","skssksrq","jmlx"};
  /**
   * ��ʾ�����˵����� String
   */
  private String scriptStr;

  /**
   * ҳ����ʾ�������� String
   */
  private String warnStr;

  /**
   * �걨��� String
   *
   * �����걨���ھ���
   */
  private String nd;

  /**
   * �Ƿ������ۺ��걨
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
   * add by tangchangfu ��˰����˰��Ŀ  2014-04-04 
   * �������۶�
   */
  private String dqxse;
  /**
   * add by tangchangfu ��˰����˰��Ŀ  2014-04-04 
   * ���������ܶ�
   */
  private String dqlrze;
  /**
   * add by tangchangfu ��˰����˰��Ŀ  2014-04-04 
   * ��ҵ����
   */
  private String qyrs;
  /**
   * add by tangchangfu ��˰����˰��Ŀ  2014-04-04 
   * ��������
   */
  private String azrs;

  /**
   * ҳ��Ҫ�����
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */
  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.dataList.clear();
    this.actionType = "Show";
    this.jsjdm = null;
    this.nsrmc = null;
    this.dqxse = null;
    this.dqlrze = null;
    this.qyrs = null;
    this.azrs = null;
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getCjrq() {
    return cjrq;
  }

  public void setCjrq(String cjrq) {
    this.cjrq = cjrq;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String lrrq) {
    this.lrrq = lrrq;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String lrr) {
    this.lrr = lrr;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String fsdm) {
    this.fsdm = fsdm;
  }

  public String getJzbz() {
    return jzbz;
  }

  public void setJzbz(String jzbz) {
    this.jzbz = jzbz;
  }

  public String getDjzclxdm() {
    return djzclxdm;
  }

  public void setDjzclxdm(String djzclxdm) {
    this.djzclxdm = djzclxdm;
  }

  public String getGjbzhydm() {
    return gjbzhydm;
  }

  public void setGjbzhydm(String gjbzhydm) {
    this.gjbzhydm = gjbzhydm;
  }

  public ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public String getScriptStr() {
    return scriptStr;
  }

  public void setScriptStr(String scriptStr) {
    this.scriptStr = scriptStr;
  }

  public String getWarnStr() {
    return warnStr;
  }

  public void setWarnStr(String warnStr) {
    this.warnStr = warnStr;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String nd) {
    this.nd = nd;
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

public String getDqxse() {
	return dqxse;
}

public void setDqxse(String dqxse) {
	this.dqxse = dqxse;
}

public String getDqlrze() {
	return dqlrze;
}

public void setDqlrze(String dqlrze) {
	this.dqlrze = dqlrze;
}

public String getQyrs() {
	return qyrs;
}

public void setQyrs(String qyrs) {
	this.qyrs = qyrs;
}

public String getAzrs() {
	return azrs;
}

public void setAzrs(String azrs) {
	this.azrs = azrs;
}

  /* end added by huxiaofeng 2005.8.16*/

}
