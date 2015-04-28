package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;

public class AddCqxxForm extends BaseForm {
    private String cqrmc; //'��Ǩ������'
    private String cqfw; //'��Ǩ��Χ'
    private String bcqrmc; //'����Ǩ������'
    private ArrayList bcqrlxList = new ArrayList();
    private String bcqrlxdm; //'����Ǩ�����ʹ���'
    private String bcqrlxmc; //'����Ǩ����������'
    private String zjhm; //'֤������'
    private ArrayList zjlx = new ArrayList();
    private String zjlxdm; //'֤�����ʹ���'
    private String zjlxmc; //'֤����������'
    private String cqxxdz; //'��Ǩ��ϸ��ַ'
    private String bcje; //'�������'
    private ArrayList bclx = new ArrayList();
    private String bclxdm; //'�������ʹ���'
    private String bclxmc; //'������������'
    private String bcmj; //'�������'
    private String bcfwdz; //'�������ݵ�ַ'
    private String cqxkzh; //'��Ǩ���֤��'
    private String cqxmmc; //'��Ǩ��Ŀ����'
    private String cqmj; //'��Ǩ���'
    private HashMap szqxMap = new HashMap(); //'��������'
    private String szqx;
    private String cqxxbh; //'���'
    private String gjrmc; // ����������

    public String getGjrmc() {
        return gjrmc;
    }

    public void setGjrmc(String gjrmc) {
        this.gjrmc = gjrmc;
    }

    public AddCqxxForm() {
        szqxMap.put("01", "����");
        szqxMap.put("02", "����");
        szqxMap.put("03", "����");
        szqxMap.put("04", "����");
        szqxMap.put("05", "����");
        szqxMap.put("06", "����");
        szqxMap.put("07", "��̨");
        szqxMap.put("08", "ʯ��ɽ");
        szqxMap.put("09", "��ͷ��");
        szqxMap.put("10", "��ɽ");
        szqxMap.put("11", "��ƽ");
        szqxMap.put("12", "ͨ��");
        szqxMap.put("13", "˳��");
        szqxMap.put("14", "����");
        szqxMap.put("15", "��ɽ");
        szqxMap.put("16", "����");
        szqxMap.put("17", "����");
        szqxMap.put("18", "ƽ��");
        szqxMap.put("19", "����");
        szqxMap.put("20", "������");
        szqxMap.put("21", "��վ");
        szqxMap.put("22", "����");
    }

    public Object getData() {
        Cqxxb cqxxb = new Cqxxb();
        switch (Integer.parseInt(bclxdm)) {
        case Constants.BCLX_HB:
            cqxxb.setBcje(DataConvert.String2BigDecimal(this.bcje));
            break;
        case Constants.BCLX_HH:
            cqxxb.setBcje(DataConvert.String2BigDecimal((bcje)));
            cqxxb.setBcmj(DataConvert.String2BigDecimal((this.bcmj)));
            break;
        case Constants.BCLX_SW:
            cqxxb.setBcmj(DataConvert.String2BigDecimal((this.bcmj)));
            break;
        }
        cqxxb.setBcfwdz(this.bcfwdz);
        cqxxb.setBclxdm(this.bclxdm);
        cqxxb.setBclxmc(this.bclxmc);
        cqxxb.setBcqrlxdm(this.bcqrlxdm);
        cqxxb.setBcqrlxmc(this.bcqrlxmc);
        cqxxb.setBcqrmc(this.bcqrmc);
        cqxxb.setCjrq(new Timestamp(System.currentTimeMillis()));
        cqxxb.setCqxxdz(this.cqxxdz);
        cqxxb.setCqfw(this.cqfw);
        //if (this.cqmj!=null && !this.cqmj.equals(""))
        cqxxb.setCqmj(DataConvert.String2BigDecimal((this.cqmj)));
        cqxxb.setCqrmc(this.cqrmc);
        cqxxb.setCqxkzh(this.cqxkzh);
        cqxxb.setQxdm(this.szqx);
        cqxxb.setSzqx((String) szqxMap.get(szqx));
        cqxxb.setZjlxdm(this.zjlxdm);
        cqxxb.setZjlxmc(this.zjlxmc);
        cqxxb.setZjhm(this.zjhm);
        cqxxb.setCqxmmc(this.cqxmmc);
        cqxxb.setLrrq(new Timestamp(System.currentTimeMillis()));
        cqxxb.setSjly(Constants.CQXXB_SJLY_LR);
        cqxxb.setGjrmc(this.gjrmc);
        return cqxxb;
    }


    /**
     * ��ո���
     * �ɸ�����ʵ��
     */
    public void clear() {
        this.setBcje("");
        this.setBcmj("");
        this.setBcfwdz("");
        this.setBclxdm("");
        this.setBclxmc("");
        this.setBcqrlxdm("");
        this.setBcqrlxmc("");
        this.setBcqrmc("");
        this.setCqxxdz("");
        this.setCqfw("");
        this.setCqmj("");
        this.setCqrmc("");
        this.setCqxkzh("");
        this.setSzqx("");

        this.setZjlxdm("");
        this.setZjlxmc("");
        this.setZjhm("");
        this.setCqxmmc("");
        this.setGjrmc("");
    }

