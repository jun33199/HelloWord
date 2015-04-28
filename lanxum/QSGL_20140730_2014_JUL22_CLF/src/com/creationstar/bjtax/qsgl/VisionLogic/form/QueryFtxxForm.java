package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryFtxxBo;
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
 * @author 卫军丽
 * @version 1.0
 */

public class QueryFtxxForm extends BaseForm {
    /**
     * 存在标示，true：存在；false：不存在
     */
    private boolean exist = false;

    /**
     * 表示是否是查询结果
     */
    private boolean afterQuery = false;

    /**
     * 使用情况 Sbftgl值对象的列表
     */
    private ArrayList listSbxx = new ArrayList();
    /**
     * 土地、房屋唯一标识
     */
    public String tdfwid;

    /**
     * 房地产项目名称
     */
    private String fdcxmmx;
    /**
     * 合同（契约）签订时间
     */
    private String htqdsj;
    /**
     * 分类
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm;

    /**
     * 土地、房屋权属转移类型
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    private String tdfwqszylx;
    private String tdfwqszylxmc;

    /**
     * 房屋类别
     */
    public ArrayList fwlxList = new ArrayList();
    private String fwlxdm;
    private String fwlxmc;


    /**
     * 土地、房屋座落地址
     */
    private String tdfwzldz;
    /**
     * 土地、房屋权属转移面积
     */
    private String tdfwqszymj;
    /**
     * 房屋建筑面积
     */
    private String fwjzmj;
    /**
     * 成交价格（人民币）
     */
    private String cjjgrmb;
    /**
     * 评估价格（人民币）
     */
    private String pgjgrmb;
    /**
     * 成交价格（外币）
     */
    private String cjjgwb;
    /**
     * 币种
     */
    public ArrayList bzList = new ArrayList();
    private String bzdm;
    private String bzmc;
    /**
     * 汇率
     */
    private String hl;
    /**
     * 折合价格（人民币）
     */
    private String zhjgrmb;
    /**
     * 申报人
     */
    private String cjr;
    /**
     * 申报日期
     */
    private String cjrq;

    public boolean isAfterQuery() {
        return afterQuery;
    }

    public String getBzdm() {
        return bzdm;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getCjjgrmb() {
        return cjjgrmb;
    }

    public String getCjjgwb() {
        return cjjgwb;
    }

    public boolean isExist() {
        return exist;
    }

    public String getFdcxmmx() {
        return fdcxmmx;
    }

    public String getFldm() {
        return fldm;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getFwjzmj() {
        return fwjzmj;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public ArrayList getFwlxList() {
        return fwlxList;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getHl() {
        return hl;
    }

    public String getHtqdsj() {
        return htqdsj;
    }

    public String getPgjgrmb() {
        return pgjgrmb;
    }

    public String getTdfwid() {
        return tdfwid;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getZhjgrmb() {
        return zhjgrmb;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getCjr() {
        return cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setZhjgrmb(String zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    public void setPgjgrmb(String pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setHtqdsj(String htqdsj) {
        this.htqdsj = htqdsj;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxList(ArrayList fwlxList) {
        this.fwlxList = fwlxList;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFdcxmmx(String fdcxmmx) {
        this.fdcxmmx = fdcxmmx;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setCjjgwb(String cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    public void setCjjgrmb(String cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 将QueryFtxxBo中的查询结果保存到form中，以备页面调用
     */
    public void setData(QueryFtxxBo bo) {
        this.tdfwid = bo.getTdfwid();
        this.fdcxmmx = bo.getFdcxmmx();
        this.htqdsj = DataConvert.TimeStamp2String(bo.getHtqdsj());
        this.flmc = bo.getFlmc();
        this.tdfwqszylxmc = bo.getTdfwqszylxmc();
        this.fwlxmc = bo.getFwlxmc();
        this.tdfwzldz = bo.getTdfwzldz();
        this.tdfwqszymj = DataConvert.BigDecimal2String(bo.getTdfwqszymj());
        this.fwjzmj = DataConvert.BigDecimal2String(bo.getFwjzmj());
        this.cjjgrmb = DataConvert.BigDecimal2String(bo.getCjjgrmb());
        this.pgjgrmb = DataConvert.BigDecimal2String(bo.getPgjgrmb());
        this.cjjgwb = DataConvert.BigDecimal2String(bo.getCjjgwb());
        this.bzmc = bo.getBzmc();
        this.hl = DataConvert.BigDecimal2String(bo.getHl());
        this.zhjgrmb = DataConvert.BigDecimal2String(bo.getZhjgrmb());
        this.cjr = bo.getCjr();
        this.cjrq = DataConvert.TimeStamp2String(bo.getCjrq());
        this.listSbxx = bo.getListSbxx();

    }

    /**
     * 清空查询结果
     */
    public void clearResult() {
        this.fdcxmmx = "";
        this.htqdsj = "";
        this.flmc = "";
        this.tdfwqszylxmc = "";
        this.fwlxmc = "";
        this.tdfwzldz = "";
        this.tdfwqszymj = "";
        this.fwjzmj = "";
        this.cjjgrmb = "";
        this.pgjgrmb = "";
        this.cjjgwb = "";
        this.bzmc = "";
        this.hl = "";
        this.zhjgrmb = "";
        this.cjr = "";
        this.cjrq = "";
        this.listSbxx = null;

    }


}
