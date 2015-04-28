package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.HzJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;

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
public class hzWsz2JksForm extends QueryBaseForm {

    //汇总方式代码
    public String hzfsdm;

    //汇总凡方式名称
    public String hzfsmc;

    //缴税方式代码
    public String jsfsdm;

    //缴税方式名称
    public String jsfsmc;

    //截至日期
    public String jzrq;

    //起始日期
    public String qsrq;

    //缴款凭证号
    public String jkpzh;

    //完税证号
    public String wszh;

    //生成缴款书的方式，用于撤销缴款书时定位用wszProcessor还是jksProcessor
    public String scfs;

    //缴税方式列表
    public ArrayList jsfsList;


    //以下用于汇总生成的缴款书的打印页面显示

    public String szdm = ""; //税种名称
    public String lsgx = ""; //隶属关系
    public String sklx = ""; //税款类型
    public String tfrq = ""; //填发日期
    public String tfrqn = ""; //填发日期年
    public String tfrqy = ""; //填发日期月
    public String tfrqr = ""; //填发日期日
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

    public String jhh = ""; //国库交换号

    public ArrayList mxList; //打印页面显示的项目名称、计税金额等明细

    //以下为补录用到的属性
    public String[] mxxmdm; //税种税目代码
    public String[] mxxmmc; //税种税目名称
    public String[] mxkssl; //课税数量
    public String[] mxjsje; //计税金额
    public String[] mxsjse; //实缴税额

    public ArrayList szsmList; //税种税目list，就是明细列表中的项目名称下拉框

    public String fenpiao; //是否有分票的情况，0为没有，1为有

    public String[] jkpzhao; //分票的缴款凭证号

    private Sbjkzb sbjkzb;

    public String drpch; //导入批次号

    public String dzjktfrq = ""; //国库交换号
    public String sbbh = ""; //国库交换号


