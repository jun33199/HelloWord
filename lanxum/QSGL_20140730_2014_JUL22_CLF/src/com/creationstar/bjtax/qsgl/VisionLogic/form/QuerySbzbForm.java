package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo;

public class QuerySbzbForm extends QueryBaseForm {
    public Object getData() {
        QuerySbzbBo bo = new QuerySbzbBo();
        bo.setSbbh(this.getSbbh());
        return bo;
    }
}
