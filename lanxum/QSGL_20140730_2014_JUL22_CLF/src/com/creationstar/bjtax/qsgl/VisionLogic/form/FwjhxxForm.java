package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
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
public class FwjhxxForm extends BaseForm {

    /**
     * 是否是个人申报进入的交换
     */
    private boolean person = true;


    /**
     * 交换的对方选择个人还是非个人，0个人
     */
    public String jhperson = "0";

    /**
     * 申报表号
     */
    public String sbbh;

    /**
     * 身份证件类型
     */

    public ArrayList sfzjlxList = new ArrayList();
    /**
     * 国际地区代码
     */
    public ArrayList gjdqList;
    /**
     * 土地房屋ID
     */
    public String tdfwid;

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
    public String fldm;
    /**
     * 土地、房屋权属转移类型
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    public String tdfwqszylx;
    public String tdfwqszymc;

    /**
     * 土地级次列表
     */
    private ArrayList tdjcList = new ArrayList();


    /**
     * 房屋类别
     */
    public ArrayList fwlbList = new ArrayList();
    public String fwlxdm;
    public String fwlxmc;

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
    public String bz;
    public String bzdm = "USD";

    /**
     *nsr
     */
    public List nsrList = new ArrayList();

    /**
     * 汇率
     */
    public String hn;
    /**
     * 折合元(人民币)
     */
    public String zhyrmb;

    /**
     * 容积率
     */
    public String rjl;

    /**
     * 土地级次
     */
    public String tdjc;

    /**
     * 缴款方式
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm = "01";
    public String jkfsmc;
    public String fcjslh;
    /**
     * 产权人页面数组
     */
    public String cqrJs = Constants.CQRJS_INIT;


    //非个人
    public ArrayList nsrlxList;
    //父类定义了
    //public String nsrlx;
    public String nsrlxmc;

    /**
     * 非个人纳税人名称
     */
    public String fgrnsrmc;

    /**
     * 非个人联系电话
     */
    public String fgrlxdh;

    /**
     * 开户银行
     */
    public ArrayList khyhList = new ArrayList();
    public String khyhdm;
    public String khyhmc;

    /**
     * 银行账号
     */
    public String yhzh;

    /**
     * 联系人姓名
     */
    public String lxrxm;


    /**
     *
     * @return ArrayList
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList getBzList() {
        return bzList;
    }

    /**
     *
     * @return String
     */
    public String getBz() {
        return bz;
    }

    /**
     *
     * @return String
     */
    public String getCjjgyrmb() {
        return cjjgyrmb;
    }

    /**
     *
     * @return String
     */
    public String getCjjgywb() {
        return cjjgywb;
    }

    /**
     *
     * @return String
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList getFlList() {
        return flList;
    }

    /**
     *
     * @return String
     */
    public String getFwjzmj() {
        return fwjzmj;
    }

