package com.syax.bjtax.shenbao.model.kjqysds;


/**
 * �۽������˻�����Ϣ
 * @author tum
 *
 */

import com.syax.common.xml.XMLVOInterface;
import java.util.Map;
import com.syax.common.xml.util.XMLBuildUtil;


public class KJYWRXX implements XMLVOInterface{

	/**
	 * ���������
	 */
	private String kjrjsjdm;
	/**
	 * �۽���������˰ʶ���
	 */
	private String kjrnssbh;
	/**
	 * �۽�����������
	 */
	private String kjrmc_cn;
	/**
	 * �۽���Ӣ������
	 */
	private String kjrmc_en;
	/**
	 * �۽������ĵ�ַ
	 */
	private String kjrdz_cn;
	/**
	 * �۽��˲�������
	 */
	private String kjrcwfzr;
	/**
	 * ��ϵ��
	 */
	private String kjrlxr;
	/**
	 * ��ϵ�绰
	 */
	private String kjrlxdh;
	/**
	 * �۽�����������
	 */
	private String kjryzbm;
	/**
	 * �۽��˴������
	 */
	private String kjrczhm;


    public KJYWRXX()
    {
        super();
    }

    /**
     * ������ת����XML�ַ���
     * @return String
     */
    public String toXML()
    {
        StringBuffer xmlstr = new StringBuffer("<kjywrxx>");
        xmlstr.append(toXMLChilds());
        xmlstr.append("</kjywrxx>");

        return xmlstr.toString();
    }

    /**
     * ���ڵ���Ϣת����xml�ַ���
     * @return String
     */
    public String toXMLChilds()
    {
        StringBuffer xmlstr = new StringBuffer();
        // �۽������˼��������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrjsjdm", kjrjsjdm));
        //  �۽���������˰ʶ���
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrnssbh", kjrnssbh));
        // �۽�����������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrmc_cn", kjrmc_cn));
        // �۽���Ӣ������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrmc_en", kjrmc_en));
        // �۽������ĵ�ַ
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrdz_cn", kjrdz_cn));
        // �۽��˲�������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrcwfzr", kjrcwfzr));
        // ��ϵ��
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrlxr", kjrlxr));
        // ��ϵ�绰
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrlxdh", kjrlxdh));
        // �۽�����������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjryzbm", kjryzbm));
        // �۽��˴������
        xmlstr.append(XMLBuildUtil.appendObjectElement("kjrczhm", kjrczhm));

        return xmlstr.toString();
    }

    public Map getListTypeMap()
    {
        return null;
    }


	public String getKjrjsjdm() {
		return kjrjsjdm;
	}
	public void setKjrjsjdm(String kjrjsjdm) {
		this.kjrjsjdm = kjrjsjdm;
	}
	public String getKjrnssbh() {
		return kjrnssbh;
	}
	public void setKjrnssbh(String kjrnssbh) {
		this.kjrnssbh = kjrnssbh;
	}

	public String getKjrcwfzr() {
		return kjrcwfzr;
	}
	public void setKjrcwfzr(String kjrcwfzr) {
		this.kjrcwfzr = kjrcwfzr;
	}
	public String getKjrlxr() {
		return kjrlxr;
	}
	public void setKjrlxr(String kjrlxr) {
		this.kjrlxr = kjrlxr;
	}
	public String getKjrlxdh() {
		return kjrlxdh;
	}
	public void setKjrlxdh(String kjrlxdh) {
		this.kjrlxdh = kjrlxdh;
	}
	public String getKjryzbm() {
		return kjryzbm;
	}
	public void setKjryzbm(String kjryzbm) {
		this.kjryzbm = kjryzbm;
	}
	public String getKjrczhm() {
		return kjrczhm;
	}
	public void setKjrczhm(String kjrczhm) {
		this.kjrczhm = kjrczhm;
	}

    public String getKjrdz_cn()
    {
        return kjrdz_cn;
    }

    public String getKjrmc_cn()
    {
        return kjrmc_cn;
    }

    public String getKjrmc_en()
    {
        return kjrmc_en;
    }

    public void setKjrmc_en(String kjrmc_en)
    {
        this.kjrmc_en = kjrmc_en;
    }

    public void setKjrmc_cn(String kjrmc_cn)
    {
        this.kjrmc_cn = kjrmc_cn;
    }

    public void setKjrdz_cn(String kjrdz_cn)
    {
        this.kjrdz_cn = kjrdz_cn;
    }
}
