package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.DataConvert;

/**
 *
 * <p>Title:补录完税证的form </p>
 *
 * <p>Description:补录完税证的form </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class WszForm extends BaseForm {
    /**
     * 票证种类代码
     */
    public String pzzldm;

    /**
     * 字别
     */
    public String ndzb;

    /**
     * 完税证号码
     */
    public String wszh;

    /**
     * 填发日期
     */
    public String tfrq;

    /**
     * 纳税人代码
     */
    public String nsrdm;

    /**
     * 纳税人名称
     */
    public String nsrmc;

    /**
     * 契约（合同）签订日期
     */
    public String htqdrq;

    /**
     * 房地产位置
     */
    public String fdcwz;

    /**
     * 契税所属开始日期
     */
    public String skssksrq;

    /**
     * 契税所属截至日期
     */
    public String skssjzrq;

    /**
     * 地址
     */
    public String dz;

    /**
     * 税种税目
     */
    public String szsmmc;
    public String szsmdm;

    /**
     * 税种
     */
    public String szmc;
    public String szdm;

    /**
     * 权属转移面积
     */
    public String qszymj;

    /**
     * 计税金额
     */
    public String jsje;

    /**
     * 税率
     */
    public String sl;

    /**
     * 实缴税额
     */
    public String sjse;

    /**
     * 金额合计
     */
    public String jehj_dx;

    /**
     * 金额合计
     */
    public String jehj;

    /**
     * 逾期天数
     */
    public String yqts;

    /**
     * 滞纳金
     */
    public String znj;

    /**
     * 征收机关
     */
    public String zsjg;

    /**
     * 备注
     */
    public String bz;


    /**
     * 生成的完税证的数量
     */
    public int wszTotals;

    /**
     * 打印到第几张完税证
     */
    public int printPages;

    /**
     * 缴款凭证号
     */
    public String jkpzh;

    /**
     * 申报汇总单号
     */
    public String sbhzdh;

    /**
     * 处理标记代码
     */
    public String clbjdm;

    /**
     * 完税证是否有分票的情况，0为没有，1为有
     */
    public String fp;

    /**
     * 完税证主表的vo
     */
    public Qswszz qswszz;

    /**
     * 完税证明细表的vo
     */
    public Qswszmx qswszmx;

    public void getWszzVo() {
        this.qswszz.setWszh(this.getWszh());
        this.qswszz.setClbjdm(this.getClbjdm());
        this.qswszz.setNdzb(this.getNdzb());
        this.qswszz.setPzzldm(this.getPzzldm());
    }

    public void setWszData() {
        this.qswszz.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.qswszz.setHjsjje(DataConvert.String2BigDecimal(this.getSjse()));

        this.qswszmx.setSkssksrq(DataConvert.String2Timestamp(this.getSkssksrq()));
        this.qswszmx.setSkssksrq(DataConvert.String2Timestamp(this.getSkssjzrq()));
        this.qswszmx.setJsje(DataConvert.String2BigDecimal(this.getJsje()));
        this.qswszmx.setSjse(this.qswszz.getHjsjje());
        this.qswszmx.setCjrq(this.qswszz.getCjrq());

    }

    /**
     * 获得字别
     * @return String
     */
    public String getNdzb() {
        return ndzb;
    }

    public String getBz() {
        return bz;
    }

    public String getDz() {
        return dz;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public String getHtqdrq() {
        return htqdrq;
    }

    public String getJehj() {
        return jehj;
    }

    public String getJsje() {
        return jsje;
    }

    public String getNsrdm() {
        return nsrdm;
    }

    public String getQszymj() {
        return qszymj;
    }

    public String getSjse() {
        return sjse;
    }

    public String getSl() {
        return sl;
    }

    public String getTfrq() {
        return tfrq;
    }

    public String getWszh() {
        return wszh;
    }

    public String getZsjg() {
        return zsjg;
    }

    /**
     * 设置字别
     * @param zb String
     */
    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setHtqdrq(String htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setNsrdm(String nsrdm) {
        this.nsrdm = nsrdm;
    }

    public void setQszymj(String qszymj) {
        this.qszymj = qszymj;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    public void setZsjg(String zsjg) {
        this.zsjg = zsjg;
    }

    public void setYqts(String yqts) {
        this.yqts = yqts;
    }

    public void setZnj(String znj) {
        this.znj = znj;
    }

    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }

    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    public void setJehj_dx(String jehj_dx) {
        this.jehj_dx = jehj_dx;
    }

    public void setSkssjzrq(String skssjzrq) {
        this.skssjzrq = skssjzrq;
    }

    public void setSkssksrq(String skssksrq) {
        this.skssksrq = skssksrq;
    }

    public void setWszTotals(int wszTotals) {
        this.wszTotals = wszTotals;
    }

    public void setPrintPages(int printPages) {
        this.printPages = printPages;
    }

    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setQswszmx(Qswszmx qswszmx) {
        this.qswszmx = qswszmx;
    }

    public void setQswszz(Qswszz qswszz) {
        this.qswszz = qswszz;
    }

    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getYqts() {
        return yqts;
    }

    public String getZnj() {
        return znj;
    }

    public String getSzdm() {
        return szdm;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getSzsmdm() {
        return szsmdm;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    public String getPzzldm() {
        return pzzldm;
    }

    public String getJehj_dx() {
        return jehj_dx;
    }

    public String getSkssjzrq() {
        return skssjzrq;
    }

    public String getSkssksrq() {
        return skssksrq;
    }

    public int getWszTotals() {
        return wszTotals;
    }

    public int getPrintPages() {
        return printPages;
    }

    public String getClbjdm() {
        return clbjdm;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public Qswszmx getQswszmx() {
        return qswszmx;
    }

    public Qswszz getQswszz() {
        return qswszz;
    }

    public String getSbhzdh() {
        return sbhzdh;
    }

    public String getFp() {
        return fp;
    }

}
