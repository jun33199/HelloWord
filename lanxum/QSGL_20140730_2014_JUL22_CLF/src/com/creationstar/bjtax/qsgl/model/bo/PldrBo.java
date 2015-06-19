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
 */
public class PldrBo implements Serializable {
    //���κź���ţ�������λ����ʱ���е�λ��
    public String pch;
    //˰�����
    public String setz;
    //��˰������
    public String nsrmc;
    //֤������
    public String zjhm;
    //�绰����
    public String lxdh;
    //�Ǹ�������Ȩ��
    public String fgrnsrmc;
    //�Ǹ�������Ȩ�˼��������
    public String fgrjsjdm;
    //���˵Ķ��Ȩ��
    public ArrayList grxxList = new ArrayList();
    //�Ǹ��˵Ķ��Ȩ��
    public ArrayList fgrxxList = new ArrayList();

    public BigDecimal xh;

    //������Ϣ
    public Grxx grxx;
    //�Ǹ�����Ϣ
    public Fgrxx fgrxx;
    //�걨������Ϣ
    public Sbzb sbzb;
    //���������Ϣ
    public Spjgxx spjgxx;
    //���ط�����Ϣ
    public Tufwxx tufwxx;
    //��Ǩ��Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblcq����)
    public ArrayList cqxxList;
    //����ס����Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblgyzf����)
    public ArrayList gyzfxxList;
    //���ݽ�����Ϣ(Arryalist�е�ÿ��Ԫ�ض���PldrBo����,����û�в�Ǩ�͹���)
    public ArrayList fwjhxxList;

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

    public String getFgrjsjdm() {
        return fgrjsjdm;
    }

    public void setFgrjsjdm(String fgrjsjdm) {
        this.fgrjsjdm = fgrjsjdm;
    }

    public String getFgrnsrmc() {
        return fgrnsrmc;
    }

    public void setFgrnsrmc(String fgrnsrmc) {
        this.fgrnsrmc = fgrnsrmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getSetz() {
        return setz;
    }

    public void setSetz(String setz) {
        this.setz = setz;
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
