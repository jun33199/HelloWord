package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * �����������¼��form
 * @author not attributable
 * @version 1.0
 */

public class PzlrForm extends BaseForm {
    //����id
    private String id;
    //ɾ����ť��־��trueΪ��ʾ��falseΪ����
    private String del_flag;
    //��˰����֯��������
    private String zzjgdm;
    //��˰����֯��������
    private String zzjgmc;
    //���ز���Ŀ����
    private String fdcxmmc;
    //���ؼ���
    private String tdjb;
    //�ݻ���
    private String rjl;
    //�������
    private String jzmj;
    //ƽ�����׼۸�
    private String pjjyjg;
    /*added by gaoyh to 20141016*/
    //����ÿ�׼۸�
    private String fwmtjg;
	//ʹ����ʼ����
    private String qsrq;
    //ʹ�ý�������
    private String jzrq;
    //�Ƿ�ɲ�����Ǩ��Ϣ
    private String czcq;
    //�Ƿ�ɲ����ѹ�����ס��������Ϣ
    private String czcsxx;
    //�Ƿ�ɲ������ݽ�����Ϣ
    private String czfwjyxx;
    //������Ա
    private String czry;
    //˰�������֯��������
    private String swjgzzjgdm;
    //˰�������֯��������
    private String swjgzzjgmc;
    /**
     * ���ؼ����б�
     */
    private ArrayList tdjcList = new ArrayList();


    public String getCzcq() {
        return czcq;
    }

    public String getCzcsxx() {
        return czcsxx;
    }

    public String getCzfwjyxx() {
        return czfwjyxx;
    }

    public String getCzry() {
        return czry;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getJzmj() {
        return jzmj;
    }

    public String getJzrq() {
        return jzrq;
    }

    public String getPjjyjg() {
        return pjjyjg;
    }

    public String getQsrq() {
        return qsrq;
    }

    public String getRjl() {
        return rjl;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getTdjb() {
        return tdjb;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public String getZzjgmc() {
        return zzjgmc;
    }

    public String getId() {
        return id;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public ArrayList getTdjcList() {
        return tdjcList;
    }

    public void setCzcq(String czcq) {
        this.czcq = czcq;
    }

    public void setCzcsxx(String czcsxx) {
        this.czcsxx = czcsxx;
    }

    public void setCzfwjyxx(String czfwjyxx) {
        this.czfwjyxx = czfwjyxx;
    }

    public void setCzry(String czry) {
        this.czry = czry;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public void setPjjyjg(String pjjyjg) {
        this.pjjyjg = pjjyjg;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setTdjb(String tdjb) {
        this.tdjb = tdjb;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public void setZzjgmc(String zzjgmc) {
        this.zzjgmc = zzjgmc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public void setTdjcList(ArrayList tdjcList) {
        this.tdjcList = tdjcList;
    }
    
    public String getFwmtjg() {
		return fwmtjg;
	}

	public void setFwmtjg(String fwmtjg) {
		this.fwmtjg = fwmtjg;
	}
}
