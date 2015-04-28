package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class CqxxForm extends BaseForm {

    /**
     * 判断是否来自补录
     */
    private boolean bl = false;
    /**
     * 是否是个人
     */
    private String person = "false";

    /**
     * 从修改申报信息页面进入还是增加页面
     * "ADD"
     * "EDIT"
     */
    private String entrypage = "ADD";

    /**
     * 是否是新的录入,也就是第一次使用该拆迁信息
     * 如果从库中查询查来的就是false
     */
    private boolean first = true;

    /**
     * 拆迁协议号码
     */
    public String cqxyhm;
    //昌－分局的简称；房－征收点的简称（房管所征收点）；２００５－年度；５位全分局流水号
    private String fjmc;
    private String zsdmc;
    private String nd;
    private String lsh;


    /**
     * 被拆迁房屋坐落地址
     */
    public String bcqfwzldz;
    /**
     * 拆迁补偿额
     */
    public String cqbce;
    /**
     * 本次使用补偿额
     */
    public String bcsybce;
    /**
     * 拆迁补偿剩余额
     * @return String
     */
    public String cqbcsye;

    /**
     *剩余额用完标志
     */
    public String syeywbz = "00";


    /**
     * 将CqGrForm中的数据放到jsblcq的Vo中，然后返回jsblcq
     * @return String
     */
    public Object getData() {
        Jsblcq jsblcq = new Jsblcq();
        jsblcq.bcsybce = DataConvert.String2BigDecimal(this.bcsybce);
        jsblcq.cqbce = DataConvert.String2BigDecimal(this.cqbce);
        jsblcq.cqbcsye = DataConvert.String2BigDecimal(this.getCqbcsye());
        jsblcq.cqxyh = this.cqxyhm;
        jsblcq.cqxyh = "京" + this.fjmc + "地税" + this.zsdmc + "契拆字" + nd + "第" +
                       lsh + "号";
        jsblcq.sbbh = this.sbbh;
        if (person.equals("true")) {
            jsblcq.yhbs = Constants.YHBZ_GR; //个人
        } else {
            jsblcq.yhbs = Constants.YHBZ_FGR; //非个人
        }
        jsblcq.zldz = this.bcqfwzldz;
        jsblcq.ztbs = "0";
        if (this.syeywbz.equals("on")) {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }

        Debug.out("CqGrForm sbbh: " + sbbh);

        return jsblcq;
    }

    /**
     * 获取修改的拆迁信息
     * 将CqGrForm中的数据放到jsblcq的Vo中，然后返回jsblcq
     * @return String
     */
    public Object getDataForUpdate() {
        Jsblcq jsblcq = new Jsblcq();
        jsblcq.bcsybce = DataConvert.String2BigDecimal(this.bcsybce);
        jsblcq.cqbce = DataConvert.String2BigDecimal(this.cqbce);
        jsblcq.cqbcsye = DataConvert.String2BigDecimal(this.getCqbcsye());
        jsblcq.cqxyh = this.cqxyhm;
        jsblcq.sbbh = this.sbbh;
        if (person.equals("true")) {
            jsblcq.yhbs = Constants.YHBZ_GR; //个人
        } else {
            jsblcq.yhbs = Constants.YHBZ_FGR; //非个人
        }
        jsblcq.zldz = this.bcqfwzldz;
        jsblcq.ztbs = "0";
        if (this.syeywbz.equals("on")) {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }
        Debug.out("CqGrForm sbbh: " + sbbh);

        return jsblcq;
    }

    /**
     * 将jsblcq 中的数据放到CqGrForm中
     */
    public void setData(Jsblcq jsblcq) {
        this.bcsybce = DataConvert.BigDecimal2String(jsblcq.bcsybce, 2, false);
        this.cqbce = DataConvert.BigDecimal2String(jsblcq.cqbce, 2, false);
        this.cqbcsye = DataConvert.BigDecimal2String(jsblcq.cqbcsye, 2, false);
        this.cqxyhm = jsblcq.cqxyh;
        if (jsblcq.syeywbz != null) {
            if (jsblcq.syeywbz.equals(Constants.JSBL_SYEYWBZ_YONGWAN)) {
                this.syeywbz = "on";
            } else {
                this.syeywbz = jsblcq.syeywbz;
            }
        } else {

        }
        //this.fjmc = cqxyhm.
        this.bcqfwzldz = jsblcq.zldz;
    }

    /**
     * 按照给定规则分割银行字符串
     * @param s String
     * @return String[]
     */
    public String[] splitFjmc(String s) {
        String splitString = "--";
        String[] sz = new String[2];
        int len = s.length();
        int site = s.indexOf(splitString);
        int siteLen = splitString.length();
        //字符串型如 光大银行--110108983747585
        //取出银行名称
        sz[0] = s.substring(0, site);

        //取出银行帐号
        sz[1] = s.substring(site + siteLen, len);
        return sz;
    }


    /**
     * 获得本次可使用补偿额
     * @return String
     */
    public String getBcksybce() {
        return this.getCqbcsye();
    }

    /**
     * 获得被拆迁房屋坐落地址
     * @return String
     */
    public String getBcqfwzldz() {
        return bcqfwzldz;
    }

    /**
     * 获得本次使用补偿额
     * @return String
     */
    public String getBcsybce() {
        return bcsybce;
    }

    /**
     * 获得拆迁补偿额
     * @return String
     */
    public String getCqbce() {
        return cqbce;
    }

    /**
     * 获得拆迁补偿剩余额
     * @return String
     */
    public String getCqbcsye() {
        return cqbcsye;
    }

    /**
     * 获得拆迁协议号码
     * @return String
     */
    public String getCqxyhm() {
        return cqxyhm;
    }

    public String getPerson() {
        return person;
    }

    public String getEntrypage() {
        return entrypage;
    }

    public boolean isFirst() {
        return first;
    }

    public String getLsh() {
        return lsh;
    }

    public String getFjmc() {
        return fjmc;
    }

    public String getNd() {
        return nd;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    public boolean isBl() {
        return bl;
    }

    /**
     * 设置被拆迁房屋坐落地址
     * @param bcqfwzldz String
     */
    public void setBcqfwzldz(String bcqfwzldz) {
        this.bcqfwzldz = bcqfwzldz;
    }

    /**
     * 设置本次使用补偿额
     * @param bcsybce String
     */
    public void setBcsybce(String bcsybce) {
        this.bcsybce = bcsybce;
    }

    /**
     * 设置拆迁补偿额
     * @param cqbce String
     */
    public void setCqbce(String cqbce) {
        this.cqbce = cqbce;
    }

    /**
     * 设置拆迁补偿剩余额
     * @param cqbcsye String
     */
    public void setCqbcsye(String cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    /**
     * 设置拆迁协议号码
     * @param cqxyhm String
     */
    public void setCqxyhm(String cqxyhm) {
        this.cqxyhm = cqxyhm;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setEntrypage(String entrypage) {
        this.entrypage = entrypage;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

}
