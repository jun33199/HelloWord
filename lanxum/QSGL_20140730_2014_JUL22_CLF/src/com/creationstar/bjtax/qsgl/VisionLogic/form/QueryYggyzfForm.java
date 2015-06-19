package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryYggyzfBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;


/**
 * <p>Title: ��ѯ�ѹ�����ס����Ϣʹ�����Form</p>
 *
 * <p>Description: ��Ӧ��ѯ�ѹ�����ס��ʹ�����ҳ�棬�����ѯ�����Ͳ�ѯ���</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ������
 * @version 1.0
 */
public class QueryYggyzfForm extends BaseForm {

    /**
     * ���ڱ�ʾ��true�����ڣ�false��������
     */
    private boolean exist = false;

    /**
     * ��ʾ�Ƿ��ǲ�ѯ���
     */
    private boolean afterQuery = false;

    /**
     * �ѹ�����ס��Ȩ��֤���
     */
    private String yggyzfqszsh;
    /**
     * ���������ַ
     */
    private String zldz;
    /**
     * ���ۺ�ͬ����Լ��ǩ��ʱ��
     */
    private String qdsj;
    /**
     * �������
     */
    private String jzmj;
    /**
     * �ɽ��۸�
     */
    private String cjjg;
    /**
     * ʣ���
     */
    private String sye;
    /**
     * ����Ȩ��֤��� add by zhangyj 20090219
     */
    private String fwqszsh;

    /**
     * ʹ����� Sbcqglֵ������б�
     */
    private ArrayList listSbxx;
    private QueryYggyzfBo data;

    /**
     * ����ѯ������浽form��
     */
    public void setData(QueryYggyzfBo bo) {
        this.zldz = bo.getZldz();
        this.qdsj = DataConvert.TimeStamp2String(bo.getQdsj());
        this.jzmj = DataConvert.BigDecimal2String(bo.getJzmj());
        this.cjjg = DataConvert.BigDecimal2String(bo.getCjjg());
        this.sye = DataConvert.BigDecimal2String(bo.getSye());
        this.listSbxx = bo.getListSbxx();
       this.fwqszsh=bo.getFwqszsh();
    }

    /**
     * ��ղ�ѯ���
     */
    public void clearResult() {
        this.qdsj = "";
        this.jzmj = "";
        this.zldz = "";
        this.listSbxx = null;
        this.sye = "";
       this.fwqszsh = "";
    }


    public boolean isAfterQuery() {
        return afterQuery;
    }

    public boolean isExist() {
        return exist;
    }

    public String getJzmj() {
        return jzmj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getQdsj() {
        return qdsj;
    }

    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    public String getZldz() {
        return zldz;
    }

    public String getCjjg() {
        return cjjg;
    }

    public String getSye() {
        return sye;
    }
    
    public String getFwqszsh() {
        return fwqszsh;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setQdsj(String qdsj) {
        this.qdsj = qdsj;
    }

    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public void setSye(String sye) {
        this.sye = sye;
    }

    public void setFwqszsh(String fwqszsh) {
        this.fwqszsh = fwqszsh;
    }

}