    public String getDrpch() {
        return drpch;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public Object getData() {
        WszBo bo = new WszBo();
        bo.setHzfs(this.hzfsdm);
        bo.setHzfsmc(this.hzfsmc);
        bo.setHzjzrq(this.jzrq);
        bo.setHzqsrq(this.qsrq);
        bo.setJsfs(this.jsfsdm);
        bo.setJsfsmc(this.jsfsmc);
        bo.setWszh(this.wszh);
        bo.setJsjdm(this.jsjdm);
        bo.setJkpzh(this.jkpzh);
        bo.setDrpch(this.drpch);
        return bo;
    }

    public Object getWszBo() {
        WszBo bo = new WszBo();
        bo.setDrpch(this.drpch);
        return bo;
    }

    /**
     * 撤销汇总时候需要
     * @return JksBo
     */
    public JksBo getJksBo() {
        JksBo bo = new JksBo();
        bo.setJkpzh(this.getJkpzh());
        bo.setJsjdm(this.getJsjdm());
        bo.setHzfs(this.getScfs());
        return bo;
    }

    /**
     * 补录汇总生成缴款书，给缴款书基本信息赋值
     * @param sbjkzb Sbjkzb
     */
    public void setJksInfo(Sbjkzb sbjkzb) {
        this.setSkgk(sbjkzb.getGkzzjgmc());
        this.setSklx(sbjkzb.getSklxmc());

        this.setSwjg(sbjkzb.getSwjgzzjgmc());
        this.setSzdm(sbjkzb.getSzmc());
        this.setYskmbm(sbjkzb.getYskmdm());
        this.setYskmjc(sbjkzb.getYsjcmc());
        this.setYskmmc(sbjkzb.getYskmmc());
        this.setZclx(sbjkzb.getDjzclxmc());
        this.setJkdw(sbjkzb.getNsrmc()); //纳税人计算机代码
        this.setJkdwdh(sbjkzb.getJydzlxdm());
        this.setJkdwkhyh(sbjkzb.getYhmc());
        this.setJkdwqc(sbjkzb.getNsrmc());
        this.setJkdwdm(sbjkzb.getJsjdm());
        this.setJkdwzh(sbjkzb.getZh());

        this.setLsgx(sbjkzb.getLsgxmc());
        this.setZsjg(sbjkzb.getZsswjgzzjgmc());
        this.setJhh(sbjkzb.getGkjhh());
    }

    /**
     * 补录汇总生成的缴款书时将form中的数据组织给要往后台传递的bo
     * @return HzJksBo
     */
    public HzJksBo getHzJksBo() {
        HzJksBo bo = new HzJksBo();

        //将form中保存的sbjkzb vo中的数据补全（就差一个lrrq，这个在processor里面赋值为当前时间）
        this.sbjkzb.setJkpzh(this.getJkpzh());
        this.sbjkzb.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSbrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSkssksrq(DataConvert.String2Timestamp(this.getSksskssq()));
        this.sbjkzb.setSkssjsrq(DataConvert.String2Timestamp(this.getSkssjssq()));
        this.sbjkzb.setSjje(DataConvert.String2BigDecimal(this.getJkjehj()));
        this.sbjkzb.setRkje(DataConvert.String2BigDecimal(this.getJkjehj()));
        this.sbjkzb.setBz(this.getBz());

        //将sbjkmx中的税种名称、代码，课税数量、计税金额、实缴税额赋值给bo中的相应变量
        bo.setMxjsje(this.getMxjsje());
        bo.setMxkssl(this.getMxkssl());
        bo.setMxsjse(this.getMxsjse());
        bo.setMxxmmc(this.getMxxmmc());
        bo.setMxxmdm(this.getMxxmdm());

        //将汇总方式名称、代码以及是否有分票的情况赋值给bo中的相应变量
        bo.setHzfsdm(this.getHzfsdm());
        bo.setHzfsmc(this.getHzfsmc());
        bo.setFp(this.getFenpiao());

        //将充好值的sbjkzb vo赋给bo
        bo.setSbjkzb(this.getSbjkzb());

        return bo;
    }

    public String getHzfsdm() {
        return hzfsdm;
    }

    public void setHzfsdm(String hzfsdm) {
        this.hzfsdm = hzfsdm;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    public void setJsfsList(ArrayList jsfsList) {
        this.jsfsList = jsfsList;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
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

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSkgk(String skgk) {
        this.skgk = skgk;
    }

    public void setSklx(String sklx) {
        this.sklx = sklx;
    }

    public void setSkssjssq(String skssjssq) {
        this.skssjssq = skssjssq;
    }

    public void setSksskssq(String sksskssq) {
        this.sksskssq = sksskssq;
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

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setJhh(String jhh) {
        this.jhh = jhh;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setMxjsje(String[] mxjsje) {
        this.mxjsje = mxjsje;
    }

    public void setMxkssl(String[] mxkssl) {
        this.mxkssl = mxkssl;
    }

    public void setMxsjse(String[] mxsjse) {
        this.mxsjse = mxsjse;
    }

    public void setMxxmmc(String[] mxxmmc) {
        this.mxxmmc = mxxmmc;
    }

    public void setFenpiao(String fenpiao) {
        this.fenpiao = fenpiao;
    }

    public void setMxxmdm(String[] mxxmdm) {
        this.mxxmdm = mxxmdm;
    }

    public void setSzsmList(ArrayList szsmList) {
        this.szsmList = szsmList;
    }

    public void setJkpzhao(String[] jkpzhao) {
        this.jkpzhao = jkpzhao;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public String getJzrq() {
        return jzrq;
    }

    public String getQsrq() {
        return qsrq;
    }

    public ArrayList getJsfsList() {
        return jsfsList;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    public String getHzfsmc() {
        return hzfsmc;
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

    public ArrayList getMxList() {
        return mxList;
    }

    public String getSkgk() {
        return skgk;
    }

    public String getSklx() {
        return sklx;
    }

    public String getSkssjssq() {
        return skssjssq;
    }

    public String getSksskssq() {
        return sksskssq;
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

    public String getScfs() {
        return scfs;
    }

    public String getJhh() {
        return jhh;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String[] getMxjsje() {
        return mxjsje;
    }

    public String[] getMxkssl() {
        return mxkssl;
    }

    public String[] getMxsjse() {
        return mxsjse;
    }

    public String[] getMxxmmc() {
        return mxxmmc;
    }

    public String getFenpiao() {
        return fenpiao;
    }

    public String[] getMxxmdm() {
        return mxxmdm;
    }

    public ArrayList getSzsmList() {
        return szsmList;
    }

    public String[] getJkpzhao() {
        return jkpzhao;
    }

    private void jbInit() throws Exception {
    }

    /**
     * @return Returns the dzjktfrq.
     */
    public String getDzjktfrq() {
        return dzjktfrq;
    }

    /**
     * @param dzjktfrq The dzjktfrq to set.
     */
    public void setDzjktfrq(String dzjktfrq) {
        this.dzjktfrq = dzjktfrq;
    }

    /**
     * @return Returns the sbbh.
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * @param sbbh The sbbh to set.
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }
}
