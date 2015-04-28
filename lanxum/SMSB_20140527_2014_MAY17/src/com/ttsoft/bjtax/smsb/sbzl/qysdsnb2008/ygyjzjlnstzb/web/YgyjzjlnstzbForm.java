package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ygyjzjlnstzb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class YgyjzjlnstzbForm extends QysdsNewForm {
    public YgyjzjlnstzbForm() {
    }

    public String[] getSb_columns() {
        return sb_columns;
    }

    public List getYgyjzjlnstzList() {
        return ygyjzjlnstzList;
    }

    public void setSb_columns(String[] sb_columns) {
        this.sb_columns = sb_columns;
    }

    public void setYgyjzjlnstzList(List ygyjzjlnstzList) {
        this.ygyjzjlnstzList = ygyjzjlnstzList;
    }

    /**
     * 存放固定信息的字符串数组
     */
    private String[] sb_columns = {"hc", "qczzje", "qcjsjc", "qmzzje", "qmjsjc",
                                  "nstze"};
    /**
     * 存放固定行数据的LIST
     */
    private List ygyjzjlnstzList = new ArrayList();

}
