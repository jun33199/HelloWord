package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;

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
 */
public class BlJksForm extends QueryBaseForm {
    //��ѯ��Ҫ��¼�Ľɿ���Ĳ�ѯ����
    private String jsje;
    private String sjse;

    //��Ҫ��¼�Ľɿ���ARRAYLIST(ÿ��Ԫ��ΪQueryBlJksBo)
    private ArrayList jksSbList;
    private QueryBlJksBo removeBo;

    //view�ɿ���ҳ��ÿ��Ҫ��ʾ������bo
    public JksBo jksBo;
    public String type; //�˽ɿ�������ͣ�1������ͨ��2������ʵ�
    public String zbxh; //������ţ����ʵĽɿ���Ҫ�õ�
    public String sklxdm; //˰�����ʹ���

    //����Ϊ�����ɿ������õĲ�ѯ����,typeҲ��Ҫʹ��
    public String scfs; //�ɿ������ɷ�ʽ
    public String jkpzh; //�ɿ�ƾ֤��
    public String re_jkpzh; //ȷ�Ͻɿ�ƾ֤��
    //��ѯ���bo
    public QueryBlJksBo resultBo;

    //��ȡ��ѯ����
    public QueryBlJksBo getBo() {
        QueryBlJksBo bo = new QueryBlJksBo();
        bo.setJkpzh(this.jkpzh);
        bo.setSjly(this.scfs);
        bo.setType(this.getType());
        return bo;
    }


    public ArrayList getJksSbList() {
        return jksSbList;
    }

    public JksBo getJksBo() {
        return jksBo;
    }

    public String getType() {
        return type;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getRe_jkpzh() {
        return re_jkpzh;
    }

    public String getScfs() {
        return scfs;
    }

    public String getZbxh() {
        return zbxh;
    }

    public QueryBlJksBo getResultBo() {
        return resultBo;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public String getJsje() {
        return jsje;
    }

    public String getSjse() {
        return sjse;
    }

    public QueryBlJksBo getRemoveBo() {
        return removeBo;
    }

    public void setJksSbList(ArrayList jksSbList) {
        this.jksSbList = jksSbList;
    }

    public void setJksBo(JksBo jksBo) {
        this.jksBo = jksBo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setRe_jkpzh(String re_jkpzh) {
        this.re_jkpzh = re_jkpzh;
    }

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setResultBo(QueryBlJksBo resultBo) {
        this.resultBo = resultBo;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setRemoveBo(QueryBlJksBo removeBo) {
        this.removeBo = removeBo;
    }

}
