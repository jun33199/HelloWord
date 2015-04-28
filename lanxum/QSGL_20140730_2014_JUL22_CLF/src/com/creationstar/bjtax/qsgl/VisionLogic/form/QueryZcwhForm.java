package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: ����ά���Ĳ�ѯForm </p>
 *
 * <p>Description: �̳�QueryBaseForm�����߲�ѯForm </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class QueryZcwhForm extends QueryBaseForm {
    public QueryZcwhForm() {
    }

    public String getZbdm() {
        return zbdm;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    /**
     * ����ú���
     */
    public void clear() {
        this.setZbdm("");
        this.setZbmc("");
    }

    /**
     * ʵ�ָ��ඨ��ķ������� QueryForm�е����ݷ��뵽��Ӧ�� BO ���У�����ɿ��
     * @return Object
     */
    public Object getData() {
        Debug.out("into QueryZcwhForm....");
        Zcwh zw = new Zcwh();
        zw.setZbdm(this.getZbdm());
        zw.setZbmc(this.getZbmc());
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
}
