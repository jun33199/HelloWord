package com.syax.bjtax.shenbao.model.kjqysds;


/**
 * <p>Title: ������˰��������ϵͳ�����۽���ҵ����˰-��ϸ��Ϣ</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class MXXXVO implements XMLVOInterface
{
	Map recordMap = new HashMap();
    /**
     * �����Ǽ���ţ���¼������
     */
	private String badjxh;
	
    /**
     * ��ͬ���
     */
	private String htbh;
    
    /**
     * ��ͬ����
     */
    private String htmc;
    
    /**
     * ��ͬǩԼ����
     */
    private String htqyrq;
    
    /**
     * ��ͬ��Ч��
     */
    private String htyxq;
    
    /**
     * ��ͬ���
     */
    private String htje;
    
    /**
     * �����
     */
    private String tbrq;
    
    /**
     * ���״̬
     */
    private String shzt;


    public MXXXVO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<mxxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</mxxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // �����Ǽ���ţ���¼������
        xmlstr.append(XMLBuildUtil.appendStringElement("badjxh", this.badjxh));
        // ��ͬ���
        xmlstr.append(XMLBuildUtil.appendStringElement("htbh", this.htbh));
        // ��ͬ����
        xmlstr.append(XMLBuildUtil.appendStringElement("htmc", this.htmc));
        // ��ͬǩԼ����
        xmlstr.append(XMLBuildUtil.appendStringElement("htqyrq", this.htqyrq));
        // ��ͬ��Ч��
        xmlstr.append(XMLBuildUtil.appendStringElement("htyxq", this.htyxq));
        // ��ͬ���
        xmlstr.append(XMLBuildUtil.appendStringElement("htje", this.htje));
        // �����
        xmlstr.append(XMLBuildUtil.appendStringElement("tbrq", this.tbrq));
        // ���״̬
        xmlstr.append(XMLBuildUtil.appendStringElement("shzt", this.shzt));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }

	public String getBadjxh() {
		return badjxh;
	}

	public String getHtbh() {
		return htbh;
	}

	public String getHtmc() {
		return htmc;
	}

	public String getHtqyrq() {
		return htqyrq;
	}

	public String getHtyxq() {
		return htyxq;
	}

	public String getHtje() {
		return htje;
	}

	public String getTbrq() {
		return tbrq;
	}

	public String getShzt() {
		return shzt;
	}

	public void setBadjxh(String badjxh) {
		this.badjxh = badjxh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public void setHtqyrq(String htqyrq) {
		this.htqyrq = htqyrq;
	}

	public void setHtyxq(String htyxq) {
		this.htyxq = htyxq;
	}

	public void setHtje(String htje) {
		this.htje = htje;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

    

}
