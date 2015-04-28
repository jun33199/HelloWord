/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 综合申报中前台往后台传输申报数据</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DeclareInfor
    implements Serializable
{

    /**
     * 申报缴款主表信息
     */
    private Sbjkzb sbjkzb;

    /**
     * 申报的明细信息
     */
    private List sbjkmxInfo;

    /**
     * 打印标识:1.一票一税;2.一票多税(科目);3.一票多税(税目)
     */
    private int printTag;

    /**
     * 是否需要返回缴款数据标识
     */
    private boolean isReturnPaymentInfo;

    public DeclareInfor ()
    {
    }

    //构造函数同时填充了 sbjkzb(主表信息)和sbjkmxInfo(多条明细信息)
    public DeclareInfor (Sbjkzb sbjkzb, List sbjkmxInfo)
    {
        this.sbjkzb = sbjkzb;
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public Sbjkzb getSbjkzb ()
    {
        return sbjkzb;
    }

    public void setSbjkzb (Sbjkzb sbjkzb)
    {
        this.sbjkzb = sbjkzb;
    }

    public void setSbjkmxInfo (List sbjkmxInfo)
    {
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public List getSbjkmxInfo ()
    {
        return sbjkmxInfo;
    }

    public boolean isIsReturnPaymentInfo ()
    {
        return isReturnPaymentInfo;
    }

    public void setIsReturnPaymentInfo (boolean isReturnPaymentInfo)
    {
        this.isReturnPaymentInfo = isReturnPaymentInfo;
    }

    public int getPrintTag ()
    {
        return printTag;
    }

    public void setPrintTag (int printTag)
    {
        this.printTag = printTag;
    }
}