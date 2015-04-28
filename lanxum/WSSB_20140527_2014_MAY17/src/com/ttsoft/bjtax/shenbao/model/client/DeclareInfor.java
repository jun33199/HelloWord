package com.ttsoft.bjtax.shenbao.model.client;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import java.util.List;
import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 综合申报中前台往后台传输申报数据</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-8-22
 */

public class DeclareInfor implements Serializable
{

    //申报缴款主表信息
    private Sbjkzb sbjkzb;

    //申报的明细信息
    private List sbjkmxInfo;
    //打印标识:1.一票一税;2.一票多税(科目);3.一票多税(税目)
    private int printTag;

    //是否需要返回缴款数据标识
    private boolean isReturnPaymentInfo;

    public DeclareInfor()
    {
    }

    //构造函数同时填充了 sbjkzb(主表信息)和sbjkmxInfo(多条明细信息)
    public DeclareInfor(Sbjkzb sbjkzb, List sbjkmxInfo)
    {
        this.sbjkzb = sbjkzb;
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public Sbjkzb getSbjkzb()
    {
        return sbjkzb;
    }

    public void setSbjkzb(Sbjkzb sbjkzb)
    {
        this.sbjkzb = sbjkzb;
    }

    public void setSbjkmxInfo(List sbjkmxInfo)
    {
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public List getSbjkmxInfo()
    {
        return sbjkmxInfo;
    }

    public boolean isIsReturnPaymentInfo()
    {
        return isReturnPaymentInfo;
    }

    public void setIsReturnPaymentInfo(boolean isReturnPaymentInfo)
    {
        this.isReturnPaymentInfo = isReturnPaymentInfo;
    }
    public int getPrintTag()
    {
        return printTag;
    }
    public void setPrintTag(int printTag)
    {
        this.printTag = printTag;
    }
}