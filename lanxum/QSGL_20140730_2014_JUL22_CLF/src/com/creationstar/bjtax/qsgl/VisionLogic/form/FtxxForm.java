package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Arith;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;


/**
 *
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
public class FtxxForm extends BaseForm {
    /**
     * �Ƿ��Ǹ���
     */
    private String person = "false";


    /**
     * �Ƿ��ǲ���
     */
    private boolean bzqs = false;

    /**
     * ˰�����
     */
    private String setz;

    /**
     * ���ؼ���
     */
    private String tdjc;

    /**
     * �ݻ���
     */
    private String rjl;

    
    /**
     * �걨����
     */
    private String sbrq;
    
    public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	/**
     * ���ء�����Ψһ��ʶ
     */
    public String tdfwwybz;
    /**
     * ���ز���Ŀ����
     */
    public String fdcxmmc;
    /**
     * ��Լǩ��ʱ��
     */
    public String hyqdsj;
    /**
     * ����
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm = "07";

    /**
     * ���ء�����Ȩ��ת������
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    public String tdfwqszylxmc;
    public String tdfwqszylxdm;

    /**
     * �������
     */
    public ArrayList fwlbList = new ArrayList();
    public String fwlbmc;
    public String fwlbdm = "03";

    /**
     * �Ƿ���ַ�
     */
    public String sfesf = Constants.TUFWXX_SFESF_FALSE;
    /**
     * ���ء����������ַ
     */
    public String tdfwzldz;
    /**
     * ���ء�����Ȩ��ת�����
     */
    public String tdfwqszymj;
    /**
     * ���ݽ������
     */
    public String fwjzmj;
    /**
     * �ɽ��۸� Ԫ(�����)
     */
    public String cjjgyrmb;
    /**
     * �����۸�
     */
    public String pgjg;
    /**
     * �ɽ��۸� Ԫ(���)
     */
    public String cjjgywb;
    /**
     *����
     */
    public ArrayList bzList = new ArrayList();
    public String bzmc;
    public String bzdm = "USD";

    /**
     * ���ؼ����б�
     */
    private ArrayList tdjcList = new ArrayList();


    /**
     * ����
     */
    public String hn;
    /**
     * �ۺ�Ԫ(�����)
     */
    public String zhyrmb;

    //yangxiao 2008-12-06 start
    /**
     * ɨ���ʾ
     */
    public String smbs;
    //yangxiao 2008-12-06 end

    /**
     * ��óɽ��۸� Ԫ(�����)
     * @return String
     */
    public String getCjjgyrmb() {
        return cjjgyrmb;
    }

    /**
     * ��óɽ��۸� Ԫ(���)
     * @return String
     */
    public String getCjjgywb() {
        return cjjgywb;
    }

    /**
     * ��÷��ز���Ŀ����
     * @return String
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     * ��÷��ݽ������
     * @return String
     */
    public String getFwjzmj() {
        return fwjzmj;
    }

    /**
     * ��û���
     * @return String
     */
    public String getHn() {
        return hn;
    }

    /**
     * ��ú�Լǩ��ʱ��
     * @return String
     */
    public String getHyqdsj() {
        return hyqdsj;
    }

    /**
     * ��������۸�
     * @return String
     */
    public String getPgjg() {
        return pgjg;
    }

    /**
     * ������ء�����Ȩ��ת�����
     * @return String
     */
    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     * ������ء�����Ψһ��ʶ
     * @return String
     */
    public String getTdfwwybz() {
        return tdfwwybz;
    }

    /**
     * ������ء����������ַ
     * @return String
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     * ����ۺ�Ԫ(�����)
     * @return String
     */
    public String getZhyrmb() {
        return zhyrmb;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public ArrayList getFwlbList() {
        return fwlbList;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzdm() {
        return bzdm;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFldm() {
        return fldm;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getFwlbdm() {
        return fwlbdm;
    }

    public String getFwlbmc() {
        return fwlbmc;
    }

    public String getTdfwqszylxdm() {
        return tdfwqszylxdm;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public String getPerson() {
        return person;
    }

    public boolean isBzqs() {
        return bzqs;
    }

    public String getSetz() {
        return setz;
    }

    public String getRjl() {
        return rjl;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getSfesf() {
        return sfesf;
    }

    public ArrayList getTdjcList() {
        return tdjcList;
    }

    public String getSmbs() {
        return smbs;
    }

    /**
     * ���óɽ��۸� Ԫ(�����)
     * @param cjjgyrmb String
     */
    public void setCjjgyrmb(String cjjgyrmb) {
        this.cjjgyrmb = cjjgyrmb;
    }

    /**
     * ���óɽ��۸� Ԫ(���)
     * @param cjjgywb String
     */
    public void setCjjgywb(String cjjgywb) {
        this.cjjgywb = cjjgywb;
    }

    /**
     * ���÷��ز���Ŀ����
     * @param fdcxmmc String
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    /**
     * ���÷��ݽ������
     * @param fwjzmj String
     */
    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * ���û���
     * @param hn String
     */
    public void setHn(String hn) {
        this.hn = hn;
    }

    /**
     *  ���ú�Լǩ��ʱ��
     * @param hyqdsj String
     */
    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    /**
     *  ���������۸�
     * @param pgjg String
     */
    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    /**
     * �������ء�����Ȩ��ת�����
     * @param tdfwqszymj String
     */
    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * �������ء�����Ψһ��ʶ
     * @param tdfwwybz String
     */
    public void setTdfwwybz(String tdfwwybz) {
        this.tdfwwybz = tdfwwybz;
    }

    /**
     * �������ء����������ַ
     * @param tdfwzldz String
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * �����ۺ�Ԫ(�����)
     * @param zhyrmb String
     */
    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFwlbdm(String fwlbdm) {
        this.fwlbdm = fwlbdm;
    }

    public void setFwlbmc(String fwlbmc) {
        this.fwlbmc = fwlbmc;
    }

    public void setTdfwqszylxdm(String tdfwqszylxdm) {
        this.tdfwqszylxdm = tdfwqszylxdm;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setBzqs(boolean bzqs) {
        this.bzqs = bzqs;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setSfesf(String sfesf) {
        this.sfesf = sfesf;
    }

    public void setTdjcList(ArrayList tdjcList) {
        this.tdjcList = tdjcList;
    }

    public void setSmbs(String smbs) {
        this.smbs = smbs;
    }

    /**
     * ��FtxxForm�е����ݷŵ�tufwxx��Vo�У�Ȼ�󷵻�tufwxx
     * @return String
     */

    public Object getData() {
        Tufwxx tufwxx = new Tufwxx();
        tufwxx.sbbh = this.sbbh;
        tufwxx.tdfwid = this.tdfwwybz;
        tufwxx.fdcxmmc = this.fdcxmmc;
        tufwxx.htqdsj = DataConvert.String2Timestamp(this.hyqdsj);
        tufwxx.fldm = this.fldm;
        tufwxx.flmc = this.flmc;

        tufwxx.setz = this.setz;

        tufwxx.tdfwqszylx = this.tdfwqszylxdm;
        tufwxx.tdfwqszymc = this.tdfwqszylxmc;

        tufwxx.fwlxdm = this.fwlbdm;
        tufwxx.fwlxmc = this.fwlbmc;

        tufwxx.tdfwzldz = this.tdfwzldz;

        //�޸�С����λ��ʾΪ��λ��modify by fujx��20081129
        if (null == this.tdfwqszymj || "null".equals(this.tdfwqszymj) ||
            "".equals(this.tdfwqszymj)) {
            tufwxx.tdfwqszymj = DataConvert.String2BigDecimal(Arith.roundStr(
                    "0.000", 3));
        } else {
            tufwxx.tdfwqszymj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                    tdfwqszymj, 3));
        }
        tufwxx.cjjgrmb = DataConvert.String2BigDecimal(this.cjjgyrmb);
        tufwxx.pgjgrmb = DataConvert.String2BigDecimal(this.pgjg);
        tufwxx.cjjgwb = DataConvert.String2BigDecimal(this.cjjgywb);
        tufwxx.bzdm = this.bzdm;
        tufwxx.bzmc = this.bzmc;
        //�޸�С����λ��ʾΪ��λ��modify by fujx��20081129
        if (null == this.fwjzmj || "null".equals(this.fwjzmj) ||
            "".equals(this.fwjzmj)) {
            tufwxx.fwjzmj = DataConvert.String2BigDecimal(Arith.roundStr(
                    "0.000", 3));
        } else {
            tufwxx.fwjzmj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                    fwjzmj, 3));
        }
        tufwxx.hldm = DataConvert.String2BigDecimal(this.hn);
        Debug.out("tdfwqszylxdm=" + tdfwqszylxdm);
        Debug.out("jzmj=" + fwjzmj);
        tufwxx.zhjgrmb = DataConvert.String2BigDecimal(this.zhyrmb);
        tufwxx.rjl = this.rjl;
        tufwxx.tdjc = this.tdjc;
        tufwxx.sfesf = this.sfesf;
        return tufwxx;
    }

    /**
     * ��tufwxx �е����ݷŵ�FtxxForm��
     */

    public void setData(Tufwxx tufwxx) {
        this.sbbh = tufwxx.sbbh;
        this.tdfwwybz = tufwxx.tdfwid;
        this.fdcxmmc = tufwxx.fdcxmmc;
        this.hyqdsj = DataConvert.TimeStamp2String(tufwxx.htqdsj);
        this.fldm = tufwxx.fldm;
        this.tdfwqszylxdm = tufwxx.tdfwqszylx;
        this.fwlbdm = tufwxx.fwlxdm;
        this.tdfwzldz = tufwxx.tdfwzldz;
        this.tdfwqszymj = DataConvert.BigDecimal2String(tufwxx.tdfwqszymj, 2, false);
        this.cjjgyrmb = DataConvert.BigDecimal2String(tufwxx.cjjgrmb, 2, false);
        this.pgjg = DataConvert.BigDecimal2String(tufwxx.pgjgrmb, 2, false);
        this.cjjgywb = DataConvert.BigDecimal2String(tufwxx.cjjgwb, 2, false);
        this.bzdm = tufwxx.bzdm;
        this.hn = DataConvert.BigDecimal2String(tufwxx.hldm, 5, false);
        this.zhyrmb = DataConvert.BigDecimal2String(tufwxx.zhjgrmb, 2, false);
        this.fwjzmj = DataConvert.BigDecimal2String(tufwxx.fwjzmj, 2, false);

        this.flmc = tufwxx.flmc;
        this.tdfwqszylxmc = tufwxx.tdfwqszymc;
        this.fwlbmc = tufwxx.fwlxmc;
        this.bzmc = tufwxx.bzmc;
//        this.setz = tufwxx.setz;
        this.rjl = tufwxx.rjl;
        this.tdjc = tufwxx.tdjc;
        this.sfesf = tufwxx.sfesf;
    }
}
