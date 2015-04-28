package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.HzJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;

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
public class hzWsz2JksForm extends QueryBaseForm {

    //���ܷ�ʽ����
    public String hzfsdm;

    //���ܷ���ʽ����
    public String hzfsmc;

    //��˰��ʽ����
    public String jsfsdm;

    //��˰��ʽ����
    public String jsfsmc;

    //��������
    public String jzrq;

    //��ʼ����
    public String qsrq;

    //�ɿ�ƾ֤��
    public String jkpzh;

    //��˰֤��
    public String wszh;

    //���ɽɿ���ķ�ʽ�����ڳ����ɿ���ʱ��λ��wszProcessor����jksProcessor
    public String scfs;

    //��˰��ʽ�б�
    public ArrayList jsfsList;


    //�������ڻ������ɵĽɿ���Ĵ�ӡҳ����ʾ

    public String szdm = ""; //˰������
    public String lsgx = ""; //������ϵ
    public String sklx = ""; //˰������
    public String tfrq = ""; //�����
    public String tfrqn = ""; //�������
    public String tfrqy = ""; //�������
    public String tfrqr = ""; //�������
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

    public String jhh = ""; //���⽻����

    public ArrayList mxList; //��ӡҳ����ʾ����Ŀ���ơ���˰������ϸ

    //����Ϊ��¼�õ�������
    public String[] mxxmdm; //˰��˰Ŀ����
    public String[] mxxmmc; //˰��˰Ŀ����
    public String[] mxkssl; //��˰����
    public String[] mxjsje; //��˰���
    public String[] mxsjse; //ʵ��˰��

    public ArrayList szsmList; //˰��˰Ŀlist��������ϸ�б��е���Ŀ����������

    public String fenpiao; //�Ƿ��з�Ʊ�������0Ϊû�У�1Ϊ��

    public String[] jkpzhao; //��Ʊ�Ľɿ�ƾ֤��

    private Sbjkzb sbjkzb;

    public String drpch; //�������κ�

    public String dzjktfrq = ""; //���⽻����
    public String sbbh = ""; //���⽻����


