package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;
import com.creationstar.bjtax.qsgl.util.*;
import com.ttsoft.common.util.*;

public class BzqsForm extends BaseForm {
    public BzqsForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * ��������
     */
    public String cjrq; /**
    * �������ز��������
    */
   public String fwtdbmslh;

    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ίҵ�������ԡ�
     */
    private String jwywbh = "";

    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ͬ������ԡ�
     */
    private String htbh = "";


    /**
     * ״̬��ʾ
     */
    public String ztbs;

    /**
     * �û���ʾ
     */
    public String yhbs;


    /**
     * ��ϵ�绰
     */
    public String lxdh;

    /**
     * ����Ȩ��
     */
    public String zcqr;

    /**
     * ��Ȩ��ҳ������
     */
    public String cqrJs = Constants.CQRJS_INIT;


    /**
     * ����
     * @return ArrayList of Grxx
     */
    public List nsrList = new ArrayList();

    /**
     * ����
     */
    public ArrayList gjdqList = new ArrayList();
    /**
     * ���֤������
     */
    public ArrayList sfzjlxList = new ArrayList();

    /**
     * ���ز���Ŀ����
     */
    public String fdcxmmc;
    /**
     * ��Լǩ��ʱ��
     */
    public String hyqdsj;
    /**
     * ����
     */
    public ArrayList flList = new ArrayList();
    public String fl;
    public String flmc;
    /**
     * ���ء�����Ȩ��ת������
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    public String tdfwqszylx;
    public String tdfwqszylxmc;
    /**
     * �������
     */
    public ArrayList fwlbList = new ArrayList();
    public String fwlb = "03";
    public String fwlbmc;
    /**
     * ���ء����������ַ
     */
    public String tdfwzldz;
    /**
     * ���ء�����Ȩ��ת�����
     */
    public String tdfwqszymj;
    /**
     * ���ݽ������
     */
    public String fwjzmj;
    /**
     * �ɽ��۸� Ԫ(�����)
     */
    public String cjjgyrmb;
    /**
     * �����۸�
     */
    public String pgjg;
    /**
     * �ɽ��۸�  Ԫ(���)
     */
    public String cjjgywb;
    /**
     * ����
     */
    public ArrayList bzList = new ArrayList();
    public String bz = "USD";
    public String bzmc;

    /**
     * ���ؼ���
     */
    private String tdjc;

    /**
     * �ݻ���
     */
    private String rjl;

    /**
     * ����
     */
    public String hn;
    /**
     * �ۺ�Ԫ(�����)
     */
    public String zhyrmb;
    /**
     * ��˰�ˣ������ˣ�ǩ��
     */
    public String nsrqz;
    /**
     * ��ע
     */
    public String beizhu;

    /**
     * �Ǹ��˵���Ϣ
     */


    /**
     * ��˰������
     */
    public ArrayList nsrlxList;
    public String nsrlxmc;


    /**
     * ��������
     */
    public ArrayList khyhList = new ArrayList();
    public String khyhdm;
    public String khyhmc;

    /**
     * �����˺�
     */
    public String yhzh;

    /**
     * ��ϵ������
     */
    public String lxrxm;

    /**
     * ɨ����˰������
     */
    public String smnsrmc;

    public String getBeizhu() {
        return beizhu;
    }

