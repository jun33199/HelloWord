package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PlcxBo2;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 *
 * ˵������form�����ҳ����"��������(˰����Ա)"ģ��
 */
public class QueryPlcxForm2 extends QueryBaseForm {
    //�����ṩ����Ϣ
    private String sjtgz = "";

    //������
    private String pch = "";

    //����ʱ��
    private String drsj = "";

    //�ύʱ��
    private String tjsj = "";

    //����ʱ���
    private String drsjBegin = "";
    private String drsjEnd = "";

    //�ύʱ���
    private String tjsjBegin = "";
    private String tjsjEnd = "";


    public String getDrsj() {
        return drsj;
    }

    public String getPch() {
        return pch;
    }

    public String getSjtgz() {
        return sjtgz;
    }

    public String getTjsj() {
        return tjsj;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public String getTjsjBegin() {
        return tjsjBegin;
    }

    public String getTjsjEnd() {
        return tjsjEnd;
    }

    public void setDrsj(String drsj) {
        this.drsj = drsj;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setSjtgz(String sjtgz) {
        this.sjtgz = sjtgz;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }

    public void setTjsjBegin(String tjsjBegin) {
        this.tjsjBegin = tjsjBegin;
    }

    public void setTjsjEnd(String tjsjEnd) {
        this.tjsjEnd = tjsjEnd;
    }

    /**
     * ǰ̨ҳ��Ĳ�ѯ����
     * @return Object
     */
    public Object getData() {
        PlcxBo2 bo = new PlcxBo2();
        bo.setDrsj(drsj);
        bo.setPch(pch);
        bo.setSjtgz(sjtgz);
        bo.setDrsjBegin(drsjBegin);
        bo.setDrsjEnd(drsjEnd);
        bo.setTjsjBegin(tjsjBegin);
        bo.setTjsjEnd(tjsjEnd);
//        bo.setTjsj(tjsj);
//        bo.setSjtgz(sjtgz);
        return bo;
    }
}
