package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.ttsoft.common.util.QueryCache;

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
public class PldyForm2 extends QueryBaseForm {
    // ���κ�
    private String pch = "";
    // �����ṩ�߼��������
    private String tgzjsjdm = "";
    // �����ṩ������
    private String tgzgjmc = "";
    //����ʱ���
    private String drsjBegin = "";
    private String drsjEnd = "";
    // ��ѯ����
    private Object queryObj = new Object();
    // ��ѯ������Ϣ��������������ʱ����formʹ��
    private QueryCache tmpQyeryCache = new QueryCache();

    // ��ѯĳ���ν����������ӡʹ��
    ArrayList resultList = new ArrayList();

    public String getPch() {
        return pch;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setTgzgjmc(String tgzgjmc) {
        this.tgzgjmc = tgzgjmc;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setTmpQyeryCache(QueryCache tmpQyeryCache) {
        this.tmpQyeryCache = tmpQyeryCache;
    }

    public void setQueryObj(Object obj) {
        this.queryObj = obj;
    }

    /**
     * ǰ̨ҳ��Ĳ�ѯ����
     * @return Object
     */
    public Object getData() {
        PldyBo2 bo = new PldyBo2();
        bo.setPch(pch);
        bo.setTgzgjmc(tgzgjmc);
        bo.setTgzjsjdm(tgzjsjdm);
        bo.setDrsjBegin(drsjBegin);
        bo.setDrsjEnd(drsjEnd);
        return bo;
    }

    public String getTgzgjmc() {
        return tgzgjmc;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public ArrayList getResultList() {
        return resultList;
    }

    public QueryCache getTmpQyeryCache() {
        return tmpQyeryCache;
    }

    public Object getQueryObj() {
        return queryObj;
    }
}
