package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.List;

/**
 * ������Э����ۺ��걨form
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
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
     * ������Ϣ
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