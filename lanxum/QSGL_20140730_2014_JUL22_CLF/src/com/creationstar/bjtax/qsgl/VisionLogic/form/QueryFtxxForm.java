package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.QueryFtxxBo;
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
 * @author ������
 * @version 1.0
 */

public class QueryFtxxForm extends BaseForm {
    /**
     * ���ڱ�ʾ��true�����ڣ�false��������
     */
    private boolean exist = false;

    /**
     * ��ʾ�Ƿ��ǲ�ѯ���
     */
    private boolean afterQuery = false;

    /**
     * ʹ����� Sbftglֵ������б�
     */
    private ArrayList listSbxx = new ArrayList();
    /**
     * ���ء�����Ψһ��ʶ
     */
    public String tdfwid;

    /**
     * ���ز���Ŀ����
     */
    private String fdcxmmx;
    /**
     * ��ͬ����Լ��ǩ��ʱ��
     */
    private String htqdsj;
    /**
     * ����
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm;

    /**
     * ���ء�����Ȩ��ת������
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    private String tdfwqszylx;
    private String tdfwqszylxmc;

    /**
     * �������
     */
    public ArrayList fwlxList = new ArrayList();
    private String fwlxdm;
    private String fwlxmc;


    /**
     * ���ء����������ַ
     */
    private String tdfwzldz;
    /**
     * ���ء�����Ȩ��ת�����
     */
    private String tdfwqszymj;
    /**
     * ���ݽ������
     */
    private String fwjzmj;
    /**
     * �ɽ��۸�����ң�
     */
    private String cjjgrmb;
    /**
     * �����۸�����ң�
     */
    private String pgjgrmb;
    /**
     * �ɽ��۸���ң�
     */
    private String cjjgwb;
    /**
     * ����
     */
    public ArrayList bzList = new ArrayList();
    private String bzdm;
    private String bzmc;
    /**
     * ����
     */
    private String hl;
    /**
     * �ۺϼ۸�����ң�
     */
    private String zhjgrmb;
    /**
     * �걨��
     */
    private String cjr;
    /**
     * �걨����
     */
    private String cjrq;

    public boolean isAfterQuery() {
        return afterQuery;
    }

    public String getBzdm() {
        return bzdm;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getCjjgrmb() {
        return cjjgrmb;
    }

    public String getCjjgwb() {
        return cjjgwb;
    }

    public boolean isExist() {
        return exist;
    }

    public String getFdcxmmx() {
        return fdcxmmx;
    }

    public String getFldm() {
        return fldm;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getFwjzmj() {
        return fwjzmj;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public ArrayList getFwlxList() {
        return fwlxList;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getHl() {
        return hl;
    }

    public String getHtqdsj() {
        return htqdsj;
    }

    public String getPgjgrmb() {
        return pgjgrmb;
    }

    public String getTdfwid() {
        return tdfwid;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getZhjgrmb() {
        return zhjgrmb;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getCjr() {
        return cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setAfterQuery(boolean afterQuery) {
        this.afterQuery = afterQuery;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setZhjgrmb(String zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    public void setPgjgrmb(String pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setHtqdsj(String htqdsj) {
        this.htqdsj = htqdsj;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxList(ArrayList fwlxList) {
        this.fwlxList = fwlxList;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFdcxmmx(String fdcxmmx) {
        this.fdcxmmx = fdcxmmx;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void setCjjgwb(String cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    public void setCjjgrmb(String cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * ��QueryFtxxBo�еĲ�ѯ������浽form�У��Ա�ҳ�����
     */
    public void setData(QueryFtxxBo bo) {
        this.tdfwid = bo.getTdfwid();
        this.fdcxmmx = bo.getFdcxmmx();
        this.htqdsj = DataConvert.TimeStamp2String(bo.getHtqdsj());
        this.flmc = bo.getFlmc();
        this.tdfwqszylxmc = bo.getTdfwqszylxmc();
        this.fwlxmc = bo.getFwlxmc();
        this.tdfwzldz = bo.getTdfwzldz();
        this.tdfwqszymj = DataConvert.BigDecimal2String(bo.getTdfwqszymj());
        this.fwjzmj = DataConvert.BigDecimal2String(bo.getFwjzmj());
        this.cjjgrmb = DataConvert.BigDecimal2String(bo.getCjjgrmb());
        this.pgjgrmb = DataConvert.BigDecimal2String(bo.getPgjgrmb());
        this.cjjgwb = DataConvert.BigDecimal2String(bo.getCjjgwb());
        this.bzmc = bo.getBzmc();
        this.hl = DataConvert.BigDecimal2String(bo.getHl());
        this.zhjgrmb = DataConvert.BigDecimal2String(bo.getZhjgrmb());
        this.cjr = bo.getCjr();
        this.cjrq = DataConvert.TimeStamp2String(bo.getCjrq());
        this.listSbxx = bo.getListSbxx();

    }

    /**
     * ��ղ�ѯ���
     */
    public void clearResult() {
        this.fdcxmmx = "";
        this.htqdsj = "";
        this.flmc = "";
        this.tdfwqszylxmc = "";
        this.fwlxmc = "";
        this.tdfwzldz = "";
        this.tdfwqszymj = "";
        this.fwjzmj = "";
        this.cjjgrmb = "";
        this.pgjgrmb = "";
        this.cjjgwb = "";
        this.bzmc = "";
        this.hl = "";
        this.zhjgrmb = "";
        this.cjr = "";
        this.cjrq = "";
        this.listSbxx = null;

    }


}
