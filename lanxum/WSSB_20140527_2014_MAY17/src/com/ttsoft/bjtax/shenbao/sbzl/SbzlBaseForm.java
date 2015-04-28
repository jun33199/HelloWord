package com.ttsoft.bjtax.shenbao.sbzl;

import com.ttsoft.framework.form.BaseForm;


/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

public class SbzlBaseForm  extends BaseForm
{
   protected String frompage;

    public SbzlBaseForm()
    {
    }
    public String getFrompage()
    {
        return frompage;
    }
    public void setFrompage(String frompage)
    {
        this.frompage = frompage;
    }

}