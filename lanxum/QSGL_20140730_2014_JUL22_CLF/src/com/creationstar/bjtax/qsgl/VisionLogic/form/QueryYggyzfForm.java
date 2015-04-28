package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryYggyzfBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;


/**
 * <p>Title: 查询已购公有住房信息使用情况Form</p>
 *
 * <p>Description: 对应查询已购公有住房使用情况页面，保存查询条件和查询结果</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class QueryYggyzfForm extends BaseForm {

    /**
     * 存在标示，true：存在；false：不存在
     */
    private boolean exist = false;

    /**
     * 表示是否是查询结果
     */
    private boolean afterQuery = false;

    /**
     * 已购公有住房权属证书号
     */
    private String yggyzfqszsh;
    /**
     * 房屋坐落地址
     */
    private String zldz;
    /**
     * 出售合同（契约）签订时间
     */
    private String qdsj;
    /**
     * 建筑面积
     */
    private String jzmj;
    /**
     * 成交价格
     */
    private String cjjg;
    /**
     * 剩余额
     */
    private String sye;
    /**
     * 房屋权属证书号 add by zhangyj 20090219
     */
    private String fwqszsh;

    /**
     * 使用情况 Sbcqgl值对象的列表
     */
    private ArrayList listSbxx;
    private QueryYggyzfBo data;

    /**
     * 将查询结果保存到form中
     */
    public void setData(QueryYggyzfBo bo) {
        this.zldz = bo.getZldz();
        this.qdsj = DataConvert.TimeStamp2String(bo.getQdsj());
        this.jzmj = DataConvert.BigDecimal2String(bo.getJzmj());
        this.cjjg = DataConvert.BigDecimal2String(bo.getCjjg());
        this.sye = DataConvert.BigDecimal2String(bo.getSye());
        this.listSbxx = bo.getListSbxx();
       this.fwqszsh=bo.getFwqszsh();
    }

    /**
     * 清空查询结果
     */
    public void clearResult() {
        this.qdsj = "";
        this.jzmj = "";
        this.zldz = "";
        this.listSbxx = null;
        this.sye = "";
       this.fwqszsh = "";
    }


    public boolean isAfterQuery() {
        return afterQuery;
    }

    public boolean isExist() {
        return exist;
    }

    public String getJzmj() {
        return jzmj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getQdsj() {
        return qdsj;
    }

    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    public String getZldz() {
        return zldz;
    }

    public String getCjjg() {
        return cjjg;
    }

    public String getSye() {
        return sye;
    }
    
    public String getFwqszsh() {
        return fwqszsh;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setQdsj(String qdsj) {
        this.qdsj = qdsj;
    }

    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public void setSye(String sye) {
        this.sye = sye;
    }

    public void setFwqszsh(String fwqszsh) {
        this.fwqszsh = fwqszsh;
    }

}
