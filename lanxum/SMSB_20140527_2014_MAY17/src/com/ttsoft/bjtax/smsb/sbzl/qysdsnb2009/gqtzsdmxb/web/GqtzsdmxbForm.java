package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class GqtzsdmxbForm extends QysdsNewForm {

    private String bz32;
    private String item58;
    private String nd1;
    private String nd2;
    private String nd3;
    private String nd4;
    private String nd5;
    /**
     * 存放固定信息的字符串数组
     */
    private String[] sb_columns = {"hc", "C1", "C2", "C3", "C4", "C5", "C6",
                                  "C7", "C8", "C9", "C10", "C11", "C12", "C13",
                                  "C14", "C15", "C16"};
    /**
     * 存放固定信息的字符串数组
     */
    private String[] sbbczl_columns = {"hc1", "P_1", "P_2", "P_3", "P_4",
                                      "P_5"};


    private String[] hj_columns = {"hjhc", "hjje"};


    /**
     * 存放固定行数据的LIST
     */

    private List sbbczlList = new ArrayList();
    /**
     * 存放动态行数据的LIST
     */
    private List hjList = new ArrayList();


    public String[] getSb_columns() {
        return sb_columns;
    }

    public String[] getSbbczl_columns() {
        return sbbczl_columns;
    }

    public List getSbbczlList() {
        return sbbczlList;
    }


    public String getBz32() {
        return bz32;
    }

    public String getItem58() {
        return item58;
    }

    public String[] getHj_columns() {
        return hj_columns;
    }

    public List getHjList() {
        return hjList;
    }

    public String getNd1() {
        int nd=Integer.parseInt(this.getSkssksrq().substring(0,4))-1;
        return String.valueOf(nd);
    }

    public String getNd2() {
        int nd=Integer.parseInt(this.getSkssksrq().substring(0,4))-2;
        return String.valueOf(nd);
    }

    public String getNd3() {
        int nd=Integer.parseInt(this.getSkssksrq().substring(0,4))-3;
        return String.valueOf(nd);
    }

    public String getNd4() {
        int nd=Integer.parseInt(this.getSkssksrq().substring(0,4))-4;
        return String.valueOf(nd);
    }

    public String getNd5() {
        int nd=Integer.parseInt(this.getSkssksrq().substring(0,4))-5;
        return String.valueOf(nd);
    }


    public void setSb_columns(String[] sb_columns) {
        this.sb_columns = sb_columns;
    }

    public void setSbbczl_columns(String[] sbbczl_columns) {
        this.sbbczl_columns = sbbczl_columns;
    }

    public void setSbbczlList(List sbbczlList) {
        this.sbbczlList = sbbczlList;
    }


    public void setBz32(String bz32) {
        this.bz32 = bz32;
    }

    public void setItem58(String item58) {
        this.item58 = item58;
    }

    public void setHj_columns(String[] hj_columns) {
        this.hj_columns = hj_columns;
    }

    public void setHjList(List hjList) {
        this.hjList = hjList;
    }

    public void setNd5(String nd5) {
        this.nd5 = nd5;
    }

    public void setNd4(String nd4) {
        this.nd4 = nd4;
    }

    public void setNd3(String nd3) {
        this.nd3 = nd3;
    }

    public void setNd2(String nd2) {
        this.nd2 = nd2;
    }

    public void setNd1(String nd1) {
        this.nd1 = nd1;
    }

    public static void main(String[] args) {
        System.out.println("1.2".substring("1.2".indexOf(".") + 1));
        System.out.println("1.2".substring(0, "1.2".indexOf(".")));
        System.out.println("20090101".substring(0, 4));
    }

}
