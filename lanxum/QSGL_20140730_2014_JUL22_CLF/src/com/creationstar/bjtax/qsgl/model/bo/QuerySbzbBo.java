package com.creationstar.bjtax.qsgl.model.bo;


import java.io.Serializable;
import java.sql.Timestamp;

public class QuerySbzbBo implements Serializable {


    /**
     * �Ƿ����
     */
    private boolean person;
    /**
     * �Ƿ�¼�뷿����Ϣ
     */
    private boolean existFtxx;
    /**
     * �Ƿ�¼���Ǩ��Ϣ
     */
    private boolean existCqxx;
    /**
     * �Ƿ�¼�빫��ס����Ϣ
     */
    private boolean existGyzf;
    /**
     * �Ƿ�¼�뷿�ݽ�����Ϣ
     */
    private boolean existFwjh;
    /**
     * �걨���
     */
    private String sbbh;
    /**
     * �걨����
     */
    private java.sql.Timestamp sbrq;
    /**
     * �걨״̬
     */

    private String ztbs;


    public String getIsPersonName() {
        if (person) {
            return "����";
        } else {
            return "�Ǹ���";
        }
    }

    public String getBooleanName(boolean flag) {
        if (flag) {
            return "��";
        } else {
            return "��";
        }
    }

    public boolean isExistCqxx() {
        return existCqxx;
    }

    public boolean isExistFtxx() {
        return existFtxx;
    }

    public boolean isExistFwjh() {
        return existFwjh;
    }

    public boolean isExistGyzf() {
        return existGyzf;
    }

    public boolean isPerson() {
        return person;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Timestamp getSbrq() {
        return sbrq;
    }

    public String getZtbs() {
        return ztbs;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setPerson(boolean isPerson) {
        this.person = isPerson;
    }

    public void setExistGyzf(boolean existGyzf) {
        this.existGyzf = existGyzf;
    }

    public void setExistFwjh(boolean existFwjh) {
        this.existFwjh = existFwjh;
    }

    public void setExistFtxx(boolean existFtxx) {
        this.existFtxx = existFtxx;
    }

    public void setExistCqxx(boolean existCqxx) {
        this.existCqxx = existCqxx;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }
}
