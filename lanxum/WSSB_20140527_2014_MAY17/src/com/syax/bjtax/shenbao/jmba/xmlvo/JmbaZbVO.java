package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;


/**
 *
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ���ⱸ��VO
 * ����Ϊ���ⱸ�������ݽṹ
 * ���ݽṹ����
 *
 *    --���
 *    --... ...
 *    --������ϸVO
 *    --���������嵥VO
 *
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 *  * @author Chenmt
 * @version 1.0
 */
public class JmbaZbVO  implements XMLVOInterface{
	

	private String xh;
	/**
	 * ����������
	 */
	private String basqbh;
	/**
	 * �������������
	 */
	private String basqwsh;

	/**
	 *	�������
	 */
	private String band;

	/**
	 * ���ⱸ���������
	 */
	private String jmbasxdm;

	/**
	 * ���ⱸ����������
	 */
	private String jmbasxmc;

	/**
	 * ״̬����
	 */
	private String ztdm;

	/**
	 * ״̬����
	 */
	private String ztmc;

	/**
	 * �����ӡ¼����
	 */
	private String bajmse;
	/**
	 * �����ӡ¼����
	 */
	private String bajmbl;

	private String fhwjmc;
	
	

	/**
	 * ��ϸVO
	 */
	private List qysdsjmba = new ArrayList();

	/**
	 * ������ϸVO
	 */
	private List wnqysdsjmba = new ArrayList();
    /**
     * @todo niuy add
     * ����չ�ַ��� 13B 14B�洢���̵���ʹ��
     */
    private List jmbadtnd = new ArrayList();
    
	


	/**
	 * ¼������
	 */
	private String lrrq;

	/**
	 * ��ʼ����
	 */
	private String qsrq;

	/**
	 * ��ֹ����
	 */
	private String jzrq;

	/**
	 * ˰�ִ���
	 */
	private String szdm;

	/**
	 * ˰������
	 */
	private String szmc;

	private String bsfsdm;

	private String bsfsmc;

	private List bajlzlqd = new ArrayList();
	private Map m = new HashMap();

	//private String

	public JmbaZbVO(){
		super();
        m.put("bajlzlqd", "com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO");


	}

	public Map getListTypeMap() {
			m.put("qysdsjmba", VOConstants.badmmapvo.get(jmbasxdm));
			m.put("wnqysdsjmba", VOConstants.wnbadmmapvo.get(jmbasxdm));
		return m;
	}

