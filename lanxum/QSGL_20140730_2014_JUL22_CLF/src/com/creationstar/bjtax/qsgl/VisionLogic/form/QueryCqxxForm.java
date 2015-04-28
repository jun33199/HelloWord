package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryCqxxBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;

/**
 * <p>Title: 查询拆迁信息使用情况Form</p>
 *
 * <p>Description: 对应查询拆迁使用情况页面，保存查询条件和查询结果</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QueryCqxxForm extends BaseForm {
    public QueryCqxxForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 拆迁协议号码 此字段作为查询条件
     */
    private String cqxyh;

    /**
     * 存在标示，true：存在；false：不存在
     */
    private boolean exist = false;

    /**
     * 表示是否是查询结果
     */
    private boolean afterQuery = false;

    //以下字段作为查询结果显示拆迁信息
    /**
     * 拆迁房屋坐落地址
     */
    private String zldz;

    /**
     * 拆迁补偿额
     */
    private String cqbce;

    /**
     * 拆迁补偿剩余额
     */
    private String cqbcsye;
    /**
     * 申报人
     */
    private String cjr;
    /**
     * 申报日期
     */
    private String cjrq;

    /**
     *剩余额
     */
    private String sye;

    /**
     * 使用情况 Sbcqgl值对象的列表
     */
    private ArrayList listSbxx;

    /**
     * 将查询结果保存到form中
     */
    public void setData(QueryCqxxBo bo) {
        this.cqxyh = bo.getCqxyh();
        this.zldz = bo.getZldz();
        this.cqbce = DataConvert.BigDecimal2String(bo.getCqbce());
        this.cqbcsye = DataConvert.BigDecimal2String(bo.getCqbcsye());
        this.sye = DataConvert.BigDecimal2String(bo.getCqbcsye());
        this.cjr = bo.getCjr();
        this.cjrq = DataConvert.TimeStamp2String(bo.getCjrq());
        this.listSbxx = bo.getListSbxx();
    }

    /**
     * 清空查询结果
     */
    public void clearResult() {
        this.cqbce = "";
        this.cqbcsye = "";
        this.zldz = "";
        this.cjr = "";
        this.cjrq = "";
        this.sye = "";
        this.listSbxx = null;
    }

    public String getCqbce() {
        return cqbce;
    }

    public String getCqbcsye() {
        return cqbcsye;
    }

    public String getCqxyh() {
        return cqxyh;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getZldz() {
        return zldz;
    }

    public boolean isExist() {
        return exist;
    }

    public boolean isAfterQuery() {
        return afterQuery;
    }

    public String getCjr() {
        return cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public String getSye() {
        return sye;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCqxyh(String cqxyh) {
        this.cqxyh = cqxyh;
    }

    public void setCqbcsye(String cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    public void setCqbce(String cqbce) {
        this.cqbce = cqbce;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public void setSye(String sye) {
        this.sye = sye;
    }

    private void jbInit() throws Exception {
    }
}
