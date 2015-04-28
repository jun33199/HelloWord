package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;

/**
 * <p>Title:�ɿ��齨������ʱ��¼�걨�Ͳ�¼�걨����˰֤����form </p>
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
public class BlAllSbWszForm extends BaseForm {

    //��˰֤��
    private String wszh;

    //�ɿ����
    private String jksh;

    //�ɿ�������ͣ�1������ͨ��2������ʵ�
    private String type;

    //��¼���ͣ���¼�걨��¼�걨��˰֤��ʾ
    private String bllx = "0";

    //�����ʾ
    private String zwbs;

    /**
     * ������ţ����ʵĽɿ���Ҫ�õ�
     */
    private String zbxh;

    //˰�����ʹ���
    private String sklxdm;

    /**
     * ����Ϊ��¼���ܵĽɿ��������õ�
     */
    //�걨�������
    private String[] sbbhList;
    //��˰֤������
    private String[] wszList;
    //����ֱ�
    private String[] ndzbList;
    //Ʊ֤����
    private String[] pzzlList;

    //��¼���ܵĽɿ���ȷ��ҳ���arraylist,����BlJksBo
    private ArrayList dataList;

    //������ʾ�걨��źͺ˶�֪ͨ��ŵ�arraylist,ÿ��������hdtzs vo
    private ArrayList sbhdList;

    //��ȡҳ������������ݣ������̨
    public Object getBo() {
        QueryBlJksBo bo = new QueryBlJksBo();
        if (this.getBllx().equals("0")) {
            bo.setJkpzh(this.jksh);
            bo.setSbbh(this.sbbh);
            bo.setType(this.type);
            bo.setZbxh(this.getZbxh());
            bo.setSklxdm(this.sklxdm);
            bo.setZwbs(this.zwbs);
            return bo;
        }

        ArrayList list = new ArrayList();
        if (this.getBllx().equals("1")) {
            for (int i = 0; i < this.getSbbhList().length; i++) {
                BlJksBo blJksBo = new BlJksBo();
                blJksBo.setJkpzh(this.getJksh());
                blJksBo.setSbbh(sbbhList[i]);
                blJksBo.setWszh(wszList[i]);
                blJksBo.setNdzb(ndzbList[i]);
                blJksBo.setPzzldm(pzzlList[i]);
                blJksBo.setType(this.type);
                blJksBo.setZbxh(this.getZbxh());
                blJksBo.setSklxdm(this.sklxdm);
                blJksBo.setZwbs(this.zwbs);
                list.add(blJksBo);
            }
            return list;
        }
        return null;
    }

    public String getJksh() {
        return jksh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getBllx() {
        return bllx;
    }

    public String[] getNdzbList() {
        return ndzbList;
    }

    public String[] getPzzlList() {
        return pzzlList;
    }

    public String[] getSbbhList() {
        return sbbhList;
    }

    public String[] getWszList() {
        return wszList;
    }

    public ArrayList getDataList() {
        return dataList;
    }

    public String getType() {
        return type;
    }

    public String getZbxh() {
        return zbxh;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public ArrayList getSbhdList() {
        return sbhdList;
    }

    public String getZwbs() {
        return zwbs;
    }

    public void setJksh(String jksh) {
        this.jksh = jksh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setBllx(String bllx) {
        this.bllx = bllx;
    }

    public void setNdzbList(String[] ndzbList) {
        this.ndzbList = ndzbList;
    }

    public void setPzzlList(String[] pzzlList) {
        this.pzzlList = pzzlList;
    }

    public void setSbbhList(String[] sbbhList) {
        this.sbbhList = sbbhList;
    }

    public void setWszList(String[] wszList) {
        this.wszList = wszList;
    }

    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setSbhdList(ArrayList sbhdList) {
        this.sbhdList = sbhdList;
    }

    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

}
