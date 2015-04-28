package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * 减免契税个人对应的bo
 */


public class JmsbBo implements Serializable {

    /**
     * 个人
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();

    /**
     * 创建日期
     */
    private String cjrq;


    /**
     * 申报日期
     */
    public Timestamp sbrq;

    /**
     * 计算机代码
     */
    public String jsjdm;
    /**
     * 用户标识
     */
    public String yhbs;
    /**
     * 申报表号
     */
    public String sbbh;

    /**
     * 房屋土地部门受理号
     */
    public String fwtdbmslh;
    /**
     * 状态标示
     */
    public String ztbs;

    /**
     * 国籍
     */
    public ArrayList gjList = new ArrayList();

    /**
     * 主产权人信息
     */
    private Grxx voZcqrxx = null;

    /**
     * 纳税人类型
     */
    public String nsrlx;
    /**
     * 纳税人类型
     */
    public String nsrlxmc;


    /**
     * 纳税人名称
     */
    public String nsrmc;
    /**
     * 联系电话
     */
    public String lxdh;
    /**
     * 身份证件类型
     */
    public ArrayList sfzjlxList = new ArrayList();
    //   public String sfzjlx;
    //   public String sfzjlxmc;
    /**
     * 身份证件号码
     */
    //  public String sfzjhm;
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
    public String fl;
    public String flmc;
    /**
     * 土地、房屋权属转移类型
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    public String tdfwqszylx;
    public String tdfwqszylxmc;
    /**
     * 房屋类别
     */
    public ArrayList fwlbList = new ArrayList();
    public String fwlb;
    public String fwlbmc;
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
     * 成交价格  元(外币)
     */
    public String cjjgywb;
    /**
     * 币种
     */
    public ArrayList bzList = new ArrayList();
    public String bz;
    public String bzmc;
    /**
     * 汇率
     */
    public String hn;
    /**
     * 折合元(人民币)
     */
    public String zhyrmb;
    /**
     * 纳税人（代理人）签章
     */
    public String nsrqz;
    /**
     * 备注
     */
    public String beizhu;

//添加非个人信息
    /**
     * 纳税计算机代码
     */
    public String nsjsjdm;

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
     * 容积率
     */
    public String rjl;

    /**
     * 土地级次
     */
    public String tdjc;


    /**
     * 契税减免类别
     */
    public ArrayList qsjmlbList = new ArrayList();
    public String qsjmlb;
    public String qsjmlbmc;

    public String[] qsjmlbSelect;

    /**
     * 其它减免理由备注
     */
    public String qtjmlybeizhu;
    
    /**
     * 契税减免性质
     */
    public String qsjmxzdm;

    /**
     * 页面操作类型
     * 0－添加；2－更改；OperationType
     */
    public int ymczlxdm;


    /**
     * 获得
     * @return ArrayList
     */
    public ArrayList getBzList() {
        return bzList;
    }

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
     * 获得分类
     * @return ArrayList
     */
    public String getFl() {
        return fl;
    }

    /**
     * 获得房屋建筑面积
     * @return String
     */
    public String getFwjzmj() {
        return fwjzmj;
    }

