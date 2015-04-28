package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * ������˰���˶�Ӧ��bo
 */


public class JmsbBo implements Serializable {

    /**
     * ����
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();

    /**
     * ��������
     */
    private String cjrq;


    /**
     * �걨����
     */
    public Timestamp sbrq;

    /**
     * ���������
     */
    public String jsjdm;
    /**
     * �û���ʶ
     */
    public String yhbs;
    /**
     * �걨���
     */
    public String sbbh;

    /**
     * �������ز��������
     */
    public String fwtdbmslh;
    /**
     * ״̬��ʾ
     */
    public String ztbs;

    /**
     * ����
     */
    public ArrayList gjList = new ArrayList();

    /**
     * ����Ȩ����Ϣ
     */
    private Grxx voZcqrxx = null;

    /**
     * ��˰������
     */
    public String nsrlx;
    /**
     * ��˰������
     */
    public String nsrlxmc;


    /**
     * ��˰������
     */
    public String nsrmc;
    /**
     * ��ϵ�绰
     */
    public String lxdh;
    /**
     * ���֤������
     */
    public ArrayList sfzjlxList = new ArrayList();
    //   public String sfzjlx;
    //   public String sfzjlxmc;
    /**
     * ���֤������
     */
    //  public String sfzjhm;
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
    public String fwlb;
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
    public String bz;
    public String bzmc;
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

//��ӷǸ�����Ϣ
    /**
     * ��˰���������
     */
    public String nsjsjdm;

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
     * �ݻ���
     */
    public String rjl;

    /**
     * ���ؼ���
     */
    public String tdjc;


    /**
     * ��˰�������
     */
    public ArrayList qsjmlbList = new ArrayList();
    public String qsjmlb;
    public String qsjmlbmc;

    public String[] qsjmlbSelect;

    /**
     * �����������ɱ�ע
     */
    public String qtjmlybeizhu;
    
    /**
     * ��˰��������
     */
    public String qsjmxzdm;

    /**
     * ҳ���������
     * 0����ӣ�2�����ģ�OperationType
     */
    public int ymczlxdm;


    /**
     * ���
     * @return ArrayList
     */
    public ArrayList getBzList() {
        return bzList;
    }

    /**
     * ��óɽ��۸� Ԫ(�����)
     * @return String
     */
    public String getCjjgyrmb() {
        return cjjgyrmb;
    }

    /**
     * ��óɽ��۸� Ԫ(���)
     * @return String
     */
    public String getCjjgywb() {
        return cjjgywb;
    }

    /**
     * ��÷��ز���Ŀ����
     * @return String
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     * ��÷���
     * @return ArrayList
     */
    public String getFl() {
        return fl;
    }

    /**
     * ��÷��ݽ������
     * @return String
     */
    public String getFwjzmj() {
        return fwjzmj;
    }

    /**
     * ��÷������
     * @return ArrayList
     */
    public String getFwlb() {
        return fwlb;
    }

    /**
     * ��û���
     * @return String
     */
    public String getHn() {
        return hn;
    }

    /**
     * ��ú�Լǩ��ʱ��
     * @return String
     */
    public String getHyqdsj() {
        return hyqdsj;
    }

    /**
     * �����ϵ�绰
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * �����˰�ˣ������ˣ�ǩ��
     * @return String
     */
    public String getNsrqz() {
        return nsrqz;
    }

    /**
     * ��������۸�
     * @return String
     */
    public String getPgjg() {
        return pgjg;
    }

    /**
     * ������ء�����Ȩ��ת������
     * @return ArrayList
     */
    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    /**
     * ������ء�����Ȩ��ת�����
     * @return String
     */
    public String getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     * ������ء����������ַ
     * @return String
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     * ����ۺ�Ԫ(�����)
     * @return String
     */
    public String getZhyrmb() {
        return zhyrmb;
    }

