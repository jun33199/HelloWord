package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;

/**
 * ��ʾĳ�������������ϸ��Ϣ
 *
 * @author not attributable
 * @version 1.0
 */
public class PlslForm extends BaseForm {
    //��ʾĳ���걨��detail��Ϣ
    //plsbBO����
    private PldrBo bo;
    //������Ϣ
    private Grxx grxx;
    //�Ǹ�����Ϣ
    private Fgrxx fgrxx;
    //���������Ϣ
    private Spjgxx spjgxx;
    //���ط�����Ϣ
    private Tufwxx tufwxx;
    //��Ǩ��Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblcq����)
    private ArrayList cqxxList;
    //����ס����Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblgyzf����)
    private ArrayList gyzfxxList;
    //���ݽ�����Ϣ(Arryalist�е�ÿ��Ԫ�ض���PldrBo����,����û�в�Ǩ�͹���)
    private ArrayList fwjhxxList;
    //���˷Ǹ���(0,1)
    private String person;
    //���޲�Ǩ
    private String cq;
    //���޹���ס��
    private String gf;
    //���޷��ݽ���
    private String fj;
    //������������
    private String sp;
    //�Ƿ������ط��ݲ��������
    private String tdbm;
    //�Ƿ�����ɾ��������¼ 0--���� 1--����
    private String del = "0";
    //�쿴��ϸҳ�淵���Ǹ�ҳ��ı�ʾ
    private String back = "dr";
    //˰�����
    private String setz;

    //���˵Ķ��Ȩ��
    public ArrayList grxxList = new ArrayList();
    //�Ǹ��˵Ķ��Ȩ��
    public ArrayList fgrxxList = new ArrayList();


    public void setGrxxList(ArrayList grxxList) {
        this.grxxList = grxxList;
    }

    public ArrayList getGrxxList() {
        return grxxList;
    }

    public void setFgrxxList(ArrayList fgrxxList) {
        this.fgrxxList = fgrxxList;
    }

    public ArrayList getFgrxxList() {
        return fgrxxList;
    }

    public String getSetz() {
        return setz;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public PldrBo getBo() {
        return bo;
    }


    public ArrayList getCqxxList() {
        return cqxxList;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public ArrayList getFwjhxxList() {
        return fwjhxxList;
    }

    public Grxx getGrxx() {
        return grxx;
    }

    public ArrayList getGyzfxxList() {
        return gyzfxxList;
    }


    public Spjgxx getSpjgxx() {
        return spjgxx;
    }

    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getCq() {
        return cq;
    }

    public String getFj() {
        return fj;
    }

    public String getGf() {
        return gf;
    }

    public String getSp() {
        return sp;
    }

    public String getPerson() {
        return person;
    }

    public String getTdbm() {
        return tdbm;
    }


    public String getDel() {
        return del;
    }

    public String getBack() {
        return back;
    }


    public void setBo(PldrBo bo) {
        this.bo = bo;
    }


    public void setCqxxList(ArrayList cqxxList) {
        this.cqxxList = cqxxList;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setFwjhxxList(ArrayList fwjhxxList) {
        this.fwjhxxList = fwjhxxList;
    }

    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    public void setGyzfxxList(ArrayList gyzfxxList) {
        this.gyzfxxList = gyzfxxList;
    }


    public void setSpjgxx(Spjgxx spjgxx) {
        this.spjgxx = spjgxx;
    }

    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setCq(String cq) {
        this.cq = cq;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setTdbm(String tdbm) {
        this.tdbm = tdbm;
    }


    public void setDel(String del) {
        this.del = del;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
