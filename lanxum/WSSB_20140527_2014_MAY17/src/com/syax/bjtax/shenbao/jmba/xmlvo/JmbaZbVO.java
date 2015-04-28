package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;


/**
 *
 * <p>
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 减免备案VO
 * 本类为减免备案主数据结构
 * 数据结构如下
 *
 *    --编号
 *    --... ...
 *    --备案明细VO
 *    --备案资料清单VO
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
	 * 备案申请编号
	 */
	private String basqbh;
	/**
	 * 备案申请文书号
	 */
	private String basqwsh;

	/**
	 *	备案年度
	 */
	private String band;

	/**
	 * 减免备案事项代码
	 */
	private String jmbasxdm;

	/**
	 * 减免备案事项名称
	 */
	private String jmbasxmc;

	/**
	 * 状态代码
	 */
	private String ztdm;

	/**
	 * 状态名称
	 */
	private String ztmc;

	/**
	 * 文书打印录入项
	 */
	private String bajmse;
	/**
	 * 文书打印录入项
	 */
	private String bajmbl;

	private String fhwjmc;
	
	

	/**
	 * 明细VO
	 */
	private List qysdsjmba = new ArrayList();

	/**
	 * 往年明细VO
	 */
	private List wnqysdsjmba = new ArrayList();
    /**
     * @todo niuy add
     * 数据展现分离 13B 14B存储过程调用使用
     */
    private List jmbadtnd = new ArrayList();
    
	


	/**
	 * 录入日期
	 */
	private String lrrq;

	/**
	 * 起始日期
	 */
	private String qsrq;

	/**
	 * 截止日期
	 */
	private String jzrq;

	/**
	 * 税种代码
	 */
	private String szdm;

	/**
	 * 税种名称
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
		//明细数据
        if (qysdsjmba != null) {
            for (int i = 0; i < qysdsjmba.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) qysdsjmba.get(i)).toXML();
            }
        }

		//往年明细数据
        if (wnqysdsjmba != null) {
            for (int i = 0; i < wnqysdsjmba.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) wnqysdsjmba.get(i)).toXML();
            }
        }

        //往年年度数据
        if (jmbadtnd != null) {
            for (int i = 0; i < jmbadtnd.size(); i++) {
                xmlstr += ( (JmbamxVoInterface) jmbadtnd.get(i)).toXML();
            }
        }


        //资料清单

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
		//以下两行因企业所得税减免税备案系统新增作废变更功能所做的修改，供纳税人在网上申报模块查看具体备案事项的作废说明及作废日期用 20120806 刘超
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
	 * 作废说明
	 * 2012-8-6 系统因新增作废变更功能，于VO中增加此属性 
	 * 刘超
	 */
	private String zfsm;
	
	/**
	 * 作废日期
	 * 2012-8-6 系统因新增作废变更功能，于VO中增加此属性 
	 * 刘超
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
