package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * <p>Title: </p>
 *
 * <p>Description:�˶�֪ͨ��ҵ�������Ҫ���ں˶�֪ͨ������ɣ���ӡ </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class HdtzsBo implements Serializable {
    /**
     * �걨���
     */
    private String sbbh;

    /**
     * �˶�֪ͨ���_�޸�
     */
    private String hdtzsh_xg;
    /**
     * �������� �����ķ���ʹ�ø�����������Ϊ��˰����
     */
    private String fwlxmc;

    /**
     * �������� �����ķ���ʹ�ø�����������Ϊ��˰����
     */
    private String fwlxdm;

    /**
     * ��������ļ���˰��
     */
    private BigDecimal spjmse;

    /**
     * �˶�֪ͨ��ֵ����
     */
    private Hdtzs voHdtzs;

    /**
     * ���ݻ�����Ϣ
     */
    private Tufwxx voTufwxx;

    /**
     * ��������ӳ��
     */
    private HashMap jmnrMap;


    public HdtzsBo() {
    }

    public boolean isBz() {
        return this.voHdtzs.bzbs.equals(Constants.BZBS_BZ);
    }

    public HashMap getJmnrMap() {
        return jmnrMap;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Hdtzs getVoHdtzs() {
        return voHdtzs;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public BigDecimal getSpjmse() {
        return spjmse;
    }

    public void setVoHdtzs(Hdtzs voHdtzs) {
        this.voHdtzs = voHdtzs;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setJmnrMap(HashMap jmnrMap) {
        this.jmnrMap = jmnrMap;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setSpjmse(BigDecimal spjmse) {
        this.spjmse = spjmse;
    }

    /**
     * @return voTufwxx
     */
    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    /**
     * @param voTufwxx Ҫ���õ� voTufwxx
     */
    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public String getHdtzsh_xg() {
        return hdtzsh_xg;
    }

    public void setHdtzsh_xg(String hdtzsh_xg) {
        this.hdtzsh_xg = hdtzsh_xg;
    }

}
