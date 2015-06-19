package com.ttsoft.bjtax.shenbao.service.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: �����걨�����ؽ������</p>
 * <p>Description: ����Ϊ˰���нӿڴ���Ľ����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class YhsbResult
    implements Serializable {

  /**
   * ���������룬8λ�ַ���ɣ�1��2λΪ��������3��4λΪҵ��ӿڱ��룬5��6��7��8Ϊ��ϸ��Ϣ����
   * �ݶ�1��2λ�����磬00xxxxxx��ʾҵ����ȫ�ɹ���99xxxxxx��ʾҵ����ȫʧ��
   * �ݶ�3��4λ,��ʱ��ʹ�ã�
   * �ݶ�5��6��7��8λ ��ʾ������Ϣ�����磬xxxx0000��ʾҵ����ȫ�ɹ���xxxx9999��ʾҵ����ȫʧ��
   */
  private String resultCode = null;

  /**
   * ��������������
   */
  private String resultDescription = null;

  /**
   * ҵ����������
   */
  private String jsjdm = null;

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgdm=null;

  /**
   * ���ش���
   */
  private String qxdm = null;

  /**
   * ����ֵ����,����,�������걨���нɿ�����걨���нɿ�ö���ΪList,���ڿۿ���������ö���Ϊ��
   */
  private Object rnObject = null;

  /**
   * ����ֵ�����Class��,����ǿ������ת��
   */
  private String rnObjectClassName = null;

  //
  public String printContent(){
    StringBuffer sb=new StringBuffer();
    sb.append("YhsbResult[");
    sb.append("resultCode:");
    sb.append(resultCode);
    sb.append("|resultDescription:");
    sb.append(resultDescription);
    sb.append("|jsjdm:");
    sb.append(jsjdm);
    sb.append("|swjgzzjgdm:");
    sb.append(swjgzzjgdm);
    sb.append("|qxdm:");
    sb.append(qxdm);
    sb.append("|rnObjectClassName:");
    sb.append(rnObjectClassName);
    sb.append("]");
    String content=sb.toString();
    System.out.println(content);
    return content;
  }

  //
  public boolean checkVaild(){
    return true;
  }

  //////////////////////////////////////////////////////////////////////////////
  ////////////         properties's getter and setter         //////////////////
  //////////////////////////////////////////////////////////////////////////////
  public String getJsjdm() {
    return jsjdm;
  }

  public String getQxdm() {
    return qxdm;
  }

  public String getResultCode() {
    return resultCode;
  }

  public String getResultDescription() {
    return resultDescription;
  }

  public Object getRnObject() {
    return rnObject;
  }

  public String getRnObjectClassName() {
    return rnObjectClassName;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public void setQxdm(String qxdm) {
    this.qxdm = qxdm;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public void setResultDescription(String resultDescription) {
    this.resultDescription = resultDescription;
  }

  public void setRnObject(Object rnObject) {
    this.rnObject = rnObject;
  }

  public void setRnObjectClassName(String rnObjectClassName) {
    this.rnObjectClassName = rnObjectClassName;
  }
  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }
  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

}