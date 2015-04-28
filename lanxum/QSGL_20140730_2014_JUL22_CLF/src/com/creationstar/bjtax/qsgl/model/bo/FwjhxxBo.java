package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.util.Constants;


public class FwjhxxBo implements Serializable {
    /**
     * ���������ʾ
     */
    public String jhperson;
    /**
     * �������ػ�����Ϣ����
     * @return Tufwxx
     */
    public Tufwxx tufwxx;
    /**
     * ���˻�����Ϣ����
     * @return Tufwxx
     */
    public Grxx grxx;

    /**
     * ���˻�����Ϣ����
     * @return Tufwxx
     */
    public List nsrList;

    /**
     * �Ǹ��˻�����Ϣ����
     * @return Fgrxx
     */
    public Fgrxx fgrxx;

    /**
     * ����Ȩ����Ϣ
     */
    private Grxx voZcqrxx = null;

    /**
     * �걨���
     */
    public String sbbh;

    public String bz;

    /**
     *
     */
    public String fcjslh;

    /**
     *
     */
    public String jkfsdm;

    /**
     *
     */
    public String jkfsmc;

    /**
     *
     * @return Grxx
     */
    public Grxx getGrxx() {
        return grxx;
    }

    /**
     *
     * @return Tufwxx
     */
    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getFcjslh() {
        return fcjslh;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getBz() {
        return bz;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public String getJhperson() {
        return jhperson;
    }

    /**
     *
     * @param voGrxx Grxx
     */
    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    /**
     *
     * @param voTufwxx Tufwxx
     */
    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setJhperson(String jhperson) {
        this.jhperson = jhperson;
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

}
