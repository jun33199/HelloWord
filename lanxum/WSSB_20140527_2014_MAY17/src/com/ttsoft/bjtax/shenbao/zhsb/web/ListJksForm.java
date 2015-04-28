package com.ttsoft.bjtax.shenbao.zhsb.web;

import com.ttsoft.framework.form.BaseForm;

public class ListJksForm extends BaseForm
{
    public ListJksForm()
    {
    }

    private String jkshIndex;
    private String sbbhIndex;
    private int ypdsCurrentPage = 0;

    public String getJkshIndex()
    {
        return jkshIndex;
    }
    public void setJkshIndex(String jkshIndex)
    {
        this.jkshIndex = jkshIndex;
    }
    public String getSbbhIndex()
    {
        return sbbhIndex;
    }
    public void setSbbhIndex(String sbbhIndex)
    {
        this.sbbhIndex = sbbhIndex;
    }
    public int getYpdsCurrentPage()
    {
        return ypdsCurrentPage;
    }
    public void setYpdsCurrentPage(int ypdsCurrentPage)
    {
        this.ypdsCurrentPage = ypdsCurrentPage;
    }
}