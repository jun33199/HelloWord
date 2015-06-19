package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryCqxxBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;

/**
 * <p>Title: ��ѯ��Ǩ��Ϣʹ�����Form</p>
 *
 * <p>Description: ��Ӧ��ѯ��Ǩʹ�����ҳ�棬�����ѯ�����Ͳ�ѯ���</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QueryCqxxForm extends BaseForm {
    public QueryCqxxForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ��ǨЭ����� ���ֶ���Ϊ��ѯ����
     */
    private String cqxyh;

    /**
     * ���ڱ�ʾ��true�����ڣ�false��������
     */
    private boolean exist = false;

    /**
     * ��ʾ�Ƿ��ǲ�ѯ���
     */
    private boolean afterQuery = false;

    //�����ֶ���Ϊ��ѯ�����ʾ��Ǩ��Ϣ
    /**
     * ��Ǩ���������ַ
     */
    private String zldz;

    /**
     * ��Ǩ������
     */
    private String cqbce;

    /**
     * ��Ǩ����ʣ���
     */
    private String cqbcsye;
    /**
     * �걨��
     */
    private String cjr;
    /**
     * �걨����
     */
    private String cjrq;

    /**
     *ʣ���
     */
    private String sye;

    /**
     * ʹ����� Sbcqglֵ������б�
     */
    private ArrayList listSbxx;

    /**
     * ����ѯ������浽form��
     */
    public void setData(QueryCqxxBo bo) {
        this.cqxyh = bo.getCqxyh();
        this.zldz = bo.getZldz();
        this.cqbce = DataConvert.BigDecimal2String(bo.getCqbce());
        this.cqbcsye = DataConvert.BigDecimal2String(bo.getCqbcsye());
        this.sye = DataConvert.BigDecimal2String(bo.getCqbcsye());
        this.cjr = bo.getCjr();
        this.cjrq = DataConvert.TimeStamp2String(bo.getCjrq());
        this.listSbxx = bo.getListSbxx();
    }

    /**
     * ��ղ�ѯ���
     */
    public void clearResult() {
        this.cqbce = "";
        this.cqbcsye = "";
        this.zldz = "";
        this.cjr = "";
        this.cjrq = "";
        this.sye = "";
        this.listSbxx = null;
    }

    public String getCqbce() {
        return cqbce;
    }

    public String getCqbcsye() {
        return cqbcsye;
    }

    public String getCqxyh() {
        return cqxyh;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getZldz() {
        return zldz;
    }

    public boolean isExist() {
        return exist;
    }

    public boolean isAfterQuery() {
        return afterQuery;
    }

    public String getCjr() {
        return cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public String getSye() {
        return sye;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCqxyh(String cqxyh) {
        this.cqxyh = cqxyh;
    }

    public void setCqbcsye(String cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    public void setCqbce(String cqbce) {
        this.cqbce = cqbce;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public void setSye(String sye) {
        this.sye = sye;
    }

    private void jbInit() throws Exception {
    }
}
