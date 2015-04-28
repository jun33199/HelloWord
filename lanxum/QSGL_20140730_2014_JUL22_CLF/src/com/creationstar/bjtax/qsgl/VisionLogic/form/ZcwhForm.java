package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: ����ά��Form </p>
 *
 * <p>Description: ����ά����Form����һ����һ����Ӧ��̨�ĵ����Form </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class ZcwhForm extends BaseForm {
    public ZcwhForm() {
    }

    public String getBz() {
        return bz;
    }

    public String getJzsj() {
        return jzsj;
    }

    public String getQssj() {
        return qssj;
    }

    public String getZbdm() {
        return zbdm;
    }

    public String getZbmc() {
        return zbmc;
    }

    public String getZbz() {
        return zbz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public void setQssj(String qssj) {
        this.qssj = qssj;
    }

    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public void setZbz(String zbz) {
        this.zbz = zbz;
    }

    /**
     * ������Zcwhֵ����ĸ���ֵ��ֵ��Form�ĸ���
     * @param zcwh Zcwh
     */
    public void setData(Zcwh zcwh) {
        zbdm = zcwh.getZbdm();
        zbmc = zcwh.getZbmc();
        zbz = zcwh.getZbz();
        qssj = String.valueOf(zcwh.getSxqsrq());
        jzsj = String.valueOf(zcwh.getSxjzrq());
        bz = zcwh.getBz();
    }

    /**
     * ������ά����FORM��,��ֵ����Ӧ��VO��,�Թ���̨����
     * @return Object
     */
    public Object getData() {
        Zcwh zw = new Zcwh();
        zw.setZbdm(this.getZbdm());
        zw.setZbmc(this.getZbmc());
        zw.setZbz(this.getZbz());
        zw.setBz(this.getBz());
        zw.setLrr("zhaobo");

        try {
            zw.setSxqsrq(DataConvert.String2Timestamp(this.getQssj()));
            zw.setSxjzrq(DataConvert.String2Timestamp(this.getJzsj()));
            zw.setLrrq(new Timestamp(System.currentTimeMillis()));
        } catch (Exception ex) {
            Debug.out(ex.getMessage());
        }

        return zw;
    }

    /**
     * ָ�����
     */
    private String zbdm;

    /**
     * ָ������
     */
    private String zbmc;

    /**
     * ָ��ֵ
     */
    private String zbz;

    /**
     * ��Ч��ʼʱ��
     */
    private String qssj;

    /**
     * ��Ч��ֹʱ��
     */
    private String jzsj;

    /**
     * ��ע
     */
    private String bz;
}
