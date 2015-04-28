package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 *
 * 说明：该Bo是针对页面中"批量受理(税务人员)"模块
 */
public class PldrBo2 implements Serializable {
    //批次号和序号，用来定位在临时表中的位置
    public String pch;
    public BigDecimal xh;

    //个人信息
    public Grxx grxx;
    //非个人信息
    public Fgrxx fgrxx;
    //申报主表信息
    public Sbzb sbzb;
    //审批结果信息
    public Spjgxx spjgxx;
    //土地房屋信息
    public Tufwxx tufwxx;
    //拆迁信息(Arryalist中的每个元素都是jsblcq对象)
    public ArrayList cqxxList;
    //公有住房信息(Arryalist中的每个元素都是jsblgyzf对象)
    public ArrayList gyzfxxList;
    //房屋交换信息(Arryalist中的每个元素都是PldrBo对象,但是没有拆迁和公房)
    public ArrayList fwjhxxList;
    //个人的多产权人
    public ArrayList grxxList = new ArrayList();
    //非个人的多产权人
    public ArrayList fgrxxList = new ArrayList();

    public JghdsjBo hdbo;

    public void setGrxxList(ArrayList grxxList) {
        this.grxxList = grxxList;
    }

    public ArrayList getGrxxList() {
        return grxxList;
    }

    public void setFgrxxList(ArrayList fgrxxList) {
        this.fgrxxList = fgrxxList;
    }

    public ArrayList getFgrxxList() {
        return fgrxxList;
    }


    public ArrayList getCqxxList() {
        return cqxxList;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public ArrayList getFwjhxxList() {
        return fwjhxxList;
    }

    public Grxx getGrxx() {
        return grxx;
    }

    public ArrayList getGyzfxxList() {
        return gyzfxxList;
    }

    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getPch() {
        return pch;
    }

    public BigDecimal getXh() {
        return xh;
    }

    public Spjgxx getSpjgxx() {
        return spjgxx;
    }


    public void setCqxxList(ArrayList cqxxList) {
        this.cqxxList = cqxxList;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setFwjhxxList(ArrayList fwjhxxList) {
        this.fwjhxxList = fwjhxxList;
    }

    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    public void setGyzfxxList(ArrayList gyzfxxList) {
        this.gyzfxxList = gyzfxxList;
    }

    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    public void setSpjgxx(Spjgxx spjgxx) {
        this.spjgxx = spjgxx;
    }

    public Sbzb getSbzb() {
        return sbzb;
    }

    public void setSbzb(Sbzb sbzb) {
        this.sbzb = sbzb;
    }

    /**
     * @return Returns the hdbo.
     */
    public JghdsjBo getHdbo() {
        return hdbo;
    }

    /**
     * @param hdbo The hdbo to set.
     */
    public void setHdbo(JghdsjBo hdbo) {
        this.hdbo = hdbo;
    }
}
