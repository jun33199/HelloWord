package com.syax.bjtax.shenbao.model.common;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbxxVO implements XMLVOInterface
{
	//Description�����ܷ�Χ��������
	private String zgfwjdlx = "";
	
    /**
     * ���
     */
    private String nd = "";

    /**
     * ����
     */
    private String jd = "";

    /**
     * ˰��������ʼ����
     */
    private String skssksrq = "";

    /**
     * ˰��������������
     */
    private String skssjsrq = "";
    /**
     * ˰����������
     */
    private String skssrq = "";

    /**
     * �걨����
     */
    private String sbrq = "";
    /**
     * ¼������
     */
    private String Lrrq = "";

    /**
     * �걨��ʽ
     */
    private String fsdm = "";

    
    public SbxxVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<sbxx>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbxx>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("fsdm",fsdm);
        xmlstr += XMLBuildUtil.appendStringElement("jd",jd);
        xmlstr += XMLBuildUtil.appendStringElement("nd",nd);
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq);
        xmlstr += XMLBuildUtil.appendStringElement("zgfwjdlx",zgfwjdlx);
        return xmlstr;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }

    public String getJd()
    {
        return jd;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public String getSbrq()
    {
        return sbrq;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

	/**
	 * @description: getter-- zgfwjdlx
	 * @return the zgfwjdlx
	 */
	public String getZgfwjdlx() {
		return zgfwjdlx;
	}
	/**
	 * @description: setter-- zgfwjdlx
	 * @param zgfwjdlx the zgfwjdlx to set
	 */
	public void setZgfwjdlx(String zgfwjdlx) {
		this.zgfwjdlx = zgfwjdlx;
	}
	
	public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
	public String getLrrq() {
		return Lrrq;
	}
	public void setLrrq(String lrrq) {
		Lrrq = lrrq;
	}
	public void setSkssrq(String skssrq) {
		// TODO Auto-generated method stub
		this.skssrq = skssrq;
	}
	public String getSkssrq() {
		return skssrq;
	}


}
