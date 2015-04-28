package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;

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
public class QueryWszForm extends QueryBaseForm {

	//合同编号  zzb add 20090831
    public String htbh="";

    /**
     * 判断页面来源
     */
    public String yuan;

    /**
     * 票证种类代码
     */
    public String pzzldm;

    /**
     * 字别
     */
    public String zb;
    /**
     * 完税证起始号码
     */
    public String wszqshm;
    /**
     * 完税证截止号码
     */
    public String wszjzhm;
    /**
     * 填发起始日期
     */
    public String tfqsrq;
    /**
     * 填发截止日期
     */
    public String tfjzrq;

    //打印页面所用的内容
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
     * 经办人
     */
    public String jbr;

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
     * 明细信息对应的结果集
     */
    public QueryWszBo mxbo = new QueryWszBo();

    /**
     * 生成完税证返回的结果
     */
    public ArrayList wszList;

    /**
     * 生成的完税证的数量
     */
    public int wszTotals;

    /**
     * 打印到第几张完税证
     */
    public int printPages;

    /**
     * 明细中税目arraylist
     */
    public ArrayList smlist;

    /**
     * 明细中房地产权属转移面积arraylist
     */
    public ArrayList mjlist;

    /**
     * 明细中计 税 金 额arraylist
     */
    public ArrayList jsjelist;


    /**
     * 明细中税率％arraylist
     */
    public ArrayList sllist;

    /**
     * 明细中实 纳 金 额arraylist
     */
    public ArrayList snjelist;
    
    /**
     * 明细中税款所属日期arraylist
     */
    public ArrayList skssrqlist;
    
    private Object data;

    /**
     * 清空用函数
     */
    /*   public void clear()
       {
           this.setPzzldm("");
           this.setNsrmc("");
           this.setWszqshm("");
           this.setWszjzhm("");
           this.setTfqsrq("");
           this.setTfjzrq("");
       }
     */
    /**
     * 获得填发截止日期
     * @return String
     */
    public String getTfjzrq() {
        return tfjzrq;
    }

    /**
     * 获得填发起始日期
     * @return String
     */
    public String getTfqsrq() {
        return tfqsrq;
    }

    /**
     *  获得完税证截止号码
     * @return String
     */
    public String getWszjzhm() {
        return wszjzhm;
    }

    /**
     * 获得完税证起始号码
     * @return String
     */
    public String getWszqshm() {
        return wszqshm;
    }

    /**
     * 获得字别
     * @return String
     */
    public String getZb() {
        return zb;
    }

    public QueryWszBo getMxbo() {
        return mxbo;
    }

    public ArrayList getJsjelist() {
        return jsjelist;
    }

    public ArrayList getMjlist() {
        return mjlist;
    }

    public ArrayList getSllist() {
        return sllist;
    }

    public ArrayList getSmlist() {
        return smlist;
    }

    public ArrayList getSnjelist() {
        return snjelist;
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
     * 设置填发截止日期
     * @param tfjzrq String
     */
    public void setTfjzrq(String tfjzrq) {
        this.tfjzrq = tfjzrq;
    }

    /**
     * 设置填发起始日期
     * @param tfqsrq String
     */
    public void setTfqsrq(String tfqsrq) {
        this.tfqsrq = tfqsrq;
    }

    /**
     * 设置完税证截止号码
     * @param wszjzhm String
     */
    public void setWszjzhm(String wszjzhm) {
        this.wszjzhm = wszjzhm;
    }

    /**
     * 设置完税证起始号码
     * @param wszqshm String
     */
    public void setWszqshm(String wszqshm) {
        this.wszqshm = wszqshm;
    }

    /**
     * 设置字别
     * @param zb String
     */
    public void setZb(String zb) {
        this.zb = zb;
    }

    public void setMxbo(QueryWszBo mxbo) {
        this.mxbo = mxbo;
    }

    public void setJsjelist(ArrayList jsjelist) {
        this.jsjelist = jsjelist;
    }

    public void setMjlist(ArrayList mjlist) {
        this.mjlist = mjlist;
    }

    public void setSllist(ArrayList sllist) {
        this.sllist = sllist;
    }

    public void setSmlist(ArrayList smlist) {
        this.smlist = smlist;
    }

    public void setSnjelist(ArrayList snjelist) {
        this.snjelist = snjelist;
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

    public void setYuan(String yuan) {
        this.yuan = yuan;
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

    public void setWszList(ArrayList wszList) {
        this.wszList = wszList;
    }

    public void setWszTotals(int wszTotals) {
        this.wszTotals = wszTotals;
    }

    public void setPrintPages(int printPages) {
        this.printPages = printPages;
    }

    //将QueryWszForm中的数据放到wszBo中，返回wszBo
    public Object getData() {
        QueryWszBo queryWszBo = new QueryWszBo();
        queryWszBo.setNdzb(this.zb);
        queryWszBo.setPzzldm(this.pzzldm);
        queryWszBo.setStartWszh(this.wszqshm);
        queryWszBo.setEndWszh(this.wszjzhm);
        queryWszBo.setStartCjrq(this.tfqsrq);
        queryWszBo.setEndCjrq(this.tfjzrq);
        queryWszBo.setNsrmc(this.nsrmc);

        return queryWszBo;
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

    public String getYuan() {
        return yuan;
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

    public ArrayList getWszList() {
        return wszList;
    }

    public int getWszTotals() {
        return wszTotals;
    }

    public int getPrintPages() {
        return printPages;
    }

    /**
     * @return Returns the nsrmc.
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * @param nsrmc The nsrmc to set.
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * @return Returns the jbr.
     */
    public String getJbr() {
        return jbr;
    }

    public String getHtbh()
    {
        return htbh;
    }

    /**
     * @param jbr The jbr to set.
     */
    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

	public ArrayList getSkssrqlist() {
		return skssrqlist;
	}

	public void setSkssrqlist(ArrayList skssrqlist) {
		this.skssrqlist = skssrqlist;
	}
}
