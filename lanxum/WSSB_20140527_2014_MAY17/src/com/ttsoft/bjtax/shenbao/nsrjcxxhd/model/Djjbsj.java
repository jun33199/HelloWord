package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

public class Djjbsj implements Serializable {
	
	public String toXML() {
		StringBuffer sb = new StringBuffer("<djjbsj>");
		sb.append(XMLBuildUtil.appendStringElement("lsgxmc", this.lsgxmc));
		sb.append(XMLBuildUtil.appendStringElement("swdjlx", this.swdjlx));
		sb.append(XMLBuildUtil.appendStringElement("yhzrq", DateUtil.dateFormat(this.yhzrq)));
		sb.append(XMLBuildUtil.appendStringElement("scjxdm", this.scjxdm));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("sfzjhm", this.sfzjhm));
		sb.append(XMLBuildUtil.appendStringElement("slry", this.slry));
		sb.append(XMLBuildUtil.appendStringElement("zczbbzdm", this.zczbbzdm));
		sb.append(XMLBuildUtil.appendStringElement("zcdzyb", this.zcdzyb));
		sb.append(XMLBuildUtil.appendStringElement("qyzy", this.qyzy));
		sb.append(XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc));
		sb.append(XMLBuildUtil.appendStringElement("slrq", DateUtil.dateFormat(this.slrq)));
		sb.append(XMLBuildUtil.appendStringElement("zrrtzblhj", this.zrrtzblhj+""));
		sb.append(XMLBuildUtil.appendStringElement("djzzldm", this.djzzldm));
		sb.append(XMLBuildUtil.appendStringElement("hsxsdm", this.hsxsdm));
		sb.append(XMLBuildUtil.appendStringElement("zzjgdm", this.zzjgdm));
		sb.append(XMLBuildUtil.appendStringElement("gjbzhydm", this.gjbzhydm));
		sb.append(XMLBuildUtil.appendStringElement("jydzlxdm", this.jydzlxdm));
		sb.append(XMLBuildUtil.appendStringElement("kydjrq", DateUtil.dateFormat(this.kydjrq)));
		sb.append(XMLBuildUtil.appendStringElement("swjgzzjgdm", this.swjgzzjgdm));
		sb.append(XMLBuildUtil.appendStringElement("yyzzh", this.yyzzh));
		sb.append(XMLBuildUtil.appendStringElement("gdsgghbs", this.gdsgghbs+""));
		sb.append(XMLBuildUtil.appendStringElement("scbs", this.scbs));
		sb.append(XMLBuildUtil.appendStringElement("gjbzhymc", this.gjbzhymc));
		sb.append(XMLBuildUtil.appendStringElement("jydz", this.jydz));
		sb.append(XMLBuildUtil.appendStringElement("zgbmdm", this.zgbmdm));
		sb.append(XMLBuildUtil.appendStringElement("djzclxdm", this.djzclxdm));
		sb.append(XMLBuildUtil.appendStringElement("hzfhry", this.hzfhry));
		sb.append(XMLBuildUtil.appendStringElement("swdjzh", this.swdjzh));
		sb.append(XMLBuildUtil.appendStringElement("swjgzzjgmc", this.swjgzzjgmc));
		sb.append(XMLBuildUtil.appendStringElement("xgsj", DateUtil.dateFormat(this.xgsj)));
		sb.append(XMLBuildUtil.appendStringElement("wzztzblhj", this.wzztzblhj + ""));
		sb.append(XMLBuildUtil.appendStringElement("zcdz", this.zcdz));
		sb.append(XMLBuildUtil.appendStringElement("qrry", this.qrry));
		sb.append(XMLBuildUtil.appendStringElement("hzfhrq", DateUtil.dateFormat(this.hzfhrq)));
		sb.append(XMLBuildUtil.appendStringElement("cjsj", DateUtil.dateFormat(this.cjsj)));
		sb.append(XMLBuildUtil.appendStringElement("zcdzlxdh", this.zcdzlxdh));
		sb.append(XMLBuildUtil.appendStringElement("djzclxmc", this.djzclxmc));
		sb.append(XMLBuildUtil.appendStringElement("djsllx", this.djsllx));
		sb.append(XMLBuildUtil.appendStringElement("djsllx", this.djsllx));
		sb.append(XMLBuildUtil.appendStringElement("qrrq", DateUtil.dateFormat(this.qrrq)));
		sb.append(XMLBuildUtil.appendStringElement("lsgxdm", this.lsgxdm));
		sb.append(XMLBuildUtil.appendStringElement("nsrzt", this.nsrzt));
		sb.append(XMLBuildUtil.appendStringElement("kjzddm", this.kjzddm));
		sb.append(XMLBuildUtil.appendStringElement("zczbje", this.zczbje + ""));
		sb.append(XMLBuildUtil.appendStringElement("jyfw", this.jyfw));
		sb.append(XMLBuildUtil.appendStringElement("yhzry", this.yhzry));
		sb.append(XMLBuildUtil.appendStringElement("jydzyb", this.jydzyb));
		sb.append("</djjbsj>");
		return sb.toString();
	}
	
	private static final long serialVersionUID = 1L;
    /**
     * ������ϵ����
     */
    String lsgxmc;
    /**
     * ˰��Ǽ�����
     */

    String swdjlx;
    /**
     * �黻֤����
     */

    Date yhzrq;
    /**
     * �����������
     */

    String scjxdm;
    /**
     * ���������
     */

    String jsjdm;
    /**
     * ���֤������
     */

    String sfzjhm;
    /**
     * ������Ա
     */

    String slry;
    /**
     * ע���ʱ����ִ���
     */

    String zczbbzdm;
    /**
     * ע����ʱ�
     */

    String zcdzyb;
    /**
     * ��ҵ��ҳ��ַ
     */

    String qyzy;
    /**
     * ��˰������
     */

    String nsrmc;
    /**
     * ��������
     */

    Date slrq;
    /**
     * ��Ȼ��Ͷ�ʱ����ϼ�
     */

    double zrrtzblhj;

    /**
     * �Ǽ�֤�������
     */
    String djzzldm;
    /**
     * ������ʽ��Ԥ����ʽ����
     */

    String hsxsdm;
    /**
     * ��֯��������
     */

    String zzjgdm;
    /**
     * ���ұ�׼��ҵ����
     */

    String gjbzhydm;
    /**
     * ��Ӫ����ϵ�绰
     */

    String jydzlxdm;
    /**
     * ��ҵ�Ǽ�����
     */

    Date kydjrq;
    /**
     * ˰�������֯��������
     */

    String swjgzzjgdm;
    /**
     * Ӫҵִ�պ�
     */

    String yyzzh;
    /**
     * ����˰���ܻ���ʶ
     */

    long gdsgghbs;

    /**
     * ɾ����ʶ
     */
    String scbs;
    /**
     * ���ұ�׼��ҵ����
     */

    String gjbzhymc;
    /**
     * ��Ӫ��ַ
     */

    String jydz;
    /**
     * ���ܲ��Ŵ���
     */

    String zgbmdm;
    /**
     * �Ǽ�ע�����ʹ���
     */

    String djzclxdm;
    /**
     * ��׼�ֻ���Ա
     */

    String hzfhry;
    /**
     * ˰��Ǽ�֤��
     */

    String swdjzh;
    /**
     * ˰�������֯��������
     */

    String swjgzzjgmc;
    /**
     * �޸�ʱ��
     */

    Date xgsj;
    /**
     * ����Ͷ�ʱ����ϼƣ�%��
     */

    double wzztzblhj;

    /**
     * ע���ַ
     */
    String zcdz;
    /**
     * ȷ����Ա
     */

    String qrry;
    /**
     * ��׼�ֻ�����
     */

    Date hzfhrq;
    /**
     * ����ʱ��
     */

    Date cjsj;
    /**
     * ע���ַ��ϵ�绰
     */

    String zcdzlxdh;
    /**
     * �Ǽ�ע����������
     */

    String djzclxmc;
    /**
     * �Ǽ���������
     */

    String djsllx;
    /**
     * ȷ������
     */

    Date qrrq;
    /**
     * ������ϵ����
     */

    String lsgxdm;
    /**
     * ��˰��״̬
     */

    String nsrzt;
    /**
     * ����ƶȴ���
     */

    String kjzddm;
    /**
     * ע���ʱ����
     */

    long zczbje;

    /**
     * ��Ӫ��Χ
     */
    String jyfw;
    /**
     * �黻֤��Ա
     */

    String yhzry;
    /**
     * ��Ӫ��ַ�ʱ�
     */

    String jydzyb;

	public String getLsgxmc() {
		return lsgxmc;
	}

	public void setLsgxmc(String lsgxmc) {
		this.lsgxmc = lsgxmc;
	}

	public String getSwdjlx() {
		return swdjlx;
	}

	public void setSwdjlx(String swdjlx) {
		this.swdjlx = swdjlx;
	}

	public Date getYhzrq() {
		return yhzrq;
	}

	public void setYhzrq(Date yhzrq) {
		this.yhzrq = yhzrq;
	}

	public String getScjxdm() {
		return scjxdm;
	}

	public void setScjxdm(String scjxdm) {
		this.scjxdm = scjxdm;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getSfzjhm() {
		return sfzjhm;
	}

	public void setSfzjhm(String sfzjhm) {
		this.sfzjhm = sfzjhm;
	}

	public String getSlry() {
		return slry;
	}

	public void setSlry(String slry) {
		this.slry = slry;
	}

	public String getZczbbzdm() {
		return zczbbzdm;
	}

	public void setZczbbzdm(String zczbbzdm) {
		this.zczbbzdm = zczbbzdm;
	}

	public String getZcdzyb() {
		return zcdzyb;
	}

	public void setZcdzyb(String zcdzyb) {
		this.zcdzyb = zcdzyb;
	}

	public String getQyzy() {
		return qyzy;
	}

	public void setQyzy(String qyzy) {
		this.qyzy = qyzy;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public Date getSlrq() {
		return slrq;
	}

	public void setSlrq(Date slrq) {
		this.slrq = slrq;
	}

	public double getZrrtzblhj() {
		return zrrtzblhj;
	}

	public void setZrrtzblhj(double zrrtzblhj) {
		this.zrrtzblhj = zrrtzblhj;
	}

	public String getDjzzldm() {
		return djzzldm;
	}

	public void setDjzzldm(String djzzldm) {
		this.djzzldm = djzzldm;
	}

	public String getHsxsdm() {
		return hsxsdm;
	}

	public void setHsxsdm(String hsxsdm) {
		this.hsxsdm = hsxsdm;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getGjbzhydm() {
		return gjbzhydm;
	}

	public void setGjbzhydm(String gjbzhydm) {
		this.gjbzhydm = gjbzhydm;
	}

	public String getJydzlxdm() {
		return jydzlxdm;
	}

	public void setJydzlxdm(String jydzlxdm) {
		this.jydzlxdm = jydzlxdm;
	}

	public Date getKydjrq() {
		return kydjrq;
	}

	public void setKydjrq(Date kydjrq) {
		this.kydjrq = kydjrq;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getYyzzh() {
		return yyzzh;
	}

	public void setYyzzh(String yyzzh) {
		this.yyzzh = yyzzh;
	}

	public long getGdsgghbs() {
		return gdsgghbs;
	}

	public void setGdsgghbs(long gdsgghbs) {
		this.gdsgghbs = gdsgghbs;
	}

	public String getScbs() {
		return scbs;
	}

	public void setScbs(String scbs) {
		this.scbs = scbs;
	}

	public String getGjbzhymc() {
		return gjbzhymc;
	}

	public void setGjbzhymc(String gjbzhymc) {
		this.gjbzhymc = gjbzhymc;
	}

	public String getJydz() {
		return jydz;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public String getZgbmdm() {
		return zgbmdm;
	}

	public void setZgbmdm(String zgbmdm) {
		this.zgbmdm = zgbmdm;
	}

	public String getDjzclxdm() {
		return djzclxdm;
	}

	public void setDjzclxdm(String djzclxdm) {
		this.djzclxdm = djzclxdm;
	}

	public String getHzfhry() {
		return hzfhry;
	}

	public void setHzfhry(String hzfhry) {
		this.hzfhry = hzfhry;
	}

	public String getSwdjzh() {
		return swdjzh;
	}

	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public double getWzztzblhj() {
		return wzztzblhj;
	}

	public void setWzztzblhj(double wzztzblhj) {
		this.wzztzblhj = wzztzblhj;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getQrry() {
		return qrry;
	}

	public void setQrry(String qrry) {
		this.qrry = qrry;
	}

	public Date getHzfhrq() {
		return hzfhrq;
	}

	public void setHzfhrq(Date hzfhrq) {
		this.hzfhrq = hzfhrq;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getZcdzlxdh() {
		return zcdzlxdh;
	}

	public void setZcdzlxdh(String zcdzlxdh) {
		this.zcdzlxdh = zcdzlxdh;
	}

	public String getDjzclxmc() {
		return djzclxmc;
	}

	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}

	public String getDjsllx() {
		return djsllx;
	}

	public void setDjsllx(String djsllx) {
		this.djsllx = djsllx;
	}

	public Date getQrrq() {
		return qrrq;
	}

	public void setQrrq(Date qrrq) {
		this.qrrq = qrrq;
	}

	public String getLsgxdm() {
		return lsgxdm;
	}

	public void setLsgxdm(String lsgxdm) {
		this.lsgxdm = lsgxdm;
	}

	public String getNsrzt() {
		return nsrzt;
	}

	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}

	public String getKjzddm() {
		return kjzddm;
	}

	public void setKjzddm(String kjzddm) {
		this.kjzddm = kjzddm;
	}

	public long getZczbje() {
		return zczbje;
	}

	public void setZczbje(long zczbje) {
		this.zczbje = zczbje;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getYhzry() {
		return yhzry;
	}

	public void setYhzry(String yhzry) {
		this.yhzry = yhzry;
	}

	public String getJydzyb() {
		return jydzyb;
	}

	public void setJydzyb(String jydzyb) {
		this.jydzyb = jydzyb;
	}
	
	
}
