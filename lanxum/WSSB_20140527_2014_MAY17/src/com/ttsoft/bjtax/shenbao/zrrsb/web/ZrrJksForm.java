package com.ttsoft.bjtax.shenbao.zrrsb.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;

public class ZrrJksForm extends BaseForm
{
    private List dataList;
    private List nlwDataList;
    private String jkshIndex;
    private String sbbhIndex;
    private String jsjdm;
    private String nsrmc;
    private int lwState = 0;
    private int ypdsCurrentPage = 0;

    public ZrrJksForm()
    {
        dataList = new ArrayList();
        nlwDataList = new ArrayList();
    }

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
    public String getJsjdm()
    {
        return jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public List getDataList()
    {
        return dataList;
    }

    public List getNlwDataList()
    {
        return nlwDataList;
    }

    public int getLwState()
    {
        return lwState;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setDataList(List dataList)
    {
        this.dataList = dataList;
    }

    public void setNlwDataList(List nlwDataList)
    {
        this.nlwDataList = nlwDataList;
    }

    public void setLwState(int lwState)
    {
        this.lwState = lwState;
    }
}
