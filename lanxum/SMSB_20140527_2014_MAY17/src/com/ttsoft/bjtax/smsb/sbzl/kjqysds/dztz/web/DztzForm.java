package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;


/**
 * <p>Title: 扣缴企业所得税-电子台帐Form</p>
 *
 * <p>Description: 记录电子台帐页面域值</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author liuc
 * @version 1.0
 */

import com.ttsoft.bjtax.smsb.model.kjqysds.*;
import com.ttsoft.framework.form.*;

public class DztzForm extends BaseForm
{
    public DztzForm()
    {
    }
	


    /**
     * 扣缴人计算机代码
     */
    private String jsjdm;


    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

}
