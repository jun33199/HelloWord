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
 * Description: ������������Ϊ���ܵĲ�ѯ���� ��ѯ����ֱ𱣴浽���Ե�vo
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
     * ���淿�ݽ����Է����걨����
     */
    private FwjhxxBo dfSbxxBo;

    /**
     * ��˰�걨���
     */
    private String sbbh;

    /**
     * ��ӡʹ�õ���˰�걨���
     */
    private String printSbbh;

    /**
     * �Ƿ������˷��ݻ�����Ϣ
     */
    private boolean addedTufwxx = false;

    /**
     * �Ƿ������˷��ݽ�����Ϣ
     */
    private boolean addedFwjhxx = false;

    /**
     * �걨��������
     */
    private Sbzb voSbzb = null;

    /**
     * ��˽����Ϣ
     */
    private Spjgxx voSpjgxx = null;

    /**
     * ���ݻ�����Ϣ
     */
    private Tufwxx voTufwxx = null;

    /**
     * ����Ȩ����Ϣ
     */
    private Grxx voZcqrxx = null;

    /**
     * �Ǹ�����Ϣ
     */
    private Fgrxx voFgrxx = null;

    /**
     * ���ݽ�����Ϣ
     */
    private FwjhxxBo voFwjh = null;

    /**
     * ������ArrayList��Ϊ�������ػ�����Ϣ����Ǩ���ѹ�����ס�� ÿ��ArrayList�У��������Ӧ��Vo��ArrayList�еĶ���
     */
    private ArrayList cqList = new ArrayList();

    private ArrayList gyzfList = new ArrayList();

    private List nsrList = new ArrayList();

    public JghdsjBo hdbo;


    /**
     * ��˰�����걨�� ArrayList
     *
     */
    private ArrayList jmsbbList = new ArrayList();

    /**
     * �������������� list ���־ֻ��ء��оֻ���
     */
    private ArrayList spjgList = new ArrayList();
    private String spjg = "9000";

    /**
     * ����״̬ list 5�����ͬ�⣻6����˲�ͬ��
     */
    private ArrayList spztList = new ArrayList();
    private String spzt = Constants.ZB_ZTBS_JS_BTY;

    /**
     * ��������
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
     * �жϸ��걨�Ƿ��Ǹ���
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
     * �жϸ��걨�Ƿ��ǲ���
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
     * @param jmsbbList Ҫ���õ� jmsbbList
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