    /**
     * 获得房屋类别
     * @return ArrayList
     */
    public String getFwlb() {
        return fwlb;
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
     * 获得联系电话
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得纳税人（代理人）签章
     * @return String
     */
    public String getNsrqz() {
        return nsrqz;
    }

    /**
     * 获得评估价格
     * @return String
     */
    public String getPgjg() {
        return pgjg;
    }

    /**
     * 获得土地、房屋权属转移类型
     * @return ArrayList
     */
    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    /**
     * 获得土地、房屋权属转移面积
     * @return String
     */
    public String getTdfwqszymj() {
        return tdfwqszymj;
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

    public String getBz() {
        return bz;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public ArrayList getFwlbList() {
        return fwlbList;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public String getFwtdbmslh() {
        return fwtdbmslh;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getKhyhdm() {
        return khyhdm;
    }

    public ArrayList getKhyhList() {
        return khyhList;
    }

    public String getLxrxm() {
        return lxrxm;
    }

    public String getNsjsjdm() {
        return nsjsjdm;
    }


    public String getYhzh() {
        return yhzh;
    }


    public String getKhyhmc() {
        return khyhmc;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getYhbs() {
        return yhbs;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFwlbmc() {
        return fwlbmc;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }


    public ArrayList getGjList() {
        return gjList;
    }

    public String getCjrq() {
        return cjrq;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getRjl() {
        return rjl;
    }

    /**
     * 设置备注
     * @param bz ArrayList
     */
    public void setBz(String bz) {
        this.bz = bz;
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
     * 设置折合元(人民币)
     * @param zhyrmb String
     */
    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    /**
     * 设置土地、房屋座落地址
     * @param tdfwzldz String
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * 设置土地、房屋权属转移面积
     * @param tdfwqszymj String
     */
    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * 设置土地、房屋权属转移类型
     * @param tdfwqszylx ArrayList
     */
    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    /**
     * 设置评估价格
     * @param pgjg String
     */
    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    /**
     * 设置纳税人（代理人）签章
     * @param nsrqz String
     */
    public void setNsrqz(String nsrqz) {
        this.nsrqz = nsrqz;
    }

    /**
     * 设置联系电话
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 设置合约签订时间
     * @param hyqdsj String
     */
    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    /**
     * 设置合约签订时间
     * @param hn String
     */
    public void setHn(String hn) {
        this.hn = hn;
    }

    /**
     * 设置房屋类别
     * @param fwlb ArrayList
     */
    public void setFwlb(String fwlb) {
        this.fwlb = fwlb;
    }

    /**
     * 设置房屋建筑面积
     * @param fwjzmj String
     */
    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * 设置分类
     * @param fl ArrayList
     */
    public void setFl(String fl) {
        this.fl = fl;
    }

    /**
     * 设置房地产项目名称
     * @param fdcxmmc String
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setFwtdbmslh(String fwtdbmslh) {
        this.fwtdbmslh = fwtdbmslh;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    public void setKhyhList(ArrayList khyhList) {
        this.khyhList = khyhList;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    public void setNsjsjdm(String nsjsjdm) {
        this.nsjsjdm = nsjsjdm;
    }


    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }


    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFwlbmc(String fwlbmc) {
        this.fwlbmc = fwlbmc;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setGjList(ArrayList gjList) {
        this.gjList = gjList;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    private void jbInit() throws Exception {
    }


    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    /**
     * @return Returns the voZcqrxx.
     */
    public Grxx getVoZcqrxx() {
        if (voZcqrxx != null) {
            return voZcqrxx;
        }
        if (nsrList == null || nsrList.size() == 0) {
            return null;
        }
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx g = (Grxx) nsrList.get(i);
            if (g.getCqrlx().equals(Constants.CQRLX_ZCQR)) {
                this.setVoZcqrxx(g);
                return g;
            }
        }
        return null;
    }

    /**
     * @param voZcqrxx The voZcqrxx to set.
     */
    public void setVoZcqrxx(Grxx voZcqrxx) {
        this.voZcqrxx = voZcqrxx;
    }

    /**
     * @return sbrq
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * @param sbrq 要设置的 sbrq
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * @return qsjmlb
     */
    public String getQsjmlb() {
        return qsjmlb;
    }

    /**
     * @param qsjmlb 要设置的 qsjmlb
     */
    public void setQsjmlb(String qsjmlb) {
        this.qsjmlb = qsjmlb;
    }

    /**
     * @return qsjmlbList
     */
    public ArrayList getQsjmlbList() {
        return qsjmlbList;
    }

    /**
     * @param qsjmlbList 要设置的 qsjmlbList
     */
    public void setQsjmlbList(ArrayList qsjmlbList) {
        this.qsjmlbList = qsjmlbList;
    }

    /**
     * @return qsjmlbmc
     */
    public String getQsjmlbmc() {
        return qsjmlbmc;
    }

    /**
     * @param qsjmlbmc 要设置的 qsjmlbmc
     */
    public void setQsjmlbmc(String qsjmlbmc) {
        this.qsjmlbmc = qsjmlbmc;
    }

    public int getYmczlxdm() {
        return ymczlxdm;
    }

    public void setYmczlxdm(int ymczlxdm) {
        this.ymczlxdm = ymczlxdm;
    }

    /**
     * @return qsjmlbSelect
     */
    public String[] getQsjmlbSelect() {
        return qsjmlbSelect;
    }

    /**
     * @param qsjmlbSelect 要设置的 qsjmlbSelect
     */
    public void setQsjmlbSelect(String[] qsjmlbSelect) {
        this.qsjmlbSelect = qsjmlbSelect;
    }

    /**
     * @return qtjmlybeizhu
     */
    public String getQtjmlybeizhu() {
        return qtjmlybeizhu;
    }

    /**
     * @param qtjmlybeizhu 要设置的 qtjmlybeizhu
     */
    public void setQtjmlybeizhu(String qtjmlybeizhu) {
        this.qtjmlybeizhu = qtjmlybeizhu;
    }

	public String getQsjmxzdm() {
		return qsjmxzdm;
	}

	public void setQsjmxzdm(String qsjmxzdm) {
		this.qsjmxzdm = qsjmxzdm;
	}


}