    public String getDrpch() {
        return drpch;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public Object getData() {
        WszBo bo = new WszBo();
        bo.setHzfs(this.hzfsdm);
        bo.setHzfsmc(this.hzfsmc);
        bo.setHzjzrq(this.jzrq);
        bo.setHzqsrq(this.qsrq);
        bo.setJsfs(this.jsfsdm);
        bo.setJsfsmc(this.jsfsmc);
        bo.setWszh(this.wszh);
        bo.setJsjdm(this.jsjdm);
        bo.setJkpzh(this.jkpzh);
        bo.setDrpch(this.drpch);
        return bo;
    }

    public Object getWszBo() {
        WszBo bo = new WszBo();
        bo.setDrpch(this.drpch);
        return bo;
    }

    /**
     * ��������ʱ����Ҫ
     * @return JksBo
     */
    public JksBo getJksBo() {
        JksBo bo = new JksBo();
        bo.setJkpzh(this.getJkpzh());
        bo.setJsjdm(this.getJsjdm());
        bo.setHzfs(this.getScfs());
        return bo;
    }

    /**
     * ��¼�������ɽɿ��飬���ɿ��������Ϣ��ֵ
     * @param sbjkzb Sbjkzb
     */
    public void setJksInfo(Sbjkzb sbjkzb) {
        this.setSkgk(sbjkzb.getGkzzjgmc());
        this.setSklx(sbjkzb.getSklxmc());

        this.setSwjg(sbjkzb.getSwjgzzjgmc());
        this.setSzdm(sbjkzb.getSzmc());
        this.setYskmbm(sbjkzb.getYskmdm());
        this.setYskmjc(sbjkzb.getYsjcmc());
        this.setYskmmc(sbjkzb.getYskmmc());
        this.setZclx(sbjkzb.getDjzclxmc());
        this.setJkdw(sbjkzb.getNsrmc()); //��˰�˼��������
        this.setJkdwdh(sbjkzb.getJydzlxdm());
        this.setJkdwkhyh(sbjkzb.getYhmc());
        this.setJkdwqc(sbjkzb.getNsrmc());
        this.setJkdwdm(sbjkzb.getJsjdm());
        this.setJkdwzh(sbjkzb.getZh());

        this.setLsgx(sbjkzb.getLsgxmc());
        this.setZsjg(sbjkzb.getZsswjgzzjgmc());
        this.setJhh(sbjkzb.getGkjhh());
    }

    /**
     * ��¼�������ɵĽɿ���ʱ��form�е�������֯��Ҫ����̨���ݵ�bo
     * @return HzJksBo
     */
    public HzJksBo getHzJksBo() {
        HzJksBo bo = new HzJksBo();

        //��form�б����sbjkzb vo�е����ݲ�ȫ���Ͳ�һ��lrrq�������processor���渳ֵΪ��ǰʱ�䣩
        this.sbjkzb.setJkpzh(this.getJkpzh());
        this.sbjkzb.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSbrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.sbjkzb.setSkssksrq(DataConvert.String2Timestamp(this.getSksskssq()));
        this.sbjkzb.setSkssjsrq(DataConvert.String2Timestamp(this.getSkssjssq()));
        this.sbjkzb.setSjje(DataConvert.String2BigDecimal(this.getJkjehj()));
        this.sbjkzb.setRkje(DataConvert.String2BigDecimal(this.getJkjehj()));
        this.sbjkzb.setBz(this.getBz());

        //��sbjkmx�е�˰�����ơ����룬��˰��������˰��ʵ��˰�ֵ��bo�е���Ӧ����
        bo.setMxjsje(this.getMxjsje());
        bo.setMxkssl(this.getMxkssl());
        bo.setMxsjse(this.getMxsjse());
        bo.setMxxmmc(this.getMxxmmc());
        bo.setMxxmdm(this.getMxxmdm());

        //�����ܷ�ʽ���ơ������Լ��Ƿ��з�Ʊ�������ֵ��bo�е���Ӧ����
        bo.setHzfsdm(this.getHzfsdm());
        bo.setHzfsmc(this.getHzfsmc());
        bo.setFp(this.getFenpiao());

        //�����ֵ��sbjkzb vo����bo
        bo.setSbjkzb(this.getSbjkzb());

        return bo;
    }

    public String getHzfsdm() {
        return hzfsdm;
    }

    public void setHzfsdm(String hzfsdm) {
        this.hzfsdm = hzfsdm;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    public void setJsfsList(ArrayList jsfsList) {
        this.jsfsList = jsfsList;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
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

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSkgk(String skgk) {
        this.skgk = skgk;
    }

    public void setSklx(String sklx) {
        this.sklx = sklx;
    }

    public void setSkssjssq(String skssjssq) {
        this.skssjssq = skssjssq;
    }

    public void setSksskssq(String sksskssq) {
        this.sksskssq = sksskssq;
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

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setJhh(String jhh) {
        this.jhh = jhh;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setMxjsje(String[] mxjsje) {
        this.mxjsje = mxjsje;
    }

    public void setMxkssl(String[] mxkssl) {
        this.mxkssl = mxkssl;
    }

    public void setMxsjse(String[] mxsjse) {
        this.mxsjse = mxsjse;
    }

    public void setMxxmmc(String[] mxxmmc) {
        this.mxxmmc = mxxmmc;
    }

    public void setFenpiao(String fenpiao) {
        this.fenpiao = fenpiao;
    }

    public void setMxxmdm(String[] mxxmdm) {
        this.mxxmdm = mxxmdm;
    }

    public void setSzsmList(ArrayList szsmList) {
        this.szsmList = szsmList;
    }

    public void setJkpzhao(String[] jkpzhao) {
        this.jkpzhao = jkpzhao;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public String getJzrq() {
        return jzrq;
    }

    public String getQsrq() {
        return qsrq;
    }

    public ArrayList getJsfsList() {
        return jsfsList;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    public String getHzfsmc() {
        return hzfsmc;
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

    public ArrayList getMxList() {
        return mxList;
    }

    public String getSkgk() {
        return skgk;
    }

    public String getSklx() {
        return sklx;
    }

    public String getSkssjssq() {
        return skssjssq;
    }

    public String getSksskssq() {
        return sksskssq;
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

    public String getScfs() {
        return scfs;
    }

    public String getJhh() {
        return jhh;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String[] getMxjsje() {
        return mxjsje;
    }

    public String[] getMxkssl() {
        return mxkssl;
    }

    public String[] getMxsjse() {
        return mxsjse;
    }

    public String[] getMxxmmc() {
        return mxxmmc;
    }

    public String getFenpiao() {
        return fenpiao;
    }

    public String[] getMxxmdm() {
        return mxxmdm;
    }

    public ArrayList getSzsmList() {
        return szsmList;
    }

    public String[] getJkpzhao() {
        return jkpzhao;
    }

    private void jbInit() throws Exception {
    }

    /**
     * @return Returns the dzjktfrq.
     */
    public String getDzjktfrq() {
        return dzjktfrq;
    }

    /**
     * @param dzjktfrq The dzjktfrq to set.
     */
    public void setDzjktfrq(String dzjktfrq) {
        this.dzjktfrq = dzjktfrq;
    }

    /**
     * @return Returns the sbbh.
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * @param sbbh The sbbh to set.
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }
}