    /**
     *
     * @return String
     */
    public String getFwlxdm() {
        return fwlxdm;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList getFwlbList() {
        return fwlbList;
    }

    /**
     *
     * @return String
     */
    public String getHn() {
        return hn;
    }

    /**
     *
     * @return String
     */
    public String getHyqdsj() {
        return hyqdsj;
    }

    /**
     *
     * @return String
     */
    public String getPgjg() {
        return pgjg;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    /**
     *
     * @return ArrayList
     */
    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    /**
     *
     * @return String
     */
    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     *
     * @return String
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     *
     * @return String
     */
    public String getZhyrmb() {
        return zhyrmb;
    }


    public ArrayList getJkfsList() {
        return jkfsList;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getFcjslh() {
        return fcjslh;
    }

    public String getBzdm() {
        return bzdm;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getFldm() {
        return fldm;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getTdfwqszymc() {
        return tdfwqszymc;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getGjdqList() {
        return gjdqList;
    }

    public String getKhyhdm() {
        return khyhdm;
    }

    public ArrayList getKhyhList() {
        return khyhList;
    }

    public String getKhyhmc() {
        return khyhmc;
    }

    public String getLxrxm() {
        return lxrxm;
    }

    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getYhzh() {
        return yhzh;
    }

    public String getJhperson() {
        return jhperson;
    }

    public String getFgrlxdh() {
        return fgrlxdh;
    }

    public String getFgrnsrmc() {
        return fgrnsrmc;
    }

    public boolean isPerson() {
        return person;
    }

    public String getTdfwid() {
        return tdfwid;
    }

    public String getRjl() {
        return rjl;
    }

    public String getTdjc() {
        return tdjc;
    }

    /**
     *
     * @param bz String
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     *
     * @param bz String
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     *
     * @param bzList ArrayList
     */
    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    /**
     *
     * @param zhyrmb String
     */
    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    /**
     *
     * @param tdfwzldz String
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     *
     * @param tdfwqszymj String
     */
    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     *
     * @param tdfwqszylxList ArrayList
     */
    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    /**
     *
     * @param sfzjlxList ArrayList
     */
    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    /**
     *
     * @param pgjg String
     */
    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    /**
     *
     * @param hyqdsj String
     */
    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    /**
     *
     * @param hn String
     */
    public void setHn(String hn) {
        this.hn = hn;
    }

    /**
     *
     * @param fwlbList ArrayList
     */
    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    /**
     *
     * @param fwlb String
     */
    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    /**
     *
     * @param fwjzmj String
     */
    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     *
     * @param flList ArrayList
     */
    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    /**
     *
     * @param fdcxmmc String
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    /**
     *
     * @param cjjgywb String
     */
    public void setCjjgywb(String cjjgywb) {
        this.cjjgywb = cjjgywb;
    }

    /**
     *
     * @param cjjgyrmb String
     */
    public void setCjjgyrmb(String cjjgyrmb) {
        this.cjjgyrmb = cjjgyrmb;
    }


    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setTdfwqszymc(String tdfwqszymc) {
        this.tdfwqszymc = tdfwqszymc;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setGjdqList(ArrayList gjdqList) {
        this.gjdqList = gjdqList;
    }

    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    public void setKhyhList(ArrayList khyhList) {
        this.khyhList = khyhList;
    }

    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public void setJhperson(String jhperson) {
        this.jhperson = jhperson;
    }

    public void setFgrlxdh(String fgrlxdh) {
        this.fgrlxdh = fgrlxdh;
    }

    public void setFgrnsrmc(String fgrnsrmc) {
        this.fgrnsrmc = fgrnsrmc;
    }

    public void setPerson(boolean person) {
        this.person = person;
    }

    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public Object getData(List l) {
        FwjhxxBo fwjhxxBo = new FwjhxxBo();

        fwjhxxBo.sbbh = this.sbbh;
        fwjhxxBo.jkfsdm = this.jkfsdm;
        fwjhxxBo.jkfsmc = this.jkfsmc;
        fwjhxxBo.fcjslh = this.fcjslh;
        fwjhxxBo.jhperson = this.jhperson;

        Fgrxx fgrxx = new Fgrxx();
        if (jhperson.equals("0")) {
            //获得客户端的数据--
            this.setCqrJs(ActionUtil.displayMNsrDS(l));

            fwjhxxBo.setNsrList(l);
            this.setNsrList(l);
        }
        if (jhperson.equals("1")) {
            fgrxx.sbbh = this.sbbh;
            fgrxx.nsrmc = this.fgrnsrmc;
            fgrxx.lxdh = this.fgrlxdh;
            fgrxx.jsjdm = this.jsjdm;
            fgrxx.nsrlxmc = this.nsrlxmc;
            fgrxx.nsrlxdm = this.nsrlx;
            fgrxx.khyhdm = this.khyhdm;
            if (khyhmc != null && !khyhmc.equals("")) {
                String sz[] = DataConvert.splitYh(khyhmc);
                fgrxx.khyhmc = sz[0];
                fgrxx.yhzh = sz[1];
            } else {
                fgrxx.khyhmc = "";
                fgrxx.yhzh = "";
            }

            fgrxx.lxrxm = this.lxrxm;
        }
        //fwjhxxBo.grxx = grxx;
        fwjhxxBo.fgrxx = fgrxx;

        Tufwxx tufwxx = new Tufwxx();

        tufwxx.tdfwid = this.tdfwid;
        tufwxx.fdcxmmc = this.fdcxmmc;
        tufwxx.htqdsj = DataConvert.String2Timestamp(this.hyqdsj);
        tufwxx.fldm = this.fldm;
        tufwxx.flmc = this.flmc;
        Debug.out("tufwxx.fldm is in FwjhxxForm " + tufwxx.fldm);
        tufwxx.fwlxdm = "";
        tufwxx.fwlxmc = "";
        tufwxx.tdfwzldz = this.tdfwzldz;
        tufwxx.fwjzmj = DataConvert.String2BigDecimal(this.fwjzmj);
        tufwxx.cjjgrmb = DataConvert.String2BigDecimal(this.cjjgyrmb);
        tufwxx.pgjgrmb = DataConvert.String2BigDecimal(this.pgjg);
        tufwxx.cjjgwb = DataConvert.String2BigDecimal(this.cjjgywb);
        tufwxx.bzdm = this.bzdm;
        tufwxx.bzmc = this.bz;
        tufwxx.hldm = DataConvert.String2BigDecimal(this.hn);
        tufwxx.zhjgrmb = DataConvert.String2BigDecimal(this.zhyrmb);
        tufwxx.tdfwqszylx = this.tdfwqszylx;
        tufwxx.tdfwqszymc = this.tdfwqszymc;
        tufwxx.tdfwqszylx = this.tdfwqszylx;
        tufwxx.tdfwqszymj = DataConvert.String2BigDecimal(this.tdfwqszymj);
        tufwxx.rjl = this.rjl;
        tufwxx.tdjc = this.tdjc;
        fwjhxxBo.tufwxx = tufwxx;

        return fwjhxxBo;
    }

    /**
     * 清空页面
     */
    public void clear() {
        this.bz = "";
        this.bzdm = "USD";
        this.cjjgyrmb = "";
        this.cjjgywb = "";
        this.fcjslh = "";
        this.fdcxmmc = "";
        this.fgrlxdh = "";
        this.fgrnsrmc = "";
        this.fldm = "";
        this.flmc = "";
        this.fwjzmj = "";
        this.fwlxdm = "";
        this.fwlxmc = "";

        this.hn = "";
        this.hyqdsj = "";
        this.jhperson = "0";
        this.jkfsdm = "01";
        this.jkfsmc = "";
        this.jsjdm = "";
        this.khyhdm = "";
        this.khyhmc = "";
        this.lxrxm = "";
        this.nsrlx = "";
        this.nsrlxmc = "";
        this.nsrmc = "";
        this.operationType = "Show";
        this.pgjg = "";
        this.setCqrJs(Constants.CQRJS_INIT);
        this.setNsrList(new ArrayList());
        this.tdfwqszylx = "";
        this.tdfwqszymc = "";
        this.tdfwqszymj = "";
        this.tdfwzldz = "";
        this.rjl = Constants.TUFWXX_RJL_HIGH;
        this.tdjc = Constants.TUFWXX_TDJC_ONE;
    }


    public void setData(FwjhxxBo bo) {
        Tufwxx tufwxx = bo.getTufwxx();

        //tufwxx.getSbbh();
        this.sbbh = bo.getSbbh();
        this.bz = tufwxx.getBzmc();
        this.bzdm = tufwxx.getBzdm();
        this.cjjgyrmb = DataConvert.BigDecimal2String(tufwxx.getCjjgrmb(), 2, false);
        this.cjjgywb = DataConvert.BigDecimal2String(tufwxx.getCjjgwb(), 2, false);
        this.fcjslh = bo.getFcjslh();
        this.tdfwid = tufwxx.getTdfwid();
        this.fdcxmmc = tufwxx.getFdcxmmc();
        this.fldm = tufwxx.getFldm();
        this.flmc = tufwxx.getFlmc();
        this.fwjzmj = DataConvert.BigDecimal2String(tufwxx.getFwjzmj(), 2, false);
        this.fwlxdm = tufwxx.getFwlxdm();
        this.fwlxmc = tufwxx.getFwlxmc();
        this.hn = DataConvert.BigDecimal2String(tufwxx.getHldm(), 5, false);
        this.hyqdsj = DataConvert.TimeStamp2String(tufwxx.getHtqdsj());
        this.jhperson = bo.getJhperson();
        this.jkfsdm = bo.getJkfsdm();
        this.jkfsmc = bo.getJkfsmc();
        this.pgjg = DataConvert.BigDecimal2String(tufwxx.getPgjgrmb(), 2, false);
        this.tdfwqszylx = tufwxx.getTdfwqszylx();
        this.tdfwqszymc = tufwxx.getTdfwqszymc();
        this.tdfwqszymj = DataConvert.BigDecimal2String(tufwxx.getTdfwqszymj(),
                2, false);
        this.tdfwzldz = tufwxx.getTdfwzldz();
        this.zhyrmb = DataConvert.BigDecimal2String(tufwxx.getZhjgrmb(), 2, false);
        this.rjl = tufwxx.getRjl();
        this.tdjc = tufwxx.getTdjc();

        if (bo.getJhperson().equals("0")) {

            List l = bo.getNsrList();
            this.setNsrList(l);
            //获得客户端的数据
            this.setCqrJs(ActionUtil.displayMNsrDS(l));
        } else {
            Fgrxx fgrxx = bo.getFgrxx();
            this.fgrlxdh = fgrxx.getLxdh();
            this.fgrnsrmc = fgrxx.getNsrmc();
            this.jsjdm = fgrxx.getJsjdm();
            this.khyhdm = fgrxx.getKhyhdm();
            Debug.out("khyhdm+" + khyhdm);
            this.khyhmc = fgrxx.getKhyhmc();
            Debug.out("khyhmc+" + khyhmc);
            this.lxrxm = fgrxx.getLxrxm();
            this.nsrlx = fgrxx.getNsrlxdm();
            this.nsrlxmc = fgrxx.getNsrlxmc();
            this.sbbh = fgrxx.getSbbh();
            this.yhzh = fgrxx.getYhzh();
            Debug.out("yhzh+" + yhzh);

        }

    }

    /**
     * @return Returns the cqrJs.
     */
    public String getCqrJs() {
        return cqrJs;
    }

    /**
     * @param cqrJs The cqrJs to set.
     */
    public void setCqrJs(String cqrJs) {
        this.cqrJs = cqrJs;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    public ArrayList getTdjcList() {
        return tdjcList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    public void setTdjcList(ArrayList tdjcList) {
        this.tdjcList = tdjcList;
    }
}
