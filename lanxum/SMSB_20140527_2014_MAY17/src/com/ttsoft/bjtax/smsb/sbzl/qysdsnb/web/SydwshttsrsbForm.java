package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

/**
 *
 * <p>Title: ������˰��˰�����ģ��--�����걨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: �廪ͬ��</p>
 * @author ��������
 * @version 1.0
 */
public class SydwshttsrsbForm
    extends BaseForm {

  /**
   * �б������Ŀ���� String[]
   */
  private String[] columns = {
      "hc", "bqljs"};

  /**
   * ��������� String
   */
  private String jsjdm;

  /**
   * ��˰������ String
   */
  private String nsrmc;

  /**
   * ע���ַ��ϵ�绰 String
   */
  private String zcdzlxdh;

  /**
   * Ӧ˰���������ʶ String
   */
  private String sfyyssr;

  /**
   * �Ǽ�ע�����ʹ��� String
   */
  private String djzclxdm;

  /**
   * �걨��� String
   */

  private String nd;

  /**
   * ¼���˴��� String
   *
   * �ӵ�¼������ȡ��
   */
  private String lrr;

  /**
   * ¼������ String
   */

  private String lrrq;

  /**
   * ����ʱ�� String
   */
  private String cjsj;

  /**
   * ˰��������ʼ������� String
   *
   * ϵͳ�����걨�����Զ�����
   */

  private String skssksrq;

  /**
   * ˰��������Ƚ������� String
   *
   * ϵͳ�����걨�����Զ�����
   */

  private String skssjsrq;

  /**
   * �Ǽ��걨��ʽ���� String
   *
   * �ӵ�¼������ȡ��
   */

  private String fsdm;

  /**
   * ˰�������֯�������� String
   *
   * �ӵ�¼������ȡ��
   */
  private String swjgzzjgdm;

  /**
   * ��ҵ��λ��������������걨����
   */
  private List srsb = new ArrayList();

  /**
   * ��ҵ�걨����
   */
  private String sbrq;

  /**
   *  �ۺ��걨��ת��
   */
  private String iszhsb;

  /**
   * ���ش���
   */
  private String qxdm;

  public String getQxdm() {
    return this.qxdm;
  }

  public void setQxdm(String _qxdm) {
    this.qxdm = _qxdm;
  }

  public String getIszhsb() {
    return this.iszhsb;
  }

  public void setIszhsb(String _iszhsb) {
    this.iszhsb = _iszhsb;
  }

  public String getJsjdm() {
    return this.jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNsrmc() {
    return this.nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getZcdzlxdh() {
    return this.zcdzlxdh;
  }

  public void setZcdzlxdh(String zcdzlxdh) {
    this.zcdzlxdh = zcdzlxdh;
  }

  public String getSfyyssr() {
    return sfyyssr;
  }

  public void setSfyyssr(String _sfyyssr) {
    this.sfyyssr = _sfyyssr;
  }

  public String getDjzclxdm() {
    return this.djzclxdm;
  }

  public void setDjzclxdm(String _djzclxdm) {
    this.djzclxdm = _djzclxdm;
  }

  public String getSbrq() {
    return this.sbrq;
  }

  public void setSbrq(String rq) {
    this.sbrq = rq;
  }

  public String[] getColumns() {
    return this.columns;
  }

  public void setColumns(String[] _columns) {
    this.columns = _columns;
  }

  public List getDataList() {
    return this.srsb;
  }

  public void setDataList(List _srsb) {
    this.srsb = _srsb;
  }

  public String getNd() {
    return nd;
  }

  public void setNd(String _nd) {
    nd = _nd;
  }

  public String getLrr() {
    return lrr;
  }

  public void setLrr(String _lrr) {
    lrr = _lrr;
  }

  public String getCjrq() {
    return cjsj;
  }

  public void setCjrq(String _cjsj) {
    cjsj = _cjsj;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String _swjgzzjgdm) {
    swjgzzjgdm = _swjgzzjgdm;
  }

  public String getFsdm() {
    return fsdm;
  }

  public void setFsdm(String _fsdm) {
    fsdm = _fsdm;
  }

  public String getLrrq() {
    return lrrq;
  }

  public void setLrrq(String _lrrq) {
    lrrq = _lrrq;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String _skssksrq) {
    skssksrq = _skssksrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String _skssjsrq) {
    skssjsrq = _skssjsrq;
  }

  /**
   * ҳ��Ҫ�����
   * @param actionMapping struts.action.ActionMapping
   * @param httpServletRequest HttpServletRequest
   */

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {

    zcdzlxdh = null;

    sfyyssr = "1";

    djzclxdm = "";

    jsjdm = null;
    nd = null;
    cjsj = null;
    lrrq = null;
    //skssksrq = null;
    //skssjsrq = null;
    swjgzzjgdm = null;
    lrr = null;
    //sbrq = null;
    fsdm = null;
    nsrmc = null;

    getDataList().clear();
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
