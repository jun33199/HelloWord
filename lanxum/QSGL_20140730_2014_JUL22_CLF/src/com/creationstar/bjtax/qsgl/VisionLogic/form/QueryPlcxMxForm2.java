package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo2;

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
public class QueryPlcxMxForm2 extends QueryBaseForm {
    //���κ�
    private String pch;

    //״̬
    private String zt;

    public String getPch() {
        return pch;
    }

    public String getZt() {
        return zt;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    /**
     * ǰ̨ҳ��Ĳ�ѯ����
     * @return Object
     */
    public Object getData() {
        PlcxMxBo2 bo = new PlcxMxBo2();
        bo.setPch(pch);
        bo.setZt(zt);
        return bo;
    }
}
