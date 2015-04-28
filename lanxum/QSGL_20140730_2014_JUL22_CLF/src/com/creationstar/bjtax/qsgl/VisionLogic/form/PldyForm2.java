package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.ttsoft.common.util.QueryCache;

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
public class PldyForm2 extends QueryBaseForm {
    // 批次号
    private String pch = "";
    // 数据提供者计算机代码
    private String tgzjsjdm = "";
    // 数据提供者名称
    private String tgzgjmc = "";
    //导入时间段
    private String drsjBegin = "";
    private String drsjEnd = "";
    // 查询条件
    private Object queryObj = new Object();
    // 查询导入信息表结果集，供返回时回填form使用
    private QueryCache tmpQyeryCache = new QueryCache();

    // 查询某批次结果集，供打印使用
    ArrayList resultList = new ArrayList();

    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setTgzgjmc(String tgzgjmc) {
        this.tgzgjmc = tgzgjmc;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setTmpQyeryCache(QueryCache tmpQyeryCache) {
        this.tmpQyeryCache = tmpQyeryCache;
    }

    public void setQueryObj(Object obj) {
        this.queryObj = obj;
    }

    /**
     * 前台页面的查询条件
     * @return Object
     */
    public Object getData() {
        PldyBo2 bo = new PldyBo2();
        bo.setPch(pch);
        bo.setTgzgjmc(tgzgjmc);
        bo.setTgzjsjdm(tgzjsjdm);
        bo.setDrsjBegin(drsjBegin);
        bo.setDrsjEnd(drsjEnd);
        return bo;
    }

    public String getTgzgjmc() {
        return tgzgjmc;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public ArrayList getResultList() {
        return resultList;
    }

    public QueryCache getTmpQyeryCache() {
        return tmpQyeryCache;
    }

    public Object getQueryObj() {
        return queryObj;
    }
}
