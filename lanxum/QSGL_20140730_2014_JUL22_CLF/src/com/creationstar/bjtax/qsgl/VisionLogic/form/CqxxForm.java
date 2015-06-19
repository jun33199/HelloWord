package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ������
 * @version 1.0
 */
public class CqxxForm extends BaseForm {

    /**
     * �ж��Ƿ����Բ�¼
     */
    private boolean bl = false;
    /**
     * �Ƿ��Ǹ���
     */
    private String person = "false";

    /**
     * ���޸��걨��Ϣҳ����뻹������ҳ��
     * "ADD"
     * "EDIT"
     */
    private String entrypage = "ADD";

    /**
     * �Ƿ����µ�¼��,Ҳ���ǵ�һ��ʹ�øò�Ǩ��Ϣ
     * ����ӿ��в�ѯ�����ľ���false
     */
    private boolean first = true;

    /**
     * ��ǨЭ�����
     */
    public String cqxyhm;
    //�����־ֵļ�ƣ��������յ�ļ�ƣ����������յ㣩��������������ȣ���λȫ�־���ˮ��
    private String fjmc;
    private String zsdmc;
    private String nd;
    private String lsh;


    /**
     * ����Ǩ���������ַ
     */
    public String bcqfwzldz;
    /**
     * ��Ǩ������
     */
    public String cqbce;
    /**
     * ����ʹ�ò�����
     */
    public String bcsybce;
    /**
     * ��Ǩ����ʣ���
     * @return String
     */
    public String cqbcsye;

    /**
     *ʣ��������־
     */
    public String syeywbz = "00";


    /**
     * ��CqGrForm�е����ݷŵ�jsblcq��Vo�У�Ȼ�󷵻�jsblcq
     * @return String
     */
    public Object getData() {
        Jsblcq jsblcq = new Jsblcq();
        jsblcq.bcsybce = DataConvert.String2BigDecimal(this.bcsybce);
        jsblcq.cqbce = DataConvert.String2BigDecimal(this.cqbce);
        jsblcq.cqbcsye = DataConvert.String2BigDecimal(this.getCqbcsye());
        jsblcq.cqxyh = this.cqxyhm;
        jsblcq.cqxyh = "��" + this.fjmc + "��˰" + this.zsdmc + "������" + nd + "��" +
                       lsh + "��";
        jsblcq.sbbh = this.sbbh;
        if (person.equals("true")) {
            jsblcq.yhbs = Constants.YHBZ_GR; //����
        } else {
            jsblcq.yhbs = Constants.YHBZ_FGR; //�Ǹ���
        }
        jsblcq.zldz = this.bcqfwzldz;
        jsblcq.ztbs = "0";
        if (this.syeywbz.equals("on")) {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }

        Debug.out("CqGrForm sbbh: " + sbbh);

        return jsblcq;
    }

    /**
     * ��ȡ�޸ĵĲ�Ǩ��Ϣ
     * ��CqGrForm�е����ݷŵ�jsblcq��Vo�У�Ȼ�󷵻�jsblcq
     * @return String
     */
    public Object getDataForUpdate() {
        Jsblcq jsblcq = new Jsblcq();
        jsblcq.bcsybce = DataConvert.String2BigDecimal(this.bcsybce);
        jsblcq.cqbce = DataConvert.String2BigDecimal(this.cqbce);
        jsblcq.cqbcsye = DataConvert.String2BigDecimal(this.getCqbcsye());
        jsblcq.cqxyh = this.cqxyhm;
        jsblcq.sbbh = this.sbbh;
        if (person.equals("true")) {
            jsblcq.yhbs = Constants.YHBZ_GR; //����
        } else {
            jsblcq.yhbs = Constants.YHBZ_FGR; //�Ǹ���
        }
        jsblcq.zldz = this.bcqfwzldz;
        jsblcq.ztbs = "0";
        if (this.syeywbz.equals("on")) {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblcq.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }
        Debug.out("CqGrForm sbbh: " + sbbh);

        return jsblcq;
    }

    /**
     * ��jsblcq �е����ݷŵ�CqGrForm��
     */
    public void setData(Jsblcq jsblcq) {
        this.bcsybce = DataConvert.BigDecimal2String(jsblcq.bcsybce, 2, false);
        this.cqbce = DataConvert.BigDecimal2String(jsblcq.cqbce, 2, false);
        this.cqbcsye = DataConvert.BigDecimal2String(jsblcq.cqbcsye, 2, false);
        this.cqxyhm = jsblcq.cqxyh;
        if (jsblcq.syeywbz != null) {
            if (jsblcq.syeywbz.equals(Constants.JSBL_SYEYWBZ_YONGWAN)) {
                this.syeywbz = "on";
            } else {
                this.syeywbz = jsblcq.syeywbz;
            }
        } else {

        }
        //this.fjmc = cqxyhm.
        this.bcqfwzldz = jsblcq.zldz;
    }

    /**
     * ���ո�������ָ������ַ���
     * @param s String
     * @return String[]
     */
    public String[] splitFjmc(String s) {
        String splitString = "--";
        String[] sz = new String[2];
        int len = s.length();
        int site = s.indexOf(splitString);
        int siteLen = splitString.length();
        //�ַ������� �������--110108983747585
        //ȡ����������
        sz[0] = s.substring(0, site);

        //ȡ�������ʺ�
        sz[1] = s.substring(site + siteLen, len);
        return sz;
    }


    /**
     * ��ñ��ο�ʹ�ò�����
     * @return String
     */
    public String getBcksybce() {
        return this.getCqbcsye();
    }

    /**
     * ��ñ���Ǩ���������ַ
     * @return String
     */
    public String getBcqfwzldz() {
        return bcqfwzldz;
    }

    /**
     * ��ñ���ʹ�ò�����
     * @return String
     */
    public String getBcsybce() {
        return bcsybce;
    }

    /**
     * ��ò�Ǩ������
     * @return String
     */
    public String getCqbce() {
        return cqbce;
    }

    /**
     * ��ò�Ǩ����ʣ���
     * @return String
     */
    public String getCqbcsye() {
        return cqbcsye;
    }

    /**
     * ��ò�ǨЭ�����
     * @return String
     */
    public String getCqxyhm() {
        return cqxyhm;
    }

    public String getPerson() {
        return person;
    }

    public String getEntrypage() {
        return entrypage;
    }

    public boolean isFirst() {
        return first;
    }

    public String getLsh() {
        return lsh;
    }

    public String getFjmc() {
        return fjmc;
    }

    public String getNd() {
        return nd;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    public boolean isBl() {
        return bl;
    }

    /**
     * ���ñ���Ǩ���������ַ
     * @param bcqfwzldz String
     */
    public void setBcqfwzldz(String bcqfwzldz) {
        this.bcqfwzldz = bcqfwzldz;
    }

    /**
     * ���ñ���ʹ�ò�����
     * @param bcsybce String
     */
    public void setBcsybce(String bcsybce) {
        this.bcsybce = bcsybce;
    }

    /**
     * ���ò�Ǩ������
     * @param cqbce String
     */
    public void setCqbce(String cqbce) {
        this.cqbce = cqbce;
    }

    /**
     * ���ò�Ǩ����ʣ���
     * @param cqbcsye String
     */
    public void setCqbcsye(String cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    /**
     * ���ò�ǨЭ�����
     * @param cqxyhm String
     */
    public void setCqxyhm(String cqxyhm) {
        this.cqxyhm = cqxyhm;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setEntrypage(String entrypage) {
        this.entrypage = entrypage;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

}
