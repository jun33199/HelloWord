

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srbsydw.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:����һ��������ҵ��λ��������塢������ҵ��λ������Ŀ��ϸ��</p>
 * @author hezy
 * @version 1.1
 */


public class SrbsydwForm2009 extends QysdsNewForm {
    public SrbsydwForm2009() {
    }

    /**
     * ����˰�걨�б������Ŀ���� �дΡ���� String[]
     */
    private String[] srb_columns = {"hc", "je"};

    public String[] getSrb_columns() {
        return srb_columns;
    }

    public void setSrb_columns(String[] srb_columns) {
        this.srb_columns = srb_columns;
    }


}
