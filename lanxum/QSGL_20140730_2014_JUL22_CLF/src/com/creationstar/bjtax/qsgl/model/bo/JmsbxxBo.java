package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 *
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description: 单独的属性作为可能的查询条件 查询结果分别保存到各自的vo
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JmsbxxBo implements Serializable {

    /**
     * 保存房屋交换对方的申报数据
     */
    private FwjhxxBo dfSbxxBo;

    /**
     * 纳税申报表号
     */
    private String sbbh;

    /**
     * 打印使用的纳税申报表号
     */
    private String printSbbh;

    /**
     * 是否增加了房屋基本信息
     */
    private boolean addedTufwxx = false;

    /**
     * 是否增加了房屋交换信息
     */
    private boolean addedFwjhxx = false;

    /**
     * 申报主表数据
     */
    private Sbzb voSbzb = null;

    /**
     * 审核结果信息
     */
    private Spjgxx voSpjgxx = null;

    /**
     * 房屋基本信息
     */
    private Tufwxx voTufwxx = null;

    /**
     * 主产权人信息
     */
    private Grxx voZcqrxx = null;

    /**
     * 非个人信息
     */
    private Fgrxx voFgrxx = null;

    /**
     * 房屋交换信息
     */
    private FwjhxxBo voFwjh = null;

    /**
     * 两个个ArrayList作为房屋土地基本信息、拆迁、已购公有住房 每个ArrayList中，就是相对应的Vo（ArrayList中的对象）
     */
    private ArrayList cqList = new ArrayList();

    private ArrayList gyzfList = new ArrayList();

    private List nsrList = new ArrayList();

    public JghdsjBo hdbo;


    /**
     * 契税减免申报表 ArrayList
     *
     */
    private ArrayList jmsbbList = new ArrayList();

    /**
     * 审批机构－机关 list 本分局机关、市局机关
     */
    private ArrayList spjgList = new ArrayList();
    private String spjg = "9000";

    /**
     * 审批状态 list 5－审核同意；6－审核不同意
     */
    private ArrayList spztList = new ArrayList();
    private String spzt = Constants.ZB_ZTBS_JS_BTY;

    /**
     * 审批日期
     */
    private Timestamp sprq;

    /**
     * @return Returns the hdbo.
     */
    public JghdsjBo getHdbo() {
        return hdbo;
    }

    /**
     * @param hdbo
     *            The hdbo to set.
     */
    public void setHdbo(JghdsjBo hdbo) {
        this.hdbo = hdbo;
    }

    /**
     * 判断该申报是否是个人
     *
     * @return boolean
     */
    public boolean isPerson() {
        if (voSbzb == null) {
            return false;
        }
        if (voSbzb.yhbs.equals(Constants.YHBZ_GR)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断该申报是否是不征
     *
     * @return boolean
     */
    public boolean isBZ() {
        if (voSbzb == null) {
            return false;
        }
        if (voSbzb.bljmsbs.equals(Constants.ZB_BLJMSBS_BZ)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return boolean
     */
    public String getState() {
        return this.voSbzb.ztbs;
    }

    public boolean isAddedFwjhxx() {
        return addedFwjhxx;
    }

    public boolean isAddedTufwxx() {
        return addedTufwxx;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    public ArrayList getGyzfList() {
        return gyzfList;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Fgrxx getVoFgrxx() {
        return voFgrxx;
    }

    public FwjhxxBo getVoFwjh() {
        return voFwjh;
    }

    public Sbzb getVoSbzb() {
        return voSbzb;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    public Spjgxx getVoSpjgxx() {
        return voSpjgxx;
    }

    public FwjhxxBo getDfSbxxBo() {
        return dfSbxxBo;
    }

    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public void setVoSbzb(Sbzb voSbzb) {
        this.voSbzb = voSbzb;
    }

    public void setVoFwjh(FwjhxxBo voFwjh) {
        this.voFwjh = voFwjh;
    }

    public void setVoFgrxx(Fgrxx voFgrxx) {
        this.voFgrxx = voFgrxx;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setGyzfList(ArrayList gyzfList) {
        this.gyzfList = gyzfList;
    }

    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public void setAddedTufwxx(boolean addedTufwxx) {
        this.addedTufwxx = addedTufwxx;
    }

    public void setAddedFwjhxx(boolean addedFwjhxx) {
        this.addedFwjhxx = addedFwjhxx;
    }

    public void setVoSpjgxx(Spjgxx voSpjgxx) {
        this.voSpjgxx = voSpjgxx;
    }

    public void setDfSbxxBo(FwjhxxBo dfSbxxBo) {
        this.dfSbxxBo = dfSbxxBo;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList
     *            The nsrList to set.
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
     * @param voZcqrxx
     *            The voZcqrxx to set.
     */
    public void setVoZcqrxx(Grxx voZcqrxx) {
        this.voZcqrxx = voZcqrxx;
    }

    /**
     * @return Returns the printSbbh.
     */
    public String getPrintSbbh() {
        return printSbbh;
    }

    /**
     * @param printSbbh
     *            The printSbbh to set.
     */
    public void setPrintSbbh(String printSbbh) {
        this.printSbbh = printSbbh;
    }


    /**
     * @return jmsbbList
     */
    public ArrayList getJmsbbList() {
        return jmsbbList;
    }

    /**
     * @param jmsbbList 要设置的 jmsbbList
     */
    public void setJmsbbList(ArrayList jmsbbList) {
        this.jmsbbList = jmsbbList;
    }


    public String getSpjg() {
        return spjg;
    }

    public void setSpjg(String spjg) {
        this.spjg = spjg;
    }


    public String getSpzt() {
        return spzt;
    }

    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }

    public Timestamp getSprq() {
        return sprq;
    }

    public void setSprq(Timestamp sprq) {
        this.sprq = sprq;
    }

    public ArrayList getSpjgList() {
        return spjgList;
    }

    public void setSpjgList(ArrayList spjgList) {
        this.spjgList = spjgList;
    }

    public ArrayList getSpztList() {
        return spztList;
    }

    public void setSpztList(ArrayList spztList) {
        this.spztList = spztList;
    }
}
