package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: 政策维护Form </p>
 *
 * <p>Description: 政策维护的Form，是一个对一个对应后台的单表的Form </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 赵博
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
     * 将参数Zcwh值对象的各域值赋值给Form的各域。
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
     * 从政策维护的FORM中,赋值到相应的VO中,以供后台调用
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
     * 指标代码
     */
    private String zbdm;

    /**
     * 指标名称
     */
    private String zbmc;

    /**
     * 指标值
     */
    private String zbz;

    /**
     * 有效起始时间
     */
    private String qssj;

    /**
     * 有效截止时间
     */
    private String jzsj;

    /**
     * 备注
     */
    private String bz;
}