	public String toXML() {
		String xmlstr ="<jmsbajl>";
		xmlstr += toXMLChilds();
		//��ϸ����
        if (qysdsjmba != null) {
            for (int i = 0; i < qysdsjmba.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) qysdsjmba.get(i)).toXML();
            }
        }

		//������ϸ����
        if (wnqysdsjmba != null) {
            for (int i = 0; i < wnqysdsjmba.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) wnqysdsjmba.get(i)).toXML();
            }
        }

        //�����������
        if (jmbadtnd != null) {
            for (int i = 0; i < jmbadtnd.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) jmbadtnd.get(i)).toXML();
            }
        }


        //�����嵥

        if (bajlzlqd != null) {
            for (int i = 0; i < bajlzlqd.size(); i++) {
                xmlstr += ( (JmbaZlqdVO) bajlzlqd.get(i)).toXML();
            }
        }
		xmlstr+="</jmsbajl>";

		return xmlstr;
	}
	
	

	public String toXMLChilds() {
		String xmlstr = "";
		xmlstr += XMLBuildUtil.appendStringElement("basqbh",basqbh);
		xmlstr += XMLBuildUtil.appendStringElement("basqwsh",basqwsh);
		xmlstr += XMLBuildUtil.appendStringElement("band",band);
		xmlstr += XMLBuildUtil.appendStringElement("jmbasxdm",jmbasxdm);
		xmlstr += XMLBuildUtil.appendStringElement("jmbasxmc",jmbasxmc);
		xmlstr += XMLBuildUtil.appendStringElement("bajmse",bajmse);
		xmlstr += XMLBuildUtil.appendStringElement("bajmbl",bajmbl);
		xmlstr += XMLBuildUtil.appendStringElement("fhwjmc",fhwjmc);
		xmlstr += XMLBuildUtil.appendStringElement("qsrq",qsrq);
		xmlstr += XMLBuildUtil.appendStringElement("ztdm",ztdm);
		xmlstr += XMLBuildUtil.appendStringElement("ztmc",ztmc);
		xmlstr += XMLBuildUtil.appendStringElement("jzrq",jzrq);
		xmlstr += XMLBuildUtil.appendStringElement("szdm",szdm);
		xmlstr += XMLBuildUtil.appendStringElement("szmc",szmc);
		xmlstr += XMLBuildUtil.appendStringElement("bsfsdm",bsfsdm);
		xmlstr += XMLBuildUtil.appendStringElement("bsfsmc",bsfsmc);
		xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);	
		//------------------------------------------------------------------------------------------------------------------
		//������������ҵ����˰����˰����ϵͳ�������ϱ�������������޸ģ�����˰���������걨ģ��鿴���屸�����������˵�������������� 20120806 ����
		xmlstr += XMLBuildUtil.appendStringElement("zfsm",zfsm);
		xmlstr += XMLBuildUtil.appendStringElement("zfrq",zfrq);
		//------------------------------------------------------------------------------------------------------------------
		return xmlstr;
	}

	

	/**
	 * @return Returns the bajmbl.
	 */
	public String getBajmbl() {
		return bajmbl;
	}
	/**
	 * @param bajmbl The bajmbl to set.
	 */
	public void setBajmbl(String bajmbl) {
		this.bajmbl = bajmbl;
	}
	/**
	 * @return Returns the bajmse.
	 */
	public String getBajmse() {
		return bajmse;
	}
	/**
	 * @param bajmse The bajmse to set.
	 */
	public void setBajmse(String bajmse) {
		this.bajmse = bajmse;
	}
	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getBasqbh() {
		return basqbh;
	}

	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}

	public String getBsfsdm() {
		return bsfsdm;
	}

	public void setBsfsdm(String bsfsdm) {
		this.bsfsdm = bsfsdm;
	}

	public String getBsfsmc() {
		return bsfsmc;
	}

	public void setBsfsmc(String bsfsmc) {
		this.bsfsmc = bsfsmc;
	}

	public String getFhwjmc() {
		return fhwjmc;
	}

	public void setFhwjmc(String fhwjmc) {
		this.fhwjmc = fhwjmc;
	}

	public String getJmbasxdm() {
		return jmbasxdm;
	}

	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}

	public String getJmbasxmc() {
		return jmbasxmc;
	}

	public void setJmbasxmc(String jmbasxmc) {
		this.jmbasxmc = jmbasxmc;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}


	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getSzdm() {
		return szdm;
	}

	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}

	public String getSzmc() {
		return szmc;
	}

	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}



	public String getZtdm() {
		return ztdm;
	}

	public void setZtdm(String ztdm) {
		this.ztdm = ztdm;
	}

	public String getZtmc() {
		return ztmc;
	}

	public void setZtmc(String ztmc) {
		this.ztmc = ztmc;
	}


	/**
	 * @return Returns the bajlzlqd.
	 */
	public List getBajlzlqd() {
		return bajlzlqd;
	}
	/**
	 * @param bajlzlqd The bajlzlqd to set.
	 */
	public void setBajlzlqd(List bajlzlqd) {
		this.bajlzlqd = bajlzlqd;
	}
	/**
	 * @return Returns the qysdsjmba.
	 */
	public List getQysdsjmba() {
		return qysdsjmba;
	}

    public String getXh() {
        return xh;
    }

    /**
	 * @param qysdsjmba The qysdsjmba to set.
	 */
	public void setQysdsjmba(List qysdsjmba) {
		this.qysdsjmba = qysdsjmba;
	}

    public void setXh(String xh) {
        this.xh = xh;
    }
	/**
	 * @return Returns the basqwsh.
	 */
	public String getBasqwsh() {
		return basqwsh;
	}
	/**
	 * @param basqwsh The basqwsh to set.
	 */
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	/**
	 * @return Returns the lrrq.
	 */
	public String getLrrq() {
		return lrrq;
	}
	/**
	 * @param lrrq The lrrq to set.
	 */
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	/**
	 * @return Returns the wnqysdsjmba.
	 */
	public List getWnqysdsjmba() {
		return wnqysdsjmba;
	}

    public List getJmbadtnd() {
        return jmbadtnd;
    }
    

    /**
	 * @param wnqysdsjmba The wnqysdsjmba to set.
	 */
	public void setWnqysdsjmba(List wnqysdsjmba) {
		this.wnqysdsjmba = wnqysdsjmba;
	}

    public void setJmbadtnd(List jmbadtnd) {
        this.jmbadtnd = jmbadtnd;
    }
    
    /**
	 * ����˵��
	 * 2012-8-6 ϵͳ���������ϱ�����ܣ���VO�����Ӵ����� 
	 * ����
	 */
	private String zfsm;
	
	/**
	 * ��������
	 * 2012-8-6 ϵͳ���������ϱ�����ܣ���VO�����Ӵ����� 
	 * ����
	 */
	private String zfrq;

	
	/**
	 * getters & setters
	 * @return
	 */
	
	public String getZfsm() {
		return zfsm;
	}

	public void setZfsm(String zfsm) {
		this.zfsm = zfsm;
	}

	public String getZfrq() {
		return zfrq;
	}

	public void setZfrq(String zfrq) {
		this.zfrq = zfrq;
	}
    
    
}
