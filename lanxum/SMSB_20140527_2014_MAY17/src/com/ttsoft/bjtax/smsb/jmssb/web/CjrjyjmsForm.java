package com.ttsoft.bjtax.smsb.jmssb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description: ���òм��˾�ҵ��ҵӪҵ˰����˰�걨��
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */
public class CjrjyjmsForm extends BaseForm {
	/*
	 * ���������
	 */
	private String jsjdm;
	/*
	 * �����������
	 */
	private String sqspbh;
	/*
	 * ��˰��ʶ��ţ�˰��Ǽ��ʺţ�
	 */
	private String swdjzh;
	/*
	 * ��λ����
	 */
	private String dwxz;
	/*
	 * ְ��������
	 */
	private String zgzrs;
	/*
	 * ���òм�ְ������
	 */
	private String cjrzgrs;
	/*
	 * �м�ְ��ռְ���������ı���
	 */
	private String cjrybl;
	/*
	 * Ӫҵ˰Ӧ˰����
	 */
	private String ynyyssr;
	/*
	 * Ӧ��Ӫҵ˰˰��
	 */
	private String yjyysse;
	/*
	 * �����ܱ��Ż����ߵ�˰��
	 */
	private String xsyhzzse;
	/*
	 * ����Ӧ����Ӫҵ˰�޶�
	 */
	private String byyjzyysxe;
	/*
	 * ����ĩ����Ӫҵ˰�޶�
	 */
	private String syyjzyysxe;
	/*
	 * ���¿ɼ���Ӫҵ˰�޶�
	 */
	private String bykjzyysxe;
	/*
	 * ����ʵ�ʼ���Ӫҵ˰�޶�
	 */
	private String bysjjzyysye;
	/*
	 * ���¼���Ӫҵ˰��Ӫҵ˰˰��
	 */
	private String bysjyesse;
	/*
	 * ����ĩ����Ӫҵ˰�޶�
	 */
	private String bymjzyysxe;
	/*
	 * �걨����
	 */
	private String sbrq;
	/*
	 * ¼������
	 */
	private String lrrq;
	/*
	 * ��ʽ����
	 */
	private String fsdm;
	/*
	 * ˰�������֯��������
	 */
	private String swjgzzjgdm;
	/*
	 * ¼���˴���
	 */
	private String lrr;
	/*
	 * ˰����������
	 */
	private String skssrq;
	/*
	 * ���
	 */
	private String nd;
	/*
	 * ��������
	 */
	private String cjrq;
	/*
	 * ��ע
	 */
	private String bz;
	/*
	 * ���ش���
	 */
	private String qxdm;
	/*
	 * ��˰������
	 */
	private String nsrmc;
	/*
	 * ע���ַ
	 */
	private String zcdz;
	/*
	 * ��Ӫ��Χ
	 */
	private String jyfw;
	/*
	 * ҳ���ʶ
	 */
	private String sign;
	
	private String sign_re;
	/*
	 * ��λ��������
	 */
	private List dwxzList = new ArrayList();
	/*
	 * ��ѯ�����Ϣ
	 */
	private String isError;

	private String[] columns = { "jsjdm", "swdjzh", "dwxz", "zgzrs", "cjrzgrs",
			"cjrybl", "ynyyssr", "yjyysse", "xsyhzzse", "byyjzyysxe",
			"syyjzyysxe", "bykjzyysxe", "bysjjzyysye", "bysjyesse",
			"bymjzyysxe", "bymjzyysxe", "skssjsrq" };

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.actionType = "Show";
		this.swdjzh = null;
		this.jsjdm = null;
		this.nsrmc = null;
		this.zgzrs = null;
		this.cjrzgrs = null;
		this.cjrybl = null;
		this.ynyyssr = null;
		this.yjyysse = null;
		this.xsyhzzse = null;
		this.byyjzyysxe = null;
		this.syyjzyysxe = null;
		this.bykjzyysxe = null;
		this.bysjjzyysye = null;
		this.bysjyesse = null;
		this.bymjzyysxe = null;
		this.bymjzyysxe = null;
		this.sign = null;
		this.skssrq=null;
	}
	public void resetA(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.actionType = "Show";
		this.zgzrs = null;
		this.cjrzgrs = null;
		this.cjrybl = null;
		this.ynyyssr = null;
		this.yjyysse = null;
		this.xsyhzzse = null;
		this.byyjzyysxe = null;
		this.syyjzyysxe = null;
		this.bykjzyysxe = null;
		this.bysjjzyysye = null;
		this.bysjyesse = null;
		this.bymjzyysxe = null;
		this.bymjzyysxe = null;
		this.skssrq=null;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getSwdjzh() {
		return swdjzh;
	}

	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}

	public String getDwxz() {
		return dwxz;
	}

	public String getZgzrs() {
		return zgzrs;
	}

	public void setZgzrs(String zgzrs) {
		this.zgzrs = zgzrs;
	}

	public String getCjrzgrs() {
		return cjrzgrs;
	}

	public void setCjrzgrs(String cjrzgrs) {
		this.cjrzgrs = cjrzgrs;
	}

	public String getCjrybl() {
		return cjrybl;
	}

	public void setCjrybl(String cjrybl) {
		this.cjrybl = cjrybl;
	}

	public String getYnyyssr() {
		return ynyyssr;
	}

	public void setYnyyssr(String ynyyssr) {
		this.ynyyssr = ynyyssr;
	}

	public String getYjyysse() {
		return yjyysse;
	}

	public void setYjyysse(String yjyysse) {
		this.yjyysse = yjyysse;
	}

	public String getXsyhzzse() {
		return xsyhzzse;
	}

	public void setXsyhzzse(String xsyhzzse) {
		this.xsyhzzse = xsyhzzse;
	}

	public String getByyjzyysxe() {
		return byyjzyysxe;
	}

	public void setByyjzyysxe(String byyjzyysxe) {
		this.byyjzyysxe = byyjzyysxe;
	}

	public String getSyyjzyysxe() {
		return syyjzyysxe;
	}

	public void setSyyjzyysxe(String syyjzyysxe) {
		this.syyjzyysxe = syyjzyysxe;
	}

	public String getBykjzyysxe() {
		return bykjzyysxe;
	}

	public void setBykjzyysxe(String bykjzyysxe) {
		this.bykjzyysxe = bykjzyysxe;
	}

	public String getBysjjzyysye() {
		return bysjjzyysye;
	}

	public void setBysjjzyysye(String bysjjzyysye) {
		this.bysjjzyysye = bysjjzyysye;
	}

	public String getBysjyesse() {
		return bysjyesse;
	}

	public void setBysjyesse(String bysjyesse) {
		this.bysjyesse = bysjyesse;
	}

	public String getBymjzyysxe() {
		return bymjzyysxe;
	}

	public void setBymjzyysxe(String bymjzyysxe) {
		this.bymjzyysxe = bymjzyysxe;
	}

	public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getFsdm() {
		return fsdm;
	}

	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getQxdm() {
		return qxdm;
	}

	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public List getDwxzList() {
		return dwxzList;
	}

	public void setDwxzList(List dwxzList) {
		this.dwxzList = dwxzList;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getSkssrq() {
		return skssrq;
	}

	public void setSkssrq(String skssrq) {
		this.skssrq = skssrq;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSqspbh() {
		return sqspbh;
	}

	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}
	public String getSign_re() {
		return sign_re;
	}
	public void setSign_re(String sign_re) {
		this.sign_re = sign_re;
	}

}