    public void setData(Cqxxb cqxxb) {
        this.setBcje(DataConvert.BigDecimal2String(cqxxb.getBcje(), 2, false));
        this.setBcmj(DataConvert.BigDecimal2String(cqxxb.getBcmj(), 2, false));
        this.setBcfwdz(cqxxb.getBcfwdz());
        this.setBclxdm(cqxxb.getBclxdm());
        this.setBclxmc(cqxxb.getBclxmc());
        this.setBcqrlxdm(cqxxb.getBcqrlxdm());
        this.setBcqrlxmc(cqxxb.getBcqrlxmc());
        this.setBcqrmc(cqxxb.getBcqrmc());
        this.setCqxxdz(cqxxb.getCqxxdz());
        this.setCqfw(cqxxb.getCqfw());
        this.setCqmj(DataConvert.BigDecimal2String(cqxxb.getCqmj(), 2, false));
        this.setCqrmc(cqxxb.getCqrmc());
        this.setCqxkzh(cqxxb.getCqxkzh());
        this.setSzqx(cqxxb.getQxdm());

        //this.setSzqx((String)szqxMap.get(szqx));
        this.setZjlxdm(cqxxb.getZjlxdm());
        this.setZjlxmc(cqxxb.getZjlxmc());
        this.setZjhm(cqxxb.getZjhm());
        this.setCqxmmc(cqxxb.getCqxmmc());
        //this.setLrrq(new Timestamp(System.currentTimeMillis()));
        this.setCqxxbh(cqxxb.getCqxxbh());
        this.setGjrmc(cqxxb.getGjrmc());

    }

    public String getBcfwdz() {
        return bcfwdz;
    }

    public String getBcje() {
        return bcje;
    }

    public String getBclxdm() {
        return bclxdm;
    }

    public String getBcmj() {
        return bcmj;
    }

    public String getBcqrlxdm() {
        return bcqrlxdm;
    }

    public String getBcqrmc() {
        return bcqrmc;
    }

    public String getCqfw() {
        return cqfw;
    }

    public String getCqmj() {
        return cqmj;
    }

    public String getCqrmc() {
        return cqrmc;
    }

    public String getCqxkzh() {
        return cqxkzh;
    }

    public String getCqxmmc() {
        return cqxmmc;
    }

    public String getCqxxdz() {
        return cqxxdz;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public ArrayList getBclx() {
        return bclx;
    }

    public String getBclxmc() {
        return bclxmc;
    }

    public ArrayList getBcqrlxList() {
        return bcqrlxList;
    }

    public String getBcqrlxmc() {
        return bcqrlxmc;
    }

    public ArrayList getZjlx() {
        return zjlx;
    }

    public String getZjlxmc() {
        return zjlxmc;
    }

    public HashMap getSzqxMap() {
        return szqxMap;
    }

    public String getSzqx() {
        return szqx;
    }

    public void setBcfwdz(String bcfwdz) {
        this.bcfwdz = bcfwdz;
    }

    public void setBclxdm(String bclxdm) {
        this.bclxdm = bclxdm;
    }

    public void setBcje(String bcje) {
        this.bcje = bcje;
    }

    public void setBcmj(String bcmj) {
        this.bcmj = bcmj;
    }

    public void setBcqrlxdm(String bcqrlxdm) {
        this.bcqrlxdm = bcqrlxdm;
    }

    public void setBcqrmc(String bcqrmc) {
        this.bcqrmc = bcqrmc;
    }

    public void setCqfw(String cqfw) {
        this.cqfw = cqfw;
    }

    public void setCqmj(String cqmj) {
        this.cqmj = cqmj;
    }

    public void setCqrmc(String cqrmc) {
        this.cqrmc = cqrmc;
    }

    public void setCqxkzh(String cqxkzh) {
        this.cqxkzh = cqxkzh;
    }

    public void setCqxmmc(String cqxmmc) {
        this.cqxmmc = cqxmmc;
    }

    public void setCqxxdz(String cqxxdz) {
        this.cqxxdz = cqxxdz;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setBclx(ArrayList bclx) {
        this.bclx = bclx;
    }

    public void setBclxmc(String bclxmc) {
        this.bclxmc = bclxmc;
    }

    public void setBcqrlxList(ArrayList bcqrlxList) {
        this.bcqrlxList = bcqrlxList;
    }

    public void setBcqrlxmc(String bcqrlxmc) {
        this.bcqrlxmc = bcqrlxmc;
    }

    public void setZjlx(ArrayList zjlx) {
        this.zjlx = zjlx;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    public void setSzqxMap(HashMap szqxMap) {
        this.szqxMap = szqxMap;
    }

    public void setSzqx(String szqx) {
        this.szqx = szqx;
    }

    /**
     * @return Returns the cqxxbh.
     */
    public String getCqxxbh() {
        return cqxxbh;
    }

    /**
     * @param cqxxbh The cqxxbh to set.
     */
    public void setCqxxbh(String cqxxbh) {
        this.cqxxbh = cqxxbh;
    }
}
