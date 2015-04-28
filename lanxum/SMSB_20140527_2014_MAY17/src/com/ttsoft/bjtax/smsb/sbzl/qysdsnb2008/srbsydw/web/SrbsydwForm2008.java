

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srbsydw.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:附表一（３）事业单位、社会团体、民办非企业单位收入项目明细表</p>
 * @author hezy
 * @version 1.1
 */


public class SrbsydwForm2008 extends QysdsNewForm {
    public SrbsydwForm2008() {
    }

    /**
     * 所得税年报列表标题项目代码 行次、金额 String[]
     */
    private String[] srb_columns = {"hc", "je"};

    public String[] getSrb_columns() {
        return srb_columns;
    }

    public void setSrb_columns(String[] srb_columns) {
        this.srb_columns = srb_columns;
    }


}
