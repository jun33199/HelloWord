package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QueryBlJksBo implements Serializable {

    /**
     * ��ѯ��������˰���
     */
    private String jsje;

    /**
     * ��ѯ������ʵ��˰��
     */
    private String sjse;

    /**
     * �걨�ɿ������vo
     */
    private Sbjkzb sbjkzb;

    /**
     * �걨�ɿ���ϸ��vo
     */
    private Sbjkmx sbjkmx;

    /**
     * �ɿ������ϸ
     */
    private ArrayList mxList;

    /**
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * �걨��ţ��걨�ɿ���˶Խ���ʱ��Ҫ�õ�
     */
    private String sbbh;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

    /**
     * �ɿ�������ͣ�1������ͨ��2������ʵ�
     */
    private String type;

    /**
     * ������Դ������ʱ���ã�ͨ���������֪���ǻ��ܵĻ����걨�Ľɿ��飬1�����걨�ģ�2������ܵ�
     */
    private String sjly;

    /**
     * ������ţ����ʵĽɿ���Ҫ�õ�
     */
    private String zbxh;

    /**
     * �����ʶ
     */
    private String zwbs;

    /**
     * ����걨��ŵ�ArrayList���������ɵĽɿ���˶Խ���ʱ��Ҫ�õ�����ŵĶ���String���͵�sbbh
     */
    private ArrayList sbbhList;

    public ArrayList getMxList() {
        return mxList;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public String getSbbh() {
        return sbbh;
    }

    public ArrayList getSbbhList() {
        return sbbhList;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getType() {
        return type;
    }

    public String getSjly() {
        return sjly;
    }

    public String getZbxh() {
        return zbxh;
    }

    public String getJsje() {
        return jsje;
    }

    public String getSjse() {
        return sjse;
    }

    public String getZwbs() {
        return zwbs;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbbhList(ArrayList sbbhList) {
        this.sbbhList = sbbhList;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

}
