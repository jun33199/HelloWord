package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨������Ϣ��</p>
 * @author lianglw
 * @version 1.1
 */

public class JbxxbForm2008
extends BaseForm {
	public JbxxbForm2008() {
		try {
			this.jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * ��˰�˼�������� String
	 */
	private String jsjdm;
	
	/**
	 * ��˰��ʶ��ţ�˰��Ǽ�֤�� String
	 */
	private String nsrsbh;
	
	/**
	 * ��˰������ String
	 */
	private String nsrmc;
	
	
	/**
	 * �걨��� String
	 */
	private String sbnd;
	
	/**
	 * ˰����� String
	 */
	private String sknd;
	
	/**
	 * ������������-�Ǽ�ע�����ʹ��� String
	 */
	private String ssjjlxdm;
	
	/**
	 * ������������-�Ǽ�ע���������� String
	 */
	private String ssjjlxmc;
	
	/**
	 * ��ϵ�绰 String
	 */
	private String lxdh;
		
	/**
	 * ��Ӫ��ַ String
	 */
	private String jydz;
	
	   /**
     * ������ҵ����
     */
    private String sshydm;
    
    /**
     * ������ҵ����
     */
    private String sshymc;
	
	
	/**
	 * ���շ�ʽ���� String
	 */
	private String zsfsdm;

	/**
	 * ���շ�ʽ����
	 */
	private String zsfsmc;
	
	/**
	 * �ƻ��ƶȴ��� String
	 */
	private String cwkjzddm;

	/**
	 * �ƻ��ƶ����� String
	 */
	private String cwkjzdmc;
	
	/**
	 * �ƻ��ƶȴ����޸�ǰ�� String
	 */
	private String cwkjzddm_old;

	
	
	/**
	 * ���ʹ�����ʽ���� String
	 */
	private String gzglxsdm;
	
	/**
	 * ���ʹ�����ʽ���� String
	 */
	private String gzglxsmc;

	
	/**
	 * ���ʹ�����ʽ�����޸�ǰ�� String
	 */
	private String gzglxsdm_old;

	
	/**
	 * �������ʹ��� String
	 */
	private String jmlxdm;
	
	/**
	 * ������������ String
	 */
	private String jmlxmc;

	
	/**
	 * �������ʹ����޸�ǰ�� String
	 */
	private String jmlxdm_old;
	
	/**
	 * ˰�������֯�������� String
	 *
	 * �ӵǼ�������ȡ��
	 */
	
	private String swjgzzjgdm;
	
	/**
	 * ˰�������֯�������� String
	 */
	private String swjgzzjgmc;
	
	
	/**
	 * �����˴��� String
	 *
	 * �ӵ�¼������ȡ��
	 */
	private String cjr;
	
	/**
	 * ����ʱ�� String
	 */
	
	private String cjrq;
	
    
	/**
	 * ¼���˴��� String
	 *
	 * �ӵ�¼������ȡ��
	 */
	private String lrr;
	
	/**
	 * ¼������ String
	 */
	
	private String lrrq;
	
	/**
	 * �걨���� String
	 */
	
	private String sbrq;
	
	/**
	 * ϵͳ���� String
	 */
	
	private String xtjb;
	
	/**
	 * ���������� String
	 */
	
	private String bbmsf;
	
	/**
	 * ˰��������ʼ���� String
	 */
	
	private String skssksrq;
	
	/**
	 * ˰�������������� String
	 */
	
	private String skssjsrq;
	
	/**
	 * �汾�� String
	 */
	
	private String version;
	
	/**
	 * ����������� String
	 */
	
	private String sqspbh;
	
	private String iszhsb;

	
	private void jbInit() throws Exception {
	}
	
	
	/**
	 * ҳ��Ҫ�����
	 * @param actionMapping struts.action.ActionMapping
	 * @param httpServletRequest HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		

		this.jsjdm = "";
		this.nsrsbh = "";
		
		this.nsrmc = "";	
		this.sknd = "";
		this.sbnd = "";
		this.ssjjlxdm = "";
		this.ssjjlxmc = "";

		this.lxdh = "";
		this.jydz = "";
		this.sshydm = "";
		this.sshymc = "";

		
		this.zsfsdm = "";	
		this.zsfsmc = "";
		this.cwkjzddm = "";
		this.cwkjzdmc = "";
		this.cwkjzddm_old = "";

		this.gzglxsdm = "";
		this.gzglxsmc = "";
		this.gzglxsdm_old = "";

		this.jmlxdm = "";
		this.jmlxmc = "";
		this.jmlxdm_old = "";

		this.swjgzzjgdm = "";	
		this.swjgzzjgmc = "";
		this.cjr = "";
		this.cjrq = "";
		this.lrr = "";
		this.lrrq = "";
		this.xtjb = "";
		this.bbmsf = "";
		
		this.skssksrq="";
		
		this.skssjsrq="";
		
		this.iszhsb="";
		this.sbrq="";
		
		this.version="";
		
		this.sqspbh="";

	}


	public String getBbmsf() {
		return bbmsf;
	}


	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}


	public String getCjr() {
		return cjr;
	}


	public void setCjr(String cjr) {
		this.cjr = cjr;
	}


	public String getCjrq() {
		return cjrq;
	}


	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}


	public String getCwkjzddm() {
		return cwkjzddm;
	}


	public void setCwkjzddm(String cwkjzddm) {
		this.cwkjzddm = cwkjzddm;
	}


	public String getCwkjzddm_old() {
		return cwkjzddm_old;
	}


	public void setCwkjzddm_old(String cwkjzddm_old) {
		this.cwkjzddm_old = cwkjzddm_old;
	}


	public String getCwkjzdmc() {
		return cwkjzdmc;
	}


	public void setCwkjzdmc(String cwkjzdmc) {
		this.cwkjzdmc = cwkjzdmc;
	}


	public String getGzglxsdm() {
		return gzglxsdm;
	}


	public void setGzglxsdm(String gzglxsdm) {
		this.gzglxsdm = gzglxsdm;
	}


	public String getGzglxsdm_old() {
		return gzglxsdm_old;
	}


	public void setGzglxsdm_old(String gzglxsdm_old) {
		this.gzglxsdm_old = gzglxsdm_old;
	}


	public String getGzglxsmc() {
		return gzglxsmc;
	}


	public void setGzglxsmc(String gzglxsmc) {
		this.gzglxsmc = gzglxsmc;
	}


	public String getIszhsb() {
		return iszhsb;
	}


	public void setIszhsb(String iszhsb) {
		this.iszhsb = iszhsb;
	}


	public String getJmlxdm() {
		return jmlxdm;
	}


	public void setJmlxdm(String jmlxdm) {
		this.jmlxdm = jmlxdm;
	}


	public String getJmlxdm_old() {
		return jmlxdm_old;
	}


	public void setJmlxdm_old(String jmlxdm_old) {
		this.jmlxdm_old = jmlxdm_old;
	}


	public String getJmlxmc() {
		return jmlxmc;
	}


	public void setJmlxmc(String jmlxmc) {
		this.jmlxmc = jmlxmc;
	}


	public String getJsjdm() {
		return jsjdm;
	}


	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}


	public String getJydz() {
		return jydz;
	}


	public void setJydz(String jydz) {
		this.jydz = jydz;
	}


	public String getLrr() {
		return lrr;
	}


	public void setLrr(String lrr) {
		this.lrr = lrr;
	}


	public String getLrrq() {
		return lrrq;
	}


	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}


	public String getLxdh() {
		return lxdh;
	}


	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}





	public String getNsrmc() {
		return nsrmc;
	}


	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}


	public String getNsrsbh() {
		return nsrsbh;
	}


	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}





	public String getSbrq() {
		return sbrq;
	}


	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}


	public String getSkssjsrq() {
		return skssjsrq;
	}


	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}


	public String getSkssksrq() {
		return skssksrq;
	}


	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}


	public String getSqspbh() {
		return sqspbh;
	}


	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}


	public String getSshydm() {
		return sshydm;
	}


	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}


	public String getSshymc() {
		return sshymc;
	}


	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}


	public String getSsjjlxdm() {
		return ssjjlxdm;
	}


	public void setSsjjlxdm(String ssjjlxdm) {
		this.ssjjlxdm = ssjjlxdm;
	}


	public String getSsjjlxmc() {
		return ssjjlxmc;
	}


	public void setSsjjlxmc(String ssjjlxmc) {
		this.ssjjlxmc = ssjjlxmc;
	}


	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}


	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}


	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}


	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getXtjb() {
		return xtjb;
	}


	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}


	public String getZsfsdm() {
		return zsfsdm;
	}


	public void setZsfsdm(String zsfsdm) {
		this.zsfsdm = zsfsdm;
	}


	public String getZsfsmc() {
		return zsfsmc;
	}


	public void setZsfsmc(String zsfsmc) {
		this.zsfsmc = zsfsmc;
	}


	public String getSbnd() {
		return sbnd;
	}


	public void setSbnd(String sbnd) {
		this.sbnd = sbnd;
	}


	public String getSknd() {
		return sknd;
	}


	public void setSknd(String sknd) {
		this.sknd = sknd;
	}


	


	


	
	
	
	
	
}