    public String getBz() {
        return bz;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public ArrayList getFwlbList() {
        return fwlbList;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public String getFwtdbmslh() {
        return fwtdbmslh;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getKhyhdm() {
        return khyhdm;
    }

    public ArrayList getKhyhList() {
        return khyhList;
    }

    public String getLxrxm() {
        return lxrxm;
    }

    public String getNsjsjdm() {
        return nsjsjdm;
    }


    public String getYhzh() {
        return yhzh;
    }


    public String getKhyhmc() {
        return khyhmc;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getJsjdm() {
        return jsjdm;
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


    public ArrayList getGjList() {
        return gjList;
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

    /**
     * ���ñ�ע
     * @param bz ArrayList
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ���óɽ��۸� Ԫ(�����)
     * @param cjjgyrmb String
     */
    public void setCjjgyrmb(String cjjgyrmb) {
        this.cjjgyrmb = cjjgyrmb;
    }

    /**
     * ���óɽ��۸� Ԫ(���)
     * @param cjjgywb String
     */
    public void setCjjgywb(String cjjgywb) {
        this.cjjgywb = cjjgywb;
    }

    /**
     * �����ۺ�Ԫ(�����)
     * @param zhyrmb String
     */
    public void setZhyrmb(String zhyrmb) {
        this.zhyrmb = zhyrmb;
    }

    /**
     * �������ء����������ַ
     * @param tdfwzldz String
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * �������ء�����Ȩ��ת�����
     * @param tdfwqszymj String
     */
    public void setTdfwqszymj(String tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * �������ء�����Ȩ��ת������
     * @param tdfwqszylx ArrayList
     */
    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    /**
     * ���������۸�
     * @param pgjg String
     */
    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    /**
     * ������˰�ˣ������ˣ�ǩ��
     * @param nsrqz String
     */
    public void setNsrqz(String nsrqz) {
        this.nsrqz = nsrqz;
    }

    /**
     * ������ϵ�绰
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * ���ú�Լǩ��ʱ��
     * @param hyqdsj String
     */
    public void setHyqdsj(String hyqdsj) {
        this.hyqdsj = hyqdsj;
    }

    /**
     * ���ú�Լǩ��ʱ��
     * @param hn String
     */
    public void setHn(String hn) {
        this.hn = hn;
    }

    /**
     * ���÷������
     * @param fwlb ArrayList
     */
    public void setFwlb(String fwlb) {
        this.fwlb = fwlb;
    }

    /**
     * ���÷��ݽ������
     * @param fwjzmj String
     */
    public void setFwjzmj(String fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * ���÷���
     * @param fl ArrayList
     */
    public void setFl(String fl) {
        this.fl = fl;
    }

    /**
     * ���÷��ز���Ŀ����
     * @param fdcxmmc String
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFwlbList(ArrayList fwlbList) {
        this.fwlbList = fwlbList;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setFwtdbmslh(String fwtdbmslh) {
        this.fwtdbmslh = fwtdbmslh;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    public void setKhyhList(ArrayList khyhList) {
        this.khyhList = khyhList;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    public void setNsjsjdm(String nsjsjdm) {
        this.nsjsjdm = nsjsjdm;
    }


    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }


    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
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

    public void setGjList(ArrayList gjList) {
        this.gjList = gjList;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    private void jbInit() throws Exception {
    }


    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    /**
     * @return Returns the voZcqrxx.
     */
    public Grxx getVoZcqrxx() {
        if (voZcqrxx != null) {
            return voZcqrxx;
        }
        if (nsrList == null || nsrList.size() == 0) {
            return null;
        }
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx g = (Grxx) nsrList.get(i);
            if (g.getCqrlx().equals(Constants.CQRLX_ZCQR)) {
                this.setVoZcqrxx(g);
                return g;
            }
        }
        return null;
    }

    /**
     * @param voZcqrxx The voZcqrxx to set.
     */
    public void setVoZcqrxx(Grxx voZcqrxx) {
        this.voZcqrxx = voZcqrxx;
    }

    /**
     * @return sbrq
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * @param sbrq Ҫ���õ� sbrq
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * @return qsjmlb
     */
    public String getQsjmlb() {
        return qsjmlb;
    }

    /**
     * @param qsjmlb Ҫ���õ� qsjmlb
     */
    public void setQsjmlb(String qsjmlb) {
        this.qsjmlb = qsjmlb;
    }

    /**
     * @return qsjmlbList
     */
    public ArrayList getQsjmlbList() {
        return qsjmlbList;
    }

    /**
     * @param qsjmlbList Ҫ���õ� qsjmlbList
     */
    public void setQsjmlbList(ArrayList qsjmlbList) {
        this.qsjmlbList = qsjmlbList;
    }

    /**
     * @return qsjmlbmc
     */
    public String getQsjmlbmc() {
        return qsjmlbmc;
    }

    /**
     * @param qsjmlbmc Ҫ���õ� qsjmlbmc
     */
    public void setQsjmlbmc(String qsjmlbmc) {
        this.qsjmlbmc = qsjmlbmc;
    }

    public int getYmczlxdm() {
        return ymczlxdm;
    }

    public void setYmczlxdm(int ymczlxdm) {
        this.ymczlxdm = ymczlxdm;
    }

    /**
     * @return qsjmlbSelect
     */
    public String[] getQsjmlbSelect() {
        return qsjmlbSelect;
    }

    /**
     * @param qsjmlbSelect Ҫ���õ� qsjmlbSelect
     */
    public void setQsjmlbSelect(String[] qsjmlbSelect) {
        this.qsjmlbSelect = qsjmlbSelect;
    }

    /**
     * @return qtjmlybeizhu
     */
    public String getQtjmlybeizhu() {
        return qtjmlybeizhu;
    }

    /**
     * @param qtjmlybeizhu Ҫ���õ� qtjmlybeizhu
     */
    public void setQtjmlybeizhu(String qtjmlybeizhu) {
        this.qtjmlybeizhu = qtjmlybeizhu;
    }

	public String getQsjmxzdm() {
		return qsjmxzdm;
	}

	public void setQsjmxzdm(String qsjmxzdm) {
		this.qsjmxzdm = qsjmxzdm;
	}


}
