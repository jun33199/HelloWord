package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.BizLogic.vo.*;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;
import com.creationstar.bjtax.qsgl.util.*;

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
 * @author 赵博
 * @version 1.0
 */
public class JksForm extends QueryBaseForm {

    //缴款书的查询条件


   //合同编号  zzb add 20090831
   public String htbh="";


    /**
     * 缴款凭证号
     */
    public String jkpzh = "";

    /**
     * 申报起始日期
     */
    public String sbqsrq = "";

    /**
     * 申报截止日期
     */
    public String sbjzrq = "";

    //打印页面需要的属性
    public String szdm = ""; //税种名称
    public String lsgx = ""; //隶属关系
    public String sklx = ""; //税款类型
    public String tfrq = ""; //填发日期
    public String tfrqn = ""; //填发日期年
    public String tfrqy = ""; //填发日期月
    public String tfrqr = ""; //填发日期 日   
    public String zclx = ""; //注册类型
    public String zsjg = ""; //征收机关

    public String jkdwdm = ""; //缴款单位代码
    public String jkdwdh = ""; //缴款单位电话
    public String jkdwqc = ""; //缴款单位全称
    public String jkdwkhyh = ""; //缴款单位开户银行
    public String jkdwzh = ""; ///缴款单位账号

    public String yskmbm = ""; //预算科目编码
    public String yskmmc = ""; //预算科目名称
    public String yskmjc = ""; //预算科目级次
    public String gkjhh = ""; //国库交换号
    public String skgk = ""; //收款国库

    public String sksskssq = ""; //税款所属开始时期
    public String skssjssq = ""; //税款所属截止时期
    public String skxjrq = ""; //税款限缴日期

    public String jkjehj_nu = ""; //缴款金额合计数字
    public String jkjehj = ""; //缴款金额合计

    public String jkdw = ""; //缴款单位
    public String swjg = ""; //税务机关
    public String bz = ""; //备注

    public String jkmxxmmc = ""; //项目名称
    public String jkmxkssl = ""; //课税数量
    public String jkmxjsje = ""; //计税金额
    public String jkmxsjse = ""; //实缴税额
    public String jkmxfcbl = ""; //分成比例
    
    public String jhh = ""; //交换号
    public String fdcwz = ""; //房地产位置

    public ArrayList mxList; //打印页面显示的项目名称、计税金额等明细

    /**
     * 缴款书的生成方式
     */
    public String scfs;

    /**
     * 查询结果
     */
    public JksBo jksBo;

    //以下为补录非汇总生成缴款书用到的属性
    public Sbjkzb sbjkzb;

    public Sbjkmx sbjkmx;

    public void setJksData() {
        this.sbjkmx.setJsje(DataConvert.String2BigDecimal(this.getJkmxjsje()));
        this.sbjkmx.setSjse(DataConvert.String2BigDecimal(this.getJkmxsjse()));
        this.sbjkmx.setRkje(this.sbjkmx.getSjse());
        this.sbjkmx.setSkssksrq(DataConvert.String2Timestamp(this.getSksskssq()));
        this.sbjkmx.setSkssjsrq(DataConvert.String2Timestamp(this.getSkssjssq()));

        this.sbjkzb.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSkssksrq(this.sbjkmx.getSkssksrq());
        this.sbjkzb.setSkssjsrq(this.sbjkmx.getSkssjsrq());
        this.sbjkzb.setSjje(this.sbjkmx.getSjse());
        this.sbjkzb.setRkje(this.sbjkmx.getSjse());
    }

