package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.BizLogic.vo.*;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;
import com.creationstar.bjtax.qsgl.util.*;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class JksForm extends QueryBaseForm {

    //�ɿ���Ĳ�ѯ����


   //��ͬ���  zzb add 20090831
   public String htbh="";


    /**
     * �ɿ�ƾ֤��
     */
    public String jkpzh = "";

    /**
     * �걨��ʼ����
     */
    public String sbqsrq = "";

    /**
     * �걨��ֹ����
     */
    public String sbjzrq = "";

    //��ӡҳ����Ҫ������
    public String szdm = ""; //˰������
    public String lsgx = ""; //������ϵ
    public String sklx = ""; //˰������
    public String tfrq = ""; //�����
    public String tfrqn = ""; //�������
    public String tfrqy = ""; //�������
    public String tfrqr = ""; //����� ��   
    public String zclx = ""; //ע������
    public String zsjg = ""; //���ջ���

    public String jkdwdm = ""; //�ɿλ����
    public String jkdwdh = ""; //�ɿλ�绰
    public String jkdwqc = ""; //�ɿλȫ��
    public String jkdwkhyh = ""; //�ɿλ��������
    public String jkdwzh = ""; ///�ɿλ�˺�

    public String yskmbm = ""; //Ԥ���Ŀ����
    public String yskmmc = ""; //Ԥ���Ŀ����
    public String yskmjc = ""; //Ԥ���Ŀ����
    public String gkjhh = ""; //���⽻����
    public String skgk = ""; //�տ����

    public String sksskssq = ""; //˰��������ʼʱ��
    public String skssjssq = ""; //˰��������ֹʱ��
    public String skxjrq = ""; //˰���޽�����

    public String jkjehj_nu = ""; //�ɿ���ϼ�����
    public String jkjehj = ""; //�ɿ���ϼ�

    public String jkdw = ""; //�ɿλ
    public String swjg = ""; //˰�����
    public String bz = ""; //��ע

    public String jkmxxmmc = ""; //��Ŀ����
    public String jkmxkssl = ""; //��˰����
    public String jkmxjsje = ""; //��˰���
    public String jkmxsjse = ""; //ʵ��˰��
    public String jkmxfcbl = ""; //�ֳɱ���
    
    public String jhh = ""; //������
    public String fdcwz = ""; //���ز�λ��

    public ArrayList mxList; //��ӡҳ����ʾ����Ŀ���ơ���˰������ϸ

    /**
     * �ɿ�������ɷ�ʽ
     */
    public String scfs;

    /**
     * ��ѯ���
     */
    public JksBo jksBo;

    //����Ϊ��¼�ǻ������ɽɿ����õ�������
    public Sbjkzb sbjkzb;

    public Sbjkmx sbjkmx;

    public void setJksData() {
        this.sbjkmx.setJsje(DataConvert.String2BigDecimal(this.getJkmxjsje()));
        this.sbjkmx.setSjse(DataConvert.String2BigDecimal(this.getJkmxsjse()));
        this.sbjkmx.setRkje(this.sbjkmx.getSjse());
        this.sbjkmx.setSkssksrq(DataConvert.String2Timestamp(this.getSksskssq()));
        this.sbjkmx.setSkssjsrq(DataConvert.String2Timestamp(this.getSkssjssq()));

        this.sbjkzb.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSkssksrq(this.sbjkmx.getSkssksrq());
        this.sbjkzb.setSkssjsrq(this.sbjkmx.getSkssjsrq());
        this.sbjkzb.setSjje(this.sbjkmx.getSjse());
        this.sbjkzb.setRkje(this.sbjkmx.getSjse());
    }

    public Object getData() {
        JksBo bo = new JksBo();
        bo.setJkpzh(this.jkpzh);
        bo.setSbqsrq(this.sbqsrq);
        bo.setSbjzrq(this.sbjzrq);
        bo.setHzfs(this.scfs);
        return bo;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getBz() {
        return bz;
    }

    public String getGkjhh() {
        return gkjhh;
    }

    public String getJkdw() {
        return jkdw;
    }

    public String getJkdwdh() {
        return jkdwdh;
    }

    public String getJkdwdm() {
        return jkdwdm;
    }

    public String getJkdwkhyh() {
        return jkdwkhyh;
    }

    public String getJkdwqc() {
        return jkdwqc;
    }

    public String getJkdwzh() {
        return jkdwzh;
    }

    public String getJkjehj() {
        return jkjehj;
    }

    public String getJkjehj_nu() {
        return jkjehj_nu;
    }

    public String getJkmxjsje() {
        return jkmxjsje;
    }

    public String getJkmxkssl() {
        return jkmxkssl;
    }

    public String getJkmxsjse() {
        return jkmxsjse;
    }

    public String getJkmxxmmc() {
        return jkmxxmmc;
    }

    public String getJkmxfcbl() {
        return jkmxfcbl;
    }
    
    public String getLsgx() {
        return lsgx;
    }

    public String getSkgk() {
        return skgk;
    }

    public String getSklx() {
        return sklx;
    }

    public String getSkxjrq() {
        return skxjrq;
    }

    public String getSwjg() {
        return swjg;
    }

    public String getSzdm() {
        return szdm;
    }

    public String getTfrq() {
        return tfrq;
    }

    public String getTfrqn() {
        return tfrqn;
    }
    
    public String getTfrqy() {
        return tfrqy;
    }
    
    public String getTfrqr() {
        return tfrqr;
    }
    
    public String getYskmbm() {
        return yskmbm;
    }

    public String getYskmjc() {
        return yskmjc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getZclx() {
        return zclx;
    }

    public String getZsjg() {
        return zsjg;
    }

    public String getSkssjssq() {
        return skssjssq;
    }

    public String getSksskssq() {
        return sksskssq;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getSbjzrq() {
        return sbjzrq;
    }

    public String getSbqsrq() {
        return sbqsrq;
    }

    public JksBo getJksBo() {
        return jksBo;
    }

    public String getJhh() {
        return jhh;
    }

    public String getScfs() {
        return scfs;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String getHtbh()
    {
        return htbh;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setGkjhh(String gkjhh) {
        this.gkjhh = gkjhh;
    }

    public void setJkdw(String jkdw) {
        this.jkdw = jkdw;
    }

    public void setJkdwdh(String jkdwdh) {
        this.jkdwdh = jkdwdh;
    }

    public void setJkdwdm(String jkdwdm) {
        this.jkdwdm = jkdwdm;
    }

    public void setJkdwkhyh(String jkdwkhyh) {
        this.jkdwkhyh = jkdwkhyh;
    }

    public void setJkdwqc(String jkdwqc) {
        this.jkdwqc = jkdwqc;
    }

    public void setJkdwzh(String jkdwzh) {
        this.jkdwzh = jkdwzh;
    }

    public void setJkjehj(String jkjehj) {
        this.jkjehj = jkjehj;
    }

    public void setJkjehj_nu(String jkjehj_nu) {
        this.jkjehj_nu = jkjehj_nu;
    }

    public void setJkmxjsje(String jkmxjsje) {
        this.jkmxjsje = jkmxjsje;
    }

    public void setJkmxkssl(String jkmxkssl) {
        this.jkmxkssl = jkmxkssl;
    }

    public void setJkmxsjse(String jkmxsjse) {
        this.jkmxsjse = jkmxsjse;
    }

    public void setJkmxxmmc(String jkmxxmmc) {
        this.jkmxxmmc = jkmxxmmc;
    }

    public void setJkmxfcbl(String jkmxfcbl) {
        this.jkmxfcbl = jkmxfcbl;
    }
    
    public void setLsgx(String lsgx) {
        this.lsgx = lsgx;
    }

    public void setSkgk(String skgk) {
        this.skgk = skgk;
    }

    public void setSklx(String sklx) {
        this.sklx = sklx;
    }

    public void setSkxjrq(String skxjrq) {
        this.skxjrq = skxjrq;
    }

    public void setSwjg(String swjg) {
        this.swjg = swjg;
    }

    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    public void setTfrqn(String tfrqn) {
        this.tfrqn = tfrqn;
    }
    
    public void setTfrqy(String tfrqy) {
        this.tfrqy = tfrqy;
    }
    
    public void setTfrqr(String tfrqr) {
        this.tfrqr = tfrqr;
    }
    
    public void setYskmbm(String yskmbm) {
        this.yskmbm = yskmbm;
    }

    public void setYskmjc(String yskmjc) {
        this.yskmjc = yskmjc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setZclx(String zclx) {
        this.zclx = zclx;
    }

    public void setZsjg(String zsjg) {
        this.zsjg = zsjg;
    }

    public void setSkssjssq(String skssjssq) {
        this.skssjssq = skssjssq;
    }

    public void setSksskssq(String sksskssq) {
        this.sksskssq = sksskssq;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSbjzrq(String sbjzrq) {
        this.sbjzrq = sbjzrq;
    }

    public void setSbqsrq(String sbqsrq) {
        this.sbqsrq = sbqsrq;
    }

    public void setJksBo(JksBo jksBo) {
        this.jksBo = jksBo;
    }

    public void setJhh(String jhh) {
        this.jhh = jhh;
    }

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

    private void jbInit() throws Exception {
    }
}
