package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.ekernel.db.or.ORObject;

     /**
	 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
	 * <p>Description: �پ�ҵ�����걨��ϸ��һ����</p>
	 * <p>Copyright: Copyright (c) 2006</p>
	 * <p>Company: TTSOFT</p>
	 * @author qinwei
	 * @version 1.0
	 */
	public class Zjyjmsbmx12
	    implements ORObject {

		//˰����������
	    
	    private String jsjdm;

	    //�������ʹ���
	    
	    private String bblxdm;
	    
	    //������
	    
	    private String bbq;
	    
	    //�������
	    
	    private String bbnd;
	    

	    //��˰�����ʹ���
	    
	    private String nsrlxdm;
	    
	    //�걨�����ʹ���
	    
	    private String sbblxdm;
	    
	    //��˰����������
	    
	    private String nsrlxmc;
	    
	    //�����¸�ʧҵ����
	    
	    private String xnxgsyrs;
	    
	    //˰�ִ���
	    
	    private String szdm;
	    
	    //��1��
	    
	    private String j1;
	    
	    //��2��
	    
	    private String j2;
	    
	    //��3��
	    
	    private String j3;
	    
	    //¼����
	    
	    private String lrr;
	    
	    //¼������
	    
	    private Timestamp lrrq;
	    
	    //������
	    
	    private String cjr;
	    
	    //��������
	    
	    private Timestamp cjrq; 
	    
	    //���ش���
	    private String qxdm;
	    
	    //���
	    
	    private String nd;

		public String getBblxdm() {
			return bblxdm;
		}

		public void setBblxdm(String bblxdm) {
			this.bblxdm = bblxdm;
		}

		public String getBbnd() {
			return bbnd;
		}

		public void setBbnd(String bbnd) {
			this.bbnd = bbnd;
		}

		public String getBbq() {
			return bbq;
		}

		public void setBbq(String bbq) {
			this.bbq = bbq;
		}

		public String getCjr() {
			return cjr;
		}

		public void setCjr(String cjr) {
			this.cjr = cjr;
		}

		public Timestamp getCjrq() {
			return cjrq;
		}

		public void setCjrq(Timestamp cjrq) {
			this.cjrq = cjrq;
		}

		public String getJ1() {
			return j1;
		}

		public void setJ1(String j1) {
			this.j1 = j1;
		}

		public String getJ2() {
			return j2;
		}

		public void setJ2(String j2) {
			this.j2 = j2;
		}

		public String getJ3() {
			return j3;
		}

		public void setJ3(String j3) {
			this.j3 = j3;
		}

		public String getJsjdm() {
			return jsjdm;
		}

		public void setJsjdm(String jsjdm) {
			this.jsjdm = jsjdm;
		}

		public String getLrr() {
			return lrr;
		}

		public void setLrr(String lrr) {
			this.lrr = lrr;
		}

		public Timestamp getLrrq() {
			return lrrq;
		}

		public void setLrrq(Timestamp lrrq) {
			this.lrrq = lrrq;
		}

		public String getNd() {
			return nd;
		}

		public void setNd(String nd) {
			this.nd = nd;
		}

		public String getNsrlxdm() {
			return nsrlxdm;
		}

		public void setNsrlxdm(String nsrlxdm) {
			this.nsrlxdm = nsrlxdm;
		}

		public String getNsrlxmc() {
			return nsrlxmc;
		}

		public void setNsrlxmc(String nsrlxmc) {
			this.nsrlxmc = nsrlxmc;
		}

		public String getQxdm() {
			return qxdm;
		}

		public void setQxdm(String qxdm) {
			this.qxdm = qxdm;
		}

		public String getSbblxdm() {
			return sbblxdm;
		}

		public void setSbblxdm(String sbblxdm) {
			this.sbblxdm = sbblxdm;
		}

		public String getSzdm() {
			return szdm;
		}

		public void setSzdm(String szdm) {
			this.szdm = szdm;
		}

		public String getXnxgsyrs() {
			return xnxgsyrs;
		}

		public void setXnxgsyrs(String xnxgsyrs) {
			this.xnxgsyrs = xnxgsyrs;
		}

}
