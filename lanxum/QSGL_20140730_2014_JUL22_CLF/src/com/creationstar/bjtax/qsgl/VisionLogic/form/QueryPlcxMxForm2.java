package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo2;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 *
 * 说明：该form是针对页面中"批量受理(税务人员)"模块
 */
public class QueryPlcxMxForm2 extends QueryBaseForm {
    //批次号
    private String pch;

    //状态
    private String zt;

    public String getPch() {
        return pch;
    }

    public String getZt() {
        return zt;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    /**
     * 前台页面的查询条件
     * @return Object
     */
    public Object getData() {
        PlcxMxBo2 bo = new PlcxMxBo2();
        bo.setPch(pch);
        bo.setZt(zt);
        return bo;
    }
}
