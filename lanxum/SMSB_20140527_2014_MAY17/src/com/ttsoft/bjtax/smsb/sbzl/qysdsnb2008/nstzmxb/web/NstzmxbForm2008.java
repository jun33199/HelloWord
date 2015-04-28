package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.nstzmxb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class NstzmxbForm2008 extends QysdsNewForm {
    public NstzmxbForm2008() {
    }

    /**
     * 存放固定信息的字符串数组
     */
     private String[] sb_columns ={"hc","zzje","ssje","tzje","tjje"};
     /**
      * 存放固定行数据的LIST
      */
     private List nstzmxzjbsj = new ArrayList();

     public void setSb_cloumns(String[] je)
     {
             this.sb_columns = je;
     }
    public String[] getSb_cloumns()
    {
            return this.sb_columns;
    }
    public void setDataList(List list)
    {
            this.nstzmxzjbsj = list;
    }
    public List getDataList()
    {
            return this.nstzmxzjbsj;
    }

}