    public Object getData() {
        JksBo bo = new JksBo();
        bo.setJkpzh(this.jkpzh);
        bo.setSbqsrq(this.sbqsrq);
        bo.setSbjzrq(this.sbjzrq);
        bo.setHzfs(this.scfs);
        return bo;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getBz() {
        return bz;
    }

    public String getGkjhh() {
        return gkjhh;
    }

    public String getJkdw() {
        return jkdw;
    }

    public String getJkdwdh() {
        return jkdwdh;
    }

    public String getJkdwdm() {
        return jkdwdm;
    }

    public String getJkdwkhyh() {
        return jkdwkhyh;
    }

    public String getJkdwqc() {
        return jkdwqc;
    }

    public String getJkdwzh() {
        return jkdwzh;
    }

    public String getJkjehj() {
        return jkjehj;
    }

    public String getJkjehj_nu() {
        return jkjehj_nu;
    }

    public String getJkmxjsje() {
        return jkmxjsje;
    }

    public String getJkmxkssl() {
        return jkmxkssl;
    }

    public String getJkmxsjse() {
        return jkmxsjse;
    }

    public String getJkmxxmmc() {
        return jkmxxmmc;
    }

    public String getJkmxfcbl() {
        return jkmxfcbl;
    }
    
    public String getLsgx() {
        return lsgx;
    }

    public String getSkgk() {
        return skgk;
    }

    public String getSklx() {
        return sklx;
    }

    public String getSkxjrq() {
        return skxjrq;
    }

    public String getSwjg() {
        return swjg;
    }

    public String getSzdm() {
        return szdm;
    }

    public String getTfrq() {
        return tfrq;
    }

    public String getTfrqn() {
        return tfrqn;
    }
    
    public String getTfrqy() {
        return tfrqy;
    }
    
    public String getTfrqr() {
        return tfrqr;
    }
    
    public String getYskmbm() {
        return yskmbm;
    }

    public String getYskmjc() {
        return yskmjc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getZclx() {
        return zclx;
    }

    public String getZsjg() {
        return zsjg;
    }

    public String getSkssjssq() {
        return skssjssq;
    }

    public String getSksskssq() {
        return sksskssq;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getSbjzrq() {
        return sbjzrq;
    }

    public String getSbqsrq() {
        return sbqsrq;
    }

    public JksBo getJksBo() {
        return jksBo;
    }

    public String getJhh() {
        return jhh;
    }

    public String getScfs() {
        return scfs;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String getHtbh()
    {
        return htbh;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setGkjhh(String gkjhh) {
        this.gkjhh = gkjhh;
    }

    public void setJkdw(String jkdw) {
        this.jkdw = jkdw;
    }

    public void setJkdwdh(String jkdwdh) {
        this.jkdwdh = jkdwdh;
    }

    public void setJkdwdm(String jkdwdm) {
        this.jkdwdm = jkdwdm;
    }

    public void setJkdwkhyh(String jkdwkhyh) {
        this.jkdwkhyh = jkdwkhyh;
    }

    public void setJkdwqc(String jkdwqc) {
        this.jkdwqc = jkdwqc;
    }

    public void setJkdwzh(String jkdwzh) {
        this.jkdwzh = jkdwzh;
    }

    public void setJkjehj(String jkjehj) {
        this.jkjehj = jkjehj;
    }

    public void setJkjehj_nu(String jkjehj_nu) {
        this.jkjehj_nu = jkjehj_nu;
    }

    public void setJkmxjsje(String jkmxjsje) {
        this.jkmxjsje = jkmxjsje;
    }

    public void setJkmxkssl(String jkmxkssl) {
        this.jkmxkssl = jkmxkssl;
    }

    public void setJkmxsjse(String jkmxsjse) {
        this.jkmxsjse = jkmxsjse;
    }

    public void setJkmxxmmc(String jkmxxmmc) {
        this.jkmxxmmc = jkmxxmmc;
    }

    public void setJkmxfcbl(String jkmxfcbl) {
        this.jkmxfcbl = jkmxfcbl;
    }
    
    public void setLsgx(String lsgx) {
        this.lsgx = lsgx;
    }

    public void setSkgk(String skgk) {
        this.skgk = skgk;
    }

    public void setSklx(String sklx) {
        this.sklx = sklx;
    }

    public void setSkxjrq(String skxjrq) {
        this.skxjrq = skxjrq;
    }

    public void setSwjg(String swjg) {
        this.swjg = swjg;
    }

    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    public void setTfrqn(String tfrqn) {
        this.tfrqn = tfrqn;
    }
    
    public void setTfrqy(String tfrqy) {
        this.tfrqy = tfrqy;
    }
    
    public void setTfrqr(String tfrqr) {
        this.tfrqr = tfrqr;
    }
    
    public void setYskmbm(String yskmbm) {
        this.yskmbm = yskmbm;
    }

    public void setYskmjc(String yskmjc) {
        this.yskmjc = yskmjc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setZclx(String zclx) {
        this.zclx = zclx;
    }

    public void setZsjg(String zsjg) {
        this.zsjg = zsjg;
    }

    public void setSkssjssq(String skssjssq) {
        this.skssjssq = skssjssq;
    }

    public void setSksskssq(String sksskssq) {
        this.sksskssq = sksskssq;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSbjzrq(String sbjzrq) {
        this.sbjzrq = sbjzrq;
    }

    public void setSbqsrq(String sbqsrq) {
        this.sbqsrq = sbqsrq;
    }

    public void setJksBo(JksBo jksBo) {
        this.jksBo = jksBo;
    }

    public void setJhh(String jhh) {
        this.jhh = jhh;
    }

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

    private void jbInit() throws Exception {
    }
}
