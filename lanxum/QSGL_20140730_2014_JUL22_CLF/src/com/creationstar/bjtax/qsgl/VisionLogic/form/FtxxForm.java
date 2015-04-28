package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Arith;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;


/**
 *
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
public class FtxxForm extends BaseForm {
    /**
     * 是否是个人
     */
    private String person = "false";


    /**
     * 是否是不征
     */
    private boolean bzqs = false;

    /**
     * 税额调整
     */
    private String setz;

    /**
     * 土地级次
     */
    private String tdjc;

    /**
     * 容积率
     */
    private String rjl;

    
    /**
     * 申报日期
     */
    private String sbrq;
    
    public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	/**
     * 土地、房屋唯一标识
     */
    public String tdfwwybz;
    /**
     * 房地产项目名称
     */
    public String fdcxmmc;
    /**
     * 合约签订时间
     */
    public String hyqdsj;
    /**
     * 分类
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm = "07";

    /**
     * 土地、房屋权属转移类型
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    public String tdfwqszylxmc;
    public String tdfwqszylxdm;

    /**
     * 房屋类别
     */
    public ArrayList fwlbList = new ArrayList();
    public String fwlbmc;
    public String fwlbdm = "03";

    /**
     * 是否二手房
     */
    public String sfesf = Constants.TUFWXX_SFESF_FALSE;
    /**
     * 土地、房屋座落地址
     */
    public String tdfwzldz;
    /**
     * 土地、房屋权属转移面积
     */
    public String tdfwqszymj;
    /**
     * 房屋建筑面积
     */
    public String fwjzmj;
    /**
     * 成交价格 元(人民币)
     */
    public String cjjgyrmb;
    /**
     * 评估价格
     */
    public String pgjg;
    /**
     * 成交价格 元(外币)
     */
    public String cjjgywb;
    /**
     *币种
     */
    public ArrayList bzList = new ArrayList();
    public String bzmc;
    public String bzdm = "USD";

    /**
     * 土地级次列表
     */
    private ArrayList tdjcList = new ArrayList();


    /**
     * 汇率
     */
    public String hn;
    /**
     * 折合元(人民币)
     */
    public String zhyrmb;

    //yangxiao 2008-12-06 start
    /**
     * 扫描标示
     */
    public String smbs;
    //yangxiao 2008-12-06 end

    /**
     * 获得成交价格 元(人民币)
     * @return String
     */
    public String getCjjgyrmb() {
        return cjjgyrmb;
    }

    /**
     * 获得成交价格 元(外币)
     * @return String
     */
    public String getCjjgywb() {
        return cjjgywb;
    }

    /**
     * 获得房地产项目名称
     * @return String
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     * 获得房屋建筑面积
     * @return String
     */
    public String getFwjzmj() {
        return fwjzmj;
    }

    /**
     * 获得汇率
     * @return String
     */
    public String getHn() {
        return hn;
    }

    /**
     * 获得合约签订时间
     * @return String
     */
    public String getHyqdsj() {
        return hyqdsj;
    }

    /**
     * 获得评估价格
     * @return String
     */
    public String getPgjg() {
        return pgjg;
    }

    /**
     * 获得土地、房屋权属转移面积
     * @return String
     */
    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     * 获得土地、房屋唯一标识
     * @return String
     */
    public String getTdfwwybz() {
        return tdfwwybz;
    }

    /**
     * 获得土地、房屋座落地址
     * @return String
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     * 获得折合元(人民币)
     * @return String
     */
    public String getZhyrmb() {
        return zhyrmb;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public ArrayList getFwlbList() {
        return fwlbList;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzdm() {
        return bzdm;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFldm() {
        return fldm;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getFwlbdm() {
        return fwlbdm;
    }

    public String getFwlbmc() {
        return fwlbmc;
    }

    public String getTdfwqszylxdm() {
        return tdfwqszylxdm;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public String getPerson() {
        return person;
    }

    public boolean isBzqs() {
        return bzqs;
    }

    public String getSetz() {
        return setz;
    }

    public String getRjl() {
        return rjl;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getSfesf() {
        return sfesf;
    }

    public ArrayList getTdjcList() {
        return tdjcList;
    }

    public String getSmbs() {
        return smbs;
    }

    /**
     * 设置成交价格 元(人民币)
     * @param cjjgyrmb String
     */
    public void setCjjgyrmb(String cjjgyrmb) {
        this.cjjgyrmb = cjjgyrmb;
    }

    /**
     * 设置成交价格 元(外币)
     * @param cjjgywb String
     */
    public void setCjjgywb(String cjjgywb) {
        this.cjjgywb = cjjgywb;
    }

    /**
     * 设置房地产项目名称
     * @param fdcxmmc String
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    /**
     * 设置房屋建筑面积
     * @param fwjzmj String
     */
    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * 设置汇率
     * @param hn String
     */
    public void setHn(String hn) {
        this.hn = hn;
    }

    /**
     *  设置合约签订时间
     * @param hyqdsj String
     */
    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    /**
     *  设置评估价格
     * @param pgjg String
     */
    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    /**
     * 设置土地、房屋权属转移面积
     * @param tdfwqszymj String
     */
    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * 设置土地、房屋唯一标识
     * @param tdfwwybz String
     */
    public void setTdfwwybz(String tdfwwybz) {
        this.tdfwwybz = tdfwwybz;
    }

    /**
     * 设置土地、房屋座落地址
     * @param tdfwzldz String
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * 设置折合元(人民币)
     * @param zhyrmb String
     */
    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFwlbdm(String fwlbdm) {
        this.fwlbdm = fwlbdm;
    }

    public void setFwlbmc(String fwlbmc) {
        this.fwlbmc = fwlbmc;
    }

    public void setTdfwqszylxdm(String tdfwqszylxdm) {
        this.tdfwqszylxdm = tdfwqszylxdm;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setBzqs(boolean bzqs) {
        this.bzqs = bzqs;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setSfesf(String sfesf) {
        this.sfesf = sfesf;
    }

    public void setTdjcList(ArrayList tdjcList) {
        this.tdjcList = tdjcList;
    }

    public void setSmbs(String smbs) {
        this.smbs = smbs;
    }

    /**
     * 将FtxxForm中的数据放到tufwxx的Vo中，然后返回tufwxx
     * @return String
     */

    public Object getData() {
        Tufwxx tufwxx = new Tufwxx();
        tufwxx.sbbh = this.sbbh;
        tufwxx.tdfwid = this.tdfwwybz;
        tufwxx.fdcxmmc = this.fdcxmmc;
        tufwxx.htqdsj = DataConvert.String2Timestamp(this.hyqdsj);
        tufwxx.fldm = this.fldm;
        tufwxx.flmc = this.flmc;

        tufwxx.setz = this.setz;

        tufwxx.tdfwqszylx = this.tdfwqszylxdm;
        tufwxx.tdfwqszymc = this.tdfwqszylxmc;

        tufwxx.fwlxdm = this.fwlbdm;
        tufwxx.fwlxmc = this.fwlbmc;

        tufwxx.tdfwzldz = this.tdfwzldz;

        //修改小数部位显示为三位，modify by fujx，20081129
        if (null == this.tdfwqszymj || "null".equals(this.tdfwqszymj) ||
            "".equals(this.tdfwqszymj)) {
            tufwxx.tdfwqszymj = DataConvert.String2BigDecimal(Arith.roundStr(
                    "0.000", 3));
        } else {
            tufwxx.tdfwqszymj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                    tdfwqszymj, 3));
        }
        tufwxx.cjjgrmb = DataConvert.String2BigDecimal(this.cjjgyrmb);
        tufwxx.pgjgrmb = DataConvert.String2BigDecimal(this.pgjg);
        tufwxx.cjjgwb = DataConvert.String2BigDecimal(this.cjjgywb);
        tufwxx.bzdm = this.bzdm;
        tufwxx.bzmc = this.bzmc;
        //修改小数部位显示为三位，modify by fujx，20081129
        if (null == this.fwjzmj || "null".equals(this.fwjzmj) ||
            "".equals(this.fwjzmj)) {
            tufwxx.fwjzmj = DataConvert.String2BigDecimal(Arith.roundStr(
                    "0.000", 3));
        } else {
            tufwxx.fwjzmj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                    fwjzmj, 3));
        }
        tufwxx.hldm = DataConvert.String2BigDecimal(this.hn);
        Debug.out("tdfwqszylxdm=" + tdfwqszylxdm);
        Debug.out("jzmj=" + fwjzmj);
        tufwxx.zhjgrmb = DataConvert.String2BigDecimal(this.zhyrmb);
        tufwxx.rjl = this.rjl;
        tufwxx.tdjc = this.tdjc;
        tufwxx.sfesf = this.sfesf;
        return tufwxx;
    }

    /**
     * 将tufwxx 中的数据放到FtxxForm中
     */

    public void setData(Tufwxx tufwxx) {
        this.sbbh = tufwxx.sbbh;
        this.tdfwwybz = tufwxx.tdfwid;
        this.fdcxmmc = tufwxx.fdcxmmc;
        this.hyqdsj = DataConvert.TimeStamp2String(tufwxx.htqdsj);
        this.fldm = tufwxx.fldm;
        this.tdfwqszylxdm = tufwxx.tdfwqszylx;
        this.fwlbdm = tufwxx.fwlxdm;
        this.tdfwzldz = tufwxx.tdfwzldz;
        this.tdfwqszymj = DataConvert.BigDecimal2String(tufwxx.tdfwqszymj, 2, false);
        this.cjjgyrmb = DataConvert.BigDecimal2String(tufwxx.cjjgrmb, 2, false);
        this.pgjg = DataConvert.BigDecimal2String(tufwxx.pgjgrmb, 2, false);
        this.cjjgywb = DataConvert.BigDecimal2String(tufwxx.cjjgwb, 2, false);
        this.bzdm = tufwxx.bzdm;
        this.hn = DataConvert.BigDecimal2String(tufwxx.hldm, 5, false);
        this.zhyrmb = DataConvert.BigDecimal2String(tufwxx.zhjgrmb, 2, false);
        this.fwjzmj = DataConvert.BigDecimal2String(tufwxx.fwjzmj, 2, false);

        this.flmc = tufwxx.flmc;
        this.tdfwqszylxmc = tufwxx.tdfwqszymc;
        this.fwlbmc = tufwxx.fwlxmc;
        this.bzmc = tufwxx.bzmc;
//        this.setz = tufwxx.setz;
        this.rjl = tufwxx.rjl;
        this.tdjc = tufwxx.tdjc;
        this.sfesf = tufwxx.sfesf;
    }
}
