package com.ttsoft.bjtax.smsb.gghsb.web;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003-2004�����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author not attributable
 * @version 1.0
 */

import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.*;


/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: �ۿ���Ϣ��ѯForm</p>
 * @author not attributable
 * @version 1.0
 */

public class KkxxcxForm extends BaseForm{
    public KkxxcxForm() {
        dataList = new ArrayList();
    }

    /**
     * ��ϸ��Ŀ����
     */
    private java.util.ArrayList dataList;


    /**
     * ��ҳ��ÿҳ��¼��
     */
    private int length;

    /**
     * ��ҳ����ǰҳ��
     */
    private int pgNum;

    /**
     * ��ҳ����ҳ��
     */
    private int pgSum;

    /** ���          */
    private String jsjdm;

    /** ���          */
    private String nd;
    /** ����          */
    private String zq;
    /** ����          */
    private String qx;
    /** ˰����        */
    private String swjs;
    /** ����          */
    private String jx;
    /** ��������*/
    private String jxmc;
    /** �ۿ��־      */
    private String kkbz;
    /** �ۿ�ɹ�ԭ��*/
    private String kkbcgyy;

    /** �����б�*/
    private ArrayList jxList =null;
    /** ˰�����б�*/
    private ArrayList swjsList = null;
    /**�����б� */
    private ArrayList qxList = null;
    /** �ۿ���ϼ�*/
    private String kkjehj;
    /**��Ӧ��˰��ϼ� */
    private String ynjehj;
    /**��¼�� */
    private String jls;
    /**�����ϼ� */
    private String hshj;

    /**�ۿ���Ϣ�������� */
    private String[] dataListTitle;
    /**�ۿ���Ϣkey */
    private String[] dataListKey;
    /** �ϼ���Ϣ���ݼ�*/
    private List sumList;
    /** �ϼ���Ϣ����*/
    private String[] sumListTitle;
    /** �ϼ���Ϣkey*/
    private String[] sumListKey;




    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        pgNum = 0;
        pgSum = 0;
        length = 0;
    }
    public java.util.ArrayList getDataList() {
        return dataList;
    }
    public int getPgNum() {
        return pgNum;
    }
    public int getLength() {
        return length;
    }
    public int getPgSum() {
        return pgSum;
    }
    public void setPgSum(int pgSum) {
        this.pgSum = pgSum;
    }
    public void setPgNum(int pgNum) {
        this.pgNum = pgNum;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDataList(java.util.ArrayList dataList) {
        this.dataList = dataList;
    }
    public String getJx() {
        return jx;
    }
    public String getKkbcgyy() {
        return kkbcgyy;
    }
    public String getKkbz() {
        return kkbz;
    }
    public String getNd() {
        return nd;
    }
    public String getQx() {
        return qx;
    }
    public String getSwjs() {
        return swjs;
    }
    public String getZq() {
        return zq;
    }
    public void setZq(String zq) {
        this.zq = zq;
    }
    public void setSwjs(String swjs) {
        this.swjs = swjs;
    }
    public void setNd(String nd) {
        this.nd = nd;
    }
    public void setQx(String qx) {
        this.qx = qx;
    }
    public void setKkbcgyy(String kkbcgyy) {
        this.kkbcgyy = kkbcgyy;
    }
    public void setJx(String jx) {
        this.jx = jx;
    }
    public void setKkbz(String kkbz) {
        this.kkbz = kkbz;
    }

    public ArrayList getJxList() {
        return jxList;
    }

    public void setJxList(ArrayList jxList) {
        this.jxList = jxList;
    }

    public ArrayList getSwjsList() {
        return swjsList;
    }

    public void setSwjsList(ArrayList swjsList) {
        this.swjsList = swjsList;
    }

    public String getKkjehj() {
        if (kkjehj == null){
            kkjehj="";
        }
        return kkjehj;
    }

    public void setKkjehj(String kkjehj) {
        this.kkjehj = kkjehj;
    }

    public String getYnjehj() {
        if(ynjehj == null){
            ynjehj="";
        }
        return ynjehj;
    }

    public void setYnjehj(String ynjehj) {
        this.ynjehj = ynjehj;
    }

    public String getJls() {
        if(jls == null){
            jls ="";
        }
        return jls;
    }

    public void setJls(String jls) {
        this.jls = jls;
    }

    public String[] getDataListTitle() {
        return dataListTitle;
    }

    public void setDataListTitle(String[] dataListTitle) {
        this.dataListTitle = dataListTitle;
    }

    public String[] getDataListKey() {
        return dataListKey;
    }

    public void setDataListKey(String[] dataListKey) {
        this.dataListKey = dataListKey;
    }

    public List getSumList() {
        return sumList;
    }

    public void setSumList(List sumList) {
        this.sumList = sumList;
    }

    public String[] getSumListTitle() {
        return sumListTitle;
    }

    public void setSumListTitle(String[] sumListTitle) {
        this.sumListTitle = sumListTitle;
    }

    public String[] getSumListKey() {
        return sumListKey;
    }

    public void setSumListKey(String[] sumListKey) {
        this.sumListKey = sumListKey;
    }

    public String getJxmc() {
        return jxmc;
    }

    public void setJxmc(String jxmc) {
        this.jxmc = jxmc;
    }

    public ArrayList getQxList() {
        return qxList;
    }

    public void setQxList(ArrayList qxList) {
        this.qxList = qxList;
    }
  public String getHshj() {
    return hshj;
  }
  public void setHshj(String hshj) {
    this.hshj = hshj;
  }
  public String getJsjdm() {
    return jsjdm;
  }
  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }
}