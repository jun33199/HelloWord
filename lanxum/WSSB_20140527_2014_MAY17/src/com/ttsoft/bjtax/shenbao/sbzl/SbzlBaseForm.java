package com.ttsoft.bjtax.shenbao.sbzl;

import com.ttsoft.framework.form.BaseForm;


/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 ��һ���Źɷ����޹�˾����Ȩ����.</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
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