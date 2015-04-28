package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: 政策维护的查询Form </p>
 *
 * <p>Description: 继承QueryBaseForm的政策查询Form </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 赵博
 * @version 1.0
 */
public class QueryZcwhForm extends QueryBaseForm {
    public QueryZcwhForm() {
    }

    public String getZbdm() {
        return zbdm;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    /**
     * 清空用函数
     */
    public void clear() {
        this.setZbdm("");
        this.setZbmc("");
    }

    /**
     * 实现父类定义的方法，将 QueryForm中的数据放入到相应的 BO 类中，以完成框架
     * @return Object
     */
    public Object getData() {
        Debug.out("into QueryZcwhForm....");
        Zcwh zw = new Zcwh();
        zw.setZbdm(this.getZbdm());
        zw.setZbmc(this.getZbmc());
        return zw;
    }

    /**
     * 指标代码
     */
    private String zbdm;

    /**
     * 指标名称
     */
    private String zbmc;
}
