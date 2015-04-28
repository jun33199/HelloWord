package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;

/**
 * <p>Title:缴款书建立关联时补录申报和补录申报及完税证共用form </p>
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

    //完税证号
    private String wszh;

    //缴款书号
    private String jksh;

    //缴款书的类型，1代表普通，2代表调帐的
    private String type;

    //补录类型：补录申报或补录申报完税证标示
    private String bllx = "0";

    //帐务表示
    private String zwbs;

    /**
     * 主表序号，调帐的缴款书要用到
     */
    private String zbxh;

    //税款类型代码
    private String sklxdm;

    /**
     * 以下为补录汇总的缴款书内容用到
     */
    //申报表号数组
    private String[] sbbhList;
    //完税证号数组
    private String[] wszList;
    //年度字别
    private String[] ndzbList;
    //票证种类
    private String[] pzzlList;

    //补录汇总的缴款书确认页面的arraylist,包含BlJksBo
    private ArrayList dataList;

    //保存显示申报表号和核定通知书号的arraylist,每个对象都是hdtzs vo
    private ArrayList sbhdList;

    //获取页面上输入的数据，传入后台
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