    public String getBz() {
        return bz;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getCjjgyrmb() {
        return cjjgyrmb;
    }

    public String getCjjgywb() {
        return cjjgywb;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getFl() {
        return fl;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public String getFwjzmj() {
        return fwjzmj;
    }

    public String getFwlb() {
        return fwlb;
    }

    public ArrayList getFwlbList() {
        return fwlbList;
    }

    public String getFwtdbmslh() {
        return fwtdbmslh;
    }

    public String getHn() {
        return hn;
    }

    public String getHyqdsj() {
        return hyqdsj;
    }

    public String getKhyhdm() {
        return khyhdm;
    }

    public ArrayList getKhyhList() {
        return khyhList;
    }

    public String getLxdh() {
        return lxdh;
    }

    public String getLxrxm() {
        return lxrxm;
    }


    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public String getNsrqz() {
        return nsrqz;
    }

    public String getPgjg() {
        return pgjg;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getYhzh() {
        return yhzh;
    }

    public String getZhyrmb() {
        return zhyrmb;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getKhyhmc() {
        return khyhmc;
    }

    public String getYhbs() {
        return yhbs;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFwlbmc() {
        return fwlbmc;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }


    public String getCjrq() {
        return cjrq;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getRjl() {
        return rjl;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setCjjgyrmb(String cjjgyrmb) {
        this.cjjgyrmb = cjjgyrmb;
    }

    public void setCjjgywb(String cjjgywb) {
        this.cjjgywb = cjjgywb;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    public void setFwlb(String fwlb) {
        this.fwlb = fwlb;
    }

    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    public void setFwtdbmslh(String fwtdbmslh) {
        this.fwtdbmslh = fwtdbmslh;
    }

    public void setHn(String hn) {
        this.hn = hn;
    }

    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    public void setKhyhList(ArrayList khyhList) {
        this.khyhList = khyhList;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }


    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setNsrqz(String nsrqz) {
        this.nsrqz = nsrqz;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }


    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFwlbmc(String fwlbmc) {
        this.fwlbmc = fwlbmc;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    } //��ȡbo���󣬱���ʱ�����̨

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public Object getData(List l) {
        BzqsBo bo = (BzqsBo) getData();
        //��ÿͻ��˵�����--
        this.setCqrJs(ActionUtil.displayMNsrDS(l));

        bo.setNsrList(l);
        this.setNsrList(l);
        return bo;
    }

    public Object getData() {
        Debug.out(nsrmc);
        BzqsBo bo = new BzqsBo();
        //���ͻ��˽�ίҵ���Ų�������bo�����С��޸��ˣ�����ϼ20081125
        bo.setJwywbh(this.jwywbh);
        //���ͻ��˺�ͬ��Ų�������bo�����С��޸��ˣ�����ϼ20081125
        bo.setHtbh(this.htbh);
        bo.bz = bz;
        bo.bzmc = bzmc;
        bo.cjjgyrmb = cjjgyrmb;
        bo.cjjgywb = cjjgywb;
        bo.fdcxmmc = fdcxmmc;
        bo.fl = fl;
        bo.flmc = flmc;
        bo.fwjzmj = fwjzmj;
        bo.fwlb = fwlb;
        bo.fwlbmc = fwlbmc;
        bo.hn = hn;
        bo.hyqdsj = hyqdsj;
        bo.lxdh = lxdh;
        bo.nsrmc = nsrmc;
        bo.nsrqz = nsrqz;
        bo.pgjg = pgjg;
        bo.tdfwqszylx = tdfwqszylx;
        bo.tdfwqszylxmc = tdfwqszylxmc;
        bo.tdfwqszymj = tdfwqszymj;
        bo.tdfwzldz = tdfwzldz;
        bo.zhyrmb = zhyrmb;
        bo.beizhu = beizhu;
        bo.fwtdbmslh = fwtdbmslh;
        bo.nsrlx = nsrlx;
        bo.nsrlxmc = nsrlxmc;
        bo.ztbs = ztbs; //ǰ̨ҳ���ϻ��
        bo.yhbs = yhbs;
        bo.rjl = rjl;
        bo.tdjc = tdjc;

        //����Ϊ������Ϣ


        //����Ϊ�Ǹ�����Ϣ

        bo.nsrlx = nsrlx;
        bo.nsrlxmc = nsrlxmc;
        bo.khyhdm = khyhdm;
        if (khyhmc != null && !khyhmc.equals("")) {
            String sz[] = DataConvert.splitYh(khyhmc);
            bo.khyhmc = sz[0];
            bo.yhzh = sz[1];
        } else {
            bo.khyhmc = "";
            bo.yhzh = "";
        }

        bo.lxrxm = lxrxm;
        bo.flmc = flmc;
        bo.jsjdm = jsjdm;
        bo.sbbh = sbbh;
        return bo;
    }

    /**
     * ���form
     */
    public void clear() {

        bz = "USD";
        cjjgyrmb = "";
        cjjgywb = "";
        fdcxmmc = "";
        nsrmc = "";
        fl = "01";
        fwjzmj = "";
        fwlb = "03";
        hn = "";
        hyqdsj = "";
        lxdh = "";
        nsrmc = "";
        nsrqz = "";
        pgjg = "";
        tdfwqszylx = "01";
        tdfwqszymj = "";
        tdfwzldz = "";
        zhyrmb = "";
        beizhu = "";
        fwtdbmslh = "";
        //����ʱ�����ίҵ�����ֶ�
        //modify by fujx,20081126
        jwywbh = "";

        //����ʱ�����ͬ����ֶ�
        //modify by fujx,20081126
        htbh = "";

        nsrlx = "01";
        ztbs = "";
        cqrJs = Constants.CQRJS_INIT;
        nsrList = new ArrayList();
        //�Ǹ��˲���
        jsjdm = "";
        khyhdm = "";
        khyhmc = "";
        yhzh = "";
        lxrxm = "";
        flmc = "";
        khyhList = new ArrayList();
        jsjdm = "";
        sbbh = "";
    }

    private void jbInit() throws Exception {
    }


    /**
     * @return Returns the cqrJs.
     */
    public String getCqrJs() {
        return cqrJs;
    }

    /**
     * @param cqrJs The cqrJs to set.
     */
    public void setCqrJs(String cqrJs) {
        this.cqrJs = cqrJs;
    }

    /**
     * @return Returns the gjdqList.
     */
    public ArrayList getGjdqList() {
        return gjdqList;
    }

    /**
     * @param gjdqList The gjdqList to set.
     */
    public void setGjdqList(ArrayList gjdqList) {
        this.gjdqList = gjdqList;
    }

    /**
     * @return Returns the zcqr.
     */
    public String getZcqr() {
        return zcqr;
    }

    /**
     * @param zcqr The zcqr to set.
     */
    public void setZcqr(String zcqr) {
        this.zcqr = zcqr;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getSmnsrmc()
    {
        return smnsrmc;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setSmnsrmc(String smnsrmc)
    {
        this.smnsrmc = smnsrmc;
    }
}
