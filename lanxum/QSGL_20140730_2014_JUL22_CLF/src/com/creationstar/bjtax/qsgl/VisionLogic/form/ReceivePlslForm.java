package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

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
public class ReceivePlslForm extends BaseForm {
    //受理页面的信息
    private ArrayList nsrgrList;
    private ArrayList nsrfgrdmList;
    private ArrayList nsrfgrmcList;
    private ArrayList sptsList;
    private ArrayList spjeList;
    private ArrayList spyyList;
    private ArrayList cqbceList;
    private ArrayList cqsyeList;
    private ArrayList gfcjjgList;
    private ArrayList fgrspList;
    private ArrayList gfsyeList;
    private ArrayList tufwxxList;

    private ArrayList okList;

    //受理页面返回那个页面的标志位
    private String back;

    public String getBack() {
        return back;
    }


    public ArrayList getOkList() {
        return okList;
    }

    public ArrayList getCqbceList() {
        return cqbceList;
    }

    public ArrayList getCqsyeList() {
        return cqsyeList;
    }

    public ArrayList getGfcjjgList() {
        return gfcjjgList;
    }

    public ArrayList getGfsyeList() {
        return gfsyeList;
    }

    public ArrayList getNsrfgrdmList() {
        return nsrfgrdmList;
    }

    public ArrayList getNsrfgrmcList() {
        return nsrfgrmcList;
    }

    public ArrayList getNsrgrList() {
        return nsrgrList;
    }

    public ArrayList getSpjeList() {
        return spjeList;
    }

    public ArrayList getSptsList() {
        return sptsList;
    }

    public ArrayList getSpyyList() {
        return spyyList;
    }

    public ArrayList getFgrspList() {
        return fgrspList;
    }

    public ArrayList getTufwxxList() {
        return tufwxxList;
    }


    public void setBack(String back) {
        this.back = back;
    }


    public void setOkList(ArrayList okList) {
        this.okList = okList;
    }

    public void setCqbceList(ArrayList cqbceList) {
        this.cqbceList = cqbceList;
    }

    public void setCqsyeList(ArrayList cqsyeList) {
        this.cqsyeList = cqsyeList;
    }

    public void setGfcjjgList(ArrayList gfcjjgList) {
        this.gfcjjgList = gfcjjgList;
    }

    public void setGfsyeList(ArrayList gfsyeList) {
        this.gfsyeList = gfsyeList;
    }

    public void setNsrfgrdmList(ArrayList nsrfgrdmList) {
        this.nsrfgrdmList = nsrfgrdmList;
    }

    public void setNsrfgrmcList(ArrayList nsrfgrmcList) {
        this.nsrfgrmcList = nsrfgrmcList;
    }

    public void setNsrgrList(ArrayList nsrgrList) {
        this.nsrgrList = nsrgrList;
    }

    public void setSpjeList(ArrayList spjeList) {
        this.spjeList = spjeList;
    }

    public void setSptsList(ArrayList sptsList) {
        this.sptsList = sptsList;
    }

    public void setSpyyList(ArrayList spyyList) {
        this.spyyList = spyyList;
    }

    public void setFgrspList(ArrayList fgrspList) {
        this.fgrspList = fgrspList;
    }

    public void setTufwxxList(ArrayList tufwxxList) {
        this.tufwxxList = tufwxxList;
    }


}
