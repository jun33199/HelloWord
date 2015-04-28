package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.List;

/**
 * 无三方协议的综合申报form
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZhsbWithoutSfxyForm extends ZhsbForm
{
    public ZhsbWithoutSfxyForm()
    {
    }

    /**
     * 银行信息
     */
    private List bankInfo;

    public List getBankInfo()
    {
        return bankInfo;
    }
    public void setBankInfo(List bankInfo)
    {
        this.bankInfo = bankInfo;
    }
}